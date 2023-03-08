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

    private Timer seeTimer;
    private TimerTask seeTimerTask;
    private int seeTime = 60;

    private void initSeeTimer() {
        seeTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (seeTime == 0) {
                    destroyTimer();
                    destroySeeTimer();
                } else {
                    seeTime--;
                }
            }
        };
        seeTimer = new Timer();
    }

    public void destroySeeTimer() {
        seeTime = 60;
        if (seeTimer != null) {
            seeTimer.cancel();
            seeTimer = null;
        }
        if (seeTimerTask != null) {
            seeTimerTask.cancel();
            seeTimerTask = null;
        }
    }

    public void startSeeTimer() {
        destroySeeTimer();
        initSeeTimer();
        seeTimer.schedule(seeTimerTask, 0, 1000);
    }

    private Timer timer;
    private TimerTask timerTask;
    private int time = 3;

    private void initTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (haveFace) {
                    time = 3;
                } else {
                    if (time == 0) {
                        //TODO
                        time = 3;
                        destroySeeTimer();
                        destroyTimer();
                    } else {
                        time--;
                    }
                }
            }
        };
        timer = new Timer();
    }

    public void destroyTimer() {
        time = 3;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    public void startTimer() {
        destroyTimer();
        initTimer();
        timer.schedule(timerTask, 0, 1000);
    }

    /**
     * 事件处理程序注册到RTC引擎用于RTC回调。
     * <p>
     * 注意UI操作需要在UI线程，因为RTC
     * <p>
     * 引擎在单独的线程中处理事件。
     */
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

        /**
         *在本地用户加入指定频道时发生。
         *通道名称分配基于在joinChannel方法中指定的channelName。
         *如果在调用joinChannel时未指定，则服务器会自动分配一个uid。
         *
         * @param //channel频道名称。
         * @param //uid用户ID。
         * @param经过的时间从用户调用joinChannel到触发此回调为止的时间（毫秒）。
         */

        @Override
        public void onJoinChannelSuccess(String channel, final int uid, int elapsed) {
            iRtcEngineEventCallBackHandler.onJoinChannelSuccess(channel, uid, elapsed);
        }

        /**
         *在接收和解码第一个远程视频帧时发生。
         *在以下两种情况下都会触发此回调：
         *
         *远程用户加入频道并发送视频流。
         *远程用户停止发送视频流，并在15秒后重新发送。 可能的原因包括：
         *远程用户离开频道。
         *远程用户脱机。
         *远程用户调用mutateLocalVideoStream方法。
         *远程用户调用disableVideo方法。
         *
         * @param //uid发送视频流的远程用户的用户ID。
         * @param //width视频流的宽度（像素）。
         * @param //height视频流的高度（像素）。
         * @param经过时间从本地用户调用joinChannel方法直到触发此回调为止的时间（毫秒）。
         */
        @Override
        public void onFirstRemoteVideoDecoded(final int uid, int width, int height, int elapsed) {
            iRtcEngineEventCallBackHandler.onFirstRemoteVideoDecoded(uid, width, height, elapsed);
        }

        /**
         *当远程用户（通信）/主机（实时广播）离开频道时发生。
         *
         *用户脱机的原因有两个：
         *
         *离开频道：当使用者/主机离开频道时，使用者/主机会传送
         *再见消息。收到此消息后，SDK会确定
         *用户/主机离开频道。
         *
         *脱机：在一定时间内未收到用户或主机的数据包
         *时间段（通讯配置文件为20秒，实时直播则为20秒
         *广播配置文件），SDK假定用户/主机脱机。穷人
         *网络连接可能会导致错误的检测，因此我们建议使用
         * Agora RTM SDK提供可靠的离线检测。
         *
         * @param //uid离开通道或脱机的用户或主机的ID。
         * @param原因用户下线的原因：
         *
         * USER_OFFLINE_QUIT（0）：用户离开了当前频道。
         * USER_OFFLINE_DROPPED（1）：SDK超时并且用户脱机，因为在一定时间内未收到任何数据包。如果用户退出呼叫且消息未传递到SDK（由于通道不可靠），则SDK会假定用户已下线。
         * USER_OFFLINE_BECOME_AUDIENCE（2）：（仅实时广播。）客户端角色从主持人切换到听众。
         */

        @Override
        public void onUserOffline(final int uid, int reason) {
            iRtcEngineEventCallBackHandler.onUserOffline(uid, reason);
        }

        /**
         * 远端用户（通信场景）/主播（直播场景）加入当前频道回调。
         * @param uid 新加入频道的远端用户/主播 ID
         * @param elapsed    从本地用户调用 joinChannel/setClientRole 到触发该回调的延迟（毫秒）
         *
         * 通信场景下，该回调提示有远端用户加入了频道，并返回新加入用户的 ID；如果加入之前，已经有其他用户在频道中了，新加入的用户也会收到这些已有用户加入频道的回调
         * 直播场景下，该回调提示有主播加入了频道，并返回该主播的用户 ID。如果在加入之前，已经有主播在频道中了，新加入的用户也会收到已有主播加入频道的回调。Agora 建议连麦主播不超过 17 人
         * 该回调在如下情况下会被触发：
         *
         * 远端用户/主播调用 joinChannel 方法加入频道。
         * 远端用户加入频道后调用 setClientRole 将用户角色改变为主播。
         * 远端用户/主播网络中断后重新加入频道。
         * 主播通过调用 addInjectStreamUrl 方法成功输入在线媒体流。
         *
         * 直播场景下：
         * 主播间能相互收到新主播加入频道的回调，并能获得该主播的用户 ID
         * 观众也能收到新主播加入频道的回调，并能获得该主播的用户 ID
         * 当 Web 端加入直播频道时，只要 Web 端有推流，SDK 会默认该 Web 端为主播，并触发该回调
         */
        @Override
        public void onUserJoined(int uid, int elapsed) {
            iRtcEngineEventCallBackHandler.onUserJoined(uid, elapsed);
            handler.post(() -> {
                if (mediaDataObserverPlugin != null) {
                    mediaDataObserverPlugin.addDecodeBuffer(uid);
                }
            });
        }

        /**
         * 重新加入频道回调。
         * 有时候由于网络原因，客户端可能会和服务器失去连接，SDK 会进行自动重连，自动重连成功后触发此回调方法。
         * @param channel 频道名
         * @param uid     用户 ID。如果 joinChannel 中指定了 uid，则此处返回该 ID；否则使用 Agora 服务器自动分配的 ID
         * @param elapsed 从开始重连到重连成功的时间（毫秒）
         */
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
