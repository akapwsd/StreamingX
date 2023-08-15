package com.code.bean;

public class MatchBean {
    private MatchExpectBean expect;
    private MatchAttrBean attr;
    private String ts;
    private int matchType;

    public MatchExpectBean getExpect() {
        return expect;
    }

    public void setExpect(MatchExpectBean expect) {
        this.expect = expect;
    }

    public MatchAttrBean getAttr() {
        return attr;
    }

    public void setAttr(MatchAttrBean attr) {
        this.attr = attr;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public int getMatchType() {
        return matchType;
    }

    public void setMatchType(int matchType) {
        this.matchType = matchType;
    }

    @Override
    public String toString() {
        return "MatchBean{" +
                "expect=" + expect +
                ", attr=" + attr +
                ", ts='" + ts + '\'' +
                ", matchType=" + matchType +
                '}';
    }
}
