package com.code.bean;

public class ModelRequestBean {
    int sort;
    String offset;
    int limit;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ModelRequestBean{" +
                "sort=" + sort +
                ", offset='" + offset + '\'' +
                ", limit=" + limit +
                '}';
    }
}
