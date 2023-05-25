package com.code.bean;

import java.util.ArrayList;

public class ApplyResultBean {
    private ArrayList<ApplyStatusBean> list;

    public ArrayList<ApplyStatusBean> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "ApplyResultBean{" +
                "list=" + list +
                '}';
    }
}
