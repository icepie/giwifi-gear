package com.gbcom.gwifi.functions.redbag.domain;

import java.p456io.Serializable;

public class LuckyHitRecord implements Serializable {
    private int accountId;
    private transient int balance;
    private int bestLucky;
    private String createAt;
    private String faceUrl;
    private int gender;
    private int hitBeans;
    private String nickName;
    private String tenantName;

    public LuckyHitRecord() {
        this.hitBeans = 0;
    }

    public LuckyHitRecord(int i, String str, String str2, int i2, String str3, String str4, int i3) {
        this.accountId = i;
        this.nickName = str;
        this.faceUrl = str2;
        this.gender = i2;
        this.tenantName = str3;
        this.createAt = str4;
        this.hitBeans = i3;
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

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int i) {
        this.balance = i;
    }

    public int getBestLucky() {
        return this.bestLucky;
    }

    public void setBestLucky(int i) {
        this.bestLucky = i;
    }
}
