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
    private Map<String, DefaultAvatarBean> defaultAvatarMap;

    public ArrayList<ModelBean> getList() {
        return list;
    }

    public Map<String, Integer> getStateMap() {
        return stateMap;
    }

    public Map<String, DefaultAvatarBean> getDefaultAvatarMap() {
        return defaultAvatarMap;
    }

    @Override
    public String toString() {
        return "ModelListBean{" + "list=" + list + ", stateMap=" + stateMap + ", defaultAvatarMap=" + defaultAvatarMap + '}';
    }
}
