package com.gbcom.gwifi.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.gbcom.gwifi.base.app.GBApplication;

/* renamed from: com.gbcom.gwifi.util.w */
public class VersionUtil {

    /* renamed from: a */
    private static final String f13495a = "VersionUtil";

    /* renamed from: a */
    public static String m14565a() {
        PackageInfo b = m14567b(GBApplication.instance());
        if (b == null) {
            return "";
        }
        return b.versionName;
    }

    /* renamed from: a */
    public static int m14564a(Context context) {
        PackageInfo b = m14567b(GBApplication.instance());
        if (b == null) {
            return -1;
        }
        return b.versionCode;
    }

    /* renamed from: b */
    private static PackageInfo m14567b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            Log.m14403e(f13495a, "VersionUtil getPackageInfo():" + e.toString());
            return null;
        }
    }

    /* renamed from: b */
    public static long m14566b() {
        PackageInfo b = m14567b(GBApplication.instance());
        if (b == null) {
            return -1;
        }
        return b.lastUpdateTime;
    }
}
