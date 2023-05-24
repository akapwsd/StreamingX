package com.code.bean;

/**
 * anchor cover Object
 *
 * @author chan
 * @version 1.0.5
 * @since 1.0.5
 * @since 6 May 2023
 * @since JDK8
 */
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
