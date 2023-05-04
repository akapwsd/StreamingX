package com.code.bean;

public class ChannelResultBean {
    ChannelInfoBean ch;
    String token;

    public ChannelInfoBean getCh() {
        return ch;
    }

    public void setCh(ChannelInfoBean ch) {
        this.ch = ch;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CreateChannelResultBean{" +
                "ch=" + ch +
                ", token='" + token + '\'' +
                '}';
    }
}
