package com.gbcom.gwifi.base.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.p009v4.app.Fragment;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.functions.aboutus.AboutUsActivity;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.ping.PingTestActivity;
import com.gbcom.gwifi.functions.product.AppActivity;
import com.gbcom.gwifi.functions.product.AppDownloadActivity;
import com.gbcom.gwifi.functions.product.GameActivity;
import com.gbcom.gwifi.functions.product.NewsActivity;
import com.gbcom.gwifi.functions.profile.UserLevelActivity;
import com.gbcom.gwifi.functions.redbag.RedbagMainActivity;
import com.gbcom.gwifi.functions.revenue.DigActivity;
import com.gbcom.gwifi.functions.revenue.FindActivity;
import com.gbcom.gwifi.functions.revenue.ScoreActivity;
import com.gbcom.gwifi.functions.revenue.ShakeActivity;
import com.gbcom.gwifi.functions.revenue.SingleRedbagActivity;
import com.gbcom.gwifi.functions.system.SysStateActivity;
import com.gbcom.gwifi.functions.test.DeviceTest2Activity;
import com.gbcom.gwifi.functions.wifi.p253a.WifiState;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.wxapi.WXMiniProgramService;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.base.app.a */
public class GBElementManager {

    /* renamed from: a */
    public static final int f8823a = 0;

    /* renamed from: b */
    public static final int f8824b = 1;

    /* renamed from: c */
    public static final int f8825c = 2;

    /* renamed from: d */
    public static final int f8826d = 3;

    /* renamed from: e */
    public static final int f8827e = 4;

    /* renamed from: f */
    public static final int f8828f = 5;

    /* renamed from: g */
    public static final int f8829g = 6;

    /* renamed from: h */
    public static final int f8830h = 7;

    /* renamed from: i */
    public static final int f8831i = 8;

    /* renamed from: j */
    public static final int f8832j = 9;

    /* renamed from: k */
    public static final int f8833k = 10;

    /* renamed from: l */
    public static final int f8834l = 11;

    /* renamed from: m */
    public static final int f8835m = 12;

    /* renamed from: n */
    public static final int f8836n = 13;

    /* renamed from: o */
    public static final int f8837o = 14;

    /* renamed from: p */
    public static final int f8838p = 15;

    /* renamed from: q */
    public static final int f8839q = 16;

    /* renamed from: r */
    public static final int f8840r = 21;

    /* renamed from: t */
    private static GBElementManager f8841t = null;

    /* renamed from: s */
    OkRequestHandler<String> f8842s = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.base.app.GBElementManager.C25301 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    private GBElementManager() {
    }

    /* renamed from: a */
    public static final GBElementManager m10471a() {
        if (f8841t == null) {
            f8841t = new GBElementManager();
        }
        return f8841t;
    }

    /* renamed from: a */
    private int m10469a(HashMap<String, Object> hashMap, String str) {
        if (!hashMap.containsKey(str) || hashMap.get(str) == null) {
            return 0;
        }
        return ((Integer) hashMap.get(str)).intValue();
    }

    /* renamed from: b */
    private String m10476b(HashMap<String, Object> hashMap, String str) {
        if (!hashMap.containsKey(str) || hashMap.get(str) == null) {
            return "";
        }
        return (String) hashMap.get(str);
    }

    /* renamed from: a */
    private int m10470a(JSONObject jSONObject, String str) {
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getInt(str);
            }
            return 0;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: b */
    private String m10477b(JSONObject jSONObject, String str) {
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getString(str);
            }
            return "";
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static void m10474a(Context context, Fragment fragment, String str, int i) {
        if (!str.equals(Constant.f13158M)) {
            if (str.equals(Constant.f13160O)) {
                if (i != 0) {
                    Intent intent = new Intent(GBApplication.instance(), AppActivity.class);
                    intent.putExtra("productId", i);
                    if (context != null) {
                        context.startActivity(intent);
                    }
                    if (fragment != null) {
                        fragment.startActivity(intent);
                    }
                }
            } else if (str.equals(Constant.f13162Q)) {
                if (i != 0) {
                    Intent intent2 = new Intent(GBApplication.instance(), GameActivity.class);
                    intent2.putExtra("productId", i);
                    if (context != null) {
                        context.startActivity(intent2);
                    }
                    if (fragment != null) {
                        fragment.startActivity(intent2);
                    }
                }
            } else if (str.equals(Constant.f13163R)) {
            } else {
                if (str.equals(Constant.f13164S)) {
                    Intent intent3 = new Intent(GBApplication.instance(), NewsActivity.class);
                    if (context != null) {
                        context.startActivity(intent3);
                    }
                    if (fragment != null) {
                        fragment.startActivity(intent3);
                    }
                } else if (!str.equals(Constant.f13161P)) {
                    GBActivity.showMessageToast("请升级app体验最新内容...");
                }
            }
        }
    }

