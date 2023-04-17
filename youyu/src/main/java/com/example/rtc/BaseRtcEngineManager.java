package com.example.rtc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.listener.IRtcEngineEventCallBackHandler;
import com.example.rawdata.MediaDataObserverPlugin;
import com.example.youyu.api.RtcManager;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoEncoderConfiguration;

public class BaseRtcEngineManager {
    public static final String TAG = "BaseRtcEngineManager";
    private IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler;
    private static BaseRtcEngineManager baseRtcEngineManager;
    private RtcEngine mRtcEngine;

    public static BaseRtcEngineManager getInstance() {
        if (baseRtcEngineManager == null) {
            synchronized (BaseRtcEngineManager.class) {
                if (baseRtcEngineManager == null) {
                    baseRtcEngineManager = new BaseRtcEngineManager();
                }
            }
        }
        return baseRtcEngineManager;
    }

    public RtcEngine getRtcEngine() {
        return mRtcEngine;
    }

    public void setIRtcEngineEventCallBackHandler(IRtcEngineEventCallBackHandler callBackHandler) {
        this.iRtcEngineEventCallBackHandler = callBackHandler;
    }

    public void initBaseRtc(Context context) {
        try {
            mRtcEngine = RtcEngine.create(context, "", mRtcEventHandler);
            mRtcEngine.enableVideo();
            mRtcEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(
                    VideoEncoderConfiguration.VD_640x360,
                    VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15,
                    VideoEncoderConfiguration.STANDARD_BITRATE,
                    VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onFacePositionChanged(int imageWidth, int imageHeight, AgoraFacePositionInfo[] agoraFacePositionInfos) {
            iRtcEngineEventCallBackHandler.onFacePositionChanged(imageWidth, imageHeight, agoraFacePositionInfos);
        }

        @Override
        public void onJoinChannelSuccess(String channel, final int uid, int elapsed) {
            iRtcEngineEventCallBackHandler.onJoinChannelSuccess(channel, uid, elapsed);
        }

        @Override
        public void onFirstRemoteVideoDecoded(final int uid, int width, int height, int elapsed) {
            iRtcEngineEventCallBackHandler.onFirstRemoteVideoDecoded(uid, width, height, elapsed);
        }

        @Override
        public void onUserOffline(final int uid, int reason) {
            iRtcEngineEventCallBackHandler.onUserOffline(uid, reason);
        }

        @Override
        public void onUserJoined(int uid, int elapsed) {
            iRtcEngineEventCallBackHandler.onUserJoined(uid, elapsed);
        }

        @Override
        public void onRequestToken() {
            iRtcEngineEventCallBackHandler.onRequestToken();
        }

        @Override
        public void onTokenPrivilegeWillExpire(String token) {
            iRtcEngineEventCallBackHandler.onTokenPrivilegeWillExpire(token);
            RtcManager.getInstance().requestNewToken(token);
        }
    };

    private static MediaDataObserverPlugin mediaDataObserverPlugin;

    public static MediaDataObserverPlugin getMediaDataObserverPlugin() {
        if (mediaDataObserverPlugin == null) {
            synchronized (RtcEngineManager.class) {
                if (mediaDataObserverPlugin == null) {
                    mediaDataObserverPlugin = MediaDataObserverPlugin.the();
                }
            }
        }
        return mediaDataObserverPlugin;
    }

    public static void clearMediaDataObserverPlugin() {
        mediaDataObserverPlugin = null;
    }

    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
}
