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

    /**
     * 缓存声网正常心跳时间
     * @param time
     */
    public void setAgoraTime(long time){
        RtcSpBase.put("agora_time", time);
    }

    public long getAgoraTime(){
        return (long) RtcSpBase.get("agora_time", 0L);
    }
}
