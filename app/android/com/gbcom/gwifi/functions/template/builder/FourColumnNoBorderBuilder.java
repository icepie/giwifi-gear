package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.support.p009v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.p252a.CommonItemClickListener;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.NoScrollGridView;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.g */
public class FourColumnNoBorderBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() == 0) {
            return null;
        }
        View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_four_column_no_border, viewGroup, false);
        NoScrollGridView noScrollGridView = (NoScrollGridView) inflate.findViewById(C2425R.C2427id.no_scroll_grid);
        noScrollGridView.setAdapter((ListAdapter) new C3141a(context, jSONArray));
        noScrollGridView.setOnItemClickListener(new CommonItemClickListener(context, jSONArray));
        return inflate;
    }

    /* renamed from: com.gbcom.gwifi.functions.template.builder.g$b */
    /* compiled from: FourColumnNoBorderBuilder */
    static class C3142b {

        /* renamed from: a */
        ImageView f11788a;

        /* renamed from: b */
        TextView f11789b;

        C3142b() {
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.template.builder.g$a */
    /* compiled from: FourColumnNoBorderBuilder */
    class C3141a extends BaseAdapter {

        /* renamed from: b */
        private JSONArray f11786b;

        /* renamed from: c */
        private Context f11787c;

        public C3141a(Context context, JSONArray jSONArray) {
            this.f11786b = jSONArray;
            this.f11787c = context;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            C3142b bVar;
            View view2;
            if (this.f11786b == null) {
                return null;
            }
            try {
                JSONObject jSONObject = this.f11786b.getJSONObject(i % this.f11786b.length());
                String string = jSONObject.getString("title");
                String string2 = jSONObject.getString("icon_url");
                if (view == null) {
                    view2 = LayoutInflater.from(this.f11787c).inflate(C2425R.layout.tp_image_with_title_item, viewGroup, false);
                    C3142b bVar2 = new C3142b();
                    view2.setTag(bVar2);
                    bVar = bVar2;
                } else {
                    bVar = (C3142b) view.getTag();
                    view2 = view;
                }
                bVar.f11788a = (ImageView) view2.findViewById(C2425R.C2427id.img);
                bVar.f11789b = (TextView) view2.findViewById(2131755156);
                if (!C3467s.m14513e(string2)) {
                    ImageTools.m14149a(string2, bVar.f11788a, GBApplication.instance().options);
                } else {
                    bVar.f11788a.setImageResource(C2425R.C2426drawable.loading_small);
                }
                bVar.f11789b.setText(string);
                return view2;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        public int getCount() {
            if (this.f11786b == null) {
                return 0;
            }
            return this.f11786b.length();
        }
    }
}
