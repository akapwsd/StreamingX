package com.code.callback;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.code.okhttp.WSManager;
import com.code.utils.LogUtil;
import com.code.youyu.api.StreamingXRtcManager;

public class SensorsDataActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "SensorsDataActivityLifecycleCallbacks";
    private int startActivityCount = 0;

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        startActivityCount++;
        LogUtil.d(TAG, "onActivityCreated startActivityCount:" + startActivityCount);
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
        startActivityCount = startActivityCount > 0 ? --startActivityCount : 0;
        LogUtil.d(TAG, "onActivityDestroyed startActivityCount:" + startActivityCount);
        if (startActivityCount <= 0) {
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
