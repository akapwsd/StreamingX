package com.code.utils;

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

    public void setAccessKeyId(String access_key_id) {
        RtcSpBase.put("access_key_id", access_key_id);
    }

    public String getAccessKeyId() {
        return (String) RtcSpBase.get("access_key_id", "");
    }

    public void setAccessKeySecret(String access_key_secret) {
        RtcSpBase.put("access_key_secret", access_key_secret);
    }

    public String getAccessKeySecret() {
        return (String) RtcSpBase.get("access_key_secret", "");
    }

    public void setSessionToken(String session_token) {
        RtcSpBase.put("session_token", session_token);
    }

    public String getSessionToken() {
        return (String) RtcSpBase.get("session_token", "");
    }
}
