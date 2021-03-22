package com.gbcom.gwifi.functions.wifi;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.codec.EncryptUtil;
import com.gbcom.gwifi.functions.wifi.entity.AuthInfo;
import com.gbcom.gwifi.functions.wifi.entity.CheckResult;
import com.gbcom.gwifi.functions.wifi.entity.CheckTask;
import com.gbcom.gwifi.p221a.p226d.OkHttpService;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.C3470v;
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
import java.net.URL;
import java.net.URLDecoder;
import java.p456io.IOException;
import java.p456io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Response;

/* renamed from: com.gbcom.gwifi.functions.wifi.c */
public class MultiCheckNetWorkUtils {

    /* renamed from: a */
    public static Handler f12999a = null;

    /* renamed from: b */
    public static Handler f13000b = null;

    /* renamed from: c */
    private static final String f13001c = "MultiCheckNetWorkUtils";

    /* renamed from: d */
    private static final int f13002d = 4;

    /* renamed from: e */
    private static final int f13003e = 5;

    /* renamed from: f */
    private static CheckTask f13004f;

    /* renamed from: g */
    private static HandlerThread f13005g;

    /* renamed from: a */
    public static void m13939a(Context context, CallBack aVar) {
        m13940a(context, aVar, true);
    }

    /* renamed from: b */
    public static void m13944b(Context context, CallBack aVar) {
        m13940a(context, aVar, false);
    }

    /* renamed from: a */
    private static void m13940a(Context context, CallBack aVar, boolean z) {
        m13938a(context);
        if (!NetworkUtils.m14426a(context)) {
            CheckTask a = m13936a(aVar, z);
            a.getCheckResult().setGiwifi(false);
            a.getCheckResult().setOnlineState(0);
            Log.m14398b(f13001c, "not NetworkAvailable");
            m13949d(a);
            return;
        }
        f12999a.sendMessage(f12999a.obtainMessage(4, m13936a(aVar, z)));
    }

    /* renamed from: a */
    public static CheckTask m13936a(CallBack aVar, boolean z) {
        return new CheckTask(aVar, z);
    }

