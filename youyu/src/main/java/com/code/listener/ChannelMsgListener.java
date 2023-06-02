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
public interface ChannelMsgListener {
    void sendSuccess(String fp);

    void sendFail(int code, String error);
}
