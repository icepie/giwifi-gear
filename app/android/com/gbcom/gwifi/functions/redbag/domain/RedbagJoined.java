package com.gbcom.gwifi.functions.redbag.domain;

import java.p456io.Serializable;

public class RedbagJoined implements Serializable {
    private String createAt;
    private int deductBeans;
    private int hitBeans;
    private int luckyNumberBonus;
    private int redId;
    private String redName;
    private String roundNo;
    private int totalBeans;

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

    public String getRoundNo() {
        return this.roundNo;
    }

    public void setRoundNo(String str) {
        this.roundNo = str;
    }

    public int getTotalBeans() {
        return this.totalBeans;
    }

    public void setTotalBeans(int i) {
        this.totalBeans = i;
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

    public int getDeductBeans() {
        return this.deductBeans;
    }

    public void setDeductBeans(int i) {
        this.deductBeans = i;
    }

    public int getLuckyNumberBonus() {
        return this.luckyNumberBonus;
    }

    public void setLuckyNumberBonus(int i) {
        this.luckyNumberBonus = i;
    }
}
