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
import com.gbcom.gwifi.functions.product.domain.Banner;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.domain.Game;
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

public class GameSpecialActivity extends GBActivity implements XListView.AbstractC3496a {
    public static final int DOWN_ANIM = 5;
    public static final int INSTALL_OPEN_ANIM = 6;
    public static final int P2C_ANIM = 7;

    /* renamed from: d */
    private static final String f10394d = "GameSpecialActivity";

    /* renamed from: e */
    private static final Object f10395e = f10394d;

    /* renamed from: l */
    private static final int f10396l = 1;

    /* renamed from: m */
    private static final int f10397m = 5000;

    /* renamed from: A */
    private List<Banner> f10398A;

    /* renamed from: B */
    private TextView f10399B;

    /* renamed from: C */
    private RelativeLayout f10400C;

    /* renamed from: D */
    private View f10401D;

    /* renamed from: E */
    private ImageView f10402E;

    /* renamed from: F */
    private String f10403F = ",";

    /* renamed from: G */
    private DowningReceiver f10404G;

    /* renamed from: H */
    private HashMap<String, View> f10405H = new HashMap<>();

    /* renamed from: I */
    private HandlerC2880c f10406I;

    /* renamed from: J */
    private AppInstallReceiver f10407J;

    /* renamed from: K */
    private ResumeDownIconReceiver f10408K;

    /* renamed from: L */
    private String f10409L;

    /* renamed from: M */
    private LoadingView f10410M;

