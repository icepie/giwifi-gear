package com.gbcom.gwifi.p235c.p237b.p238a;

import android.content.Context;
import android.content.Intent;
import com.taobao.accs.common.Constants;

/* renamed from: com.gbcom.gwifi.c.b.a.d */
public class MeizuCompatImpl extends BelowApi23CompatImpl {
    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
    /* renamed from: a */
    public boolean mo24503a() {
        return true;
    }

    @Override // com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat.AbstractC2596a
    /* renamed from: a */
    public boolean mo24504a(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        intent.putExtra(Constants.KEY_PACKAGE_NAME, context.getPackageName());
        intent.setFlags(268435456);
        context.startActivity(intent);
        return true;
    }
}
