package com.gbcom.gwifi.util.p257b;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.gbcom.gwifi.util.C3467s;
import org.android.agoo.common.AgooConstants;

/* renamed from: com.gbcom.gwifi.util.b.d */
public class ScreenUtil {
    /* renamed from: a */
    public static C3452a m14170a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return new C3452a(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    /* renamed from: b */
    public static int m14174b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /* renamed from: c */
    public static int m14176c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /* renamed from: com.gbcom.gwifi.util.b.d$a */
    /* compiled from: ScreenUtil */
    public static class C3452a {

        /* renamed from: a */
        public int f13142a;

        /* renamed from: b */
        public int f13143b;

        public C3452a() {
        }

        public C3452a(int i, int i2) {
            this.f13142a = i;
            this.f13143b = i2;
        }
    }

    /* renamed from: d */
    public static float m14178d(Context context) {
        new DisplayMetrics();
        return context.getResources().getDisplayMetrics().density;
    }

    /* renamed from: a */
    public static int m14169a(Context context, float f) {
        return (int) ((m14178d(context) * f) + 0.5f);
    }

    /* renamed from: b */
    public static int m14175b(Context context, float f) {
        return (int) ((f / m14178d(context)) + 0.5f);
    }

    /* renamed from: a */
    public static int m14168a() {
        return Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", DispatchConstants.ANDROID));
    }

    /* renamed from: a */
    public static int[] m14172a(Activity activity) {
        int[] iArr = new int[2];
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            iArr[0] = displayMetrics.widthPixels;
            iArr[1] = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iArr;
    }

    /* renamed from: b */
    public static int m14173b(Activity activity) {
        int i = m14172a(activity)[1];
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return i - displayMetrics.heightPixels;
    }

    /* renamed from: a */
    public static void m14171a(ListView listView) {
        ListAdapter adapter;
        if (!(listView == null || (adapter = listView.getAdapter()) == null)) {
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                View view = adapter.getView(i2, null, listView);
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
            ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = (listView.getDividerHeight() * (adapter.getCount() - 1)) + i;
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + layoutParams.height);
            listView.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: c */
    public static boolean m14177c(Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            DisplayCutout displayCutout = null;
            if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT >= 28) {
                displayCutout = activity.getWindow().getDecorView().getRootWindowInsets().getDisplayCutout();
            }
            if (displayCutout != null) {
                return true;
            }
            return false;
        }
        String str = Build.MANUFACTURER;
        if (C3467s.m14513e(str)) {
            return false;
        }
        if (str.equalsIgnoreCase("HUAWEI")) {
            return m14182g(activity);
        }
        if (str.equalsIgnoreCase("xiaomi")) {
            return m14181f(activity);
        }
        if (str.equalsIgnoreCase(AgooConstants.MESSAGE_SYSTEM_SOURCE_OPPO)) {
            return m14180e(activity);
        }
        if (str.equalsIgnoreCase(AgooConstants.MESSAGE_SYSTEM_SOURCE_VIVO)) {
            return m14179d(activity);
        }
        return false;
    }

    /* renamed from: d */
    private static boolean m14179d(Activity activity) {
        try {
            Class<?> cls = Class.forName("android.util.FtFeature");
            return ((Boolean) cls.getMethod("isFeatureSupport", Integer.TYPE).invoke(cls, 32)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: e */
    private static boolean m14180e(Activity activity) {
        return activity.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /* renamed from: f */
    private static boolean m14181f(Activity activity) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Integer) cls.getMethod("getInt", new Class[]{String.class, Integer.TYPE}).invoke(cls, new Object[]{"ro.miui.notch", 0})).intValue() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: g */
    private static boolean m14182g(Activity activity) {
        try {
            Class<?> loadClass = activity.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }
}
