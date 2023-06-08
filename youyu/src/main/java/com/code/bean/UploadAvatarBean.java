package com.code.bean;

public class UploadAvatarBean {
    private String md5;

    public String avatarOriginalUrl;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getAvatarOriginalUrl() {
        return avatarOriginalUrl;
    }

    public void setAvatarOriginalUrl(String avatarOriginalUrl) {
        this.avatarOriginalUrl = avatarOriginalUrl;
    }

    @Override
    public String toString() {
        return "UploadAvatarBean{" +
                "md5='" + md5 + '\'' +
                ", avatarOriginalUrl='" + avatarOriginalUrl + '\'' +
                '}';
    }
}
