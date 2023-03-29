package com.example.youyu.api;


import android.content.Context;

import com.example.rtc.BaseRtcEngineManager;

public class RtcManager {
    private static final class RtcManagerHolder {
        static final RtcManager rtcManager = new RtcManager();
    }

    public static RtcManager getInstance() {
        return RtcManagerHolder.rtcManager;
    }

    public void initRtc(Context context){
        BaseRtcEngineManager.getInstance().initBaseRtc(context);
    }
}