    /* renamed from: a */
    private void m10472a(Context context, Fragment fragment) {
        Intent intent = new Intent(GBApplication.instance(), AboutUsActivity.class);
        if (context != null) {
            context.startActivity(intent);
        }
        if (fragment != null) {
            fragment.startActivity(intent);
        }
    }

    /* renamed from: b */
    private void m10478b(Context context, Fragment fragment) {
        Intent intent = new Intent(GBApplication.instance(), SysStateActivity.class);
        if (context != null) {
            context.startActivity(intent);
        }
        if (fragment != null) {
            fragment.startActivity(intent);
        }
    }

    /* renamed from: c */
    private void m10479c(Context context, Fragment fragment) {
        Intent intent = new Intent(GBApplication.instance(), DeviceTest2Activity.class);
        intent.putExtra("type", "system_set");
        if (context != null) {
            context.startActivity(intent);
        }
        if (fragment != null) {
            fragment.startActivity(intent);
        }
    }

    /* renamed from: d */
    private void m10480d(Context context, Fragment fragment) {
        Intent intent = new Intent(GBApplication.instance(), PingTestActivity.class);
        if (context != null) {
            context.startActivity(intent);
        }
        if (fragment != null) {
            fragment.startActivity(intent);
        }
    }

    /* renamed from: e */
    private void m10481e(Context context, Fragment fragment) {
        Intent intent = new Intent(GBApplication.instance(), UserLevelActivity.class);
        if (context != null) {
            context.startActivity(intent);
        }
        if (fragment != null) {
            fragment.startActivity(intent);
        }
    }

    /* renamed from: a */
    private void m10475a(String str, String str2, String str3) {
        if (C3467s.m14513e(str2)) {
            GBActivity.openBackWebView(str3, str);
        } else if (!CacheWiFi.getInstance().isInnerWifi()) {
            GBActivity.openBackWebView(str3, str);
        } else if (CacheWiFi.getInstance().getCurrentState() == WifiState.SUCCESS) {
            GBActivity.openBackWebView(str2, str, 0, 1);
        } else {
            GBActivity.showMessageToast("请认证后浏览");
        }
    }

    /* renamed from: a */
    private void m10473a(Context context, Fragment fragment, int i) {
        if (i == 1) {
            GBActivity.showMessageToast("请升级app体验最新内容...");
        } else if (i == 2) {
            GBActivity.showMessageToast("请升级app体验最新内容...");
        } else if (i == 5) {
            Intent intent = new Intent(GBApplication.instance(), AppDownloadActivity.class);
            if (context != null) {
                context.startActivity(intent);
            }
            if (fragment != null) {
                fragment.startActivity(intent);
            }
        } else {
            UmengCount.queryMakeScore(GBApplication.instance());
            Intent intent2 = new Intent(GBApplication.instance(), ScoreActivity.class);
            if (context != null) {
                context.startActivity(intent2);
            }
            if (fragment != null) {
                fragment.startActivity(intent2);
            }
        }
    }

    /* renamed from: f */
    private void m10482f(Context context, Fragment fragment) {
    }

    /* renamed from: g */
    private void m10483g(Context context, Fragment fragment) {
        Intent intent = new Intent(GBApplication.instance(), RedbagMainActivity.class);
        if (context != null) {
            context.startActivity(intent);
        }
        if (fragment != null) {
            fragment.startActivity(intent);
        }
    }

    /* renamed from: h */
    private void m10484h(Context context, Fragment fragment) {
        if (!CacheWiFi.getInstance().isInnerWifi()) {
            Intent intent = new Intent(GBApplication.instance(), ShakeActivity.class);
            if (context != null) {
                context.startActivity(intent);
            }
            if (fragment != null) {
                fragment.startActivity(intent);
            }
        } else if (CacheWiFi.getInstance().getCurrentState() != WifiState.SUCCESS) {
            GBActivity.showMessageToast("请先认证!");
        } else {
            Intent intent2 = new Intent(GBApplication.instance(), ShakeActivity.class);
            if (context != null) {
                context.startActivity(intent2);
            }
            if (fragment != null) {
                fragment.startActivity(intent2);
            }
        }
    }

