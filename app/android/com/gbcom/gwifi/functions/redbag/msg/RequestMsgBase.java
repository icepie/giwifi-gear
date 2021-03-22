package com.gbcom.gwifi.functions.redbag.msg;

import android.os.Build;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.util.FormatUtil;
import com.gbcom.gwifi.util.VersionUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAuth;

public class RequestMsgBase extends AbstractMsg {
    protected int activityId;
    protected String appName;
    protected String appVersion;
    protected short command;
    protected String deviceMac;
    protected String model;

    /* renamed from: os */
    protected String f11360os;
    protected String osVersion;
    protected int sequenceId;
    protected String sign;
    protected String terminalMac;
    protected String token;

    public RequestMsgBase() {
    }

    public RequestMsgBase(short s) {
        this.activityId = 1;
        this.command = s;
        this.sequenceId = FormatUtil.m14235f();
        this.appName = "GW";
        this.appVersion = VersionUtil.m14564a(GBApplication.instance()) + "";
        this.f11360os = DispatchConstants.ANDROID;
        this.osVersion = Build.VERSION.RELEASE;
        this.model = Build.MODEL;
        this.token = CacheAccount.getInstance().getUserToken();
        this.terminalMac = CacheAuth.getInstance().getLocalMac();
        this.deviceMac = WiFiUtil.m14022a(GBApplication.instance()).mo27615i();
    }

    @Override // com.gbcom.gwifi.functions.redbag.msg.AbstractMsg
    public short getType() {
        return this.command;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getOs() {
        return this.f11360os;
    }

    public void setOs(String str) {
        this.f11360os = str;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public short getCommand() {
        return this.command;
    }

    public void setCommand(short s) {
        this.command = s;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public int getActivityId() {
        return this.activityId;
    }

    public void setActivityId(int i) {
        this.activityId = i;
    }

    public String getTerminalMac() {
        return this.terminalMac;
    }

    public void setTerminalMac(String str) {
        this.terminalMac = str;
    }

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public int getSequenceId() {
        return this.sequenceId;
    }

    public void setSequenceId(int i) {
        this.sequenceId = i;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }
}
