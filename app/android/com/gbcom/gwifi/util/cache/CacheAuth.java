package com.gbcom.gwifi.util.cache;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.p242c.p243a.GameConfig;
import com.gbcom.gwifi.functions.p242c.p243a.GameIpNetMask;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.functions.wifi.entity.AuthInfo;
import com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.CheckUtil;
import com.gbcom.gwifi.util.Config;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Ipv4Util;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.SystemUtil;
import java.util.List;

public class CacheAuth extends CacheBase {
    private static final String CACHE_AUTH_METHOD = "CACHE_AUTH_METHOD";
    private static final String CACHE_CIRCLE_LOGIN = "CACHE_CIRCLE_LOGIN";
    private static final String CACHE_CONTACT_PHONE = "CACHE_CONTACT_PHONE";
    private static final String CACHE_GIWIFI_AUTH_METHOD = "CACHE_GIWIFI_AUTH_METHOD";
    private static final String CACHE_GW_ID = "CACHE_GW_ID";
    private static final String CACHE_GW_SN = "CACHE_GW_SN";
    private static final String CACHE_LOCAL_MAC = "CACHE_LOCAL_MAC";
    private static final String CACHE_ONLINE_LOCAL_IP = "CACHE_ONLINE_LOCAL_IP";
    private static final String CACHE_ORG_ID = "CACHE_ORG_ID";
    private static final String CACHE_ORG_TYPE = "CACHE_ORG_TYPE";
    private static final String CACHE_PORTAL_HOST = "CACHE_PORTAL_HOST";
    private static final String CACHE_PORTAL_IS_ACTIVE_DOWNLINE = "CACHE_PORTAL_IS_ACTIVE_DOWNLINE";
    private static final String CACHE_PORTAL_IS_PORTAL = "CACHE_PORTAL_IS_PORTAL";
    private static final String CACHE_PORTAL_LOCATION = "CACHE_PORTAL_LOCATION";
    private static final String CACHE_PORTAL_NASNAME = "CACHE_PORTAL_NASNAME";
    private static final String CACHE_PORTAL_ONLINETIME = "CACHE_PORTAL_ONLINETIME";
    private static final String CACHE_PORTAL_PORT = "CACHE_PORTAL_PORT";
    private static final String CACHE_STATION_CLOUD = "CACHE_STATION_CLOUD";
    private static final String CACHE_SUGGEST_PHONE = "CACHE_SUGGEST_PHONE";
    private static final String TAG = "CacheAuth";
    private static CacheAuth instance = null;
    private final String CACHE_AUTH_MODE = "CACHE_AUTH_MODE";
    private final String CACHE_AUTH_TYPE = "CACHE_AUTH_TYPE";
    private final String CACHE_FILTER_ID = "CACHE_FILTER_ID";
    private final String CACHE_LAST_RELEASE_TIME = "CACHE_LAST_RELEASE_TIME";
    private final AuthInfo authBean = new AuthInfo();
    private String onlineLocalIp = null;

    public static CacheAuth getInstance() {
        if (instance == null) {
            instance = new CacheAuth();
        }
        return instance;
    }

    public boolean checkGameIpNetMask() {
        char c;
        char c2 = 2;
        String onlineLocalIp2 = getOnlineLocalIp();
        String j = WiFiUtil.m14021a().mo27616j();
        Log.m14398b(TAG, "BaseGiWiFiInfoView: oldIp=" + onlineLocalIp2 + ",newIp=" + j);
        if (onlineLocalIp2 == null || C3467s.m14513e(onlineLocalIp2) || j == null || C3467s.m14513e(j) || onlineLocalIp2.equals(j)) {
            return true;
        }
        GameConfig gameConfig = CacheGame.getInstance().getGameConfig();
        if (gameConfig == null) {
            Log.m14403e(TAG, "checkGameIpNetMask: gameConfig is null");
            return true;
        } else if (gameConfig.mo24523b() == 0) {
            Log.m14403e(TAG, "checkGameIpNetMask: gameConfig game disable");
            return true;
        } else {
            List<GameIpNetMask> f = gameConfig.mo24530f();
            if (f != null) {
                int i = 0;
                while (true) {
                    if (i >= f.size()) {
                        c = 1;
                        break;
                    }
                    GameIpNetMask cVar = f.get(i);
                    if (Ipv4Util.m14373a(onlineLocalIp2, cVar.mo24535a(), Ipv4Util.m14381f(cVar.mo24537b()))) {
                        c = 2;
                        break;
                    }
                    i++;
                }
                int i2 = 0;
                while (true) {
                    if (i2 >= f.size()) {
                        c2 = 1;
                        break;
                    }
                    GameIpNetMask cVar2 = f.get(i2);
                    if (Ipv4Util.m14373a(j, cVar2.mo24535a(), Ipv4Util.m14381f(cVar2.mo24537b()))) {
                        break;
                    }
                    i2++;
                }
            } else {
                c2 = 1;
                c = 1;
            }
            return c == c2;
        }
    }

