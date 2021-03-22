package com.gbcom.gwifi.util;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import com.gbcom.gwifi.base.app.GBApplication;

/* renamed from: com.gbcom.gwifi.util.x */
public class WebViewUtil {
    /* renamed from: a */
    public static void m14568a() {
        Context applicationContext = GBApplication.instance().getApplicationContext();
        CookieSyncManager.createInstance(applicationContext);
        CookieManager instance = CookieManager.getInstance();
        instance.removeAllCookie();
        instance.removeSessionCookie();
        CookieSyncManager.createInstance(applicationContext);
        CookieManager instance2 = CookieManager.getInstance();
        instance2.removeAllCookie();
        instance2.removeSessionCookie();
        applicationContext.deleteDatabase("webview.db");
        applicationContext.deleteDatabase("webviewCache.db");
        new WebView(applicationContext).clearCache(true);
        new com.tencent.smtt.sdk.WebView(applicationContext).clearCache(true);
    }
}
