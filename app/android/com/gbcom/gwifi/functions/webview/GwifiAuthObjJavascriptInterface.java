package com.gbcom.gwifi.functions.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.functions.wifi.MultiCheckNetWorkUtils;
import com.gbcom.gwifi.functions.wifi.entity.CheckResult;
import com.gbcom.gwifi.util.Log;

@SuppressLint({"JavascriptInterface"})
/* renamed from: com.gbcom.gwifi.functions.webview.a */
public class GwifiAuthObjJavascriptInterface {

    /* renamed from: a */
    private static String f12758a = "GwifiAuthObjJavascriptInterface";

    /* renamed from: b */
    private Context f12759b;

    public GwifiAuthObjJavascriptInterface(Context context) {
        this.f12759b = context;
    }

    @JavascriptInterface
    /* renamed from: a */
    public void mo27412a() {
        Log.m14400c(f12758a, "auth load");
        MultiCheckNetWorkUtils.m13939a(GBApplication.instance(), new CallBack() {
            /* class com.gbcom.gwifi.functions.webview.GwifiAuthObjJavascriptInterface.C33711 */

            @Override // com.gbcom.gwifi.base.p234c.CallBack
            /* renamed from: a */
            public void mo24437a(Object obj) {
                Log.m14400c(GwifiAuthObjJavascriptInterface.f12758a, "AuthObj");
                CheckResult checkResult = (CheckResult) obj;
                if (checkResult != null && checkResult.isGiwifi() && (GwifiAuthObjJavascriptInterface.this.f12759b instanceof BackX5WebViewActivity)) {
                    ((BackX5WebViewActivity) GwifiAuthObjJavascriptInterface.this.f12759b).userLogin();
                }
            }
        });
    }

    @JavascriptInterface
    /* renamed from: b */
    public String mo27413b() {
        Toast.makeText(this.f12759b, "", 0).show();
        return "";
    }
}