    public synchronized void setLocalMac(String str) {
        if (CheckUtil.m14084b(str) && !str.equals("00:00:00:00:00:00") && !str.equals(WifiInfo.DEFAULT_MAC_ADDRESS)) {
            String trim = str.trim();
            String localMac = getLocalMac();
            if (!trim.toUpperCase().equals(localMac)) {
                setStringValue(CACHE_LOCAL_MAC, trim);
                this.authBean.setClientMac(trim);
                if (!C3467s.m14513e(localMac)) {
                    Log.m14398b(TAG, "BaseGiWiFiInfoView send WIFI_MAC_CHANGE_ACTION");
                    GBApplication.instance().sendBroadcast(new Intent(Constant.f13246bU));
                    CacheWiFi.getInstance().setNeedNoticeMac();
                }
                GiwifiPushAgent.getInstance(GBApplication.instance()).updateStatus();
            }
        }
    }

    public synchronized void setLocalMac2(String str) {
        if (CheckUtil.m14084b(str) && !str.equals("00:00:00:00:00:00") && !str.equals(WifiInfo.DEFAULT_MAC_ADDRESS)) {
            String trim = str.trim();
            if (!trim.toUpperCase().equals(getLocalMac())) {
                setStringValue(CACHE_LOCAL_MAC, trim);
                this.authBean.setClientMac(trim);
            }
        }
    }

    public synchronized String getLocalMac() {
        return getStringValue(CACHE_LOCAL_MAC, "").toUpperCase();
    }

    public synchronized void clearLocalMac() {
        setStringValue(CACHE_LOCAL_MAC, "");
        this.authBean.setClientMac("");
    }

    public synchronized void setOnlineLocalIp(String str) {
        setStringValue(CACHE_ONLINE_LOCAL_IP, str);
        this.onlineLocalIp = str;
    }

    public synchronized String getOnlineLocalIp() {
        return getStringValue(CACHE_ONLINE_LOCAL_IP, "");
    }

    public void setCacheAuthBean(int i, String str, String str2, String str3, int i2, int i3, String str4, String str5) {
        this.authBean.setAuthState(Integer.valueOf(i));
        if (i == 2) {
            String j = WiFiUtil.m14021a().mo27616j();
            setOnlineLocalIp(j);
            Log.m14398b(TAG, "setOnlineLocalIp " + j);
        }
        setGwId(str);
        setGwSn(str2);
        setGwIp(WiFiUtil.m14022a(GBApplication.instance()).mo27617k());
        setLocalIp(WiFiUtil.m14022a(GBApplication.instance()).mo27616j());
        setLocalMac(str3);
        setOnlineTime(i2);
        setLogoutReason(i3);
        setContactPhone(str4);
        setSuggestPhone(str5);
    }

    public void resetCacheAuthBean() {
        setGwIp(WiFiUtil.m14022a(GBApplication.instance()).mo27617k());
        setLocalIp(WiFiUtil.m14022a(GBApplication.instance()).mo27616j());
    }

    public String getGwIp() {
        String gwIp = this.authBean.getGwIp();
        if (gwIp == null || C3467s.m14513e(gwIp)) {
            return "";
        }
        return gwIp;
    }

