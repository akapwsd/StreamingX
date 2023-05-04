package com.code.bean;

public class ChannelTokenBean {
    String channelId;
    String token;
    String expiration;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "ChannelTokenBean{" +
                "channelId='" + channelId + '\'' +
                ", token='" + token + '\'' +
                ", expiration='" + expiration + '\'' +
                '}';
    }
}
