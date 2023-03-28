package com.example.youyu.api;

public interface WebSocketResultListener {
    void onMessage(Object data);
    void onOpen();
    void onClose();
    void onError();
}
