package com.code.bean;

public class AccountBean {
    private int uid;
    private String nick;
    private int agency;
    private String birthDay;
    private int state;
    private String createTime;
    private String updateTime;

    public int getUid() {
        return uid;
    }

    public String getNick() {
        return nick;
    }

    public int getAgency() {
        return agency;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public int getState() {
        return state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                "uid=" + uid +
                ", nick='" + nick + '\'' +
                ", agency=" + agency +
                ", birthDay='" + birthDay + '\'' +
                ", state=" + state +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
