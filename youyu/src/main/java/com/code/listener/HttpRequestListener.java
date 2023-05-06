package com.code.listener;

/**
 * RTC interface request result callback
 *
 * @author chan
 * @version 1.0.5
 * @since 1.0.5
 * @since 6 May 2023
 * @since JDK11
 */
public interface HttpRequestListener {
    /**
     * The interface request is successful
     *
     * @param o The parameters returned by the interface request successfully
     *          There are two data types:
     *          <ul>
     *          <li>{@link com.code.bean.ChannelResultBean}
     *          </ul>
     */
    void requestSuccess(Object o);

    /**
     * Interface request failed
     *
     * @param code  Interface request failure error code
     * @param error Interface request failed error reason
     */
    void requestError(int code, String error);
}
