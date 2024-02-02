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

    public void setToken(String token) {
        RtcSpBase.put("model_token", token);
    }

    public String getToken() {
        return (String) RtcSpBase.get("model_token", "");
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

    public void setChannelId(String calling_channel) {
        RtcSpBase.put("calling_channel", calling_channel);
    }

    public String getChannelId() {
        return (String) RtcSpBase.get("calling_channel", "");
    }

    public void setMessageId(long messageId) {
        RtcSpBase.put("message_id", messageId);
    }

    public long getMessageId() {
        return (long) RtcSpBase.get("message_id", 0L);
    }

    public void setUserUid(String uid) {
        RtcSpBase.put("user_uid", uid);
    }

    public String getUserUid() {
        return (String) RtcSpBase.get("user_uid", "");
    }

    public void setPaasImPrefix(String paasImPrefix) {
        RtcSpBase.put("paasImPrefix", paasImPrefix);
    }

    public String getPaasImPrefix() {
        return (String) RtcSpBase.get("paasImPrefix", "");
    }
}
