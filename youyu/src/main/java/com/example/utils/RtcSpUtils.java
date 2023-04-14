package com.example.utils;

public class RtcSpUtils {

    private static final String TAG = "RtcSpUtils";

    private static RtcSpUtils mSPUserUtils;

    private RtcSpUtils() {

    }

    public synchronized static RtcSpUtils getInstance() {
        synchronized (RtcSpUtils.class) {
            if (mSPUserUtils == null) {
                mSPUserUtils = new RtcSpUtils();
            }
        }
        return mSPUserUtils;
    }

    public void Clear() {
        RtcSpBase.clear();
    }

    public void setAgoraTime(long time) {
        RtcSpBase.put("agora_time", time);
    }

    public long getAgoraTime() {
        return (long) RtcSpBase.get("agora_time", 0L);
    }

    public void setToken(String token) {
        RtcSpBase.put("rtc_token", token);
    }

    public String getToken() {
        return (String) RtcSpBase.get("rtc_token", "");
    }
}
