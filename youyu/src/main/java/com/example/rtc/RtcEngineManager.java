package com.example.rtc;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.constants.AgoraConstants;
import com.example.listener.FacePositionListener;
import com.example.rawdata.MediaDataObserverPlugin;
import com.example.service.AgoraLocalService;
import com.example.utils.RtcSpUtils;

import java.util.Timer;
import java.util.TimerTask;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;

public class RtcEngineManager {
    private static final String TAG = "RtcEngineManager";
    private AgoraLocalService mService = null;
    private volatile static RtcEngineManager instance;
    private SurfaceView mLocalView;
    private SurfaceView mRemoteView;
    private FrameLayout bigView;
    private FrameLayout localView;
    private int mBigViewState = 0;
    public static final int BIG_VIEW_STATE_REMOTE = 0;
    public static final int BIG_VIEW_STATE_LOCAL = 1;

    private ServiceConnection mConnection;
    private int uid = -1;//好友uid
    private boolean isLive = false;
    private int level = 0;
    private int RemoteId = -1;//好友对应声网的id
    private String avatar = "";//好友头像
    private String peerName = "";//好友名字
    private String roomId = "";//房间号
    private String cCover_url;//小视频，封面地址
    private int cCover_type;//小视频，封面类型
    private boolean isMatch = false;
    private double diamondPrice = 0;//主播价格
    private int userType = -1;//好友或者匹配到的用户 userType类型
    private int selfType = -1;//自己的类型 0.普通用户 1.客服  3.主播
    private boolean isCallVideo = false;//是否正在进行声网通信  默认false
    private boolean windowIsShow = false;//悬浮小窗体是否显示 默认false
    private boolean isBestNum = false;//是否是靓号
    private boolean isLackBalance = false;//从小窗恢复时 如果余额不足 继续显示红色字体
    private boolean haveCalling = false;
    private boolean callVideoSelfIsJoin = false;//拨打方自己是否加入了房间
    private boolean callVideoRemoteIsJoin = false;//拨打方远端是否加入了房间
    private boolean haveFace = false;
    private int modelPrice = 0;
    private boolean isNeedMatch = true;//挂断后是否需要继续匹配 默认为true  当余额不足需要跳转充值页面时 就不需要再继续匹配

    private boolean isNeedMatchDelay = true;//跳转到匹配界面后是否延时几秒后在进行匹配 默认true

    private boolean isCallForModel = false;

    private FacePositionListener facePositionListener;

    public boolean isCallForModel() {
        return isCallForModel;
    }

    public void setCallForModel(boolean callForModel) {
        isCallForModel = callForModel;
    }

    public boolean isNeedMatchDelay() {
        return isNeedMatchDelay;
    }

    public void setNeedMatchDelay(boolean needMatchDelay) {
        isNeedMatchDelay = needMatchDelay;
    }

    public boolean isNeedMatch() {
        return isNeedMatch;
    }

    public void setNeedMatch(boolean needMatch) {
        isNeedMatch = needMatch;
    }

    //声网视频当前剩余分钟数
    private int money = -1;

    private int buy = -1;

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isHaveCalling() {
        return haveCalling;
    }

    public void setHaveCalling(boolean haveCalling) {
        this.haveCalling = haveCalling;
    }

    public void setFacePositionListener(FacePositionListener facePositionListener) {
        this.facePositionListener = facePositionListener;
    }

    public void removeFacePositionListener() {
        if (facePositionListener != null) {
            facePositionListener = null;
        }
    }

    /**
     * 自己加入房间通知
     *
     * @param data
     */
    public void selfJoinRoomSuccess(Object data) {
        setCallVideoSelfIsJoin(true);
    }

    /**
     * 好友加入房间通知
     *
     * @param data
     */
    public void remoteJoinRoomSuccess(Object data) {
        setCallVideoRemoteIsJoin(true);
    }

    /**
     * 对方拒接了拨打请求 将本地加入房间状态 刷新为false
     * 因为在拨打好友时 就会去主动加入房间
     */
    public void remoteRejectCallVideo() {
        setCallVideoSelfIsJoin(false);
    }

