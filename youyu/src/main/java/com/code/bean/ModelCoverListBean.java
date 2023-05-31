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
    private ArrayList<OtherBean> others;

    public ArrayList<OtherBean> getOthers() {
        return others;
    }

    public DefaultAvatarBean getDefaultAvatar() {
        return defaultAvatar;
    }

    @Override
    public String toString() {
        return "ModelCoverListBean{" +
                "defaultAvatar=" + defaultAvatar +
                ", others=" + others +
                '}';
    }
}
