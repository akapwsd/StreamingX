package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.example.okhttp.WSManager;
import com.example.youyu.api.RtcManager;


public class MainActivity extends Activity implements WSManager.WebSocketDataListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendBtn = findViewById(R.id.send_btn);
        RtcManager.getInstance().initRtc(this);
        WSManager.getInstance().init(this);
    }

    @Override
    public void onClosed(int code, String reason) {

    }

    @Override
    public void onClosing(int code, String reason) {

    }

    @Override
    public void onFailure(String reason) {

    }

    @Override
    public void onMessage(Object data) {

    }

    @Override
    public void onOpen() {

    }
}