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

/* renamed from: com.gbcom.gwifi.functions.template.builder.y */
public class ThreeColumnGibuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() == 0) {
            return null;
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        for (int i = 0; i < jSONArray.length(); i += 3) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            View inflate = LayoutInflater.from(context).inflate(C2425R.layout.tp_three_column_gi, viewGroup, false);
            ((LinearLayout) inflate.findViewById(C2425R.C2427id.three_column)).getLayoutParams().height = (DensityUtil.m14127a(context) * 1) / 3;
            TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.one_title);
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.one_img);
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(C2425R.C2427id.one_layout);
            String string = jSONObject2.getString("title");
            String string2 = jSONObject2.getString("icon_url");
            String string3 = jSONObject2.getString("title_color");
            if (!C3467s.m14513e(string3)) {
                textView.setTextColor(Color.parseColor(string3));
            }
            textView.setText(string);
            ImageTools.m14149a(string2, imageView, GBApplication.instance().options);
            linearLayout2.setOnClickListener(new CommonClickListener(context, jSONObject2));
            LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(C2425R.C2427id.two_layout);
            if (i + 1 < jSONArray.length()) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i + 1);
                TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.two_title);
                ImageView imageView2 = (ImageView) inflate.findViewById(C2425R.C2427id.two_img);
                String string4 = jSONObject3.getString("title");
                String string5 = jSONObject3.getString("icon_url");
                String string6 = jSONObject3.getString("title_color");
                if (!C3467s.m14513e(string6)) {
                    textView2.setTextColor(Color.parseColor(string6));
                }
                textView2.setText(string4);
                ImageTools.m14149a(string5, imageView2, GBApplication.instance().options);
                linearLayout3.setOnClickListener(new CommonClickListener(context, jSONObject3));
            } else {
                linearLayout3.setVisibility(4);
            }
            LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(C2425R.C2427id.three_layout);
            if (i + 2 < jSONArray.length()) {
                JSONObject jSONObject4 = jSONArray.getJSONObject(i + 2);
                TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.three_title);
                ImageView imageView3 = (ImageView) inflate.findViewById(C2425R.C2427id.three_img);
                String string7 = jSONObject4.getString("title");
                String string8 = jSONObject4.getString("icon_url");
                String string9 = jSONObject4.getString("title_color");
                if (!C3467s.m14513e(string9)) {
                    textView3.setTextColor(Color.parseColor(string9));
                }
                textView3.setText(string7);
                ImageTools.m14149a(string8, imageView3, GBApplication.instance().options);
                linearLayout4.setOnClickListener(new CommonClickListener(context, jSONObject4));
            } else {
                linearLayout4.setVisibility(4);
            }
            linearLayout.addView(inflate);
        }
        if (linearLayout.getChildCount() == 0) {
            return null;
        }
        return linearLayout;
    }
}
