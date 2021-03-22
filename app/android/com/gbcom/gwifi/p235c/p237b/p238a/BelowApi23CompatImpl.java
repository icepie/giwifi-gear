package com.gbcom.gwifi.p235c.p237b.p238a;

import android.content.Context;
import android.os.Build;
import com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat;

/* renamed from: com.gbcom.gwifi.c.b.a.b */
public abstract class BelowApi23CompatImpl implements FloatingPermissionCompat.AbstractC2596a {
    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
    /* renamed from: b */
    public boolean mo24505b(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            return FloatingPermissionCompat.m10768a(context, 24);
        }
        return true;
    }
}
