package com.code.bean;

public class JoinChannelBean {
    String uid;
    String broadcaster;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(String broadcaster) {
        this.broadcaster = broadcaster;
    }

    @Override
    public String toString() {
        return "JoinChannelBean{" +
                "uid='" + uid + '\'' +
                ", broadcaster='" + broadcaster + '\'' +
                '}';
    }
}
