package com.code.rtc;

import android.content.Context;

import com.code.constants.AgoraConstants;
import com.code.listener.IRtcEngineEventCallBackHandler;
import com.code.okhttp.WSManager;
import com.code.utils.LogUtil;
import com.code.utils.RtcSpUtils;
import com.code.youyu.api.StreamingXRtcManager;

import java.util.HashMap;
import java.util.Map;

import io.agora.rtc2.Constants;
import io.agora.rtc2.IRtcEngineEventHandler;
import io.agora.rtc2.RtcEngine;
import io.agora.rtc2.video.VideoEncoderConfiguration;


public class BaseRtcEngineManager {
    public static final String TAG = "BaseRtcEngineManager";
    //    private IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler;
    private static BaseRtcEngineManager baseRtcEngineManager;
    private RtcEngine mRtcEngine;
    private static String TEST_KEY = "d204e16e727048b08a1d8e1ae10bb238";
    private static String RELEASE_KEY = "50b9eccdb721455b849a88b375d64e34";
    private final HashMap<String, IRtcEngineEventCallBackHandler> callBackHandlerHashMap = new HashMap<>();

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

    public void setIRtcEngineEventCallBackHandler(String tag, IRtcEngineEventCallBackHandler callBackHandler) {
        callBackHandlerHashMap.put(tag, callBackHandler);
    }

    public void initBaseRtc(Context context) {
        LogUtil.d(TAG, "initBaseRtc is start");
        try {
            String key;
            if (StreamingXRtcManager.getInstance().isEnableDebug) {
                key = TEST_KEY;
            } else {
                key = RELEASE_KEY;
            }
            mRtcEngine = RtcEngine.create(context, key, mRtcEventHandler);
            mRtcEngine.enableVideo();
            mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
            mRtcEngine.setBeautyEffectOptions(true, AgoraConstants.BEAUTY_OPTIONS);
            mRtcEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(VideoEncoderConfiguration.VD_640x360, VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15, VideoEncoderConfiguration.STANDARD_BITRATE, VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT));
            mRtcEngine.setParameters("{\"che.video.enableRemoteViewMirror\":true}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.d(TAG, "initBaseRtc is end");
    }

    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onFacePositionChanged(int imageWidth, int imageHeight, AgoraFacePositionInfo[] agoraFacePositionInfos) {
            LogUtil.d(TAG, "onFacePositionChanged is start imageWidth:" + imageWidth + " imageHeight:" + imageHeight + " agoraFacePositionInfos:" + agoraFacePositionInfos);
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.onFacePositionChanged(imageWidth, imageHeight, agoraFacePositionInfos);
                }
            }
        }

        @Override
        public void onJoinChannelSuccess(String channel, final int uid, int elapsed) {
            LogUtil.d(TAG, "onJoinChannelSuccess is start channel:" + channel + " uid:" + uid + " elapsed:" + elapsed);
            WSManager.getInstance().mChannelId = channel;
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.onJoinChannelSuccess(channel, uid, elapsed);
                }
            }
        }

        @Override
        public void onFirstRemoteVideoDecoded(final int uid, int width, int height, int elapsed) {
            LogUtil.d(TAG, "onFirstRemoteVideoDecoded is start uid:" + uid + " width:" + width + " height:" + height + " elapsed:" + elapsed);
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.onFirstRemoteVideoDecoded(uid, width, height, elapsed);
                }
            }
        }

        @Override
        public void onUserOffline(final int uid, int reason) {
            LogUtil.d(TAG, "onUserOffline is start uid:" + uid + " reason:" + reason);
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.onUserOffline(uid, reason);
                }
            }
        }

        @Override
        public void onUserJoined(int uid, int elapsed) {
            LogUtil.d(TAG, "onUserJoined is start uid:" + uid + " elapsed:" + elapsed);
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.onUserJoined(uid, elapsed);
                }
            }
        }

        @Override
        public void onRequestToken() {
            LogUtil.d(TAG, "onRequestToken is start");
            StreamingXRtcManager.getInstance().closeVideoChat();
        }

        @Override
        public void onTokenPrivilegeWillExpire(String token) {
            LogUtil.d(TAG, "onTokenPrivilegeWillExpire is start token:" + token);
            StreamingXRtcManager.getInstance().requestNewToken();
        }

        @Override
        public void onLastmileQuality(int quality) {
            super.onLastmileQuality(quality);
            VideoEncoderConfiguration videoEncoderConfiguration = null;
            switch (quality) {
                case Constants.QUALITY_EXCELLENT:
                case Constants.QUALITY_GOOD:
                case Constants.QUALITY_POOR:
                    videoEncoderConfiguration = new VideoEncoderConfiguration(VideoEncoderConfiguration.VD_640x360, VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15, VideoEncoderConfiguration.STANDARD_BITRATE, VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT);
                    break;
                case Constants.QUALITY_BAD:
                case Constants.QUALITY_VBAD:
                    videoEncoderConfiguration = new VideoEncoderConfiguration(VideoEncoderConfiguration.VD_640x360, VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_10, VideoEncoderConfiguration.STANDARD_BITRATE, VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT);
                    break;
            }
            mRtcEngine.setVideoEncoderConfiguration(videoEncoderConfiguration);
        }

        @Override
        public void onConnectionStateChanged(int state, int reason) {
            LogUtil.d(TAG, "onConnectionStateChanged is start state:" + state + " reason:" + reason);
            if ((state == 1 && reason == 10) || (state == 5 && reason == 10)) {
                StreamingXRtcManager.getInstance().closeVideoChat();
            }
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.onConnectionStateChanged(state, reason);
                }
            }
        }
    };
}
