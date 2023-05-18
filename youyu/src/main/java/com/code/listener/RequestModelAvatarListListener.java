package com.code.listener;

import com.code.bean.ModelCoverListBean;

public interface RequestModelAvatarListListener {
    void onResult(ModelCoverListBean data);

    void onFailure(int code, String reason);
}
