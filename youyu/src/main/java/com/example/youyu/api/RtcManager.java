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

    public void showRemoteView(Context context, int uid, FrameLayout view) {
        SurfaceView surfaceView = RtcEngine.CreateRendererView(context);
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.setupRemoteVideo(new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, uid));
        view.addView(surfaceView, 0, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
    }

    public void showLocalView(Context context, int uid, FrameLayout view) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        SurfaceView surfaceView = RtcEngine.CreateRendererView(context);
        surfaceView.setZOrderOnTop(true);
        view.addView(surfaceView);
        rtcEngine.setupLocalVideo(
                new VideoCanvas(
                        surfaceView,
                        VideoCanvas.RENDER_MODE_HIDDEN,
                        uid
                )
        );
        surfaceView.setZOrderMediaOverlay(true);
    }
}
