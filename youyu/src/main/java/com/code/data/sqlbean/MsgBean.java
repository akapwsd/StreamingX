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
    @Generated(hash = 332465387)
    public MsgBean(Long id, String fp, boolean isBroadcaster, long msgId, long pts, @NotNull String uid,
            @NotNull String peerUid, int sourceType, String content, String localPath, int status, int state,
            double progress, long actualTime, String nickName, String avatar) {
        this.id = id;
        this.fp = fp;
        this.isBroadcaster = isBroadcaster;
        this.msgId = msgId;
        this.pts = pts;
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

    @Override
    public String toString() {
        return "MsgBean{" +
                "id=" + id +
                ", fp='" + fp + '\'' +
                ", isBroadcaster=" + isBroadcaster +
                ", msgId=" + msgId +
                ", pts=" + pts +
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
                '}';
    }
}
