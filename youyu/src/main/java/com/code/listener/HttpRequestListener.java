package com.code.listener;

public interface HttpRequestListener {
    void requestSuccess(Object o);

    void requestError(int code, String error);
}
