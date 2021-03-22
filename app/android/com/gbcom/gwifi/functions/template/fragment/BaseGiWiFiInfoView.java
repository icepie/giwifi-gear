package com.gbcom.gwifi.functions.template.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.StatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.codec.EncryptUtil;
import com.gbcom.gwifi.functions.aboutus.AboutUsActivity;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.loading.domain.WindowConfigResponse;
import com.gbcom.gwifi.functions.p242c.p243a.GameConfig;
import com.gbcom.gwifi.functions.p242c.p243a.GameFilter;
import com.gbcom.gwifi.functions.p242c.p243a.GameIpNetMask;
import com.gbcom.gwifi.functions.ping.PingTestActivity;
import com.gbcom.gwifi.functions.system.SysStateActivity;
import com.gbcom.gwifi.functions.test.DeviceTest2Activity;
import com.gbcom.gwifi.functions.wifi.CheckNetWorkReceiver;
import com.gbcom.gwifi.functions.wifi.MultiCheckNetWorkUtils;
import com.gbcom.gwifi.functions.wifi.WiFi;
import com.gbcom.gwifi.functions.wifi.WiFiActivity;
import com.gbcom.gwifi.functions.wifi.WiFiService;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.functions.wifi.entity.BaseWifi;
import com.gbcom.gwifi.functions.wifi.entity.CheckResult;
import com.gbcom.gwifi.functions.wifi.entity.EapWifi;
import com.gbcom.gwifi.functions.wifi.entity.NoneWifi;
import com.gbcom.gwifi.functions.wifi.entity.PskWifi;
import com.gbcom.gwifi.functions.wifi.entity.WepWifi;
import com.gbcom.gwifi.functions.wifi.entity.WiFiScanList;
import com.gbcom.gwifi.functions.wifi.entity.WiFiScanResult;
import com.gbcom.gwifi.functions.wifi.p253a.LogoutCode;
import com.gbcom.gwifi.functions.wifi.p253a.WifiState;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.p235c.p237b.Utils;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.CheckUtil;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Config;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.FormatUtil;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.NetworkUtils;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheGame;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.util.p257b.UIUtil;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.tencent.smtt.sdk.TbsReaderView;
import com.umeng.message.proguard.MessageStore;
import java.net.URLDecoder;
import java.p456io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import libcore.icu.RelativeDateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;
import p419io.netty.handler.traffic.AbstractTrafficShapingHandler;

public abstract class BaseGiWiFiInfoView extends LinearLayout {

    /* renamed from: A */
    private static final int f11944A = 40;

    /* renamed from: B */
    private static final int f11945B = 41;

    /* renamed from: C */
    private static final int f11946C = 42;

    /* renamed from: D */
    private static final int f11947D = 43;

    /* renamed from: E */
    private static final int f11948E = 44;

    /* renamed from: F */
    private static final int f11949F = 50;

    /* renamed from: G */
    private static final int f11950G = 51;

    /* renamed from: H */
    private static final int f11951H = 60;

    /* renamed from: b */
    public static Toast f11952b = null;

    /* renamed from: c */
    public static String f11953c = "请打开定位获取WiFi名称 >>";

    /* renamed from: g */
    private static final String f11954g = "BaseGiWiFiInfoView";

    /* renamed from: h */
    private static final Object f11955h = "WifiFragment";

    /* renamed from: k */
    private static final int f11956k = 100;

    /* renamed from: l */
    private static final int f11957l = 2;

    /* renamed from: m */
    private static final int f11958m = 3;

    /* renamed from: n */
    private static final int f11959n = 4;

    /* renamed from: o */
    private static final int f11960o = 6;

    /* renamed from: p */
    private static final int f11961p = 7;

    /* renamed from: q */
    private static final int f11962q = 9;

    /* renamed from: r */
    private static final int f11963r = 11;

    /* renamed from: s */
    private static final int f11964s = 12;

    /* renamed from: t */
    private static final int f11965t = 13;

    /* renamed from: u */
    private static final int f11966u = 14;

    /* renamed from: v */
    private static final int f11967v = 15;

    /* renamed from: w */
    private static final int f11968w = 17;

    /* renamed from: x */
    private static final int f11969x = 30;

    /* renamed from: y */
    private static final int f11970y = 31;

    /* renamed from: z */
    private static final int f11971z = 34;

    /* renamed from: I */
    private String[] f11972I = {"WiFi已关闭", "正在关闭WiFi", "正在开启WiFi", "已开启WiFi", "未连接WiFi", "正在连接WiFi", "已连接", "正在检查网络", "已连接,但需要认证", "已连接,但上不了网", "成功连接到网络", "WiFi密码错误", "无法连接，请重新尝试", "正在尝试连接", "未开启WiFi", "可上网(认证结果稍后更新)", "已认证,只能访问内网"};

    /* renamed from: J */
    private String f11973J = "成功连接到游戏专线";

    /* renamed from: K */
    private String[] f11974K = {"gwifi", "giwifi", "wowifi", "wo-wifi"};

    /* renamed from: L */
    private String[] f11975L = {"wifi"};

    /* renamed from: M */
    private Long f11976M = -1L;

    /* renamed from: N */
    private Context f11977N;

    /* renamed from: O */
    private HandlerC3231a f11978O;

    /* renamed from: P */
    private Request f11979P;

    /* renamed from: Q */
    private Request f11980Q;

    /* renamed from: R */
    private Request f11981R;

    /* renamed from: S */
    private Request f11982S;

    /* renamed from: T */
    private Request f11983T;

    /* renamed from: U */
    private int f11984U = 0;

    /* renamed from: V */
    private int f11985V = 0;

    /* renamed from: W */
    private boolean f11986W = false;

    /* renamed from: a */
    public Lock f11987a;

    /* renamed from: aA */
    private TextView f11988aA;

    /* renamed from: aB */
    private TextView f11989aB;

    /* renamed from: aC */
    private ImageView f11990aC;

    /* renamed from: aD */
    private ImageView f11991aD;

    /* renamed from: aE */
    private ImageView f11992aE;

    /* renamed from: aF */
    private ImageView f11993aF;

    /* renamed from: aG */
    private ImageView f11994aG;

    /* renamed from: aH */
    private ImageView f11995aH;

    /* renamed from: aI */
    private ImageView f11996aI;

    /* renamed from: aJ */
    private ImageView f11997aJ;

    /* renamed from: aK */
    private ImageView f11998aK;

    /* renamed from: aL */
    private ImageView f11999aL;

    /* renamed from: aM */
    private ImageView f12000aM;

    /* renamed from: aN */
    private ImageView f12001aN;

    /* renamed from: aO */
    private ImageView f12002aO;

    /* renamed from: aP */
    private ImageView f12003aP;

    /* renamed from: aQ */
    private ImageView f12004aQ;

    /* renamed from: aR */
    private ImageView f12005aR;

    /* renamed from: aS */
    private ImageView f12006aS;

    /* renamed from: aT */
    private ImageView f12007aT;

    /* renamed from: aU */
    private LinearLayout f12008aU;

    /* renamed from: aV */
    private LinearLayout f12009aV;

    /* renamed from: aW */
    private LinearLayout f12010aW;

    /* renamed from: aX */
    private ImageView f12011aX;

    /* renamed from: aY */
    private ImageView f12012aY;

    /* renamed from: aZ */
    private ImageView f12013aZ;

    /* renamed from: aa */
    private boolean f12014aa = true;

    /* renamed from: ab */
    private boolean f12015ab = false;

    /* renamed from: ac */
    private WifiManager f12016ac;

    /* renamed from: ad */
    private ConnectivityManager f12017ad;

    /* renamed from: ae */
    private BaseWifi f12018ae;

    /* renamed from: af */
    private int f12019af = 0;

    /* renamed from: ag */
    private ArrayList<String> f12020ag = new ArrayList<>();

    /* renamed from: ah */
    private Long f12021ah = -1L;

    /* renamed from: ai */
    private long f12022ai = 0;

    /* renamed from: aj */
    private int f12023aj = 0;

    /* renamed from: ak */
    private int f12024ak = 0;

    /* renamed from: al */
    private boolean f12025al = false;

    /* renamed from: am */
    private boolean f12026am = false;

    /* renamed from: an */
    private boolean f12027an = false;

    /* renamed from: ao */
    private boolean f12028ao = false;

    /* renamed from: ap */
    private ProgressDialog f12029ap;

    /* renamed from: aq */
    private RelativeLayout f12030aq;

    /* renamed from: ar */
    private LinearLayout f12031ar;

    /* renamed from: as */
    private Button f12032as;

    /* renamed from: at */
    private Button f12033at;

    /* renamed from: au */
    private Button f12034au;

    /* renamed from: av */
    private Button f12035av;

    /* renamed from: aw */
    private LinearLayout f12036aw;

    /* renamed from: ax */
    private TextView f12037ax;

    /* renamed from: ay */
    private LinearLayout f12038ay;

    /* renamed from: az */
    private TextView f12039az;

    /* renamed from: bA */
    private int f12040bA = -1;

    /* renamed from: bB */
    private String f12041bB = "";

    /* renamed from: bC */
    private ArrayList<WiFiScanResult> f12042bC = new ArrayList<>();

    /* renamed from: bD */
    private ArrayList<WiFiScanResult> f12043bD = new ArrayList<>();

    /* renamed from: bE */
    private final int f12044bE = 0;

    /* renamed from: bF */
    private final int f12045bF = 1;

    /* renamed from: bG */
    private final int f12046bG = 2;

    /* renamed from: bH */
    private int f12047bH = 0;

    /* renamed from: bI */
    private String f12048bI = "";

    /* renamed from: bJ */
    private int f12049bJ = -1;

    /* renamed from: bK */
    private HashSet<String> f12050bK = new HashSet<>();

    /* renamed from: bL */
    private EnumC3247q f12051bL;

    /* renamed from: bM */
    private AbstractC3238h f12052bM;

    /* renamed from: bN */
    private AbstractC3237g f12053bN;

    /* renamed from: bO */
    private AbstractC3243m f12054bO;

    /* renamed from: bP */
    private AbstractC3240j f12055bP;

    /* renamed from: bQ */
    private AbstractC3242l f12056bQ;

    /* renamed from: bR */
    private AbstractC3241k f12057bR;

    /* renamed from: bS */
    private AbstractC3239i f12058bS;

    /* renamed from: bT */
    private AbstractC3232b f12059bT = null;

    /* renamed from: bU */
    private AbstractC3236f f12060bU = null;

    /* renamed from: bV */
    private AbstractC3233c f12061bV = null;

    /* renamed from: bW */
    private AbstractC3235e f12062bW = null;

    /* renamed from: bX */
    private AbstractC3234d f12063bX = null;

    /* renamed from: bY */
    private View.OnClickListener f12064bY = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC31781 */

