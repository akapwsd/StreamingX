package com.code.bean;

public class UploadUserInfoBean {
    private int uid;
    private String nick;
    private String birthday;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UploadUserInfoBean{" +
                "uid=" + uid +
                ", nick='" + nick + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
