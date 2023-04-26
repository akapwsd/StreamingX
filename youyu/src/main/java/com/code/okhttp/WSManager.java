package com.code.okhttp;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.code.bean.ModelBean;
import com.code.listener.IRtcEngineEventCallBackHandler;
import com.code.utils.DataUtils;
import com.code.utils.LogUtil;
import com.code.youyu.api.Constants;
import com.code.youyu.api.RtcManager;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import batprotobuf.Streaming;
import io.reactivex.rxjava3.annotations.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WSManager {
    private final String TAG = "WSManager";
    private final static int MAX_RECONNECT_NUM = 5;
    private final static int RECONNECT_MILLS = 5000;
    private final static int GLOBAL_HEART_BEAT_RATE = 5000;
    private final static int ROOM_HEART_BEAT_RATE = 5000;
    private final static String BASE_URL = "wss://api.hitradegate.com/v1/ws";
    private static HashMap<Integer, WeakReference<WebSocketResultListener>> sWeakRefListeners;
    private IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler;
    private static ArrayList<WeakReference<RequestModelListListener>> mRequestListeners;
    private WebSocket mWebSocket;
    private OkHttpClient mClient;
    private String mWbSocketUrl;
    private boolean isReceivePong = true;
    private boolean isReceiveRoomAlivePong = true;
    private boolean isConnect = false;
    private boolean isCallIng = false;
    public String mChannelId = "";
    public String mToken = "";
    private String access_key_id;
    private String access_key_secret;
    private String session_token;
    private int reconnectNum = 0;
    private Handler heartHandler = new Handler();
    private Handler roomAliveHeartHandler = new Handler();
    private long global_ping_send_time = 0L;
    private long room_ping_send_time = 0L;

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
            mRequestListeners = new ArrayList<>();
            this.access_key_id = access_key_id;
            this.access_key_secret = access_key_secret;
            this.session_token = session_token;
            mWbSocketUrl = BASE_URL;
            LogUtil.i(TAG, "mWbSocketUrl=" + mWbSocketUrl);
            mClient = new OkHttpClient.Builder().writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build();
            connect();
        }
        LogUtil.d(TAG, "init is end");
    }

    public void disconnect(int code, String reason) {
        if (isConnect()) {
            sWeakRefListeners.clear();
            mWebSocket.cancel();
            mWebSocket.close(code, reason);
        }
        closeConnect();
    }

    private void closeConnect() {
        isConnect = false;
        global_ping_send_time = 0L;
        if (heartHandler != null) {
            heartHandler.removeCallbacksAndMessages(null);
            heartHandler = null;
        }
        if (roomAliveHeartHandler != null) {
            roomAliveHeartHandler.removeCallbacksAndMessages(null);
            roomAliveHeartHandler = null;
        }
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
            closeConnect();
            if (!TextUtils.isEmpty(t.getMessage()) && !t.getMessage().equals("socket closed")) {
                reconnect();
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
            int type = 0;
            int code = 0;
            String msg = "";
            LogUtil.e(TAG, "onMessage is start receive code:" + code + " type:" + type);
            if (code == 200) {
                switch (type) {
                    case Constants.PONG:
                        isReceivePong = true;
                        break;
                    case Constants.ROOM_PONG:
                        isReceiveRoomAlivePong = true;
                        break;
                    case Constants.BAN_ROOM:
                        iRtcEngineEventCallBackHandler.banRoom();
                        break;
                    case Constants.GET_MODEL_LIST:
                        eventResultModelListener(true, new ArrayList<>(), 0, "");
                        break;
                }
            } else {
                if (type == Constants.GET_MODEL_LIST) {
                    eventResultModelListener(false, new ArrayList<>(), code, msg);
                } else {
                    eventFailWsDataListener(type, code, msg);
                }
            }
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            super.onOpen(webSocket, response);
            LogUtil.e(TAG, "websocket is connect:" + response);
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
            if (currentTimeMillis - global_ping_send_time >= GLOBAL_HEART_BEAT_RATE && isReceivePong) {
                isReceivePong = false;
                global_ping_send_time = currentTimeMillis;
                ping();
                heartHandler.postDelayed(this, GLOBAL_HEART_BEAT_RATE);
            } else {
                disconnect(1001, "disconnect");
            }
        }
    };

    Runnable roomHeartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            if (isCallIng) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - room_ping_send_time >= ROOM_HEART_BEAT_RATE && isReceiveRoomAlivePong) {
                    isReceiveRoomAlivePong = false;
                    room_ping_send_time = currentTimeMillis;
                    ping();
                    roomAliveHeartHandler.postDelayed(this, ROOM_HEART_BEAT_RATE);
                } else {
                    isCallIng = false;
                    RtcManager.getInstance().closeVideoChat();
                }
            }
        }
    };

    public void joinChannel(String channelId, String token) {
        mChannelId = channelId;
        mToken = token;
        isCallIng = true;
        room_ping_send_time = 0L;
        if (roomAliveHeartHandler != null) {
            roomAliveHeartHandler.post(roomHeartBeatRunnable);
        }
    }

    public void leaveChannel() {
        mChannelId = "";
        mToken = "";
        isCallIng = false;
        room_ping_send_time = 0L;
        if (roomAliveHeartHandler != null) {
            roomAliveHeartHandler.removeCallbacksAndMessages(null);
        }
        RtcManager.getInstance().closeVideoChat();
    }

    protected void ping() {
        Streaming.ping ping = Streaming.ping.newBuilder().build();
        WSManager.getInstance().send(ByteString.of(ping.toByteArray()));
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

    public void registerModelListener(RequestModelListListener listener) {
        if (!mRequestListeners.contains(listener)) {
            mRequestListeners.add(new WeakReference<>(listener));
        }
    }

    private void eventResultModelListener(boolean isSuccess, ArrayList<ModelBean> list, int code, String error) {
        Iterator<WeakReference<RequestModelListListener>> iterator = mRequestListeners.iterator();
        while (iterator.hasNext()) {
            WeakReference<RequestModelListListener> ref = iterator.next();
            if (ref == null) {
                break;
            }
            if (ref.get() == null) {
                iterator.remove();
            } else {
                if (isSuccess) {
                    ref.get().onResult(list);
                } else {
                    ref.get().onFailure(code, error);
                }
                unregisterModelListener(ref.get());
            }
            break;
        }
    }

    public void unregisterModelListener(RequestModelListListener listener) {
        Iterator<WeakReference<RequestModelListListener>> iterator = mRequestListeners.iterator();
        while (iterator.hasNext()) {
            WeakReference<RequestModelListListener> ref = iterator.next();
            if (ref == null) {
                break;
            }
            if (ref.get() == null) {
                iterator.remove();
            }
            if (ref.get() == listener) {
                iterator.remove();
                break;
            }
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

    public interface RequestModelListListener {
        void onResult(ArrayList<ModelBean> dataList);

        void onFailure(int code, String reason);
    }
}