    /* renamed from: i */
    private void m10485i(Context context, Fragment fragment) {
        if (!CacheWiFi.getInstance().isInnerWifi()) {
            Intent intent = new Intent(GBApplication.instance(), DigActivity.class);
            if (context != null) {
                context.startActivity(intent);
            }
            if (fragment != null) {
                fragment.startActivity(intent);
            }
        } else if (CacheWiFi.getInstance().getCurrentState() != WifiState.SUCCESS) {
            GBActivity.showMessageToast("请先认证!");
        } else {
            Intent intent2 = new Intent(GBApplication.instance(), DigActivity.class);
            if (context != null) {
                context.startActivity(intent2);
            }
            if (fragment != null) {
                fragment.startActivity(intent2);
            }
        }
    }

    /* renamed from: j */
    private void m10486j(Context context, Fragment fragment) {
        if (!CacheWiFi.getInstance().isInnerWifi()) {
            Intent intent = new Intent(GBApplication.instance(), FindActivity.class);
            if (context != null) {
                context.startActivity(intent);
            }
            if (fragment != null) {
                fragment.startActivity(intent);
            }
        } else if (CacheWiFi.getInstance().getCurrentState() != WifiState.SUCCESS) {
            GBActivity.showMessageToast("请先认证!");
        } else {
            Intent intent2 = new Intent(GBApplication.instance(), FindActivity.class);
            if (context != null) {
                context.startActivity(intent2);
            }
            if (fragment != null) {
                fragment.startActivity(intent2);
            }
        }
    }

    /* renamed from: k */
    private void m10487k(Context context, Fragment fragment) {
        Intent intent = new Intent(GBApplication.instance(), SingleRedbagActivity.class);
        if (context != null) {
            context.startActivity(intent);
        }
        if (fragment != null) {
            fragment.startActivity(intent);
        }
    }

    /* renamed from: a */
    public void mo24383a(HashMap<String, Object> hashMap, Activity activity, Fragment fragment) {
        switch (m10469a(hashMap, "source_type")) {
            case 0:
            case 12:
            case 13:
            case 15:
            case 16:
            case 21:
                return;
            case 1:
                m10474a(activity, fragment, m10476b(hashMap, "product_type"), m10469a(hashMap, "product_id"));
                return;
            case 2:
                m10475a(m10476b(hashMap, "title"), m10476b(hashMap, "local_wap_url"), m10476b(hashMap, "wap_url"));
                return;
            case 3:
                m10473a(activity, fragment, 0);
                return;
            case 4:
                GBActivity.showMessageToast("稍后上架，敬请期待...");
                return;
            case 5:
                m10482f(activity, fragment);
                return;
            case 6:
                m10483g(activity, fragment);
                return;
            case 7:
                m10484h(activity, fragment);
                return;
            case 8:
                m10485i(activity, fragment);
                return;
            case 9:
                m10486j(activity, fragment);
                return;
            case 10:
                m10487k(activity, fragment);
                return;
            case 11:
                GBActivity.showMessageToast("稍后上架，敬请期待...");
                return;
            case 14:
                WXMiniProgramService.m14865a().mo28458a(m10476b(hashMap, "xcx_user_name"), m10476b(hashMap, "xcx_path"), m10469a(hashMap, "xcx_miniProgramType"));
                return;
            case 17:
            case 18:
            case 19:
            case 20:
            default:
                GBActivity.showMessageToast("稍后上架，敬请期待...");
                return;
        }
    }

