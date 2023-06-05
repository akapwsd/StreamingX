package com.code.myapplication;

import android.app.Application;

import com.code.youyu.api.StreamingXRtcManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StreamingXRtcManager.getInstance().enableLog();
    }
}
