package com.code.bean;

import java.util.ArrayList;

public class AvatarListBean {
    private ArrayList<AvatarBean> list;

    public ArrayList<AvatarBean> getList() {
        return list;
    }

    public void setList(ArrayList<AvatarBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "AvatarListBean{" +
                "list=" + list +
                '}';
    }
}
