package com.code.listener;

import com.code.bean.ModelBean;

import java.util.ArrayList;

public interface RequestModelListListener {
    void onResult(ArrayList<ModelBean> dataList);

    void onFailure(int code, String reason);
}
