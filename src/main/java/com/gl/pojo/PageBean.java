package com.gl.pojo;

import java.util.List;

public class PageBean<T> {
    private int totalCount;
    private List<T> datas;

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", datas=" + datas +
                '}';
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public PageBean() {
    }

    public PageBean(int totalCount, List<T> datas) {
        this.totalCount = totalCount;
        this.datas = datas;
    }
}
