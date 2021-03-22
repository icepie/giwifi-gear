package com.gbcom.gwifi.base.app;

import android.content.Context;
import android.text.ClipboardManager;
import android.view.WindowManager;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.wxapi.WXInterestView;

/* renamed from: com.gbcom.gwifi.base.app.b */
public class GBWindowManager {

    /* renamed from: a */
    private static WXInterestView f8844a;

    /* renamed from: b */
    private static WindowManager.LayoutParams f8845b;

    /* renamed from: c */
    private static WindowManager f8846c;

    /* renamed from: a */
    public static void m10494a(Context context, String str) {
        WindowManager b = m10495b(context);
        int width = b.getDefaultDisplay().getWidth();
        int height = b.getDefaultDisplay().getHeight();
        if (f8844a == null) {
            f8844a = new WXInterestView(context);
            if (!C3467s.m14513e(str)) {
                ((ClipboardManager) context.getSystemService("clipboard")).setText(str);
            }
            if (f8845b == null) {
                f8845b = new WindowManager.LayoutParams();
                f8845b.type = 2002;
                f8845b.format = 1;
                f8845b.flags = 40;
                f8845b.gravity = 51;
                f8845b.width = WXInterestView.f13870a;
                f8845b.height = WXInterestView.f13871b;
                f8845b.x = width;
                f8845b.y = height / 2;
            }
            f8844a.mo28454a(f8845b);
            b.addView(f8844a, f8845b);
        }
    }

    /* renamed from: a */
    public static void m10493a(Context context) {
        if (f8844a != null) {
            m10495b(context).removeView(f8844a);
            f8844a = null;
            f8845b = null;
        }
    }

    /* renamed from: b */
    private static WindowManager m10495b(Context context) {
        if (f8846c == null) {
            f8846c = (WindowManager) context.getSystemService("window");
        }
        return f8846c;
    }
}
