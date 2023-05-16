package com.code.myapplication;

import android.app.Application;

import com.code.youyu.api.RtcManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RtcManager.getInstance().enableLog();
    }
}
