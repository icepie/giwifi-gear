package com.gbcom.gwifi.functions.wifi.entity;

import java.p456io.Serializable;

public class WiFiScanResult implements Serializable {
    public String BSSID;
    public String SSID;
    public String capabilities;
    public int channel;
    public int level;

    public WiFiScanResult() {
    }

    public WiFiScanResult(String str, String str2, int i, int i2, String str3) {
        this.SSID = str;
        this.BSSID = str2;
        this.level = i;
        this.channel = i2;
        this.capabilities = str3;
    }

    public String getSSID() {
        return this.SSID;
    }

    public void setSSID(String str) {
        this.SSID = str;
    }

    public String getBSSID() {
        return this.BSSID;
    }

    public void setBSSID(String str) {
        this.BSSID = str;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getChannel() {
        return this.channel;
    }

    public void setChannel(int i) {
        this.channel = i;
    }

    public String getCapabilities() {
        return this.capabilities;
    }

    public void setCapabilities(String str) {
        this.capabilities = str;
    }
}
