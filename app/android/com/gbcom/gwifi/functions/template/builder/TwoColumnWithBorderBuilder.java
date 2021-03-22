package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.graphics.Color;
import android.support.p009v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.p257b.ImageTools;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.ac */
public class TwoColumnWithBorderBuilder implements ViewBuilder {
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
        for (int i = 0; i < jSONArray.length(); i += 2) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_two_column_border_item, (ViewGroup) null);
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C2425R.C2427id.left_item);
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.left_item_img);
            TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.left_item_title);
            TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.left_item_sub_title);
            String string = jSONObject2.getString("title");
            String string2 = jSONObject2.getString("sub_title");
            String string3 = jSONObject2.getString("icon_url");
            String string4 = jSONObject2.getString("title_color");
            textView.setText(string);
            if (!C3467s.m14513e(string4)) {
                textView.setTextColor(Color.parseColor(string4));
            }
            textView2.setText(string2);
            ImageTools.m14149a(string3, imageView, GBApplication.instance().options);
            relativeLayout.setOnClickListener(new CommonClickListener(context, jSONObject2));
            RelativeLayout relativeLayout2 = (RelativeLayout) inflate.findViewById(C2425R.C2427id.right_item);
            if (i + 1 < jSONArray.length()) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i + 1);
                ImageView imageView2 = (ImageView) inflate.findViewById(C2425R.C2427id.right_item_img);
                TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.right_item_title);
                TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.right_item_sub_title);
                String string5 = jSONObject3.getString("title");
                String string6 = jSONObject3.getString("sub_title");
                String string7 = jSONObject3.getString("icon_url");
                String string8 = jSONObject3.getString("title_color");
                textView3.setText(string5);
                if (!C3467s.m14513e(string8)) {
                    textView3.setTextColor(Color.parseColor(string8));
                }
                textView4.setText(string6);
                ImageTools.m14149a(string7, imageView2, GBApplication.instance().options);
                relativeLayout2.setOnClickListener(new CommonClickListener(context, jSONObject3));
            } else {
                relativeLayout2.setVisibility(4);
            }
            linearLayout.addView(inflate);
        }
        if (linearLayout.getChildCount() == 0) {
            return null;
        }
        return linearLayout;
    }
}
