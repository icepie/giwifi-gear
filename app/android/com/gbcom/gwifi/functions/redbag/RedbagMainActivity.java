package com.gbcom.gwifi.functions.redbag;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.StatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.redbag.domain.AccountInfo;
import com.gbcom.gwifi.functions.redbag.domain.AccountPrizePoolBonus;
import com.gbcom.gwifi.functions.redbag.domain.Config;
import com.gbcom.gwifi.functions.redbag.domain.HitRecord;
import com.gbcom.gwifi.functions.redbag.domain.JoinHistory;
import com.gbcom.gwifi.functions.redbag.domain.LastRound;
import com.gbcom.gwifi.functions.redbag.domain.LuckyRedbagSimple;
import com.gbcom.gwifi.functions.redbag.domain.PrizePoolSetting;
import com.gbcom.gwifi.functions.redbag.domain.Redbag;
import com.gbcom.gwifi.functions.redbag.domain.ThisRound;
import com.gbcom.gwifi.functions.redbag.msg.AccountInfoRequest;
import com.gbcom.gwifi.functions.redbag.msg.AccountInfoResponse;
import com.gbcom.gwifi.functions.redbag.msg.ConfigRequest;
import com.gbcom.gwifi.functions.redbag.msg.ConfigResponse;
import com.gbcom.gwifi.functions.redbag.msg.HeartBeatOnNotify;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagBettingNotify;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagBettingRequest;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagBettingResponse;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagDetailRequest;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagDetailResponse;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagIntoRequest;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagIntoResponse;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagJoinedRequest;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagJoinedResponse;
import com.gbcom.gwifi.functions.redbag.msg.NewComerRedbagBettingRequest;
import com.gbcom.gwifi.functions.redbag.msg.NewComerRedbagBettingResponse;
import com.gbcom.gwifi.functions.redbag.msg.NotifyMsg;
import com.gbcom.gwifi.functions.redbag.msg.PrizePoolRequest;
import com.gbcom.gwifi.functions.redbag.msg.PrizePoolResponse;
import com.gbcom.gwifi.functions.redbag.msg.RankingRequest;
import com.gbcom.gwifi.functions.redbag.msg.RankingResponse;
import com.gbcom.gwifi.functions.redbag.msg.RedbagBettingRequest;
import com.gbcom.gwifi.functions.redbag.msg.RedbagBettingResponse;
import com.gbcom.gwifi.functions.redbag.msg.RedbagDetailRequest;
import com.gbcom.gwifi.functions.redbag.msg.RedbagDetailResponse;
import com.gbcom.gwifi.functions.redbag.msg.RedbagExitRequest;
import com.gbcom.gwifi.functions.redbag.msg.RedbagIntoRequest;
import com.gbcom.gwifi.functions.redbag.msg.RedbagIntoResponse;
import com.gbcom.gwifi.functions.redbag.msg.RedbagJoinedRequest;
import com.gbcom.gwifi.functions.redbag.msg.RedbagJoinedResponse;
import com.gbcom.gwifi.functions.redbag.msg.RedbagListRequest;
import com.gbcom.gwifi.functions.redbag.msg.RedbagListResponse;
import com.gbcom.gwifi.functions.redbag.msg.RedbagMessageNotify;
import com.gbcom.gwifi.functions.redbag.msg.RedbagMessageResponse;
import com.gbcom.gwifi.functions.redbag.msg.RedbagMessegeRequest;
import com.gbcom.gwifi.functions.redbag.msg.RedbagNotifyResult;
import com.gbcom.gwifi.functions.redbag.p251a.CommonListAdapter;
import com.gbcom.gwifi.functions.redbag.p251a.RedbagListAdapter;
import com.gbcom.gwifi.functions.redbag.view.BalanceImage;
import com.gbcom.gwifi.functions.redbag.view.BonusImage;
import com.gbcom.gwifi.functions.redbag.view.MessageImage;
import com.gbcom.gwifi.functions.redbag.view.PrizePoolImage;
import com.gbcom.gwifi.functions.redbag.view.RedbagWelfareView;
import com.gbcom.gwifi.functions.redbag.view.TabContainer;
import com.gbcom.gwifi.functions.revenue.ScoreActivity;
import com.gbcom.gwifi.p221a.p222a.ClientStarter;
import com.gbcom.gwifi.p221a.p222a.MsgDispatcherListener;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.LoadingView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.p136b.p137a.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;
import p419io.netty.channel.Channel;
import p419io.netty.channel.ChannelHandlerContext;
import p419io.netty.handler.timeout.IdleStateHandler;

public class RedbagMainActivity extends GBActivity implements View.OnClickListener, ClientStarter.AbstractC2435a, MsgDispatcherListener, RedbagListAdapter.AbstractC3067a, RedbagListAdapter.AbstractC3068b, TabContainer.AbstractC3078a {
    public static Channel channel = null;

    /* renamed from: d */
    private static final Object f11069d = "RedbagMainActivity";

    /* renamed from: e */
    private static final String f11070e = "notify_update";

    /* renamed from: f */
    private static final int f11071f = 2;

    /* renamed from: g */
    private static final int f11072g = 0;

    /* renamed from: h */
    private static final int f11073h = 1;

    /* renamed from: i */
    private static final String f11074i = "notify_result";

    /* renamed from: j */
    private static final String f11075j = "notify_msg";

    /* renamed from: k */
    private static final String f11076k = "lucky_notify";

    /* renamed from: A */
    private TextView f11077A;

    /* renamed from: B */
    private int f11078B = 0;

    /* renamed from: C */
    private ClientStarter f11079C;

    /* renamed from: D */
    private String f11080D;

    /* renamed from: E */
    private boolean f11081E;

    /* renamed from: F */
    private ImageView f11082F;

    /* renamed from: G */
    private TextView f11083G;

    /* renamed from: H */
    private TextView f11084H;

    /* renamed from: I */
    private String f11085I;

    /* renamed from: J */
    private boolean f11086J;

    /* renamed from: K */
    private BroadcastReceiver f11087K;

    /* renamed from: L */
    private boolean f11088L;

    /* renamed from: M */
    private LinearLayout f11089M;

    /* renamed from: N */
    private BalanceImage f11090N;

    /* renamed from: O */
    private int f11091O;

    /* renamed from: P */
    private PrizePoolResponse f11092P;

    /* renamed from: Q */
    private int[] f11093Q = {C2425R.C2426drawable.ranking1, C2425R.C2426drawable.ranking2, C2425R.C2426drawable.ranking3};

    /* renamed from: R */
    private boolean f11094R;

    /* renamed from: S */
    private int f11095S = 5000;

    /* renamed from: T */
    private int f11096T = 30000;

    /* renamed from: U */
    private LinearLayout f11097U;

    /* renamed from: V */
    private TabContainer f11098V;

    /* renamed from: W */
    private LoadingView f11099W;

    /* renamed from: X */
    private boolean f11100X;

    /* renamed from: Y */
    private boolean f11101Y;

    /* renamed from: Z */
    private boolean f11102Z;

    /* renamed from: a */
    List<RedbagMessageResponse.ResponseBean.PrizePoolBonusesBean> f11103a = new ArrayList();

    /* renamed from: aA */
    private int f11104aA;

    /* renamed from: aB */
    private RelativeLayout f11105aB;

    /* renamed from: aC */
    private RedbagWelfareView f11106aC;

    /* renamed from: aD */
    private RedbagWelfareView f11107aD;

    /* renamed from: aE */
    private RedbagWelfareView f11108aE;

    /* renamed from: aF */
    private RedbagWelfareView f11109aF;

    /* renamed from: aG */
    private int f11110aG = -1;

    /* renamed from: aH */
    private BroadcastReceiver f11111aH;

    /* renamed from: aI */
    private PopupWindow f11112aI;

    /* renamed from: aJ */
    private PopupWindow f11113aJ;

    /* renamed from: aK */
    private int f11114aK;

    /* renamed from: aL */
    private int f11115aL;

    /* renamed from: aM */
    private boolean f11116aM;

    /* renamed from: aN */
    private boolean f11117aN = true;

    /* renamed from: aO */
    private int f11118aO;

    /* renamed from: aP */
    private HashMap<Integer, View> f11119aP = new HashMap<>();

    /* renamed from: aQ */
    private ArrayList<LuckyRedbagSimple> f11120aQ = new ArrayList<>();

    /* renamed from: aR */
    private LuckyRedbagDetailResponse f11121aR;

    /* renamed from: aS */
    private long f11122aS;

    /* renamed from: aT */
    private int f11123aT = 0;

    /* renamed from: aU */
    private boolean f11124aU;

    /* renamed from: aV */
    private boolean f11125aV = false;

    /* renamed from: aW */
    private boolean f11126aW;

    /* renamed from: aX */
    private IdleStateHandler f11127aX;

    /* renamed from: aY */
    private RelativeLayout f11128aY;

    /* renamed from: aZ */
    private MessageImage f11129aZ;

    /* renamed from: aa */
    private PopupWindow f11130aa;

    /* renamed from: ab */
    private PopupWindow f11131ab;

    /* renamed from: ac */
    private PopupWindow f11132ac;

    /* renamed from: ad */
    private PopupWindow f11133ad;

    /* renamed from: ae */
    private boolean f11134ae;

    /* renamed from: af */
    private boolean f11135af;

    /* renamed from: ag */
    private String f11136ag;

    /* renamed from: ah */
    private String f11137ah;

    /* renamed from: ai */
    private boolean f11138ai;

    /* renamed from: aj */
    private HashMap<Integer, String> f11139aj = new HashMap<>();

    /* renamed from: ak */
    private HashMap<Integer, String> f11140ak;

    /* renamed from: al */
    private int f11141al;

    /* renamed from: am */
    private String f11142am;

    /* renamed from: an */
    private String f11143an;

    /* renamed from: ao */
    private boolean f11144ao;

    /* renamed from: ap */
    private boolean f11145ap = false;

    /* renamed from: aq */
    private boolean f11146aq;

    /* renamed from: ar */
    private int f11147ar;

    /* renamed from: as */
    private int f11148as;

    /* renamed from: at */
    private int f11149at = -1;

    /* renamed from: au */
    private int f11150au;

    /* renamed from: av */
    private int f11151av;

    /* renamed from: aw */
    private ImageView f11152aw;

    /* renamed from: ax */
    private int f11153ax;

    /* renamed from: ay */
    private int f11154ay;

    /* renamed from: az */
    private int f11155az;

    /* renamed from: b */
    DisplayImageOptions f11156b = new DisplayImageOptions.Builder().showImageOnLoading(C2425R.C2426drawable.loading_small).showImageForEmptyUri(C2425R.C2426drawable.user).showImageOnFail(C2425R.C2426drawable.user).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();

    /* renamed from: ba */
    private int f11157ba;

    /* renamed from: bb */
    private boolean f11158bb;

    /* renamed from: bc */
    private Handler f11159bc = new Handler();

    /* renamed from: bd */
    private TextView f11160bd;

    /* renamed from: be */
    private String f11161be = "";

    /* renamed from: bf */
    private boolean f11162bf;

    /* renamed from: bg */
    private boolean f11163bg;

