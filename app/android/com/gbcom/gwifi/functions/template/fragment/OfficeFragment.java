package com.gbcom.gwifi.functions.template.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.gbcom.edu.functionModule.main.circle.activitys.CircleUserDetailActivity;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.app.GBElementManager;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.loading.domain.TabDatas;
import com.gbcom.gwifi.functions.notify.NotifyListActivity;
import com.gbcom.gwifi.functions.notify.p245a.NotifyDao;
import com.gbcom.gwifi.functions.profile.MyProfileActivity;
import com.gbcom.gwifi.functions.profile.UserLevelActivity;
import com.gbcom.gwifi.functions.system.SystemSetActivity;
import com.gbcom.gwifi.functions.wifi.MultiCheckNetWorkUtils;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Config;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.ResourceParse;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAd;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.LoadingView;
import com.gbcom.gwifi.widget.PullToRefreshView;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.template.fragment.h */
public class OfficeFragment extends GBFragment implements View.OnClickListener {

    /* renamed from: b */
    private static final String f12441b = "OfficeFragment";

    /* renamed from: c */
    private static final Object f12442c = f12441b;

    /* renamed from: t */
    private static final int f12443t = 2;

    /* renamed from: u */
    private static final int f12444u = 80;

    /* renamed from: w */
    private static final String f12445w = "元";

    /* renamed from: x */
    private static final int f12446x = 14;

    /* renamed from: A */
    private SimpleDateFormat f12447A;

    /* renamed from: B */
    private ScrollView f12448B;

    /* renamed from: C */
    private TextView f12449C;

    /* renamed from: D */
    private ImageView f12450D;

    /* renamed from: E */
    private AnimationDrawable f12451E;

    /* renamed from: F */
    private RelativeLayout f12452F;

    /* renamed from: G */
    private LinearLayout f12453G;

    /* renamed from: H */
    private ImageView f12454H;

    /* renamed from: I */
    private LinearLayout f12455I;

    /* renamed from: J */
    private LoadingView f12456J;

    /* renamed from: K */
    private LinearLayout f12457K;

    /* renamed from: L */
    private LinearLayout f12458L;

    /* renamed from: M */
    private LinearLayout f12459M;

    /* renamed from: N */
    private LinearLayout f12460N;

    /* renamed from: O */
    private String f12461O = "";

    /* renamed from: P */
    private String f12462P = "";

    /* renamed from: Q */
    private String f12463Q;

    /* renamed from: R */
    private ImageView f12464R;

    /* renamed from: S */
    private long f12465S = -1;

    /* renamed from: T */
    private BroadcastReceiver f12466T = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.OfficeFragment.C33144 */

