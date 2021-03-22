package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
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
import com.gbcom.gwifi.util.p257b.ImageTools;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.z */
public class ThreeColumnWithBorderBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() == 0) {
            return null;
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        for (int i = 0; i < jSONArray.length(); i += 3) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_three_column_border_item, (ViewGroup) null);
            String string = jSONObject2.getString("title");
            String string2 = jSONObject2.getString("icon_url");
            ((TextView) inflate.findViewById(C2425R.C2427id.first_item_title)).setText(string);
            ImageTools.m14149a(string2, (ImageView) inflate.findViewById(C2425R.C2427id.first_item_img), GBApplication.instance().options);
            ((RelativeLayout) inflate.findViewById(C2425R.C2427id.first_item)).setOnClickListener(new CommonClickListener(context, jSONObject2));
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C2425R.C2427id.second_item);
            if (i + 1 < jSONArray.length()) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i + 1);
                String string3 = jSONObject3.getString("title");
                String string4 = jSONObject3.getString("icon_url");
                ((TextView) inflate.findViewById(C2425R.C2427id.second_item_title)).setText(string3);
                ImageTools.m14149a(string4, (ImageView) inflate.findViewById(C2425R.C2427id.second_item_img), GBApplication.instance().options);
                relativeLayout.setOnClickListener(new CommonClickListener(context, jSONObject3));
            } else {
                relativeLayout.setVisibility(4);
            }
            RelativeLayout relativeLayout2 = (RelativeLayout) inflate.findViewById(C2425R.C2427id.third_item);
            if (i + 2 < jSONArray.length()) {
                JSONObject jSONObject4 = jSONArray.getJSONObject(i + 2);
                String string5 = jSONObject4.getString("title");
                String string6 = jSONObject4.getString("icon_url");
                ((TextView) inflate.findViewById(C2425R.C2427id.third_item_title)).setText(string5);
                ImageTools.m14149a(string6, (ImageView) inflate.findViewById(C2425R.C2427id.third_item_img), GBApplication.instance().options);
                relativeLayout2.setOnClickListener(new CommonClickListener(context, jSONObject4));
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
