package com.example.youyu.api;


import android.content.Context;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import com.example.rtc.BaseRtcEngineManager;

import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;

public class RtcManager {
    private static final class RtcManagerHolder {
        static final RtcManager rtcManager = new RtcManager();
    }

    public static RtcManager getInstance() {
        return RtcManagerHolder.rtcManager;
    }

    public void initRtc(Context context) {
        BaseRtcEngineManager.getInstance().initBaseRtc(context);
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
}
