package com.code.bean;

public class MsgBean {
    private String fp;
    private String from;
    private String channelId;

    private String msg;
    private long sendTime;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getFp() {
        return fp;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }

    @Override
    public String toString() {
        return "MsgBean{" +
                ", fp='" + fp + '\'' +
                ", channelId='" + channelId + '\'' +
                ", msg='" + msg + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
