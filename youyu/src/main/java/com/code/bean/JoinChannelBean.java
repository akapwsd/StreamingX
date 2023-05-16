package com.code.bean;

public class JoinChannelBean {
    String broadcaster;
    public void setBroadcaster(String broadcaster) {
        this.broadcaster = broadcaster;
    }

    @Override
    public String toString() {
        return "JoinChannelBean{" +
                ", broadcaster='" + broadcaster + '\'' +
                '}';
    }
}
