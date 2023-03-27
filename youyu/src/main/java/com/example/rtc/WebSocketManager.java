package com.example.rtc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.example.sokect.JWebSocketClient;
import com.example.sokect.WebSocketService;
import com.example.youyu.api.WebSocketResultListener;

public class WebSocketManager {
    private static WebSocketManager webSocketManager;
    private WebSocketResultListener mWebSocketResultListener;

    public static WebSocketManager getInstance() {
        if (webSocketManager == null) {
            synchronized (WebSocketManager.class) {
                if (webSocketManager == null) {
                    webSocketManager = new WebSocketManager();
                }
            }
        }
        return webSocketManager;
    }

    private JWebSocketClient client;
    private WebSocketService.JWebSocketClientBinder binder;
    private WebSocketService jWebSClientService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //服务与活动成功绑定
            Log.d("ZHIZHI", "service bind success");
            binder = (WebSocketService.JWebSocketClientBinder) iBinder;
            jWebSClientService = binder.getService();
            client = jWebSClientService.client;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("ZHIZHI", "service disconnect");
        }
    };

    public JWebSocketClient getClient() {
        if (client != null) {
            return client;
        } else {
            return null;
        }
    }

    public void bindService(Context context, WebSocketResultListener webSocketResultListener) {
        Log.d("ZHIZHI", "bindService is start");
        this.mWebSocketResultListener = webSocketResultListener;
        Intent bindIntent = new Intent(context, WebSocketService.class);
        context.bindService(bindIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public WebSocketResultListener getWebSocketResultListener() {
        if (mWebSocketResultListener != null) {
            return mWebSocketResultListener;
        } else {
            return null;
        }
    }

    public void sendMsg(String msg) {
        if (null != client) {
            Log.i("ZHIZHI", "send msg：" + msg);
            try {
                client.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
