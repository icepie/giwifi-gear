package com.gbcom.gwifi.functions.product;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.p009v4.view.PagerAdapter;
import android.support.p009v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.product.domain.ImageNews;
import com.gbcom.gwifi.functions.product.domain.TextNews;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.JazzyViewPager;
import com.gbcom.gwifi.widget.LoadingView;
import com.gbcom.gwifi.widget.OutlineContainer;
import com.gbcom.gwifi.widget.XListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import p041c.Request;

public class NewsActivity extends GBActivity implements XListView.AbstractC3496a {

    /* renamed from: a */
    private static final String f10540a = "NewsActivity";

    /* renamed from: g */
    private static final int f10541g = 1;

    /* renamed from: h */
    private static final int f10542h = 5000;

    /* renamed from: A */
    private ImageView f10543A;

    /* renamed from: B */
    private LoadingView f10544B;

    /* renamed from: C */
    private ViewPager.OnPageChangeListener f10545C = new ViewPager.OnPageChangeListener() {
        /* class com.gbcom.gwifi.functions.product.NewsActivity.C28933 */

        /* renamed from: b */
        private int f10577b = 0;

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            int size = i % NewsActivity.this.f10565r.size();
            NewsActivity.this.f10567t = i;
            NewsActivity.this.f10563p.getChildAt(this.f10577b).setBackgroundResource(C2425R.C2426drawable.gi_dot_normal);
            NewsActivity.this.f10563p.getChildAt(size).setBackgroundResource(C2425R.C2426drawable.gi_dot_focused);
            NewsActivity.this.f10569v.setText((CharSequence) NewsActivity.this.f10565r.get(size));
            this.f10577b = size;
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }
    };

    /* renamed from: D */
    private PagerAdapter f10546D = new PagerAdapter() {
        /* class com.gbcom.gwifi.functions.product.NewsActivity.C28944 */

        @Override // android.support.p009v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view;
            if (NewsActivity.this.f10565r.size() == 0) {
                view = NewsActivity.this.f10543A;
            } else {
                view = (View) NewsActivity.this.f10564q.get(i % NewsActivity.this.f10565r.size());
            }
            if (view.getParent() == null) {
                int a = DensityUtil.m14127a(GBApplication.instance());
                ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
                view.setTag(Integer.valueOf(i));
                view.setOnClickListener(NewsActivity.this.f10548F);
                viewGroup.addView(view, -1, a);
            }
            NewsActivity.this.f10566s.mo28280a(view, i);
            return view;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(NewsActivity.this.f10566s.mo28286b(i));
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public int getCount() {
            if (NewsActivity.this.f10565r.size() == 0) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view instanceof OutlineContainer ? ((OutlineContainer) view).getChildAt(0) == obj : view == obj;
        }
    };

    /* renamed from: E */
    private OkRequestHandler<String> f10547E = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.NewsActivity.C28955 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            if (NewsActivity.this.f10557j == abVar) {
                NewsActivity.this.startNewsDrawable();
                if (NewsActivity.this.f10544B.getVisibility() == 8) {
                    NewsActivity.this.showProgressDialog("正在加载...");
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            NewsActivity.this.f10560m = false;
            NewsActivity.this.dismissProgressDialog();
            if (NewsActivity.this.f10557j == abVar) {
                NewsActivity.this.errNewsDrawable();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            NewsActivity.this.dismissProgressDialog();
            CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
            NewsActivity.this.f10560m = false;
            if (NewsActivity.this.f10557j == abVar) {
                if (GBActivity.dealException(deSerializeJson)) {
                    NewsActivity.this.errNewsDrawable();
                    return;
                }
                NewsActivity.m12003l(NewsActivity.this);
                Log.m14400c(NewsActivity.f10540a, "post_listview.refreshComplete");
                NewsActivity.this.f10556i.mo28429c();
                if (NewsActivity.this.f10558k == 1) {
                    NewsActivity.this.f10573z.clear();
                    NewsActivity.this.f10561n.clear();
                    NewsActivity.this.f10562o.notifyDataSetChanged();
                }
                HashMap hashMap = (HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class);
                String obj = hashMap.get("news").toString();
                NewsActivity.this.f10559l = ((Integer) hashMap.get("totalPageNum")).intValue();
                ArrayList arrayList = new ArrayList();
                for (HashMap hashMap2 : (List) JsonUtil.m14386a(obj, List.class)) {
                    String str2 = (String) hashMap2.get("url");
                    TextNews textNews = new TextNews((String) hashMap2.get("title"), (String) hashMap2.get("subTitle"), str2, (String) hashMap2.get("imageUrl"), (Integer) hashMap2.get("id"), (Integer) hashMap2.get("updateTime"), false, (Integer) hashMap2.get("createTime"), (String) hashMap2.get("publishTime"));
                    if (!NewsActivity.this.f10573z.contains(str2)) {
                        NewsActivity.this.f10573z.add(str2);
                        NewsActivity.this.f10561n.add(textNews);
                        arrayList.add(textNews);
                    }
                }
                NewsActivity.this.stopVideoDrawable();
                NewsActivity.this.f10562o.notifyDataSetChanged();
            } else if (NewsActivity.this.f10571x == abVar && deSerializeJson != null && !GBActivity.dealException(deSerializeJson)) {
                List<HashMap> list = (List) JsonUtil.m14386a(((HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class)).get("news").toString(), List.class);
                if (list.isEmpty()) {
                    NewsActivity.this.f10572y.setVisibility(8);
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                NewsActivity.this.f10568u.clear();
                for (HashMap hashMap3 : list) {
                    ImageNews imageNews = new ImageNews(false, (String) hashMap3.get("imageUrl"), (String) hashMap3.get("title"), (String) hashMap3.get("url"), (Integer) hashMap3.get("id"));
                    NewsActivity.this.f10568u.add(imageNews);
                    arrayList2.add(imageNews);
                }
                NewsActivity.this.f10572y.setVisibility(0);
                NewsActivity.this.m11989a((NewsActivity) NewsActivity.this.f10568u);
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: F */
    private View.OnClickListener f10548F = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.product.NewsActivity.View$OnClickListenerC28966 */

        public void onClick(View view) {
            switch (view.getId()) {
                case C2425R.C2427id.text_news_item /*{ENCODED_INT: 2131755819}*/:
                    TextNews textNews = (TextNews) view.getTag();
                    GBActivity.openBackWebView(textNews.getUrl(), textNews.getTitle());
                    return;
                default:
                    ImageNews imageNews = (ImageNews) NewsActivity.this.f10568u.get(((Integer) view.getTag()).intValue() % NewsActivity.this.f10565r.size());
                    GBActivity.openBackWebView(imageNews.getUrl(), imageNews.getTitle());
                    return;
            }
        }
    };

    /* renamed from: G */
    private Handler f10549G = new Handler() {
        /* class com.gbcom.gwifi.functions.product.NewsActivity.HandlerC28977 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    if (!NewsActivity.this.f10555f) {
                        NewsActivity.this.m11993b();
                    }
                    NewsActivity.this.m11985a((NewsActivity) 5000);
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: H */
    private LoadingView.AbstractC3488a f10550H = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.product.NewsActivity.C28988 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            NewsActivity.this.f10556i.mo28424a();
        }
    };

    /* renamed from: b */
    private RelativeLayout f10551b;

    /* renamed from: c */
    private TextView f10552c;

    /* renamed from: d */
    private TextView f10553d;

    /* renamed from: e */
    private boolean f10554e = false;

    /* renamed from: f */
    private boolean f10555f = false;

    /* renamed from: i */
    private XListView f10556i;

    /* renamed from: j */
    private Request f10557j;

    /* renamed from: k */
    private int f10558k = 0;

    /* renamed from: l */
    private int f10559l = 1;

    /* renamed from: m */
    private Boolean f10560m = false;

    /* renamed from: n */
    private List<TextNews> f10561n;

    /* renamed from: o */
    private C2899a f10562o;

    /* renamed from: p */
    private LinearLayout f10563p;

    /* renamed from: q */
    private List<View> f10564q;

    /* renamed from: r */
    private List<String> f10565r;

    /* renamed from: s */
    private JazzyViewPager f10566s;

    /* renamed from: t */
    private int f10567t = 0;

    /* renamed from: u */
    private List<ImageNews> f10568u;

    /* renamed from: v */
    private TextView f10569v;

    /* renamed from: w */
    private RelativeLayout f10570w;

    /* renamed from: x */
    private Request f10571x;

    /* renamed from: y */
    private View f10572y;

    /* renamed from: z */
    private List<String> f10573z;

    /* renamed from: l */
    static /* synthetic */ int m12003l(NewsActivity newsActivity) {
        int i = newsActivity.f10558k;
        newsActivity.f10558k = i + 1;
        return i;
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "新闻界面", C2425R.layout.news_activity, true);
        m11984a();
    }

    /* renamed from: a */
    private void m11984a() {
        this.f10551b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10551b.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.product.NewsActivity.View$OnClickListenerC28911 */

            public void onClick(View view) {
                NewsActivity.this.finish();
            }
        });
        this.f10553d = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10552c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10553d.setText("新闻");
        this.f10552c.setText("");
        this.f10561n = new ArrayList();
        this.f10568u = new ArrayList();
        this.f10565r = new ArrayList();
        this.f10564q = new ArrayList();
        this.f10573z = new ArrayList();
        this.f10543A = new ImageView(this);
        this.f10543A.setImageResource(C2425R.C2426drawable.loading_big);
        this.f10544B = (LoadingView) findViewById(C2425R.C2427id.loading_view);
        this.f10544B.mo28298a(this.f10550H);
        this.f10572y = getLayoutInflater().inflate(C2425R.layout.common_image_news, (ViewGroup) null);
        this.f10572y.setVisibility(8);
        this.f10563p = (LinearLayout) this.f10572y.findViewById(C2425R.C2427id.dot_ll);
        this.f10570w = (RelativeLayout) this.f10572y.findViewById(C2425R.C2427id.image_news);
        this.f10569v = (TextView) this.f10572y.findViewById(C2425R.C2427id.image_news_title);
        m11986a(this.f10572y, JazzyViewPager.EnumC3486b.Standard);
        this.f10556i = (XListView) findViewById(C2425R.C2427id.post_listview);
        this.f10562o = new C2899a();
        this.f10556i.setAdapter((ListAdapter) this.f10562o);
        this.f10556i.mo28426a((XListView.AbstractC3496a) this);
        this.f10556i.mo28424a();
    }

    @Override // com.gbcom.gwifi.widget.XListView.AbstractC3496a
    public void onRefresh() {
        updateNews();
        updateImageNews();
    }

    @Override // com.gbcom.gwifi.widget.XListView.AbstractC3496a
    public void onLoadMore() {
        queryNextPage();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.product.NewsActivity$a */
    public class C2899a extends BaseAdapter {
        C2899a() {
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            TextNews textNews;
            if (i == 0 && 8 != NewsActivity.this.f10572y.getVisibility()) {
                return NewsActivity.this.f10572y;
            }
            if (8 != NewsActivity.this.f10572y.getVisibility()) {
                textNews = (TextNews) NewsActivity.this.f10561n.get(i - 1);
            } else {
                textNews = (TextNews) NewsActivity.this.f10561n.get(i);
            }
            View inflate = NewsActivity.this.getLayoutInflater().inflate(C2425R.layout.news_text_item, viewGroup, false);
            ((TextView) inflate.findViewById(16908310)).setText(textNews.getTitle());
            ((TextView) inflate.findViewById(16908308)).setText(textNews.getSubTitle());
            ((TextView) inflate.findViewById(C2425R.C2427id.publicTime)).setText(textNews.getPublishTime());
            inflate.findViewById(C2425R.C2427id.text_news_item).setTag(textNews);
            inflate.findViewById(C2425R.C2427id.text_news_item).setOnClickListener(NewsActivity.this.f10548F);
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.news_icon);
            if (textNews.getImageUrl() == null || textNews.getImageUrl().length() <= 5) {
                imageView.setVisibility(8);
                return inflate;
            }
            ImageTools.m14149a(textNews.getImageUrl(), imageView, GBApplication.instance().options);
            return inflate;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return NewsActivity.this.f10561n.get(i);
        }

        public int getCount() {
            if (8 != NewsActivity.this.f10572y.getVisibility()) {
                return NewsActivity.this.f10561n.size() + 1;
            }
            return NewsActivity.this.f10561n.size();
        }
    }

    /* renamed from: a */
    private void m11986a(View view, JazzyViewPager.EnumC3486b bVar) {
        if (this.f10566s == null) {
            this.f10566s = (JazzyViewPager) view.findViewById(C2425R.C2427id.jazzy_pager);
            this.f10566s.mo28279a(bVar);
            this.f10566s.setAdapter(this.f10546D);
            this.f10566s.setOnPageChangeListener(this.f10545C);
            this.f10566s.setPageMargin(30);
            this.f10566s.getLayoutParams().height = (DensityUtil.m14127a(GBApplication.instance()) * 3) / 4;
            this.f10566s.setCurrentItem(this.f10565r.size() * 1000);
            this.f10567t = this.f10565r.size() * 1000;
            this.f10566s.setOnTouchListener(new View.OnTouchListener() {
                /* class com.gbcom.gwifi.functions.product.NewsActivity.View$OnTouchListenerC28922 */

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case 0:
                            NewsActivity.this.f10555f = true;
                            break;
                        case 1:
                            NewsActivity.this.f10555f = false;
                            break;
                    }
                    return false;
                }
            });
        }
        this.f10566s.getAdapter().notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11989a(List<ImageNews> list) {
        this.f10564q.clear();
        this.f10565r.clear();
        this.f10563p.removeAllViews();
        for (ImageNews imageNews : list) {
            View inflate = getLayoutInflater().inflate(C2425R.layout.common_dot, (ViewGroup) this.f10563p, false);
            ImageView imageView = new ImageView(this);
            ImageTools.m14149a(imageNews.getImageUrl(), imageView, GBApplication.instance().options2);
            this.f10564q.add(imageView);
            this.f10565r.add(imageNews.getTitle());
            this.f10563p.addView(inflate);
        }
        if (this.f10565r.size() > 0) {
            startAutoScroll();
            this.f10570w.setVisibility(0);
            this.f10566s.getAdapter().notifyDataSetChanged();
        }
    }

    public void startNewsDrawable() {
        this.f10544B.mo28307i();
        this.f10544B.mo28313o();
        this.f10544B.mo28315q();
        if (this.f10544B.mo28310l()) {
            this.f10544B.mo28309k();
        }
        this.f10544B.mo28297a();
        this.f10544B.mo28302d();
        this.f10544B.mo28304f();
        this.f10544B.mo28300b();
    }

    public void errNewsDrawable() {
        this.f10556i.setVisibility(8);
        this.f10544B.setVisibility(0);
        this.f10544B.mo28303e();
        this.f10544B.mo28301c();
        this.f10544B.mo28305g();
        this.f10544B.mo28306h();
        this.f10544B.mo28308j();
        this.f10544B.mo28312n();
        this.f10544B.mo28314p();
    }

    public void stopVideoDrawable() {
        this.f10556i.setVisibility(0);
        this.f10544B.setVisibility(8);
        this.f10544B.mo28301c();
    }

    public void queryNextPage() {
        if (this.f10558k <= this.f10559l && !this.f10560m.booleanValue()) {
            this.f10560m = true;
            this.f10557j = HttpUtil.m14243a(this.f10558k + 1, 20, this.f10547E, "");
        }
    }

    public void updateNews() {
        this.f10558k = 0;
        queryNextPage();
    }

    public void updateImageNews() {
        stopAutoScroll();
        this.f10571x = HttpUtil.m14268a(this.f10547E, "");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11985a(long j) {
        this.f10549G.removeMessages(1);
        this.f10549G.sendEmptyMessageDelayed(1, j);
    }

    public void startAutoScroll() {
        this.f10554e = true;
        m11985a(5000);
    }

    public void stopAutoScroll() {
        this.f10554e = false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11993b() {
        if (this.f10554e) {
            this.f10566s.setCurrentItem(this.f10567t + 1, true);
        }
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
        if (this.f10565r != null && this.f10565r.size() > 0) {
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
}
