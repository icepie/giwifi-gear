package com.gbcom.gwifi.p235c.p236a;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.p235c.p239c.SystemUtils;
import java.sql.Types;

/* renamed from: com.gbcom.gwifi.c.a.e */
public class FloatWindowManager {

    /* renamed from: a */
    public static final int f9079a = 10;

    /* renamed from: b */
    public static final int f9080b = 11;

    /* renamed from: c */
    public static final int f9081c = 12;

    /* renamed from: d */
    private int f9082d = 0;

    /* renamed from: e */
    private IFloatView f9083e;

    /* renamed from: f */
    private boolean f9084f = false;

    /* renamed from: g */
    private FrameLayout f9085g;

    /* renamed from: h */
    private FloatViewParams f9086h;

    /* renamed from: i */
    private WindowManager f9087i;

    /* renamed from: j */
    private LastWindowInfo f9088j = LastWindowInfo.m10759a();

    /* renamed from: k */
    private GBActivity f9089k;

    /* renamed from: a */
    public synchronized void mo24487a(GBActivity gBActivity, int i) {
        if (gBActivity != null) {
            this.f9089k = gBActivity;
            mo24486a(gBActivity.getApplicationContext(), i);
        }
    }

    /* renamed from: a */
    public synchronized void mo24486a(Context context, int i) {
        if (context != null) {
            this.f9082d = i;
            try {
                this.f9084f = true;
                m10703a(context);
            } catch (Exception e) {
                e.printStackTrace();
                this.f9084f = false;
            }
        }
    }

    /* renamed from: a */
    private void m10703a(Context context) {
        if (context != null) {
            this.f9086h = m10707d(context);
            if (this.f9082d == 10) {
                m10704b(context);
            } else {
                m10706c(context);
            }
            this.f9084f = true;
        }
    }

    /* renamed from: b */
    private void m10704b(Context context) {
        try {
            if (this.f9089k != null) {
                this.f9083e = new FloatView(context, this.f9086h);
                this.f9085g = (FrameLayout) this.f9089k.getWindow().getDecorView().getRootView().findViewById(16908290);
                this.f9085g.addView((View) this.f9083e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m10706c(Context context) {
        this.f9087i = SystemUtils.m10807c(context);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.packageName = context.getPackageName();
        layoutParams.flags = 82344;
        if (this.f9082d == 11) {
            layoutParams.type = Types.CLOB;
        } else if (this.f9082d == 12) {
            if (Build.VERSION.SDK_INT >= 26) {
                layoutParams.type = 2038;
            } else {
                layoutParams.type = Types.ARRAY;
            }
        }
        layoutParams.format = 1;
        layoutParams.gravity = 8388659;
        layoutParams.width = this.f9086h.f9066a;
        layoutParams.height = this.f9086h.f9067b;
        layoutParams.x = this.f9086h.f9068c;
        layoutParams.y = this.f9086h.f9069d;
        this.f9083e = new FloatWindowView(context, this.f9086h, layoutParams);
        try {
            this.f9087i.addView((View) this.f9083e, layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((FloatWindowView) this.f9083e).mo24490a(this.f9082d);
    }

    /* renamed from: d */
    private FloatViewParams m10707d(Context context) {
        int i;
        FloatViewParams dVar = new FloatViewParams();
        int a = SystemUtils.m10801a(context);
        int a2 = SystemUtils.m10803a(context, false);
        int g = SystemUtils.m10811g(context);
        int a3 = SystemUtils.m10802a(context, 15.0f);
        int a4 = SystemUtils.m10802a(context, 150.0f);
        if (this.f9082d == 10) {
            int i2 = a4 + g;
        }
        int a5 = a - SystemUtils.m10802a(context, 30.0f);
        int a6 = SystemUtils.m10802a(context, 80.0f);
        int a7 = SystemUtils.m10802a(context, 15.0f);
        if (a5 <= a6) {
            i = a5;
        } else {
            i = a5;
        }
        float f = (1.0f * ((float) a6)) / ((float) a5);
        FloatViewParams c = this.f9088j.mo24497c();
        if (c != null) {
            dVar.f9066a = c.f9066a;
            dVar.f9067b = c.f9067b;
            dVar.f9068c = c.f9068c;
            dVar.f9069d = c.f9069d;
            dVar.f9070e = c.f9070e;
            dVar.f9071f = c.f9071f;
        } else {
            dVar.f9066a = i;
            dVar.f9067b = a6;
            dVar.f9068c = (a - i) / 2;
            dVar.f9069d = a3;
            dVar.f9070e = i;
            dVar.f9071f = a6;
        }
        dVar.f9072g = a;
        dVar.f9073h = a2;
        if (this.f9082d == 10) {
            dVar.f9073h = a2 - g;
        }
        dVar.f9078m = a7;
        dVar.f9076k = i;
        dVar.f9075j = i;
        dVar.f9077l = f;
        return dVar;
    }

    /* renamed from: a */
    public IFloatView mo24485a() {
        return this.f9083e;
    }

    /* renamed from: b */
    public synchronized void mo24488b() {
        if (this.f9084f) {
            this.f9084f = false;
            if (this.f9083e != null) {
                this.f9088j.mo24495a(this.f9083e.mo24465b());
            }
            try {
                if (!(this.f9087i == null || this.f9083e == null)) {
                    m10705c();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!(this.f9085g == null || this.f9083e == null)) {
                this.f9085g.removeView((View) this.f9083e);
            }
            this.f9083e = null;
            this.f9087i = null;
            this.f9085g = null;
        }
    }

    /* renamed from: c */
    private void m10705c() {
        try {
            this.f9087i.removeViewImmediate((View) this.f9083e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
