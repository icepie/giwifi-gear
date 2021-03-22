package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.support.p009v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.C3467s;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.ae */
public class VerticalLinearLayoutBuilder implements ViewBuilder, OnDestoryBuilder {

    /* renamed from: a */
    private ZhongGuangCommendBuilder f11710a;

    /* renamed from: b */
    private SchoolNotifyBuilder f11711b;

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        ViewBuilder a;
        ScrollView scrollView = (ScrollView) LayoutInflater.from(context).inflate(C2425R.layout.tp_vertical_layout, viewGroup, false);
        LinearLayout linearLayout = (LinearLayout) scrollView.findViewById(C2425R.C2427id.root_view);
        JSONArray jSONArray = jSONObject.getJSONArray(Constants.ELEMNAME_CHILDREN_STRING);
        this.f11711b = null;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString("child_layout_id");
            if (!C3467s.m14513e(string) && (a = ViewBuilderFactory.m12846a(string)) != null) {
                if (a instanceof ZhongGuangCommendBuilder) {
                    this.f11710a = (ZhongGuangCommendBuilder) a;
                }
                View a2 = a.mo26982a(context, linearLayout, jSONObject2, fragmentManager);
                if (a instanceof SchoolNotifyBuilder) {
                    this.f11711b = (SchoolNotifyBuilder) a;
                }
                if (a2 != null) {
                    if (!(a instanceof ImageBuilder)) {
                        linearLayout.addView(a2);
                    }
                    linearLayout.addView(LayoutInflater.from(context).inflate(C2425R.layout.tp_split_horizon_line, viewGroup, false));
                }
            }
        }
        return scrollView;
    }

    @Override // com.gbcom.gwifi.functions.template.builder.OnDestoryBuilder
    /* renamed from: a */
    public void mo26983a() {
        if (this.f11710a != null) {
            this.f11710a.mo26983a();
        }
    }

    /* renamed from: b */
    public void mo26984b() {
        if (this.f11711b != null) {
            this.f11711b.mo27065a();
        }
    }
}
