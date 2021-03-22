package com.gbcom.gwifi.functions.wifi;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.codec.EncryptUtil;
import com.gbcom.gwifi.functions.test.DeviceTest2Activity;
import com.gbcom.gwifi.functions.wifi.entity.AuthInfo;
import com.gbcom.gwifi.functions.wifi.entity.CheckResult;
import com.gbcom.gwifi.p221a.p226d.OkHttpService;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Config;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Ipv4Util;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.NetworkUtils;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.p456io.IOException;
import java.p456io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;
import p041c.Response;

/* renamed from: com.gbcom.gwifi.functions.wifi.b */
public class DeviceCheckNetWorkUtils {

    /* renamed from: a */
    public static Handler f12985a = null;

    /* renamed from: b */
    private static String f12986b = "DeviceCheckNetWorkUtils";

    /* renamed from: c */
    private static final int f12987c = 1;

    /* renamed from: d */
    private static final int f12988d = 2;

    /* renamed from: e */
    private static Request f12989e;

    /* renamed from: f */
    private static Request f12990f;

    /* renamed from: g */
    private static Request f12991g;

    /* renamed from: h */
    private static Request f12992h;

    /* renamed from: i */
    private static AtomicBoolean f12993i = new AtomicBoolean(false);

    /* renamed from: j */
    private static CallBack f12994j;

    /* renamed from: k */
    private static CheckResult f12995k;

    /* renamed from: l */
    private static boolean f12996l;

