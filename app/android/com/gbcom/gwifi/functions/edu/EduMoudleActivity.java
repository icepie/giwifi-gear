package com.gbcom.gwifi.functions.edu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.edu.domain.SchoolNotifyBanner;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.DragGridLayout;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class EduMoudleActivity extends GBActivity {

    /* renamed from: a */
    private RelativeLayout f9294a;

    /* renamed from: b */
    private TextView f9295b;

    /* renamed from: c */
    private TextView f9296c;

    /* renamed from: d */
    private List<SchoolNotifyBanner> f9297d = new ArrayList();

    /* renamed from: e */
    private List<SchoolNotifyBanner> f9298e = new ArrayList();

    /* renamed from: f */
    private List<SchoolNotifyBanner> f9299f;

    /* renamed from: g */
    private GridView f9300g;

    /* renamed from: h */
    private GridView f9301h;

    /* renamed from: i */
    private DragGridLayout f9302i;

    /* renamed from: j */
    private DragGridLayout f9303j;

    /* renamed from: k */
    private List<View> f9304k = new ArrayList();

    /* renamed from: l */
    private List<View> f9305l = new ArrayList();

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "", C2425R.layout.edumod_activity_main, true);
        m10987b();
        this.f9299f = (List) getIntent().getSerializableExtra("datas");
        m10986a();
    }

    /* renamed from: a */
    private void m10986a() {
        this.f9303j = (DragGridLayout) findViewById(C2425R.C2427id.bottomDragGridLayout);
        this.f9302i = (DragGridLayout) findViewById(C2425R.C2427id.topDragGridLayout);
        this.f9297d.clear();
        this.f9298e.clear();
        if (this.f9299f.size() > 7) {
            for (int i = 0; i < 7; i++) {
                this.f9297d.add(this.f9299f.get(i));
            }
            for (int i2 = 7; i2 < this.f9299f.size(); i2++) {
                this.f9298e.add(this.f9299f.get(i2));
            }
        } else {
            this.f9297d.addAll(this.f9299f);
        }
        this.f9304k.clear();
        this.f9305l.clear();
        for (int i3 = 0; i3 < this.f9297d.size(); i3++) {
            View inflate = LayoutInflater.from(this).inflate(C2425R.layout.edumod_item_drag2, (ViewGroup) null);
            inflate.setTag(Integer.valueOf(i3));
            ImageTools.m14149a(this.f9297d.get(i3).getImgUrl(), (ImageView) inflate.findViewById(2131755361), GBApplication.instance().options);
            ((TextView) inflate.findViewById(C2425R.C2427id.tv_item)).setText(this.f9297d.get(i3).getTitle());
            try {
                inflate.setOnClickListener(new CommonClickListener(this, new JSONObject(this.f9297d.get(i3).getObject())));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f9304k.add(inflate);
        }
        this.f9302i.mo28260a(false);
        this.f9302i.mo28257a(new DragGridLayout.AbstractC3479a() {
            /* class com.gbcom.gwifi.functions.edu.EduMoudleActivity.C26181 */

            @Override // com.gbcom.gwifi.widget.DragGridLayout.AbstractC3479a
            /* renamed from: a */
            public void mo24638a(View view) {
                try {
                    view.setOnClickListener(new CommonClickListener(EduMoudleActivity.this, new JSONObject(((SchoolNotifyBanner) EduMoudleActivity.this.f9297d.get(((Integer) view.getTag()).intValue())).getObject())));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        this.f9302i.mo28259a(this.f9304k);
        for (int i4 = 0; i4 < this.f9298e.size(); i4++) {
            View inflate2 = LayoutInflater.from(this).inflate(C2425R.layout.edumod_item_drag2, (ViewGroup) null);
            inflate2.setTag(Integer.valueOf(i4));
            ImageTools.m14149a(this.f9298e.get(i4).getImgUrl(), (ImageView) inflate2.findViewById(2131755361), GBApplication.instance().options);
            ((TextView) inflate2.findViewById(C2425R.C2427id.tv_item)).setText(this.f9298e.get(i4).getTitle());
            try {
                inflate2.setOnClickListener(new CommonClickListener(this, new JSONObject(this.f9298e.get(i4).getObject())));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.f9305l.add(inflate2);
        }
        this.f9303j.mo28260a(false);
        this.f9303j.mo28259a(this.f9305l);
        if (this.f9298e.size() == 0) {
            findViewById(C2425R.C2427id.tv_other).setVisibility(8);
        }
    }

    /* renamed from: b */
    private void m10987b() {
        this.f9294a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f9294a.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.edu.EduMoudleActivity.View$OnClickListenerC26192 */

            public void onClick(View view) {
                EduMoudleActivity.this.finish();
            }
        });
        this.f9296c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f9295b = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f9296c.setText("更多功能");
        this.f9295b.setText("");
    }

    /* renamed from: com.gbcom.gwifi.functions.edu.EduMoudleActivity$a */
    private class C2620a extends BaseAdapter {

        /* renamed from: b */
        private final List<SchoolNotifyBanner> f9309b;

        public C2620a(List<SchoolNotifyBanner> list) {
            this.f9309b = list;
        }

        public int getCount() {
            if (this.f9309b == null) {
                return 0;
            }
            return this.f9309b.size();
        }

        public Object getItem(int i) {
            return this.f9309b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.edumod_item_edumod, viewGroup, false);
            ImageTools.m14149a(this.f9309b.get(i % this.f9309b.size()).getImgUrl(), (ImageView) inflate.findViewById(2131755361), GBApplication.instance().options);
            ((TextView) inflate.findViewById(C2425R.C2427id.tv_item)).setText(this.f9309b.get(i % this.f9309b.size()).getTitle());
            try {
                inflate.setOnClickListener(new CommonClickListener(EduMoudleActivity.this, new JSONObject(this.f9309b.get(i).getObject())));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return inflate;
        }
    }
}
