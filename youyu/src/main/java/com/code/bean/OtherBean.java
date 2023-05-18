package com.code.bean;

public class OtherBean {
    private String batchId;
    private int uid;
    private String avatarThumb;
    private String avatarStandard;
    private String avatarClear;
    private int state;

    public int getState() {
        return state;
    }

    public String getBatchId() {
        return batchId;
    }

    public int getUid() {
        return uid;
    }

    public String getAvatarThumb() {
        return avatarThumb;
    }

    public String getAvatarStandard() {
        return avatarStandard;
    }

    public String getAvatarClear() {
        return avatarClear;
    }

    @Override
    public String toString() {
        return "OtherBean{" +
                "batchId='" + batchId + '\'' +
                ", uid=" + uid +
                ", avatarThumb='" + avatarThumb + '\'' +
                ", avatarStandard='" + avatarStandard + '\'' +
                ", avatarClear='" + avatarClear + '\'' +
                ", state=" + state +
                '}';
    }
}
