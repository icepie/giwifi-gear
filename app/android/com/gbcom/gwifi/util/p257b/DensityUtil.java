package com.gbcom.gwifi.util.p257b;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/* renamed from: com.gbcom.gwifi.util.b.b */
public class DensityUtil {

    /* renamed from: a */
    private static int f13139a = 0;

    /* renamed from: b */
    private static int f13140b = 0;

    /* renamed from: c */
    private static float f13141c = 0.0f;

    /* renamed from: a */
    public static int m14128a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    /* renamed from: b */
    public static int m14131b(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().scaledDensity * f) + 0.5f);
    }

    /* renamed from: c */
    public static int m14133c(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    /* renamed from: d */
    public static int m14135d(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: a */
    public static int m14129a(Resources resources, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, resources.getDisplayMetrics());
    }

    /* renamed from: a */
    public static int m14127a(Context context) {
        if (f13139a != 0) {
            return f13139a;
        }
        m14136e(context);
        return f13139a;
    }

    /* renamed from: b */
    public static int m14130b(Context context) {
        if (f13140b != 0) {
            return f13140b;
        }
        m14136e(context);
        return f13140b;
    }

    /* renamed from: e */
    private static void m14136e(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        f13139a = displayMetrics.widthPixels;
        f13140b = displayMetrics.heightPixels;
        f13141c = displayMetrics.density;
    }

    /* renamed from: c */
    public static float m14132c(Context context) {
        if (f13141c != 0.0f) {
            return f13141c;
        }
        m14136e(context);
        return f13141c;
    }

    /* renamed from: d */
    public static int m14134d(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            int parseInt = Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString());
            if (context.getResources().getDimensionPixelOffset(parseInt) != 0) {
                return context.getResources().getDimensionPixelOffset(parseInt);
            }
            return 38;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
