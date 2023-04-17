package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.listener.IRtcEngineEventCallBackHandler;
import com.example.okhttp.WSManager;
import com.example.youyu.api.RtcManager;

public class AgoVideoActivity extends AppCompatActivity {
    public FrameLayout smallView;
    public FrameLayout bigView;
    public int localUid = 0;
    public int peerUid = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ago_video);
        Button hangUpBtn = findViewById(R.id.hang_up_btn);
        smallView = findViewById(R.id.small_view);
        bigView = findViewById(R.id.big_view);
        hangUpBtn.setOnClickListener(view -> RtcManager.getInstance().hangUpCall(new WSManager.WebSocketResultListener() {
            @Override
            public void onSuccess(int code, String data) {

            }

            @Override
            public void onFailure(int code, String reason) {

            }
        }));
        RtcManager.getInstance().setIRtcEngineEventCallBackHandler(new IRtcEngineEventCallBackHandler() {
        });
        initVideoView();
    }

    public void initVideoView() {
        RtcManager.getInstance().showLocalView(this, localUid, smallView);
        RtcManager.getInstance().showRemoteView(this, peerUid, bigView);
    }
}