    public boolean isCallVideoSelfIsJoin() {
        return callVideoSelfIsJoin;
    }

    public void setCallVideoSelfIsJoin(boolean callVideoSelfIsJoin) {
        this.callVideoSelfIsJoin = callVideoSelfIsJoin;
    }

    public boolean isCallVideoRemoteIsJoin() {
        return callVideoRemoteIsJoin;
    }

    public void setCallVideoRemoteIsJoin(boolean callVideoRemoteIsJoin) {
        this.callVideoRemoteIsJoin = callVideoRemoteIsJoin;
    }

    public boolean isLackBalance() {
        return isLackBalance;
    }

    public void setLackBalance(boolean lackBalance) {
        isLackBalance = lackBalance;
    }

    public boolean isBestNum() {
        return isBestNum;
    }

    public void setBestNum(boolean bestNum) {
        isBestNum = bestNum;
    }

    private RtcEngineManager() {
        if (BaseRtcEngineManager.getInstance().getRtcEngine() != null) {
            initializeEngine();
        }
    }

    public static RtcEngineManager getRtcEngineManager() {
        if (instance == null) {
            synchronized (RtcEngineManager.class) {
                if (instance == null) {
                    instance = new RtcEngineManager();
                }
            }
        }
        return instance;
    }

    public int getModelPrice() {
        return modelPrice;
    }

