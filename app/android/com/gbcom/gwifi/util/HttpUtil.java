package com.gbcom.gwifi.util;

import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.p107b.C1499b;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.codec.AESUtils;
import com.gbcom.gwifi.codec.EncryptUtil;
import com.gbcom.gwifi.codec.MD5Util;
import com.gbcom.gwifi.codec.MsgEncoder;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.functions.wifi.entity.AuthInfo;
import com.gbcom.gwifi.p221a.RequestHandler;
import com.gbcom.gwifi.p221a.p223b.DefaultHttpService;
import com.gbcom.gwifi.p221a.p223b.HttpRequest;
import com.gbcom.gwifi.p221a.p223b.HttpResponse;
import com.gbcom.gwifi.p221a.p223b.Session;
import com.gbcom.gwifi.p221a.p223b.p224a.BasicHttpRequest;
import com.gbcom.gwifi.p221a.p226d.OkHttpService;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheGame;
import com.gbcom.gwifi.util.p256a.TokenUtil;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.taobao.accs.common.Constants;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.net.URLEncoder;
import java.p456io.File;
import java.p456io.IOException;
import java.p456io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.android.agoo.message.MessageService;
import p041c.FormBody;
import p041c.MediaType;
import p041c.MultipartBody;
import p041c.OkHttpClient;
import p041c.Request;
import p041c.RequestBody;
import p041c.Response;
import sun.security.x509.CRLDistributionPointsExtension;

/* renamed from: com.gbcom.gwifi.util.h */
public class HttpUtil {

    /* renamed from: A */
    private static String f13342A = "/appUser/modifySPwd.bin";

    /* renamed from: B */
    private static String f13343B = "/appUser/setAvatar.bin";

    /* renamed from: C */
    private static String f13344C = "/appUser/editUser.bin";

    /* renamed from: D */
    private static String f13345D = "/appUser/getUser.bin";

    /* renamed from: E */
    private static String f13346E = "/appUser/agreementSign.bin";

    /* renamed from: F */
    private static String f13347F = "/appUser/setDeviceToken.bin";

    /* renamed from: G */
    private static String f13348G = "/appUser/getOrgList.bin";

    /* renamed from: H */
    private static String f13349H = "/appUser/thirdUserCheck";

    /* renamed from: I */
    private static String f13350I = "/appUser/thirdBindPhone";

    /* renamed from: J */
    private static String f13351J = "/appUser/setTerminalInfo.bin";

    /* renamed from: K */
    private static String f13352K = "/appUser/setAppInfo.bin";

    /* renamed from: L */
    private static String f13353L = "/gportal/app/queryAuthState";

    /* renamed from: M */
    private static String f13354M = "/gportal/app/authLogin";

    /* renamed from: N */
    private static String f13355N = "/gportal/app/authLogout";

    /* renamed from: O */
    private static String f13356O = "/gportal/app/reBindMac";

    /* renamed from: P */
    private static String f13357P = "/gportal/app/syncAuthMac";

    /* renamed from: Q */
    private static String f13358Q = "/shop/appApi/onlinehall";

    /* renamed from: R */
    private static String f13359R = "/shop/point/signin";

    /* renamed from: S */
    private static String f13360S = "/shop/point/app_download";

    /* renamed from: T */
    private static String f13361T = "/shop/user/complete_identity_data";

    /* renamed from: U */
    private static String f13362U = "/shop/user/complete_all_data";

    /* renamed from: V */
    private static String f13363V = "/shop/task/effect";

    /* renamed from: W */
    private static String f13364W = "/shop/app/getModuleList";

    /* renamed from: X */
    private static String f13365X = "/appBase/querySocietyNews.bin";

    /* renamed from: Y */
    private static String f13366Y = "/appBase/querySocietyImageNews.bin";

    /* renamed from: Z */
    private static String f13367Z = "/appBase/queryPostNews.bin";

    /* renamed from: a */
    public static final String f13368a = "http";

    /* renamed from: aA */
    private static String f13369aA = "/appPoints/getSingleRedbagPre.html";

    /* renamed from: aB */
    private static String f13370aB = "/appGiwifi/queryEdu";

    /* renamed from: aC */
    private static String f13371aC = "/appGiwifi/queryNotify";

    /* renamed from: aD */
    private static String f13372aD = "/appGiwifi/queryTopNews";

    /* renamed from: aE */
    private static String f13373aE = "/appGiwifi/queryAllNews";

    /* renamed from: aF */
    private static String f13374aF = "/cgi/org/getOrgsByCoordinate";

    /* renamed from: aG */
    private static String f13375aG = "/appToken/getToken";

    /* renamed from: aH */
    private static ConcurrentHashMap<HttpRequest, Session> f13376aH = new ConcurrentHashMap<>();

    /* renamed from: aI */
    private static RequestHandler<HttpRequest, HttpResponse> f13377aI = new RequestHandler<HttpRequest, HttpResponse>() {
        /* class com.gbcom.gwifi.util.HttpUtil.C34551 */

        /* renamed from: a */
        public void mo24084b(HttpRequest cVar, HttpResponse dVar) {
            Session jVar = (Session) HttpUtil.f13376aH.get(cVar);
            HttpUtil.m14286a(cVar, jVar);
            if (jVar != null && jVar.f8519a != null && jVar.f8521c == 2) {
                jVar.f8519a.mo24084b(cVar, dVar);
            }
        }

        /* renamed from: b */
        public void mo24083a(HttpRequest cVar, HttpResponse dVar) {
            Session jVar = (Session) HttpUtil.f13376aH.get(cVar);
            HttpUtil.m14286a(cVar, jVar);
            if (jVar != null && jVar.f8519a != null && jVar.f8521c == 2) {
                jVar.f8519a.mo24083a(cVar, dVar);
            }
        }

        /* renamed from: a */
        public void mo24082a(HttpRequest cVar, long j, long j2) {
            Session jVar = (Session) HttpUtil.f13376aH.get(cVar);
            if (jVar != null && jVar.f8519a != null && jVar.f8521c == 2) {
                jVar.f8519a.mo24082a(cVar, j, j2);
            }
        }

        /* renamed from: a */
        public void mo24081a(HttpRequest cVar) {
            Session jVar = (Session) HttpUtil.f13376aH.get(cVar);
            if (jVar != null && jVar.f8519a != null && jVar.f8521c == 2) {
                jVar.f8519a.mo24081a(cVar);
            }
        }
    };

    /* renamed from: aa */
    private static String f13378aa = "/appBase/queryPostImageNews.bin";

    /* renamed from: ab */
    private static String f13379ab = "/appBase/queryNavigation.bin";

    /* renamed from: ac */
    private static String f13380ac = "/appHome/recommend/info.html";

    /* renamed from: ad */
    private static String f13381ad = "/appProduct/homepage/banner.html";

    /* renamed from: ae */
    private static String f13382ae = "/appProduct/cat/info.html";

    /* renamed from: af */
    private static String f13383af = "/appProduct/product/tag_filter.html";

    /* renamed from: ag */
    private static String f13384ag = "/appProduct/product/info.html";

    /* renamed from: ah */
    private static String f13385ah = "/appProduct/tvmobile/list.html";

    /* renamed from: ai */
    private static String f13386ai = "/appProduct/product/getSearchCount.html";

    /* renamed from: aj */
    private static String f13387aj = "/appProduct/product/search.html";

    /* renamed from: ak */
    private static String f13388ak = "/appProduct/product/add_view_count.html";

    /* renamed from: al */
    private static String f13389al = "/appProduct/product/hotSearch.html";

    /* renamed from: am */
    private static String f13390am = "/appProduct/tag.html";

    /* renamed from: an */
    private static String f13391an = "/appProduct/homepage/hot.html";

    /* renamed from: ao */
    private static String f13392ao = "/appProduct/homepage/new.html";

    /* renamed from: ap */
    private static String f13393ap = "/appProduct/product/socialContact.html";

    /* renamed from: aq */
    private static String f13394aq = "/appProduct/homepage/video/info.html";

    /* renamed from: ar */
    private static String f13395ar = "/app/stat/customAdsCount";

    /* renamed from: as */
    private static String f13396as = "/appPoints/getShakeAd.html";

    /* renamed from: at */
    private static String f13397at = "/appPoints/getShakePointsInfo.html";

    /* renamed from: au */
    private static String f13398au = "/appPoints/updatePoints.bin";

    /* renamed from: av */
    private static String f13399av = "/appPoints/getAdCfg.html";

    /* renamed from: aw */
    private static String f13400aw = "/appPoints/adClickCfg.html";

    /* renamed from: ax */
    private static String f13401ax = "/appPoints/getFindCfg.html";

    /* renamed from: ay */
    private static String f13402ay = "/appPoints/getSingleRedbagConfig.html";

    /* renamed from: az */
    private static String f13403az = "/appPoints/getSingleRedbagRank.html";

    /* renamed from: b */
    public static String f13404b = "/appUser/reLogin.bin";

    /* renamed from: c */
    public static String f13405c = "/appUser/reBindMac.bin";

    /* renamed from: d */
    private static final String f13406d = "HttpUtil";

    /* renamed from: e */
    private static final String f13407e = "https";

    /* renamed from: f */
    private static String f13408f = "/appBase/queryWeather.bin";

    /* renamed from: g */
    private static String f13409g = "/appVersion/queryLatest.bin";

    /* renamed from: h */
    private static String f13410h = "/appVersion/download.bin";

    /* renamed from: i */
    private static String f13411i = "/appWidget/navigation/list.html";

    /* renamed from: j */
    private static String f13412j = "/appConfig/uploadCheckLog.html";

    /* renamed from: k */
    private static String f13413k = "/appConfig/getInfo.html";

    /* renamed from: l */
    private static String f13414l = "/appConfig/getTemplate.do";

    /* renamed from: m */
    private static String f13415m = "/appConfig/querySdkNews";

    /* renamed from: n */
    private static String f13416n = "/appTab/queryTab";

    /* renamed from: o */
    private static String f13417o = "/appScore/getInfo.html";

    /* renamed from: p */
    private static String f13418p = "/appLocal/getInfo.html";

    /* renamed from: q */
    private static String f13419q = "/appWicketConfig/getConfig";

    /* renamed from: r */
    private static String f13420r = "/appAccelerate/setAppXunYou";

    /* renamed from: s */
    private static String f13421s = "/appConfig/queryJobNumShow";

    /* renamed from: t */
    private static String f13422t = "/flowAppApi/getRecommendAppList";

    /* renamed from: u */
    private static String f13423u = "/flowAppApi/getAppDetail";

    /* renamed from: v */
    private static String f13424v = "/flowAppApi/gainAppBeans";

    /* renamed from: w */
    private static String f13425w = "/appUser/createPassword.bin";

    /* renamed from: x */
    private static String f13426x = "/appUser/appLogin.bin";

    /* renamed from: y */
    private static String f13427y = "/appUser/register.bin";

    /* renamed from: z */
    private static String f13428z = "/appUser/resetSPwd.bin";

