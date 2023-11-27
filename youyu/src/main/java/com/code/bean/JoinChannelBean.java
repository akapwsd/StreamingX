package com.code.bean;

public class JoinChannelBean {
    private String broadcaster;

    private String uid;

    public void setBroadcaster(String broadcaster) {
        this.broadcaster = broadcaster;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "JoinChannelBean{" +
                "broadcaster='" + broadcaster + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
