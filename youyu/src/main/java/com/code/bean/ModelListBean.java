package com.code.bean;

import java.util.ArrayList;
import java.util.Map;

/**
 * anchor info list Object
 *
 * @author chan
 * @version 1.0.5
 * @since 1.0.5
 * @since 6 May 2023
 * @since JDK8
 */
public class ModelListBean {
    /**
     * List of host information
     */
    private ArrayList<ModelBean> list;
    /**
     * Host status map
     * <ul>
     * <li>0 offline
     * <li>1 online idle
     * <li>2 online busy
     * <li>3 online but unavailable
     * </ul>
     */
    private Map<String, Integer> stateMap;
    /**
     * Anchor default avatar map
     */
    private Map<String, DefaultAvatarBean> profilePhoto;
    private Map<String, String> currentChannel;
    private Map<String, AvatarBean> defaultCoverMap;

    public ArrayList<ModelBean> getList() {
        return list;
    }

    public void setList(ArrayList<ModelBean> list) {
        this.list = list;
    }

    public Map<String, Integer> getStateMap() {
        return stateMap;
    }

    public void setStateMap(Map<String, Integer> stateMap) {
        this.stateMap = stateMap;
    }

    public Map<String, DefaultAvatarBean> getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Map<String, DefaultAvatarBean> profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Map<String, String> getCurrentChannel() {
        return currentChannel;
    }

    public void setCurrentChannel(Map<String, String> currentChannel) {
        this.currentChannel = currentChannel;
    }

    public Map<String, AvatarBean> getDefaultCoverMap() {
        return defaultCoverMap;
    }

    public void setDefaultCoverMap(Map<String, AvatarBean> defaultCoverMap) {
        this.defaultCoverMap = defaultCoverMap;
    }

    @Override
    public String toString() {
        return "ModelListBean{" +
                "list=" + list +
                ", stateMap=" + stateMap +
                ", profilePhoto=" + profilePhoto +
                ", currentChannel=" + currentChannel +
                ", defaultCoverMap=" + defaultCoverMap +
                '}';
    }
}
