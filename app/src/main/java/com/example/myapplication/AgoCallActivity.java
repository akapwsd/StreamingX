package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.okhttp.WSManager;
import com.example.youyu.api.RtcManager;

public class AgoCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ago_call);
        Button acceptBtn = findViewById(R.id.accept_btn);
        Button disagreeBtn = findViewById(R.id.dis_btn);
        acceptBtn.setOnClickListener(view -> {
            RtcManager.getInstance().acceptCall(new WSManager.WebSocketResultListener() {
                @Override
                public void onSuccess(int code, String data) {

                }

                @Override
                public void onFailure(int code, String reason) {

                }
            });
        });
        disagreeBtn.setOnClickListener(view -> {
            RtcManager.getInstance().rejectCall(new WSManager.WebSocketResultListener() {
                @Override
                public void onSuccess(int code, String data) {

                }

                @Override
                public void onFailure(int code, String reason) {

                }
            });
        });
    }
}