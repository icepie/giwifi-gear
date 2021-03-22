package com.gbcom.gwifi.util.cache;

import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.util.JsonUtil;
import java.util.HashMap;

public class CacheBase {
    protected static final HashMap<String, Object> cacheData = new HashMap<>();

    /* access modifiers changed from: protected */
    public void setStringValue(String str, String str2) {
        cacheData.put(str, str2);
        GBApplication.instance().getSharedPreferences().edit().putString(str, str2).commit();
    }

    /* access modifiers changed from: protected */
    public String getStringValue(String str, String str2) {
        if (cacheData.containsKey(str)) {
            return (String) cacheData.get(str);
        }
        String string = GBApplication.instance().getSharedPreferences().getString(str, str2);
        cacheData.put(str, string);
        return string;
    }

    /* access modifiers changed from: protected */
    public String getStringValue(String str) {
        return getStringValue(str, "");
    }

    /* access modifiers changed from: protected */
    public void setLongValue(String str, long j) {
        cacheData.put(str, Long.valueOf(j));
        GBApplication.instance().getSharedPreferences().edit().putLong(str, j).commit();
    }

    /* access modifiers changed from: protected */
    public long getLongValue(String str, long j) {
        if (cacheData.containsKey(str)) {
            return ((Long) cacheData.get(str)).longValue();
        }
        Long valueOf = Long.valueOf(GBApplication.instance().getSharedPreferences().getLong(str, j));
        cacheData.put(str, valueOf);
        return valueOf.longValue();
    }

    /* access modifiers changed from: protected */
    public long getLongValue(String str) {
        return getLongValue(str, 0);
    }

    /* access modifiers changed from: protected */
    public void setIntValue(String str, int i) {
        cacheData.put(str, Integer.valueOf(i));
        GBApplication.instance().getSharedPreferences().edit().putInt(str, i).commit();
    }

    /* access modifiers changed from: protected */
    public int getIntValue(String str, int i) {
        if (cacheData.containsKey(str)) {
            return ((Integer) cacheData.get(str)).intValue();
        }
        int i2 = GBApplication.instance().getSharedPreferences().getInt(str, i);
        cacheData.put(str, Integer.valueOf(i2));
        return i2;
    }

    /* access modifiers changed from: protected */
    public int getIntValue(String str) {
        return getIntValue(str, 0);
    }

    /* access modifiers changed from: protected */
    public void setBooleanValue(String str, boolean z) {
        cacheData.put(str, Boolean.valueOf(z));
        GBApplication.instance().getSharedPreferences().edit().putBoolean(str, z).commit();
    }

    /* access modifiers changed from: protected */
    public boolean getBooleanValue(String str, boolean z) {
        if (cacheData.containsKey(str)) {
            return ((Boolean) cacheData.get(str)).booleanValue();
        }
        boolean z2 = GBApplication.instance().getSharedPreferences().getBoolean(str, z);
        cacheData.put(str, Boolean.valueOf(z2));
        return z2;
    }

    /* access modifiers changed from: protected */
    public boolean getBooleanValue(String str) {
        return getBooleanValue(str, false);
    }

    /* access modifiers changed from: protected */
    public void setObjectHashMapValue(String str, HashMap<String, Object> hashMap) {
        cacheData.put(str, hashMap);
        GBApplication.instance().getSharedPreferences().edit().putString(str, JsonUtil.m14388a(hashMap)).commit();
    }

    /* access modifiers changed from: protected */
    public void setIntHashMapValue(String str, HashMap<String, Integer> hashMap) {
        cacheData.put(str, hashMap);
        GBApplication.instance().getSharedPreferences().edit().putString(str, JsonUtil.m14387a((Object) hashMap)).commit();
    }

    /* access modifiers changed from: protected */
    public HashMap getHashMapValue(String str) {
        if (cacheData.containsKey(str)) {
            return (HashMap) cacheData.get(str);
        }
        String string = GBApplication.instance().getSharedPreferences().getString(str, "");
        HashMap hashMap = null;
        if (!string.equals("")) {
            hashMap = (HashMap) JsonUtil.m14386a(string, HashMap.class);
        }
        cacheData.put(str, hashMap);
        return hashMap;
    }
}
