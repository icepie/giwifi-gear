package com.gbcom.gwifi.functions.redbag.domain;

public class LuckyRedbagSimple {
    private int isJoin;
    private int luckyRedbagId;
    private transient String luckyRemark;
    private int order;
    private int status;

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int i) {
        this.order = i;
    }

    public LuckyRedbagSimple() {
        this.status = 0;
    }

    public LuckyRedbagSimple(int i, String str) {
        this.luckyRedbagId = i;
        this.luckyRemark = str;
    }

    public int getLuckyRedbagId() {
        return this.luckyRedbagId;
    }

    public void setLuckyRedbagId(int i) {
        this.luckyRedbagId = i;
    }

    public String getLuckyRemark() {
        return this.luckyRemark;
    }

    public void setLuckyRemark(String str) {
        this.luckyRemark = str;
    }

    public int getIsJoin() {
        return this.isJoin;
    }

    public void setIsJoin(int i) {
        this.isJoin = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }
}
