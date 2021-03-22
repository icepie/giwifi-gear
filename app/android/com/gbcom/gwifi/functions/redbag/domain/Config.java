package com.gbcom.gwifi.functions.redbag.domain;

public class Config {
    private int heartbeatPeriod;
    private int heartbeatSwitch;
    private transient int newComerHitBeans;
    private transient int newComerHitRangeBegin;
    private transient int newComerHitRangeEnd;
    private transient int newComerHitType;
    private transient int newComerSwitch;
    private int redbagHitAction;
    private int refreshTime;
    private String ruleDetail;

    public int getRefreshTime() {
        return this.refreshTime;
    }

    public void setRefreshTime(int i) {
        this.refreshTime = i;
    }

    public int getHeartbeatSwitch() {
        return this.heartbeatSwitch;
    }

    public void setHeartbeatSwitch(int i) {
        this.heartbeatSwitch = i;
    }

    public int getHeartbeatPeriod() {
        return this.heartbeatPeriod;
    }

    public void setHeartbeatPeriod(int i) {
        this.heartbeatPeriod = i;
    }

    public int getNewComerSwitch() {
        return this.newComerSwitch;
    }

    public void setNewComerSwitch(int i) {
        this.newComerSwitch = i;
    }

    public int getNewComerHitType() {
        return this.newComerHitType;
    }

    public void setNewComerHitType(int i) {
        this.newComerHitType = i;
    }

    public int getNewComerHitBeans() {
        return this.newComerHitBeans;
    }

    public void setNewComerHitBeans(int i) {
        this.newComerHitBeans = i;
    }

    public int getNewComerHitRangeBegin() {
        return this.newComerHitRangeBegin;
    }

    public void setNewComerHitRangeBegin(int i) {
        this.newComerHitRangeBegin = i;
    }

    public int getNewComerHitRangeEnd() {
        return this.newComerHitRangeEnd;
    }

    public void setNewComerHitRangeEnd(int i) {
        this.newComerHitRangeEnd = i;
    }

    public int getRedbagHitAction() {
        return this.redbagHitAction;
    }

    public void setRedbagHitAction(int i) {
        this.redbagHitAction = i;
    }

    public String getRuleDetail() {
        return this.ruleDetail;
    }

    public void setRuleDetail(String str) {
        this.ruleDetail = str;
    }
}
