package com.gbcom.gwifi.p235c.p237b.p238a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat;

/* renamed from: com.gbcom.gwifi.c.b.a.a */
public class Api23CompatImpl implements FloatingPermissionCompat.AbstractC2596a {

    /* renamed from: a */
    private static final String f9139a = "Api23CompatImpl";

    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
    /* renamed from: b */
    public boolean mo24505b(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                return ((Boolean) Settings.class.getDeclaredMethod("canDrawOverlays", Context.class).invoke(null, context)).booleanValue();
            } catch (Exception e) {
                Log.e(f9139a, Log.getStackTraceString(e));
            }
        }
        return true;
    }

    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
    /* renamed from: a */
    public boolean mo24503a() {
        return true;
    }

    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
    /* renamed from: a */
    public boolean mo24504a(Context context) {
        try {
            Intent intent = new Intent(Settings.class.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION").get(null).toString());
            intent.setFlags(268435456);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            Log.e(f9139a, Log.getStackTraceString(e));
            return false;
        }
    }
}
