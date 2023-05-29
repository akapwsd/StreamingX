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
public class AvatarBean {
    /**
     * Unique random value for this image
     */
    private String md5;
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

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setAvatarThumb(String avatarThumb) {
        this.avatarThumb = avatarThumb;
    }

    public void setAvatarStandard(String avatarStandard) {
        this.avatarStandard = avatarStandard;
    }

    public void setAvatarClear(String avatarClear) {
        this.avatarClear = avatarClear;
    }

    @Override
    public String toString() {
        return "AvatarBean{" +
                "md5='" + md5 + '\'' +
                ", avatarThumb='" + avatarThumb + '\'' +
                ", avatarStandard='" + avatarStandard + '\'' +
                ", avatarClear='" + avatarClear + '\'' +
                '}';
    }
}
