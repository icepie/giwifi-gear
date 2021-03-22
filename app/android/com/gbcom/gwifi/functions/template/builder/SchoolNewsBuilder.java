package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.content.Intent;
import android.support.p009v4.app.FragmentManager;
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
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.edu.NewsListActivity;
import com.gbcom.gwifi.functions.edu.domain.TopNews;
import com.gbcom.gwifi.functions.template.p252a.CommonNotifyClickListener;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.template.builder.v */
public class SchoolNewsBuilder implements ViewBuilder {

    /* renamed from: a */
    OkRequestHandler f11896a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.builder.SchoolNewsBuilder.C31672 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == SchoolNewsBuilder.this.f11897b) {
                try {
                    if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                        SchoolNewsBuilder.this.f11900e = ((TopNews) SchoolNewsBuilder.this.f11898c.mo21588a(str, TopNews.class)).getData().getNews_list();
                        if (SchoolNewsBuilder.this.f11900e != null && SchoolNewsBuilder.this.f11900e.size() != 0) {
                            SchoolNewsBuilder.this.f11899d.setAdapter((ListAdapter) new C3169a(SchoolNewsBuilder.this.f11900e));
                            SchoolNewsBuilder.this.f11899d.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                /* class com.gbcom.gwifi.functions.template.builder.SchoolNewsBuilder.C31672.C31681 */

                                @Override // android.widget.AdapterView.OnItemClickListener
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                                    try {
                                        new CommonNotifyClickListener(SchoolNewsBuilder.this.f11903h, new JSONObject(SchoolNewsBuilder.this.f11898c.mo21597b((TopNews.DataBean.NewsListBean) SchoolNewsBuilder.this.f11900e.get(i)))).onClick(view);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
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
    private Request f11897b;

    /* renamed from: c */
    private Gson f11898c;

    /* renamed from: d */
    private ListView f11899d;

    /* renamed from: e */
    private List<TopNews.DataBean.NewsListBean> f11900e;

    /* renamed from: f */
    private LinearLayout f11901f;

    /* renamed from: g */
    private View f11902g;

    /* renamed from: h */
    private Context f11903h;

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(final Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        this.f11903h = context;
        this.f11902g = LayoutInflater.from(context).inflate(C2425R.layout.layout_schoolnews, viewGroup, false);
        ((TextView) this.f11902g.findViewById(C2425R.C2427id.tv_school)).setText(jSONObject.getString("child_layout_title"));
        if (this.f11898c == null) {
            this.f11898c = GsonUtil.m14241a();
        }
        this.f11899d = (ListView) this.f11902g.findViewById(C2425R.C2427id.f8357lv);
        this.f11899d.setFocusable(false);
        this.f11901f = (LinearLayout) this.f11902g.findViewById(C2425R.C2427id.ll_top);
        this.f11901f.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.builder.SchoolNewsBuilder.View$OnClickListenerC31661 */

            public void onClick(View view) {
                context.startActivity(new Intent(context, NewsListActivity.class));
            }
        });
        this.f11897b = HttpUtil.m14270a(this.f11896a, "", context);
        return this.f11902g;
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.template.builder.v$a */
    /* compiled from: SchoolNewsBuilder */
    public class C3169a extends BaseAdapter {

        /* renamed from: b */
        private final List<TopNews.DataBean.NewsListBean> f11909b;

        public C3169a(List<TopNews.DataBean.NewsListBean> list) {
            this.f11909b = list;
        }

        public int getCount() {
            if (this.f11909b == null) {
                return 0;
            }
            return this.f11909b.size();
        }

        public Object getItem(int i) {
            return this.f11909b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            TopNews.DataBean.NewsListBean newsListBean = this.f11909b.get(i);
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
            return view;
        }
    }
}
