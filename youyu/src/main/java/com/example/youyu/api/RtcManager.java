package com.example.youyu.api;


import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import com.example.listener.IRtcEngineEventCallBackHandler;
import com.example.listener.InitResultListener;
import com.example.listener.RtcRequestEventHandler;
import com.example.okhttp.WSManager;
import com.example.rtc.BaseRtcEngineManager;
import com.example.utils.HttpRequestUtils;
import com.example.utils.RtcSpBase;
import com.example.utils.RtcSpUtils;

import io.agora.rtc.RtcEngine;
import io.agora.rtc.models.ChannelMediaOptions;
import io.agora.rtc.video.VideoCanvas;
import okio.ByteString;

public class RtcManager {
    private static final class RtcManagerHolder {
        static final RtcManager rtcManager = new RtcManager();
    }

    public static RtcManager getInstance() {
        return RtcManagerHolder.rtcManager;
    }

    private InitResultListener mInitResultListener;

    public void initRtc(Context context, String accountToken, InitResultListener initResultListener) {
        mInitResultListener = initResultListener;
        RtcSpBase.initContent(context);
        BaseRtcEngineManager.getInstance().initBaseRtc(context);
        WSManager.getInstance().init(context);
        HttpRequestUtils.getInstance().requestToken(context, accountToken, new HttpRequestUtils.HttpRequestListener() {
            @Override
            public void requestSuccess(String o, String msg) {
                RtcSpUtils.getInstance().setToken(msg);
                mInitResultListener.onSuccess();
            }

            @Override
            public void requestError(int code, String error) {
                mInitResultListener.onFail(code, error);
            }
        });
    }

    public void createRtc(RtcRequestEventHandler rtcRequestEventHandler) {
        WSManager.getInstance().create(rtcRequestEventHandler);
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

    public void setIRtcEngineEventCallBackHandler(IRtcEngineEventCallBackHandler callBackHandler) {
        BaseRtcEngineManager.getInstance().setIRtcEngineEventCallBackHandler(callBackHandler);
    }

    public void showRemoteView(Context context, int uid, FrameLayout view) {
        remoteFrameLayout = view;
        mRemoteUid = uid;
        remoteView = RtcEngine.CreateRendererView(context);
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.setupRemoteVideo(new VideoCanvas(remoteView, VideoCanvas.RENDER_MODE_HIDDEN, mRemoteUid));
        remoteFrameLayout.addView(remoteView, 0, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
    }

    public void showLocalView(Context context, int uid, FrameLayout view) {
        localFrameLayout = view;
        localUid = uid;
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        localView = RtcEngine.CreateRendererView(context);
        localView.setZOrderOnTop(true);
        localFrameLayout.addView(localView);
        rtcEngine.setupLocalVideo(
                new VideoCanvas(
                        localView,
                        VideoCanvas.RENDER_MODE_HIDDEN,
                        localUid
                )
        );
        localView.setZOrderMediaOverlay(true);
        new Handler().postDelayed(() -> userJoinChannel(uid), 500);
    }

    private void userJoinChannel(int uid) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        String accessToken = WSManager.getInstance().token;
        String channel = WSManager.getInstance().channelId;
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
            rtcEngine.setupRemoteVideo(
                    new VideoCanvas(
                            remoteView,
                            VideoCanvas.RENDER_MODE_HIDDEN,
                            mRemoteUid
                    )
            );
            localFrameLayout.addView(remoteView);
            localView = RtcEngine.CreateRendererView(context);
            rtcEngine.setupLocalVideo(
                    new VideoCanvas(
                            localView,
                            VideoCanvas.RENDER_MODE_HIDDEN,
                            localUid
                    )
            );
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
            rtcEngine.setupLocalVideo(
                    new VideoCanvas(
                            localView,
                            VideoCanvas.RENDER_MODE_HIDDEN,
                            localUid
                    )
            );

            remoteView = RtcEngine.CreateRendererView(context);
            rtcEngine.setupRemoteVideo(
                    new VideoCanvas(
                            remoteView,
                            VideoCanvas.RENDER_MODE_HIDDEN,
                            mRemoteUid
                    )
            );
            remoteFrameLayout.addView(
                    remoteView,
                    0,
                    new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT
                    )
            );
            localView.setZOrderMediaOverlay(true);
        }
    }

    public void startCall(WSManager.WebSocketResultListener listener) {
        WSManager.getInstance().registerWSDataListener(Constants.START_CALL, listener);
        byte[] a = new byte[0];
        WSManager.getInstance().send(ByteString.of(a));
    }

    public void hangUpCall(WSManager.WebSocketResultListener listener) {
        WSManager.getInstance().registerWSDataListener(Constants.HANG_UP, listener);
        byte[] a = new byte[0];
        WSManager.getInstance().send(ByteString.of(a));
    }

    public void acceptCall(WSManager.WebSocketResultListener listener) {
        WSManager.getInstance().registerWSDataListener(Constants.ACCEPT_CALL, listener);
        byte[] a = new byte[0];
        WSManager.getInstance().send(ByteString.of(a));
    }

    public void rejectCall(WSManager.WebSocketResultListener listener) {
        WSManager.getInstance().registerWSDataListener(Constants.REJECT_CALL, listener);
        byte[] a = new byte[0];
        WSManager.getInstance().send(ByteString.of(a));
    }

    public void getModelList(WSManager.RequestModelListListener listener) {
        WSManager.getInstance().registerModelListener(listener);
        byte[] a = new byte[0];
        WSManager.getInstance().send(ByteString.of(a));
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
        byte[] a = new byte[0];
        WSManager.getInstance().send(ByteString.of(a));
    }
}
