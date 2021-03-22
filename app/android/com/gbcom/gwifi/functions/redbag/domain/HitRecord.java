package com.gbcom.gwifi.functions.redbag.domain;

import java.p456io.Serializable;

public class HitRecord implements Serializable {
    private int accountId;
    private String createAt;
    private int deductBeans;
    private String faceUrl;
    private int gender;
    private int hitBeans;
    private int luckyNumberBonus;
    private String nickName;
    private String tenantName;

    public String getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(String str) {
        this.createAt = str;
    }

    public HitRecord() {
    }

    public HitRecord(int i, String str, String str2, int i2, String str3, int i3, int i4, int i5) {
        this.accountId = i;
        this.nickName = str;
        this.faceUrl = str2;
        this.gender = i2;
        this.tenantName = str3;
        this.hitBeans = i3;
        this.deductBeans = i4;
        this.luckyNumberBonus = i5;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getFaceUrl() {
        return this.faceUrl;
    }

    public void setFaceUrl(String str) {
        this.faceUrl = str;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int i) {
        this.gender = i;
    }

    public String getTenantName() {
        return this.tenantName;
    }

    public void setTenantName(String str) {
        this.tenantName = str;
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
