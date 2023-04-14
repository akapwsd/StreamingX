package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.example.listener.RtcRequestEventHandler;
import com.example.okhttp.WSManager;
import com.example.youyu.api.RtcManager;


public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendBtn = findViewById(R.id.send_btn);
        RtcManager.getInstance().initRtc(this, "", new RtcRequestEventHandler() {

        });
    }
}