package com.example.listener;

import io.agora.rtc.IRtcEngineEventHandler;

public interface FacePositionListener {
    void haveFace(IRtcEngineEventHandler.AgoraFacePositionInfo agoraFacePositionInfo);
    void noFace();
}
