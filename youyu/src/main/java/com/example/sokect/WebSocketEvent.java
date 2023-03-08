package com.example.sokect;

public class WebSocketEvent {
    private String message;

    public WebSocketEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
