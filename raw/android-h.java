//
// Decompiled by Jadx - 1784ms
//
package com.gbcom.gwifi.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import c.ab;
import c.ac;
import c.ad;
import c.r;
import c.w;
import c.x;
import c.y;
import com.gbcom.gwifi.a.b.a;
import com.gbcom.gwifi.a.b.c;
import com.gbcom.gwifi.a.b.d;
import com.gbcom.gwifi.a.b.j;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.codec.EncryptUtil;
import com.gbcom.gwifi.codec.b;
import com.gbcom.gwifi.codec.e;
import com.gbcom.gwifi.functions.wifi.g;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheGame;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/* compiled from: HttpUtil */
public class h {
    private static String A = "/appUser/modifySPwd.bin";
    private static String B = "/appUser/setAvatar.bin";
    private static String C = "/appUser/editUser.bin";
    private static String D = "/appUser/getUser.bin";
    private static String E = "/appUser/agreementSign.bin";
    private static String F = "/appUser/setDeviceToken.bin";
    private static String G = "/appUser/getOrgList.bin";
    private static String H = "/appUser/thirdUserCheck";
    private static String I = "/appUser/thirdBindPhone";
    private static String J = "/appUser/setTerminalInfo.bin";
    private static String K = "/appUser/setAppInfo.bin";
    private static String L = "/gportal/app/queryAuthState";
    private static String M = "/gportal/app/authLogin";
    private static String N = "/gportal/app/authLogout";
    private static String O = "/gportal/app/reBindMac";
    private static String P = "/gportal/app/syncAuthMac";
    private static String Q = "/shop/appApi/onlinehall";
    private static String R = "/shop/point/signin";
    private static String S = "/shop/point/app_download";
    private static String T = "/shop/user/complete_identity_data";
    private static String U = "/shop/user/complete_all_data";
    private static String V = "/shop/task/effect";
    private static String W = "/shop/app/getModuleList";
    private static String X = "/appBase/querySocietyNews.bin";
    private static String Y = "/appBase/querySocietyImageNews.bin";
    private static String Z = "/appBase/queryPostNews.bin";
    public static final String a = "http";
    private static String aA = "/appPoints/getSingleRedbagPre.html";
    private static String aB = "/appGiwifi/queryEdu";
    private static String aC = "/appGiwifi/queryNotify";
    private static String aD = "/appGiwifi/queryTopNews";
    private static String aE = "/appGiwifi/queryAllNews";
    private static String aF = "/cgi/org/getOrgsByCoordinate";
    private static String aG = "/appToken/getToken";
    private static ConcurrentHashMap<c, j> aH = new ConcurrentHashMap<>();
    private static com.gbcom.gwifi.a.c<c, d> aI = new 1();
    private static String aa = "/appBase/queryPostImageNews.bin";
    private static String ab = "/appBase/queryNavigation.bin";
    private static String ac = "/appHome/recommend/info.html";
    private static String ad = "/appProduct/homepage/banner.html";
    private static String ae = "/appProduct/cat/info.html";
    private static String af = "/appProduct/product/tag_filter.html";
    private static String ag = "/appProduct/product/info.html";
    private static String ah = "/appProduct/tvmobile/list.html";
    private static String ai = "/appProduct/product/getSearchCount.html";
    private static String aj = "/appProduct/product/search.html";
    private static String ak = "/appProduct/product/add_view_count.html";
    private static String al = "/appProduct/product/hotSearch.html";
    private static String am = "/appProduct/tag.html";
    private static String an = "/appProduct/homepage/hot.html";
    private static String ao = "/appProduct/homepage/new.html";
    private static String ap = "/appProduct/product/socialContact.html";
    private static String aq = "/appProduct/homepage/video/info.html";
    private static String ar = "/app/stat/customAdsCount";
    private static String as = "/appPoints/getShakeAd.html";
    private static String at = "/appPoints/getShakePointsInfo.html";
    private static String au = "/appPoints/updatePoints.bin";
    private static String av = "/appPoints/getAdCfg.html";
    private static String aw = "/appPoints/adClickCfg.html";
    private static String ax = "/appPoints/getFindCfg.html";
    private static String ay = "/appPoints/getSingleRedbagConfig.html";
    private static String az = "/appPoints/getSingleRedbagRank.html";
    public static String b = "/appUser/reLogin.bin";
    public static String c = "/appUser/reBindMac.bin";
    private static final String d = "HttpUtil";
    private static final String e = "https";
    private static String f = "/appBase/queryWeather.bin";
    private static String g = "/appVersion/queryLatest.bin";
    private static String h = "/appVersion/download.bin";
    private static String i = "/appWidget/navigation/list.html";
    private static String j = "/appConfig/uploadCheckLog.html";
    private static String k = "/appConfig/getInfo.html";
    private static String l = "/appConfig/getTemplate.do";
    private static String m = "/appConfig/querySdkNews";
    private static String n = "/appTab/queryTab";
    private static String o = "/appScore/getInfo.html";
    private static String p = "/appLocal/getInfo.html";
    private static String q = "/appWicketConfig/getConfig";
    private static String r = "/appAccelerate/setAppXunYou";
    private static String s = "/appConfig/queryJobNumShow";
    private static String t = "/flowAppApi/getRecommendAppList";
    private static String u = "/flowAppApi/getAppDetail";
    private static String v = "/flowAppApi/gainAppBeans";
    private static String w = "/appUser/createPassword.bin";
    private static String x = "/appUser/appLogin.bin";
    private static String y = "/appUser/register.bin";
    private static String z = "/appUser/resetSPwd.bin";

    public h() {
    }

    static /* synthetic */ ConcurrentHashMap o() {
        return aH;
    }

    static {
    }

    public static void a(Context context, c cVar, com.gbcom.gwifi.a.c<c, d> cVar2) {
        aH.put(cVar, new j(cVar, cVar2));
        a(context).b(cVar, aI);
    }

    public static String a(String str, Map<String, Object> map) {
        String str2;
        String a2 = a("http", str);
        String str3 = "";
        if (map != null) {
            for (String next : map.keySet()) {
                if (!str3.equals("")) {
                    str3 = str3 + "&";
                }
                str3 = str3 + next + "=" + map.get(next);
            }
        }
        if (!str3.equals("")) {
            str2 = a2 + "?";
        } else {
            str2 = a2;
        }
        String str4 = str2 + str3;
        k.c("HttpUtil", "action:" + str4);
        return str4;
    }

    private static String p() {
        if (!CacheAuth.getInstance().isPortal() && CacheAuth.getInstance().getStationCloud().equals("test.gwifi.com.cn")) {
            return "http://testservice.gwifi.com.cn:8090";
        }
        return "http://mobileapi.gwifi.com.cn:8090";
    }

    private static String q() {
        if (!CacheAuth.getInstance().isPortal() && CacheAuth.getInstance().getStationCloud().equals("test.gwifi.com.cn")) {
            return "http://api2.gwifi.com.cn";
        }
        return "http://api.gwifi.com.cn";
    }

