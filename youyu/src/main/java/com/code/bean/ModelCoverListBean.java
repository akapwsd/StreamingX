package com.code.bean;

public class ModelCoverListBean {
    private DefaultAvatarBean defaultAvatar;
    private OtherBean others;

    public DefaultAvatarBean getDefaultAvatar() {
        return defaultAvatar;
    }

    public OtherBean getOthers() {
        return others;
    }

    @Override
    public String toString() {
        return "ModelCoverListBean{" + "defaultAvatar=" + defaultAvatar + ", others=" + others + '}';
    }
}
