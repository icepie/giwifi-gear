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

/* renamed from: com.gbcom.gwifi.functions.template.builder.p */
public class LeftOneRightTowVideoBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() < 3) {
            return null;
        }
        View inflate = LayoutInflater.from(context).inflate(C2425R.layout.tp_left_one_right_two_video, viewGroup, false);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.commend)).getLayoutParams().height = (DensityUtil.m14127a(context) * 1) / 2;
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131755605);
        TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.left_tv);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(C2425R.C2427id.left_bg);
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(C2425R.C2427id.right_one_layout);
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.right_one_iv);
        TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.right_one_tv);
        LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(C2425R.C2427id.right_one_bg);
        LinearLayout linearLayout5 = (LinearLayout) inflate.findViewById(C2425R.C2427id.right_two_layout);
        ImageView imageView2 = (ImageView) inflate.findViewById(C2425R.C2427id.right_two_iv);
        TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.right_two_tv);
        LinearLayout linearLayout6 = (LinearLayout) inflate.findViewById(C2425R.C2427id.right_two_bg);
        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
        String string = jSONObject2.getString("title");
        String string2 = jSONObject2.getString("title_color");
        String string3 = jSONObject2.getString("tag_bg_color");
        ImageTools.m14149a(jSONObject2.getString("icon_url"), (ImageView) inflate.findViewById(C2425R.C2427id.left_iv), GBApplication.instance().options);
        if (!C3467s.m14513e(string)) {
            linearLayout2.setVisibility(0);
            textView.setText(string);
            if (!C3467s.m14513e(string2)) {
                textView.setTextColor(Color.parseColor(string2));
            }
            if (!C3467s.m14513e(string3)) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setColor(Color.parseColor(string3));
                linearLayout2.setBackgroundDrawable(gradientDrawable);
            }
            linearLayout.setOnClickListener(new CommonClickListener(context, jSONObject2));
        } else {
            linearLayout2.setVisibility(8);
        }
        JSONObject jSONObject3 = jSONArray.getJSONObject(1);
        String string4 = jSONObject3.getString("title");
        String string5 = jSONObject3.getString("title_color");
        String string6 = jSONObject3.getString("tag_bg_color");
        ImageTools.m14149a(jSONObject3.getString("icon_url"), imageView, GBApplication.instance().options);
        if (!C3467s.m14513e(string4)) {
            linearLayout4.setVisibility(0);
            textView2.setText(string4);
            if (!C3467s.m14513e(string5)) {
                textView2.setTextColor(Color.parseColor(string5));
            }
            if (!C3467s.m14513e(string6)) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setColor(Color.parseColor(string6));
                linearLayout4.setBackgroundDrawable(gradientDrawable2);
            }
            linearLayout3.setOnClickListener(new CommonClickListener(context, jSONObject3));
        } else {
            linearLayout4.setVisibility(8);
        }
        JSONObject jSONObject4 = jSONArray.getJSONObject(2);
        String string7 = jSONObject4.getString("title");
        String string8 = jSONObject4.getString("title_color");
        String string9 = jSONObject4.getString("tag_bg_color");
        ImageTools.m14149a(jSONObject4.getString("icon_url"), imageView2, GBApplication.instance().options);
        if (!C3467s.m14513e(string7)) {
            linearLayout6.setVisibility(0);
            textView3.setText(string7);
            if (!C3467s.m14513e(string8)) {
                textView3.setTextColor(Color.parseColor(string8));
            }
            if (!C3467s.m14513e(string9)) {
                GradientDrawable gradientDrawable3 = new GradientDrawable();
                gradientDrawable3.setColor(Color.parseColor(string9));
                linearLayout6.setBackgroundDrawable(gradientDrawable3);
            }
            linearLayout5.setOnClickListener(new CommonClickListener(context, jSONObject4));
        } else {
            linearLayout6.setVisibility(8);
        }
        return inflate;
    }
}
