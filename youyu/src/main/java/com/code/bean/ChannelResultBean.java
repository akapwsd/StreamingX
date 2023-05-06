package com.code.bean;

/**
 * Interface request receipt data object
 *
 * @author chan
 * @version 1.0.5
 * @since 1.0.5
 * @since 6 May 2023
 * @since JDK11
 */
public class ChannelResultBean {
    private ChannelInfoBean ch;
    private String token;

    /**
     * Get the room details object
     *
     * @return Room Details Data Object{@link ChannelInfoBean}
     */
    public ChannelInfoBean getCh() {
        return ch;
    }

    public void setCh(ChannelInfoBean ch) {
        this.ch = ch;
    }

    /**
     * Get room token
     *
     * @return room token
     */
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CreateChannelResultBean{" + "ch=" + ch + ", token='" + token + '\'' + '}';
    }
}
