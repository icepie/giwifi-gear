package com.gbcom.gwifi.functions.redbag.domain;

public class AccountInfo {
    private int accountId;
    private int balance;
    private String balanceChargeUrl;
    private transient int count;
    private String faceUrl;
    private int gender;
    private String nickName;
    private String tenantName;
    private String userName;

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

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int i) {
        this.balance = i;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public String getBalanceChargeUrl() {
        return this.balanceChargeUrl;
    }

    public void setBalanceChargeUrl(String str) {
        this.balanceChargeUrl = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("id=" + this.accountId);
        stringBuffer.append(",userName=" + this.userName);
        stringBuffer.append(",faceUrl=" + this.faceUrl);
        stringBuffer.append(",nickName=" + this.nickName);
        stringBuffer.append(",gender=" + this.gender);
        stringBuffer.append(",tenantName=" + this.tenantName);
        stringBuffer.append(",balance=" + this.balance);
        return stringBuffer.toString();
    }
}
