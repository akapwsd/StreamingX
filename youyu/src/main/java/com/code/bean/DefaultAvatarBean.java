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
    private String thumb;
    /**
     * Anchor standard avatar
     */
    private String standard;
    /**
     * Anchor clear avatar
     */
    private String clear;
    private String createdAt;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getClear() {
        return clear;
    }

    public void setClear(String clear) {
        this.clear = clear;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
