package com.gbcom.gwifi.functions.template.p252a;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import com.gbcom.gwifi.base.app.GBElementManager;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: com.gbcom.gwifi.functions.template.a.c */
public abstract class AbstractItemClickListener implements AdapterView.OnItemClickListener {
    /* renamed from: a */
    public abstract JSONArray mo26971a();

    /* renamed from: b */
    public abstract Context mo26972b();

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        try {
            GBElementManager.m10471a().mo24384a(mo26971a().getJSONObject(i), mo26972b());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
