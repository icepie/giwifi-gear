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

/* renamed from: com.gbcom.gwifi.functions.template.builder.aa */
public class TopOneBottomScrollBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() == 0) {
            return null;
        }
        View inflate = LayoutInflater.from(context).inflate(C2425R.layout.tp_top_one_bottom_scroll, viewGroup, false);
        int a = DensityUtil.m14127a(context);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131755407);
        TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.title_top_one_bottom_scroll);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(C2425R.C2427id.top_one_layout);
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.top_one_iv);
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(C2425R.C2427id.bottom_layout);
        LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(C2425R.C2427id.scroll_layout);
        if (jSONArray.length() == 1) {
            linearLayout.getLayoutParams().height = (a * 15) / 33;
            linearLayout3.setVisibility(8);
        } else {
            linearLayout.getLayoutParams().height = (a * 2) / 3;
            linearLayout3.setVisibility(0);
        }
        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
        String string = jSONObject2.getString("icon_url");
        String string2 = jSONObject2.getString("title");
        String string3 = jSONObject2.getString("title_color");
        if (!C3467s.m14513e(string2)) {
            textView.setText(string2);
            if (!C3467s.m14513e(string3)) {
                textView.setTextColor(Color.parseColor(string3));
            }
        }
        ImageTools.m14149a(string, imageView, GBApplication.instance().options);
        linearLayout2.setOnClickListener(new CommonClickListener(context, jSONObject2));
        if (jSONArray.length() > 1) {
            for (int i = 1; i < jSONArray.length(); i++) {
                View inflate2 = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_bottom_scroll_item, (ViewGroup) linearLayout4, false);
                inflate2.getLayoutParams().width = (a * 7) / 16;
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                String string4 = jSONObject3.getString("title");
                String string5 = jSONObject3.getString("icon_url");
                ((TextView) inflate2.findViewById(C2425R.C2427id.item_title)).setText(string4);
                ImageTools.m14149a(string5, (ImageView) inflate2.findViewById(C2425R.C2427id.item_icon), GBApplication.instance().options);
                ((LinearLayout) inflate2.findViewById(C2425R.C2427id.item_scroll_layout)).setOnClickListener(new CommonClickListener(context, jSONObject3));
                linearLayout4.addView(inflate2);
            }
        }
        return inflate;
    }
}
