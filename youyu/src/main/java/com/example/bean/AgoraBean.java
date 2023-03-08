package com.example.bean;

public class AgoraBean {
    private int state;
    private int remote_uid;
    private int selfUid;
    private int uid;
    private int agoraType = -1;//拨打的声网 是语音 还是 视频   0: 视频  1: 音频
    private String country_code;//城市码
    private long age;//
    private int gender;//性别
    private double price;
    private int level;
    private int roomType = 1;
    private int role = 2;
    private String token;
    private boolean isFree = true;
    private String frame;
    private String secret;
    private long createTime;
    private boolean isWarning = false;
    private int roomState = 1;
    private boolean isDisplay;
    private boolean isModel;

    public int getRoomState() {
        return roomState;
    }

    public void setRoomState(int roomState) {
        this.roomState = roomState;
    }

    public boolean isWarning() {
        return isWarning;
    }

    public void setWarning(boolean warning) {
        isWarning = warning;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getPlusCircleAvatar() {
        return plusCircleAvatar;
    }

    public void setPlusCircleAvatar(String plusCircleAvatar) {
        this.plusCircleAvatar = plusCircleAvatar;
    }

    private String plusCircleAvatar;

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAgoraType() {
        return agoraType;
    }

    public void setAgoraType(int agoraType) {
        this.agoraType = agoraType;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isBestNum() {
        return isBestNum;
    }

    public void setBestNum(boolean bestNum) {
        isBestNum = bestNum;
    }

    private boolean isBestNum;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    private int userType;

    public boolean isModel() {
        return isModel;
    }

    public void setModel(boolean model) {
        isModel = model;
    }

    private String name = "";
    private String avatar = "";

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    private String roomId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean display) {
        isDisplay = display;
    }

    public int getSelfUid() {
        return selfUid;
    }

    public void setSelfUid(int selfUid) {
        this.selfUid = selfUid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRemote_uid() {
        return remote_uid;
    }

    public void setRemote_uid(int remote_uid) {
        this.remote_uid = remote_uid;
    }
}