    /* renamed from: a */
    private static void m13938a(Context context) {
        if (f12999a == null) {
            f13005g = new HandlerThread("noUiHandlerThread");
            f13005g.start();
            f12999a = new Handler(f13005g.getLooper()) {
                /* class com.gbcom.gwifi.functions.wifi.MultiCheckNetWorkUtils.HandlerC33951 */

                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    switch (message.what) {
                        case 4:
                            Log.m14398b(MultiCheckNetWorkUtils.f13001c, "EXECUTE_TASK_CODE");
                            if (message != null) {
                                CheckTask unused = MultiCheckNetWorkUtils.f13004f = (CheckTask) message.obj;
                                MultiCheckNetWorkUtils.m13950e(MultiCheckNetWorkUtils.f13004f);
                                if (MultiCheckNetWorkUtils.f13004f.getCheckResult().getGwReqState().intValue() != 0) {
                                    if (MultiCheckNetWorkUtils.f13004f.getCheckResult().getGwReqState().intValue() != 2) {
                                        MultiCheckNetWorkUtils.f13004f.getCheckResult().setGiwifi(true);
                                    }
                                    AuthInfo authInfo = MultiCheckNetWorkUtils.f13004f.getCheckResult().getAuthInfo();
                                    if (authInfo != null) {
                                        switch (authInfo.getAuthState().intValue()) {
                                            case 0:
                                                MultiCheckNetWorkUtils.m13953g();
                                                break;
                                            case 1:
                                                MultiCheckNetWorkUtils.f13004f.getCheckResult().setOnlineState(1);
                                                MultiCheckNetWorkUtils.f13004f.getCheckResult().setRelease(false);
                                                break;
                                            case 2:
                                                MultiCheckNetWorkUtils.f13004f.getCheckResult().setOnlineState(2);
                                                MultiCheckNetWorkUtils.f13004f.getCheckResult().setRelease(false);
                                                break;
                                            case 200:
                                                MultiCheckNetWorkUtils.f13004f.getCheckResult().setOnlineState(200);
                                                MultiCheckNetWorkUtils.f13004f.getCheckResult().setRelease(false);
                                                break;
                                            case 253:
                                                MultiCheckNetWorkUtils.f13004f.getCheckResult().setOnlineState(1);
                                                MultiCheckNetWorkUtils.f13004f.getCheckResult().setRelease(true);
                                                break;
                                            default:
                                                MultiCheckNetWorkUtils.f13004f.getCheckResult().setOnlineState(1);
                                                MultiCheckNetWorkUtils.f13004f.getCheckResult().setRelease(false);
                                                break;
                                        }
                                    } else {
                                        MultiCheckNetWorkUtils.m13953g();
                                    }
                                } else {
                                    C3470v.m14563a(GBApplication.instance(), MultiCheckNetWorkUtils.f13004f.getCheckResult().getGwReqMessage());
                                    MultiCheckNetWorkUtils.f13004f.getCheckResult().setGiwifi(false);
                                    MultiCheckNetWorkUtils.f13004f.getCheckResult().setRelease(false);
                                    if (MultiCheckNetWorkUtils.f13004f.getCheckResult().getAuthInfo() == null) {
                                        MultiCheckNetWorkUtils.m13953g();
                                    } else if (MultiCheckNetWorkUtils.f13004f.getCheckResult().getAuthInfo().getAuthState().intValue() == 1) {
                                        MultiCheckNetWorkUtils.f13004f.getCheckResult().setOnlineState(1);
                                    } else if (MultiCheckNetWorkUtils.f13004f.getCheckResult().getAuthInfo().getAuthState().intValue() == 2) {
                                        MultiCheckNetWorkUtils.f13004f.getCheckResult().setOnlineState(2);
                                    } else if (MultiCheckNetWorkUtils.f13004f.getCheckResult().getAuthInfo().getAuthState().intValue() == 200) {
                                        MultiCheckNetWorkUtils.f13004f.getCheckResult().setOnlineState(200);
                                    }
                                }
                                MultiCheckNetWorkUtils.m13949d(MultiCheckNetWorkUtils.f13004f);
                                return;
                            }
                            return;
                        case 5:
                            if (message.obj != null) {
                                CheckTask unused2 = MultiCheckNetWorkUtils.f13004f = (CheckTask) message.obj;
                                MultiCheckNetWorkUtils.f13004f.onResult();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            };
            f13000b = new Handler(context.getMainLooper()) {
                /* class com.gbcom.gwifi.functions.wifi.MultiCheckNetWorkUtils.HandlerC33962 */

                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    switch (message.what) {
                        case 5:
                            if (message.obj != null) {
                                ((CheckTask) message.obj).onResult();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            };
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public static void m13949d(CheckTask checkTask) {
        Log.m14400c(f13001c, "onResult " + JsonUtil.m14387a(checkTask.getCheckResult()));
        if (checkTask.isMainThread()) {
            f13000b.sendMessage(f13000b.obtainMessage(5, checkTask));
        } else {
            f12999a.sendMessage(f12999a.obtainMessage(5, checkTask));
        }
    }

    /* renamed from: c */
    private static void m13946c() {
        String k = WiFiUtil.m14022a(GBApplication.instance()).mo27617k();
        if (!Ipv4Util.m14374b(k)) {
            Log.m14403e(f13001c, "gatewayIp " + k + " is invalid");
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
                        f13004f.getCheckResult().setAuthInfo(authInfo);
                        return;
                    }
                }
                CacheAuth.getInstance().setAuthMethod(0);
                return;
            }
            CacheAuth.getInstance().setAuthMethod(2);
            AuthInfo authInfo2 = new AuthInfo();
            authInfo2.setAuthState(200);
            f13004f.getCheckResult().setAuthInfo(authInfo2);
        }
    }

    /* renamed from: d */
    private static void m13948d() {
        AuthInfo authInfo = new AuthInfo();
        authInfo.setAuthState(2);
        f13004f.getCheckResult().setAuthInfo(authInfo);
        CacheAuth.getInstance().setAuthMethod(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004a, code lost:
        if (android.text.TextUtils.isEmpty(r1) == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00cf, code lost:
        if (android.text.TextUtils.isEmpty(r0) == false) goto L_0x00d1;
     */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m13951e() {
        /*
        // Method dump skipped, instructions count: 397
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.functions.wifi.MultiCheckNetWorkUtils.m13951e():boolean");
    }

    /* renamed from: a */
    private static boolean m13942a(boolean z) {
        int i;
        int i2;
        String portalNasName;
        String string;
        try {
            String a = HttpUtil.m14278a(GBApplication.instance(), "");
            if (a == null) {
                f13004f.getCheckResult().setGwReqState(1003);
                f13004f.getCheckResult().setGwReqMessage("请求失败:No Response");
                return false;
            }
            JSONObject jSONObject = new JSONObject(EncryptUtil.decrypt(URLDecoder.decode(new JSONObject(a).getString("data"), "UTF-8")));
            int i3 = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            jSONObject.getString("resultMsg");
            f13004f.getCheckResult().setGwReqState(1);
            f13004f.getCheckResult().setGiwifi(true);
            CacheAuth.getInstance().setAuthMethod(2);
            if (i3 != 0) {
                return false;
            }
            int i4 = -1;
            String str = "";
            String str2 = "";
            String str3 = "";
            String str4 = "";
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                if (jSONObject2.has("authState")) {
                    i4 = jSONObject2.getInt("authState");
                }
                if (jSONObject2.has("onlineTime")) {
                    int i5 = jSONObject2.getInt("onlineTime");
                    CacheAuth.getInstance().setOnlineTime(i5);
                    CacheAuth.getInstance().setPortalOnlineTime(i5);
                    i = i5;
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
                    str = jSONObject2.getString("userMac");
                    CacheAuth.getInstance().setLocalMac(str);
                }
                if (jSONObject2.has("logoutReason")) {
                    i2 = jSONObject2.getInt("logoutReason");
                    CacheAuth.getInstance().setLogoutReason(i2);
                } else {
                    i2 = 0;
                }
                if (z) {
                    i4 = 1;
                }
                AuthInfo authInfo = new AuthInfo();
                authInfo.setStationSn(str2);
                if (2 == i4 || 200 == i4) {
                    authInfo.setClientMac(str);
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
                    authInfo.setAuthState(Integer.valueOf(i4));
                } else {
                    authInfo.setAuthState(1);
                }
                f13004f.getCheckResult().setAuthInfo(authInfo);
                f13004f.getCheckResult().setGwReqState(1);
                f13004f.getCheckResult().setGwReqMessage(a);
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                f13004f.getCheckResult().setGwReqState(0);
                f13004f.getCheckResult().setGwReqMessage("请求失败:" + e.getMessage());
                return false;
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            f13004f.getCheckResult().setGwReqState(0);
            f13004f.getCheckResult().setGwReqMessage("请求失败:" + e2.getMessage());
            return false;
        } catch (IOException e3) {
            e3.printStackTrace();
            f13004f.getCheckResult().setGwReqState(0);
            f13004f.getCheckResult().setGwReqMessage("请求失败:" + e3.getMessage());
            return false;
        } catch (JSONException e4) {
            e4.printStackTrace();
            f13004f.getCheckResult().setGwReqState(0);
            f13004f.getCheckResult().setGwReqMessage("请求失败:" + e4.getMessage());
            return false;
        }
    }

    /* renamed from: f */
    private static boolean m13952f() {
        try {
            Response n = HttpUtil.m14343n();
            if (n == null) {
                f13004f.getCheckResult().setGwReqState(1003);
                f13004f.getCheckResult().setGwReqMessage("请求失败:No Response");
                return false;
            }
            String trim = new String(n.mo16127h().bytes(), "UTF-8").trim();
            Log.m14398b(f13001c, "queryAuthState: " + trim);
            CommonMsg deSerializeJson = CommonMsg.deSerializeJson(trim);
            if (deSerializeJson == null) {
                f13004f.getCheckResult().setGwReqState(1002);
                f13004f.getCheckResult().setGwReqMessage("解析失败:" + trim);
                return false;
            }
            String data = deSerializeJson.getData();
            HashMap hashMap = (HashMap) JsonUtil.m14386a(data, HashMap.class);
            Integer num = (Integer) hashMap.get("auth_state");
            String obj = hashMap.get("gw_id").toString();
            String str = (String) hashMap.get("station_sn");
            String str2 = (String) hashMap.get("client_mac");
            int intValue = ((Integer) hashMap.get("online_time")).intValue();
            int intValue2 = ((Integer) hashMap.get("logout_reason")).intValue();
            String str3 = (String) hashMap.get("contact_phone");
            String str4 = (String) hashMap.get("suggest_phone");
            if (hashMap.containsKey("orgId")) {
                String oriOrgId = CacheAuth.getInstance().getOriOrgId();
                String str5 = (String) hashMap.get("orgId");
                if (!TextUtils.equals(oriOrgId, str5)) {
                    CacheAccount.getInstance().setCollegeId(0);
                    CacheAccount.getInstance().setIdentityType(0);
                    CacheAccount.getInstance().setDepartmentId(0);
                }
                CacheAuth.getInstance().setOrgId(str5);
            }
            if (hashMap.containsKey("station_cloud")) {
                CacheAuth.getInstance().setStationCloud((String) hashMap.get("station_cloud"));
                CacheAuth.getInstance().setAuthMethod(1);
            }
            CacheAuth.getInstance().setCacheAuthBean(num.intValue(), obj, str, str2, intValue, intValue2, str3, str4);
            AuthInfo authInfo = new AuthInfo();
            authInfo.setAuthState(num);
            authInfo.setGwId(obj);
            authInfo.setStationSn(str);
            authInfo.setClientMac(str2);
            authInfo.setOnlineTime(Integer.valueOf(intValue));
            authInfo.setLogoutReason(Integer.valueOf(intValue2));
            authInfo.setContactPhone(str3);
            authInfo.setSuggestPhone(str4);
            f13004f.getCheckResult().setAuthInfo(authInfo);
            f13004f.getCheckResult().setGwReqState(1);
            f13004f.getCheckResult().setGwReqMessage(data);
            return true;
        } catch (IOException e) {
            Log.m14398b(f13001c, "queryAuthState: " + e.toString());
            e.printStackTrace();
            f13004f.getCheckResult().setGwReqState(0);
            f13004f.getCheckResult().setGwReqMessage("请求失败:" + e.toString());
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public static void m13950e(CheckTask checkTask) {
        boolean e = m13951e();
        int authMethod = CacheAuth.getInstance().getAuthMethod();
        if (authMethod == 1) {
            Log.m14398b(f13001c, "checkAuthState begin");
            m13952f();
            Log.m14398b(f13001c, "checkAuthState end");
            Log.m14400c(f13001c, "gateway req:" + ((Object) checkTask.getCheckResult().getGwReqState()) + "->" + checkTask.getCheckResult().getGwReqMessage());
        } else if (authMethod == 2) {
            Log.m14398b(f13001c, "checkPortalAuthState begin");
            if (e) {
                Log.m14398b(f13001c, "checkPortalAuthState nettest need auth");
            }
            m13942a(e);
            Log.m14398b(f13001c, "checkPortalAuthState end");
            Log.m14400c(f13001c, "gateway req:" + ((Object) checkTask.getCheckResult().getGwReqState()) + "->" + checkTask.getCheckResult().getGwReqMessage());
        } else {
            String k = WiFiUtil.m14022a(GBApplication.instance()).mo27617k();
            if (!Ipv4Util.m14374b(k)) {
                Log.m14403e(f13001c, "gatewayIp " + k + " is invalid");
                CacheAuth.getInstance().setAuthMethod(0);
            } else if (NetworkUtils.m14428a(k, 8060, 1500)) {
                CacheAuth.getInstance().setAuthMethod(1);
                Log.m14398b(f13001c, "checkAuthState begin");
                m13952f();
                Log.m14398b(f13001c, "checkAuthState end");
                Log.m14400c(f13001c, "gateway req:" + ((Object) checkTask.getCheckResult().getGwReqState()) + "->" + checkTask.getCheckResult().getGwReqMessage());
            } else {
                String a = NetworkUtils.m14425a(Constant.f13230bE);
                if (C3467s.m14513e(a) || a.equals("127.0.0.1") || !Ipv4Util.m14374b(a)) {
                    Log.m14403e(f13001c, "can't dns as.gwifi.com.cn");
                    Log.m14403e(f13001c, "as.gwifi.com.cn ip " + a + " is invalid");
                    if (Config.m14094a().mo27813p()) {
                        String q = Config.m14094a().mo27814q();
                        if (!C3467s.m14513e(q) && Ipv4Util.m14374b(q) && NetworkUtils.m14428a(q, 80, 1500)) {
                            CacheAuth.getInstance().setAuthMethod(2);
                            CacheAuth.getInstance().setPortalHost(q);
                            Log.m14398b(f13001c, "checkPortalAuthState begin");
                            m13942a(e);
                            Log.m14398b(f13001c, "checkPortalAuthState end");
                            Log.m14400c(f13001c, "gateway req:" + ((Object) checkTask.getCheckResult().getGwReqState()) + "->" + checkTask.getCheckResult().getGwReqMessage());
                            return;
                        }
                    }
                    CacheAuth.getInstance().setAuthMethod(0);
                    return;
                }
                CacheAuth.getInstance().setAuthMethod(2);
                Log.m14398b(f13001c, "checkPortalAuthState begin");
                m13942a(e);
                Log.m14398b(f13001c, "checkPortalAuthState end");
                Log.m14400c(f13001c, "gateway req:" + ((Object) checkTask.getCheckResult().getGwReqState()) + "->" + checkTask.getCheckResult().getGwReqMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public static void m13953g() {
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
                f13004f.getCheckResult().setInternetReqMessage("异常信息 : " + e.toString());
                f13004f.getCheckResult().setInternetReqState(0);
                i++;
                z2 = false;
            }
        }
        if (!z) {
            f13004f.getCheckResult().setOnlineState(0);
            return;
        }
        f13004f.getCheckResult().setInternetReqState(1);
        try {
            String str = new String(adVar.mo16127h().bytes(), "utf-8");
            if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                f13004f.getCheckResult().setOnlineState(2);
                f13004f.getCheckResult().setInternetReqMessage(str);
                return;
            }
            f13004f.getCheckResult().setOnlineState(1);
            f13004f.getCheckResult().setInternetReqMessage(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            String exc = e2.toString();
            f13004f.getCheckResult().setOnlineState(0);
            f13004f.getCheckResult().setInternetReqMessage(exc);
        }
    }

    /* renamed from: a */
    private static boolean m13941a(CheckResult checkResult, String str) {
        try {
            final URL url = new URL(str);
            FutureTask futureTask = new FutureTask(new Callable() {
                /* class com.gbcom.gwifi.functions.wifi.MultiCheckNetWorkUtils.CallableC33973 */

                /* renamed from: a */
                public InetAddress call() throws Exception {
                    return InetAddress.getByName(url.getHost());
                }
            });
            Executors.newSingleThreadExecutor().execute(futureTask);
            if (((InetAddress) futureTask.get((long) 3000, TimeUnit.MILLISECONDS)) != null) {
                return true;
            }
            checkResult.setInternetReqState(0);
            checkResult.setInternetReqMessage("异常信息 : dns解析异常");
            return false;
        } catch (Exception e) {
            checkResult.setInternetReqState(0);
            checkResult.setInternetReqMessage("异常信息 : " + e.toString());
        }
    }
}
