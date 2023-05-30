package com.code.bean;

public class JoinChannelBean {
    private String broadcaster;
    private String token;

    public void setBroadcaster(String broadcaster) {
        this.broadcaster = broadcaster;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JoinChannelBean{" +
                "broadcaster='" + broadcaster + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
