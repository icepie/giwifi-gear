package com.gbcom.gwifi.util.cache;

import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.SystemUtil;

public class CacheSystem extends CacheBase {
    private static CacheSystem instance = null;
    private final String CACHE_SYSTEM_ANDROIDID = "CACHE_SYSTEM_ANDROIDID";
    private final String CACHE_SYSTEM_CPU = "CACHE_SYSTEM_CPU";
    private final String CACHE_SYSTEM_IMEI = "CACHE_SYSTEM_IMEI";

    public static CacheSystem getInstance() {
        if (instance == null) {
            instance = new CacheSystem();
        }
        return instance;
    }

    public void setSystemImei(String str) {
        setStringValue("CACHE_SYSTEM_IMEI", str);
    }

    public String getSystemImei() {
        String stringValue = getStringValue("CACHE_SYSTEM_IMEI", "");
        if (!C3467s.m14513e(stringValue)) {
            return stringValue;
        }
        String c = SystemUtil.m14527c(GBApplication.instance().getApplicationContext());
        if (C3467s.m14513e(c)) {
            return "";
        }
        setStringValue("CACHE_SYSTEM_IMEI", c);
        return c;
    }

    public void setSystemCpu(String str) {
        setStringValue("CACHE_SYSTEM_CPU", str);
    }

    public String getSystemCpu() {
        String stringValue = getStringValue("CACHE_SYSTEM_CPU", "");
        if (!C3467s.m14513e(stringValue)) {
            return stringValue;
        }
        String a = SystemUtil.m14520a("getprop ro.product.cpu.abi");
        if (C3467s.m14513e(a)) {
            return "";
        }
        setStringValue("CACHE_SYSTEM_CPU", a);
        return a;
    }
}
