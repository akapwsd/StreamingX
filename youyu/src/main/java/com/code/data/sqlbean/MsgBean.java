package com.code.data.sqlbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MsgBean {
    @Id
    private String fp;//Locally logged message unique identification number
    private long pts;
    @NotNull
    private int mUid; //your uid
    @NotNull
    private int peerUid; //peer uid
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
    @Generated(hash = 270969052)
    public MsgBean(String fp, long pts, int mUid, int peerUid, int sourceType, String content, String localPath,
            int status, int state, double progress, long actualTime, String nickName, String avatar) {
        this.fp = fp;
        this.pts = pts;
        this.mUid = mUid;
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
    }
    @Generated(hash = 237905234)
    public MsgBean() {
    }
    public String getFp() {
        return this.fp;
    }
    public void setFp(String fp) {
        this.fp = fp;
    }
    public int getMUid() {
        return this.mUid;
    }
    public void setMUid(int mUid) {
        this.mUid = mUid;
    }
    public int getPeerUid() {
        return this.peerUid;
    }
    public void setPeerUid(int peerUid) {
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
    public long getPts() {
        return this.pts;
    }
    public void setPts(long pts) {
        this.pts = pts;
    }
}
