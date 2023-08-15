package com.code.bean;

public class MatchAttrBean {
    private String name;
    private String photoUrl;
    private int gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "MatchAttrBean{" +
                "name='" + name + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", gender=" + gender +
                '}';
    }
}
