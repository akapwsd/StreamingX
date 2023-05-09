package com.code.youyu.api;

import android.content.Context;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import com.code.bean.ChannelTokenBean;
import com.code.listener.HttpRequestListener;
import com.code.listener.IRtcEngineEventCallBackHandler;
import com.code.listener.RequestModelListListener;
import com.code.okhttp.WSManager;
import com.code.rtc.BaseRtcEngineManager;
import com.code.utils.HttpRequestUtils;
import com.code.utils.LogUtil;
import com.code.utils.RtcSpBase;
import com.code.utils.RtcSpUtils;

import io.agora.rtc.RtcEngine;
import io.agora.rtc.models.ChannelMediaOptions;
import io.agora.rtc.video.VideoCanvas;

/**
 * RtcManager is a method class for fast communication
 * It includes functional interfaces such as obtaining anchor information, dialing audio and video, and video and audio control
 * <p>
 * You can use this class to quickly realize the audio and video functions of the anchor
 * The specific function interface is as follows:
 * <ul>
 * <li>call video
 * <li>dial audio
 * </ul>
 *
 * @author chan
 * @version 1.0.5
 * @since 1.0.5
 * @since 6 May 2023
 * @since JDK8
 */
public class RtcManager {
    private static final String TAG = "RtcManager";
    private static final int BIG_VIEW_STATE_REMOTE = 0;
    private static final int BIG_VIEW_STATE_LOCAL = 1;
    private Context mContext;
    private SurfaceView remoteView;
    private SurfaceView localView;
    private int mBigViewState = BIG_VIEW_STATE_REMOTE;
    /**
     * The other party's uid
     */
    public int mRemoteUid;
    /**
     * own uid
     */
    public int localUid;
    private FrameLayout remoteFrameLayout;
    private FrameLayout localFrameLayout;
    private static RtcManager rtcManager;

    /**
     * Get the singleton method of RtcManager object
     *
     * @return RtcManager
     */
    public static RtcManager getInstance() {
        if (rtcManager == null) {
            synchronized (RtcManager.class) {
                if (rtcManager == null) {
                    rtcManager = new RtcManager();
                }
            }
        }
        return rtcManager;
    }

    /**
     * This is the RTC initialization method, you need to call it before using all audio and video functions
     * For example call it in your Application
     *
     * @param context           the context
     * @param access_key_id     the access_key_id
     * @param access_key_secret the access_key_secret
     * @param session_token     the session_token
     */
    public void initRtc(Context context, String access_key_id, String access_key_secret, String session_token) {
        LogUtil.d(TAG, "initRtc is start");
        mContext = context;
        RtcSpBase.initContent(context);
        RtcSpUtils.getInstance().setChannelId("");
        BaseRtcEngineManager.getInstance().initBaseRtc(context);
        WSManager.getInstance().init(context, access_key_id, access_key_secret, session_token);
    }

    /**
     * Enable RTC log printing
     */
    public void enableLog() {
        LogUtil.setLogLevel(LogUtil.Level.Level_HIGH.ordinal());
    }

    /**
     * Register listener communication callback method
     *
     * @param callBackHandler Room event callback object
     *                        {@link IRtcEngineEventCallBackHandler} IRtcEngineEventCallBackHandler listener object
     * @see IRtcEngineEventCallBackHandler
     */
    public void setIRtcEngineEventCallBackHandler(IRtcEngineEventCallBackHandler callBackHandler) {
        BaseRtcEngineManager.getInstance().setIRtcEngineEventCallBackHandler(callBackHandler);
        WSManager.getInstance().setIRtcEngineEventCallBackHandler(callBackHandler);
    }

