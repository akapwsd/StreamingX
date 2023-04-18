package com.example.myapplication;

import android.app.Application;

import com.example.listener.InitResultListener;
import com.example.utils.LogUtil;
import com.example.youyu.api.RtcManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RtcManager.getInstance().initRtc(this, "", new InitResultListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFail(int code, String msg) {

            }
        });
        RtcManager.getInstance().enableLog();
    }
}
