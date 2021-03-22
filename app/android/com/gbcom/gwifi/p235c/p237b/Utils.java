package com.gbcom.gwifi.p235c.p237b;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

/* renamed from: com.gbcom.gwifi.c.b.b */
public class Utils {

    /* renamed from: a */
    private static final String f9144a = "Utils";

    /* renamed from: a */
    public static double m10793a() {
        try {
            String a = m10794a("ro.build.version.emui");
            return Double.parseDouble(a.substring(a.indexOf("_") + 1));
        } catch (Exception e) {
            e.printStackTrace();
            return 4.0d;
        }
    }

    /* renamed from: b */
    public static int m10796b() {
        String a = m10794a("ro.miui.ui.version.name");
        if (a != null) {
            try {
                return Integer.parseInt(a.substring(1));
            } catch (Exception e) {
                Log.e(f9144a, "get miui version code error, version : " + a);
            }
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x005e A[SYNTHETIC, Splitter:B:16:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006f A[SYNTHETIC, Splitter:B:23:0x006f] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m10794a(java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 129
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.p235c.p237b.Utils.m10794a(java.lang.String):java.lang.String");
    }

    /* renamed from: c */
    public static boolean m10797c() {
        return Build.MANUFACTURER.contains("HUAWEI");
    }

    /* renamed from: d */
    public static boolean m10798d() {
        return !TextUtils.isEmpty(m10794a("ro.miui.ui.version.name"));
    }

    /* renamed from: e */
    public static boolean m10799e() {
        String a = m10794a("ro.build.display.id");
        if (TextUtils.isEmpty(a)) {
            return false;
        }
        if (a.contains("flyme") || a.toLowerCase().contains("flyme")) {
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public static boolean m10800f() {
        return Build.MANUFACTURER.contains("QiKU");
    }

    /* renamed from: a */
    public static boolean m10795a(Context context, Intent intent) {
        if (intent != null && context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
            return true;
        }
        return false;
    }
}
