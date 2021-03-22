package com.gbcom.gwifi.functions.template.p252a;

import android.content.Context;
import android.view.View;
import com.gbcom.gwifi.base.app.GBElementManager;
import com.gbcom.gwifi.util.C3467s;
import java.util.HashMap;

/* renamed from: com.gbcom.gwifi.functions.template.a.b */
public abstract class AbstractGiWiFiAdClickListener implements View.OnClickListener {

    /* renamed from: a */
    private int f11692a;

    /* renamed from: a */
    public abstract HashMap mo26967a();

    /* renamed from: b */
    public abstract Context mo26968b();

    /* renamed from: c */
    public abstract int mo26969c();

    public void onClick(View view) {
        HashMap<String, Object> a = mo26967a();
        this.f11692a = mo26969c();
        if (a != null) {
            if (!a.containsKey("localImgUrl") || !C3467s.m14513e((String) a.get("localImgUrl"))) {
            }
            GBElementManager.m10471a().mo24382a(a, this.f11692a);
        }
    }
}
