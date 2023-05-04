package com.code.bean;

public class ChannelInfoBean {
    String id;
    int category;
    int state;
    String startTs;
    String endTs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStartTs() {
        return startTs;
    }

    public void setStartTs(String startTs) {
        this.startTs = startTs;
    }

    public String getEndTs() {
        return endTs;
    }

    public void setEndTs(String endTs) {
        this.endTs = endTs;
    }

    @Override
    public String toString() {
        return "ChannelInfoBean{" +
                "id='" + id + '\'' +
                ", category=" + category +
                ", state=" + state +
                ", startTs='" + startTs + '\'' +
                ", endTs='" + endTs + '\'' +
                '}';
    }
}
