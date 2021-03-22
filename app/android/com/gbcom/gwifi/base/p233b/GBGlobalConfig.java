package com.gbcom.gwifi.base.p233b;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.p009v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.app.GBElementManager;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.codec.EncryptUtil;
import com.gbcom.gwifi.functions.edu.domain.NotifyBean;
import com.gbcom.gwifi.functions.loading.DialogActivity;
import com.gbcom.gwifi.functions.loading.LoginThirdActivity;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.loading.StatusLoginActivity;
import com.gbcom.gwifi.functions.loading.domain.TabDatas;
import com.gbcom.gwifi.functions.location.RabbitMQParam;
import com.gbcom.gwifi.functions.p242c.p243a.GameConfig;
import com.gbcom.gwifi.functions.p242c.p243a.GameFilter;
import com.gbcom.gwifi.functions.p242c.p243a.GameIpNetMask;
import com.gbcom.gwifi.functions.wifi.CheckNetWorkReceiver;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.functions.wifi.p253a.AuthResultCode;
import com.gbcom.gwifi.p221a.p226d.OkHttpService;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.FormatUtil;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.ResourceParse;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.URLUtil;
import com.gbcom.gwifi.util.VersionUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAd;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheEdu;
import com.gbcom.gwifi.util.cache.CacheGame;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.util.p256a.TokenUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.util.p257b.ScreenUtil;
import com.gbcom.gwifi.util.p257b.StatusBarUtil;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.net.URLDecoder;
import java.p456io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.base.b.a */
public class GBGlobalConfig extends OkRequestHandler<String> {

    /* renamed from: a */
    public static boolean f8850a = false;

    /* renamed from: b */
    public static boolean f8851b = false;

    /* renamed from: c */
    public static final int f8852c = 1;

    /* renamed from: d */
    public static final int f8853d = 2;

    /* renamed from: e */
    public static final int f8854e = 3;

    /* renamed from: f */
    public static final int f8855f = 4;

    /* renamed from: g */
    public static final int f8856g = 5;

    /* renamed from: h */
    public static boolean f8857h = true;

    /* renamed from: j */
    private static final String f8858j = "GBGlobalConfig";

    /* renamed from: k */
    private static final Object f8859k = "oldUserLoginRequest";

    /* renamed from: r */
    private static GBGlobalConfig f8860r = new GBGlobalConfig();

    /* renamed from: t */
    private static Boolean f8861t = false;

    /* renamed from: u */
    private static long f8862u = 0;

    /* renamed from: A */
    private String f8863A;

    /* renamed from: B */
    private String f8864B;

    /* renamed from: C */
    private Gson f8865C;

    /* renamed from: D */
    private Request f8866D;

    /* renamed from: E */
    private Request f8867E;

    /* renamed from: F */
    private Request f8868F;

    /* renamed from: G */
    private Request f8869G;

    /* renamed from: H */
    private Request f8870H;

    /* renamed from: I */
    private Request f8871I;

    /* renamed from: J */
    private Request f8872J;

    /* renamed from: K */
    private int f8873K = 0;

    /* renamed from: L */
    private Request f8874L;

