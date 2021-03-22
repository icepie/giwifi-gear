package com.gbcom.gwifi.functions.redbag.domain;

import java.p456io.Serializable;

public class LuckyRedbagJoined implements Serializable {
    private String createAt;
    private int hitBeans;
    private int luckyRedbagId;
    private String luckyRemark;

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

    public String getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(String str) {
        this.createAt = str;
    }

    public int getHitBeans() {
        return this.hitBeans;
    }

    public void setHitBeans(int i) {
        this.hitBeans = i;
    }
}
