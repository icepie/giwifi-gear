package com.gbcom.gwifi.functions.wifi.entity;

public class CheckResult {
    private AuthInfo authInfo;
    private String gwReqMessage = "";
    private Integer gwReqState = 2;
    private String internetReqMessage = "";
    private Integer internetReqState = 2;
    private boolean isGiwifi = false;
    private boolean isRelease = false;
    private String loginReqMessage = "";
    private Integer loginReqState = 2;
    private Integer onlineState;

    public Integer getGwReqState() {
        return this.gwReqState;
    }

    public void setGwReqState(Integer num) {
        this.gwReqState = num;
    }

    public Integer getInternetReqState() {
        return this.internetReqState;
    }

    public void setInternetReqState(Integer num) {
        this.internetReqState = num;
    }

    public Integer getLoginReqState() {
        return this.loginReqState;
    }

    public void setLoginReqState(Integer num) {
        this.loginReqState = num;
    }

    public String getGwReqMessage() {
        return this.gwReqMessage;
    }

    public void setGwReqMessage(String str) {
        this.gwReqMessage = str;
    }

    public String getInternetReqMessage() {
        return this.internetReqMessage;
    }

    public void setInternetReqMessage(String str) {
        this.internetReqMessage = str;
    }

    public String getLoginReqMessage() {
        return this.loginReqMessage;
    }

    public void setLoginReqMessage(String str) {
        this.loginReqMessage = str;
    }

    public AuthInfo getAuthInfo() {
        return this.authInfo;
    }

    public void setAuthInfo(AuthInfo authInfo2) {
        this.authInfo = authInfo2;
    }

    public Integer getOnlineState() {
        return this.onlineState;
    }

    public void setOnlineState(Integer num) {
        this.onlineState = num;
    }

    public boolean isGiwifi() {
        return this.isGiwifi;
    }

    public void setGiwifi(boolean z) {
        this.isGiwifi = z;
    }

    public void setRelease(boolean z) {
        this.isRelease = z;
    }

    public boolean getRelease() {
        return this.isRelease;
    }
}
