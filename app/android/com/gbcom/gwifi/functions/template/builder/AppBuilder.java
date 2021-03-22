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
import com.gbcom.gwifi.functions.template.p252a.CommonLayoutClickListener;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.p257b.ImageTools;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.b */
public class AppBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() == 0) {
            return null;
        }
        View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_app, viewGroup, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131755301);
        ((TextView) inflate.findViewById(C2425R.C2427id.tv_balance)).setText("(旺豆奖励)");
        String string = jSONObject.getString("child_layout_title");
        ((TextView) inflate.findViewById(C2425R.C2427id.sub_title)).setText(jSONObject.getString("child_layout_sub_title"));
        ((TextView) inflate.findViewById(2131755156)).setText(string);
        ((RelativeLayout) inflate.findViewById(C2425R.C2427id.more)).setOnClickListener(new CommonLayoutClickListener(context, jSONObject));
        int length = jSONArray.length() <= 3 ? jSONArray.length() : 3;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            View inflate2 = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_app_item, (ViewGroup) linearLayout, false);
            ((TextView) inflate2.findViewById(C2425R.C2427id.item_title)).setText(jSONObject2.getString("title"));
            inflate2.setOnClickListener(new CommonClickListener(context, jSONObject2));
            ImageView imageView = (ImageView) inflate2.findViewById(C2425R.C2427id.item_icon);
            String string2 = jSONObject2.getString("icon_url");
            if (C3467s.m14513e(string2) || string2.length() <= 5) {
                imageView.setImageResource(C2425R.C2426drawable.loading_small);
            } else {
                ImageTools.m14149a(string2, imageView, GBApplication.instance().options);
            }
            String string3 = jSONObject2.getString("sub_title");
            ((TextView) inflate2.findViewById(C2425R.C2427id.item_point)).setText(jSONObject2.getString("tag_name"));
            ((TextView) inflate2.findViewById(C2425R.C2427id.item_size)).setText(string3);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
            layoutParams.weight = 1.0f;
            inflate2.setLayoutParams(layoutParams);
            linearLayout.addView(inflate2, linearLayout.getChildCount());
        }
        return inflate;
    }
}
