package com.gbcom.gwifi.util;

import android.support.p009v4.view.MotionEventCompat;
import java.util.regex.Pattern;
import org.apache.xalan.templates.Constants;

/* renamed from: com.gbcom.gwifi.util.i */
public class Ipv4Util {

    /* renamed from: a */
    public static final int f13429a = 1;

    /* renamed from: b */
    public static final int f13430b = 2;

    /* renamed from: c */
    public static final int f13431c = 3;

    /* renamed from: d */
    public static final int f13432d = 4;

    /* renamed from: e */
    private static final String f13433e = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})";

    /* renamed from: g */
    private static int[] f13434g = new int[2];

    /* renamed from: h */
    private static int[] f13435h = new int[2];

    /* renamed from: i */
    private static int[] f13436i = new int[2];

    /* renamed from: j */
    private static int f13437j = m14381f("255.0.0.0");

    /* renamed from: k */
    private static int f13438k = m14381f("255.255.0.0");

    /* renamed from: l */
    private static int f13439l = m14381f("255.255.255.0");

    /* renamed from: f */
    private int f13440f;

    static {
        f13434g[0] = m14381f("1.0.0.1");
        f13434g[1] = m14381f("126.255.255.254");
        f13435h[0] = m14381f("128.0.0.1");
        f13435h[1] = m14381f("191.255.255.254");
        f13436i[0] = m14381f("192.168.0.0");
        f13436i[1] = m14381f("192.168.255.255");
    }

    public Ipv4Util() {
        this.f13440f = m14381f("255.255.255.0");
    }

    public Ipv4Util(String str) {
        this.f13440f = m14381f(str);
        if (this.f13440f == 0) {
            throw new UnknownError();
        }
    }

    /* renamed from: a */
    public int mo28151a() {
        return this.f13440f;
    }

    /* renamed from: a */
    public boolean mo28153a(String str, String str2) {
        return m14373a(str, str2, this.f13440f);
    }

    /* renamed from: a */
    public static boolean m14373a(String str, String str2, int i) {
        if (!m14374b(str) || !m14374b(str2)) {
            return false;
        }
        if ((m14381f(str) & i) == (m14381f(str2) & i)) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m14375b(String str, String str2) {
        return m14373a(str, str2, m14378d(str));
    }

    /* renamed from: a */
    public int mo28152a(String str) {
        return m14381f(str) & this.f13440f;
    }

    /* renamed from: a */
    public static int m14370a(String str, int i) {
        return m14381f(str) & i;
    }

    /* renamed from: b */
    public static boolean m14374b(String str) {
        return m14379d(str, f13433e);
    }

    /* renamed from: d */
    private static boolean m14379d(String str, String str2) {
        if (str == null) {
            return false;
        }
        return Pattern.matches(str2, str.trim());
    }

    /* renamed from: c */
    public static int m14377c(String str, String str2) {
        int f = m14381f(str);
        int f2 = m14381f(str2);
        if (f > f2) {
            return -1;
        }
        if (f <= f2) {
            return 1;
        }
        return 0;
    }

    /* renamed from: c */
    public static int m14376c(String str) {
        int f = m14381f(str);
        if (f >= f13436i[0] && f <= f13436i[1]) {
            return 3;
        }
        if (f >= f13435h[0] && f <= f13435h[1]) {
            return 2;
        }
        if (f < f13434g[0] || f > f13434g[1]) {
            return 4;
        }
        return 1;
    }

    /* renamed from: d */
    public static int m14378d(String str) {
        switch (m14376c(str)) {
            case 1:
                return f13437j;
            case 2:
                return f13438k;
            case 3:
                return f13439l;
            default:
                return f13439l;
        }
    }

    /* renamed from: e */
    public static String m14380e(String str) {
        return m14371a(m14378d(str));
    }

    /* renamed from: a */
    public static String m14371a(int i) {
        return ((i >> 24) & 255) + Constants.ATTRVAL_THIS + ((i >> 16) & 255) + Constants.ATTRVAL_THIS + ((i >> 8) & 255) + Constants.ATTRVAL_THIS + (i & 255);
    }

    /* renamed from: a */
    public static String m14372a(byte[] bArr) {
        return (bArr[0] & 255) + Constants.ATTRVAL_THIS + (bArr[1] & 255) + Constants.ATTRVAL_THIS + (bArr[2] & 255) + Constants.ATTRVAL_THIS + (bArr[3] & 255);
    }

    /* renamed from: f */
    public static int m14381f(String str) {
        byte[] g = m14382g(str);
        return ((g[0] << 24) & -16777216) | (g[3] & 255) | ((g[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((g[1] << 16) & 16711680);
    }

    /* renamed from: g */
    public static byte[] m14382g(String str) {
        try {
            String[] split = str.split("\\.");
            int length = split.length;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) (Integer.parseInt(split[i]) & 255);
            }
            return bArr;
        } catch (Exception e) {
            return new byte[4];
        }
    }
}
