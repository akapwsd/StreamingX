package com.code.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.code.bean.MsgBean;
import com.code.listener.ChannelMsgListener;
import com.code.listener.IRtcEngineEventCallBackHandler;
import com.code.utils.LogUtil;
import com.code.youyu.api.StreamingXRtcManager;

public class AgoVideoActivity extends Activity {
    public FrameLayout smallView;
    public FrameLayout bigView;
    public String sendFp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ago_video);
        Button hangUpBtn = findViewById(R.id.hang_up_btn);
        Button sendMsgBtn = findViewById(R.id.send_msg_btn);
        smallView = findViewById(R.id.small_view);
        bigView = findViewById(R.id.big_view);
        StreamingXRtcManager.getInstance().setIRtcEngineEventCallBackHandler(new IRtcEngineEventCallBackHandler() {
            @Override
            public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
                LogUtil.d("TEST", "onJoinChannelSuccess channel:" + channel + " uid:" + uid);
            }

            @Override
            public void onUserJoined(int uid, int elapsed) {
                LogUtil.d("TEST", "onUserJoined uid:" + uid);
                runOnUiThread(() -> StreamingXRtcManager.getInstance().showRemoteView(AgoVideoActivity.this, uid, bigView));
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
            public void receiveMsg(MsgBean msg) {

            }
        });
        initVideoView();
        hangUpBtn.setOnClickListener(view -> {
            StreamingXRtcManager.getInstance().closeVideoChat();
            finish();
        });
        sendMsgBtn.setOnClickListener(view -> sendFp = StreamingXRtcManager.getInstance().sendMsg("123456", new ChannelMsgListener() {
            @Override
            public void sendSuccess(String fp) {
                if (sendFp.equals(fp)) {
                    Toast.makeText(AgoVideoActivity.this, "send success", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AgoVideoActivity.this, "send fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void sendFail(int code, String error) {

            }
        }));
    }

    public void initVideoView() {
        StreamingXRtcManager.getInstance().showLocalView(this, smallView);
    }
}