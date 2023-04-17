package com.example.listener;

import com.example.bean.ModelBean;

public abstract class RtcRequestEventHandler {
    public RtcRequestEventHandler() {
    }

    public void onReceiveCall(ModelBean modelBean) {

    }

    public void onReceiveHangUp(byte[] evt) {

    }

    public void onPeerUserAcceptCall(byte[] evt) {

    }

    public void onPeerUserDisAgreeCall(byte[] evt) {

    }
}
