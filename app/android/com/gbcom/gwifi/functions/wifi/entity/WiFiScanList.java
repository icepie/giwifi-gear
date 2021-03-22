package com.gbcom.gwifi.functions.wifi.entity;

import java.util.ArrayList;

public class WiFiScanList {
    private long timestamp;
    private ArrayList<WiFiScanResult> wifi2gList;
    private ArrayList<WiFiScanResult> wifi5gList;

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public ArrayList<WiFiScanResult> getWifi2gList() {
        return this.wifi2gList;
    }

    public void setWifi2gList(ArrayList<WiFiScanResult> arrayList) {
        this.wifi2gList = arrayList;
    }

    public ArrayList<WiFiScanResult> getWifi5gList() {
        return this.wifi5gList;
    }

    public void setWifi5gList(ArrayList<WiFiScanResult> arrayList) {
        this.wifi5gList = arrayList;
    }
}
