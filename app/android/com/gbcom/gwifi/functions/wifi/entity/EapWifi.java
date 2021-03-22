package com.gbcom.gwifi.functions.wifi.entity;

public class EapWifi extends BaseWifi {
    private String anonymousIdentity;
    private int eapMethod;
    private String identity;
    private String password;
    private int phase2Method;

    public String getAnonymousIdentity() {
        return this.anonymousIdentity;
    }

    public int getEapMethod() {
        return this.eapMethod;
    }

    public String getIdentity() {
        return this.identity;
    }

    public String getPassword() {
        return this.password;
    }

    public int getPhase2Method() {
        return this.phase2Method;
    }

    @Override // com.gbcom.gwifi.functions.wifi.entity.BaseWifi
    public BaseWifi newInstance() {
        return new EapWifi();
    }

    @Override // com.gbcom.gwifi.functions.wifi.entity.BaseWifi
    public BaseWifi[] newInstance(int i) {
        return new EapWifi[i];
    }

    public void setAnonymousIdentity(String str) {
        this.anonymousIdentity = str;
    }

    public void setEapMethod(int i) {
        this.eapMethod = i;
    }

    public void setIdentity(String str) {
        this.identity = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setPhase2Method(int i) {
        this.phase2Method = i;
    }

    @Override // com.gbcom.gwifi.functions.wifi.entity.BaseWifi
    public String toString() {
        return "EapWifi [identity=" + this.identity + ", anonymousIdentity=" + this.anonymousIdentity + ", password=" + this.password + ", eapMethod=" + this.eapMethod + ", phase2Method=" + this.phase2Method + ", bssid=" + this.bssid + ", ssid=" + this.ssid + ", updateTime=" + this.updateTime + ", capabilities=" + this.capabilities + ", level=" + this.level + ", connectTime=" + this.connectTime + ", shareWifi=" + this.shareWifi + "]";
    }
}
