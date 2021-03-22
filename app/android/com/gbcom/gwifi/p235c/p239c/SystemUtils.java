package com.gbcom.gwifi.p235c.p239c;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import anet.channel.strategy.dispatch.DispatchConstants;

/* renamed from: com.gbcom.gwifi.c.c.a */
public class SystemUtils {

    /* renamed from: a */
    private static int f9145a = 0;

    /* renamed from: b */
    private static int f9146b = 0;

    /* renamed from: c */
    private static int f9147c = 0;

    private SystemUtils() {
    }

    /* renamed from: a */
    public static int m10801a(Context context) {
        if (f9146b > 0) {
            return f9146b;
        }
        if (context == null) {
            return 0;
        }
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /* renamed from: a */
    public static int m10803a(Context context, boolean z) {
        if (context == null) {
            return 0;
        }
        int b = m10805b(context);
        return !z ? b - m10808d(context) : b;
    }

    /* renamed from: b */
    public static int m10805b(Context context) {
        if (f9145a > 0) {
            return f9145a;
        }
        if (context == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = m10807c(context).getDefaultDisplay();
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealMetrics(displayMetrics);
            } else {
                defaultDisplay.getMetrics(displayMetrics);
            }
            f9145a = displayMetrics.heightPixels;
        } catch (Exception e) {
            f9145a = defaultDisplay.getHeight();
        }
        return f9145a;
    }

    /* renamed from: c */
    public static WindowManager m10807c(Context context) {
        if (context == null) {
            return null;
        }
        return (WindowManager) context.getSystemService("window");
    }

    /* renamed from: d */
    public static int m10808d(Context context) {
        if (!m10809e(context)) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", DispatchConstants.ANDROID));
    }

    /* renamed from: e */
    public static boolean m10809e(Context context) {
        boolean z = true;
        if (Build.VERSION.SDK_INT >= 17) {
            Display defaultDisplay = m10807c(context).getDefaultDisplay();
            Point point = new Point();
            Point point2 = new Point();
            defaultDisplay.getSize(point);
            defaultDisplay.getRealSize(point2);
            return (point2.x == point.x && point2.y == point.y) ? false : true;
        }
        boolean hasPermanentMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        if (hasPermanentMenuKey || deviceHasKey) {
            z = false;
        }
        return z;
    }

    /* renamed from: a */
    public static int m10802a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    /* renamed from: b */
    public static float m10804b(Context context, float f) {
        return TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }

    /* renamed from: c */
    public static int m10806c(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: f */
    public static int m10810f(Context context) {
        if (f9147c > 0) {
            return f9147c;
        }
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            f9147c = context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            f9147c = 0;
        }
        return f9147c;
    }

    /* renamed from: g */
    public static int m10811g(Context context) {
        int f = m10810f(context);
        if (f == 0) {
            return m10802a(context, 30.0f);
        }
        return f;
    }
}
