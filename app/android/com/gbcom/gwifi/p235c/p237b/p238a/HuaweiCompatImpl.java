package com.gbcom.gwifi.p235c.p237b.p238a;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.gbcom.gwifi.p235c.p237b.Utils;

/* renamed from: com.gbcom.gwifi.c.b.a.c */
public class HuaweiCompatImpl extends BelowApi23CompatImpl {

    /* renamed from: a */
    private static final String f9140a = "HuaweiCompatImpl";

    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
    /* renamed from: a */
    public boolean mo24503a() {
        return true;
    }

    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
    /* renamed from: a */
    public boolean mo24504a(Context context) {
        try {
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity"));
            if (Utils.m10793a() == 3.1d) {
                context.startActivity(intent);
                return false;
            }
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity"));
            context.startActivity(intent);
            return false;
        } catch (SecurityException e) {
            Intent intent2 = new Intent();
            intent2.setFlags(268435456);
            intent2.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
            context.startActivity(intent2);
            Log.e(f9140a, Log.getStackTraceString(e));
            return false;
        } catch (ActivityNotFoundException e2) {
            Intent intent3 = new Intent();
            intent3.setFlags(268435456);
            intent3.setComponent(new ComponentName("com.Android.settings", "com.android.settings.permission.TabItem"));
            context.startActivity(intent3);
            Log.e(f9140a, Log.getStackTraceString(e2));
            return false;
        } catch (Exception e3) {
            Log.e(f9140a, Log.getStackTraceString(e3));
            return false;
        }
    }
}
