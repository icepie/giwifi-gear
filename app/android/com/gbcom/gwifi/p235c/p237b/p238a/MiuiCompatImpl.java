package com.gbcom.gwifi.p235c.p237b.p238a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.gbcom.gwifi.p235c.p237b.Utils;
import com.umeng.message.common.C6366a;

/* renamed from: com.gbcom.gwifi.c.b.a.e */
public class MiuiCompatImpl extends BelowApi23CompatImpl {

    /* renamed from: a */
    private static final String f9141a = "MiuiCompatImpl";

    /* renamed from: b */
    private int f9142b;

    public MiuiCompatImpl() {
        this.f9142b = -1;
        this.f9142b = Utils.m10796b();
    }

    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
    /* renamed from: a */
    public boolean mo24503a() {
        return this.f9142b >= 5 && this.f9142b <= 8;
    }

    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
    /* renamed from: a */
    public boolean mo24504a(Context context) {
        if (this.f9142b == 5) {
            return m10788f(context);
        }
        if (this.f9142b == 6) {
            return m10787e(context);
        }
        if (this.f9142b == 7) {
            return m10786d(context);
        }
        if (this.f9142b == 8) {
            return m10785c(context);
        }
        Log.e(f9141a, "this is a special MIUI rom version, its version code " + this.f9142b);
        return false;
    }

    /* renamed from: c */
    private boolean m10785c(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(268435456);
        if (Utils.m10795a(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        Intent intent2 = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent2.setPackage("com.miui.securitycenter");
        intent2.putExtra("extra_pkgname", context.getPackageName());
        intent2.setFlags(268435456);
        if (!Utils.m10795a(context, intent2)) {
            return m10788f(context);
        }
        context.startActivity(intent2);
        return true;
    }

    /* renamed from: d */
    private boolean m10786d(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(268435456);
        if (Utils.m10795a(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        Log.e(f9141a, "applyV7 Intent is not available!");
        return false;
    }

    /* renamed from: e */
    private boolean m10787e(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(268435456);
        if (Utils.m10795a(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        Log.e(f9141a, "applyV6 Intent is not available!");
        return false;
    }

    /* renamed from: f */
    private boolean m10788f(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts(C6366a.f23181c, packageName, null));
        intent.setFlags(268435456);
        if (Utils.m10795a(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        Log.e(f9141a, "applyV5 intent is not available!");
        return false;
    }
}
