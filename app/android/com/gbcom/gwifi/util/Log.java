package com.gbcom.gwifi.util;

import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;

/* renamed from: com.gbcom.gwifi.util.k */
public class Log {

    /* renamed from: a */
    public static final int f13443a = 2;

    /* renamed from: b */
    public static final int f13444b = 3;

    /* renamed from: c */
    public static final int f13445c = 4;

    /* renamed from: d */
    public static final int f13446d = 5;

    /* renamed from: e */
    public static final int f13447e = 6;

    /* renamed from: f */
    private static String f13448f = "GBCOM_LOG";

    /* renamed from: a */
    public static void m14393a(int i, String str) {
        if (i >= Config.m14094a().mo27807j()) {
            GiwifiMobclickAgent.reportError(GBApplication.instance(), str);
        }
    }

    /* renamed from: a */
    public static boolean m14397a(int i) {
        if (i < Config.m14094a().mo27807j()) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static void m14394a(String str) {
        m14395a(f13448f, str);
    }

    /* renamed from: a */
    public static void m14395a(String str, String str2) {
        if (2 >= Config.m14094a().mo27807j()) {
            android.util.Log.v(str, str2);
        }
    }

    /* renamed from: a */
    public static void m14396a(String str, String str2, Throwable th) {
        if (2 >= Config.m14094a().mo27807j()) {
            android.util.Log.v(str, str2, th);
        }
    }

    /* renamed from: b */
    public static void m14398b(String str, String str2) {
        if (3 >= Config.m14094a().mo27807j()) {
            android.util.Log.d(str, str2);
        }
        m14393a(3, str2);
    }

    /* renamed from: c */
    public static void m14400c(String str, String str2) {
        if (4 >= Config.m14094a().mo27807j()) {
            android.util.Log.i(str, str2);
        }
    }

    /* renamed from: b */
    public static void m14399b(String str, String str2, Throwable th) {
        if (4 >= Config.m14094a().mo27807j()) {
            android.util.Log.i(str, str2, th);
        }
    }

    /* renamed from: d */
    public static void m14402d(String str, String str2) {
        if (5 >= Config.m14094a().mo27807j()) {
            android.util.Log.w(str, str2);
        }
        m14393a(5, str2);
    }

    /* renamed from: c */
    public static void m14401c(String str, String str2, Throwable th) {
        if (5 >= Config.m14094a().mo27807j()) {
            android.util.Log.w(str, str2, th);
        }
    }

    /* renamed from: e */
    public static void m14403e(String str, String str2) {
        if (6 >= Config.m14094a().mo27807j()) {
            android.util.Log.e(str, str2);
        }
        m14393a(6, str2);
    }
}
