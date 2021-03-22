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

/* renamed from: com.gbcom.gwifi.functions.template.builder.af */
public class VideoBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() < 3) {
            return null;
        }
        View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_video, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131755301);
        String string = jSONObject.getString("child_layout_title");
        ((TextView) inflate.findViewById(C2425R.C2427id.more_title)).setText(jSONObject.getString("child_layout_sub_title"));
        ((TextView) inflate.findViewById(2131755156)).setText(string);
        ((RelativeLayout) inflate.findViewById(C2425R.C2427id.more)).setOnClickListener(new CommonLayoutClickListener(context, jSONObject));
        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.item_icon_one);
        ((TextView) inflate.findViewById(C2425R.C2427id.item_title_one)).setText(jSONObject2.getString("title"));
        ((RelativeLayout) inflate.findViewById(C2425R.C2427id.rl_one)).setOnClickListener(new CommonClickListener(context, jSONObject2));
        String string2 = jSONObject2.getString("icon_url");
        if (C3467s.m14513e(string2) || string2.length() <= 5) {
            imageView.setImageResource(C2425R.C2426drawable.loading_small);
        } else {
            ImageTools.m14149a(string2, imageView, GBApplication.instance().options);
        }
        JSONObject jSONObject3 = jSONArray.getJSONObject(1);
        ImageView imageView2 = (ImageView) inflate.findViewById(C2425R.C2427id.item_icon_two);
        ((TextView) inflate.findViewById(C2425R.C2427id.item_title_two)).setText(jSONObject3.getString("title"));
        ((RelativeLayout) inflate.findViewById(C2425R.C2427id.rl_two)).setOnClickListener(new CommonClickListener(context, jSONObject3));
        String string3 = jSONObject3.getString("icon_url");
        if (C3467s.m14513e(string3) || string3.length() <= 5) {
            imageView2.setImageResource(C2425R.C2426drawable.loading_small);
        } else {
            ImageTools.m14149a(string3, imageView2, GBApplication.instance().options);
        }
        JSONObject jSONObject4 = jSONArray.getJSONObject(2);
        ImageView imageView3 = (ImageView) inflate.findViewById(C2425R.C2427id.item_icon_three);
        ((TextView) inflate.findViewById(C2425R.C2427id.item_title_three)).setText(jSONObject4.getString("title"));
        ((RelativeLayout) inflate.findViewById(C2425R.C2427id.rl_three)).setOnClickListener(new CommonClickListener(context, jSONObject4));
        String string4 = jSONObject4.getString("icon_url");
        if (C3467s.m14513e(string4) || string4.length() <= 5) {
            imageView3.setImageResource(C2425R.C2426drawable.loading_small);
        } else {
            ImageTools.m14149a(string4, imageView3, GBApplication.instance().options);
        }
        return inflate;
    }
}