    public void setGwIp(String str) {
        this.authBean.setGwIp(str);
    }

    public String getGwId() {
        String gwId = this.authBean.getGwId();
        if (gwId == null || C3467s.m14513e(gwId)) {
            return "";
        }
        return gwId;
    }

    public void setGwId(String str) {
        Log.m14398b(TAG, "CacheAuth: setGwId " + str);
        if (!getStringValue(CACHE_GW_ID).equals(str)) {
            setStringValue(CACHE_GW_ID, str);
            if (!C3467s.m14513e(str)) {
                GBApplication.instance().sendBroadcast(new Intent(Constant.f13248bW));
            }
        }
        this.authBean.setGwId(str);
    }

    public String getGwSn() {
        if (SystemUtil.m14533f()) {
            return GBApplication.instance().getResources().getString(C2425R.C2429string.project_sn);
        }
        return getStringValue(CACHE_GW_SN, AuthInfo.DEFAULT_SN);
    }

    public void setGwSn(String str) {
        Log.m14398b(TAG, "CacheAuth: setGwSn " + str);
        setStringValue(CACHE_GW_SN, str);
        this.authBean.setStationSn(str);
    }

    public String getLocalIp() {
        String localIp = this.authBean.getLocalIp();
        if (localIp == null || C3467s.m14513e(localIp)) {
            return "";
        }
        return localIp;
    }

    public void setLocalIp(String str) {
        this.authBean.setLocalIp(str);
    }

    public void setOnlineTime(int i) {
        this.authBean.setOnlineTime(Integer.valueOf(i));
    }

    public int getOnlineTime() {
        return this.authBean.getOnlineTime().intValue();
    }

    public int getLogoutReason() {
        return this.authBean.getLogoutReason().intValue();
    }

    public void setLogoutReason(int i) {
        this.authBean.setLogoutReason(Integer.valueOf(i));
    }

    public String getContactPhone() {
        return getStringValue(CACHE_CONTACT_PHONE, Constant.f13225b);
    }

    public void setContactPhone(String str) {
        setStringValue(CACHE_CONTACT_PHONE, str);
        this.authBean.setContactPhone(str);
    }

    public String getSuggestPhone() {
        return getStringValue(CACHE_SUGGEST_PHONE, "");
    }

    public void setSuggestPhone(String str) {
        setStringValue(CACHE_SUGGEST_PHONE, str);
        this.authBean.setSuggestPhone(str);
    }

    public void setStationCloud(String str) {
        setStringValue(CACHE_STATION_CLOUD, str);
    }

    public String getStationCloud() {
        String stringValue = getStringValue(CACHE_STATION_CLOUD, "");
        return !TextUtils.isEmpty(stringValue) ? stringValue : "login.gwifi.com.cn";
    }

    public void setOrgId(String str) {
        setStringValue(CACHE_ORG_ID, str);
    }

    public String getOriOrgId() {
        return getStringValue(CACHE_ORG_ID, "0");
    }

    public String getOrgId() {
        int intValue;
        int intValue2;
        String stringValue = getStringValue(CACHE_ORG_ID, "0");
        if (C3467s.m14513e(stringValue) || stringValue.equals("0")) {
            return (!getInstance().isPortal() || (intValue = CacheAccount.getInstance().getHotspotGroupId().intValue()) <= 0) ? stringValue : "" + intValue;
        }
        if (!getInstance().isPortal() || (intValue2 = CacheAccount.getInstance().getHotspotGroupId().intValue()) <= 0) {
            return stringValue;
        }
        return "" + intValue2;
    }

    public void setOrgType(int i) {
        setIntValue(CACHE_ORG_TYPE, i);
    }

    public int getOrgType() {
        int i = 2;
        if (!SystemUtil.m14531e() && Config.m14094a().mo27812o()) {
            i = 1;
        }
        return getIntValue(CACHE_ORG_TYPE, i);
    }

    public void setPortalLocation(String str) {
        setStringValue(CACHE_PORTAL_LOCATION, str);
    }

