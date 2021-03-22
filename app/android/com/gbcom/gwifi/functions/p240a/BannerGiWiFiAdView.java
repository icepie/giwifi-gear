package com.gbcom.gwifi.functions.p240a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.p252a.CommonGiWiFiAdClickListener;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import java.util.HashMap;

/* renamed from: com.gbcom.gwifi.functions.a.b */
public class BannerGiWiFiAdView implements AdView {
    @Override // com.gbcom.gwifi.functions.p240a.AdView
    /* renamed from: a */
    public View mo24512a(Context context, ViewGroup viewGroup, HashMap hashMap) {
        View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_image_build, viewGroup, true);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.ll_img)).getLayoutParams().height = (DensityUtil.m14127a(context) * 2) / 3;
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.root_image);
        imageView.setOnClickListener(new CommonGiWiFiAdClickListener(context, hashMap, 0));
        if (!hashMap.containsKey("localImgUrl") || C3467s.m14513e((String) hashMap.get("localImgUrl"))) {
            return null;
        }
        ImageTools.m14149a((String) hashMap.get("localImgUrl"), imageView, GBApplication.instance().options);
        return inflate;
    }

    @Override // com.gbcom.gwifi.functions.p240a.AdView
    /* renamed from: a */
    public void mo24513a() {
    }
}
