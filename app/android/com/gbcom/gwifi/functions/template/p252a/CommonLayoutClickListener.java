package com.gbcom.gwifi.functions.template.p252a;

import android.content.Context;
import android.view.View;
import com.gbcom.gwifi.base.app.GBElementManager;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.a.g */
public class CommonLayoutClickListener extends AbstractClickListener {

    /* renamed from: a */
    private Context f11700a;

    /* renamed from: b */
    private JSONObject f11701b;

    public CommonLayoutClickListener(Context context, JSONObject jSONObject) {
        this.f11700a = context;
        this.f11701b = jSONObject;
    }

    /* renamed from: a */
    public void mo26978a(JSONObject jSONObject) {
        this.f11701b = jSONObject;
    }

    /* renamed from: a */
    public void mo26977a(Context context) {
        this.f11700a = context;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractClickListener
    /* renamed from: a */
    public JSONObject mo26963a() {
        return this.f11701b;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractClickListener
    /* renamed from: b */
    public Context mo26964b() {
        return this.f11700a;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractClickListener
    public void onClick(View view) {
        GBElementManager.m10471a().mo24385b(mo26963a(), mo26964b());
    }
}
