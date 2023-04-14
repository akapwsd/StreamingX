package com.example.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.utils.LogUtil;
import com.example.youyu.api.RtcManager;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WSManager {
    private final String TAG = "WSManager";
    private static ArrayList<WeakReference<WebSocketDataListener>> sWeakRefListeners;
    private WebSocket mWebSocket;
    private OkHttpClient mClient;
    private String mWbSocketUrl;
    private boolean isReceivePong;

    private static final class SInstanceHolder {
        static final WSManager sInstance = new WSManager();
    }

    public static WSManager getInstance() {

        return SInstanceHolder.sInstance;
    }


    /**
     * init WebSocket
     */
    public void init() {
        sWeakRefListeners = new ArrayList<>();
        mWbSocketUrl = "ws://echo.websocket.org";
        Log.e(TAG, "mWbSocketUrl=" + mWbSocketUrl);
        mClient = new OkHttpClient.Builder()
                .pingInterval(10, TimeUnit.SECONDS)
                .build();
        connect();
    }

    public void connect() {
        Request request = new Request.Builder()
                .url(mWbSocketUrl)
                .build();
        mWebSocket = mClient.newWebSocket(request, new WsListener());
    }

    class WsListener extends WebSocketListener {
        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            Log.e(TAG, "onClosed！");
            super.onClosed(webSocket, code, reason);
            wsOnClosed(code, reason);
            //断线重连
            if (code == 1001) {
                Log.e(TAG, "断线重连！");
                connect();
            }
        }

        @Override
        public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            super.onClosing(webSocket, code, reason);
            wsOnClosing(code, reason);
        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
            Log.e(TAG, "onFailure！" + t.getMessage());
            wsOnFailure(t.getMessage());
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            super.onMessage(webSocket, text);
            Log.e(TAG, "receive message:" + text);
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
            super.onMessage(webSocket, bytes);
            Log.e(TAG, "receive bytes:" + bytes);
            wsOnMessage(bytes);
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            super.onOpen(webSocket, response);
            LogUtil.e(TAG, "connect success！");
            wsOnOpen();
            mWebSocket = webSocket;
            isReceivePong = true;
            heartHandler.sendEmptyMessage(10);
        }
    }

    // send ping
    Handler heartHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what != 10) return false;
            if (isReceivePong) {
                RtcManager.getInstance().ping();
                isReceivePong = false;
                heartHandler.sendEmptyMessageDelayed(10, 10000);
            } else {
                closeConnect(1001, "disconnect");
            }
            return false;
        }
    });


//    /**
//     * send msg
//     */
//    public void send(final String message) {
//        if (mWebSocket != null) {
//            mWebSocket.send(message);
//        }
//    }

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
    public void disconnect(int code, String reason, WebSocketDataListener webSocketDataListener) {
        if (mWebSocket != null)
            unregisterWSDataListener(webSocketDataListener);
        assert mWebSocket != null;
        mWebSocket.close(code, reason);
    }

    public void closeConnect(int code, String reason) {
        if (mWebSocket != null)
            sWeakRefListeners.clear();
        assert mWebSocket != null;
        mWebSocket.close(code, reason);
    }

    /**
     * register listener
     */
    public void registerWSDataListener(WebSocketDataListener listener) {
        if (!sWeakRefListeners.contains(listener)) {
            sWeakRefListeners.add(new WeakReference<>(listener));
        }
    }

    /**
     * unregister listener
     */
    private void unregisterWSDataListener(WebSocketDataListener listener) {
        Iterator<WeakReference<WebSocketDataListener>> iterator = sWeakRefListeners.iterator();
        while (iterator.hasNext()) {
            WeakReference<WebSocketDataListener> ref = iterator.next();
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

    private void wsOnClosed(int code, String reason) {
        for (WeakReference<WebSocketDataListener> info : sWeakRefListeners) {
            if (info.get() != null) {
                info.get().onClosed(code, reason);
            }
        }
    }

    private void wsOnOpen() {
        for (WeakReference<WebSocketDataListener> info : sWeakRefListeners) {
            if (info.get() != null) {
                info.get().onOpen();
            }
        }
    }

    private void wsOnClosing(int code, String reason) {
        for (WeakReference<WebSocketDataListener> info : sWeakRefListeners) {
            if (info.get() != null) {
                info.get().onClosing(code, reason);
            }
        }
    }

    private void wsOnMessage(Object data) {
        for (WeakReference<WebSocketDataListener> info : sWeakRefListeners) {
            if (info.get() != null) {
                info.get().onMessage(data);
            }
        }
    }

    private void wsOnFailure(String reason) {
        for (WeakReference<WebSocketDataListener> info : sWeakRefListeners) {
            if (info.get() != null) {
                info.get().onFailure(reason);
            }
        }
    }

    public interface WebSocketDataListener {
        void onClosed(int code, String reason);

        void onClosing(int code, String reason);

        void onFailure(String reason);

        void onMessage(Object data);

        void onOpen();
    }
}
