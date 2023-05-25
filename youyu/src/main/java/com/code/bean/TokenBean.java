package com.code.bean;

public class TokenBean {
    private int id;
    private String token;

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "TokenBean{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }
}
