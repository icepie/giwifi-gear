package com.gbcom.gwifi.functions.wifi.entity;

public class WepWifi extends BaseWifi {
    private String password;

    public String getPassword() {
        return this.password;
    }

    @Override // com.gbcom.gwifi.functions.wifi.entity.BaseWifi
    public BaseWifi newInstance() {
        return new WepWifi();
    }

    @Override // com.gbcom.gwifi.functions.wifi.entity.BaseWifi
    public BaseWifi[] newInstance(int i) {
        return new WepWifi[i];
    }

    public void setPassword(String str) {
        this.password = str;
    }

    @Override // com.gbcom.gwifi.functions.wifi.entity.BaseWifi
    public String toString() {
        return "WepWifi [password=" + this.password + ", bssid=" + this.bssid + ", ssid=" + this.ssid + ", updateTime=" + this.updateTime + ", capabilities=" + this.capabilities + ", level=" + this.level + ", connectTime=" + this.connectTime + ", shareWifi=" + this.shareWifi + "]";
    }
}
