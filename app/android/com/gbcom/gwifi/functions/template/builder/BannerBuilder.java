package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.support.p009v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import com.gbcom.gwifi.functions.p240a.AdView;
import com.gbcom.gwifi.functions.p240a.BannerGiWiFiAdView;
import com.gbcom.gwifi.util.cache.CacheAd;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.c */
public class BannerBuilder implements ViewBuilder {

    /* renamed from: a */
    private int f11769a = 0;

    /* renamed from: b */
    private int f11770b = 0;

    /* renamed from: c */
    private AdView f11771c;

    public BannerBuilder(int i) {
        this.f11769a = i;
    }

    /* renamed from: a */
    public AdView mo27000a() {
        return this.f11771c;
    }

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        return m12865a(context, viewGroup);
    }

    /* renamed from: a */
    private View m12865a(Context context, ViewGroup viewGroup) {
        if (this.f11769a == 1) {
            List<HashMap<String, Object>> adConnFlowList = CacheAd.getInstance().getAdConnFlowList();
            if (adConnFlowList == null || adConnFlowList.size() <= 0) {
                return null;
            }
            for (HashMap<String, Object> hashMap : adConnFlowList) {
                this.f11770b = ((Integer) hashMap.get("adState")).intValue();
                switch (this.f11770b) {
                    case 2:
                    case 3:
                        this.f11771c = new BannerGiWiFiAdView();
                        break;
                }
                if (this.f11771c != null) {
                    return this.f11771c.mo24512a(context, viewGroup, hashMap);
                }
            }
        } else if (this.f11769a == 2) {
            List<HashMap<String, Object>> adEntertainmentFlowList = CacheAd.getInstance().getAdEntertainmentFlowList();
            if (adEntertainmentFlowList == null || adEntertainmentFlowList.size() <= 0) {
                return null;
            }
            for (HashMap<String, Object> hashMap2 : adEntertainmentFlowList) {
                this.f11770b = ((Integer) hashMap2.get("adState")).intValue();
                switch (this.f11770b) {
                    case 2:
                    case 3:
                        this.f11771c = new BannerGiWiFiAdView();
                        break;
                }
                if (this.f11771c != null) {
                    return this.f11771c.mo24512a(context, viewGroup, hashMap2);
                }
            }
        } else if (this.f11769a == 3) {
            List<HashMap<String, Object>> adLocalFlowList = CacheAd.getInstance().getAdLocalFlowList();
            if (adLocalFlowList == null || adLocalFlowList.size() <= 0) {
                return null;
            }
            for (HashMap<String, Object> hashMap3 : adLocalFlowList) {
                this.f11770b = ((Integer) hashMap3.get("adState")).intValue();
                switch (this.f11770b) {
                    case 2:
                    case 3:
                        this.f11771c = new BannerGiWiFiAdView();
                        break;
                }
                if (this.f11771c != null) {
                    return this.f11771c.mo24512a(context, viewGroup, hashMap3);
                }
            }
        }
        return null;
    }
}
