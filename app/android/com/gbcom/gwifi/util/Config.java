package com.gbcom.gwifi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheBase;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.HashMap;

/* renamed from: com.gbcom.gwifi.util.b */
public class Config extends CacheBase {

    /* renamed from: A */
    private static int f13108A = 0;

    /* renamed from: B */
    private static boolean f13109B = false;

    /* renamed from: C */
    private static boolean f13110C = false;

    /* renamed from: D */
    private static Config f13111D = null;

    /* renamed from: a */
    public static final String f13112a = "WX_APPKEY";

    /* renamed from: b */
    public static final String f13113b = "WX_PAY_PARTNERID";

    /* renamed from: c */
    public static final String f13114c = "WX_PAY_KEY";

    /* renamed from: g */
    private static final String f13115g = "Config";

    /* renamed from: h */
    private static final String f13116h = "WOCLOUD_URL_PREFIX";

    /* renamed from: i */
    private static final String f13117i = "WOCDN_URL_PREFIX";

    /* renamed from: j */
    private static final String f13118j = "CHARGE_SERVER_URL";

    /* renamed from: k */
    private static final String f13119k = "RESOURCE_URL_PREFIX";

    /* renamed from: l */
    private static final String f13120l = "CHECK_NETWORK_URL";

    /* renamed from: m */
    private static final String f13121m = "HOT_BOOK_URL";

    /* renamed from: n */
    private static final String f13122n = "STATION_CLOUD_URL";

    /* renamed from: o */
    private static final String f13123o = "YOU_MI_APP_ID";

    /* renamed from: p */
    private static final String f13124p = "YOU_MI_APP_SECRET";

    /* renamed from: q */
    private static final String f13125q = "HX_APPKEY";

    /* renamed from: r */
    private static final String f13126r = "HX_CLIENT_SERVICE";

    /* renamed from: s */
    private static final String f13127s = "UMENG_APPKEY";

    /* renamed from: t */
    private static final String f13128t = "UMENG_MESSAGE_SECRET";

    /* renamed from: y */
    private static final String f13129y = "circle_tab_contains";

    /* renamed from: z */
    private static String f13130z = "";

    /* renamed from: d */
    HashMap<String, String> f13131d = new HashMap<>();

    /* renamed from: e */
    boolean f13132e = false;

    /* renamed from: f */
    String f13133f = null;

    /* renamed from: u */
    private final String f13134u = "app_log_level";

    /* renamed from: v */
    private final String f13135v = "auto_auth";

    /* renamed from: w */
    private final String f13136w = "run_Model";

    /* renamed from: x */
    private final String f13137x = "DEVELOP_MODE";

