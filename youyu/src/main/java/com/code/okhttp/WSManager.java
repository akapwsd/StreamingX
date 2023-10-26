package com.code.okhttp;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.code.bean.MsgBean;
import com.code.listener.ChannelMsgListener;
import com.code.listener.IRtcEngineEventCallBackHandler;
import com.code.msg.NettyMsg;
import com.code.utils.DataUtils;
import com.code.utils.LogUtil;
import com.code.utils.RtcSpUtils;
import com.code.youyu.api.Constants;
import com.code.youyu.api.StreamingXRtcManager;
import com.google.protobuf.InvalidProtocolBufferException;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import uyujoy.api.channelim.frontend.ChannelIm;
import uyujoy.com.api.channel.frontend.ChannelBase;
import uyujoy.com.api.channel.frontend.ChannelImform;
import uyujoy.com.api.gateway.frontend.Api;
import uyujoy.com.api.gateway.frontend.Base;

public class WSManager {
    private final String TAG = "WSManager";
    private final static int MAX_RECONNECT_NUM = 5;
    private final static int RECONNECT_MILLS = 1000;
    private final static int GLOBAL_HEART_BEAT_RATE = 5000;
    private final static int MAX_DIS_RECEIVE_NUM = 1;
    private int disReceivePongIndex = 0;
    private final static String BASE_URL = "wss://api.streamingxapp.com/v1/ws";
    private final static String BASE_TEST_URL = "wss://api.hitradegate.com/v1/ws";
    private final static String MODEL_BASE_URL = "wss://broadcaster.streamingxapp.com/v1/ws";
    private final static String MODEL_BASE_TEST_URL = "wss://broadcaster.hitradegate.com/v1/ws";
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
    private String token;
    private int reconnectNum = 0;
    private Handler heartHandler;
    private ChannelMsgListener channelMsgListener;
    private Request request;

    private static final class SInstanceHolder {
        static final WSManager sInstance = new WSManager();
    }

    public static WSManager getInstance() {

        return SInstanceHolder.sInstance;
    }

    public void setIRtcEngineEventCallBackHandler(IRtcEngineEventCallBackHandler callBackHandler) {
        this.iRtcEngineEventCallBackHandler = callBackHandler;
    }

    public void renewToken(String newToken) {
        LogUtil.d(TAG, "renewToken1 is start");
        heartHandler.removeCallbacksAndMessages(null);
        token = newToken;
        access_key_id = "";
        access_key_secret = "";
        session_token = "";
        RtcSpUtils.getInstance().setToken(token);
        RtcSpUtils.getInstance().setAccessKeyId("");
        RtcSpUtils.getInstance().setAccessKeySecret("");
        RtcSpUtils.getInstance().setSessionToken("");
        if (request != null) {
            request = null;
            request = new Request.Builder().url(mWbSocketUrl).addHeader("Authorization", token).build();
            mWebSocket.close(1003, "renew token");
            mWebSocket = null;
            mWebSocket = mClient.newWebSocket(request, new WsListener());
        }
        LogUtil.d(TAG, "renewToken1 is end");
    }

