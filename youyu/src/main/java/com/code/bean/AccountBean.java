package com.code.bean;

public class AccountBean {
    private int uid;
    private String nick;
    private int agency;
    private String birthDay;
    private int state;
    private String country;
    private String language;
    private int gender;
    private int level;
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

    public String getCountry() {
        return country;
    }

    public int getGender() {
        return gender;
    }

    public String getLanguage() {
        return language;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                "uid=" + uid +
                ", nick='" + nick + '\'' +
                ", agency=" + agency +
                ", birthDay='" + birthDay + '\'' +
                ", state=" + state +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", gender=" + gender +
                ", level=" + level +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
