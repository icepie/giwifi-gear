package com.gbcom.gwifi.functions.loading;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.p009v4.app.ActivityCompat;
import android.support.p009v4.app.Fragment;
import android.support.p009v4.content.ContextCompat;
import android.support.p009v4.view.PagerAdapter;
import android.support.p012v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import com.gbcom.edu.functionModule.main.circle.activitys.MessageCommentActivity;
import com.gbcom.edu.functionModule.main.circle.activitys.MessageLikeActivity;
import com.gbcom.edu.functionModule.main.circle.bean.CircleUserBean;
import com.gbcom.edu.functionModule.main.manager.ISDKUser;
import com.gbcom.edu.functionModule.main.manager.SDKUserManager;
import com.gbcom.edu.p219a.CircleFragment;
import com.gbcom.edu.p219a.MessageFragment;
import com.gbcom.edu.p220b.BadgeHelper;
import com.gbcom.edu.p220b.Const;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.FragmentTabActivity;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.codec.MD5Util;
import com.gbcom.gwifi.functions.aboutus.AboutUsActivity;
import com.gbcom.gwifi.functions.edu.domain.NotifyBean;
import com.gbcom.gwifi.functions.loading.domain.NotifyWhenAppExit;
import com.gbcom.gwifi.functions.loading.domain.TabDatas;
import com.gbcom.gwifi.functions.notify.p245a.NotifyDao;
import com.gbcom.gwifi.functions.ping.PingTestActivity;
import com.gbcom.gwifi.functions.product.AppActivity;
import com.gbcom.gwifi.functions.product.AppDownloadActivity;
import com.gbcom.gwifi.functions.product.GameActivity;
import com.gbcom.gwifi.functions.product.NewsActivity;
import com.gbcom.gwifi.functions.system.SysStateActivity;
import com.gbcom.gwifi.functions.template.builder.ViewBuilderFactory;
import com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView;
import com.gbcom.gwifi.functions.template.fragment.EduFragment;
import com.gbcom.gwifi.functions.template.fragment.FindTabFragment;
import com.gbcom.gwifi.functions.template.fragment.FirstTabFragment;
import com.gbcom.gwifi.functions.template.fragment.GiWiFiInfoExView;
import com.gbcom.gwifi.functions.template.fragment.GiWiFiInfoHomeView;
import com.gbcom.gwifi.functions.template.fragment.HomeFragment;
import com.gbcom.gwifi.functions.template.fragment.OfficeFragment;
import com.gbcom.gwifi.functions.template.fragment.SecondTabFragment;
import com.gbcom.gwifi.functions.template.p252a.CommonNotifyClickListener;
import com.gbcom.gwifi.functions.test.DeviceTest2Activity;
import com.gbcom.gwifi.p235c.p236a.FloatViewListener;
import com.gbcom.gwifi.p235c.p236a.FloatWindowManager;
import com.gbcom.gwifi.p235c.p236a.IFloatView;
import com.gbcom.gwifi.p235c.p237b.FloatingPermissionCompat;
import com.gbcom.gwifi.third.kefu.easemob.GiwifiHXService;
import com.gbcom.gwifi.third.kefu.easemob.GwifiHXHelper;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent;
import com.gbcom.gwifi.third.umeng.push.GiwifiPushService;
import com.gbcom.gwifi.third.umeng.share.GiwifiShareApi;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Config;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.FormatUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.NetworkUtils;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.VersionUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAd;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheEdu;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.util.p257b.ScreenUtil;
import com.gbcom.gwifi.util.p257b.StatusBarUtil;
import com.gbcom.gwifi.widget.JazzyViewPager;
import com.gbcom.gwifi.widget.OutlineContainer;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.p136b.p137a.Gson;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;
import p459me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends FragmentTabActivity {
    public static int AFTER_TRY_USER_SHOW_TIME = 100;
    public static String completeInfoMsg = "";
    public static String completeInfoUrl = "";

    /* renamed from: g */
    private static final String f9595g = "MainActivity";
    public static boolean needCompleteInfo = false;

    /* renamed from: A */
    private int f9596A = 0;

    /* renamed from: B */
    private boolean f9597B = false;

    /* renamed from: C */
    private int f9598C = -1;

    /* renamed from: D */
    private BadgeHelper f9599D;

    /* renamed from: E */
    private int f9600E = 0;

    /* renamed from: F */
    private BroadcastReceiver f9601F;

    /* renamed from: G */
    private BroadcastReceiver f9602G;

    /* renamed from: H */
    private HashMap<String, Object> f9603H;

    /* renamed from: I */
    private boolean f9604I = true;

    /* renamed from: J */
    private GiWiFiInfoExView f9605J;

    /* renamed from: K */
    private LinearLayout f9606K;

    /* renamed from: L */
    private BroadcastReceiver f9607L = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.loading.MainActivity.C26881 */

        public void onReceive(Context context, Intent intent) {
            if (MainActivity.this.m11263l()) {
                MainActivity.this.setUnReadMsgState();
            }
        }
    };

    /* renamed from: M */
    private Gson f9608M;

    /* renamed from: N */
    private Integer f9609N = 0;

    /* renamed from: O */
    private List<TabDatas.DataBean.TabListBean> f9610O = new ArrayList();

    /* renamed from: P */
    private int f9611P = 0;

    /* renamed from: Q */
    private int f9612Q = 1;

    /* renamed from: R */
    private int f9613R = 2;

    /* renamed from: S */
    private HashMap<Integer, Integer> f9614S = new HashMap<>();

    /* renamed from: T */
    private boolean f9615T = false;

    /* renamed from: U */
    private PopupWindow f9616U;

    /* renamed from: V */
    private NotifyBean.DataBean.NotifyListBean f9617V;

    /* renamed from: W */
    private TextView f9618W;

    /* renamed from: X */
    private WifiManager f9619X;

    /* renamed from: Y */
    private int f9620Y;

    /* renamed from: Z */
    private JazzyViewPager f9621Z;

    /* renamed from: aa */
    private boolean f9622aa;

    /* renamed from: ab */
    private AbstractC2735a f9623ab = new AbstractC2735a() {
        /* class com.gbcom.gwifi.functions.loading.MainActivity.C272538 */

        @Override // com.gbcom.gwifi.functions.loading.MainActivity.AbstractC2735a
        /* renamed from: a */
        public void mo25031a() {
            MainActivity currentMainActivity = GBApplication.instance().getCurrentMainActivity();
            if (currentMainActivity != null) {
                currentMainActivity.wifiViewResume();
            }
        }

        @Override // com.gbcom.gwifi.functions.loading.MainActivity.AbstractC2735a
        /* renamed from: b */
        public void mo25032b() {
        }
    };

    /* renamed from: ac */
    private String f9624ac;

    /* renamed from: ad */
    private BroadcastReceiver f9625ad = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.loading.MainActivity.C27305 */

        public void onReceive(Context context, Intent intent) {
            if (MainActivity.this.f9643j == null) {
                GiwifiPushService giwifiPushService = GBApplication.instance().getGiwifiPushService();
                if (giwifiPushService != null) {
                    MainActivity.this.f9643j = giwifiPushService.getGiwifiPushAgent();
                }
                if (MainActivity.this.f9643j == null) {
                    MainActivity.this.f9643j = new GiwifiPushAgent(GBApplication.instance(), 0);
                }
                if (SystemUtil.m14531e()) {
                    MainActivity.this.f9643j.setResourcePackageName("com.gbcom.gwifi");
                }
                MainActivity.this.f9643j.onAppStart();
                MainActivity.this.f9643j.registerHandleToken();
                MainActivity.this.f9643j.setDisplayNotificationNumber(2);
            }
            if (MainActivity.this.f9643j != null) {
                MainActivity.this.f9643j.checkUnhandleMessage(MainActivity.this);
            }
            MainActivity.this.m11282v();
            MainActivity.this.m11289y();
            GBGlobalConfig.m10510c().mo24396a(MainActivity.f9595g, context);
            if (MainActivity.this.f9615T && GBGlobalConfig.m10510c().mo24400a(MainActivity.this.getApplicationContext())) {
                MainActivity.this.f9615T = false;
                GBGlobalConfig.m10510c().mo24416p();
            }
            if (CacheApp.getInstance().getAppDptipShow()) {
                String appDptipContent = CacheApp.getInstance().getAppDptipContent();
                if (!C3467s.m14513e(appDptipContent) && SystemUtil.m14525b(context, "com.gbcom.gwifi")) {
                    GBGlobalConfig.m10510c().mo24397a("提示", appDptipContent, "", "确定", 4);
                }
            }
        }
    };

    /* renamed from: ae */
    private BroadcastReceiver f9626ae = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.loading.MainActivity.C27316 */

        public void onReceive(Context context, Intent intent) {
            Intent intent2 = new Intent(GBApplication.instance(), DeviceTest2Activity.class);
            intent2.putExtra("type", "hy");
            MainActivity.this.startActivity(intent2);
        }
    };

    /* renamed from: af */
    private BroadcastReceiver f9627af = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.loading.MainActivity.C27327 */

        public void onReceive(Context context, Intent intent) {
            int afterAuthJumpTabType = CacheApp.getInstance().getAfterAuthJumpTabType();
            if (MainActivity.this.f9610O == null || MainActivity.this.f9610O.size() <= 0) {
                if (afterAuthJumpTabType <= MainActivity.this.getTabHost().getTabWidget().getTabCount()) {
                    MainActivity.this.setCurrentTabId(afterAuthJumpTabType);
                }
            } else if (SystemUtil.m14531e()) {
                if (afterAuthJumpTabType == 0 || afterAuthJumpTabType == 3) {
                    MainActivity.this.getTabHost().setCurrentTab(MainActivity.this.f9611P);
                } else {
                    MainActivity.this.getTabHost().setCurrentTab(MainActivity.this.f9612Q);
                }
            } else if (afterAuthJumpTabType != 4) {
                MainActivity.this.getTabHost().setCurrentTab(((Integer) MainActivity.this.f9614S.get(Integer.valueOf(afterAuthJumpTabType + 1))).intValue());
            } else {
                MainActivity.this.getTabHost().setCurrentTab(MainActivity.this.f9612Q);
            }
        }
    };

    /* renamed from: ag */
    private BroadcastReceiver f9628ag = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.loading.MainActivity.C27338 */

        public void onReceive(Context context, Intent intent) {
            GBGlobalConfig.m10510c().mo24418r();
        }
    };

    /* renamed from: ah */
    private BroadcastReceiver f9629ah = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.loading.MainActivity.C27349 */

        public void onReceive(Context context, Intent intent) {
            GBGlobalConfig.m10510c().mo24419s();
        }
    };

    /* renamed from: ai */
    private BroadcastReceiver f9630ai = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.loading.MainActivity.C268910 */

        public void onReceive(Context context, Intent intent) {
            if (CacheWiFi.getInstance().checkNeedNoticeMac()) {
                MainActivity.this.showSecondDialog("提示", "系统'随机MAC'未关闭。关闭后，WiFi漫游无需频繁认证，上网更便捷！", "去关闭", new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.loading.MainActivity.C268910.C26901 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                        String m = HttpUtil.m14341m();
                        if (!C3467s.m14513e(m)) {
                            GBActivity.openBackWebView(m, "");
                        } else {
                            GBActivity.showMessageToast("链接无效...");
                        }
                    }
                }, new DialogInterface.OnCancelListener() {
                    /* class com.gbcom.gwifi.functions.loading.MainActivity.C268910.DialogInterface$OnCancelListenerC26912 */

                    public void onCancel(DialogInterface dialogInterface) {
                    }
                });
            }
        }
    };

    /* renamed from: aj */
    private boolean f9631aj = true;

    /* renamed from: ak */
    private FloatWindowManager f9632ak;

    /* renamed from: al */
    private final Runnable f9633al = new Runnable() {
        /* class com.gbcom.gwifi.functions.loading.MainActivity.RunnableC271932 */

        @Override // java.lang.Runnable
        public void run() {
            MainActivity.this.mo24957f();
        }
    };

    /* renamed from: am */
    private final int f9634am = 1024;

    /* renamed from: an */
    private AbstractC2735a f9635an;

    /* renamed from: ao */
    private CountDownTimerC2736b f9636ao;

    /* renamed from: c */
    ProgressDialog f9637c;
    public int connPos = -1;

    /* renamed from: d */
    ImageLoadingListener f9638d = new ImageLoadingListener() {
        /* class com.gbcom.gwifi.functions.loading.MainActivity.C270521 */

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingStarted(String str, View view) {
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingFailed(String str, View view, FailReason failReason) {
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            MainActivity.this.f9651r.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.C270521.View$OnClickListenerC27061 */

                public void onClick(View view) {
                    MainActivity.this.m11284w();
                }
            });
            MainActivity.this.m11273q();
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }
    };

    /* renamed from: e */
    protected Context f9639e;
    public int eduPos = -1;

    /* renamed from: f */
    protected int f9640f = 0;

    /* renamed from: h */
    private PopupWindow f9641h;
    public Handler handler = new Handler();
    public int homePos = -1;

    /* renamed from: i */
    private ViewGroup f9642i;

    /* renamed from: j */
    private GiwifiPushAgent f9643j;

    /* renamed from: k */
    private boolean f9644k = true;

    /* renamed from: l */
    private String f9645l;

    /* renamed from: m */
    private boolean f9646m = false;

    /* renamed from: n */
    private TextView f9647n;

    /* renamed from: o */
    private Executor f9648o = Executors.newFixedThreadPool(1);
    public int officePos = -1;

    /* renamed from: p */
    private BroadcastReceiver f9649p;

    /* renamed from: q */
    private boolean f9650q = false;

    /* renamed from: r */
    private ImageView f9651r;

    /* renamed from: s */
    private ImageView f9652s;

    /* renamed from: t */
    private View f9653t;

    /* renamed from: u */
    private String f9654u;

    /* renamed from: v */
    private int f9655v = 0;

    /* renamed from: w */
    private int f9656w;

    /* renamed from: x */
    private int f9657x;

    /* renamed from: y */
    private String f9658y = "";

    /* renamed from: z */
    private int f9659z;

    /* renamed from: com.gbcom.gwifi.functions.loading.MainActivity$a */
    public interface AbstractC2735a {
        /* renamed from: a */
        void mo25031a();

        /* renamed from: b */
        void mo25032b();
    }

    /* renamed from: C */
    static /* synthetic */ int m11229C(MainActivity mainActivity) {
        int i = mainActivity.f9600E;
        mainActivity.f9600E = i + 1;
        return i;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: j */
    private ProgressDialog m11259j() {
        if (this.f9637c == null) {
            this.f9637c = new ProgressDialog(this);
            this.f9637c.setCanceledOnTouchOutside(false);
            this.f9637c.setOnCancelListener(new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.DialogInterface$OnCancelListenerC269312 */

                public void onCancel(DialogInterface dialogInterface) {
                    MainActivity.this.f9622aa = false;
                }
            });
        }
        return this.f9637c;
    }

    /* renamed from: k */
    private void m11261k() {
        this.f9605J = (GiWiFiInfoExView) findViewById(C2425R.C2427id.wifi_info_layout);
        this.f9606K = (LinearLayout) findViewById(C2425R.C2427id.wifi_info_layout_bg);
        this.f9605J.mo27110a(new BaseGiWiFiInfoView.AbstractC3238h() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.C270823 */

            @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.AbstractC3238h
            /* renamed from: a */
            public void mo25006a(View view) {
                MainActivity.this.showAndClose();
            }

            @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.AbstractC3238h
            /* renamed from: a */
            public void mo25005a() {
                if (Config.m14094a().mo27812o()) {
                    if (!CacheWiFi.getInstance().getNetworkReachable()) {
                    }
                } else {
                    MainActivity.this.showAndClose();
                }
            }
        });
        this.f9606K.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.View$OnClickListenerC272134 */

            public void onClick(View view) {
                MainActivity.this.showAndClose();
            }
        });
        this.f9618W = (TextView) this.f9605J.findViewById(2131755571);
    }

    /* renamed from: a */
    private void m11241a(final List<TabDatas.DataBean.TabListBean> list) {
        String loginPhone = CacheAccount.getInstance().getLoginPhone();
        String loginStaticPassword = CacheAccount.getInstance().getLoginStaticPassword();
        int parseInt = Integer.parseInt(CacheAuth.getInstance().getOrgId());
        int orgType = CacheAuth.getInstance().getOrgType();
        m11226A();
        new SDKUserManager(this).mo24032a(Constant.f13315cx, loginPhone, loginStaticPassword, parseInt, orgType, Constant.f13316cy, new ISDKUser() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.C272336 */

            @Override // com.gbcom.edu.functionModule.main.manager.ISDKUser
            /* renamed from: a */
            public void mo24034a(int i, CircleUserBean circleUserBean) {
                Log.m14398b(MainActivity.f9595g, "i:" + i);
                if (list != null && list.size() > 0) {
                    MainActivity.this.getTabHost().clearAllTabs();
                    MainActivity.this.m11247b((MainActivity) list);
                }
                if (i == 200) {
                    MainActivity.this.f9598C = 1;
                    if (MainActivity.this.f9600E > 0) {
                        MainActivity.this.setCircleUnReadMsgState(true);
                    }
                    if (circleUserBean != null) {
                        String i2 = circleUserBean.mo23997i();
                        if (!C3467s.m14513e(i2)) {
                            CacheAccount.getInstance().setCircleAvatarUrl(i2);
                        }
                        CacheAccount.getInstance().setCircleSex(circleUserBean.mo24010s());
                        CacheAccount.getInstance().setCircleBirth(circleUserBean.mo24003l());
                        CacheAccount.getInstance().setCircleCity(circleUserBean.mo24005n());
                    }
                    CacheAuth.getInstance().setCircleLogin(true);
                }
            }

            @Override // com.gbcom.edu.functionModule.main.manager.ISDKUser
            /* renamed from: a */
            public void mo24033a(int i) {
            }
        });
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.FragmentTabActivity, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        setActivityName("主界面");
        Log.m14398b(f9595g, "init:1 " + FormatUtil.m14213a());
        super.onCreate(bundle);
        Log.m14398b(f9595g, "init:2 " + FormatUtil.m14213a());
        this.f9644k = getIntent().getBooleanExtra("isAuto", true);
        Config.m14094a().mo27797a(this.f9644k);
        String stringExtra = getIntent().getStringExtra(Constant.f13323i);
        String stringExtra2 = getIntent().getStringExtra("staticPassword");
        if (stringExtra != null && !stringExtra.equals("")) {
            CacheAccount.getInstance().setLoginPhone(stringExtra);
            CacheAccount.getInstance().setLoginStaticPassword(stringExtra2);
        }
        Log.m14398b(f9595g, "init:3 " + FormatUtil.m14213a());
        StatusBarUtil.m14194c(this);
        StatusBarUtil.m14183a((Activity) this);
        getWindow().setBackgroundDrawable(getResources().getDrawable(C2425R.color.yellow_4));
        Log.m14398b(f9595g, "init:4 " + FormatUtil.m14213a());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.f13238bM);
        registerReceiver(this.f9625ad, intentFilter);
        this.f9615T = true;
        List<TabDatas.DataBean.TabListBean> tabList = CacheApp.getInstance().getTabList();
        Log.m14398b(f9595g, "init:5 " + FormatUtil.m14213a());
        if (Config.m14094a().mo27812o() && !CacheAuth.getInstance().isPortal()) {
            m11241a(tabList);
        }
        GBGlobalConfig.m10510c().mo24415o();
        Log.m14398b(f9595g, "init:6 " + FormatUtil.m14213a());
        if (tabList == null || tabList.size() <= 0) {
            this.f9631aj = true;
            if (SystemUtil.m14531e()) {
                addTab("0", C2425R.layout.tab_home, HomeFragment.class, (Bundle) null);
                addTab("1", C2425R.layout.tab_edu, EduFragment.class, (Bundle) null);
                this.homePos = 0;
                this.eduPos = 1;
            } else {
                addTab("0", C2425R.layout.tab_conn, FirstTabFragment.class, (Bundle) null);
                addTab("1", C2425R.layout.tab_entertainment, SecondTabFragment.class, (Bundle) null);
                addTab(MessageService.MSG_DB_NOTIFY_CLICK, C2425R.layout.tab_find, FindTabFragment.class, (Bundle) null);
                addTab(MessageService.MSG_DB_NOTIFY_DISMISS, C2425R.layout.tab_mine, OfficeFragment.class, (Bundle) null);
                this.connPos = 0;
                this.officePos = 3;
            }
            Log.m14398b(f9595g, "init:8 " + FormatUtil.m14213a());
            if (m11263l()) {
                setUnReadMsgState();
            }
            Log.m14398b(f9595g, "init:9 " + FormatUtil.m14213a());
        } else {
            this.f9631aj = false;
            m11247b(tabList);
            Log.m14398b(f9595g, "init:7 " + FormatUtil.m14213a());
        }
        this.f9649p = new BroadcastReceiver() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.C272437 */

            public void onReceive(Context context, Intent intent) {
                MainActivity.this.m11279t();
            }
        };
        Log.m14398b(f9595g, "init:10 " + FormatUtil.m14213a());
        registerReceiver(this.f9649p, new IntentFilter(Constant.f13232bG));
        registerReceiver(this.f9607L, new IntentFilter(Constant.f13287cI));
        registerReceiver(this.f9627af, new IntentFilter(Constant.f13242bQ));
        registerReceiver(this.f9626ae, new IntentFilter(Constant.f13243bR));
        registerReceiver(this.f9628ag, new IntentFilter(Constant.f13246bU));
        registerReceiver(this.f9629ah, new IntentFilter(Constant.f13247bV));
        registerReceiver(this.f9630ai, new IntentFilter(Constant.f13236bK));
        Log.m14398b(f9595g, "init:11 " + FormatUtil.m14213a());
        m11265m();
        Log.m14398b(f9595g, "init:12 " + FormatUtil.m14213a());
        setBackAdView();
        Log.m14398b(f9595g, "init:13 " + FormatUtil.m14213a());
        if (!SystemUtil.m14531e()) {
            m11261k();
            Log.m14398b(f9595g, "init:14 " + FormatUtil.m14213a());
        }
        requestLocationPermission();
        this.f9619X = (WifiManager) getApplicationContext().getSystemService("wifi");
        this.f9619X.createMulticastLock("mygroup").acquire();
        Log.m14398b(f9595g, "init:15 " + FormatUtil.m14213a());
        checkWifiLocation();
        Log.m14398b(f9595g, "init:16 " + FormatUtil.m14213a());
        this.f9639e = this;
        if (!mo24956e()) {
        }
        this.f9632ak = new FloatWindowManager();
        getFragment("0");
        this.f9636ao = new CountDownTimerC2736b(4000, 4000);
        this.f9640f = 10;
        mo24953c();
        Log.m14398b(f9595g, "init:17 " + FormatUtil.m14213a());
    }

    public void wifiViewResume() {
        View view;
        GiWiFiInfoHomeView giWiFiInfoHomeView;
        GiWiFiInfoExView wiFiInfoView = getWiFiInfoView();
        if (wiFiInfoView != null) {
            wiFiInfoView.mo27141aS();
        }
        if (getHomeView() != null) {
            Fragment fragment = getFragment("0");
            if ((fragment instanceof HomeFragment) && (view = fragment.getView()) != null && (giWiFiInfoHomeView = (GiWiFiInfoHomeView) view.findViewById(C2425R.C2427id.home_wifi_info_layout)) != null) {
                giWiFiInfoHomeView.mo27141aS();
            }
        }
    }

    public void requestLocationPermission() {
        requestPermissions(this, new String[]{MsgConstant.PERMISSION_ACCESS_WIFI_STATE, "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, this.f9623ab);
    }

    public void checkWifiLocation() {
        if (!NetworkUtils.m14430b(this.f9619X.getConnectionInfo().getBSSID())) {
            if (this.f9618W != null) {
                this.f9618W.setVisibility(8);
            }
            CacheWiFi.getInstance().setApLocation("");
            return;
        }
        if (this.f9618W != null) {
            this.f9618W.setVisibility(0);
        }
        this.f9648o.execute(new Runnable() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.RunnableC272639 */

            @Override // java.lang.Runnable
            public void run() {
                final String a = NetworkUtils.m14424a();
                MainActivity.this.runOnUiThread(new Runnable() {
                    /* class com.gbcom.gwifi.functions.loading.MainActivity.RunnableC272639.RunnableC27271 */

                    @Override // java.lang.Runnable
                    public void run() {
                        if (a == null || TextUtils.isEmpty(a)) {
                            if (MainActivity.this.f9618W != null) {
                                MainActivity.this.f9618W.setText("未获取到您的位置");
                            }
                            CacheWiFi.getInstance().setApLocation("");
                            return;
                        }
                        if (MainActivity.this.f9618W != null) {
                            MainActivity.this.f9618W.setText("当前WiFi位置:" + a);
                        }
                        CacheWiFi.getInstance().setApLocation(a);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11247b(List<TabDatas.DataBean.TabListBean> list) {
        String str;
        if (SystemUtil.m14531e()) {
            String str2 = "";
            String str3 = "";
            Iterator<TabDatas.DataBean.TabListBean> it = list.iterator();
            while (it.hasNext()) {
                TabDatas.DataBean.TabListBean next = it.next();
                int layout_type = next.getLayout_type();
                if (!(layout_type == 1 || layout_type == 4)) {
                    if (layout_type == 2) {
                        str2 = next.getTab_name();
                    }
                    if (layout_type == 3) {
                        str = next.getTab_name();
                    } else {
                        str = str3;
                    }
                    it.remove();
                    str3 = str;
                }
                str2 = str2;
            }
            if (!C3467s.m14513e(str3)) {
                this.f9624ac = str3;
            } else if (!C3467s.m14513e(str2)) {
                this.f9624ac = str2;
            } else {
                this.f9624ac = "";
            }
            this.f9610O = list;
            for (int i = 0; i < this.f9610O.size(); i++) {
                TabDatas.DataBean.TabListBean tabListBean = this.f9610O.get(i);
                int layout_type2 = tabListBean.getLayout_type();
                if (layout_type2 == 4) {
                    tabListBean.setTab_name(this.f9624ac);
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("tabListBean", tabListBean);
                if (layout_type2 == 1) {
                    addTab(i + "", m11236a(tabListBean, C2425R.layout.tab_home), HomeFragment.class, bundle);
                    this.homePos = i;
                } else if (layout_type2 == 4) {
                    addTab(i + "", m11236a(tabListBean, C2425R.layout.tab_edu), EduFragment.class, bundle);
                    this.eduPos = i;
                }
                this.f9614S.put(Integer.valueOf(layout_type2), Integer.valueOf(i));
            }
        } else {
            Iterator<TabDatas.DataBean.TabListBean> it2 = list.iterator();
            while (it2.hasNext()) {
                int layout_type3 = it2.next().getLayout_type();
                if (!Config.m14094a().mo27812o() || CacheAuth.getInstance().isPortal()) {
                    if (!(layout_type3 == 1 || layout_type3 == 2 || layout_type3 == 3 || layout_type3 == 4)) {
                        it2.remove();
                    }
                } else if (!(layout_type3 == 1 || layout_type3 == 2 || layout_type3 == 3 || layout_type3 == 4 || layout_type3 == 5 || layout_type3 == 6)) {
                    it2.remove();
                }
            }
            this.f9610O = list;
            boolean z = false;
            for (int i2 = 0; i2 < list.size(); i2++) {
                TabDatas.DataBean.TabListBean tabListBean2 = list.get(i2);
                int layout_type4 = tabListBean2.getLayout_type();
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("tabListBean", tabListBean2);
                tabListBean2.getChild_tab();
                if (layout_type4 == 1) {
                    addTab(i2 + "", m11236a(tabListBean2, C2425R.layout.tab_conn), FirstTabFragment.class, bundle2);
                    this.connPos = i2;
                } else if (layout_type4 == 4) {
                    addTab(i2 + "", m11236a(tabListBean2, C2425R.layout.tab_mine), OfficeFragment.class, bundle2);
                    this.officePos = i2;
                } else if (layout_type4 == 2) {
                    addTab(i2 + "", m11236a(tabListBean2, C2425R.layout.tab_entertainment), SecondTabFragment.class, bundle2);
                } else if (layout_type4 == 3) {
                    addTab(i2 + "", m11236a(tabListBean2, C2425R.layout.tab_find), FindTabFragment.class, bundle2);
                } else if (layout_type4 == 5) {
                    addTab(i2 + "", m11236a(tabListBean2, C2425R.layout.tab_entertainment), MessageFragment.class, bundle2);
                } else if (layout_type4 == 6) {
                    addTab(i2 + "", m11236a(tabListBean2, C2425R.layout.tab_find), CircleFragment.class, bundle2);
                    z = true;
                }
                this.f9614S.put(Integer.valueOf(layout_type4), Integer.valueOf(i2));
            }
            Config.m14094a().mo27799b(z);
        }
        TabWidget tabWidget = getTabHost().getTabWidget();
        for (int i3 = 0; i3 < tabWidget.getChildCount(); i3++) {
            View childAt = tabWidget.getChildAt(i3);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
            childAt.setLayoutParams(layoutParams);
        }
        if (m11263l()) {
            setUnReadMsgState();
        }
    }

    public void setUnReadMsgState() {
        View view;
        View b;
        if (getTabHost() != null) {
            int[] iArr = {this.officePos, this.homePos};
            for (int i = 0; i < iArr.length; i++) {
                if (!(iArr[i] == -1 || (b = m11245b(iArr[i])) == null)) {
                    RelativeLayout relativeLayout = (RelativeLayout) b.findViewById(C2425R.C2427id.message_linear);
                    TextView textView = (TextView) b.findViewById(C2425R.C2427id.message_tv);
                    if (relativeLayout != null) {
                        relativeLayout.setVisibility(0);
                    }
                    if (textView != null) {
                        textView.setText(this.f9659z + "");
                    }
                }
            }
            for (int i2 = 0; i2 < this.f9610O.size(); i2++) {
                Fragment fragment = getFragment("" + i2);
                if (fragment != null && (((fragment instanceof HomeFragment) || (fragment instanceof EduFragment)) && (view = fragment.getView()) != null)) {
                    RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(C2425R.C2427id.message_linear);
                    TextView textView2 = (TextView) view.findViewById(C2425R.C2427id.message_tv);
                    if (relativeLayout2 != null) {
                        relativeLayout2.setVisibility(0);
                    }
                    if (textView2 != null) {
                        textView2.setText(this.f9659z + "");
                    }
                }
            }
            ShortcutBadger.m39127a(getApplicationContext(), this.f9659z);
        }
    }

    public void setReadMsgState() {
        View view;
        View b;
        this.f9659z = 0;
        if (getTabHost() != null) {
            int[] iArr = {this.officePos, this.homePos};
            for (int i = 0; i < iArr.length; i++) {
                if (!(iArr[i] == -1 || (b = m11245b(iArr[i])) == null)) {
                    RelativeLayout relativeLayout = (RelativeLayout) b.findViewById(C2425R.C2427id.message_linear);
                    TextView textView = (TextView) b.findViewById(C2425R.C2427id.message_tv);
                    if (relativeLayout != null) {
                        relativeLayout.setVisibility(8);
                    }
                    if (textView != null) {
                        textView.setText("");
                    }
                }
            }
            for (int i2 = 0; i2 < this.f9610O.size(); i2++) {
                Fragment fragment = getFragment("" + i2);
                if (fragment != null && (((fragment instanceof HomeFragment) || (fragment instanceof EduFragment)) && (view = fragment.getView()) != null)) {
                    RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(C2425R.C2427id.message_linear);
                    TextView textView2 = (TextView) view.findViewById(C2425R.C2427id.message_tv);
                    if (relativeLayout2 != null) {
                        relativeLayout2.setVisibility(8);
                    }
                    if (textView2 != null) {
                        textView2.setText("");
                    }
                }
            }
            ShortcutBadger.m39126a(getApplicationContext());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: l */
    private boolean m11263l() {
        try {
            List query = NotifyDao.m11495b().mo24212d(GBApplication.instance()).where().mo33357eq("read", false).query();
            if (query == null || query.size() <= 0) {
                return false;
            }
            this.f9659z = query.size();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.gbcom.gwifi.base.activity.FragmentTabActivity
    public void onTabChanged(String str) {
        ImageView imageView;
        if (!this.f9631aj) {
            if (!(this.f9609N == null || this.f9609N.intValue() == -1)) {
                View b = m11245b(this.f9609N.intValue());
                if (b != null) {
                    imageView = (ImageView) b.findViewById(2131755155);
                } else {
                    imageView = null;
                }
                if (!SystemUtil.m14531e()) {
                    String unselected_icon = this.f9610O.get(this.f9609N.intValue()).getUnselected_icon();
                    if (!TextUtils.isEmpty(unselected_icon) && imageView != null) {
                        ImageTools.m14149a(unselected_icon, imageView, GBApplication.instance().tabOptions);
                    }
                }
            }
            Integer valueOf = Integer.valueOf(str);
            this.f9609N = valueOf;
            TabDatas.DataBean.TabListBean tabListBean = this.f9610O.get(valueOf.intValue());
            ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.4f), PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.4f));
            if ((getLastFragment() instanceof OfficeFragment) || (getLastFragment() instanceof HomeFragment)) {
                setHeadTextColorGrey();
                if (getLastFragment() instanceof OfficeFragment) {
                    ((OfficeFragment) getLastFragment()).mo27323a();
                }
                if (getLastFragment() instanceof HomeFragment) {
                    ((HomeFragment) getLastFragment()).mo27299a();
                }
                final RelativeLayout relativeLayout = (RelativeLayout) m11245b(valueOf.intValue()).findViewById(C2425R.C2427id.office_rl);
                if (getLastFragment() instanceof OfficeFragment) {
                    ImageView imageView2 = (ImageView) m11245b(valueOf.intValue()).findViewById(2131755155);
                    if (!TextUtils.isEmpty(tabListBean.getSelected_icon())) {
                        ImageTools.m14149a(tabListBean.getSelected_icon(), imageView2, GBApplication.instance().tabOptions);
                    }
                }
                ofPropertyValuesHolder.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    /* class com.gbcom.gwifi.functions.loading.MainActivity.C272940 */

                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float floatValue = ((Float) valueAnimator.getAnimatedValue("scaleX")).floatValue();
                        float floatValue2 = ((Float) valueAnimator.getAnimatedValue("scaleY")).floatValue();
                        relativeLayout.setScaleX(floatValue);
                        relativeLayout.setScaleY(floatValue2);
                    }
                });
                ofPropertyValuesHolder.setTarget(relativeLayout);
                ofPropertyValuesHolder.setDuration(300L);
                ofPropertyValuesHolder.setRepeatCount(1);
                ofPropertyValuesHolder.setRepeatMode(2);
                ofPropertyValuesHolder.start();
                if (getLastFragment() instanceof HomeFragment) {
                    ((HomeFragment) getLastFragment()).mo27300a(0);
                    return;
                }
                return;
            }
            setCircleUnReadMsgState(false);
            setHeadTextColorRed();
            final ImageView imageView3 = (ImageView) m11245b(valueOf.intValue()).findViewById(2131755155);
            if (!(getLastFragment() instanceof EduFragment) && !TextUtils.isEmpty(tabListBean.getSelected_icon())) {
                ImageTools.m14149a(tabListBean.getSelected_icon(), imageView3, GBApplication.instance().tabOptions);
            }
            ofPropertyValuesHolder.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.C27032 */

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue("scaleX")).floatValue();
                    float floatValue2 = ((Float) valueAnimator.getAnimatedValue("scaleY")).floatValue();
                    imageView3.setScaleX(floatValue);
                    imageView3.setScaleY(floatValue2);
                }
            });
            ofPropertyValuesHolder.setTarget(imageView3);
            ofPropertyValuesHolder.setDuration(300L);
            ofPropertyValuesHolder.setRepeatCount(1);
            ofPropertyValuesHolder.setRepeatMode(2);
            ofPropertyValuesHolder.start();
            if (getLastFragment() instanceof CircleFragment) {
            }
            if ((getLastFragment() instanceof FirstTabFragment) || (getLastFragment() instanceof FindTabFragment)) {
                ((GiWiFiInfoExView) findViewById(C2425R.C2427id.wifi_info_layout)).mo27101a(0L);
                return;
            }
            return;
        }
        Integer valueOf2 = Integer.valueOf(str);
        ValueAnimator ofPropertyValuesHolder2 = ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.4f), PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.4f));
        if ((getLastFragment() instanceof OfficeFragment) || (getLastFragment() instanceof HomeFragment)) {
            setHeadTextColorGrey();
            if (getLastFragment() instanceof HomeFragment) {
                ((HomeFragment) getLastFragment()).mo27299a();
            }
            if (getLastFragment() instanceof OfficeFragment) {
                ((OfficeFragment) getLastFragment()).mo27323a();
            }
            final RelativeLayout relativeLayout2 = (RelativeLayout) m11245b(valueOf2.intValue()).findViewById(C2425R.C2427id.office_rl);
            ofPropertyValuesHolder2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.C27163 */

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue("scaleX")).floatValue();
                    float floatValue2 = ((Float) valueAnimator.getAnimatedValue("scaleY")).floatValue();
                    relativeLayout2.setScaleX(floatValue);
                    relativeLayout2.setScaleY(floatValue2);
                }
            });
            ofPropertyValuesHolder2.setTarget(relativeLayout2);
            ofPropertyValuesHolder2.setDuration(300L);
            ofPropertyValuesHolder2.setRepeatCount(1);
            ofPropertyValuesHolder2.setRepeatMode(2);
            ofPropertyValuesHolder2.start();
            if (getLastFragment() instanceof HomeFragment) {
                ((HomeFragment) getLastFragment()).mo27300a(0);
                return;
            }
            return;
        }
        setHeadTextColorRed();
        if ((getLastFragment() instanceof FirstTabFragment) || (getLastFragment() instanceof SecondTabFragment) || (getLastFragment() instanceof FindTabFragment) || (getLastFragment() instanceof CircleFragment) || (getLastFragment() instanceof MessageFragment) || (getLastFragment() instanceof EduFragment)) {
            final ImageView imageView4 = (ImageView) m11245b(valueOf2.intValue()).findViewById(2131755155);
            ofPropertyValuesHolder2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.C27284 */

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue("scaleX")).floatValue();
                    float floatValue2 = ((Float) valueAnimator.getAnimatedValue("scaleY")).floatValue();
                    imageView4.setScaleX(floatValue);
                    imageView4.setScaleY(floatValue2);
                }
            });
            ofPropertyValuesHolder2.setTarget(imageView4);
            ofPropertyValuesHolder2.setDuration(300L);
            ofPropertyValuesHolder2.setRepeatCount(1);
            ofPropertyValuesHolder2.setRepeatMode(2);
            ofPropertyValuesHolder2.start();
        }
        if ((getLastFragment() instanceof FirstTabFragment) || (getLastFragment() instanceof FindTabFragment)) {
            ((GiWiFiInfoExView) findViewById(C2425R.C2427id.wifi_info_layout)).mo27101a(0L);
        }
    }

    /* renamed from: m */
    private void m11265m() {
        this.f9645l = getIntent().getStringExtra("webUrl");
        if (C3467s.m14513e(this.f9645l) || this.f9645l.length() <= 5) {
            this.f9646m = getIntent().getBooleanExtra("isAdProductType", false);
            if (this.f9646m) {
                m11267n();
                return;
            }
            return;
        }
        openBackWebView(this.f9645l, "");
    }

    /* renamed from: n */
    private void m11267n() {
        m11238a(CacheAd.getInstance().getAdProductId(), CacheAd.getInstance().getAdProductType());
    }

    /* renamed from: a */
    private void m11238a(int i, String str) {
        if (!str.equals(Constant.f13158M)) {
            if (str.equals(Constant.f13160O)) {
                Intent intent = new Intent(GBApplication.instance(), AppActivity.class);
                intent.putExtra("productId", i);
                startActivity(intent);
            } else if (str.equals(Constant.f13162Q)) {
                Intent intent2 = new Intent(GBApplication.instance(), GameActivity.class);
                intent2.putExtra("productId", i);
                startActivity(intent2);
            } else if (str.equals(Constant.f13163R)) {
            } else {
                if (str.equals(Constant.f13164S)) {
                    startActivity(new Intent(GBApplication.instance(), NewsActivity.class));
                } else if (str.equals(Constant.f13164S)) {
                    m11269o();
                }
            }
        }
    }

    /* renamed from: o */
    private void m11269o() {
    }

    @Override // com.gbcom.gwifi.base.activity.FragmentTabActivity, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        Log.m14398b(f9595g, "onResume");
        this.f9604I = true;
        NotifyWhenAppExit offlineNotify = CacheApp.getInstance().getOfflineNotify();
        if (offlineNotify != null) {
            if (this.f9643j != null) {
                this.f9643j.handleNotifyWhenAppExist(offlineNotify);
            }
            CacheApp.getInstance().setOfflineNotify(null);
        }
        if (this.f9643j != null) {
            this.f9643j.onResume(this);
        }
        wifiViewResume();
        super.onResume();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.FragmentTabActivity, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onPause() {
        super.onPause();
        if (this.f9640f != 12 && mo24955d()) {
            m11231D();
            mo24959g();
        }
    }

    /* renamed from: b */
    private View m11245b(int i) {
        if (getTabHost() == null || i == -1) {
            return null;
        }
        return getTabHost().getTabWidget().getChildAt(i);
    }

    public View getConnView() {
        return m11245b(this.connPos);
    }

    public View getHomeView() {
        return m11245b(this.homePos);
    }

    public GiWiFiInfoExView getWiFiInfoView() {
        return this.f9605J;
    }

    /* renamed from: b */
    private void m11246b(int i, String str) {
        switch (i) {
            case 1:
            default:
                return;
            case 2:
                startActivity(new Intent(GBApplication.instance(), AboutUsActivity.class));
                return;
            case 3:
                startActivity(new Intent(GBApplication.instance(), SysStateActivity.class));
                return;
            case 4:
                Intent intent = new Intent(GBApplication.instance(), DeviceTest2Activity.class);
                intent.putExtra("type", "system_set");
                startActivity(intent);
                return;
            case 5:
                startActivity(new Intent(GBApplication.instance(), PingTestActivity.class));
                return;
            case 6:
                GBActivity.openBackWebView(str, "");
                return;
        }
    }

    public void showPopUpWindow(NotifyBean.DataBean.NotifyListBean notifyListBean, int i) {
        this.f9617V = notifyListBean;
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.layout_schoolnotify, (ViewGroup) null);
        ((Button) inflate.findViewById(C2425R.C2427id.btn_all)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.View$OnClickListenerC269211 */

            public void onClick(View view) {
                if (MainActivity.this.f9616U != null) {
                    MainActivity.this.f9616U.dismiss();
                }
                CacheEdu.getInstance().setEduNotifyNum(MainActivity.this.f9617V.getLimit_num());
                try {
                    new CommonNotifyClickListener(MainActivity.this, new JSONObject(MainActivity.this.f9608M.mo21597b(MainActivity.this.f9617V))).onClick(view);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        inflate.findViewById(C2425R.C2427id.iv_close).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.View$OnClickListenerC269413 */

            public void onClick(View view) {
                if (MainActivity.this.f9616U != null) {
                    MainActivity.this.f9616U.dismiss();
                }
            }
        });
        int c = ScreenUtil.m14176c((Context) this) + StatusBarUtil.m14184a((Context) this);
        if (this.f9616U == null) {
            this.f9616U = new PopupWindow(inflate, -1, c, true);
        }
        this.f9616U.setClippingEnabled(false);
        this.f9616U.setBackgroundDrawable(new ColorDrawable());
        TextView textView = (TextView) this.f9616U.getContentView().findViewById(2131755412);
        String digest = this.f9617V.getDigest();
        if (!TextUtils.isEmpty(digest)) {
            digest = digest + "...";
        } else if (!TextUtils.isEmpty(this.f9617V.getTitle())) {
            digest = this.f9617V.getTitle();
        }
        if (!TextUtils.isEmpty(digest)) {
            this.f9616U.showAtLocation(inflate, 51, 0, 0);
            CacheEdu.getInstance().setEduNotifyNum(i + 1);
            CacheEdu.getInstance().setEduNotifyLastTime(System.currentTimeMillis());
            textView.setText(digest);
        }
    }

    public boolean isWindowShowing() {
        if (this.f9616U == null) {
            return false;
        }
        return this.f9616U.isShowing();
    }

    /* renamed from: a */
    private View m11236a(TabDatas.DataBean.TabListBean tabListBean, int i) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(i, (ViewGroup) getTabHost(), false);
        if (i == 2130968917 || i == 2130968913) {
            if (i == 2130968913 && !C3467s.m14513e(this.f9624ac)) {
                ((TextView) inflate.findViewById(16908310)).setText(this.f9624ac);
            }
            return inflate;
        } else if (tabListBean == null) {
            return inflate;
        } else {
            if (!TextUtils.isEmpty(tabListBean.getUnselected_icon())) {
                ImageTools.m14149a(tabListBean.getUnselected_icon(), (ImageView) inflate.findViewById(2131755155), GBApplication.instance().tabOptions);
            }
            ((TextView) inflate.findViewById(16908310)).setText(tabListBean.getTab_name());
            return inflate;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p009v4.app.FragmentActivity
    public void onNewIntent(Intent intent) {
        if (!SystemUtil.m14531e()) {
            String stringExtra = intent.getStringExtra("message");
            int intExtra = intent.getIntExtra("thumb", 0);
            if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals(ViewBuilderFactory.f11738s)) {
                Log.m14403e(f9595g, "onRestoreInstanceState....");
            }
            setCurrentTab("1");
            if (intExtra == 1) {
                startActivity(new Intent(this, MessageLikeActivity.class));
            } else {
                startActivity(new Intent(this, MessageCommentActivity.class));
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.FragmentTabActivity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        Log.m14398b(f9595g, "onRestoreInstanceState....");
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.FragmentTabActivity, android.support.p009v4.app.FragmentActivity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.m14398b(f9595g, "onSaveInstanceState....");
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity
    public void onRestart() {
        if (!Constant.f13285cG) {
            List<GBActivity> activitiesList = GBApplication.instance().getActivitiesList();
            int i = 0;
            while (true) {
                if (i >= activitiesList.size()) {
                    break;
                } else if (activitiesList.get(i) instanceof AppDownloadActivity) {
                    startActivity(new Intent(GBApplication.instance(), AppDownloadActivity.class));
                    break;
                } else {
                    i++;
                }
            }
        }
        super.onRestart();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != AFTER_TRY_USER_SHOW_TIME) {
            new GiwifiShareApi(this).onActivityResult(i, i2, intent);
        } else if (needCompleteInfo) {
            GBGlobalConfig.m10510c();
            GBGlobalConfig.m10503a(completeInfoUrl, completeInfoMsg);
        } else {
            GBGlobalConfig.m10510c();
            GBGlobalConfig.m10518f();
        }
    }

    public void setBackAdView() {
        this.f9653t = findViewById(C2425R.C2427id.ad_layout);
        this.f9651r = (ImageView) this.f9653t.findViewById(C2425R.C2427id.ad_iv);
        this.f9652s = (ImageView) this.f9653t.findViewById(C2425R.C2427id.close_ad_iv);
        this.f9652s.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.View$OnClickListenerC269514 */

            public void onClick(View view) {
                MainActivity.this.m11270p();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: p */
    private void m11270p() {
        Animation loadAnimation = AnimationUtils.loadAnimation(GBApplication.instance(), C2425R.anim.slide_out_trans_scale);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.animationAnimation$AnimationListenerC269615 */

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                MainActivity.this.f9653t.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.f9653t.startAnimation(loadAnimation);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: q */
    private void m11273q() {
        Animation loadAnimation = AnimationUtils.loadAnimation(GBApplication.instance(), C2425R.anim.slide_in_trans_scale);
        this.f9653t.setVisibility(0);
        this.f9653t.startAnimation(loadAnimation);
    }

    /* renamed from: r */
    private void m11275r() {
        int d = DensityUtil.m14134d(this);
        this.f9647n = new TextView(GBApplication.instance());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, d);
        if (SystemUtil.m14531e()) {
            this.f9647n.setBackgroundColor(getResources().getColor(C2425R.color.yellow_4));
        } else {
            this.f9647n.setBackgroundColor(getResources().getColor(2131624113));
        }
        this.f9647n.setLayoutParams(layoutParams);
        ((ViewGroup) getWindow().getDecorView()).addView(this.f9647n);
    }

    public void setHeadTextColorGrey() {
        getWindow().setBackgroundDrawable(getResources().getDrawable(C2425R.color.yellow_4));
    }

    public void setHeadTextColorRed() {
        getWindow().setBackgroundDrawable(getResources().getDrawable(2131624113));
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onStop() {
        super.onStop();
    }

    @Override // com.gbcom.gwifi.base.activity.FragmentTabActivity, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        if (this.f9643j != null) {
            this.f9643j.onDestroy();
        }
        this.handler.removeCallbacksAndMessages(null);
        unregisterReceiver(this.f9625ad);
        unregisterReceiver(this.f9649p);
        unregisterReceiver(this.f9607L);
        unregisterReceiver(this.f9627af);
        unregisterReceiver(this.f9626ae);
        unregisterReceiver(this.f9628ag);
        unregisterReceiver(this.f9629ah);
        unregisterReceiver(this.f9630ai);
        if (this.f9605J != null) {
            this.f9605J.mo27143aU();
        }
        List<GBActivity> activitiesList = GBApplication.instance().getActivitiesList();
        AppDownloadActivity appDownloadActivity = null;
        for (int i = 0; i < activitiesList.size(); i++) {
            if (activitiesList.get(i) instanceof AppDownloadActivity) {
                appDownloadActivity = (AppDownloadActivity) activitiesList.get(i);
            }
        }
        if (appDownloadActivity != null) {
            appDownloadActivity.getThirdFragment().mo25509d();
            appDownloadActivity.finish();
        }
        CacheWiFi.getInstance().setApLocation("");
        super.onDestroy();
        if (this.f9601F != null) {
            unregisterReceiver(this.f9601F);
            this.f9601F = null;
        }
        if (this.f9602G != null) {
            unregisterReceiver(this.f9602G);
            this.f9602G = null;
        }
        m11233E();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: s */
    private void m11276s() {
        runOnUiThread(new Runnable() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.RunnableC269716 */

            @Override // java.lang.Runnable
            public void run() {
                if (!MainActivity.this.isFinishing()) {
                    MainActivity.this.f9637c.dismiss();
                }
                GiwifiHXService.getInstance(GBApplication.instance()).loginHX(MainActivity.this);
                if (MainActivity.this.f9641h != null && MainActivity.this.f9641h.isShowing()) {
                    MainActivity.this.f9641h.dismiss();
                }
            }
        });
    }

    public void showPopWindow(Context context) {
        if (this.f9641h == null) {
            this.f9642i = (ViewGroup) getLayoutInflater().inflate(C2425R.layout.sys_pop_menu_second, (ViewGroup) null, true);
            RelativeLayout relativeLayout = (RelativeLayout) this.f9642i.findViewById(C2425R.C2427id.service_qq);
            if (CacheAccount.getInstance().getAppKefuState() == 0) {
                relativeLayout.setVisibility(8);
            } else {
                relativeLayout.setVisibility(0);
            }
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.View$OnClickListenerC269817 */

                public void onClick(View view) {
                    if (MainActivity.this.f9604I) {
                        MainActivity.this.f9604I = false;
                        UmengCount.queryAppCustomerService(GBApplication.instance(), GBActivity.ONLINE_SERVICE);
                        if (GiwifiHXService.getInstance(GBApplication.instance()).isLoggedInBefore()) {
                            MainActivity.this.f9637c = MainActivity.this.m11259j();
                            MainActivity.this.f9637c.setMessage(MainActivity.this.getResources().getString(2131296331));
                            MainActivity.this.f9637c.show();
                            MainActivity.this.m11276s();
                            return;
                        }
                        MainActivity.this.f9622aa = true;
                        MainActivity.this.f9637c = MainActivity.this.m11259j();
                        MainActivity.this.f9637c.setMessage(MainActivity.this.getResources().getString(2131296331));
                        if (!MainActivity.this.f9637c.isShowing()) {
                            if (!MainActivity.this.isFinishing()) {
                                MainActivity.this.f9637c.show();
                            } else {
                                return;
                            }
                        }
                        String loginPhone = CacheAccount.getInstance().getLoginPhone();
                        GwifiHXHelper.getInstance().LoginHX(loginPhone, MD5Util.m10825a(loginPhone).substring(0, 8).toLowerCase(), new GwifiHXHelper.LoginCallBack() {
                            /* class com.gbcom.gwifi.functions.loading.MainActivity.View$OnClickListenerC269817.C26991 */

                            @Override // com.gbcom.gwifi.third.kefu.easemob.GwifiHXHelper.LoginCallBack
                            public void success() {
                                if (MainActivity.this.f9622aa) {
                                    MainActivity.this.m11276s();
                                }
                            }

                            @Override // com.gbcom.gwifi.third.kefu.easemob.GwifiHXHelper.LoginCallBack
                            public void error(final String str) {
                                MainActivity.this.f9604I = true;
                                if (MainActivity.this.f9637c != null && MainActivity.this.f9637c.isShowing()) {
                                    MainActivity.this.f9637c.dismiss();
                                }
                                if (MainActivity.this.f9622aa && !MainActivity.this.isFinishing()) {
                                    MainActivity.this.runOnUiThread(new Runnable() {
                                        /* class com.gbcom.gwifi.functions.loading.MainActivity.View$OnClickListenerC269817.C26991.RunnableC27001 */

                                        @Override // java.lang.Runnable
                                        public void run() {
                                            if (MainActivity.this.f9641h != null && MainActivity.this.f9641h.isShowing()) {
                                                MainActivity.this.f9641h.dismiss();
                                            }
                                            GBActivity.showMessageToast(str);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
            ((RelativeLayout) this.f9642i.findViewById(C2425R.C2427id.about_us_rl)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.View$OnClickListenerC270118 */

                public void onClick(View view) {
                    MainActivity.this.contactSalesMan(view);
                    if (MainActivity.this.f9641h != null && MainActivity.this.f9641h.isShowing()) {
                        MainActivity.this.f9641h.dismiss();
                    }
                }
            });
            ((RelativeLayout) this.f9642i.findViewById(C2425R.C2427id.exit_rl)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.View$OnClickListenerC270219 */

                public void onClick(View view) {
                    MainActivity.this.contactSuggest(view);
                    if (MainActivity.this.f9641h != null && MainActivity.this.f9641h.isShowing()) {
                        MainActivity.this.f9641h.dismiss();
                    }
                }
            });
            this.f9641h = new PopupWindow((View) this.f9642i, -1, -1, true);
            this.f9641h.setTouchable(true);
            this.f9642i.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.View$OnClickListenerC270420 */

                public void onClick(View view) {
                    MainActivity.this.f9641h.dismiss();
                }
            });
            this.f9641h.setBackgroundDrawable(new BitmapDrawable());
            this.f9641h.showAsDropDown(findViewById(C2425R.C2427id.include));
            this.f9641h.update();
        } else if (this.f9641h.isShowing()) {
            this.f9641h.dismiss();
            this.f9641h = null;
        } else {
            if (this.f9642i != null) {
                RelativeLayout relativeLayout2 = (RelativeLayout) this.f9642i.findViewById(C2425R.C2427id.service_qq);
                if (CacheAccount.getInstance().getAppKefuState() == 0) {
                    relativeLayout2.setVisibility(8);
                } else {
                    relativeLayout2.setVisibility(0);
                }
            }
            this.f9641h.showAsDropDown(findViewById(C2425R.C2427id.include));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: t */
    private void m11279t() {
        if (System.currentTimeMillis() - CacheApp.getInstance().getLastAutoCheckVersionTime() > 86400000) {
            Log.m14400c(f9595g, "check version.....");
            if (Config.m14094a().mo27812o() && Config.m14094a().mo27811n()) {
                checkVersion();
            } else if (!isCheckVersion) {
                isCheckVersion = true;
                checkVersionHidden();
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (m11289y()) {
            return true;
        }
        if (CacheAccount.getInstance().getUserLevel() == 3) {
            checkAndExitApp();
            return true;
        }
        m11280u();
        return true;
    }

    /* renamed from: u */
    private void m11280u() {
        if (this.f9650q) {
            if (this.f9655v == 2 && this.f9653t.getVisibility() == 0) {
                m11270p();
            }
            checkAndExitApp();
            return;
        }
        this.f9650q = true;
        if (this.f9655v == 0) {
            checkAndExitApp();
            return;
        }
        checkAndExitNoToastApp();
        switch (this.f9655v) {
            case 2:
                if (this.f9603H != null && this.f9603H.size() > 0) {
                    m11240a(this.f9603H);
                    return;
                }
                return;
            case 3:
            case 4:
            case 5:
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: v */
    private void m11282v() {
        List<HashMap<String, Object>> adExitViewList = CacheAd.getInstance().getAdExitViewList();
        if (adExitViewList != null && adExitViewList.size() > 0) {
            Iterator<HashMap<String, Object>> it = adExitViewList.iterator();
            if (it.hasNext()) {
                HashMap<String, Object> next = it.next();
                this.f9655v = ((Integer) next.get("adState")).intValue();
                if (this.f9655v == 2) {
                    this.f9603H = next;
                }
            }
            switch (this.f9655v) {
                case 3:
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private void m11240a(HashMap hashMap) {
        if (C3467s.m14513e(this.f9654u) && hashMap.containsKey("localImgUrl")) {
            this.f9654u = (String) hashMap.get("localImgUrl");
            if (hashMap.containsKey("localWebUrl")) {
                this.f9645l = (String) hashMap.get("localWebUrl");
            }
            if (hashMap.containsKey("sourceType")) {
                this.f9656w = ((Integer) hashMap.get("sourceType")).intValue();
            }
            if (hashMap.containsKey("productId")) {
                this.f9657x = ((Integer) hashMap.get("productId")).intValue();
            }
            if (hashMap.containsKey("productType")) {
                this.f9658y = (String) hashMap.get("productType");
            }
            if (!C3467s.m14513e(this.f9654u)) {
                ImageTools.m14153a(this.f9654u, this.f9651r, this.f9638d);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: w */
    private void m11284w() {
        UmengCount.queryBackScreenAd(GBApplication.instance(), "退出的广告");
        if (this.f9655v == 2) {
            switch (this.f9656w) {
                case 1:
                    m11238a(this.f9657x, this.f9658y);
                    return;
                case 2:
                    if (!C3467s.m14513e(this.f9645l)) {
                        openBackWebView(this.f9645l, "");
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: x */
    private void m11287x() {
    }

    public void finish() {
        super.finish();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: y */
    private boolean m11289y() {
        if (this.f9605J == null || this.f9605J.getVisibility() != 0 || this.f9597B) {
            return false;
        }
        if (this.f9606K != null) {
            this.f9606K.setVisibility(8);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(this.f9605J, 0, 0, (float) Math.hypot((double) this.f9605J.getWidth(), (double) this.f9605J.getHeight()), 10.0f);
            createCircularReveal.addListener(new Animator.AnimatorListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.C270722 */

                public void onAnimationStart(Animator animator) {
                    MainActivity.this.f9597B = true;
                }

                public void onAnimationEnd(Animator animator) {
                    if (MainActivity.this.f9605J != null) {
                        MainActivity.this.f9605J.setVisibility(8);
                    }
                    MainActivity.this.f9597B = false;
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            });
            createCircularReveal.setDuration(1000);
            createCircularReveal.start();
        } else {
            Animation loadAnimation = AnimationUtils.loadAnimation(this, C2425R.anim.wifiinfoview_exit);
            this.f9605J.startAnimation(loadAnimation);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.animationAnimation$AnimationListenerC270924 */

                public void onAnimationStart(Animation animation) {
                    if (MainActivity.this.f9606K != null) {
                        MainActivity.this.f9606K.setVisibility(8);
                    }
                    MainActivity.this.f9597B = true;
                }

                public void onAnimationEnd(Animation animation) {
                    if (MainActivity.this.f9605J != null) {
                        MainActivity.this.f9605J.setVisibility(8);
                    }
                    MainActivity.this.f9597B = false;
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
        return true;
    }

    public void showWiFiView() {
        if (this.f9605J != null && this.f9605J.getVisibility() != 0) {
            m11233E();
            checkWifiLocation();
            if (this.f9597B) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(this.f9605J, 0, 0, 0.0f, (float) Math.hypot((double) this.f9605J.getWidth(), (double) this.f9605J.getHeight()));
                createCircularReveal.addListener(new Animator.AnimatorListener() {
                    /* class com.gbcom.gwifi.functions.loading.MainActivity.C271025 */

                    public void onAnimationStart(Animator animator) {
                        if (MainActivity.this.f9605J != null) {
                            MainActivity.this.f9605J.setVisibility(0);
                            MainActivity.this.f9605J.mo27141aS();
                        }
                        MainActivity.this.f9597B = true;
                    }

                    public void onAnimationEnd(Animator animator) {
                        if (MainActivity.this.f9606K != null) {
                            MainActivity.this.f9606K.setVisibility(0);
                        }
                        MainActivity.this.f9597B = false;
                    }

                    public void onAnimationCancel(Animator animator) {
                    }

                    public void onAnimationRepeat(Animator animator) {
                    }
                });
                createCircularReveal.setDuration(1000);
                if (this.f9605J != null) {
                    this.f9605J.setVisibility(0);
                    this.f9605J.mo27141aS();
                }
                createCircularReveal.start();
                return;
            }
            if (this.f9605J != null) {
                this.f9605J.setVisibility(0);
                this.f9605J.mo27141aS();
            }
            Animation loadAnimation = AnimationUtils.loadAnimation(this, C2425R.anim.wifiinfoview_enter);
            if (this.f9605J != null) {
                this.f9605J.startAnimation(loadAnimation);
            }
            loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.animationAnimation$AnimationListenerC271126 */

                public void onAnimationStart(Animation animation) {
                    if (MainActivity.this.f9653t != null) {
                        MainActivity.this.f9653t.setVisibility(8);
                    }
                }

                public void onAnimationEnd(Animation animation) {
                    if (MainActivity.this.f9606K != null) {
                        MainActivity.this.f9606K.setVisibility(0);
                    }
                    if (MainActivity.this.f9653t != null) {
                        MainActivity.this.f9653t.setVisibility(8);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }

    public void hideWiFiView() {
        if (this.f9605J == null || this.f9605J.getVisibility() != 0 || this.f9597B) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(this.f9605J, 0, 0, (float) Math.hypot((double) this.f9605J.getWidth(), (double) this.f9605J.getHeight()), 0.0f);
            createCircularReveal.addListener(new Animator.AnimatorListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.C271227 */

                public void onAnimationStart(Animator animator) {
                    if (MainActivity.this.f9606K != null) {
                        MainActivity.this.f9606K.setVisibility(8);
                    }
                    MainActivity.this.f9597B = true;
                }

                public void onAnimationEnd(Animator animator) {
                    if (MainActivity.this.f9605J != null) {
                        MainActivity.this.f9605J.setVisibility(8);
                    }
                    MainActivity.this.f9597B = false;
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            });
            createCircularReveal.setDuration(1000);
            createCircularReveal.start();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this, C2425R.anim.wifiinfoview_exit);
        this.f9605J.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.animationAnimation$AnimationListenerC271328 */

            public void onAnimationStart(Animation animation) {
                if (MainActivity.this.f9606K != null) {
                    MainActivity.this.f9606K.setVisibility(8);
                }
                MainActivity.this.f9597B = true;
            }

            public void onAnimationEnd(Animation animation) {
                if (MainActivity.this.f9605J != null) {
                    MainActivity.this.f9605J.setVisibility(8);
                }
                MainActivity.this.f9597B = false;
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void showAndClose() {
        if (this.f9605J != null) {
            if (this.f9605J.getVisibility() == 0) {
                hideWiFiView();
            } else {
                showWiFiView();
            }
        }
    }

    /* renamed from: z */
    private void m11291z() {
        int a = VersionUtil.m14564a(this);
        if (!CacheApp.getInstance().getQuanziGuide() || a != CacheApp.getInstance().getFirstGuideCode().intValue()) {
            CacheApp.getInstance().setQuanziGuide(true);
            CacheApp.getInstance().setFirstGuideCode(Integer.valueOf(a));
            final View inflate = ((ViewStub) findViewById(C2425R.C2427id.quanzi_vs)).inflate();
            final int[] iArr = {C2425R.C2426drawable.circle1, C2425R.C2426drawable.circle2, C2425R.C2426drawable.circle3};
            this.f9621Z = (JazzyViewPager) findViewById(C2425R.C2427id.quanzi_guide_pager);
            this.f9621Z.mo28279a(JazzyViewPager.EnumC3486b.Accordion);
            this.f9621Z.setAdapter(new PagerAdapter() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.C271429 */

                @Override // android.support.p009v4.view.PagerAdapter
                public Object instantiateItem(ViewGroup viewGroup, final int i) {
                    ImageView imageView = new ImageView(GBApplication.instance());
                    int a = DensityUtil.m14127a(GBApplication.instance());
                    imageView.setImageResource(iArr[i]);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    viewGroup.addView(imageView, -1, a);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        /* class com.gbcom.gwifi.functions.loading.MainActivity.C271429.View$OnClickListenerC27151 */

                        public void onClick(View view) {
                            if (i == iArr.length - 1) {
                                inflate.setVisibility(8);
                            }
                        }
                    });
                    return imageView;
                }

                @Override // android.support.p009v4.view.PagerAdapter
                public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                    View view = (View) obj;
                    viewGroup.removeView((View) obj);
                }

                @Override // android.support.p009v4.view.PagerAdapter
                public int getCount() {
                    return iArr.length;
                }

                @Override // android.support.p009v4.view.PagerAdapter
                public boolean isViewFromObject(View view, Object obj) {
                    return view instanceof OutlineContainer ? ((OutlineContainer) view).getChildAt(0) == obj : view == obj;
                }
            });
        }
    }

    /* renamed from: A */
    private void m11226A() {
        this.f9601F = new BroadcastReceiver() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.C271730 */

            public void onReceive(Context context, Intent intent) {
                Log.m14398b(MainActivity.f9595g, "CircleMessageReceiver onReceive");
                if (MainActivity.this.f9598C != -1) {
                    MainActivity.this.setCircleUnReadMsgState(true);
                    MainActivity.m11229C(MainActivity.this);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Const.f8108aV);
        registerReceiver(this.f9601F, intentFilter, Const.f8109aW, null);
        this.f9602G = new BroadcastReceiver() {
            /* class com.gbcom.gwifi.functions.loading.MainActivity.C271831 */

            public void onReceive(Context context, Intent intent) {
                HashMap hashMap;
                Log.m14398b(MainActivity.f9595g, "CircleUpdateUserReceiver onReceive");
                String stringExtra = intent.getStringExtra("userData");
                if (!TextUtils.isEmpty(stringExtra) && (hashMap = (HashMap) JsonUtil.m14386a(stringExtra, HashMap.class)) != null && !hashMap.isEmpty()) {
                    if (hashMap.containsKey("avatar")) {
                        String str = (String) hashMap.get("avatar");
                        if (!C3467s.m14513e(str)) {
                            CacheAccount.getInstance().setCircleAvatarUrl(str);
                            GBApplication.instance().sendBroadcast(new Intent(Constant.f13231bF));
                        }
                    }
                    if (hashMap.containsKey(CommonNetImpl.SEX)) {
                        CacheAccount.getInstance().setCircleSex(Integer.parseInt((String) hashMap.get(CommonNetImpl.SEX)));
                    }
                    if (hashMap.containsKey("birthday")) {
                        CacheAccount.getInstance().setCircleBirth((String) hashMap.get("birthday"));
                    }
                    if (hashMap.containsKey("city")) {
                        CacheAccount.getInstance().setCircleCity((String) hashMap.get("city"));
                    }
                }
            }
        };
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(Const.f8110aX);
        registerReceiver(this.f9602G, intentFilter2, Const.f8111aY, null);
    }

    public void setCircleUnReadMsgState(boolean z) {
        if (getTabHost() != null && this.f9598C == 1) {
            ImageView imageView = (ImageView) m11245b(this.f9598C).findViewById(2131755155);
            if (z && this.f9609N.intValue() != 1) {
                if (this.f9599D == null) {
                    this.f9599D = new BadgeHelper(this);
                }
                if (this.f9600E < 1) {
                    this.f9600E = 1;
                }
                this.f9599D.mo23955a(1);
                this.f9599D.mo23958a(true);
                this.f9599D.mo23957a(40, 0, 0, 0);
                this.f9599D.mo23956a(12, 12);
                this.f9599D.mo23961a(imageView);
                this.f9599D.mo23965e(this.f9600E);
            } else if (this.f9609N.intValue() == this.f9598C) {
                this.f9600E = 0;
                if (this.f9599D != null) {
                    ((ViewGroup) imageView.getParent()).removeView(this.f9599D);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo24953c() {
        if (mo24955d()) {
            Log.m14398b(f9595g, " ShowFloatWindow");
            this.handler.post(this.f9633al);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public boolean mo24955d() {
        if ((getLastFragment() instanceof CircleFragment) || (getLastFragment() instanceof MessageFragment)) {
            return true;
        }
        if (this.f9636ao != null) {
            this.f9636ao.cancel();
        }
        m11231D();
        mo24959g();
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public boolean mo24956e() {
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo24957f() {
        this.f9632ak.mo24487a((GBActivity) this, this.f9640f);
        m11228B();
        this.f9636ao.start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public void mo24959g() {
        if (this.f9632ak != null) {
            this.f9632ak.mo24488b();
        }
    }

    /* renamed from: B */
    private void m11228B() {
        IFloatView a = this.f9632ak.mo24485a();
        if (a != null) {
            m11230C();
            a.mo24463a(new FloatViewListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.C272033 */

                @Override // com.gbcom.gwifi.p235c.p236a.FloatViewListener
                /* renamed from: a */
                public void mo24481a() {
                    MainActivity.this.m11231D();
                    MainActivity.this.mo24959g();
                }

                @Override // com.gbcom.gwifi.p235c.p236a.FloatViewListener
                /* renamed from: b */
                public void mo24482b() {
                    MainActivity.this.mo24964h();
                }

                @Override // com.gbcom.gwifi.p235c.p236a.FloatViewListener
                /* renamed from: c */
                public void mo24483c() {
                    Log.m14398b(MainActivity.f9595g, "onMoved");
                }

                @Override // com.gbcom.gwifi.p235c.p236a.FloatViewListener
                /* renamed from: d */
                public void mo24484d() {
                    Log.m14398b(MainActivity.f9595g, "onDragged");
                }
            });
        }
    }

    /* renamed from: C */
    private void m11230C() {
        getWindow().addFlags(128);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: D */
    private void m11231D() {
        getWindow().clearFlags(128);
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public void mo24964h() {
        Toast.makeText(this.f9639e, "FloatWindow clicked", 1).show();
    }

    public int getActionBarHeight() {
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        new Rect();
        int abs = Math.abs(rect.top - getWindow().findViewById(16908290).getTop());
        Log.m14398b(f9595g, "标题栏高度：" + abs);
        return abs;
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public void mo24966i() {
        if (FloatingPermissionCompat.m10767a().mo24500a(this.f9639e)) {
            mo24953c();
        } else {
            new AlertDialog.Builder(this.f9639e).setTitle("悬浮窗权限未开启").setMessage("你的手机没有授权" + this.f9639e.getString(2131296409) + "获得悬浮窗权限，视频悬浮窗功能将无法正常使用").setPositiveButton("开启", new DialogInterface.OnClickListener() {
                /* class com.gbcom.gwifi.functions.loading.MainActivity.DialogInterface$OnClickListenerC272235 */

                public void onClick(DialogInterface dialogInterface, int i) {
                    FloatingPermissionCompat.m10767a().mo24502b(MainActivity.this.f9639e);
                }
            }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
        }
    }

    @Override // android.support.p009v4.app.FragmentActivity
    public void onBackPressed() {
        super.onBackPressed();
        m11233E();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: E */
    private void m11233E() {
        if (this.f9640f != 12) {
            mo24959g();
        }
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.ActivityCompat.OnRequestPermissionsResultCallback, android.support.p009v4.app.FragmentActivity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        boolean z = false;
        super.onRequestPermissionsResult(i, strArr, iArr);
        StringBuilder sb = new StringBuilder();
        int length = strArr.length;
        StringBuilder sb2 = sb;
        for (int i2 = 0; i2 < length; i2++) {
            sb2 = sb2.append(strArr[i2] + "\r\n");
        }
        switch (i) {
            case 1024:
                int i3 = 0;
                while (true) {
                    if (i3 >= iArr.length) {
                        z = true;
                    } else if (iArr[i3] == -1) {
                        this.f9635an.mo25032b();
                    } else {
                        i3++;
                    }
                }
                if (z) {
                    this.f9635an.mo25031a();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void requestPermissions(Context context, String[] strArr, AbstractC2735a aVar) {
        boolean z = false;
        this.f9635an = aVar;
        StringBuilder sb = new StringBuilder();
        int length = strArr.length;
        StringBuilder sb2 = sb;
        for (int i = 0; i < length; i++) {
            sb2 = sb2.append(strArr[i] + "\r\n");
        }
        int length2 = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length2) {
                z = true;
                break;
            } else if (ContextCompat.checkSelfPermission(context, strArr[i2]) == -1) {
                ActivityCompat.requestPermissions((Activity) context, strArr, 1024);
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            this.f9635an.mo25031a();
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.loading.MainActivity$b */
    public class CountDownTimerC2736b extends CountDownTimer {
        public CountDownTimerC2736b(long j, long j2) {
            super(j, j2);
        }

        public void onTick(long j) {
        }

        public void onFinish() {
            try {
                MainActivity.this.m11233E();
            } catch (Exception e) {
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        checkAgreementYes();
    }
}
