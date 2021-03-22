package com.gbcom.gwifi.functions.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.wifi.entity.CheckResult;
import com.gbcom.gwifi.functions.wifi.p253a.LogoutCode;
import com.gbcom.gwifi.functions.wifi.p253a.WifiState;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheWiFi;

public class CheckNetWorkReceiver extends BroadcastReceiver {

    /* renamed from: a */
    public static String f12764a = "com.gbcom.gwifi.net_change";

    /* renamed from: b */
    public static String f12765b = "com.intent.action.autoCheck";

    /* renamed from: c */
    public static String f12766c = "com.intent.action.autoCheck.finish";

    /* renamed from: d */
    private static final String f12767d = "CheckNetWorkReceiver";

    /* renamed from: e */
    private boolean f12768e = false;

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.m14398b(f12767d, "onReceive " + action);
        if (action.equals(Constant.f13289cK)) {
            m13833a();
        } else if (action.equals(Constant.f13290cL)) {
            m13838d();
        }
    }

    /* renamed from: a */
    private void m13833a() {
        if (this.f12768e) {
            Log.m14398b(f12767d, "background isChecking");
            return;
        }
        this.f12768e = true;
        Log.m14400c(f12767d, "background checking...");
        m13836b();
    }

    /* renamed from: b */
    private void m13836b() {
        MultiCheckNetWorkUtils.m13944b(GBApplication.instance(), new CallBack() {
            /* class com.gbcom.gwifi.functions.wifi.CheckNetWorkReceiver.C33731 */

            @Override // com.gbcom.gwifi.base.p234c.CallBack
            /* renamed from: a */
            public void mo24437a(Object obj) {
                CheckResult checkResult = (CheckResult) obj;
                if (checkResult == null) {
                    CheckNetWorkReceiver.this.f12768e = false;
                } else if (checkResult.getOnlineState() == null) {
                    CheckNetWorkReceiver.this.f12768e = false;
                } else {
                    CheckNetWorkReceiver.this.f12768e = false;
                    switch (checkResult.getOnlineState().intValue()) {
                        case 0:
                            if (!(CacheWiFi.getInstance().getCurrentState() == WifiState.FAILED && CacheWiFi.getInstance().isInnerWifi() == checkResult.isGiwifi())) {
                                CheckNetWorkReceiver.this.m13837c();
                                break;
                            }
                        case 1:
                            if (!(CacheWiFi.getInstance().getCurrentState() == WifiState.LOGIN && CacheWiFi.getInstance().isInnerWifi() == checkResult.isGiwifi())) {
                                CheckNetWorkReceiver.this.m13837c();
                                break;
                            }
                        case 2:
                            if (!(CacheWiFi.getInstance().getCurrentState() == WifiState.SUCCESS && CacheWiFi.getInstance().isInnerWifi() == checkResult.isGiwifi())) {
                                CheckNetWorkReceiver.this.m13837c();
                                break;
                            }
                        case 200:
                            if (!(CacheWiFi.getInstance().getCurrentState() == WifiState.SUCCESS_NONET && CacheWiFi.getInstance().isInnerWifi() == checkResult.isGiwifi())) {
                                CheckNetWorkReceiver.this.m13837c();
                                break;
                            }
                    }
                    GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
                    if (currentActivity instanceof MainActivity) {
                        ((MainActivity) currentActivity).checkWifiLocation();
                        ((MainActivity) currentActivity).wifiViewResume();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m13837c() {
        Intent intent = new Intent(f12764a);
        if (GBApplication.instance() != null) {
            GBApplication.instance().sendBroadcast(intent);
        }
    }

    /* renamed from: d */
    private void m13838d() {
        MultiCheckNetWorkUtils.m13939a(GBApplication.instance(), new CallBack() {
            /* class com.gbcom.gwifi.functions.wifi.CheckNetWorkReceiver.C33742 */

            @Override // com.gbcom.gwifi.base.p234c.CallBack
            /* renamed from: a */
            public void mo24437a(Object obj) {
                CheckResult checkResult = (CheckResult) obj;
                if (checkResult != null && checkResult.isGiwifi() && LogoutCode.m13901a(CacheAuth.getInstance().getLogoutReason())) {
                    GBGlobalConfig.m10510c().mo24408h();
                }
            }
        });
    }
}
