package com.code.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.code.bean.ModelBean;
import com.code.listener.RtcRequestEventHandler;
import com.code.youyu.api.RtcManager;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendBtn = findViewById(R.id.send_btn);
        RtcManager.getInstance().setRtcRequestEventHandler(new RtcRequestEventHandler() {
            @Override
            public void onReceiveCall(ModelBean modelBean) {
                startActivity(new Intent(MainActivity.this, AgoCallActivity.class));
            }

            @Override
            public void onReceiveHangUp(byte[] evt) {
                super.onReceiveHangUp(evt);
            }

            @Override
            public void onPeerUserAcceptCall(byte[] evt) {
                super.onPeerUserAcceptCall(evt);
            }

            @Override
            public void onPeerUserDisAgreeCall(byte[] evt) {
                super.onPeerUserDisAgreeCall(evt);
            }
        });
    }
}