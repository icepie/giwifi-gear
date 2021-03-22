package com.gbcom.gwifi.util;

import com.alipay.sdk.p116j.C1536i;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.regex.Pattern;

/* renamed from: com.gbcom.gwifi.util.a */
public class CheckUtil {
    /* renamed from: a */
    public static boolean m14083a(String str) {
        if (str.length() < 7 || str.length() > 15 || "".equals(str)) {
            return false;
        }
        return Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}").matcher(str).find();
    }

    /* renamed from: b */
    public static boolean m14084b(String str) {
        if (C3467s.m14513e(str)) {
            return false;
        }
        return Pattern.compile("^[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}$").matcher(str).find();
    }

    /* renamed from: c */
    public static String m14085c(String str) {
        return Pattern.compile("[^0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ&!@#$^*()_+-=?/|\\\\]").matcher(str).replaceAll("").replaceAll("\\.", "").replaceAll(",", "").replaceAll(SimpleComparison.LESS_THAN_OPERATION, "").replaceAll(C1536i.f3510b, "").replaceAll(":", "").trim();
    }

    /* renamed from: d */
    public static boolean m14086d(String str) {
        if (str.length() != 11) {
            return false;
        }
        return Pattern.compile("^1[123456789]\\d{9}$").matcher(str).matches();
    }

    /* renamed from: a */
    public static boolean m14082a(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }
}