    Config() {
        Context applicationContext = GBApplication.instance().getApplicationContext();
        try {
            PackageManager packageManager = applicationContext.getPackageManager();
            String packageName = applicationContext.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            f13130z = packageInfo.versionName;
            f13108A = packageInfo.versionCode;
            this.f13131d.clear();
            this.f13131d.put("2.9.9.", "10.199.238.1");
            if (f13130z.startsWith("2.9.9.")) {
                this.f13132e = true;
                this.f13133f = "10.199.238.1";
            }
            f13109B = false;
            if (!packageName.contains("school")) {
                f13110C = true;
                return;
            }
            f13110C = false;
            if (TextUtils.isEmpty(f13130z)) {
                return;
            }
            if (f13130z.startsWith("2.1.1.")) {
                f13109B = true;
            } else {
                f13109B = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static Config m14094a() {
        if (f13111D == null) {
            f13111D = new Config();
        }
        return f13111D;
    }

    /* renamed from: b */
    public static void m14098b() {
        GBApplication.instance().getSharedPreferences().edit().putString(f13116h, "").putString(f13117i, "").putString(f13119k, "").putString(f13120l, "").putString(f13118j, "").putString(f13122n, "").putString(f13121m, "").putString(f13123o, "").putString(f13124p, "").putString(f13125q, "").putString(f13126r, "").putString(f13127s, "").putString(f13128t, "").putString(f13112a, "").putString(f13113b, "").putString(f13114c, "").putString("iemi", "").commit();
        cacheData.clear();
    }

    /* renamed from: a */
    public static void m14097a(String str) {
        String[] split = str.split("\n");
        SharedPreferences.Editor edit = GBApplication.instance().getSharedPreferences().edit();
        for (String str2 : split) {
            String trim = str2.split(SimpleComparison.EQUAL_TO_OPERATION)[1].trim();
            if (str2.contains(f13116h)) {
                edit.putString(f13116h, trim);
                cacheData.put(f13116h, trim);
            } else if (str2.contains(f13117i)) {
                edit.putString(f13117i, trim);
                cacheData.put(f13117i, trim);
            } else if (str2.contains(f13119k)) {
                edit.putString(f13119k, trim);
                cacheData.put(f13119k, trim);
            } else if (str2.contains(f13120l)) {
                edit.putString(f13120l, trim);
                cacheData.put(f13120l, trim);
            } else if (str2.contains(f13118j)) {
                edit.putString(f13118j, trim);
                cacheData.put(f13118j, trim);
            } else if (str2.contains(f13122n)) {
                edit.putString(f13122n, trim);
                cacheData.put(f13122n, trim);
            } else if (str2.contains(f13121m)) {
                edit.putString(f13121m, trim);
                cacheData.put(f13121m, trim);
            } else if (str2.contains(f13123o)) {
                edit.putString(f13123o, trim);
                cacheData.put(f13123o, trim);
            } else if (str2.contains(f13124p)) {
                edit.putString(f13124p, trim);
                cacheData.put(f13124p, trim);
            } else if (str2.contains(f13125q)) {
                edit.putString(f13125q, trim);
                cacheData.put(f13125q, trim);
            } else if (str2.contains(f13126r)) {
                edit.putString(f13126r, trim);
                cacheData.put(f13126r, trim);
            } else if (str2.contains(f13127s)) {
                edit.putString(f13127s, trim);
                cacheData.put(f13127s, trim);
            } else if (str2.contains(f13128t)) {
                edit.putString(f13128t, trim);
                cacheData.put(f13128t, trim);
            } else if (str2.contains(f13112a)) {
                edit.putString(f13112a, trim).commit();
                cacheData.put(f13112a, trim);
            } else if (str2.contains(f13113b)) {
                edit.putString(f13113b, trim).commit();
                cacheData.put(f13113b, trim);
            } else if (str2.contains(f13114c)) {
                edit.putString(f13114c, trim).commit();
                cacheData.put(f13114c, trim);
            }
        }
        edit.commit();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0054 A[SYNTHETIC, Splitter:B:18:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0059 A[Catch:{ IOException -> 0x00a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b1 A[SYNTHETIC, Splitter:B:37:0x00b1] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b6 A[Catch:{ IOException -> 0x00ba }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m14095a(android.content.Context r7) {
        /*
        // Method dump skipped, instructions count: 205
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.util.Config.m14095a(android.content.Context):java.lang.String");
    }

    /* renamed from: a */
    public static String m14096a(Context context, String str) {
        if (cacheData.containsKey(str)) {
            return (String) cacheData.get(str);
        }
        String string = GBApplication.instance().getSharedPreferences().getString(str, "");
        if (C3467s.m14513e(string)) {
            Log.m14400c("Config", "load file start...");
            String a = m14095a(GBApplication.instance());
            Log.m14400c("Config", "load file " + a);
            m14097a(a);
        } else {
            cacheData.put(str, string);
        }
        return (String) cacheData.get(str);
    }

    /* renamed from: c */
    public String mo27800c() {
        return m14096a(GBApplication.instance(), f13125q);
    }

    /* renamed from: d */
    public String mo27801d() {
        return m14096a(GBApplication.instance(), f13126r);
    }

    /* renamed from: e */
    public String mo27802e() {
        Context applicationContext = GBApplication.instance().getApplicationContext();
        if (CacheAuth.getInstance().getStationCloud().equals("test.gwifi.com.cn")) {
            return applicationContext.getResources().getString(C2425R.C2429string.umengAppKey4Test);
        }
        return applicationContext.getResources().getString(C2425R.C2429string.umengAppKey);
    }

    /* renamed from: f */
    public String mo27803f() {
        Context applicationContext = GBApplication.instance().getApplicationContext();
        if (CacheAuth.getInstance().getStationCloud().equals("test.gwifi.com.cn")) {
            return applicationContext.getResources().getString(C2425R.C2429string.umengMessageSecret4Test);
        }
        return applicationContext.getResources().getString(C2425R.C2429string.umengMessageSecret);
    }

    /* renamed from: g */
    public String mo27804g() {
        return GBApplication.instance().getApplicationContext().getResources().getString(C2425R.C2429string.weixinAppId);
    }

    /* renamed from: h */
    public String mo27805h() {
        return m14096a(GBApplication.instance(), f13114c);
    }

    /* renamed from: i */
    public String mo27806i() {
        if (cacheData.get(f13119k) != null) {
            return (String) cacheData.get(f13119k);
        }
        String a = m14096a(GBApplication.instance(), f13119k);
        cacheData.put(f13119k, a);
        return a;
    }

    /* renamed from: a */
    public void mo27795a(int i) {
        setIntValue("app_log_level", i);
    }

    /* renamed from: j */
    public int mo27807j() {
        return getIntValue("app_log_level", 5);
    }

    /* renamed from: a */
    public void mo27797a(boolean z) {
        setBooleanValue("auto_auth", z);
    }

    /* renamed from: k */
    public boolean mo27808k() {
        return getBooleanValue("auto_auth");
    }

    /* renamed from: a */
    public void mo27796a(Integer num) {
        setIntValue("run_Model", num.intValue());
    }

    /* renamed from: l */
    public Integer mo27809l() {
        return Integer.valueOf(getIntValue("run_Model", 1));
    }

    /* renamed from: b */
    public void mo27798b(int i) {
        setIntValue("DEVELOP_MODE", i);
    }

    /* renamed from: m */
    public int mo27810m() {
        return getIntValue("DEVELOP_MODE", 0);
    }

    /* renamed from: b */
    public void mo27799b(boolean z) {
        setBooleanValue(f13129y, z);
    }

    /* renamed from: n */
    public boolean mo27811n() {
        return getBooleanValue(f13129y);
    }

    /* renamed from: o */
    public boolean mo27812o() {
        return f13110C;
    }

    /* renamed from: p */
    public boolean mo27813p() {
        return this.f13132e;
    }

    /* renamed from: q */
    public String mo27814q() {
        return this.f13133f;
    }
}
