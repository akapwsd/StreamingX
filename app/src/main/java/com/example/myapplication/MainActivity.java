package com.example.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import com.example.rtc.WebSocketManager;
import com.example.sokect.JWebSocketClient;
import com.example.sokect.WebSocketService;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebSocketManager.getInstance().bindService(this);
        Button sendBtn = findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(view -> WebSocketManager.getInstance().sendMsg("123"));
    }
}