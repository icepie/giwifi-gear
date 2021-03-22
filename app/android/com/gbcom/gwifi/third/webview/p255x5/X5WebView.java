package com.gbcom.gwifi.third.webview.p255x5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/* renamed from: com.gbcom.gwifi.third.webview.x5.X5WebView */
public class X5WebView extends WebView {
    private WebViewClient client = new WebViewClient() {
        /* class com.gbcom.gwifi.third.webview.p255x5.X5WebView.C34431 */

        @Override // com.tencent.smtt.sdk.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    };
    TextView title;

    @SuppressLint({"SetJavaScriptEnabled"})
    public X5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initWebViewSettings();
        getView().setClickable(true);
    }

    private void initWebViewSettings() {
        WebSettings settings = getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setSupportMultipleWindows(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        settings.setCacheMode(2);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setSavePassword(false);
    }

    public X5WebView(Context context) {
        super(context);
        setBackgroundColor(85621);
    }
}
