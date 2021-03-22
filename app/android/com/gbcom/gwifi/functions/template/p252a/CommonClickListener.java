package com.gbcom.gwifi.functions.template.p252a;

import android.content.Context;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.a.d */
public class CommonClickListener extends AbstractClickListener {

    /* renamed from: a */
    private Context f11693a;

    /* renamed from: b */
    private JSONObject f11694b;

    public CommonClickListener(Context context, JSONObject jSONObject) {
        this.f11693a = context;
        this.f11694b = jSONObject;
    }

    /* renamed from: a */
    public void mo26975a(JSONObject jSONObject) {
        this.f11694b = jSONObject;
    }

    /* renamed from: a */
    public void mo26974a(Context context) {
        this.f11693a = context;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractClickListener
    /* renamed from: a */
    public JSONObject mo26963a() {
        return this.f11694b;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractClickListener
    /* renamed from: b */
    public Context mo26964b() {
        return this.f11693a;
    }
}