    public void setModelPrice(int modelPrice) {
        this.modelPrice = modelPrice;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isWindowIsShow() {
        return windowIsShow;
    }

    public void setWindowIsShow(boolean windowIsShow) {
        this.windowIsShow = windowIsShow;
    }

    public boolean isCallVideo() {
        return isCallVideo;
    }

    public void setCallVideo(boolean callVideo) {
        isCallVideo = callVideo;
    }

    public int getSelfType() {
        return selfType;
    }

    public void setSelfType(int selfType) {
        this.selfType = selfType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public boolean isMatch() {
        return isMatch;
    }

    public void setMatch(boolean match) {
        isMatch = match;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setRemoteId(int RemoteId) {
        this.RemoteId = RemoteId;
    }

    public int getRemoteId() {
        return RemoteId;
    }

    public String getPeerName() {
        return peerName;
    }

    public void setPeerName(String peerName) {
        this.peerName = peerName;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public AgoraLocalService getService() {
        return mService;
    }


    /**
     * 开启声网心跳服务
     */
    public void startServices(String roomId, Context context) {
        this.roomId = roomId;
        Intent intent = new Intent(context, AgoraLocalService.class);
        if (mConnection == null) {
            mConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    RtcSpUtils.getInstance().setAgoraTime(System.currentTimeMillis());
                    AgoraLocalService.LocalBinder binder = (AgoraLocalService.LocalBinder) service;
                    mService = binder.getService();
                    mService.onTime(1);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    RtcSpUtils.getInstance().setAgoraTime(0L);
                    mConnection = null;
                    mService = null;
                    setLackBalance(false);
                }
            };
            context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }
    }

    /**
     * 关闭心跳服务
     */
    public void stopServices(Context context) {
        if (mConnection != null) {
            RtcEngineManager.getRtcEngineManager().isCallVideo = false;
            context.unbindService(mConnection);
            mConnection = null;
        }
    }

    /**
     * 心跳超时 房间不存在了
     */
    public void heartTimeOut(Context context) {
        closeVideoChat(context);
    }

    /**
     * 心跳房间异常  关闭视频或者音频通话
     */
    public void closeVideoChat(Context context) {
        isCallForModel = false;
        setCallVideo(false);
        setLackBalance(false);
        setCallVideoRemoteIsJoin(false);
        setCallVideoSelfIsJoin(false);
        setMoney(-1);
        BaseRtcEngineManager.getInstance().getRtcEngine().leaveChannel();
        BaseRtcEngineManager.getInstance().getRtcEngine().stopPreview();
        stopServices(context);
    }


    //对方挂断 或者 有人房间心跳超时
    public void hungUp(Context context) {
        isCallForModel = false;
        setLackBalance(false);
        setCallVideo(false);
        setCallVideoRemoteIsJoin(false);
        setCallVideoSelfIsJoin(false);
        RtcSpUtils.getInstance().setAgoraTime(0L);
        setMoney(-1);
        stopServices(context);
        RtcEngine mRtcEngine = RtcEngineManager.getRtcEngineManager().getRtcEngine();
        if (mRtcEngine != null) {
            mRtcEngine.leaveChannel();
            mRtcEngine.stopPreview();
        }
    }

    public void selfQuitRoom(Context context) {
        isCallForModel = false;
        setLackBalance(false);
        setCallVideo(false);
        setCallVideoRemoteIsJoin(false);
        setCallVideoSelfIsJoin(false);
        RtcSpUtils.getInstance().setAgoraTime(0L);
        setMoney(-1);
        stopServices(context);
        RtcEngine mRtcEngine = RtcEngineManager.getRtcEngineManager().getRtcEngine();
        if (mRtcEngine != null) {
            mRtcEngine.leaveChannel();
            mRtcEngine.stopPreview();
        }
    }

    private Activity context;

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    private int mediaType = -1;

    /**
     * 给主播打电话
     * 这个方法还有一个重载方法
     *
     * @param uid
     */
    public void startCallVideo(int uid, String peerName, String avatar, int userType, int mediaType, String plusCircle, int cCover_type, String cCover_url) {
        isCallForModel = false;
        this.peerName = peerName;
        this.uid = uid;
        this.avatar = avatar;
        this.userType = userType;
        this.mediaType = mediaType;
        this.isBottomRecharge = true;
        this.plusCircle = plusCircle;
        clearJoinRoomStatus();
        //先查询该主播的价格
        QueryThePrice(uid);
        this.cCover_type = cCover_type;
        this.cCover_url = cCover_url;
    }

    //当钻石不足时  从底部弹出充值
    private boolean isBottomRecharge;

    private String plusCircle;

    public boolean isBottomRecharge() {
        return isBottomRecharge;
    }

    /**
     * 拨打好友时 重置加入房间状态
     */
    public void clearJoinRoomStatus() {
        //重置远端(好友)加入房间状态
        setCallVideoRemoteIsJoin(false);
        //重置自己加入房间状态
        setCallVideoSelfIsJoin(false);
    }

    /**
     * 被好友拨打时 重置自己加入房间的状态
     */
    public void clearSelfJoinRoomStatus() {
        //重置自己加入房间状态
        setCallVideoSelfIsJoin(false);
    }

    public void setBottomRecharge(boolean bottomRecharge) {
        isBottomRecharge = bottomRecharge;
    }

    /**
     * 给主播打电话-热门列表拨打
     *
     * @param uid isBottomRecharge 当钻石不足时  从底部弹出充值
     */
    public void startCallVideo(int uid, String peerName, String avatar, int userType, int mediaType, String plusCircle, boolean isBottomRecharge, int cCover_type, String cCover_url) {
        isCallForModel = false;
        this.peerName = peerName;
        this.uid = uid;
        this.avatar = avatar;
        this.userType = userType;
        this.mediaType = mediaType;
        this.isBottomRecharge = isBottomRecharge;
        clearJoinRoomStatus();
        this.plusCircle = plusCircle;
        //先查询该主播的价格
        QueryThePrice(uid);
        this.cCover_type = cCover_type;
        this.cCover_url = cCover_url;
    }

    public void beginCallVideo(int uid, int mediaType, int roomType) {
        isCallForModel = true;
//        TcpRequestHelper.getInstance().SfDialAgora(uid, mediaType, roomType);
    }

    /**
     * 拨打主播好友时 先查询好友价格
     *
     * @param uid
     */
    public void QueryThePrice(int uid) {
//        TcpRequestHelper.getInstance().getModelPrice(uid);
    }

    private Long age;
    private int gender;
    private String country_code = "";

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public static String convertToTime(int seconds) {
        if (seconds < 0) {
            seconds = 0;
        }
        int h = seconds / 3600;                //小时
        int m = (seconds % 3600) / 60;        //分钟
        int s = (seconds % 3600) % 60;        //秒
        String hh = "";
        String mm = "";
        String ss = "";
        if (String.valueOf(h).length() == 1) {
            //前面补一位0
            hh = "0" + h;
        } else {
            hh = String.valueOf(h);
        }
        if (String.valueOf(m).length() == 1) {
            //前面补一位0
            mm = "0" + m;
        } else {
            mm = String.valueOf(m);
        }
        if (String.valueOf(s).length() == 1) {
            //前面补一位0
            ss = "0" + s;
        } else {
            ss = String.valueOf(s);
        }

        if (h > 0) {
            return hh + ":" + mm + ":" + ss;
        }
        return mm + ":" + ss;
    }

    public void startTime() {
        if (mService != null) {
            mService.onTime(1);
        }
    }

    public RtcEngine getRtcEngine() {
        return BaseRtcEngineManager.getInstance().getRtcEngine();
    }

    public final void preview(boolean start, SurfaceView view, int uid) {
        if (start) {
            //显示本地预览界面
            BaseRtcEngineManager.getInstance().getRtcEngine().setupLocalVideo(new VideoCanvas(view, VideoCanvas.RENDER_MODE_HIDDEN, uid));
            BaseRtcEngineManager.getInstance().getRtcEngine().startPreview();
        } else {
            //onPause 状态 停止 预览
            BaseRtcEngineManager.getInstance().getRtcEngine().stopPreview();
        }
    }

    public final void SwitchCamera() {
        BaseRtcEngineManager.getInstance().getRtcEngine().switchCamera();
    }

    /**
     * 加入房间
     *
     * @param channel     频道名
     * @param uid         用户id
     * @param accessToken 声网Token
     */
    public final void userJoinChannel(String accessToken, final String channel, int uid) {
        if (BaseRtcEngineManager.getInstance().getRtcEngine() != null) {
            BaseRtcEngineManager.getInstance().getRtcEngine().setChannelProfile(1);
            BaseRtcEngineManager.getInstance().getRtcEngine().setClientRole(1);
            BaseRtcEngineManager.getInstance().getRtcEngine().joinChannel(accessToken, channel, "Extra Optional Data", uid);
        }
    }

    private void initializeEngine() {
        try {
            BaseRtcEngineManager.getInstance().getRtcEngine().setChannelProfile(io.agora.rtc.Constants.CHANNEL_PROFILE_COMMUNICATION);
            BaseRtcEngineManager.getInstance().getRtcEngine().setBeautyEffectOptions(true, AgoraConstants.BEAUTY_OPTIONS);
            BaseRtcEngineManager.getInstance().getRtcEngine().enableFaceDetection(true);
        } catch (Exception e) {
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
    }


    /**
     * 离开频道。
     * 离开频道，即挂断或退出通话。
     * 调用 joinChannel 后，必须调用 leaveChannel 结束通话，否则无法开始下一次通话。
     * 不管当前是否在通话中，都可以调用 leaveChannel，没有副作用。
     * 该方法会把会话相关的所有资源释放掉。该方法是异步操作，调用返回时并没有真正退出频道。
     * 成功调用该方法离开频道后，本地会触发 onLeaveChannel 回调；通信场景下的用户和直播场景下的主播离开频道后，远端会触发 onUserOffline 回调。
     */
    private void leaveChannel() {
        BaseRtcEngineManager.getInstance().getRtcEngine().leaveChannel();
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

    private CallForModelListener callForModelListener;

    public interface CallForModelListener {
        void callSuccess(String token, String roomId);
    }

    public void setCallForModelListener(CallForModelListener callForModelListener) {
        this.callForModelListener = callForModelListener;
    }

    public void closeCallForModelListener() {
        if (callForModelListener != null) {
            callForModelListener = null;
        }
    }

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

    private void showSmallLive(Context context, FrameLayout view, int uid) {
        localView = view;
        RtcEngineManager.getRtcEngineManager().getRtcEngine().enableLocalVideo(true);
        mLocalView = RtcEngine.CreateRendererView(context);
        mLocalView.setZOrderOnTop(true);
        localView.addView(mLocalView);
        RtcEngineManager.getRtcEngineManager().getRtcEngine().setupLocalVideo(
                new VideoCanvas(
                        mLocalView,
                        VideoCanvas.RENDER_MODE_HIDDEN,
                        uid
                )
        );
        mLocalView.setZOrderMediaOverlay(true);
        BaseRtcEngineManager.getInstance().setFacePositionListener(new FacePositionListener() {
            @Override
            public void haveFace(IRtcEngineEventHandler.AgoraFacePositionInfo agoraFacePositionInfo) {
                if (agoraFacePositionInfo.x > 0) {

                }
            }

            @Override
            public void noFace() {

            }
        });
    }

    /**
     * 加入房间
     */
    private void joinChannel(int uid, String token) {
        RtcEngineManager.getRtcEngineManager().userJoinChannel(token, roomId, uid);
    }

    private void showBigLive(Context context, FrameLayout view, int uid) {
        bigView = view;
        mRemoteView = RtcEngine.CreateRendererView(context);
        RtcEngineManager.getRtcEngineManager().getRtcEngine().setupRemoteVideo(
                new VideoCanvas(
                        mRemoteView,
                        VideoCanvas.RENDER_MODE_HIDDEN,
                        uid
                )
        );
        bigView.addView(
                mRemoteView,
                0,
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                )
        );
    }

    /**
     * 切换大小屏幕显示
     */
    private void switchSmallOrBigLive(Context context, int mRemoteUid, int localUid) {
        RtcEngineManager.getRtcEngineManager().getRtcEngine().setupRemoteVideo(null);
        if (mBigViewState == BIG_VIEW_STATE_REMOTE) {
            mBigViewState = BIG_VIEW_STATE_LOCAL;
            //清除小窗口添加的 view
            if (mLocalView.getParent() != null) {
                ((FrameLayout) mLocalView.getParent()).removeView(mLocalView);
                mLocalView = null;
            }
            //清除大窗口添加的 view
            if (mRemoteView.getParent() != null) {
                ((FrameLayout) mRemoteView.getParent()).removeView(mRemoteView);
                mRemoteView = null;
            }
            //为小窗口添加远端视频流
            mRemoteView = RtcEngine.CreateRendererView(context);
            RtcEngineManager.getRtcEngineManager().getRtcEngine().setupRemoteVideo(
                    new VideoCanvas(
                            mRemoteView,
                            VideoCanvas.RENDER_MODE_HIDDEN,
                            mRemoteUid
                    )
            );
            localView.addView(mRemoteView);
            //为大窗口添加本地视频流
            mLocalView = RtcEngine.CreateRendererView(context);
            RtcEngineManager.getRtcEngineManager().getRtcEngine().setupLocalVideo(
                    new VideoCanvas(
                            mLocalView,
                            VideoCanvas.RENDER_MODE_HIDDEN,
                            localUid
                    )
            );
            bigView.addView(mLocalView);
            //把小视频窗口至于顶端
            mRemoteView.setZOrderMediaOverlay(true);
        } else {
            mBigViewState = BIG_VIEW_STATE_REMOTE;
            //清除小窗口添加的 view
            if (mLocalView.getParent() != null) {
                ((FrameLayout) mLocalView.getParent()).removeView(mLocalView);
                mLocalView = null;
            }
            //清除大窗口添加的 view
            if (mRemoteView.getParent() != null) {
                ((FrameLayout) mRemoteView.getParent()).removeView(mRemoteView);
                mRemoteView = null;
            }
            mLocalView = RtcEngine.CreateRendererView(context);
            localView.addView(mLocalView);
            RtcEngineManager.getRtcEngineManager().getRtcEngine().setupLocalVideo(
                    new VideoCanvas(
                            mLocalView,
                            VideoCanvas.RENDER_MODE_HIDDEN,
                            localUid
                    )
            );

            mRemoteView = RtcEngine.CreateRendererView(context);
            RtcEngineManager.getRtcEngineManager().getRtcEngine().setupRemoteVideo(
                    new VideoCanvas(
                            mRemoteView,
                            VideoCanvas.RENDER_MODE_HIDDEN,
                            mRemoteUid
                    )
            );
            bigView.addView(
                    mRemoteView,
                    0,
                    new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT
                    )
            );
            //把小视频窗口至于顶端
            mLocalView.setZOrderMediaOverlay(true);
        }
    }

}
