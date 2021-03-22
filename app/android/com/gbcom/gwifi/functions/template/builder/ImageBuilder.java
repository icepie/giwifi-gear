package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.support.p009v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.nostra13.universalimageloader.core.ImageLoader;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.k */
public class ImageBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        View inflate = LayoutInflater.from(context).inflate(C2425R.layout.tp_image, viewGroup, false);
        int a = DensityUtil.m14127a(context);
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.root_image);
        imageView.getLayoutParams().height = (a * 2) / 3;
        JSONArray jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS);
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
        String string = jSONObject2.getString("icon_url");
        viewGroup.addView(inflate);
        ImageTools.m14152a(string, imageView, GBApplication.instance().options, ImageLoader.DEALIMAGE.FILLWIDTH);
        imageView.setOnClickListener(new CommonClickListener(context, jSONObject2));
        return inflate;
    }
}
