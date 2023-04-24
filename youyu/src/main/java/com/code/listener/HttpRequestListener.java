package com.code.listener;

public interface HttpRequestListener {
    void requestSuccess(String o, String msg);

    void requestError(int code, String error);
}
