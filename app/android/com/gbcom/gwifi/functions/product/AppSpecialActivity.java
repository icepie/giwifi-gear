package com.gbcom.gwifi.functions.product;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.p009v4.view.PagerAdapter;
import android.support.p009v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.download.MyIntents;
import com.gbcom.gwifi.functions.product.domain.App;
import com.gbcom.gwifi.functions.product.domain.Banner;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.p248b.DownloadFileDao;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.ResourceParse;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.StorageUtils;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.JazzyViewPager;
import com.gbcom.gwifi.widget.LoadingView;
import com.gbcom.gwifi.widget.OutlineContainer;
import com.gbcom.gwifi.widget.ProgressWheel;
import com.gbcom.gwifi.widget.XListView;
import com.taobao.accs.common.Constants;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.p456io.File;
import java.p456io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p041c.Request;

public class AppSpecialActivity extends GBActivity implements XListView.AbstractC3496a {
    public static final int DOWN_ANIM = 5;
    public static final int INSTALL_OPEN_ANIM = 6;
    public static final int P2C_ANIM = 7;

    /* renamed from: d */
    private static final String f10102d = "AppSpecialActivity";

    /* renamed from: e */
    private static final Object f10103e = f10102d;

    /* renamed from: m */
    private static final int f10104m = 1;

    /* renamed from: n */
    private static final int f10105n = 5000;

    /* renamed from: A */
    private int f10106A = 0;

    /* renamed from: B */
    private List<Banner> f10107B;

    /* renamed from: C */
    private TextView f10108C;

    /* renamed from: D */
    private RelativeLayout f10109D;

    /* renamed from: E */
    private View f10110E;

    /* renamed from: F */
    private ImageView f10111F;

    /* renamed from: G */
    private String f10112G = ",";

    /* renamed from: H */
    private DowningReceiver f10113H;

    /* renamed from: I */
    private boolean f10114I = false;

    /* renamed from: J */
    private HashMap<String, View> f10115J = new HashMap<>();

    /* renamed from: K */
    private HandlerC2835c f10116K;

    /* renamed from: L */
    private AppInstallReceiver f10117L;

    /* renamed from: M */
    private ResumeDownIconReceiver f10118M;

    /* renamed from: N */
    private TextView f10119N;

    /* renamed from: O */
    private LoadingView f10120O;

