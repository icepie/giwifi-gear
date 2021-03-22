package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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

/* renamed from: com.gbcom.gwifi.functions.template.builder.o */
public class LeftOneRightTowCommendBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() < 3) {
            return null;
        }
        View inflate = LayoutInflater.from(context).inflate(C2425R.layout.tp_left_one_right_two_commend, viewGroup, false);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.commend)).getLayoutParams().height = (DensityUtil.m14127a(context) * 2) / 3;
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131755605);
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.left_iv);
        TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.left_tv);
        TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.left_update);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(C2425R.C2427id.left_bg);
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(C2425R.C2427id.right_one_layout);
        ImageView imageView2 = (ImageView) inflate.findViewById(C2425R.C2427id.right_one_iv);
        TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.right_one_tv);
        TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.right_one_yellow);
        LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(C2425R.C2427id.right_one_bg);
        LinearLayout linearLayout5 = (LinearLayout) inflate.findViewById(C2425R.C2427id.right_two_layout);
        ImageView imageView3 = (ImageView) inflate.findViewById(C2425R.C2427id.right_two_iv);
        TextView textView5 = (TextView) inflate.findViewById(C2425R.C2427id.right_two_tv);
        TextView textView6 = (TextView) inflate.findViewById(C2425R.C2427id.right_two_green);
        LinearLayout linearLayout6 = (LinearLayout) inflate.findViewById(C2425R.C2427id.right_two_bg);
        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
        String string = jSONObject2.getString("title");
        String string2 = jSONObject2.getString("sub_title");
        String string3 = jSONObject2.getString("title_color");
        String string4 = jSONObject2.getString("tag_bg_color");
        String string5 = jSONObject2.getString("icon_url");
        if (!C3467s.m14513e(string2)) {
            textView2.setText(string2);
        }
        ImageTools.m14149a(string5, imageView, GBApplication.instance().options);
        if (!C3467s.m14513e(string)) {
            linearLayout2.setVisibility(0);
            textView.setText(string);
            if (!C3467s.m14513e(string3)) {
                textView.setTextColor(Color.parseColor(string3));
            }
            if (!C3467s.m14513e(string4)) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setColor(Color.parseColor(string4));
                linearLayout2.setBackgroundDrawable(gradientDrawable);
            }
            linearLayout.setOnClickListener(new CommonClickListener(context, jSONObject2));
        } else {
            linearLayout2.setVisibility(8);
        }
        JSONObject jSONObject3 = jSONArray.getJSONObject(1);
        String string6 = jSONObject3.getString("title");
        String string7 = jSONObject3.getString("sub_title");
        String string8 = jSONObject3.getString("title_color");
        String string9 = jSONObject3.getString("tag_bg_color");
        String string10 = jSONObject3.getString("icon_url");
        if (!C3467s.m14513e(string7)) {
            textView4.setText(string7);
        }
        ImageTools.m14149a(string10, imageView2, GBApplication.instance().options);
        if (!C3467s.m14513e(string6)) {
            linearLayout4.setVisibility(0);
            textView3.setText(string6);
            if (!C3467s.m14513e(string8)) {
                textView3.setTextColor(Color.parseColor(string8));
            }
            if (!C3467s.m14513e(string9)) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setColor(Color.parseColor(string9));
                linearLayout4.setBackgroundDrawable(gradientDrawable2);
            }
            linearLayout3.setOnClickListener(new CommonClickListener(context, jSONObject3));
        } else {
            linearLayout4.setVisibility(8);
        }
        JSONObject jSONObject4 = jSONArray.getJSONObject(2);
        String string11 = jSONObject4.getString("title");
        String string12 = jSONObject4.getString("sub_title");
        String string13 = jSONObject4.getString("title_color");
        String string14 = jSONObject4.getString("tag_bg_color");
        String string15 = jSONObject4.getString("icon_url");
        if (!C3467s.m14513e(string12)) {
            textView6.setText(string12);
        }
        ImageTools.m14149a(string15, imageView3, GBApplication.instance().options);
        if (!C3467s.m14513e(string11)) {
            linearLayout6.setVisibility(0);
            textView5.setText(string11);
            if (!C3467s.m14513e(string13)) {
                textView5.setTextColor(Color.parseColor(string13));
            }
            if (!C3467s.m14513e(string14)) {
                GradientDrawable gradientDrawable3 = new GradientDrawable();
                gradientDrawable3.setColor(Color.parseColor(string14));
                linearLayout6.setBackgroundDrawable(gradientDrawable3);
            }
            linearLayout5.setOnClickListener(new CommonClickListener(context, jSONObject4));
        } else {
            linearLayout6.setVisibility(8);
        }
        return inflate;
    }
}
