package com.code.data.sqlbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MsgBean {
    @Id(autoincrement = true)
    private Long id;
    private String fp;//Locally logged message unique identification number
    private boolean isBroadcaster = false;
    private long msgId;
    private long pts;
    private long account;
    private int userType;
    @NotNull
    private String uid; //your uid
    @NotNull
    private String peerUid; //peer uid
    @NotNull
    private int sourceType;  //source  Constants.MSG_RECEIVER is receiver  Constants.MSG_SENDER is sender
    private String content;
    private String localPath;
    @NotNull
    private int status;
    @NotNull
    private int state = 1;
    private double progress;
    private long actualTime;
    private String nickName;
    private String avatar;
    private int wight;
    private int height;
    private int size;
    private int time;
    private String ext;
    private String hash;
    @Generated(hash = 1865244351)
    public MsgBean(Long id, String fp, boolean isBroadcaster, long msgId, long pts, long account, int userType,
            @NotNull String uid, @NotNull String peerUid, int sourceType, String content, String localPath,
            int status, int state, double progress, long actualTime, String nickName, String avatar, int wight,
            int height, int size, int time, String ext, String hash) {
        this.id = id;
        this.fp = fp;
        this.isBroadcaster = isBroadcaster;
        this.msgId = msgId;
        this.pts = pts;
        this.account = account;
        this.userType = userType;
        this.uid = uid;
        this.peerUid = peerUid;
        this.sourceType = sourceType;
        this.content = content;
        this.localPath = localPath;
        this.status = status;
        this.state = state;
        this.progress = progress;
        this.actualTime = actualTime;
        this.nickName = nickName;
        this.avatar = avatar;
        this.wight = wight;
        this.height = height;
        this.size = size;
        this.time = time;
        this.ext = ext;
        this.hash = hash;
    }
    @Generated(hash = 237905234)
    public MsgBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFp() {
        return this.fp;
    }
    public void setFp(String fp) {
        this.fp = fp;
    }
    public boolean getIsBroadcaster() {
        return this.isBroadcaster;
    }
    public void setIsBroadcaster(boolean isBroadcaster) {
        this.isBroadcaster = isBroadcaster;
    }
    public long getMsgId() {
        return this.msgId;
    }
    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }
    public long getPts() {
        return this.pts;
    }
    public void setPts(long pts) {
        this.pts = pts;
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
    public int getSourceType() {
        return this.sourceType;
    }
    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getLocalPath() {
        return this.localPath;
    }
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getState() {
        return this.state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public double getProgress() {
        return this.progress;
    }
    public void setProgress(double progress) {
        this.progress = progress;
    }
    public long getActualTime() {
        return this.actualTime;
    }
    public void setActualTime(long actualTime) {
        this.actualTime = actualTime;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public int getWight() {
        return this.wight;
    }
    public void setWight(int wight) {
        this.wight = wight;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getSize() {
        return this.size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getTime() {
        return this.time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public String getExt() {
        return this.ext;
    }
    public void setExt(String ext) {
        this.ext = ext;
    }
    public String getHash() {
        return this.hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "MsgBean{" +
                "id=" + id +
                ", fp='" + fp + '\'' +
                ", isBroadcaster=" + isBroadcaster +
                ", msgId=" + msgId +
                ", pts=" + pts +
                ", account=" + account +
                ", userType=" + userType +
                ", uid='" + uid + '\'' +
                ", peerUid='" + peerUid + '\'' +
                ", sourceType=" + sourceType +
                ", content='" + content + '\'' +
                ", localPath='" + localPath + '\'' +
                ", status=" + status +
                ", state=" + state +
                ", progress=" + progress +
                ", actualTime=" + actualTime +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", wight=" + wight +
                ", height=" + height +
                ", size=" + size +
                ", time=" + time +
                ", ext='" + ext + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
