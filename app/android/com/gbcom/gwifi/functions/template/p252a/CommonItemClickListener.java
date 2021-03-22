package com.gbcom.gwifi.functions.template.p252a;

import android.content.Context;
import org.json.JSONArray;

/* renamed from: com.gbcom.gwifi.functions.template.a.f */
public class CommonItemClickListener extends AbstractItemClickListener {

    /* renamed from: a */
    private Context f11698a;

    /* renamed from: b */
    private JSONArray f11699b;

    public CommonItemClickListener(Context context, JSONArray jSONArray) {
        this.f11698a = context;
        this.f11699b = jSONArray;
    }

    /* renamed from: a */
    public void mo26976a(Context context) {
        this.f11698a = context;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractItemClickListener
    /* renamed from: a */
    public JSONArray mo26971a() {
        return this.f11699b;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractItemClickListener
    /* renamed from: b */
    public Context mo26972b() {
        return this.f11698a;
    }
}
