package com.example.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import com.example.sokect.JWebSocketClient;
import com.example.sokect.WebSocketService;

public class MainActivity extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService();
        Button sendBtn = findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(view -> sendMsg("123"));
    }

    private void bindService() {
        Log.d("ZHIZHI", "bindService is start");
        Intent bindIntent = new Intent(MainActivity.this, WebSocketService.class);
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
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