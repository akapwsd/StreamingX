package com.code.bean;

import java.util.ArrayList;
import java.util.Map;

public class ModelListBean {
    private ArrayList<ModelBean> list;
    private Map<String, Integer> stateMap;
    private Map<String, ModelInfoBean> defaultAvatarMap;

    public ArrayList<ModelBean> getList() {
        return list;
    }

    public Map<String, Integer> getStateMap() {
        return stateMap;
    }

    public Map<String, ModelInfoBean> getDefaultAvatarMap() {
        return defaultAvatarMap;
    }

    @Override
    public String toString() {
        return "ModelListBean{" +
                "list=" + list +
                ", stateMap=" + stateMap +
                ", defaultAvatarMap=" + defaultAvatarMap +
                '}';
    }
}
