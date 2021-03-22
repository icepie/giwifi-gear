package com.gbcom.gwifi.p235c.p237b;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import com.gbcom.gwifi.p235c.p237b.p238a.Api23CompatImpl;
import com.gbcom.gwifi.p235c.p237b.p238a.BelowApi23CompatImpl;
import com.gbcom.gwifi.p235c.p237b.p238a.HuaweiCompatImpl;
import com.gbcom.gwifi.p235c.p237b.p238a.MeizuCompatImpl;
import com.gbcom.gwifi.p235c.p237b.p238a.MiuiCompatImpl;
import com.gbcom.gwifi.p235c.p237b.p238a.QihooCompatImpl;

/* renamed from: com.gbcom.gwifi.c.b.a */
public class FloatingPermissionCompat {

    /* renamed from: a */
    private static final String f9135a = "FloatPermissionCompat";

    /* renamed from: b */
    private static FloatingPermissionCompat f9136b;

    /* renamed from: c */
    private AbstractC2596a f9137c;

    /* renamed from: com.gbcom.gwifi.c.b.a$a */
    /* compiled from: FloatingPermissionCompat */
    public interface AbstractC2596a {
        /* renamed from: a */
        boolean mo24503a();

        /* renamed from: a */
        boolean mo24504a(Context context);

        /* renamed from: b */
        boolean mo24505b(Context context);
    }

    private FloatingPermissionCompat() {
        if (Build.VERSION.SDK_INT < 23) {
            if (Utils.m10798d()) {
                this.f9137c = new MiuiCompatImpl();
            } else if (Utils.m10799e()) {
                this.f9137c = new MeizuCompatImpl();
            } else if (Utils.m10797c()) {
                this.f9137c = new HuaweiCompatImpl();
            } else if (Utils.m10800f()) {
                this.f9137c = new QihooCompatImpl();
            } else {
                this.f9137c = new BelowApi23CompatImpl() {
                    /* class com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.C25951 */

                    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
                    /* renamed from: a */
                    public boolean mo24503a() {
                        return false;
                    }

                    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
                    /* renamed from: a */
                    public boolean mo24504a(Context context) {
                        return false;
                    }
                };
            }
        } else if (Utils.m10799e()) {
            this.f9137c = new MeizuCompatImpl();
        } else {
            this.f9137c = new Api23CompatImpl();
        }
    }

    /* renamed from: a */
    public static FloatingPermissionCompat m10767a() {
        if (f9136b == null) {
            f9136b = new FloatingPermissionCompat();
        }
        return f9136b;
    }

    /* renamed from: a */
    public boolean mo24500a(Context context) {
        return this.f9137c.mo24505b(context);
    }

    /* renamed from: b */
    public boolean mo24501b() {
        return this.f9137c.mo24503a();
    }

    /* renamed from: b */
    public boolean mo24502b(Context context) {
        if (!mo24501b()) {
            return false;
        }
        return this.f9137c.mo24504a(context);
    }

    @TargetApi(19)
    /* renamed from: a */
    public static boolean m10768a(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                return ((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke((AppOpsManager) context.getSystemService("appops"), new Object[]{Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()), context.getPackageName()})).intValue() == 0;
            } catch (Exception e) {
                Log.e(f9135a, Log.getStackTraceString(e));
            }
        } else {
            Log.e(f9135a, "Below API 19 cannot invoke!");
            return false;
        }
    }
}
