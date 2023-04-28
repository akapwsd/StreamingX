package com.code.youyu.api;


import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import com.code.listener.HttpRequestListener;
import com.code.listener.IRtcEngineEventCallBackHandler;
import com.code.listener.RequestModelListListener;
import com.code.okhttp.WSManager;
import com.code.rtc.BaseRtcEngineManager;
import com.code.utils.HttpRequestUtils;
import com.code.utils.LogUtil;
import com.code.utils.RtcSpBase;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.internal.LastmileProbeConfig;
import io.agora.rtc.models.ChannelMediaOptions;
import io.agora.rtc.video.VideoCanvas;
import okio.ByteString;

public class RtcManager {
    public static final String TAG = "RtcManager";

    private static final class RtcManagerHolder {
        static final RtcManager rtcManager = new RtcManager();
    }

    public static RtcManager getInstance() {
        return RtcManagerHolder.rtcManager;
    }

    private Context mContext;

    public void initRtc(Context context, String access_key_id, String access_key_secret, String session_token) {
        LogUtil.d(TAG, "initRtc is start");
        mContext = context;
        RtcSpBase.initContent(context);
        BaseRtcEngineManager.getInstance().initBaseRtc(context);
        WSManager.getInstance().init(context, access_key_id, access_key_secret, session_token);
    }

    public void enableLog() {
        LogUtil.setLogLevel(LogUtil.Level.Level_HIGH.ordinal());
    }

    public void setIRtcEngineEventCallBackHandler(IRtcEngineEventCallBackHandler callBackHandler) {
        BaseRtcEngineManager.getInstance().setIRtcEngineEventCallBackHandler(callBackHandler);
        WSManager.getInstance().setIRtcEngineEventCallBackHandler(callBackHandler);
    }

    public static final int BIG_VIEW_STATE_REMOTE = 0;
    public static final int BIG_VIEW_STATE_LOCAL = 1;
    private SurfaceView remoteView;
    private SurfaceView localView;
    private int mBigViewState = BIG_VIEW_STATE_REMOTE;
    public int mRemoteUid;
    public int localUid;
    private FrameLayout remoteFrameLayout;
    private FrameLayout localFrameLayout;

    public void quickShowView(Context context, int myUid, int peerUid, FrameLayout bigView, FrameLayout smallView) {
        setIRtcEngineEventCallBackHandler(new IRtcEngineEventCallBackHandler() {
            @Override
            public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {
                super.onFirstRemoteVideoDecoded(uid, width, height, elapsed);
            }

            @Override
            public void onUserOffline(int uid, int reason) {
                super.onUserOffline(uid, reason);
            }

            @Override
            public void onUserJoined(int uid, int elapsed) {
                super.onUserJoined(uid, elapsed);
            }

            @Override
            public void onFacePositionChanged(int imageWidth, int imageHeight, IRtcEngineEventHandler.AgoraFacePositionInfo[] agoraFacePositionInfos) {
                super.onFacePositionChanged(imageWidth, imageHeight, agoraFacePositionInfos);
            }
        });
        showLocalView(context, myUid, smallView);
    }

    public void showRemoteView(Context context, int uid, FrameLayout view) {
        remoteFrameLayout = view;
        mRemoteUid = uid;
        remoteView = RtcEngine.CreateRendererView(context);
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.setupRemoteVideo(new VideoCanvas(remoteView, VideoCanvas.RENDER_MODE_HIDDEN, mRemoteUid));
        remoteFrameLayout.addView(remoteView, 0, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    public void showLocalView(Context context, int uid, FrameLayout view) {
        localFrameLayout = view;
        localUid = uid;
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        localView = RtcEngine.CreateRendererView(context);
        localView.setZOrderOnTop(true);
        localFrameLayout.addView(localView);
        rtcEngine.setupLocalVideo(new VideoCanvas(localView, VideoCanvas.RENDER_MODE_HIDDEN, localUid));
        localView.setZOrderMediaOverlay(true);
        new Handler().postDelayed(() -> userJoinChannel(uid), 500);
    }

    public void joinAudio(int uid) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.enableLocalVideo(false);
        new Handler().postDelayed(() -> userJoinChannel(uid), 500);
    }

    private void userJoinChannel(int uid) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        String accessToken = WSManager.getInstance().mToken;
        String channel = WSManager.getInstance().mChannelId;
        if (rtcEngine != null && !TextUtils.isEmpty(accessToken) && TextUtils.isEmpty(channel)) {
            ChannelMediaOptions option = new ChannelMediaOptions();
            option.autoSubscribeAudio = true;
            option.autoSubscribeVideo = true;
            rtcEngine.joinChannel(accessToken, channel, "Extra Optional Data", uid, option);
        }
    }

