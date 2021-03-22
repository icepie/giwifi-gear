package com.gbcom.gwifi.functions.template.builder;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.support.p009v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.js2app.X5Js2AppUtil;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.URLUtil;
import com.gbcom.gwifi.widget.LoadingView;
import com.gbcom.gwifi.widget.LoadingWebView;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.ak */
public class X5WebViewBuilder implements ViewBuilder {

    /* renamed from: h */
    private static final String f11754h = "X5WebViewBuilder";

    /* renamed from: a */
    public String f11755a = "";

    /* renamed from: b */
    public String f11756b = "";

    /* renamed from: c */
    public LoadingWebView f11757c;

    /* renamed from: d */
    public ProgressBar f11758d;

    /* renamed from: e */
    public boolean f11759e;

    /* renamed from: f */
    public WebView f11760f;

    /* renamed from: g */
    public RelativeLayout f11761g;

    /* renamed from: i */
    private LoadingView.AbstractC3488a f11762i = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.template.builder.X5WebViewBuilder.C31363 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            X5WebViewBuilder.this.mo26992a();
            X5WebViewBuilder.this.f11760f.reload();
        }
    };

    /* renamed from: a */
    public void mo26992a() {
        if (this.f11760f != null) {
            this.f11760f.setVisibility(8);
        }
        this.f11757c.mo28327i();
        this.f11757c.mo28333o();
        this.f11757c.mo28335q();
        if (this.f11757c.mo28330l()) {
            this.f11757c.mo28329k();
        }
        this.f11757c.setVisibility(0);
        this.f11757c.mo28317a();
        this.f11757c.mo28322d();
        this.f11757c.mo28324f();
        this.f11757c.mo28320b();
    }

    /* renamed from: b */
    public void mo26993b() {
        if (this.f11760f != null) {
            this.f11760f.setVisibility(8);
        }
        this.f11757c.setVisibility(0);
        this.f11757c.mo28323e();
        this.f11757c.mo28321c();
        this.f11757c.mo28325g();
        this.f11757c.mo28326h();
        this.f11757c.mo28328j();
        this.f11757c.mo28332n();
        this.f11757c.mo28334p();
    }

    /* renamed from: c */
    public void mo26994c() {
        if (this.f11760f != null) {
            this.f11760f.setVisibility(0);
        }
        this.f11757c.setVisibility(8);
        this.f11757c.mo28321c();
    }

    /* renamed from: a */
    private void m12856a(Context context, ViewGroup viewGroup, String str) {
        this.f11760f = (WebView) this.f11761g.findViewById(C2425R.C2427id.x5webview);
        this.f11758d = (ProgressBar) this.f11761g.findViewById(C2425R.C2427id.WebViewProgress);
        this.f11758d.setMax(100);
        this.f11760f.setWebViewClient(new WebViewClient() {
            /* class com.gbcom.gwifi.functions.template.builder.X5WebViewBuilder.C31341 */

            @Override // com.tencent.smtt.sdk.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Log.m14400c(X5WebViewBuilder.f11754h, "url:" + str);
                if (X5Js2AppUtil.processURL(webView, str)) {
                    return true;
                }
                Uri parse = Uri.parse(str);
                Log.m14400c(X5WebViewBuilder.f11754h, "uri:" + ((Object) parse));
                String scheme = parse.getScheme();
                if ("alipays".equals(scheme) || "weixin".equals(scheme)) {
                    try {
                        GBApplication.instance().getCurrentActivity().startActivity(new Intent("android.intent.action.VIEW", parse));
                        return true;
                    } catch (ActivityNotFoundException e) {
                        return true;
                    }
                } else if (!str.startsWith("taobao://") || !SystemUtil.m14522a(GBApplication.instance(), AgooConstants.TAOBAO_PACKAGE)) {
                    String decode = Uri.decode(str);
                    if (decode.startsWith(WebView.SCHEME_TEL)) {
                        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
                        if (currentActivity == null) {
                            return true;
                        }
                        currentActivity.callPhone(decode.substring(4));
                        return true;
                    } else if (decode.startsWith("sms:")) {
                        GBApplication.instance().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(decode)));
                        return true;
                    } else if (decode.equals("gbcom://com.gbcom.gwifi") || decode.equals("gbcom://com.gbcom.gwifi.school")) {
                        X5WebViewBuilder.this.mo26993b();
                        return true;
                    } else if (decode.startsWith("http:") || decode.startsWith("https:") || decode.startsWith("ftp")) {
                        return false;
                    } else {
                        try {
                            Intent intent = new Intent("android.intent.action.VIEW", parse);
                            List<ResolveInfo> queryIntentActivities = GBApplication.instance().getPackageManager().queryIntentActivities(intent, 65536);
                            if (queryIntentActivities == null || queryIntentActivities.size() == 0) {
                                return true;
                            }
                            GBApplication.instance().startActivity(intent);
                            return true;
                        } catch (ActivityNotFoundException e2) {
                            return true;
                        }
                    }
                } else {
                    try {
                        Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.VIEW");
                        intent2.setData(parse);
                        GBApplication.instance().getCurrentActivity().startActivity(intent2);
                        return true;
                    } catch (Exception e3) {
                        return true;
                    }
                }
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                CookieManager.getInstance().getCookie(str);
                if (webView != null) {
                    webView.getSettings().setSavePassword(false);
                    X5WebViewBuilder.this.f11758d.setVisibility(8);
                    if (X5WebViewBuilder.this.f11759e || str.equals(X5WebViewBuilder.this.f11756b)) {
                        X5WebViewBuilder.this.mo26993b();
                        X5WebViewBuilder.this.f11759e = false;
                    }
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    int i = displayMetrics.heightPixels;
                    int i2 = displayMetrics.widthPixels;
                }
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                X5WebViewBuilder.this.f11759e = true;
                if (webView != null) {
                    webView.getSettings().setSavePassword(false);
                    X5WebViewBuilder.this.f11756b = str2;
                    webView.loadDataWithBaseURL(str2, "", "text/html", "utf-8", str2);
                }
                X5WebViewBuilder.this.mo26993b();
                super.onReceivedError(webView, i, str, str2);
            }
        });
        this.f11760f.setWebChromeClient(new WebChromeClient() {
            /* class com.gbcom.gwifi.functions.template.builder.X5WebViewBuilder.C31352 */

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                if (i >= 100) {
                    X5WebViewBuilder.this.f11758d.setVisibility(8);
                    return;
                }
                if (X5WebViewBuilder.this.f11758d.getVisibility() == 8) {
                    X5WebViewBuilder.this.f11758d.setVisibility(0);
                }
                try {
                    X5WebViewBuilder.this.f11758d.setProgress(webView.getProgress());
                    if (webView.getProgress() > 5) {
                        X5WebViewBuilder.this.mo26994c();
                    }
                } catch (Exception e) {
                }
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissionsCallback geolocationPermissionsCallback) {
                geolocationPermissionsCallback.invoke(str, true, false);
            }
        });
        WebSettings settings = this.f11760f.getSettings();
        settings.setAllowFileAccess(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportMultipleWindows(false);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setAppCachePath(context.getDir("appcache", 0).getPath());
        settings.setDatabasePath(context.getDir("databases", 0).getPath());
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setGeolocationEnabled(true);
        settings.setGeolocationDatabasePath(context.getFilesDir().getPath());
        settings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setCacheMode(-1);
        }
        settings.setSaveFormData(true);
        settings.setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        settings.setSavePassword(false);
        this.f11760f.requestFocus();
        this.f11760f.setVerticalScrollBarEnabled(true);
        this.f11760f.setHorizontalScrollBarEnabled(true);
        long currentTimeMillis = System.currentTimeMillis();
        this.f11760f.loadUrl(str);
        Log.m14398b("time-cost", "cost time: " + (System.currentTimeMillis() - currentTimeMillis));
        CookieSyncManager.createInstance(context);
        CookieSyncManager.getInstance().sync();
    }

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        if (!(this.f11760f == null || this.f11761g == null)) {
            this.f11760f.removeAllViews();
            this.f11761g.removeView(this.f11760f);
            this.f11760f.destroy();
        }
        if (jSONObject == null) {
            return null;
        }
        String string = jSONObject.getString("layout_wap_url");
        if (C3467s.m14513e(string)) {
            return null;
        }
        String a = URLUtil.m14541a(string);
        this.f11761g = (RelativeLayout) LayoutInflater.from(context).inflate(C2425R.layout.tp_web_view_x5, viewGroup, false);
        this.f11757c = (LoadingWebView) this.f11761g.findViewById(C2425R.C2427id.loading_view);
        this.f11757c.mo28318a(this.f11762i);
        mo26992a();
        m12856a(context, viewGroup, a);
        return this.f11761g;
    }
}
