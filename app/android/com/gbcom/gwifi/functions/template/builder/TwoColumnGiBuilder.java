package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.graphics.Color;
import android.support.p009v4.app.FragmentManager;
import android.view.LayoutInflater;
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

/* renamed from: com.gbcom.gwifi.functions.template.builder.ab */
public class TwoColumnGiBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() < 2) {
            return null;
        }
        View inflate = LayoutInflater.from(context).inflate(C2425R.layout.tp_two_column_gi, viewGroup, false);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.two_layout)).getLayoutParams().height = (DensityUtil.m14127a(context) * 1) / 3;
        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
        String string = jSONObject2.getString("title");
        String string2 = jSONObject2.getString("sub_title");
        String string3 = jSONObject2.getString("icon_url");
        String string4 = jSONObject2.getString("title_color");
        String string5 = jSONObject2.getString("tag_bg_color");
        TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.left_title);
        TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.left_sub_title);
        textView.setText(string);
        textView2.setText(string2);
        ImageTools.m14149a(string3, (ImageView) inflate.findViewById(C2425R.C2427id.left_img), GBApplication.instance().options);
        ((LinearLayout) inflate.findViewById(2131755605)).setOnClickListener(new CommonClickListener(context, jSONObject2));
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
        TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.right_title);
        TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.right_sub_title);
        textView3.setText(string6);
        textView4.setText(string7);
        ImageTools.m14149a(string8, (ImageView) inflate.findViewById(C2425R.C2427id.right_img), GBApplication.instance().options);
        ((LinearLayout) inflate.findViewById(2131755607)).setOnClickListener(new CommonClickListener(context, jSONObject3));
        if (!C3467s.m14513e(string9)) {
            textView3.setTextColor(Color.parseColor(string9));
        }
        if (!C3467s.m14513e(string10)) {
            textView4.setTextColor(Color.parseColor(string10));
        }
        return inflate;
    }
}