        public void onReceive(Context context, Intent intent) {
            if (OfficeFragment.this.getActivity() != null) {
                OfficeFragment.this.f12454H.setImageResource(C2425R.C2426drawable.user_message_third);
                ((MainActivity) OfficeFragment.this.getActivity()).setReadMsgState();
            }
        }
    };

    /* renamed from: U */
    private BroadcastReceiver f12467U = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.OfficeFragment.C33155 */

        public void onReceive(Context context, Intent intent) {
            OfficeFragment.this.f12454H.setImageResource(C2425R.C2426drawable.user_message_red);
            OfficeFragment.this.m13625j();
        }
    };

    /* renamed from: V */
    private AdapterView.OnItemClickListener f12468V = new AdapterView.OnItemClickListener() {
        /* class com.gbcom.gwifi.functions.template.fragment.OfficeFragment.C33166 */

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i != 0) {
                UmengCount.queryOfficeNavigation(GBApplication.instance(), (String) view.getTag(C2425R.C2427id.tag_second));
                GBActivity.openBackWebView((String) view.getTag(C2425R.C2427id.tag_first), (String) view.getTag(C2425R.C2427id.tag_second), ((Integer) view.getTag(C2425R.C2427id.tag_five)).intValue());
                return;
            }
            String k = HttpUtil.m14335k();
            if (!C3467s.m14513e(k)) {
                GBActivity.openOfficeBackWebView(k, "");
            } else {
                GBActivity.showMessageToast("链接无效...");
            }
        }
    };

    /* renamed from: W */
    private BroadcastReceiver f12469W = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.OfficeFragment.C33188 */

        public void onReceive(Context context, Intent intent) {
            OfficeFragment.this.m13633n();
        }
    };

    /* renamed from: X */
    private LoadingView.AbstractC3488a f12470X = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.template.fragment.OfficeFragment.C331110 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            OfficeFragment.this.m13635o();
        }
    };

    /* renamed from: Y */
    private Handler f12471Y = new Handler() {
        /* class com.gbcom.gwifi.functions.template.fragment.OfficeFragment.HandlerC33122 */

        public void handleMessage(Message message) {
            switch (message.what) {
                case 14:
                    OfficeFragment.this.m13627k();
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: a */
    OkRequestHandler<String> f12472a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.fragment.OfficeFragment.C33199 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            OfficeFragment.this.mo27324b();
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (OfficeFragment.this.isAdded()) {
                OfficeFragment.this.f12479j.mo28398a("更新于:" + OfficeFragment.this.f12447A.format(new Date(System.currentTimeMillis())));
                if (OfficeFragment.this.f12481l == abVar) {
                    ArrayList<HashMap<String, Object>> officeInfo = CacheApp.getInstance().getOfficeInfo();
                    if (officeInfo != null) {
                        if (officeInfo != null && officeInfo.size() > 2 && ((String) officeInfo.get(0).get("title")).equals("WiFi充值") && ((String) officeInfo.get(1).get("title")).equals("账单查询")) {
                            OfficeFragment.this.f12461O = (String) officeInfo.get(0).get("wap_url");
                            OfficeFragment.this.f12462P = (String) officeInfo.get(1).get("wap_url");
                            Iterator<HashMap<String, Object>> it = officeInfo.iterator();
                            while (it.hasNext()) {
                                String str = (String) it.next().get("title");
                                if (str.equals("WiFi充值") || str.equals("账单查询")) {
                                    it.remove();
                                }
                            }
                        }
                        OfficeFragment.this.f12486q = officeInfo;
                        OfficeFragment.this.f12478i.notifyDataSetChanged();
                        OfficeFragment.this.mo27326d();
                        return;
                    }
                    OfficeFragment.this.mo27325c();
                } else if (OfficeFragment.this.f12482m != abVar && OfficeFragment.this.f12483n == abVar) {
                    GBActivity.showMessageToast("签到失败!");
                    OfficeFragment.this.f12455I.setEnabled(true);
                }
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (OfficeFragment.this.isAdded()) {
                OfficeFragment.this.f12479j.mo28398a("更新于:" + OfficeFragment.this.f12447A.format(new Date(System.currentTimeMillis())));
                if (abVar == OfficeFragment.this.f12481l) {
                    OfficeFragment.this.m13604a((OfficeFragment) str);
                } else if (abVar == OfficeFragment.this.f12482m) {
                    OfficeFragment.this.m13608b((OfficeFragment) str);
                    GBGlobalConfig.m10510c().mo24414n();
                } else if (abVar == OfficeFragment.this.f12483n) {
                    OfficeFragment.this.m13611c((OfficeFragment) str);
                }
                OfficeFragment.this.f12471Y.removeMessages(14);
                OfficeFragment.this.f12471Y.sendEmptyMessage(14);
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: d */
    private ImageView f12473d;

    /* renamed from: e */
    private TextView f12474e;

    /* renamed from: f */
    private TextView f12475f;

    /* renamed from: g */
    private ListView f12476g;

    /* renamed from: h */
    private RelativeLayout f12477h;

    /* renamed from: i */
    private C3321b f12478i;

    /* renamed from: j */
    private PullToRefreshView f12479j;

    /* renamed from: k */
    private LinearLayout f12480k;

    /* renamed from: l */
    private Request f12481l;

    /* renamed from: m */
    private Request f12482m;

    /* renamed from: n */
    private Request f12483n;

    /* renamed from: o */
    private C3322c f12484o;

    /* renamed from: p */
    private C3320a f12485p;

    /* renamed from: q */
    private ArrayList<HashMap<String, Object>> f12486q = new ArrayList<>();

    /* renamed from: r */
    private boolean f12487r = true;

    /* renamed from: s */
    private final int f12488s = 1;

    /* renamed from: v */
    private final int f12489v = 86400;

    /* renamed from: y */
    private long f12490y = -1;

    /* renamed from: z */
    private final long f12491z = 3000;

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            List<TabDatas.DataBean.TabListBean.ChildTabBean> child_tab = ((TabDatas.DataBean.TabListBean) arguments.get("tabListBean")).getChild_tab();
            if (child_tab.size() > 0) {
                this.f12463Q = child_tab.get(0).getName();
            }
        }
        return layoutInflater.inflate(C2425R.layout.fragment_office, viewGroup, false);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        m13601a(view);
        m13621h();
        m13623i();
        super.onViewCreated(view, bundle);
    }

    /* renamed from: a */
    private void m13601a(View view) {
        this.f12447A = new SimpleDateFormat("MM-dd HH:mm");
        this.f12479j = (PullToRefreshView) view.findViewById(C2425R.C2427id.main_pull_refresh_view);
        this.f12479j.mo28397a(new PullToRefreshView.AbstractC3494b() {
            /* class com.gbcom.gwifi.functions.template.fragment.OfficeFragment.C33101 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
            public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
                OfficeFragment.this.m13635o();
            }
        });
        this.f12480k = (LinearLayout) view.findViewById(C2425R.C2427id.user_set_linear);
        this.f12480k.setOnClickListener(this);
        this.f12477h = (RelativeLayout) view.findViewById(C2425R.C2427id.office_fragment_ly);
        this.f12473d = (ImageView) view.findViewById(C2425R.C2427id.user_head);
        this.f12473d.setOnClickListener(this);
        this.f12474e = (TextView) view.findViewById(C2425R.C2427id.user_phone);
        this.f12476g = (ListView) view.findViewById(C2425R.C2427id.office_gv);
        this.f12478i = new C3321b();
        this.f12476g.setAdapter((ListAdapter) this.f12478i);
        this.f12476g.setOnItemClickListener(this.f12468V);
        this.f12476g.requestFocus();
        this.f12448B = (ScrollView) view.findViewById(C2425R.C2427id.office_scroll);
        this.f12456J = (LoadingView) view.findViewById(C2425R.C2427id.loading_view);
        this.f12456J.mo28298a(this.f12470X);
        this.f12450D = (ImageView) view.findViewById(C2425R.C2427id.office_iv_share);
        this.f12451E = (AnimationDrawable) this.f12450D.getBackground();
        this.f12452F = (RelativeLayout) view.findViewById(C2425R.C2427id.office_share);
        this.f12452F.setOnClickListener(this);
        this.f12453G = (LinearLayout) view.findViewById(C2425R.C2427id.message_layout);
        this.f12453G.setOnClickListener(this);
        this.f12454H = (ImageView) view.findViewById(C2425R.C2427id.message_icon);
        this.f12455I = (LinearLayout) view.findViewById(C2425R.C2427id.sign_layout);
        this.f12455I.setEnabled(true);
        this.f12455I.setOnClickListener(this);
        this.f12457K = (LinearLayout) view.findViewById(C2425R.C2427id.office_item_rl);
        this.f12457K.setOnClickListener(this);
        this.f12459M = (LinearLayout) view.findViewById(C2425R.C2427id.inquiry_layout);
        this.f12459M.setOnClickListener(this);
        this.f12460N = (LinearLayout) view.findViewById(C2425R.C2427id.recharge_layout);
        this.f12460N.setOnClickListener(this);
        ((TextView) view.findViewById(C2425R.C2427id.office_title)).setText(this.f12463Q);
        this.f12464R = (ImageView) view.findViewById(C2425R.C2427id.iv_type);
        ((TextView) view.findViewById(C2425R.C2427id.user_data)).setOnClickListener(this);
    }

    /* renamed from: h */
    private void m13621h() {
        try {
            List query = NotifyDao.m11495b().mo24212d(GBApplication.instance()).where().mo33357eq("read", false).query();
            if (query != null && query.size() > 0) {
                this.f12487r = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    private void m13623i() {
        if (!CacheAuth.getInstance().isPortal()) {
            MultiCheckNetWorkUtils.m13944b(GBApplication.instance(), new CallBack() {
                /* class com.gbcom.gwifi.functions.template.fragment.OfficeFragment.C33133 */

                @Override // com.gbcom.gwifi.base.p234c.CallBack
                /* renamed from: a */
                public void mo24437a(Object obj) {
                    OfficeFragment.this.f12465S = System.currentTimeMillis();
                    OfficeFragment.this.mo27328f();
                }
            });
        } else if (!TextUtils.isEmpty(CacheAuth.getInstance().getPortalHost())) {
            this.f12465S = System.currentTimeMillis();
            mo27328f();
        } else {
            Log.m14403e(f12441b, "invalid data: portal but no portal");
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.f13238bM);
        intentFilter.addAction(Constant.f13239bN);
        GBApplication.instance().registerReceiver(this.f12469W, intentFilter);
        this.f12484o = new C3322c();
        GBApplication.instance().registerReceiver(this.f12484o, new IntentFilter(Constant.f13231bF));
        GBApplication.instance().registerReceiver(this.f12466T, new IntentFilter(Constant.f13286cH));
        GBApplication.instance().registerReceiver(this.f12467U, new IntentFilter(Constant.f13287cI));
        this.f12485p = new C3320a();
        GBApplication.instance().registerReceiver(this.f12485p, new IntentFilter(Constant.f13233bH));
        if (!this.f12487r) {
            this.f12454H.setImageResource(C2425R.C2426drawable.user_message_red);
            m13625j();
        }
        m13627k();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: j */
    private void m13625j() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setUnReadMsgState();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.template.fragment.h$b */
    /* compiled from: OfficeFragment */
    public class C3321b extends BaseAdapter {
        C3321b() {
        }

        public int getCount() {
            return OfficeFragment.this.f12486q.size() + 1;
        }

        public Object getItem(int i) {
            if (i != 0) {
                return OfficeFragment.this.f12486q.get(i);
            }
            return "";
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (i == 0) {
                View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.fragment_office_item, viewGroup, false);
                TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.tv_assert);
                textView.setVisibility(0);
                String balance = CacheAccount.getInstance().getBalance();
                textView.setText("余额 " + balance + ",旺豆 " + CacheAccount.getInstance().getTotalScore());
                ((TextView) inflate.findViewById(C2425R.C2427id.office_item_text)).setText("我的资产");
                ((ImageView) inflate.findViewById(C2425R.C2427id.office_item_img)).setImageResource(C2425R.C2426drawable.myassets);
                return inflate;
            }
            HashMap hashMap = (HashMap) OfficeFragment.this.f12486q.get(i - 1);
            View inflate2 = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.fragment_office_item, viewGroup, false);
            String str = (String) hashMap.get("image_url");
            String str2 = (String) hashMap.get("wap_url");
            Integer num = (Integer) hashMap.get("need_internet");
            String str3 = (String) hashMap.get("title");
            Integer num2 = (Integer) hashMap.get("has_submenu");
            Integer num3 = (Integer) hashMap.get("has_title_bar");
            ((TextView) inflate2.findViewById(C2425R.C2427id.office_item_text)).setText(str3);
            ImageTools.m14149a(str, (ImageView) inflate2.findViewById(C2425R.C2427id.office_item_img), GBApplication.instance().options);
            if (str2 != null) {
                inflate2.setTag(C2425R.C2427id.tag_first, str2);
            } else {
                inflate2.setTag(C2425R.C2427id.tag_first, "");
            }
            if (str3 != null) {
                inflate2.setTag(C2425R.C2427id.tag_second, str3);
            } else {
                inflate2.setTag(C2425R.C2427id.tag_second, "");
            }
            if (num != null) {
                inflate2.setTag(C2425R.C2427id.tag_three, num);
            } else {
                inflate2.setTag(C2425R.C2427id.tag_three, 1);
            }
            if (num2 != null) {
                inflate2.setTag(C2425R.C2427id.tag_four, num2);
            } else {
                inflate2.setTag(C2425R.C2427id.tag_four, 0);
            }
            if (num3 != null) {
                inflate2.setTag(C2425R.C2427id.tag_five, num3);
            } else {
                inflate2.setTag(C2425R.C2427id.tag_five, 0);
            }
            return inflate2;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0028, code lost:
        if (com.gbcom.gwifi.util.C3467s.m14513e(r0) == false) goto L_0x002a;
     */
    /* renamed from: k */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m13627k() {
        /*
            r4 = this;
            com.gbcom.gwifi.util.cache.CacheAccount r0 = com.gbcom.gwifi.util.cache.CacheAccount.getInstance()
            java.lang.String r1 = r0.getAvatarUrl()
            com.gbcom.gwifi.util.b r0 = com.gbcom.gwifi.util.Config.m14094a()
            boolean r0 = r0.mo27812o()
            if (r0 == 0) goto L_0x005d
            com.gbcom.gwifi.util.b r0 = com.gbcom.gwifi.util.Config.m14094a()
            boolean r0 = r0.mo27811n()
            if (r0 == 0) goto L_0x005d
            com.gbcom.gwifi.util.cache.CacheAccount r0 = com.gbcom.gwifi.util.cache.CacheAccount.getInstance()
            java.lang.String r0 = r0.getCircleAvatarUrl()
            boolean r2 = com.gbcom.gwifi.util.C3467s.m14513e(r0)
            if (r2 != 0) goto L_0x005d
        L_0x002a:
            android.widget.ImageView r1 = r4.f12473d
            com.gbcom.gwifi.base.app.GBApplication r2 = com.gbcom.gwifi.base.app.GBApplication.instance()
            com.nostra13.universalimageloader.core.DisplayImageOptions r2 = r2.head_options
            com.gbcom.gwifi.functions.template.fragment.h$7 r3 = new com.gbcom.gwifi.functions.template.fragment.h$7
            r3.<init>()
            com.gbcom.gwifi.util.p257b.ImageTools.m14150a(r0, r1, r2, r3)
            r4.mo27323a()
            r4.m13629l()
            com.gbcom.gwifi.util.cache.CacheApp r0 = com.gbcom.gwifi.util.cache.CacheApp.getInstance()
            java.lang.String r0 = r0.getShareUrl()
            boolean r0 = com.gbcom.gwifi.util.C3467s.m14513e(r0)
            if (r0 != 0) goto L_0x0055
            android.widget.RelativeLayout r0 = r4.f12452F
            r1 = 0
            r0.setVisibility(r1)
        L_0x0054:
            return
        L_0x0055:
            android.widget.RelativeLayout r0 = r4.f12452F
            r1 = 8
            r0.setVisibility(r1)
            goto L_0x0054
        L_0x005d:
            r0 = r1
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.functions.template.fragment.OfficeFragment.m13627k():void");
    }

    /* renamed from: l */
    private void m13629l() {
    }

    /* renamed from: a */
    public void mo27323a() {
        if (this.f12474e != null) {
            String loginPhone = CacheAccount.getInstance().getLoginPhone();
            if (TextUtils.isEmpty(loginPhone)) {
                this.f12474e.setVisibility(8);
                return;
            }
            if (loginPhone.length() == 11) {
                this.f12474e.setText((loginPhone.substring(0, 3) + "****") + loginPhone.substring(7));
            } else {
                this.f12474e.setText(loginPhone);
            }
            this.f12474e.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.template.fragment.h$c */
    /* compiled from: OfficeFragment */
    public class C3322c extends BroadcastReceiver {
        private C3322c() {
        }

        public void onReceive(Context context, Intent intent) {
            m13645a(intent);
        }

        /* renamed from: a */
        private void m13645a(Intent intent) {
            if (intent != null && intent.getAction().equals(Constant.f13231bF)) {
                OfficeFragment.this.m13627k();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13604a(String str) {
        HashMap<String, Object> m = ResourceParse.m14462m(str.getBytes());
        if (m == null) {
            ArrayList<HashMap<String, Object>> officeInfo = CacheApp.getInstance().getOfficeInfo();
            if (officeInfo != null) {
                if (officeInfo != null && officeInfo.size() > 2 && ((String) officeInfo.get(0).get("title")).equals("WiFi充值") && ((String) officeInfo.get(1).get("title")).equals("账单查询")) {
                    this.f12461O = (String) officeInfo.get(0).get("wap_url");
                    this.f12462P = (String) officeInfo.get(1).get("wap_url");
                    Iterator<HashMap<String, Object>> it = officeInfo.iterator();
                    while (it.hasNext()) {
                        String str2 = (String) it.next().get("title");
                        if (str2.equals("WiFi充值") || str2.equals("账单查询")) {
                            it.remove();
                        }
                    }
                }
                this.f12486q = officeInfo;
                this.f12478i.notifyDataSetChanged();
                mo27326d();
                return;
            }
            mo27325c();
        } else if (ResultCode.m14475a((Integer) m.get(AbstractC5718b.JSON_ERRORCODE))) {
            HashMap hashMap = (HashMap) m.get("data");
            ArrayList<HashMap<String, Object>> arrayList = (ArrayList) hashMap.get("modules");
            CacheApp.getInstance().setOfficeInfo(arrayList);
            if (arrayList != null && arrayList.size() > 2 && ((String) arrayList.get(0).get("title")).equals("WiFi充值") && ((String) arrayList.get(1).get("title")).equals("账单查询")) {
                this.f12461O = (String) arrayList.get(0).get("wap_url");
                this.f12462P = (String) arrayList.get(1).get("wap_url");
                Iterator<HashMap<String, Object>> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    String str3 = (String) it2.next().get("title");
                    if (str3.equals("WiFi充值") || str3.equals("账单查询")) {
                        it2.remove();
                    }
                }
            }
            this.f12486q = arrayList;
            if (hashMap.containsKey("extend_infos")) {
                String str4 = (String) ((HashMap) hashMap.get("extend_infos")).get("share_url");
                if (!C3467s.m14513e("share_url")) {
                    CacheApp.getInstance().setShareUrl(str4);
                } else {
                    CacheApp.getInstance().setShareUrl("");
                }
            }
            this.f12478i.notifyDataSetChanged();
            mo27326d();
        } else {
            mo27325c();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m13608b(String str) {
        if (GBGlobalConfig.m10510c().mo24406d(str)) {
            m13603a(Integer.valueOf(CacheAccount.getInstance().getUserLevel()));
            GBApplication.instance().sendBroadcast(new Intent(Constant.f13231bF));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m13611c(String str) {
        List<HashMap<String, Object>> adEventList;
        HashMap<String, Object> q = ResourceParse.m14466q(str.getBytes());
        this.f12455I.setEnabled(true);
        if (q != null) {
            if (!ResultCode.m14475a((Integer) q.get(AbstractC5718b.JSON_ERRORCODE))) {
                GBActivity.showMessageToast((String) q.get("resultMsg"));
            } else if (1 == ((Integer) ((HashMap) q.get("data")).get("point_change_category")).intValue() && (adEventList = CacheAd.getInstance().getAdEventList()) != null) {
                for (HashMap<String, Object> hashMap : adEventList) {
                    if (hashMap.containsKey("state") && 1 == ((Integer) hashMap.get("state")).intValue()) {
                        String str2 = (String) hashMap.get("userLevelList");
                        Integer num = (Integer) hashMap.get("eventType");
                        String str3 = (String) hashMap.get("url");
                        CacheAccount.getInstance().getUserLevel();
                        if (!C3467s.m14513e(str3)) {
                            GBActivity.openOfficeBackWebView(str3, "");
                            return;
                        } else {
                            GBActivity.showMessageToast("当前版本暂不支持该功能...");
                            return;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m13603a(Integer num) {
        switch (num.intValue()) {
            case 0:
                this.f12464R.setImageResource(C2425R.C2426drawable.user_level_small_free);
                return;
            case 1:
                this.f12464R.setImageResource(C2425R.C2426drawable.user_level_small_normal);
                return;
            case 2:
                this.f12464R.setImageResource(C2425R.C2426drawable.user_level_small_deal);
                return;
            case 3:
                this.f12464R.setImageResource(C2425R.C2426drawable.user_level_small_vip);
                return;
            case 4:
                this.f12464R.setImageResource(C2425R.C2426drawable.user_level_small_deal);
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    public void mo27324b() {
        this.f12456J.mo28307i();
        this.f12456J.mo28313o();
        this.f12456J.mo28315q();
        if (this.f12456J.mo28310l()) {
            this.f12456J.mo28309k();
        }
        this.f12456J.mo28297a();
        this.f12456J.mo28302d();
        this.f12456J.mo28304f();
        this.f12456J.mo28300b();
    }

    /* renamed from: c */
    public void mo27325c() {
        this.f12448B.setVisibility(8);
        this.f12456J.setVisibility(0);
        this.f12456J.mo28303e();
        this.f12456J.mo28301c();
        this.f12456J.mo28305g();
        this.f12456J.mo28306h();
        this.f12456J.mo28308j();
        this.f12456J.mo28312n();
        this.f12456J.mo28314p();
    }

    /* renamed from: d */
    public void mo27326d() {
        this.f12448B.setVisibility(0);
        this.f12456J.setVisibility(8);
        this.f12456J.mo28301c();
    }

    public void onClick(View view) {
        Intent intent;
        Intent intent2;
        switch (view.getId()) {
            case C2425R.C2427id.user_head /*{ENCODED_INT: 2131755443}*/:
                if (CacheAccount.getInstance().getLoginPhone().equals("")) {
                    GBActivity.showMessageToast("对不起，请先注册会员");
                    return;
                }
                if (!Config.m14094a().mo27812o() || !Config.m14094a().mo27811n()) {
                    intent2 = new Intent(GBApplication.instance(), MyProfileActivity.class);
                } else {
                    intent2 = new Intent(GBApplication.instance(), CircleUserDetailActivity.class);
                    intent2.putExtra("userName", "me");
                }
                startActivity(intent2);
                return;
            case C2425R.C2427id.user_set_linear /*{ENCODED_INT: 2131755485}*/:
                startActivity(new Intent(GBApplication.instance(), SystemSetActivity.class));
                return;
            case C2425R.C2427id.message_layout /*{ENCODED_INT: 2131755487}*/:
                UmengCount.queryNotifyClick(GBApplication.instance());
                startActivity(new Intent(GBApplication.instance(), NotifyListActivity.class));
                return;
            case C2425R.C2427id.user_data /*{ENCODED_INT: 2131755491}*/:
                if (CacheAccount.getInstance().getLoginPhone().equals("")) {
                    GBActivity.showMessageToast("对不起，请先注册会员");
                    return;
                }
                if (!Config.m14094a().mo27812o() || !Config.m14094a().mo27811n()) {
                    intent = new Intent(GBApplication.instance(), MyProfileActivity.class);
                } else {
                    intent = new Intent(GBApplication.instance(), CircleUserDetailActivity.class);
                    intent.putExtra("userName", "me");
                }
                startActivity(intent);
                return;
            case C2425R.C2427id.sign_layout /*{ENCODED_INT: 2131755492}*/:
                m13631m();
                return;
            case C2425R.C2427id.recharge_layout /*{ENCODED_INT: 2131755493}*/:
                if (!C3467s.m14513e(this.f12461O)) {
                    GBActivity.openOfficeBackWebView(this.f12461O, "");
                    return;
                } else {
                    GBActivity.showMessageToast("暂不支持此项业务!");
                    return;
                }
            case C2425R.C2427id.inquiry_layout /*{ENCODED_INT: 2131755494}*/:
                if (!C3467s.m14513e(this.f12462P)) {
                    GBActivity.openOfficeBackWebView(this.f12462P, "");
                    return;
                } else {
                    GBActivity.showMessageToast("暂不支持此项业务!");
                    return;
                }
            case C2425R.C2427id.office_item_rl /*{ENCODED_INT: 2131755495}*/:
                startActivity(new Intent(GBApplication.instance(), UserLevelActivity.class));
                return;
            case C2425R.C2427id.office_share /*{ENCODED_INT: 2131755497}*/:
                String shareUrl = CacheApp.getInstance().getShareUrl();
                if (!C3467s.m14513e(shareUrl)) {
                    GBActivity.openOfficeBackWebView(shareUrl, "");
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: m */
    private void m13631m() {
        UmengCount.queryLogin(GBApplication.instance());
        List<HashMap<String, Object>> adEventList = CacheAd.getInstance().getAdEventList();
        if (adEventList != null) {
            for (HashMap<String, Object> hashMap : adEventList) {
                if (hashMap.containsKey("state") && 1 == ((Integer) hashMap.get("state")).intValue()) {
                    Integer num = (Integer) hashMap.get("eventType");
                    if (((String) hashMap.get("userLevelList")).contains(CacheAccount.getInstance().getUserLevel() + "") && num != null) {
                        if (num.intValue() == 5) {
                            String str = (String) hashMap.get("url");
                            int i = 0;
                            if (hashMap.containsKey("source_type") && hashMap.get("source_type") != null) {
                                i = ((Integer) hashMap.get("source_type")).intValue();
                            }
                            if (i != 2) {
                                m13605a(hashMap);
                            } else {
                                m13614d(str);
                                return;
                            }
                        }
                        if (num.intValue() == 2) {
                            m13614d((String) hashMap.get("url"));
                            return;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: d */
    private void m13614d(String str) {
        long lastSignTime = CacheAccount.getInstance().getLastSignTime();
        if (lastSignTime == 0) {
            if (!C3467s.m14513e(str)) {
                CacheAccount.getInstance().setLastSignTime(System.currentTimeMillis());
                GBActivity.openSignBackWebView(str, "", true);
                return;
            }
            GBActivity.showMessageToast("当前版本暂不支持该功能...");
        } else if ((System.currentTimeMillis() - lastSignTime) / 86400000 >= 1) {
            if (!C3467s.m14513e(str)) {
                CacheAccount.getInstance().setLastSignTime(System.currentTimeMillis());
                GBActivity.openSignBackWebView(str, "", true);
                return;
            }
            GBActivity.showMessageToast("当前版本暂不支持该功能...");
        } else if (!C3467s.m14513e(str)) {
            GBActivity.openOfficeBackWebView(str, "");
        } else {
            GBActivity.showMessageToast("稍后上架，敬请期待...");
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && i == 0) {
            String string = intent.getExtras().getString("result");
            if (!C3467s.m14513e(string)) {
                GBActivity.openOfficeBackWebView(string, "");
            }
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        GBApplication.instance().unregisterReceiver(this.f12484o);
        GBApplication.instance().unregisterReceiver(this.f12469W);
        GBApplication.instance().unregisterReceiver(this.f12466T);
        GBApplication.instance().unregisterReceiver(this.f12467U);
        GBApplication.instance().unregisterReceiver(this.f12485p);
    }

    /* renamed from: e */
    public void mo27327e() {
        this.f12482m = HttpUtil.m14290b(GBApplication.instance(), this.f12472a, f12442c);
    }

    /* renamed from: f */
    public void mo27328f() {
        this.f12481l = HttpUtil.m14315e(getActivity(), this.f12472a, "");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: n */
    private boolean m13633n() {
        if (System.currentTimeMillis() - this.f12490y < 3000) {
            return false;
        }
        this.f12490y = System.currentTimeMillis();
        HttpUtil.m14287a(f12442c);
        if (System.currentTimeMillis() - this.f12465S >= 3000) {
            mo27327e();
            mo27328f();
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: o */
    private void m13635o() {
        if (!m13633n()) {
            this.f12479j.mo28398a("更新于:" + this.f12447A.format(new Date(System.currentTimeMillis())));
        } else {
            GBGlobalConfig.m10510c().mo24414n();
        }
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.f12451E.start();
        GiwifiMobclickAgent.onPageStart("营业厅界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        this.f12451E.stop();
        GiwifiMobclickAgent.onPageEnd("营业厅界面");
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.template.fragment.h$a */
    /* compiled from: OfficeFragment */
    public class C3320a extends BroadcastReceiver {
        private C3320a() {
        }

        public void onReceive(Context context, Intent intent) {
            HttpUtil.m14287a(OfficeFragment.f12442c);
            OfficeFragment.this.mo27327e();
        }
    }

    /* renamed from: a */
    private void m13605a(HashMap<String, Object> hashMap) {
        GBElementManager.m10471a().mo24383a(hashMap, (Activity) null, this);
    }
}
