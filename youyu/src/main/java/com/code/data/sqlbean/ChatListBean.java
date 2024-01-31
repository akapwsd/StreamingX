package com.code.data.sqlbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ChatListBean {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String mUid;

    @NotNull
    private String peerUid;

    private int unreadMsgCount;

    private long oldMessageTime;

    private String oldMessage;

    private int status;

    private int sourceType;
    
    private String nickName;

    private int sendState;

    private String userAvatar;

    @Generated(hash = 359873815)
    public ChatListBean(Long id, @NotNull String mUid, @NotNull String peerUid,
            int unreadMsgCount, long oldMessageTime, String oldMessage, int status,
            int sourceType, String nickName, int sendState, String userAvatar) {
        this.id = id;
        this.mUid = mUid;
        this.peerUid = peerUid;
        this.unreadMsgCount = unreadMsgCount;
        this.oldMessageTime = oldMessageTime;
        this.oldMessage = oldMessage;
        this.status = status;
        this.sourceType = sourceType;
        this.nickName = nickName;
        this.sendState = sendState;
        this.userAvatar = userAvatar;
    }

    @Generated(hash = 1121489494)
    public ChatListBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMUid() {
        return this.mUid;
    }

    public void setMUid(String mUid) {
        this.mUid = mUid;
    }

    public String getPeerUid() {
        return this.peerUid;
    }

    public void setPeerUid(String peerUid) {
        this.peerUid = peerUid;
    }

    public int getUnreadMsgCount() {
        return this.unreadMsgCount;
    }

    public void setUnreadMsgCount(int unreadMsgCount) {
        this.unreadMsgCount = unreadMsgCount;
    }

    public long getOldMessageTime() {
        return this.oldMessageTime;
    }

    public void setOldMessageTime(long oldMessageTime) {
        this.oldMessageTime = oldMessageTime;
    }

    public String getOldMessage() {
        return this.oldMessage;
    }

    public void setOldMessage(String oldMessage) {
        this.oldMessage = oldMessage;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSendState() {
        return this.sendState;
    }

    public void setSendState(int sendState) {
        this.sendState = sendState;
    }

    public String getUserAvatar() {
        return this.userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    @Override
    public String toString() {
        return "ChatListBean{" +
                "id=" + id +
                ", mUid='" + mUid + '\'' +
                ", peerUid='" + peerUid + '\'' +
                ", unreadMsgCount=" + unreadMsgCount +
                ", oldMessageTime=" + oldMessageTime +
                ", oldMessage='" + oldMessage + '\'' +
                ", status=" + status +
                ", sourceType=" + sourceType +
                ", nickName='" + nickName + '\'' +
                ", sendState=" + sendState +
                ", userAvatar='" + userAvatar + '\'' +
                '}';
    }
}
