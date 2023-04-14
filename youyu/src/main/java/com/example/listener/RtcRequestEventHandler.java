package com.example.listener;

public abstract class RtcRequestEventHandler {
    public RtcRequestEventHandler() {
    }

    public void onReceiveCall(byte[] evt) {

    }

    public void onReceiveHangUp(byte[] evt) {

    }

    public void onPeerUserAcceptCall(byte[] evt) {

    }

    public void onPeerUserDisAgreeCall(byte[] evt) {

    }
}
