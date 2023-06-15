package com.code.youyu.api;

import android.content.Context;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import com.code.bean.ChannelTokenBean;
import com.code.listener.ChannelMsgListener;
import com.code.listener.HttpRequestListener;
import com.code.listener.IRtcEngineEventCallBackHandler;
import com.code.listener.RequestModelAvatarListListener;
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
public class StreamingXRtcManager {
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
    private static StreamingXRtcManager streamingXRtcManager;
    private boolean isInit = false;

    /**
     * Get the singleton method of RtcManager object
     *
     * @return RtcManager
     */
    public static StreamingXRtcManager getInstance() {
        if (streamingXRtcManager == null) {
            synchronized (StreamingXRtcManager.class) {
                if (streamingXRtcManager == null) {
                    streamingXRtcManager = new StreamingXRtcManager();
                }
            }
        }
        return streamingXRtcManager;
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
    public boolean initRtc(Context context, String access_key_id, String access_key_secret, String session_token) {
        LogUtil.d(TAG, "initRtc is start");
        if (!TextUtils.isEmpty(access_key_id) && !TextUtils.isEmpty(access_key_secret) && !TextUtils.isEmpty(session_token)) {
            mContext = context;
            RtcSpBase.initContent(context);
            RtcSpUtils.getInstance().setChannelId("");
            BaseRtcEngineManager.getInstance().initBaseRtc(context);
            WSManager.getInstance().init(context, access_key_id, access_key_secret, session_token);
            isInit = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean initModelRtc(Context context, String token) {
        LogUtil.d(TAG, "initModelRtc is start");
        if (!TextUtils.isEmpty(token)) {
            mContext = context;
            RtcSpBase.initContent(context);
            RtcSpUtils.getInstance().setChannelId("");
            BaseRtcEngineManager.getInstance().initBaseRtc(context);
            WSManager.getInstance().init(context, token);
            isInit = true;
            return true;
        } else {
            return false;
        }
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
        if (isInit) {
            remoteFrameLayout = view;
            mRemoteUid = uid;
            remoteView = RtcEngine.CreateRendererView(context);
            RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
            rtcEngine.setupRemoteVideo(new VideoCanvas(remoteView, VideoCanvas.RENDER_MODE_HIDDEN, mRemoteUid));
            view.addView(remoteView, 0, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    /**
     * Display the local screen
     *
     * @param context the context
     * @param view    A view that displays its own screen
     */
    public void showLocalView(Context context, FrameLayout view) {
        LogUtil.d(TAG, "showLocalView is start showLocalView uid:" + localUid);
        if (isInit) {
            userJoinChannel(localUid);
            localFrameLayout = view;
            RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
            rtcEngine.enableLocalVideo(true);
            localView = RtcEngine.CreateRendererView(context);
            localView.setZOrderOnTop(true);
            view.addView(localView);
            rtcEngine.setupLocalVideo(new VideoCanvas(localView, VideoCanvas.RENDER_MODE_HIDDEN, localUid));
            localView.setZOrderMediaOverlay(true);
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
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
        if (isInit) {
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
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    /**
     * call video
     *
     * @param channel The RTC number of the video
     */
    public void callVideo(String channel, String token, String localUid) {
        if (isInit) {
            joinChannel(channel, token, localUid, Constants.VIDEO);
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    /**
     * call audio
     *
     * @param channel The RTC number of the audio
     */
    public void callAudio(String channel, String token, String localUid) {
        if (isInit) {
            joinChannel(channel, token, localUid, Constants.AUDIO);
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }

    }

    private void joinChannel(String channelId, String token, String localUid, int category) {
        StreamingXRtcManager.getInstance().localUid = Integer.parseInt(localUid);
        WSManager.getInstance().joinChannel(channelId, token, Integer.parseInt(localUid), io.agora.rtc.Constants.CLIENT_ROLE_BROADCASTER, category);
    }

    /**
     * Get the host resource list 20 pieces of data per page by default
     *
     * @param page     List pagination, starting from 0
     * @param listener callback interface
     *                 {@link RequestModelListListener} HttpRequestListener listener object
     * @see RequestModelListListener
     */
    public void getModelList(int page, RequestModelListListener listener) {
        if (isInit) {
            HttpRequestUtils.getInstance().getModelList(mContext, 0, page, 20, listener);
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    /**
     * Get the host resource list
     *
     * @param sort     to sort
     * @param page     List pagination, starting from 0
     * @param limit    Pagination limit field, default 10, maximum 50
     * @param listener callback interface
     *                 {@link RequestModelListListener} HttpRequestListener listener object
     * @see RequestModelListListener
     */
    public void getModelList(int sort, int page, int limit, RequestModelListListener listener) {
        if (isInit) {
            HttpRequestUtils.getInstance().getModelList(mContext, sort, page, limit, listener);
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    /**
     * Get the host's avatar information
     *
     * @param modelId  anchor's id
     * @param listener callback interface
     *                 {@link RequestModelAvatarListListener} HttpRequestListener listener object
     * @see RequestModelAvatarListListener
     */
    public void getModelAvatar(int modelId, RequestModelAvatarListListener listener) {
        if (isInit) {
            HttpRequestUtils.getInstance().getModelAvatar(mContext, modelId, listener);
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    /**
     * Switch front and rear cameras
     */
    public void switchCamera() {
        if (isInit) {
            RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
            rtcEngine.switchCamera();
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    /**
     * Unsubscribe or resume audio streaming for all remote users.
     * <p>
     * After calling this method successfully, the local user will unsubscribe or restore the subscription to all remote users' audio streams,
     * including the audio streams of users who join the channel after calling this method.
     * <p>
     * This method needs to be called after joining the channel.
     *
     * @param isEnable Whether to unsubscribe the audio stream of all remote users:
     *                 <ul>
     *                 <li>true: unsubscribe from all remote users' audio streams
     *                 <li>false: (default) subscribe to all remote users' audio streams.
     *                 </ul>
     */
    public void actionAllAudio(boolean isEnable) {
        if (isInit) {
            RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
            rtcEngine.muteAllRemoteAudioStreams(isEnable);
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    /**
     * Cancel or resume subscription to the audio stream of the specified remote user.
     *
     * @param uid      Specifies the user ID of the user.
     * @param isEnable Whether to unsubscribe from the audio stream of the specified remote user.
     *                 <ul>
     *                 <li>true: Unsubscribe from the specified user's audio stream.
     *                 <li>false: (default) Subscribe to the specified user's audio stream.
     *                 </ul>
     */
    public void actionAudio(int uid, boolean isEnable) {
        if (isInit) {
            RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
            rtcEngine.muteRemoteAudioStream(uid, isEnable);
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    /**
     * Cancel or resume publishing a local audio stream.
     *
     * @param isEnable Whether to unpublish the local audio stream.
     *                 <ul>
     *                 <li>true: Unpublish.
     *                 <li>false: (default) publish.
     *                 </ul>
     */
    public void actionLocalAudio(boolean isEnable) {
        if (isInit) {
            RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
            rtcEngine.muteLocalAudioStream(isEnable);
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    /**
     * Unsubscribe or resume subscription to video streams of all remote users.
     * <p>
     * After successfully calling this method, the local user will unsubscribe or restore the subscription to all remote users' video streams,
     * including the video streams of users who join the channel after calling this method.
     * <p>
     * This method needs to be called after joining the channel.
     *
     * @param isEnable
     */
    public void actionAllVideo(boolean isEnable) {
        if (isInit) {
            RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
            rtcEngine.muteAllRemoteVideoStreams(isEnable);
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    /**
     * Cancel or resume publishing a local video stream.
     *
     * @param isEnable Whether to cancel sending the local video stream.
     *                 <ul>
     *                 <li>true: cancel sending local video stream.
     *                 <li>false: (default) send local video stream.
     *                 </ul>
     */
    public void actionLocalVideo(boolean isEnable) {
        if (isInit) {
            RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
            rtcEngine.muteLocalVideoStream(isEnable);
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    /**
     * Cancel or restore subscription to the video stream of the specified remote user.
     *
     * @param uid      Specifies the user ID of the user.
     * @param isEnable Whether to unsubscribe from the video stream of the specified remote user.
     *                 <ul>
     *                 <li>true: Unsubscribe from the specified user's video stream.
     *                 <li>false: (default) Subscribe to the specified user's video stream.
     *                 </ul>
     */
    public void actionVideo(int uid, boolean isEnable) {
        if (isInit) {
            RtcEngine rtcEngine = BaseRtcEngineManager.getInstance().getRtcEngine();
            rtcEngine.muteRemoteVideoStream(uid, isEnable);
        } else {
            LogUtil.e(TAG, "StreamingXRtcManager is not initialized");
        }
    }

    public void requestNewToken() {
        String channel = WSManager.getInstance().mChannelId;
        String token = RtcSpUtils.getInstance().getToken();

        if (!TextUtils.isEmpty(token)) {
            LogUtil.d(TAG, "requestNewToken is start have token");
            HttpRequestUtils.getInstance().getModelChannelToken(mContext, token, channel, new HttpRequestListener() {
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
        } else {
            LogUtil.d(TAG, "requestNewToken is start token empty");
            HttpRequestUtils.getInstance().getChannelToken(mContext, channel, new HttpRequestListener() {
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

    /**
     * Face recognition function
     * <p>
     * This method needs to be called on initialization
     *
     * @param isEnable Whether to enable face recognition
     *                 <ul>
     *                 <li>true:Turn on face recognition
     *                 <li>false:Turn off face recognition
     *                 </ul>
     */
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

    /**
     * Called during a video call, it can be used to text chat with the other party
     *
     * @param msg                sent text content
     * @param channelMsgListener callback interface
     *                           {@link ChannelMsgListener} ChannelMsgListener listener object
     * @return Returns the unique identifier for the current message
     * @see ChannelMsgListener
     */
    public String sendMsg(String msg, ChannelMsgListener channelMsgListener) {
        return WSManager.getInstance().sendMsg(localUid, msg, channelMsgListener);
    }
}