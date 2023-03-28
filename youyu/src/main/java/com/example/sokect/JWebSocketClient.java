package com.example.sokect;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Map;

public class JWebSocketClient extends WebSocketClient {

    public JWebSocketClient(URI serverUri) {
        super(serverUri, new Draft_6455());
    }

    public JWebSocketClient(URI serverUri, Draft draft){
        super(serverUri,draft);
    }

    public JWebSocketClient(URI serverUri, Map<String,String> httpHeaders){
        super(serverUri,httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handShakeData) {

    }

    @Override
    public void onMessage(String message) {

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        super.onMessage(bytes);
    }
}
