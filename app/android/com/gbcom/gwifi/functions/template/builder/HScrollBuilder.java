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
import android.widget.RelativeLayout;
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

/* renamed from: com.gbcom.gwifi.functions.template.builder.j */
public class HScrollBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        View inflate = LayoutInflater.from(context).inflate(C2425R.layout.tp_hscroll, viewGroup, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C2425R.C2427id.scroll_layout);
        JSONArray jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS);
        for (int i = 0; i < jSONArray.length(); i++) {
            View inflate2 = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_hscroll_item, (ViewGroup) linearLayout, false);
            int a = DensityUtil.m14127a(context);
            RelativeLayout relativeLayout = (RelativeLayout) inflate2.findViewById(C2425R.C2427id.post_rl);
            relativeLayout.getLayoutParams().width = (a * 2) / 9;
            relativeLayout.getLayoutParams().height = (a * 2) / 9;
            ImageView imageView = (ImageView) inflate2.findViewById(C2425R.C2427id.post_img);
            TextView textView = (TextView) inflate2.findViewById(C2425R.C2427id.post_text);
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString("icon_url");
            String string2 = jSONObject2.getString("tag_name");
            String string3 = jSONObject2.getString("tag_bg_color");
            ImageTools.m14149a(string, imageView, GBApplication.instance().options);
            imageView.setTag(new CommonClickListener(context, jSONObject2));
            imageView.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.template.builder.HScrollBuilder.View$OnClickListenerC31481 */

                public void onClick(View view) {
                    ((CommonClickListener) view.getTag()).onClick(view);
                }
            });
            if (!C3467s.m14513e(string2)) {
                textView.setText(string2);
            } else {
                textView.setVisibility(8);
            }
            if (!C3467s.m14513e(string3)) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(15.0f);
                gradientDrawable.setColor(Color.parseColor(string3));
                textView.setPadding(DensityUtil.m14128a(GBApplication.instance(), 2.0f), 0, DensityUtil.m14128a(GBApplication.instance(), 2.0f), 0);
                textView.setBackgroundDrawable(gradientDrawable);
            }
            linearLayout.addView(inflate2);
        }
        if (linearLayout.getChildCount() == 0) {
            return null;
        }
        return inflate;
    }
}
