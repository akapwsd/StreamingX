package com.example.youyu.api;


import android.content.Context;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import com.example.listener.InitResultListener;
import com.example.listener.RtcRequestEventHandler;
import com.example.okhttp.WSManager;
import com.example.rtc.BaseRtcEngineManager;
import com.example.utils.HttpRequestUtils;
import com.example.utils.RtcSpBase;
import com.example.utils.RtcSpUtils;

import io.agora.rtc.RtcEngine;
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
        BaseRtcEngineManager.getInstance().initBaseRtc(context);
        RtcSpBase.initContent(context);
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

    public void createRtc(Context context, RtcRequestEventHandler rtcRequestEventHandler) {
        WSManager.getInstance().init(context, rtcRequestEventHandler);
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

    public void switchCamera(){

    }

    public void actionVoice(){

    }

    public void actionVideo(){

    }
}
