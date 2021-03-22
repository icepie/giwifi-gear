package com.gbcom.gwifi.functions.redbag.domain;

public class AccountPrizePoolBonus {
    private int accountId;
    private String createAt;
    private String faceUrl;
    private int gender;
    private int hitBeans;
    private int luckyNumberBonus;
    private String nickName;
    private String tenantName;

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }

    public String getFaceUrl() {
        return this.faceUrl;
    }

    public void setFaceUrl(String str) {
        this.faceUrl = str;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
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

    public int getLuckyNumberBonus() {
        return this.luckyNumberBonus;
    }

    public void setLuckyNumberBonus(int i) {
        this.luckyNumberBonus = i;
    }

    public String getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(String str) {
        this.createAt = str;
    }
}
