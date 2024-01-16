package com.code.listener;

/**
 * IM interface request result callback
 *
 * @author chan
 * @version 1.0.5
 * @since 1.0.5
 * @since 6 May 2023
 * @since JDK8
 */
public interface ChatMsgListener {
    void sendResult(String fp, int state);

    void sendFail(int code, String error);

    void sendProgress(int progress);
}
