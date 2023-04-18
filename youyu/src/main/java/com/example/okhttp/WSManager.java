package com.example.okhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.bean.ModelBean;
import com.example.listener.RtcRequestEventHandler;
import com.example.rtc.BaseRtcEngineManager;
import com.example.utils.LogUtil;
import com.example.youyu.api.Constants;
import com.example.youyu.api.RtcManager;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import batprotobuf.Streaming;
import io.agora.rtc.RtcEngine;
import io.reactivex.rxjava3.annotations.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WSManager {
    private final String TAG = "WSManager";
    private static HashMap<Integer, WeakReference<WebSocketResultListener>> sWeakRefListeners;

    private static ArrayList<WeakReference<RequestModelListListener>> mRequestListeners;
    private WebSocket mWebSocket;
    private OkHttpClient mClient;
    private String mWbSocketUrl;
    private boolean isReceivePong = true;
    private boolean isReceiveRoomAlivePong = true;

    private boolean isCallIng = false;
    private Context mContext;
    public String channelId = "";
    public String token = "";
    private final ConcurrentHashMap<RtcRequestEventHandler, Integer> mRtcHandlers = new ConcurrentHashMap();

    private static final class SInstanceHolder {
        static final WSManager sInstance = new WSManager();
    }

    public static WSManager getInstance() {

        return SInstanceHolder.sInstance;
    }


    /**
     * init WebSocket
     */
    public void init(Context context) {
        LogUtil.d(TAG, "init is start");
        if (context != null) {
            mContext = context;
            sWeakRefListeners = new HashMap<>();
            mRequestListeners = new ArrayList<>();
            mWbSocketUrl = "ws://echo.websocket.org";
            Log.e(TAG, "mWbSocketUrl=" + mWbSocketUrl);
            mClient = new OkHttpClient.Builder().pingInterval(10, TimeUnit.SECONDS).build();
            connect();
        }
        LogUtil.d(TAG, "init is end");
    }

    public void create(RtcRequestEventHandler rtcRequestEventHandler) {
        addHandler(rtcRequestEventHandler);
    }

    public void connect() {
        LogUtil.d(TAG, "connect is start");
        Request request = new Request.Builder().url(mWbSocketUrl).build();
        mWebSocket = mClient.newWebSocket(request, new WsListener());
        LogUtil.d(TAG, "connect is end");
    }

    class WsListener extends WebSocketListener {
        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            LogUtil.e(TAG, "onClosed code:" + code + " reason:" + reason);
            super.onClosed(webSocket, code, reason);
            if (code == 1001) {
                LogUtil.e(TAG, "disconnect");
                connect();
            }
        }

        @Override
        public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            LogUtil.d(TAG, "onClosing code:" + code + " reason:" + reason);
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
            LogUtil.e(TAG, "onFailure！" + t.getMessage());
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
                    case Constants.START_CALL:
                    case Constants.ACCEPT_CALL:
                        channelId = "channelId";
                        token = "token";
                        isCallIng = true;
                        roomAliveHeartHandler.sendEmptyMessage(11);
                        eventSuccessWsDataListener(type, msg);
                        break;
                    case Constants.HANG_UP:
                    case Constants.REJECT_CALL:
                        channelId = "";
                        token = "";
                        isCallIng = false;
                        RtcManager.getInstance().closeVideoChat();
                        eventSuccessWsDataListener(type, msg);
                        break;
                    case Constants.GET_MODEL_LIST:
                        eventResultModelListener(true, new ArrayList<>(), 0, "");
                        break;
                    case Constants.REQUEST_NEW_TOKEN:
                        String newToken = "";
                        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
                        rtcEngine.renewToken(newToken);
                        break;
                    default:
                        onEvent(type, bytes.toByteArray());
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
            LogUtil.e(TAG, "connect success！");
            mWebSocket = webSocket;
            isReceivePong = true;
            heartHandler.sendEmptyMessage(10);
        }
    }

    /**
     * send room alive ping
     */
    Handler roomAliveHeartHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what != 11) return false;
            if (isCallIng) {
                if (isReceiveRoomAlivePong) {
                    roomPing();
                    isReceiveRoomAlivePong = false;
                    roomAliveHeartHandler.sendEmptyMessageDelayed(11, 10000);
                } else {
                    isCallIng = false;
                    RtcManager.getInstance().closeVideoChat();
                }
            }
            return false;
        }
    });
    /**
     * send rtc all ping
     */
    Handler heartHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what != 10) return false;
            if (isReceivePong) {
                ping();
                isReceivePong = false;
                heartHandler.sendEmptyMessageDelayed(10, 10000);
            } else {
                closeConnect(1001, "disconnect");
            }
            return false;
        }
    });

    protected void ping() {
        Streaming.ping ping = Streaming.ping.newBuilder().build();
        WSManager.getInstance().send(ByteString.of(ping.toByteArray()));
    }

    protected void roomPing() {
        byte[] a = new byte[0];
        WSManager.getInstance().send(ByteString.of(a));
    }

    /**
     * send msg
     */
    public void send(final ByteString message) {
        if (mWebSocket != null) {
            mWebSocket.send(message);
        }
    }

    /**
     * go disconnect
     */
    public void disconnect(int code, String reason) {
        if (mWebSocket != null) sWeakRefListeners.clear();
        assert mWebSocket != null;
        mWebSocket.close(code, reason);
    }

    public void closeConnect(int code, String reason) {
        if (mWebSocket != null) sWeakRefListeners.clear();
        assert mWebSocket != null;
        mWebSocket.close(code, reason);
    }

    public void registerModelListener(RequestModelListListener listener) {
        if (!mRequestListeners.contains(listener)) {
            mRequestListeners.add(new WeakReference<>(listener));
        }
    }

    public void eventResultModelListener(boolean isSuccess, ArrayList<ModelBean> list, int code, String error) {
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

    private void unregisterModelListener(RequestModelListListener listener) {
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

    public void eventSuccessWsDataListener(int tag, String data) {
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

    public void addHandler(RtcRequestEventHandler handler) {
        LogUtil.e(TAG, "addHandler add one handler");
        this.mRtcHandlers.put(handler, 0);
    }

    public void removeHandler(RtcRequestEventHandler handler) {
        LogUtil.e(TAG, "removeHandler one handler");
        if (this.mRtcHandlers.containsKey(handler)) {
            this.mRtcHandlers.remove(handler);
        }

    }

    protected void onEvent(int eventId, byte[] evt) {
        try {
            Iterator<RtcRequestEventHandler> var3 = this.mRtcHandlers.keySet().iterator();
            while (var3.hasNext()) {
                RtcRequestEventHandler var4 = var3.next();
                if (var4 == null) {
                    var3.remove();
                } else {
                    this.handleEvent(eventId, evt, var4);
                }
            }
        } catch (Exception var5) {
            LogUtil.e(TAG, "onEvent: " + var5);
        }

    }

    protected void handleEvent(int eventId, byte[] evt, RtcRequestEventHandler handler) {
        LogUtil.e(TAG, "handleEvent is start eventId:" + eventId);
        switch (eventId) {
            case 1001:
                ModelBean modelBean = new ModelBean();
                handler.onReceiveCall(modelBean);
                break;
            case 1002:
                handler.onReceiveHangUp(evt);
                break;
            case 1003:
                handler.onPeerUserAcceptCall(evt);
                break;
            case 1004:
                handler.onPeerUserDisAgreeCall(evt);
                break;
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