        public void onClick(View view) {
            CacheAuth.getInstance().setIsActiveDownline(false);
            UmengCount.queryNearWifi(GBApplication.instance());
            BaseGiWiFiInfoView.this.m13072bk();
        }
    };

    /* renamed from: bZ */
    private View.OnClickListener f12065bZ = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC318112 */

        public void onClick(View view) {
            BaseGiWiFiInfoView.this.mo27177b("开启中...");
            BaseGiWiFiInfoView.this.mo27168at();
            BaseGiWiFiInfoView.this.f12109cr.removeMessages(9);
            BaseGiWiFiInfoView.this.f12109cr.sendEmptyMessageDelayed(9, AbstractTrafficShapingHandler.DEFAULT_MAX_TIME);
            WiFiUtil.m14022a(GBApplication.instance()).mo27604b();
        }
    };

    /* renamed from: ba */
    private TextView f12066ba;

    /* renamed from: bb */
    private TextView f12067bb;

    /* renamed from: bc */
    private TextView f12068bc;

    /* renamed from: bd */
    private TextView f12069bd;

    /* renamed from: be */
    private boolean f12070be = false;

    /* renamed from: bf */
    private int f12071bf = 1;

    /* renamed from: bg */
    private int f12072bg = 0;

    /* renamed from: bh */
    private ViewGroup f12073bh;

    /* renamed from: bi */
    private ViewGroup f12074bi;

    /* renamed from: bj */
    private Button f12075bj;

    /* renamed from: bk */
    private ImageView f12076bk;

    /* renamed from: bl */
    private ViewGroup f12077bl;

    /* renamed from: bm */
    private ImageView f12078bm;

    /* renamed from: bn */
    private WiFiScanResult f12079bn;

    /* renamed from: bo */
    private String f12080bo;

    /* renamed from: bp */
    private ArrayList<WiFiScanResult> f12081bp = new ArrayList<>();

    /* renamed from: bq */
    private ArrayList<WiFiScanResult> f12082bq = new ArrayList<>();

    /* renamed from: br */
    private String f12083br = "";

    /* renamed from: bs */
    private List<GameIpNetMask> f12084bs = new ArrayList();

    /* renamed from: bt */
    private final int f12085bt = 0;

    /* renamed from: bu */
    private final int f12086bu = 1;

    /* renamed from: bv */
    private final int f12087bv = 2;

    /* renamed from: bw */
    private final int f12088bw = 3;

    /* renamed from: bx */
    private final int f12089bx = 4;

    /* renamed from: by */
    private int f12090by = 0;

    /* renamed from: bz */
    private String f12091bz = "";

    /* renamed from: ca */
    private View.OnClickListener f12092ca = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC319323 */

        public void onClick(View view) {
            if (BaseGiWiFiInfoView.this.mo27161am().equals(BaseGiWiFiInfoView.this.f12119i)) {
                UmengCount.queryOffLine(GBApplication.instance());
                BaseGiWiFiInfoView.this.f11978O.removeMessages(4);
                BaseGiWiFiInfoView.this.f11978O.sendMessage(BaseGiWiFiInfoView.this.f11978O.obtainMessage(4));
                WifiInfo connectionInfo = BaseGiWiFiInfoView.this.f12016ac.getConnectionInfo();
                if (connectionInfo != null) {
                    String bssid = connectionInfo.getBSSID();
                    Log.m14400c(BaseGiWiFiInfoView.f11954g, "logout bssid:" + bssid);
                    if (CheckUtil.m14084b(bssid) && !BaseGiWiFiInfoView.this.f12020ag.contains(bssid.toLowerCase())) {
                        BaseGiWiFiInfoView.this.f12020ag.add(bssid.toLowerCase());
                    }
                }
            } else if (CacheAuth.getInstance().isPortal()) {
                CacheAuth.getInstance().setIsActiveDownline(false);
                CacheAuth.getInstance().resetAuthType();
                BaseGiWiFiInfoView.this.m13075bn();
            } else {
                UmengCount.queryOneKeyLine(GBApplication.instance());
                CacheAuth.getInstance().resetAuthType();
                Log.m14400c(BaseGiWiFiInfoView.f11954g, "oneKeyConnectWifiClickListener authWifi");
                BaseGiWiFiInfoView.this.m13075bn();
            }
        }
    };

    /* renamed from: cb */
    private View.OnClickListener f12093cb = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC320534 */

        public void onClick(View view) {
            if (System.currentTimeMillis() - BaseGiWiFiInfoView.this.f12021ah.longValue() >= 3000) {
                BaseGiWiFiInfoView.this.mo27183d(BaseGiWiFiInfoView.this.f11972I[7]);
                BaseGiWiFiInfoView.this.f12021ah = Long.valueOf(System.currentTimeMillis());
                BaseGiWiFiInfoView.this.m13081bt();
            }
        }
    };

    /* renamed from: cc */
    private View.OnClickListener f12094cc = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC321745 */

        public void onClick(View view) {
            UmengCount.queryConnectGwifi(GBApplication.instance());
            BaseGiWiFiInfoView.this.f12020ag.clear();
            if (BaseGiWiFiInfoView.this.m13071bj()) {
                if (!BaseGiWiFiInfoView.this.m13066be()) {
                    BaseGiWiFiInfoView.this.f11977N.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                }
            } else if (!BaseGiWiFiInfoView.this.m13077bp()) {
                BaseGiWiFiInfoView.this.m13072bk();
            }
        }
    };

    /* renamed from: cd */
    private View.OnTouchListener f12095cd = new View.OnTouchListener() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnTouchListenerC322350 */

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    BaseGiWiFiInfoView.this.f12023aj = (int) motionEvent.getX();
                    BaseGiWiFiInfoView.this.f12024ak = (int) motionEvent.getY();
                    return true;
                case 1:
                    int x = ((int) motionEvent.getX()) - BaseGiWiFiInfoView.this.f12023aj;
                    int y = ((int) motionEvent.getY()) - BaseGiWiFiInfoView.this.f12024ak;
                    if (Math.abs(x) >= Math.abs(y) || y >= 0 || Math.abs(y) <= 100 || BaseGiWiFiInfoView.this.f12063bX == null) {
                        return true;
                    }
                    BaseGiWiFiInfoView.this.f12063bX.mo27252a(view);
                    return true;
                default:
                    return true;
            }
        }
    };

    /* renamed from: ce */
    private View.OnClickListener f12096ce = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC32003 */

        public void onClick(View view) {
            WiFiScanResult wiFiScanResult;
            WiFiScanResult wiFiScanResult2;
            WiFiScanResult wiFiScanResult3;
            WiFiScanResult wiFiScanResult4;
            WiFiScanResult gameGameWifi;
            WiFiScanResult wiFiScanResult5;
            int i = 0;
            GameConfig gameConfig = CacheGame.getInstance().getGameConfig();
            if (gameConfig == null) {
                Log.m14403e(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: gameConfig is null");
                GBActivity.showMessageToast("当前环境不支持游戏专线");
            } else if (gameConfig.mo24523b() == 0) {
                Log.m14403e(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: gameConfig game disable");
                GBActivity.showMessageToast("当前环境不支持游戏专线");
            } else if (gameConfig.mo24529e().size() == 0) {
                Log.m14403e(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: gameConfig game filter list empty");
                GBActivity.showMessageToast("当前环境尚未配置游戏专线参数");
            } else {
                BaseGiWiFiInfoView.this.f12083br = gameConfig.mo24528d();
                String c = gameConfig.mo24526c();
                if (c.equals("")) {
                    Log.m14403e(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: gameConfig game ssid is empty");
                    GBActivity.showMessageToast("当前环境尚未配置游戏相关参数");
                    return;
                }
                String a = gameConfig.mo24518a();
                BaseGiWiFiInfoView.this.f12084bs = gameConfig.mo24530f();
                String scanWifiList = CacheWiFi.getInstance().getScanWifiList();
                if (C3467s.m14513e(scanWifiList)) {
                    Log.m14403e(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: gameConfig wifiList empty");
                    GBActivity.showMessageToast("当前无法获取WiFi列表，稍后尝试");
                } else if (!JsonUtil.m14389a(scanWifiList)) {
                    Log.m14403e(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: gameConfig wifiList data invalid");
                    GBActivity.showMessageToast("当前无法获取WiFi列表，稍后尝试");
                } else {
                    WiFiScanList wiFiScanList = (WiFiScanList) GsonUtil.m14241a().mo21588a(scanWifiList, WiFiScanList.class);
                    if (wiFiScanList == null) {
                        Log.m14403e(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: gameConfig wifiList data parse fail");
                        GBActivity.showMessageToast("当前无法获取WiFi列表，稍后尝试");
                        return;
                    }
                    ArrayList<WiFiScanResult> wifi2gList = wiFiScanList.getWifi2gList();
                    ArrayList<WiFiScanResult> wifi5gList = wiFiScanList.getWifi5gList();
                    WiFiScanResult wiFiScanResult6 = null;
                    BaseGiWiFiInfoView.this.f12081bp.clear();
                    BaseGiWiFiInfoView.this.f12082bq.clear();
                    if (BaseGiWiFiInfoView.this.f12071bf == 1) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= wifi5gList.size()) {
                                break;
                            }
                            String ssid = wifi5gList.get(i2).getSSID();
                            if (BaseGiWiFiInfoView.this.mo27187f(ssid) && ssid.indexOf(c) != -1) {
                                if (wiFiScanResult6 == null) {
                                    wiFiScanResult5 = wifi5gList.get(i2);
                                } else {
                                    wiFiScanResult5 = wiFiScanResult6;
                                }
                                BaseGiWiFiInfoView.this.f12081bp.add(wifi5gList.get(i2));
                                if (BaseGiWiFiInfoView.this.f12081bp.size() >= 5) {
                                    wiFiScanResult6 = wiFiScanResult5;
                                    break;
                                }
                                wiFiScanResult6 = wiFiScanResult5;
                            }
                            i2++;
                        }
                        WiFiScanResult wiFiScanResult7 = null;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= wifi2gList.size()) {
                                wiFiScanResult4 = wiFiScanResult7;
                                break;
                            }
                            String ssid2 = wifi2gList.get(i3).getSSID();
                            if (BaseGiWiFiInfoView.this.mo27187f(ssid2) && ssid2.indexOf(c) != -1) {
                                if (wiFiScanResult7 == null) {
                                    wiFiScanResult4 = wifi2gList.get(i3);
                                } else {
                                    wiFiScanResult4 = wiFiScanResult7;
                                }
                                BaseGiWiFiInfoView.this.f12082bq.add(wifi2gList.get(i3));
                                if (BaseGiWiFiInfoView.this.f12082bq.size() >= 5) {
                                    break;
                                }
                                wiFiScanResult7 = wiFiScanResult4;
                            }
                            i3++;
                        }
                        if (wiFiScanResult4 != null) {
                            if (wiFiScanResult6 == null) {
                                wiFiScanResult6 = wiFiScanResult4;
                            } else if (wiFiScanResult4.getLevel() > wiFiScanResult6.getLevel() + 20) {
                                wiFiScanResult6 = wiFiScanResult4;
                            }
                        }
                        if (wiFiScanResult6 == null && (gameGameWifi = CacheWiFi.getInstance().getGameGameWifi()) != null && BaseGiWiFiInfoView.this.mo27187f(gameGameWifi.getSSID()) && gameGameWifi.getSSID().indexOf(c) != -1) {
                            BaseGiWiFiInfoView.this.f12081bp.add(gameGameWifi);
                            Log.m14398b(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: choose cache " + gameGameWifi.getSSID());
                            wiFiScanResult6 = gameGameWifi;
                        }
                        if (wiFiScanResult6 == null) {
                            Log.m14403e(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: gameConfig wifiList choose fail");
                            WiFiService.m13974a().mo27590e();
                            BaseGiWiFiInfoView.this.m13111h((BaseGiWiFiInfoView) ("请手动切换到游戏专线:\nWiFi名称带'" + c + "'"));
                            return;
                        }
                        Log.m14398b(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: choose " + wiFiScanResult6.getSSID());
                        BaseGiWiFiInfoView.this.f12079bn = wiFiScanResult6;
                        CacheWiFi.getInstance().setGameGameWifi(wiFiScanResult6);
                        if (gameConfig.mo24529e().size() == 1) {
                            BaseGiWiFiInfoView.this.f12080bo = gameConfig.mo24529e().get(0).mo24533b();
                            if (Build.VERSION.SDK_INT >= 29) {
                                BaseGiWiFiInfoView.this.mo27098Z();
                                BaseGiWiFiInfoView.this.m13111h((BaseGiWiFiInfoView) ("请手动切换到游戏专线:\n" + BaseGiWiFiInfoView.this.m13069bh()));
                                return;
                            }
                            BaseGiWiFiInfoView.this.m13093c((BaseGiWiFiInfoView) wiFiScanResult6);
                            BaseGiWiFiInfoView.this.mo27119a(wiFiScanResult6);
                            return;
                        }
                        BaseGiWiFiInfoView.this.m13018a((BaseGiWiFiInfoView) gameConfig);
                        return;
                    }
                    int i4 = 0;
                    while (true) {
                        if (i4 >= wifi5gList.size()) {
                            break;
                        }
                        String ssid3 = wifi5gList.get(i4).getSSID();
                        if (BaseGiWiFiInfoView.this.mo27187f(ssid3) && ssid3.indexOf(a) != -1 && ssid3.indexOf(c) == -1) {
                            if (wiFiScanResult6 == null) {
                                wiFiScanResult3 = wifi5gList.get(i4);
                            } else {
                                wiFiScanResult3 = wiFiScanResult6;
                            }
                            BaseGiWiFiInfoView.this.f12081bp.add(wifi5gList.get(i4));
                            if (BaseGiWiFiInfoView.this.f12081bp.size() >= 5) {
                                wiFiScanResult6 = wiFiScanResult3;
                                break;
                            }
                            wiFiScanResult6 = wiFiScanResult3;
                        }
                        i4++;
                    }
                    WiFiScanResult wiFiScanResult8 = null;
                    while (true) {
                        if (i >= wifi2gList.size()) {
                            wiFiScanResult = wiFiScanResult8;
                            break;
                        }
                        String ssid4 = wifi2gList.get(i).getSSID();
                        if (BaseGiWiFiInfoView.this.mo27187f(ssid4) && ssid4.indexOf(a) != -1 && ssid4.indexOf(c) == -1) {
                            if (wiFiScanResult8 == null) {
                                wiFiScanResult = wifi2gList.get(i);
                            } else {
                                wiFiScanResult = wiFiScanResult8;
                            }
                            BaseGiWiFiInfoView.this.f12082bq.add(wifi2gList.get(i));
                            if (BaseGiWiFiInfoView.this.f12082bq.size() >= 5) {
                                break;
                            }
                            wiFiScanResult8 = wiFiScanResult;
                        }
                        i++;
                    }
                    if (wiFiScanResult != null) {
                        if (wiFiScanResult6 == null) {
                            wiFiScanResult6 = wiFiScanResult;
                        } else if (wiFiScanResult.getLevel() > wiFiScanResult6.getLevel() + 20) {
                            wiFiScanResult6 = wiFiScanResult;
                        }
                    }
                    if (wiFiScanResult6 != null || (wiFiScanResult2 = CacheWiFi.getInstance().getGameCommonWifi()) == null || !BaseGiWiFiInfoView.this.mo27187f(wiFiScanResult2.getSSID()) || wiFiScanResult2.getSSID().indexOf(a) == -1 || wiFiScanResult2.getSSID().indexOf(c) != -1) {
                        wiFiScanResult2 = wiFiScanResult6;
                    } else {
                        BaseGiWiFiInfoView.this.f12081bp.add(wiFiScanResult2);
                        Log.m14398b(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: choose cache " + wiFiScanResult2.getSSID());
                    }
                    if (wiFiScanResult2 == null) {
                        Log.m14403e(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: gameConfig wifiList choose fail");
                        WiFiService.m13974a().mo27590e();
                        BaseGiWiFiInfoView.this.m13111h((BaseGiWiFiInfoView) ("请手动切换到普通线路:\nWiFi名称带'" + a + "'"));
                        return;
                    }
                    Log.m14398b(BaseGiWiFiInfoView.f11954g, "gameModeSwitch: choose " + wiFiScanResult2.getSSID());
                    CacheWiFi.getInstance().setGameCommonWifi(wiFiScanResult2);
                    if (Build.VERSION.SDK_INT >= 29) {
                        BaseGiWiFiInfoView.this.mo27098Z();
                        BaseGiWiFiInfoView.this.m13111h((BaseGiWiFiInfoView) ("请手动切换到普通线路:\n" + BaseGiWiFiInfoView.this.m13069bh()));
                        return;
                    }
                    BaseGiWiFiInfoView.this.m13100d((BaseGiWiFiInfoView) wiFiScanResult2);
                    BaseGiWiFiInfoView.this.mo27119a(wiFiScanResult2);
                }
            }
        }
    };

    /* renamed from: cf */
    private View.OnClickListener f12097cf = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC32114 */

        public void onClick(View view) {
            if (!C3467s.m14513e(BaseGiWiFiInfoView.this.f12083br)) {
                GBActivity.openOfficeBackWebView(BaseGiWiFiInfoView.this.f12083br, "游戏专线使用说明");
            } else {
                GBActivity.showMessageToast("暂不支持此项业务!");
            }
        }
    };

    /* renamed from: cg */
    private View.OnClickListener f12098cg = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC32225 */

        public void onClick(View view) {
            String b = HttpUtil.m14300b();
            if (!C3467s.m14513e(b)) {
                GBActivity.openOfficeBackWebView(b, "使用帮助");
            } else {
                GBActivity.showMessageToast("暂不支持此项业务!");
            }
        }
    };

    /* renamed from: ch */
    private boolean f12099ch = false;

    /* renamed from: ci */
    private CallBack f12100ci = new CallBack() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C318617 */

        @Override // com.gbcom.gwifi.base.p234c.CallBack
        /* renamed from: a */
        public void mo24437a(Object obj) {
            BaseGiWiFiInfoView.this.m13057bG();
            if (!BaseGiWiFiInfoView.this.m13063bM()) {
                CacheWiFi.getInstance().setInnerWifi(false);
                BaseGiWiFiInfoView.this.mo27116a(WifiState.DISCONNECTED);
                return;
            }
            CheckResult checkResult = (CheckResult) obj;
            BaseGiWiFiInfoView.this.f12014aa = false;
            BaseGiWiFiInfoView.this.f12016ac.getConnectionInfo();
            CacheAuth.getInstance().setIsActiveDownline(true);
            GBApplication.instance().getCurrentActivity().dismissProgressDialog();
            if (checkResult != null) {
                CacheWiFi.getInstance().setInnerWifi(checkResult.isGiwifi());
                boolean isGiwifi = checkResult.isGiwifi();
                switch (checkResult.getOnlineState().intValue()) {
                    case 0:
                        if (!NetworkUtils.m14426a(GBApplication.instance())) {
                            GBActivity.showMessageToast("网络歇菜了,请稍后尝试");
                        }
                        BaseGiWiFiInfoView.this.mo27117a(WifiState.FAILED, isGiwifi);
                        return;
                    case 1:
                        BaseGiWiFiInfoView.this.mo27117a(WifiState.LOGIN, isGiwifi);
                        return;
                    case 2:
                        BaseGiWiFiInfoView.this.mo27117a(WifiState.SUCCESS, isGiwifi);
                        return;
                    case 200:
                        BaseGiWiFiInfoView.this.mo27117a(WifiState.SUCCESS_NONET, isGiwifi);
                        return;
                    default:
                        return;
                }
            }
        }
    };

    /* renamed from: cj */
    private CallBack f12101cj = new CallBack() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C318718 */

        @Override // com.gbcom.gwifi.base.p234c.CallBack
        /* renamed from: a */
        public void mo24437a(Object obj) {
            if (!BaseGiWiFiInfoView.this.m13063bM()) {
                BaseGiWiFiInfoView.this.mo27116a(WifiState.DISCONNECTED);
                CacheWiFi.getInstance().setInnerWifi(false);
                return;
            }
            CheckResult checkResult = (CheckResult) obj;
            CacheWiFi.getInstance().setInnerWifi(checkResult.isGiwifi());
            switch (checkResult.getOnlineState().intValue()) {
                case 0:
                    if (CacheWiFi.getInstance().getCurrentState() != WifiState.FAILED || CacheWiFi.getInstance().isInnerWifi() != checkResult.isGiwifi()) {
                        BaseGiWiFiInfoView.this.m13087bz();
                        BaseGiWiFiInfoView.this.mo27117a(WifiState.FAILED, checkResult.isGiwifi());
                        return;
                    }
                    return;
                case 1:
                    BaseGiWiFiInfoView.this.f11985V = 0;
                    if (!(CacheWiFi.getInstance().getCurrentState() == WifiState.LOGIN && CacheWiFi.getInstance().isInnerWifi() == checkResult.isGiwifi())) {
                        BaseGiWiFiInfoView.this.m13020a((BaseGiWiFiInfoView) EnumC3247q.OneKeyShow);
                        if (CacheWiFi.getInstance().getCurrentState() == WifiState.SUCCESS) {
                            BaseGiWiFiInfoView.this.mo27183d(BaseGiWiFiInfoView.this.f11972I[8]);
                            BaseGiWiFiInfoView.this.mo27120a(BaseGiWiFiInfoView.this.f12120j);
                            BaseGiWiFiInfoView.this.m13044b((BaseGiWiFiInfoView) EnumC3246p.fiveShow);
                            CacheWiFi.getInstance().setCurrentState(WifiState.LOGIN);
                            BaseGiWiFiInfoView.this.m13094c((BaseGiWiFiInfoView) checkResult.isGiwifi());
                        } else {
                            BaseGiWiFiInfoView.this.m13087bz();
                            BaseGiWiFiInfoView.this.mo27183d(BaseGiWiFiInfoView.this.f11972I[8]);
                            BaseGiWiFiInfoView.this.mo27120a(BaseGiWiFiInfoView.this.f12120j);
                            BaseGiWiFiInfoView.this.m13044b((BaseGiWiFiInfoView) EnumC3246p.fiveShow);
                            CacheWiFi.getInstance().setCurrentState(WifiState.LOGIN);
                        }
                    }
                    if (checkResult.getRelease()) {
                        if (((CacheAuth.getInstance().getLastReleaseTime() / 1000) + 150) - (System.currentTimeMillis() / 1000) > 0) {
                            BaseGiWiFiInfoView.this.m13020a((BaseGiWiFiInfoView) EnumC3247q.RELEASE);
                            BaseGiWiFiInfoView.this.mo27183d(BaseGiWiFiInfoView.this.f11972I[15]);
                            BaseGiWiFiInfoView.this.mo27120a(BaseGiWiFiInfoView.this.f12120j);
                            CacheWiFi.getInstance().setCurrentState(WifiState.LOGIN);
                            BaseGiWiFiInfoView.this.mo27121a("00:00:00", false);
                            BaseGiWiFiInfoView.this.m13044b((BaseGiWiFiInfoView) EnumC3246p.sevenShow);
                            if (BaseGiWiFiInfoView.this.f11978O != null) {
                                BaseGiWiFiInfoView.this.f11978O.removeMessages(11);
                                BaseGiWiFiInfoView.this.f11978O.sendMessageDelayed(BaseGiWiFiInfoView.this.f11978O.obtainMessage(11), AbstractTrafficShapingHandler.DEFAULT_MAX_TIME);
                                return;
                            }
                            return;
                        }
                        BaseGiWiFiInfoView.this.m13020a((BaseGiWiFiInfoView) EnumC3247q.OneKeyShow);
                        BaseGiWiFiInfoView.this.mo27183d(BaseGiWiFiInfoView.this.f11972I[8]);
                        BaseGiWiFiInfoView.this.mo27120a(BaseGiWiFiInfoView.this.f12120j);
                        BaseGiWiFiInfoView.this.m13044b((BaseGiWiFiInfoView) EnumC3246p.fiveShow);
                        return;
                    }
                    return;
                case 2:
                    BaseGiWiFiInfoView.this.f11985V = 0;
                    if (!(CacheWiFi.getInstance().getCurrentState() == WifiState.SUCCESS && CacheWiFi.getInstance().isInnerWifi() == checkResult.isGiwifi())) {
                        BaseGiWiFiInfoView.this.mo27117a(WifiState.SUCCESS, checkResult.isGiwifi());
                    }
                    if (SystemUtil.m14521a() && checkResult.isGiwifi()) {
                        BaseGiWiFiInfoView.this.mo27120a(BaseGiWiFiInfoView.this.f12119i);
                        BaseGiWiFiInfoView.this.mo27160al();
                        BaseGiWiFiInfoView.this.mo27158aj();
                        return;
                    }
                    return;
                case 200:
                    BaseGiWiFiInfoView.this.f11985V = 0;
                    if (!(CacheWiFi.getInstance().getCurrentState() == WifiState.SUCCESS_NONET && CacheWiFi.getInstance().isInnerWifi() == checkResult.isGiwifi())) {
                        BaseGiWiFiInfoView.this.mo27117a(WifiState.SUCCESS_NONET, checkResult.isGiwifi());
                    }
                    if (SystemUtil.m14521a() && checkResult.isGiwifi()) {
                        BaseGiWiFiInfoView.this.mo27120a(BaseGiWiFiInfoView.this.f12119i);
                        BaseGiWiFiInfoView.this.mo27160al();
                        BaseGiWiFiInfoView.this.mo27158aj();
                        return;
                    }
                    return;
                case 253:
                    BaseGiWiFiInfoView.this.m13020a((BaseGiWiFiInfoView) EnumC3247q.RELEASE);
                    BaseGiWiFiInfoView.this.mo27183d(BaseGiWiFiInfoView.this.f11972I[15]);
                    BaseGiWiFiInfoView.this.mo27120a(BaseGiWiFiInfoView.this.f12120j);
                    CacheWiFi.getInstance().setCurrentState(WifiState.LOGIN);
                    BaseGiWiFiInfoView.this.mo27121a("00:00:00", false);
                    if (((CacheAuth.getInstance().getLastReleaseTime() / 1000) + 150) - (System.currentTimeMillis() / 1000) > 0) {
                        if (BaseGiWiFiInfoView.this.f11978O != null) {
                            BaseGiWiFiInfoView.this.f11978O.removeMessages(11);
                            BaseGiWiFiInfoView.this.f11978O.sendMessageDelayed(BaseGiWiFiInfoView.this.f11978O.obtainMessage(11), 0);
                            return;
                        }
                        return;
                    } else if (BaseGiWiFiInfoView.this.f11978O != null) {
                        BaseGiWiFiInfoView.this.f11978O.removeMessages(11);
                        BaseGiWiFiInfoView.this.f11978O.sendMessageDelayed(BaseGiWiFiInfoView.this.f11978O.obtainMessage(11), 0);
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };

    /* renamed from: ck */
    private Gson f12102ck;

    /* renamed from: cl */
    private BroadcastReceiver f12103cl = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C320837 */

        public void onReceive(Context context, Intent intent) {
            BaseGiWiFiInfoView.this.mo27173ay();
            if (!SystemUtil.m14531e()) {
                BaseGiWiFiInfoView.this.m13070bi();
            }
            GBGlobalConfig.m10510c().mo24417q();
        }
    };

    /* renamed from: cm */
    private BroadcastReceiver f12104cm = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C320938 */

        public void onReceive(Context context, Intent intent) {
            BaseGiWiFiInfoView.this.mo27101a(0L);
        }
    };

    /* renamed from: cn */
    private BroadcastReceiver f12105cn = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C321039 */

        public void onReceive(Context context, Intent intent) {
            Log.m14400c(BaseGiWiFiInfoView.f11954g, "webViewAuthNetworkReceiver authWifi");
            BaseGiWiFiInfoView.this.m13075bn();
        }
    };

    /* renamed from: co */
    private BroadcastReceiver f12106co = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C321240 */

        public void onReceive(Context context, Intent intent) {
            Log.m14398b(BaseGiWiFiInfoView.f11954g, "gameConfig get");
            GBGlobalConfig.m10510c().mo24417q();
        }
    };

    /* renamed from: cp */
    private int f12107cp = 0;

    /* renamed from: cq */
    private BroadcastReceiver f12108cq = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C321341 */

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
                    NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                    NetworkInfo.DetailedState detailedState = networkInfo.getDetailedState();
                    Log.m14400c(BaseGiWiFiInfoView.f11954g, "detailedState:" + ((Object) detailedState));
                    BaseGiWiFiInfoView.this.m13087bz();
                    switch (C322149.f12179b[detailedState.ordinal()]) {
                        case 1:
                            BaseGiWiFiInfoView.this.mo27183d("正在身份验证...");
                            return;
                        case 2:
                            WifiInfo connectionInfo = BaseGiWiFiInfoView.this.f12016ac.getConnectionInfo();
                            if (connectionInfo != null) {
                                String j = WiFiUtil.m14021a().mo27616j();
                                String trim = WiFiUtil.m14035e(GBApplication.instance().getApplicationContext()).trim();
                                String a = BaseGiWiFiInfoView.this.m13011a((BaseGiWiFiInfoView) connectionInfo, (WifiInfo) networkInfo);
                                if (BaseGiWiFiInfoView.this.mo27187f(a)) {
                                    CacheWiFi.getInstance().setCurrentSsid(a);
                                    BaseGiWiFiInfoView.this.m13128o((BaseGiWiFiInfoView) a);
                                    BaseGiWiFiInfoView.this.m13130p((BaseGiWiFiInfoView) a);
                                } else {
                                    CacheWiFi.getInstance().setCurrentSsid("");
                                    if (!GBActivity.checkPermissions(context, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})) {
                                        Log.m14398b(BaseGiWiFiInfoView.f11954g, "ACCESS_FINE_LOCATION not granted");
                                    } else if (BaseGiWiFiInfoView.this.f12109cr != null) {
                                        BaseGiWiFiInfoView.this.f12107cp = 0;
                                        BaseGiWiFiInfoView.this.f12109cr.removeMessages(34);
                                        BaseGiWiFiInfoView.this.f12109cr.sendEmptyMessageDelayed(34, 1000);
                                    }
                                }
                                String bssid = connectionInfo.getBSSID();
                                if (!CheckUtil.m14084b(bssid) || bssid.equals("00:00:00:00:00:00")) {
                                    CacheWiFi.getInstance().setCurrentBssid("");
                                } else {
                                    CacheWiFi.getInstance().setCurrentBssid(bssid);
                                }
                                Log.m14398b(BaseGiWiFiInfoView.f11954g, "wifiChange CONNECTED ssid=" + a + ",bssid=" + bssid);
                                Log.m14398b(BaseGiWiFiInfoView.f11954g, "terminal ip=" + j + ",mac=" + trim);
                                if (CheckUtil.m14084b(trim) && !trim.equals("00:00:00:00:00:00") && !trim.equals(WifiInfo.DEFAULT_MAC_ADDRESS)) {
                                    String localMac = CacheAuth.getInstance().getLocalMac();
                                    if (localMac.equals("")) {
                                        Log.m14398b(BaseGiWiFiInfoView.f11954g, "wifiChange CONNECTED empty=>setLocalMac");
                                        CacheAuth.getInstance().setLocalMac(trim);
                                    } else if (!trim.toUpperCase().equals(localMac)) {
                                        Log.m14398b(BaseGiWiFiInfoView.f11954g, "wifiChange CONNECTED change=>setLocalMac");
                                        GBGlobalConfig.f8851b = true;
                                        CacheAuth.getInstance().setLocalMac(trim);
                                    }
                                } else if (GBGlobalConfig.f8851b || !CheckUtil.m14084b(trim) || trim.equals("00:00:00:00:00:00") || trim.equals(WifiInfo.DEFAULT_MAC_ADDRESS)) {
                                    Log.m14398b(BaseGiWiFiInfoView.f11954g, "wifiChange CONNECTED clearLocalMac");
                                    CacheAuth.getInstance().clearLocalMac();
                                }
                                if (a == null || !a.equals(BaseGiWiFiInfoView.this.f11975L[0])) {
                                    BaseGiWiFiInfoView.this.m13028a((BaseGiWiFiInfoView) a, bssid);
                                } else {
                                    BaseGiWiFiInfoView.this.m13132q((BaseGiWiFiInfoView) bssid);
                                }
                            }
                            BaseGiWiFiInfoView.this.mo27116a(WifiState.CONNECTED);
                            return;
                        case 3:
                            BaseGiWiFiInfoView.this.f12015ab = true;
                            BaseGiWiFiInfoView.this.mo27183d("正在建立连接...");
                            WifiInfo connectionInfo2 = BaseGiWiFiInfoView.this.f12016ac.getConnectionInfo();
                            if (connectionInfo2 != null) {
                                String a2 = BaseGiWiFiInfoView.this.m13011a((BaseGiWiFiInfoView) connectionInfo2, (WifiInfo) networkInfo);
                                Log.m14398b(BaseGiWiFiInfoView.f11954g, "wifiChange CONNECTING ssid=" + a2);
                                if (a2 == null || !a2.equals(BaseGiWiFiInfoView.this.f11975L[0])) {
                                    BaseGiWiFiInfoView.this.m13028a((BaseGiWiFiInfoView) a2, connectionInfo2.getBSSID());
                                    return;
                                } else {
                                    BaseGiWiFiInfoView.this.m13132q((BaseGiWiFiInfoView) connectionInfo2.getBSSID());
                                    return;
                                }
                            } else {
                                return;
                            }
                        case 4:
                            BaseGiWiFiInfoView.this.mo27116a(WifiState.DISCONNECTED);
                            return;
                        case 5:
                            BaseGiWiFiInfoView.this.mo27183d("正在停止连接...");
                            return;
                        case 6:
                            BaseGiWiFiInfoView.this.mo27183d("尝试连接失败");
                            return;
                        case 7:
                            BaseGiWiFiInfoView.this.mo27183d("准备数据连接...");
                            return;
                        case 8:
                            BaseGiWiFiInfoView.this.f12015ab = true;
                            WifiInfo connectionInfo3 = BaseGiWiFiInfoView.this.f12016ac.getConnectionInfo();
                            if (connectionInfo3 != null) {
                                String a3 = BaseGiWiFiInfoView.this.m13011a((BaseGiWiFiInfoView) connectionInfo3, (WifiInfo) networkInfo);
                                Log.m14398b(BaseGiWiFiInfoView.f11954g, "wifiChange OBTAINING_IPADDR ssid=" + a3);
                                if (a3 == null || !a3.equals(BaseGiWiFiInfoView.this.f11975L[0])) {
                                    BaseGiWiFiInfoView.this.m13028a((BaseGiWiFiInfoView) a3, connectionInfo3.getBSSID());
                                } else {
                                    BaseGiWiFiInfoView.this.m13132q((BaseGiWiFiInfoView) connectionInfo3.getBSSID());
                                }
                            }
                            BaseGiWiFiInfoView.this.mo27183d("正在获取ip地址...");
                            return;
                        case 9:
                            BaseGiWiFiInfoView.this.mo27183d("连接已停用");
                            return;
                        default:
                            return;
                    }
                } else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                    int intExtra = intent.getIntExtra("wifi_state", 1);
                    int intExtra2 = intent.getIntExtra("previous_wifi_state", 1);
                    switch (intExtra) {
                        case 0:
                            if (intExtra2 != 1) {
                                BaseGiWiFiInfoView.this.mo27116a(WifiState.CLOSING);
                                return;
                            }
                            return;
                        case 1:
                            if (intExtra2 != 1) {
                                BaseGiWiFiInfoView.this.mo27116a(WifiState.CLOSED);
                                return;
                            }
                            return;
                        case 2:
                            if (intExtra2 == 1 || intExtra2 == 0) {
                                BaseGiWiFiInfoView.this.mo27116a(WifiState.OPENING);
                                return;
                            }
                            return;
                        case 3:
                            if (intExtra2 != 3) {
                                BaseGiWiFiInfoView.this.mo27116a(WifiState.OPENED);
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                } else if (intent.getAction().equals(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)) {
                    SupplicantState supplicantState = (SupplicantState) intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
                    WiFiService.m13974a().mo27574a(supplicantState);
                    Log.m14400c(BaseGiWiFiInfoView.f11954g, "supplicantState:" + ((Object) supplicantState));
                    if (intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, -1) == 1) {
                        BaseGiWiFiInfoView.this.mo27116a(WifiState.AUTH_FAILED);
                    } else {
                        if (supplicantState == SupplicantState.INACTIVE) {
                        }
                    }
                } else if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    boolean p = WiFiUtil.m14022a(GBApplication.instance()).mo27622p();
                    boolean bM = BaseGiWiFiInfoView.this.m13063bM();
                    if (p && !bM) {
                        BaseGiWiFiInfoView.this.m13052bB();
                        CacheWiFi.getInstance().setCurrentBssid("");
                        CacheAuth.getInstance().resetCacheAuthBean();
                        GBApplication.instance().sendBroadcast(new Intent(Constant.f13238bM));
                    } else if (!WiFiUtil.m14022a(GBApplication.instance()).mo27620n()) {
                        BaseGiWiFiInfoView.this.m13052bB();
                    }
                } else {
                    if (intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION)) {
                    }
                }
            }
        }
    };

    /* renamed from: cr */
    private Handler f12109cr = new Handler() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.HandlerC321442 */

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        public void handleMessage(Message message) {
            switch (message.what) {
                case 6:
                    if (BaseGiWiFiInfoView.this.f12027an) {
                        DecimalFormat decimalFormat = new DecimalFormat("00");
                        long currentTimeMillis = System.currentTimeMillis() - BaseGiWiFiInfoView.this.f12022ai;
                        long j = currentTimeMillis / RelativeDateTimeFormatter.HOUR_IN_MILLIS;
                        BaseGiWiFiInfoView.this.mo27121a(decimalFormat.format(j) + ":" + decimalFormat.format((currentTimeMillis - ((3600 * j) * 1000)) / RelativeDateTimeFormatter.MINUTE_IN_MILLIS) + ":" + decimalFormat.format((currentTimeMillis % RelativeDateTimeFormatter.MINUTE_IN_MILLIS) / 1000), false);
                        BaseGiWiFiInfoView.this.f12109cr.removeMessages(7);
                        BaseGiWiFiInfoView.this.f12109cr.removeMessages(6);
                        BaseGiWiFiInfoView.this.f12109cr.sendEmptyMessageDelayed(6, 1000);
                        return;
                    }
                    BaseGiWiFiInfoView.this.mo27121a("00:00:00", false);
                    return;
                case 7:
                    if (BaseGiWiFiInfoView.this.f12028ao) {
                        DecimalFormat decimalFormat2 = new DecimalFormat("00");
                        long currentTimeMillis2 = System.currentTimeMillis() - BaseGiWiFiInfoView.this.f12022ai;
                        long j2 = currentTimeMillis2 / RelativeDateTimeFormatter.HOUR_IN_MILLIS;
                        BaseGiWiFiInfoView.this.mo27121a(decimalFormat2.format(j2) + ":" + decimalFormat2.format((currentTimeMillis2 - ((3600 * j2) * 1000)) / RelativeDateTimeFormatter.MINUTE_IN_MILLIS) + ":" + decimalFormat2.format((currentTimeMillis2 % RelativeDateTimeFormatter.MINUTE_IN_MILLIS) / 1000), true);
                        BaseGiWiFiInfoView.this.f12109cr.removeMessages(6);
                        BaseGiWiFiInfoView.this.f12109cr.removeMessages(7);
                        BaseGiWiFiInfoView.this.f12109cr.sendEmptyMessageDelayed(7, 1000);
                        return;
                    }
                    BaseGiWiFiInfoView.this.mo27121a("00:00:00", true);
                    return;
                case 9:
                    if (!BaseGiWiFiInfoView.this.f12016ac.isWifiEnabled()) {
                        GBActivity.showMessageToast("WiFi启动失败，请手动启动WiFi");
                        BaseGiWiFiInfoView.this.m13079br();
                        BaseGiWiFiInfoView.this.m13052bB();
                        return;
                    }
                    return;
                case 12:
                    if (BaseGiWiFiInfoView.this.mo27161am().equals(BaseGiWiFiInfoView.this.f12119i)) {
                        UmengCount.queryOffLine(GBApplication.instance());
                        GBApplication.instance().getCurrentActivity().showProgressDialog("正在下线...");
                        BaseGiWiFiInfoView.this.f11978O.removeMessages(4);
                        BaseGiWiFiInfoView.this.f11978O.sendMessage(BaseGiWiFiInfoView.this.f11978O.obtainMessage(4));
                        WifiInfo connectionInfo = BaseGiWiFiInfoView.this.f12016ac.getConnectionInfo();
                        if (connectionInfo != null) {
                            String bssid = connectionInfo.getBSSID();
                            Log.m14400c(BaseGiWiFiInfoView.f11954g, "logout bssid:" + bssid);
                            if (CheckUtil.m14084b(bssid) && !BaseGiWiFiInfoView.this.f12020ag.contains(bssid.toLowerCase())) {
                                BaseGiWiFiInfoView.this.f12020ag.add(bssid.toLowerCase());
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    UmengCount.queryOneKeyLine(GBApplication.instance());
                    CacheAuth.getInstance().resetAuthType();
                    Log.m14400c(BaseGiWiFiInfoView.f11954g, "ONE_KEY_CONNECT msg authWifi");
                    BaseGiWiFiInfoView.this.m13075bn();
                    return;
                case 13:
                case 17:
                case 30:
                case 31:
                default:
                    return;
                case 14:
                    if (BaseGiWiFiInfoView.this.f12015ab) {
                    }
                    break;
                case 15:
                    break;
                case 34:
                    WifiInfo connectionInfo2 = BaseGiWiFiInfoView.this.f12016ac.getConnectionInfo();
                    if (connectionInfo2 != null) {
                        String a = BaseGiWiFiInfoView.this.m13011a((BaseGiWiFiInfoView) connectionInfo2, (WifiInfo) BaseGiWiFiInfoView.this.f12017ad.getActiveNetworkInfo());
                        Log.m14398b(BaseGiWiFiInfoView.f11954g, "WIFI_SSID_GET " + BaseGiWiFiInfoView.this.f12107cp + " ssid=" + a);
                        String e = BaseGiWiFiInfoView.this.mo27185e(a);
                        if (BaseGiWiFiInfoView.this.mo27187f(e)) {
                            BaseGiWiFiInfoView.this.f12107cp = 0;
                            CacheWiFi.getInstance().setCurrentSsid(e);
                            String bssid2 = connectionInfo2.getBSSID();
                            if (!CheckUtil.m14084b(bssid2) || bssid2.equals("00:00:00:00:00:00")) {
                                CacheWiFi.getInstance().setCurrentBssid("");
                            } else {
                                CacheWiFi.getInstance().setCurrentBssid(bssid2);
                            }
                            if (e == null || !e.equals(BaseGiWiFiInfoView.this.f11975L[0])) {
                                BaseGiWiFiInfoView.this.m13028a((BaseGiWiFiInfoView) e, bssid2);
                            } else {
                                BaseGiWiFiInfoView.this.m13132q((BaseGiWiFiInfoView) bssid2);
                            }
                            if (BaseGiWiFiInfoView.this.f12109cr != null) {
                                BaseGiWiFiInfoView.this.f12109cr.removeMessages(40);
                                BaseGiWiFiInfoView.this.f12109cr.sendEmptyMessageDelayed(40, 100);
                                return;
                            }
                            return;
                        }
                        BaseGiWiFiInfoView.m13037af(BaseGiWiFiInfoView.this);
                        if (BaseGiWiFiInfoView.this.f12107cp < 4 && BaseGiWiFiInfoView.this.f12109cr != null) {
                            BaseGiWiFiInfoView.this.f12109cr.removeMessages(34);
                            BaseGiWiFiInfoView.this.f12109cr.sendEmptyMessageDelayed(34, 1000);
                            return;
                        }
                        return;
                    }
                    return;
                case 40:
                    Log.m14398b(BaseGiWiFiInfoView.f11954g, "recvMessages CHECK_GAME_CONTROL");
                    BaseGiWiFiInfoView.this.mo27178ba();
                    return;
                case 41:
                    BaseGiWiFiInfoView.this.f11987a.lock();
                    try {
                        if (BaseGiWiFiInfoView.this.f12090by == 1) {
                            BaseGiWiFiInfoView.this.m13111h((BaseGiWiFiInfoView) ("切换到游戏专线超时，可手动连接" + BaseGiWiFiInfoView.this.f12091bz));
                        }
                        return;
                    } finally {
                        BaseGiWiFiInfoView.this.f11987a.unlock();
                    }
                case 42:
                    BaseGiWiFiInfoView.this.f11987a.lock();
                    try {
                        if (BaseGiWiFiInfoView.this.f12090by == 2) {
                            BaseGiWiFiInfoView.this.m13111h((BaseGiWiFiInfoView) ("切换到普通线路超时，可手动连接" + BaseGiWiFiInfoView.this.f12091bz));
                        }
                        return;
                    } finally {
                        BaseGiWiFiInfoView.this.f11987a.unlock();
                    }
                case 43:
                    if (Build.VERSION.SDK_INT >= 29) {
                        Log.m14398b(BaseGiWiFiInfoView.f11954g, "recv GAME_SWITCH_ACCE_ACK");
                        String currentSsid = CacheWiFi.getInstance().getCurrentSsid();
                        Log.m14398b(BaseGiWiFiInfoView.f11954g, "currenySsid=" + currentSsid);
                        if (C3467s.m14513e(currentSsid) || !currentSsid.equals(BaseGiWiFiInfoView.this.f12091bz)) {
                            BaseGiWiFiInfoView.this.m13111h((BaseGiWiFiInfoView) ("切换到游戏专线失败，可手动连接" + BaseGiWiFiInfoView.this.f12091bz));
                            return;
                        } else {
                            GBActivity.showMessageLongToast("切换到游戏专线成功，如无法上网可手动认证");
                            return;
                        }
                    } else {
                        return;
                    }
                case 44:
                    if (Build.VERSION.SDK_INT >= 29) {
                        String currentSsid2 = CacheWiFi.getInstance().getCurrentSsid();
                        if (C3467s.m14513e(currentSsid2) || !currentSsid2.equals(BaseGiWiFiInfoView.this.f12091bz)) {
                            BaseGiWiFiInfoView.this.m13111h((BaseGiWiFiInfoView) ("切换到普通线路失败，可手动连接" + BaseGiWiFiInfoView.this.f12091bz));
                            return;
                        } else {
                            GBActivity.showMessageLongToast("切换到普通线路成功，如无法上网可手动认证");
                            return;
                        }
                    } else {
                        return;
                    }
                case 50:
                    BaseGiWiFiInfoView.this.f12116d.lock();
                    try {
                        if (BaseGiWiFiInfoView.this.f12047bH == 1) {
                            BaseGiWiFiInfoView.this.m13111h((BaseGiWiFiInfoView) ("切换到GiWiFi网络超时，可手动连接" + BaseGiWiFiInfoView.this.f12048bI));
                        }
                        return;
                    } finally {
                        BaseGiWiFiInfoView.this.f12116d.unlock();
                    }
                case 51:
                    if (Build.VERSION.SDK_INT >= 29) {
                        String currentSsid3 = CacheWiFi.getInstance().getCurrentSsid();
                        if (C3467s.m14513e(currentSsid3) || !currentSsid3.equals(BaseGiWiFiInfoView.this.f12048bI)) {
                            BaseGiWiFiInfoView.this.m13111h((BaseGiWiFiInfoView) ("切换到GiWiFi网络失败，可手动连接" + BaseGiWiFiInfoView.this.f12048bI));
                            return;
                        } else {
                            GBActivity.showMessageLongToast("切换到GiWiFi网络成功，如无法上网可手动认证");
                            return;
                        }
                    } else {
                        return;
                    }
                case 60:
                    if (BaseGiWiFiInfoView.f11952b == null) {
                        BaseGiWiFiInfoView.f11952b = Toast.makeText(GBApplication.instance(), (String) message.obj, 1);
                    } else {
                        BaseGiWiFiInfoView.f11952b.setText((String) message.obj);
                        BaseGiWiFiInfoView.f11952b.setDuration(1);
                    }
                    BaseGiWiFiInfoView.f11952b.setGravity(17, 0, 0);
                    BaseGiWiFiInfoView.f11952b.show();
                    return;
            }
            BaseGiWiFiInfoView.this.m13051bA();
        }
    };

    /* renamed from: cs */
    private CallBack f12110cs = new CallBack() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C321543 */

        @Override // com.gbcom.gwifi.base.p234c.CallBack
        /* renamed from: a */
        public void mo24437a(Object obj) {
            GBActivity currentActivity;
            Log.m14400c(BaseGiWiFiInfoView.f11954g, "LoginCallBack:call(" + obj.toString() + MessageStore.f23536t);
            BaseGiWiFiInfoView.this.m13074bm();
            if (obj != null) {
                if (obj instanceof Boolean) {
                    if (((Boolean) obj).booleanValue()) {
                        BaseGiWiFiInfoView.this.mo27117a(WifiState.SUCCESS, true);
                        BaseGiWiFiInfoView.this.f12109cr.removeMessages(31);
                        BaseGiWiFiInfoView.this.f12109cr.sendEmptyMessage(31);
                        return;
                    }
                    BaseGiWiFiInfoView.this.mo27120a(BaseGiWiFiInfoView.this.f12120j);
                    if (Config.m14094a().mo27812o() && (currentActivity = GBApplication.instance().getCurrentActivity()) != null && (currentActivity instanceof MainActivity)) {
                        ((MainActivity) currentActivity).showWiFiView();
                    }
                } else if ((obj instanceof Integer) && ((Integer) obj).intValue() == 200) {
                    BaseGiWiFiInfoView.this.mo27117a(WifiState.SUCCESS_NONET, true);
                }
            }
        }
    };

    /* renamed from: ct */
    private BroadcastReceiver f12111ct = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C321644 */

        public void onReceive(Context context, Intent intent) {
            Log.m14400c(BaseGiWiFiInfoView.f11954g, "accountActivateReceiver authWifi");
            BaseGiWiFiInfoView.this.m13075bn();
        }
    };

    /* renamed from: cu */
    private BroadcastReceiver f12112cu = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C321846 */

        public void onReceive(Context context, Intent intent) {
            BaseGiWiFiInfoView.this.mo27154af();
            BaseGiWiFiInfoView.this.mo27159ak();
        }
    };

    /* renamed from: cv */
    private BroadcastReceiver f12113cv = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C321947 */

        public void onReceive(Context context, Intent intent) {
            BaseGiWiFiInfoView.this.mo27153ae();
            BaseGiWiFiInfoView.this.mo27158aj();
        }
    };

    /* renamed from: cw */
    private BroadcastReceiver f12114cw = new CheckNetWorkReceiver();

    /* renamed from: cx */
    private BroadcastReceiver f12115cx = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C322048 */

        public void onReceive(Context context, Intent intent) {
            BaseGiWiFiInfoView.this.mo27179bb();
        }
    };

    /* renamed from: d */
    public Lock f12116d;

    /* renamed from: e */
    Handler f12117e = new Handler() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.HandlerC319121 */

        public void handleMessage(Message message) {
            ImageView imageView = (ImageView) message.obj;
            if (BaseGiWiFiInfoView.this.f11986W) {
                imageView.setImageResource(C2425R.C2426drawable.eye_1);
            } else {
                imageView.setImageResource(C2425R.C2426drawable.eye_2);
            }
        }
    };

    /* renamed from: f */
    OkRequestHandler<String> f12118f = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C320635 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (abVar == BaseGiWiFiInfoView.this.f11980Q) {
                C3470v.m14563a(BaseGiWiFiInfoView.this.f11977N, "当前网络故障");
                BaseGiWiFiInfoView.this.f11978O.removeMessages(100);
            }
            if (abVar == BaseGiWiFiInfoView.this.f11983T) {
                C3470v.m14563a(BaseGiWiFiInfoView.this.f11977N, "当前网络故障");
                BaseGiWiFiInfoView.this.f11978O.removeMessages(100);
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == BaseGiWiFiInfoView.this.f11980Q) {
                BaseGiWiFiInfoView.this.m13117j((BaseGiWiFiInfoView) str);
            } else if (abVar == BaseGiWiFiInfoView.this.f11981R) {
                BaseGiWiFiInfoView.this.m13119k((BaseGiWiFiInfoView) str);
            } else if (abVar == BaseGiWiFiInfoView.this.f11982S) {
                BaseGiWiFiInfoView.this.m13121l((BaseGiWiFiInfoView) str);
            } else if (abVar == BaseGiWiFiInfoView.this.f11983T) {
                BaseGiWiFiInfoView.this.m13124m((BaseGiWiFiInfoView) str);
            } else if (abVar == BaseGiWiFiInfoView.this.f11979P) {
                BaseGiWiFiInfoView.this.m13125n((BaseGiWiFiInfoView) str);
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: i */
    private String f12119i = " 下 线 ";

    /* renamed from: j */
    private String f12120j = "认证上网";

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$b */
    public interface AbstractC3232b {
        /* renamed from: a */
        void mo27250a(View view);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$c */
    public interface AbstractC3233c {
        /* renamed from: a */
        void mo27251a(View view);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$d */
    public interface AbstractC3234d {
        /* renamed from: a */
        void mo27252a(View view);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$e */
    public interface AbstractC3235e {
        /* renamed from: a */
        void mo27253a(View view);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$f */
    public interface AbstractC3236f {
        /* renamed from: a */
        void mo27254a(View view);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$g */
    public interface AbstractC3237g {
        /* renamed from: a */
        void mo24475a(String str);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$h */
    public interface AbstractC3238h {
        /* renamed from: a */
        void mo25005a();

        /* renamed from: a */
        void mo25006a(View view);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$i */
    public interface AbstractC3239i {
        /* renamed from: a */
        void mo24476a(EnumC3246p pVar);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$j */
    public interface AbstractC3240j {
        /* renamed from: a */
        void mo27255a(EnumC3246p pVar);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$k */
    public interface AbstractC3241k {
        /* renamed from: a */
        void mo27256a(EnumC3246p pVar);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$l */
    public interface AbstractC3242l {
        /* renamed from: a */
        void mo27257a(EnumC3246p pVar);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$m */
    public interface AbstractC3243m {
        /* renamed from: a */
        void mo24474a(String str);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$q */
    public enum EnumC3247q {
        wifiBtnShow,
        OneKeyShow,
        GwifiShow,
        CheckNetwork,
        RELEASE
    }

    /* access modifiers changed from: protected */
    /* renamed from: A */
    public abstract ImageView mo27073A();

    /* access modifiers changed from: protected */
    /* renamed from: B */
    public abstract ImageView mo27074B();

    /* access modifiers changed from: protected */
    /* renamed from: C */
    public abstract ImageView mo27075C();

    /* access modifiers changed from: protected */
    /* renamed from: D */
    public abstract ImageView mo27076D();

    /* access modifiers changed from: protected */
    /* renamed from: E */
    public abstract ImageView mo27077E();

    /* access modifiers changed from: protected */
    /* renamed from: F */
    public abstract ImageView mo27078F();

    /* access modifiers changed from: protected */
    /* renamed from: G */
    public abstract LinearLayout mo27079G();

    /* access modifiers changed from: protected */
    /* renamed from: H */
    public abstract LinearLayout mo27080H();

    /* access modifiers changed from: protected */
    /* renamed from: I */
    public abstract LinearLayout mo27081I();

    /* access modifiers changed from: protected */
    /* renamed from: J */
    public abstract ImageView mo27082J();

    /* access modifiers changed from: protected */
    /* renamed from: K */
    public abstract ImageView mo27083K();

    /* access modifiers changed from: protected */
    /* renamed from: L */
    public abstract ImageView mo27084L();

    /* access modifiers changed from: protected */
    /* renamed from: M */
    public abstract TextView mo27085M();

    /* access modifiers changed from: protected */
    /* renamed from: N */
    public abstract TextView mo27086N();

    /* access modifiers changed from: protected */
    /* renamed from: O */
    public abstract TextView mo27087O();

    /* access modifiers changed from: protected */
    /* renamed from: P */
    public abstract TextView mo27088P();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public abstract ViewGroup mo27089Q();

    /* access modifiers changed from: protected */
    /* renamed from: R */
    public abstract ViewGroup mo27090R();

    /* access modifiers changed from: protected */
    /* renamed from: S */
    public abstract Button mo27091S();

    /* access modifiers changed from: protected */
    /* renamed from: T */
    public abstract ImageView mo27092T();

    /* access modifiers changed from: protected */
    /* renamed from: U */
    public abstract ViewGroup mo27093U();

    /* access modifiers changed from: protected */
    /* renamed from: V */
    public abstract ImageView mo27094V();

    /* access modifiers changed from: protected */
    /* renamed from: W */
    public abstract LinearLayout mo27095W();

    /* access modifiers changed from: protected */
    /* renamed from: X */
    public abstract TextView mo27096X();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract View mo27099a(Context context);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract RelativeLayout mo27100a();

    /* access modifiers changed from: protected */
    /* renamed from: aY */
    public abstract void mo27147aY();

    /* access modifiers changed from: protected */
    /* renamed from: aZ */
    public abstract void mo27148aZ();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract LinearLayout mo27175b();

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract Button mo27180c();

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract int mo27182d();

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public abstract int mo27184e();

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public abstract Button mo27186f();

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public abstract Button mo27188g();

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public abstract Button mo27189h();

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public abstract LinearLayout mo27190i();

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public abstract TextView mo27191j();

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public abstract LinearLayout mo27192k();

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public abstract TextView mo27193l();

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public abstract TextView mo27194m();

    /* access modifiers changed from: protected */
    /* renamed from: n */
    public abstract TextView mo27195n();

    /* access modifiers changed from: protected */
    /* renamed from: o */
    public abstract ImageView mo27196o();

    /* access modifiers changed from: protected */
    /* renamed from: p */
    public abstract ImageView mo27197p();

    /* access modifiers changed from: protected */
    /* renamed from: q */
    public abstract ImageView mo27198q();

    /* access modifiers changed from: protected */
    /* renamed from: r */
    public abstract ImageView mo27199r();

    /* access modifiers changed from: protected */
    /* renamed from: s */
    public abstract ImageView mo27200s();

    /* access modifiers changed from: protected */
    /* renamed from: t */
    public abstract ImageView mo27201t();

    /* access modifiers changed from: protected */
    /* renamed from: u */
    public abstract ImageView mo27202u();

    /* access modifiers changed from: protected */
    /* renamed from: v */
    public abstract ImageView mo27203v();

    /* access modifiers changed from: protected */
    /* renamed from: w */
    public abstract ImageView mo27204w();

    /* access modifiers changed from: protected */
    /* renamed from: x */
    public abstract ImageView mo27205x();

    /* access modifiers changed from: protected */
    /* renamed from: y */
    public abstract ImageView mo27206y();

    /* access modifiers changed from: protected */
    /* renamed from: z */
    public abstract ImageView mo27207z();

    /* renamed from: af */
    static /* synthetic */ int m13037af(BaseGiWiFiInfoView baseGiWiFiInfoView) {
        int i = baseGiWiFiInfoView.f12107cp;
        baseGiWiFiInfoView.f12107cp = i + 1;
        return i;
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$p */
    public enum EnumC3246p {
        unconnectShow(0),
        oneShow(1),
        twoShow(2),
        threeShow(3),
        fourShow(4),
        fiveShow(5),
        sixShow(6),
        sevenShow(7),
        mobileShow(8);
        

        /* renamed from: j */
        private int f12204j = 0;

        private EnumC3246p(int i) {
            this.f12204j = i;
        }

        /* renamed from: a */
        public static EnumC3246p m13296a(int i) {
            switch (i) {
                case 0:
                    return unconnectShow;
                case 1:
                    return oneShow;
                case 2:
                    return twoShow;
                case 3:
                    return threeShow;
                case 4:
                    return fourShow;
                case 5:
                    return fiveShow;
                case 6:
                    return sixShow;
                case 7:
                    return sevenShow;
                case 8:
                    return mobileShow;
                default:
                    return null;
            }
        }

        /* renamed from: a */
        public int mo27260a() {
            return this.f12204j;
        }
    }

    public BaseGiWiFiInfoView(Context context) {
        super(context);
        m13091c(context);
    }

    public BaseGiWiFiInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m13091c(context);
    }

    public BaseGiWiFiInfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13091c(context);
    }

    /* renamed from: bd */
    private String m13065bd() {
        HashSet hashSet = new HashSet();
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        for (int i2 = 0; i2 < this.f12042bC.size(); i2++) {
            String ssid = this.f12042bC.get(i2).getSSID();
            if (!hashSet.contains(ssid)) {
                hashSet.add(ssid);
                stringBuffer.append(ssid);
                stringBuffer.append("\n");
                i++;
                if (i >= 6) {
                    break;
                }
            }
        }
        for (int i3 = 0; i3 < this.f12043bD.size(); i3++) {
            String ssid2 = this.f12043bD.get(i3).getSSID();
            if (!hashSet.contains(ssid2)) {
                hashSet.add(ssid2);
                stringBuffer.append(ssid2);
                stringBuffer.append("\n");
                i++;
                if (i >= 6) {
                    break;
                }
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: Y */
    public int mo27097Y() {
        return 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: be */
    private boolean m13066be() {
        WiFiScanResult wiFiScanResult;
        WiFiScanResult wiFiScanResult2;
        String a = m13011a(this.f12016ac.getConnectionInfo(), this.f12017ad.getActiveNetworkInfo());
        if (mo27187f(a) && !this.f12050bK.contains(a)) {
            this.f12050bK.add(a);
        }
        String scanWifiList = CacheWiFi.getInstance().getScanWifiList();
        if (C3467s.m14513e(scanWifiList)) {
            Log.m14403e(f11954g, "tryConnectGiWiFi: wifiList empty");
            return false;
        } else if (!JsonUtil.m14389a(scanWifiList)) {
            Log.m14403e(f11954g, "tryConnectGiWiFi: wifiList data invalid");
            return false;
        } else {
            WiFiScanList wiFiScanList = (WiFiScanList) GsonUtil.m14241a().mo21588a(scanWifiList, WiFiScanList.class);
            if (wiFiScanList == null) {
                Log.m14403e(f11954g, "tryConnectGiWiFi: wifiList data parse fail");
                return false;
            }
            ArrayList<WiFiScanResult> wifi2gList = wiFiScanList.getWifi2gList();
            ArrayList<WiFiScanResult> wifi5gList = wiFiScanList.getWifi5gList();
            this.f12042bC.clear();
            this.f12043bD.clear();
            int i = 0;
            WiFiScanResult wiFiScanResult3 = null;
            while (true) {
                if (i >= wifi5gList.size()) {
                    break;
                }
                String ssid = wifi5gList.get(i).getSSID();
                if (mo27187f(ssid) && !this.f12050bK.contains(ssid) && NetworkUtils.m14430b(wifi5gList.get(i).getBSSID())) {
                    if (wiFiScanResult3 == null) {
                        wiFiScanResult2 = wifi5gList.get(i);
                    } else {
                        wiFiScanResult2 = wiFiScanResult3;
                    }
                    this.f12042bC.add(wifi5gList.get(i));
                    if (this.f12042bC.size() >= 4) {
                        wiFiScanResult3 = wiFiScanResult2;
                        break;
                    }
                    wiFiScanResult3 = wiFiScanResult2;
                }
                i++;
            }
            WiFiScanResult wiFiScanResult4 = null;
            int i2 = 0;
            while (true) {
                if (i2 >= wifi2gList.size()) {
                    wiFiScanResult = wiFiScanResult4;
                    break;
                }
                String ssid2 = wifi2gList.get(i2).getSSID();
                if (mo27187f(ssid2) && !this.f12050bK.contains(ssid2) && NetworkUtils.m14430b(wifi2gList.get(i2).getBSSID())) {
                    if (wiFiScanResult4 == null) {
                        wiFiScanResult = wifi2gList.get(i2);
                    } else {
                        wiFiScanResult = wiFiScanResult4;
                    }
                    this.f12043bD.add(wifi2gList.get(i2));
                    if (this.f12043bD.size() >= 4) {
                        break;
                    }
                    wiFiScanResult4 = wiFiScanResult;
                }
                i2++;
            }
            if (wiFiScanResult != null) {
                if (wiFiScanResult3 == null) {
                    wiFiScanResult3 = wiFiScanResult;
                } else if (wiFiScanResult.getLevel() > wiFiScanResult3.getLevel() + 20) {
                    wiFiScanResult3 = wiFiScanResult;
                }
            }
            if (wiFiScanResult3 == null) {
                Log.m14403e(f11954g, "tryConnectGiWiFi: wifiList choose fail");
                WiFiService.m13974a().mo27590e();
                return false;
            }
            Log.m14398b(f11954g, "tryConnectGiWiFi choose " + wiFiScanResult3.getSSID());
            if (Build.VERSION.SDK_INT >= 29) {
                mo27097Y();
                m13111h("请手动切换到" + getResources().getString(C2425R.C2429string.wifi_name) + "网络:\n" + m13065bd());
            } else {
                m13047b(wiFiScanResult3);
                mo27119a(wiFiScanResult3);
            }
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0059  */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m13108g(java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 189
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.m13108g(java.lang.String):boolean");
    }

    /* renamed from: bf */
    private void m13067bf() {
        int networkId;
        WifiInfo connectionInfo = this.f12016ac.getConnectionInfo();
        if (connectionInfo != null && (networkId = connectionInfo.getNetworkId()) > 0) {
            this.f12016ac.removeNetwork(networkId);
        }
    }

    /* renamed from: bg */
    private int m13068bg() {
        WifiInfo connectionInfo = this.f12016ac.getConnectionInfo();
        if (connectionInfo != null) {
            return connectionInfo.getNetworkId();
        }
        return -1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: h */
    private void m13111h(String str) {
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.showNormalDialog("", str, "手动连接", "关闭", (GBActivity.AbstractC2517a) new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C322451 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    if (Build.VERSION.SDK_INT < 29) {
                        BaseGiWiFiInfoView.this.f11977N.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                    } else if (Utils.m10798d()) {
                        BaseGiWiFiInfoView.this.f11977N.startActivity(new Intent("android.settings.panel.action.WIFI"));
                    } else {
                        BaseGiWiFiInfoView.this.f11977N.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                    }
                }
            }, (GBActivity.AbstractC2517a) new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C322552 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                }
            }, false, (DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.DialogInterface$OnCancelListenerC322653 */

                public void onCancel(DialogInterface dialogInterface) {
                }
            });
        }
    }

    /* renamed from: i */
    private void m13113i(String str) {
        this.f12109cr.sendMessage(this.f12109cr.obtainMessage(60, str));
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: b */
    private void m13047b(WiFiScanResult wiFiScanResult) {
        if (Build.VERSION.SDK_INT >= 29) {
            m13113i("正在扫描" + getResources().getString(C2425R.C2429string.wifi_name) + "网络，稍后如果看到信号\"" + wiFiScanResult.getSSID() + "\"，请点击");
        } else {
            m13113i("正在尝试切换到" + getResources().getString(C2425R.C2429string.wifi_name) + "网络：" + wiFiScanResult.getSSID() + ",请稍等");
        }
        this.f12116d.lock();
        try {
            this.f12049bJ = m13068bg();
            this.f12047bH = 1;
            this.f12048bI = wiFiScanResult.getSSID();
            this.f12116d.unlock();
            if (Build.VERSION.SDK_INT >= 29) {
                this.f12109cr.sendEmptyMessageDelayed(50, AbstractTrafficShapingHandler.DEFAULT_MAX_TIME);
            } else {
                this.f12109cr.sendEmptyMessageDelayed(50, 8000);
            }
        } catch (Throwable th) {
            this.f12116d.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m13093c(WiFiScanResult wiFiScanResult) {
        if (Build.VERSION.SDK_INT >= 29) {
            m13113i("正在扫描游戏专线，稍后如果看到信号\"" + wiFiScanResult.getSSID() + "\"，请点击");
        } else {
            m13113i("正在尝试切换到游戏专线" + wiFiScanResult.getSSID() + ",请稍等");
        }
        this.f11987a.lock();
        try {
            this.f12040bA = m13068bg();
            this.f12090by = 1;
            this.f12091bz = wiFiScanResult.getSSID();
            this.f11987a.unlock();
            if (Build.VERSION.SDK_INT >= 29) {
                this.f12109cr.sendEmptyMessageDelayed(41, AbstractTrafficShapingHandler.DEFAULT_MAX_TIME);
            } else {
                this.f12109cr.sendEmptyMessageDelayed(41, 8000);
            }
        } catch (Throwable th) {
            this.f11987a.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m13100d(WiFiScanResult wiFiScanResult) {
        if (Build.VERSION.SDK_INT >= 29) {
            m13113i("正在扫描普通线路，稍后如果看到信号\"" + wiFiScanResult.getSSID() + "\"，请点击");
        } else {
            m13113i("正在尝试切换到普通线路" + wiFiScanResult.getSSID() + ",请稍等");
        }
        this.f11987a.lock();
        try {
            this.f12040bA = m13068bg();
            this.f12090by = 2;
            this.f12091bz = wiFiScanResult.getSSID();
            this.f11987a.unlock();
            if (Build.VERSION.SDK_INT >= 29) {
                this.f12109cr.sendEmptyMessageDelayed(42, AbstractTrafficShapingHandler.DEFAULT_MAX_TIME);
            } else {
                this.f12109cr.sendEmptyMessageDelayed(42, 8000);
            }
        } catch (Throwable th) {
            this.f11987a.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bh */
    private String m13069bh() {
        HashSet hashSet = new HashSet();
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        for (int i2 = 0; i2 < this.f12081bp.size(); i2++) {
            String ssid = this.f12081bp.get(i2).getSSID();
            if (!hashSet.contains(ssid)) {
                hashSet.add(ssid);
                stringBuffer.append(ssid);
                stringBuffer.append("\n");
                i++;
            }
        }
        for (int i3 = 0; i3 < this.f12082bq.size(); i3++) {
            String ssid2 = this.f12082bq.get(i3).getSSID();
            if (!hashSet.contains(ssid2)) {
                hashSet.add(ssid2);
                stringBuffer.append(ssid2);
                stringBuffer.append("\n");
                i++;
                if (i >= 4) {
                    break;
                }
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: Z */
    public int mo27098Z() {
        return 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13018a(GameConfig aVar) {
        int i;
        LinearLayout linearLayout;
        if (aVar == null) {
            Log.m14403e(f11954g, "gameChannelChoice: gameConfig is null");
            GBActivity.showMessageToast("当前环境不支持游戏加速");
        } else if (aVar.mo24523b() == 0) {
            Log.m14403e(f11954g, "gameChannelChoice: gameConfig game disable");
            GBActivity.showMessageToast("当前环境不支持游戏加速");
        } else if (aVar.mo24529e().size() == 0) {
            Log.m14403e(f11954g, "gameChannelChoice: gameConfig game filterList empty");
            GBActivity.showMessageToast("当前环境尚未配置游戏加速参数");
        } else {
            GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
            if (currentActivity == null) {
                Log.m14403e(f11954g, "gameChannelChoice: current activity null");
                GBActivity.showMessageToast("当前Activity为空");
                return;
            }
            final Dialog dialog = new Dialog(currentActivity, C2425R.C2430style.login_dialog);
            View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.wifi_filter_list_five, (ViewGroup) null);
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(C2425R.C2427id.one);
            LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(C2425R.C2427id.two);
            LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(C2425R.C2427id.three);
            LinearLayout linearLayout5 = (LinearLayout) inflate.findViewById(C2425R.C2427id.four);
            LinearLayout linearLayout6 = (LinearLayout) inflate.findViewById(C2425R.C2427id.five);
            List<GameFilter> e = aVar.mo24529e();
            int size = e.size();
            if (size < 5) {
                linearLayout6.setVisibility(8);
            } else {
                linearLayout6.setVisibility(0);
            }
            if (size < 4) {
                linearLayout5.setVisibility(8);
            } else {
                linearLayout5.setVisibility(0);
            }
            if (size < 3) {
                linearLayout4.setVisibility(8);
            } else {
                linearLayout4.setVisibility(0);
            }
            if (size < 2) {
                linearLayout3.setVisibility(8);
            } else {
                linearLayout3.setVisibility(0);
            }
            for (int i2 = 1; i2 <= 5; i2++) {
                int i3 = 0;
                LinearLayout linearLayout7 = null;
                if (i2 == 1) {
                    i3 = C2425R.C2427id.one_text;
                    linearLayout7 = linearLayout2;
                }
                if (i2 == 2) {
                    i3 = C2425R.C2427id.two_text;
                    linearLayout7 = linearLayout3;
                }
                if (i2 == 3) {
                    i3 = C2425R.C2427id.three_text;
                    linearLayout7 = linearLayout4;
                }
                if (i2 == 4) {
                    i3 = C2425R.C2427id.four_text;
                    linearLayout7 = linearLayout5;
                }
                if (i2 == 5) {
                    linearLayout = linearLayout6;
                    i = 2131756220;
                } else {
                    i = i3;
                    linearLayout = linearLayout7;
                }
                if (size >= i2) {
                    int i4 = i2 - 1;
                    final String a = e.get(i4).mo24531a();
                    final String b = e.get(i4).mo24533b();
                    TextView textView = (TextView) inflate.findViewById(i);
                    textView.setText(b);
                    if (!C3467s.m14513e(this.f12080bo) && b.equals(this.f12080bo)) {
                        textView.setBackgroundResource(C2425R.C2426drawable.gi_btn_bg_darkblue);
                        textView.setTextColor(-1);
                    }
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC31892 */

                        public void onClick(View view) {
                            if (dialog != null) {
                                dialog.dismiss();
                                BaseGiWiFiInfoView.this.f12080bo = b;
                                CacheAuth.getInstance().setFilterId(a);
                                if (Build.VERSION.SDK_INT >= 29) {
                                    BaseGiWiFiInfoView.this.mo27098Z();
                                    BaseGiWiFiInfoView.this.m13111h((BaseGiWiFiInfoView) ("请手动切换到游戏专线:\n" + BaseGiWiFiInfoView.this.m13069bh()));
                                    return;
                                }
                                BaseGiWiFiInfoView.this.m13093c((BaseGiWiFiInfoView) BaseGiWiFiInfoView.this.f12079bn);
                                BaseGiWiFiInfoView.this.mo27119a(BaseGiWiFiInfoView.this.f12079bn);
                            }
                        }
                    });
                }
            }
            dialog.setContentView(inflate, new ViewGroup.LayoutParams((int) getResources().getDimension(C2425R.dimen.dialog_width), -2));
            dialog.show();
        }
    }

    /* renamed from: b */
    private void m13043b(Context context) {
        LinearLayout W;
        mo27099a(context);
        this.f12037ax = mo27191j();
        this.f12038ay = mo27192k();
        this.f12039az = mo27193l();
        this.f11989aB = mo27194m();
        this.f11988aA = mo27195n();
        this.f11990aC = mo27196o();
        this.f11998aK = mo27204w();
        this.f11991aD = mo27197p();
        this.f11992aE = mo27198q();
        this.f11993aF = mo27199r();
        this.f11994aG = mo27200s();
        this.f11995aH = mo27201t();
        this.f11996aI = mo27202u();
        this.f11997aJ = mo27203v();
        this.f11999aL = mo27205x();
        this.f12000aM = mo27206y();
        this.f12001aN = mo27207z();
        this.f12002aO = mo27073A();
        this.f12003aP = mo27074B();
        this.f12004aQ = mo27075C();
        this.f12005aR = mo27076D();
        this.f12006aS = mo27077E();
        this.f12007aT = mo27078F();
        this.f12008aU = mo27079G();
        this.f12009aV = mo27080H();
        this.f12010aW = mo27081I();
        if (this.f12010aW != null) {
            this.f12010aW.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC32276 */

                public void onClick(View view) {
                    BaseGiWiFiInfoView.this.m13016a((BaseGiWiFiInfoView) 4, (int) "");
                }
            });
        }
        if (this.f12009aV != null) {
            this.f12009aV.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC32287 */

                public void onClick(View view) {
                    BaseGiWiFiInfoView.this.m13016a((BaseGiWiFiInfoView) 5, (int) "");
                }
            });
        }
        if (this.f12008aU != null) {
            this.f12008aU.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC32298 */

                public void onClick(View view) {
                    BaseGiWiFiInfoView.this.m13016a((BaseGiWiFiInfoView) 3, (int) "");
                }
            });
        }
        this.f12011aX = mo27082J();
        this.f12012aY = mo27083K();
        this.f12013aZ = mo27084L();
        this.f12066ba = mo27085M();
        this.f12067bb = mo27086N();
        this.f12068bc = mo27087O();
        this.f12069bd = mo27088P();
        this.f12030aq = mo27100a();
        if (this.f12030aq != null) {
            this.f12030aq.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC32309 */

                public void onClick(View view) {
                    if (BaseGiWiFiInfoView.this.f12052bM != null) {
                        BaseGiWiFiInfoView.this.f12052bM.mo25006a(view);
                    }
                }
            });
        }
        this.f12031ar = mo27175b();
        if (this.f12031ar != null) {
            this.f12031ar.setOnClickListener(this.f12064bY);
        }
        this.f12032as = mo27180c();
        if (this.f12032as != null) {
            this.f12032as.setOnClickListener(this.f12092ca);
        }
        this.f12033at = mo27186f();
        if (this.f12033at != null) {
            this.f12033at.setOnClickListener(this.f12065bZ);
        }
        this.f12034au = mo27188g();
        if (this.f12034au != null) {
            this.f12034au.setOnClickListener(this.f12093cb);
        }
        this.f12035av = mo27189h();
        if (this.f12035av != null) {
            this.f12035av.setOnClickListener(this.f12094cc);
            if (SystemUtil.m14533f()) {
                this.f12035av.setText("连接" + ((Object) getResources().getText(C2425R.C2429string.wifi_name)));
            }
        }
        this.f12036aw = mo27190i();
        if (this.f12036aw != null) {
            this.f12036aw.setOnTouchListener(this.f12095cd);
        }
        this.f12077bl = mo27093U();
        this.f12078bm = mo27094V();
        if (this.f12078bm != null) {
            this.f12078bm.setOnClickListener(this.f12098cg);
        }
        this.f12073bh = mo27089Q();
        this.f12074bi = mo27090R();
        this.f12075bj = mo27091S();
        if (this.f12075bj != null) {
            this.f12075bj.setOnClickListener(this.f12096ce);
        }
        this.f12076bk = mo27092T();
        if (this.f12076bk != null) {
            this.f12076bk.setOnClickListener(this.f12097cf);
        }
        GBGlobalConfig.m10510c().mo24417q();
        WiFiService.m13974a().mo27583b(context);
        if (SystemUtil.m14533f() && (W = mo27095W()) != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) W.getLayoutParams();
            layoutParams.weight = 0.0f;
            W.setLayoutParams(layoutParams);
            W.setVisibility(8);
            ViewParent parent = W.getParent();
            if (parent != null && (parent instanceof LinearLayout)) {
                LinearLayout linearLayout = (LinearLayout) parent;
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams2.weight = 0.5f;
                linearLayout.setLayoutParams(layoutParams2);
            }
        }
    }

    /* renamed from: c */
    private void m13091c(Context context) {
        this.f11977N = context;
        Log.m14398b(f11954g, "init:1 " + FormatUtil.m14213a());
        m13043b(context);
        Log.m14398b(f11954g, "init:2 " + FormatUtil.m14213a());
        HandlerThread handlerThread = new HandlerThread("cache");
        handlerThread.start();
        this.f11978O = new HandlerC3231a(handlerThread.getLooper());
        this.f12016ac = (WifiManager) GBApplication.instance().getApplicationContext().getSystemService("wifi");
        this.f12017ad = (ConnectivityManager) GBApplication.instance().getApplicationContext().getSystemService("connectivity");
        if (!SystemUtil.m14531e() || (this instanceof GiWiFiInfoHomeView)) {
            WifiInfo connectionInfo = this.f12016ac.getConnectionInfo();
            if (connectionInfo == null || connectionInfo.getSupplicantState() == null) {
                mo27116a(WifiState.DISCONNECTED);
                m13087bz();
            } else if (connectionInfo.getSupplicantState() == SupplicantState.SCANNING || connectionInfo.getSupplicantState() == SupplicantState.DISCONNECTED) {
                mo27116a(WifiState.DISCONNECTED);
                m13087bz();
            } else if (connectionInfo.getSupplicantState() == SupplicantState.COMPLETED) {
                mo27116a(WifiState.CHECKING);
                this.f11984U = 0;
            }
            GBApplication.instance().registerReceiver(this.f12103cl, new IntentFilter(Constant.f13238bM));
            GBApplication.instance().registerReceiver(this.f12104cm, new IntentFilter(CheckNetWorkReceiver.f12764a));
            GBApplication.instance().registerReceiver(this.f12105cn, new IntentFilter(Constant.f13245bT));
            GBApplication.instance().registerReceiver(this.f12106co, new IntentFilter(Constant.f13248bW));
            if (!m13063bM()) {
                this.f12025al = true;
                m13077bp();
            }
            this.f12026am = this.f12016ac.isWifiEnabled();
            m13079br();
            m13052bB();
            m13078bq();
            if (!SystemUtil.m14531e()) {
                m13070bi();
            }
            this.f11987a = new ReentrantLock();
            this.f12116d = new ReentrantLock();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bi */
    private void m13070bi() {
        this.f11979P = HttpUtil.m14354t(GBApplication.instance(), this.f12118f, f11955h);
    }

    /* renamed from: a */
    private void m13017a(View view) {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bj */
    private boolean m13071bj() {
        return Build.VERSION.SDK_INT >= 23;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bk */
    private void m13072bk() {
        if (m13071bj()) {
            this.f11977N.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            return;
        }
        this.f11977N.startActivity(new Intent(GBApplication.instance(), WiFiActivity.class));
    }

    /* renamed from: aa */
    public void mo27149aa() {
        if (this.f12034au != null) {
            this.f12034au.setEnabled(true);
        }
    }

    /* renamed from: ab */
    public void mo27150ab() {
        if (this.f12034au != null) {
            this.f12034au.setEnabled(false);
        }
    }

    /* renamed from: ac */
    public void mo27151ac() {
        if (this.f12034au != null) {
            this.f12034au.setVisibility(8);
        }
    }

    /* renamed from: ad */
    public void mo27152ad() {
        if (this.f12034au != null) {
            this.f12034au.setVisibility(0);
        }
    }

    /* renamed from: ae */
    public void mo27153ae() {
        if (this.f12035av != null) {
            this.f12035av.setEnabled(true);
        }
    }

    /* renamed from: af */
    public void mo27154af() {
        if (this.f12035av != null) {
            this.f12035av.setEnabled(false);
        }
    }

    /* renamed from: ag */
    public void mo27155ag() {
        if (this.f12035av != null) {
            this.f12035av.setVisibility(8);
        }
    }

    /* renamed from: ah */
    public void mo27156ah() {
        if (this.f12035av != null) {
            this.f12035av.setVisibility(0);
        }
    }

    /* renamed from: ai */
    public void mo27157ai() {
        if (this.f12032as != null) {
            this.f12032as.setVisibility(8);
        }
    }

    /* renamed from: aj */
    public void mo27158aj() {
        if (this.f12032as != null) {
            this.f12032as.setEnabled(true);
        }
    }

    /* renamed from: ak */
    public void mo27159ak() {
        if (this.f12032as != null) {
            this.f12032as.setEnabled(false);
        }
    }

    /* renamed from: al */
    public void mo27160al() {
        if (this.f12032as != null) {
            this.f12032as.setVisibility(0);
        }
    }

    /* renamed from: a */
    public void mo27120a(String str) {
        if (this.f12032as != null) {
            this.f12032as.setText(str);
        }
    }

    /* renamed from: am */
    public String mo27161am() {
        if (this.f12032as != null) {
            return this.f12032as.getText().toString();
        }
        return "";
    }

    /* renamed from: an */
    public void mo27162an() {
        if (this.f12033at != null) {
            this.f12033at.setText("开启WiFi");
        }
    }

    /* renamed from: b */
    public void mo27177b(String str) {
        if (this.f12033at != null) {
            this.f12033at.setText(str);
        }
    }

    /* renamed from: ao */
    public void mo27163ao() {
        int d;
        if (this.f12032as != null && (d = mo27182d()) > 0) {
            this.f12032as.setBackgroundResource(d);
        }
    }

    /* renamed from: ap */
    public void mo27164ap() {
        int e;
        if (this.f12032as != null && (e = mo27184e()) > 0) {
            this.f12032as.setBackgroundResource(e);
        }
    }

    /* renamed from: aq */
    public void mo27165aq() {
        if (this.f12033at != null) {
            this.f12033at.setVisibility(8);
        }
    }

    /* renamed from: ar */
    public void mo27166ar() {
        if (this.f12033at != null) {
            this.f12033at.setVisibility(0);
        }
    }

    /* renamed from: as */
    public void mo27167as() {
        if (this.f12033at != null) {
            this.f12033at.setEnabled(true);
        }
    }

    /* renamed from: at */
    public void mo27168at() {
        if (this.f12033at != null) {
            this.f12033at.setEnabled(false);
        }
    }

    /* renamed from: a */
    public void mo27121a(String str, boolean z) {
        if (this.f12039az != null) {
            this.f12039az.setText(str);
        }
        if (str.equals("00:00:00")) {
            if (this.f12037ax != null) {
                this.f12037ax.setVisibility(0);
            }
            if (this.f12038ay != null) {
                this.f12038ay.setVisibility(8);
                return;
            }
            return;
        }
        if (this.f12037ax != null) {
            this.f12037ax.setVisibility(8);
        }
        if (this.f12038ay != null) {
            this.f12038ay.setVisibility(0);
        }
    }

    /* renamed from: au */
    public void mo27169au() {
    }

    /* renamed from: c */
    public void mo27181c(String str) {
        if (C3467s.m14513e(str)) {
            if (this.f11988aA != null) {
                this.f11988aA.setText("无网络");
                this.f11988aA.setClickable(false);
                this.f12099ch = true;
            }
        } else if (this.f11988aA != null) {
            this.f11988aA.setText(str);
            if (str.equals(f11953c)) {
                this.f11988aA.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC317910 */

                    public void onClick(View view) {
                        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
                        if (currentActivity != null) {
                            GBGlobalConfig.m10510c().mo24390a((Activity) currentActivity);
                        }
                    }
                });
            } else {
                this.f11988aA.setClickable(false);
            }
            this.f12099ch = false;
        }
    }

    /* renamed from: av */
    public String mo27170av() {
        if (this.f11988aA != null) {
            return this.f11988aA.getText().toString();
        }
        return "";
    }

    /* renamed from: d */
    public void mo27183d(String str) {
        if (str.equals(this.f11972I[10])) {
            CacheWiFi.getInstance().setNetworkReachable(true);
        } else {
            CacheWiFi.getInstance().setNetworkReachable(false);
        }
        if (2 == this.f12071bf) {
            str = this.f11973J;
        }
        if (this.f12037ax != null) {
            this.f12037ax.setText(str);
        }
        if (this.f12054bO != null) {
            this.f12054bO.mo24474a(str);
        }
    }

    /* renamed from: aw */
    public String mo27171aw() {
        if (this.f12037ax != null) {
            return this.f12037ax.getText().toString();
        }
        return "";
    }

    /* renamed from: a */
    private void m13019a(EnumC3246p pVar) {
        if (this.f12055bP != null) {
            this.f12055bP.mo27255a(pVar);
        }
        if (this.f12056bQ != null) {
            this.f12056bQ.mo27257a(pVar);
        }
        if (this.f12057bR != null) {
            this.f12057bR.mo27256a(pVar);
        }
        if (this.f12058bS != null) {
            this.f12058bS.mo24476a(pVar);
        }
    }

    /* renamed from: ax */
    public void mo27172ax() {
        if (this.f11990aC != null) {
            this.f11990aC.setVisibility(0);
        }
        if (this.f11999aL != null) {
            this.f11999aL.setVisibility(0);
        }
        if (this.f12000aM != null) {
            this.f12000aM.setVisibility(8);
        }
        if (this.f12001aN != null) {
            this.f12001aN.setVisibility(8);
        }
        if (this.f12002aO != null) {
            this.f12002aO.setVisibility(8);
        }
        if (this.f12003aP != null) {
            this.f12003aP.setVisibility(8);
        }
        if (this.f12004aQ != null) {
            this.f12004aQ.setVisibility(8);
        }
        if (this.f12005aR != null) {
            this.f12005aR.setVisibility(8);
        }
        if (this.f12006aS != null) {
            this.f12006aS.setVisibility(8);
        }
        if (this.f12007aT != null) {
            this.f12007aT.setVisibility(8);
        }
        m13019a(EnumC3246p.unconnectShow);
    }

    /* renamed from: ay */
    public void mo27173ay() {
        if (this.f11990aC != null) {
            this.f11990aC.setVisibility(8);
        }
    }

    /* renamed from: az */
    public void mo27174az() {
        if (this.f11991aD != null) {
            this.f11991aD.setVisibility(8);
        }
    }

    /* renamed from: aA */
    public void mo27123aA() {
        if (this.f11991aD != null) {
            this.f11991aD.setVisibility(0);
        }
        if (this.f11999aL != null) {
            this.f11999aL.setVisibility(8);
        }
        if (this.f12000aM != null) {
            this.f12000aM.setVisibility(0);
        }
        if (this.f12001aN != null) {
            this.f12001aN.setVisibility(8);
        }
        if (this.f12002aO != null) {
            this.f12002aO.setVisibility(8);
        }
        if (this.f12003aP != null) {
            this.f12003aP.setVisibility(8);
        }
        if (this.f12004aQ != null) {
            this.f12004aQ.setVisibility(8);
        }
        if (this.f12005aR != null) {
            this.f12005aR.setVisibility(8);
        }
        if (this.f12006aS != null) {
            this.f12006aS.setVisibility(8);
        }
        if (this.f12007aT != null) {
            this.f12007aT.setVisibility(8);
        }
        m13019a(EnumC3246p.oneShow);
    }

    /* renamed from: aB */
    public void mo27124aB() {
        if (this.f11992aE != null) {
            this.f11992aE.setVisibility(8);
        }
    }

    /* renamed from: aC */
    public void mo27125aC() {
        if (this.f11992aE != null) {
            this.f11992aE.setVisibility(0);
        }
        if (this.f11999aL != null) {
            this.f11999aL.setVisibility(8);
        }
        if (this.f12000aM != null) {
            this.f12000aM.setVisibility(8);
        }
        if (this.f12001aN != null) {
            this.f12001aN.setVisibility(0);
        }
        if (this.f12002aO != null) {
            this.f12002aO.setVisibility(8);
        }
        if (this.f12003aP != null) {
            this.f12003aP.setVisibility(8);
        }
        if (this.f12004aQ != null) {
            this.f12004aQ.setVisibility(8);
        }
        if (this.f12005aR != null) {
            this.f12005aR.setVisibility(8);
        }
        if (this.f12006aS != null) {
            this.f12006aS.setVisibility(8);
        }
        if (this.f12007aT != null) {
            this.f12007aT.setVisibility(8);
        }
        m13019a(EnumC3246p.twoShow);
    }

    /* renamed from: aD */
    public void mo27126aD() {
        if (this.f11993aF != null) {
            this.f11993aF.setVisibility(8);
        }
    }

    /* renamed from: aE */
    public void mo27127aE() {
        if (this.f11993aF != null) {
            this.f11993aF.setVisibility(0);
        }
        if (this.f11999aL != null) {
            this.f11999aL.setVisibility(8);
        }
        if (this.f12000aM != null) {
            this.f12000aM.setVisibility(8);
        }
        if (this.f12001aN != null) {
            this.f12001aN.setVisibility(8);
        }
        if (this.f12002aO != null) {
            this.f12002aO.setVisibility(0);
        }
        if (this.f12003aP != null) {
            this.f12003aP.setVisibility(8);
        }
        if (this.f12004aQ != null) {
            this.f12004aQ.setVisibility(8);
        }
        if (this.f12005aR != null) {
            this.f12005aR.setVisibility(8);
        }
        if (this.f12006aS != null) {
            this.f12006aS.setVisibility(8);
        }
        if (this.f12007aT != null) {
            this.f12007aT.setVisibility(8);
        }
        m13019a(EnumC3246p.threeShow);
    }

    /* renamed from: aF */
    public void mo27128aF() {
        if (this.f11994aG != null) {
            this.f11994aG.setVisibility(8);
        }
    }

    /* renamed from: aG */
    public void mo27129aG() {
        if (this.f11994aG != null) {
            this.f11994aG.setVisibility(0);
        }
        if (this.f11999aL != null) {
            this.f11999aL.setVisibility(8);
        }
        if (this.f12000aM != null) {
            this.f12000aM.setVisibility(8);
        }
        if (this.f12001aN != null) {
            this.f12001aN.setVisibility(8);
        }
        if (this.f12002aO != null) {
            this.f12002aO.setVisibility(8);
        }
        if (this.f12003aP != null) {
            this.f12003aP.setVisibility(0);
        }
        if (this.f12004aQ != null) {
            this.f12004aQ.setVisibility(8);
        }
        if (this.f12005aR != null) {
            this.f12005aR.setVisibility(8);
        }
        if (this.f12006aS != null) {
            this.f12006aS.setVisibility(8);
        }
        if (this.f12007aT != null) {
            this.f12007aT.setVisibility(8);
        }
        m13019a(EnumC3246p.fourShow);
    }

    /* renamed from: aH */
    public void mo27130aH() {
        if (this.f11995aH != null) {
            this.f11995aH.setVisibility(8);
        }
    }

    /* renamed from: aI */
    public void mo27131aI() {
        if (this.f11995aH != null) {
            this.f11995aH.setVisibility(0);
        }
        if (this.f11999aL != null) {
            this.f11999aL.setVisibility(8);
        }
        if (this.f12000aM != null) {
            this.f12000aM.setVisibility(8);
        }
        if (this.f12001aN != null) {
            this.f12001aN.setVisibility(8);
        }
        if (this.f12002aO != null) {
            this.f12002aO.setVisibility(8);
        }
        if (this.f12003aP != null) {
            this.f12003aP.setVisibility(8);
        }
        if (this.f12004aQ != null) {
            this.f12004aQ.setVisibility(0);
        }
        if (this.f12005aR != null) {
            this.f12005aR.setVisibility(8);
        }
        if (this.f12006aS != null) {
            this.f12006aS.setVisibility(8);
        }
        if (this.f12007aT != null) {
            this.f12007aT.setVisibility(8);
        }
        m13019a(EnumC3246p.fiveShow);
    }

    /* renamed from: aJ */
    public void mo27132aJ() {
        if (this.f11996aI != null) {
            this.f11996aI.setVisibility(8);
        }
    }

    /* renamed from: aK */
    public void mo27133aK() {
        if (this.f11996aI != null) {
            this.f11996aI.setVisibility(0);
        }
        if (this.f11999aL != null) {
            this.f11999aL.setVisibility(8);
        }
        if (this.f12000aM != null) {
            this.f12000aM.setVisibility(8);
        }
        if (this.f12001aN != null) {
            this.f12001aN.setVisibility(8);
        }
        if (this.f12002aO != null) {
            this.f12002aO.setVisibility(8);
        }
        if (this.f12003aP != null) {
            this.f12003aP.setVisibility(8);
        }
        if (this.f12004aQ != null) {
            this.f12004aQ.setVisibility(8);
        }
        if (this.f12005aR != null) {
            this.f12005aR.setVisibility(0);
        }
        if (this.f12006aS != null) {
            this.f12006aS.setVisibility(8);
        }
        if (this.f12007aT != null) {
            this.f12007aT.setVisibility(8);
        }
        m13019a(EnumC3246p.sixShow);
    }

    /* renamed from: aL */
    public void mo27134aL() {
        if (this.f11997aJ != null) {
            this.f11997aJ.setVisibility(8);
        }
    }

    /* renamed from: aM */
    public void mo27135aM() {
        if (this.f11997aJ != null) {
            this.f11997aJ.setVisibility(0);
        }
        if (this.f11999aL != null) {
            this.f11999aL.setVisibility(8);
        }
        if (this.f12000aM != null) {
            this.f12000aM.setVisibility(8);
        }
        if (this.f12001aN != null) {
            this.f12001aN.setVisibility(8);
        }
        if (this.f12002aO != null) {
            this.f12002aO.setVisibility(8);
        }
        if (this.f12003aP != null) {
            this.f12003aP.setVisibility(8);
        }
        if (this.f12004aQ != null) {
            this.f12004aQ.setVisibility(8);
        }
        if (this.f12005aR != null) {
            this.f12005aR.setVisibility(8);
        }
        if (this.f12006aS != null) {
            this.f12006aS.setVisibility(0);
        }
        if (this.f12007aT != null) {
            this.f12007aT.setVisibility(8);
        }
        m13019a(EnumC3246p.sevenShow);
    }

    /* renamed from: aN */
    public void mo27136aN() {
        if (this.f11998aK != null) {
            this.f11998aK.setVisibility(8);
        }
    }

    /* renamed from: aO */
    public void mo27137aO() {
        if (this.f11998aK != null) {
            this.f11998aK.setVisibility(0);
        }
        if (this.f11999aL != null) {
            this.f11999aL.setVisibility(8);
        }
        if (this.f12000aM != null) {
            this.f12000aM.setVisibility(8);
        }
        if (this.f12001aN != null) {
            this.f12001aN.setVisibility(8);
        }
        if (this.f12002aO != null) {
            this.f12002aO.setVisibility(8);
        }
        if (this.f12003aP != null) {
            this.f12003aP.setVisibility(8);
        }
        if (this.f12004aQ != null) {
            this.f12004aQ.setVisibility(8);
        }
        if (this.f12005aR != null) {
            this.f12005aR.setVisibility(8);
        }
        if (this.f12006aS != null) {
            this.f12006aS.setVisibility(8);
        }
        if (this.f12007aT != null) {
            this.f12007aT.setVisibility(0);
        }
        m13019a(EnumC3246p.mobileShow);
    }

    /* renamed from: a */
    public void mo27104a(AbstractC3232b bVar) {
        this.f12059bT = bVar;
    }

    /* renamed from: a */
    public void mo27108a(AbstractC3236f fVar) {
        this.f12060bU = fVar;
    }

    /* renamed from: a */
    public void mo27105a(AbstractC3233c cVar) {
        this.f12061bV = cVar;
    }

    /* renamed from: a */
    public void mo27107a(AbstractC3235e eVar) {
        this.f12062bW = eVar;
    }

    /* renamed from: a */
    public void mo27106a(AbstractC3234d dVar) {
        this.f12063bX = dVar;
    }

    /* renamed from: a */
    public void mo27109a(AbstractC3237g gVar) {
        this.f12053bN = gVar;
    }

    /* renamed from: a */
    public void mo27115a(AbstractC3243m mVar) {
        this.f12054bO = mVar;
    }

    /* renamed from: a */
    public void mo27112a(AbstractC3240j jVar) {
        this.f12055bP = jVar;
    }

    /* renamed from: a */
    public void mo27114a(AbstractC3242l lVar) {
        this.f12056bQ = lVar;
    }

    /* renamed from: a */
    public void mo27113a(AbstractC3241k kVar) {
        this.f12057bR = kVar;
    }

    /* renamed from: a */
    public void mo27111a(AbstractC3239i iVar) {
        this.f12058bS = iVar;
    }

    /* renamed from: a */
    public void mo27110a(AbstractC3238h hVar) {
        this.f12052bM = hVar;
    }

    /* renamed from: aP */
    public void mo27138aP() {
        this.f12036aw.startAnimation(AnimationUtils.loadAnimation(getContext(), C2425R.anim.wifi_state_start));
    }

    /* renamed from: aQ */
    public void mo27139aQ() {
        this.f12036aw.startAnimation(AnimationUtils.loadAnimation(getContext(), C2425R.anim.wifianim_exit));
    }

    /* renamed from: a */
    private void m13029a(HashMap<String, Object> hashMap) {
        m13048b(hashMap);
    }

    /* renamed from: b */
    private void m13048b(final HashMap<String, Object> hashMap) {
        String str;
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            Log.m14398b(f11954g, "checkVersionDialog " + currentActivity.toString());
            String str2 = "最新版本：" + hashMap.get("versionName").toString();
            String obj = hashMap.get("versionContent").toString();
            if (((Boolean) hashMap.get("isReallyMust")).booleanValue()) {
                str = "退出应用";
            } else {
                str = "稍后更新";
            }
            currentActivity.showVersionDialog(str2, obj, "立即更新", str, new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C318011 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    if (GBActivity.installApkBack(hashMap.get(TbsReaderView.KEY_FILE_PATH).toString())) {
                    }
                }
            }, new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C318213 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    if (((Boolean) hashMap.get("isReallyMust")).booleanValue()) {
                        BaseGiWiFiInfoView.this.mo27144aV();
                    }
                }
            }, ((Boolean) hashMap.get("isReallyMust")).booleanValue(), new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.DialogInterface$OnCancelListenerC318314 */

                public void onCancel(DialogInterface dialogInterface) {
                }
            });
        }
    }

    /* renamed from: bl */
    private void m13073bl() {
        SystemUtil.m14534g(getContext());
        if (this.f12029ap != null) {
            if (!this.f12029ap.isShowing()) {
                this.f12029ap.show();
            }
        } else if (GBApplication.instance().getCurrentActivity() != null) {
            this.f12029ap = new ProgressDialog(GBApplication.instance().getCurrentActivity());
            this.f12029ap.setTitle("请稍等...");
            this.f12029ap.setCancelable(true);
            this.f12029ap.setOnCancelListener(new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.DialogInterface$OnCancelListenerC318415 */

                public void onCancel(DialogInterface dialogInterface) {
                    if (BaseGiWiFiInfoView.this.f12029ap != null) {
                        BaseGiWiFiInfoView.this.f12029ap.hide();
                    }
                }
            });
            this.f12029ap.show();
            this.f12029ap.getWindow().setContentView(C2425R.layout.progress);
            this.f12029ap.getWindow().getAttributes().dimAmount = 0.0f;
            this.f12029ap.getWindow().setGravity(1);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bm */
    private void m13074bm() {
        if (this.f12029ap != null) {
            this.f12029ap.hide();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bn */
    private void m13075bn() {
        if (this instanceof GiWiFiInfoHomeView) {
            Log.m14400c(f11954g, "authWifi GiWiFiInfoHomeView");
        } else {
            Log.m14400c(f11954g, "authWifi  not GiWiFiInfoHomeView");
        }
        if (!this.f12016ac.isWifiEnabled()) {
            m13074bm();
            return;
        }
        Log.m14400c(f11954g, "startAutoConnect()");
        this.f12020ag.clear();
        if (!m13063bM()) {
            m13074bm();
        } else if (this.f12016ac.getConnectionInfo() == null) {
            m13074bm();
        } else {
            m13073bl();
            MultiCheckNetWorkUtils.m13939a(GBApplication.instance(), new CallBack() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C318516 */

                @Override // com.gbcom.gwifi.base.p234c.CallBack
                /* renamed from: a */
                public void mo24437a(Object obj) {
                    CheckResult checkResult = (CheckResult) obj;
                    if (checkResult == null) {
                        BaseGiWiFiInfoView.this.m13074bm();
                    } else if (checkResult.isGiwifi()) {
                        BaseGiWiFiInfoView.this.m13076bo();
                        if (!BaseGiWiFiInfoView.this.m13059bI() || checkResult.getAuthInfo() == null || checkResult.getAuthInfo().getAuthState().intValue() != 1) {
                            BaseGiWiFiInfoView.this.m13074bm();
                            BaseGiWiFiInfoView.this.m13084bw();
                        } else if (!GBGlobalConfig.m10510c().mo24401a(BaseGiWiFiInfoView.this.f12110cs)) {
                            BaseGiWiFiInfoView.this.m13074bm();
                        }
                    } else {
                        BaseGiWiFiInfoView.this.m13020a((BaseGiWiFiInfoView) EnumC3247q.GwifiShow);
                        if (checkResult.getOnlineState().intValue() != 2) {
                            BaseGiWiFiInfoView.this.m13077bp();
                            return;
                        }
                        BaseGiWiFiInfoView.this.m13074bm();
                        BaseGiWiFiInfoView.this.mo27183d(BaseGiWiFiInfoView.this.f11972I[10]);
                        BaseGiWiFiInfoView.this.m13053bC();
                        if (Config.m14094a().mo27812o() && Config.m14094a().mo27811n()) {
                            Log.m14400c(BaseGiWiFiInfoView.f11954g, "check version broadcast start.....");
                            GBApplication.instance().sendBroadcast(new Intent(Constant.f13232bG));
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bo */
    private void m13076bo() {
        HttpUtil.m14287a(f11955h);
    }

    /* renamed from: aR */
    public void mo27140aR() {
        MultiCheckNetWorkUtils.m13939a(GBApplication.instance(), this.f12101cj);
    }

    /* renamed from: b */
    private void m13049b(boolean z) {
        View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.wifi_password_input, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(C2425R.C2427id.wifi_password_et);
        final ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.eye_iv);
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC318819 */

            public void onClick(View view) {
                UIUtil.m14202a(view);
                BaseGiWiFiInfoView.this.f11986W = !BaseGiWiFiInfoView.this.f11986W;
                if (BaseGiWiFiInfoView.this.f11986W) {
                    editText.setInputType(145);
                } else {
                    editText.setInputType(129);
                }
                editText.setSelection(editText.getText().length());
                BaseGiWiFiInfoView.this.f12117e.removeMessages(1, imageView);
                BaseGiWiFiInfoView.this.f12117e.sendMessageDelayed(BaseGiWiFiInfoView.this.f12117e.obtainMessage(1, imageView), 300);
            }
        });
        if (z) {
            inflate.findViewById(2131755836).setVisibility(0);
        }
        GBApplication.instance().getCurrentActivity().showNormalDialog("输入" + this.f12018ae.getSsid() + "密码", inflate, "加入", "取消", new GBActivity.AbstractC2517a() {
            /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C319020 */

            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
            public void onClick(Dialog dialog, View view) {
                String obj = editText.getText().toString();
                if (TextUtils.isEmpty(obj)) {
                    GBActivity.showMessageToast("请输入WiFi密码");
                } else if (obj.length() < 8) {
                    GBActivity.showMessageToast("密码至少8位");
                } else {
                    BaseGiWiFiInfoView.this.f12015ab = false;
                    BaseGiWiFiInfoView.this.f12109cr.sendMessageDelayed(BaseGiWiFiInfoView.this.f12109cr.obtainMessage(14), 10000);
                    BaseGiWiFiInfoView.this.mo27118a(BaseGiWiFiInfoView.this.f12018ae, obj);
                    dialog.dismiss();
                }
            }
        }, (GBActivity.AbstractC2517a) null);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bp */
    private synchronized boolean m13077bp() {
        ScanResult a;
        boolean z = false;
        synchronized (this) {
            List<ScanResult> scanResults = this.f12016ac.getScanResults();
            if (!(scanResults == null || (a = m13008a(scanResults)) == null)) {
                mo27102a(a);
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    private ScanResult m13008a(List<ScanResult> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            ScanResult scanResult = list.get(i);
            if (scanResult != null && NetworkUtils.m14430b(scanResult.BSSID) && !scanResult.SSID.toLowerCase().contains("cmcc")) {
                arrayList.add(scanResult);
            }
        }
        if (arrayList.size() > 1) {
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                ScanResult scanResult2 = arrayList.get(i2);
                String lowerCase = scanResult2.SSID.toLowerCase();
                if (lowerCase.contains(this.f11974K[0]) || lowerCase.contains(this.f11974K[1]) || lowerCase.contains(this.f11974K[2]) || lowerCase.contains(this.f11974K[3])) {
                    arrayList2.add(scanResult2);
                }
            }
            if (arrayList2.size() > 1) {
                return m13039b(arrayList2);
            }
            if (arrayList2.size() == 1) {
                return arrayList2.get(0);
            }
            return m13039b(arrayList);
        } else if (arrayList.size() == 1) {
            return arrayList.get(0);
        } else {
            return null;
        }
    }

    /* renamed from: b */
    private ScanResult m13039b(List<ScanResult> list) {
        int i = 0;
        int i2 = -1000;
        for (int i3 = 0; i3 < list.size(); i3++) {
            ScanResult scanResult = list.get(i3);
            if (scanResult.level > i2) {
                i2 = scanResult.level;
                i = i3;
            }
        }
        return list.get(i);
    }

    /* renamed from: a */
    public void mo27102a(ScanResult scanResult) {
        BaseWifi b = WiFiService.m13974a().mo27580b(scanResult);
        this.f12015ab = false;
        this.f12109cr.sendMessageDelayed(this.f12109cr.obtainMessage(14), 10000);
        mo27118a(b, "");
    }

    /* renamed from: a */
    public void mo27119a(WiFiScanResult wiFiScanResult) {
        BaseWifi a = WiFiService.m13974a().mo27572a(wiFiScanResult);
        this.f12015ab = false;
        this.f12109cr.sendMessageDelayed(this.f12109cr.obtainMessage(14), 10000);
        mo27118a(a, "");
    }

    /* renamed from: a */
    public void mo27118a(BaseWifi baseWifi, String str) {
        m13076bo();
        this.f11978O.removeMessages(2);
        Message obtainMessage = this.f11978O.obtainMessage(2);
        obtainMessage.obj = new Object[]{baseWifi, str};
        this.f11978O.sendMessage(obtainMessage);
    }

    /* renamed from: bq */
    private void m13078bq() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.NETWORK_IDS_CHANGED_ACTION);
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        GBApplication.instance().registerReceiver(this.f12108cq, intentFilter);
        GBApplication.instance().registerReceiver(this.f12111ct, new IntentFilter(Constant.f13241bP));
        GBApplication.instance().registerReceiver(this.f12112cu, new IntentFilter(CheckNetWorkReceiver.f12765b));
        GBApplication.instance().registerReceiver(this.f12113cv, new IntentFilter(CheckNetWorkReceiver.f12766c));
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(Constant.f13289cK);
        intentFilter2.addAction(Constant.f13290cL);
        GBApplication.instance().registerReceiver(this.f12114cw, intentFilter2);
        GBApplication.instance().registerReceiver(this.f12115cx, new IntentFilter(Constant.f13249bX));
    }

    /* renamed from: a */
    public void mo27122a(boolean z) {
        if (this.f12026am != z) {
            this.f12026am = z;
            m13079br();
            m13052bB();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: br */
    private void m13079br() {
        if (!this.f12026am) {
            mo27162an();
            mo27167as();
        }
    }

    /* renamed from: aS */
    public void mo27141aS() {
        Log.m14398b(f11954g, "onResume");
        if (this.f12109cr != null) {
            this.f12109cr.removeMessages(15);
            this.f12109cr.sendEmptyMessageDelayed(15, StatsManager.DEFAULT_TIMEOUT_MILLIS);
            this.f12109cr.removeMessages(40);
            this.f12109cr.sendEmptyMessageDelayed(40, 3000);
        }
        if (this.f11978O != null) {
            if (!this.f12014aa) {
                this.f11978O.removeMessages(11);
                this.f11978O.sendMessageDelayed(this.f11978O.obtainMessage(11), 0);
            } else {
                return;
            }
        }
        GiwifiMobclickAgent.onPageStart("连接界面");
    }

    /* renamed from: aT */
    public void mo27142aT() {
        GiwifiMobclickAgent.onPageEnd("连接界面");
    }

    /* renamed from: bs */
    private void m13080bs() {
        GBApplication.instance().unregisterReceiver(this.f12108cq);
        GBApplication.instance().unregisterReceiver(this.f12111ct);
        GBApplication.instance().unregisterReceiver(this.f12104cm);
        GBApplication.instance().unregisterReceiver(this.f12103cl);
        GBApplication.instance().unregisterReceiver(this.f12112cu);
        GBApplication.instance().unregisterReceiver(this.f12113cv);
        GBApplication.instance().unregisterReceiver(this.f12105cn);
        GBApplication.instance().unregisterReceiver(this.f12114cw);
        GBApplication.instance().unregisterReceiver(this.f12115cx);
        GBApplication.instance().unregisterReceiver(this.f12106co);
    }

    /* renamed from: aU */
    public void mo27143aU() {
        Log.m14403e(f11954g, "wifiFragment onDestroy...");
        if (this.f12029ap != null) {
            this.f12029ap.dismiss();
        }
        m13076bo();
        if (!SystemUtil.m14531e()) {
            m13080bs();
        } else if (this instanceof GiWiFiInfoHomeView) {
            m13080bs();
        }
        if (this.f12109cr != null) {
            this.f12109cr.removeCallbacksAndMessages(null);
        }
        if (this.f12117e != null) {
            this.f12117e.removeCallbacksAndMessages(null);
        }
        if (this.f11978O != null) {
            this.f11978O.getLooper().quit();
        }
        WiFiService.m13974a().mo27592g();
        WiFiService.m13974a().mo27584c(getContext());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$a */
    public class HandlerC3231a extends Handler {
        public HandlerC3231a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 2:
                    BaseWifi baseWifi = (BaseWifi) ((Object[]) message.obj)[0];
                    String obj = ((Object[]) message.obj)[1].toString();
                    if (!(baseWifi instanceof NoneWifi)) {
                        if (baseWifi instanceof PskWifi) {
                            ((PskWifi) baseWifi).setPassword(obj);
                        } else if (baseWifi instanceof WepWifi) {
                            ((WepWifi) baseWifi).setPassword(obj);
                        } else if (baseWifi instanceof EapWifi) {
                            ((EapWifi) baseWifi).setPassword(obj);
                        }
                    }
                    WiFiService.m13974a().mo27585c(baseWifi);
                    return;
                case 3:
                    Log.m14400c(BaseGiWiFiInfoView.f11954g, "CHECK_NET_STATE_WHAT check");
                    BaseGiWiFiInfoView.this.m13081bt();
                    return;
                case 4:
                    BaseGiWiFiInfoView.this.m13076bo();
                    if (CacheAuth.getInstance().isPortal()) {
                        BaseGiWiFiInfoView.this.f11983T = HttpUtil.m14346p(GBApplication.instance(), BaseGiWiFiInfoView.this.f12118f, BaseGiWiFiInfoView.f11955h);
                        BaseGiWiFiInfoView.this.f11978O.sendEmptyMessageDelayed(100, 10000);
                        return;
                    }
                    BaseGiWiFiInfoView.this.f11980Q = HttpUtil.m14296b(BaseGiWiFiInfoView.this.f12118f, BaseGiWiFiInfoView.f11955h);
                    BaseGiWiFiInfoView.this.f11978O.sendEmptyMessageDelayed(100, 10000);
                    return;
                case 11:
                    BaseGiWiFiInfoView.this.mo27140aR();
                    return;
                case 100:
                    C3470v.m14563a(BaseGiWiFiInfoView.this.f11977N, "当前网络故障");
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: j */
    private void m13117j(String str) {
        if (!GBActivity.dealException(CommonMsg.deSerializeJson(str.getBytes()))) {
            this.f11984U = 0;
            Log.m14398b(f11954g, "下线....");
            m13076bo();
            this.f11978O.removeMessages(100);
            mo27120a(this.f12120j);
            mo27183d(this.f11972I[8]);
            m13044b(EnumC3246p.fiveShow);
            CacheWiFi.getInstance().setCurrentState(WifiState.LOGIN);
            m13087bz();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: k */
    private void m13119k(String str) {
        try {
            JSONObject jSONObject = new JSONObject(EncryptUtil.decrypt(URLDecoder.decode(new JSONObject(str).getString("data"), "UTF-8")));
            if (jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                int i = jSONObject2.getInt("authState");
                jSONObject2.getInt("onlineTime");
                if (i != 2) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: l */
    private void m13121l(String str) {
        try {
            JSONObject jSONObject = new JSONObject(EncryptUtil.decrypt(URLDecoder.decode(new JSONObject(str).getString("data"), "UTF-8")));
            if (jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                String str2 = "";
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                if (jSONObject2.has("tips")) {
                    str2 = jSONObject2.getString("tips");
                }
                if (C3467s.m14513e(str2)) {
                    mo27117a(WifiState.SUCCESS, true);
                    return;
                }
                int i = 0;
                if (jSONObject2.has("authState")) {
                    i = jSONObject2.getInt("authState");
                }
                if (i == 200) {
                    mo27117a(WifiState.SUCCESS_NONET, true);
                } else {
                    mo27117a(WifiState.SUCCESS, true);
                }
                if (GBApplication.instance().getCurrentActivity() != null) {
                    GBApplication.instance().getCurrentActivity().showSecondDialog("提示", str2, "确定", new GBActivity.AbstractC2517a() {
                        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C319222 */

                        @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                        public void onClick(Dialog dialog, View view) {
                            dialog.dismiss();
                        }
                    }, new DialogInterface.OnCancelListener() {
                        /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.DialogInterface$OnCancelListenerC319424 */

                        public void onCancel(DialogInterface dialogInterface) {
                            dialogInterface.dismiss();
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: m */
    private void m13124m(String str) {
        try {
            if (new JSONObject(EncryptUtil.decrypt(URLDecoder.decode(new JSONObject(str).getString("data"), "UTF-8"))).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                this.f11984U = 0;
                Log.m14398b(f11954g, "下线....");
                m13076bo();
                this.f11978O.removeMessages(100);
                mo27120a(this.f12120j);
                mo27183d(this.f11972I[8]);
                m13044b(EnumC3246p.fiveShow);
                CacheWiFi.getInstance().setCurrentState(WifiState.LOGIN);
                m13087bz();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: n */
    private void m13125n(String str) {
        try {
            if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                if (this.f12102ck == null) {
                    this.f12102ck = GsonUtil.m14241a();
                }
                List<WindowConfigResponse.DataBean.WicketConfigListBean> wicket_config_list = ((WindowConfigResponse) this.f12102ck.mo21588a(str, WindowConfigResponse.class)).getData().getWicket_config_list();
                if (wicket_config_list.size() == 0) {
                    if (this.f12010aW != null) {
                        this.f12010aW.setVisibility(0);
                        this.f12010aW.setOnClickListener(new View.OnClickListener() {
                            /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC319525 */

                            public void onClick(View view) {
                                BaseGiWiFiInfoView.this.m13016a((BaseGiWiFiInfoView) 4, (int) "");
                            }
                        });
                    }
                    if (this.f12009aV != null) {
                        this.f12009aV.setVisibility(0);
                        this.f12009aV.setOnClickListener(new View.OnClickListener() {
                            /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC319626 */

                            public void onClick(View view) {
                                BaseGiWiFiInfoView.this.m13016a((BaseGiWiFiInfoView) 5, (int) "");
                            }
                        });
                    }
                    if (this.f12008aU != null) {
                        this.f12008aU.setVisibility(0);
                        this.f12008aU.setOnClickListener(new View.OnClickListener() {
                            /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC319727 */

                            public void onClick(View view) {
                                BaseGiWiFiInfoView.this.m13016a((BaseGiWiFiInfoView) 3, (int) "");
                            }
                        });
                        return;
                    }
                    return;
                }
                if (wicket_config_list.size() == 1) {
                    final WindowConfigResponse.DataBean.WicketConfigListBean wicketConfigListBean = wicket_config_list.get(0);
                    if (this.f12011aX != null) {
                        ImageTools.m14148a(wicketConfigListBean.getImg_url(), this.f12011aX);
                    }
                    if (this.f12066ba != null) {
                        this.f12066ba.setText(wicketConfigListBean.getTitle());
                    }
                    if (this.f12010aW != null) {
                        this.f12010aW.setVisibility(0);
                        this.f12010aW.setOnClickListener(new View.OnClickListener() {
                            /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC319828 */

                            public void onClick(View view) {
                                BaseGiWiFiInfoView.this.m13016a((BaseGiWiFiInfoView) wicketConfigListBean.getSource_type(), (int) wicketConfigListBean.getWap_url());
                            }
                        });
                    }
                    if (this.f12009aV != null) {
                        this.f12009aV.setVisibility(8);
                    }
                    if (this.f12008aU != null) {
                        this.f12008aU.setVisibility(8);
                    }
                }
                if (wicket_config_list.size() == 2) {
                    final WindowConfigResponse.DataBean.WicketConfigListBean wicketConfigListBean2 = wicket_config_list.get(0);
                    if (this.f12011aX != null) {
                        ImageTools.m14148a(wicketConfigListBean2.getImg_url(), this.f12011aX);
                    }
                    if (this.f12066ba != null) {
                        this.f12066ba.setText(wicketConfigListBean2.getTitle());
                    }
                    final WindowConfigResponse.DataBean.WicketConfigListBean wicketConfigListBean3 = wicket_config_list.get(1);
                    if (this.f12012aY != null) {
                        ImageTools.m14148a(wicketConfigListBean3.getImg_url(), this.f12012aY);
                    }
                    if (this.f12067bb != null) {
                        this.f12067bb.setText(wicketConfigListBean3.getTitle());
                    }
                    if (this.f12010aW != null) {
                        this.f12010aW.setVisibility(0);
                        this.f12010aW.setOnClickListener(new View.OnClickListener() {
                            /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC319929 */

                            public void onClick(View view) {
                                BaseGiWiFiInfoView.this.m13016a((BaseGiWiFiInfoView) wicketConfigListBean2.getSource_type(), (int) wicketConfigListBean2.getWap_url());
                            }
                        });
                    }
                    if (this.f12009aV != null) {
                        this.f12009aV.setVisibility(0);
                        this.f12009aV.setOnClickListener(new View.OnClickListener() {
                            /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC320130 */

                            public void onClick(View view) {
                                BaseGiWiFiInfoView.this.m13016a((BaseGiWiFiInfoView) wicketConfigListBean3.getSource_type(), (int) wicketConfigListBean3.getWap_url());
                            }
                        });
                    }
                    if (this.f12008aU != null) {
                        this.f12008aU.setVisibility(8);
                    }
                }
                if (wicket_config_list.size() >= 3) {
                    final WindowConfigResponse.DataBean.WicketConfigListBean wicketConfigListBean4 = wicket_config_list.get(0);
                    if (this.f12011aX != null) {
                        ImageTools.m14148a(wicketConfigListBean4.getImg_url(), this.f12011aX);
                    }
                    if (this.f12066ba != null) {
                        this.f12066ba.setText(wicketConfigListBean4.getTitle());
                    }
                    final WindowConfigResponse.DataBean.WicketConfigListBean wicketConfigListBean5 = wicket_config_list.get(1);
                    if (this.f12012aY != null) {
                        ImageTools.m14148a(wicketConfigListBean5.getImg_url(), this.f12012aY);
                    }
                    if (this.f12067bb != null) {
                        this.f12067bb.setText(wicketConfigListBean5.getTitle());
                    }
                    final WindowConfigResponse.DataBean.WicketConfigListBean wicketConfigListBean6 = wicket_config_list.get(2);
                    if (this.f12013aZ != null) {
                        ImageTools.m14148a(wicketConfigListBean6.getImg_url(), this.f12013aZ);
                    }
                    if (this.f12068bc != null) {
                        this.f12068bc.setText(wicketConfigListBean6.getTitle());
                    }
                    if (this.f12010aW != null) {
                        this.f12010aW.setVisibility(0);
                        this.f12010aW.setOnClickListener(new View.OnClickListener() {
                            /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC320231 */

                            public void onClick(View view) {
                                BaseGiWiFiInfoView.this.m13016a((BaseGiWiFiInfoView) wicketConfigListBean4.getSource_type(), (int) wicketConfigListBean4.getWap_url());
                            }
                        });
                    }
                    if (this.f12009aV != null) {
                        this.f12009aV.setVisibility(0);
                        this.f12009aV.setOnClickListener(new View.OnClickListener() {
                            /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC320332 */

                            public void onClick(View view) {
                                BaseGiWiFiInfoView.this.m13016a((BaseGiWiFiInfoView) wicketConfigListBean5.getSource_type(), (int) wicketConfigListBean5.getWap_url());
                            }
                        });
                    }
                    if (this.f12008aU != null) {
                        this.f12008aU.setVisibility(0);
                        this.f12008aU.setOnClickListener(new View.OnClickListener() {
                            /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.View$OnClickListenerC320433 */

                            public void onClick(View view) {
                                BaseGiWiFiInfoView.this.m13016a((BaseGiWiFiInfoView) wicketConfigListBean6.getSource_type(), (int) wicketConfigListBean6.getWap_url());
                            }
                        });
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13016a(int i, String str) {
        switch (i) {
            case 1:
            default:
                return;
            case 2:
                this.f11977N.startActivity(new Intent(GBApplication.instance(), AboutUsActivity.class));
                return;
            case 3:
                this.f11977N.startActivity(new Intent(GBApplication.instance(), SysStateActivity.class));
                return;
            case 4:
                Intent intent = new Intent(GBApplication.instance(), DeviceTest2Activity.class);
                intent.putExtra("type", "system_set");
                this.f11977N.startActivity(intent);
                return;
            case 5:
                this.f11977N.startActivity(new Intent(GBApplication.instance(), PingTestActivity.class));
                return;
            case 6:
                GBActivity.openBackWebView(str, "");
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bt */
    private void m13081bt() {
        m13076bo();
        MultiCheckNetWorkUtils.m13939a(GBApplication.instance(), this.f12100ci);
    }

    /* renamed from: bu */
    private void m13082bu() {
        WifiInfo connectionInfo = this.f12016ac.getConnectionInfo();
        String bssid = connectionInfo.getBSSID();
        String ssid = connectionInfo.getSSID();
        if (CheckUtil.m14084b(bssid) && !bssid.equals("00:00:00:00:00:00")) {
            String cacheBssidList = CacheApp.getInstance().getCacheBssidList();
            if (!cacheBssidList.contains(bssid.toUpperCase())) {
                CacheApp.getInstance().setCacheBssidList(cacheBssidList + bssid.toUpperCase() + ",");
            }
        }
        String cacheSsidList = CacheApp.getInstance().getCacheSsidList();
        if (!cacheSsidList.contains(ssid)) {
            CacheApp.getInstance().setCacheSsidList(cacheSsidList + ssid + ",");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0080, code lost:
        if (r5.f12020ag.contains(r2.toLowerCase()) != false) goto L_0x0082;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo27117a(com.gbcom.gwifi.functions.wifi.p253a.WifiState r6, boolean r7) {
        /*
        // Method dump skipped, instructions count: 308
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.mo27117a(com.gbcom.gwifi.functions.wifi.a.f, boolean):void");
    }

    /* renamed from: a */
    private void m13030a(boolean z, boolean z2) {
        if (!this.f12025al) {
            this.f12025al = true;
        }
        m13053bC();
        GBApplication.instance().sendBroadcast(new Intent(Constant.f13238bM));
        mo27183d(this.f11972I[10]);
        Log.m14400c(f11954g, "check version broadcast start.....");
        GBApplication.instance().sendBroadcast(new Intent(Constant.f13232bG));
        m13074bm();
        if (!z) {
            m13020a(EnumC3247q.GwifiShow);
        } else if (z2) {
            m13020a(EnumC3247q.CheckNetwork);
        } else {
            m13082bu();
            m13020a(EnumC3247q.OneKeyShow);
            mo27120a(this.f12119i);
            this.f12109cr.removeMessages(31);
            this.f12109cr.sendEmptyMessage(31);
            int onlineTime = CacheAuth.getInstance().getOnlineTime();
            if (onlineTime >= 0) {
                this.f12022ai = System.currentTimeMillis() - ((long) (onlineTime * 1000));
                m13085bx();
                m13053bC();
            }
        }
    }

    /* renamed from: b */
    private void m13050b(boolean z, boolean z2) {
        if (!this.f12025al) {
            this.f12025al = true;
        }
        m13053bC();
        mo27183d(this.f11972I[16]);
        m13074bm();
        if (!z) {
            m13020a(EnumC3247q.GwifiShow);
        } else if (z2) {
            m13020a(EnumC3247q.CheckNetwork);
        } else {
            m13082bu();
            m13020a(EnumC3247q.OneKeyShow);
            mo27120a(this.f12119i);
        }
    }

    /* renamed from: a */
    public void mo27116a(WifiState fVar) {
        Log.m14398b(f11954g, "changeWifiState " + fVar.toString());
        m13087bz();
        CacheWiFi.getInstance().setCurrentState(fVar);
        switch (fVar) {
            case CLOSED:
                this.f12109cr.removeMessages(9);
                mo27183d(this.f11972I[0]);
                mo27120a(this.f12120j);
                mo27122a(false);
                return;
            case CLOSING:
                Log.m14398b(f11954g, "NOWIFI changeWifiState CLOSING");
                mo27183d(this.f11972I[4]);
                return;
            case OPENING:
            case TRY_CONNECT:
            default:
                return;
            case OPENED:
                mo27122a(true);
                return;
            case DISCONNECTED:
                Log.m14398b(f11954g, "NOWIFI changeWifiState DISCONNECTED");
                mo27183d(this.f11972I[4]);
                if (!m13063bM()) {
                    m13052bB();
                    return;
                }
                return;
            case CONNECTING:
                mo27183d(this.f11972I[5]);
                return;
            case CONNECTED:
                synchronized (this.f11976M) {
                    this.f11976M = Long.valueOf(System.currentTimeMillis());
                }
                this.f11984U = 0;
                m13076bo();
                mo27183d("检查是否可以上网...");
                m13084bw();
                return;
            case CHECKING:
                mo27183d("检查是否可以上网...");
                return;
            case AUTH_FAILED:
                if (this.f12018ae == null) {
                    return;
                }
                if (WiFi.m13960a(this.f12018ae.getCapabilities()).equals(WiFi.f13010d)) {
                    WifiConfiguration a = WiFi.m13957a(this.f12016ac, this.f12018ae, (String) null);
                    if (a != null) {
                        this.f12016ac.removeNetwork(a.networkId);
                        this.f12016ac.saveConfiguration();
                    }
                    if (this.f12019af < 1) {
                        GBActivity.showMessageToast("WIFI无法连接，请重新尝试");
                        return;
                    }
                    this.f12019af++;
                    mo27118a(this.f12018ae, "");
                    return;
                }
                WifiConfiguration a2 = WiFi.m13957a(this.f12016ac, this.f12018ae, (String) null);
                if (a2 != null) {
                    this.f12016ac.removeNetwork(a2.networkId);
                    this.f12016ac.saveConfiguration();
                }
                m13049b(true);
                return;
        }
    }

    /* renamed from: bv */
    private void m13083bv() {
        View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.wifi_dialog_auth, (ViewGroup) null);
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.showNormalDialog(CacheWiFi.getInstance().getCurrentSsid() + "需要认证", inflate, "认证", "取消", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.C320736 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    GBActivity.openBackWebView("http://www.baidu.com", "网页认证");
                }
            }, (GBActivity.AbstractC2517a) null);
        }
    }

    /* renamed from: e */
    public String mo27185e(String str) {
        if (str == null) {
            return null;
        }
        return str.replace("\"", "");
    }

    /* renamed from: f */
    public boolean mo27187f(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        String replaceAll = str.replaceAll("\"", "");
        if (replaceAll.equalsIgnoreCase("<unknown ssid>") || replaceAll.equals(f11953c) || "0x".equals(replaceAll) || replaceAll.contains("nvram warning") || replaceAll.contains("NVRAM WARNING")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private String m13011a(WifiInfo wifiInfo, NetworkInfo networkInfo) {
        String str = null;
        if (wifiInfo != null) {
            str = wifiInfo.getSSID().replace("\"", "");
        }
        if (!mo27187f(str) && networkInfo != null && networkInfo.isConnected() && 1 == networkInfo.getType()) {
            String extraInfo = networkInfo.getExtraInfo();
            Log.m14400c(f11954g, "extraInfo:" + extraInfo);
            if (!C3467s.m14513e(extraInfo)) {
                str = extraInfo.replace("\"", "");
            }
        }
        Log.m14400c(f11954g, "getSsid ssid:" + str);
        return str;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: o */
    private void m13128o(String str) {
        this.f11987a.lock();
        try {
            if (this.f12090by > 0) {
                if (str == null) {
                    this.f11987a.unlock();
                } else if (!mo27187f(str)) {
                    this.f11987a.unlock();
                } else if (!this.f12091bz.trim().equals(str.trim())) {
                    this.f11987a.unlock();
                } else {
                    if (1 == this.f12090by) {
                        if (Build.VERSION.SDK_INT >= 29) {
                            this.f12090by = 3;
                            this.f12109cr.sendEmptyMessageDelayed(43, 3000);
                        } else {
                            GBActivity.showMessageLongToast("切换到游戏专线成功，如无法上网可手动认证");
                        }
                    } else if (2 == this.f12090by) {
                        if (Build.VERSION.SDK_INT >= 29) {
                            this.f12090by = 4;
                            this.f12109cr.sendEmptyMessageDelayed(44, 3000);
                        } else {
                            GBActivity.showMessageToast("切换到普通线路成功，如无法上网可手动认证");
                        }
                    }
                    if (Build.VERSION.SDK_INT < 29) {
                        this.f12090by = 0;
                        this.f12091bz = "";
                        if (this.f12040bA > 0) {
                            this.f12016ac.removeNetwork(this.f12040bA);
                            this.f12016ac.saveConfiguration();
                        }
                        this.f12040bA = -1;
                    }
                    this.f11987a.unlock();
                }
            }
        } finally {
            this.f11987a.unlock();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: p */
    private void m13130p(String str) {
        this.f12116d.lock();
        try {
            if (this.f12047bH > 0 && str != null && mo27187f(str) && this.f12048bI.trim().equals(str.trim())) {
                if (1 == this.f12047bH) {
                    if (Build.VERSION.SDK_INT >= 29) {
                        this.f12090by = 2;
                        this.f12109cr.sendEmptyMessageDelayed(51, 3000);
                    } else {
                        GBActivity.showMessageLongToast("切换到" + getResources().getString(C2425R.C2429string.wifi_name) + "网络成功，如无法上网可手动认证");
                    }
                }
                if (Build.VERSION.SDK_INT < 29) {
                    this.f12047bH = 0;
                    this.f12048bI = "";
                    if (this.f12049bJ > 0) {
                        this.f12016ac.removeNetwork(this.f12049bJ);
                        this.f12016ac.saveConfiguration();
                    }
                    this.f12049bJ = -1;
                }
            }
        } finally {
            this.f12116d.unlock();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bw */
    private void m13084bw() {
        this.f12014aa = true;
        this.f11984U++;
        this.f11978O.removeMessages(3);
        this.f11978O.sendMessageDelayed(this.f11978O.obtainMessage(3), 500);
    }

    /* renamed from: a */
    public void mo27101a(long j) {
        if (this.f12026am) {
            if (this.f12099ch && this.f12109cr != null) {
                this.f12109cr.removeMessages(15);
                this.f12109cr.sendEmptyMessageDelayed(15, StatsManager.DEFAULT_TIMEOUT_MILLIS);
            }
            if (!this.f12014aa && this.f11978O != null) {
                this.f11978O.removeMessages(11);
                this.f11978O.sendMessageDelayed(this.f11978O.obtainMessage(11), j);
            }
        }
    }

    /* renamed from: bx */
    private void m13085bx() {
        if (!this.f12027an) {
            this.f12027an = true;
            mo27169au();
            this.f12109cr.sendMessage(this.f12109cr.obtainMessage(6));
        }
    }

    /* renamed from: by */
    private void m13086by() {
        if (!this.f12028ao) {
            this.f12028ao = true;
            mo27169au();
            this.f12109cr.sendMessage(this.f12109cr.obtainMessage(7));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bz */
    private void m13087bz() {
        if (this.f12027an) {
            this.f12027an = false;
            mo27120a(this.f12120j);
            if (GBApplication.instance().getCurrentActivity() != null) {
                mo27169au();
            }
            if (this.f12028ao) {
                this.f12028ao = false;
            }
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$n */
    class C3244n implements Comparator<BaseWifi> {
        C3244n() {
        }

        /* renamed from: a */
        public int compare(BaseWifi baseWifi, BaseWifi baseWifi2) {
            if (baseWifi == baseWifi2) {
                return 0;
            }
            if (!CacheWiFi.getInstance().getCurrentBssid().equals("")) {
                if (CacheWiFi.getInstance().getCurrentBssid().equalsIgnoreCase(baseWifi.getBssid())) {
                    return -1;
                }
                if (CacheWiFi.getInstance().getCurrentBssid().equalsIgnoreCase(baseWifi2.getBssid())) {
                    return 1;
                }
            }
            if (!CacheWiFi.getInstance().getCurrentSsid().equals("")) {
                if (CacheWiFi.getInstance().getCurrentSsid().equalsIgnoreCase(baseWifi.getSsid())) {
                    return -1;
                }
                if (CacheWiFi.getInstance().getCurrentSsid().equalsIgnoreCase(baseWifi2.getSsid())) {
                    return 1;
                }
            }
            if (WiFi.m13960a(baseWifi.getCapabilities()).equals(WiFi.f13010d) && !WiFi.m13960a(baseWifi2.getCapabilities()).equals(WiFi.f13010d)) {
                return -1;
            }
            if (WiFi.m13960a(baseWifi2.getCapabilities()).equals(WiFi.f13010d) && !WiFi.m13960a(baseWifi.getCapabilities()).equals(WiFi.f13010d)) {
                return 1;
            }
            String cacheBssidList = CacheApp.getInstance().getCacheBssidList();
            if (cacheBssidList.contains(baseWifi.getBssid().toUpperCase()) && !cacheBssidList.contains(baseWifi2.getBssid().toUpperCase())) {
                return -1;
            }
            if (!cacheBssidList.contains(baseWifi.getBssid().toUpperCase()) && cacheBssidList.contains(baseWifi2.getBssid().toUpperCase())) {
                return 1;
            }
            int calculateSignalLevel = WifiManager.calculateSignalLevel(baseWifi.getLevel(), 4);
            int calculateSignalLevel2 = WifiManager.calculateSignalLevel(baseWifi2.getLevel(), 4);
            if (calculateSignalLevel == calculateSignalLevel2) {
                return baseWifi.getSsid().compareToIgnoreCase(baseWifi2.getSsid());
            }
            return calculateSignalLevel <= calculateSignalLevel2 ? 1 : -1;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bA */
    private void m13051bA() {
        boolean bM = m13063bM();
        WifiInfo connectionInfo = this.f12016ac.getConnectionInfo();
        String av = mo27170av();
        String aw = mo27171aw();
        if (bM && (aw.equals("无网络") || aw.equals(this.f11972I[4]) || WiFiUtil.m14028b(av))) {
            mo27183d(this.f11972I[5]);
            CacheWiFi.getInstance().setCurrentState(WifiState.TRY_CONNECT);
            m13056bF();
            m13081bt();
            String a = m13011a(connectionInfo, this.f12017ad.getActiveNetworkInfo());
            if (a == null || !a.equals(this.f11975L[0])) {
                m13028a(a, connectionInfo.getBSSID());
            } else {
                m13132q(connectionInfo.getBSSID());
            }
        }
        String aw2 = mo27171aw();
        if (bM && aw2.equals(this.f11972I[5]) && CacheWiFi.getInstance().getCurrentState() == WifiState.SUCCESS) {
            mo27183d(this.f11972I[10]);
            m13053bC();
            if (Config.m14094a().mo27812o() && Config.m14094a().mo27811n()) {
                Log.m14400c(f11954g, "check version broadcast start.....");
                GBApplication.instance().sendBroadcast(new Intent(Constant.f13232bG));
            }
        }
        String av2 = mo27170av();
        if ((!mo27187f(av2) || av2.equals("无网络")) && bM && connectionInfo != null && connectionInfo.getSSID() != null) {
            String ssid = connectionInfo.getSSID();
            Log.m14398b(f11954g, "checkWifiConnectState ssid=" + ssid);
            if (ssid == null || !ssid.equals(this.f11975L[0])) {
                m13028a(ssid, connectionInfo.getBSSID());
            } else {
                m13132q(connectionInfo.getBSSID());
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m13094c(boolean z) {
        m13087bz();
        if (z) {
            m13076bo();
            if (LogoutCode.m13901a(CacheAuth.getInstance().getLogoutReason())) {
                GBGlobalConfig.m10510c().mo24408h();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bB */
    private synchronized void m13052bB() {
        String t = WiFiUtil.m14021a().mo27626t();
        Log.m14398b(f11954g, "initNetwork isWifiOpen=" + this.f12026am + ",networkType=" + t);
        if (!this.f12026am && t.equals("")) {
            CacheWiFi.getInstance().setCurrentState(WifiState.CLOSED);
            m13044b(EnumC3246p.unconnectShow);
            mo27181c("");
            CacheWiFi.getInstance().setApLocation("");
            mo27183d(this.f11972I[14]);
            m13020a(EnumC3247q.wifiBtnShow);
        } else if (!this.f12026am && !t.equals("")) {
            CacheAuth.getInstance().resetCacheAuthBean();
            CacheWiFi.getInstance().setCurrentState(WifiState.CLOSED);
            m13044b(EnumC3246p.mobileShow);
            mo27181c(t);
            CacheWiFi.getInstance().setApLocation("");
            mo27183d(this.f11972I[10]);
            m13020a(EnumC3247q.wifiBtnShow);
            if (Config.m14094a().mo27812o() && Config.m14094a().mo27811n()) {
                Log.m14400c(f11954g, "check version broadcast start.....");
                GBApplication.instance().sendBroadcast(new Intent(Constant.f13232bG));
            }
        } else if (this.f12026am && t.equals("")) {
            CacheWiFi.getInstance().setCurrentState(WifiState.DISCONNECTED);
            m13044b(EnumC3246p.unconnectShow);
            mo27181c("");
            CacheWiFi.getInstance().setApLocation("");
            Log.m14398b(f11954g, "NOWIFI initNetwork wifi Open But networkType empty");
            mo27183d(this.f11972I[4]);
            m13020a(EnumC3247q.GwifiShow);
        } else if (this.f12026am && !t.equals("") && !t.equals("WIFI")) {
            CacheAuth.getInstance().resetCacheAuthBean();
            CacheWiFi.getInstance().setCurrentState(WifiState.DISCONNECTED);
            m13044b(EnumC3246p.mobileShow);
            mo27121a("00:00:00", false);
            mo27183d(this.f11972I[10]);
            mo27181c(t);
            CacheWiFi.getInstance().setApLocation("");
            m13020a(EnumC3247q.GwifiShow);
            m13053bC();
            if (Config.m14094a().mo27812o() && Config.m14094a().mo27811n()) {
                Log.m14400c(f11954g, "check version broadcast start.....");
                GBApplication.instance().sendBroadcast(new Intent(Constant.f13232bG));
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bC */
    private synchronized void m13053bC() {
        WifiInfo connectionInfo = ((WifiManager) GBApplication.instance().getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo != null && WiFiUtil.m14021a().mo27626t().equals("WIFI")) {
            switch (WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 4)) {
                case 0:
                    m13044b(EnumC3246p.oneShow);
                    break;
                case 1:
                    m13044b(EnumC3246p.twoShow);
                    break;
                case 2:
                    m13044b(EnumC3246p.threeShow);
                    break;
                case 3:
                    m13044b(EnumC3246p.fourShow);
                    break;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private synchronized void m13044b(EnumC3246p pVar) {
        m13054bD();
        switch (pVar) {
            case unconnectShow:
                mo27172ax();
                break;
            case oneShow:
                mo27123aA();
                break;
            case twoShow:
                mo27125aC();
                break;
            case threeShow:
                mo27127aE();
                break;
            case fourShow:
                mo27129aG();
                break;
            case fiveShow:
                mo27131aI();
                break;
            case sixShow:
                mo27133aK();
                break;
            case sevenShow:
                mo27135aM();
                break;
            case mobileShow:
                mo27137aO();
                break;
        }
        CacheWiFi.getInstance().setWifiControlState(pVar.mo27260a());
    }

    /* renamed from: bD */
    private void m13054bD() {
        mo27173ay();
        mo27174az();
        mo27124aB();
        mo27126aD();
        mo27128aF();
        mo27130aH();
        mo27132aJ();
        mo27134aL();
        mo27136aN();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private synchronized void m13020a(EnumC3247q qVar) {
        m13056bF();
        m13055bE();
        this.f12051bL = qVar;
        switch (qVar) {
            case wifiBtnShow:
                mo27167as();
                mo27166ar();
                break;
            case OneKeyShow:
                mo27158aj();
                mo27160al();
                mo27163ao();
                break;
            case GwifiShow:
                if (CacheGame.getInstance().getGameConfig() != null) {
                    CacheAuth.getInstance().setGwId("");
                    GBApplication.instance().sendBroadcast(new Intent(Constant.f13249bX));
                }
                if (SystemUtil.m14521a() && CacheWiFi.getInstance().isInnerWifi()) {
                    mo27120a(this.f12119i);
                    mo27160al();
                    mo27158aj();
                    break;
                } else {
                    mo27153ae();
                    mo27156ah();
                    break;
                }
            case CheckNetwork:
                mo27149aa();
                mo27152ad();
                break;
            case RELEASE:
                mo27159ak();
                mo27160al();
                mo27164ap();
                break;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$49 */
    public static /* synthetic */ class C322149 {

        /* renamed from: b */
        static final /* synthetic */ int[] f12179b = new int[NetworkInfo.DetailedState.values().length];

        static {
            f12181d = new int[EnumC3247q.values().length];
            try {
                f12181d[EnumC3247q.wifiBtnShow.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f12181d[EnumC3247q.OneKeyShow.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f12181d[EnumC3247q.GwifiShow.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f12181d[EnumC3247q.CheckNetwork.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f12181d[EnumC3247q.RELEASE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            f12180c = new int[EnumC3246p.values().length];
            try {
                f12180c[EnumC3246p.unconnectShow.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f12180c[EnumC3246p.oneShow.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f12180c[EnumC3246p.twoShow.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f12180c[EnumC3246p.threeShow.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f12180c[EnumC3246p.fourShow.ordinal()] = 5;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f12180c[EnumC3246p.fiveShow.ordinal()] = 6;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f12180c[EnumC3246p.sixShow.ordinal()] = 7;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f12180c[EnumC3246p.sevenShow.ordinal()] = 8;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f12180c[EnumC3246p.mobileShow.ordinal()] = 9;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f12179b[NetworkInfo.DetailedState.AUTHENTICATING.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f12179b[NetworkInfo.DetailedState.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f12179b[NetworkInfo.DetailedState.CONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError e17) {
            }
            try {
                f12179b[NetworkInfo.DetailedState.DISCONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError e18) {
            }
            try {
                f12179b[NetworkInfo.DetailedState.DISCONNECTING.ordinal()] = 5;
            } catch (NoSuchFieldError e19) {
            }
            try {
                f12179b[NetworkInfo.DetailedState.FAILED.ordinal()] = 6;
            } catch (NoSuchFieldError e20) {
            }
            try {
                f12179b[NetworkInfo.DetailedState.IDLE.ordinal()] = 7;
            } catch (NoSuchFieldError e21) {
            }
            try {
                f12179b[NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal()] = 8;
            } catch (NoSuchFieldError e22) {
            }
            try {
                f12179b[NetworkInfo.DetailedState.SUSPENDED.ordinal()] = 9;
            } catch (NoSuchFieldError e23) {
            }
            f12178a = new int[WifiState.values().length];
            try {
                f12178a[WifiState.LOGIN.ordinal()] = 1;
            } catch (NoSuchFieldError e24) {
            }
            try {
                f12178a[WifiState.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError e25) {
            }
            try {
                f12178a[WifiState.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError e26) {
            }
            try {
                f12178a[WifiState.SUCCESS_NONET.ordinal()] = 4;
            } catch (NoSuchFieldError e27) {
            }
            try {
                f12178a[WifiState.CLOSED.ordinal()] = 5;
            } catch (NoSuchFieldError e28) {
            }
            try {
                f12178a[WifiState.CLOSING.ordinal()] = 6;
            } catch (NoSuchFieldError e29) {
            }
            try {
                f12178a[WifiState.OPENING.ordinal()] = 7;
            } catch (NoSuchFieldError e30) {
            }
            try {
                f12178a[WifiState.OPENED.ordinal()] = 8;
            } catch (NoSuchFieldError e31) {
            }
            try {
                f12178a[WifiState.TRY_CONNECT.ordinal()] = 9;
            } catch (NoSuchFieldError e32) {
            }
            try {
                f12178a[WifiState.DISCONNECTED.ordinal()] = 10;
            } catch (NoSuchFieldError e33) {
            }
            try {
                f12178a[WifiState.CONNECTING.ordinal()] = 11;
            } catch (NoSuchFieldError e34) {
            }
            try {
                f12178a[WifiState.CONNECTED.ordinal()] = 12;
            } catch (NoSuchFieldError e35) {
            }
            try {
                f12178a[WifiState.CHECKING.ordinal()] = 13;
            } catch (NoSuchFieldError e36) {
            }
            try {
                f12178a[WifiState.AUTH_FAILED.ordinal()] = 14;
            } catch (NoSuchFieldError e37) {
            }
        }
    }

    /* renamed from: bE */
    private void m13055bE() {
        mo27165aq();
        mo27157ai();
        mo27155ag();
        mo27151ac();
        mo27163ao();
    }

    /* renamed from: bF */
    private void m13056bF() {
        mo27168at();
        mo27159ak();
        mo27168at();
        mo27150ab();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bG */
    private void m13057bG() {
        mo27167as();
        mo27158aj();
        mo27153ae();
        mo27149aa();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: q */
    private void m13132q(String str) {
        List<ScanResult> scanResults = this.f12016ac.getScanResults();
        if (!(scanResults == null || str == null)) {
            for (int i = 0; i < scanResults.size(); i++) {
                ScanResult scanResult = scanResults.get(i);
                if (str.equals(scanResult.BSSID)) {
                    m13028a(scanResult.SSID, scanResult.BSSID);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13028a(String str, String str2) {
        GBActivity currentActivity;
        String e = mo27185e(str);
        if (e != null) {
            if (!mo27187f(e)) {
                if (!GBActivity.checkPermissions(GBApplication.instance().getApplicationContext(), new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})) {
                    e = f11953c;
                }
            }
            mo27181c(e);
        }
        if (!str2.equals(CacheWiFi.getInstance().getLastApBssid()) && (currentActivity = GBApplication.instance().getCurrentActivity()) != null && (currentActivity instanceof MainActivity)) {
            ((MainActivity) currentActivity).checkWifiLocation();
            CacheWiFi.getInstance().setLastApBssid(str2);
        }
    }

    /* renamed from: bH */
    private PackageInfo m13058bH() {
        try {
            return GBApplication.instance().getPackageManager().getPackageInfo(GBApplication.instance().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bI */
    private boolean m13059bI() {
        return true;
    }

    /* renamed from: c */
    private boolean m13096c(HashMap<String, Object> hashMap) {
        String str = (String) hashMap.get(TbsReaderView.KEY_FILE_PATH);
        if (str != null && !str.equals("") && new File(str).exists()) {
            return true;
        }
        CacheApp.getInstance().removeDownloadVersionBackground();
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: aV */
    public void mo27144aV() {
        System.exit(0);
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView$o */
    private class C3245o extends BroadcastReceiver {
        private C3245o() {
        }

        public void onReceive(Context context, Intent intent) {
            BaseGiWiFiInfoView.this.m13075bn();
        }
    }

    /* renamed from: a */
    public synchronized void mo27103a(ImageView imageView, EnumC3246p pVar) {
        switch (pVar) {
            case unconnectShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_0_title);
                break;
            case oneShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_1_title);
                break;
            case twoShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_2_title);
                break;
            case threeShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_3_title);
                break;
            case fourShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_4_title);
                break;
            case fiveShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_5_title);
                break;
            case sixShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_6_title);
                break;
            case sevenShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_7_title);
                break;
            case mobileShow:
                imageView.setImageResource(C2425R.C2426drawable.mobile_network_title);
                break;
        }
    }

    /* renamed from: b */
    public synchronized void mo27176b(ImageView imageView, EnumC3246p pVar) {
        switch (pVar) {
            case unconnectShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_0);
                break;
            case oneShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_1);
                break;
            case twoShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_2);
                break;
            case threeShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_3);
                break;
            case fourShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_4);
                break;
            case fiveShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_5);
                break;
            case sixShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_6);
                break;
            case sevenShow:
                imageView.setImageResource(C2425R.C2426drawable.wifi_7);
                break;
            case mobileShow:
                imageView.setImageResource(C2425R.C2426drawable.mobile_network);
                break;
        }
    }

    /* renamed from: aW */
    public String mo27145aW() {
        if (this.f12069bd != null) {
            return this.f12069bd.getText().toString();
        }
        return "";
    }

    /* renamed from: aX */
    public AbstractC3237g mo27146aX() {
        return this.f12053bN;
    }

    /* renamed from: a */
    private void m13015a(int i) {
        int i2 = 1;
        switch (i) {
            case 1:
            case 3:
                break;
            case 2:
                i2 = 2;
                break;
            default:
                i2 = 0;
                break;
        }
        this.f12071bf = i2;
        CacheAuth.getInstance().setAuthMode(i2);
        if (i != 3) {
            this.f12072bg = i;
        }
    }

    /* renamed from: bJ */
    private void m13060bJ() {
        if (!this.f12070be) {
            WiFiService.m13974a().mo27591f();
        }
        this.f12070be = true;
        m13015a(1);
        mo27148aZ();
        if (this.f12075bj != null) {
            this.f12075bj.setText("进入游戏专线");
        }
        if (this.f12073bh != null) {
            this.f12073bh.setVisibility(0);
        }
        if (this.f12074bi != null) {
            this.f12074bi.setVisibility(0);
        }
        if (this.f12077bl != null) {
            this.f12077bl.setVisibility(8);
        }
        if (this.f12078bm != null) {
            this.f12078bm.setVisibility(8);
        }
    }

    /* renamed from: bK */
    private void m13061bK() {
        if (!this.f12070be) {
            WiFiService.m13974a().mo27591f();
        }
        this.f12070be = true;
        m13015a(2);
        mo27148aZ();
        if (this.f12075bj != null) {
            this.f12075bj.setText("退出游戏专线");
        }
        if (this.f12073bh != null) {
            this.f12073bh.setVisibility(0);
        }
        if (this.f12074bi != null) {
            this.f12074bi.setVisibility(0);
        }
        if (this.f12077bl != null) {
            this.f12077bl.setVisibility(8);
        }
        if (this.f12078bm != null) {
            this.f12078bm.setVisibility(8);
        }
    }

    /* renamed from: bL */
    private void m13062bL() {
        if (this.f12070be) {
            WiFiService.m13974a().mo27592g();
        }
        this.f12070be = false;
        m13015a(3);
        mo27147aY();
        if (this.f12073bh != null) {
            this.f12073bh.setVisibility(8);
        }
        if (this.f12074bi != null) {
            this.f12074bi.setVisibility(8);
        }
        if (this.f12077bl != null) {
            this.f12077bl.setVisibility(0);
        }
        if (this.f12078bm != null) {
            this.f12078bm.setVisibility(0);
        }
    }

    /* renamed from: ba */
    public void mo27178ba() {
        if (this.f12016ac == null) {
            this.f12016ac = (WifiManager) GBApplication.instance().getApplicationContext().getSystemService("wifi");
        }
        if (!this.f12016ac.isWifiEnabled()) {
            Log.m14403e(f11954g, "checkGameControl: wifi not open");
            m13062bL();
            return;
        }
        WifiInfo connectionInfo = this.f12016ac.getConnectionInfo();
        if (connectionInfo == null) {
            Log.m14403e(f11954g, "checkGameControl: connection is null");
            m13062bL();
            return;
        }
        String a = m13011a(connectionInfo, this.f12017ad.getActiveNetworkInfo());
        String bssid = connectionInfo.getBSSID();
        Log.m14400c(f11954g, " doCheckGameControl ssid:" + a);
        Log.m14400c(f11954g, " doCheckGameControl bssid:" + bssid);
        if (!mo27187f(a)) {
            Log.m14403e(f11954g, "checkGameControl: ssid is invalid," + a);
            m13062bL();
            return;
        }
        m13028a(a, bssid);
        GameConfig gameConfig = CacheGame.getInstance().getGameConfig();
        if (gameConfig == null) {
            Log.m14403e(f11954g, "checkGameControl: gameConfig is null");
            m13062bL();
        } else if (gameConfig.mo24523b() == 0) {
            Log.m14403e(f11954g, "checkGameControl: gameConfig game disable");
            m13062bL();
        } else {
            this.f12083br = gameConfig.mo24528d();
            if (gameConfig.mo24529e().size() == 0) {
                Log.m14403e(f11954g, "checkGameControl: gameConfig game filter list empty");
                m13062bL();
                return;
            }
            String c = gameConfig.mo24526c();
            if (c.equals("")) {
                Log.m14403e(f11954g, "checkGameControl: gameConfig game ssid is empty");
                m13062bL();
                return;
            }
            this.f12084bs = gameConfig.mo24530f();
            if (a.indexOf(c) != -1) {
                Log.m14398b(f11954g, "checkGameControl: showGameExitControl");
                m13061bK();
            } else {
                Log.m14398b(f11954g, "checkGameControl: showGameAcceControl");
                m13060bJ();
            }
            if (this.f12051bL != null) {
                switch (this.f12051bL) {
                    case wifiBtnShow:
                    case GwifiShow:
                        Log.m14403e(f11954g, "checkGameControl: not support game wifi");
                        m13062bL();
                        return;
                    case OneKeyShow:
                    default:
                        return;
                }
            }
        }
    }

    /* renamed from: bb */
    public void mo27179bb() {
        this.f12109cr.removeMessages(40);
        this.f12109cr.sendEmptyMessage(40);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: bM */
    private boolean m13063bM() {
        return WiFiUtil.m14021a().mo27621o();
    }
}