    public void switchView(Context context) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.setupRemoteVideo(null);
        if (mBigViewState == BIG_VIEW_STATE_REMOTE) {
            mBigViewState = BIG_VIEW_STATE_LOCAL;
            if (localView.getParent() != null) {
                ((FrameLayout) localView.getParent()).removeView(localView);
                localView = null;
            }
            if (remoteView.getParent() != null) {
                ((FrameLayout) remoteView.getParent()).removeView(remoteView);
                remoteView = null;
            }
            remoteView = RtcEngine.CreateRendererView(context);
            rtcEngine.setupRemoteVideo(new VideoCanvas(remoteView, VideoCanvas.RENDER_MODE_HIDDEN, mRemoteUid));
            localFrameLayout.addView(remoteView);
            localView = RtcEngine.CreateRendererView(context);
            rtcEngine.setupLocalVideo(new VideoCanvas(localView, VideoCanvas.RENDER_MODE_HIDDEN, localUid));
            remoteFrameLayout.addView(localView);
            remoteView.setZOrderMediaOverlay(true);
        } else {
            mBigViewState = BIG_VIEW_STATE_REMOTE;
            if (localView.getParent() != null) {
                ((FrameLayout) localView.getParent()).removeView(localView);
                localView = null;
            }
            if (remoteView.getParent() != null) {
                ((FrameLayout) remoteView.getParent()).removeView(remoteView);
                remoteView = null;
            }
            localView = RtcEngine.CreateRendererView(context);
            localFrameLayout.addView(localView);
            rtcEngine.setupLocalVideo(new VideoCanvas(localView, VideoCanvas.RENDER_MODE_HIDDEN, localUid));

            remoteView = RtcEngine.CreateRendererView(context);
            rtcEngine.setupRemoteVideo(new VideoCanvas(remoteView, VideoCanvas.RENDER_MODE_HIDDEN, mRemoteUid));
            remoteFrameLayout.addView(remoteView, 0, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            localView.setZOrderMediaOverlay(true);
        }
    }

    //*=====================================================================================================================//
    public void createChannel(String uid, int callType, String access_key_id, String access_key_secret, String session_token, HttpRequestListener listener) {
        HttpRequestUtils.getInstance().createChannel(mContext, access_key_id, access_key_secret, session_token, listener);
    }

    public void joinChannel(String channel, String uid, int callType, HttpRequestListener listener) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        LastmileProbeConfig lastmileProbeConfig = new LastmileProbeConfig();
        lastmileProbeConfig.probeUplink = true;
        lastmileProbeConfig.probeDownlink = true;
        rtcEngine.startLastmileProbeTest(lastmileProbeConfig);
        HttpRequestUtils.getInstance().joinChannel(mContext, channel, listener);
    }

    public void leaveChannel() {
        WSManager.getInstance().leaveChannel();
    }

    public void requestChannelToken(String channel, String uid, int callType, HttpRequestListener listener) {
        HttpRequestUtils.getInstance().getChannelToken(mContext, channel, listener);
    }

    public void getModelList(RequestModelListListener listener) {
        HttpRequestUtils.getInstance().getModelList(mContext, listener);
    }

    public void switchCamera() {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.switchCamera();
    }

    public void actionAllAudio(boolean isEnable) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.muteAllRemoteAudioStreams(isEnable);
    }

    public void actionAudio(int uid, boolean isEnable) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.muteRemoteAudioStream(uid, isEnable);
    }

    public void actionLocalAudio(boolean isEnable) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.muteLocalAudioStream(isEnable);
    }

    public void actionAllVideo(boolean isEnable) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.muteAllRemoteVideoStreams(isEnable);
    }

    public void actionLocalVideo(boolean isEnable) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.muteLocalVideoStream(isEnable);
    }

    public void actionVideo(int uid, boolean isEnable) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.muteRemoteVideoStream(uid, isEnable);
    }

    public void requestNewToken(String oldToken) {
        String channel = WSManager.getInstance().mChannelId;
        HttpRequestUtils.getInstance().getChannelToken(mContext, channel, new HttpRequestListener() {
            @Override
            public void requestSuccess(String o, String msg) {
                String newToken = "";
                LogUtil.e(TAG, "requestSuccess newToken:" + newToken);
                RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
                rtcEngine.renewToken(newToken);
            }

            @Override
            public void requestError(int code, String error) {
                LogUtil.e(TAG, "requestError code:" + code + " error:" + error);
            }
        });
    }

    public void closeVideoChat() {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.leaveChannel();
        rtcEngine.stopPreview();
    }
}
