package com.gbcom.gwifi.util.cache;

import android.support.p009v4.app.Fragment;
import android.view.View;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView;
import com.gbcom.gwifi.functions.template.fragment.GiWiFiInfoExView;
import com.gbcom.gwifi.functions.template.fragment.GiWiFiInfoHomeView;
import com.gbcom.gwifi.functions.template.fragment.HomeFragment;
import com.gbcom.gwifi.functions.wifi.entity.WiFiScanResult;
import com.gbcom.gwifi.functions.wifi.p253a.WifiState;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.JsonUtil;

public class CacheWiFi extends CacheBase {
    private static final String SHORT_SSID_PREFIX = "short_ssid_prefix";
    private static CacheWiFi instance = null;
    private final String AP_LOCATION = "ap_location";
    private final String CACHE_HAS_NOTICE_MAC_TIMES = "CACHE_HAS_NOTICE_MAC_TIMES";
    private final String CACHE_NEED_NOTICE_MAC = "CACHE_NEED_NOTICE_MAC";
    private final String GAME_COMMON_WIFI = "GAME_COMMON_WIFI";
    private final String GAME_GAME_WIFI = "GAME_GAME_WIFI";
    private final String LAST_AP_BSSID = "LAST_AP_BSSID";
    private final String NETWORK_REACHABLE = "network_reachable";
    private final String SCAN_WIFI_LIST = "scan_wifi_list";
    private final String WIFICONTROL_STATE = "wificontrol_state";
    private String currentBssid = "";
    private String currentSsid = "";
    private WifiState currentState = WifiState.TRY_CONNECT;
    private boolean isInnerWifi;

    public boolean checkNeedNoticeMac() {
        int intValue;
        if (!getBooleanValue("CACHE_NEED_NOTICE_MAC", false) || (intValue = getIntValue("CACHE_HAS_NOTICE_MAC_TIMES", 0)) >= 10) {
            return false;
        }
        setIntValue("CACHE_HAS_NOTICE_MAC_TIMES", intValue + 1);
        setBooleanValue("CACHE_NEED_NOTICE_MAC", false);
        return true;
    }

    public void setNeedNoticeMac() {
        setBooleanValue("CACHE_NEED_NOTICE_MAC", true);
    }

    public static CacheWiFi getInstance() {
        if (instance == null) {
            instance = new CacheWiFi();
        }
        return instance;
    }

    public String getCurrentSsid() {
        return this.currentSsid;
    }

    public void setCurrentSsid(String str) {
        this.currentSsid = str;
    }

    public String getCurrentBssid() {
        return this.currentBssid;
    }

    public void setCurrentBssid(String str) {
        this.currentBssid = str;
    }

    public WifiState getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(WifiState fVar) {
        this.currentState = fVar;
    }

    public boolean isInnerWifi() {
        return this.isInnerWifi;
    }

    public void setInnerWifi(boolean z) {
        this.isInnerWifi = z;
    }

    public void setWifiControlState(int i) {
        setIntValue("wificontrol_state", i);
    }

    public int getWifiControlState() {
        return getIntValue("wificontrol_state", 0);
    }

    public void setNetworkReachable(boolean z) {
        setBooleanValue("network_reachable", z);
    }

    public boolean getNetworkReachable() {
        return getBooleanValue("network_reachable", false);
    }

    public void setScanWifiList(String str) {
        setStringValue("scan_wifi_list", str);
    }

    public String getScanWifiList() {
        return getStringValue("scan_wifi_list", "");
    }

    public void setApLocation(String str) {
        GBActivity currentActivity;
        String stringValue = getStringValue("ap_location", "");
        setStringValue("ap_location", str);
        if (!stringValue.equals(str) && (currentActivity = GBApplication.instance().getCurrentActivity()) != null && (currentActivity instanceof MainActivity)) {
            MainActivity mainActivity = (MainActivity) currentActivity;
            setConnWifiLocation(mainActivity, str);
            setHomeWiFiLocation(mainActivity, str);
        }
    }

    private void setConnWifiLocation(MainActivity mainActivity, String str) {
        GiWiFiInfoExView giWiFiInfoExView;
        BaseGiWiFiInfoView.AbstractC3237g aX;
        if (mainActivity.getConnView() != null && (giWiFiInfoExView = (GiWiFiInfoExView) mainActivity.findViewById(C2425R.C2427id.wifi_info_layout)) != null && (aX = giWiFiInfoExView.mo27146aX()) != null) {
            aX.mo24475a(str);
        }
    }

    private void setHomeWiFiLocation(MainActivity mainActivity, String str) {
        View view;
        GiWiFiInfoHomeView giWiFiInfoHomeView;
        BaseGiWiFiInfoView.AbstractC3237g aX;
        if (mainActivity != null && mainActivity.getHomeView() != null) {
            Fragment fragment = mainActivity.getFragment("0");
            if ((fragment instanceof HomeFragment) && (view = fragment.getView()) != null && (giWiFiInfoHomeView = (GiWiFiInfoHomeView) view.findViewById(C2425R.C2427id.home_wifi_info_layout)) != null && (aX = giWiFiInfoHomeView.mo27146aX()) != null) {
                aX.mo24475a(str);
            }
        }
    }

    public String getApLocation() {
        return getStringValue("ap_location", "");
    }

    public void setShortSsidPrefix(String str) {
        setStringValue(SHORT_SSID_PREFIX, str);
    }

    public String getShortSsidPrefix() {
        return getStringValue(SHORT_SSID_PREFIX, ",");
    }

    public void setLastApBssid(String str) {
        setStringValue("LAST_AP_BSSID", str);
    }

    public String getLastApBssid() {
        return getStringValue("LAST_AP_BSSID", "");
    }

    public void setGameCommonWifi(WiFiScanResult wiFiScanResult) {
        if (wiFiScanResult != null) {
            setStringValue("GAME_COMMON_WIFI", JsonUtil.m14387a(wiFiScanResult));
        }
    }

    public WiFiScanResult getGameCommonWifi() {
        String stringValue = getStringValue("GAME_COMMON_WIFI", "");
        if (C3467s.m14513e(stringValue)) {
            return null;
        }
        return (WiFiScanResult) JsonUtil.m14386a(stringValue, WiFiScanResult.class);
    }

    public void setGameGameWifi(WiFiScanResult wiFiScanResult) {
        if (wiFiScanResult != null) {
            setStringValue("GAME_GAME_WIFI", JsonUtil.m14387a(wiFiScanResult));
        }
    }

    public WiFiScanResult getGameGameWifi() {
        String stringValue = getStringValue("GAME_GAME_WIFI", "");
        if (C3467s.m14513e(stringValue)) {
            return null;
        }
        return (WiFiScanResult) JsonUtil.m14386a(stringValue, WiFiScanResult.class);
    }
}
