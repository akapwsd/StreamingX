package com.code.listener;

import io.agora.rtc.IRtcEngineEventHandler;

public abstract class IRtcEngineEventCallBackHandler {
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {

    }

    public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {

    }

    public void onUserOffline(int uid, int reason) {

    }

    public void onUserJoined(int uid, int elapsed) {
    }

    public void onFacePositionChanged(int imageWidth, int imageHeight, IRtcEngineEventHandler.AgoraFacePositionInfo[] agoraFacePositionInfos) {

    }

    public void banRoom(String uid,int reason) {

    }

    public void closeRoom(int reason) {

    }
}