    public void renewToken(String access_key_id, String access_key_secret, String session_token) {
        LogUtil.d(TAG, "renewToken2 is start");
        heartHandler.removeCallbacksAndMessages(null);
        this.access_key_id = access_key_id;
        this.access_key_secret = access_key_secret;
        this.session_token = session_token;
        token = "";
        RtcSpUtils.getInstance().setAccessKeyId(access_key_id);
        RtcSpUtils.getInstance().setAccessKeySecret(access_key_secret);
        RtcSpUtils.getInstance().setSessionToken(session_token);
        RtcSpUtils.getInstance().setToken("");
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        String Content_Type = "application/json";
        String data = X_Uyj_Timestamp + Content_Type;
        String sign = DataUtils.sha256_HMAC(access_key_secret, data);
        LogUtil.d(TAG, "sign:" + sign);
        if (request != null) {
            request = null;
            request = new Request.Builder().url(mWbSocketUrl).addHeader("Authorization", "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign).addHeader("Session-Token", session_token).addHeader("X-Uyj-Timestamp", X_Uyj_Timestamp).addHeader("Content-Type", Content_Type).build();
            mWebSocket.cancel();
            mWebSocket.close(1003, "renew token");
            mWebSocket = null;
            mWebSocket = mClient.newWebSocket(request, new WsListener());
        }
        LogUtil.d(TAG, "renewToken2 is end");
    }

    /**
     * init WebSocket
     */
    public void init(Context context, String access_key_id, String access_key_secret, String session_token) {
        LogUtil.d(TAG, "init is start");
        if (context != null) {
            sWeakRefListeners = new HashMap<>();
            heartHandler = new Handler();
            this.access_key_id = access_key_id;
            this.access_key_secret = access_key_secret;
            this.session_token = session_token;
            if (StreamingXRtcManager.getInstance().isEnableDebug) {
                mWbSocketUrl = BASE_TEST_URL;
            } else {
                mWbSocketUrl = BASE_URL;
            }
            RtcSpUtils.getInstance().setAccessKeyId(access_key_id);
            RtcSpUtils.getInstance().setAccessKeySecret(access_key_secret);
            RtcSpUtils.getInstance().setSessionToken(session_token);
            RtcSpUtils.getInstance().setToken("");
            LogUtil.i(TAG, "mWbSocketUrl=" + mWbSocketUrl);
            mClient = new OkHttpClient.Builder().writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).pingInterval(10, TimeUnit.SECONDS).build();
            connect();
        }
        LogUtil.d(TAG, "init is end");
    }

