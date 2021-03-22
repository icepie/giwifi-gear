package com.gbcom.gwifi.functions.template.p252a;

import android.content.Context;
import android.view.View;
import com.gbcom.gwifi.base.app.GBElementManager;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.a.a */
public abstract class AbstractClickListener implements View.OnClickListener {
    /* renamed from: a */
    public abstract JSONObject mo26963a();

    /* renamed from: b */
    public abstract Context mo26964b();

    /* renamed from: c */
    public void mo26965c() {
        GBElementManager.m10471a().mo24384a(mo26963a(), mo26964b());
    }

    public void onClick(View view) {
        GBElementManager.m10471a().mo24384a(mo26963a(), mo26964b());
    }
}
