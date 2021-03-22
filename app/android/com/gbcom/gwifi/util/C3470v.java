package com.gbcom.gwifi.util;

import android.content.Context;
import android.widget.Toast;

/* renamed from: com.gbcom.gwifi.util.v */
/* compiled from: Utils */
public class C3470v {

    /* renamed from: a */
    private static long f13491a = 0;

    /* renamed from: b */
    private static long f13492b = 0;

    /* renamed from: c */
    private static String f13493c;

    /* renamed from: d */
    private static Toast f13494d;

    /* renamed from: a */
    public static void m14563a(Context context, String str) {
        if (f13494d == null) {
            f13494d = Toast.makeText(context, str, 0);
            f13494d.show();
            f13491a = System.currentTimeMillis();
        } else {
            f13492b = System.currentTimeMillis();
            if (!str.equals(f13493c)) {
                f13493c = str;
                f13494d.setText(str);
                f13494d.show();
            } else if (f13492b - f13491a > 0) {
                f13494d.show();
            }
        }
        f13491a = f13492b;
    }
}
