package com.gbcom.gwifi.functions.revenue;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.p009v4.view.PagerAdapter;
import android.support.p009v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.product.AppActivity;
import com.gbcom.gwifi.functions.product.AppDownloadActivity;
import com.gbcom.gwifi.functions.product.AppSpecialActivity;
import com.gbcom.gwifi.functions.product.BannerAppListActivity;
import com.gbcom.gwifi.functions.product.BannerGameListActivity;
import com.gbcom.gwifi.functions.product.BannerVideoListActivity;
import com.gbcom.gwifi.functions.product.GameActivity;
import com.gbcom.gwifi.functions.product.GameSpecialActivity;
import com.gbcom.gwifi.functions.product.domain.Banner;
import com.gbcom.gwifi.functions.revenue.domain.NativeInfo;
import com.gbcom.gwifi.functions.wifi.p253a.WifiState;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.ResourceParse;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.JazzyViewPager;
import com.gbcom.gwifi.widget.LoadingView;
import com.gbcom.gwifi.widget.NoScrollListView;
import com.gbcom.gwifi.widget.OutlineContainer;
import com.gbcom.gwifi.widget.PullToRefreshView;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import p041c.Request;

public class ScoreActivity extends GBActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    /* renamed from: t */
    private static final int f11480t = 1;

    /* renamed from: u */
    private static final int f11481u = 5000;

    /* renamed from: A */
    private Banner f11482A;

    /* renamed from: B */
    private Banner f11483B;

    /* renamed from: C */
    private ArrayList<NativeInfo> f11484C;

    /* renamed from: D */
    private PullToRefreshView f11485D;

    /* renamed from: E */
    private ScrollView f11486E;

    /* renamed from: F */
    private SimpleDateFormat f11487F;

    /* renamed from: G */
    private LoadingView f11488G;

    /* renamed from: H */
    private ImageView f11489H;

    /* renamed from: I */
    private ArrayList<HashMap<String, Object>> f11490I;

    /* renamed from: J */
    private int f11491J = 0;

    /* renamed from: K */
    private PagerAdapter f11492K = new PagerAdapter() {
        /* class com.gbcom.gwifi.functions.revenue.ScoreActivity.C31019 */

        @Override // android.support.p009v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view;
            if (ScoreActivity.this.f11502f.size() == 0) {
                view = new ImageView(GBApplication.instance());
            } else {
                view = (View) ScoreActivity.this.f11501e.get(i % ScoreActivity.this.f11502f.size());
            }
            if (view.getParent() == null) {
                int a = DensityUtil.m14127a(GBApplication.instance());
                ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
                view.setTag(Integer.valueOf(i));
                view.setOnClickListener(ScoreActivity.this);
                viewGroup.addView(view, -1, a);
            }
            ScoreActivity.this.f11500d.mo28280a(view, i);
            return view;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(ScoreActivity.this.f11500d.mo28286b(i));
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public int getCount() {
            if (ScoreActivity.this.f11502f.size() == 0) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view instanceof OutlineContainer ? ((OutlineContainer) view).getChildAt(0) == obj : view == obj;
        }
    };

    /* renamed from: L */
    private ViewPager.OnPageChangeListener f11493L = new ViewPager.OnPageChangeListener() {
        /* class com.gbcom.gwifi.functions.revenue.ScoreActivity.C309210 */

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            int size = i % ScoreActivity.this.f11502f.size();
            ScoreActivity.this.f11513q = i;
            if (!(ScoreActivity.this.f11503g.getChildAt(ScoreActivity.this.f11491J) == null || ScoreActivity.this.f11503g.getChildAt(size) == null)) {
                for (int i2 = 0; i2 < ScoreActivity.this.f11503g.getChildCount(); i2++) {
                    if (i2 != size) {
                        ScoreActivity.this.f11503g.getChildAt(i2).setBackgroundResource(C2425R.C2426drawable.gi_dot_normal);
                    } else {
                        ScoreActivity.this.f11503g.getChildAt(i2).setBackgroundResource(C2425R.C2426drawable.gi_dot_focused);
                    }
                }
                ScoreActivity.this.f11504h.setText((CharSequence) ScoreActivity.this.f11502f.get(size));
                ScoreActivity.this.f11491J = size;
            }
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }
    };

    /* renamed from: M */
    private Handler f11494M = new Handler() {
        /* class com.gbcom.gwifi.functions.revenue.ScoreActivity.HandlerC309311 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    if (!ScoreActivity.this.f11515s) {
                        ScoreActivity.this.m12679c();
                    }
                    ScoreActivity.this.m12663a((ScoreActivity) 5000);
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: N */
    private OkRequestHandler<String> f11495N = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.revenue.ScoreActivity.C30942 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            ScoreActivity.this.startHomeDrawable();
            if (ScoreActivity.this.f11488G.getVisibility() == 8) {
                ScoreActivity.this.showProgressDialog("正在加载...");
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            ScoreActivity.this.dismissProgressDialog();
            ScoreActivity.this.f11485D.mo28398a("更新于:" + ScoreActivity.this.f11487F.format(new Date(System.currentTimeMillis())));
            ScoreActivity.this.errHomeDrawable();
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            HashMap<String, Object> v = ResourceParse.m14471v(str.getBytes());
            if (ResultCode.m14475a((Integer) v.get(AbstractC5718b.JSON_ERRORCODE))) {
                ScoreActivity.this.dismissProgressDialog();
                ScoreActivity.this.f11485D.mo28398a("更新于:" + ScoreActivity.this.f11487F.format(new Date(System.currentTimeMillis())));
                ScoreActivity.this.stopHomeDrawable();
                HashMap hashMap = (HashMap) v.get("data");
                if (hashMap.containsKey("totalScore")) {
                    ScoreActivity.this.f11519y = ((Integer) hashMap.get("totalScore")).intValue();
                    ScoreActivity.this.f11506j.setText(ScoreActivity.this.f11519y + "");
                }
                if (hashMap.containsKey("bannerList")) {
                    ScoreActivity.this.m12684d((ScoreActivity) ((ArrayList) hashMap.get("bannerList")));
                }
                if (hashMap.containsKey("functionList")) {
                    ScoreActivity.this.m12681c((ScoreActivity) ((ArrayList) hashMap.get("functionList")));
                }
                if (hashMap.containsKey("scoreList")) {
                    ScoreActivity.this.m12676b((ScoreActivity) ((ArrayList) hashMap.get("scoreList")));
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: O */
    private LoadingView.AbstractC3488a f11496O = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.revenue.ScoreActivity.C30953 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            ScoreActivity.this.m12662a();
        }
    };

    /* renamed from: a */
    BaseAdapter f11497a = new BaseAdapter() {
        /* class com.gbcom.gwifi.functions.revenue.ScoreActivity.C30986 */

        public int getCount() {
            return ScoreActivity.this.f11484C.size();
        }

        public Object getItem(int i) {
            return ScoreActivity.this.f11484C.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.score_wall_item, viewGroup, false);
            NativeInfo nativeInfo = (NativeInfo) ScoreActivity.this.f11484C.get(i);
            ImageTools.m14149a(nativeInfo.getIconUrl(), (ImageView) inflate.findViewById(C2425R.C2427id.score_item_icon), GBApplication.instance().options4);
            ((TextView) inflate.findViewById(C2425R.C2427id.score_item_title)).setText(nativeInfo.getTitle());
            ((TextView) inflate.findViewById(C2425R.C2427id.score_item_points)).setText(nativeInfo.getSubTitle());
            return inflate;
        }
    };

    /* renamed from: b */
    private RelativeLayout f11498b;

    /* renamed from: c */
    private TextView f11499c;

    /* renamed from: d */
    private JazzyViewPager f11500d;

    /* renamed from: e */
    private List<View> f11501e;

    /* renamed from: f */
    private List<String> f11502f;

    /* renamed from: g */
    private LinearLayout f11503g;

    /* renamed from: h */
    private TextView f11504h;

    /* renamed from: i */
    private RelativeLayout f11505i;

    /* renamed from: j */
    private TextView f11506j;

    /* renamed from: k */
    private LinearLayout f11507k;

    /* renamed from: l */
    private TextView f11508l;

    /* renamed from: m */
    private ImageView f11509m;

    /* renamed from: n */
    private LinearLayout f11510n;

    /* renamed from: o */
    private TextView f11511o;

    /* renamed from: p */
    private ImageView f11512p;

    /* renamed from: q */
    private int f11513q = 0;

    /* renamed from: r */
    private boolean f11514r = false;

    /* renamed from: s */
    private boolean f11515s = false;

    /* renamed from: v */
    private NoScrollListView f11516v;

    /* renamed from: w */
    private View f11517w;

    /* renamed from: x */
    private Request f11518x;

    /* renamed from: y */
    private int f11519y;

    /* renamed from: z */
    private ArrayList<Banner> f11520z = new ArrayList<>();

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "旺豆界面", C2425R.layout.score_activity, true);
        m12674b();
        m12662a();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12662a() {
        this.f11518x = HttpUtil.m14316e(this.f11495N, "");
        this.f11491J = 0;
    }

    /* renamed from: b */
    private void m12674b() {
        this.f11498b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11498b.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.revenue.ScoreActivity.View$OnClickListenerC30911 */

            public void onClick(View view) {
                ScoreActivity.this.finish();
            }
        });
        this.f11499c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11499c.setText("赚旺豆");
        ((TextView) findViewById(C2425R.C2427id.title_edit_tv)).setVisibility(8);
        this.f11517w = getLayoutInflater().inflate(C2425R.layout.score_activity_include, (ViewGroup) null);
        this.f11503g = (LinearLayout) this.f11517w.findViewById(C2425R.C2427id.dot_ll);
        this.f11505i = (RelativeLayout) this.f11517w.findViewById(C2425R.C2427id.image_news);
        this.f11504h = (TextView) this.f11517w.findViewById(C2425R.C2427id.score_news_title);
        this.f11506j = (TextView) this.f11517w.findViewById(C2425R.C2427id.points);
        this.f11489H = (ImageView) this.f11517w.findViewById(C2425R.C2427id.iv_one);
        this.f11508l = (TextView) this.f11517w.findViewById(C2425R.C2427id.title_lottery);
        this.f11509m = (ImageView) this.f11517w.findViewById(C2425R.C2427id.score_lottery_iv);
        this.f11511o = (TextView) this.f11517w.findViewById(C2425R.C2427id.title_change);
        this.f11512p = (ImageView) this.f11517w.findViewById(C2425R.C2427id.score_change_iv);
        this.f11507k = (LinearLayout) this.f11517w.findViewById(C2425R.C2427id.lottery_layout);
        this.f11510n = (LinearLayout) this.f11517w.findViewById(C2425R.C2427id.score_change_layout);
        ((LinearLayout) this.f11517w.findViewById(C2425R.C2427id.ll_balance)).setOnClickListener(this);
        this.f11507k.setOnClickListener(this);
        this.f11510n.setOnClickListener(this);
        this.f11516v = (NoScrollListView) findViewById(C2425R.C2427id.score_lv);
        this.f11516v.addHeaderView(this.f11517w);
        this.f11484C = new ArrayList<>();
        this.f11516v.setAdapter((ListAdapter) this.f11497a);
        this.f11516v.setOnItemClickListener(this);
        this.f11486E = (ScrollView) findViewById(C2425R.C2427id.home_scroll);
        this.f11487F = new SimpleDateFormat("MM-dd HH:mm");
        this.f11485D = (PullToRefreshView) findViewById(C2425R.C2427id.score_refresh_view);
        this.f11485D.mo28397a(new PullToRefreshView.AbstractC3494b() {
            /* class com.gbcom.gwifi.functions.revenue.ScoreActivity.C30964 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
            public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
                ScoreActivity.this.m12662a();
            }
        });
        this.f11485D.mo28396a(new PullToRefreshView.AbstractC3493a() {
            /* class com.gbcom.gwifi.functions.revenue.ScoreActivity.C30975 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3493a
            public void onFooterRefresh(PullToRefreshView pullToRefreshView) {
                ScoreActivity.this.f11485D.mo28403c();
            }
        });
        this.f11488G = (LoadingView) findViewById(C2425R.C2427id.loading_view);
        this.f11488G.mo28298a(this.f11496O);
        ((TextView) findViewById(C2425R.C2427id.tv_middle)).setText("做任务获取旺豆");
        ((TextView) findViewById(C2425R.C2427id.tv_my_balance)).setText("我的旺豆");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.ll_balance /*{ENCODED_INT: 2131755611}*/:
                String k = HttpUtil.m14335k();
                if (!C3467s.m14513e(k)) {
                    GBActivity.openBackWebView(k, "");
                    return;
                } else {
                    GBActivity.showMessageToast("链接无效...");
                    return;
                }
            case C2425R.C2427id.lottery_layout /*{ENCODED_INT: 2131755941}*/:
                if (this.f11482A != null) {
                    m12664a(this.f11482A);
                    return;
                }
                return;
            case C2425R.C2427id.score_change_layout /*{ENCODED_INT: 2131755944}*/:
                if (this.f11483B != null) {
                    m12664a(this.f11483B);
                    return;
                }
                return;
            default:
                m12664a(this.f11520z.get(((Integer) view.getTag()).intValue() % this.f11502f.size()));
                return;
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i != 0) {
            NativeInfo nativeInfo = this.f11484C.get(i - 1);
            Intent intent = new Intent();
            intent.putExtra("title", nativeInfo.getTitle());
            switch (nativeInfo.getType()) {
                case 0:
                    intent.setClass(this, GameSpecialActivity.class);
                    startActivity(intent);
                    return;
                case 1:
                    showMessageToast("即将上线，敬请期待!");
                    return;
                case 2:
                    showMessageToast("即将上线，敬请期待!");
                    return;
                case 3:
                    showMessageToast("即将上线，敬请期待!");
                    return;
                case 4:
                    startActivity(new Intent(this, AppSpecialActivity.class));
                    return;
                case 5:
                    if (!CacheWiFi.getInstance().isInnerWifi()) {
                        startActivity(new Intent(GBApplication.instance(), AppDownloadActivity.class));
                        return;
                    } else if (CacheWiFi.getInstance().getCurrentState() != WifiState.SUCCESS) {
                        GBActivity.showMessageToast("请先认证!");
                        return;
                    } else {
                        startActivity(new Intent(GBApplication.instance(), AppDownloadActivity.class));
                        return;
                    }
                case 6:
                    if (!CacheWiFi.getInstance().isInnerWifi()) {
                        startActivity(new Intent(GBApplication.instance(), ShakeActivity.class));
                        return;
                    } else if (CacheWiFi.getInstance().getCurrentState() != WifiState.SUCCESS) {
                        GBActivity.showMessageToast("请先认证!");
                        return;
                    } else {
                        startActivity(new Intent(GBApplication.instance(), ShakeActivity.class));
                        return;
                    }
                case 7:
                    if (!CacheWiFi.getInstance().isInnerWifi()) {
                        startActivity(new Intent(GBApplication.instance(), DigActivity.class));
                        return;
                    } else if (CacheWiFi.getInstance().getCurrentState() != WifiState.SUCCESS) {
                        GBActivity.showMessageToast("请先认证!");
                        return;
                    } else {
                        startActivity(new Intent(GBApplication.instance(), DigActivity.class));
                        return;
                    }
                case 8:
                    if (!CacheWiFi.getInstance().isInnerWifi()) {
                        startActivity(new Intent(GBApplication.instance(), FindActivity.class));
                        return;
                    } else if (CacheWiFi.getInstance().getCurrentState() != WifiState.SUCCESS) {
                        GBActivity.showMessageToast("请先认证!");
                        return;
                    } else {
                        startActivity(new Intent(GBApplication.instance(), FindActivity.class));
                        return;
                    }
                case 9:
                    String webUrl = nativeInfo.getWebUrl();
                    if (!TextUtils.isEmpty(webUrl)) {
                        GBActivity.openBackWebView(webUrl, nativeInfo.getTitle());
                        return;
                    }
                    return;
                default:
                    showMessageToast("即将上线，敬请期待!");
                    return;
            }
        }
    }

    /* renamed from: a */
    private void m12664a(Banner banner) {
        String wapUrl = banner.getWapUrl();
        String title = banner.getTitle();
        UmengCount.queryEntertainmentBanner(GBApplication.instance(), title);
        if (wapUrl != null && wapUrl.trim().length() > 5) {
            GBActivity.openBackWebView(wapUrl, title);
        } else if (banner.getCatId() == null || banner.getCatId().intValue() == 0) {
            String catType = banner.getCatType();
            int intValue = banner.getProductId().intValue();
            if (catType.equals(Constant.f13158M)) {
                return;
            }
            if (catType.equals(Constant.f13160O)) {
                Intent intent = new Intent(GBApplication.instance(), AppActivity.class);
                intent.putExtra("productId", intValue);
                startActivity(intent);
                return;
            }
            Intent intent2 = new Intent(GBApplication.instance(), GameActivity.class);
            intent2.putExtra("productId", intValue);
            startActivity(intent2);
        } else {
            String catType2 = banner.getCatType();
            int intValue2 = banner.getCatId().intValue();
            if (catType2.equals(Constant.f13158M)) {
                Intent intent3 = new Intent(GBApplication.instance(), BannerVideoListActivity.class);
                intent3.putExtra("catId", intValue2);
                intent3.putExtra("title", title);
                startActivity(intent3);
            } else if (catType2.equals(Constant.f13160O)) {
                Intent intent4 = new Intent(GBApplication.instance(), BannerAppListActivity.class);
                intent4.putExtra("catId", intValue2);
                intent4.putExtra("title", title);
                startActivity(intent4);
            } else {
                Intent intent5 = new Intent(GBApplication.instance(), BannerGameListActivity.class);
                intent5.putExtra("catId", intValue2);
                intent5.putExtra("title", title);
                startActivity(intent5);
            }
        }
    }

    /* renamed from: a */
    private void m12670a(ArrayList<Banner> arrayList) {
        if (arrayList != null) {
            if (this.f11501e == null) {
                this.f11501e = new ArrayList(arrayList.size());
                this.f11502f = new ArrayList(arrayList.size());
            } else {
                this.f11501e.clear();
                this.f11502f.clear();
                this.f11503g.removeAllViews();
            }
            Iterator<Banner> it = arrayList.iterator();
            while (it.hasNext()) {
                Banner next = it.next();
                View inflate = getLayoutInflater().inflate(C2425R.layout.common_dot, (ViewGroup) this.f11503g, false);
                ImageView imageView = new ImageView(this);
                ImageTools.m14149a(next.getImageUrl(), imageView, GBApplication.instance().options2);
                this.f11501e.add(imageView);
                this.f11502f.add(next.getTitle());
                this.f11503g.addView(inflate);
            }
            if (this.f11502f.size() > 0) {
                this.f11505i.setVisibility(0);
                m12668a(JazzyViewPager.EnumC3486b.Standard);
            }
        }
    }

    /* renamed from: a */
    private void m12668a(JazzyViewPager.EnumC3486b bVar) {
        if (this.f11500d == null) {
            this.f11500d = (JazzyViewPager) this.f11517w.findViewById(C2425R.C2427id.jazzy_pager);
            this.f11500d.mo28279a(bVar);
            this.f11500d.setAdapter(this.f11492K);
            this.f11500d.setOnPageChangeListener(this.f11493L);
            this.f11500d.setPageMargin(30);
            this.f11500d.setCurrentItem(this.f11502f.size() * 1000);
            this.f11513q = this.f11502f.size() * 1000;
            this.f11500d.getLayoutParams().height = (DensityUtil.m14127a(GBApplication.instance()) * 3) / 4;
            this.f11500d.setOnTouchListener(new View.OnTouchListener() {
                /* class com.gbcom.gwifi.functions.revenue.ScoreActivity.View$OnTouchListenerC30997 */

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case 0:
                            ScoreActivity.this.f11515s = true;
                            break;
                        case 1:
                            ScoreActivity.this.f11515s = false;
                            break;
                    }
                    return false;
                }
            });
        }
        if (this.f11490I.size() == 1) {
            this.f11500d.setVisibility(8);
            this.f11489H.setVisibility(0);
            this.f11503g.setVisibility(8);
            final String str = (String) this.f11490I.get(0).get("title");
            final String str2 = (String) this.f11490I.get(0).get("wapUrl");
            this.f11504h.setText(str);
            this.f11489H.setScaleType(ImageView.ScaleType.FIT_XY);
            this.f11489H.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.revenue.ScoreActivity.View$OnClickListenerC31008 */

                public void onClick(View view) {
                    GBActivity.openBackWebView(str2, str);
                }
            });
            ImageTools.m14149a((String) this.f11490I.get(0).get("iconUrl"), this.f11489H, GBApplication.instance().options2);
            return;
        }
        this.f11500d.setVisibility(0);
        this.f11489H.setVisibility(8);
        this.f11503g.setVisibility(0);
        this.f11500d.getAdapter().notifyDataSetChanged();
        startAutoScroll();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12663a(long j) {
        this.f11494M.removeMessages(1);
        this.f11494M.sendEmptyMessageDelayed(1, j);
    }

    public void startAutoScroll() {
        this.f11514r = true;
        m12663a(5000);
    }

    public void stopAutoScroll() {
        this.f11514r = false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12679c() {
        if (this.f11514r) {
            this.f11500d.setCurrentItem(this.f11513q + 1, true);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12676b(ArrayList<HashMap<String, Object>> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<HashMap<String, Object>> it = arrayList.iterator();
            while (it.hasNext()) {
                HashMap<String, Object> next = it.next();
                NativeInfo nativeInfo = new NativeInfo();
                nativeInfo.setIconUrl((String) next.get("iconUrl"));
                nativeInfo.setTitle((String) next.get("title"));
                nativeInfo.setSubTitle((String) next.get("subTitle"));
                nativeInfo.setType(((Integer) next.get("type")).intValue());
                nativeInfo.setWebUrl((String) next.get("wap_url"));
                arrayList2.add(nativeInfo);
            }
            this.f11484C.clear();
            this.f11484C.addAll(arrayList2);
            this.f11497a.notifyDataSetChanged();
            arrayList2.clear();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12681c(ArrayList<HashMap<String, Object>> arrayList) {
        if (arrayList != null) {
            if (arrayList.size() == 1) {
                HashMap<String, Object> hashMap = arrayList.get(0);
                this.f11482A = new Banner();
                if (hashMap.containsKey("iconUrl")) {
                    this.f11482A.setImageUrl((String) hashMap.get("iconUrl"));
                }
                if (hashMap.containsKey("title")) {
                    this.f11482A.setTitle((String) hashMap.get("title"));
                }
                if (hashMap.containsKey("sourceType")) {
                    this.f11482A.setType((Integer) hashMap.get("sourceType"));
                }
                if (hashMap.containsKey("productType")) {
                    this.f11482A.setCatType((String) hashMap.get("productType"));
                }
                if (hashMap.containsKey("productId")) {
                    this.f11482A.setProductId((Integer) hashMap.get("productId"));
                }
                if (hashMap.containsKey("wapUrl")) {
                    this.f11482A.setWapUrl((String) hashMap.get("wapUrl"));
                }
            }
            if (arrayList.size() >= 2) {
                HashMap<String, Object> hashMap2 = arrayList.get(0);
                this.f11482A = new Banner();
                if (hashMap2.containsKey("iconUrl")) {
                    this.f11482A.setImageUrl((String) hashMap2.get("iconUrl"));
                }
                if (hashMap2.containsKey("title")) {
                    this.f11482A.setTitle((String) hashMap2.get("title"));
                }
                if (hashMap2.containsKey("sourceType")) {
                    this.f11482A.setType((Integer) hashMap2.get("sourceType"));
                }
                if (hashMap2.containsKey("productType")) {
                    this.f11482A.setCatType((String) hashMap2.get("productType"));
                }
                if (hashMap2.containsKey("productId")) {
                    this.f11482A.setProductId((Integer) hashMap2.get("productId"));
                }
                if (hashMap2.containsKey("wapUrl")) {
                    this.f11482A.setWapUrl((String) hashMap2.get("wapUrl"));
                }
                HashMap<String, Object> hashMap3 = arrayList.get(1);
                this.f11483B = new Banner();
                if (hashMap2.containsKey("iconUrl")) {
                    this.f11483B.setImageUrl((String) hashMap3.get("iconUrl"));
                }
                if (hashMap2.containsKey("title")) {
                    this.f11483B.setTitle((String) hashMap3.get("title"));
                }
                if (hashMap2.containsKey("sourceType")) {
                    this.f11483B.setType((Integer) hashMap3.get("sourceType"));
                }
                if (hashMap2.containsKey("productType")) {
                    this.f11483B.setCatType((String) hashMap3.get("productType"));
                }
                if (hashMap2.containsKey("productId")) {
                    this.f11483B.setProductId((Integer) hashMap3.get("productId"));
                }
                if (hashMap2.containsKey("wapUrl")) {
                    this.f11483B.setWapUrl((String) hashMap3.get("wapUrl"));
                }
            }
            m12683d();
        }
    }

    /* renamed from: d */
    private void m12683d() {
        if (this.f11482A != null) {
            this.f11508l.setText(this.f11482A.getTitle());
            ImageTools.m14149a(this.f11482A.getImageUrl(), this.f11509m, GBApplication.instance().options4);
        }
        if (this.f11483B != null) {
            this.f11511o.setText(this.f11483B.getTitle());
            ImageTools.m14149a(this.f11483B.getImageUrl(), this.f11512p, GBApplication.instance().options4);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m12684d(ArrayList<HashMap<String, Object>> arrayList) {
        this.f11490I = arrayList;
        if (arrayList != null && arrayList.size() != 0) {
            ArrayList arrayList2 = new ArrayList();
            while (arrayList2.size() < 4) {
                Iterator<HashMap<String, Object>> it = arrayList.iterator();
                while (it.hasNext()) {
                    HashMap<String, Object> next = it.next();
                    Banner banner = new Banner();
                    if (next.containsKey("iconUrl")) {
                        banner.setImageUrl((String) next.get("iconUrl"));
                    }
                    if (next.containsKey("title")) {
                        banner.setTitle((String) next.get("title"));
                    }
                    if (next.containsKey("sourceType")) {
                        banner.setType((Integer) next.get("sourceType"));
                    }
                    if (next.containsKey("productType")) {
                        banner.setCatType((String) next.get("productType"));
                    }
                    if (next.containsKey("productId")) {
                        banner.setProductId((Integer) next.get("productId"));
                    }
                    if (next.containsKey("wapUrl")) {
                        banner.setWapUrl((String) next.get("wapUrl"));
                    }
                    arrayList2.add(banner);
                }
            }
            this.f11520z.clear();
            this.f11520z.addAll(arrayList2);
            arrayList2.clear();
            m12670a(this.f11520z);
        }
    }

    public void startHomeDrawable() {
        this.f11488G.mo28307i();
        this.f11488G.mo28313o();
        this.f11488G.mo28315q();
        if (this.f11488G.mo28310l()) {
            this.f11488G.mo28309k();
        }
        this.f11488G.mo28297a();
        this.f11488G.mo28302d();
        this.f11488G.mo28304f();
        this.f11488G.mo28300b();
    }

    public void errHomeDrawable() {
        this.f11486E.setVisibility(8);
        this.f11488G.setVisibility(0);
        this.f11488G.mo28303e();
        this.f11488G.mo28301c();
        this.f11488G.mo28305g();
        this.f11488G.mo28306h();
        this.f11488G.mo28308j();
        this.f11488G.mo28312n();
        this.f11488G.mo28314p();
    }

    public void stopHomeDrawable() {
        this.f11486E.setVisibility(0);
        this.f11488G.setVisibility(8);
        this.f11488G.mo28301c();
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("积分墙界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        this.f11494M.removeCallbacksAndMessages(null);
        if (this.f11500d != null) {
            this.f11500d.removeAllViews();
        }
        super.onDestroy();
    }

    /* renamed from: a */
    private void m12669a(Integer num) {
        switch (num.intValue()) {
        }
    }
}
