package com.code.okhttp;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.code.listener.IRtcEngineEventCallBackHandler;
import com.code.utils.DataUtils;
import com.code.utils.LogUtil;
import com.code.utils.RtcSpUtils;
import com.code.youyu.api.Constants;
import com.code.youyu.api.RtcManager;
import com.google.protobuf.InvalidProtocolBufferException;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import uyujoy.com.api.gateway.frontend.Api;
import uyujoy.com.api.gateway.frontend.Base;

public class WSManager {
    private final String TAG = "WSManager";
    private final static int MAX_RECONNECT_NUM = 5;
    private final static int RECONNECT_MILLS = 5000;
    private final static int GLOBAL_HEART_BEAT_RATE = 5000;
    private final static int ROOM_HEART_BEAT_RATE = 5000;
    private final static String BASE_URL = "wss://api.hitradegate.com/v1/ws";
    private static HashMap<Integer, WeakReference<WebSocketResultListener>> sWeakRefListeners;
    private IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler;
    private WebSocket mWebSocket;
    private OkHttpClient mClient;
    private String mWbSocketUrl;
    private boolean isReceivePong = true;
    private boolean isConnect = false;
    private boolean isCallIng = false;
    public String mChannelId = "";
    public int mClientRole;
    public String mUid = "";
    public int mCallType = 1;
    public String mToken = "";
    private String access_key_id;
    private String access_key_secret;
    private String session_token;
    private int reconnectNum = 0;
    private Handler heartHandler = new Handler();
    private long global_ping_send_time = 0L;

    private static final class SInstanceHolder {
        static final WSManager sInstance = new WSManager();
    }

    public static WSManager getInstance() {

        return SInstanceHolder.sInstance;
    }

    public void setIRtcEngineEventCallBackHandler(IRtcEngineEventCallBackHandler callBackHandler) {
        this.iRtcEngineEventCallBackHandler = callBackHandler;
    }

