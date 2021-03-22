package com.gbcom.gwifi.functions.edu;

import android.os.Build;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.edu.domain.AllNews;
import com.gbcom.gwifi.functions.template.p252a.CommonNotifyClickListener;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.LoadingView;
import com.gbcom.gwifi.widget.PullToRefreshView;
import com.gbcom.gwifi.widget.RoundViewOutlineProvider;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

public class NewsListActivity extends GBActivity implements View.OnTouchListener, PullToRefreshView.AbstractC3493a, PullToRefreshView.AbstractC3494b {

    /* renamed from: a */
    OkRequestHandler f9310a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.edu.NewsListActivity.C26222 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            NewsListActivity.this.errWebDrawable();
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            AllNews allNews;
            if (abVar == NewsListActivity.this.f9317h) {
                try {
                    if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0 && (allNews = (AllNews) NewsListActivity.this.f9318i.mo21588a(str, AllNews.class)) != null) {
                        List<AllNews.DataBean.NewsListBean> news_list = allNews.getData().getNews_list();
                        if (news_list.size() > 0) {
                            NewsListActivity.m11004e(NewsListActivity.this);
                            NewsListActivity.this.stopOfficeDrawable();
                        } else {
                            NewsListActivity.this.stopOfficeDrawable();
                            C3470v.m14563a(NewsListActivity.this, "已经全部加载完毕");
                        }
                        List<AllNews.DataBean.PhotoNewsListBean> photo_news_list = allNews.getData().getPhoto_news_list();
                        for (int i = 0; i < photo_news_list.size(); i++) {
                            if (!TextUtils.isEmpty(photo_news_list.get(i).getImg_url())) {
                                NewsListActivity.this.f9321l.add(photo_news_list.get(i));
                            }
                        }
                        if (photo_news_list.size() > 0) {
                            NewsListActivity.this.f9323n.add(NewsListActivity.this.f9321l);
                        }
                        NewsListActivity.this.f9323n.addAll(news_list);
                        if (NewsListActivity.this.f9328s == null) {
                            NewsListActivity.this.f9328s = new C2629b(NewsListActivity.this.f9323n);
                            NewsListActivity.this.f9319j.setAdapter((ListAdapter) NewsListActivity.this.f9328s);
                        } else {
                            NewsListActivity.this.f9328s.notifyDataSetChanged();
                            NewsListActivity.this.f9326q.mo28398a("更新于:" + NewsListActivity.this.f9329t.format(new Date(System.currentTimeMillis())));
                            NewsListActivity.this.f9326q.mo28403c();
                            if (NewsListActivity.this.f9333x) {
                                NewsListActivity.this.f9333x = false;
                                NewsListActivity.this.f9319j.setSelection(NewsListActivity.this.f9319j.getCount() - 1);
                            }
                        }
                        NewsListActivity.this.f9319j.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            /* class com.gbcom.gwifi.functions.edu.NewsListActivity.C26222.C26231 */

                            @Override // android.widget.AdapterView.OnItemClickListener
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                                if (NewsListActivity.this.f9321l.size() == 0) {
                                    try {
                                        new CommonNotifyClickListener(NewsListActivity.this, new JSONObject(NewsListActivity.this.f9318i.mo21597b((AllNews.DataBean.NewsListBean) NewsListActivity.this.f9323n.get(i)))).onClick(view);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else if (i != 0) {
                                    try {
                                        new CommonNotifyClickListener(NewsListActivity.this, new JSONObject(NewsListActivity.this.f9318i.mo21597b((AllNews.DataBean.NewsListBean) NewsListActivity.this.f9323n.get(i)))).onClick(view);
                                    } catch (JSONException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    private RelativeLayout f9311b;

    /* renamed from: c */
    private TextView f9312c;

    /* renamed from: d */
    private TextView f9313d;

    /* renamed from: e */
    private ViewPager f9314e;

    /* renamed from: f */
    private LinearLayout f9315f;

    /* renamed from: g */
    private int f9316g;

    /* renamed from: h */
    private Request f9317h;

    /* renamed from: i */
    private Gson f9318i;

    /* renamed from: j */
    private ListView f9319j;

    /* renamed from: k */
    private Handler f9320k = new Handler() {
        /* class com.gbcom.gwifi.functions.edu.NewsListActivity.HandlerC26211 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    NewsListActivity.this.f9314e.setCurrentItem(NewsListActivity.this.f9314e.getCurrentItem() + 1);
                    NewsListActivity.this.f9320k.sendEmptyMessageDelayed(0, 3000);
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: l */
    private List<AllNews.DataBean.PhotoNewsListBean> f9321l = new ArrayList();

    /* renamed from: m */
    private TextView f9322m;

    /* renamed from: n */
    private List<Object> f9323n = new ArrayList();

    /* renamed from: o */
    private int f9324o;

    /* renamed from: p */
    private int f9325p;

    /* renamed from: q */
    private PullToRefreshView f9326q;

    /* renamed from: r */
    private int f9327r = 1;

    /* renamed from: s */
    private C2629b f9328s;

    /* renamed from: t */
    private SimpleDateFormat f9329t;

    /* renamed from: u */
    private int f9330u;

    /* renamed from: v */
    private int f9331v;

    /* renamed from: w */
    private int f9332w;

    /* renamed from: x */
    private boolean f9333x;

    /* renamed from: y */
    private LoadingView f9334y;

    /* renamed from: e */
    static /* synthetic */ int m11004e(NewsListActivity newsListActivity) {
        int i = newsListActivity.f9327r;
        newsListActivity.f9327r = i + 1;
        return i;
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "", C2425R.layout.news_activity_list, true);
        m10999b();
        getIntent();
        this.f9319j = (ListView) findViewById(C2425R.C2427id.f8357lv);
        this.f9329t = new SimpleDateFormat("MM-dd HH:mm");
        this.f9326q = (PullToRefreshView) findViewById(C2425R.C2427id.PullToRefreshView);
        this.f9326q.mo28396a((PullToRefreshView.AbstractC3493a) this);
        this.f9326q.mo28397a((PullToRefreshView.AbstractC3494b) this);
        this.f9319j.setOnTouchListener(this);
        this.f9331v = getResources().getDisplayMetrics().heightPixels;
        this.f9332w = getResources().getDisplayMetrics().widthPixels;
        m10993a();
    }

    /* renamed from: a */
    private void m10993a() {
        if (this.f9318i == null) {
            this.f9318i = GsonUtil.m14241a();
        }
        this.f9317h = HttpUtil.m14269a(this.f9310a, "", this.f9327r, this);
    }

    @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3493a
    public void onFooterRefresh(PullToRefreshView pullToRefreshView) {
        this.f9317h = HttpUtil.m14269a(this.f9310a, "", this.f9327r, this);
        this.f9333x = true;
    }

    @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
    public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
        this.f9323n.clear();
        this.f9321l.clear();
        if (this.f9315f != null) {
            this.f9315f.removeAllViews();
        }
        this.f9327r = 1;
        this.f9317h = HttpUtil.m14269a(this.f9310a, "", this.f9327r, this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.edu.NewsListActivity$b */
    public class C2629b extends BaseAdapter {

        /* renamed from: b */
        private final List<Object> f9347b;

        public C2629b(List<Object> list) {
            this.f9347b = list;
        }

        public int getCount() {
            if (this.f9347b == null) {
                return 0;
            }
            return this.f9347b.size();
        }

        public Object getItem(int i) {
            return this.f9347b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            Object obj = this.f9347b.get(i);
            if (obj instanceof AllNews.DataBean.NewsListBean) {
                AllNews.DataBean.NewsListBean newsListBean = (AllNews.DataBean.NewsListBean) obj;
                int size = newsListBean.getImg_urls().size();
                if (size == 0) {
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.news_item_newsbuilder_noimage, viewGroup, false);
                    ((TextView) view.findViewById(2131755406)).setText(newsListBean.getTitle());
                    TextView textView = (TextView) view.findViewById(C2425R.C2427id.tv_source);
                    if (!TextUtils.isEmpty(newsListBean.getPublish_user())) {
                        textView.setText(newsListBean.getPublish_user());
                    }
                    ((TextView) view.findViewById(C2425R.C2427id.tv_time)).setText(newsListBean.getCreate_time());
                } else if (size == 1 || size == 2) {
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.news_item_newsbuilder_oneimage, viewGroup, false);
                    ((TextView) view.findViewById(2131755406)).setText(newsListBean.getTitle());
                    TextView textView2 = (TextView) view.findViewById(C2425R.C2427id.tv_source);
                    if (!TextUtils.isEmpty(newsListBean.getPublish_user())) {
                        textView2.setText(newsListBean.getPublish_user());
                    }
                    ((TextView) view.findViewById(C2425R.C2427id.tv_time)).setText(newsListBean.getCreate_time());
                    ImageTools.m14149a(newsListBean.getImg_urls().get(0), (ImageView) view.findViewById(C2425R.C2427id.f8354iv), GBApplication.instance().options);
                } else if (size >= 3) {
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.news_item_newsbuilder_threeimage, viewGroup, false);
                    ((TextView) view.findViewById(2131755406)).setText(newsListBean.getTitle());
                    TextView textView3 = (TextView) view.findViewById(C2425R.C2427id.tv_source);
                    if (!TextUtils.isEmpty(newsListBean.getPublish_user())) {
                        textView3.setText(newsListBean.getPublish_user());
                    }
                    ((TextView) view.findViewById(C2425R.C2427id.tv_time)).setText(newsListBean.getCreate_time());
                    ImageTools.m14149a(newsListBean.getImg_urls().get(0), (ImageView) view.findViewById(C2425R.C2427id.image1), GBApplication.instance().options);
                    ImageTools.m14149a(newsListBean.getImg_urls().get(1), (ImageView) view.findViewById(C2425R.C2427id.image2), GBApplication.instance().options);
                    ImageTools.m14149a(newsListBean.getImg_urls().get(2), (ImageView) view.findViewById(C2425R.C2427id.image3), GBApplication.instance().options);
                }
            }
            if (obj instanceof List) {
                if (NewsListActivity.this.f9320k != null) {
                    NewsListActivity.this.f9320k.removeCallbacksAndMessages(null);
                }
                List list = (List) obj;
                if (SystemUtil.m14531e()) {
                    view2 = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.news_item_newsbuilder_viewpager_margin, viewGroup, false);
                } else {
                    view2 = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.news_item_newsbuilder_viewpager, viewGroup, false);
                }
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                layoutParams.height = (int) (((double) NewsListActivity.this.f9332w) * 0.43d);
                layoutParams.width = NewsListActivity.this.f9332w;
                view2.setLayoutParams(layoutParams);
                NewsListActivity.this.m10995a((NewsListActivity) list, (List) view2);
            } else {
                view2 = view;
            }
            if (obj instanceof String) {
                return LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.news_item_news_bottom, viewGroup, false);
            }
            return view2;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m10995a(final List<AllNews.DataBean.PhotoNewsListBean> list, View view) {
        this.f9314e = (ViewPager) view.findViewById(C2425R.C2427id.viewpager);
        if (SystemUtil.m14531e() && Build.VERSION.SDK_INT >= 21) {
            this.f9314e.setClipToOutline(true);
            this.f9314e.setOutlineProvider(new RoundViewOutlineProvider(20.0f));
        }
        this.f9315f = (LinearLayout) view.findViewById(C2425R.C2427id.ll_point_group);
        this.f9322m = (TextView) view.findViewById(2131755383);
        this.f9314e.setAdapter(new C2628a(list));
        this.f9314e.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /* class com.gbcom.gwifi.functions.edu.NewsListActivity.C26243 */

            @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                int size = i % list.size();
                NewsListActivity.this.f9315f.getChildAt(NewsListActivity.this.f9316g).setEnabled(false);
                NewsListActivity.this.f9316g = size;
                NewsListActivity.this.f9315f.getChildAt(size).setEnabled(true);
                NewsListActivity.this.f9322m.setText(((AllNews.DataBean.PhotoNewsListBean) list.get(size)).getTitle());
            }

            @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        if (list.size() != 0) {
            this.f9330u = 5000000 % list.size();
        }
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(C2425R.C2426drawable.gi_img_point);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(6, 6);
            if (i != 0) {
                layoutParams.leftMargin = 6;
                imageView.setEnabled(false);
            }
            imageView.setLayoutParams(layoutParams);
            this.f9315f.addView(imageView);
        }
        this.f9314e.setCurrentItem(5000000 - this.f9330u);
        this.f9320k.sendEmptyMessageDelayed(0, 3000);
        this.f9314e.setOnTouchListener(new View.OnTouchListener() {
            /* class com.gbcom.gwifi.functions.edu.NewsListActivity.View$OnTouchListenerC26254 */

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        NewsListActivity.this.f9324o = (int) motionEvent.getX();
                        NewsListActivity.this.f9325p = (int) motionEvent.getY();
                        NewsListActivity.this.f9320k.removeCallbacksAndMessages(null);
                        break;
                    case 1:
                        NewsListActivity.this.f9325p = (int) motionEvent.getY();
                        NewsListActivity.this.f9320k.sendEmptyMessageDelayed(0, 3000);
                        int abs = Math.abs(((int) motionEvent.getX()) - NewsListActivity.this.f9324o);
                        int abs2 = Math.abs(((int) motionEvent.getY()) - NewsListActivity.this.f9325p);
                        if (abs < 5 && abs2 < 5) {
                            try {
                                new CommonNotifyClickListener(NewsListActivity.this, new JSONObject(NewsListActivity.this.f9318i.mo21597b(list.get(NewsListActivity.this.f9314e.getCurrentItem() % list.size())))).mo26965c();
                                break;
                            } catch (JSONException e) {
                                e.printStackTrace();
                                break;
                            }
                        }
                }
                return false;
            }
        });
    }

    /* renamed from: b */
    private void m10999b() {
        this.f9311b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f9312c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f9313d = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f9312c.setText("新闻中心");
        this.f9313d.setVisibility(8);
        this.f9311b.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.edu.NewsListActivity.View$OnClickListenerC26265 */

            public void onClick(View view) {
                NewsListActivity.this.finish();
            }
        });
        this.f9334y = (LoadingView) findViewById(C2425R.C2427id.loadingview);
        startOfficeDrawable();
        this.f9334y.mo28298a(new LoadingView.AbstractC3488a() {
            /* class com.gbcom.gwifi.functions.edu.NewsListActivity.C26276 */

            @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
            /* renamed from: a */
            public void mo24655a(View view) {
                NewsListActivity.this.startOfficeDrawable();
                NewsListActivity.this.f9323n.clear();
                NewsListActivity.this.f9321l.clear();
                if (NewsListActivity.this.f9315f != null) {
                    NewsListActivity.this.f9315f.removeAllViews();
                }
                NewsListActivity.this.f9327r = 1;
                NewsListActivity.this.f9317h = HttpUtil.m14269a(NewsListActivity.this.f9310a, "", NewsListActivity.this.f9327r, NewsListActivity.this);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.edu.NewsListActivity$a */
    public class C2628a extends PagerAdapter {

        /* renamed from: b */
        private final List<AllNews.DataBean.PhotoNewsListBean> f9345b;

        public C2628a(List<AllNews.DataBean.PhotoNewsListBean> list) {
            this.f9345b = list;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public int getCount() {
            if (this.f9345b == null) {
                return 0;
            }
            if (this.f9345b.size() == 1) {
                NewsListActivity.this.f9322m.setText(this.f9345b.get(0).getTitle());
                return 1;
            } else if (this.f9345b.size() != 0) {
                return 10000000;
            } else {
                return 0;
            }
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            ImageView imageView = new ImageView(NewsListActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            String img_url = this.f9345b.get(i % this.f9345b.size()).getImg_url();
            if (!TextUtils.isEmpty(img_url)) {
                ImageTools.m14149a(img_url, imageView, GBApplication.instance().options);
            }
            viewGroup.addView(imageView);
            return imageView;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    public void startOfficeDrawable() {
        this.f9334y.setVisibility(0);
        this.f9334y.mo28307i();
        this.f9334y.mo28313o();
        this.f9334y.mo28315q();
        if (this.f9334y.mo28310l()) {
            this.f9334y.mo28309k();
        }
        this.f9334y.mo28297a();
        this.f9334y.mo28302d();
        this.f9334y.mo28304f();
        this.f9334y.mo28300b();
    }

    public void stopOfficeDrawable() {
        this.f9334y.setVisibility(8);
        this.f9334y.mo28301c();
    }

    public void errWebDrawable() {
        this.f9334y.setVisibility(0);
        this.f9334y.mo28303e();
        this.f9334y.mo28301c();
        this.f9334y.mo28305g();
        this.f9334y.mo28306h();
        this.f9334y.mo28308j();
        this.f9334y.mo28312n();
        this.f9334y.mo28314p();
        this.f9326q.mo28400b();
    }
}
