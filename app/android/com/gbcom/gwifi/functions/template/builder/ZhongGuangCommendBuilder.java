package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.os.Build;
import android.support.p009v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.functions.wifi.p253a.WifiState;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.al */
public class ZhongGuangCommendBuilder implements ViewBuilder, OnDestoryBuilder {

    /* renamed from: a */
    private LinearLayout f11766a;

    /* renamed from: b */
    private WebView f11767b;

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        if (!(this.f11767b == null || this.f11766a == null)) {
            this.f11767b.removeAllViews();
            this.f11766a.removeView(this.f11767b);
            this.f11767b.destroy();
        }
        this.f11766a = (LinearLayout) LayoutInflater.from(context).inflate(C2425R.layout.tp_zhongguang_commend, viewGroup, false);
        this.f11767b = new WebView(context);
        ((LinearLayout) this.f11766a.findViewById(C2425R.C2427id.ll_web)).addView(this.f11767b);
        this.f11767b.getSettings().setUseWideViewPort(true);
        this.f11767b.getSettings().setGeolocationEnabled(true);
        if (Build.VERSION.SDK_INT >= 19) {
            this.f11767b.getSettings().setCacheMode(-1);
        }
        this.f11767b.getSettings().setDisplayZoomControls(false);
        this.f11767b.getSettings().setDomStorageEnabled(true);
        this.f11767b.getSettings().setSaveFormData(true);
        this.f11767b.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        this.f11767b.getSettings().setLoadWithOverviewMode(true);
        this.f11767b.getSettings().setSavePassword(false);
        this.f11767b.setWebViewClient(new WebViewClient() {
            /* class com.gbcom.gwifi.functions.template.builder.ZhongGuangCommendBuilder.C31371 */

            @Override // com.tencent.smtt.sdk.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!CacheWiFi.getInstance().isInnerWifi()) {
                    GBActivity.showMessageToast("请连接giwifi网络浏览");
                } else if (CacheWiFi.getInstance().getCurrentState() != WifiState.SUCCESS) {
                    GBActivity.showMessageToast("请认证后浏览");
                } else {
                    GBActivity.openBackWebView(str, "");
                }
                return true;
            }
        });
        this.f11767b.loadUrl("file:///android_asset/test.html");
        return this.f11766a;
    }

    @Override // com.gbcom.gwifi.functions.template.builder.OnDestoryBuilder
    /* renamed from: a */
    public void mo26983a() {
        if (this.f11767b != null && this.f11766a != null) {
            this.f11767b.loadUrl("javascript:(function(){var video =document.getElementsByTagName('video')[0];video.pause();document.webkitCancelFullScreen();})()");
        }
    }
}