    /* renamed from: c */
    OkRequestHandler<String> f11164c = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.C30375 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            RedbagMainActivity.this.errWebDrawable();
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            try {
                CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
                if (!GBActivity.dealException(deSerializeJson)) {
                    String data = deSerializeJson.getData();
                    if (!TextUtils.isEmpty(data)) {
                        JSONObject jSONObject = new JSONObject(data);
                        RedbagMainActivity.this.f11168o = Integer.parseInt(jSONObject.getString("uid"));
                        CacheAccount.getInstance().setUserId(RedbagMainActivity.this.f11168o);
                        Log.d("aaaaaa", "mUid:" + RedbagMainActivity.this.f11168o);
                    }
                }
                if (RedbagMainActivity.this.f11168o != 0) {
                    RedbagMainActivity.this.m12438b();
                } else {
                    RedbagMainActivity.this.errWebDrawable();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                if (RedbagMainActivity.this.f11168o != 0) {
                    RedbagMainActivity.this.m12438b();
                } else {
                    RedbagMainActivity.this.errWebDrawable();
                }
            } catch (Throwable th) {
                if (RedbagMainActivity.this.f11168o != 0) {
                    RedbagMainActivity.this.m12438b();
                } else {
                    RedbagMainActivity.this.errWebDrawable();
                }
                throw th;
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };
    public ChannelHandlerContext ctx;

    /* renamed from: l */
    private RelativeLayout f11165l;

    /* renamed from: m */
    private TextView f11166m;

    /* renamed from: n */
    private TextView f11167n;

    /* renamed from: o */
    private int f11168o = 0;

    /* renamed from: p */
    private Gson f11169p;

    /* renamed from: q */
    private RedbagListResponse f11170q;

    /* renamed from: r */
    private ListView f11171r;

    /* renamed from: s */
    private List<Object> f11172s = new ArrayList();

    /* renamed from: t */
    private List<Object> f11173t = new ArrayList();

    /* renamed from: u */
    private RedbagListAdapter f11174u;

    /* renamed from: v */
    private int f11175v;

    /* renamed from: w */
    private String f11176w;

    /* renamed from: x */
    private PrizePoolImage f11177x;

    /* renamed from: y */
    private ArrayList<Redbag> f11178y = new ArrayList<>();

    /* renamed from: z */
    private Executor f11179z = Executors.newFixedThreadPool(10);

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "抢红包", C2425R.layout.redbag_activity_main, true);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f11153ax = displayMetrics.widthPixels;
        this.f11154ay = displayMetrics.heightPixels;
        m12450c();
        this.f11140ak = CacheAccount.getInstance().getUserBalance();
        m12419a();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12419a() {
        int userId = CacheAccount.getInstance().getUserId();
        if (userId != 0) {
            this.f11168o = userId;
            m12438b();
            Log.d("aaaaaa", "CacheAccount.getInstance().getUserId()=" + this.f11168o);
            return;
        }
        HttpUtil.m14290b(GBApplication.instance(), this.f11164c, f11069d);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12438b() {
        if (this.f11079C == null) {
            this.f11079C = new ClientStarter(this);
        }
        this.f11079C.mo24045a(this);
        this.f11179z.execute(new Runnable() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC29941 */

            @Override // java.lang.Runnable
            public void run() {
                RedbagMainActivity.this.f11079C.mo24044a();
            }
        });
    }