    /* renamed from: a */
    public static void m14285a(Context context, HttpRequest cVar, RequestHandler<HttpRequest, HttpResponse> cVar2) {
        f13376aH.put(cVar, new Session(cVar, cVar2));
        m14275a(context).mo24036a(cVar, f13377aI);
    }

    /* renamed from: a */
    public static String m14283a(String str, Map<String, Object> map) {
        String a = m14281a("http", str);
        String str2 = "";
        if (map != null) {
            for (String str3 : map.keySet()) {
                if (!str2.equals("")) {
                    str2 = str2 + "&";
                }
                str2 = str2 + str3 + SimpleComparison.EQUAL_TO_OPERATION + map.get(str3);
            }
        }
        String str4 = (!str2.equals("") ? a + "?" : a) + str2;
        Log.m14400c(f13406d, "action:" + str4);
        return str4;
    }

    /* renamed from: p */
    private static String m14347p() {
        if (!CacheAuth.getInstance().isPortal() && CacheAuth.getInstance().getStationCloud().equals("test.gwifi.com.cn")) {
            return "http://testservice.gwifi.com.cn:8090";
        }
        return "http://mobileapi.gwifi.com.cn:8090";
    }

    /* renamed from: q */
    private static String m14349q() {
        if (!CacheAuth.getInstance().isPortal() && CacheAuth.getInstance().getStationCloud().equals("test.gwifi.com.cn")) {
            return "http://api2.gwifi.com.cn";
        }
        return "http://api.gwifi.com.cn";
    }

