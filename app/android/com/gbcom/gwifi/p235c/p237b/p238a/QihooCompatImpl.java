package com.gbcom.gwifi.p235c.p237b.p238a;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.gbcom.gwifi.p235c.p237b.Utils;

/* renamed from: com.gbcom.gwifi.c.b.a.f */
public class QihooCompatImpl extends BelowApi23CompatImpl {

    /* renamed from: a */
    private static final String f9143a = "QihooCompatImpl";

    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
    /* renamed from: a */
    public boolean mo24503a() {
        return true;
    }

    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
    /* renamed from: a */
    public boolean mo24504a(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$OverlaySettingsActivity");
        intent.setFlags(268435456);
        if (Utils.m10795a(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        intent.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.appEnterActivity");
        if (Utils.m10795a(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        Log.e(f9143a, "can't open permission page with particular name, please use \"adb shell dumpsys activity\" command and tell me the name of the float window permission page");
        return false;
    }
}