    /* renamed from: N */
    private ViewPager.OnPageChangeListener f10411N = new ViewPager.OnPageChangeListener() {
        /* class com.gbcom.gwifi.functions.product.GameSpecialActivity.C28735 */

        /* renamed from: b */
        private int f10446b = 0;

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            int size = i % GameSpecialActivity.this.f10436x.size();
            GameSpecialActivity.this.f10438z = i;
            GameSpecialActivity.this.f10434v.getChildAt(this.f10446b).setBackgroundResource(C2425R.C2426drawable.gi_dot_normal);
            GameSpecialActivity.this.f10434v.getChildAt(size).setBackgroundResource(C2425R.C2426drawable.gi_dot_focused);
            GameSpecialActivity.this.f10399B.setText((CharSequence) GameSpecialActivity.this.f10436x.get(size));
            this.f10446b = size;
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }
    };

    /* renamed from: O */
    private PagerAdapter f10412O = new PagerAdapter() {
        /* class com.gbcom.gwifi.functions.product.GameSpecialActivity.C28746 */

        @Override // android.support.p009v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view;
            if (GameSpecialActivity.this.f10436x.size() == 0) {
                view = GameSpecialActivity.this.f10402E;
            } else {
                view = (View) GameSpecialActivity.this.f10435w.get(i % GameSpecialActivity.this.f10436x.size());
            }
            if (view.getParent() == null) {
                int a = DensityUtil.m14127a(GameSpecialActivity.this);
                ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
                view.setTag(Integer.valueOf(i));
                view.setOnClickListener(GameSpecialActivity.this.f10414Q);
                viewGroup.addView(view, -1, a);
            }
            GameSpecialActivity.this.f10437y.mo28280a(view, i);
            return view;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(GameSpecialActivity.this.f10437y.mo28286b(i));
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public int getCount() {
            if (GameSpecialActivity.this.f10436x.size() == 0) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view instanceof OutlineContainer ? ((OutlineContainer) view).getChildAt(0) == obj : view == obj;
        }
    };

    /* renamed from: P */
    private OkRequestHandler<String> f10413P = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.GameSpecialActivity.C28757 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            if (GameSpecialActivity.this.f10429q == abVar) {
                GameSpecialActivity.this.startGameDrawable();
                if (GameSpecialActivity.this.f10410M.getVisibility() == 8) {
                    GameSpecialActivity.this.showProgressDialog("正在加载...");
                    return;
                }
                return;
            }
            GameSpecialActivity.this.showProgressDialog("正在加载...");
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            GameSpecialActivity.this.f10431s = false;
            GameSpecialActivity.this.errGameDrawable();
            GameSpecialActivity.this.dismissProgressDialog();
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            int intValue;
            int intValue2;
            GameSpecialActivity.this.dismissProgressDialog();
            if (GameSpecialActivity.this.f10429q == abVar) {
                GameSpecialActivity.this.f10431s = false;
                GameSpecialActivity.this.f10430r = 1;
                GameSpecialActivity.this.f10432t.clear();
                GameSpecialActivity.this.f10433u.notifyDataSetChanged();
                GameSpecialActivity.this.f10403F = ",";
                HashMap<String, Object> a = ResourceParse.m14446a(str.getBytes());
                if (a == null) {
                    GameSpecialActivity.this.errGameDrawable();
                } else if (ResultCode.m14475a((Integer) a.get(AbstractC5718b.JSON_ERRORCODE))) {
                    GameSpecialActivity.this.f10426n.mo28429c();
                    HashMap hashMap = (HashMap) a.get("data");
                    ArrayList arrayList = (ArrayList) hashMap.get("banners");
                    if (arrayList.isEmpty()) {
                        GameSpecialActivity.this.f10401D.setVisibility(8);
                    } else {
                        ArrayList arrayList2 = new ArrayList();
                        GameSpecialActivity.this.f10398A.clear();
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            HashMap hashMap2 = (HashMap) it.next();
                            Banner banner = new Banner();
                            banner.setCatId((Integer) hashMap2.get("catId"));
                            banner.setCatType((String) hashMap2.get("catType"));
                            banner.setImageUrl((String) hashMap2.get("imgUrl"));
                            banner.setTitle((String) hashMap2.get("catTitle"));
                            banner.setWapUrl((String) hashMap2.get("wapUrl"));
                            banner.setType(5);
                            arrayList2.add(banner);
                            GameSpecialActivity.this.f10398A.add(banner);
                        }
                        GameSpecialActivity.this.f10401D.setVisibility(0);
                        GameSpecialActivity.this.m11865a((GameSpecialActivity) GameSpecialActivity.this.f10398A);
                    }
                    Iterator it2 = ((ArrayList) hashMap.get("products")).iterator();
                    while (it2.hasNext()) {
                        HashMap hashMap3 = (HashMap) it2.next();
                        Integer num = (Integer) hashMap3.get("productId");
                        if (!GameSpecialActivity.this.f10403F.contains("," + ((Object) num) + ",")) {
                            GameSpecialActivity.this.f10403F += ((Object) num) + ",";
                            Game game = new Game();
                            game.setTitle((String) hashMap3.get("productName"));
                            game.setImageUrl((String) hashMap3.get("imgUrl"));
                            if (((Integer) hashMap3.get("appPointsReward")) == null) {
                                intValue2 = 0;
                            } else {
                                intValue2 = ((Integer) hashMap3.get("appPointsReward")).intValue();
                            }
                            game.setAppPointsReward(Integer.valueOf(intValue2));
                            game.setDownloadCount((Integer) hashMap3.get("downloadCount"));
                            game.setSid((Integer) hashMap3.get("productId"));
                            game.setFileTotalSize(Long.valueOf((long) (((Double) hashMap3.get("fileTotalSize")).doubleValue() * 1024.0d)));
                            game.setSid((Integer) hashMap3.get("productId"));
                            game.setSpeed((Integer) hashMap3.get("isSpeed"));
                            game.setPackageName((String) hashMap3.get("appPackage"));
                            game.setTags(C3467s.m14507a((List<String[]>) ((ArrayList) hashMap3.get("tags"))));
                            Iterator it3 = ((ArrayList) hashMap3.get("downloads")).iterator();
                            while (it3.hasNext()) {
                                HashMap hashMap4 = (HashMap) it3.next();
                                game.setFileUrl((String) hashMap4.get("fileUrl"));
                                game.setIsWap((Integer) hashMap4.get("isWap"));
                                if (hashMap4.containsKey("wapUrl")) {
                                    game.setWapUrl((String) hashMap4.get("wapUrl"));
                                }
                            }
                            GameSpecialActivity.this.f10432t.add(game);
                        }
                    }
                    GameSpecialActivity.this.stopGameDrawable();
                    GameSpecialActivity.this.f10433u.notifyDataSetChanged();
                } else {
                    GameSpecialActivity.this.errGameDrawable();
                }
            } else if (GameSpecialActivity.this.f10428p == abVar) {
                GameSpecialActivity.this.f10431s = false;
                HashMap<String, Object> b = ResourceParse.m14448b(str.getBytes());
                if (b == null) {
                    GameSpecialActivity.this.f10426n.mo28431d();
                    GameSpecialActivity.this.errGameDrawable();
                } else if (!ResultCode.m14475a((Integer) b.get(AbstractC5718b.JSON_ERRORCODE))) {
                    GameSpecialActivity.this.errGameDrawable();
                } else {
                    GameSpecialActivity.this.f10426n.mo28429c();
                    GameSpecialActivity.m11901r(GameSpecialActivity.this);
                    Iterator it4 = ((ArrayList) ((HashMap) b.get("data")).get("products")).iterator();
                    while (it4.hasNext()) {
                        HashMap hashMap5 = (HashMap) it4.next();
                        Integer num2 = (Integer) hashMap5.get("productId");
                        if (!GameSpecialActivity.this.f10403F.contains("," + ((Object) num2) + ",")) {
                            GameSpecialActivity.this.f10403F += ((Object) num2) + ",";
                            Game game2 = new Game();
                            game2.setTitle((String) hashMap5.get("productName"));
                            game2.setImageUrl((String) hashMap5.get("imgUrl"));
                            game2.setDownloadCount((Integer) hashMap5.get("downloadCount"));
                            game2.setSid((Integer) hashMap5.get("productId"));
                            game2.setFileTotalSize(Long.valueOf((long) (((Double) hashMap5.get("fileTotalSize")).doubleValue() * 1024.0d)));
                            game2.setSid((Integer) hashMap5.get("productId"));
                            game2.setSpeed((Integer) hashMap5.get("isSpeed"));
                            game2.setPackageName((String) hashMap5.get("appPackage"));
                            if (((Integer) hashMap5.get("appPointsReward")) == null) {
                                intValue = 0;
                            } else {
                                intValue = ((Integer) hashMap5.get("appPointsReward")).intValue();
                            }
                            game2.setAppPointsReward(Integer.valueOf(intValue));
                            game2.setTags(C3467s.m14507a((List<String[]>) ((ArrayList) hashMap5.get("tags"))));
                            Iterator it5 = ((ArrayList) hashMap5.get("downloads")).iterator();
                            while (it5.hasNext()) {
                                HashMap hashMap6 = (HashMap) it5.next();
                                game2.setFileUrl((String) hashMap6.get("fileUrl"));
                                game2.setIsWap((Integer) hashMap6.get("isWap"));
                                if (hashMap6.containsKey("wapUrl")) {
                                    game2.setWapUrl((String) hashMap6.get("wapUrl"));
                                }
                            }
                            GameSpecialActivity.this.f10432t.add(game2);
                        }
                    }
                    GameSpecialActivity.this.stopGameDrawable();
                    GameSpecialActivity.this.f10433u.notifyDataSetChanged();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: Q */
    private View.OnClickListener f10414Q = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.product.GameSpecialActivity.View$OnClickListenerC28768 */

        public void onClick(View view) {
            switch (view.getId()) {
                case C2425R.C2427id.game_item_rl /*{ENCODED_INT: 2131755521}*/:
                    Intent intent = new Intent(GameSpecialActivity.this, GameActivity.class);
                    intent.putExtra("productId", (Integer) view.getTag());
                    GameSpecialActivity.this.startActivity(intent);
                    return;
                case C2425R.C2427id.game_icon /*{ENCODED_INT: 2131755522}*/:
                default:
                    Integer num = (Integer) view.getTag();
                    int intValue = ((Banner) GameSpecialActivity.this.f10398A.get(num.intValue() % GameSpecialActivity.this.f10436x.size())).getCatId().intValue();
                    Log.m14400c(GameSpecialActivity.f10394d, "catId.." + (num.intValue() % GameSpecialActivity.this.f10436x.size()));
                    Intent intent2 = new Intent(GameSpecialActivity.this, BannerGameListActivity.class);
                    intent2.putExtra("catId", intValue);
                    intent2.putExtra("title", ((Banner) GameSpecialActivity.this.f10398A.get(num.intValue() % GameSpecialActivity.this.f10436x.size())).getTitle());
                    GameSpecialActivity.this.startActivity(intent2);
                    return;
                case C2425R.C2427id.game_download_iv /*{ENCODED_INT: 2131755523}*/:
                    GameSpecialActivity.this.m11883d((GameSpecialActivity) view);
                    Game game = (Game) view.getTag();
                    return;
                case C2425R.C2427id.game_open_btn /*{ENCODED_INT: 2131755524}*/:
                    GameSpecialActivity.this.f10418b.removeMessages(6, view);
                    GameSpecialActivity.this.f10418b.sendMessageDelayed(GameSpecialActivity.this.f10418b.obtainMessage(6, view), 200);
                    return;
                case C2425R.C2427id.game_pb /*{ENCODED_INT: 2131755525}*/:
                    GameSpecialActivity.this.m11858a((GameSpecialActivity) view);
                    return;
                case C2425R.C2427id.game_downing_pause /*{ENCODED_INT: 2131755526}*/:
                    GameSpecialActivity.this.f10417a.removeMessages(7, view);
                    GameSpecialActivity.this.f10417a.sendMessageDelayed(GameSpecialActivity.this.f10417a.obtainMessage(7, view), 300);
                    return;
            }
        }
    };

    /* renamed from: R */
    private Handler f10415R = new Handler() {
        /* class com.gbcom.gwifi.functions.product.GameSpecialActivity.HandlerC28702 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    if (!GameSpecialActivity.this.f10424j) {
                        GameSpecialActivity.this.m11877c();
                    }
                    GameSpecialActivity.this.m11856a((GameSpecialActivity) 5000);
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: S */
    private LoadingView.AbstractC3488a f10416S = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.product.GameSpecialActivity.C28713 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            GameSpecialActivity.this.f10426n.mo28424a();
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: a */
    Handler f10417a = new Handler() {
        /* class com.gbcom.gwifi.functions.product.GameSpecialActivity.HandlerC28779 */

        public void handleMessage(Message message) {
            GameSpecialActivity.this.m11871b((GameSpecialActivity) ((View) message.obj));
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: b */
    Handler f10418b = new Handler() {
        /* class com.gbcom.gwifi.functions.product.GameSpecialActivity.HandlerC286810 */

        public void handleMessage(Message message) {
            GameSpecialActivity.this.m11878c((GameSpecialActivity) ((View) message.obj));
        }
    };

    /* renamed from: c */
    Handler f10419c = new Handler() {
        /* class com.gbcom.gwifi.functions.product.GameSpecialActivity.HandlerC286911 */

        public void handleMessage(Message message) {
            View view = (View) message.obj;
            GameSpecialActivity.this.m11887e((GameSpecialActivity) view);
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 6);
            intent.putExtra("url", ((Game) view.getTag()).getFileUrl());
            Log.m14400c(GameSpecialActivity.f10394d, "向服务传递URL>.." + ((Game) view.getTag()).getFileUrl());
            GameSpecialActivity.this.startService(intent);
        }
    };

    /* renamed from: f */
    private RelativeLayout f10420f;

    /* renamed from: g */
    private TextView f10421g;

    /* renamed from: h */
    private TextView f10422h;

    /* renamed from: i */
    private boolean f10423i = false;

    /* renamed from: j */
    private boolean f10424j = false;

    /* renamed from: k */
    private final int f10425k = 1;

    /* renamed from: n */
    private XListView f10426n;

    /* renamed from: o */
    private HandlerC2878a f10427o;

    /* renamed from: p */
    private Request f10428p;

    /* renamed from: q */
    private Request f10429q;

    /* renamed from: r */
    private int f10430r = 0;

    /* renamed from: s */
    private Boolean f10431s = false;

    /* renamed from: t */
    private List<Game> f10432t;

    /* renamed from: u */
    private C2879b f10433u;

    /* renamed from: v */
    private LinearLayout f10434v;

    /* renamed from: w */
    private List<View> f10435w;

    /* renamed from: x */
    private List<String> f10436x;

    /* renamed from: y */
    private JazzyViewPager f10437y;

    /* renamed from: z */
    private int f10438z = 0;

    /* renamed from: r */
    static /* synthetic */ int m11901r(GameSpecialActivity gameSpecialActivity) {
        int i = gameSpecialActivity.f10430r;
        gameSpecialActivity.f10430r = i + 1;
        return i;
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "游戏专题界面", C2425R.layout.game_special_activity, true);
        this.f10409L = getIntent().getStringExtra("title");
        m11855a();
    }

    /* renamed from: a */
    private void m11855a() {
        this.f10420f = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10420f.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.product.GameSpecialActivity.View$OnClickListenerC28671 */

            public void onClick(View view) {
                GameSpecialActivity.this.finish();
            }
        });
        this.f10422h = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10421g = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        if (TextUtils.isEmpty(this.f10409L)) {
            this.f10422h.setText("游戏");
        } else {
            this.f10422h.setText(this.f10409L);
        }
        this.f10421g.setText("");
        this.f10432t = new ArrayList();
        this.f10398A = new ArrayList();
        this.f10436x = new ArrayList();
        this.f10435w = new ArrayList();
        this.f10402E = new ImageView(this);
        this.f10402E.setImageResource(C2425R.C2426drawable.loading_big);
        this.f10410M = (LoadingView) findViewById(C2425R.C2427id.loading_view);
        this.f10410M.mo28298a(this.f10416S);
        this.f10401D = getLayoutInflater().inflate(C2425R.layout.common_image_news, (ViewGroup) null);
        this.f10401D.setVisibility(8);
        this.f10434v = (LinearLayout) this.f10401D.findViewById(C2425R.C2427id.dot_ll);
        this.f10400C = (RelativeLayout) this.f10401D.findViewById(C2425R.C2427id.image_news);
        this.f10399B = (TextView) this.f10401D.findViewById(C2425R.C2427id.image_news_title);
        m11859a(this.f10401D, JazzyViewPager.EnumC3486b.Standard);
        this.f10426n = (XListView) findViewById(C2425R.C2427id.post_listview);
        this.f10433u = new C2879b();
        this.f10426n.setAdapter((ListAdapter) this.f10433u);
        HandlerThread handlerThread = new HandlerThread("cache_news");
        handlerThread.start();
        this.f10427o = new HandlerC2878a(handlerThread.getLooper());
        this.f10406I = new HandlerC2880c();
        this.f10426n.mo28426a((XListView.AbstractC3496a) this);
        this.f10426n.mo28424a();
    }

    @Override // com.gbcom.gwifi.widget.XListView.AbstractC3496a
    public void onRefresh() {
        this.f10430r = 0;
        stopAutoScroll();
        m11869b();
    }

    /* renamed from: b */
    private void m11869b() {
        this.f10429q = HttpUtil.m14272a(Constant.f13162Q, this.f10413P, f10395e);
    }

    @Override // com.gbcom.gwifi.widget.XListView.AbstractC3496a
    public void onLoadMore() {
        queryNextPage();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.product.GameSpecialActivity$b */
    public class C2879b extends BaseAdapter {
        C2879b() {
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Game game;
            Game game2 = (Game) GameSpecialActivity.this.f10432t.get(i);
            if (i == 0 && 8 != GameSpecialActivity.this.f10401D.getVisibility()) {
                return GameSpecialActivity.this.f10401D;
            }
            if (8 != GameSpecialActivity.this.f10401D.getVisibility()) {
                game = (Game) GameSpecialActivity.this.f10432t.get(i - 1);
            } else {
                game = (Game) GameSpecialActivity.this.f10432t.get(i);
            }
            View inflate = GameSpecialActivity.this.getLayoutInflater().inflate(C2425R.layout.game_item, viewGroup, false);
            inflate.setTag(game.getFileUrl());
            TextView textView = (TextView) inflate.findViewById(16908308);
            TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.game_size);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.download_num);
            TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.game_item_text);
            ((TextView) inflate.findViewById(C2425R.C2427id.game_item_add_score)).setText("+ " + ((Object) game.getAppPointsReward()));
            textView4.setVisibility(0);
            textView4.setText(Constant.f13309cr);
            ((TextView) inflate.findViewById(16908310)).setText(game.getTitle());
            ArrayList<String[]> b = C3467s.m14509b(game.getTags());
            String str = "";
            if (!b.isEmpty()) {
                str = b.get(0)[1];
            }
            textView.setText(str);
            textView2.setText(C3467s.m14501a(game.getFileTotalSize().longValue()));
            textView3.setText("已下载" + ((Object) game.getDownloadCount()) + "次");
            inflate.findViewById(C2425R.C2427id.game_item_rl).setOnClickListener(GameSpecialActivity.this.f10414Q);
            inflate.findViewById(C2425R.C2427id.game_item_rl).setTag(game.getSid());
            GameSpecialActivity.this.f10405H.put(game.getPackageName(), inflate);
            Button button = (Button) inflate.findViewById(C2425R.C2427id.game_open_btn);
            if (game.getIsWap().intValue() == 1) {
                button.setTag(new String[]{game.getIsWap().toString(), game.getWapUrl(), game.getTitle()});
            } else {
                button.setTag(new String[]{game.getIsWap().toString(), game.getFileUrl()});
            }
            button.setOnClickListener(GameSpecialActivity.this.f10414Q);
            ProgressWheel progressWheel = (ProgressWheel) inflate.findViewById(C2425R.C2427id.game_pb);
            progressWheel.setTag(game);
            progressWheel.setOnClickListener(GameSpecialActivity.this.f10414Q);
            Button button2 = (Button) inflate.findViewById(C2425R.C2427id.game_downing_pause);
            button2.setTag(game);
            button2.setOnClickListener(GameSpecialActivity.this.f10414Q);
            if (game.getSpeed().intValue() == 1) {
                inflate.findViewById(C2425R.C2427id.speed_icon).setVisibility(0);
            }
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.game_icon);
            if (game.getImageUrl() == null || game.getImageUrl().length() <= 5) {
                imageView.setVisibility(8);
            } else {
                ImageTools.m14149a(game.getImageUrl(), imageView, GBApplication.instance().options);
            }
            if (!StorageUtils.m14492c(GameSpecialActivity.this, game.getPackageName())) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(GameSpecialActivity.this, "url", game.getFileUrl());
                if (downloadFile != null) {
                    switch (downloadFile.getStateId().intValue()) {
                        case 0:
                            button.setVisibility(0);
                            progressWheel.setVisibility(8);
                            button2.setVisibility(8);
                            break;
                        case 1:
                            button.setVisibility(8);
                            progressWheel.setVisibility(8);
                            button2.setVisibility(0);
                            break;
                        case 2:
                            button.setVisibility(8);
                            progressWheel.setVisibility(0);
                            button2.setVisibility(8);
                            break;
                    }
                }
            } else {
                button.setVisibility(0);
                progressWheel.setVisibility(8);
                button2.setVisibility(8);
                button.setText("打开");
            }
            if (game.getIsWap().intValue() == 1) {
                button.setText("打开");
                button.setVisibility(0);
                progressWheel.setVisibility(8);
                button2.setVisibility(8);
            }
            return inflate;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return GameSpecialActivity.this.f10432t.get(i);
        }

        public int getCount() {
            if (8 != GameSpecialActivity.this.f10401D.getVisibility()) {
                return GameSpecialActivity.this.f10432t.size() + 1;
            }
            return GameSpecialActivity.this.f10432t.size();
        }
    }

    /* renamed from: a */
    private void m11859a(View view, JazzyViewPager.EnumC3486b bVar) {
        if (this.f10437y == null) {
            this.f10437y = (JazzyViewPager) view.findViewById(C2425R.C2427id.jazzy_pager);
            this.f10437y.mo28279a(bVar);
            this.f10437y.setAdapter(this.f10412O);
            this.f10437y.setOnPageChangeListener(this.f10411N);
            this.f10437y.setPageMargin(30);
            this.f10437y.getLayoutParams().height = (DensityUtil.m14127a(this) * 3) / 4;
            this.f10437y.setCurrentItem(this.f10436x.size() * 1000);
            this.f10438z = this.f10436x.size() * 1000;
            this.f10437y.setOnTouchListener(new View.OnTouchListener() {
                /* class com.gbcom.gwifi.functions.product.GameSpecialActivity.View$OnTouchListenerC28724 */

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case 0:
                            GameSpecialActivity.this.f10424j = true;
                            break;
                        case 1:
                            GameSpecialActivity.this.f10424j = false;
                            break;
                    }
                    return false;
                }
            });
        }
        this.f10437y.getAdapter().notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11865a(List<Banner> list) {
        this.f10435w.clear();
        this.f10436x.clear();
        this.f10434v.removeAllViews();
        for (Banner banner : list) {
            View inflate = getLayoutInflater().inflate(C2425R.layout.common_dot, (ViewGroup) this.f10434v, false);
            ImageView imageView = new ImageView(this);
            ImageTools.m14149a(banner.getImageUrl(), imageView, GBApplication.instance().options2);
            this.f10435w.add(imageView);
            this.f10436x.add(banner.getTitle());
            this.f10434v.addView(inflate);
        }
        if (this.f10436x.size() > 0) {
            startAutoScroll();
            this.f10400C.setVisibility(0);
            this.f10437y.getAdapter().notifyDataSetChanged();
        }
    }

    public void startGameDrawable() {
        this.f10410M.mo28307i();
        this.f10410M.mo28313o();
        this.f10410M.mo28315q();
        if (this.f10410M.mo28310l()) {
            this.f10410M.mo28309k();
        }
        this.f10410M.mo28297a();
        this.f10410M.mo28302d();
        this.f10410M.mo28304f();
        this.f10410M.mo28300b();
    }

    public void errGameDrawable() {
        this.f10426n.setVisibility(8);
        this.f10410M.setVisibility(0);
        this.f10410M.mo28303e();
        this.f10410M.mo28301c();
        this.f10410M.mo28305g();
        this.f10410M.mo28306h();
        this.f10410M.mo28308j();
        this.f10410M.mo28312n();
        this.f10410M.mo28314p();
    }

    public void stopGameDrawable() {
        this.f10426n.setVisibility(0);
        this.f10410M.setVisibility(8);
        this.f10410M.mo28301c();
    }

    public void queryNextPage() {
        if (!this.f10431s.booleanValue()) {
            this.f10431s = true;
            this.f10428p = HttpUtil.m14259a(this, "", this.f10430r + 1, 10, Constant.f13162Q, 1, this.f10413P, f10395e);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11858a(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (this.f10426n.findViewWithTag(((Game) view.getTag()).getFileUrl()) != null) {
            intent.putExtra("type", 3);
            intent.putExtra("url", ((Game) view.getTag()).getFileUrl());
            startService(intent);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11871b(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (this.f10426n.findViewWithTag(((Game) view.getTag()).getFileUrl()) != null) {
            intent.putExtra("type", 5);
            intent.putExtra("url", ((Game) view.getTag()).getFileUrl());
            startService(intent);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11878c(View view) {
        String[] strArr = (String[]) view.getTag();
        int parseInt = Integer.parseInt(strArr[0]);
        String str = strArr[1];
        if (parseInt == 1) {
            GBActivity.openBackWebView(str, strArr[2]);
            return;
        }
        View findViewWithTag = this.f10426n.findViewWithTag(str);
        Button button = (Button) findViewWithTag.findViewById(C2425R.C2427id.game_open_btn);
        Button button2 = (Button) findViewWithTag.findViewById(C2425R.C2427id.game_downing_pause);
        if (button.getText().equals("打开")) {
            for (Map.Entry<String, View> entry : this.f10405H.entrySet()) {
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
            button2.setVisibility(8);
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (button.getText().equals("安装")) {
            if (new File(downloadFile.getLocalFile()).exists()) {
                StorageUtils.m14482a(this, downloadFile.getLocalFile());
                return;
            }
            button.setVisibility(8);
            button2.setVisibility(8);
            DownloadFileDao.m12152b().delete(this, downloadFile);
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (button.getText().equals("打开")) {
            StorageUtils.m14488b(this, StorageUtils.m14494d(this, downloadFile.getLocalFile()));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m11883d(View view) {
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
            this.f10419c.removeMessages(5, view);
            this.f10419c.sendMessageDelayed(this.f10419c.obtainMessage(5, view), 1500);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m11887e(View view) {
        if (((DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", ((Game) view.getTag()).getFileUrl())) != null) {
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 4);
            intent.putExtra("url", ((Game) view.getTag()).getFileUrl());
            startService(intent);
        }
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setUrl(((Game) view.getTag()).getFileUrl());
        downloadFile.setName(((Game) view.getTag()).getTitle());
        downloadFile.setTags(((Game) view.getTag()).getTags());
        downloadFile.setDownsize(0L);
        downloadFile.setFileTotalSize(((Game) view.getTag()).getFileTotalSize());
        downloadFile.setCreateTime(Long.valueOf(System.currentTimeMillis()));
        downloadFile.setProductId(((Game) view.getTag()).getSid());
        downloadFile.setProductType(Constant.f13162Q);
        downloadFile.setStateId(2);
        downloadFile.setPackageName(((Game) view.getTag()).getPackageName());
        DownloadFileDao.m12152b().mo24215e((Context) this, downloadFile);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.product.GameSpecialActivity$a */
    public class HandlerC2878a extends Handler {
        public HandlerC2878a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    GameSpecialActivity.this.m11857a((GameSpecialActivity) ((Intent) message.obj));
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11857a(Intent intent) {
        int parseInt;
        View findViewWithTag = this.f10426n.findViewWithTag(intent.getStringExtra("url"));
        if (findViewWithTag != null) {
            ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb);
            if (TextUtils.isEmpty(intent.getStringExtra(MyIntents.f9255c))) {
                parseInt = 0;
            } else {
                parseInt = Integer.parseInt(intent.getStringExtra(MyIntents.f9255c));
            }
            if (progressWheel != null) {
                Message obtainMessage = this.f10406I.obtainMessage(1);
                obtainMessage.obj = new Object[]{progressWheel, Integer.valueOf(parseInt)};
                this.f10406I.sendMessage(obtainMessage);
            }
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    /* renamed from: com.gbcom.gwifi.functions.product.GameSpecialActivity$c */
    public class HandlerC2880c extends Handler {
        private HandlerC2880c() {
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
    private void m11856a(long j) {
        this.f10415R.removeMessages(1);
        this.f10415R.sendEmptyMessageDelayed(1, j);
    }

    public void startAutoScroll() {
        this.f10423i = true;
        m11856a(5000);
    }

    public void stopAutoScroll() {
        this.f10423i = false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11877c() {
        if (this.f10423i) {
            this.f10437y.setCurrentItem(this.f10438z + 1, true);
        }
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
        if (this.f10436x != null && this.f10436x.size() > 0) {
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
            StorageUtils.m14481a(GameSpecialActivity.this);
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") && StorageUtils.m14492c(context, schemeSpecificPart) && (view = (View) GameSpecialActivity.this.f10405H.get(schemeSpecificPart)) != null) {
                ((Button) view.findViewById(C2425R.C2427id.game_open_btn)).setText("打开");
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(GameSpecialActivity.this, Constants.KEY_PACKAGE_NAME, schemeSpecificPart);
                if (downloadFile == null || downloadFile.getLocalFile() == null || downloadFile.getStateId().intValue() != 0 || !new File(downloadFile.getLocalFile()).exists() || (findViewWithTag = GameSpecialActivity.this.f10426n.findViewWithTag(downloadFile.getUrl())) == null) {
                    View view2 = (View) GameSpecialActivity.this.f10405H.get(schemeSpecificPart);
                    if (view2 != null) {
                        Button button = (Button) view2.findViewById(C2425R.C2427id.game_open_btn);
                        button.setText("安装");
                        button.setVisibility(8);
                        return;
                    }
                    return;
                }
                ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_open_btn)).setText("安装");
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
                    View findViewWithTag = GameSpecialActivity.this.f10426n.findViewWithTag(str);
                    for (Map.Entry entry : GameSpecialActivity.this.f10405H.entrySet()) {
                        if (entry.getValue() == findViewWithTag && StorageUtils.m14492c(GameSpecialActivity.this, (String) entry.getKey())) {
                            ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_open_btn)).setText("打开");
                            return;
                        }
                    }
                    if (findViewWithTag != null) {
                        Button button = (Button) findViewWithTag.findViewById(C2425R.C2427id.game_open_btn);
                        button.setText("安装");
                        button.setVisibility(8);
                        ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(8);
                        ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb);
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
            m11907a(intent);
        }

        /* renamed from: a */
        private void m11907a(Intent intent) {
            if (intent != null && intent.getAction().equals("com.filter.download.receiver")) {
                switch (intent.getIntExtra("type", -1)) {
                    case 0:
                        GameSpecialActivity.this.f10427o.removeMessages(1);
                        GameSpecialActivity.this.f10427o.sendMessage(GameSpecialActivity.this.f10427o.obtainMessage(1, intent));
                        return;
                    case 1:
                        String stringExtra = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra)) {
                            GameSpecialActivity.this.m11881c((GameSpecialActivity) stringExtra);
                            Log.m14400c(GameSpecialActivity.f10394d, "COMPLETE url..>" + stringExtra);
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
                        GameSpecialActivity.this.m11870b((GameSpecialActivity) intent);
                        return;
                    case 5:
                        GameSpecialActivity.this.dealContinueTask(intent);
                        return;
                    case 6:
                        String stringExtra2 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra2)) {
                            GameSpecialActivity.this.m11875b((GameSpecialActivity) stringExtra2);
                            return;
                        }
                        return;
                    case 9:
                        intent.getStringExtra("url");
                        return;
                    case 10:
                        GameSpecialActivity.this.m11864a((GameSpecialActivity) intent.getStringExtra("url"));
                        return;
                }
            }
        }
    }

    public void dealContinueTask(Intent intent) {
        View findViewWithTag = this.f10426n.findViewWithTag(intent.getStringExtra("url"));
        if (findViewWithTag != null) {
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(8);
            ((ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb)).setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11870b(Intent intent) {
        m11857a(intent);
        View findViewWithTag = this.f10426n.findViewWithTag(intent.getStringExtra("url"));
        if (findViewWithTag != null) {
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(0);
            ((ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb)).setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11864a(String str) {
        View findViewWithTag = this.f10426n.findViewWithTag(str);
        if (findViewWithTag != null) {
            ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb);
            progressWheel.mo28351a("0");
            progressWheel.mo28349a(0);
            progressWheel.setVisibility(8);
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11875b(String str) {
        View findViewWithTag = this.f10426n.findViewWithTag(str);
        if (findViewWithTag != null) {
            ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb);
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_open_btn)).setVisibility(8);
            progressWheel.setVisibility(0);
            progressWheel.mo28351a("0");
            progressWheel.mo28349a(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11881c(String str) {
        Log.m14398b(f10394d, "dealCompleteTask");
        View findViewWithTag = this.f10426n.findViewWithTag(str);
        if (findViewWithTag != null) {
            Button button = (Button) findViewWithTag.findViewById(C2425R.C2427id.game_open_btn);
            button.setVisibility(0);
            ((ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb)).setVisibility(8);
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(8);
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", str);
            if (downloadFile != null && StorageUtils.m14492c(this, downloadFile.getPackageName())) {
                button.setText("打开");
            }
        }
    }
}
