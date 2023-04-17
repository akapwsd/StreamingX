package com.example.listener;

public interface InitResultListener {
    void onSuccess();
    void onFail(int code,String msg);
}
