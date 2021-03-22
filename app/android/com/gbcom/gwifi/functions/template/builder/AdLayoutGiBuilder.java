package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.support.p009v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.a */
public class AdLayoutGiBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() == 0) {
            return null;
        }
        View inflate = LayoutInflater.from(context).inflate(C2425R.layout.tp_ad_gi, viewGroup, false);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.ad_layout)).getLayoutParams().height = (DensityUtil.m14127a(context) * 1) / 6;
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.ad_img);
        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
        ImageTools.m14149a(jSONObject2.getString("icon_url"), imageView, GBApplication.instance().options);
        imageView.setOnClickListener(new CommonClickListener(context, jSONObject2));
        return inflate;
    }
}
