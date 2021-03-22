package com.gbcom.gwifi.util.p257b;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.p009v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import anet.channel.strategy.dispatch.DispatchConstants;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.p456io.File;
import java.p456io.FileInputStream;
import java.util.Properties;

/* renamed from: com.gbcom.gwifi.util.b.e */
public class StatusBarUtil {

    /* renamed from: a */
    private static final String f13144a = "ro.miui.ui.version.code";

    /* renamed from: a */
    public static int m14183a(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (m14188a(activity, true)) {
                return 1;
            }
            if (m14189a(activity.getWindow(), true)) {
                return 2;
            }
            if (Build.VERSION.SDK_INT >= 23) {
                activity.getWindow().getDecorView().setSystemUiVisibility(9216);
                return 3;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static void m14185a(Activity activity, int i) {
        if (i == 1) {
            m14188a(activity, true);
        } else if (i == 2) {
            m14189a(activity.getWindow(), true);
        } else if (i == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(9216);
        }
    }

    /* renamed from: b */
    public static int m14190b(Activity activity) {
        if (Build.VERSION.SDK_INT < 19) {
            return 0;
        }
        if (m14188a(activity, false)) {
            return 1;
        }
        if (m14189a(activity.getWindow(), false)) {
            return 2;
        }
        if (Build.VERSION.SDK_INT < 23) {
            return 0;
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(0);
        return 3;
    }

    /* renamed from: b */
    public static void m14191b(Activity activity, int i) {
        if (i == 1) {
            m14188a(activity, false);
        } else if (i == 2) {
            m14189a(activity.getWindow(), false);
        } else if (i == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(0);
        }
    }

    /* renamed from: a */
    public static boolean m14189a(Window window, boolean z) {
        int i;
        if (window != null) {
            try {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i2 = declaredField.getInt(null);
                int i3 = declaredField2.getInt(attributes);
                if (z) {
                    i = i2 | i3;
                } else {
                    i = (i2 ^ -1) & i3;
                }
                declaredField2.setInt(attributes, i);
                window.setAttributes(attributes);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m14188a(Activity activity, boolean z) {
        Window window = activity.getWindow();
        if (window == null) {
            return false;
        }
        Class<?> cls = window.getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
            if (z) {
                method.invoke(window, Integer.valueOf(i), Integer.valueOf(i));
            } else {
                method.invoke(window, 0, Integer.valueOf(i));
            }
            try {
                if (Build.VERSION.SDK_INT < 23) {
                    return true;
                }
                if (z) {
                    window.addFlags(Integer.MIN_VALUE);
                    window.getDecorView().setSystemUiVisibility(8192);
                    return true;
                }
                activity.getWindow().getDecorView().setSystemUiVisibility(0);
                return true;
            } catch (Exception e) {
                return true;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    /* renamed from: a */
    public static void m14186a(Activity activity, int i, boolean z) {
        int i2;
        int i3;
        int color = ContextCompat.getColor(activity, 2131624012);
        activity.getWindow().getDecorView().setSystemUiVisibility(256);
        if (m14192b()) {
            if (Build.VERSION.SDK_INT >= 23) {
                int systemUiVisibility = activity.getWindow().getDecorView().getSystemUiVisibility();
                if (z) {
                    i3 = systemUiVisibility | 8192;
                } else {
                    i3 = systemUiVisibility & -8193;
                }
                activity.getWindow().getDecorView().setSystemUiVisibility(i3);
                m14200e(activity, i);
                return;
            }
            m14188a(activity, z);
            if (Build.VERSION.SDK_INT >= 19) {
                m14201f(activity, i);
            }
        } else if (m14187a()) {
            m14188a(activity, z);
            if (Build.VERSION.SDK_INT >= 19) {
                m14201f(activity, i);
            }
        } else if (m14196c()) {
            m14193b(activity, z);
            if (Build.VERSION.SDK_INT >= 19) {
                m14201f(activity, i);
            }
        } else if (Build.VERSION.SDK_INT >= 23) {
            int systemUiVisibility2 = activity.getWindow().getDecorView().getSystemUiVisibility();
            if (z) {
                i2 = systemUiVisibility2 | 8192;
            } else {
                i2 = systemUiVisibility2 & -8193;
            }
            activity.getWindow().getDecorView().setSystemUiVisibility(i2);
            m14200e(activity, i);
        } else if (Build.VERSION.SDK_INT >= 21) {
            if (z) {
                m14200e(activity, color);
            } else {
                m14200e(activity, i);
            }
        } else if (Build.VERSION.SDK_INT < 19) {
        } else {
            if (z) {
                m14201f(activity, color);
            } else {
                m14201f(activity, i);
            }
        }
    }

    /* renamed from: c */
    public static void m14195c(Activity activity, int i) {
        m14186a(activity, ContextCompat.getColor(activity, i), true);
    }

    @RequiresApi(api = 19)
    @TargetApi(21)
    /* renamed from: e */
    private static void m14200e(Activity activity, int i) {
        Window window = activity.getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(i);
    }

    @RequiresApi(api = 19)
    /* renamed from: f */
    private static void m14201f(Activity activity, int i) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(i);
            return;
        }
        window.addFlags(67108864);
        View view = new View(window.getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, m14184a(window.getContext()));
        layoutParams.gravity = 48;
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(i);
        ((ViewGroup) window.getDecorView()).addView(view);
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        if (viewGroup != null) {
            viewGroup.setFitsSystemWindows(true);
            viewGroup.setClipToPadding(true);
            if (viewGroup.getChildCount() > 0) {
                View childAt = viewGroup.getChildAt(0);
                if (childAt instanceof ViewGroup) {
                    childAt.setFitsSystemWindows(true);
                    ((ViewGroup) childAt).setClipToPadding(true);
                }
            }
        }
    }

    /* renamed from: a */
    private static boolean m14187a() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            String property = properties.getProperty(f13144a, null);
            if (property == null || Integer.parseInt(property) < 4) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: b */
    private static boolean m14192b() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            String property = properties.getProperty(f13144a, null);
            if (property == null || Integer.parseInt(property) < 5) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: c */
    private static boolean m14196c() {
        String str = Build.DISPLAY;
        if (str == null || str.equals("") || !str.contains("Flyme")) {
            return false;
        }
        String[] split = str.split(" ");
        for (String str2 : split) {
            if (str2.matches("^[4-9]\\.(\\d+\\.)+\\S*")) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    static boolean m14193b(Activity activity, boolean z) {
        int i;
        try {
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            int i2 = declaredField.getInt(null);
            int i3 = declaredField2.getInt(attributes);
            if (z) {
                i = i2 | i3;
            } else {
                i = (i2 ^ -1) & i3;
            }
            declaredField2.setInt(attributes, i);
            activity.getWindow().setAttributes(attributes);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static int m14184a(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", DispatchConstants.ANDROID);
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* renamed from: c */
    public static void m14194c(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            m14197d(activity);
        }
    }

    @TargetApi(19)
    /* renamed from: d */
    private static void m14197d(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().clearFlags(67108864);
            activity.getWindow().addFlags(Integer.MIN_VALUE);
            activity.getWindow().setStatusBarColor(0);
            return;
        }
        activity.getWindow().addFlags(67108864);
    }

    /* renamed from: e */
    private static void m14199e(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                childAt.setFitsSystemWindows(true);
                ((ViewGroup) childAt).setClipToPadding(true);
            }
        }
    }

    /* renamed from: d */
    public static void m14198d(Activity activity, int i) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(i);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View view = new View(window.getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, m14184a(window.getContext()));
            layoutParams.gravity = 48;
            view.setLayoutParams(layoutParams);
            view.setBackgroundColor(i);
            ((ViewGroup) window.getDecorView()).addView(view);
        }
    }
}