    private static String r() {
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            if (!s.e(portalHost)) {
                return "http://" + portalHost + ":8080/wocloud_v2";
            }
            k.e("HttpUtil", "invalid data: portal but no host");
        }
        String stationCloud = CacheAuth.getInstance().getStationCloud();
        if (!s.e(stationCloud)) {
            return "http://" + stationCloud + ":8080/wocloud_v2";
        }
        return "http://login.gwifi.com.cn:8080/wocloud_v2";
    }

    public static String a() {
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            if (!s.e(portalHost)) {
                return portalHost;
            }
            return "";
        }
        String stationCloud = CacheAuth.getInstance().getStationCloud();
        return (s.e(stationCloud) || stationCloud.equals("login.gwifi.com.cn") || stationCloud.equals("test.gwifi.com.cn")) ? "login.gwifi.com.cn" : stationCloud;
    }

    private static String s() {
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            if (!s.e(portalHost)) {
                return "http://" + portalHost + ":" + CacheAuth.getInstance().getPortalPort() + "/shop/app/getToken";
            }
            k.e("HttpUtil", "invalid data: portal but no host");
            return "";
        }
        String stationCloud = CacheAuth.getInstance().getStationCloud();
        if (s.e(stationCloud) || stationCloud.equals("login.gwifi.com.cn") || stationCloud.equals("test.gwifi.com.cn")) {
            return "http://login.gwifi.com.cn/shop/app/getToken";
        }
        return "http://" + stationCloud + "/shop/app/getToken";
    }

    private static String t() {
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            if (!s.e(portalHost)) {
                return "http://" + portalHost + ":" + CacheAuth.getInstance().getPortalPort() + "/shop";
            }
            k.e("HttpUtil", "invalid data: portal but no host");
        }
        return "https://scfapi.gwifi.com.cn/api/v2";
    }

    public static String b() {
        return "http://downres.gwifi.com.cn/problemSolving/problemsolving.html";
    }

    private static String a(String str, String str2) {
        if (str2.equals(ad) || str2.equals(ae) || str2.equals(ag) || str2.equals(af) || str2.equals(ah) || str2.equals(ai) || str2.equals(aj) || str2.equals(ak) || str2.equals(am) || str2.equals(al) || str2.equals(an) || str2.equals(ap) || str2.equals(ao) || str2.equals(aq) || str2.equals(i)) {
            return b.a().i() + str2;
        }
        if (str2.equals(h) || str2.equals(g) || str2.equals(X) || str2.equals(Y) || str2.equals(ab) || str2.equals(aa) || str2.equals(Z) || str2.equals(f) || str2.equals(p) || str2.equals(ac) || str2.equals(k) || str2.equals(l) || str2.equals(o) || str2.equals(m) || str2.equals(j) || str2.equals(as) || str2.equals(av) || str2.equals(aw) || str2.equals(q) || str2.equals(au) || str2.equals(at) || str2.equals(ax) || str2.equals(ar) || str2.equals(ay) || str2.equals(az) || str2.equals(aA) || str2.equals(aB) || str2.equals(n) || str2.equals(s)) {
            return p() + "/cdnserver/" + str2;
        } else if (str2.equals(aC) || str2.equals(aD) || str2.equals(aE)) {
            return p() + "/gixtmsserver/" + str2;
        } else if (str2.equals(aF)) {
            if (CacheAuth.getInstance().isPortal()) {
                String portalHost = CacheAuth.getInstance().getPortalHost();
                int portalPort = CacheAuth.getInstance().getPortalPort();
                if (!TextUtils.isEmpty(portalHost)) {
                    return "http://" + portalHost + ":" + portalPort + "/cmps/org/getOrgsByCoordinate/";
                }
                k.e("HttpUtil", "invalid data: portal but no host");
            }
            return q() + str2;
        } else if (str2.equals(t) || str2.equals(u) || str2.equals(v)) {
            return p() + "/flowserver/" + str2;
        } else {
            String r2 = r();
            if (s.e(r2)) {
                return null;
            }
            return r2 + str2;
        }
    }

    public static a a(Context context) {
        return GBApplication.instance().getProtocolService("http");
    }

    public static void a(Object obj) {
        com.gbcom.gwifi.a.d.d.a(obj);
    }

    public static void a(c cVar, j jVar) {
        if (cVar != null) {
            aH.remove(cVar, jVar);
        }
    }

    private static String c(String str) {
        new HashMap();
        try {
            HashMap b2 = j.b(str);
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(b2.entrySet());
            Collections.sort(arrayList, new 2());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                stringBuffer.append(((String) entry.getKey()) + "=");
                stringBuffer.append((String) entry.getValue());
                if (it.hasNext()) {
                    stringBuffer.append("&");
                }
            }
            return stringBuffer.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String a(HashMap<String, Object> hashMap) {
        new HashMap();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(hashMap.entrySet());
            Collections.sort(arrayList, new 3());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                stringBuffer.append(((String) entry.getKey()) + "=");
                stringBuffer.append(entry.getValue());
                if (it.hasNext()) {
                    stringBuffer.append("&");
                }
            }
            return stringBuffer.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String u() {
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            if (!s.e(portalHost)) {
                return "http://" + portalHost + ":" + CacheAuth.getInstance().getPortalPort();
            }
            k.e("HttpUtil", "invalid data: portal but no host");
            return "";
        }
        String stationCloud = CacheAuth.getInstance().getStationCloud();
        if (!s.e(stationCloud)) {
            return "http://" + stationCloud;
        }
        return "http://login.gwifi.com.cn";
    }

    public static String c() {
        String rechargeWebUrl = CacheApp.getInstance().getRechargeWebUrl();
        if (!s.e(rechargeWebUrl)) {
            return rechargeWebUrl;
        }
        String u2 = u();
        if (s.e(u2)) {
            k.e("HttpUtil", "getAuthRootPath is empty");
            return "";
        }
        String str = (("" + u2) + "/shop/Buy/index") + "?phone=" + CacheAccount.getInstance().getLoginPhone();
        String gwId = CacheAuth.getInstance().getGwId();
        if (!s.e(gwId)) {
            return str + "&gatewayId=" + gwId;
        }
        return str;
    }

    public static String d() {
        String gwId = CacheAuth.getInstance().getGwId();
        String u2 = u();
        if (s.e(u2)) {
            k.e("HttpUtil", "getAuthRootPath is empty");
            return "";
        }
        String str = "access_token=" + CacheAccount.getInstance().getLoginToken();
        if (!s.e(gwId)) {
            str = str + "&gw_id=" + gwId;
        }
        return u2 + U + "?" + str + "&sign=" + e.a(str + "&key=" + "5447c08b53e8dac47f81269f98cfeada").toLowerCase();
    }

    public static String e() {
        String gwId = CacheAuth.getInstance().getGwId();
        String u2 = u();
        if (s.e(u2)) {
            k.e("HttpUtil", "getAuthRootPath is empty");
            return "";
        }
        String str = "access_token=" + CacheAccount.getInstance().getLoginToken();
        if (!s.e(gwId)) {
            str = str + "&gw_id=" + gwId;
        }
        return u2 + T + "?" + str + "&sign=" + e.a(str + "&key=" + "5447c08b53e8dac47f81269f98cfeada").toLowerCase();
    }

    public static String f() {
        String u2 = u();
        if (!s.e(u2)) {
            return u2 + "/Signin/index?name={name}";
        }
        k.e("HttpUtil", "getAuthRootPath is empty");
        return "";
    }

    public static String g() {
        String u2 = u();
        if (s.e(u2)) {
            k.e("HttpUtil", "getAuthRootPath is empty");
            return "";
        }
        return ("" + u2) + "/cmps/static/themes/dashboard/Zero6/templates/tpl/public/serveragreement.html";
    }

    public static String h() {
        String u2 = u();
        if (s.e(u2)) {
            k.e("HttpUtil", "getAuthRootPath is empty");
            return "";
        }
        return ("" + u2) + "/cmps/static/themes/dashboard/Zero6/templates/tpl/public/privacyagreement.html";
    }

    public static String i() {
        String u2 = u();
        if (s.e(u2)) {
            k.b("HttpUtil", "getAuthRootPath is empty");
            return "";
        }
        return (("" + u2) + "/cmps/admin.php/mpi/service") + "?gw_id=" + CacheAuth.getInstance().getGwId();
    }

    private static String v() {
        if (CacheAuth.getInstance().isPortal()) {
            return "";
        }
        String stationCloud = CacheAuth.getInstance().getStationCloud();
        if (stationCloud.equals("test.gwifi.com.cn")) {
            return "http://test.gwifi.com.cn";
        }
        if (stationCloud.equals("login.gwifi.com.cn")) {
            return "http://login.gwifi.com.cn";
        }
        return "";
    }

    public static String j() {
        String v2 = v();
        if (s.e(v2)) {
            return "";
        }
        return ("" + v2) + "/shop/point/exchange";
    }

    public static String k() {
        String v2 = v();
        if (s.e(v2)) {
            return "";
        }
        return ((("" + v2) + "/shop/point/exchange_by_day") + "?gw_id={gw_id}") + "&name={name}";
    }

    public static String l() {
        return c();
    }

    public static String m() {
        return "http://downres.gwifi.com.cn/randomMacHelp/help4Android.html";
    }

    private static String d(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(EncryptUtil.getEncrypt(str), "UTF-8");
    }

    public static ab a(Context context, int i2, String str, int i3, String str2, int i4, String str3, String str4, int i5, int i6, String str5, int i7, String str6, String str7, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("device_model", t.b());
        hashMap.put("app_version", w.a());
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("phone", CacheAccount.getInstance().getLoginPhone());
        hashMap.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("gw_req_state", Integer.valueOf(i2));
        hashMap.put("gw_req_message", str);
        hashMap.put("lx_req_state", Integer.valueOf(i3));
        hashMap.put("lx_req_message", str2);
        hashMap.put("login_req_state", Integer.valueOf(i4));
        hashMap.put("login_req_message", str3);
        hashMap.put("gw_ip", str4);
        hashMap.put("ping_gw", Integer.valueOf(i5));
        hashMap.put("ping_cloud", Integer.valueOf(i6));
        hashMap.put("rssi", str5);
        hashMap.put("channel", Integer.valueOf(i7));
        hashMap.put("bssid", str7);
        hashMap.put("offline_reason_list", str6);
        return com.gbcom.gwifi.a.d.d.a(a("http", j), j.a(hashMap), eVar, obj);
    }

    private static HashMap<String, Object> b(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("token", EncryptUtil.getEncrypt(CacheAccount.getInstance().getUserToken()));
        hashMap.put("version", w.a());
        hashMap.put("mac", EncryptUtil.getEncrypt(CacheAuth.getInstance().getLocalMac()));
        hashMap.put("gatewayId", EncryptUtil.getEncrypt(CacheAuth.getInstance().getGwId()));
        return hashMap;
    }

    public static ab a(Context context, String str, int i2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("phone", EncryptUtil.getEncrypt(str));
        hashMap.put("type", EncryptUtil.getEncrypt(String.valueOf(i2)));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", w), j.a(b2), eVar, obj);
    }

    public static ab a(Context context, String str, int i2, int i3, int i4, String str2, String str3, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("phone", EncryptUtil.getEncrypt(str));
        hashMap.put("type", EncryptUtil.getEncrypt(String.valueOf(i2)));
        hashMap.put("hotspotGroupId", EncryptUtil.getEncrypt(String.valueOf(i3)));
        hashMap.put("identityType", EncryptUtil.getEncrypt(String.valueOf(i4)));
        hashMap.put("identityId", EncryptUtil.getEncrypt(str2));
        hashMap.put("identityName", EncryptUtil.getEncrypt(str3));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", w), j.a(b2), eVar, obj);
    }

    public static ab a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("phone", EncryptUtil.getEncrypt(str));
        hashMap.put("password", EncryptUtil.getEncrypt(str2));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str3));
        hashMap.put("ip", EncryptUtil.getEncrypt(CacheAuth.getInstance().getLocalIp()));
        hashMap.put("apMac", "");
        hashMap.put("gwAddress", EncryptUtil.getEncrypt(CacheAuth.getInstance().getGwIp()));
        hashMap.put("staType", EncryptUtil.getEncrypt(t.l(context)));
        hashMap.put("staModel", EncryptUtil.getEncrypt(t.b()));
        hashMap.put("identityName", EncryptUtil.getEncrypt(str4));
        hashMap.put("identityNumber", EncryptUtil.getEncrypt(str5));
        hashMap.put("recommendName", EncryptUtil.getEncrypt(str6));
        hashMap.put("recommendPhone", EncryptUtil.getEncrypt(str7));
        hashMap.put("agreement_signed", EncryptUtil.getEncrypt(String.valueOf(i2)));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", y), j.a(b2), eVar, obj);
    }

    public static ab a(Context context, String str, String str2, String str3, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("phone", EncryptUtil.getEncrypt(str));
        hashMap.put("password", EncryptUtil.getEncrypt(str2));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str3));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", z), j.a(b2), eVar, obj);
    }

    public static ab b(Context context, String str, String str2, String str3, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("phone", EncryptUtil.getEncrypt(str));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str2));
        hashMap.put("newStaticPassword", EncryptUtil.getEncrypt(str3));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", A), j.a(b2), eVar, obj);
    }

    public static ab c(Context context, String str, String str2, String str3, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        String gwId;
        if (str == null || s.e(str) || (gwId = CacheAuth.getInstance().getGwId()) == null || s.e(gwId)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("phone", EncryptUtil.getEncrypt(str));
        hashMap.put("password", EncryptUtil.getEncrypt(str2));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str3));
        hashMap.put("service_type", Integer.valueOf(CacheAccount.getInstance().getLoginServiceType()));
        hashMap.put("ip", EncryptUtil.getEncrypt(CacheAuth.getInstance().getLocalIp()));
        hashMap.put("apMac", "");
        hashMap.put("gwAddress", EncryptUtil.getEncrypt(CacheAuth.getInstance().getGwIp()));
        hashMap.put("staType", EncryptUtil.getEncrypt(t.l(context)));
        hashMap.put("ssid", EncryptUtil.getEncrypt(g.a(context).h()));
        hashMap.put("staModel", EncryptUtil.getEncrypt(t.b()));
        hashMap.put("im", EncryptUtil.getEncrypt(t.c(context)));
        hashMap.put("installWX", q.c(GBApplication.instance(), "com.tencent.mm") ? EncryptUtil.getEncrypt("1") : EncryptUtil.getEncrypt("0"));
        hashMap.put("imsi", EncryptUtil.getEncrypt(t.e(context)));
        hashMap.put("android_id", EncryptUtil.getEncrypt(t.f(context)));
        hashMap.put("app_uuid", EncryptUtil.getEncrypt(CacheApp.getInstance().getAppUuid()));
        hashMap.put("auth_mode", EncryptUtil.getEncrypt("" + CacheAuth.getInstance().getAuthMode()));
        hashMap.put("filter_id", EncryptUtil.getEncrypt(CacheAuth.getInstance().getFilterId()));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.b(a("http", b), j.a(b2), eVar, obj);
    }

    public static ab a(Context context, String str, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("app_uuid", EncryptUtil.getEncrypt(CacheApp.getInstance().getAppUuid()));
        hashMap.put("phone", EncryptUtil.getEncrypt(str));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(CacheAccount.getInstance().getLoginStaticPassword()));
        if (t.h(context)) {
            hashMap.put("staType", EncryptUtil.getEncrypt("pad"));
            hashMap.put("staModel", EncryptUtil.getEncrypt(t.b()));
            hashMap.put("btype", EncryptUtil.getEncrypt("2"));
        } else {
            hashMap.put("staType", EncryptUtil.getEncrypt("phone"));
            hashMap.put("staModel", EncryptUtil.getEncrypt(t.b()));
            hashMap.put("btype", EncryptUtil.getEncrypt("1"));
        }
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", c), j.a(b2), eVar, obj);
    }

    public static ab a(Context context, int i2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("orgType", Integer.valueOf(i2));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("version", w.a());
        hashMap2.put("token", CacheAccount.getInstance().getUserToken());
        hashMap2.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap2.put("gatewayId", CacheAuth.getInstance().getGwId());
        hashMap2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", G), j.a(hashMap2), eVar, obj);
    }

    public static ab a(Context context, int i2, int i3, String str, String str2, String str3, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        if (i2 == 0) {
            hashMap.put("hotspot_group_id", EncryptUtil.getEncrypt(""));
        } else {
            hashMap.put("hotspot_group_id", EncryptUtil.getEncrypt(String.valueOf(i2)));
        }
        hashMap.put("identity_type", EncryptUtil.getEncrypt(String.valueOf(i3)));
        hashMap.put("identity_id", EncryptUtil.getEncrypt(str));
        hashMap.put("identity_name", EncryptUtil.getEncrypt(str2));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str3));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", H), j.a(b2), eVar, obj);
    }

    public static ab a(Context context, int i2, int i3, String str, String str2, String str3, String str4, String str5, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("hotspot_group_id", EncryptUtil.getEncrypt(String.valueOf(i2)));
        hashMap.put("identity_type", EncryptUtil.getEncrypt(String.valueOf(i3)));
        hashMap.put("identity_id", EncryptUtil.getEncrypt(str2));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str3));
        hashMap.put("identity_name", EncryptUtil.getEncrypt(str));
        hashMap.put("phone", EncryptUtil.getEncrypt(str4));
        hashMap.put("vcode", EncryptUtil.getEncrypt(str5));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", I), j.a(b2), eVar, obj);
    }

    public static ab a(int i2, int i3, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        k.b("HttpUtil", "queryNextPage:" + i2);
        HashMap hashMap = new HashMap();
        hashMap.put("pageNo", Integer.valueOf(i2));
        hashMap.put("pageSize", Integer.valueOf(i3));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("token", CacheAccount.getInstance().getUserToken());
        hashMap2.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap2.put("gatewayId", CacheAuth.getInstance().getGwId());
        hashMap2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", X), j.a(hashMap2), eVar, obj);
    }

    public static ab a(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("token", CacheAccount.getInstance().getUserToken());
        hashMap.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("gatewayId", CacheAuth.getInstance().getGwId());
        hashMap.put("data", "");
        return com.gbcom.gwifi.a.d.d.a(a("http", Y), j.a(hashMap), eVar, obj);
    }

    public static ab a(Context context, com.gbcom.gwifi.a.d.e<byte[]> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("osType", 1);
        hashMap.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        HashMap hashMap2 = new HashMap();
        hashMap2.put("token", CacheAccount.getInstance().getUserToken());
        hashMap2.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap2.put("version", w.a());
        hashMap2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", g), j.a(hashMap2), eVar, obj);
    }

    public static String a(String str) {
        return a("http", h) + "?md5=" + str;
    }

    public static ab b(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        String k2 = g.a(GBApplication.instance()).k();
        String j2 = g.a(GBApplication.instance()).j();
        return com.gbcom.gwifi.a.d.d.b("http://" + k2 + ":8060/wifidog/userlogout?ip=" + j2 + "&mac=" + CacheAuth.getInstance().getLocalMac(), eVar, obj);
    }

    public static ab c(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        String k2 = g.a(GBApplication.instance()).k();
        if (s.e(k2)) {
            k.e("HttpUtil", "syncAuthState gatewayIp is empty");
            return null;
        }
        k.b("HttpUtil", "syncAuthState gatewayIp=" + k2);
        String c2 = t.c(GBApplication.instance());
        String appUuid = CacheApp.getInstance().getAppUuid();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (s.e(c2)) {
            k.b("HttpUtil", "IMEI UUID:" + t.d(GBApplication.instance()));
        }
        String str = "terminal_app_uuid=" + appUuid;
        k.b("HttpUtil", "syncAuthState APP UUID:" + appUuid);
        if (!s.e(c2)) {
            str = str + "&terminal_imei=" + c2;
        }
        String upperCase = e.a(((str + "&terminal_os_type=" + 1) + "&timestamp=" + currentTimeMillis) + "&key=" + "Z2l3aWZpZ3cyMDE5MDcwMTE0MDIwMA").toUpperCase();
        HashMap hashMap = new HashMap();
        hashMap.put("terminal_app_uuid", appUuid);
        hashMap.put("terminal_imei", c2);
        hashMap.put("terminal_os_type", 1);
        hashMap.put("timestamp", Long.valueOf(currentTimeMillis));
        hashMap.put("sign", upperCase);
        String a2 = j.a(hashMap);
        try {
            String a3 = b.a(a2, "gi3a3cc2c7e309581c".substring(2), "Z2l3aWZpZ3cyMDE5MDcwMTE0MDIwMA".substring(0, 16));
            HashMap hashMap2 = new HashMap();
            hashMap2.put("app_key", "gi3a3cc2c7e309581c");
            hashMap2.put("raw_data", a3);
            k.b("HttpUtil", "temp=" + a2);
            k.b("HttpUtil", "app_key=" + "gi3a3cc2c7e309581c");
            k.b("HttpUtil", "raw_data=" + a3);
            return com.gbcom.gwifi.a.d.d.a("http://" + k2 + ":8060/wifidog/fastRoamAuth", j.a(hashMap2), eVar, obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ab a(String str, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("type", str);
        return com.gbcom.gwifi.a.d.d.a(a(ad, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab a(String str, int i2, int i3, String str2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("type", str2);
        hashMap.put("key", str);
        hashMap.put("pageno", Integer.valueOf(i2));
        hashMap.put("pagesize", Integer.valueOf(i3));
        return com.gbcom.gwifi.a.d.d.a(a(aj, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab a(String str, Integer num, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("type", str);
        hashMap.put("product_id", num);
        return com.gbcom.gwifi.a.d.d.a(a(ak, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab b(Context context, int i2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("cat_id", Integer.valueOf(i2));
        return com.gbcom.gwifi.a.d.d.a(a(ae, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab a(Context context, String str, int i2, int i3, String str2, int i4, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("type", str2);
        hashMap.put("tagid", str);
        hashMap.put("pageno", Integer.valueOf(i2));
        hashMap.put("pagesize", Integer.valueOf(i3));
        hashMap.put("order", Integer.valueOf(i4));
        return com.gbcom.gwifi.a.d.d.a(a(af, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab b(Context context, String str, int i2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("type", str);
        hashMap.put("user_id", "");
        hashMap.put("device_id", "");
        hashMap.put("token", "");
        hashMap.put("product_id", Integer.valueOf(i2));
        return com.gbcom.gwifi.a.d.d.a(a(ag, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab a(Context context, File file, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("phone", CacheAccount.getInstance().getLoginPhone());
        HashMap hashMap2 = new HashMap();
        hashMap2.put("token", CacheAccount.getInstance().getUserToken());
        hashMap2.put("version", w.a());
        hashMap2.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap2.put("gatewayId", CacheAuth.getInstance().getGwId());
        hashMap2.put("data", j.a(hashMap));
        HashMap hashMap3 = new HashMap();
        hashMap3.put("head_shot", file);
        HashMap hashMap4 = new HashMap();
        hashMap4.put("root", j.a(hashMap2));
        return com.gbcom.gwifi.a.d.d.i().a(a("http", B), hashMap4, hashMap3, eVar, obj);
    }

    public static ab a(Context context, String str, String str2, int i2, String str3, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("phone", EncryptUtil.getEncrypt(CacheAccount.getInstance().getLoginPhone()));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(CacheAccount.getInstance().getLoginStaticPassword()));
        hashMap.put("alias", EncryptUtil.getEncrypt(str));
        hashMap.put("identity_name", EncryptUtil.getEncrypt(str2));
        hashMap.put("gender", EncryptUtil.getEncrypt(String.valueOf(i2)));
        hashMap.put("email", EncryptUtil.getEncrypt(str3));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", C), j.a(b2), eVar, obj);
    }

    public static ab b(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("phone", EncryptUtil.getEncrypt(CacheAccount.getInstance().getLoginPhone()));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(CacheAccount.getInstance().getLoginStaticPassword()));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", D), j.a(b2), eVar, obj);
    }

    public static ab c(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("phone", EncryptUtil.getEncrypt(CacheAccount.getInstance().getLoginPhone()));
        hashMap.put("agreement_signed", EncryptUtil.getEncrypt("2"));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", E), j.a(b2), eVar, obj);
    }

    public static ab d(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        return com.gbcom.gwifi.a.d.d.a(a(ap, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab b(Context context, String str, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("sdk_source", 1);
        hashMap.put("os", 1);
        if (t.e()) {
            hashMap.put("app_source", 3);
        }
        hashMap.put("device_token", str);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("token", CacheAccount.getInstance().getUserToken());
        hashMap2.put("version", w.a());
        hashMap2.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap2.put("gatewayId", CacheAuth.getInstance().getGwId());
        hashMap2.put("data", j.a(hashMap));
        String a2 = a("http", F);
        String a3 = j.a(hashMap2);
        k.b("HttpUtil", "action:" + a2);
        k.b("HttpUtil", "paramStr:" + a3);
        k.b("HttpUtil", "setDeviceToken:" + str);
        return com.gbcom.gwifi.a.d.d.a(a2, a3, eVar, obj);
    }

    public static ab b(String str, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        String str2;
        String a2 = w.a();
        String str3 = ("" + "app_uuid=" + CacheApp.getInstance().getAppUuid()) + "&app_token=" + str;
        if (!t.e()) {
            str2 = str3 + "&app_source=1";
        } else if (t.f()) {
            str2 = str3 + "&app_source=4";
        } else {
            str2 = str3 + "&app_source=3";
        }
        String str4 = (str2 + "&app_os=1") + "&user_account=" + CacheAccount.getInstance().getLoginPhone();
        String u2 = u();
        if (s.e(u2)) {
            k.e("HttpUtil", "getAuthRootPath is empty");
            return null;
        } else if (CacheAuth.getInstance().isPortal()) {
            String str5 = u2 + "/gportal/app/setAppSource";
            String str6 = str4 + "&sign=" + e.a(str4 + "5447c08b53e8dac47f81269f98cfeada").toLowerCase();
            HashMap hashMap = new HashMap();
            try {
                String d2 = d(str6);
                hashMap.put("version", a2);
                hashMap.put("data", d2);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            return com.gbcom.gwifi.a.d.d.a(str5, hashMap, eVar, obj);
        } else {
            try {
                return com.gbcom.gwifi.a.d.d.a((u2 + "/cmps/admin.php/mpi/setAppSource") + "?version=" + a2 + "&data=" + URLEncoder.encode(URLEncoder.encode(EncryptUtil.getEncrypt(str4), "UTF-8"), "UTF-8"), eVar, obj);
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                return null;
            }
        }
    }

    public static ab e(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        String str = "";
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            int portalPort = CacheAuth.getInstance().getPortalPort();
            if (!TextUtils.isEmpty(portalHost)) {
                str = "http://" + portalHost + ":" + portalPort + Q + "?gatewayId=" + CacheAuth.getInstance().getGwId() + "&phone=" + CacheAccount.getInstance().getLoginPhone() + "&os_type=" + 1 + "&app_version=" + w.a();
            } else {
                k.d("HttpUtil", "invalid data: portal but no host");
            }
        } else {
            str = u();
        }
        if (!s.e(str)) {
            return com.gbcom.gwifi.a.d.d.a(str + Q + "?gatewayId=" + CacheAuth.getInstance().getGwId() + "&phone=" + CacheAccount.getInstance().getLoginPhone() + "&os_type=" + 1 + "&app_version=" + w.a(), eVar, obj);
        }
        k.e("HttpUtil", "getAuthRootPath is empty");
        return null;
    }

    public static ab f(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        String u2 = u();
        if (s.e(u2)) {
            k.e("HttpUtil", "getAuthRootPath is empty");
            return null;
        }
        String loginToken = CacheAccount.getInstance().getLoginToken();
        String gwId = CacheAuth.getInstance().getGwId();
        Long valueOf = Long.valueOf(System.currentTimeMillis() / 1000);
        String str = "access_token=" + loginToken;
        if (!s.e(gwId)) {
            str = str + "&gw_id=" + gwId;
        }
        String str2 = str + "&timestamp=" + valueOf;
        return com.gbcom.gwifi.a.d.d.a(u2 + W + "?" + str2 + "&sign=" + e.a(str2 + "&key=" + "YXJjc29mdGZhY2VyZWNvZ25pemVkZXRlY3Q").toLowerCase(), eVar, obj);
    }

    public static ab a(Context context, String str, String str2, int i2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("service_type", EncryptUtil.getEncrypt(String.valueOf(i2)));
        hashMap.put("phone", EncryptUtil.getEncrypt(str));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str2));
        hashMap.put("ip", EncryptUtil.getEncrypt(CacheAuth.getInstance().getLocalIp()));
        hashMap.put("apMac", "");
        hashMap.put("gwAddress", EncryptUtil.getEncrypt(CacheAuth.getInstance().getGwIp()));
        hashMap.put("staType", EncryptUtil.getEncrypt(t.l(context)));
        hashMap.put("staModel", EncryptUtil.getEncrypt(t.b()));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", x), j.a(b2), eVar, obj);
    }

    public static ab b(Context context, String str, String str2, int i2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("service_type", EncryptUtil.getEncrypt(String.valueOf(i2)));
        hashMap.put("phone", EncryptUtil.getEncrypt(str));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str2));
        hashMap.put("ip", EncryptUtil.getEncrypt(CacheAuth.getInstance().getLocalIp()));
        hashMap.put("apMac", "");
        hashMap.put("gwAddress", EncryptUtil.getEncrypt(CacheAuth.getInstance().getGwIp()));
        hashMap.put("staType", EncryptUtil.getEncrypt(t.l(context)));
        hashMap.put("staModel", EncryptUtil.getEncrypt(t.b()));
        HashMap<String, Object> b2 = b(context);
        b2.put("data", j.a(hashMap));
        return com.gbcom.gwifi.a.d.d.a(a("http", x), j.a(b2), eVar, obj);
    }

    public static c a(Context context, String str, Integer num, String str2, Integer num2, com.gbcom.gwifi.a.c<c, d> cVar) {
        String u2 = u();
        if (s.e(u2)) {
            k.e("HttpUtil", "getAuthRootPath is empty");
            return null;
        }
        String lowerCase = e.a(CacheAccount.getInstance().getLoginPhone()).toLowerCase();
        c a2 = com.gbcom.gwifi.a.b.a.a.a(u2 + S + "?user=" + lowerCase + "&product_type=" + str + "&product_id=" + num + "&product_name=" + str2 + "&points=" + num2 + "&sig=" + e.a("5447c08b53e8dac47f81269f98cfeada" + "||" + lowerCase + "||" + num).substring(12, 20).toLowerCase());
        a(context, a2, cVar);
        return a2;
    }

    public static ab a(Context context, String str, Integer num, String str2, Integer num2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        String u2 = u();
        if (s.e(u2)) {
            k.e("HttpUtil", "getAuthRootPath is empty");
            return null;
        }
        String lowerCase = e.a(CacheAccount.getInstance().getLoginPhone()).toLowerCase();
        return com.gbcom.gwifi.a.d.d.a(u2 + S + "?user=" + lowerCase + "&product_type=" + str + "&product_id=" + num + "&product_name=" + str2 + "&points=" + num2 + "&sig=" + e.a("5447c08b53e8dac47f81269f98cfeada" + "||" + lowerCase + "||" + num).substring(12, 20).toLowerCase(), eVar, obj);
    }

    public static ab d(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("phone", CacheAccount.getInstance().getLoginPhone());
        hashMap.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("versionName", w.a());
        return com.gbcom.gwifi.a.d.d.a(a(k, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab e(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("phone", CacheAccount.getInstance().getLoginPhone());
        hashMap.put("mac", CacheAuth.getInstance().getLocalMac());
        return com.gbcom.gwifi.a.d.d.a(a(o, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab f(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        return com.gbcom.gwifi.a.d.d.a("http://" + g.a(GBApplication.instance()).k() + ":8060/wifidog/get_hotspot_group", eVar, obj);
    }

    public static ab a(com.gbcom.gwifi.a.d.e<String> eVar, Integer num, Integer num2, Integer num3, Integer num4, Object obj) {
        String k2 = g.a(GBApplication.instance()).k();
        String localMac = CacheAuth.getInstance().getLocalMac();
        return com.gbcom.gwifi.a.d.d.a("http://" + k2 + ":8060/wifidog/add_station?mac=" + localMac + "&ip=" + g.a(GBApplication.instance()).j() + "&sign=" + e.a("mac=" + localMac + "5447c08b53e8dac47f81269f98cfeada" + (System.currentTimeMillis() / 1000) + "").toLowerCase() + "&available_time=" + num + "&bw_up=" + num2 + "&bw_down=" + num3 + "&free_type=" + num4 + "&times=" + (System.currentTimeMillis() / 1000) + "", eVar, obj);
    }

    public static ab g(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        return com.gbcom.gwifi.a.d.d.a("http://" + g.a(GBApplication.instance()).k() + ":8060/wifidog/get_auth_state?mac=&ip=" + g.a(GBApplication.instance()).j(), eVar, obj);
    }

    public static ad n() throws IOException {
        return com.gbcom.gwifi.a.d.d.a("http://" + g.a(GBApplication.instance()).k() + ":8060/wifidog/get_auth_state?mac=&ip=" + g.a(GBApplication.instance()).j());
    }

    public static ad b(String str) throws IOException {
        String k2 = g.a(GBApplication.instance()).k();
        String lowerCase = CacheAuth.getInstance().getLocalMac().toLowerCase();
        return com.gbcom.gwifi.a.d.d.a("http://" + k2 + ":8881/app_server/get_ap?mac=" + lowerCase + "&ip=" + g.a(GBApplication.instance()).j() + "&token=" + e.a("mac=" + lowerCase + "5447c08b53e8dac47f81269f98cfeada" + new SimpleDateFormat("yyyyMMddHH").format(new Date())).toLowerCase() + "&method=get_client_info&bssid=" + str + "&data=");
    }

    public static ab c(Context context, int i2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("phone", CacheAccount.getInstance().getLoginPhone());
        hashMap.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("layout_type", Integer.valueOf(i2));
        hashMap.put("versionName", w.a());
        hashMap.put("identityType", CacheAccount.getInstance().getIdentityType());
        if (t.e()) {
            hashMap.put("orgTypeDefault", 2);
        } else if (b.a().o()) {
            hashMap.put("orgTypeDefault", 1);
        } else {
            hashMap.put("orgTypeDefault", 2);
        }
        return com.gbcom.gwifi.a.d.d.a(a(l, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab g(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("phone", CacheAccount.getInstance().getLoginPhone());
        hashMap.put("mac", CacheAuth.getInstance().getLocalMac());
        return com.gbcom.gwifi.a.d.d.a(a(m, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab a(Context context, int i2, int i3, String str, String str2, int i4, int i5, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("platformOs", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("sourceType", Integer.valueOf(i2));
        hashMap.put("productId", Integer.valueOf(i3));
        hashMap.put("productType", str);
        hashMap.put("webUrl", str2);
        hashMap.put("position", Integer.valueOf(i4));
        hashMap.put("loadTag", Integer.valueOf(i5));
        return com.gbcom.gwifi.a.d.d.a(a(ar, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab h(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("accountId", Integer.valueOf(CacheAccount.getInstance().getUserId()));
        hashMap.put("terminalMac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("deviceMac", g.a(context).i());
        hashMap.put("imei", t.c(context));
        return com.gbcom.gwifi.a.d.d.a(a(as, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab i(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("accountId", Integer.valueOf(CacheAccount.getInstance().getUserId()));
        hashMap.put("terminalMac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("deviceMac", g.a(context).i());
        hashMap.put("imei", t.c(context));
        return com.gbcom.gwifi.a.d.d.a(a(at, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab a(Context context, int i2, int i3, String str, int i4, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("accountId", str);
        hashMap.put("activityId", Integer.valueOf(i2));
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("transId", Integer.valueOf(i3));
        hashMap.put("transType", 1);
        hashMap.put("balance", Integer.valueOf(i4));
        hashMap.put("message", "");
        String a2 = j.a(hashMap);
        String a3 = com.gbcom.gwifi.codec.g.a(a2);
        String a4 = a("http", au);
        if (a3 != null) {
            a2 = a2.replaceFirst("\\}", ",\"sign\":\"" + a3 + "\"\\}");
        }
        return com.gbcom.gwifi.a.d.d.a(a4, a2, eVar, obj);
    }

    private static HashMap<String, Object> c(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("accountId", Integer.valueOf(CacheAccount.getInstance().getUserId()));
        hashMap.put("terminalMac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("deviceMac", g.a(context).i());
        hashMap.put("imei", t.c(context));
        return hashMap;
    }

    public static ab j(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        return com.gbcom.gwifi.a.d.d.a(a(av, (Map<String, Object>) c(context)), eVar, obj);
    }

    public static ab k(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        return com.gbcom.gwifi.a.d.d.a(a(aw, (Map<String, Object>) c(context)), eVar, obj);
    }

    public static ab l(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        return com.gbcom.gwifi.a.d.d.a(a(ax, (Map<String, Object>) c(context)), eVar, obj);
    }

    public static ab m(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        return com.gbcom.gwifi.a.d.d.a(a(ay, (Map<String, Object>) c(context)), eVar, obj);
    }

    public static ab n(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        return com.gbcom.gwifi.a.d.d.a(a(az, (Map<String, Object>) c(context)), eVar, obj);
    }

    public static ab d(Context context, int i2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap<String, Object> c2 = c(context);
        c2.put("redId", Integer.valueOf(i2));
        return com.gbcom.gwifi.a.d.d.a(a("http", aA), j.a(c2), eVar, obj);
    }

    private static HashMap<String, Object> w() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("phone", CacheAccount.getInstance().getLoginPhone());
        hashMap.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("identityType", CacheAccount.getInstance().getIdentityType());
        return hashMap;
    }

    public static ab a(int i2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap<String, Object> w2 = w();
        w2.put("num", Integer.valueOf(i2));
        return com.gbcom.gwifi.a.d.d.a(a(aB, (Map<String, Object>) w2), eVar, obj);
    }

    public static ab b(int i2, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap<String, Object> w2 = w();
        w2.put("collegeId", Integer.valueOf(CacheAccount.getInstance().getCollegeId()));
        w2.put("departmentId", Integer.valueOf(CacheAccount.getInstance().getDepartmentId()));
        String orgId = CacheAuth.getInstance().getOrgId();
        w2.put("orgId", Integer.valueOf(TextUtils.isEmpty(orgId) ? 0 : Integer.valueOf(orgId).intValue()));
        w2.put("num", Integer.valueOf(i2));
        w2.put("versionName", w.a());
        return com.gbcom.gwifi.a.d.d.a(a(aC, (Map<String, Object>) w2), eVar, obj);
    }

    public static ab a(com.gbcom.gwifi.a.d.e<String> eVar, Object obj, Context context) {
        HashMap<String, Object> w2 = w();
        w2.put("collegeId", Integer.valueOf(CacheAccount.getInstance().getCollegeId()));
        w2.put("versionName", w.a());
        w2.put("departmentId", Integer.valueOf(CacheAccount.getInstance().getDepartmentId()));
        String orgId = CacheAuth.getInstance().getOrgId();
        w2.put("orgId", Integer.valueOf(TextUtils.isEmpty(orgId) ? 0 : Integer.valueOf(orgId).intValue()));
        return com.gbcom.gwifi.a.d.d.a(a(aD, (Map<String, Object>) w2), eVar, obj);
    }

    public static ab a(com.gbcom.gwifi.a.d.e<String> eVar, Object obj, int i2, Context context) {
        HashMap<String, Object> w2 = w();
        w2.put("collegeId", Integer.valueOf(CacheAccount.getInstance().getCollegeId()));
        w2.put("departmentId", Integer.valueOf(CacheAccount.getInstance().getDepartmentId()));
        String orgId = CacheAuth.getInstance().getOrgId();
        w2.put("orgId", Integer.valueOf(TextUtils.isEmpty(orgId) ? 0 : Integer.valueOf(orgId).intValue()));
        w2.put("pageNum", Integer.valueOf(i2));
        w2.put("versionName", w.a());
        return com.gbcom.gwifi.a.d.d.a(a(aE, (Map<String, Object>) w2), eVar, obj);
    }

    public static ab b(com.gbcom.gwifi.a.d.e<String> eVar, Object obj, Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("phone", CacheAccount.getInstance().getLoginPhone());
        hashMap.put("terminalMac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("deviceMac", g.a(context).i());
        hashMap.put("imei", t.c(context));
        if (t.e()) {
            hashMap.put("orgTypeDefault", 2);
        } else if (b.a().o()) {
            hashMap.put("orgTypeDefault", 1);
        } else {
            hashMap.put("orgTypeDefault", 2);
        }
        return com.gbcom.gwifi.a.d.d.a(a(n, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab c(com.gbcom.gwifi.a.d.e<String> eVar, Object obj, Context context) {
        HashMap hashMap = new HashMap();
        String loginPhone = CacheAccount.getInstance().getLoginPhone();
        String localMac = CacheAuth.getInstance().getLocalMac();
        String str = "000000000000";
        if (CacheAuth.getInstance().isPortal()) {
            str = CacheAuth.getInstance().getGwSn();
        }
        if (s.e(loginPhone) && s.e(localMac)) {
            str = CacheAuth.getInstance().getGwSn();
        }
        hashMap.put("os", 1);
        hashMap.put("sn", str);
        hashMap.put("phone", loginPhone);
        hashMap.put("mac", localMac);
        if (t.e()) {
            hashMap.put("orgTypeDefault", 2);
        } else if (b.a().o()) {
            hashMap.put("orgTypeDefault", 1);
        } else {
            hashMap.put("orgTypeDefault", 2);
        }
        return com.gbcom.gwifi.a.d.d.a(a(s, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab a(float f2, float f3, Integer num, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        currentTimeMillis + "";
        String lowerCase = e.a(currentTimeMillis + "546c10bdfd98c5b043002a22").toLowerCase();
        HashMap hashMap = new HashMap();
        hashMap.put("longitude", Float.valueOf(f2));
        hashMap.put("latitude", Float.valueOf(f3));
        hashMap.put("orgType", num);
        hashMap.put("timestamp", Long.valueOf(currentTimeMillis));
        hashMap.put("secret", lowerCase);
        return com.gbcom.gwifi.a.d.d.a(a(aF, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab o(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        boolean h2 = t.h(context);
        hashMap.put("os_type", "1");
        hashMap.put("btype", h2 ? "2" : "1");
        hashMap.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("imei", t.c(context));
        hashMap.put("imsi", t.e(context));
        hashMap.put("android_id", t.f(context));
        hashMap.put("api_level", Build.VERSION.SDK);
        hashMap.put("manufacture", Build.MANUFACTURER);
        hashMap.put("model", Build.MODEL);
        hashMap.put("idfa", "");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("version", w.a());
        hashMap2.put("mac", CacheAuth.getInstance().getLocalMac());
        hashMap2.put("data", EncryptUtil.getEncrypt(c(j.a(hashMap))));
        return com.gbcom.gwifi.a.d.d.a(a("http", J), j.a(hashMap2), eVar, obj);
    }

    public static String a(String str, String str2, int i2) {
        try {
            return new y.a().b((long) i2, TimeUnit.SECONDS).c().a(new ab.a().a(str).b("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").a(new r.a().a("data", str2).a()).c()).b().h().string();
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x00b2  */
    public static String a(Context context, Object obj) throws IOException {
        UnsupportedEncodingException e2;
        String str;
        String a2;
        String j2 = g.a(GBApplication.instance()).j();
        String portalHost = CacheAuth.getInstance().getPortalHost();
        int portalPort = CacheAuth.getInstance().getPortalPort();
        String str2 = "timestamp=" + Long.valueOf(System.currentTimeMillis() / 1000) + "&userIp=" + j2;
        String str3 = str2 + "&sign=" + e.a(str2 + "5447c08b53e8dac47f81269f98cfeada").toLowerCase();
        HashMap hashMap = new HashMap();
        try {
            str = d(str3);
            try {
                hashMap.put("data", str);
            } catch (UnsupportedEncodingException e3) {
                e2 = e3;
                e2.printStackTrace();
                String str4 = "http://" + portalHost + ":" + portalPort + L;
                int i2 = 0;
                a2 = a(str4, str, 2);
                while (a2 == null) {
                }
                return a2;
            }
        } catch (UnsupportedEncodingException e4) {
            UnsupportedEncodingException unsupportedEncodingException = e4;
            str = "";
            e2 = unsupportedEncodingException;
            e2.printStackTrace();
            String str42 = "http://" + portalHost + ":" + portalPort + L;
            int i22 = 0;
            a2 = a(str42, str, 2);
            while (a2 == null) {
            }
            return a2;
        }
        String str422 = "http://" + portalHost + ":" + portalPort + L;
        int i222 = 0;
        a2 = a(str422, str, 2);
        while (a2 == null) {
            i222++;
            if (i222 > 3) {
                break;
            }
            a2 = a(str422, str, 2);
        }
        return a2;
    }

    public static ab h(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        if (!CacheAuth.getInstance().isPortal()) {
            k.e("HttpUtil", "invalid data: okPortalQueryAuthState but not portal");
            return null;
        }
        String str = "timestamp=" + Long.valueOf(System.currentTimeMillis() / 1000) + "&userIp=" + g.a(GBApplication.instance()).j();
        String str2 = str + "&sign=" + e.a(str + "5447c08b53e8dac47f81269f98cfeada").toLowerCase();
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("data", d(str2));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return com.gbcom.gwifi.a.d.d.a("http://" + CacheAuth.getInstance().getPortalHost() + ":" + CacheAuth.getInstance().getPortalPort() + L, hashMap, eVar, obj);
    }

    public static ab p(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        if (!CacheAuth.getInstance().isPortal()) {
            k.e("HttpUtil", "invalid data: okPortalQueryAuthState but not portal");
            return null;
        }
        String j2 = g.a(GBApplication.instance()).j();
        String portalHost = CacheAuth.getInstance().getPortalHost();
        int portalPort = CacheAuth.getInstance().getPortalPort();
        String str = "timestamp=" + Long.valueOf(System.currentTimeMillis() / 1000) + "&userIp=" + j2;
        String str2 = str + "&sign=" + e.a(str + "5447c08b53e8dac47f81269f98cfeada").toLowerCase();
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("data", d(str2));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return com.gbcom.gwifi.a.d.d.a("http://" + portalHost + ":" + portalPort + N, hashMap, eVar, obj);
    }

    public static ab q(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        if (!CacheAuth.getInstance().isPortal()) {
            k.e("HttpUtil", "invalid data: okPortalQueryAuthState but not portal");
            return null;
        }
        HashMap hashMap = new HashMap();
        Integer num = new Integer(1);
        String localMac = CacheAuth.getInstance().getLocalMac();
        String loginPhone = CacheAccount.getInstance().getLoginPhone();
        String loginStaticPassword = CacheAccount.getInstance().getLoginStaticPassword();
        hashMap.put("appUuid", CacheApp.getInstance().getAppUuid());
        hashMap.put("userIp", g.a(GBApplication.instance()).j());
        hashMap.put("nasName", CacheAuth.getInstance().getPortalNasName());
        hashMap.put("ssid", "");
        hashMap.put("nasIp", "");
        hashMap.put("userMac", localMac);
        hashMap.put("vlan", num);
        hashMap.put("apMac", "");
        hashMap.put("userFirstUrl", "");
        hashMap.put("userName", loginPhone);
        hashMap.put("passwd", loginStaticPassword);
        boolean h2 = t.h(context);
        hashMap.put("btype", h2 ? "2" : "1");
        hashMap.put("staType", h2 ? "pad" : "phone");
        hashMap.put("staModel", t.b());
        hashMap.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        String a2 = a((HashMap<String, Object>) hashMap);
        String a3 = e.a(a2 + "5447c08b53e8dac47f81269f98cfeada");
        try {
            hashMap.put("passwd", URLEncoder.encode(loginStaticPassword, "UTF-8"));
            a2 = a((HashMap<String, Object>) hashMap);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        String str = a2 + "&sign=" + a3.toLowerCase();
        HashMap hashMap2 = new HashMap();
        try {
            hashMap2.put("data", d(str));
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
        return com.gbcom.gwifi.a.d.d.a("http://" + CacheAuth.getInstance().getPortalHost() + ":" + CacheAuth.getInstance().getPortalPort() + M, hashMap2, eVar, obj);
    }

    public static ab r(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        if (!CacheAuth.getInstance().isPortal()) {
            k.e("HttpUtil", "invalid data: portalBindMacAsync but not portal");
            return null;
        }
        String portalHost = CacheAuth.getInstance().getPortalHost();
        if (TextUtils.isEmpty(portalHost)) {
            k.e("HttpUtil", "invalid data: portalBindMacAsync portal but no host");
            return null;
        }
        int portalPort = CacheAuth.getInstance().getPortalPort();
        String portalNasName = CacheAuth.getInstance().getPortalNasName();
        String localMac = CacheAuth.getInstance().getLocalMac();
        if (TextUtils.isEmpty(localMac)) {
            k.e("HttpUtil", "invalid data: portalBindMacAsync portal but empty userMac");
            return null;
        }
        String loginPhone = CacheAccount.getInstance().getLoginPhone();
        String loginStaticPassword = CacheAccount.getInstance().getLoginStaticPassword();
        HashMap hashMap = new HashMap();
        hashMap.put("appUuid", CacheApp.getInstance().getAppUuid());
        hashMap.put("nasName", portalNasName);
        hashMap.put("userMac", localMac);
        boolean h2 = t.h(context);
        hashMap.put("btype", h2 ? "2" : "1");
        hashMap.put("staType", h2 ? "pad" : "phone");
        hashMap.put("staModel", t.b());
        hashMap.put("userName", loginPhone);
        hashMap.put("passwd", loginStaticPassword);
        hashMap.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        String a2 = a((HashMap<String, Object>) hashMap);
        String a3 = e.a(a2 + "5447c08b53e8dac47f81269f98cfeada");
        try {
            hashMap.put("passwd", URLEncoder.encode(loginStaticPassword, "UTF-8"));
            a2 = a((HashMap<String, Object>) hashMap);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        String str = a2 + "&sign=" + a3.toLowerCase();
        HashMap hashMap2 = new HashMap();
        try {
            hashMap2.put("data", d(str));
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
        return com.gbcom.gwifi.a.d.d.a("http://" + portalHost + ":" + portalPort + O, hashMap2, eVar, obj);
    }

    public static ab i(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        if (!CacheAuth.getInstance().isPortal()) {
            k.e("HttpUtil", "invalid data: portalSyncAuthState but not portal");
            return null;
        }
        String portalHost = CacheAuth.getInstance().getPortalHost();
        if (TextUtils.isEmpty(portalHost)) {
            k.e("HttpUtil", "invalid data: portalSyncAuthState portal but no host");
            return null;
        }
        int portalPort = CacheAuth.getInstance().getPortalPort();
        String portalNasName = CacheAuth.getInstance().getPortalNasName();
        String e2 = g.e(GBApplication.instance().getApplicationContext());
        if (TextUtils.isEmpty(e2)) {
            k.e("HttpUtil", "invalid data: portalSyncAuthState portal but empty userMac");
            return null;
        } else if (!a.b(e2) || e2.equals("00:00:00:00:00:00") || e2.equals("02:00:00:00:00:00")) {
            k.e("HttpUtil", "invalid data: portalSyncAuthState portal invalid userMac");
            return null;
        } else {
            String appUuid = CacheApp.getInstance().getAppUuid();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            HashMap hashMap = new HashMap();
            hashMap.put("appUuid", appUuid);
            hashMap.put("nasName", portalNasName);
            hashMap.put("userMac", e2);
            boolean h2 = t.h(GBApplication.instance());
            hashMap.put("btype", h2 ? "2" : "1");
            hashMap.put("staType", h2 ? "pad" : "phone");
            hashMap.put("staModel", t.b());
            hashMap.put("timestamp", Long.valueOf(currentTimeMillis));
            String a2 = a((HashMap<String, Object>) hashMap);
            String str = a2 + "&sign=" + e.a(a2 + "5447c08b53e8dac47f81269f98cfeada").toLowerCase();
            HashMap hashMap2 = new HashMap();
            try {
                hashMap2.put("data", d(str));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
            }
            return com.gbcom.gwifi.a.d.d.a("http://" + portalHost + ":" + portalPort + P, hashMap2, eVar, obj);
        }
    }

    public static ab j(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        if (!CacheAuth.getInstance().isPortal()) {
            k.e("HttpUtil", "invalid data: portalSyncAuthState but not portal");
            return null;
        }
        String portalHost = CacheAuth.getInstance().getPortalHost();
        if (TextUtils.isEmpty(portalHost)) {
            k.e("HttpUtil", "invalid data: portalSyncAuthState portal but no host");
            return null;
        }
        int portalPort = CacheAuth.getInstance().getPortalPort();
        String portalNasName = CacheAuth.getInstance().getPortalNasName();
        HashMap hashMap = new HashMap();
        hashMap.put("nasName", portalNasName);
        hashMap.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        String a2 = a((HashMap<String, Object>) hashMap);
        String str = a2 + "&sign=" + e.a(a2 + "5447c08b53e8dac47f81269f98cfeada").toLowerCase();
        HashMap hashMap2 = new HashMap();
        try {
            hashMap2.put("data", d(str));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return com.gbcom.gwifi.a.d.d.a("http://" + portalHost + ":" + portalPort + "/gportal/app/getHotspotGroup", hashMap2, eVar, obj);
    }

    public static ab a(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj, int i2, int i3) {
        HashMap hashMap = new HashMap();
        String f2 = t.f(context);
        String c2 = t.c(context);
        String e2 = t.e(context);
        String localMac = CacheAuth.getInstance().getLocalMac();
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        String str3 = Build.VERSION.SDK;
        int userId = CacheAccount.getInstance().getUserId();
        hashMap.put("terminalAndroidId", f2);
        hashMap.put("terminalImei", c2);
        hashMap.put("terminalImsi", e2);
        hashMap.put("terminalMacAddress", localMac);
        hashMap.put("terminalManufacture", str);
        hashMap.put("terminalMode", str2);
        hashMap.put("terminalApiLevel", str3);
        hashMap.put("accountId", Integer.valueOf(userId));
        hashMap.put("pageno", Integer.valueOf(i2));
        hashMap.put("pagesize", Integer.valueOf(i3));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(f2);
        stringBuffer.append(c2);
        stringBuffer.append(e2);
        stringBuffer.append(localMac);
        stringBuffer.append(str);
        stringBuffer.append(str2);
        stringBuffer.append(str3);
        stringBuffer.append(userId);
        stringBuffer.append(i2);
        stringBuffer.append(i3);
        stringBuffer.append("546c10bdfd98c5b043002a22");
        hashMap.put("sign", e.a(stringBuffer.toString()));
        return com.gbcom.gwifi.a.d.d.a(a(t, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab a(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj, int i2) {
        HashMap hashMap = new HashMap();
        String f2 = t.f(context);
        String c2 = t.c(context);
        String e2 = t.e(context);
        String localMac = CacheAuth.getInstance().getLocalMac();
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        String str3 = Build.VERSION.SDK;
        int userId = CacheAccount.getInstance().getUserId();
        hashMap.put("terminalAndroidId", f2);
        hashMap.put("terminalImei", c2);
        hashMap.put("terminalImsi", e2);
        hashMap.put("terminalMacAddress", localMac);
        hashMap.put("terminalManufacture", str);
        hashMap.put("terminalMode", str2);
        hashMap.put("terminalApiLevel", str3);
        hashMap.put("accountId", Integer.valueOf(userId));
        hashMap.put("productId", Integer.valueOf(i2));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(f2);
        stringBuffer.append(c2);
        stringBuffer.append(e2);
        stringBuffer.append(localMac);
        stringBuffer.append(str);
        stringBuffer.append(str2);
        stringBuffer.append(str3);
        stringBuffer.append(userId);
        stringBuffer.append(i2);
        stringBuffer.append("546c10bdfd98c5b043002a22");
        hashMap.put("sign", e.a(stringBuffer.toString()));
        return com.gbcom.gwifi.a.d.d.a(a(u, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab a(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj, int i2, String str, int i3) {
        HashMap hashMap = new HashMap();
        String f2 = t.f(context);
        String c2 = t.c(context);
        String e2 = t.e(context);
        String localMac = CacheAuth.getInstance().getLocalMac();
        String str2 = Build.MANUFACTURER;
        String str3 = Build.MODEL;
        String str4 = Build.VERSION.SDK;
        int userId = CacheAccount.getInstance().getUserId();
        hashMap.put("terminalAndroidId", f2);
        hashMap.put("terminalImei", c2);
        hashMap.put("terminalImsi", e2);
        hashMap.put("terminalMacAddress", localMac);
        hashMap.put("terminalManufacture", str2);
        hashMap.put("terminalMode", str3);
        hashMap.put("terminalApiLevel", str4);
        hashMap.put("accountId", Integer.valueOf(userId));
        hashMap.put("productId", Integer.valueOf(i2));
        hashMap.put("productName", str);
        hashMap.put("points", Integer.valueOf(i3));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(f2);
        stringBuffer.append(c2);
        stringBuffer.append(e2);
        stringBuffer.append(localMac);
        stringBuffer.append(str2);
        stringBuffer.append(str3);
        stringBuffer.append(str4);
        stringBuffer.append(userId);
        stringBuffer.append(i2);
        stringBuffer.append(i3);
        stringBuffer.append("546c10bdfd98c5b043002a22");
        hashMap.put("sign", e.a(stringBuffer.toString()));
        return com.gbcom.gwifi.a.d.d.a(a(v, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab s(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", w.a());
        hashMap.put("mac", CacheAuth.getInstance().getLocalMac());
        String loginPhone = CacheAccount.getInstance().getLoginPhone();
        try {
            hashMap.put("data", d("name=" + loginPhone + "&pd=" + CacheAccount.getInstance().getLoginStaticPassword() + "&appId=" + "gif08514829dcf0103"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return com.gbcom.gwifi.a.d.d.a(a("http", aG), j.a(hashMap), eVar, obj);
    }

    public static ab t(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("phone", CacheAccount.getInstance().getLoginPhone());
        hashMap.put("terminalMac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("deviceMac", g.a(context).i());
        hashMap.put("imei", t.c(context));
        return com.gbcom.gwifi.a.d.d.a(a(q, (Map<String, Object>) hashMap), eVar, obj);
    }

    public static ab a(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("terminalAndroidId", t.f(GBApplication.instance()));
        hashMap.put("terminalImei", t.c(GBApplication.instance()));
        hashMap.put("terminalImsi", t.e(GBApplication.instance()));
        hashMap.put("terminalMacAddress", CacheAuth.getInstance().getLocalMac());
        hashMap.put("terminalManufacture", Build.MANUFACTURER);
        hashMap.put("terminalMode", Build.MODEL);
        hashMap.put("terminalApiLevel", Build.VERSION.SDK);
        String a2 = a((HashMap<String, Object>) hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("pkgName", str);
        String a3 = a((HashMap<String, Object>) hashMap2);
        String str3 = null;
        try {
            str3 = str2 + "?device=" + URLEncoder.encode(EncryptUtil.getEncrypt(a2), "UTF-8") + "&param=" + URLEncoder.encode(EncryptUtil.getEncrypt(a3), "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return com.gbcom.gwifi.a.d.d.a(str3, eVar, obj);
    }

    public static ab k(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        int intValue = CacheAccount.getInstance().getHotspotGroupId().intValue();
        int userId = CacheAccount.getInstance().getUserId();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String lowerCase = e.a(("app_id=" + "gi752e58b11af83d96" + "&project_id=" + intValue + "&timestamp=" + currentTimeMillis + "&user_id=" + userId) + "&key=" + "YXJjc29mdGZhY2VyZWNvZ25pemVkZXRlY3Q").toLowerCase();
        HashMap hashMap = new HashMap();
        hashMap.put("app_id", "gi752e58b11af83d96");
        hashMap.put("project_id", Integer.valueOf(intValue));
        hashMap.put("timestamp", Long.valueOf(currentTimeMillis));
        hashMap.put("user_id", Integer.valueOf(userId));
        hashMap.put("sign", lowerCase);
        k.b("HttpUtil", "offlineFaceDetect: getLoginToken paramString=" + j.a(hashMap));
        String s2 = s();
        if (s.e(s2)) {
            return null;
        }
        return com.gbcom.gwifi.a.d.d.a(s2 + "?" + a((HashMap<String, Object>) hashMap), eVar, obj);
    }

    public static ab l(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        return null;
    }

    public static ab b(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String lowerCase = e.a(("access_token=" + str + "&order_no=" + str2 + "&timestamp=" + currentTimeMillis) + "&key=" + "YXJjc29mdGZhY2VyZWNvZ25pemVkZXRlY3Q").toLowerCase();
        HashMap hashMap = new HashMap();
        hashMap.put("access_token", str);
        hashMap.put("order_no", str2);
        hashMap.put("timestamp", Long.valueOf(currentTimeMillis));
        hashMap.put("sign", lowerCase);
        k.b("HttpUtil", "offlineFaceDetect: updateFaceOrderSuccessLog paramString=" + j.a(hashMap));
        String t2 = t();
        if (s.e(t2)) {
            return null;
        }
        return com.gbcom.gwifi.a.d.d.a((t2 + "/face/updateFaceOrderSuccessLog") + "?" + a((HashMap<String, Object>) hashMap), eVar, obj);
    }

    public static ab a(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj, String str, File file) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String lowerCase = e.a(("access_token=" + str + "&timestamp=" + currentTimeMillis) + "&key=" + "YXJjc29mdGZhY2VyZWNvZ25pemVkZXRlY3Q").toLowerCase();
        HashMap hashMap = new HashMap();
        hashMap.put("access_token", str);
        hashMap.put("timestamp", String.valueOf(currentTimeMillis));
        hashMap.put("sign", lowerCase);
        k.b("HttpUtil", "offlineFaceDetect: registerFaceId paramString=" + j.a(hashMap));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("file", file);
        String t2 = t();
        if (s.e(t2)) {
            return null;
        }
        return com.gbcom.gwifi.a.d.d.i().a(t2 + "/face/registerFaceId", hashMap, hashMap2, eVar, obj);
    }

    public static String a(String str, File file, int i2) {
        String t2 = t();
        if (s.e(t2)) {
            return null;
        }
        String str2 = t2 + "/face/registerFaceId";
        x.a a2 = new x.a().a(x.e);
        if (file != null) {
            a2.a("file", file.getName(), ac.a(w.a("image/jpg"), file));
        }
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String lowerCase = e.a(("access_token=" + str + "&timestamp=" + currentTimeMillis) + "&key=" + "YXJjc29mdGZhY2VyZWNvZ25pemVkZXRlY3Q").toLowerCase();
        a2.a("access_token", str);
        a2.a("timestamp", String.valueOf(currentTimeMillis));
        a2.a("sign", lowerCase);
        try {
            return new y.a().b((long) i2, TimeUnit.SECONDS).c().a(new ab.a().a(str2).a(a2.a()).c()).b().h().string();
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ab a(String str, String str2, String str3, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        String str4;
        String str5;
        String stationCloud = CacheAuth.getInstance().getStationCloud();
        if (!TextUtils.isEmpty(stationCloud)) {
            if (a.a(stationCloud)) {
                str5 = "http://";
            } else {
                str5 = "https://";
            }
            str4 = str5 + stationCloud;
        } else {
            str4 = "https://login.gwifi.com.cn";
        }
        try {
            String d2 = d(CacheAccount.getInstance().getUserToken());
            String d3 = d(str);
            String d4 = d(str2);
            String d5 = d(str3);
            String d6 = d(CacheAuth.getInstance().getGwId());
            HashMap hashMap = new HashMap();
            hashMap.put("token", d2);
            hashMap.put("version", w.a());
            hashMap.put("mac", d3);
            hashMap.put("imei", d4);
            hashMap.put("android_id", d5);
            hashMap.put("gw_id", d6);
            return com.gbcom.gwifi.a.d.d.a((str4 + "/cmps/admin.php/mpi/checkAutoLogin") + "?" + a((HashMap<String, Object>) hashMap), eVar, obj);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ab m(com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        String str;
        String str2;
        HashMap hashMap = new HashMap();
        String j2 = g.a(GBApplication.instance()).j();
        String localMac = CacheAuth.getInstance().getLocalMac();
        hashMap.put("package_name", GBApplication.instance().getApplicationContext().getPackageName());
        if (CacheAuth.getInstance().isPortal()) {
            String portalNasName = CacheAuth.getInstance().getPortalNasName();
            if (s.e(portalNasName)) {
                k.e("HttpUtil", "getGameConfig: portalNasName is empty");
                CacheGame.getInstance().setGameConfig((com.gbcom.gwifi.functions.c.a.a) null);
                return null;
            }
            hashMap.put("gw_id", portalNasName);
        } else {
            String gwId = CacheAuth.getInstance().getGwId();
            if (s.e(gwId)) {
                k.e("HttpUtil", "getGameConfig: gw_id is empty");
                return null;
            }
            hashMap.put("gw_id", gwId);
        }
        hashMap.put("terminal_ip", j2);
        hashMap.put("terminal_mac", localMac);
        hashMap.put("terminal_network_type", 1);
        hashMap.put("terminal_os_type", 1);
        hashMap.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        String a2 = a((HashMap<String, Object>) hashMap);
        String str3 = a2 + "&sign=" + e.a(a2 + "&key=aHR0cDovL2F1dGguZ3dpZmkuY29tLmNuLw").toLowerCase();
        HashMap hashMap2 = new HashMap();
        try {
            hashMap2.put("data", d(str3));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            int portalPort = CacheAuth.getInstance().getPortalPort();
            if (!s.e(portalHost)) {
                str = "http://" + portalHost + ":" + portalPort;
            } else {
                k.e("HttpUtil", "invalid data: portal but no host");
                str = null;
            }
        } else {
            String stationCloud = CacheAuth.getInstance().getStationCloud();
            if (!TextUtils.isEmpty(stationCloud)) {
                if (a.a(stationCloud)) {
                    str2 = "http://";
                } else {
                    str2 = "https://";
                }
                str = str2 + stationCloud;
            } else {
                str = "https://login.gwifi.com.cn";
            }
        }
        if (!s.e(str)) {
            return com.gbcom.gwifi.a.d.d.a(str + "/cmps/admin.php/game/getGameConfig", hashMap2, eVar, obj);
        }
        return null;
    }

    public static ab u(Context context, com.gbcom.gwifi.a.d.e<String> eVar, Object obj) {
        int i2 = 1;
        String loginToken = CacheAccount.getInstance().getLoginToken();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        int b2 = d.b(CacheAccount.getInstance().getCircleBirth());
        String circleCity = CacheAccount.getInstance().getCircleCity();
        int circleSex = CacheAccount.getInstance().getCircleSex();
        if (circleSex != 0 && circleSex == 1) {
            i2 = 2;
        }
        String lowerCase = e.a(("access_token=" + loginToken + "&age=" + b2 + "&city=" + circleCity + "&gender=" + i2 + "&timestamp=" + currentTimeMillis) + "&key=" + "YXJjc29mdGZhY2VyZWNvZ25pemVkZXRlY3Q").toLowerCase();
        HashMap hashMap = new HashMap();
        hashMap.put("access_token", loginToken);
        hashMap.put("age", Integer.valueOf(b2));
        hashMap.put("city", circleCity);
        hashMap.put("gender", Integer.valueOf(i2));
        hashMap.put("timestamp", Long.valueOf(currentTimeMillis));
        hashMap.put("sign", lowerCase);
        k.b("HttpUtil", "getInstructionCode paramString=" + j.a(hashMap));
        String t2 = t();
        if (s.e(t2)) {
            return null;
        }
        return com.gbcom.gwifi.a.d.d.a((t2 + "/tpic/getInstructionCode") + "?" + a((HashMap<String, Object>) hashMap), eVar, obj);
    }
}
