package com.code.listener;

import com.code.bean.ModelListBean;

public interface RequestModelListListener {
    void onResult(ModelListBean data);

    void onFailure(int code, String reason);
}
