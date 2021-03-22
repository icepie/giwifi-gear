package com.gbcom.gwifi.functions.product;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.product.domain.Video;
import com.gbcom.gwifi.functions.product.p247a.SwingLeftInAnimationAdapter;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.ResourceParse;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.PullToRefreshView;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import p041c.Request;

public class BannerVideoListActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private static final String f10237a = "BannerVideoListActivity";

    /* renamed from: b */
    private static final Object f10238b = f10237a;

    /* renamed from: c */
    private RelativeLayout f10239c;

    /* renamed from: d */
    private TextView f10240d;

    /* renamed from: e */
    private TextView f10241e;

    /* renamed from: f */
    private ImageView f10242f;

    /* renamed from: g */
    private ImageView f10243g;

    /* renamed from: h */
    private ImageView f10244h;

    /* renamed from: i */
    private PullToRefreshView f10245i;

    /* renamed from: j */
    private long f10246j = -1;

    /* renamed from: k */
    private final long f10247k = 3000;

    /* renamed from: l */
    private SimpleDateFormat f10248l;

    /* renamed from: m */
    private ListView f10249m;

    /* renamed from: n */
    private List<Video> f10250n = new ArrayList();

    /* renamed from: o */
    private Request f10251o;

    /* renamed from: p */
    private boolean f10252p = false;

    /* renamed from: q */
    private int f10253q = -1;

    /* renamed from: r */
    private BaseAdapter f10254r = new BaseAdapter() {
        /* class com.gbcom.gwifi.functions.product.BannerVideoListActivity.C28492 */

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return BannerVideoListActivity.this.f10250n.get(i);
        }

        public int getCount() {
            return BannerVideoListActivity.this.f10250n.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Video video = (Video) BannerVideoListActivity.this.f10250n.get(i);
            View inflate = BannerVideoListActivity.this.getLayoutInflater().inflate(C2425R.layout.banner_video_item, viewGroup, false);
            ((TextView) inflate.findViewById(16908310)).setText(video.getTitle());
            ((TextView) inflate.findViewById(16908308)).setText(C3467s.m14506a(C3467s.m14509b(video.getTags()), "  "));
            ((TextView) inflate.findViewById(C2425R.C2427id.video_size)).setText(C3467s.m14501a(video.getFileTotalSize().longValue()));
            ((TextView) inflate.findViewById(C2425R.C2427id.download_num)).setText("已下载" + C3467s.m14510c((long) video.getDownloadCount().intValue()));
            if (video.getSpeed().intValue() == 1) {
                inflate.findViewById(C2425R.C2427id.speed_icon).setVisibility(0);
                inflate.findViewById(C2425R.C2427id.speed_icon2).setVisibility(0);
            }
            ImageView imageView = (ImageView) inflate.findViewById(2131755267);
            inflate.findViewById(C2425R.C2427id.detail_btn).setTag(Integer.valueOf(i));
            inflate.findViewById(C2425R.C2427id.detail_btn).setOnClickListener(BannerVideoListActivity.this);
            if (video.getImageUrl() == null || video.getImageUrl().length() <= 5) {
                imageView.setVisibility(8);
            } else {
                ImageTools.m14149a(video.getImageUrl(), imageView, GBApplication.instance().options);
            }
            inflate.setTag(Integer.valueOf(i));
            inflate.setOnClickListener(BannerVideoListActivity.this);
            return inflate;
        }
    };

    /* renamed from: s */
    private OkRequestHandler<String> f10255s = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.BannerVideoListActivity.C28503 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            BannerVideoListActivity.this.showProgressDialog("正在加载…");
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (!BannerVideoListActivity.this.isFinishing()) {
                BannerVideoListActivity.this.f10245i.mo28398a("更新于:" + BannerVideoListActivity.this.f10248l.format(new Date(System.currentTimeMillis())));
                if (BannerVideoListActivity.this.f10251o == abVar) {
                    Log.m14398b(BannerVideoListActivity.f10237a, exc.getMessage());
                    GBActivity.showMessageToast("加载数据失败！");
                    BannerVideoListActivity.this.dismissProgressDialog();
                    BannerVideoListActivity.this.finish();
                }
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (!BannerVideoListActivity.this.isFinishing()) {
                BannerVideoListActivity.this.dismissProgressDialog();
                BannerVideoListActivity.this.f10245i.mo28398a("更新于:" + BannerVideoListActivity.this.f10248l.format(new Date(System.currentTimeMillis())));
                if (abVar == BannerVideoListActivity.this.f10251o) {
                    HashMap<String, Object> d = ResourceParse.m14452d(str.getBytes());
                    if (d == null) {
                        GBActivity.showMessageToast("抱歉,数据解析失败！");
                        BannerVideoListActivity.this.finish();
                    } else if (!ResultCode.m14475a((Integer) d.get(AbstractC5718b.JSON_ERRORCODE))) {
                        Log.m14403e(BannerVideoListActivity.f10237a, "BannerAppListActivity->onRequestFinish resultCode:" + d.get(AbstractC5718b.JSON_ERRORCODE));
                        GBActivity.showMessageToast("亲！返回码错误！");
                        BannerVideoListActivity.this.finish();
                    } else {
                        ArrayList arrayList = new ArrayList();
                        Iterator it = ((ArrayList) ((HashMap) d.get("data")).get("products")).iterator();
                        while (it.hasNext()) {
                            HashMap hashMap = (HashMap) it.next();
                            Video video = new Video();
                            video.setTitle((String) hashMap.get("productName"));
                            video.setImageUrl((String) hashMap.get("imgUrl"));
                            video.setDownloadCount((Integer) hashMap.get("downloadCount"));
                            video.setSid((Integer) hashMap.get("productId"));
                            video.setSpeed((Integer) hashMap.get("isSpeed"));
                            video.setFileTotalSize(Long.valueOf((long) (((Double) hashMap.get("fileTotalSize")).doubleValue() * 1024.0d)));
                            video.setTV(Boolean.valueOf(((Integer) hashMap.get("videoSet")).intValue() == 2));
                            video.setSid((Integer) hashMap.get("productId"));
                            video.setTags(C3467s.m14507a((List<String[]>) ((ArrayList) hashMap.get("tags"))));
                            arrayList.add(video);
                        }
                        BannerVideoListActivity.this.f10250n.clear();
                        BannerVideoListActivity.this.f10250n = arrayList;
                        if (BannerVideoListActivity.this.f10250n.size() == 0) {
                            GBActivity.showMessageToast("已经加载完了");
                        } else {
                            BannerVideoListActivity.this.f10254r.notifyDataSetChanged();
                        }
                    }
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "顶部影视专题界面", C2425R.layout.banner_list_activity, true);
        m11734b();
        update();
        m11732a();
    }

    /* renamed from: a */
    private void m11732a() {
        SwingLeftInAnimationAdapter cVar = new SwingLeftInAnimationAdapter(this.f10254r);
        cVar.mo24867a((AbsListView) this.f10249m);
        this.f10249m.setAdapter((ListAdapter) cVar);
    }

    /* renamed from: b */
    private void m11734b() {
        this.f10253q = getIntent().getIntExtra("catId", -1);
        this.f10239c = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10239c.setOnClickListener(this);
        this.f10241e = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10240d = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10241e.setText(getIntent().getStringExtra("title"));
        this.f10240d.setText((CharSequence) null);
        this.f10245i = (PullToRefreshView) findViewById(C2425R.C2427id.main_pull_refresh_view);
        this.f10248l = new SimpleDateFormat("MM-dd HH:mm");
        this.f10245i.mo28397a(new PullToRefreshView.AbstractC3494b() {
            /* class com.gbcom.gwifi.functions.product.BannerVideoListActivity.C28481 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
            public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
                BannerVideoListActivity.this.update();
            }
        });
        this.f10250n = new ArrayList();
        this.f10249m = (ListView) findViewById(C2425R.C2427id.banner_lv);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            default:
                this.f10250n.get(((Integer) view.getTag()).intValue()).getSid().intValue();
                return;
        }
    }

    public void update() {
        if (System.currentTimeMillis() - this.f10246j < 3000) {
            this.f10245i.mo28398a("更新于:" + this.f10248l.format(new Date(System.currentTimeMillis())));
            return;
        }
        this.f10246j = System.currentTimeMillis();
        if (this.f10253q != -1) {
            this.f10251o = HttpUtil.m14289b(this, this.f10253q, this.f10255s, f10238b);
        }
    }
}
