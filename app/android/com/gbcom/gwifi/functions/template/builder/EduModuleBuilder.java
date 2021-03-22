package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.content.Intent;
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
import com.gbcom.gwifi.functions.edu.EduMoudleActivity;
import com.gbcom.gwifi.functions.edu.domain.SchoolNotifyBanner;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.DragGridLayout;
import java.p456io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.e */
public class EduModuleBuilder implements ViewBuilder {

    /* renamed from: a */
    private List<SchoolNotifyBanner> f11776a = new ArrayList();

    /* renamed from: b */
    private List<SchoolNotifyBanner> f11777b = new ArrayList();

    /* renamed from: c */
    private GridView f11778c;

    /* renamed from: d */
    private Context f11779d;

    /* renamed from: e */
    private JSONObject f11780e;

    /* renamed from: f */
    private JSONArray f11781f;

    /* renamed from: g */
    private DragGridLayout f11782g;

    /* renamed from: h */
    private List<View> f11783h = new ArrayList();

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        View inflate;
        this.f11779d = context;
        this.f11780e = jSONObject;
        if (SystemUtil.m14531e()) {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.layout_builder_edu_module_school, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.layout_builder_edu_module, viewGroup, false);
        }
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C2425R.C2427id.ll_main);
        this.f11782g = (DragGridLayout) inflate.findViewById(C2425R.C2427id.dragGridLayout);
        this.f11781f = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS);
        this.f11776a.clear();
        if (this.f11781f != null && this.f11781f.length() > 0) {
            for (int i = 0; i < this.f11781f.length(); i++) {
                JSONObject jSONObject2 = this.f11781f.getJSONObject(i);
                SchoolNotifyBanner schoolNotifyBanner = new SchoolNotifyBanner();
                String string = jSONObject2.getString("icon_url");
                String string2 = jSONObject2.getString("wap_url");
                schoolNotifyBanner.setTitle(jSONObject2.getString("title"));
                schoolNotifyBanner.setImgUrl(string);
                schoolNotifyBanner.setWapUrl(string2);
                schoolNotifyBanner.setObject(this.f11781f.getJSONObject(i).toString());
                this.f11776a.add(schoolNotifyBanner);
            }
        }
        m12870a();
        return inflate;
    }

    /* renamed from: a */
    private void m12870a() {
        this.f11777b.clear();
        if (this.f11776a.size() >= 7) {
            for (int i = 0; i < 7; i++) {
                this.f11777b.add(this.f11776a.get(i));
            }
        } else {
            this.f11777b.addAll(this.f11776a);
        }
        this.f11783h.clear();
        for (int i2 = 0; i2 < this.f11777b.size() + 1; i2++) {
            if (i2 != this.f11777b.size()) {
                SchoolNotifyBanner schoolNotifyBanner = this.f11777b.get(i2);
                View inflate = LayoutInflater.from(this.f11779d).inflate(C2425R.layout.edumod_item_drag2, (ViewGroup) null);
                inflate.setTag(Integer.valueOf(i2));
                ImageTools.m14149a(schoolNotifyBanner.getImgUrl(), (ImageView) inflate.findViewById(2131755361), GBApplication.instance().options);
                ((TextView) inflate.findViewById(C2425R.C2427id.tv_item)).setText(schoolNotifyBanner.getTitle());
                try {
                    inflate.setOnClickListener(new CommonClickListener(this.f11779d, new JSONObject(schoolNotifyBanner.getObject())));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.f11783h.add(inflate);
            } else {
                View inflate2 = LayoutInflater.from(this.f11779d).inflate(C2425R.layout.edumod_item_drag2, (ViewGroup) null);
                inflate2.setTag(Integer.valueOf(i2));
                ((ImageView) inflate2.findViewById(2131755361)).setImageResource(C2425R.C2426drawable.gi_more);
                ((TextView) inflate2.findViewById(C2425R.C2427id.tv_item)).setText("更多");
                inflate2.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.template.builder.EduModuleBuilder.View$OnClickListenerC31401 */

                    public void onClick(View view) {
                        Intent intent = new Intent(EduModuleBuilder.this.f11779d, EduMoudleActivity.class);
                        intent.putExtra("datas", (Serializable) EduModuleBuilder.this.f11776a);
                        EduModuleBuilder.this.f11779d.startActivity(intent);
                    }
                });
                this.f11783h.add(inflate2);
            }
        }
        this.f11782g.mo28259a(this.f11783h);
    }
}
