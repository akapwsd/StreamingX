package com.example.sokect;

import android.util.Log;
import java.net.URI;

public class JWebSocketUtils {
    public static final String TAG = "JWebSocketUtils";
    private static JWebSocketUtils jWebSocketUtils;
    URI uri = URI.create("ws://*******");
    public JWebSocketClient client;

    public static JWebSocketUtils getInstance() {
        if (jWebSocketUtils == null) {
            synchronized (JWebSocketUtils.class) {
                if (jWebSocketUtils == null) {
                    jWebSocketUtils = new JWebSocketUtils();
                }
            }
        }
        return jWebSocketUtils;
    }

    private JWebSocketUtils() {
        if (client == null) {
            client = new JWebSocketClient(uri) {
                @Override
                public void onMessage(String message) {
                    Log.e(TAG, message);
                }
            };
        }
    }
}
