package com.code.bean;

public class ModelInfoBean {
    private String batchId;
    private int uid;
    private String avatarThumb;
    private String avatarStandard;
    private String avatarClear;
    private int state;

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

    public int getState() {
        return state;
    }

    @Override
    public String toString() {
        return "ModelInfoBean{" +
                "batchId='" + batchId + '\'' +
                ", uid=" + uid +
                ", avatarThumb='" + avatarThumb + '\'' +
                ", avatarStandard='" + avatarStandard + '\'' +
                ", avatarClear='" + avatarClear + '\'' +
                ", state=" + state +
                '}';
    }
}
