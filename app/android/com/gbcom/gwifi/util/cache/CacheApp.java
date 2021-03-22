package com.gbcom.gwifi.util.cache;

import android.util.Base64;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.loading.domain.NotifyWhenAppExit;
import com.gbcom.gwifi.functions.loading.domain.TabDatas;
import com.gbcom.gwifi.functions.product.domain.RecommendAppListResponse;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.VersionUtil;
import java.p456io.ByteArrayInputStream;
import java.p456io.ByteArrayOutputStream;
import java.p456io.IOException;
import java.p456io.ObjectInputStream;
import java.p456io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CacheApp extends CacheBase {
    private static final String BACKGROUND_STAY_TIME = "background_stay_time";
    private static final String CACHE_BSSID_LIST = "LOCAL_BSSID_LIST";
    private static final String CACHE_CONTENT_PREFIX = "CACHE_CONTENT_";
    private static final String CACHE_SSID_LIST = "LOCAL_SSID_LIST";
    private static final String CACHE_UMENG_PUSH_APP_UUID = "CACHE_UMENG_PUSH_APP_UUID";
    private static final String CACHE_UMENG_PUSH_MAC = "CACHE_UMENG_PUSH_REGISTER_ID";
    private static final String CACHE_UMENG_PUSH_REGISTER_ID = "CACHE_UMENG_PUSH_REGISTER_ID";
    private static final String CACHE_UMENG_PUSH_REGISTER_ID_TEMP = "CACHE_UMENG_PUSH_REGISTER_ID_TEMP";
    private static final String DOWNLOAD_VERSION_BACKGROUND = "download_version_background";
    private static final String GO_BACKGROUND_TIME = "go_background_time";
    private static final String TAG = "CacheApp";
    private static CacheApp instance = null;
    private final String AFTER_AUTH_JUMP_TAB_TYPE = "AFTER_AUTH_JUMP_TAB_TYPE";
    private final String CACHE_AGREEMENT_TIME = "CACHE_AGREEMENT_TIME";
    private final String CACHE_AGREEMENT_YES = "CACHE_AGREEMENT_YES";
    private final String CACHE_AGRT_PRIVACY_UPDATE_TIME = "CACHE_AGRT_PRIVACY_UPDATE_TIME";
    private final String CACHE_AGRT_SERVICE_UPDATE_TIME = "CACHE_AGRT_SERVICE_UPDATE_TIME";
    private final String CACHE_APP_DPTIP_CONTENT = "CACHE_APP_DPTIP_CONTENT";
    private final String CACHE_APP_DPTIP_SHOW = "CACHE_APP_DPTIP_SHOW";
    private final String CACHE_APP_UUID = "CACHE_APP_UUID";
    private final String CACHE_ARCSOFT_FACE_IS_ACTIVE = "CACHE_ARCSOFT_FACE_IS_ACTIVE";
    private final String CACHE_ARCSOFT_FACE_IS_LIB_LOADED = "CACHE_ARCSOFT_FACE_IS_LIB_LOADED";
    private final String CACHE_ARCSOFT_FACE_LIB_LOADED_TIME = "CACHE_ARCSOFT_FACE_LIB_LOADED_TIME";
    private final String CACHE_DOWNLOAD_APP_DATA = "CACHE_DOWNLOAD_APP_DATA";
    private final String CACHE_RECHARGE_WEB_URL = "CACHE_RECHARGE_WEB_URL";
    private final String CACHE_REPORT_APP_NAME_DAY = "CACHE_REPORT_APP_NAME_DAY";
    private final String FIRST_GUIDE = "first_guide";
    private final String FIRST_GUIDE_CODE = "first_guide_code";
    private final String HAS_TITLE = "has_title";
    private final String LAST_AUTO_CHECK_VERSION_TIME = "LAST_AUTO_CHECK_VERSION_TIME";
    private final String OFFICE_MODULES = "office_modules";
    private final String OFFICE_MODULES_V2 = "office_modules_v2";
    private final String OFFLINE_NOTIFY = "OFFLINE_NOTIFY";
    private final String QUANZI_GUIDE = "quanzi_guide";
    private final String SECOND_GUIDE = "second_guide";
    private final String SECOND_GUIDE_CODE = "second_guide_code";
    private final String SHARE_URL = "share_url";
    private final String TAB_DATA = "tab_data";
    private final String WEB_GUIDE = "web_Guide";

    public static CacheApp getInstance() {
        if (instance == null) {
            instance = new CacheApp();
        }
        return instance;
    }

    public void setFirstGuide(boolean z) {
        setBooleanValue("first_guide", z);
    }

    public boolean getFirstGuide() {
        return getBooleanValue("first_guide", false);
    }

    public void setFirstGuideCode(Integer num) {
        setIntValue("first_guide_code", num.intValue());
    }

    public Integer getFirstGuideCode() {
        return Integer.valueOf(getIntValue("first_guide_code", 1));
    }

    public void setSecondGuide(boolean z) {
        setBooleanValue("second_guide", z);
    }

    public boolean getSecondGuide() {
        return getBooleanValue("second_guide", false);
    }

    public void setSecondGuideCode(Integer num) {
        setIntValue("second_guide_code", num.intValue());
    }

    public Integer getSecondGuideCode() {
        return Integer.valueOf(getIntValue("second_guide_code", 1));
    }

    public void setWeChatGuide(boolean z) {
        setBooleanValue("web_Guide", z);
    }

    public boolean getWeChatGuide() {
        return getBooleanValue("web_Guide", false);
    }

    public void setQuanziGuide(boolean z) {
        setBooleanValue("quanzi_guide", z);
    }

    public boolean getQuanziGuide() {
        return getBooleanValue("quanzi_guide", false);
    }

    public void setAfterAuthJumpTabType(int i) {
        setIntValue("AFTER_AUTH_JUMP_TAB_TYPE", i);
    }

    public int getAfterAuthJumpTabType() {
        return getIntValue("AFTER_AUTH_JUMP_TAB_TYPE", 0);
    }

    public void setTabList(List<TabDatas.DataBean.TabListBean> list) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(list);
            String str = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
            objectOutputStream.close();
            setStringValue("tab_data", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<TabDatas.DataBean.TabListBean> getTabList() {
        String stringValue = getStringValue("tab_data", "");
        if (stringValue == null || C3467s.m14513e(stringValue)) {
            return null;
        }
        try {
            return (List) new ObjectInputStream(new ByteArrayInputStream(Base64.decode(stringValue.getBytes(), 0))).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setOnBackgroundTime() {
        setLongValue(GO_BACKGROUND_TIME, System.currentTimeMillis());
    }

    public long getOnBackgroundTime() {
        return getLongValue(GO_BACKGROUND_TIME);
    }

    public void setBackgroundStayTime(int i) {
        setLongValue(BACKGROUND_STAY_TIME, (long) (i * 60 * 1000));
    }

    public long getBackgroundStayTime() {
        return getLongValue(BACKGROUND_STAY_TIME);
    }

    public ArrayList<HashMap<String, Object>> getOfficeInfo() {
        String stringValue = getStringValue("office_modules", "");
        if (!stringValue.equals("")) {
            return (ArrayList) JsonUtil.m14386a(stringValue, ArrayList.class);
        }
        return null;
    }

    public void setOfficeInfo(ArrayList<HashMap<String, Object>> arrayList) {
        setStringValue("office_modules", JsonUtil.m14387a(arrayList));
    }

    public String getOfficeModules() {
        return getStringValue("office_modules_v2", "");
    }

    public void setOfficeModules(String str) {
        setStringValue("office_modules_v2", str);
    }

    public String getRechargeWebUrl() {
        return getStringValue("CACHE_RECHARGE_WEB_URL", "");
    }

    public void setRechargeWebUrl(String str) {
        setStringValue("CACHE_RECHARGE_WEB_URL", str);
    }

    public void setShareUrl(String str) {
        setStringValue("share_url", str);
    }

    public String getShareUrl() {
        return getStringValue("share_url");
    }

    public void setDownloadVersionBackground(HashMap<String, Object> hashMap) {
        setObjectHashMapValue(DOWNLOAD_VERSION_BACKGROUND, hashMap);
    }

    public void removeDownloadVersionBackground() {
        GBApplication.instance().getSharedPreferences().edit().remove(DOWNLOAD_VERSION_BACKGROUND).commit();
    }

    public HashMap getDownloadVersionBackground() {
        return getHashMapValue(DOWNLOAD_VERSION_BACKGROUND);
    }

    public long getLastAutoCheckVersionTime() {
        return getLongValue("LAST_AUTO_CHECK_VERSION_TIME");
    }

    public void setLastAutoCheckVersionTime(long j) {
        setLongValue("LAST_AUTO_CHECK_VERSION_TIME", j);
    }

    public void setOfflineNotify(NotifyWhenAppExit notifyWhenAppExit) {
        String a = JsonUtil.m14387a(notifyWhenAppExit);
        cacheData.put("OFFLINE_NOTIFY", notifyWhenAppExit);
        GBApplication.instance().getSharedPreferences().edit().putString("OFFLINE_NOTIFY", a).commit();
    }

    public NotifyWhenAppExit getOfflineNotify() {
        if (cacheData.containsKey("OFFLINE_NOTIFY")) {
            return (NotifyWhenAppExit) cacheData.get("OFFLINE_NOTIFY");
        }
        NotifyWhenAppExit notifyWhenAppExit = (NotifyWhenAppExit) JsonUtil.m14386a(GBApplication.instance().getSharedPreferences().getString("OFFLINE_NOTIFY", null), NotifyWhenAppExit.class);
        cacheData.put("OFFLINE_NOTIFY", notifyWhenAppExit);
        return notifyWhenAppExit;
    }

    public String getCacheContent(int i) {
        return GBApplication.instance().getSharedPreferences().getString(CACHE_CONTENT_PREFIX + i, "");
    }

    public void setCacheContent(int i, String str) {
        GBApplication.instance().getSharedPreferences().edit().putString(CACHE_CONTENT_PREFIX + i, str).commit();
    }

    public void setCacheBssidList(String str) {
        setStringValue(CACHE_BSSID_LIST, str);
    }

    public String getCacheBssidList() {
        return getStringValue(CACHE_BSSID_LIST);
    }

    public void setCacheSsidList(String str) {
        setStringValue(CACHE_SSID_LIST, str);
    }

    public String getCacheSsidList() {
        return getStringValue(CACHE_SSID_LIST);
    }

    public String getUmengPushMac() {
        return getStringValue("CACHE_UMENG_PUSH_REGISTER_ID", "");
    }

    public void setUmengPushMac(String str) {
        setStringValue("CACHE_UMENG_PUSH_REGISTER_ID", str);
    }

    public String getUmengPushAppUuid() {
        return getStringValue(CACHE_UMENG_PUSH_APP_UUID, "");
    }

    public void setUmengPushAppUuid(String str) {
        setStringValue(CACHE_UMENG_PUSH_APP_UUID, str);
    }

    public String getUmengPushRegisterId() {
        return getStringValue("CACHE_UMENG_PUSH_REGISTER_ID", "");
    }

    public void setUmengPushRegisterId(String str) {
        setStringValue("CACHE_UMENG_PUSH_REGISTER_ID", str);
    }

    public String getUmengPushRegisterIdTemp() {
        return getStringValue(CACHE_UMENG_PUSH_REGISTER_ID_TEMP, "");
    }

    public void setCacheUmengPushRegisterIdTemp(String str) {
        setStringValue(CACHE_UMENG_PUSH_REGISTER_ID_TEMP, str);
    }

    public void setAppDownloadData(HashMap<String, List<RecommendAppListResponse.DataBean.AppListBean>> hashMap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(hashMap);
            String str = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
            objectOutputStream.close();
            setStringValue("CACHE_DOWNLOAD_APP_DATA", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, List<RecommendAppListResponse.DataBean.AppListBean>> getAppDownloadData() {
        String stringValue = getStringValue("CACHE_DOWNLOAD_APP_DATA", "");
        HashMap<String, List<RecommendAppListResponse.DataBean.AppListBean>> hashMap = new HashMap<>();
        if (stringValue == null || C3467s.m14513e(stringValue)) {
            return hashMap;
        }
        try {
            return (HashMap) new ObjectInputStream(new ByteArrayInputStream(Base64.decode(stringValue.getBytes(), 0))).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return hashMap;
        }
    }

    public void setReportAppNameDay(int i) {
        setIntValue("CACHE_REPORT_APP_NAME_DAY", i);
    }

    public int getReportAppNameDay() {
        return getIntValue("CACHE_REPORT_APP_NAME_DAY", -1);
    }

    public void setArcSoftFaceIsLibLoaded(boolean z) {
        setBooleanValue("CACHE_ARCSOFT_FACE_IS_LIB_LOADED", z);
        if (z) {
            setLongValue("CACHE_ARCSOFT_FACE_LIB_LOADED_TIME", System.currentTimeMillis());
        }
    }

    public boolean getArcSoftFaceIsLibLoaded() {
        boolean booleanValue = getBooleanValue("CACHE_ARCSOFT_FACE_IS_LIB_LOADED", false);
        if (!booleanValue) {
            return booleanValue;
        }
        long b = VersionUtil.m14566b();
        Log.m14398b(TAG, "CacheApp appLastUpdateTime=" + b);
        long longValue = getLongValue("CACHE_ARCSOFT_FACE_LIB_LOADED_TIME", 0);
        Log.m14398b(TAG, "CacheApp lastSetLibLoadedTime=" + longValue);
        if (longValue == 0 || b <= 0 || longValue < b) {
            return false;
        }
        return booleanValue;
    }

    public void setArcSoftFaceIsActive(boolean z) {
        setBooleanValue("CACHE_ARCSOFT_FACE_IS_ACTIVE", z);
    }

    public boolean getArcSoftFaceIsActive() {
        return getBooleanValue("CACHE_ARCSOFT_FACE_IS_ACTIVE", false);
    }

    public void setAppDptipShow(boolean z) {
        setBooleanValue("CACHE_APP_DPTIP_SHOW", z);
    }

    public boolean getAppDptipShow() {
        return getBooleanValue("CACHE_APP_DPTIP_SHOW", false);
    }

    public void setAppDptipContent(String str) {
        setStringValue("CACHE_APP_DPTIP_CONTENT", str);
    }

    public String getAppDptipContent() {
        return getStringValue("CACHE_APP_DPTIP_CONTENT", "");
    }

    public void setAppUuid(String str) {
        setStringValue("CACHE_APP_UUID", str);
    }

    public String getAppUuid() {
        String stringValue = getStringValue("CACHE_APP_UUID", "");
        if (!C3467s.m14513e(stringValue)) {
            return stringValue;
        }
        String uuid = UUID.randomUUID().toString();
        String str = uuid.substring(0, 23) + uuid.substring(24);
        setAppUuid(str);
        return str;
    }

    public boolean getAgreementYes() {
        return getBooleanValue("CACHE_AGREEMENT_YES", false);
    }

    public void setAgreementYes(boolean z) {
        setBooleanValue("CACHE_AGREEMENT_YES", z);
    }

    public long getAgreementTime() {
        return getLongValue("CACHE_AGREEMENT_TIME", 0);
    }

    public void setAgreementTime(long j) {
        setLongValue("CACHE_AGREEMENT_TIME", j);
    }

    public long getAgrtServiceUpdateTime() {
        return getLongValue("CACHE_AGRT_SERVICE_UPDATE_TIME", 0);
    }

    public void setAgrtServiceUpdateTime(long j) {
        setLongValue("CACHE_AGRT_SERVICE_UPDATE_TIME", j);
    }

    public long getAgrtPrivacyUpdateTime() {
        return getLongValue("CACHE_AGRT_PRIVACY_UPDATE_TIME", 0);
    }

    public void setAgrtPrivacyUpdateTime(long j) {
        getLongValue("CACHE_AGRT_PRIVACY_UPDATE_TIME", j);
    }
}
