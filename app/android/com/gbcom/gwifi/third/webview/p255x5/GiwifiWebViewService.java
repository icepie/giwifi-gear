package com.gbcom.gwifi.third.webview.p255x5;

import android.content.Context;
import com.gbcom.gwifi.util.Log;
import com.tencent.smtt.sdk.QbSdk;

/* renamed from: com.gbcom.gwifi.third.webview.x5.GiwifiWebViewService */
public class GiwifiWebViewService {
    private static String TAG = "GiwifiWebViewService";
    private static GiwifiWebViewService instance;

    public static GiwifiWebViewService getInstance(Context context) {
        if (instance == null) {
            instance = new GiwifiWebViewService(context);
        }
        return instance;
    }

    public GiwifiWebViewService(Context context) {
        try {
            QbSdk.initX5Environment(context, new QbSdk.PreInitCallback() {
                /* class com.gbcom.gwifi.third.webview.p255x5.GiwifiWebViewService.C34421 */

                @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
                public void onViewInitFinished(boolean z) {
                    Log.m14398b(GiwifiWebViewService.TAG, " onViewInitFinished is " + z);
                }

                @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
                public void onCoreInitFinished() {
                    Log.m14398b(GiwifiWebViewService.TAG, " onCoreInitFinished ");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
