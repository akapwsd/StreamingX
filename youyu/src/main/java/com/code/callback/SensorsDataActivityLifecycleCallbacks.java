package com.code.callback;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.code.okhttp.WSManager;
import com.code.utils.LogUtil;
import com.code.youyu.api.StreamingXRtcManager;

import java.util.ArrayList;

public class SensorsDataActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "SensorsDataActivityLifecycleCallbacks";
    private final ArrayList<String> activityList = new ArrayList<>();

    public SensorsDataActivityLifecycleCallbacks() {
        StreamingXRtcManager.getInstance().isRegisterActivityLifecycleCallBack = true;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        activityList.add(activity.getLocalClassName());
        LogUtil.d(TAG, "onActivityCreated activity counts" + activityList.size());
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }


    @Override
    public void onActivityResumed(@NonNull Activity activity) {
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        for (String activityName : activityList) {
            if (activityName.equals(activity.getLocalClassName())) {
                boolean remove = activityList.remove(activityName);
                LogUtil.d(TAG, "onActivityDestroyed remove:" + remove);
                break;
            }
        }
        LogUtil.d(TAG, "onActivityDestroyed activity counts:" + activityList.size());
        if (activityList.size() <= 0) {
            trackAppEnd();
        }
    }

    private void trackAppStart() {
    }

    private void trackAppEnd() {
        LogUtil.d(TAG, "trackAppEnd is start");
        StreamingXRtcManager.getInstance().closeVideoChat();
        WSManager.getInstance().disconnect(1002, "disconnect");
    }
}