    /**
     * init WebSocket
     */
    public void init(Context context, String access_key_id, String access_key_secret, String session_token) {
        LogUtil.d(TAG, "init is start");
        if (context != null) {
            sWeakRefListeners = new HashMap<>();
            this.access_key_id = access_key_id;
            this.access_key_secret = access_key_secret;
            this.session_token = session_token;
            mWbSocketUrl = BASE_URL;
            RtcSpUtils.getInstance().setAccessKeyId(access_key_id);
            RtcSpUtils.getInstance().setAccessKeySecret(access_key_secret);
            RtcSpUtils.getInstance().setSessionToken(session_token);
            LogUtil.i(TAG, "mWbSocketUrl=" + mWbSocketUrl);
            mClient = new OkHttpClient.Builder().writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).pingInterval(10, TimeUnit.SECONDS).build();
            connect();
        }
        LogUtil.d(TAG, "init is end");
    }

    public void disconnect(int code, String reason) {
        LogUtil.e(TAG, "disconnect is start code:" + code + " reason:" + reason);
        if (isConnect()) {
            sWeakRefListeners.clear();
            mWebSocket.cancel();
            mWebSocket.close(code, reason);
        }
    }

    private void closeConnect() {
        LogUtil.e(TAG, "closeConnect is start");
        isConnect = false;
        global_ping_send_time = 0L;
        if (heartHandler != null) {
            heartHandler.removeCallbacksAndMessages(null);
            heartHandler = null;
        }
        LogUtil.e(TAG, "closeConnect is end");
    }

    private void reconnect() {
        LogUtil.d(TAG, "reconnect is start reconnectNum:" + reconnectNum);
        if (reconnectNum <= MAX_RECONNECT_NUM) {
            try {
                Thread.sleep(RECONNECT_MILLS);
                connect();
                reconnectNum++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.e(TAG, "reconnect over,please check url or network");
        }
    }

    private boolean isConnect() {
        return mWebSocket != null && isConnect;
    }

    private void connect() {
        LogUtil.d(TAG, "connect is start access_key_id:" + access_key_id + " access_key_secret:" + access_key_secret + " session_token:" + session_token);
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        String Content_Type = "application/json";
        String data = X_Uyj_Timestamp;// + Content_Type;
        String sign = DataUtils.sha256_HMAC(access_key_secret, data);
        LogUtil.d(TAG, "sign:" + sign);
        Request request = new Request.Builder().url(mWbSocketUrl).addHeader("Authorization", "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign).addHeader("Session-Token", session_token).addHeader("X-Uyj-Timestamp", X_Uyj_Timestamp).addHeader("Content-Type", Content_Type).build();
        mWebSocket = mClient.newWebSocket(request, new WsListener());
        LogUtil.d(TAG, "connect is end");
    }

    class WsListener extends WebSocketListener {
        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            LogUtil.e(TAG, "onClosed code:" + code + " reason:" + reason);
            super.onClosed(webSocket, code, reason);
            mWebSocket = null;
            closeConnect();
        }

        @Override
        public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            LogUtil.d(TAG, "onClosing code:" + code + " reason:" + reason);
            super.onClosing(webSocket, code, reason);
            mWebSocket = null;
            closeConnect();
        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
            if (response != null) {
                LogUtil.e(TAG, "websocket onFailure:" + response.message());
            }
            LogUtil.e(TAG, "websocket fail reason：" + t.getMessage());
            if (!TextUtils.isEmpty(t.getMessage()) && !t.getMessage().equals("Socket closed")) {
                reconnect();
            } else {
                closeConnect();
            }
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            super.onMessage(webSocket, text);
            LogUtil.e(TAG, "receive message:" + text);
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
            super.onMessage(webSocket, bytes);
            LogUtil.i(TAG, "onMessage receive bytes:" + Arrays.toString(bytes.toByteArray()));
            Base.messageFrame messageFrame;
            try {
                messageFrame = Base.messageFrame.parseFrom(bytes.toByteArray());
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException(e);
            }
            int crc32 = messageFrame.getCrc32();
            LogUtil.i(TAG, "onMessage receive crc32:" + crc32);
            String OxCrcId = Integer.toHexString(crc32);
            com.google.protobuf.ByteString resultData = messageFrame.getData();
            LogUtil.i(TAG, "onMessage receive OxCrcId:" + OxCrcId);
            switch (OxCrcId) {
                case Constants.PONG:
                    isReceivePong = true;
                    break;
                case Constants.BAN_ROOM:
                    iRtcEngineEventCallBackHandler.banRoom();
                    break;
            }
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            super.onOpen(webSocket, response);
            LogUtil.i(TAG, "websocket is connect:" + response);
            mWebSocket = webSocket;
            isConnect = response.code() == 101;
            if (!isConnect) {
                reconnect();
            } else {
                LogUtil.d(TAG, "websocket is connect success");
                isReceivePong = true;
                heartHandler.post(heartBeatRunnable);
            }
        }
    }

    Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            LogUtil.d(TAG, "heartBeat receive currentTimeMillis:" + currentTimeMillis + " global_ping_send_time:" + global_ping_send_time + " isReceivePong:" + isReceivePong);
            if (heartHandler != null && currentTimeMillis - global_ping_send_time >= GLOBAL_HEART_BEAT_RATE && isReceivePong) {
                isReceivePong = false;
                global_ping_send_time = currentTimeMillis;
                ping();
                heartHandler.postDelayed(this, GLOBAL_HEART_BEAT_RATE);
            } else {
                disconnect(1001, "heart beat is disconnect");
            }
        }
    };

    public void joinChannel(String channelId, String token, int uid, int clientRole, int callType) {
        mChannelId = channelId;
        mUid = String.valueOf(uid);
        mToken = token;
        isCallIng = true;
        mClientRole = clientRole;
        mCallType = callType;
    }

    public void leaveChannel() {
        mChannelId = "";
        mToken = "";
        isCallIng = false;
    }

    protected void ping() {
        LogUtil.d(TAG, "rtc ping is start");
        String channelId = RtcSpUtils.getInstance().getChannelId();
        Api.ping ping;
        Api.ping.Builder builder = Api.ping.newBuilder();
        if (!TextUtils.isEmpty(channelId)) {
            builder.setChannelId(channelId);
        }
        ping = builder.build();
        byte[] bytes = DataUtils.assembleData(0xde174df3, ping.toByteArray());
        LogUtil.d(TAG, "rtc ping send data:" + Arrays.toString(bytes));
        WSManager.getInstance().send(ByteString.of(bytes));
    }

    protected void roomPing() {
        byte[] a = new byte[0];
        //channelId
        WSManager.getInstance().send(ByteString.of(a));
    }

    /**
     * send msg
     */
    public void send(final ByteString message) {
        if (isConnect()) {
            mWebSocket.send(message);
        } else {
            LogUtil.e(TAG, "send fail ,disconnected");
        }
    }

    /**
     * register listener
     */
    public void registerWSDataListener(int tag, WebSocketResultListener listener) {
        if (sWeakRefListeners.get(tag) == null) {
            sWeakRefListeners.put(tag, new WeakReference<>(listener));
        }
    }

    private void eventSuccessWsDataListener(int tag, String data) {
        LogUtil.e(TAG, "eventSuccessWsDataListener is start tag:" + tag);
        WeakReference<WebSocketResultListener> webSocketDataListenerWeakReference = sWeakRefListeners.get(tag);
        if (webSocketDataListenerWeakReference != null) {
            WebSocketResultListener webSocketResultListener = webSocketDataListenerWeakReference.get();
            webSocketResultListener.onSuccess(tag, data);
            unregisterWSDataListener(tag);
        }
    }

    public void eventFailWsDataListener(int tag, int code, String error) {
        LogUtil.e(TAG, "eventFailWsDataListener is start tag:" + tag);
        WeakReference<WebSocketResultListener> webSocketDataListenerWeakReference = sWeakRefListeners.get(tag);
        if (webSocketDataListenerWeakReference != null) {
            WebSocketResultListener webSocketResultListener = webSocketDataListenerWeakReference.get();
            webSocketResultListener.onFailure(code, error);
            unregisterWSDataListener(tag);
        }
    }

    /**
     * unregister listener
     */
    private void unregisterWSDataListener(int tag) {
        LogUtil.e(TAG, "unregisterWSDataListener is start tag:" + tag);
        if (sWeakRefListeners.get(tag) != null) {
            sWeakRefListeners.remove(tag);
        }
    }

    public interface WebSocketResultListener {
        void onSuccess(int code, String data);

        void onFailure(int code, String reason);
    }
}
