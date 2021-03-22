package com.gbcom.gwifi.functions.template.p252a;

import android.content.Context;
import java.util.HashMap;

/* renamed from: com.gbcom.gwifi.functions.template.a.e */
public class CommonGiWiFiAdClickListener extends AbstractGiWiFiAdClickListener {

    /* renamed from: a */
    private Context f11695a;

    /* renamed from: b */
    private HashMap f11696b;

    /* renamed from: c */
    private int f11697c;

    public CommonGiWiFiAdClickListener(Context context, HashMap hashMap, int i) {
        this.f11695a = context;
        this.f11696b = hashMap;
        this.f11697c = i;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractGiWiFiAdClickListener
    /* renamed from: a */
    public HashMap mo26967a() {
        return this.f11696b;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractGiWiFiAdClickListener
    /* renamed from: b */
    public Context mo26968b() {
        return this.f11695a;
    }

    @Override // com.gbcom.gwifi.functions.template.p252a.AbstractGiWiFiAdClickListener
    /* renamed from: c */
    public int mo26969c() {
        return this.f11697c;
    }
}
