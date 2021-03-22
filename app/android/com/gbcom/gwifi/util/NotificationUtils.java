package com.gbcom.gwifi.util;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.os.Build;
import android.support.p012v7.app.AppCompatActivity;
import android.support.p012v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.LinkedList;

/* renamed from: com.gbcom.gwifi.util.n */
public class NotificationUtils {

    /* renamed from: a */
    private static final String f13470a = NotificationUtils.class.getSimpleName();

    /* renamed from: b */
    private static final String f13471b = "checkOpNoThrow";

    /* renamed from: c */
    private static final String f13472c = "OP_POST_NOTIFICATION";

    /* renamed from: d */
    private static final double f13473d = 180.0d;

    /* renamed from: e */
    private static int f13474e;

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.util.n$a */
    /* compiled from: NotificationUtils */
    public interface AbstractC3466a {
        /* renamed from: a */
        void mo28163a(View view);
    }

    /* renamed from: a */
    public static boolean m14435a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                String packageName = context.getApplicationContext().getPackageName();
                int i = applicationInfo.uid;
                Class<?> cls = Class.forName(AppOpsManager.class.getName());
                return ((Integer) cls.getMethod(f13471b, new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke((AppOpsManager) context.getSystemService("appops"), new Object[]{Integer.valueOf(((Integer) cls.getDeclaredField(f13472c).get(Integer.class)).intValue()), Integer.valueOf(i), packageName})).intValue() == 0;
            }
        } catch (Exception e) {
            Log.m14400c(f13470a, e.getMessage());
        }
        return true;
    }

    /* renamed from: b */
    public static boolean m14437b(Context context) {
        return !m14434a(-16777216, m14439d(context));
    }

    /* renamed from: d */
    private static int m14439d(Context context) {
        if (context instanceof AppCompatActivity) {
            return m14441f(context);
        }
        return m14440e(context);
    }

    /* renamed from: a */
    private static boolean m14434a(int i, int i2) {
        int i3 = i | -16777216;
        int i4 = -16777216 | i2;
        int red = Color.red(i3) - Color.red(i4);
        int green = Color.green(i3) - Color.green(i4);
        int blue = Color.blue(i3) - Color.blue(i4);
        if (Math.sqrt((double) ((blue * blue) + (red * red) + (green * green))) < f13473d) {
            return true;
        }
        return false;
    }

    /* renamed from: e */
    private static int m14440e(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentText("DUMMY_TITLE");
        ViewGroup viewGroup = (ViewGroup) builder.build().contentView.apply(context, new FrameLayout(context));
        TextView textView = (TextView) viewGroup.findViewById(16908310);
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        m14433a(viewGroup, new AbstractC3466a() {
            /* class com.gbcom.gwifi.util.NotificationUtils.C34641 */

            @Override // com.gbcom.gwifi.util.NotificationUtils.AbstractC3466a
            /* renamed from: a */
            public void mo28163a(View view) {
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    if ("DUMMY_TITLE".equals(textView.getText().toString())) {
                        int unused = NotificationUtils.f13474e = textView.getCurrentTextColor();
                    }
                }
            }
        });
        return f13474e;
    }

    /* renamed from: f */
    private static int m14441f(Context context) {
        int i;
        float f;
        int i2 = 0;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(new NotificationCompat.Builder(context).build().contentView.getLayoutId(), (ViewGroup) null);
        TextView textView = (TextView) viewGroup.findViewById(16908310);
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        final ArrayList arrayList = new ArrayList();
        m14433a(viewGroup, new AbstractC3466a() {
            /* class com.gbcom.gwifi.util.NotificationUtils.C34652 */

            @Override // com.gbcom.gwifi.util.NotificationUtils.AbstractC3466a
            /* renamed from: a */
            public void mo28163a(View view) {
                arrayList.add((TextView) view);
            }
        });
        float f2 = -2.14748365E9f;
        int size = arrayList.size();
        int i3 = 0;
        while (i3 < size) {
            float textSize = ((TextView) arrayList.get(i3)).getTextSize();
            if (textSize > f2) {
                f = textSize;
                i = i3;
            } else {
                i = i2;
                f = f2;
            }
            i3++;
            f2 = f;
            i2 = i;
        }
        return ((TextView) arrayList.get(i2)).getCurrentTextColor();
    }

    /* renamed from: a */
    private static void m14433a(View view, AbstractC3466a aVar) {
        if (!(view == null || aVar == null)) {
            aVar.mo28163a(view);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    m14433a(viewGroup.getChildAt(i), aVar);
                }
            }
        }
    }

    /* renamed from: a */
    private static int m14432a(ViewGroup viewGroup) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(viewGroup);
        int i = 0;
        while (linkedList.size() > 0) {
            ViewGroup viewGroup2 = (ViewGroup) linkedList.getFirst();
            int i2 = i;
            for (int i3 = 0; i3 < viewGroup2.getChildCount(); i3++) {
                if (viewGroup2.getChildAt(i3) instanceof ViewGroup) {
                    linkedList.add((ViewGroup) viewGroup2.getChildAt(i3));
                } else if ((viewGroup2.getChildAt(i3) instanceof TextView) && ((TextView) viewGroup2.getChildAt(i3)).getCurrentTextColor() != -1) {
                    i2 = ((TextView) viewGroup2.getChildAt(i3)).getCurrentTextColor();
                }
            }
            linkedList.remove(viewGroup2);
            i = i2;
        }
        return i;
    }

    /* renamed from: c */
    public static boolean m14438c(Context context) {
        return !m14436b(-16777216, m14439d(context));
    }

    /* renamed from: b */
    private static boolean m14436b(int i, int i2) {
        int i3 = i | -16777216;
        int i4 = -16777216 | i2;
        int red = Color.red(i3) - Color.red(i4);
        int green = Color.green(i3) - Color.green(i4);
        int blue = Color.blue(i3) - Color.blue(i4);
        if (Math.sqrt((double) ((blue * blue) + (red * red) + (green * green))) < f13473d) {
            return true;
        }
        return false;
    }
}