    /* renamed from: r */
    private static String m14351r() {
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            if (!C3467s.m14513e(portalHost)) {
                return "http://" + portalHost + ":8080/wocloud_v2";
            }
            Log.m14403e(f13406d, "invalid data: portal but no host");
        }
        String stationCloud = CacheAuth.getInstance().getStationCloud();
        if (!C3467s.m14513e(stationCloud)) {
            return "http://" + stationCloud + ":8080/wocloud_v2";
        }
        return "http://login.gwifi.com.cn:8080/wocloud_v2";
    }

    /* renamed from: a */
    public static String m14277a() {
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            if (!C3467s.m14513e(portalHost)) {
                return portalHost;
            }
            return "";
        }
        String stationCloud = CacheAuth.getInstance().getStationCloud();
        return (C3467s.m14513e(stationCloud) || stationCloud.equals("login.gwifi.com.cn") || stationCloud.equals("test.gwifi.com.cn")) ? "login.gwifi.com.cn" : stationCloud;
    }

    /* renamed from: s */
    private static String m14353s() {
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            if (!C3467s.m14513e(portalHost)) {
                return "http://" + portalHost + ":" + CacheAuth.getInstance().getPortalPort() + "/shop/app/getToken";
            }
            Log.m14403e(f13406d, "invalid data: portal but no host");
            return "";
        }
        String stationCloud = CacheAuth.getInstance().getStationCloud();
        if (C3467s.m14513e(stationCloud) || stationCloud.equals("login.gwifi.com.cn") || stationCloud.equals("test.gwifi.com.cn")) {
            return "http://login.gwifi.com.cn/shop/app/getToken";
        }
        return "http://" + stationCloud + "/shop/app/getToken";
    }

    /* renamed from: t */
    private static String m14355t() {
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            if (!C3467s.m14513e(portalHost)) {
                return "http://" + portalHost + ":" + CacheAuth.getInstance().getPortalPort() + "/shop";
            }
            Log.m14403e(f13406d, "invalid data: portal but no host");
        }
        return "https://scfapi.gwifi.com.cn/api/v2";
    }

    /* renamed from: b */
    public static String m14300b() {
        return "http://downres.gwifi.com.cn/problemSolving/problemsolving.html";
    }

    /* renamed from: a */
    private static String m14281a(String str, String str2) {
        if (str2.equals(f13381ad) || str2.equals(f13382ae) || str2.equals(f13384ag) || str2.equals(f13383af) || str2.equals(f13385ah) || str2.equals(f13386ai) || str2.equals(f13387aj) || str2.equals(f13388ak) || str2.equals(f13390am) || str2.equals(f13389al) || str2.equals(f13391an) || str2.equals(f13393ap) || str2.equals(f13392ao) || str2.equals(f13394aq) || str2.equals(f13411i)) {
            return Config.m14094a().mo27806i() + str2;
        }
        if (str2.equals(f13410h) || str2.equals(f13409g) || str2.equals(f13365X) || str2.equals(f13366Y) || str2.equals(f13379ab) || str2.equals(f13378aa) || str2.equals(f13367Z) || str2.equals(f13408f) || str2.equals(f13418p) || str2.equals(f13380ac) || str2.equals(f13413k) || str2.equals(f13414l) || str2.equals(f13417o) || str2.equals(f13415m) || str2.equals(f13412j) || str2.equals(f13396as) || str2.equals(f13399av) || str2.equals(f13400aw) || str2.equals(f13419q) || str2.equals(f13398au) || str2.equals(f13397at) || str2.equals(f13401ax) || str2.equals(f13395ar) || str2.equals(f13402ay) || str2.equals(f13403az) || str2.equals(f13369aA) || str2.equals(f13370aB) || str2.equals(f13416n) || str2.equals(f13421s)) {
            return m14347p() + "/cdnserver/" + str2;
        } else if (str2.equals(f13371aC) || str2.equals(f13372aD) || str2.equals(f13373aE)) {
            return m14347p() + "/gixtmsserver/" + str2;
        } else if (str2.equals(f13374aF)) {
            if (CacheAuth.getInstance().isPortal()) {
                String portalHost = CacheAuth.getInstance().getPortalHost();
                int portalPort = CacheAuth.getInstance().getPortalPort();
                if (!TextUtils.isEmpty(portalHost)) {
                    return "http://" + portalHost + ":" + portalPort + "/cmps/org/getOrgsByCoordinate/";
                }
                Log.m14403e(f13406d, "invalid data: portal but no host");
            }
            return m14349q() + str2;
        } else if (str2.equals(f13422t) || str2.equals(f13423u) || str2.equals(f13424v)) {
            return m14347p() + "/flowserver/" + str2;
        } else {
            String r = m14351r();
            if (C3467s.m14513e(r)) {
                return null;
            }
            return r + str2;
        }
    }

    /* renamed from: a */
    public static DefaultHttpService m14275a(Context context) {
        return (DefaultHttpService) GBApplication.instance().getProtocolService("http");
    }

    /* renamed from: a */
    public static void m14287a(Object obj) {
        OkHttpService.m10257a(obj);
    }

    /* renamed from: a */
    public static void m14286a(HttpRequest cVar, Session jVar) {
        if (cVar != null) {
            f13376aH.remove(cVar, jVar);
        }
    }

    /* renamed from: c */
    private static String m14308c(String str) {
        new HashMap();
        try {
            HashMap<String, String> b = JsonUtil.m14391b(str);
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(b.entrySet());
            Collections.sort(arrayList, new Comparator<Map.Entry<String, String>>() {
                /* class com.gbcom.gwifi.util.HttpUtil.C34562 */

                /* renamed from: a */
                public int compare(Map.Entry<String, String> entry, Map.Entry<String, String> entry2) {
                    return entry.getKey().compareTo(entry2.getKey());
                }
            });
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                stringBuffer.append(((String) entry.getKey()) + SimpleComparison.EQUAL_TO_OPERATION);
                stringBuffer.append((String) entry.getValue());
                if (it.hasNext()) {
                    stringBuffer.append("&");
                }
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    private static String m14284a(HashMap<String, Object> hashMap) {
        new HashMap();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(hashMap.entrySet());
            Collections.sort(arrayList, new Comparator<Map.Entry<String, Object>>() {
                /* class com.gbcom.gwifi.util.HttpUtil.C34573 */

                /* renamed from: a */
                public int compare(Map.Entry<String, Object> entry, Map.Entry<String, Object> entry2) {
                    return entry.getKey().compareTo(entry2.getKey());
                }
            });
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                stringBuffer.append(((String) entry.getKey()) + SimpleComparison.EQUAL_TO_OPERATION);
                stringBuffer.append(entry.getValue());
                if (it.hasNext()) {
                    stringBuffer.append("&");
                }
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: u */
    private static String m14357u() {
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            if (!C3467s.m14513e(portalHost)) {
                return "http://" + portalHost + ":" + CacheAuth.getInstance().getPortalPort();
            }
            Log.m14403e(f13406d, "invalid data: portal but no host");
            return "";
        }
        String stationCloud = CacheAuth.getInstance().getStationCloud();
        if (!C3467s.m14513e(stationCloud)) {
            return "http://" + stationCloud;
        }
        return "http://login.gwifi.com.cn";
    }

    /* renamed from: c */
    public static String m14307c() {
        String rechargeWebUrl = CacheApp.getInstance().getRechargeWebUrl();
        if (!C3467s.m14513e(rechargeWebUrl)) {
            return rechargeWebUrl;
        }
        String u = m14357u();
        if (C3467s.m14513e(u)) {
            Log.m14403e(f13406d, "getAuthRootPath is empty");
            return "";
        }
        String str = (("" + u) + "/shop/Buy/index") + "?phone=" + CacheAccount.getInstance().getLoginPhone();
        String gwId = CacheAuth.getInstance().getGwId();
        if (!C3467s.m14513e(gwId)) {
            return str + "&gatewayId=" + gwId;
        }
        return str;
    }

    /* renamed from: d */
    public static String m14313d() {
        String gwId = CacheAuth.getInstance().getGwId();
        String u = m14357u();
        if (C3467s.m14513e(u)) {
            Log.m14403e(f13406d, "getAuthRootPath is empty");
            return "";
        }
        String str = "access_token=" + CacheAccount.getInstance().getLoginToken();
        if (!C3467s.m14513e(gwId)) {
            str = str + "&gw_id=" + gwId;
        }
        return u + f13362U + "?" + str + "&sign=" + MD5Util.m10825a(str + "&key=" + Constant.f13310cs).toLowerCase();
    }

    /* renamed from: e */
    public static String m14317e() {
        String gwId = CacheAuth.getInstance().getGwId();
        String u = m14357u();
        if (C3467s.m14513e(u)) {
            Log.m14403e(f13406d, "getAuthRootPath is empty");
            return "";
        }
        String str = "access_token=" + CacheAccount.getInstance().getLoginToken();
        if (!C3467s.m14513e(gwId)) {
            str = str + "&gw_id=" + gwId;
        }
        return u + f13361T + "?" + str + "&sign=" + MD5Util.m10825a(str + "&key=" + Constant.f13310cs).toLowerCase();
    }

    /* renamed from: f */
    public static String m14320f() {
        String u = m14357u();
        if (!C3467s.m14513e(u)) {
            return u + "/Signin/index?name={name}";
        }
        Log.m14403e(f13406d, "getAuthRootPath is empty");
        return "";
    }

    /* renamed from: g */
    public static String m14323g() {
        String u = m14357u();
        if (C3467s.m14513e(u)) {
            Log.m14403e(f13406d, "getAuthRootPath is empty");
            return "";
        }
        return ("" + u) + "/cmps/static/themes/dashboard/Zero6/templates/tpl/public/serveragreement.html";
    }

    /* renamed from: h */
    public static String m14326h() {
        String u = m14357u();
        if (C3467s.m14513e(u)) {
            Log.m14403e(f13406d, "getAuthRootPath is empty");
            return "";
        }
        return ("" + u) + "/cmps/static/themes/dashboard/Zero6/templates/tpl/public/privacyagreement.html";
    }

    /* renamed from: i */
    public static String m14329i() {
        String u = m14357u();
        if (C3467s.m14513e(u)) {
            Log.m14398b(f13406d, "getAuthRootPath is empty");
            return "";
        }
        return (("" + u) + "/cmps/admin.php/mpi/service") + "?gw_id=" + CacheAuth.getInstance().getGwId();
    }

    /* renamed from: v */
    private static String m14358v() {
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

    /* renamed from: j */
    public static String m14332j() {
        String v = m14358v();
        if (C3467s.m14513e(v)) {
            return "";
        }
        return ("" + v) + "/shop/point/exchange";
    }

    /* renamed from: k */
    public static String m14335k() {
        String v = m14358v();
        if (C3467s.m14513e(v)) {
            return "";
        }
        return ((("" + v) + "/shop/point/exchange_by_day") + "?gw_id={gw_id}") + "&name={name}";
    }

    /* renamed from: l */
    public static String m14338l() {
        return m14307c();
    }

    /* renamed from: m */
    public static String m14341m() {
        return "http://downres.gwifi.com.cn/randomMacHelp/help4Android.html";
    }

    /* renamed from: d */
    private static String m14314d(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(EncryptUtil.getEncrypt(str), "UTF-8");
    }

    /* renamed from: a */
    public static Request m14250a(Context context, int i, String str, int i2, String str2, int i3, String str3, String str4, int i4, int i5, String str5, int i6, String str6, String str7, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("device_model", SystemUtil.m14524b());
        hashMap.put("app_version", VersionUtil.m14565a());
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put(Constant.f13323i, CacheAccount.getInstance().getLoginPhone());
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap.put("gw_req_state", Integer.valueOf(i));
        hashMap.put("gw_req_message", str);
        hashMap.put("lx_req_state", Integer.valueOf(i2));
        hashMap.put("lx_req_message", str2);
        hashMap.put("login_req_state", Integer.valueOf(i3));
        hashMap.put("login_req_message", str3);
        hashMap.put("gw_ip", str4);
        hashMap.put("ping_gw", Integer.valueOf(i4));
        hashMap.put("ping_cloud", Integer.valueOf(i5));
        hashMap.put("rssi", str5);
        hashMap.put("channel", Integer.valueOf(i6));
        hashMap.put("bssid", str7);
        hashMap.put("offline_reason_list", str6);
        return OkHttpService.m10246a(m14281a("http", f13412j), JsonUtil.m14388a((HashMap<String, Object>) hashMap), eVar, obj);
    }

    /* renamed from: b */
    private static HashMap<String, Object> m14301b(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("token", EncryptUtil.getEncrypt(CacheAccount.getInstance().getUserToken()));
        hashMap.put("version", VersionUtil.m14565a());
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, EncryptUtil.getEncrypt(CacheAuth.getInstance().getLocalMac()));
        hashMap.put("gatewayId", EncryptUtil.getEncrypt(CacheAuth.getInstance().getGwId()));
        return hashMap;
    }

    /* renamed from: a */
    public static Request m14260a(Context context, String str, int i, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(str));
        hashMap.put("type", EncryptUtil.getEncrypt(String.valueOf(i)));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13425w), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14258a(Context context, String str, int i, int i2, int i3, String str2, String str3, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(str));
        hashMap.put("type", EncryptUtil.getEncrypt(String.valueOf(i)));
        hashMap.put("hotspotGroupId", EncryptUtil.getEncrypt(String.valueOf(i2)));
        hashMap.put("identityType", EncryptUtil.getEncrypt(String.valueOf(i3)));
        hashMap.put("identityId", EncryptUtil.getEncrypt(str2));
        hashMap.put("identityName", EncryptUtil.getEncrypt(str3));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13425w), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14266a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(str));
        hashMap.put(WifiEnterpriseConfig.PASSWORD_KEY, EncryptUtil.getEncrypt(str2));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str3));
        hashMap.put("ip", EncryptUtil.getEncrypt(CacheAuth.getInstance().getLocalIp()));
        hashMap.put("apMac", "");
        hashMap.put("gwAddress", EncryptUtil.getEncrypt(CacheAuth.getInstance().getGwIp()));
        hashMap.put("staType", EncryptUtil.getEncrypt(SystemUtil.m14539l(context)));
        hashMap.put("staModel", EncryptUtil.getEncrypt(SystemUtil.m14524b()));
        hashMap.put("identityName", EncryptUtil.getEncrypt(str4));
        hashMap.put("identityNumber", EncryptUtil.getEncrypt(str5));
        hashMap.put("recommendName", EncryptUtil.getEncrypt(str6));
        hashMap.put("recommendPhone", EncryptUtil.getEncrypt(str7));
        hashMap.put("agreement_signed", EncryptUtil.getEncrypt(String.valueOf(i)));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13427y), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14265a(Context context, String str, String str2, String str3, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(str));
        hashMap.put(WifiEnterpriseConfig.PASSWORD_KEY, EncryptUtil.getEncrypt(str2));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str3));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13428z), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: b */
    public static Request m14295b(Context context, String str, String str2, String str3, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(str));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str2));
        hashMap.put("newStaticPassword", EncryptUtil.getEncrypt(str3));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13342A), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: c */
    public static Request m14304c(Context context, String str, String str2, String str3, OkRequestHandler<String> eVar, Object obj) {
        String gwId;
        if (str == null || C3467s.m14513e(str) || (gwId = CacheAuth.getInstance().getGwId()) == null || C3467s.m14513e(gwId)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(str));
        hashMap.put(WifiEnterpriseConfig.PASSWORD_KEY, EncryptUtil.getEncrypt(str2));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str3));
        hashMap.put("service_type", Integer.valueOf(CacheAccount.getInstance().getLoginServiceType()));
        hashMap.put("ip", EncryptUtil.getEncrypt(CacheAuth.getInstance().getLocalIp()));
        hashMap.put("apMac", "");
        hashMap.put("gwAddress", EncryptUtil.getEncrypt(CacheAuth.getInstance().getGwIp()));
        hashMap.put("staType", EncryptUtil.getEncrypt(SystemUtil.m14539l(context)));
        hashMap.put("ssid", EncryptUtil.getEncrypt(WiFiUtil.m14022a(context).mo27614h()));
        hashMap.put("staModel", EncryptUtil.getEncrypt(SystemUtil.m14524b()));
        hashMap.put("im", EncryptUtil.getEncrypt(SystemUtil.m14527c(context)));
        hashMap.put("installWX", StorageUtils.m14492c(GBApplication.instance(), "com.tencent.mm") ? EncryptUtil.getEncrypt("1") : EncryptUtil.getEncrypt("0"));
        hashMap.put(Constants.KEY_IMSI, EncryptUtil.getEncrypt(SystemUtil.m14530e(context)));
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID, EncryptUtil.getEncrypt(SystemUtil.m14532f(context)));
        hashMap.put("app_uuid", EncryptUtil.getEncrypt(CacheApp.getInstance().getAppUuid()));
        hashMap.put("auth_mode", EncryptUtil.getEncrypt("" + CacheAuth.getInstance().getAuthMode()));
        hashMap.put("filter_id", EncryptUtil.getEncrypt(CacheAuth.getInstance().getFilterId()));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10260b(m14281a("http", f13404b), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14261a(Context context, String str, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("app_uuid", EncryptUtil.getEncrypt(CacheApp.getInstance().getAppUuid()));
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(str));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(CacheAccount.getInstance().getLoginStaticPassword()));
        if (SystemUtil.m14535h(context)) {
            hashMap.put("staType", EncryptUtil.getEncrypt(Constant.f13324j));
            hashMap.put("staModel", EncryptUtil.getEncrypt(SystemUtil.m14524b()));
            hashMap.put("btype", EncryptUtil.getEncrypt(MessageService.MSG_DB_NOTIFY_CLICK));
        } else {
            hashMap.put("staType", EncryptUtil.getEncrypt(Constant.f13323i));
            hashMap.put("staModel", EncryptUtil.getEncrypt(SystemUtil.m14524b()));
            hashMap.put("btype", EncryptUtil.getEncrypt("1"));
        }
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13405c), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14249a(Context context, int i, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("orgType", Integer.valueOf(i));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("version", VersionUtil.m14565a());
        hashMap2.put("token", CacheAccount.getInstance().getUserToken());
        hashMap2.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap2.put("gatewayId", CacheAuth.getInstance().getGwId());
        hashMap2.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13348G), JsonUtil.m14388a((HashMap<String, Object>) hashMap2), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14247a(Context context, int i, int i2, String str, String str2, String str3, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        if (i == 0) {
            hashMap.put("hotspot_group_id", EncryptUtil.getEncrypt(""));
        } else {
            hashMap.put("hotspot_group_id", EncryptUtil.getEncrypt(String.valueOf(i)));
        }
        hashMap.put("identity_type", EncryptUtil.getEncrypt(String.valueOf(i2)));
        hashMap.put("identity_id", EncryptUtil.getEncrypt(str));
        hashMap.put("identity_name", EncryptUtil.getEncrypt(str2));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str3));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13349H), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14248a(Context context, int i, int i2, String str, String str2, String str3, String str4, String str5, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("hotspot_group_id", EncryptUtil.getEncrypt(String.valueOf(i)));
        hashMap.put("identity_type", EncryptUtil.getEncrypt(String.valueOf(i2)));
        hashMap.put("identity_id", EncryptUtil.getEncrypt(str2));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str3));
        hashMap.put("identity_name", EncryptUtil.getEncrypt(str));
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(str4));
        hashMap.put("vcode", EncryptUtil.getEncrypt(str5));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13350I), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14243a(int i, int i2, OkRequestHandler<String> eVar, Object obj) {
        Log.m14398b(f13406d, "queryNextPage:" + i);
        HashMap hashMap = new HashMap();
        hashMap.put("pageNo", Integer.valueOf(i));
        hashMap.put("pageSize", Integer.valueOf(i2));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("token", CacheAccount.getInstance().getUserToken());
        hashMap2.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap2.put("gatewayId", CacheAuth.getInstance().getGwId());
        hashMap2.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13365X), JsonUtil.m14388a((HashMap<String, Object>) hashMap2), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14268a(OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("token", CacheAccount.getInstance().getUserToken());
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap.put("gatewayId", CacheAuth.getInstance().getGwId());
        hashMap.put("data", "");
        return OkHttpService.m10246a(m14281a("http", f13366Y), JsonUtil.m14388a((HashMap<String, Object>) hashMap), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14251a(Context context, OkRequestHandler<byte[]> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.KEY_OS_TYPE, 1);
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        HashMap hashMap2 = new HashMap();
        hashMap2.put("token", CacheAccount.getInstance().getUserToken());
        hashMap2.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap2.put("version", VersionUtil.m14565a());
        hashMap2.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13409g), JsonUtil.m14388a((HashMap<String, Object>) hashMap2), eVar, obj);
    }

    /* renamed from: a */
    public static String m14279a(String str) {
        return m14281a("http", f13410h) + "?md5=" + str;
    }

    /* renamed from: b */
    public static Request m14296b(OkRequestHandler<String> eVar, Object obj) {
        String k = WiFiUtil.m14022a(GBApplication.instance()).mo27617k();
        String j = WiFiUtil.m14022a(GBApplication.instance()).mo27616j();
        return OkHttpService.m10259b("http://" + k + ":8060/wifidog/userlogout?ip=" + j + "&mac=" + CacheAuth.getInstance().getLocalMac(), eVar, obj);
    }

    /* renamed from: c */
    public static Request m14305c(OkRequestHandler<String> eVar, Object obj) {
        String k = WiFiUtil.m14022a(GBApplication.instance()).mo27617k();
        if (C3467s.m14513e(k)) {
            Log.m14403e(f13406d, "syncAuthState gatewayIp is empty");
            return null;
        }
        Log.m14398b(f13406d, "syncAuthState gatewayIp=" + k);
        String c = SystemUtil.m14527c(GBApplication.instance());
        String appUuid = CacheApp.getInstance().getAppUuid();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (C3467s.m14513e(c)) {
            Log.m14398b(f13406d, "IMEI UUID:" + SystemUtil.m14529d(GBApplication.instance()));
        }
        String str = "terminal_app_uuid=" + appUuid;
        Log.m14398b(f13406d, "syncAuthState APP UUID:" + appUuid);
        if (!C3467s.m14513e(c)) {
            str = str + "&terminal_imei=" + c;
        }
        String upperCase = MD5Util.m10825a(((str + "&terminal_os_type=" + 1) + "&timestamp=" + currentTimeMillis) + "&key=" + "Z2l3aWZpZ3cyMDE5MDcwMTE0MDIwMA").toUpperCase();
        HashMap hashMap = new HashMap();
        hashMap.put("terminal_app_uuid", appUuid);
        hashMap.put("terminal_imei", c);
        hashMap.put("terminal_os_type", 1);
        hashMap.put("timestamp", Long.valueOf(currentTimeMillis));
        hashMap.put("sign", upperCase);
        String a = JsonUtil.m14388a((HashMap<String, Object>) hashMap);
        try {
            String a2 = AESUtils.m10814a(a, "gi3a3cc2c7e309581c".substring(2), "Z2l3aWZpZ3cyMDE5MDcwMTE0MDIwMA".substring(0, 16));
            HashMap hashMap2 = new HashMap();
            hashMap2.put(C1499b.f3354h, "gi3a3cc2c7e309581c");
            hashMap2.put("raw_data", a2);
            Log.m14398b(f13406d, "temp=" + a);
            Log.m14398b(f13406d, "app_key=" + "gi3a3cc2c7e309581c");
            Log.m14398b(f13406d, "raw_data=" + a2);
            return OkHttpService.m10246a("http://" + k + ":8060/wifidog/fastRoamAuth", JsonUtil.m14388a((HashMap<String, Object>) hashMap2), eVar, obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static Request m14272a(String str, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("type", str);
        return OkHttpService.m10244a(m14283a(f13381ad, hashMap), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14271a(String str, int i, int i2, String str2, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("type", str2);
        hashMap.put("key", str);
        hashMap.put("pageno", Integer.valueOf(i));
        hashMap.put("pagesize", Integer.valueOf(i2));
        return OkHttpService.m10244a(m14283a(f13387aj, hashMap), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14273a(String str, Integer num, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("type", str);
        hashMap.put("product_id", num);
        return OkHttpService.m10244a(m14283a(f13388ak, hashMap), eVar, obj);
    }

    /* renamed from: b */
    public static Request m14289b(Context context, int i, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("cat_id", Integer.valueOf(i));
        return OkHttpService.m10244a(m14283a(f13382ae, hashMap), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14259a(Context context, String str, int i, int i2, String str2, int i3, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("type", str2);
        hashMap.put("tagid", str);
        hashMap.put("pageno", Integer.valueOf(i));
        hashMap.put("pagesize", Integer.valueOf(i2));
        hashMap.put("order", Integer.valueOf(i3));
        return OkHttpService.m10244a(m14283a(f13383af, hashMap), eVar, obj);
    }

    /* renamed from: b */
    public static Request m14292b(Context context, String str, int i, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("type", str);
        hashMap.put(SocializeConstants.TENCENT_UID, "");
        hashMap.put("device_id", "");
        hashMap.put("token", "");
        hashMap.put("product_id", Integer.valueOf(i));
        return OkHttpService.m10244a(m14283a(f13384ag, hashMap), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14257a(Context context, File file, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.f13323i, CacheAccount.getInstance().getLoginPhone());
        HashMap hashMap2 = new HashMap();
        hashMap2.put("token", CacheAccount.getInstance().getUserToken());
        hashMap2.put("version", VersionUtil.m14565a());
        hashMap2.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap2.put("gatewayId", CacheAuth.getInstance().getGwId());
        hashMap2.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        HashMap hashMap3 = new HashMap();
        hashMap3.put("head_shot", file);
        HashMap hashMap4 = new HashMap();
        hashMap4.put(org.apache.xalan.templates.Constants.ELEMNAME_ROOT_STRING, JsonUtil.m14388a((HashMap<String, Object>) hashMap2));
        return OkHttpService.m10263i().mo24143a(m14281a("http", f13343B), hashMap4, hashMap3, eVar, obj);
    }

    /* renamed from: a */
    public static Request m14264a(Context context, String str, String str2, int i, String str3, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(CacheAccount.getInstance().getLoginPhone()));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(CacheAccount.getInstance().getLoginStaticPassword()));
        hashMap.put("alias", EncryptUtil.getEncrypt(str));
        hashMap.put("identity_name", EncryptUtil.getEncrypt(str2));
        hashMap.put("gender", EncryptUtil.getEncrypt(String.valueOf(i)));
        hashMap.put("email", EncryptUtil.getEncrypt(str3));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13344C), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: b */
    public static Request m14290b(Context context, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(CacheAccount.getInstance().getLoginPhone()));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(CacheAccount.getInstance().getLoginStaticPassword()));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13345D), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: c */
    public static Request m14303c(Context context, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(CacheAccount.getInstance().getLoginPhone()));
        hashMap.put("agreement_signed", EncryptUtil.getEncrypt(MessageService.MSG_DB_NOTIFY_CLICK));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13346E), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: d */
    public static Request m14311d(Context context, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        return OkHttpService.m10244a(m14283a(f13393ap, hashMap), eVar, obj);
    }

    /* renamed from: b */
    public static Request m14293b(Context context, String str, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("sdk_source", 1);
        hashMap.put("os", 1);
        if (SystemUtil.m14531e()) {
            hashMap.put("app_source", 3);
        }
        hashMap.put(MsgConstant.KEY_DEVICE_TOKEN, str);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("token", CacheAccount.getInstance().getUserToken());
        hashMap2.put("version", VersionUtil.m14565a());
        hashMap2.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap2.put("gatewayId", CacheAuth.getInstance().getGwId());
        hashMap2.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        String a = m14281a("http", f13347F);
        String a2 = JsonUtil.m14388a((HashMap<String, Object>) hashMap2);
        Log.m14398b(f13406d, "action:" + a);
        Log.m14398b(f13406d, "paramStr:" + a2);
        Log.m14398b(f13406d, "setDeviceToken:" + str);
        return OkHttpService.m10246a(a, a2, eVar, obj);
    }

    /* renamed from: b */
    public static Request m14298b(String str, OkRequestHandler<String> eVar, Object obj) {
        String str2;
        String a = VersionUtil.m14565a();
        String str3 = ("" + "app_uuid=" + CacheApp.getInstance().getAppUuid()) + "&app_token=" + str;
        if (!SystemUtil.m14531e()) {
            str2 = str3 + "&app_source=1";
        } else if (SystemUtil.m14533f()) {
            str2 = str3 + "&app_source=4";
        } else {
            str2 = str3 + "&app_source=3";
        }
        String str4 = (str2 + "&app_os=1") + "&user_account=" + CacheAccount.getInstance().getLoginPhone();
        String u = m14357u();
        if (C3467s.m14513e(u)) {
            Log.m14403e(f13406d, "getAuthRootPath is empty");
            return null;
        } else if (CacheAuth.getInstance().isPortal()) {
            String str5 = u + "/gportal/app/setAppSource";
            String str6 = str4 + "&sign=" + MD5Util.m10825a(str4 + Constant.f13310cs).toLowerCase();
            HashMap hashMap = new HashMap();
            try {
                String d = m14314d(str6);
                hashMap.put("version", a);
                hashMap.put("data", d);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return OkHttpService.m10248a(str5, hashMap, eVar, obj);
        } else {
            try {
                return OkHttpService.m10244a((u + "/cmps/admin.php/mpi/setAppSource") + "?version=" + a + "&data=" + URLEncoder.encode(URLEncoder.encode(EncryptUtil.getEncrypt(str4), "UTF-8"), "UTF-8"), eVar, obj);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    /* renamed from: e */
    public static Request m14315e(Context context, OkRequestHandler<String> eVar, Object obj) {
        String str = "";
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            int portalPort = CacheAuth.getInstance().getPortalPort();
            if (!TextUtils.isEmpty(portalHost)) {
                str = "http://" + portalHost + ":" + portalPort + f13358Q + "?gatewayId=" + CacheAuth.getInstance().getGwId() + "&phone=" + CacheAccount.getInstance().getLoginPhone() + "&os_type=" + 1 + "&app_version=" + VersionUtil.m14565a();
            } else {
                Log.m14402d(f13406d, "invalid data: portal but no host");
            }
        } else {
            str = m14357u();
        }
        if (!C3467s.m14513e(str)) {
            return OkHttpService.m10244a(str + f13358Q + "?gatewayId=" + CacheAuth.getInstance().getGwId() + "&phone=" + CacheAccount.getInstance().getLoginPhone() + "&os_type=" + 1 + "&app_version=" + VersionUtil.m14565a(), eVar, obj);
        }
        Log.m14403e(f13406d, "getAuthRootPath is empty");
        return null;
    }

    /* renamed from: f */
    public static Request m14318f(Context context, OkRequestHandler<String> eVar, Object obj) {
        String u = m14357u();
        if (C3467s.m14513e(u)) {
            Log.m14403e(f13406d, "getAuthRootPath is empty");
            return null;
        }
        String loginToken = CacheAccount.getInstance().getLoginToken();
        String gwId = CacheAuth.getInstance().getGwId();
        Long valueOf = Long.valueOf(System.currentTimeMillis() / 1000);
        String str = "access_token=" + loginToken;
        if (!C3467s.m14513e(gwId)) {
            str = str + "&gw_id=" + gwId;
        }
        String str2 = str + "&timestamp=" + ((Object) valueOf);
        return OkHttpService.m10244a(u + f13364W + "?" + str2 + "&sign=" + MD5Util.m10825a(str2 + "&key=" + TokenUtil.f13106b).toLowerCase(), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14263a(Context context, String str, String str2, int i, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("service_type", EncryptUtil.getEncrypt(String.valueOf(i)));
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(str));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str2));
        hashMap.put("ip", EncryptUtil.getEncrypt(CacheAuth.getInstance().getLocalIp()));
        hashMap.put("apMac", "");
        hashMap.put("gwAddress", EncryptUtil.getEncrypt(CacheAuth.getInstance().getGwIp()));
        hashMap.put("staType", EncryptUtil.getEncrypt(SystemUtil.m14539l(context)));
        hashMap.put("staModel", EncryptUtil.getEncrypt(SystemUtil.m14524b()));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13426x), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: b */
    public static Request m14294b(Context context, String str, String str2, int i, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("service_type", EncryptUtil.getEncrypt(String.valueOf(i)));
        hashMap.put(Constant.f13323i, EncryptUtil.getEncrypt(str));
        hashMap.put("staticPassword", EncryptUtil.getEncrypt(str2));
        hashMap.put("ip", EncryptUtil.getEncrypt(CacheAuth.getInstance().getLocalIp()));
        hashMap.put("apMac", "");
        hashMap.put("gwAddress", EncryptUtil.getEncrypt(CacheAuth.getInstance().getGwIp()));
        hashMap.put("staType", EncryptUtil.getEncrypt(SystemUtil.m14539l(context)));
        hashMap.put("staModel", EncryptUtil.getEncrypt(SystemUtil.m14524b()));
        HashMap<String, Object> b = m14301b(context);
        b.put("data", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        return OkHttpService.m10246a(m14281a("http", f13426x), JsonUtil.m14388a(b), eVar, obj);
    }

    /* renamed from: a */
    public static HttpRequest m14276a(Context context, String str, Integer num, String str2, Integer num2, RequestHandler<HttpRequest, HttpResponse> cVar) {
        String u = m14357u();
        if (C3467s.m14513e(u)) {
            Log.m14403e(f13406d, "getAuthRootPath is empty");
            return null;
        }
        String lowerCase = MD5Util.m10825a(CacheAccount.getInstance().getLoginPhone()).toLowerCase();
        HttpRequest a = BasicHttpRequest.m10185a(u + f13360S + "?user=" + lowerCase + "&product_type=" + str + "&product_id=" + ((Object) num) + "&product_name=" + str2 + "&points=" + ((Object) num2) + "&sig=" + MD5Util.m10825a(Constant.f13310cs + "||" + lowerCase + "||" + ((Object) num)).substring(12, 20).toLowerCase());
        m14285a(context, a, cVar);
        return a;
    }

    /* renamed from: a */
    public static Request m14262a(Context context, String str, Integer num, String str2, Integer num2, OkRequestHandler<String> eVar, Object obj) {
        String u = m14357u();
        if (C3467s.m14513e(u)) {
            Log.m14403e(f13406d, "getAuthRootPath is empty");
            return null;
        }
        String lowerCase = MD5Util.m10825a(CacheAccount.getInstance().getLoginPhone()).toLowerCase();
        return OkHttpService.m10244a(u + f13360S + "?user=" + lowerCase + "&product_type=" + str + "&product_id=" + ((Object) num) + "&product_name=" + str2 + "&points=" + ((Object) num2) + "&sig=" + MD5Util.m10825a(Constant.f13310cs + "||" + lowerCase + "||" + ((Object) num)).substring(12, 20).toLowerCase(), eVar, obj);
    }

    /* renamed from: d */
    public static Request m14312d(OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put(Constant.f13323i, CacheAccount.getInstance().getLoginPhone());
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap.put("versionName", VersionUtil.m14565a());
        return OkHttpService.m10244a(m14283a(f13413k, hashMap), eVar, obj);
    }

    /* renamed from: e */
    public static Request m14316e(OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put(Constant.f13323i, CacheAccount.getInstance().getLoginPhone());
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        return OkHttpService.m10244a(m14283a(f13417o, hashMap), eVar, obj);
    }

    /* renamed from: f */
    public static Request m14319f(OkRequestHandler<String> eVar, Object obj) {
        return OkHttpService.m10244a("http://" + WiFiUtil.m14022a(GBApplication.instance()).mo27617k() + ":8060/wifidog/get_hotspot_group", eVar, obj);
    }

    /* renamed from: a */
    public static Request m14267a(OkRequestHandler<String> eVar, Integer num, Integer num2, Integer num3, Integer num4, Object obj) {
        String k = WiFiUtil.m14022a(GBApplication.instance()).mo27617k();
        String localMac = CacheAuth.getInstance().getLocalMac();
        return OkHttpService.m10244a("http://" + k + ":8060/wifidog/add_station?mac=" + localMac + "&ip=" + WiFiUtil.m14022a(GBApplication.instance()).mo27616j() + "&sign=" + MD5Util.m10825a("mac=" + localMac + Constant.f13310cs + (System.currentTimeMillis() / 1000) + "").toLowerCase() + "&available_time=" + ((Object) num) + "&bw_up=" + ((Object) num2) + "&bw_down=" + ((Object) num3) + "&free_type=" + ((Object) num4) + "&times=" + (System.currentTimeMillis() / 1000) + "", eVar, obj);
    }

    /* renamed from: g */
    public static Request m14322g(OkRequestHandler<String> eVar, Object obj) {
        return OkHttpService.m10244a("http://" + WiFiUtil.m14022a(GBApplication.instance()).mo27617k() + ":8060/wifidog/get_auth_state?mac=&ip=" + WiFiUtil.m14022a(GBApplication.instance()).mo27616j(), eVar, obj);
    }

    /* renamed from: n */
    public static Response m14343n() throws IOException {
        return OkHttpService.m10251a("http://" + WiFiUtil.m14022a(GBApplication.instance()).mo27617k() + ":8060/wifidog/get_auth_state?mac=&ip=" + WiFiUtil.m14022a(GBApplication.instance()).mo27616j());
    }

    /* renamed from: b */
    public static Response m14299b(String str) throws IOException {
        String k = WiFiUtil.m14022a(GBApplication.instance()).mo27617k();
        String lowerCase = CacheAuth.getInstance().getLocalMac().toLowerCase();
        return OkHttpService.m10251a("http://" + k + ":8881/app_server/get_ap?mac=" + lowerCase + "&ip=" + WiFiUtil.m14022a(GBApplication.instance()).mo27616j() + "&token=" + MD5Util.m10825a("mac=" + lowerCase + Constant.f13310cs + new SimpleDateFormat("yyyyMMddHH").format(new Date())).toLowerCase() + "&method=get_client_info&bssid=" + str + "&data=");
    }

    /* renamed from: c */
    public static Request m14302c(Context context, int i, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put(Constant.f13323i, CacheAccount.getInstance().getLoginPhone());
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap.put("layout_type", Integer.valueOf(i));
        hashMap.put("versionName", VersionUtil.m14565a());
        hashMap.put("identityType", CacheAccount.getInstance().getIdentityType());
        if (SystemUtil.m14531e()) {
            hashMap.put("orgTypeDefault", 2);
        } else if (Config.m14094a().mo27812o()) {
            hashMap.put("orgTypeDefault", 1);
        } else {
            hashMap.put("orgTypeDefault", 2);
        }
        return OkHttpService.m10244a(m14283a(f13414l, hashMap), eVar, obj);
    }

    /* renamed from: g */
    public static Request m14321g(Context context, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put(Constant.f13323i, CacheAccount.getInstance().getLoginPhone());
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        return OkHttpService.m10244a(m14283a(f13415m, hashMap), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14246a(Context context, int i, int i2, String str, String str2, int i3, int i4, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("platformOs", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap.put("sourceType", Integer.valueOf(i));
        hashMap.put("productId", Integer.valueOf(i2));
        hashMap.put("productType", str);
        hashMap.put("webUrl", str2);
        hashMap.put("position", Integer.valueOf(i3));
        hashMap.put("loadTag", Integer.valueOf(i4));
        return OkHttpService.m10244a(m14283a(f13395ar, hashMap), eVar, obj);
    }

    /* renamed from: h */
    public static Request m14324h(Context context, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("accountId", Integer.valueOf(CacheAccount.getInstance().getUserId()));
        hashMap.put("terminalMac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("deviceMac", WiFiUtil.m14022a(context).mo27615i());
        hashMap.put("imei", SystemUtil.m14527c(context));
        return OkHttpService.m10244a(m14283a(f13396as, hashMap), eVar, obj);
    }

    /* renamed from: i */
    public static Request m14327i(Context context, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("accountId", Integer.valueOf(CacheAccount.getInstance().getUserId()));
        hashMap.put("terminalMac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("deviceMac", WiFiUtil.m14022a(context).mo27615i());
        hashMap.put("imei", SystemUtil.m14527c(context));
        return OkHttpService.m10244a(m14283a(f13397at, hashMap), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14245a(Context context, int i, int i2, String str, int i3, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("accountId", str);
        hashMap.put("activityId", Integer.valueOf(i));
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("transId", Integer.valueOf(i2));
        hashMap.put("transType", 1);
        hashMap.put("balance", Integer.valueOf(i3));
        hashMap.put("message", "");
        String a = JsonUtil.m14388a((HashMap<String, Object>) hashMap);
        String a2 = MsgEncoder.m10827a(a);
        String a3 = m14281a("http", f13398au);
        if (a2 != null) {
            a = a.replaceFirst("\\}", ",\"sign\":\"" + a2 + "\"\\}");
        }
        return OkHttpService.m10246a(a3, a, eVar, obj);
    }

    /* renamed from: c */
    private static HashMap<String, Object> m14309c(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put("accountId", Integer.valueOf(CacheAccount.getInstance().getUserId()));
        hashMap.put("terminalMac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("deviceMac", WiFiUtil.m14022a(context).mo27615i());
        hashMap.put("imei", SystemUtil.m14527c(context));
        return hashMap;
    }

    /* renamed from: j */
    public static Request m14330j(Context context, OkRequestHandler<String> eVar, Object obj) {
        return OkHttpService.m10244a(m14283a(f13399av, m14309c(context)), eVar, obj);
    }

    /* renamed from: k */
    public static Request m14333k(Context context, OkRequestHandler<String> eVar, Object obj) {
        return OkHttpService.m10244a(m14283a(f13400aw, m14309c(context)), eVar, obj);
    }

    /* renamed from: l */
    public static Request m14336l(Context context, OkRequestHandler<String> eVar, Object obj) {
        return OkHttpService.m10244a(m14283a(f13401ax, m14309c(context)), eVar, obj);
    }

    /* renamed from: m */
    public static Request m14339m(Context context, OkRequestHandler<String> eVar, Object obj) {
        return OkHttpService.m10244a(m14283a(f13402ay, m14309c(context)), eVar, obj);
    }

    /* renamed from: n */
    public static Request m14342n(Context context, OkRequestHandler<String> eVar, Object obj) {
        return OkHttpService.m10244a(m14283a(f13403az, m14309c(context)), eVar, obj);
    }

    /* renamed from: d */
    public static Request m14310d(Context context, int i, OkRequestHandler<String> eVar, Object obj) {
        HashMap<String, Object> c = m14309c(context);
        c.put("redId", Integer.valueOf(i));
        return OkHttpService.m10246a(m14281a("http", f13369aA), JsonUtil.m14388a(c), eVar, obj);
    }

    /* renamed from: w */
    private static HashMap<String, Object> m14359w() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put(Constant.f13323i, CacheAccount.getInstance().getLoginPhone());
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap.put("identityType", CacheAccount.getInstance().getIdentityType());
        return hashMap;
    }

    /* renamed from: a */
    public static Request m14244a(int i, OkRequestHandler<String> eVar, Object obj) {
        HashMap<String, Object> w = m14359w();
        w.put("num", Integer.valueOf(i));
        return OkHttpService.m10244a(m14283a(f13370aB, w), eVar, obj);
    }

    /* renamed from: b */
    public static Request m14288b(int i, OkRequestHandler<String> eVar, Object obj) {
        HashMap<String, Object> w = m14359w();
        w.put("collegeId", Integer.valueOf(CacheAccount.getInstance().getCollegeId()));
        w.put("departmentId", Integer.valueOf(CacheAccount.getInstance().getDepartmentId()));
        String orgId = CacheAuth.getInstance().getOrgId();
        w.put("orgId", Integer.valueOf(TextUtils.isEmpty(orgId) ? 0 : Integer.valueOf(orgId).intValue()));
        w.put("num", Integer.valueOf(i));
        w.put("versionName", VersionUtil.m14565a());
        return OkHttpService.m10244a(m14283a(f13371aC, w), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14270a(OkRequestHandler<String> eVar, Object obj, Context context) {
        HashMap<String, Object> w = m14359w();
        w.put("collegeId", Integer.valueOf(CacheAccount.getInstance().getCollegeId()));
        w.put("versionName", VersionUtil.m14565a());
        w.put("departmentId", Integer.valueOf(CacheAccount.getInstance().getDepartmentId()));
        String orgId = CacheAuth.getInstance().getOrgId();
        w.put("orgId", Integer.valueOf(TextUtils.isEmpty(orgId) ? 0 : Integer.valueOf(orgId).intValue()));
        return OkHttpService.m10244a(m14283a(f13372aD, w), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14269a(OkRequestHandler<String> eVar, Object obj, int i, Context context) {
        HashMap<String, Object> w = m14359w();
        w.put("collegeId", Integer.valueOf(CacheAccount.getInstance().getCollegeId()));
        w.put("departmentId", Integer.valueOf(CacheAccount.getInstance().getDepartmentId()));
        String orgId = CacheAuth.getInstance().getOrgId();
        w.put("orgId", Integer.valueOf(TextUtils.isEmpty(orgId) ? 0 : Integer.valueOf(orgId).intValue()));
        w.put("pageNum", Integer.valueOf(i));
        w.put("versionName", VersionUtil.m14565a());
        return OkHttpService.m10244a(m14283a(f13373aE, w), eVar, obj);
    }

    /* renamed from: b */
    public static Request m14297b(OkRequestHandler<String> eVar, Object obj, Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put(Constant.f13323i, CacheAccount.getInstance().getLoginPhone());
        hashMap.put("terminalMac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("deviceMac", WiFiUtil.m14022a(context).mo27615i());
        hashMap.put("imei", SystemUtil.m14527c(context));
        if (SystemUtil.m14531e()) {
            hashMap.put("orgTypeDefault", 2);
        } else if (Config.m14094a().mo27812o()) {
            hashMap.put("orgTypeDefault", 1);
        } else {
            hashMap.put("orgTypeDefault", 2);
        }
        return OkHttpService.m10244a(m14283a(f13416n, hashMap), eVar, obj);
    }

    /* renamed from: c */
    public static Request m14306c(OkRequestHandler<String> eVar, Object obj, Context context) {
        HashMap hashMap = new HashMap();
        String loginPhone = CacheAccount.getInstance().getLoginPhone();
        String localMac = CacheAuth.getInstance().getLocalMac();
        String str = AuthInfo.DEFAULT_SN;
        if (CacheAuth.getInstance().isPortal()) {
            str = CacheAuth.getInstance().getGwSn();
        }
        if (C3467s.m14513e(loginPhone) && C3467s.m14513e(localMac)) {
            str = CacheAuth.getInstance().getGwSn();
        }
        hashMap.put("os", 1);
        hashMap.put("sn", str);
        hashMap.put(Constant.f13323i, loginPhone);
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, localMac);
        if (SystemUtil.m14531e()) {
            hashMap.put("orgTypeDefault", 2);
        } else if (Config.m14094a().mo27812o()) {
            hashMap.put("orgTypeDefault", 1);
        } else {
            hashMap.put("orgTypeDefault", 2);
        }
        return OkHttpService.m10244a(m14283a(f13421s, hashMap), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14242a(float f, float f2, Integer num, OkRequestHandler<String> eVar, Object obj) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String str = currentTimeMillis + "";
        String lowerCase = MD5Util.m10825a(currentTimeMillis + "546c10bdfd98c5b043002a22").toLowerCase();
        HashMap hashMap = new HashMap();
        hashMap.put("longitude", Float.valueOf(f));
        hashMap.put("latitude", Float.valueOf(f2));
        hashMap.put("orgType", num);
        hashMap.put("timestamp", Long.valueOf(currentTimeMillis));
        hashMap.put("secret", lowerCase);
        return OkHttpService.m10244a(m14283a(f13374aF, hashMap), eVar, obj);
    }

    /* renamed from: o */
    public static Request m14344o(Context context, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        boolean h = SystemUtil.m14535h(context);
        hashMap.put("os_type", "1");
        hashMap.put("btype", h ? MessageService.MSG_DB_NOTIFY_CLICK : "1");
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap.put("imei", SystemUtil.m14527c(context));
        hashMap.put(Constants.KEY_IMSI, SystemUtil.m14530e(context));
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID, SystemUtil.m14532f(context));
        hashMap.put("api_level", Build.VERSION.SDK);
        hashMap.put("manufacture", Build.MANUFACTURER);
        hashMap.put(Constants.KEY_MODEL, Build.MODEL);
        hashMap.put("idfa", "");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("version", VersionUtil.m14565a());
        hashMap2.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        hashMap2.put("data", EncryptUtil.getEncrypt(m14308c(JsonUtil.m14388a((HashMap<String, Object>) hashMap))));
        return OkHttpService.m10246a(m14281a("http", f13351J), JsonUtil.m14388a((HashMap<String, Object>) hashMap2), eVar, obj);
    }

    /* renamed from: a */
    public static String m14282a(String str, String str2, int i) {
        try {
            return new OkHttpClient.C1268a().mo16485b((long) i, TimeUnit.SECONDS).mo16493c().mo16200a(new Request.C1190a().mo16101a(str).mo16108b("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").mo16096a((RequestBody) new FormBody.C1219a().mo16275a("data", str2).mo16276a()).mo16110c()).mo16074b().mo16127h().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x00b2  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m14278a(android.content.Context r9, java.lang.Object r10) throws java.p456io.IOException {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.util.HttpUtil.m14278a(android.content.Context, java.lang.Object):java.lang.String");
    }

    /* renamed from: h */
    public static Request m14325h(OkRequestHandler<String> eVar, Object obj) {
        if (!CacheAuth.getInstance().isPortal()) {
            Log.m14403e(f13406d, "invalid data: okPortalQueryAuthState but not portal");
            return null;
        }
        String str = "timestamp=" + ((Object) Long.valueOf(System.currentTimeMillis() / 1000)) + "&userIp=" + WiFiUtil.m14022a(GBApplication.instance()).mo27616j();
        String str2 = str + "&sign=" + MD5Util.m10825a(str + Constant.f13310cs).toLowerCase();
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("data", m14314d(str2));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return OkHttpService.m10248a("http://" + CacheAuth.getInstance().getPortalHost() + ":" + CacheAuth.getInstance().getPortalPort() + f13353L, hashMap, eVar, obj);
    }

    /* renamed from: p */
    public static Request m14346p(Context context, OkRequestHandler<String> eVar, Object obj) {
        if (!CacheAuth.getInstance().isPortal()) {
            Log.m14403e(f13406d, "invalid data: okPortalQueryAuthState but not portal");
            return null;
        }
        String j = WiFiUtil.m14022a(GBApplication.instance()).mo27616j();
        String portalHost = CacheAuth.getInstance().getPortalHost();
        int portalPort = CacheAuth.getInstance().getPortalPort();
        String str = "timestamp=" + ((Object) Long.valueOf(System.currentTimeMillis() / 1000)) + "&userIp=" + j;
        String str2 = str + "&sign=" + MD5Util.m10825a(str + Constant.f13310cs).toLowerCase();
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("data", m14314d(str2));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return OkHttpService.m10248a("http://" + portalHost + ":" + portalPort + f13355N, hashMap, eVar, obj);
    }

    /* renamed from: q */
    public static Request m14348q(Context context, OkRequestHandler<String> eVar, Object obj) {
        if (!CacheAuth.getInstance().isPortal()) {
            Log.m14403e(f13406d, "invalid data: okPortalQueryAuthState but not portal");
            return null;
        }
        HashMap hashMap = new HashMap();
        Integer num = new Integer(1);
        String localMac = CacheAuth.getInstance().getLocalMac();
        String loginPhone = CacheAccount.getInstance().getLoginPhone();
        String loginStaticPassword = CacheAccount.getInstance().getLoginStaticPassword();
        hashMap.put("appUuid", CacheApp.getInstance().getAppUuid());
        hashMap.put("userIp", WiFiUtil.m14022a(GBApplication.instance()).mo27616j());
        hashMap.put("nasName", CacheAuth.getInstance().getPortalNasName());
        hashMap.put("ssid", "");
        hashMap.put("nasIp", "");
        hashMap.put("userMac", localMac);
        hashMap.put("vlan", num);
        hashMap.put("apMac", "");
        hashMap.put("userFirstUrl", "");
        hashMap.put("userName", loginPhone);
        hashMap.put("passwd", loginStaticPassword);
        boolean h = SystemUtil.m14535h(context);
        hashMap.put("btype", h ? MessageService.MSG_DB_NOTIFY_CLICK : "1");
        hashMap.put("staType", h ? Constant.f13324j : Constant.f13323i);
        hashMap.put("staModel", SystemUtil.m14524b());
        hashMap.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        String a = m14284a((HashMap<String, Object>) hashMap);
        String a2 = MD5Util.m10825a(a + Constant.f13310cs);
        try {
            hashMap.put("passwd", URLEncoder.encode(loginStaticPassword, "UTF-8"));
            a = m14284a((HashMap<String, Object>) hashMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = a + "&sign=" + a2.toLowerCase();
        HashMap hashMap2 = new HashMap();
        try {
            hashMap2.put("data", m14314d(str));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return OkHttpService.m10248a("http://" + CacheAuth.getInstance().getPortalHost() + ":" + CacheAuth.getInstance().getPortalPort() + f13354M, hashMap2, eVar, obj);
    }

    /* renamed from: r */
    public static Request m14350r(Context context, OkRequestHandler<String> eVar, Object obj) {
        if (!CacheAuth.getInstance().isPortal()) {
            Log.m14403e(f13406d, "invalid data: portalBindMacAsync but not portal");
            return null;
        }
        String portalHost = CacheAuth.getInstance().getPortalHost();
        if (TextUtils.isEmpty(portalHost)) {
            Log.m14403e(f13406d, "invalid data: portalBindMacAsync portal but no host");
            return null;
        }
        int portalPort = CacheAuth.getInstance().getPortalPort();
        String portalNasName = CacheAuth.getInstance().getPortalNasName();
        String localMac = CacheAuth.getInstance().getLocalMac();
        if (TextUtils.isEmpty(localMac)) {
            Log.m14403e(f13406d, "invalid data: portalBindMacAsync portal but empty userMac");
            return null;
        }
        String loginPhone = CacheAccount.getInstance().getLoginPhone();
        String loginStaticPassword = CacheAccount.getInstance().getLoginStaticPassword();
        HashMap hashMap = new HashMap();
        hashMap.put("appUuid", CacheApp.getInstance().getAppUuid());
        hashMap.put("nasName", portalNasName);
        hashMap.put("userMac", localMac);
        boolean h = SystemUtil.m14535h(context);
        hashMap.put("btype", h ? MessageService.MSG_DB_NOTIFY_CLICK : "1");
        hashMap.put("staType", h ? Constant.f13324j : Constant.f13323i);
        hashMap.put("staModel", SystemUtil.m14524b());
        hashMap.put("userName", loginPhone);
        hashMap.put("passwd", loginStaticPassword);
        hashMap.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        String a = m14284a((HashMap<String, Object>) hashMap);
        String a2 = MD5Util.m10825a(a + Constant.f13310cs);
        try {
            hashMap.put("passwd", URLEncoder.encode(loginStaticPassword, "UTF-8"));
            a = m14284a((HashMap<String, Object>) hashMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = a + "&sign=" + a2.toLowerCase();
        HashMap hashMap2 = new HashMap();
        try {
            hashMap2.put("data", m14314d(str));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return OkHttpService.m10248a("http://" + portalHost + ":" + portalPort + f13356O, hashMap2, eVar, obj);
    }

    /* renamed from: i */
    public static Request m14328i(OkRequestHandler<String> eVar, Object obj) {
        if (!CacheAuth.getInstance().isPortal()) {
            Log.m14403e(f13406d, "invalid data: portalSyncAuthState but not portal");
            return null;
        }
        String portalHost = CacheAuth.getInstance().getPortalHost();
        if (TextUtils.isEmpty(portalHost)) {
            Log.m14403e(f13406d, "invalid data: portalSyncAuthState portal but no host");
            return null;
        }
        int portalPort = CacheAuth.getInstance().getPortalPort();
        String portalNasName = CacheAuth.getInstance().getPortalNasName();
        String e = WiFiUtil.m14035e(GBApplication.instance().getApplicationContext());
        if (TextUtils.isEmpty(e)) {
            Log.m14403e(f13406d, "invalid data: portalSyncAuthState portal but empty userMac");
            return null;
        } else if (!CheckUtil.m14084b(e) || e.equals("00:00:00:00:00:00") || e.equals(WifiInfo.DEFAULT_MAC_ADDRESS)) {
            Log.m14403e(f13406d, "invalid data: portalSyncAuthState portal invalid userMac");
            return null;
        } else {
            String appUuid = CacheApp.getInstance().getAppUuid();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            HashMap hashMap = new HashMap();
            hashMap.put("appUuid", appUuid);
            hashMap.put("nasName", portalNasName);
            hashMap.put("userMac", e);
            boolean h = SystemUtil.m14535h(GBApplication.instance());
            hashMap.put("btype", h ? MessageService.MSG_DB_NOTIFY_CLICK : "1");
            hashMap.put("staType", h ? Constant.f13324j : Constant.f13323i);
            hashMap.put("staModel", SystemUtil.m14524b());
            hashMap.put("timestamp", Long.valueOf(currentTimeMillis));
            String a = m14284a((HashMap<String, Object>) hashMap);
            String str = a + "&sign=" + MD5Util.m10825a(a + Constant.f13310cs).toLowerCase();
            HashMap hashMap2 = new HashMap();
            try {
                hashMap2.put("data", m14314d(str));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            return OkHttpService.m10248a("http://" + portalHost + ":" + portalPort + f13357P, hashMap2, eVar, obj);
        }
    }

    /* renamed from: j */
    public static Request m14331j(OkRequestHandler<String> eVar, Object obj) {
        if (!CacheAuth.getInstance().isPortal()) {
            Log.m14403e(f13406d, "invalid data: portalSyncAuthState but not portal");
            return null;
        }
        String portalHost = CacheAuth.getInstance().getPortalHost();
        if (TextUtils.isEmpty(portalHost)) {
            Log.m14403e(f13406d, "invalid data: portalSyncAuthState portal but no host");
            return null;
        }
        int portalPort = CacheAuth.getInstance().getPortalPort();
        String portalNasName = CacheAuth.getInstance().getPortalNasName();
        HashMap hashMap = new HashMap();
        hashMap.put("nasName", portalNasName);
        hashMap.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        String a = m14284a((HashMap<String, Object>) hashMap);
        String str = a + "&sign=" + MD5Util.m10825a(a + Constant.f13310cs).toLowerCase();
        HashMap hashMap2 = new HashMap();
        try {
            hashMap2.put("data", m14314d(str));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return OkHttpService.m10248a("http://" + portalHost + ":" + portalPort + "/gportal/app/getHotspotGroup", hashMap2, eVar, obj);
    }

    /* renamed from: a */
    public static Request m14253a(Context context, OkRequestHandler<String> eVar, Object obj, int i, int i2) {
        HashMap hashMap = new HashMap();
        String f = SystemUtil.m14532f(context);
        String c = SystemUtil.m14527c(context);
        String e = SystemUtil.m14530e(context);
        String localMac = CacheAuth.getInstance().getLocalMac();
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        String str3 = Build.VERSION.SDK;
        int userId = CacheAccount.getInstance().getUserId();
        hashMap.put("terminalAndroidId", f);
        hashMap.put("terminalImei", c);
        hashMap.put("terminalImsi", e);
        hashMap.put("terminalMacAddress", localMac);
        hashMap.put("terminalManufacture", str);
        hashMap.put("terminalMode", str2);
        hashMap.put("terminalApiLevel", str3);
        hashMap.put("accountId", Integer.valueOf(userId));
        hashMap.put("pageno", Integer.valueOf(i));
        hashMap.put("pagesize", Integer.valueOf(i2));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(f);
        stringBuffer.append(c);
        stringBuffer.append(e);
        stringBuffer.append(localMac);
        stringBuffer.append(str);
        stringBuffer.append(str2);
        stringBuffer.append(str3);
        stringBuffer.append(userId);
        stringBuffer.append(i);
        stringBuffer.append(i2);
        stringBuffer.append("546c10bdfd98c5b043002a22");
        hashMap.put("sign", MD5Util.m10825a(stringBuffer.toString()));
        return OkHttpService.m10244a(m14283a(f13422t, hashMap), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14252a(Context context, OkRequestHandler<String> eVar, Object obj, int i) {
        HashMap hashMap = new HashMap();
        String f = SystemUtil.m14532f(context);
        String c = SystemUtil.m14527c(context);
        String e = SystemUtil.m14530e(context);
        String localMac = CacheAuth.getInstance().getLocalMac();
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        String str3 = Build.VERSION.SDK;
        int userId = CacheAccount.getInstance().getUserId();
        hashMap.put("terminalAndroidId", f);
        hashMap.put("terminalImei", c);
        hashMap.put("terminalImsi", e);
        hashMap.put("terminalMacAddress", localMac);
        hashMap.put("terminalManufacture", str);
        hashMap.put("terminalMode", str2);
        hashMap.put("terminalApiLevel", str3);
        hashMap.put("accountId", Integer.valueOf(userId));
        hashMap.put("productId", Integer.valueOf(i));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(f);
        stringBuffer.append(c);
        stringBuffer.append(e);
        stringBuffer.append(localMac);
        stringBuffer.append(str);
        stringBuffer.append(str2);
        stringBuffer.append(str3);
        stringBuffer.append(userId);
        stringBuffer.append(i);
        stringBuffer.append("546c10bdfd98c5b043002a22");
        hashMap.put("sign", MD5Util.m10825a(stringBuffer.toString()));
        return OkHttpService.m10244a(m14283a(f13423u, hashMap), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14254a(Context context, OkRequestHandler<String> eVar, Object obj, int i, String str, int i2) {
        HashMap hashMap = new HashMap();
        String f = SystemUtil.m14532f(context);
        String c = SystemUtil.m14527c(context);
        String e = SystemUtil.m14530e(context);
        String localMac = CacheAuth.getInstance().getLocalMac();
        String str2 = Build.MANUFACTURER;
        String str3 = Build.MODEL;
        String str4 = Build.VERSION.SDK;
        int userId = CacheAccount.getInstance().getUserId();
        hashMap.put("terminalAndroidId", f);
        hashMap.put("terminalImei", c);
        hashMap.put("terminalImsi", e);
        hashMap.put("terminalMacAddress", localMac);
        hashMap.put("terminalManufacture", str2);
        hashMap.put("terminalMode", str3);
        hashMap.put("terminalApiLevel", str4);
        hashMap.put("accountId", Integer.valueOf(userId));
        hashMap.put("productId", Integer.valueOf(i));
        hashMap.put("productName", str);
        hashMap.put(CRLDistributionPointsExtension.POINTS, Integer.valueOf(i2));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(f);
        stringBuffer.append(c);
        stringBuffer.append(e);
        stringBuffer.append(localMac);
        stringBuffer.append(str2);
        stringBuffer.append(str3);
        stringBuffer.append(str4);
        stringBuffer.append(userId);
        stringBuffer.append(i);
        stringBuffer.append(i2);
        stringBuffer.append("546c10bdfd98c5b043002a22");
        hashMap.put("sign", MD5Util.m10825a(stringBuffer.toString()));
        return OkHttpService.m10244a(m14283a(f13424v, hashMap), eVar, obj);
    }

    /* renamed from: s */
    public static Request m14352s(Context context, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", VersionUtil.m14565a());
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, CacheAuth.getInstance().getLocalMac());
        String loginPhone = CacheAccount.getInstance().getLoginPhone();
        try {
            hashMap.put("data", m14314d("name=" + loginPhone + "&pd=" + CacheAccount.getInstance().getLoginStaticPassword() + "&appId=" + "gif08514829dcf0103"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return OkHttpService.m10246a(m14281a("http", f13375aG), JsonUtil.m14388a((HashMap<String, Object>) hashMap), eVar, obj);
    }

    /* renamed from: t */
    public static Request m14354t(Context context, OkRequestHandler<String> eVar, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("os", 1);
        hashMap.put("sn", CacheAuth.getInstance().getGwSn());
        hashMap.put(Constant.f13323i, CacheAccount.getInstance().getLoginPhone());
        hashMap.put("terminalMac", CacheAuth.getInstance().getLocalMac());
        hashMap.put("deviceMac", WiFiUtil.m14022a(context).mo27615i());
        hashMap.put("imei", SystemUtil.m14527c(context));
        return OkHttpService.m10244a(m14283a(f13419q, hashMap), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14256a(Context context, OkRequestHandler<String> eVar, Object obj, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("terminalAndroidId", SystemUtil.m14532f(GBApplication.instance()));
        hashMap.put("terminalImei", SystemUtil.m14527c(GBApplication.instance()));
        hashMap.put("terminalImsi", SystemUtil.m14530e(GBApplication.instance()));
        hashMap.put("terminalMacAddress", CacheAuth.getInstance().getLocalMac());
        hashMap.put("terminalManufacture", Build.MANUFACTURER);
        hashMap.put("terminalMode", Build.MODEL);
        hashMap.put("terminalApiLevel", Build.VERSION.SDK);
        String a = m14284a((HashMap<String, Object>) hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("pkgName", str);
        String a2 = m14284a((HashMap<String, Object>) hashMap2);
        String str3 = null;
        try {
            str3 = str2 + "?device=" + URLEncoder.encode(EncryptUtil.getEncrypt(a), "UTF-8") + "&param=" + URLEncoder.encode(EncryptUtil.getEncrypt(a2), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OkHttpService.m10244a(str3, eVar, obj);
    }

    /* renamed from: k */
    public static Request m14334k(OkRequestHandler<String> eVar, Object obj) {
        int intValue = CacheAccount.getInstance().getHotspotGroupId().intValue();
        int userId = CacheAccount.getInstance().getUserId();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String lowerCase = MD5Util.m10825a(("app_id=" + TokenUtil.f13105a + "&project_id=" + intValue + "&timestamp=" + currentTimeMillis + "&user_id=" + userId) + "&key=" + TokenUtil.f13106b).toLowerCase();
        HashMap hashMap = new HashMap();
        hashMap.put("app_id", TokenUtil.f13105a);
        hashMap.put("project_id", Integer.valueOf(intValue));
        hashMap.put("timestamp", Long.valueOf(currentTimeMillis));
        hashMap.put(SocializeConstants.TENCENT_UID, Integer.valueOf(userId));
        hashMap.put("sign", lowerCase);
        Log.m14398b(f13406d, "offlineFaceDetect: getLoginToken paramString=" + JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        String s = m14353s();
        if (C3467s.m14513e(s)) {
            return null;
        }
        return OkHttpService.m10244a(s + "?" + m14284a((HashMap<String, Object>) hashMap), eVar, obj);
    }

    /* renamed from: l */
    public static Request m14337l(OkRequestHandler<String> eVar, Object obj) {
        return null;
    }

    /* renamed from: b */
    public static Request m14291b(Context context, OkRequestHandler<String> eVar, Object obj, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String lowerCase = MD5Util.m10825a(("access_token=" + str + "&order_no=" + str2 + "&timestamp=" + currentTimeMillis) + "&key=" + TokenUtil.f13106b).toLowerCase();
        HashMap hashMap = new HashMap();
        hashMap.put("access_token", str);
        hashMap.put("order_no", str2);
        hashMap.put("timestamp", Long.valueOf(currentTimeMillis));
        hashMap.put("sign", lowerCase);
        Log.m14398b(f13406d, "offlineFaceDetect: updateFaceOrderSuccessLog paramString=" + JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        String t = m14355t();
        if (C3467s.m14513e(t)) {
            return null;
        }
        return OkHttpService.m10244a((t + "/face/updateFaceOrderSuccessLog") + "?" + m14284a((HashMap<String, Object>) hashMap), eVar, obj);
    }

    /* renamed from: a */
    public static Request m14255a(Context context, OkRequestHandler<String> eVar, Object obj, String str, File file) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String lowerCase = MD5Util.m10825a(("access_token=" + str + "&timestamp=" + currentTimeMillis) + "&key=" + TokenUtil.f13106b).toLowerCase();
        HashMap hashMap = new HashMap();
        hashMap.put("access_token", str);
        hashMap.put("timestamp", String.valueOf(currentTimeMillis));
        hashMap.put("sign", lowerCase);
        Log.m14398b(f13406d, "offlineFaceDetect: registerFaceId paramString=" + JsonUtil.m14387a((Object) hashMap));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("file", file);
        String t = m14355t();
        if (C3467s.m14513e(t)) {
            return null;
        }
        return OkHttpService.m10263i().mo24143a(t + "/face/registerFaceId", hashMap, hashMap2, eVar, obj);
    }

    /* renamed from: a */
    public static String m14280a(String str, File file, int i) {
        String t = m14355t();
        if (C3467s.m14513e(t)) {
            return null;
        }
        String str2 = t + "/face/registerFaceId";
        MultipartBody.C1265a a = new MultipartBody.C1265a().mo16436a(MultipartBody.f2383e);
        if (file != null) {
            a.mo16439a("file", file.getName(), RequestBody.m2254a(MediaType.m2874a("image/jpg"), file));
        }
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String lowerCase = MD5Util.m10825a(("access_token=" + str + "&timestamp=" + currentTimeMillis) + "&key=" + TokenUtil.f13106b).toLowerCase();
        a.mo16438a("access_token", str);
        a.mo16438a("timestamp", String.valueOf(currentTimeMillis));
        a.mo16438a("sign", lowerCase);
        try {
            return new OkHttpClient.C1268a().mo16485b((long) i, TimeUnit.SECONDS).mo16493c().mo16200a(new Request.C1190a().mo16101a(str2).mo16096a((RequestBody) a.mo16440a()).mo16110c()).mo16074b().mo16127h().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static Request m14274a(String str, String str2, String str3, OkRequestHandler<String> eVar, Object obj) {
        String str4;
        String str5;
        String stationCloud = CacheAuth.getInstance().getStationCloud();
        if (!TextUtils.isEmpty(stationCloud)) {
            if (CheckUtil.m14083a(stationCloud)) {
                str5 = "http://";
            } else {
                str5 = "https://";
            }
            str4 = str5 + stationCloud;
        } else {
            str4 = "https://login.gwifi.com.cn";
        }
        try {
            String d = m14314d(CacheAccount.getInstance().getUserToken());
            String d2 = m14314d(str);
            String d3 = m14314d(str2);
            String d4 = m14314d(str3);
            String d5 = m14314d(CacheAuth.getInstance().getGwId());
            HashMap hashMap = new HashMap();
            hashMap.put("token", d);
            hashMap.put("version", VersionUtil.m14565a());
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, d2);
            hashMap.put("imei", d3);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID, d4);
            hashMap.put("gw_id", d5);
            return OkHttpService.m10244a((str4 + "/cmps/admin.php/mpi/checkAutoLogin") + "?" + m14284a((HashMap<String, Object>) hashMap), eVar, obj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: m */
    public static Request m14340m(OkRequestHandler<String> eVar, Object obj) {
        String str;
        String str2;
        HashMap hashMap = new HashMap();
        String j = WiFiUtil.m14022a(GBApplication.instance()).mo27616j();
        String localMac = CacheAuth.getInstance().getLocalMac();
        hashMap.put("package_name", GBApplication.instance().getApplicationContext().getPackageName());
        if (CacheAuth.getInstance().isPortal()) {
            String portalNasName = CacheAuth.getInstance().getPortalNasName();
            if (C3467s.m14513e(portalNasName)) {
                Log.m14403e(f13406d, "getGameConfig: portalNasName is empty");
                CacheGame.getInstance().setGameConfig(null);
                return null;
            }
            hashMap.put("gw_id", portalNasName);
        } else {
            String gwId = CacheAuth.getInstance().getGwId();
            if (C3467s.m14513e(gwId)) {
                Log.m14403e(f13406d, "getGameConfig: gw_id is empty");
                return null;
            }
            hashMap.put("gw_id", gwId);
        }
        hashMap.put("terminal_ip", j);
        hashMap.put("terminal_mac", localMac);
        hashMap.put("terminal_network_type", 1);
        hashMap.put("terminal_os_type", 1);
        hashMap.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        String a = m14284a((HashMap<String, Object>) hashMap);
        String str3 = a + "&sign=" + MD5Util.m10825a(a + "&key=aHR0cDovL2F1dGguZ3dpZmkuY29tLmNuLw").toLowerCase();
        HashMap hashMap2 = new HashMap();
        try {
            hashMap2.put("data", m14314d(str3));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (CacheAuth.getInstance().isPortal()) {
            String portalHost = CacheAuth.getInstance().getPortalHost();
            int portalPort = CacheAuth.getInstance().getPortalPort();
            if (!C3467s.m14513e(portalHost)) {
                str = "http://" + portalHost + ":" + portalPort;
            } else {
                Log.m14403e(f13406d, "invalid data: portal but no host");
                str = null;
            }
        } else {
            String stationCloud = CacheAuth.getInstance().getStationCloud();
            if (!TextUtils.isEmpty(stationCloud)) {
                if (CheckUtil.m14083a(stationCloud)) {
                    str2 = "http://";
                } else {
                    str2 = "https://";
                }
                str = str2 + stationCloud;
            } else {
                str = "https://login.gwifi.com.cn";
            }
        }
        if (!C3467s.m14513e(str)) {
            return OkHttpService.m10248a(str + "/cmps/admin.php/game/getGameConfig", hashMap2, eVar, obj);
        }
        return null;
    }

    /* renamed from: u */
    public static Request m14356u(Context context, OkRequestHandler<String> eVar, Object obj) {
        int i = 1;
        String loginToken = CacheAccount.getInstance().getLoginToken();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        int b = DateUtil.m14209b(CacheAccount.getInstance().getCircleBirth());
        String circleCity = CacheAccount.getInstance().getCircleCity();
        int circleSex = CacheAccount.getInstance().getCircleSex();
        if (circleSex != 0 && circleSex == 1) {
            i = 2;
        }
        String lowerCase = MD5Util.m10825a(("access_token=" + loginToken + "&age=" + b + "&city=" + circleCity + "&gender=" + i + "&timestamp=" + currentTimeMillis) + "&key=" + TokenUtil.f13106b).toLowerCase();
        HashMap hashMap = new HashMap();
        hashMap.put("access_token", loginToken);
        hashMap.put("age", Integer.valueOf(b));
        hashMap.put("city", circleCity);
        hashMap.put("gender", Integer.valueOf(i));
        hashMap.put("timestamp", Long.valueOf(currentTimeMillis));
        hashMap.put("sign", lowerCase);
        Log.m14398b(f13406d, "getInstructionCode paramString=" + JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        String t = m14355t();
        if (C3467s.m14513e(t)) {
            return null;
        }
        return OkHttpService.m10244a((t + "/tpic/getInstructionCode") + "?" + m14284a((HashMap<String, Object>) hashMap), eVar, obj);
    }
}
