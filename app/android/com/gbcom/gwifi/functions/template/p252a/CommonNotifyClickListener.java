package com.gbcom.gwifi.functions.template.p252a;

import android.content.Context;
import android.view.View;
import com.gbcom.gwifi.base.app.GBElementManager;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.a.h */
public class CommonNotifyClickListener extends AbstractClickListener {

    /* renamed from: a */
    private Context f11702a;

    /* renamed from: b */
    private JSONObject f11703b;

    public CommonNotifyClickListener(Context context, JSONObject jSONObject) {
        this.f11702a = context;
        this.f11703b = jSONObject;
    }

    /* renamed from: a */
    public void mo26980a(JSONObject jSONObject) {
        this.f11703b = jSONObject;
    }

    /* renamed from: a */
    public void mo26979a(Context context) {
        this.f11702a = context;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractClickListener
    /* renamed from: a */
    public JSONObject mo26963a() {
        return this.f11703b;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractClickListener
    /* renamed from: b */
    public Context mo26964b() {
        return this.f11702a;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractClickListener
    public void onClick(View view) {
        GBElementManager.m10471a().mo24384a(mo26963a(), mo26964b());
    }
}
