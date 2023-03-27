package com.example.youyu.api;

public interface WebSocketResultListener {
    void onMessage();
    void onOpen();
    void onClose();
    void onError();
}
