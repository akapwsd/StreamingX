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
public class DefaultAvatarBean {
    /**
     * Unique random value for this image
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAvatarThumb() {
        return avatarThumb;
    }

    public void setAvatarThumb(String avatarThumb) {
        this.avatarThumb = avatarThumb;
    }

    public String getAvatarStandard() {
        return avatarStandard;
    }

    public void setAvatarStandard(String avatarStandard) {
        this.avatarStandard = avatarStandard;
    }

    public String getAvatarClear() {
        return avatarClear;
    }

    public void setAvatarClear(String avatarClear) {
        this.avatarClear = avatarClear;
    }

    @Override
    public String toString() {
        return "DefaultAvatarBean{" +
                "uid=" + uid +
                ", avatarThumb='" + avatarThumb + '\'' +
                ", avatarStandard='" + avatarStandard + '\'' +
                ", avatarClear='" + avatarClear + '\'' +
                '}';
    }
}