    public void init(Context context, String token) {
        LogUtil.d(TAG, "init is start");
        if (context != null) {
            sWeakRefListeners = new HashMap<>();
            heartHandler = new Handler();
            this.token = token;
            if (StreamingXRtcManager.getInstance().isEnableDebug) {
                mWbSocketUrl = MODEL_BASE_TEST_URL;
            } else {
                mWbSocketUrl = MODEL_BASE_URL;
            }
            RtcSpUtils.getInstance().setToken(token);
            RtcSpUtils.getInstance().setAccessKeyId("");
            RtcSpUtils.getInstance().setAccessKeySecret("");
            RtcSpUtils.getInstance().setSessionToken("");
            LogUtil.i(TAG, "mWbSocketUrl=" + mWbSocketUrl);
            mClient = new OkHttpClient.Builder().writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).pingInterval(10, TimeUnit.SECONDS).build();
            connect();
        }
        LogUtil.d(TAG, "init is end");
    }

    public void disconnect(int code, String reason) {
        LogUtil.e(TAG, "disconnect is start code:" + code + " reason:" + reason + " isConnect:" + isConnect());
        if (isConnect()) {
            sWeakRefListeners.clear();
            heartHandler.removeCallbacksAndMessages(null);
            heartHandler = null;
            mWebSocket.cancel();
            mWebSocket.close(code, reason);
        }
    }

    private void closeConnect() {
        LogUtil.e(TAG, "closeConnect is start isCallIng:" + isCallIng);
        isConnect = false;
        reconnectNum = 0;
        token = "";
        session_token = "";
        access_key_secret = "";
        access_key_id = "";
        request = null;
        mWebSocket = null;
        if (heartHandler != null) {
            heartHandler.removeCallbacksAndMessages(null);
            heartHandler = null;
        }
        if (isCallIng) {
            isCallIng = false;
            StreamingXRtcManager.getInstance().closeVideoChat();
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
            closeConnect();
        }
    }

    private boolean isConnect() {
        return mWebSocket != null && isConnect;
    }

    private void connect() {
        if (!TextUtils.isEmpty(token)) {
            LogUtil.d(TAG, "connect is start token:" + token);
            if (request == null) {
                request = new Request.Builder().url(mWbSocketUrl).addHeader("Authorization", token).build();
            }
        } else {
            LogUtil.d(TAG, "connect is start access_key_id:" + access_key_id + " access_key_secret:" + access_key_secret + " session_token:" + session_token);
            long currentTimeMillis = System.currentTimeMillis();
            String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
            String Content_Type = "application/json";
            String data = X_Uyj_Timestamp + Content_Type;
            String sign = DataUtils.sha256_HMAC(access_key_secret, data);
            LogUtil.d(TAG, "sign:" + sign);
            if (request == null) {
                request = new Request.Builder().url(mWbSocketUrl).addHeader("Authorization", "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign).addHeader("Session-Token", session_token).addHeader("X-Uyj-Timestamp", X_Uyj_Timestamp).addHeader("Content-Type", Content_Type).build();
            }
        }
        mWebSocket = mClient.newWebSocket(request, new WsListener());
        LogUtil.d(TAG, "connect is end");
    }

    class WsListener extends WebSocketListener {
        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            LogUtil.e(TAG, "onClosed code:" + code + " reason:" + reason);
            super.onClosed(webSocket, code, reason);
            mWebSocket = null;
        }

        @Override
        public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            LogUtil.e(TAG, "onClosing code:" + code + " reason:" + reason);
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
            if (!TextUtils.isEmpty(t.getMessage()) && !Objects.equals(t.getMessage(), "Socket closed") && !Objects.equals(t.getMessage(), "Canceled") && !Objects.requireNonNull(t.getMessage()).equals("Socket is closed")) {
                reconnect();
            } else {
                disconnect(1004, "socket connect fail");
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
            byte[] resultData = messageFrame.getData().toByteArray();
            LogUtil.i(TAG, "onMessage receive OxCrcId:" + OxCrcId);
            try {
                switch (OxCrcId) {
                    case Constants.PONG:
                        isReceivePong = true;
                        break;
                    case Constants.BAN_USER_ROOM:
                        ChannelImform.channelUserStateChange channelUserStateChange = ChannelImform.channelUserStateChange.parseFrom(resultData);
                        String uid = channelUserStateChange.getChu().getUid();
                        if (uid.equals(mUid)) {
                            StreamingXRtcManager.getInstance().closeVideoChat();
                        }
                        iRtcEngineEventCallBackHandler.banRoom(uid, channelUserStateChange.getReason());
                        break;
                    case Constants.CLOSE_ROOM:
                        ChannelImform.channelStateChange channelStateChange = ChannelImform.channelStateChange.parseFrom(resultData);
                        ChannelBase.channel ch = channelStateChange.getCh();
                        String serverChannel = ch.getId();
                        String channelId = RtcSpUtils.getInstance().getChannelId();
                        StreamingXRtcManager.getInstance().closeVideoChat();
                        if (!TextUtils.isEmpty(channelId) && channelId.equals(serverChannel)) {
                            iRtcEngineEventCallBackHandler.closeRoom(channelStateChange.getReason());
                        }
                        break;
                    case Constants.MSG_RECORD_ACK:
                        ChannelIm.channelMsgRecordAck channelMsgRecordAck = ChannelIm.channelMsgRecordAck.parseFrom(resultData);
                        if (channelMsgListener != null) {
                            channelMsgListener.sendSuccess(channelMsgRecordAck.getFp());
                        }
                        break;
                    case Constants.CHANNEL_MSG_RECORD:
                        ChannelIm.rcvChannelMsgRecord rcvChannelMsgRecord = ChannelIm.rcvChannelMsgRecord.parseFrom(resultData);
                        LogUtil.d(TAG, "CHANNEL_MSG_RECORD rcvChannelMsgRecord:" + rcvChannelMsgRecord);
                        MsgBean currentReceiveMsg = new MsgBean();
                        currentReceiveMsg.setMsg(rcvChannelMsgRecord.getMsg().getMsg());
                        currentReceiveMsg.setSendTime(rcvChannelMsgRecord.getMsg().getSendTime());
                        currentReceiveMsg.setFp(rcvChannelMsgRecord.getMsg().getMsgFp());
                        currentReceiveMsg.setChannelId(rcvChannelMsgRecord.getMsg().getChannelId());
                        currentReceiveMsg.setFrom(rcvChannelMsgRecord.getMsg().getFrom());
                        iRtcEngineEventCallBackHandler.receiveMsg(currentReceiveMsg);
                        break;
//                    case Constants.GET_DIFF_MSG_RECORD_ACK:
//                        break;
                    case Constants.CHANNEL_MATCH:
                        ChannelImform.channelMatched channelMatched = ChannelImform.channelMatched.parseFrom(resultData);
                        iRtcEngineEventCallBackHandler.receiveMatch(channelMatched);
                        break;
                    case Constants.CHANNEL_SKIP:
                        ChannelImform.channelSkipped channelSkipped = ChannelImform.channelSkipped.parseFrom(resultData);
                        iRtcEngineEventCallBackHandler.receiveSkip(channelSkipped.getChannelId());
                        break;
                    case Constants.DEVICE_UPDATED:
                        ChannelImform.deviceUpdated deviceUpdated = ChannelImform.deviceUpdated.parseFrom(resultData);
                        iRtcEngineEventCallBackHandler.deviceUpdated(deviceUpdated.getIp());
                        break;
                    case Constants.CONNECT_ERROR:
                        iRtcEngineEventCallBackHandler.connectError();
                        break;
                }
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException(e);
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
                if (heartHandler != null && heartBeatRunnable != null) {
                    heartHandler.post(heartBeatRunnable);
                }
            }
        }
    }

    Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            LogUtil.d(TAG, "heartBeat receive isReceivePong:" + isReceivePong);
            if (heartHandler != null) {
                if (isReceivePong) {
                    isReceivePong = false;
                    disReceivePongIndex = 0;
                    ping();
                    heartHandler.postDelayed(this, GLOBAL_HEART_BEAT_RATE);
                } else {
                    if (disReceivePongIndex <= MAX_DIS_RECEIVE_NUM) {
                        disReceivePongIndex++;
                        ping();
                        heartHandler.postDelayed(this, GLOBAL_HEART_BEAT_RATE);
                    } else {
                        isReceivePong = true;
                        disReceivePongIndex = 0;
                        reconnect();
                    }
                }
            } else {
                disconnect(1001, "heart beat is disconnect reason:heartHandler is null ERROR!");
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
        String channelId = RtcSpUtils.getInstance().getChannelId();
        LogUtil.d(TAG, "rtc ping is start channelId:" + channelId);
        Api.ping ping;
        Api.ping.Builder builder = Api.ping.newBuilder();
        if (!TextUtils.isEmpty(channelId)) {
            builder.setChannelId(channelId);
        }
        ping = builder.build();
        byte[] bytes = DataUtils.assembleData(0x85e792c3, ping.toByteArray());
        LogUtil.d(TAG, "rtc ping send data:" + Arrays.toString(bytes));
        send(ByteString.of(bytes));
    }

    public String sendMsg(int localUid, String msg, ChannelMsgListener channelMsgListener) {
        LogUtil.d(TAG, "send messenger");
        this.channelMsgListener = channelMsgListener;
        String channelId = RtcSpUtils.getInstance().getChannelId();
        if (!TextUtils.isEmpty(channelId)) {
            String msgFp = String.valueOf(NettyMsg.getInstance().getMessageID());
            ChannelIm.channelMsgRecord.Builder channelMsgRecord = ChannelIm.channelMsgRecord.newBuilder();
            channelMsgRecord.setMsgFp(msgFp);
            channelMsgRecord.setFrom(String.valueOf(localUid));
            channelMsgRecord.setChannelId(channelId);
            channelMsgRecord.setMsg(msg);
            long currentTimeMillis = System.currentTimeMillis();
            channelMsgRecord.setSendTime(currentTimeMillis);
            byte[] bytes = DataUtils.assembleData(0xc60f6256, channelMsgRecord.build().toByteArray());
            LogUtil.d(TAG, "rtc sendMsg data:" + Arrays.toString(bytes));
            send(ByteString.of(bytes));
            return msgFp;
        } else {
            return "channel does not exist.";
        }
    }

    private void getDiffMsg(int msgId) {
        LogUtil.d(TAG, "get different messenger msgId:" + msgId);
        String channelId = RtcSpUtils.getInstance().getChannelId();
        ChannelIm.getDiffChannelMsgRecord.Builder getDiffChannelMsgRecord = ChannelIm.getDiffChannelMsgRecord.newBuilder();
        getDiffChannelMsgRecord.setChannelId(channelId);
        getDiffChannelMsgRecord.setMsgId(msgId);
        byte[] bytes = DataUtils.assembleData(0xebb76c1e, getDiffChannelMsgRecord.build().toByteArray());
        send(ByteString.of(bytes));
    }

    /**
     * send msg
     */
    protected void send(final ByteString message) {
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
