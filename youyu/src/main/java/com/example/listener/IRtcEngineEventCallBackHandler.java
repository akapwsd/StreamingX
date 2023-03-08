package com.example.listener;

public interface IRtcEngineEventCallBackHandler {
    void onJoinChannelSuccess(String channel, int uid, int elapsed);

    void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed);

    void onUserOffline(int uid, int reason);

    void onUserJoined(int uid, int elapsed);
}
