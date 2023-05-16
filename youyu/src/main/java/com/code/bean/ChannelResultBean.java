package com.code.bean;

/**
 * Interface request receipt data object
 *
 * @author chan
 * @version 1.0.5
 * @since 1.0.5
 * @since 6 May 2023
 * @since JDK8
 */
public class ChannelResultBean {
    private ChannelInfoBean ch;
    private String token;
    private String uniqId;

    /**
     * Get the room details object
     *
     * @return Room Details Data Object{@link ChannelInfoBean}
     */
    public ChannelInfoBean getCh() {
        return ch;
    }

    /**
     * Get room token
     *
     * @return room token
     */
    public String getToken() {
        return token;
    }

    public String getUniqId() {
        return uniqId;
    }

    @Override
    public String toString() {
        return "CreateChannelResultBean{" + "ch=" + ch + ", token='" + token + '\'' + '}';
    }
}
