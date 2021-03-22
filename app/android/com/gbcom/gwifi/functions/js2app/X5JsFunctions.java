package com.gbcom.gwifi.functions.js2app;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.alipay.sdk.app.p106a.C1469c;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.app.GBElementManager;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.functions.product.BannerAppListActivity;
import com.gbcom.gwifi.functions.product.BannerGameListActivity;
import com.gbcom.gwifi.functions.product.BannerVideoListActivity;
import com.gbcom.gwifi.functions.redbag.RedbagMainActivity;
import com.gbcom.gwifi.functions.revenue.DigActivity;
import com.gbcom.gwifi.functions.revenue.FindActivity;
import com.gbcom.gwifi.functions.revenue.ScoreActivity;
import com.gbcom.gwifi.functions.revenue.ShakeActivity;
import com.gbcom.gwifi.functions.revenue.SingleRedbagActivity;
import com.gbcom.gwifi.functions.webview.BackX5WebViewActivity;
import com.gbcom.gwifi.functions.wifi.WiFiService;
import com.gbcom.gwifi.p235c.p239c.SystemUtils;
import com.gbcom.gwifi.third.pay.alipay.AliPayUtil;
import com.gbcom.gwifi.third.zxing.CaptureActivity;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.URLUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.wxapi.WXInterestService;
import com.gbcom.gwifi.wxapi.WXMiniProgramService;
import com.tencent.smtt.sdk.WebView;
import com.umeng.message.proguard.MessageStore;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class X5JsFunctions {
    public static final int ALIPAY_CANCEL = 6001;
    public static final int ALIPAY_DEALING = 8000;
    public static final int ALIPAY_ERROR = 4000;
    public static final int ALIPAY_NET_ERROR = 6002;
    public static final int ALIPAY_SUCCESS = 9000;
    private static String TAG = "X5JsFunctions";
    public static final int TEST_SERVICE_ID_ACTIVATE_ACCOUNT_SMS = 102;
    public static final int TEST_SERVICE_ID_SYNC_AUTH_MAC = 103;
    public static final int TEST_SERVICE_ID_WEIXINLIAN_GUIDE = 101;
    public static final int WX_INTEREST_FAILED = -1;
    public static final int WX_INTEREST_SUCCESS = 0;
    public static Object errorFun = null;
    private static BroadcastReceiver faceDetectReceiver;
    private static BroadcastReceiver formReceiver;
    private static X5JsFunctions instance = new X5JsFunctions();
    private static BroadcastReceiver qrcodeScanReceiver;
    public static Object successFun = null;
    private static BroadcastReceiver ttRewardVideoReceiver;
    public static WebView web = null;
    private static BroadcastReceiver wmRewardVideoReceiver;
    private static BroadcastReceiver wxPayReceiver;
    public final int cancel = -2;
    public final int error = -3;
    public final int failed = -1;
    private String icon_url;
    private String intro;
    private ViewGroup menuView;
    private final String packageName = "com.tencent.mm";
    View.OnClickListener shareListener = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.js2app.X5JsFunctions.View$OnClickListenerC26374 */

        public void onClick(View view) {
            X5JsFunctions.this.shareWindow.dismiss();
            int i = 0;
            switch (view.getId()) {
                case C2425R.C2427id.wechat_layout /*{ENCODED_INT: 2131755959}*/:
                    i = 1;
                    break;
                case C2425R.C2427id.wxcircle_layout /*{ENCODED_INT: 2131755960}*/:
                    i = 2;
                    break;
                case C2425R.C2427id.qzone_layout /*{ENCODED_INT: 2131755961}*/:
                    i = 3;
                    break;
                case C2425R.C2427id.sina_layout /*{ENCODED_INT: 2131755962}*/:
                    i = 4;
                    break;
                case C2425R.C2427id.qq_layout /*{ENCODED_INT: 2131755963}*/:
                    i = 5;
                    break;
            }
            if (i != 0) {
                Intent intent = new Intent(GBApplication.GWIFI_SHARE_ACTION);
                intent.putExtra("title", X5JsFunctions.this.title);
                intent.putExtra("intro", X5JsFunctions.this.intro);
                intent.putExtra("icon_url", X5JsFunctions.this.icon_url);
                intent.putExtra("wap_url", X5JsFunctions.this.wap_url);
                intent.putExtra(DispatchConstants.PLATFORM, i);
                GBApplication.instance().sendBroadcast(intent);
            }
        }
    };
    private PopupWindow shareWindow;
    public final int success = 0;
    private String title;
    private String wap_url;

    public void callJSFunction(WebView webView, String str, Object obj) {
        String str2;
        String str3 = null;
        if (obj != null) {
            str3 = JsonUtil.m14387a(obj);
        }
        if (webView != null) {
            if (str3 != null) {
                str2 = "javascript:" + str + MessageStore.f23535s + str3 + MessageStore.f23536t;
            } else {
                str2 = "javascript:" + str + "()";
            }
            Log.m14398b(TAG, "webview url=" + str2);
            webView.loadUrl(str2);
            return;
        }
        Log.m14403e(TAG, "webview is null");
    }

    public static X5JsFunctions getInstance() {
        return instance;
    }

    public static WebView getWebView() {
        return web;
    }

    public static Object getFuncSuccess() {
        return successFun;
    }

    public static Object getFuncError() {
        return errorFun;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0039 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void hasMethod(com.tencent.smtt.sdk.WebView r6, java.lang.Object r7, java.lang.Object r8, java.lang.Object r9) {
        /*
        // Method dump skipped, instructions count: 149
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.functions.js2app.X5JsFunctions.hasMethod(com.tencent.smtt.sdk.WebView, java.lang.Object, java.lang.Object, java.lang.Object):void");
    }

    public void close(WebView webView, Object obj, Object obj2, Object obj3) {
        GBApplication.instance().getCurrentActivity().finish();
        if (obj2 != null) {
            callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, ""));
        } else if (obj3 != null) {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, ""));
        }
    }

    public void reLogin(WebView webView, Object obj, Object obj2, Object obj3) {
        GBApplication.instance().getCurrentActivity().finish();
        GBApplication.instance().sendBroadcast(new Intent(Constant.f13245bT));
    }

    public void startWX(WebView webView, Object obj, Object obj2, Object obj3) {
        if (startWX()) {
            if (obj2 != null) {
                callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, ""));
            }
        } else if (obj3 != null) {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, ""));
        }
    }

    private boolean startWX() {
        try {
            if (GBApplication.instance().getPackageManager().getPackageInfo("com.tencent.mm", 0) == null) {
                return false;
            }
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage("com.tencent.mm");
            List<ResolveInfo> queryIntentActivities = GBApplication.instance().getPackageManager().queryIntentActivities(intent, 0);
            if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
                return false;
            }
            GBApplication.instance().startActivity(GBApplication.instance().getPackageManager().getLaunchIntentForPackage("com.tencent.mm").setFlags(268435456));
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void getLocation(WebView webView, Object obj, Object obj2, Object obj3) {
        try {
            String apLocation = CacheWiFi.getInstance().getApLocation();
            if (!TextUtils.isEmpty(apLocation)) {
                HashMap hashMap = new HashMap();
                hashMap.put("location", apLocation);
                getInstance().callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, "", hashMap));
                return;
            }
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, ""));
        } catch (Exception e) {
            if (obj3 != null) {
                callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, ""));
            }
            e.printStackTrace();
        }
    }

    public void doScan(WebView webView, Object obj, Object obj2, Object obj3) {
        web = webView;
        successFun = obj2;
        errorFun = obj3;
        Intent intent = new Intent(GBApplication.instance(), CaptureActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("from", "webview");
        GBApplication.instance().startActivity(intent);
        if (qrcodeScanReceiver == null) {
            IntentFilter intentFilter = new IntentFilter(GBApplication.GIWIFI_QRCODE_SCAN_CALLBACK);
            qrcodeScanReceiver = new BroadcastReceiver() {
                /* class com.gbcom.gwifi.functions.js2app.X5JsFunctions.C26341 */

                public void onReceive(Context context, Intent intent) {
                    String stringExtra = intent.getStringExtra("text");
                    GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
                    if (currentActivity != null) {
                        GBGlobalConfig.m10510c().mo24394a(currentActivity, stringExtra);
                        HashMap hashMap = new HashMap();
                        hashMap.put("text", stringExtra);
                        X5JsFunctions.getInstance().callJSFunction(X5JsFunctions.getWebView(), X5JsFunctions.getFuncSuccess().toString(), CommonMsg.commonMsgMap(0, "", hashMap));
                    } else if (X5JsFunctions.getFuncError() != null) {
                        X5JsFunctions.getInstance().callJSFunction(X5JsFunctions.getWebView(), X5JsFunctions.getFuncError().toString(), CommonMsg.commonMsgMap(-1, "current activity is null"));
                    }
                }
            };
            GBApplication.instance().registerReceiver(qrcodeScanReceiver, intentFilter);
        }
    }

    public void wxInterest(WebView webView, Object obj, Object obj2, Object obj3) {
        if (obj != null) {
            try {
                String string = new JSONObject(JsonUtil.m14387a(obj)).getString("wx_no");
                web = webView;
                successFun = obj2;
                errorFun = obj3;
                if (obj2 != null) {
                    startWX();
                    Intent intent = new Intent(GBApplication.instance(), WXInterestService.class);
                    intent.putExtra("wxNo", string);
                    GBApplication.instance().startService(intent);
                }
            } catch (JSONException e) {
                if (obj3 != null) {
                    callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, ""));
                }
                e.printStackTrace();
            }
        } else if (obj3 != null) {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, ""));
        }
    }

    public void aliPayV2(WebView webView, Object obj, Object obj2, Object obj3) {
        if (obj != null) {
            try {
                web = webView;
                successFun = obj2;
                errorFun = obj3;
                new AliPayUtil().aliPay((String) new JSONObject(JsonUtil.m14387a(obj)).get("pay_info"));
                if (obj2 != null) {
                }
            } catch (JSONException e) {
                if (obj3 != null) {
                    callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(Integer.valueOf((int) ALIPAY_ERROR), ""));
                }
                e.printStackTrace();
            }
        } else if (obj3 != null) {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(Integer.valueOf((int) ALIPAY_ERROR), ""));
        }
    }

    public void aliPay(WebView webView, Object obj, Object obj2, Object obj3) {
        if (obj != null) {
            try {
                JSONObject jSONObject = new JSONObject(JsonUtil.m14387a(obj));
                String string = jSONObject.getString("subject");
                String string2 = jSONObject.getString(AgooConstants.MESSAGE_BODY);
                String string3 = jSONObject.getString("total_fee");
                String string4 = jSONObject.getString("notify_url");
                String string5 = jSONObject.getString("it_b_pay");
                web = webView;
                successFun = obj2;
                errorFun = obj3;
                new AliPayUtil().aliPay((String) jSONObject.get("seller_id"), (String) jSONObject.get(C1469c.f3214G), string, string2, string3, string4, string5);
                if (obj2 != null) {
                }
            } catch (JSONException e) {
                if (obj3 != null) {
                    callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(Integer.valueOf((int) ALIPAY_ERROR), ""));
                }
                e.printStackTrace();
            }
        } else if (obj3 != null) {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(Integer.valueOf((int) ALIPAY_ERROR), ""));
        }
    }

    public void getUserInfo(WebView webView, Object obj, Object obj2, Object obj3) {
        HashMap hashMap = new HashMap();
        hashMap.put("userName", CacheAccount.getInstance().getLoginPhone());
        hashMap.put("gateWayId", CacheAuth.getInstance().getGwId());
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put(com.hyphenate.helpdesk.easeui.Constant.EXTRA_USER_ID, Integer.valueOf(CacheAccount.getInstance().getUserId()));
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, "", hashMap));
    }

    public void getProductInfo(WebView webView, Object obj, Object obj2, Object obj3) {
        if (obj != null) {
            try {
                JSONObject jSONObject = new JSONObject(JsonUtil.m14387a(obj));
                int intValue = ((Integer) jSONObject.get("cat_id")).intValue();
                String valueOf = String.valueOf(((Integer) jSONObject.get("product_id")).intValue());
                String string = jSONObject.getString("product_type");
                if (intValue != 0) {
                    gotoListActivity(string, intValue);
                } else {
                    gotoProductActivity(string, Long.parseLong(valueOf));
                }
                if (obj2 != null) {
                    callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, ""));
                }
            } catch (JSONException e) {
                if (obj3 != null) {
                    callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, ""));
                }
                e.printStackTrace();
            }
        }
    }

    private void gotoListActivity(String str, int i) {
        if (str.equals(Constant.f13158M)) {
            Intent intent = new Intent(GBApplication.instance(), BannerVideoListActivity.class);
            intent.putExtra("catId", i);
            intent.setFlags(268435456);
            GBApplication.instance().startActivity(intent);
        } else if (str.equals(Constant.f13160O)) {
            Intent intent2 = new Intent(GBApplication.instance(), BannerAppListActivity.class);
            intent2.putExtra("catId", i);
            intent2.setFlags(268435456);
            GBApplication.instance().startActivity(intent2);
        } else {
            Intent intent3 = new Intent(GBApplication.instance(), BannerGameListActivity.class);
            intent3.putExtra("catId", i);
            intent3.setFlags(268435456);
            GBApplication.instance().startActivity(intent3);
        }
    }

    private void gotoProductActivity(String str, long j) {
        GBElementManager.m10471a();
        GBElementManager.m10474a(GBApplication.instance(), null, str, (int) j);
    }

    private boolean testAppService(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        Log.m14398b(TAG, "X5JSFunctions: testAppService");
        new Intent().setFlags(268435456);
        try {
            int i = jSONObject.getInt("service_id");
            Log.m14398b(TAG, "X5JSFunctions: testAppService service_id = " + i);
            switch (i) {
                case 101:
                    GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
                    if (currentActivity != null) {
                        currentActivity.showWebDialog(new GBActivity.AbstractC2521e() {
                            /* class com.gbcom.gwifi.functions.js2app.X5JsFunctions.C26352 */

                            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2521e
                            /* renamed from: a */
                            public void mo24348a(Dialog dialog, View view) {
                                dialog.dismiss();
                                GBApplication.instance().getCurrentActivity().showProgressDialog("");
                            }
                        });
                        break;
                    }
                    break;
                case 102:
                    GBGlobalConfig.m10510c().mo24402b("测试短信激活");
                    break;
                case 103:
                    HttpUtil.m14328i(null, "");
                    break;
                default:
                    return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void openAppService(WebView webView, Object obj, Object obj2, Object obj3) {
        int i = 0;
        if (obj != null) {
            try {
                JSONObject jSONObject = new JSONObject(JsonUtil.m14387a(obj));
                int i2 = jSONObject.getInt("service_id");
                Intent intent = new Intent();
                intent.setFlags(268435456);
                switch (i2) {
                    case 3:
                        intent.setClass(GBApplication.instance(), ScoreActivity.class);
                        GBApplication.instance().startActivity(intent);
                        return;
                    case 4:
                    case 5:
                    case 11:
                    case 12:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    default:
                        if (!testAppService(jSONObject)) {
                            Toast.makeText(GBApplication.instance(), "当前版本暂不支持此功能...", 0).show();
                            if (obj3 != null) {
                                callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "invalid service id"));
                                return;
                            }
                            return;
                        }
                        return;
                    case 6:
                        intent.setClass(GBApplication.instance(), RedbagMainActivity.class);
                        GBApplication.instance().startActivity(intent);
                        return;
                    case 7:
                        intent.setClass(GBApplication.instance(), ShakeActivity.class);
                        GBApplication.instance().startActivity(intent);
                        return;
                    case 8:
                        intent.setClass(GBApplication.instance(), DigActivity.class);
                        GBApplication.instance().startActivity(intent);
                        return;
                    case 9:
                        intent.setClass(GBApplication.instance(), FindActivity.class);
                        GBApplication.instance().startActivity(intent);
                        return;
                    case 10:
                        intent.setClass(GBApplication.instance(), SingleRedbagActivity.class);
                        GBApplication.instance().startActivity(intent);
                        return;
                    case 13:
                    case 15:
                    case 16:
                    case 21:
                        return;
                    case 14:
                        if (jSONObject.has("xcx_user_name")) {
                            String string = jSONObject.getString("xcx_user_name");
                            String str = "";
                            if (jSONObject.has("xcx_path")) {
                                str = jSONObject.getString("xcx_path");
                            }
                            if (jSONObject.has("xcx_miniProgramType")) {
                                i = jSONObject.getInt("xcx_miniProgramType");
                            }
                            WXMiniProgramService.m14865a().mo28458a(string, str, i);
                            return;
                        } else if (obj3 != null) {
                            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "no user name"));
                            return;
                        } else {
                            return;
                        }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                if (obj3 != null) {
                    callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, ""));
                }
            }
        } else if (obj3 != null) {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, ""));
        }
    }

    public void logout(WebView webView, Object obj, Object obj2, Object obj3) {
        if (obj != null) {
            try {
                String str = (String) new JSONObject(JsonUtil.m14387a(obj)).get(Constant.f13323i);
                if (str == null) {
                    str = "";
                }
                getInstance().callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, ""));
                GBApplication.instance().sendBroadcast(new Intent(GBApplication.GWIFI_LOGOUT).putExtra(Constant.f13323i, str));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void isGiWiFiApp(WebView webView, Object obj, Object obj2, Object obj3) {
        getInstance().callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, ""));
    }

    public void shareWap(WebView webView, Object obj, Object obj2, Object obj3) {
        Log.m14398b(TAG, "shareWap");
        if (obj != null) {
            try {
                JSONObject jSONObject = new JSONObject(JsonUtil.m14387a(obj));
                this.title = (String) jSONObject.get("title");
                this.intro = (String) jSONObject.get("intro");
                this.icon_url = (String) jSONObject.get("icon_url");
                this.wap_url = (String) jSONObject.get("wap_url");
                Log.m14398b(TAG, "shareWap:" + this.title);
                Log.m14398b(TAG, "shareWap:" + this.intro);
                Log.m14398b(TAG, "shareWap:" + this.icon_url);
                Log.m14398b(TAG, "shareWap:" + this.wap_url);
                if (!C3467s.m14513e(this.title) && !C3467s.m14513e(this.intro) && !C3467s.m14513e(this.icon_url) && !C3467s.m14513e(this.wap_url)) {
                    showShareMenu(webView);
                }
                if (obj2 != null) {
                    getInstance().callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, ""));
                }
            } catch (JSONException e) {
                if (obj3 != null) {
                    callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, ""));
                }
                e.printStackTrace();
            }
        }
    }

    private void showShareMenu(WebView webView) {
        Log.m14398b(TAG, "showShareMenu:");
        if (this.shareWindow == null) {
            Log.m14398b(TAG, "showShareMenu: new");
            this.menuView = (ViewGroup) GBApplication.instance().getCurrentActivity().getLayoutInflater().inflate(C2425R.layout.web_share_menu, (ViewGroup) null, false);
            ((LinearLayout) this.menuView.findViewById(C2425R.C2427id.wechat_layout)).setOnClickListener(this.shareListener);
            ((LinearLayout) this.menuView.findViewById(C2425R.C2427id.wxcircle_layout)).setOnClickListener(this.shareListener);
            ((LinearLayout) this.menuView.findViewById(C2425R.C2427id.qzone_layout)).setOnClickListener(this.shareListener);
            ((LinearLayout) this.menuView.findViewById(C2425R.C2427id.sina_layout)).setOnClickListener(this.shareListener);
            ((LinearLayout) this.menuView.findViewById(C2425R.C2427id.qq_layout)).setOnClickListener(this.shareListener);
            this.shareWindow = new PopupWindow(this.menuView, -1, -1);
            this.shareWindow.setTouchable(true);
            this.menuView.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.js2app.X5JsFunctions.View$OnClickListenerC26363 */

                public void onClick(View view) {
                    X5JsFunctions.this.shareWindow.dismiss();
                }
            });
            this.shareWindow.setBackgroundDrawable(new BitmapDrawable());
            this.shareWindow.showAtLocation(webView, 80, 0, SystemUtils.m10808d(GBApplication.instance()));
            this.shareWindow.update();
        } else if (this.shareWindow.isShowing()) {
            Log.m14398b(TAG, "showShareMenu: dismiss");
            this.shareWindow.dismiss();
            this.shareWindow = null;
        } else {
            Log.m14398b(TAG, "showShareMenu: show");
            this.shareWindow.showAtLocation(webView, 80, 0, 0);
        }
    }

    public void androidtel(WebView webView, Object obj, Object obj2, Object obj3) {
        if (obj != null) {
            try {
                String str = (String) new JSONObject(JsonUtil.m14387a(obj)).get(Constant.f13323i);
                GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
                if (currentActivity != null) {
                    currentActivity.callPhone(str);
                }
            } catch (JSONException e) {
                if (obj3 != null) {
                    callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, ""));
                }
                e.printStackTrace();
            }
        }
    }

    public void checkVersion(WebView webView, Object obj, Object obj2, Object obj3) {
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.checkVersion();
        }
        if (obj2 != null) {
            callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, ""));
        } else if (obj3 != null) {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "no success func"));
        }
    }

    public void startScanWifiList(WebView webView, Object obj, Object obj2, Object obj3) {
        Integer num;
        Integer num2;
        if (obj != null) {
            try {
                JSONObject jSONObject = new JSONObject(JsonUtil.m14387a(obj));
                Integer.valueOf(0);
                if (jSONObject.has("wifi2gNum")) {
                    num = Integer.valueOf(jSONObject.getInt("wifi2gNum"));
                } else {
                    num = 8;
                }
                Integer.valueOf(0);
                if (jSONObject.has("wifi5gNum")) {
                    num2 = Integer.valueOf(jSONObject.getInt("wifi5gNum"));
                } else {
                    num2 = 8;
                }
                if (!WiFiService.m13974a().mo27578a(num.intValue(), num2.intValue())) {
                    if (obj3 != null) {
                        callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-105, "wifi scan fail"));
                    }
                } else if (obj2 != null) {
                    callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, ""));
                } else if (obj3 != null) {
                    callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "no success func"));
                }
            } catch (JSONException e) {
                if (obj3 != null) {
                    callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "json exception"));
                }
                e.printStackTrace();
            }
        } else if (obj3 != null) {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "args is null"));
        }
    }

    public void getScanWifiList(WebView webView, Object obj, Object obj2, Object obj3) {
        if (!WiFiService.m13974a().mo27587c()) {
            if (obj3 != null) {
                callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-101, "wifi switch off"));
            }
        } else if (WiFiService.m13974a().mo27589d() || obj3 == null) {
            String scanWifiList = CacheWiFi.getInstance().getScanWifiList();
            if (obj2 != null) {
                if (C3467s.m14513e(scanWifiList)) {
                    if (obj3 != null) {
                        callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "scan wifiList no result"));
                    }
                } else if (JsonUtil.m14389a(scanWifiList)) {
                    callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, "", (HashMap) JsonUtil.m14386a(scanWifiList, HashMap.class)));
                } else if (obj3 != null) {
                    callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "invalid result:" + scanWifiList));
                }
            } else if (obj3 != null) {
                callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "no success func"));
            }
        } else {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-102, "network permission deny"));
        }
    }

    public void clearScanWifiList(WebView webView, Object obj, Object obj2, Object obj3) {
        CacheWiFi.getInstance().setScanWifiList("");
        if (obj2 != null) {
            callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, ""));
        } else if (obj3 != null) {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "no success func"));
        }
    }

    public void startScanWifiTimer(WebView webView, Object obj, Object obj2, Object obj3) {
        if (obj2 != null) {
            WiFiService.m13974a().mo27591f();
            callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, ""));
        } else if (obj3 != null) {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "no success func"));
        }
    }

    public void stopScanWifiTimer(WebView webView, Object obj, Object obj2, Object obj3) {
        if (obj2 != null) {
            WiFiService.m13974a().mo27592g();
            callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, ""));
        } else if (obj3 != null) {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "no success func"));
        }
    }

    public void buildHomePage(WebView webView, Object obj, Object obj2, Object obj3) {
        if (obj != null) {
            GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
            if (currentActivity instanceof BackX5WebViewActivity) {
                BackX5WebViewActivity backX5WebViewActivity = (BackX5WebViewActivity) currentActivity;
                try {
                    JSONArray jSONArray = new JSONObject(JsonUtil.m14387a(obj)).getJSONArray("options");
                    int length = jSONArray.length();
                    int i = length > 2 ? 2 : length;
                    if (i == 0) {
                        backX5WebViewActivity.hideOptionBtnCustom2();
                        backX5WebViewActivity.hideOptionBtnCustom1();
                    }
                    if (i == 1) {
                        backX5WebViewActivity.hideOptionBtnCustom2();
                    }
                    for (int i2 = 0; i2 < i; i2++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        int i3 = jSONObject.getInt("button_type");
                        String string = jSONObject.getString("button_text");
                        String string2 = jSONObject.getString("button_click_url");
                        String string3 = jSONObject.getString("button_click_action");
                        String string4 = jSONObject.getString("button_click_params");
                        if (i2 == 0) {
                            backX5WebViewActivity.showOptionBtnCustom1(i3, string, URLUtil.m14541a(string2), string3, string4);
                        } else if (i2 == 1) {
                            backX5WebViewActivity.showOptionBtnCustom2(i3, string, URLUtil.m14541a(string2), string3, string4);
                        }
                    }
                } catch (JSONException e) {
                    if (obj3 != null) {
                        callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "json exception"));
                    }
                    e.printStackTrace();
                }
            } else if (obj3 != null) {
                callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "webview is null"));
            }
        } else if (obj3 != null) {
            callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, "args is null"));
        }
    }

    public void checkIosPubType(WebView webView, Object obj, Object obj2, Object obj3) {
        try {
            String m = SystemUtil.m14540m(GBApplication.instance().getApplicationContext());
            HashMap hashMap = new HashMap();
            hashMap.put("iosPubType", 0);
            hashMap.put("appPkgName", m);
            getInstance().callJSFunction(webView, obj2.toString(), CommonMsg.commonMsgMap(0, "", hashMap));
        } catch (Exception e) {
            if (obj3 != null) {
                callJSFunction(webView, obj3.toString(), CommonMsg.commonMsgMap(-1, ""));
            }
            e.printStackTrace();
        }
    }
}
