package com.gbcom.gwifi.functions.redbag.domain;

public class Redbag {
    private int deductBeans;
    private int redId;
    private String redName;
    private int totalBeans;
    private int totalCount;

    public Redbag() {
    }

    public Redbag(int i, String str, int i2, int i3) {
        this.redId = i;
        this.redName = str;
        this.totalBeans = i2;
        this.deductBeans = i3;
    }

    public int getRedId() {
        return this.redId;
    }

    public void setRedId(int i) {
        this.redId = i;
    }

    public String getRedName() {
        return this.redName;
    }

    public void setRedName(String str) {
        this.redName = str;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int i) {
        this.totalCount = i;
    }

    public int getTotalBeans() {
        return this.totalBeans;
    }

    public void setTotalBeans(int i) {
        this.totalBeans = i;
    }

    public int getDeductBeans() {
        return this.deductBeans;
    }

    public void setDeductBeans(int i) {
        this.deductBeans = i;
    }
}
