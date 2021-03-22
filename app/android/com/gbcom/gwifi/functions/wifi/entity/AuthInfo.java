package com.gbcom.gwifi.functions.wifi.entity;

public class AuthInfo {
    public static final String DEFAULT_SN = "000000000000";
    private Integer authState = 0;
    private String clientMac = "";
    private String contactPhone = "";
    private String gwId = "";
    private String gwIp = "";
    private String localIp = "";
    private Integer logoutReason = 0;
    private Integer onlineTime = 0;
    private String stationSn = "";
    private String suggestPhone = "";

    public Integer getAuthState() {
        return this.authState;
    }

    public void setAuthState(Integer num) {
        this.authState = num;
    }

    public String getGwId() {
        return this.gwId;
    }

    public void setGwId(String str) {
        this.gwId = str;
    }

    public String getStationSn() {
        return this.stationSn;
    }

    public void setStationSn(String str) {
        this.stationSn = str;
    }

    public String getClientMac() {
        return this.clientMac;
    }

    public void setClientMac(String str) {
        this.clientMac = str;
    }

    public Integer getOnlineTime() {
        return this.onlineTime;
    }

    public void setOnlineTime(Integer num) {
        this.onlineTime = num;
    }

    public Integer getLogoutReason() {
        return this.logoutReason;
    }

    public void setLogoutReason(Integer num) {
        this.logoutReason = num;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public void setContactPhone(String str) {
        this.contactPhone = str;
    }

    public String getSuggestPhone() {
        return this.suggestPhone;
    }

    public void setSuggestPhone(String str) {
        this.suggestPhone = str;
    }

    public String getGwIp() {
        return this.gwIp;
    }

    public void setGwIp(String str) {
        this.gwIp = str;
    }

    public String getLocalIp() {
        return this.localIp;
    }

    public void setLocalIp(String str) {
        this.localIp = str;
    }
}
