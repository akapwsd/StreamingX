package com.code.bean;

public class SmsBean {
    private String receipt;
    private int frq;

    public String getReceipt() {
        return receipt;
    }

    public int getFrq() {
        return frq;
    }

    @Override
    public String toString() {
        return "SmsBean{" +
                "receipt='" + receipt + '\'' +
                ", frq=" + frq +
                '}';
    }
}
