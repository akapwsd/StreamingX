package com.code.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.code.listener.IRtcEngineEventCallBackHandler;
import com.code.utils.LogUtil;
import com.code.youyu.api.RtcManager;

public class AgoVideoActivity extends Activity {
    public FrameLayout smallView;
    public FrameLayout bigView;
    public int localUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ago_video);
        Button hangUpBtn = findViewById(R.id.hang_up_btn);
        smallView = findViewById(R.id.small_view);
        bigView = findViewById(R.id.big_view);
        Button renewToken = findViewById(R.id.renew_token);
        renewToken.setOnClickListener(view -> RtcManager.getInstance().requestNewToken());
        localUid = getIntent().getIntExtra("localUid", 0);
        RtcManager.getInstance().setIRtcEngineEventCallBackHandler(new IRtcEngineEventCallBackHandler() {
            @Override
            public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
                LogUtil.d("TEST", "onJoinChannelSuccess channel:" + channel + " uid:" + uid);
            }

            @Override
            public void onUserJoined(int uid, int elapsed) {
                LogUtil.d("TEST", "onUserJoined uid:" + uid);
                runOnUiThread(() -> RtcManager.getInstance().showRemoteView(AgoVideoActivity.this, uid, bigView));
            }

            @Override
            public void onUserOffline(int uid, int reason) {
                LogUtil.d("TEST", "onUserOffline uid:" + uid);
            }

            @Override
            public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {
                LogUtil.d("TEST", "onFirstRemoteVideoDecoded uid:" + uid);
            }

            @Override
            public void banRoom() {
                LogUtil.d("TEST", "banRoom");
            }
        });
        initVideoView();
        hangUpBtn.setOnClickListener(view -> {
            RtcManager.getInstance().closeVideoChat();
            finish();
        });
    }

    public void initVideoView() {
        RtcManager.getInstance().showLocalView(this, localUid, smallView);
    }
}