    /* renamed from: a */
    public void mo24384a(JSONObject jSONObject, Context context) {
        String str;
        if (jSONObject != null) {
            String b = m10477b(jSONObject, "title");
            GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
            if (currentActivity instanceof MainActivity) {
                switch (((MainActivity) currentActivity).getTabHost().getCurrentTab()) {
                    case 0:
                        str = "tab_first";
                        break;
                    case 1:
                        str = "tab_second";
                        break;
                    case 2:
                        str = "tab_third";
                        break;
                    default:
                        str = "";
                        break;
                }
                if (!C3467s.m14513e(str)) {
                    UmengCount.addTabClick(context, str, b);
                }
            }
            switch (m10470a(jSONObject, "source_type")) {
                case 0:
                case 4:
                case 12:
                case 13:
                case 15:
                case 16:
                case 21:
                    return;
                case 1:
                    if (jSONObject.has("module_code")) {
                        String b2 = m10477b(jSONObject, "module_code");
                        if (C3467s.m14513e(b2)) {
                            return;
                        }
                        if (b2.equals("aboutUs")) {
                            m10472a(context, (Fragment) null);
                            return;
                        } else if (b2.equals("sysStatus")) {
                            m10478b(context, (Fragment) null);
                            return;
                        } else if (b2.equals("faultDetect")) {
                            m10479c(context, null);
                            return;
                        } else if (b2.equals("pingTest")) {
                            m10480d(context, null);
                            return;
                        } else if (b2.equals("chargeInfo")) {
                            m10481e(context, null);
                            return;
                        } else {
                            return;
                        }
                    } else {
                        m10474a(context, null, m10477b(jSONObject, "product_type"), m10470a(jSONObject, "product_id"));
                        return;
                    }
                case 2:
                    m10475a(b, m10477b(jSONObject, "local_wap_url"), m10477b(jSONObject, "wap_url"));
                    return;
                case 3:
                    m10473a(context, (Fragment) null, m10470a(jSONObject, "score_wall_type"));
                    return;
                case 5:
                    m10482f(context, null);
                    return;
                case 6:
                    m10483g(context, null);
                    return;
                case 7:
                    m10484h(context, null);
                    return;
                case 8:
                    m10485i(context, null);
                    return;
                case 9:
                    m10486j(context, null);
                    return;
                case 10:
                    m10487k(context, null);
                    return;
                case 11:
                    GBActivity.showMessageToast("请升级app体验最新内容...");
                    return;
                case 14:
                    WXMiniProgramService.m14865a().mo28458a(m10477b(jSONObject, "xcx_user_name"), m10477b(jSONObject, "xcx_path"), m10470a(jSONObject, "xcx_miniProgramType"));
                    return;
                case 17:
                case 18:
                case 19:
                case 20:
                default:
                    GBActivity.showMessageToast("请升级app体验最新内容...");
                    return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002c  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo24385b(org.json.JSONObject r6, android.content.Context r7) {
        /*
        // Method dump skipped, instructions count: 222
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.base.app.GBElementManager.mo24385b(org.json.JSONObject, android.content.Context):void");
    }

    /* renamed from: a */
    public void mo24382a(HashMap<String, Object> hashMap, int i) {
        int i2;
        int i3 = 0;
        if (hashMap.containsKey("sourceType")) {
            i2 = m10469a(hashMap, "sourceType");
        } else {
            i2 = 0;
        }
        Intent intent = null;
        String str = "";
        String str2 = "";
        switch (i2) {
            case 0:
            case 15:
            case 16:
            case 21:
                return;
            case 1:
                if (hashMap.containsKey("productType")) {
                    str = m10476b(hashMap, "productType");
                }
                if (hashMap.containsKey("productId")) {
                    i3 = m10469a(hashMap, "productId");
                }
                if (!str.equals(Constant.f13158M)) {
                    if (str.equals(Constant.f13160O)) {
                        intent = new Intent(GBApplication.instance(), AppActivity.class);
                        intent.putExtra("productId", i3);
                    } else if (str.equals(Constant.f13162Q)) {
                        intent = new Intent(GBApplication.instance(), GameActivity.class);
                        intent.putExtra("productId", i3);
                    } else if (!str.equals(Constant.f13163R)) {
                        if (str.equals(Constant.f13164S)) {
                            intent = new Intent(GBApplication.instance(), NewsActivity.class);
                        } else {
                            GBActivity.showMessageToast("请升级app体验最新内容...");
                            return;
                        }
                    }
                }
                GBApplication.instance().getCurrentActivity().startActivity(intent);
                HttpUtil.m14246a(GBApplication.instance(), i2, i3, str, str2, i, 2, this.f8842s, "");
                return;
            case 2:
                if (hashMap.containsKey("localWebUrl")) {
                    str2 = m10476b(hashMap, "localWebUrl");
                }
                if (!C3467s.m14513e(str2)) {
                    if (GBApplication.instance().getCurrentActivity() != null) {
                        GBApplication.instance().getCurrentActivity();
                        GBActivity.openBackWebView(str2, "");
                    }
                    HttpUtil.m14246a(GBApplication.instance(), i2, 0, str, str2, i, 2, this.f8842s, "");
                    return;
                }
                GBActivity.showMessageToast("请升级app体验最新内容...");
                return;
            case 14:
                WXMiniProgramService.m14865a().mo28458a(m10476b(hashMap, "xcx_user_name"), m10476b(hashMap, "xcx_path"), m10469a(hashMap, "xcx_miniProgramType"));
                return;
            default:
                GBActivity.showMessageToast("请升级app体验最新内容...");
                return;
        }
    }
}
