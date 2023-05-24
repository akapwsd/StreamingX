package com.code.bean;

/**
 * anchor avatar Object
 *
 * @author chan
 * @version 1.0.5
 * @since 1.0.5
 * @since 6 May 2023
 * @since JDK8
 */
public class OtherBean {
    /**
     * Unique random value for this image
     */
    private String md5;
    /**
     * image owner id
     */
    private int uid;
    /**
     * Anchor blurred avatar (small avatar)
     */
    private String avatarThumb;
    /**
     * Anchor standard avatar
     */
    private String avatarStandard;
    /**
     * Anchor clear avatar
     */
    private String avatarClear;

    public String getMd5() {
        return md5;
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
        return "OtherBean{" + "md5='" + md5 + '\'' + ", uid=" + uid + ", avatarThumb='" + avatarThumb + '\'' + ", avatarStandard='" + avatarStandard + '\'' + ", avatarClear='" + avatarClear + '\'' + '}';
    }
}