    public boolean isPortal() {
        int authMethod = getAuthMethod();
        if (authMethod == 2) {
            return true;
        }
        return authMethod != 1 && getIntValue(CACHE_GIWIFI_AUTH_METHOD, 0) == 2;
    }

    public void setPortalHost(String str) {
        setStringValue(CACHE_PORTAL_HOST, str);
    }

    public String getPortalHost() {
        String stringValue = getStringValue(CACHE_PORTAL_HOST, "");
        if (C3467s.m14513e(stringValue)) {
            return Constant.f13230bE;
        }
        return stringValue;
    }

    public void setPortalPort(int i) {
        setIntValue(CACHE_PORTAL_PORT, i);
    }

    public int getPortalPort() {
        int intValue = getIntValue(CACHE_PORTAL_PORT, -1);
        if (intValue <= 0) {
            return 80;
        }
        return intValue;
    }

    public void setPortalNasName(String str) {
        setStringValue(CACHE_PORTAL_NASNAME, str);
    }

    public String getPortalNasName() {
        return getStringValue(CACHE_PORTAL_NASNAME, "");
    }

    public void setIsActiveDownline(boolean z) {
        setBooleanValue(CACHE_PORTAL_IS_ACTIVE_DOWNLINE, z);
    }

    public boolean getIsActiveDownline() {
        return getBooleanValue(CACHE_PORTAL_IS_ACTIVE_DOWNLINE, false);
    }

    public void setPortalOnlineTime(int i) {
        setIntValue(CACHE_PORTAL_ONLINETIME, i);
    }

    public int getPortalOnlineTime() {
        return getIntValue(CACHE_PORTAL_ONLINETIME, 0);
    }

    public void resetPortalDisable() {
        setPortalHost("");
        setPortalPort(-1);
        setPortalLocation("");
        setPortalNasName("");
        setAuthMethod(0);
        setIntValue(CACHE_GIWIFI_AUTH_METHOD, 0);
    }

    public void setAuthType(int i) {
        setIntValue("CACHE_AUTH_TYPE", i);
    }

    public int getAuthType() {
        return getIntValue("CACHE_AUTH_TYPE", 1);
    }

    public void resetAuthType() {
        if (getAuthType() == 0) {
            setAuthType(1);
        }
    }

    public void setLastReleaseTime(long j) {
        setLongValue("CACHE_LAST_RELEASE_TIME", j);
    }

    public long getLastReleaseTime() {
        return getLongValue("CACHE_LAST_RELEASE_TIME", 0);
    }

    public void setAuthMode(int i) {
        setIntValue("CACHE_AUTH_MODE", i);
    }

    public int getAuthMode() {
        return getIntValue("CACHE_AUTH_MODE", 1);
    }

    public void setFilterId(String str) {
        setStringValue("CACHE_FILTER_ID", str);
    }

    public String getFilterId() {
        return getStringValue("CACHE_FILTER_ID", "");
    }

    public void setAuthMethod(int i) {
        int intValue = getIntValue("CACHE_AUTH_MODE", 0);
        setIntValue(CACHE_AUTH_METHOD, i);
        if (i == 2) {
            setGwId("");
            setIntValue(CACHE_GIWIFI_AUTH_METHOD, i);
        }
        if (i == 1) {
            setIntValue(CACHE_GIWIFI_AUTH_METHOD, i);
        }
        if (intValue != i) {
            if (!HttpUtil.m14277a().equals(CacheAccount.getInstance().getLoginTokenPlatform())) {
                CacheAccount.getInstance().setLoginToken("");
                CacheAccount.getInstance().setLoginTokenExpires(0);
                CacheAccount.getInstance().setLoginTokenPlatform("");
            }
        }
    }

    public int getAuthMethod() {
        return getIntValue(CACHE_AUTH_METHOD, 0);
    }

    public void setCircleLogin(boolean z) {
        setBooleanValue(CACHE_CIRCLE_LOGIN, z);
    }

    public boolean isCircleLogin() {
        return getBooleanValue(CACHE_CIRCLE_LOGIN, false);
    }
}
