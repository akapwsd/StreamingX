package com.code.myapplication;

import android.app.Application;

import com.code.listener.InitResultListener;
import com.code.youyu.api.RtcManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RtcManager.getInstance().initRtc(this, "", "", "", "", new InitResultListener() {
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
