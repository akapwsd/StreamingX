package com.code.bean;

/**
 * anchor info Object
 *
 * @author chan
 * @version 1.0.5
 * @since 1.0.5
 * @since 6 May 2023
 * @since JDK8
 */
public class ModelBean {
    /**
     * anchor id
     */
    private int uid;
    /**
     * anchor name
     */
    private String name;
    /**
     * anchor birthday
     */
    private String birthday;
    private String country;
    private int gender;

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCountry() {
        return country;
    }

    public int getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "ModelBean{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", country='" + country + '\'' +
                ", gender=" + gender +
                '}';
    }
}
