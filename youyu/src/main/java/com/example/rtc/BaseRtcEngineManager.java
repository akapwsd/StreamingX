package com.example.rtc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.listener.FacePositionListener;
import com.example.listener.IRtcEngineEventCallBackHandler;
import com.example.rawdata.MediaDataObserverPlugin;

import java.util.Timer;
import java.util.TimerTask;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoEncoderConfiguration;

public class BaseRtcEngineManager {
    public static final String TAG = "BaseRtcEngineManager";
    private FacePositionListener facePositionListener;
    private IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler;
    private static BaseRtcEngineManager baseRtcEngineManager;
    private RtcEngine mRtcEngine;
    private boolean haveFace = false;

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

    public void setFacePositionListener(FacePositionListener facePositionListener) {
        this.facePositionListener = facePositionListener;
    }

    public void removeFacePositionListener() {
        if (facePositionListener != null) {
            facePositionListener = null;
        }
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
            super.onFacePositionChanged(imageWidth, imageHeight, agoraFacePositionInfos);
            if (agoraFacePositionInfos.length > 0) {
                haveFace = true;
                if (facePositionListener != null) {
                    AgoraFacePositionInfo agoraFacePositionInfo = agoraFacePositionInfos[0];
                    facePositionListener.haveFace(agoraFacePositionInfo);
                }
            } else {
                haveFace = false;
                if (facePositionListener != null) {
                    facePositionListener.noFace();
                }
            }
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
            handler.post(() -> {
                if (mediaDataObserverPlugin != null) {
                    mediaDataObserverPlugin.addDecodeBuffer(uid);
                }
            });
        }

        @Override
        public void onRejoinChannelSuccess(String channel, int uid, int elapsed) {
            super.onRejoinChannelSuccess(channel, uid, elapsed);
        }

        @Override
        public void onRequestToken() {
            super.onRequestToken();
        }

        @Override
        public void onClientRoleChanged(int oldRole, int newRole) {
            super.onClientRoleChanged(oldRole, newRole);
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
