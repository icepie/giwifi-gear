package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.graphics.Color;
import android.support.p009v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.f */
public class FourColumnGiBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() < 4) {
            return null;
        }
        View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_four_column_gi, viewGroup, false);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.four_gi)).getLayoutParams().height = (DensityUtil.m14127a(context) * 1) / 3;
        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
        String string = jSONObject2.getString("title");
        String string2 = jSONObject2.getString("sub_title");
        String string3 = jSONObject2.getString("icon_url");
        String string4 = jSONObject2.getString("title_color");
        String string5 = jSONObject2.getString("tag_bg_color");
        TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.one_title);
        TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.one_sub_title);
        textView.setText(string);
        textView2.setText(string2);
        ImageTools.m14149a(string3, (ImageView) inflate.findViewById(C2425R.C2427id.one_img), GBApplication.instance().options);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.one_layout)).setOnClickListener(new CommonClickListener(context, jSONObject2));
        if (!C3467s.m14513e(string4)) {
            textView.setTextColor(Color.parseColor(string4));
        }
        if (!C3467s.m14513e(string5)) {
            textView2.setTextColor(Color.parseColor(string5));
        }
        JSONObject jSONObject3 = jSONArray.getJSONObject(1);
        String string6 = jSONObject3.getString("title");
        String string7 = jSONObject3.getString("sub_title");
        String string8 = jSONObject3.getString("icon_url");
        String string9 = jSONObject3.getString("title_color");
        String string10 = jSONObject3.getString("tag_bg_color");
        TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.two_title);
        TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.two_sub_title);
        textView3.setText(string6);
        textView4.setText(string7);
        ImageTools.m14149a(string8, (ImageView) inflate.findViewById(C2425R.C2427id.two_img), GBApplication.instance().options);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.two_layout)).setOnClickListener(new CommonClickListener(context, jSONObject3));
        if (!C3467s.m14513e(string9)) {
            textView3.setTextColor(Color.parseColor(string9));
        }
        if (!C3467s.m14513e(string10)) {
            textView4.setTextColor(Color.parseColor(string10));
        }
        JSONObject jSONObject4 = jSONArray.getJSONObject(2);
        String string11 = jSONObject4.getString("title");
        String string12 = jSONObject4.getString("sub_title");
        String string13 = jSONObject4.getString("icon_url");
        String string14 = jSONObject4.getString("title_color");
        String string15 = jSONObject4.getString("tag_bg_color");
        TextView textView5 = (TextView) inflate.findViewById(C2425R.C2427id.three_title);
        TextView textView6 = (TextView) inflate.findViewById(C2425R.C2427id.three_sub_title);
        textView5.setText(string11);
        textView6.setText(string12);
        ImageTools.m14149a(string13, (ImageView) inflate.findViewById(C2425R.C2427id.three_img), GBApplication.instance().options);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.three_layout)).setOnClickListener(new CommonClickListener(context, jSONObject4));
        if (!C3467s.m14513e(string14)) {
            textView5.setTextColor(Color.parseColor(string14));
        }
        if (!C3467s.m14513e(string15)) {
            textView6.setTextColor(Color.parseColor(string15));
        }
        JSONObject jSONObject5 = jSONArray.getJSONObject(3);
        String string16 = jSONObject5.getString("title");
        String string17 = jSONObject5.getString("sub_title");
        String string18 = jSONObject5.getString("icon_url");
        String string19 = jSONObject5.getString("title_color");
        String string20 = jSONObject5.getString("tag_bg_color");
        TextView textView7 = (TextView) inflate.findViewById(C2425R.C2427id.four_title);
        TextView textView8 = (TextView) inflate.findViewById(C2425R.C2427id.four_sub_title);
        textView7.setText(string16);
        textView8.setText(string17);
        ImageTools.m14149a(string18, (ImageView) inflate.findViewById(C2425R.C2427id.four_img), GBApplication.instance().options);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.four_layout)).setOnClickListener(new CommonClickListener(context, jSONObject5));
        if (!C3467s.m14513e(string19)) {
            textView7.setTextColor(Color.parseColor(string19));
        }
        if (!C3467s.m14513e(string20)) {
            textView8.setTextColor(Color.parseColor(string20));
        }
        return inflate;
    }
}