    /* renamed from: P */
    private ViewPager.OnPageChangeListener f10121P = new ViewPager.OnPageChangeListener() {
        /* class com.gbcom.gwifi.functions.product.AppSpecialActivity.C28285 */

        /* renamed from: b */
        private int f10156b = 0;

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            int size = i % AppSpecialActivity.this.f10147y.size();
            AppSpecialActivity.this.f10106A = i;
            AppSpecialActivity.this.f10145w.getChildAt(this.f10156b).setBackgroundResource(C2425R.C2426drawable.gi_dot_normal);
            AppSpecialActivity.this.f10145w.getChildAt(size).setBackgroundResource(C2425R.C2426drawable.gi_dot_focused);
            AppSpecialActivity.this.f10108C.setText((CharSequence) AppSpecialActivity.this.f10147y.get(size));
            this.f10156b = size;
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }
    };

    /* renamed from: Q */
    private PagerAdapter f10122Q = new PagerAdapter() {
        /* class com.gbcom.gwifi.functions.product.AppSpecialActivity.C28296 */

        @Override // android.support.p009v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view;
            if (AppSpecialActivity.this.f10147y.size() == 0) {
                view = AppSpecialActivity.this.f10111F;
            } else {
                view = (View) AppSpecialActivity.this.f10146x.get(i % AppSpecialActivity.this.f10147y.size());
            }
            if (view.getParent() == null) {
                int a = DensityUtil.m14127a(GBApplication.instance());
                ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
                view.setTag(Integer.valueOf(i));
                view.setOnClickListener(AppSpecialActivity.this.f10124S);
                viewGroup.addView(view, -1, a);
            }
            AppSpecialActivity.this.f10148z.mo28280a(view, i);
            return view;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(AppSpecialActivity.this.f10148z.mo28286b(i));
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public int getCount() {
            if (AppSpecialActivity.this.f10147y.size() == 0) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view instanceof OutlineContainer ? ((OutlineContainer) view).getChildAt(0) == obj : view == obj;
        }
    };

    /* renamed from: R */
    private OkRequestHandler<String> f10123R = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.AppSpecialActivity.C28307 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            if (AppSpecialActivity.this.f10140r == abVar) {
                AppSpecialActivity.this.startAppDrawable();
                if (AppSpecialActivity.this.f10120O.getVisibility() == 8) {
                    AppSpecialActivity.this.showProgressDialog("正在加载...");
                    return;
                }
                return;
            }
            AppSpecialActivity.this.showProgressDialog("正在加载...");
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (!AppSpecialActivity.this.isFinishing()) {
                AppSpecialActivity.this.f10142t = false;
                AppSpecialActivity.this.dismissProgressDialog();
                AppSpecialActivity.this.errAppDrawable();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            int intValue;
            if (!AppSpecialActivity.this.isFinishing()) {
                AppSpecialActivity.this.dismissProgressDialog();
                if (AppSpecialActivity.this.f10140r == abVar) {
                    AppSpecialActivity.this.f10142t = false;
                    AppSpecialActivity.this.f10141s = 1;
                    AppSpecialActivity.this.f10143u.clear();
                    AppSpecialActivity.this.f10144v.notifyDataSetChanged();
                    AppSpecialActivity.this.f10112G = ",";
                    HashMap<String, Object> a = ResourceParse.m14446a(str.getBytes());
                    if (a == null) {
                        AppSpecialActivity.this.errAppDrawable();
                    } else if (ResultCode.m14475a((Integer) a.get(AbstractC5718b.JSON_ERRORCODE))) {
                        AppSpecialActivity.this.f10137o.mo28429c();
                        HashMap hashMap = (HashMap) a.get("data");
                        ArrayList arrayList = (ArrayList) hashMap.get("banners");
                        if (arrayList.isEmpty()) {
                            AppSpecialActivity.this.f10110E.setVisibility(8);
                        } else {
                            AppSpecialActivity.this.f10107B.clear();
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                HashMap hashMap2 = (HashMap) it.next();
                                Banner banner = new Banner();
                                banner.setCatId((Integer) hashMap2.get("catId"));
                                banner.setCatType((String) hashMap2.get("catType"));
                                banner.setImageUrl((String) hashMap2.get("imgUrl"));
                                banner.setTitle((String) hashMap2.get("catTitle"));
                                banner.setWapUrl((String) hashMap2.get("wapUrl"));
                                banner.setType(3);
                                AppSpecialActivity.this.f10107B.add(banner);
                            }
                            AppSpecialActivity.this.f10110E.setVisibility(0);
                            AppSpecialActivity.this.m11639a((AppSpecialActivity) AppSpecialActivity.this.f10107B);
                        }
                        Iterator it2 = ((ArrayList) hashMap.get("products")).iterator();
                        while (it2.hasNext()) {
                            HashMap hashMap3 = (HashMap) it2.next();
                            Integer num = (Integer) hashMap3.get("productId");
                            if (!AppSpecialActivity.this.f10112G.contains("," + ((Object) num) + ",")) {
                                AppSpecialActivity.this.f10112G += ((Object) num) + ",";
                                App app = new App();
                                app.setTitle((String) hashMap3.get("productName"));
                                app.setImageUrl((String) hashMap3.get("imgUrl"));
                                if (((Integer) hashMap3.get("appPointsReward")) == null) {
                                    intValue = 0;
                                } else {
                                    intValue = ((Integer) hashMap3.get("appPointsReward")).intValue();
                                }
                                app.setAppPointsReward(Integer.valueOf(intValue));
                                app.setDownloadCount((Integer) hashMap3.get("downloadCount"));
                                app.setSid((Integer) hashMap3.get("productId"));
                                app.setFileTotalSize(Long.valueOf((long) (((Double) hashMap3.get("fileTotalSize")).doubleValue() * 1024.0d)));
                                app.setSid((Integer) hashMap3.get("productId"));
                                app.setSpeed((Integer) hashMap3.get("isSpeed"));
                                app.setTags(C3467s.m14507a((List<String[]>) ((ArrayList) hashMap3.get("tags"))));
                                app.setPackageName((String) hashMap3.get("appPackage"));
                                Iterator it3 = ((ArrayList) hashMap3.get("downloads")).iterator();
                                while (it3.hasNext()) {
                                    app.setFileUrl((String) ((HashMap) it3.next()).get("fileUrl"));
                                }
                                AppSpecialActivity.this.f10143u.add(app);
                            }
                        }
                        AppSpecialActivity.this.stopAppDrawable();
                        AppSpecialActivity.this.f10144v.notifyDataSetChanged();
                    } else {
                        AppSpecialActivity.this.errAppDrawable();
                    }
                } else if (AppSpecialActivity.this.f10139q == abVar) {
                    AppSpecialActivity.this.f10142t = false;
                    HashMap<String, Object> b = ResourceParse.m14448b(str.getBytes());
                    if (b == null) {
                        AppSpecialActivity.this.f10137o.mo28431d();
                        AppSpecialActivity.this.errAppDrawable();
                    } else if (!ResultCode.m14475a((Integer) b.get(AbstractC5718b.JSON_ERRORCODE))) {
                        AppSpecialActivity.this.errAppDrawable();
                    } else {
                        AppSpecialActivity.this.f10137o.mo28429c();
                        AppSpecialActivity.m11675r(AppSpecialActivity.this);
                        Iterator it4 = ((ArrayList) ((HashMap) b.get("data")).get("products")).iterator();
                        while (it4.hasNext()) {
                            HashMap hashMap4 = (HashMap) it4.next();
                            Integer num2 = (Integer) hashMap4.get("productId");
                            if (!AppSpecialActivity.this.f10112G.contains("," + ((Object) num2) + ",")) {
                                AppSpecialActivity.this.f10112G += ((Object) num2) + ",";
                                App app2 = new App();
                                app2.setTitle((String) hashMap4.get("productName"));
                                app2.setImageUrl((String) hashMap4.get("imgUrl"));
                                app2.setDownloadCount((Integer) hashMap4.get("downloadCount"));
                                app2.setSid((Integer) hashMap4.get("productId"));
                                app2.setFileTotalSize(Long.valueOf((long) (((Double) hashMap4.get("fileTotalSize")).doubleValue() * 1024.0d)));
                                app2.setSid((Integer) hashMap4.get("productId"));
                                app2.setSpeed((Integer) hashMap4.get("isSpeed"));
                                app2.setTags(C3467s.m14507a((List<String[]>) ((ArrayList) hashMap4.get("tags"))));
                                app2.setPackageName((String) hashMap4.get("appPackage"));
                                app2.setAppPointsReward(Integer.valueOf(((Integer) hashMap4.get("appPointsReward")) == null ? 0 : ((Integer) hashMap4.get("appPointsReward")).intValue()));
                                Iterator it5 = ((ArrayList) hashMap4.get("downloads")).iterator();
                                while (it5.hasNext()) {
                                    app2.setFileUrl((String) ((HashMap) it5.next()).get("fileUrl"));
                                }
                                AppSpecialActivity.this.f10143u.add(app2);
                            }
                        }
                        AppSpecialActivity.this.stopAppDrawable();
                        AppSpecialActivity.this.f10144v.notifyDataSetChanged();
                    }
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: S */
    private View.OnClickListener f10124S = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.product.AppSpecialActivity.View$OnClickListenerC28318 */

        public void onClick(View view) {
            switch (view.getId()) {
                case C2425R.C2427id.app_item_rl /*{ENCODED_INT: 2131755238}*/:
                    Intent intent = new Intent(AppSpecialActivity.this, AppActivity.class);
                    intent.putExtra("productId", (Integer) view.getTag());
                    AppSpecialActivity.this.startActivity(intent);
                    return;
                case C2425R.C2427id.app_icon /*{ENCODED_INT: 2131755239}*/:
                case C2425R.C2427id.speed_icon2 /*{ENCODED_INT: 2131755240}*/:
                default:
                    Integer num = (Integer) view.getTag();
                    int intValue = ((Banner) AppSpecialActivity.this.f10107B.get(num.intValue() % AppSpecialActivity.this.f10147y.size())).getCatId().intValue();
                    Log.m14400c(AppSpecialActivity.f10102d, "catId.." + (num.intValue() % AppSpecialActivity.this.f10147y.size()));
                    Intent intent2 = new Intent(AppSpecialActivity.this, BannerAppListActivity.class);
                    intent2.putExtra("catId", intValue);
                    intent2.putExtra("title", ((Banner) AppSpecialActivity.this.f10107B.get(num.intValue() % AppSpecialActivity.this.f10147y.size())).getTitle());
                    AppSpecialActivity.this.startActivity(intent2);
                    return;
                case C2425R.C2427id.app_download_iv /*{ENCODED_INT: 2131755241}*/:
                    AppSpecialActivity.this.m11657d((AppSpecialActivity) view);
                    return;
                case C2425R.C2427id.app_open_btn /*{ENCODED_INT: 2131755242}*/:
                    AppSpecialActivity.this.f10128b.removeMessages(6, view);
                    AppSpecialActivity.this.f10128b.sendMessageDelayed(AppSpecialActivity.this.f10128b.obtainMessage(6, view), 200);
                    return;
                case C2425R.C2427id.app_pb /*{ENCODED_INT: 2131755243}*/:
                    AppSpecialActivity.this.m11632a((AppSpecialActivity) view);
                    return;
                case C2425R.C2427id.app_downing_pause /*{ENCODED_INT: 2131755244}*/:
                    AppSpecialActivity.this.f10127a.removeMessages(7, view);
                    AppSpecialActivity.this.f10127a.sendMessageDelayed(AppSpecialActivity.this.f10127a.obtainMessage(7, view), 300);
                    return;
            }
        }
    };

    /* renamed from: T */
    private Handler f10125T = new Handler() {
        /* class com.gbcom.gwifi.functions.product.AppSpecialActivity.HandlerC28252 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    if (!AppSpecialActivity.this.f10134j) {
                        AppSpecialActivity.this.m11651c();
                    }
                    AppSpecialActivity.this.m11630a((AppSpecialActivity) 5000);
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: U */
    private LoadingView.AbstractC3488a f10126U = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.product.AppSpecialActivity.C28263 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            AppSpecialActivity.this.f10137o.mo28424a();
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: a */
    Handler f10127a = new Handler() {
        /* class com.gbcom.gwifi.functions.product.AppSpecialActivity.HandlerC28329 */

        public void handleMessage(Message message) {
            AppSpecialActivity.this.m11645b((AppSpecialActivity) ((View) message.obj));
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: b */
    Handler f10128b = new Handler() {
        /* class com.gbcom.gwifi.functions.product.AppSpecialActivity.HandlerC282310 */

        public void handleMessage(Message message) {
            AppSpecialActivity.this.m11652c((AppSpecialActivity) ((View) message.obj));
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: c */
    Handler f10129c = new Handler() {
        /* class com.gbcom.gwifi.functions.product.AppSpecialActivity.HandlerC282411 */

        public void handleMessage(Message message) {
            View view = (View) message.obj;
            AppSpecialActivity.this.m11661e((AppSpecialActivity) view);
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 6);
            intent.putExtra("url", ((App) view.getTag()).getFileUrl());
            Log.m14400c(AppSpecialActivity.f10102d, "向服务传递URL>.." + ((App) view.getTag()).getFileUrl());
            AppSpecialActivity.this.startService(intent);
        }
    };

    /* renamed from: f */
    private RelativeLayout f10130f;

    /* renamed from: g */
    private TextView f10131g;

    /* renamed from: h */
    private TextView f10132h;

    /* renamed from: i */
    private boolean f10133i = false;

    /* renamed from: j */
    private boolean f10134j = false;

    /* renamed from: k */
    private final int f10135k = 0;

    /* renamed from: l */
    private final int f10136l = 1;

    /* renamed from: o */
    private XListView f10137o;

    /* renamed from: p */
    private HandlerC2833a f10138p;

    /* renamed from: q */
    private Request f10139q;

    /* renamed from: r */
    private Request f10140r;

    /* renamed from: s */
    private int f10141s = 0;

    /* renamed from: t */
    private Boolean f10142t = false;

    /* renamed from: u */
    private List<App> f10143u;

    /* renamed from: v */
    private C2834b f10144v;

    /* renamed from: w */
    private LinearLayout f10145w;

    /* renamed from: x */
    private List<View> f10146x;

    /* renamed from: y */
    private List<String> f10147y;

    /* renamed from: z */
    private JazzyViewPager f10148z;

    /* renamed from: r */
    static /* synthetic */ int m11675r(AppSpecialActivity appSpecialActivity) {
        int i = appSpecialActivity.f10141s;
        appSpecialActivity.f10141s = i + 1;
        return i;
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "应用专题界面", C2425R.layout.app_activity_special, true);
        m11629a();
    }

    /* renamed from: a */
    private void m11629a() {
        this.f10130f = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10130f.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.product.AppSpecialActivity.View$OnClickListenerC28221 */

            public void onClick(View view) {
                AppSpecialActivity.this.finish();
            }
        });
        this.f10132h = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10131g = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10132h.setText("应用");
        this.f10131g.setText("");
        this.f10143u = new ArrayList();
        this.f10107B = new ArrayList();
        this.f10147y = new ArrayList();
        this.f10146x = new ArrayList();
        this.f10111F = new ImageView(this);
        this.f10111F.setImageResource(C2425R.C2426drawable.loading_big);
        this.f10120O = (LoadingView) findViewById(C2425R.C2427id.loading_view);
        this.f10120O.mo28298a(this.f10126U);
        this.f10110E = getLayoutInflater().inflate(C2425R.layout.common_image_news, (ViewGroup) null);
        this.f10110E.setVisibility(8);
        this.f10145w = (LinearLayout) this.f10110E.findViewById(C2425R.C2427id.dot_ll);
        this.f10109D = (RelativeLayout) this.f10110E.findViewById(C2425R.C2427id.image_news);
        this.f10108C = (TextView) this.f10110E.findViewById(C2425R.C2427id.image_news_title);
        m11633a(this.f10110E, JazzyViewPager.EnumC3486b.Standard);
        this.f10137o = (XListView) findViewById(C2425R.C2427id.post_listview);
        this.f10144v = new C2834b();
        this.f10137o.setAdapter((ListAdapter) this.f10144v);
        HandlerThread handlerThread = new HandlerThread("cache_news");
        handlerThread.start();
        this.f10138p = new HandlerC2833a(handlerThread.getLooper());
        this.f10116K = new HandlerC2835c();
        this.f10137o.mo28426a((XListView.AbstractC3496a) this);
        this.f10137o.mo28424a();
    }

    @Override // com.gbcom.gwifi.widget.XListView.AbstractC3496a
    public void onRefresh() {
        this.f10141s = 0;
        stopAutoScroll();
        m11643b();
    }

    /* renamed from: b */
    private void m11643b() {
        this.f10140r = HttpUtil.m14272a(Constant.f13160O, this.f10123R, f10103e);
    }

    @Override // com.gbcom.gwifi.widget.XListView.AbstractC3496a
    public void onLoadMore() {
        queryNextPage();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.product.AppSpecialActivity$b */
    public class C2834b extends BaseAdapter {
        C2834b() {
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            App app;
            if (i == 0 && 8 != AppSpecialActivity.this.f10110E.getVisibility()) {
                return AppSpecialActivity.this.f10110E;
            }
            if (8 != AppSpecialActivity.this.f10110E.getVisibility()) {
                app = (App) AppSpecialActivity.this.f10143u.get(i - 1);
            } else {
                app = (App) AppSpecialActivity.this.f10143u.get(i);
            }
            View inflate = AppSpecialActivity.this.getLayoutInflater().inflate(C2425R.layout.app_item, viewGroup, false);
            inflate.setTag(app.getFileUrl());
            TextView textView = (TextView) inflate.findViewById(16908308);
            TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.app_size);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.download_num);
            TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.app_item_text);
            ((TextView) inflate.findViewById(C2425R.C2427id.app_item_add_score)).setText("+ " + ((Object) app.getAppPointsReward()));
            textView4.setVisibility(0);
            textView4.setText(Constant.f13309cr);
            ((TextView) inflate.findViewById(16908310)).setText(app.getTitle());
            ArrayList<String[]> b = C3467s.m14509b(app.getTags());
            String str = "";
            if (!b.isEmpty()) {
                str = b.get(0)[1];
            }
            textView.setText(str);
            textView2.setText(C3467s.m14501a(app.getFileTotalSize().longValue()));
            textView3.setText("已下载" + ((Object) app.getDownloadCount()) + "次");
            inflate.findViewById(C2425R.C2427id.app_item_rl).setTag(app.getSid());
            inflate.findViewById(C2425R.C2427id.app_item_rl).setOnClickListener(AppSpecialActivity.this.f10124S);
            AppSpecialActivity.this.f10115J.put(app.getPackageName(), inflate);
            Button button = (Button) inflate.findViewById(C2425R.C2427id.app_open_btn);
            button.setTag(app.getFileUrl());
            button.setOnClickListener(AppSpecialActivity.this.f10124S);
            ProgressWheel progressWheel = (ProgressWheel) inflate.findViewById(C2425R.C2427id.app_pb);
            progressWheel.setTag(app);
            progressWheel.setOnClickListener(AppSpecialActivity.this.f10124S);
            Button button2 = (Button) inflate.findViewById(C2425R.C2427id.app_downing_pause);
            button2.setTag(app);
            button2.setOnClickListener(AppSpecialActivity.this.f10124S);
            if (app.getSpeed().intValue() == 1) {
                inflate.findViewById(C2425R.C2427id.speed_icon).setVisibility(0);
            }
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.app_icon);
            if (app.getImageUrl() == null || app.getImageUrl().length() <= 5) {
                imageView.setVisibility(8);
            } else {
                ImageTools.m14149a(app.getImageUrl(), imageView, GBApplication.instance().options);
            }
            if (StorageUtils.m14492c(AppSpecialActivity.this, app.getPackageName())) {
                button.setVisibility(0);
                progressWheel.setVisibility(8);
                button2.setVisibility(8);
                button.setText("打开");
                return inflate;
            }
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(AppSpecialActivity.this, "url", app.getFileUrl());
            if (downloadFile != null) {
                switch (downloadFile.getStateId().intValue()) {
                    case 0:
                        button.setVisibility(0);
                        progressWheel.setVisibility(8);
                        button2.setVisibility(8);
                        return inflate;
                    case 1:
                        button.setVisibility(8);
                        progressWheel.setVisibility(8);
                        button2.setVisibility(0);
                        return inflate;
                    case 2:
                        button.setVisibility(8);
                        progressWheel.setVisibility(0);
                        button2.setVisibility(8);
                        return inflate;
                }
            }
            return inflate;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return AppSpecialActivity.this.f10143u.get(i);
        }

        public int getCount() {
            if (8 != AppSpecialActivity.this.f10110E.getVisibility()) {
                return AppSpecialActivity.this.f10143u.size() + 1;
            }
            return AppSpecialActivity.this.f10143u.size();
        }
    }

    /* renamed from: a */
    private void m11633a(View view, JazzyViewPager.EnumC3486b bVar) {
        if (this.f10148z == null) {
            this.f10148z = (JazzyViewPager) view.findViewById(C2425R.C2427id.jazzy_pager);
            this.f10148z.mo28279a(bVar);
            this.f10148z.setAdapter(this.f10122Q);
            this.f10148z.setOnPageChangeListener(this.f10121P);
            this.f10148z.setPageMargin(30);
            this.f10148z.getLayoutParams().height = (DensityUtil.m14127a(GBApplication.instance()) * 3) / 4;
            this.f10148z.setCurrentItem(this.f10147y.size() * 1000);
            this.f10106A = this.f10147y.size() * 1000;
            this.f10148z.setOnTouchListener(new View.OnTouchListener() {
                /* class com.gbcom.gwifi.functions.product.AppSpecialActivity.View$OnTouchListenerC28274 */

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case 0:
                            AppSpecialActivity.this.f10134j = true;
                            break;
                        case 1:
                            AppSpecialActivity.this.f10134j = false;
                            break;
                    }
                    return false;
                }
            });
        }
        this.f10148z.getAdapter().notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11639a(List<Banner> list) {
        this.f10146x.clear();
        this.f10147y.clear();
        this.f10145w.removeAllViews();
        for (Banner banner : list) {
            View inflate = getLayoutInflater().inflate(C2425R.layout.common_dot, (ViewGroup) this.f10145w, false);
            ImageView imageView = new ImageView(this);
            ImageTools.m14149a(banner.getImageUrl(), imageView, GBApplication.instance().options2);
            this.f10146x.add(imageView);
            this.f10147y.add(banner.getTitle());
            this.f10145w.addView(inflate);
        }
        if (this.f10147y.size() > 0) {
            this.f10109D.setVisibility(0);
            this.f10148z.getAdapter().notifyDataSetChanged();
            startAutoScroll();
        }
    }

    public void startAppDrawable() {
        this.f10120O.mo28307i();
        this.f10120O.mo28313o();
        this.f10120O.mo28315q();
        if (this.f10120O.mo28310l()) {
            this.f10120O.mo28309k();
        }
        this.f10120O.mo28297a();
        this.f10120O.mo28302d();
        this.f10120O.mo28304f();
        this.f10120O.mo28300b();
    }

    public void errAppDrawable() {
        this.f10137o.setVisibility(8);
        this.f10120O.setVisibility(0);
        this.f10120O.mo28303e();
        this.f10120O.mo28301c();
        this.f10120O.mo28305g();
        this.f10120O.mo28306h();
        this.f10120O.mo28308j();
        this.f10120O.mo28312n();
        this.f10120O.mo28314p();
    }

    public void stopAppDrawable() {
        this.f10137o.setVisibility(0);
        this.f10120O.setVisibility(8);
        this.f10120O.mo28301c();
    }

    public void queryNextPage() {
        if (!this.f10142t.booleanValue()) {
            this.f10142t = true;
            this.f10139q = HttpUtil.m14259a(this, "", this.f10141s + 1, 10, Constant.f13160O, 1, this.f10123R, f10103e);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11632a(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (this.f10137o.findViewWithTag(((App) view.getTag()).getFileUrl()) != null) {
            intent.putExtra("type", 3);
            intent.putExtra("url", ((App) view.getTag()).getFileUrl());
            startService(intent);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11645b(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (this.f10137o.findViewWithTag(((App) view.getTag()).getFileUrl()) != null) {
            intent.putExtra("type", 5);
            intent.putExtra("url", ((App) view.getTag()).getFileUrl());
            startService(intent);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11652c(View view) {
        String str = (String) view.getTag();
        View findViewWithTag = this.f10137o.findViewWithTag(str);
        Button button = (Button) findViewWithTag.findViewById(C2425R.C2427id.app_open_btn);
        Button button2 = (Button) findViewWithTag.findViewById(C2425R.C2427id.app_download_iv);
        Button button3 = (Button) findViewWithTag.findViewById(C2425R.C2427id.app_downing_pause);
        if (button.getText().equals("打开")) {
            for (Map.Entry<String, View> entry : this.f10115J.entrySet()) {
                if (entry.getValue() == findViewWithTag) {
                    StorageUtils.m14488b(this, entry.getKey());
                    return;
                }
            }
            return;
        }
        DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", str);
        if (downloadFile == null) {
            button.setText("安装");
            button.setVisibility(8);
            button3.setVisibility(8);
            button2.setVisibility(0);
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (button.getText().equals("安装")) {
            if (new File(downloadFile.getLocalFile()).exists()) {
                StorageUtils.m14482a(this, downloadFile.getLocalFile());
                return;
            }
            button.setVisibility(8);
            button3.setVisibility(8);
            button2.setVisibility(0);
            DownloadFileDao.m12152b().delete(this, downloadFile);
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (button.getText().equals("打开")) {
            StorageUtils.m14488b(this, StorageUtils.m14494d(this, downloadFile.getLocalFile()));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m11657d(View view) {
        if (!StorageUtils.m14496e()) {
            GBActivity.showMessageToast("未发现SD卡");
        } else if (!StorageUtils.m14484a()) {
            GBActivity.showMessageToast("SD卡不能读写");
        } else {
            try {
                StorageUtils.m14497f();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DensityUtil.m14127a(this);
            DensityUtil.m14130b(this);
            view.getLocationOnScreen(new int[2]);
            this.f10129c.removeMessages(5, view);
            this.f10129c.sendMessageDelayed(this.f10129c.obtainMessage(5, view), 1500);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m11661e(View view) {
        if (((DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", ((App) view.getTag()).getFileUrl())) != null) {
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 4);
            intent.putExtra("url", ((App) view.getTag()).getFileUrl());
            startService(intent);
        }
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setUrl(((App) view.getTag()).getFileUrl());
        downloadFile.setName(((App) view.getTag()).getTitle());
        downloadFile.setTags(((App) view.getTag()).getTags());
        downloadFile.setDownsize(0L);
        downloadFile.setFileTotalSize(((App) view.getTag()).getFileTotalSize());
        downloadFile.setCreateTime(Long.valueOf(System.currentTimeMillis()));
        downloadFile.setProductId(((App) view.getTag()).getSid());
        downloadFile.setProductType(Constant.f13160O);
        downloadFile.setStateId(2);
        downloadFile.setPackageName(((App) view.getTag()).getPackageName());
        DownloadFileDao.m12152b().mo24215e((Context) this, downloadFile);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.product.AppSpecialActivity$a */
    public class HandlerC2833a extends Handler {
        public HandlerC2833a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    AppSpecialActivity.this.m11631a((AppSpecialActivity) ((Intent) message.obj));
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11631a(Intent intent) {
        int parseInt;
        View findViewWithTag = this.f10137o.findViewWithTag(intent.getStringExtra("url"));
        if (findViewWithTag != null) {
            ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb);
            if (TextUtils.isEmpty(intent.getStringExtra(MyIntents.f9255c))) {
                parseInt = 0;
            } else {
                parseInt = Integer.parseInt(intent.getStringExtra(MyIntents.f9255c));
            }
            if (progressWheel != null) {
                Message obtainMessage = this.f10116K.obtainMessage(1);
                obtainMessage.obj = new Object[]{progressWheel, Integer.valueOf(parseInt)};
                this.f10116K.sendMessage(obtainMessage);
            }
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    /* renamed from: com.gbcom.gwifi.functions.product.AppSpecialActivity$c */
    public class HandlerC2835c extends Handler {
        private HandlerC2835c() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    Object[] objArr = (Object[]) message.obj;
                    ((ProgressWheel) objArr[0]).mo28351a(objArr[1] + "%");
                    ((ProgressWheel) objArr[0]).mo28349a((Integer.parseInt(objArr[1].toString()) * 360) / 100);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11630a(long j) {
        this.f10125T.removeMessages(1);
        this.f10125T.sendEmptyMessageDelayed(1, j);
    }

    public void startAutoScroll() {
        this.f10133i = true;
        m11630a(5000);
    }

    public void stopAutoScroll() {
        this.f10133i = false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11651c() {
        if (this.f10133i) {
            this.f10148z.setCurrentItem(this.f10106A + 1, true);
        }
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
        if (this.f10147y != null && this.f10147y.size() > 0) {
            startAutoScroll();
        }
        GiwifiMobclickAgent.onPageStart("PostFragment");
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onPause() {
        super.onPause();
        stopAutoScroll();
        GiwifiMobclickAgent.onPageEnd("PostFragment");
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
    }

    public class AppInstallReceiver extends BroadcastReceiver {
        public AppInstallReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            View findViewWithTag;
            View view;
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            StorageUtils.m14481a(AppSpecialActivity.this);
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") && StorageUtils.m14492c(context, schemeSpecificPart) && (view = (View) AppSpecialActivity.this.f10115J.get(schemeSpecificPart)) != null) {
                ((Button) view.findViewById(C2425R.C2427id.app_open_btn)).setText("打开");
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(AppSpecialActivity.this, Constants.KEY_PACKAGE_NAME, schemeSpecificPart);
                if (downloadFile == null || downloadFile.getLocalFile() == null || downloadFile.getStateId().intValue() != 0 || !new File(downloadFile.getLocalFile()).exists() || (findViewWithTag = AppSpecialActivity.this.f10137o.findViewWithTag(downloadFile.getUrl())) == null) {
                    View view2 = (View) AppSpecialActivity.this.f10115J.get(schemeSpecificPart);
                    if (view2 != null) {
                        Button button = (Button) view2.findViewById(C2425R.C2427id.app_open_btn);
                        button.setText("安装");
                        button.setVisibility(8);
                        return;
                    }
                    return;
                }
                ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_open_btn)).setText("安装");
            }
        }
    }

    public class ResumeDownIconReceiver extends BroadcastReceiver {
        public ResumeDownIconReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String[] stringArrayExtra;
            if (intent != null && intent.getAction().equals("com.filter.resumeIcon.receiver")) {
                for (String str : intent.getStringArrayExtra("deleteUrls")) {
                    View findViewWithTag = AppSpecialActivity.this.f10137o.findViewWithTag(str);
                    for (Map.Entry entry : AppSpecialActivity.this.f10115J.entrySet()) {
                        if (entry.getValue() == findViewWithTag && StorageUtils.m14492c(AppSpecialActivity.this, (String) entry.getKey())) {
                            ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_open_btn)).setText("打开");
                            return;
                        }
                    }
                    if (findViewWithTag != null) {
                        Button button = (Button) findViewWithTag.findViewById(C2425R.C2427id.app_open_btn);
                        button.setText("安装");
                        button.setVisibility(8);
                        ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(8);
                        ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb);
                        progressWheel.mo28351a("0");
                        progressWheel.mo28349a(0);
                        progressWheel.setVisibility(8);
                    }
                }
            }
        }
    }

    public class DowningReceiver extends BroadcastReceiver {
        public DowningReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            m11681a(intent);
        }

        /* renamed from: a */
        private void m11681a(Intent intent) {
            if (intent != null && intent.getAction().equals("com.filter.download.receiver")) {
                switch (intent.getIntExtra("type", -1)) {
                    case 0:
                        AppSpecialActivity.this.f10138p.removeMessages(1);
                        AppSpecialActivity.this.f10138p.sendMessage(AppSpecialActivity.this.f10138p.obtainMessage(1, intent));
                        return;
                    case 1:
                        String stringExtra = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra)) {
                            AppSpecialActivity.this.m11655c((AppSpecialActivity) stringExtra);
                            Log.m14400c(AppSpecialActivity.f10102d, "COMPLETE url..>" + stringExtra);
                            return;
                        }
                        return;
                    case 2:
                    case 4:
                    case 7:
                    case 8:
                    default:
                        return;
                    case 3:
                        AppSpecialActivity.this.m11644b((AppSpecialActivity) intent);
                        return;
                    case 5:
                        AppSpecialActivity.this.dealContinueTask(intent);
                        return;
                    case 6:
                        String stringExtra2 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra2)) {
                            AppSpecialActivity.this.m11649b((AppSpecialActivity) stringExtra2);
                            return;
                        }
                        return;
                    case 9:
                        intent.getStringExtra("url");
                        return;
                    case 10:
                        AppSpecialActivity.this.m11638a((AppSpecialActivity) intent.getStringExtra("url"));
                        return;
                }
            }
        }
    }

    public void dealContinueTask(Intent intent) {
        View findViewWithTag = this.f10137o.findViewWithTag(intent.getStringExtra("url"));
        if (findViewWithTag != null) {
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(8);
            ((ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb)).setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11644b(Intent intent) {
        m11631a(intent);
        View findViewWithTag = this.f10137o.findViewWithTag(intent.getStringExtra("url"));
        if (findViewWithTag != null) {
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(0);
            ((ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb)).setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11638a(String str) {
        View findViewWithTag = this.f10137o.findViewWithTag(str);
        if (findViewWithTag != null) {
            ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb);
            progressWheel.mo28351a("0");
            progressWheel.mo28349a(0);
            progressWheel.setVisibility(8);
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11649b(String str) {
        View findViewWithTag = this.f10137o.findViewWithTag(str);
        if (findViewWithTag != null) {
            ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb);
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_open_btn)).setVisibility(8);
            progressWheel.setVisibility(0);
            progressWheel.mo28351a("0");
            progressWheel.mo28349a(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11655c(String str) {
        Log.m14398b(f10102d, "dealCompleteTask");
        View findViewWithTag = this.f10137o.findViewWithTag(str);
        if (findViewWithTag != null) {
            Button button = (Button) findViewWithTag.findViewById(C2425R.C2427id.app_open_btn);
            button.setVisibility(0);
            ((ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb)).setVisibility(8);
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(8);
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", str);
            if (downloadFile != null && StorageUtils.m14492c(this, downloadFile.getPackageName())) {
                button.setText("打开");
            }
        }
    }
}
