package com.gbcom.gwifi.functions.wifi.entity;

public class NoneWifi extends BaseWifi {
    @Override // com.gbcom.gwifi.functions.wifi.entity.BaseWifi
    public BaseWifi newInstance() {
        return new NoneWifi();
    }

    @Override // com.gbcom.gwifi.functions.wifi.entity.BaseWifi
    public BaseWifi[] newInstance(int i) {
        return new NoneWifi[i];
    }
}