    /* renamed from: M */
    private Handler f8875M = new Handler(Looper.getMainLooper()) {
        /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.HandlerC254722 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    GBApplication.instance().getCurrentActivity().dismissProgressDialog();
                    GBApplication.instance().sendBroadcast(new Intent(Constant.f13241bP));
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: N */
    private final long f8876N = 3000;

    /* renamed from: O */
    private long f8877O = -1;

    /* renamed from: P */
    private long f8878P = -1;

    /* renamed from: Q */
    private long f8879Q = -1;

    /* renamed from: R */
    private long f8880R = -1;

    /* renamed from: S */
    private long f8881S = -1;

    /* renamed from: i */
    SimpleDateFormat f8882i = new SimpleDateFormat("MM-dd HH:mm");

    /* renamed from: l */
    private Request f8883l;

    /* renamed from: m */
    private Request f8884m;

    /* renamed from: n */
    private Request f8885n;

    /* renamed from: o */
    private Request f8886o;

    /* renamed from: p */
    private String f8887p;

    /* renamed from: q */
    private String f8888q;

    /* renamed from: s */
    private CallBack f8889s;

    /* renamed from: v */
    private Request f8890v;

    /* renamed from: w */
    private String f8891w;

    /* renamed from: x */
    private Integer f8892x = 2;

    /* renamed from: y */
    private PopupWindow f8893y;

    /* renamed from: z */
    private String f8894z;

    /* renamed from: com.gbcom.gwifi.base.b.a$a */
    /* compiled from: GBGlobalConfig */
    public interface AbstractC2565a {
        void notifyAuth(boolean z);
    }

    /* renamed from: a */
    public void mo24389a() {
    }

    /* renamed from: b */
    static GBGlobalConfig m10506b() {
        return f8860r;
    }

    /* renamed from: c */
    public static GBGlobalConfig m10510c() {
        if (f8860r != null) {
            return f8860r;
        }
        return null;
    }

    /* renamed from: a */
    public boolean mo24400a(Context context) {
        String packageName = context.getPackageName();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(packageName) && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo24401a(CallBack aVar) {
        this.f8889s = aVar;
        this.f8887p = CacheAccount.getInstance().getLoginPhone();
        this.f8888q = CacheAccount.getInstance().getLoginStaticPassword();
        return mo24413m();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: t */
    private void m10530t() {
        this.f8890v = HttpUtil.m14267a((OkRequestHandler<String>) this, (Integer) 90, (Integer) 1024, (Integer) 4096, (Integer) 2, (Object) "");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: u */
    private void m10531u() {
        HttpUtil.m14267a((OkRequestHandler<String>) this, (Integer) 60, (Integer) 1024, (Integer) 4096, (Integer) 1, (Object) "");
    }

    @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
    public void onRequestStart(Request abVar) {
    }

    @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
    public void onRequestFailed(Request abVar, Exception exc) {
        if (abVar == this.f8883l) {
            synchronized (f8861t) {
                f8861t = false;
            }
        }
        if (abVar == this.f8886o) {
            synchronized (f8861t) {
                f8861t = false;
            }
        }
        if (abVar == this.f8883l) {
            GBApplication.instance().sendBroadcast(new Intent(CheckNetWorkReceiver.f12766c));
            C3470v.m14563a(GBApplication.instance(), "云认证失败，请检查网络");
        }
        if (abVar == this.f8886o) {
            GBApplication.instance().sendBroadcast(new Intent(CheckNetWorkReceiver.f12766c));
            C3470v.m14563a(GBApplication.instance(), "本地认证失败，请检查网络");
        } else if (abVar == this.f8890v && GBApplication.instance().getCurrentActivity() != null) {
            GBActivity.showMessageToast("微信认证失败！请检查网络");
            GBApplication.instance().getCurrentActivity().dismissProgressDialog();
        }
        if (abVar == this.f8884m) {
            GBActivity.showMessageToast("云认证绑定设备失败，请检查网络");
        }
        if (abVar == this.f8885n) {
            GBActivity.showMessageToast("本地认证绑定设备失败，请检查网络");
        }
        if (abVar == this.f8872J) {
            GBActivity.showMessageToast("Mac变化同步失败，请检查网络");
            this.f8873K++;
            Log.m14398b(f8858j, "wifiChange send notify to gateway failed:" + this.f8873K);
            if (this.f8873K <= 1) {
                GBApplication.instance().sendBroadcast(new Intent(Constant.f13247bV));
            }
        } else if (this.f8889s != null) {
            this.f8889s.mo24437a(false);
            this.f8889s = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: v */
    private void m10532v() {
        if (f8857h) {
            m10518f();
        }
        if (this.f8889s != null) {
            this.f8889s.mo24437a(true);
            this.f8889s = null;
        }
        GBApplication.instance().sendBroadcast(new Intent(Constant.f13235bJ));
    }

    /* renamed from: e */
    private void m10516e(String str) {
        HashMap hashMap;
        String str2;
        HashMap hashMap2;
        String str3 = null;
        GBApplication.instance().sendBroadcast(new Intent(CheckNetWorkReceiver.f12766c));
        synchronized (f8861t) {
            f8861t = false;
        }
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (ResultCode.m14476b(deSerializeJson)) {
            CacheAuth.getInstance().setOnlineTime(0);
            String j = WiFiUtil.m14021a().mo27616j();
            CacheAuth.getInstance().setOnlineLocalIp(j);
            Log.m14398b(f8858j, "setOnlineLocalIp " + j);
            String data = deSerializeJson.getData();
            HashMap hashMap3 = (HashMap) JsonUtil.m14386a(data, HashMap.class);
            if (hashMap3.containsKey("tips")) {
                m10502a(deSerializeJson, hashMap3, data);
            } else {
                m10509b(deSerializeJson, hashMap3, data);
            }
        } else {
            if (this.f8889s != null) {
                this.f8889s.mo24437a(false);
                this.f8889s = null;
            }
            int intValue = deSerializeJson.getResultCode().intValue();
            if (AuthResultCode.m13892i(intValue)) {
                if (deSerializeJson.getData() == null || (hashMap2 = (HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class)) == null) {
                    str2 = null;
                } else {
                    if (hashMap2.containsKey("send_phone")) {
                        str2 = (String) hashMap2.get("send_phone");
                    } else {
                        str2 = null;
                    }
                    if (hashMap2.containsKey("send_content")) {
                        str3 = (String) hashMap2.get("send_content");
                    }
                }
                if (!C3467s.m14513e(str2)) {
                    CacheAccount.getInstance().setCacheSendPhone(str2);
                }
                if (!C3467s.m14513e(str3)) {
                    CacheAccount.getInstance().setCacheSendContent(str3);
                }
                mo24402b(deSerializeJson.getResultMsg());
            } else if (AuthResultCode.m13893j(intValue)) {
                mo24410j();
            } else if (AuthResultCode.m13894k(intValue)) {
                String str4 = "您的套餐或试用期已到期，是否充值或办理业务？";
                if (AuthResultCode.m13895l(intValue)) {
                    str4 = "您今天的试用时长已满，是否充值或办理业务？";
                }
                mo24398a(str4, true);
            } else if (AuthResultCode.m13899p(intValue)) {
                mo24398a(deSerializeJson.getResultMsg() + " 是否充值办理？", true);
            } else if (AuthResultCode.m13896m(intValue)) {
                mo24404b(deSerializeJson.getResultMsg(), false);
            } else if (AuthResultCode.m13888e(intValue)) {
                mo24411k();
            } else if (AuthResultCode.m13889f(intValue)) {
                mo24412l();
            } else if (AuthResultCode.m13897n(intValue)) {
                HashMap hashMap4 = (HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class);
                try {
                    if (hashMap4.containsKey("show_wechat_page_enable")) {
                        this.f8892x = (Integer) hashMap4.get("show_wechat_page_enable");
                    }
                    this.f8891w = (String) hashMap4.get("third_auth_url");
                    if (this.f8892x.intValue() != 2) {
                        int a = VersionUtil.m14564a(GBApplication.instance());
                        if (CacheApp.getInstance().getWeChatGuide() && a == CacheApp.getInstance().getFirstGuideCode().intValue()) {
                            GBApplication.instance().getCurrentActivity().showProgressDialog("");
                            m10530t();
                        } else if (GBApplication.instance().getCurrentActivity() != null) {
                            GBApplication.instance().getCurrentActivity().showWebDialog(new GBActivity.AbstractC2521e() {
                                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C25311 */

                                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2521e
                                /* renamed from: a */
                                public void mo24348a(Dialog dialog, View view) {
                                    dialog.dismiss();
                                    GBApplication.instance().getCurrentActivity().showProgressDialog("");
                                    GBGlobalConfig.this.m10530t();
                                }
                            });
                        }
                    } else if (GBApplication.instance().getCurrentActivity() != null) {
                        m10533w();
                    }
                } catch (Exception e) {
                }
            } else if (deSerializeJson == null || deSerializeJson.getData() == null || (hashMap = (HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class)) == null || C3467s.m14513e((String) hashMap.get("redirect_url"))) {
                GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
                if (currentActivity == null) {
                    return;
                }
                if (deSerializeJson.getResultCode().intValue() != 102 || !C3467s.m14513e(deSerializeJson.getResultMsg())) {
                    currentActivity.gotoError(this.f8887p, deSerializeJson);
                } else {
                    GBActivity.showMessageToast("服务器繁忙,请稍后尝试");
                }
            } else {
                m10503a(hashMap.get("redirect_url").toString(), deSerializeJson.getResultMsg());
            }
        }
    }

    /* renamed from: f */
    private void m10519f(String str) {
        String str2;
        JSONObject jSONObject;
        String str3;
        final int i;
        String str4 = null;
        GBApplication.instance().sendBroadcast(new Intent(CheckNetWorkReceiver.f12766c));
        synchronized (f8861t) {
            f8861t = false;
        }
        if (str != null) {
            try {
                JSONObject jSONObject2 = new JSONObject(EncryptUtil.decrypt(URLDecoder.decode(new JSONObject(str).getString("data"), "UTF-8")));
                int i2 = jSONObject2.getInt(AbstractC5718b.JSON_ERRORCODE);
                if (i2 == 0) {
                    CacheAuth.getInstance().setOnlineTime(0);
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("data");
                    if (jSONObject3.has("tips")) {
                        String string = jSONObject3.getString("tips");
                        if (!C3467s.m14513e(string)) {
                            if (jSONObject3.has("authState")) {
                                i = jSONObject3.getInt("authState");
                            } else {
                                i = 0;
                            }
                            if (GBApplication.instance().getCurrentActivity() != null) {
                                GBApplication.instance().getCurrentActivity().showSecondDialog("提示", string, "确定", new GBActivity.AbstractC2517a() {
                                    /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C253412 */

                                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                                    public void onClick(Dialog dialog, View view) {
                                        dialog.dismiss();
                                        if (i != 200) {
                                            GBGlobalConfig.this.m10532v();
                                        } else if (GBGlobalConfig.this.f8889s != null) {
                                            GBGlobalConfig.this.f8889s.mo24437a(Integer.valueOf(i));
                                            GBGlobalConfig.this.f8889s = null;
                                        }
                                    }
                                }, new DialogInterface.OnCancelListener() {
                                    /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.DialogInterface$OnCancelListenerC254823 */

                                    public void onCancel(DialogInterface dialogInterface) {
                                        dialogInterface.dismiss();
                                        if (i != 200) {
                                            GBGlobalConfig.this.m10532v();
                                        } else if (GBGlobalConfig.this.f8889s != null) {
                                            GBGlobalConfig.this.f8889s.mo24437a(Integer.valueOf(i));
                                            GBGlobalConfig.this.f8889s = null;
                                        }
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        m10532v();
                        return;
                    }
                    m10532v();
                    return;
                }
                if (this.f8889s != null) {
                    this.f8889s.mo24437a(false);
                    this.f8889s = null;
                }
                String string2 = jSONObject2.getString("resultMsg");
                GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
                if (currentActivity != null) {
                    if (AuthResultCode.m13892i(i2)) {
                        if (!jSONObject2.has("data") || (jSONObject = jSONObject2.getJSONObject("data")) == null) {
                            str2 = null;
                        } else {
                            if (jSONObject.has("send_phone")) {
                                str3 = (String) jSONObject.get("send_phone");
                            } else {
                                str3 = null;
                            }
                            if (jSONObject.has("send_content")) {
                                str2 = (String) jSONObject.get("send_content");
                                str4 = str3;
                            } else {
                                str2 = null;
                                str4 = str3;
                            }
                        }
                        if (!C3467s.m14513e(str4)) {
                            CacheAccount.getInstance().setCacheSendPhone(str4);
                        }
                        if (!C3467s.m14513e(str2)) {
                            CacheAccount.getInstance().setCacheSendContent(str2);
                        }
                        mo24402b(string2);
                    }
                    if (AuthResultCode.m13893j(i2)) {
                        mo24410j();
                    }
                    if (AuthResultCode.m13894k(i2) || ((i2 == 203 || i2 == 1) && string2.indexOf("购买") != -1)) {
                        String str5 = "您的套餐或试用期已到期，是否充值或办理业务？";
                        if (AuthResultCode.m13895l(i2)) {
                            str5 = "您今天的试用时长已满，是否充值或办理业务？";
                        }
                        if ((i2 == 203 || i2 == 1) && string2.indexOf("购买") != -1) {
                            str5 = string2 + "，是否充值或办理业务？";
                        }
                        mo24398a(str5, false);
                    } else if (AuthResultCode.m13899p(i2)) {
                        mo24398a(string2 + " 是否充值办理？", false);
                    } else {
                        if (AuthResultCode.m13896m(i2) || ((i2 == 203 || i2 == 1) && string2.indexOf("绑定") != -1)) {
                            String localMac = CacheAuth.getInstance().getLocalMac();
                            if (C3467s.m14513e(localMac)) {
                                CacheAuth.getInstance().setLocalMac(WiFiUtil.m14035e(GBApplication.instance().getApplicationContext()));
                                localMac = CacheAuth.getInstance().getLocalMac();
                            }
                            if (!C3467s.m14513e(localMac)) {
                                mo24404b(string2, true);
                                return;
                            }
                        }
                        if (AuthResultCode.m13898o(i2) || ((i2 == 105 || i2 == 1) && string2.indexOf("完善") != -1)) {
                            mo24405c(string2 + " 是否立即完善资料？");
                            return;
                        }
                        if (AuthResultCode.m13888e(i2)) {
                            mo24411k();
                        }
                        if (AuthResultCode.m13889f(i2)) {
                            mo24412l();
                        }
                        currentActivity.gotoError(this.f8887p, string2);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        } else if (this.f8889s != null) {
            this.f8889s.mo24437a(false);
            this.f8889s = null;
        }
    }

    /* renamed from: g */
    private void m10521g(String str) {
        HashMap<String, Object> x = ResourceParse.m14473x(str.getBytes());
        if (ResultCode.m14475a((Integer) x.get(AbstractC5718b.JSON_ERRORCODE))) {
            HashMap hashMap = (HashMap) x.get("data");
            if (hashMap.containsKey("shortSsid")) {
                CacheWiFi.getInstance().setShortSsidPrefix((String) hashMap.get("shortSsid"));
            }
            if (hashMap.containsKey("backgroundTime")) {
                CacheApp.getInstance().setBackgroundStayTime(((Integer) hashMap.get("backgroundTime")).intValue());
            }
            if (hashMap.containsKey("eventList")) {
                CacheAd.getInstance().setAdEventList((List) hashMap.get("eventList"));
            }
            if (hashMap.containsKey("scoreList")) {
                List list = (List) hashMap.get("scoreList");
            }
            if (hashMap.containsKey("auth_type")) {
                CacheAuth.getInstance().setAuthType(((Integer) hashMap.get("auth_type")).intValue());
            }
        }
    }

    /* renamed from: h */
    private void m10523h(String str) {
        try {
            if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                if (this.f8865C == null) {
                    this.f8865C = GsonUtil.m14241a();
                }
                List<TabDatas.DataBean.TabListBean> tab_list = ((TabDatas) this.f8865C.mo21588a(str, TabDatas.class)).getData().getTab_list();
                if (tab_list != null && tab_list.size() > 0) {
                    CacheApp.getInstance().setTabList(tab_list);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    private void m10525i(String str) {
        int i;
        int i2 = 1;
        int i3 = 0;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE) != 0) {
                CacheAccount.getInstance().setAppKefuState(1);
                CacheAccount.getInstance().setReportLocationSwitch(0);
                return;
            }
            new HashMap();
            JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
            if (jSONObject2.has("app_login_phone_state")) {
            }
            if (jSONObject2.has("app_login_sn_state")) {
                CacheAccount.getInstance().setAppLoginSnState(jSONObject2.getInt("app_login_sn_state"));
            }
            if (jSONObject2.has("app_login_default_mode") && jSONObject2.getInt("app_login_default_mode") == 2 && CacheAccount.getInstance().getLastLoginType() == 0) {
                CacheAccount.getInstance().setLastLoginType(2);
            }
            if (jSONObject2.has("cloud_mode")) {
            }
            if (jSONObject2.has("app_custom_icon_state")) {
                CacheAccount.getInstance().setAppCustomIconState(jSONObject2.getInt("app_custom_icon_state"));
            }
            if (jSONObject2.has("kefu_state")) {
                i2 = jSONObject2.getInt("kefu_state");
            }
            CacheAccount.getInstance().setAppKefuState(i2);
            if (jSONObject2.has("report_location_switch")) {
                i3 = jSONObject2.getInt("report_location_switch");
            }
            CacheAccount.getInstance().setReportLocationSwitch(i3);
            RabbitMQParam cVar = new RabbitMQParam();
            if (i3 > 0) {
                if (jSONObject2.has("report_location_user_name")) {
                    String string = jSONObject2.getString("report_location_user_name");
                    if (!C3467s.m14513e(string)) {
                        cVar.mo25206a(string);
                    }
                }
                if (jSONObject2.has("report_location_password")) {
                    String string2 = jSONObject2.getString("report_location_password");
                    if (!C3467s.m14513e(string2)) {
                        cVar.mo25208b(string2);
                    }
                }
                if (jSONObject2.has("report_location_host_name")) {
                    String string3 = jSONObject2.getString("report_location_host_name");
                    if (!C3467s.m14513e(string3)) {
                        cVar.mo25210c(string3);
                    }
                }
                if (jSONObject2.has("report_location_port") && (i = jSONObject2.getInt("report_location_port")) != 0) {
                    cVar.mo25205a(i);
                }
                if (jSONObject2.has("report_location_virtual_host")) {
                    String string4 = jSONObject2.getString("report_location_virtual_host");
                    if (!C3467s.m14513e(string4)) {
                        cVar.mo25212d(string4);
                    }
                }
                if (jSONObject2.has("report_location_queue_name")) {
                    String string5 = jSONObject2.getString("report_location_queue_name");
                    if (!C3467s.m14513e(string5)) {
                        cVar.mo25214e(string5);
                    }
                }
                if (jSONObject2.has("report_location_exchange_name")) {
                    String string6 = jSONObject2.getString("report_location_exchange_name");
                    if (!C3467s.m14513e(string6)) {
                        cVar.mo25216f(string6);
                    }
                }
                if (jSONObject2.has("report_location_type")) {
                    String string7 = jSONObject2.getString("report_location_type");
                    if (!C3467s.m14513e(string7)) {
                        cVar.mo25218g(string7);
                    }
                }
                if (jSONObject2.has("report_location_rounting_key")) {
                    String string8 = jSONObject2.getString("report_location_rounting_key");
                    if (!C3467s.m14513e(string8)) {
                        cVar.mo25220h(string8);
                    }
                }
            } else {
                cVar.mo25206a("");
                cVar.mo25208b("");
                cVar.mo25210c("");
                cVar.mo25205a(0);
                cVar.mo25212d("");
                cVar.mo25214e("");
                cVar.mo25216f("");
                cVar.mo25218g("");
                cVar.mo25220h("");
            }
            CacheAccount.getInstance().setReportLocationParam(cVar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo24399a(List<NotifyBean.DataBean.NotifyListBean> list) {
        GBActivity currentActivity;
        if (list != null && list.size() != 0 && (currentActivity = GBApplication.instance().getCurrentActivity()) != null && mo24400a(currentActivity.getApplicationContext())) {
            int limit_num = list.get(0).getLimit_num();
            if (System.currentTimeMillis() - CacheEdu.getInstance().getEduNotifyLastTime().longValue() >= 86400000) {
                CacheEdu.getInstance().setEduNotifyNum(0);
            }
            String title = list.get(0).getTitle();
            if (!TextUtils.equals(title, CacheEdu.getInstance().getEduNotifyTitle())) {
                CacheEdu.getInstance().setEduNotifyTitle(title);
                CacheEdu.getInstance().setEduNotifyNum(0);
                CacheEdu.getInstance().setEduNotifyLastTime(0);
            }
            int eduNotifyNum = CacheEdu.getInstance().getEduNotifyNum();
            try {
                if (new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").parse(list.get(0).getLimit_time() + " 23-59-59").getTime() > System.currentTimeMillis() && eduNotifyNum < limit_num && (currentActivity instanceof MainActivity)) {
                    ((MainActivity) currentActivity).showPopUpWindow(list.get(0), eduNotifyNum);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void mo24395a(String str) {
        try {
            if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                if (this.f8865C == null) {
                    this.f8865C = GsonUtil.m14241a();
                }
                List<NotifyBean.DataBean.NotifyListBean> notify_list = ((NotifyBean) this.f8865C.mo21588a(str, NotifyBean.class)).getData().getNotify_list();
                if (notify_list != null && notify_list.size() != 0) {
                    mo24399a(notify_list);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: j */
    private void m10527j(String str) {
        String str2;
        String str3;
        if (str == null) {
            CacheGame.getInstance().setGameConfig(null);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(EncryptUtil.decrypt(URLDecoder.decode(new JSONObject(str).getString("data"), "UTF-8")));
            if (jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                GameConfig aVar = new GameConfig();
                String str4 = "";
                ArrayList arrayList = new ArrayList();
                if (jSONObject2.has("common_ssid_name")) {
                    str4 = jSONObject2.getString("common_ssid_name");
                }
                int i = jSONObject2.has("game_enable") ? jSONObject2.getInt("game_enable") : 0;
                if (jSONObject2.has("game_ssid_name")) {
                    str2 = jSONObject2.getString("game_ssid_name");
                } else {
                    str2 = "";
                }
                if (jSONObject2.has("game_help_url")) {
                    str3 = jSONObject2.getString("game_help_url");
                } else {
                    str3 = "";
                }
                if (jSONObject2.has("game_filter")) {
                    JSONArray jSONArray = jSONObject2.getJSONArray("game_filter");
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                        arrayList.add(new GameFilter(jSONObject3.getString("id"), jSONObject3.getString("name")));
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                if (jSONObject2.has("game_ipnetmask")) {
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("game_ipnetmask");
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        JSONObject jSONObject4 = jSONArray2.getJSONObject(i3);
                        arrayList2.add(new GameIpNetMask(jSONObject4.getString("ip"), jSONObject4.getString("netmask")));
                    }
                }
                if (i > 0 && (str4 == null || str4.equals(""))) {
                    str4 = GBApplication.instance().getResources().getString(C2425R.C2429string.wifi_name);
                }
                aVar.mo24520a(str4);
                aVar.mo24519a(i);
                aVar.mo24524b(str2);
                aVar.mo24527c(str3);
                aVar.mo24521a(arrayList);
                aVar.mo24525b(arrayList2);
                GameConfig gameConfig = CacheGame.getInstance().getGameConfig();
                if (gameConfig == null || !aVar.mo24522a(gameConfig)) {
                    CacheGame.getInstance().setGameConfig(aVar);
                    GBApplication.instance().sendBroadcast(new Intent(Constant.f13249bX));
                    return;
                }
                return;
            }
            String string = jSONObject.getString("resultMsg");
            if (GBApplication.instance().getCurrentActivity() != null) {
                C3470v.m14563a(GBApplication.instance(), string);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            CacheGame.getInstance().setGameConfig(null);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            CacheGame.getInstance().setGameConfig(null);
        }
    }

    /* renamed from: a */
    public void onRequestFinish(Request abVar, String str) {
        if (abVar == this.f8883l) {
            m10516e(str);
        } else if (abVar == this.f8886o) {
            m10519f(str);
        } else if (abVar == this.f8890v) {
            if (GBApplication.instance().getCurrentActivity() != null) {
                GBApplication.instance().getCurrentActivity().dismissProgressDialog();
                GBActivity.openBackWebView(this.f8891w, "");
                CacheApp.getInstance().setWeChatGuide(true);
                CacheAuth.getInstance().setLastReleaseTime(System.currentTimeMillis());
            }
        } else if (this.f8866D == abVar) {
            m10521g(str);
            m10515e();
        } else if (this.f8867E == abVar) {
            m10523h(str);
        } else if (this.f8868F == abVar) {
            m10525i(str);
        } else if (abVar == this.f8869G) {
            Log.m14398b(f8858j, "getLoginToken response:" + str);
            TokenUtil.m14091a(abVar, str);
        } else if (abVar == this.f8870H) {
            Log.m14398b(f8858j, "getRegisteredFaceId response:" + str);
            TokenUtil.m14092a(str);
        } else if (abVar == this.f8871I) {
            m10527j(str);
        }
        if (abVar == this.f8884m) {
            CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
            if (!GBActivity.dealException(deSerializeJson)) {
                GBActivity.showMessageToast(deSerializeJson.getResultMsg());
                this.f8875M.sendEmptyMessageDelayed(1, 4000);
                GBApplication.instance().getCurrentActivity().showProgressDialog(".....");
            }
        }
        if (abVar == this.f8885n) {
            try {
                JSONObject jSONObject = new JSONObject(EncryptUtil.decrypt(URLDecoder.decode(new JSONObject(str).getString("data"), "UTF-8")));
                int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
                String string = jSONObject.getString("resultMsg");
                if (i == 0) {
                    GBActivity.showMessageToast(string);
                    this.f8875M.sendEmptyMessageDelayed(1, 4000);
                    GBApplication.instance().getCurrentActivity().showProgressDialog(".....");
                } else {
                    GBActivity.showMessageToast(string);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        if (abVar == this.f8872J) {
            Log.m14398b(f8858j, "wifiChange send notify to gateway succeed");
            this.f8873K = 0;
            GBApplication.instance().sendBroadcast(new Intent(Constant.f13236bK));
        }
        if (abVar == this.f8874L) {
            mo24395a(str);
        }
    }

    /* renamed from: w */
    private void m10533w() {
        GBApplication.instance().getCurrentActivity().showThirdDialog("提示", "WiFi充值免关注微信公众号!", "充值", "继续", new GBActivity.AbstractC2518b() {
            /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C255025 */

            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2518b
            /* renamed from: a */
            public void mo24345a(Dialog dialog, View view) {
                dialog.dismiss();
                GBGlobalConfig.this.m10531u();
                String c = HttpUtil.m14307c();
                if (!C3467s.m14513e(c)) {
                    GBActivity.openBackWebView(c, "");
                } else {
                    GBActivity.showMessageToast("链接无效...");
                }
            }
        }, new GBActivity.AbstractC2520d() {
            /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C255126 */

            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2520d
            /* renamed from: a */
            public void mo24347a(Dialog dialog, View view) {
                dialog.dismiss();
                int a = VersionUtil.m14564a(GBApplication.instance());
                if (CacheApp.getInstance().getWeChatGuide() && a == CacheApp.getInstance().getFirstGuideCode().intValue()) {
                    GBApplication.instance().getCurrentActivity().showProgressDialog("");
                    GBGlobalConfig.this.m10530t();
                } else if (GBApplication.instance().getCurrentActivity() != null) {
                    GBApplication.instance().getCurrentActivity().showWebDialog(new GBActivity.AbstractC2521e() {
                        /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C255126.C25521 */

                        @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2521e
                        /* renamed from: a */
                        public void mo24348a(Dialog dialog, View view) {
                            dialog.dismiss();
                            GBApplication.instance().getCurrentActivity().showProgressDialog("");
                            GBGlobalConfig.this.m10530t();
                        }
                    });
                }
            }
        }, new GBActivity.AbstractC2519c() {
            /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C255327 */

            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2519c
            /* renamed from: a */
            public void mo24346a(Dialog dialog, View view) {
                dialog.dismiss();
                if (GBApplication.instance().getCurrentActivity() != null) {
                    GBApplication.instance().getCurrentActivity().showWebDialog(new GBActivity.AbstractC2521e() {
                        /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C255327.C25541 */

                        @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2521e
                        /* renamed from: a */
                        public void mo24348a(Dialog dialog, View view) {
                            dialog.dismiss();
                            GBApplication.instance().getCurrentActivity().showProgressDialog("");
                            GBGlobalConfig.this.m10530t();
                        }
                    });
                }
            }
        });
    }

    /* renamed from: a */
    private void m10502a(final CommonMsg commonMsg, final HashMap hashMap, final String str) {
        String str2 = (String) hashMap.get("tips");
        if (C3467s.m14513e(str2)) {
            m10509b(commonMsg, hashMap, str);
        } else if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().getCurrentActivity().showSecondDialog("提示", str2, "确定", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C255528 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    GBGlobalConfig.this.m10509b(commonMsg, hashMap, str);
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.DialogInterface$OnCancelListenerC255629 */

                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                    GBGlobalConfig.this.m10509b(commonMsg, hashMap, str);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: x */
    private void m10534x() {
        if (MainActivity.needCompleteInfo) {
            m10510c();
            m10503a(MainActivity.completeInfoUrl, MainActivity.completeInfoMsg);
            return;
        }
        m10510c();
        m10518f();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m10509b(CommonMsg commonMsg, HashMap hashMap, String str) {
        boolean z;
        boolean z2;
        HashMap<String, Integer> hashMap2;
        int i;
        Boolean bool = (Boolean) hashMap.get("checkPhone");
        if (bool == null || !bool.booleanValue()) {
            Integer num = (Integer) hashMap.get("userType");
            String str2 = (String) hashMap.get("showtext");
            Integer num2 = (Integer) hashMap.get("mode");
            Integer num3 = (Integer) hashMap.get("willStop");
            CacheAccount.getInstance().updateInfo(this.f8887p, (String) hashMap.get("userToken"));
            if (hashMap.containsKey("event")) {
                f8857h = false;
                HashMap hashMap3 = (HashMap) hashMap.get("event");
                this.f8864B = (String) hashMap3.get("cancelClickUrl");
                this.f8863A = (String) hashMap3.get("cancelMessage");
                this.f8894z = (String) hashMap3.get("imageClickUrl");
                View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.sys_popup_notification, (ViewGroup) null);
                ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.iv_tp_ad);
                imageView.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.View$OnClickListenerC255830 */

                    public void onClick(View view) {
                        GBActivity.openBackWebView(GBGlobalConfig.this.f8894z, "");
                        GBGlobalConfig.this.f8893y.dismiss();
                    }
                });
                ((ImageView) inflate.findViewById(C2425R.C2427id.btn_tp_ad)).setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.View$OnClickListenerC25422 */

                    public void onClick(View view) {
                        if (TextUtils.isEmpty(GBGlobalConfig.this.f8864B) && TextUtils.isEmpty(GBGlobalConfig.this.f8863A)) {
                            GBGlobalConfig.this.f8893y.dismiss();
                        }
                        if (!TextUtils.isEmpty(GBGlobalConfig.this.f8863A)) {
                            GBApplication.instance().getCurrentActivity().showSecondDialog("提示", GBGlobalConfig.this.f8863A, "确定", new GBActivity.AbstractC2517a() {
                                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.View$OnClickListenerC25422.C25431 */

                                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                                public void onClick(Dialog dialog, View view) {
                                    GBActivity.openBackWebView(GBGlobalConfig.this.f8864B, "");
                                    GBGlobalConfig.f8857h = true;
                                    dialog.dismiss();
                                    GBGlobalConfig.this.f8893y.dismiss();
                                }
                            }, new DialogInterface.OnCancelListener() {
                                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.View$OnClickListenerC25422.DialogInterface$OnCancelListenerC25442 */

                                public void onCancel(DialogInterface dialogInterface) {
                                    dialogInterface.dismiss();
                                    GBGlobalConfig.f8857h = true;
                                    GBGlobalConfig.this.f8893y.dismiss();
                                }
                            });
                        }
                    }
                });
                ImageTools.m14148a((String) hashMap3.get("showImage"), imageView);
                int c = ScreenUtil.m14176c(GBApplication.instance()) + StatusBarUtil.m14184a(GBApplication.instance());
                if (this.f8893y == null) {
                    this.f8893y = new PopupWindow(inflate, -1, c, true);
                }
                this.f8893y.setClippingEnabled(false);
                this.f8893y.setBackgroundDrawable(new ColorDrawable());
                MainActivity currentMainActivity = GBApplication.instance().getCurrentMainActivity();
                if (currentMainActivity != null) {
                    this.f8893y.showAtLocation(currentMainActivity.getWindow().getDecorView(), 51, 0, 0);
                } else {
                    return;
                }
            }
            if (num3 == null || num3.intValue() != 1) {
                if (2 == num2.intValue() && num.intValue() == 0 && !TextUtils.isEmpty(str2)) {
                    z = true;
                } else {
                    z = false;
                }
                if (str == null || C3467s.m14513e((String) hashMap.get("redirect_url"))) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                MainActivity.needCompleteInfo = z2;
                if (z) {
                    if (z2) {
                        MainActivity.completeInfoUrl = (String) hashMap.get("redirect_url");
                        MainActivity.completeInfoMsg = commonMsg.getResultMsg();
                    }
                    GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
                    if (currentActivity != null) {
                        currentActivity.showSecondDialog("", "登录成功！\n业务使用期剩余" + str2, "开始上网", new GBActivity.AbstractC2517a() {
                            /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C25573 */

                            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                            public void onClick(Dialog dialog, View view) {
                                GBGlobalConfig.this.m10534x();
                            }
                        }, new DialogInterface.OnCancelListener() {
                            /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.DialogInterface$OnCancelListenerC25594 */

                            public void onCancel(DialogInterface dialogInterface) {
                                GBGlobalConfig.this.m10534x();
                            }
                        });
                    }
                } else if (z2) {
                    m10503a(hashMap.get("redirect_url").toString(), commonMsg.getResultMsg());
                } else if (f8857h) {
                    m10518f();
                }
            } else {
                int currentTimeMillis = (int) (System.currentTimeMillis() / 86400000);
                HashMap<String, Integer> willStopTime = CacheAccount.getInstance().getWillStopTime();
                String loginPhone = CacheAccount.getInstance().getLoginPhone();
                if (willStopTime == null) {
                    hashMap2 = new HashMap<>();
                } else {
                    hashMap2 = willStopTime;
                }
                if (hashMap2.containsKey(loginPhone)) {
                    i = hashMap2.get(loginPhone).intValue();
                } else {
                    i = -1;
                }
                if (currentTimeMillis > i) {
                    hashMap2.put(loginPhone, Integer.valueOf(currentTimeMillis));
                    CacheAccount.getInstance().setWillStopTime(hashMap2);
                    mo24402b(commonMsg.getResultMsg());
                    return;
                }
            }
            if (this.f8889s != null) {
                this.f8889s.mo24437a(true);
                this.f8889s = null;
            }
            GBApplication.instance().sendBroadcast(new Intent(Constant.f13235bJ));
            return;
        }
        mo24402b(commonMsg.getResultMsg());
    }

    /* renamed from: a */
    private static boolean m10505a(HashMap<String, Object> hashMap, String str, String str2, String str3) {
        String str4 = (String) hashMap.get(str3);
        String str5 = null;
        if (hashMap.containsKey(str2)) {
            str5 = ((String) hashMap.get(str2)).trim();
        }
        if (str5 == null || C3467s.m14513e(str5) || str5.equals("23:59")) {
            GBActivity.openBackWebView(str4, "");
            return true;
        } else if (str.compareTo(str5) >= 0) {
            return false;
        } else {
            GBActivity.openBackWebView(str4, "");
            return true;
        }
    }

    /* renamed from: d */
    public static void m10512d() {
        if (!SystemUtil.m14531e()) {
            HttpUtil.m14356u(GBApplication.instance(), new OkRequestHandler<String>() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C25605 */

                @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
                public void onRequestStart(Request abVar) {
                }

                @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
                public void onRequestFailed(Request abVar, Exception exc) {
                }

                /* renamed from: a */
                public void onRequestFinish(Request abVar, String str) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.getInt("errcode") != 0) {
                            Log.m14398b(GBGlobalConfig.f8858j, "getInstructionCode fail " + jSONObject.getString("errmsg"));
                            return;
                        }
                        Object obj = jSONObject.get("data");
                        if (obj == null || !(obj instanceof JSONObject)) {
                            Log.m14398b(GBGlobalConfig.f8858j, "getInstructionCode invalid data ");
                            return;
                        }
                        String string = ((JSONObject) obj).getString("word");
                        if (C3467s.m14513e(string)) {
                            Log.m14398b(GBGlobalConfig.f8858j, "getInstructionCode word is empty");
                        } else {
                            ((ClipboardManager) GBApplication.instance().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Label", string));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.m14403e(GBGlobalConfig.f8858j, e.getMessage());
                    }
                }

                @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
                public void onRequestProgress(Request abVar, long j, long j2) {
                }
            }, f8859k);
        }
    }

    /* renamed from: e */
    public static void m10515e() {
        List<HashMap<String, Object>> adEventList = CacheAd.getInstance().getAdEventList();
        if (adEventList != null) {
            for (HashMap<String, Object> hashMap : adEventList) {
                if (hashMap.containsKey("state") && 1 == ((Integer) hashMap.get("state")).intValue()) {
                    Integer num = (Integer) hashMap.get("eventType");
                    if (((String) hashMap.get("userLevelList")).contains(CacheAccount.getInstance().getUserLevel() + "") && num != null && num.intValue() == 7) {
                        m10512d();
                        return;
                    }
                }
            }
        }
    }

    /* renamed from: f */
    public static void m10518f() {
        if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().sendBroadcast(new Intent(Constant.f13236bK));
            List<HashMap<String, Object>> adEventList = CacheAd.getInstance().getAdEventList();
            if (adEventList != null) {
                for (HashMap<String, Object> hashMap : adEventList) {
                    if (hashMap.containsKey("state") && 1 == ((Integer) hashMap.get("state")).intValue()) {
                        Integer num = (Integer) hashMap.get("eventType");
                        if (((String) hashMap.get("userLevelList")).contains(CacheAccount.getInstance().getUserLevel() + "") && num != null) {
                            if (num.intValue() == 6) {
                                int i = 0;
                                if (hashMap.containsKey("source_type") && hashMap.get("source_type") != null) {
                                    i = ((Integer) hashMap.get("source_type")).intValue();
                                }
                                if (i != 2) {
                                    m10504a(hashMap);
                                    return;
                                }
                                return;
                            }
                            if (num.intValue() == 1) {
                                String d = FormatUtil.m14230d();
                                if (!m10505a(hashMap, d, "division_time", "url") && !m10505a(hashMap, d, "division_time1", "url1")) {
                                    m10505a(hashMap, d, "division_time2", "url2");
                                } else {
                                    return;
                                }
                            }
                            if (num.intValue() == 3) {
                                CacheApp.getInstance().setAfterAuthJumpTabType(((Integer) hashMap.get("tab_type")).intValue());
                                GBApplication.instance().sendBroadcast(new Intent(Constant.f13242bQ));
                            }
                            if (num.intValue() == 7) {
                                m10512d();
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static void m10504a(HashMap<String, Object> hashMap) {
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            GBElementManager.m10471a().mo24383a(hashMap, currentActivity, (Fragment) null);
        }
    }

    /* renamed from: a */
    public static void m10503a(final String str, String str2) {
        GBActivity currentActivity;
        if (!C3467s.m14513e(str) && (currentActivity = GBApplication.instance().getCurrentActivity()) != null) {
            currentActivity.showNormalDialog("提示", str2 != null ? str2 : "请完善个人信息", "立即完善", "关闭", (GBActivity.AbstractC2517a) new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C25616 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    GBActivity.openBackWebView(str, "完善个人信息");
                }
            }, (GBActivity.AbstractC2517a) new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C25627 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                }
            }, false, (DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.DialogInterface$OnCancelListenerC25638 */

                public void onCancel(DialogInterface dialogInterface) {
                }
            });
        }
    }

    @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
    public void onRequestProgress(Request abVar, long j, long j2) {
    }

    /* renamed from: g */
    public void mo24407g() {
        String cacheSendPhone = CacheAccount.getInstance().getCacheSendPhone();
        String cacheSendContent = CacheAccount.getInstance().getCacheSendContent();
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + cacheSendPhone));
        intent.addFlags(268435456);
        intent.putExtra("sms_body", cacheSendContent);
        GBApplication.instance().startActivity(intent);
    }

    /* renamed from: b */
    public void mo24402b(String str) {
        mo24397a("通知", str, "取消", "发送", 1);
    }

    /* renamed from: h */
    public void mo24408h() {
        if (!C3467s.m14513e(CacheAccount.getInstance().getLoginPhone())) {
            mo24397a("通知", "当前帐号已在其他终端登录,是否重新认证?", "不必了", "重新认证", 2);
        }
    }

    /* renamed from: i */
    public void mo24409i() {
        mo24397a("用户服务协议", "GiWiFi在此特别提醒您认真阅读、充分理解本《服务协议》: 本《协议》是您与本公司之间关于用户注册、登录、使用GiWiFi网络和软件服务所订立的协议。本《协议》...", "取消", "同意协议", 3);
    }

    /* renamed from: j */
    public void mo24410j() {
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.showSecondDialog("通知", "您的账号信息可能已被变更，请您重新登录!", "确定", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C25649 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    GBGlobalConfig.this.m10535y();
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.DialogInterface$OnCancelListenerC253210 */

                public void onCancel(DialogInterface dialogInterface) {
                    GBGlobalConfig.this.m10535y();
                }
            });
        }
    }

    /* renamed from: k */
    public void mo24411k() {
        final GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.showSecondDialog("提示", "当前要求手机号登录,请选择手机号登录", "重新登录", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C253311 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    currentActivity.startActivity(new Intent(GBApplication.instance(), LoginThirdActivity.class));
                    dialog.dismiss();
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.DialogInterface$OnCancelListenerC253513 */

                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                }
            });
        }
    }

    /* renamed from: l */
    public void mo24412l() {
        final GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.showSecondDialog("提示", "当前要求学工号登录,请选择学工号登录", "重新登录", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C253614 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    currentActivity.startActivity(new Intent(GBApplication.instance(), StatusLoginActivity.class));
                    dialog.dismiss();
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.DialogInterface$OnCancelListenerC253715 */

                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                }
            });
        }
    }

    /* renamed from: a */
    public void mo24398a(String str, final boolean z) {
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.showSecondDialog("提示", str, "充值办理", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C253816 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    if (z) {
                        GBGlobalConfig.this.m10531u();
                    }
                    String c = HttpUtil.m14307c();
                    if (!C3467s.m14513e(c)) {
                        GBActivity.openBackWebView(c, "");
                    } else {
                        GBActivity.showMessageToast("链接无效...");
                    }
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.DialogInterface$OnCancelListenerC253917 */

                public void onCancel(DialogInterface dialogInterface) {
                }
            });
        }
    }

    /* renamed from: b */
    public void mo24404b(String str, final boolean z) {
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.showSecondDialog("提示", str, "绑定", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C254018 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    if (!z) {
                        GBGlobalConfig.this.m10536z();
                    } else {
                        GBGlobalConfig.this.m10498A();
                    }
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.DialogInterface$OnCancelListenerC254119 */

                public void onCancel(DialogInterface dialogInterface) {
                }
            });
        }
    }

    /* renamed from: c */
    public void mo24405c(String str) {
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.showSecondDialog("提示", str, "完善资料", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.C254520 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    String d = HttpUtil.m14313d();
                    if (!C3467s.m14513e(d)) {
                        GBActivity.openBackWebView(d, "");
                    } else {
                        GBActivity.showMessageToast("链接无效...");
                    }
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.DialogInterface$OnCancelListenerC254621 */

                public void onCancel(DialogInterface dialogInterface) {
                }
            });
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0051, code lost:
        if (r9.f8883l == null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        com.gbcom.gwifi.util.HttpUtil.m14287a(com.gbcom.gwifi.base.p233b.GBGlobalConfig.f8859k);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
        if (r9.f8886o == null) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005c, code lost:
        com.gbcom.gwifi.util.HttpUtil.m14287a(com.gbcom.gwifi.base.p233b.GBGlobalConfig.f8859k);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0061, code lost:
        r9.f8887p = com.gbcom.gwifi.util.cache.CacheAccount.getInstance().getLoginPhone();
        r9.f8888q = com.gbcom.gwifi.util.cache.CacheAccount.getInstance().getLoginStaticPassword();
        com.gbcom.gwifi.base.app.GBApplication.instance().sendBroadcast(new android.content.Intent(com.gbcom.gwifi.functions.wifi.CheckNetWorkReceiver.f12765b));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008b, code lost:
        if (com.gbcom.gwifi.util.cache.CacheAuth.getInstance().getAuthType() != 1) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0095, code lost:
        if (com.gbcom.gwifi.util.cache.CacheAuth.getInstance().isPortal() == false) goto L_0x00df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0097, code lost:
        r9.f8886o = com.gbcom.gwifi.util.HttpUtil.m14348q(com.gbcom.gwifi.base.app.GBApplication.instance(), r9, com.gbcom.gwifi.base.p233b.GBGlobalConfig.f8859k);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a5, code lost:
        if (r9.f8886o != null) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a7, code lost:
        com.gbcom.gwifi.util.Log.m14403e(com.gbcom.gwifi.base.p233b.GBGlobalConfig.f8858j, "oldUserPortalLoginRequest is null");
        com.gbcom.gwifi.base.app.GBApplication.instance().sendBroadcast(new android.content.Intent(com.gbcom.gwifi.functions.wifi.CheckNetWorkReceiver.f12766c));
        r1 = com.gbcom.gwifi.base.p233b.GBGlobalConfig.f8861t;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00be, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        com.gbcom.gwifi.base.p233b.GBGlobalConfig.f8861t = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c6, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c9, code lost:
        if (r9.f8889s == null) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00cb, code lost:
        r9.f8889s.mo24437a(false);
        r9.f8889s = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00df, code lost:
        r9.f8883l = com.gbcom.gwifi.util.HttpUtil.m14304c(com.gbcom.gwifi.base.app.GBApplication.instance(), r9.f8887p, "", r9.f8888q, r9, com.gbcom.gwifi.base.p233b.GBGlobalConfig.f8859k);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f4, code lost:
        if (r9.f8883l != null) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f6, code lost:
        com.gbcom.gwifi.util.Log.m14403e(com.gbcom.gwifi.base.p233b.GBGlobalConfig.f8858j, "oldUserLoginRequest is null");
        com.gbcom.gwifi.base.app.GBApplication.instance().sendBroadcast(new android.content.Intent(com.gbcom.gwifi.functions.wifi.CheckNetWorkReceiver.f12766c));
        r1 = com.gbcom.gwifi.base.p233b.GBGlobalConfig.f8861t;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010d, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        com.gbcom.gwifi.base.p233b.GBGlobalConfig.f8861t = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0115, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0118, code lost:
        if (r9.f8889s == null) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011a, code lost:
        r9.f8889s.mo24437a(false);
        r9.f8889s = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0129, code lost:
        com.gbcom.gwifi.base.app.GBApplication.instance().sendBroadcast(new android.content.Intent(com.gbcom.gwifi.functions.wifi.CheckNetWorkReceiver.f12766c));
        r1 = com.gbcom.gwifi.base.p233b.GBGlobalConfig.f8861t;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0139, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        com.gbcom.gwifi.base.p233b.GBGlobalConfig.f8861t = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0141, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0144, code lost:
        if (r9.f8889s == null) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0146, code lost:
        r9.f8889s.mo24437a(false);
        r9.f8889s = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        return true;
     */
    /* renamed from: m */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo24413m() {
        /*
        // Method dump skipped, instructions count: 341
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.base.p233b.GBGlobalConfig.mo24413m():boolean");
    }

    /* renamed from: a */
    public void mo24397a(String str, String str2, String str3, String str4, Integer num) {
        if (!CacheAccount.getInstance().getLoginPhone().equals("")) {
            Intent intent = new Intent(GBApplication.instance(), DialogActivity.class);
            intent.putExtra("title", str);
            intent.putExtra("content", str2);
            intent.putExtra("positiveText", str3);
            intent.putExtra("negativeText", str4);
            intent.putExtra("type", num);
            intent.setFlags(268435456);
            GBApplication.instance().startActivity(intent);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: y */
    private void m10535y() {
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            String loginPhone = CacheAccount.getInstance().getLoginPhone();
            CacheAccount.getInstance().clearCacheAccountPassword(loginPhone);
            CacheAuth.getInstance().resetPortalDisable();
            if (CacheAccount.getInstance().getLastLoginType() == 2) {
                Intent intent = new Intent(GBApplication.instance(), StatusLoginActivity.class);
                intent.putExtra(Constant.f13323i, loginPhone);
                currentActivity.startActivity(intent);
            } else {
                Intent intent2 = new Intent(GBApplication.instance(), LoginThirdActivity.class);
                intent2.putExtra(Constant.f13323i, loginPhone);
                currentActivity.startActivity(intent2);
            }
            GBApplication.instance().exitOther();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: z */
    private void m10536z() {
        this.f8884m = HttpUtil.m14261a(GBApplication.instance(), this.f8887p, this, "");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: A */
    private void m10498A() {
        this.f8885n = HttpUtil.m14350r(GBApplication.instance(), this, "");
    }

    /* renamed from: n */
    public void mo24414n() {
        if (TokenUtil.m14093a()) {
            this.f8870H = HttpUtil.m14337l(this, "");
        } else {
            this.f8869G = HttpUtil.m14334k(this, "");
        }
    }

    /* renamed from: o */
    public void mo24415o() {
        if (System.currentTimeMillis() - this.f8877O < 3000) {
            Log.m14398b(f8858j, "GlobalConfig: querySsidWithAdstate too many " + this.f8882i.format(new Date(System.currentTimeMillis())));
            return;
        }
        this.f8877O = System.currentTimeMillis();
        Log.m14398b(f8858j, "GlobalConfig: querySsidWithAdstate " + this.f8882i.format(new Date(System.currentTimeMillis())));
        this.f8866D = HttpUtil.m14312d(this, "");
    }

    /* renamed from: a */
    public void mo24396a(String str, Context context) {
        if (System.currentTimeMillis() - this.f8878P < 3000) {
            Log.m14398b(f8858j, "GlobalConfig: queryTab too many " + this.f8882i.format(new Date(System.currentTimeMillis())));
            return;
        }
        this.f8878P = System.currentTimeMillis();
        Log.m14398b(f8858j, "GlobalConfig: queryTab " + this.f8882i.format(new Date(System.currentTimeMillis())));
        this.f8867E = HttpUtil.m14297b(this, str, context);
    }

    /* renamed from: b */
    public void mo24403b(String str, Context context) {
        if (System.currentTimeMillis() - this.f8879Q < 3000) {
            Log.m14398b(f8858j, "GlobalConfig: queryLoginSetting too many " + this.f8882i.format(new Date(System.currentTimeMillis())));
            return;
        }
        this.f8879Q = System.currentTimeMillis();
        Log.m14398b(f8858j, "GlobalConfig: queryLoginSetting " + this.f8882i.format(new Date(System.currentTimeMillis())));
        this.f8868F = HttpUtil.m14306c(this, str, context);
    }

    /* renamed from: p */
    public void mo24416p() {
        if (System.currentTimeMillis() - this.f8880R < 3000) {
            Log.m14398b(f8858j, "GlobalConfig: queryEduNotify too many " + this.f8882i.format(new Date(System.currentTimeMillis())));
            return;
        }
        this.f8880R = System.currentTimeMillis();
        Log.m14398b(f8858j, "GlobalConfig: queryEduNotify " + this.f8882i.format(new Date(System.currentTimeMillis())));
        this.f8874L = HttpUtil.m14288b(0, this, "");
    }

    /* renamed from: q */
    public void mo24417q() {
        if (System.currentTimeMillis() - this.f8881S < 3000) {
            Log.m14398b(f8858j, "GlobalConfig: getGameConfig too many " + this.f8882i.format(new Date(System.currentTimeMillis())));
            return;
        }
        this.f8881S = System.currentTimeMillis();
        Log.m14398b(f8858j, "GlobalConfig: getGameConfig " + this.f8882i.format(new Date(System.currentTimeMillis())));
        this.f8871I = HttpUtil.m14340m(this, "");
    }

    /* renamed from: r */
    public void mo24418r() {
        if (!CacheAuth.getInstance().checkGameIpNetMask()) {
            Log.m14398b(f8858j, "BaseGiWiFiInfoView: checkGameIpNetMask false");
            return;
        }
        this.f8873K = 0;
        if (CacheAuth.getInstance().isPortal()) {
            Log.m14398b(f8858j, "BaseGiWiFiInfoView: send syncAuthState to aaa");
            this.f8872J = HttpUtil.m14328i(this, "");
            return;
        }
        Log.m14398b(f8858j, "BaseGiWiFiInfoView: send syncAuthState to gateway");
        this.f8872J = HttpUtil.m14305c(this, "");
    }

    /* renamed from: s */
    public void mo24419s() {
        if (CacheAuth.getInstance().isPortal()) {
            Log.m14398b(f8858j, "wifiChange send notify to aaa");
            this.f8872J = HttpUtil.m14328i(this, "");
            return;
        }
        Log.m14398b(f8858j, "wifiChange send notify to gateway");
        this.f8872J = HttpUtil.m14305c(this, "");
    }

    /* renamed from: d */
    public boolean mo24406d(String str) {
        String str2;
        long j;
        long j2;
        Object obj;
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (GBActivity.dealException(deSerializeJson)) {
            return false;
        }
        HashMap hashMap = (HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class);
        int intValue = ((Integer) hashMap.get("uid")).intValue();
        Integer.valueOf(((Integer) hashMap.get("sign_state")) == null ? 0 : ((Integer) hashMap.get("sign_state")).intValue());
        Integer valueOf = Integer.valueOf(((Integer) hashMap.get("user_level")) == null ? 0 : ((Integer) hashMap.get("user_level")).intValue());
        String str3 = hashMap.containsKey("user_level_name") ? (String) hashMap.get("user_level_name") : "";
        Integer valueOf2 = Integer.valueOf(hashMap.get("hotspot_group_id") != null ? Integer.parseInt(hashMap.get("hotspot_group_id").toString()) : 0);
        Integer valueOf3 = Integer.valueOf(hashMap.get("identity_type") != null ? Integer.parseInt(hashMap.get("identity_type").toString()) : 0);
        Integer valueOf4 = Integer.valueOf(hashMap.get("college_id") != null ? Integer.parseInt(hashMap.get("college_id").toString()) : 0);
        Integer valueOf5 = Integer.valueOf(hashMap.get("department_id") != null ? Integer.parseInt(hashMap.get("department_id").toString()) : 0);
        String str4 = (String) hashMap.get("avatarUrl");
        if (TextUtils.isEmpty(str4)) {
            str2 = "";
        } else {
            str2 = str4;
        }
        Integer num = (Integer) hashMap.get("gender");
        String c = FormatUtil.m14226c(num.intValue());
        String str5 = (String) hashMap.get("alias");
        String str6 = (String) hashMap.get("email");
        String str7 = (String) hashMap.get("identity_name");
        String str8 = hashMap.get("identity_id") != null ? (String) hashMap.get("identity_id") : "";
        String str9 = (String) hashMap.get("remain_time");
        String str10 = (String) hashMap.get("lan_remain_time");
        String str11 = (String) hashMap.get("balance");
        String str12 = (String) hashMap.get("total_score");
        HashMap<String, Object> hashMap2 = null;
        if (hashMap.containsKey("subdata1")) {
            hashMap2 = (HashMap) hashMap.get("subdata1");
        }
        CacheAccount.getInstance().setCacheAccountBean(intValue, valueOf.intValue(), str3, valueOf2.intValue(), valueOf3.intValue(), valueOf4.intValue(), valueOf5.intValue(), str7, str8, num.intValue(), c, str5, str2, str6, str9, str10, str11, str12, hashMap2);
        if (hashMap.containsKey("agrt_service_update_time")) {
            j = ((Long) hashMap.get("agrt_service_update_time")).longValue();
        } else {
            j = 0;
        }
        if (hashMap.containsKey("agrt_privacy_update_time")) {
            j2 = ((Long) hashMap.get("agrt_privacy_update_time")).longValue();
        } else {
            j2 = 0;
        }
        CacheApp.getInstance().setAgrtServiceUpdateTime(j);
        CacheApp.getInstance().setAgrtPrivacyUpdateTime(j2);
        Integer.valueOf(0);
        if (hashMap.containsKey("agreement_signed")) {
            CacheAccount.getInstance().setSignAgreementState(((Integer) hashMap.get("agreement_signed")).intValue());
        }
        if (hashMap.containsKey("remain_time_seconds") && (obj = hashMap.get("remain_time_seconds")) != null) {
            CacheAccount.getInstance().setRemainTimeSecondValue(Double.valueOf(obj.toString()).longValue());
        }
        return true;
    }

    /* renamed from: a */
    public void mo24392a(Activity activity, String str, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(activity).setMessage(str).setPositiveButton("确定", onClickListener).setNegativeButton("取消", onClickListener).create().show();
    }

    /* renamed from: a */
    public void mo24390a(Activity activity) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + activity.getPackageName()));
        intent.addCategory("android.intent.category.DEFAULT");
        activity.startActivityForResult(intent, 100);
    }

    /* renamed from: a */
    public void mo24391a(final Activity activity, String str) {
        mo24392a(activity, str, new DialogInterface.OnClickListener() {
            /* class com.gbcom.gwifi.base.p233b.GBGlobalConfig.DialogInterface$OnClickListenerC254924 */

            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case -1:
                        GBGlobalConfig.this.mo24390a(activity);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    /* renamed from: b */
    private void m10507b(GBActivity gBActivity, String str) {
        int i;
        int i2;
        String str2;
        JSONObject jSONObject;
        String str3;
        String str4 = null;
        try {
            Log.m14398b(f8858j, "processScanResponse() " + str);
            JSONObject jSONObject2 = new JSONObject(str);
            if (jSONObject2.has("action")) {
                i = jSONObject2.getInt("action");
            } else {
                i = 2;
            }
            if (i == 1 || i == 2) {
                if (jSONObject2.has("encrypt")) {
                    i2 = jSONObject2.getInt("encrypt");
                } else {
                    i2 = 2;
                }
                if (i2 == 1 || i2 == 2) {
                    if (jSONObject2.has("datas")) {
                        str2 = jSONObject2.getString("datas");
                    } else {
                        str2 = null;
                    }
                    if (C3467s.m14513e(str2)) {
                        Log.m14403e(f8858j, "processScanResponse() fail,datas=" + str2);
                        return;
                    }
                    if (2 == i2) {
                        String decrypt = EncryptUtil.decrypt(URLDecoder.decode(str2, "UTF-8"));
                        if (C3467s.m14513e(decrypt)) {
                            Log.m14403e(f8858j, "processScanResponse() fail,rawData is empty");
                            return;
                        }
                        jSONObject = new JSONObject(decrypt);
                    } else if (1 == i2) {
                        jSONObject = new JSONObject(str2);
                    } else {
                        jSONObject = null;
                    }
                    if (jSONObject == null) {
                        Log.m14403e(f8858j, "processScanResponse() fail,dataObject is null");
                        return;
                    }
                    if (jSONObject.has("url")) {
                        str3 = jSONObject.getString("url");
                    } else {
                        str3 = null;
                    }
                    if (C3467s.m14513e(str3)) {
                        Log.m14403e(f8858j, "processScanResponse() fail,url is null");
                        return;
                    }
                    if (jSONObject.has("param")) {
                        str4 = jSONObject.getString("param");
                    }
                    if (C3467s.m14513e(str4)) {
                        Log.m14403e(f8858j, "processScanResponse() fail,param is null");
                        str4 = "";
                    }
                    if (!C3467s.m14513e(str4)) {
                        str3 = str3 + "?" + str4;
                    }
                    Log.m14398b(f8858j, "processScanResponse() targetUrl=" + str3);
                    if (2 == i) {
                        GBActivity.openBackWebView(str3, "");
                    } else if (1 == i) {
                        OkHttpService.m10244a(URLUtil.m14541a(str3), (OkRequestHandler) null, (Object) null);
                    }
                } else {
                    Log.m14403e(f8858j, "processScanResponse() fail,encrypt=" + i2);
                }
            } else {
                Log.m14403e(f8858j, "processScanResponse() fail,action=" + i);
            }
        } catch (JSONException e) {
            Log.m14403e(f8858j, "processScanResponse():" + e.toString());
        } catch (UnsupportedEncodingException e2) {
            Log.m14403e(f8858j, "processScanResponse():" + e2.toString());
        }
    }

    /* renamed from: a */
    public void mo24394a(GBActivity gBActivity, String str) {
        int i = 0;
        Log.m14398b(f8858j, "scan text:" + str);
        if (!C3467s.m14513e(str)) {
            String[] strArr = {"gbcom://", "gwifi://", "giwifi://", "giedu://", "giadesfcf5b6b"};
            while (true) {
                if (i >= strArr.length) {
                    break;
                } else if (str.startsWith(strArr[i])) {
                    m10507b(gBActivity, str.substring(strArr[i].length()));
                    break;
                } else {
                    i++;
                }
            }
            if (i != strArr.length) {
                return;
            }
            if (JsonUtil.m14392c(str)) {
                m10507b(gBActivity, str);
            } else if (str.startsWith("http://") || str.startsWith("https://")) {
                GBActivity.openBackWebView(str, "");
            } else {
                mo24397a("提示", "扫描结果:" + str, "", "确定", 153);
            }
        }
    }
}
