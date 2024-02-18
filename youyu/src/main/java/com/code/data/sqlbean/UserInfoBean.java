package com.code.data.sqlbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserInfoBean {
    @Id(autoincrement = true)
    private Long id;
    private long account;
    private int userType;
    @NotNull
    private String uid;
    @NotNull
    private String peerUid;
    private String name;
    private String avatar;

    @Generated(hash = 1532783430)
    public UserInfoBean(Long id, long account, int userType, @NotNull String uid,
                        @NotNull String peerUid, String name, String avatar) {
        this.id = id;
        this.account = account;
        this.userType = userType;
        this.uid = uid;
        this.peerUid = peerUid;
        this.name = name;
        this.avatar = avatar;
    }

    @Generated(hash = 1818808915)
    public UserInfoBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getAccount() {
        return this.account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public int getUserType() {
        return this.userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPeerUid() {
        return this.peerUid;
    }

    public void setPeerUid(String peerUid) {
        this.peerUid = peerUid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "id=" + id +
                ", account=" + account +
                ", userType=" + userType +
                ", uid='" + uid + '\'' +
                ", peerUid='" + peerUid + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
