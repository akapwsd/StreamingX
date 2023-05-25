package com.code.bean;

public class ApplyStatusBean {
    private int id;
    private int uid;
    private int applyType;
    private int applyState;
    private String applyVal;
    private String createTime;
    private String updateTime;

    public int getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public int getApplyType() {
        return applyType;
    }

    public int getApplyState() {
        return applyState;
    }

    public String getApplyVal() {
        return applyVal;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "ApplyStatusBean{" +
                "id=" + id +
                ", uid=" + uid +
                ", applyType=" + applyType +
                ", applyState=" + applyState +
                ", applyVal='" + applyVal + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
