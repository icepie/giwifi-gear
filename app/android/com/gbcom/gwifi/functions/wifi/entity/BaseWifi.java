package com.gbcom.gwifi.functions.wifi.entity;

import com.gbcom.gwifi.functions.wifi.p253a.AuthState;
import com.gbcom.gwifi.functions.wifi.p253a.ConnectState;
import com.gbcom.gwifi.functions.wifi.p253a.DetailedState;
import java.p456io.Serializable;
import java.util.EnumMap;

public abstract class BaseWifi implements Serializable, Comparable<BaseWifi> {
    private static final EnumMap<DetailedState, ConnectState> stateMap = new EnumMap<>(DetailedState.class);
    protected String bssid;
    protected String capabilities;
    protected long connectTime = -1;
    protected int frequency = 0;
    private Boolean isGroup = false;
    private Boolean isTitle = false;
    protected int level;
    protected AuthState needAuth = AuthState.NO_AUTH;
    protected boolean shareWifi = false;
    protected String ssid;
    protected long updateTime;

    public abstract BaseWifi newInstance();

    public abstract BaseWifi[] newInstance(int i);

    static {
        stateMap.put(DetailedState.DISCONNECTED, ConnectState.DISCONNECTED);
        stateMap.put(DetailedState.FAIL, ConnectState.DISCONNECTED);
        stateMap.put(DetailedState.CONNECTED_TIME_OUT, ConnectState.DISCONNECTED);
        stateMap.put(DetailedState.IDLE, ConnectState.DISCONNECTED);
        stateMap.put(DetailedState.PASSWORD_ERROR, ConnectState.DISCONNECTED);
        stateMap.put(DetailedState.CONNECTING, ConnectState.CONNECTING);
        stateMap.put(DetailedState.AUTHENTICATING, ConnectState.CONNECTING);
        stateMap.put(DetailedState.OBTAINING_IPADDR, ConnectState.CONNECTING);
        stateMap.put(DetailedState.CONNECTED, ConnectState.CONNECTED);
        stateMap.put(DetailedState.CONNECTED_GOOD, ConnectState.CONNECTED);
        stateMap.put(DetailedState.CONNECTED_BLOCK, ConnectState.CONNECTED);
        stateMap.put(DetailedState.CONNECTED_AUTH_DO, ConnectState.CONNECTED);
        stateMap.put(DetailedState.CONNECTED_AUTH_NO, ConnectState.CONNECTED);
    }

    public Boolean getIsGroup() {
        return this.isGroup;
    }

    public void setIsGroup(Boolean bool) {
        this.isGroup = bool;
    }

    public Boolean getIsTitle() {
        return this.isTitle;
    }

    public void setIsTitle(Boolean bool) {
        this.isTitle = bool;
    }

    public static ConnectState getConnectState(DetailedState dVar) {
        return stateMap.get(dVar);
    }

    public int compareTo(BaseWifi baseWifi) {
        return baseWifi.level - this.level;
    }

    public String getBssid() {
        return this.bssid;
    }

    public String getCapabilities() {
        return this.capabilities;
    }

    public long getConnectTime() {
        return this.connectTime;
    }

    public int getLevel() {
        return this.level;
    }

    public AuthState getNeedAuth() {
        return this.needAuth;
    }

    public String getSsid() {
        return this.ssid;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public boolean isShareWifi() {
        return this.shareWifi;
    }

    public void setBssid(String str) {
        this.bssid = str;
    }

    public void setCapabilities(String str) {
        this.capabilities = str;
    }

    public void setConnectTime(long j) {
        this.connectTime = j;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public void setNeedAuth(AuthState bVar) {
        this.needAuth = bVar;
    }

    public void setShareWifi(boolean z) {
        this.shareWifi = z;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public void setUpdateTime(long j) {
        this.updateTime = j;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public String toString() {
        return "BaseWifi [bssid=" + this.bssid + ", ssid=" + this.ssid + ", updateTime=" + this.updateTime + ", capabilities=" + this.capabilities + ", level=" + this.level + ", connectTime=" + this.connectTime + ", shareWifi=" + this.shareWifi + "]";
    }
}
