package com.code.bean;

/**
 * Room Details Object
 *
 * @author chan
 * @version 1.0.5
 * @since 1.0.5
 * @since 6 May 2023
 * @since JDK8
 */
public class ChannelInfoBean {
    private String id;
    private int category;
    private int state;
    private String startTs;
    private String endTs;

    /**
     * Get the channel number of the call room
     *
     * @return Call room channel number
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * call room type
     *
     * @return room type
     * <ul>
     *     <li> 1 is video
     *     <li> 0 is audio
     * </ul>
     */
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStartTs() {
        return startTs;
    }

    public void setStartTs(String startTs) {
        this.startTs = startTs;
    }

    public String getEndTs() {
        return endTs;
    }

    public void setEndTs(String endTs) {
        this.endTs = endTs;
    }

    @Override
    public String toString() {
        return "ChannelInfoBean{" + "id='" + id + '\'' + ", category=" + category + ", state=" + state + ", startTs='" + startTs + '\'' + ", endTs='" + endTs + '\'' + '}';
    }
}