    /**
     * Show remote screen
     *
     * @param context the context
     * @param uid     The other party's uid
     * @param view    view that need to display the other party's screen
     */
    public void showRemoteView(Context context, int uid, FrameLayout view) {
        remoteFrameLayout = view;
        mRemoteUid = uid;
        remoteView = RtcEngine.CreateRendererView(context);
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.setupRemoteVideo(new VideoCanvas(remoteView, VideoCanvas.RENDER_MODE_HIDDEN, mRemoteUid));
        view.addView(remoteView, 0, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    /**
     * Display the local screen
     *
     * @param context the context
     * @param uid     own uid
     * @param view    A view that displays its own screen
     */
    public void showLocalView(Context context, int uid, FrameLayout view) {
        LogUtil.d(TAG, "showLocalView is start showLocalView uid:" + uid);
        userJoinChannel(uid);
        localFrameLayout = view;
        localUid = uid;
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.enableLocalVideo(true);
        localView = RtcEngine.CreateRendererView(context);
        localView.setZOrderOnTop(true);
        view.addView(localView);
        rtcEngine.setupLocalVideo(new VideoCanvas(localView, VideoCanvas.RENDER_MODE_HIDDEN, localUid));
        localView.setZOrderMediaOverlay(true);
    }

    private void userJoinChannel(int uid) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        String accessToken = WSManager.getInstance().mToken;
        String channel = WSManager.getInstance().mChannelId;
        int mClientRole = WSManager.getInstance().mClientRole;
        LogUtil.d(TAG, "userJoinChannel is start step 1");
        if (rtcEngine != null && !TextUtils.isEmpty(accessToken) && !TextUtils.isEmpty(channel)) {
            LogUtil.d(TAG, "userJoinChannel is start uid:" + uid + " accessToken:" + accessToken + " channel:" + channel + " mClientRole:" + mClientRole);
            ChannelMediaOptions option = new ChannelMediaOptions();
            option.autoSubscribeAudio = true;
            option.autoSubscribeVideo = true;
            rtcEngine.setClientRole(mClientRole);
            rtcEngine.joinChannel(accessToken, channel, "Extra Optional Data", uid, option);
        }
    }

    /**
     * Switch between remote and local video
     *
     * @param context the context
     */
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

    public void createChannel(String uid, int category, HttpRequestListener listener) {
        HttpRequestUtils.getInstance().createChannel(mContext, uid, category, listener);
    }

    /**
     * call video
     *
     * @param channel  The RTC number of the video
     * @param uid      own uid
     * @param peerUid  The other party's uid
     * @param listener callback interface
     *                 {@link HttpRequestListener} HttpRequestListener listener object
     * @see HttpRequestListener
     */
    public void callVideo(String channel, String uid, String peerUid, HttpRequestListener listener) {
        joinChannel(channel, uid, peerUid, Constants.VIDEO, listener);
    }

    /**
     * call audio
     *
     * @param channel  The RTC number of the audio
     * @param uid      own uid
     * @param peerUid  The other party's uid
     * @param listener callback interface
     *                 {@link HttpRequestListener} HttpRequestListener listener object
     * @see HttpRequestListener
     */
    public void callAudio(String channel, String uid, String peerUid, HttpRequestListener listener) {
        joinChannel(channel, uid, peerUid, Constants.AUDIO, listener);
    }

    private void joinChannel(String channel, String uid, String peerUid, int category, HttpRequestListener listener) {
        HttpRequestUtils.getInstance().joinChannel(mContext, channel, uid, peerUid, category, listener);
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

    public void requestNewToken() {
        String channel = WSManager.getInstance().mChannelId;
        String uid = WSManager.getInstance().mUid;
        HttpRequestUtils.getInstance().getChannelToken(mContext, channel, uid, new HttpRequestListener() {
            @Override
            public void requestSuccess(Object o) {
                ChannelTokenBean channelTokenBean = (ChannelTokenBean) o;
                String newToken = channelTokenBean.getToken();
                String serverChannelId = channelTokenBean.getChannelId();
                LogUtil.d(TAG, "requestSuccess newToken:" + newToken + " serverChannelId:" + serverChannelId);
                if (channel.equals(serverChannelId)) {
                    RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
                    rtcEngine.renewToken(newToken);
                }
            }

            @Override
            public void requestError(int code, String error) {
                LogUtil.e(TAG, "requestError code:" + code + " error:" + error);
            }
        });
    }

    /**
     * Hang up and disconnect the call
     */
    public void closeVideoChat() {
        WSManager.getInstance().leaveChannel();
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.leaveChannel();
        RtcSpUtils.getInstance().setChannelId("");
    }

    public void enableFaceDetection(boolean isEnable) {
        RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
        rtcEngine.enableFaceDetection(isEnable);
    }

    /**
     * If you want to get more function extensions,
     * you can directly get the RtcEngine object for operation
     */
    public RtcEngine getRtcEngine() {
        return BaseRtcEngineManager.getInstance().getRtcEngine();
    }
}