    /* renamed from: m */
    private static OkRequestHandler<String> f12997m = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.wifi.DeviceCheckNetWorkUtils.C33932 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (abVar == DeviceCheckNetWorkUtils.f12989e) {
                CacheAuth.getInstance().resetCacheAuthBean();
                if (exc == null || (!(exc instanceof SocketTimeoutException) && !(exc instanceof ConnectTimeoutException))) {
                    DeviceCheckNetWorkUtils.f12995k.setGwReqState(0);
                } else {
                    DeviceCheckNetWorkUtils.f12995k.setGwReqState(1001);
                }
                DeviceCheckNetWorkUtils.f12995k.setGwReqMessage("异常信息 : " + exc.toString());
                DeviceCheckNetWorkUtils.f12985a.sendEmptyMessage(2);
            } else if (abVar == DeviceCheckNetWorkUtils.f12990f) {
                if (exc == null || (!(exc instanceof SocketTimeoutException) && !(exc instanceof ConnectTimeoutException))) {
                    DeviceCheckNetWorkUtils.f12995k.setGwReqState(0);
                } else {
                    DeviceCheckNetWorkUtils.f12995k.setGwReqState(1001);
                }
                DeviceCheckNetWorkUtils.f12995k.setGwReqMessage("异常信息 : " + exc.toString());
                DeviceCheckNetWorkUtils.f12985a.sendEmptyMessage(2);
            } else if (abVar == DeviceCheckNetWorkUtils.f12991g) {
                if (exc == null || (!(exc instanceof SocketTimeoutException) && !(exc instanceof ConnectTimeoutException))) {
                    DeviceCheckNetWorkUtils.f12995k.setLoginReqState(0);
                } else {
                    DeviceCheckNetWorkUtils.f12995k.setLoginReqState(1001);
                }
                DeviceCheckNetWorkUtils.f12995k.setLoginReqMessage(exc.toString());
                DeviceCheckNetWorkUtils.m13931q();
            } else if (abVar == DeviceCheckNetWorkUtils.f12992h) {
                if (exc == null || (!(exc instanceof SocketTimeoutException) && !(exc instanceof ConnectTimeoutException))) {
                    DeviceCheckNetWorkUtils.f12995k.setLoginReqState(0);
                } else {
                    DeviceCheckNetWorkUtils.f12995k.setLoginReqState(1001);
                }
                DeviceCheckNetWorkUtils.f12995k.setLoginReqMessage(exc.toString());
                DeviceCheckNetWorkUtils.m13931q();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == DeviceCheckNetWorkUtils.f12989e) {
                DeviceCheckNetWorkUtils.m13915e(str);
            } else if (abVar == DeviceCheckNetWorkUtils.f12990f) {
                DeviceCheckNetWorkUtils.m13917f(str);
            } else if (abVar == DeviceCheckNetWorkUtils.f12991g) {
                DeviceCheckNetWorkUtils.m13919g(str);
            } else if (abVar == DeviceCheckNetWorkUtils.f12992h) {
                DeviceCheckNetWorkUtils.m13921h(str);
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: a */
    public static void m13906a(Context context, CallBack aVar) {
        GBActivity currentActivity;
        synchronized (f12993i) {
            if (!f12993i.get() || (currentActivity = GBApplication.instance().getCurrentActivity()) == null || !(currentActivity instanceof DeviceTest2Activity)) {
                f12993i.set(true);
                f12994j = aVar;
                m13905a(context);
                f12995k = new CheckResult();
                f12985a.sendMessage(f12985a.obtainMessage(1, aVar));
            }
        }
    }

    /* renamed from: j */
    private static void m13924j() {
        String k = WiFiUtil.m14022a(GBApplication.instance()).mo27617k();
        if (!Ipv4Util.m14374b(k)) {
            Log.m14403e(f12986b, "gatewayIp " + k + " is invalid");
            CacheAuth.getInstance().setAuthMethod(0);
        } else if (NetworkUtils.m14428a(k, 8060, 1500)) {
            CacheAuth.getInstance().setAuthMethod(1);
        } else {
            String a = NetworkUtils.m14425a(Constant.f13230bE);
            if (C3467s.m14513e(a) || a.equals("127.0.0.1") || !Ipv4Util.m14374b(a)) {
                if (Config.m14094a().mo27813p()) {
                    String q = Config.m14094a().mo27814q();
                    if (!C3467s.m14513e(q) && Ipv4Util.m14374b(q) && NetworkUtils.m14428a(q, 80, 1500)) {
                        CacheAuth.getInstance().setAuthMethod(2);
                        CacheAuth.getInstance().setPortalHost(q);
                        AuthInfo authInfo = new AuthInfo();
                        authInfo.setAuthState(200);
                        f12995k.setAuthInfo(authInfo);
                        return;
                    }
                }
                CacheAuth.getInstance().setAuthMethod(0);
                return;
            }
            CacheAuth.getInstance().setAuthMethod(2);
            AuthInfo authInfo2 = new AuthInfo();
            authInfo2.setAuthState(200);
            f12995k.setAuthInfo(authInfo2);
        }
    }

    /* renamed from: k */
    private static boolean m13925k() {
        AuthInfo authInfo = new AuthInfo();
        authInfo.setAuthState(2);
        f12995k.setAuthInfo(authInfo);
        CacheAuth.getInstance().setAuthMethod(0);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004a, code lost:
        if (android.text.TextUtils.isEmpty(r1) == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00cf, code lost:
        if (android.text.TextUtils.isEmpty(r0) == false) goto L_0x00d1;
     */
    /* renamed from: l */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m13926l() {
        /*
        // Method dump skipped, instructions count: 393
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.functions.wifi.DeviceCheckNetWorkUtils.m13926l():boolean");
    }

    /* renamed from: m */
    private static boolean m13927m() {
        f12990f = HttpUtil.m14325h(f12997m, "");
        return true;
    }

    /* renamed from: n */
    private static boolean m13928n() {
        f12989e = HttpUtil.m14322g(f12997m, "");
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: o */
    public static void m13929o() {
        f12996l = m13926l();
        int authMethod = CacheAuth.getInstance().getAuthMethod();
        if (authMethod == 1) {
            m13928n();
        } else if (authMethod == 2) {
            m13927m();
        } else {
            String k = WiFiUtil.m14022a(GBApplication.instance()).mo27617k();
            if (!Ipv4Util.m14374b(k)) {
                Log.m14403e(f12986b, "gatewayIp " + k + " is invalid");
                CacheAuth.getInstance().setAuthMethod(0);
            } else if (NetworkUtils.m14428a(k, 8060, 1500)) {
                CacheAuth.getInstance().setAuthMethod(1);
                m13928n();
            } else {
                String a = NetworkUtils.m14425a(Constant.f13230bE);
                if (C3467s.m14513e(a) || a.equals("127.0.0.1") || !Ipv4Util.m14374b(a)) {
                    Log.m14403e(f12986b, "can't dns as.gwifi.com.cn");
                    Log.m14403e(f12986b, "as.gwifi.com.cn ip " + a + " is invalid");
                    if (Config.m14094a().mo27813p()) {
                        String q = Config.m14094a().mo27814q();
                        if (!C3467s.m14513e(q) && Ipv4Util.m14374b(q) && NetworkUtils.m14428a(q, 80, 1500)) {
                            CacheAuth.getInstance().setAuthMethod(2);
                            CacheAuth.getInstance().setPortalHost(q);
                            m13927m();
                            return;
                        }
                    }
                    CacheAuth.getInstance().setAuthMethod(0);
                    return;
                }
                CacheAuth.getInstance().setAuthMethod(2);
                m13927m();
            }
        }
    }

    /* renamed from: a */
    private static void m13905a(Context context) {
        if (f12985a == null) {
            HandlerThread handlerThread = new HandlerThread("noUiHandlerThread");
            handlerThread.start();
            f12985a = new Handler(handlerThread.getLooper()) {
                /* class com.gbcom.gwifi.functions.wifi.DeviceCheckNetWorkUtils.HandlerC33921 */

                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    switch (message.what) {
                        case 1:
                            DeviceCheckNetWorkUtils.m13929o();
                            return;
                        case 2:
                            DeviceCheckNetWorkUtils.m13932r();
                            DeviceCheckNetWorkUtils.m13931q();
                            return;
                        default:
                            return;
                    }
                }
            };
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public static void m13915e(String str) {
        String unsupportedEncodingException;
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (deSerializeJson != null) {
            String data = deSerializeJson.getData();
            HashMap hashMap = (HashMap) JsonUtil.m14386a(data, HashMap.class);
            Integer num = (Integer) hashMap.get("auth_state");
            String obj = hashMap.get("gw_id").toString();
            String str2 = (String) hashMap.get("station_sn");
            String str3 = (String) hashMap.get("client_mac");
            int intValue = ((Integer) hashMap.get("online_time")).intValue();
            int intValue2 = ((Integer) hashMap.get("logout_reason")).intValue();
            String str4 = (String) hashMap.get("contact_phone");
            String str5 = (String) hashMap.get("suggest_phone");
            if (hashMap.containsKey("orgId")) {
                String oriOrgId = CacheAuth.getInstance().getOriOrgId();
                String str6 = (String) hashMap.get("orgId");
                if (!TextUtils.equals(oriOrgId, str6)) {
                    CacheAccount.getInstance().setCollegeId(0);
                    CacheAccount.getInstance().setIdentityType(0);
                    CacheAccount.getInstance().setDepartmentId(0);
                }
                CacheAuth.getInstance().setOrgId(str6);
            }
            AuthInfo authInfo = new AuthInfo();
            authInfo.setAuthState(num);
            authInfo.setGwId(obj);
            authInfo.setStationSn(str2);
            authInfo.setClientMac(str3);
            authInfo.setOnlineTime(Integer.valueOf(intValue));
            authInfo.setLogoutReason(Integer.valueOf(intValue2));
            authInfo.setContactPhone(str4);
            authInfo.setSuggestPhone(str5);
            f12995k.setAuthInfo(authInfo);
            f12995k.setGwReqState(1);
            f12995k.setGwReqMessage(data);
            CacheAuth.getInstance().resetCacheAuthBean();
            CacheAuth.getInstance().setCacheAuthBean(num.intValue(), obj, str2, str3, intValue, intValue2, str4, str5);
            if (num.intValue() == 0 || num.intValue() == 2 || num.intValue() == 200 || num.intValue() == 253) {
                f12985a.sendEmptyMessage(2);
            } else {
                m13930p();
            }
        } else {
            CacheAuth.getInstance().resetCacheAuthBean();
            try {
                unsupportedEncodingException = new String(str.getBytes(), "UTF-8").trim();
            } catch (UnsupportedEncodingException e) {
                unsupportedEncodingException = e.toString();
            }
            f12995k.setGwReqState(1002);
            f12995k.setGwReqMessage("解析失败:" + unsupportedEncodingException + "\n");
            f12985a.sendEmptyMessage(2);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public static void m13917f(String str) {
        int i;
        int i2;
        String portalNasName;
        String string;
        int i3 = 1;
        if (str == null) {
            f12995k.setGwReqState(1002);
            f12995k.setGwReqMessage("请求失败:No Response");
            f12985a.sendEmptyMessage(2);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(EncryptUtil.decrypt(URLDecoder.decode(new JSONObject(str).getString("data"), "UTF-8")));
            int i4 = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            jSONObject.getString("resultMsg");
            f12995k.setGwReqState(1);
            f12995k.setGiwifi(true);
            CacheAuth.getInstance().setAuthMethod(2);
            if (i4 != 0) {
                f12995k.setGwReqState(1002);
                f12995k.setGwReqMessage("请求失败:resultCode=" + i4);
                f12985a.sendEmptyMessage(2);
                return;
            }
            int i5 = -1;
            String str2 = "";
            String str3 = "";
            String str4 = "";
            String str5 = "";
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                if (jSONObject2.has("authState")) {
                    i5 = jSONObject2.getInt("authState");
                }
                if (jSONObject2.has("onlineTime")) {
                    int i6 = jSONObject2.getInt("onlineTime");
                    CacheAuth.getInstance().setOnlineTime(i6);
                    CacheAuth.getInstance().setPortalOnlineTime(i6);
                    i = i6;
                } else {
                    i = 0;
                }
                if (jSONObject2.has("nasName") && (((portalNasName = CacheAuth.getInstance().getPortalNasName()) == null || TextUtils.isEmpty(portalNasName)) && (string = jSONObject2.getString("nasName")) != null && !TextUtils.isEmpty(string))) {
                    CacheAuth.getInstance().setPortalNasName(string);
                }
                if (jSONObject2.has("stationSn")) {
                    str2 = jSONObject2.getString("stationSn");
                    CacheAuth.getInstance().setGwSn(str2);
                }
                if (jSONObject2.has("contactPhone")) {
                    str3 = jSONObject2.getString("contactPhone");
                    CacheAuth.getInstance().setContactPhone(str3);
                }
                if (jSONObject2.has("suggestPhone")) {
                    str4 = jSONObject2.getString("suggestPhone");
                    CacheAuth.getInstance().setSuggestPhone(str4);
                }
                if (jSONObject2.has("userMac")) {
                    str5 = jSONObject2.getString("userMac");
                    CacheAuth.getInstance().setLocalMac(str5);
                }
                if (jSONObject2.has("logoutReason")) {
                    i2 = jSONObject2.getInt("logoutReason");
                    CacheAuth.getInstance().setLogoutReason(i2);
                } else {
                    i2 = 0;
                }
                if (!f12996l) {
                    i3 = i5;
                }
                AuthInfo authInfo = new AuthInfo();
                authInfo.setStationSn(str2);
                if (2 == i3 || 200 == i3) {
                    authInfo.setClientMac(str5);
                    authInfo.setOnlineTime(Integer.valueOf(i));
                    authInfo.setLogoutReason(Integer.valueOf(i2));
                    authInfo.setContactPhone(str3);
                    authInfo.setSuggestPhone(str4);
                    if (jSONObject2.has("nasName")) {
                        String string2 = jSONObject2.getString("nasName");
                        if (!TextUtils.isEmpty(string2)) {
                            CacheAuth.getInstance().setPortalNasName(string2);
                        }
                    }
                    authInfo.setAuthState(Integer.valueOf(i3));
                } else {
                    authInfo.setAuthState(1);
                }
                f12995k.setAuthInfo(authInfo);
                f12995k.setGwReqState(1);
                f12995k.setGwReqMessage(str);
                if (i3 == 0 || i3 == 2 || i3 == 200) {
                    f12985a.sendEmptyMessage(2);
                } else {
                    m13930p();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                f12995k.setGwReqState(0);
                f12995k.setGwReqMessage("请求失败:" + e.getMessage());
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            f12995k.setGwReqState(0);
            f12995k.setGwReqMessage("请求失败:" + e2.getMessage());
        } catch (JSONException e3) {
            e3.printStackTrace();
            f12995k.setGwReqState(0);
            f12995k.setGwReqMessage("请求失败:" + e3.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public static void m13919g(String str) {
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (deSerializeJson == null) {
            f12995k.setLoginReqState(1002);
        } else if (deSerializeJson.getResultCode().intValue() == 0) {
            f12995k.setLoginReqState(1);
            CacheAuth.getInstance().setOnlineTime(0);
            Boolean bool = (Boolean) ((HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class)).get("checkPhone");
        } else {
            f12995k.setLoginReqState(1003);
        }
        try {
            f12995k.setLoginReqMessage(new String(str.getBytes(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        m13931q();
    }

    /* access modifiers changed from: private */
    /* renamed from: h */
    public static void m13921h(String str) {
        if (str != null) {
            try {
                if (new JSONObject(EncryptUtil.decrypt(URLDecoder.decode(new JSONObject(str).getString("data"), "UTF-8"))).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                    CacheAuth.getInstance().setOnlineTime(0);
                    f12995k.setLoginReqState(1);
                } else {
                    f12995k.setLoginReqState(1003);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                f12995k.setLoginReqState(1002);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                f12995k.setLoginReqState(1002);
            }
            try {
                f12995k.setLoginReqMessage(new String(str.getBytes(), "UTF-8"));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
            }
            m13931q();
        }
    }

    /* renamed from: p */
    private static void m13930p() {
        int authMethod = CacheAuth.getInstance().getAuthMethod();
        if (authMethod == 1) {
            f12991g = HttpUtil.m14304c(GBApplication.instance(), CacheAccount.getInstance().getLoginPhone(), "", CacheAccount.getInstance().getLoginStaticPassword(), f12997m, "");
        } else if (authMethod == 2) {
            f12992h = HttpUtil.m14348q(GBApplication.instance(), f12997m, "");
        }
    }

    /* renamed from: a */
    public static CheckResult m13903a() {
        return f12995k;
    }

    /* access modifiers changed from: private */
    /* renamed from: q */
    public static void m13931q() {
        synchronized (f12993i) {
            f12993i.set(false);
        }
        f12994j.mo24437a(f12995k);
    }

    /* access modifiers changed from: private */
    /* renamed from: r */
    public static int m13932r() {
        boolean z;
        Response adVar;
        boolean z2 = true;
        int i = 0;
        while (true) {
            if (i >= 2) {
                z = z2;
                adVar = null;
                break;
            }
            try {
                adVar = OkHttpService.m10251a(WiFiConst.f13040u);
                z = true;
                break;
            } catch (IOException e) {
                f12995k.setInternetReqMessage("异常信息 : " + e.toString());
                f12995k.setInternetReqState(0);
                i++;
                z2 = false;
            }
        }
        if (!z) {
            f12995k.setOnlineState(0);
            return 0;
        }
        f12995k.setInternetReqState(1);
        try {
            String str = new String(adVar.mo16127h().bytes(), "utf-8");
            if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                f12995k.setOnlineState(2);
                f12995k.setInternetReqMessage(str);
                return 2;
            }
            f12995k.setInternetReqMessage(str);
            f12995k.setOnlineState(1);
            return 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            f12995k.setOnlineState(0);
            f12995k.setInternetReqMessage(e2.toString());
            return 0;
        }
    }

    /* renamed from: i */
    private static boolean m13923i(String str) {
        try {
            final URL url = new URL(str);
            FutureTask futureTask = new FutureTask(new Callable() {
                /* class com.gbcom.gwifi.functions.wifi.DeviceCheckNetWorkUtils.CallableC33943 */

                /* renamed from: a */
                public InetAddress call() throws Exception {
                    return InetAddress.getByName(url.getHost());
                }
            });
            Executors.newSingleThreadExecutor().execute(futureTask);
            if (((InetAddress) futureTask.get((long) 3000, TimeUnit.MILLISECONDS)) != null) {
                return true;
            }
            f12995k.setInternetReqState(0);
            f12995k.setInternetReqMessage("异常信息 : dns解析异常");
            return false;
        } catch (Exception e) {
            f12995k.setInternetReqState(0);
            f12995k.setInternetReqMessage("异常信息 : " + e.toString());
        }
    }

    /* renamed from: a */
    private static HashMap m13904a(int i, boolean z, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("state", Integer.valueOf(i));
        hashMap.put("isInner", Boolean.valueOf(z));
        hashMap.put("auth_state", Integer.valueOf(i2));
        return hashMap;
    }
}
