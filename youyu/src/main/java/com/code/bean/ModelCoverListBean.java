package com.code.bean;

import java.util.ArrayList;

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
    private ArrayList<AvatarBean> others;

    public ArrayList<AvatarBean> getOthers() {
        return others;
    }

    public DefaultAvatarBean getDefaultAvatar() {
        return defaultAvatar;
    }

    public void setDefaultAvatar(DefaultAvatarBean defaultAvatar) {
        this.defaultAvatar = defaultAvatar;
    }

    public void setOthers(ArrayList<AvatarBean> others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "ModelCoverListBean{" +
                "defaultAvatar=" + defaultAvatar +
                ", others=" + others +
                '}';
    }
}
