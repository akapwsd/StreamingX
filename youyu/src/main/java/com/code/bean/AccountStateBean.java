package com.code.bean;

public class AccountStateBean {
    private int state;
    private String channelId;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "AccountStateBean{" +
                "state=" + state +
                ", channelId='" + channelId + '\'' +
                '}';
    }
}