    /* renamed from: c */
    private void m12450c() {
        this.f11165l = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11165l.setOnClickListener(this);
        this.f11166m = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11167n = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11166m.setText("抢红包");
        this.f11167n.setVisibility(8);
        this.f11089M = (LinearLayout) findViewById(C2425R.C2427id.ll_head);
        this.f11171r = (ListView) findViewById(C2425R.C2427id.lv_main);
        this.f11171r.setVerticalScrollBarEnabled(false);
        this.f11177x = (PrizePoolImage) findViewById(C2425R.C2427id.prizepool);
        this.f11177x.setOnClickListener(this);
        this.f11090N = (BalanceImage) findViewById(C2425R.C2427id.balanceimage);
        this.f11077A = (TextView) findViewById(C2425R.C2427id.tv_balance);
        this.f11082F = (ImageView) findViewById(C2425R.C2427id.more);
        this.f11082F.setOnClickListener(this);
        this.f11098V = (TabContainer) findViewById(C2425R.C2427id.tabcontainer);
        this.f11098V.mo26759a(this);
        this.f11097U = (LinearLayout) findViewById(C2425R.C2427id.ll_content);
        this.f11087K = new BroadcastReceiver() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.C299812 */

            public void onReceive(Context context, Intent intent) {
                RedbagMainActivity.this.f11086J = true;
                RedbagMainActivity.this.f11085I = intent.getStringExtra("redName");
                RedbagMainActivity.this.f11148as = intent.getIntExtra("totalBeans", -1);
                RedbagMainActivity.this.processRequestRedbagDetail(RedbagMainActivity.this.f11168o, intent.getIntExtra("redId", -1), intent.getStringExtra("roundNo"));
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("startactivity");
        GBApplication.instance().registerReceiver(this.f11087K, intentFilter);
        this.f11088L = true;
        this.f11111aH = new BroadcastReceiver() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.C301123 */

            public void onReceive(Context context, Intent intent) {
                RedbagMainActivity.this.f11145ap = intent.getBooleanExtra("isFromLuckyJoined", false);
                final int intExtra = intent.getIntExtra("LuckyId", -1);
                RedbagMainActivity.this.f11179z.execute(new Runnable() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.C301123.RunnableC30121 */

                    @Override // java.lang.Runnable
                    public void run() {
                        RedbagMainActivity.this.m12421a((RedbagMainActivity) RedbagMainActivity.this.f11168o, intExtra);
                    }
                });
            }
        };
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("startLuckyActivity");
        GBApplication.instance().registerReceiver(this.f11111aH, intentFilter2);
        this.f11099W = (LoadingView) findViewById(C2425R.C2427id.loadingview);
        startOfficeDrawable();
        this.f11099W.mo28298a(new LoadingView.AbstractC3488a() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.C302534 */

            @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
            /* renamed from: a */
            public void mo24655a(View view) {
                RedbagMainActivity.this.startOfficeDrawable();
                RedbagMainActivity.this.m12419a();
            }
        });
        ((BalanceImage) findViewById(C2425R.C2427id.balanceimage)).setOnClickListener(this);
        this.f11105aB = (RelativeLayout) findViewById(2131755340);
        this.f11128aY = (RelativeLayout) findViewById(C2425R.C2427id.luckyMesssge);
        this.f11129aZ = (MessageImage) findViewById(C2425R.C2427id.luckyText);
        this.f11160bd = (TextView) findViewById(C2425R.C2427id.tv_messsge);
        this.f11160bd.setSelected(true);
    }

    /* renamed from: a */
    private void m12434a(List<HitRecord> list, HitRecord hitRecord, int i) {
        int i2 = ((RelativeLayout.LayoutParams) this.f11128aY.getLayoutParams()).leftMargin;
        this.f11129aZ.mo26744a("恭喜" + hitRecord.getNickName() + "抽中幸运数字" + hitRecord.getHitBeans() + ",获得" + hitRecord.getLuckyNumberBonus() + Constant.f13309cr);
        this.f11128aY.measure(0, 0);
        int i3 = i2 - this.f11153ax;
        int measuredWidth = (this.f11153ax - this.f11128aY.getMeasuredWidth()) / 2;
        final int measuredWidth2 = i3 + measuredWidth + 0 + this.f11128aY.getMeasuredWidth();
        int measuredWidth3 = measuredWidth + this.f11128aY.getMeasuredWidth();
        final ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, (-measuredWidth2) - measuredWidth3);
        valueAnimator.setDuration(StatsManager.DEFAULT_TIMEOUT_MILLIS);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.C302936 */

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (Build.VERSION.SDK_INT >= 19 && intValue <= (-measuredWidth2) && !RedbagMainActivity.this.f11158bb) {
                    RedbagMainActivity.this.f11158bb = true;
                    valueAnimator.pause();
                    RedbagMainActivity.this.f11159bc.postDelayed(new Runnable() {
                        /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.C302936.RunnableC30301 */

                        @Override // java.lang.Runnable
                        public void run() {
                            if (Build.VERSION.SDK_INT >= 19) {
                                valueAnimator.resume();
                            }
                        }
                    }, StatsManager.DEFAULT_TIMEOUT_MILLIS);
                }
                RedbagMainActivity.this.f11128aY.setTranslationX((float) intValue);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.C303137 */

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                RedbagMainActivity.this.f11158bb = false;
                RedbagMainActivity.this.f11162bf = false;
            }
        });
        valueAnimator.start();
    }

    /* renamed from: a */
    private void m12432a(String str) {
        int i = ((RelativeLayout.LayoutParams) this.f11128aY.getLayoutParams()).leftMargin;
        this.f11129aZ.mo26744a(str);
        this.f11128aY.measure(0, 0);
        int i2 = i - this.f11153ax;
        int measuredWidth = (this.f11153ax - this.f11128aY.getMeasuredWidth()) / 2;
        final int measuredWidth2 = i2 + measuredWidth + 0 + this.f11128aY.getMeasuredWidth();
        int measuredWidth3 = measuredWidth + this.f11128aY.getMeasuredWidth();
        final ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, (-measuredWidth2) - measuredWidth3);
        valueAnimator.setDuration(StatsManager.DEFAULT_TIMEOUT_MILLIS);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.C303238 */

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (Build.VERSION.SDK_INT >= 19 && intValue <= (-measuredWidth2) && !RedbagMainActivity.this.f11158bb) {
                    RedbagMainActivity.this.f11158bb = true;
                    valueAnimator.pause();
                    RedbagMainActivity.this.f11159bc.postDelayed(new Runnable() {
                        /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.C303238.RunnableC30331 */

                        @Override // java.lang.Runnable
                        public void run() {
                            if (Build.VERSION.SDK_INT >= 19) {
                                valueAnimator.resume();
                            }
                        }
                    }, StatsManager.DEFAULT_TIMEOUT_MILLIS);
                }
                RedbagMainActivity.this.f11128aY.setTranslationX((float) intValue);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.C303439 */

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                RedbagMainActivity.this.f11158bb = false;
            }
        });
        valueAnimator.start();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.more /*{ENCODED_INT: 2131755503}*/:
                m12491k();
                return;
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.prizepool /*{ENCODED_INT: 2131755865}*/:
                this.f11179z.execute(new Runnable() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC30203 */

                    @Override // java.lang.Runnable
                    public void run() {
                        RedbagMainActivity.this.m12451c((RedbagMainActivity) RedbagMainActivity.this.f11168o);
                        RedbagMainActivity.this.f11135af = true;
                    }
                });
                return;
            case C2425R.C2427id.balanceimage /*{ENCODED_INT: 2131755866}*/:
                if (!TextUtils.isEmpty(this.f11136ag)) {
                    GBActivity.openBackWebView(this.f11136ag, "在线购买旺豆");
                    this.f11144ao = true;
                    return;
                }
                return;
            case C2425R.C2427id.ll_score /*{ENCODED_INT: 2131755898}*/:
                if (this.f11130aa != null && this.f11130aa.isShowing()) {
                    this.f11130aa.dismiss();
                }
                this.f11179z.execute(new Runnable() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC303640 */

                    @Override // java.lang.Runnable
                    public void run() {
                        RedbagMainActivity.this.m12457d((RedbagMainActivity) RedbagMainActivity.this.f11168o);
                    }
                });
                return;
            case C2425R.C2427id.ll_ranking /*{ENCODED_INT: 2131755899}*/:
                if (this.f11130aa != null && this.f11130aa.isShowing()) {
                    this.f11130aa.dismiss();
                }
                this.f11179z.execute(new Runnable() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC30072 */

                    @Override // java.lang.Runnable
                    public void run() {
                        RedbagMainActivity.this.m12464e((RedbagMainActivity) RedbagMainActivity.this.f11168o);
                    }
                });
                return;
            case C2425R.C2427id.ll_rule /*{ENCODED_INT: 2131755900}*/:
                if (this.f11130aa != null && this.f11130aa.isShowing()) {
                    this.f11130aa.dismiss();
                }
                m12483i();
                return;
            case C2425R.C2427id.ll_welfare /*{ENCODED_INT: 2131755901}*/:
                this.f11179z.execute(new Runnable() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC30354 */

                    @Override // java.lang.Runnable
                    public void run() {
                        RedbagMainActivity.this.m12441b((RedbagMainActivity) RedbagMainActivity.this.f11168o, 0, 20);
                    }
                });
                return;
            default:
                return;
        }
    }

    @Override // com.gbcom.gwifi.p221a.p222a.MsgDispatcherListener
    public void messageReceived(ChannelHandlerContext channelHandlerContext, final Object obj) {
        runOnUiThread(new Runnable() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC30386 */

            @Override // java.lang.Runnable
            public void run() {
                String obj = obj.toString();
                if (RedbagMainActivity.this.f11169p == null) {
                    RedbagMainActivity.this.f11169p = GsonUtil.m14241a();
                }
                if (RedbagMainActivity.this.f11122aS != 0 && System.currentTimeMillis() - RedbagMainActivity.this.f11122aS > ((long) (RedbagMainActivity.this.f11123aT * 3 * 1000)) && RedbagMainActivity.this.f11125aV) {
                    Log.d("aaaaaa", "服务器异常");
                }
                try {
                    RedbagMainActivity.this.m12424a((RedbagMainActivity) Math.abs(Integer.parseInt(new JSONObject(obj).getJSONObject("responseHeader").getString("command"))), (int) obj);
                } catch (Exception e) {
                    try {
                        RedbagMainActivity.this.m12424a((RedbagMainActivity) Math.abs(Integer.parseInt(new JSONObject(obj).getJSONObject("notifyHeader").getString("command"))), (int) obj);
                    } catch (Exception e2) {
                        try {
                            if (new JSONObject(obj).getInt("command") == 5) {
                                RedbagMainActivity.this.f11122aS = System.currentTimeMillis();
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12424a(int i, String str) {
        switch (i) {
            case 1000:
                m12492k(str);
                return;
            case 1001:
                m12504q(str);
                return;
            case 1011:
                this.ctx.pipeline().remove("IdleStateHandler");
                this.f11125aV = false;
                return;
            case 1012:
                this.f11123aT = ((HeartBeatOnNotify) this.f11169p.mo21588a(str, HeartBeatOnNotify.class)).getNotify().getHeartBeatPeriod();
                if (this.ctx.pipeline().get("IdleStateHandler") == null) {
                    this.ctx.pipeline().addFirst("IdleStateHandler", new IdleStateHandler(0, this.f11123aT, 0));
                }
                this.f11125aV = true;
                return;
            case 4001:
                m12510t(str);
                return;
            case 4002:
                m12507s(str);
                return;
            case 4003:
                m12496m(str);
                return;
            case 4004:
                m12500o(str);
                return;
            case 4005:
                m12493l(str);
                return;
            case 4011:
                m12506r(str);
                return;
            case 4012:
                m12498n(str);
                return;
            case 4200:
                m12502p(str);
                return;
            case 4300:
                m12489j(str);
                return;
            case 4401:
                m12477g(str);
                return;
            case 4411:
                m12463e();
                return;
            case 4451:
                m12485i(str);
                return;
            case 4452:
                m12472f(str);
                return;
            case 4453:
                m12481h(str);
                return;
            case 4454:
                m12460d(str);
                return;
            case 4461:
                m12456d();
                return;
            case 4462:
                m12465e(str);
                return;
            case 4500:
                m12453c(str);
                return;
            case 4556:
                m12446b(str);
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    private void m12446b(String str) {
        int size;
        int size2;
        int i = 0;
        RedbagMessageNotify.NotifyBean.RedbagContentBean redbagContent = ((RedbagMessageNotify) this.f11169p.mo21588a(str, RedbagMessageNotify.class)).getNotify().getRedbagContent();
        if (redbagContent != null) {
            this.f11161be = redbagContent.getContent();
            String str2 = this.f11161be;
            if (!TextUtils.isEmpty(str2)) {
                if (str2.length() >= 25) {
                    str2 = str2.substring(0, 25);
                }
                m12432a(str2);
                String str3 = this.f11161be + " ";
                String str4 = this.f11161be + " ";
                int i2 = 0;
                while (true) {
                    if (i2 >= (this.f11103a.size() >= 1 ? 1 : this.f11103a.size())) {
                        break;
                    }
                    String str5 = str3 + "恭喜" + this.f11103a.get(i2).getNickName() + "抽中幸运数字 获得" + this.f11103a.get(i2).getLuckyNumberBonus() + "旺豆 ";
                    i2++;
                    str3 = str5;
                }
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str3);
                String str6 = str4;
                while (true) {
                    if (this.f11103a.size() >= 1) {
                        size2 = 1;
                    } else {
                        size2 = this.f11103a.size();
                    }
                    if (i < size2) {
                        RedbagMessageResponse.ResponseBean.PrizePoolBonusesBean prizePoolBonusesBean = this.f11103a.get(i);
                        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                        ForegroundColorSpan foregroundColorSpan3 = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                        spannableStringBuilder.setSpan(foregroundColorSpan, str6.length() + 2, prizePoolBonusesBean.getNickName().length() + 2 + str6.length(), 34);
                        spannableStringBuilder.setSpan(foregroundColorSpan2, prizePoolBonusesBean.getNickName().length() + 8 + str6.length(), prizePoolBonusesBean.getNickName().length() + 8 + str6.length(), 34);
                        spannableStringBuilder.setSpan(foregroundColorSpan3, prizePoolBonusesBean.getNickName().length() + 11 + str6.length(), prizePoolBonusesBean.getNickName().length() + 13 + (prizePoolBonusesBean.getLuckyNumberBonus() + "").length() + str6.length(), 34);
                        i++;
                        str6 = str6 + "恭喜" + prizePoolBonusesBean.getNickName() + "抽中幸运数字 获得" + prizePoolBonusesBean.getLuckyNumberBonus() + "旺豆 ";
                    } else {
                        this.f11160bd.setText(spannableStringBuilder);
                        return;
                    }
                }
            } else {
                String str7 = "";
                int i3 = 0;
                while (true) {
                    if (i3 >= (this.f11103a.size() >= 2 ? 2 : this.f11103a.size())) {
                        break;
                    }
                    String str8 = str7 + "恭喜" + this.f11103a.get(i3).getNickName() + "抽中幸运数字 获得" + this.f11103a.get(i3).getLuckyNumberBonus() + "旺豆 ";
                    i3++;
                    str7 = str8;
                }
                SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(str7);
                String str9 = "";
                while (true) {
                    if (this.f11103a.size() >= 2) {
                        size = 2;
                    } else {
                        size = this.f11103a.size();
                    }
                    if (i < size) {
                        RedbagMessageResponse.ResponseBean.PrizePoolBonusesBean prizePoolBonusesBean2 = this.f11103a.get(i);
                        ForegroundColorSpan foregroundColorSpan4 = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                        ForegroundColorSpan foregroundColorSpan5 = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                        ForegroundColorSpan foregroundColorSpan6 = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                        spannableStringBuilder2.setSpan(foregroundColorSpan4, str9.length() + 2, prizePoolBonusesBean2.getNickName().length() + 2 + str9.length(), 34);
                        spannableStringBuilder2.setSpan(foregroundColorSpan5, prizePoolBonusesBean2.getNickName().length() + 8 + str9.length(), prizePoolBonusesBean2.getNickName().length() + 8 + str9.length(), 34);
                        spannableStringBuilder2.setSpan(foregroundColorSpan6, prizePoolBonusesBean2.getNickName().length() + 11 + str9.length(), prizePoolBonusesBean2.getNickName().length() + 13 + (prizePoolBonusesBean2.getLuckyNumberBonus() + "").length() + str9.length(), 34);
                        str9 = str9 + "恭喜" + prizePoolBonusesBean2.getNickName() + "抽中幸运数字 获得" + prizePoolBonusesBean2.getLuckyNumberBonus() + "旺豆 ";
                        i++;
                    } else {
                        this.f11160bd.setText(spannableStringBuilder2);
                        return;
                    }
                }
            }
        }
    }

    /* renamed from: c */
    private void m12453c(String str) {
        int size;
        int size2;
        int i = 0;
        RedbagMessageResponse redbagMessageResponse = (RedbagMessageResponse) this.f11169p.mo21588a(str, RedbagMessageResponse.class);
        if (redbagMessageResponse.getResponseHeader().getStatus() == 0) {
            RedbagMessageResponse.ResponseBean.RedbagContentBean redbagContent = redbagMessageResponse.getResponse().getRedbagContent();
            if (redbagContent == null) {
                List<RedbagMessageResponse.ResponseBean.PrizePoolBonusesBean> prizePoolBonuses = redbagMessageResponse.getResponse().getPrizePoolBonuses();
                String str2 = "";
                int i2 = 0;
                while (true) {
                    if (i2 >= (prizePoolBonuses.size() >= 2 ? 2 : prizePoolBonuses.size())) {
                        break;
                    }
                    String str3 = str2 + "恭喜" + prizePoolBonuses.get(i2).getNickName() + "抽中幸运数字 获得" + prizePoolBonuses.get(i2).getLuckyNumberBonus() + "旺豆 ";
                    i2++;
                    str2 = str3;
                }
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str2);
                String str4 = "";
                this.f11103a.clear();
                if (prizePoolBonuses.size() > 0) {
                    this.f11103a.add(prizePoolBonuses.get(0));
                }
                if (prizePoolBonuses.size() > 1) {
                    this.f11103a.add(prizePoolBonuses.get(1));
                }
                while (true) {
                    if (prizePoolBonuses.size() >= 2) {
                        size2 = 2;
                    } else {
                        size2 = prizePoolBonuses.size();
                    }
                    if (i < size2) {
                        RedbagMessageResponse.ResponseBean.PrizePoolBonusesBean prizePoolBonusesBean = prizePoolBonuses.get(i);
                        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                        ForegroundColorSpan foregroundColorSpan3 = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                        spannableStringBuilder.setSpan(foregroundColorSpan, str4.length() + 2, prizePoolBonusesBean.getNickName().length() + 2 + str4.length(), 34);
                        spannableStringBuilder.setSpan(foregroundColorSpan2, prizePoolBonusesBean.getNickName().length() + 8 + str4.length(), prizePoolBonusesBean.getNickName().length() + 8 + str4.length(), 34);
                        spannableStringBuilder.setSpan(foregroundColorSpan3, prizePoolBonusesBean.getNickName().length() + 11 + str4.length(), prizePoolBonusesBean.getNickName().length() + 13 + (prizePoolBonusesBean.getLuckyNumberBonus() + "").length() + str4.length(), 34);
                        str4 = str4 + "恭喜" + prizePoolBonusesBean.getNickName() + "抽中幸运数字 获得" + prizePoolBonusesBean.getLuckyNumberBonus() + "旺豆 ";
                        i++;
                    } else {
                        this.f11160bd.setText(spannableStringBuilder);
                        return;
                    }
                }
            } else {
                this.f11161be = redbagContent.getContent();
                String str5 = this.f11161be;
                String str6 = this.f11161be + " ";
                String str7 = this.f11161be + " ";
                if (!TextUtils.isEmpty(str5)) {
                    if (str5.length() >= 25) {
                        str5 = str5.substring(0, 25);
                    }
                    m12432a(str5);
                }
                List<RedbagMessageResponse.ResponseBean.PrizePoolBonusesBean> prizePoolBonuses2 = redbagMessageResponse.getResponse().getPrizePoolBonuses();
                int i3 = 0;
                while (true) {
                    if (i3 >= (prizePoolBonuses2.size() >= 1 ? 1 : prizePoolBonuses2.size())) {
                        break;
                    }
                    str6 = str6 + "恭喜" + prizePoolBonuses2.get(i3).getNickName() + "抽中幸运数字 获得" + prizePoolBonuses2.get(i3).getLuckyNumberBonus() + "旺豆 ";
                    i3++;
                }
                SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(str6);
                this.f11103a.clear();
                if (prizePoolBonuses2.size() > 0) {
                    this.f11103a.add(prizePoolBonuses2.get(0));
                }
                if (prizePoolBonuses2.size() > 1) {
                    this.f11103a.add(prizePoolBonuses2.get(1));
                }
                String str8 = str7;
                while (true) {
                    if (prizePoolBonuses2.size() >= 1) {
                        size = 1;
                    } else {
                        size = prizePoolBonuses2.size();
                    }
                    if (i < size) {
                        RedbagMessageResponse.ResponseBean.PrizePoolBonusesBean prizePoolBonusesBean2 = prizePoolBonuses2.get(i);
                        ForegroundColorSpan foregroundColorSpan4 = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                        ForegroundColorSpan foregroundColorSpan5 = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                        ForegroundColorSpan foregroundColorSpan6 = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                        spannableStringBuilder2.setSpan(foregroundColorSpan4, str8.length() + 2, prizePoolBonusesBean2.getNickName().length() + 2 + str8.length(), 34);
                        spannableStringBuilder2.setSpan(foregroundColorSpan5, prizePoolBonusesBean2.getNickName().length() + 8 + str8.length(), prizePoolBonusesBean2.getNickName().length() + 8 + str8.length(), 34);
                        spannableStringBuilder2.setSpan(foregroundColorSpan6, prizePoolBonusesBean2.getNickName().length() + 11 + str8.length(), prizePoolBonusesBean2.getNickName().length() + 13 + (prizePoolBonusesBean2.getLuckyNumberBonus() + "").length() + str8.length(), 34);
                        i++;
                        str8 = str8 + "恭喜" + prizePoolBonusesBean2.getNickName() + "抽中幸运数字 获得" + prizePoolBonusesBean2.getLuckyNumberBonus() + "旺豆 ";
                    } else {
                        this.f11160bd.setText(spannableStringBuilder2);
                        return;
                    }
                }
            }
        }
    }

    /* renamed from: d */
    private void m12456d() {
        this.f11179z.execute(new Runnable() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC30397 */

            @Override // java.lang.Runnable
            public void run() {
                RedbagMainActivity.this.m12470f((RedbagMainActivity) RedbagMainActivity.this.f11168o);
            }
        });
    }

    /* renamed from: d */
    private void m12460d(String str) {
        LuckyRedbagJoinedResponse luckyRedbagJoinedResponse = (LuckyRedbagJoinedResponse) this.f11169p.mo21588a(str, LuckyRedbagJoinedResponse.class);
        if (luckyRedbagJoinedResponse.getResponseHeader().getStatus() == 0) {
            Intent intent = new Intent(this, RedbagWelfareScoreActivity.class);
            intent.putExtra("luckyRedbagJoinedResponse", luckyRedbagJoinedResponse);
            startActivity(intent);
        }
    }

    /* renamed from: e */
    private void m12465e(String str) {
        LuckyRedbagBettingNotify luckyRedbagBettingNotify = (LuckyRedbagBettingNotify) this.f11169p.mo21588a(str, LuckyRedbagBettingNotify.class);
        final int luckyRedbagId = luckyRedbagBettingNotify.getNotify().getLuckyRedbagId();
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (luckyRedbagBettingNotify.getNotify().getHasSale() != luckyRedbagBettingNotify.getNotify().getCount()) {
            if (this.f11110aG == luckyRedbagId && (currentActivity instanceof RedbagWelfareActivity)) {
                Intent intent = new Intent();
                intent.setAction(f11076k);
                intent.putExtra("luckyRedbagBettingNotify", luckyRedbagBettingNotify);
                sendBroadcast(intent);
            }
        } else if (this.f11110aG == luckyRedbagId && (currentActivity instanceof RedbagWelfareActivity)) {
            this.f11126aW = true;
            this.f11179z.execute(new Runnable() {
                /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC30408 */

                @Override // java.lang.Runnable
                public void run() {
                    RedbagMainActivity.this.m12421a((RedbagMainActivity) RedbagMainActivity.this.f11168o, luckyRedbagId);
                }
            });
        }
        if (luckyRedbagBettingNotify.getNotify().getHasSale() == luckyRedbagBettingNotify.getNotify().getCount()) {
            View view = this.f11119aP.get(Integer.valueOf(luckyRedbagId));
            view.findViewById(C2425R.C2427id.rl_out).setVisibility(0);
            view.findViewById(C2425R.C2427id.rl_already_gain).setVisibility(8);
            view.findViewById(C2425R.C2427id.rl_welfare).setVisibility(8);
            view.setTag(true);
            view.clearAnimation();
        }
    }

    /* renamed from: f */
    private void m12472f(String str) {
        LuckyRedbagBettingResponse luckyRedbagBettingResponse = (LuckyRedbagBettingResponse) this.f11169p.mo21588a(str, LuckyRedbagBettingResponse.class);
        if (luckyRedbagBettingResponse.getResponseHeader().getStatus() == 0) {
            this.f11090N.mo26737a(luckyRedbagBettingResponse.getResponse().getBalance() + "");
            this.f11110aG = luckyRedbagBettingResponse.getResponse().getLuckyRedbagId();
            View view = this.f11119aP.get(Integer.valueOf(this.f11110aG));
            view.findViewById(C2425R.C2427id.rl_out).setVisibility(8);
            view.findViewById(C2425R.C2427id.rl_already_gain).setVisibility(0);
            view.findViewById(C2425R.C2427id.rl_welfare).setVisibility(8);
            view.setTag(true);
            view.clearAnimation();
            this.f11121aR = m12416a(luckyRedbagBettingResponse);
            Intent intent = new Intent();
            intent.setClass(this, RedbagWelfareActivity.class);
            intent.putExtra("mUid", this.f11168o);
            intent.putExtra("luckyRedbagDetailResponse", this.f11121aR);
            startActivity(intent);
            return;
        }
        C3470v.m14563a(this, luckyRedbagBettingResponse.getResponseHeader().getErrorMessage());
    }

    /* renamed from: g */
    private void m12477g(String str) {
        NewComerRedbagBettingResponse newComerRedbagBettingResponse = (NewComerRedbagBettingResponse) this.f11169p.mo21588a(str, NewComerRedbagBettingResponse.class);
        if (newComerRedbagBettingResponse.getResponseHeader().getStatus() == 0) {
            m12431a(newComerRedbagBettingResponse);
        }
    }

    /* renamed from: a */
    private void m12431a(NewComerRedbagBettingResponse newComerRedbagBettingResponse) {
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.redbag_newcommer_betting, (ViewGroup) null);
        ((TextView) inflate.findViewById(C2425R.C2427id.tv_balance)).setText(newComerRedbagBettingResponse.getResponse().getHitBeans() + Constant.f13309cr);
        this.f11090N.mo26737a(newComerRedbagBettingResponse.getResponse().getBalance() + "");
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.iv_close);
        if (this.f11113aJ == null) {
            this.f11113aJ = new PopupWindow(inflate, -1, -1, true);
        }
        this.f11113aJ.setBackgroundDrawable(new ColorDrawable());
        this.f11113aJ.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.View$OnClickListenerC30419 */

            public void onClick(View view) {
                if (RedbagMainActivity.this.f11113aJ != null) {
                    RedbagMainActivity.this.f11113aJ.dismiss();
                }
            }
        });
    }

    /* renamed from: e */
    private void m12463e() {
        m12469f();
    }

    /* renamed from: f */
    private void m12469f() {
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.redbag_newcommer, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.iv_gain);
        ImageView imageView2 = (ImageView) inflate.findViewById(C2425R.C2427id.iv_close);
        if (this.f11112aI == null) {
            this.f11112aI = new PopupWindow(inflate, -1, -1, true);
        }
        this.f11112aI.setBackgroundDrawable(new ColorDrawable());
        this.f11112aI.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.View$OnClickListenerC299510 */

            public void onClick(View view) {
                if (RedbagMainActivity.this.f11112aI != null) {
                    RedbagMainActivity.this.f11112aI.dismiss();
                }
                RedbagMainActivity.this.f11179z.execute(new Runnable() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.View$OnClickListenerC299510.RunnableC29961 */

                    @Override // java.lang.Runnable
                    public void run() {
                        RedbagMainActivity.this.m12475g((RedbagMainActivity) RedbagMainActivity.this.f11168o);
                    }
                });
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.View$OnClickListenerC299711 */

            public void onClick(View view) {
                if (RedbagMainActivity.this.f11112aI != null) {
                    RedbagMainActivity.this.f11112aI.dismiss();
                }
            }
        });
    }

    /* renamed from: h */
    private void m12481h(String str) {
        this.f11121aR = (LuckyRedbagDetailResponse) this.f11169p.mo21588a(str, LuckyRedbagDetailResponse.class);
        if (this.f11121aR.getResponseHeader().getStatus() != 0) {
            return;
        }
        if (this.f11126aW) {
            this.f11126aW = false;
            Intent intent = new Intent();
            intent.setAction("updateResult");
            intent.putExtra("luckyRedbagDetailResponse", this.f11121aR);
            sendBroadcast(intent);
        } else if (this.f11124aU) {
            this.f11124aU = false;
            Intent intent2 = new Intent();
            intent2.setAction("updateLucky");
            intent2.putExtra("updateLucky", true);
            intent2.putExtra("luckyRedbagDetailResponse", this.f11121aR);
            sendBroadcast(intent2);
        } else if (this.f11145ap) {
            this.f11145ap = false;
            Intent intent3 = new Intent();
            intent3.setClass(this, RedbagWelfareActivity.class);
            intent3.putExtra("mUid", this.f11168o);
            intent3.putExtra("luckyRedbagDetailResponse", this.f11121aR);
            this.f11110aG = this.f11121aR.getResponse().getLuckyRedbagId();
            startActivity(intent3);
        } else {
            int status = this.f11121aR.getResponse().getStatus();
            if (this.f11116aM) {
                this.f11116aM = false;
                Intent intent4 = new Intent();
                intent4.setClass(this, RedbagWelfareActivity.class);
                intent4.putExtra("mUid", this.f11168o);
                intent4.putExtra("luckyRedbagDetailResponse", this.f11121aR);
                this.f11110aG = this.f11121aR.getResponse().getLuckyRedbagId();
                startActivity(intent4);
                this.f11179z.execute(new Runnable() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC299913 */

                    @Override // java.lang.Runnable
                    public void run() {
                        RedbagMainActivity.this.m12470f((RedbagMainActivity) RedbagMainActivity.this.f11168o);
                    }
                });
                return;
            }
            if (status == 0) {
                Intent intent5 = new Intent();
                intent5.setClass(this, RedbagWelfareActivity.class);
                intent5.putExtra("mUid", this.f11168o);
                intent5.putExtra("luckyRedbagDetailResponse", this.f11121aR);
                this.f11110aG = this.f11121aR.getResponse().getLuckyRedbagId();
                startActivity(intent5);
            }
            if (status == 1) {
                Intent intent6 = new Intent();
                intent6.setClass(this, RedbagWelfareActivity.class);
                intent6.putExtra("mUid", this.f11168o);
                intent6.putExtra("luckyRedbagDetailResponse", this.f11121aR);
                this.f11110aG = this.f11121aR.getResponse().getLuckyRedbagId();
                startActivity(intent6);
            }
        }
    }

    /* renamed from: a */
    private void m12430a(final LuckyRedbagDetailResponse luckyRedbagDetailResponse) {
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.redbag_popup_welfare_betting, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
        inflate.findViewById(C2425R.C2427id.iv_close).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.View$OnClickListenerC300014 */

            public void onClick(View view) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
        ((ImageView) inflate.findViewById(C2425R.C2427id.iv_gain)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.View$OnClickListenerC300115 */

            public void onClick(View view) {
                RedbagMainActivity.this.f11179z.execute(new Runnable() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.View$OnClickListenerC300115.RunnableC30021 */

                    @Override // java.lang.Runnable
                    public void run() {
                        RedbagMainActivity.this.m12440b((RedbagMainActivity) RedbagMainActivity.this.f11168o, luckyRedbagDetailResponse.getResponse().getLuckyRedbagId());
                    }
                });
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    /* renamed from: b */
    private void m12445b(final LuckyRedbagDetailResponse luckyRedbagDetailResponse) {
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.redbag_popup_welfare, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
        ((TextView) inflate.findViewById(2131755381)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.View$OnClickListenerC300316 */

            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(RedbagMainActivity.this, RedbagWelfareActivity.class);
                intent.putExtra("mUid", RedbagMainActivity.this.f11168o);
                intent.putExtra("luckyRedbagDetailResponse", luckyRedbagDetailResponse);
                intent.putExtra("mUid", RedbagMainActivity.this.f11168o);
                RedbagMainActivity.this.f11110aG = luckyRedbagDetailResponse.getResponse().getLuckyRedbagId();
                RedbagMainActivity.this.startActivity(intent);
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
        inflate.findViewById(C2425R.C2427id.iv_close).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.View$OnClickListenerC300417 */

            public void onClick(View view) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    /* renamed from: i */
    private void m12485i(String str) {
        LuckyRedbagIntoResponse luckyRedbagIntoResponse = (LuckyRedbagIntoResponse) this.f11169p.mo21588a(str, LuckyRedbagIntoResponse.class);
        if (luckyRedbagIntoResponse.getResponseHeader().getStatus() == 0) {
            ArrayList<LuckyRedbagSimple> luckyList = luckyRedbagIntoResponse.getResponse().getLuckyList();
            this.f11120aQ.clear();
            if (this.f11106aC != null) {
                this.f11106aC.clearAnimation();
                this.f11105aB.removeView(this.f11106aC);
                this.f11106aC = null;
            }
            if (this.f11107aD != null) {
                this.f11107aD.clearAnimation();
                this.f11105aB.removeView(this.f11107aD);
                this.f11107aD = null;
            }
            if (this.f11108aE != null) {
                this.f11108aE.clearAnimation();
                this.f11105aB.removeView(this.f11108aE);
                this.f11108aE = null;
            }
            if (this.f11109aF != null) {
                this.f11109aF.clearAnimation();
                this.f11105aB.removeView(this.f11109aF);
                this.f11109aF = null;
            }
            for (int i = 0; i < luckyList.size(); i++) {
                LuckyRedbagSimple luckyRedbagSimple = luckyList.get(i);
                if (luckyRedbagSimple.getStatus() == 0 && luckyRedbagSimple.getIsJoin() == 0) {
                    this.f11120aQ.add(luckyList.get(i));
                }
            }
            if (this.f11120aQ.size() >= 1) {
                LuckyRedbagSimple luckyRedbagSimple2 = this.f11120aQ.get(0);
                if (this.f11106aC == null) {
                    this.f11106aC = new RedbagWelfareView(this);
                    this.f11105aB.addView(this.f11106aC);
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins((int) (((double) this.f11154ay) * 0.04d), (int) (((double) this.f11154ay) * 0.2d), 0, 0);
                    this.f11106aC.setLayoutParams(layoutParams);
                    this.f11106aC.mo26750a(this.f11120aQ.get(0));
                }
                this.f11119aP.put(Integer.valueOf(luckyRedbagSimple2.getLuckyRedbagId()), this.f11106aC);
            }
            if (this.f11120aQ.size() >= 2) {
                LuckyRedbagSimple luckyRedbagSimple3 = this.f11120aQ.get(1);
                if (this.f11107aD == null) {
                    this.f11107aD = new RedbagWelfareView(this);
                    this.f11105aB.addView(this.f11107aD);
                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams2.setMargins(0, (int) (((double) this.f11154ay) * 0.2d), (int) (((double) this.f11154ay) * 0.04d), 0);
                    layoutParams2.addRule(11);
                    this.f11107aD.setLayoutParams(layoutParams2);
                    this.f11107aD.mo26750a(this.f11120aQ.get(1));
                }
                this.f11119aP.put(Integer.valueOf(luckyRedbagSimple3.getLuckyRedbagId()), this.f11107aD);
            }
            if (this.f11120aQ.size() >= 3) {
                LuckyRedbagSimple luckyRedbagSimple4 = this.f11120aQ.get(2);
                if (this.f11108aE == null) {
                    this.f11108aE = new RedbagWelfareView(this);
                    this.f11105aB.addView(this.f11108aE);
                    RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams3.addRule(12);
                    layoutParams3.addRule(9);
                    layoutParams3.setMargins((int) (((double) this.f11154ay) * 0.04d), 0, 0, (int) (((double) this.f11154ay) * 0.2d));
                    this.f11108aE.setLayoutParams(layoutParams3);
                    this.f11108aE.mo26750a(this.f11120aQ.get(2));
                }
                this.f11119aP.put(Integer.valueOf(luckyRedbagSimple4.getLuckyRedbagId()), this.f11108aE);
            }
            if (this.f11120aQ.size() >= 4) {
                LuckyRedbagSimple luckyRedbagSimple5 = this.f11120aQ.get(3);
                if (this.f11109aF == null) {
                    this.f11109aF = new RedbagWelfareView(this);
                    this.f11105aB.addView(this.f11109aF);
                    RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams4.addRule(12);
                    layoutParams4.setMargins(0, 0, (int) (((double) this.f11154ay) * 0.04d), (int) (((double) this.f11154ay) * 0.2d));
                    layoutParams4.addRule(11);
                    this.f11109aF.setLayoutParams(layoutParams4);
                    this.f11109aF.mo26750a(this.f11120aQ.get(3));
                }
                this.f11119aP.put(Integer.valueOf(luckyRedbagSimple5.getLuckyRedbagId()), this.f11109aF);
            }
        }
    }

    /* renamed from: j */
    private void m12489j(String str) {
        RankingResponse rankingResponse = (RankingResponse) this.f11169p.mo21588a(str, RankingResponse.class);
        if (rankingResponse.getResponseHeader().getStatus() == 0) {
            m12433a(rankingResponse.getResponse().getList());
        }
    }

    /* renamed from: k */
    private void m12492k(String str) {
        ConfigResponse configResponse = (ConfigResponse) this.f11169p.mo21588a(str, ConfigResponse.class);
        if (configResponse.getResponseHeader().getStatus() == 0 && configResponse != null) {
            Config config = configResponse.getResponse().getConfig();
            if (config != null) {
                this.f11137ah = config.getRuleDetail();
            }
            this.f11114aK = config.getRedbagHitAction();
            this.f11123aT = config.getHeartbeatPeriod();
            if (config.getHeartbeatSwitch() == 1) {
                this.f11125aV = true;
                if (this.ctx.pipeline().get("IdleStateHandler") == null) {
                    this.ctx.pipeline().addFirst("IdleStateHandler", new IdleStateHandler(0, this.f11123aT, 0));
                }
            }
        }
    }

    /* renamed from: l */
    private void m12493l(String str) {
        RedbagJoinedResponse redbagJoinedResponse = (RedbagJoinedResponse) this.f11169p.mo21588a(str, RedbagJoinedResponse.class);
        if (redbagJoinedResponse.getResponseHeader().getStatus() == 0) {
            Intent intent = new Intent(this, RedbagScoreActivity.class);
            intent.putExtra("redbagJoinedResponse", redbagJoinedResponse);
            startActivity(intent);
        }
    }

    /* renamed from: m */
    private void m12496m(String str) {
        RedbagBettingResponse redbagBettingResponse = (RedbagBettingResponse) this.f11169p.mo21588a(str, RedbagBettingResponse.class);
        int status = redbagBettingResponse.getResponseHeader().getStatus();
        if (status == 0) {
            this.f11090N.mo26737a(redbagBettingResponse.getResponse().getBalance() + "");
            this.f11143an = redbagBettingResponse.getResponse().getRoundNo();
            if (this.f11114aK == 2) {
                Intent intent = new Intent(this, RedbagRoomActivity.class);
                intent.putExtra("uid", this.f11168o);
                intent.putExtra("resId", this.f11175v);
                Log.d("wwwwww", "resId:------>" + this.f11175v);
                intent.putExtra("redRound", this.f11143an);
                intent.putExtra("redName", this.f11080D);
                intent.putExtra("totalBeans", this.f11147ar);
                intent.putExtra("totalCount", this.f11118aO);
                startActivity(intent);
                this.f11081E = true;
            }
        } else if (status == 7) {
            redbagBettingResponse.getResponseHeader().getErrorMessage();
            C3470v.m14563a(this, "旺豆不足,正在前往赚旺豆页面");
            this.f11144ao = true;
            startActivity(new Intent(this, ScoreActivity.class));
        } else if (status == 5) {
            C3470v.m14563a(this, redbagBettingResponse.getResponseHeader().getErrorMessage());
        } else if (status == 6) {
            C3470v.m14563a(this, "您已参加本次红包,请等待开局");
            Intent intent2 = new Intent(this, RedbagRoomActivity.class);
            intent2.putExtra("uid", this.f11168o);
            intent2.putExtra("resId", this.f11175v);
            intent2.putExtra("redRound", this.f11176w);
            intent2.putExtra("redName", this.f11080D);
            intent2.putExtra("totalBeans", this.f11147ar);
            intent2.putExtra("totalCount", this.f11118aO);
            Log.d("wwwwww", "resId:------>" + this.f11175v);
            startActivity(intent2);
            this.f11081E = true;
        } else if (status == 2) {
            C3470v.m14563a(this, redbagBettingResponse.getResponseHeader().getErrorMessage());
        } else if (status == 5) {
            C3470v.m14563a(this, redbagBettingResponse.getResponseHeader().getErrorMessage());
        } else if (status == 1) {
            C3470v.m14563a(this, redbagBettingResponse.getResponseHeader().getErrorMessage());
        } else if (status == 3) {
            C3470v.m14563a(this, redbagBettingResponse.getResponseHeader().getErrorMessage());
        } else if (status == 4) {
            C3470v.m14563a(this, redbagBettingResponse.getResponseHeader().getErrorMessage());
        }
    }

    /* renamed from: n */
    private void m12498n(String str) {
        int size;
        RedbagNotifyResult redbagNotifyResult = (RedbagNotifyResult) this.f11169p.mo21588a(str, RedbagNotifyResult.class);
        int redId = redbagNotifyResult.getNotify().getRedId();
        if (redId == this.f11175v && TextUtils.equals(this.f11143an, redbagNotifyResult.getNotify().getRoundNo())) {
            Intent intent = new Intent();
            intent.setAction(f11074i);
            intent.putExtra("notifyResult", redbagNotifyResult);
            sendBroadcast(intent);
        }
        if (redbagNotifyResult != null) {
            this.f11177x.mo26747a(redbagNotifyResult.getNotify().getPrizePoolBeans() + "");
        }
        List<HitRecord> hitRecord = redbagNotifyResult.getNotify().getHitRecord();
        for (int i = 0; i < hitRecord.size(); i++) {
            HitRecord hitRecord2 = hitRecord.get(i);
            int luckyNumberBonus = hitRecord2.getLuckyNumberBonus();
            if (luckyNumberBonus != 0) {
                RedbagMessageResponse.ResponseBean.PrizePoolBonusesBean prizePoolBonusesBean = new RedbagMessageResponse.ResponseBean.PrizePoolBonusesBean();
                prizePoolBonusesBean.setNickName(hitRecord2.getNickName());
                prizePoolBonusesBean.setHitBeans(hitRecord2.getHitBeans());
                prizePoolBonusesBean.setLuckyNumberBonus(hitRecord2.getLuckyNumberBonus());
                this.f11103a.add(0, prizePoolBonusesBean);
                this.f11163bg = true;
                if (!this.f11162bf) {
                    m12434a(hitRecord, hitRecord2, i);
                    this.f11162bf = true;
                }
            }
            if (hitRecord2.getAccountId() == this.f11168o) {
                if (luckyNumberBonus != 0) {
                    m12429a(hitRecord.get(i), redId);
                }
                try {
                    this.f11090N.mo26737a((hitRecord2.getLuckyNumberBonus() + Integer.parseInt(this.f11090N.mo26736a().toString().trim()) + hitRecord2.getHitBeans()) + "");
                } catch (Exception e) {
                }
            }
        }
        if (this.f11163bg) {
            this.f11163bg = false;
            String str2 = "";
            int i2 = 0;
            while (true) {
                if (i2 >= (this.f11103a.size() >= 2 ? 2 : this.f11103a.size())) {
                    break;
                }
                String str3 = str2 + "恭喜" + this.f11103a.get(i2).getNickName() + "抽中幸运数字 获得" + this.f11103a.get(i2).getLuckyNumberBonus() + "旺豆 ";
                i2++;
                str2 = str3;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str2);
            int i3 = 0;
            String str4 = "";
            while (true) {
                if (this.f11103a.size() >= 2) {
                    size = 2;
                } else {
                    size = this.f11103a.size();
                }
                if (i3 >= size) {
                    break;
                }
                RedbagMessageResponse.ResponseBean.PrizePoolBonusesBean prizePoolBonusesBean2 = this.f11103a.get(i3);
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                ForegroundColorSpan foregroundColorSpan3 = new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
                spannableStringBuilder.setSpan(foregroundColorSpan, str4.length() + 2, prizePoolBonusesBean2.getNickName().length() + 2 + str4.length(), 34);
                spannableStringBuilder.setSpan(foregroundColorSpan2, prizePoolBonusesBean2.getNickName().length() + 8 + str4.length(), prizePoolBonusesBean2.getNickName().length() + 8 + str4.length(), 34);
                spannableStringBuilder.setSpan(foregroundColorSpan3, prizePoolBonusesBean2.getNickName().length() + 11 + str4.length(), prizePoolBonusesBean2.getNickName().length() + 13 + (prizePoolBonusesBean2.getLuckyNumberBonus() + "").length() + str4.length(), 34);
                str4 = str4 + "恭喜" + prizePoolBonusesBean2.getNickName() + "抽中幸运数字 获得" + prizePoolBonusesBean2.getLuckyNumberBonus() + "旺豆 ";
                i3++;
            }
            this.f11160bd.setText(spannableStringBuilder);
        }
        if (redId == this.f11175v) {
            this.f11172s.clear();
            this.f11172s.add(redbagNotifyResult.getNotify().getHitRecord());
            this.f11172s.add(redbagNotifyResult.getNotify().getNextRoundNo());
            this.f11173t.clear();
            this.f11173t.addAll(this.f11172s);
            this.f11176w = redbagNotifyResult.getNotify().getNextRoundNo();
            this.f11174u = new RedbagListAdapter(this.f11172s, this.f11080D, this.f11118aO);
            this.f11171r.setAdapter((ListAdapter) this.f11174u);
            this.f11171r.setSelection(this.f11171r.getCount() - 1);
            this.f11174u.mo26116a((RedbagListAdapter.AbstractC3068b) this);
            this.f11174u.mo26115a((RedbagListAdapter.AbstractC3067a) this);
        }
    }

    /* renamed from: a */
    private void m12429a(HitRecord hitRecord, int i) {
        int i2;
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.redbag_popupwindow_bonus, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
        if (this.f11178y != null) {
            i2 = 0;
            for (int i3 = 0; i3 < this.f11178y.size(); i3++) {
                if (this.f11178y.get(i3).getRedId() == i) {
                    i2 = this.f11178y.get(i3).getDeductBeans();
                }
            }
        } else {
            i2 = 0;
        }
        BonusImage bonusImage = (BonusImage) inflate.findViewById(C2425R.C2427id.bonusimage);
        int hitBeans = hitRecord.getHitBeans() - i2;
        String str = hitRecord.getLuckyNumberBonus() + "";
        StringBuilder append = new StringBuilder().append("恭喜你抽取幸运旺豆数");
        if (hitBeans < 0) {
            hitBeans = hitRecord.getHitBeans();
        }
        bonusImage.mo26740a(str, append.append(hitBeans).append(",获得奖励").append(Constant.f13309cr).append(hitRecord.getLuckyNumberBonus()).toString());
        bonusImage.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.View$OnClickListenerC300518 */

            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    /* renamed from: o */
    private void m12500o(String str) {
        RedbagDetailResponse redbagDetailResponse = (RedbagDetailResponse) this.f11169p.mo21588a(str, RedbagDetailResponse.class);
        Log.d("aaaaaa", "getHitRecord.size :" + redbagDetailResponse.getResponse().getHitRecord().size());
        if (this.f11086J) {
            this.f11086J = false;
            Intent intent = new Intent(this, RedbagResultActivity.class);
            intent.putExtra("source", "fromPersonal");
            intent.putExtra("redbagDetailResponse", redbagDetailResponse);
            intent.putExtra("redName", this.f11085I);
            intent.putExtra("totalBeans", this.f11148as);
            intent.putExtra("totalCount", this.f11118aO);
            startActivity(intent);
        } else if (this.f11081E) {
            Intent intent2 = new Intent();
            intent2.setAction(f11070e);
            intent2.putExtra("redbagDetailResponse", redbagDetailResponse);
            sendBroadcast(intent2);
            this.f11081E = false;
        }
    }

    /* renamed from: p */
    private void m12502p(String str) {
        this.f11092P = (PrizePoolResponse) this.f11169p.mo21588a(str, PrizePoolResponse.class);
        if (this.f11092P.getResponse() != null) {
            this.f11091O = this.f11092P.getResponse().getTotalBeans();
            this.f11177x.mo26747a(this.f11091O + "");
            if (this.f11135af) {
                this.f11135af = false;
                m12487j();
            }
        }
    }

    /* renamed from: q */
    private void m12504q(String str) {
        AccountInfoResponse accountInfoResponse = (AccountInfoResponse) this.f11169p.mo21588a(str, AccountInfoResponse.class);
        if (accountInfoResponse != null) {
            AccountInfo info = accountInfoResponse.getResponse().getInfo();
            if (info != null) {
                this.f11136ag = info.getBalanceChargeUrl();
                this.f11090N.mo26737a(info.getBalance() + "");
            } else {
                errWebDrawable();
                this.f11099W.mo28301c();
            }
        }
        stopOfficeDrawable();
        this.f11097U.setVisibility(0);
    }

    /* renamed from: r */
    private void m12506r(String str) {
        final NotifyMsg notifyMsg = (NotifyMsg) this.f11169p.mo21588a(str, NotifyMsg.class);
        final GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        JoinHistory joinHistory = new JoinHistory();
        joinHistory.setCreateAt(notifyMsg.getNotify().getCreateAt());
        joinHistory.setNickName(notifyMsg.getNotify().getNickName());
        if (notifyMsg.getNotify().getRedId() == this.f11175v) {
            this.f11179z.execute(new Runnable() {
                /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC300619 */

                @Override // java.lang.Runnable
                public void run() {
                    if ((currentActivity instanceof RedbagRoomActivity) && TextUtils.equals(RedbagMainActivity.this.f11143an, notifyMsg.getNotify().getRoundNo())) {
                        Intent intent = new Intent();
                        intent.setAction(RedbagMainActivity.f11075j);
                        intent.putExtra("notifyMsg", notifyMsg);
                        RedbagMainActivity.this.sendBroadcast(intent);
                    }
                }
            });
            if (this.f11172s.size() < this.f11118aO + 2) {
                this.f11173t.add(joinHistory);
                this.f11172s.clear();
                this.f11172s.addAll(this.f11173t);
                this.f11174u = new RedbagListAdapter(this.f11172s, this.f11080D, this.f11118aO);
                this.f11171r.setAdapter((ListAdapter) this.f11174u);
                this.f11171r.setSelection(this.f11171r.getCount() - 1);
                this.f11174u.mo26116a((RedbagListAdapter.AbstractC3068b) this);
                this.f11174u.mo26115a((RedbagListAdapter.AbstractC3067a) this);
            }
        }
    }

    /* renamed from: s */
    private void m12507s(String str) {
        this.f11172s.clear();
        this.f11173t.clear();
        RedbagIntoResponse redbagIntoResponse = (RedbagIntoResponse) this.f11169p.mo21588a(str, RedbagIntoResponse.class);
        this.f11177x.mo26747a(redbagIntoResponse.getResponse().getPrizePoolBeans() + "");
        LastRound lastRound = redbagIntoResponse.getResponse().getLastRound();
        if (!(lastRound.getRoundNo() == null || lastRound.getJoinHistory().size() == 0)) {
            this.f11172s.add(lastRound.getHitRecord());
        }
        ThisRound thisRound = redbagIntoResponse.getResponse().getThisRound();
        this.f11176w = thisRound.getRoundNo();
        this.f11172s.add(thisRound.getRoundNo());
        for (int i = 0; i < thisRound.getJoinHistory().size(); i++) {
            this.f11172s.add(thisRound.getJoinHistory().get(i));
        }
        this.f11174u = new RedbagListAdapter(this.f11172s, this.f11178y.get(this.f11078B).getRedName(), this.f11118aO);
        this.f11171r.setAdapter((ListAdapter) this.f11174u);
        this.f11174u.mo26116a((RedbagListAdapter.AbstractC3068b) this);
        this.f11174u.mo26115a((RedbagListAdapter.AbstractC3067a) this);
        if (this.f11094R) {
            this.f11094R = false;
            this.f11171r.post(new Runnable() {
                /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC300820 */

                @Override // java.lang.Runnable
                public void run() {
                    RedbagMainActivity.this.f11171r.smoothScrollToPosition(RedbagMainActivity.this.f11171r.getCount() - 1);
                }
            });
        } else {
            this.f11171r.setSelection(this.f11171r.getCount() - 1);
        }
        this.f11173t.addAll(this.f11172s);
        stopOfficeDrawable();
    }

    /* renamed from: t */
    private void m12510t(String str) {
        this.f11098V.removeAllViews();
        this.f11170q = (RedbagListResponse) this.f11169p.mo21588a(str, RedbagListResponse.class);
        if (this.f11170q.getResponseHeader().getStatus() == 0) {
            this.f11178y.clear();
            this.f11178y = this.f11170q.getResponse().getRedList();
            for (int i = 0; i < this.f11178y.size(); i++) {
                this.f11098V.mo26758a(m12412a(i, this.f11178y));
            }
            this.f11118aO = this.f11178y.get(0).getTotalCount();
            this.f11175v = this.f11178y.get(0).getRedId();
            this.f11080D = this.f11178y.get(0).getRedName();
            this.f11147ar = this.f11178y.get(0).getDeductBeans();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("需" + this.f11178y.get(0).getDeductBeans() + Constant.f13309cr);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(-65536), 1, (this.f11178y.get(0).getDeductBeans() + "").length() + 1, 34);
            this.f11077A.setText(spannableStringBuilder);
            return;
        }
        C3470v.m14563a(this, this.f11170q.getResponseHeader().getErrorMessage());
    }

    @Override // com.gbcom.gwifi.p221a.p222a.MsgDispatcherListener
    public void channelActive(ChannelHandlerContext channelHandlerContext) {
        Log.d("aaaaaa", "-----channelActive-----");
        this.f11138ai = true;
        this.ctx = channelHandlerContext;
        channel = channelHandlerContext.channel();
        if (this.f11125aV && this.f11123aT != 0 && channelHandlerContext.pipeline().get("IdleStateHandler") == null) {
            channelHandlerContext.pipeline().addFirst("IdleStateHandler", new IdleStateHandler(0, this.f11123aT, 0));
        }
        channelHandlerContext.channel().writeAndFlush(new ConfigRequest());
        this.f11179z.execute(new Runnable() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC300921 */

            @Override // java.lang.Runnable
            public void run() {
                RedbagMainActivity.this.m12439b((RedbagMainActivity) RedbagMainActivity.this.f11168o);
                RedbagMainActivity.this.m12420a((RedbagMainActivity) RedbagMainActivity.this.f11168o);
                RedbagMainActivity.this.m12451c((RedbagMainActivity) RedbagMainActivity.this.f11168o);
                RedbagMainActivity.this.m12470f((RedbagMainActivity) RedbagMainActivity.this.f11168o);
                RedbagMainActivity.this.m12474g();
            }
        });
    }

    @Override // com.gbcom.gwifi.p221a.p222a.MsgDispatcherListener
    public void channelInactive(ChannelHandlerContext channelHandlerContext) {
        Log.d("aaaaaa", "-----channelInactive-----");
        this.f11138ai = false;
        runOnUiThread(new Runnable() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC301022 */

            @Override // java.lang.Runnable
            public void run() {
                RedbagMainActivity.this.m12479h();
                if (!RedbagMainActivity.this.f11100X) {
                    RedbagMainActivity.this.f11098V.removeAllViews();
                }
                List<GBActivity> activitiesList = GBApplication.instance().getActivitiesList();
                for (int i = 0; i < activitiesList.size(); i++) {
                    if (activitiesList.get(i) instanceof RedbagRoomActivity) {
                        activitiesList.get(i).finish();
                    }
                    if (activitiesList.get(i) instanceof RedbagResultActivity) {
                        activitiesList.get(i).finish();
                    }
                    if (activitiesList.get(i) instanceof RedbagScoreActivity) {
                        activitiesList.get(i).finish();
                    }
                }
                if (RedbagMainActivity.this.f11100X) {
                    RedbagMainActivity.this.finish();
                } else {
                    RedbagMainActivity.this.f11097U.setVisibility(8);
                    RedbagMainActivity.this.errWebDrawable();
                }
                if (RedbagMainActivity.this.f11106aC != null) {
                    RedbagMainActivity.this.f11106aC.clearAnimation();
                    RedbagMainActivity.this.f11105aB.removeView(RedbagMainActivity.this.f11106aC);
                    RedbagMainActivity.this.f11106aC = null;
                }
                if (RedbagMainActivity.this.f11107aD != null) {
                    RedbagMainActivity.this.f11107aD.clearAnimation();
                    RedbagMainActivity.this.f11105aB.removeView(RedbagMainActivity.this.f11107aD);
                    RedbagMainActivity.this.f11107aD = null;
                }
                if (RedbagMainActivity.this.f11108aE != null) {
                    RedbagMainActivity.this.f11108aE.clearAnimation();
                    RedbagMainActivity.this.f11105aB.removeView(RedbagMainActivity.this.f11108aE);
                    RedbagMainActivity.this.f11108aE = null;
                }
                if (RedbagMainActivity.this.f11109aF != null) {
                    RedbagMainActivity.this.f11109aF.clearAnimation();
                    RedbagMainActivity.this.f11105aB.removeView(RedbagMainActivity.this.f11109aF);
                    RedbagMainActivity.this.f11109aF = null;
                }
            }
        });
    }

    @Override // com.gbcom.gwifi.p221a.p222a.MsgDispatcherListener
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) {
        Log.d("aaaaaa", "-----exceptionCaught-----" + th.toString());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12423a(int i, int i2, String str) {
        RedbagBettingRequest redbagBettingRequest = new RedbagBettingRequest();
        redbagBettingRequest.setAccountId(i);
        redbagBettingRequest.setRedId(i2);
        redbagBettingRequest.setRoundNo(str);
        channel.writeAndFlush(redbagBettingRequest);
    }

    public void processRequestRedbagInto(int i, int i2) {
        RedbagIntoRequest redbagIntoRequest = new RedbagIntoRequest();
        redbagIntoRequest.setAccountId(i);
        redbagIntoRequest.setRedId(i2);
        channel.writeAndFlush(redbagIntoRequest);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12420a(int i) {
        RedbagListRequest redbagListRequest = new RedbagListRequest();
        redbagListRequest.setAccountId(i);
        channel.writeAndFlush(redbagListRequest);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12439b(int i) {
        AccountInfoRequest accountInfoRequest = new AccountInfoRequest();
        accountInfoRequest.setAccountId(i);
        channel.writeAndFlush(accountInfoRequest);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12451c(int i) {
        channel.writeAndFlush(new PrizePoolRequest());
    }

    public void processRequestRedbagDetail(int i, int i2, String str) {
        RedbagDetailRequest redbagDetailRequest = new RedbagDetailRequest();
        redbagDetailRequest.setAccountId(i);
        redbagDetailRequest.setRedId(i2);
        redbagDetailRequest.setRoundNo(str);
        channel.writeAndFlush(redbagDetailRequest);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12422a(int i, int i2, int i3) {
        RedbagExitRequest redbagExitRequest = new RedbagExitRequest();
        redbagExitRequest.setAccountId(i);
        redbagExitRequest.setRedId(i2);
        redbagExitRequest.setCurrRedId(i3);
        channel.writeAndFlush(redbagExitRequest);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m12457d(int i) {
        RedbagJoinedRequest redbagJoinedRequest = new RedbagJoinedRequest();
        redbagJoinedRequest.setAccountId(i);
        channel.writeAndFlush(redbagJoinedRequest);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m12464e(int i) {
        RankingRequest rankingRequest = new RankingRequest();
        rankingRequest.setAccountId(i);
        channel.writeAndFlush(rankingRequest);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: f */
    private void m12470f(int i) {
        LuckyRedbagIntoRequest luckyRedbagIntoRequest = new LuckyRedbagIntoRequest();
        luckyRedbagIntoRequest.setAccountId(i);
        channel.writeAndFlush(luckyRedbagIntoRequest);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12421a(int i, int i2) {
        LuckyRedbagDetailRequest luckyRedbagDetailRequest = new LuckyRedbagDetailRequest();
        luckyRedbagDetailRequest.setAccountId(i);
        luckyRedbagDetailRequest.setLuckyRedbagId(i2);
        channel.writeAndFlush(luckyRedbagDetailRequest);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: g */
    private void m12475g(int i) {
        NewComerRedbagBettingRequest newComerRedbagBettingRequest = new NewComerRedbagBettingRequest();
        newComerRedbagBettingRequest.setAccountId(i);
        channel.writeAndFlush(newComerRedbagBettingRequest);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12440b(int i, int i2) {
        LuckyRedbagBettingRequest luckyRedbagBettingRequest = new LuckyRedbagBettingRequest();
        luckyRedbagBettingRequest.setAccountId(i);
        luckyRedbagBettingRequest.setLuckyRedbagId(i2);
        channel.writeAndFlush(luckyRedbagBettingRequest);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12441b(int i, int i2, int i3) {
        LuckyRedbagJoinedRequest luckyRedbagJoinedRequest = new LuckyRedbagJoinedRequest();
        luckyRedbagJoinedRequest.setAccountId(i);
        luckyRedbagJoinedRequest.setStart(i2);
        luckyRedbagJoinedRequest.setSize(i3);
        channel.writeAndFlush(luckyRedbagJoinedRequest);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: g */
    private void m12474g() {
        channel.writeAndFlush(new RedbagMessegeRequest());
    }

    @Override // android.support.p009v4.app.FragmentActivity
    public void onBackPressed() {
        this.f11100X = true;
        if (this.ctx != null) {
            this.ctx.close();
        }
        super.onBackPressed();
        if (this.f11088L) {
            GBApplication.instance().unregisterReceiver(this.f11087K);
            this.f11088L = false;
        }
        m12479h();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
        this.f11159bc.removeCallbacksAndMessages(null);
        this.f11159bc = null;
        if (this.ctx != null) {
            this.ctx.close();
        }
        m12479h();
        if (this.f11088L) {
            GBApplication.instance().unregisterReceiver(this.f11087K);
            this.f11088L = false;
        }
        if (this.f11168o != 0 && !TextUtils.isEmpty(this.f11090N.mo26736a().toString().trim())) {
            this.f11139aj.put(Integer.valueOf(this.f11168o), this.f11090N.mo26736a().toString().trim());
            CacheAccount.getInstance().setUserBalance(this.f11139aj);
        }
        GBApplication.instance().unregisterReceiver(this.f11111aH);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity
    public void onRestart() {
        super.onRestart();
        if (this.f11138ai && this.f11144ao) {
            this.f11144ao = false;
            this.f11179z.execute(new Runnable() {
                /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC301324 */

                @Override // java.lang.Runnable
                public void run() {
                    RedbagMainActivity.this.m12439b((RedbagMainActivity) RedbagMainActivity.this.f11168o);
                    Log.d("aaaaaa", "onRestart():processRequestAccountInfo");
                }
            });
        }
    }

    @Override // com.gbcom.gwifi.functions.redbag.p251a.RedbagListAdapter.AbstractC3068b
    public void onOpenClick(View view, final String str) {
        this.f11179z.execute(new Runnable() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC301425 */

            @Override // java.lang.Runnable
            public void run() {
                RedbagMainActivity.this.m12423a((RedbagMainActivity) RedbagMainActivity.this.f11168o, RedbagMainActivity.this.f11175v, (int) str);
            }
        });
    }

    @Override // com.gbcom.gwifi.functions.redbag.p251a.RedbagListAdapter.AbstractC3067a
    public void onOpenClick(View view, ArrayList<HitRecord> arrayList) {
        Intent intent = new Intent(this, RedbagResultActivity.class);
        intent.putExtra("source", "lastRound");
        intent.putExtra("lastRound", arrayList);
        intent.putExtra("redName", this.f11080D);
        intent.putExtra("totalBeans", this.f11147ar);
        intent.putExtra("mUid", this.f11168o);
        intent.putExtra("totalCount", this.f11118aO);
        startActivity(intent);
    }

    /* renamed from: a */
    private View m12412a(int i, ArrayList<Redbag> arrayList) {
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.redbag_item_bottom_main, (ViewGroup) null);
        this.f11083G = (TextView) inflate.findViewById(2131755200);
        this.f11084H = (TextView) inflate.findViewById(C2425R.C2427id.tv_item);
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.iv_item);
        Redbag redbag = arrayList.get(i);
        this.f11083G.setText(redbag.getRedName());
        this.f11084H.setText(redbag.getRedName() + "红包场");
        ((TextView) inflate.findViewById(C2425R.C2427id.tv_name_selected)).setText(redbag.getRedName());
        return inflate;
    }

    @Override // com.gbcom.gwifi.functions.redbag.view.TabContainer.AbstractC3078a
    public void onTabSelected(int i, View view) {
        this.f11094R = true;
        this.f11078B = i;
        this.f11080D = this.f11178y.get(i).getRedName();
        this.f11175v = this.f11178y.get(i).getRedId();
        this.f11118aO = this.f11178y.get(i).getTotalCount();
        this.f11179z.execute(new Runnable() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC301526 */

            @Override // java.lang.Runnable
            public void run() {
                RedbagMainActivity.this.processRequestRedbagInto(RedbagMainActivity.this.f11168o, RedbagMainActivity.this.f11175v);
                RedbagMainActivity.this.runOnUiThread(new Runnable() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC301526.RunnableC30161 */

                    @Override // java.lang.Runnable
                    public void run() {
                    }
                });
            }
        });
        Log.d("aaaaaa", "onTabSelected:position" + i);
        ((ImageView) view.findViewById(C2425R.C2427id.iv_item)).setImageResource(C2425R.C2426drawable.redbag_bottom_open);
        ((TextView) view.findViewById(2131755200)).setVisibility(8);
        ((TextView) view.findViewById(C2425R.C2427id.tv_item)).setTextColor(getResources().getColor(C2425R.color.red));
        TextView textView = (TextView) view.findViewById(C2425R.C2427id.tv_name_selected);
        textView.setText(this.f11178y.get(i).getRedName());
        textView.setVisibility(0);
        textView.setTextColor(getResources().getColor(C2425R.color.redbagBottomText));
        this.f11147ar = this.f11178y.get(i).getDeductBeans();
        String valueOf = String.valueOf(this.f11147ar);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("需" + valueOf + Constant.f13309cr);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(-65536), 1, valueOf.length() + 1, 34);
        this.f11077A.setText(spannableStringBuilder);
        this.f11179z.execute(new Runnable() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC301727 */

            @Override // java.lang.Runnable
            public void run() {
                if (RedbagMainActivity.this.f11149at != -1) {
                    RedbagMainActivity.this.m12422a((RedbagMainActivity) RedbagMainActivity.this.f11168o, ((Redbag) RedbagMainActivity.this.f11178y.get(RedbagMainActivity.this.f11078B)).getRedId(), ((Redbag) RedbagMainActivity.this.f11178y.get(RedbagMainActivity.this.f11149at)).getRedId());
                }
            }
        });
    }

    @Override // com.gbcom.gwifi.functions.redbag.view.TabContainer.AbstractC3078a
    public void onTabUnselected(int i, View view) {
        Log.d("aaaaaa", "onTabUnselected:position" + i);
        this.f11149at = i;
        ((ImageView) view.findViewById(C2425R.C2427id.iv_item)).setImageResource(C2425R.C2426drawable.redbag_bottom);
        TextView textView = (TextView) view.findViewById(2131755200);
        textView.setVisibility(0);
        textView.setTextColor(getResources().getColor(C2425R.color.redbagBottomText));
        ((TextView) view.findViewById(C2425R.C2427id.tv_name_selected)).setVisibility(8);
        ((TextView) view.findViewById(C2425R.C2427id.tv_item)).setTextColor(getResources().getColor(C2425R.color.black));
    }

    public void startOfficeDrawable() {
        this.f11099W.setVisibility(0);
        this.f11099W.mo28307i();
        this.f11099W.mo28313o();
        this.f11099W.mo28315q();
        if (this.f11099W.mo28310l()) {
            this.f11099W.mo28309k();
        }
        this.f11099W.mo28297a();
        this.f11099W.mo28302d();
        this.f11099W.mo28304f();
        this.f11099W.mo28300b();
    }

    public void errWebDrawable() {
        this.f11099W.setVisibility(0);
        this.f11099W.mo28303e();
        this.f11099W.mo28301c();
        this.f11099W.mo28305g();
        this.f11099W.mo28306h();
        this.f11099W.mo28308j();
        this.f11099W.mo28312n();
        this.f11099W.mo28314p();
    }

    public void stopOfficeDrawable() {
        this.f11099W.setVisibility(8);
        this.f11099W.mo28301c();
    }

    @Override // com.gbcom.gwifi.p221a.p222a.ClientStarter.AbstractC2435a
    public void setOnError(String str) {
        runOnUiThread(new Runnable() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC301828 */

            @Override // java.lang.Runnable
            public void run() {
                RedbagMainActivity.this.errWebDrawable();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: h */
    private void m12479h() {
        if (this.f11130aa != null && this.f11130aa.isShowing()) {
            this.f11130aa.dismiss();
        }
        if (this.f11131ab != null && this.f11131ab.isShowing()) {
            this.f11131ab.dismiss();
        }
        if (this.f11132ac != null && this.f11132ac.isShowing()) {
            this.f11132ac.dismiss();
        }
        if (this.f11133ad != null && this.f11133ad.isShowing()) {
            this.f11133ad.dismiss();
        }
    }

    /* renamed from: a */
    private void m12433a(List<RankingResponse.ResponseBean.ListBean> list) {
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.redbag_popupwindow_ranking, (ViewGroup) null);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C2425R.C2427id.ll_main);
        final ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.iv_close);
        ListView listView = (ListView) inflate.findViewById(C2425R.C2427id.f8357lv);
        listView.setVerticalScrollBarEnabled(false);
        listView.setAdapter((ListAdapter) new CommonListAdapter(list, C2425R.layout.redbag_popupwindow_ranking_lv, new CommonListAdapter.AbstractC3060a<RankingResponse.ResponseBean.ListBean>() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.C301929 */

            /* renamed from: a */
            public void mo24662a(CommonListAdapter.C3061b bVar, RankingResponse.ResponseBean.ListBean listBean, int i) {
                TextView textView = (TextView) bVar.mo26111a(C2425R.C2427id.tv_ranking);
                if (i <= 2) {
                    textView.setBackgroundResource(RedbagMainActivity.this.f11093Q[i]);
                    textView.setText("");
                } else {
                    textView.setBackgroundResource(C2425R.color.transparent);
                    textView.setGravity(17);
                    textView.setText((i + 1) + "");
                }
                bVar.mo26112a(C2425R.C2427id.ranking_name, (CharSequence) listBean.getNickName()).mo26112a(C2425R.C2427id.ranking_bonus, (CharSequence) (listBean.getTotalHitBeans() + "")).mo26113a(C2425R.C2427id.ranking_avater, listBean.getFaceUrl());
            }
        }));
        this.f11133ad = new PopupWindow(inflate, -1, -1, true);
        this.f11133ad.setBackgroundDrawable(new ColorDrawable());
        this.f11133ad.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
        ((FrameLayout) inflate.findViewById(C2425R.C2427id.fl_main)).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.ViewTreeObserver$OnGlobalLayoutListenerC302130 */

            public void onGlobalLayout() {
                int right = linearLayout.getRight();
                int top = linearLayout.getTop();
                imageView.layout(right - (imageView.getMeasuredWidth() / 2), top - (imageView.getMeasuredHeight() / 2), right + (imageView.getMeasuredWidth() / 2), top + (imageView.getMeasuredWidth() / 2));
                DisplayMetrics displayMetrics = RedbagMainActivity.this.getResources().getDisplayMetrics();
                int i = displayMetrics.widthPixels;
                int i2 = displayMetrics.heightPixels;
                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                layoutParams.width = (int) (((double) i) * 0.8d);
                layoutParams.height = (int) (((double) i2) * 0.8d);
                linearLayout.setLayoutParams(layoutParams);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.View$OnClickListenerC302231 */

            public void onClick(View view) {
                RedbagMainActivity.this.f11133ad.dismiss();
            }
        });
    }

    /* renamed from: i */
    private void m12483i() {
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.redbag_popupwindow_rule, (ViewGroup) null);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C2425R.C2427id.fl_main);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C2425R.C2427id.ll_main);
        final ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.iv_close);
        TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.tv_rule);
        if (this.f11137ah != null) {
            textView.setText(Html.fromHtml(this.f11137ah));
        }
        this.f11131ab = new PopupWindow(inflate, -1, -1, true);
        this.f11131ab.setBackgroundDrawable(new ColorDrawable());
        this.f11131ab.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.ViewTreeObserver$OnGlobalLayoutListenerC302332 */

            public void onGlobalLayout() {
                int right = linearLayout.getRight();
                int top = linearLayout.getTop();
                imageView.layout(right - (imageView.getMeasuredWidth() / 2), top - (imageView.getMeasuredHeight() / 2), right + (imageView.getMeasuredWidth() / 2), top + (imageView.getMeasuredWidth() / 2));
                DisplayMetrics displayMetrics = RedbagMainActivity.this.getResources().getDisplayMetrics();
                int i = displayMetrics.widthPixels;
                int i2 = displayMetrics.heightPixels;
                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                layoutParams.width = (int) (((double) i) * 0.8d);
                layoutParams.height = (int) (((double) i2) * 0.8d);
                linearLayout.setLayoutParams(layoutParams);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.View$OnClickListenerC302433 */

            public void onClick(View view) {
                RedbagMainActivity.this.f11131ab.dismiss();
            }
        });
    }

    /* renamed from: j */
    private void m12487j() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        final int i = displayMetrics.widthPixels;
        final int i2 = displayMetrics.heightPixels;
        runOnUiThread(new Runnable() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC302635 */

            @Override // java.lang.Runnable
            public void run() {
                View inflate = LayoutInflater.from(RedbagMainActivity.this).inflate(C2425R.layout.redbag_popupwindow_prizepool, (ViewGroup) null);
                FrameLayout frameLayout = (FrameLayout) inflate.findViewById(C2425R.C2427id.fl_main);
                final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C2425R.C2427id.ll_main);
                final ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.iv_close);
                ((TextView) inflate.findViewById(C2425R.C2427id.tv_prizepool)).setText(RedbagMainActivity.this.f11091O + "");
                List<PrizePoolSetting> luckyNumbers = RedbagMainActivity.this.f11092P.getResponse().getLuckyNumbers();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < luckyNumbers.size(); i++) {
                    arrayList.add(luckyNumbers.get(i));
                }
                LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(C2425R.C2427id.ll_number);
                LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(C2425R.C2427id.ll_chance);
                for (int i2 = 0; i2 < arrayList.size() + 1; i2++) {
                    TextView textView = new TextView(inflate.getContext());
                    textView.setTextColor(RedbagMainActivity.this.getResources().getColor(C2425R.color.red));
                    textView.setGravity(17);
                    TextView textView2 = new TextView(inflate.getContext());
                    textView2.setBackgroundColor(RedbagMainActivity.this.getResources().getColor(2131624113));
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 2.0f);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                    LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(1, -1);
                    textView.setTextColor(RedbagMainActivity.this.getResources().getColor(C2425R.color.red));
                    textView.setGravity(17);
                    if (i2 == arrayList.size()) {
                        textView.setText("幸运旺豆数");
                        linearLayout2.addView(textView, 0, layoutParams);
                        linearLayout2.addView(textView2, 1, layoutParams3);
                    } else if (i2 == arrayList.size() - 1) {
                        textView.setText(((PrizePoolSetting) arrayList.get(i2)).getNumber() + "");
                        linearLayout2.addView(textView, layoutParams2);
                    } else {
                        textView.setText(((PrizePoolSetting) arrayList.get(i2)).getNumber() + "");
                        linearLayout2.addView(textView, layoutParams2);
                        linearLayout2.addView(textView2, layoutParams3);
                    }
                }
                for (int i3 = 0; i3 < arrayList.size() + 1; i3++) {
                    TextView textView3 = new TextView(inflate.getContext());
                    textView3.setTextColor(RedbagMainActivity.this.getResources().getColor(C2425R.color.red));
                    textView3.setGravity(17);
                    TextView textView4 = new TextView(inflate.getContext());
                    textView4.setBackgroundColor(RedbagMainActivity.this.getResources().getColor(2131624113));
                    LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -2, 2.0f);
                    LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                    LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(1, -1);
                    textView3.setTextColor(RedbagMainActivity.this.getResources().getColor(C2425R.color.black));
                    textView3.setGravity(17);
                    if (i3 == arrayList.size()) {
                        textView3.setText("开启奖金比例");
                        linearLayout3.addView(textView3, 0, layoutParams4);
                        linearLayout3.addView(textView4, 1, layoutParams6);
                    } else if (i3 == arrayList.size() - 1) {
                        textView3.setText(((PrizePoolSetting) arrayList.get(i3)).getReward());
                        linearLayout3.addView(textView3, layoutParams5);
                    } else {
                        textView3.setText(((PrizePoolSetting) arrayList.get(i3)).getReward());
                        linearLayout3.addView(textView3, layoutParams5);
                        linearLayout3.addView(textView4, layoutParams6);
                    }
                }
                List<AccountPrizePoolBonus> luckyAccounts = RedbagMainActivity.this.f11092P.getResponse().getLuckyAccounts();
                LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(C2425R.C2427id.bonus_1);
                LinearLayout linearLayout5 = (LinearLayout) inflate.findViewById(C2425R.C2427id.bonus_2);
                LinearLayout linearLayout6 = (LinearLayout) inflate.findViewById(C2425R.C2427id.bonus_3);
                View findViewById = inflate.findViewById(C2425R.C2427id.line_1);
                View findViewById2 = inflate.findViewById(C2425R.C2427id.line_2);
                if (luckyAccounts.size() == 1) {
                    linearLayout4.setVisibility(0);
                    findViewById.setVisibility(4);
                    linearLayout5.setVisibility(4);
                    findViewById2.setVisibility(4);
                    linearLayout6.setVisibility(4);
                    ImageTools.m14149a(luckyAccounts.get(0).getFaceUrl(), (ImageView) inflate.findViewById(C2425R.C2427id.tv_avater1), RedbagMainActivity.this.f11156b);
                    ((TextView) inflate.findViewById(C2425R.C2427id.tv_name1)).setText(luckyAccounts.get(0).getNickName());
                    ((TextView) inflate.findViewById(C2425R.C2427id.tv_bonus1)).setText(luckyAccounts.get(0).getLuckyNumberBonus() + "");
                } else if (luckyAccounts.size() == 2) {
                    linearLayout4.setVisibility(0);
                    findViewById.setVisibility(0);
                    linearLayout5.setVisibility(0);
                    findViewById2.setVisibility(0);
                    ImageTools.m14149a(luckyAccounts.get(0).getFaceUrl(), (ImageView) inflate.findViewById(C2425R.C2427id.tv_avater1), RedbagMainActivity.this.f11156b);
                    ((TextView) inflate.findViewById(C2425R.C2427id.tv_name1)).setText(luckyAccounts.get(0).getNickName());
                    ((TextView) inflate.findViewById(C2425R.C2427id.tv_bonus1)).setText(luckyAccounts.get(0).getLuckyNumberBonus() + "");
                    ImageTools.m14149a(luckyAccounts.get(1).getFaceUrl(), (ImageView) inflate.findViewById(C2425R.C2427id.tv_avater2), RedbagMainActivity.this.f11156b);
                    ((TextView) inflate.findViewById(C2425R.C2427id.tv_name2)).setText(luckyAccounts.get(1).getNickName());
                    ((TextView) inflate.findViewById(C2425R.C2427id.tv_bonus2)).setText(luckyAccounts.get(1).getLuckyNumberBonus() + "");
                } else if (luckyAccounts.size() >= 3) {
                    linearLayout4.setVisibility(0);
                    findViewById.setVisibility(0);
                    linearLayout5.setVisibility(0);
                    findViewById2.setVisibility(0);
                    linearLayout6.setVisibility(0);
                    ImageTools.m14149a(luckyAccounts.get(0).getFaceUrl(), (ImageView) inflate.findViewById(C2425R.C2427id.tv_avater1), RedbagMainActivity.this.f11156b);
                    ((TextView) inflate.findViewById(C2425R.C2427id.tv_name1)).setText(luckyAccounts.get(0).getNickName());
                    ((TextView) inflate.findViewById(C2425R.C2427id.tv_bonus1)).setText(luckyAccounts.get(0).getLuckyNumberBonus() + "");
                    ImageTools.m14149a(luckyAccounts.get(1).getFaceUrl(), (ImageView) inflate.findViewById(C2425R.C2427id.tv_avater2), RedbagMainActivity.this.f11156b);
                    ((TextView) inflate.findViewById(C2425R.C2427id.tv_name2)).setText(luckyAccounts.get(1).getNickName());
                    ((TextView) inflate.findViewById(C2425R.C2427id.tv_bonus2)).setText(luckyAccounts.get(1).getLuckyNumberBonus() + "");
                    ImageTools.m14149a(luckyAccounts.get(2).getFaceUrl(), (ImageView) inflate.findViewById(C2425R.C2427id.tv_avater3), RedbagMainActivity.this.f11156b);
                    ((TextView) inflate.findViewById(C2425R.C2427id.tv_name3)).setText(luckyAccounts.get(2).getNickName());
                    ((TextView) inflate.findViewById(C2425R.C2427id.tv_bonus3)).setText(luckyAccounts.get(2).getLuckyNumberBonus() + "");
                }
                RedbagMainActivity.this.f11132ac = new PopupWindow(inflate, -1, -1, true);
                RedbagMainActivity.this.f11132ac.setBackgroundDrawable(new ColorDrawable());
                RedbagMainActivity.this.f11132ac.showAsDropDown(RedbagMainActivity.this.findViewById(C2425R.C2427id.my_title_layout));
                frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC302635.ViewTreeObserver$OnGlobalLayoutListenerC30271 */

                    public void onGlobalLayout() {
                        int right = linearLayout.getRight();
                        int top = linearLayout.getTop();
                        imageView.layout(right - (imageView.getMeasuredWidth() / 2), top - (imageView.getMeasuredHeight() / 2), right + (imageView.getMeasuredWidth() / 2), top + (imageView.getMeasuredWidth() / 2));
                        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                        layoutParams.width = (int) (((double) i) * 0.8d);
                        layoutParams.height = (int) (((double) i2) * 0.8d);
                        linearLayout.setLayoutParams(layoutParams);
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagMainActivity.RunnableC302635.View$OnClickListenerC30282 */

                    public void onClick(View view) {
                        RedbagMainActivity.this.f11132ac.dismiss();
                    }
                });
            }
        });
    }

    /* renamed from: k */
    private void m12491k() {
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.redbag_popupwidow, (ViewGroup) null);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.ll_rule)).setOnClickListener(this);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.ll_score)).setOnClickListener(this);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.ll_ranking)).setOnClickListener(this);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.ll_welfare)).setOnClickListener(this);
        this.f11130aa = new PopupWindow(inflate, -2, -2, true);
        this.f11130aa.setBackgroundDrawable(new ColorDrawable());
        this.f11130aa.showAsDropDown(this.f11082F, (-this.f11082F.getWidth()) - this.f11089M.getPaddingRight(), 0);
    }

    /* renamed from: a */
    private LuckyRedbagDetailResponse m12416a(LuckyRedbagBettingResponse luckyRedbagBettingResponse) {
        LuckyRedbagDetailResponse luckyRedbagDetailResponse = new LuckyRedbagDetailResponse();
        LuckyRedbagDetailResponse.LuckyRedbagDetailResponseBody response = luckyRedbagDetailResponse.getResponse();
        response.setHitBeans(luckyRedbagBettingResponse.getResponse().getHitBeans());
        response.setLuckyCount(luckyRedbagBettingResponse.getResponse().getLuckyCount());
        response.setLuckyRedbagId(luckyRedbagBettingResponse.getResponse().getLuckyRedbagId());
        response.setHasSale(luckyRedbagBettingResponse.getResponse().getHasSale());
        response.setHasHitBeans(luckyRedbagBettingResponse.getResponse().getHasHitBeans());
        response.setHitRecord(luckyRedbagBettingResponse.getResponse().getHitRecord());
        response.setHitRecordSize(luckyRedbagBettingResponse.getResponse().getHitRecordSize());
        response.setIsJoin(luckyRedbagBettingResponse.getResponse().getIsJoin());
        response.setLuckyCostTime(luckyRedbagBettingResponse.getResponse().getLuckyCostTime());
        response.setLuckyTotalBeans(luckyRedbagBettingResponse.getResponse().getLuckyTotalBeans());
        return luckyRedbagDetailResponse;
    }
}
