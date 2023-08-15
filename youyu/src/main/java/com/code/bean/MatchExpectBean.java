package com.code.bean;

public class MatchExpectBean {
    private String country;
    private String language;
    private int gender;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "MatchExpectBean{" +
                "country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", gender=" + gender +
                '}';
    }
}
