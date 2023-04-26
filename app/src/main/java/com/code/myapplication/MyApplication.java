package com.code.myapplication;

import android.app.Application;

import com.code.youyu.api.RtcManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RtcManager.getInstance().initRtc(this, "84849b48da8f11ed97a904d9f5ac3e12", "t3rUAwLO4DyZOgyg80zMTkROL0_dD_oO0b!wohkL", "");
        RtcManager.getInstance().enableLog();
    }
}
