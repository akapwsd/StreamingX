package com.example.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.AgoraBean;
import com.example.rtc.RtcEngineManager;
import com.example.utils.AgoraSizeUtils;
import com.example.utils.RtcSpUtils;
import com.example.youyu.R;

import java.lang.ref.SoftReference;
import java.util.Timer;
import java.util.TimerTask;

public class AgoraLocalService extends Service {
    private final static String TAG = "AgoraLocalService";
    private final IBinder mBinder = new LocalBinder();
    private Handler handler;//心跳发送Handler
    private long time = 10000;//声网房间心跳时间10s
    private int checkTime = 20;//如果20秒没有收到正常心跳 挂断当前正在通话的音频及视频
    private TimerTask task;//通话时间计时
    private Timer timer;
    private long startTime;//视频通话的时间
    private SoftReference<Handler> sfHandler;
    private AgoraBean agoraBean;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //TODO send beat
            if (handler != null) {
                handler.postDelayed(this, time);
            }
        }
    };

    public class LocalBinder extends Binder {
        public AgoraLocalService getService() {
            return AgoraLocalService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler = null;
        mHandler = null;
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 绑定该service时 触发
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        //TODO send beat
        if (handler != null) {
            if (runnable != null) {
                handler.removeCallbacks(runnable);
            }
            handler = null;
        }
        handler = new Handler();
        handler.postDelayed(runnable, 3000);
        return mBinder;
    }

    /**
     * 开始计时  通话计时
     *
     * @param time 开始时间点秒
     */
    public void onTime(long time) {
        startTime = time;
        if (timer == null) {
            if (task != null) {
                task.cancel();
            }
            task = new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 1;
                    if (mHandler != null) {
                        mHandler.sendMessage(message);
                    }
                }
            };
            timer = new Timer(true);
            timer.schedule(task, 0, 1000);
        }
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                checkHeartStatus();
                startTime++;
                //TODO listener out time
            }
            return false;
        }
    });

    /**
     * 检查心跳状态
     */
    private void checkHeartStatus() {
        long lastTime = RtcSpUtils.getInstance().getAgoraTime();
        long currentTime = System.currentTimeMillis();
        if (lastTime == 0) {
            return;
        }
        if ((currentTime - lastTime) / 1000 > checkTime) {
            boolean callVideo = RtcEngineManager.getRtcEngineManager().isCallVideo();
            if (callVideo) {
                RtcEngineManager.getRtcEngineManager().closeVideoChat(this);
                //TODO listener out heart is dead
            }
        }
    }

    public String getStartTime() {
        return convertToTime((int) startTime);
    }

    public static String secondsToTime(int seconds) {
        int h = seconds / 3600;                //小时
        int m = (seconds % 3600) / 60;        //分钟
        int s = (seconds % 3600) % 60;        //秒
        return h + ":" + m + ":" + s;
    }

    public static String convertToTime(int seconds) {
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

    public Handler getDelayHandler() {
        if (sfHandler == null) {
            sfHandler = new SoftReference<>(new Handler(getMainLooper()));
        }
        Handler handler = sfHandler.get();
        if (handler == null) {
            sfHandler = new SoftReference<>(new Handler(getMainLooper()));
            return sfHandler.get();
        }
        return handler;
    }
}
