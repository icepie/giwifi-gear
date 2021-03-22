package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.support.p009v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.edu.domain.SchoolNotifyBanner;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.DragGridLayout;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.t */
public class OfficeModuleBuilder implements ViewBuilder {

    /* renamed from: a */
    private List<SchoolNotifyBanner> f11888a = new ArrayList();

    /* renamed from: b */
    private List<SchoolNotifyBanner> f11889b = new ArrayList();

    /* renamed from: c */
    private GridView f11890c;

    /* renamed from: d */
    private Context f11891d;

    /* renamed from: e */
    private JSONObject f11892e;

    /* renamed from: f */
    private JSONArray f11893f;

    /* renamed from: g */
    private DragGridLayout f11894g;

    /* renamed from: h */
    private List<View> f11895h = new ArrayList();

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        this.f11891d = context;
        this.f11892e = jSONObject;
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.layout_builder_office_module, viewGroup, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C2425R.C2427id.ll_main);
        this.f11894g = (DragGridLayout) inflate.findViewById(C2425R.C2427id.dragGridLayout);
        this.f11893f = jSONObject.getJSONArray("modules");
        this.f11888a.clear();
        if (this.f11893f != null && this.f11893f.length() > 0) {
            for (int i = 0; i < this.f11893f.length(); i++) {
                JSONObject jSONObject2 = this.f11893f.getJSONObject(i);
                SchoolNotifyBanner schoolNotifyBanner = new SchoolNotifyBanner();
                String string = jSONObject2.getString("icon_url");
                String string2 = jSONObject2.getString("wap_url");
                schoolNotifyBanner.setTitle(jSONObject2.getString("title"));
                schoolNotifyBanner.setImgUrl(string);
                schoolNotifyBanner.setWapUrl(string2);
                schoolNotifyBanner.setObject(this.f11893f.getJSONObject(i).toString());
                this.f11888a.add(schoolNotifyBanner);
            }
        }
        m12953a();
        return inflate;
    }

    /* renamed from: a */
    private void m12953a() {
        this.f11889b.clear();
        this.f11889b.addAll(this.f11888a);
        this.f11895h.clear();
        for (int i = 0; i < this.f11889b.size(); i++) {
            SchoolNotifyBanner schoolNotifyBanner = this.f11889b.get(i);
            View inflate = LayoutInflater.from(this.f11891d).inflate(C2425R.layout.edumod_item_drag2, (ViewGroup) null);
            inflate.setTag(Integer.valueOf(i));
            ImageTools.m14149a(schoolNotifyBanner.getImgUrl(), (ImageView) inflate.findViewById(2131755361), GBApplication.instance().options);
            ((TextView) inflate.findViewById(C2425R.C2427id.tv_item)).setText(schoolNotifyBanner.getTitle());
            try {
                inflate.setOnClickListener(new CommonClickListener(this.f11891d, new JSONObject(schoolNotifyBanner.getObject())));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f11895h.add(inflate);
        }
        this.f11894g.mo28259a(this.f11895h);
    }
}
