package com.gbcom.gwifi.functions.revenue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.functions.revenue.domain.SingleRedbagRanking;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

public class SingleRedbagRankingActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    OkRequestHandler<String> f11629a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.revenue.SingleRedbagRankingActivity.C31211 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            SingleRedbagRankingActivity.this.f11637i.setAdapter((ListAdapter) new C3122a());
            C3470v.m14563a(SingleRedbagRankingActivity.this, "网络异常,请检查网路!");
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == SingleRedbagRankingActivity.this.f11634f) {
                try {
                    if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                        SingleRedbagRanking.DataBean data = ((SingleRedbagRanking) SingleRedbagRankingActivity.this.f11635g.mo21588a(str, SingleRedbagRanking.class)).getData();
                        SingleRedbagRankingActivity.this.f11636h = data.getRankList();
                        SingleRedbagRankingActivity.this.f11637i.setAdapter((ListAdapter) new C3122a());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    private RelativeLayout f11630b;

    /* renamed from: c */
    private TextView f11631c;

    /* renamed from: d */
    private TextView f11632d;

    /* renamed from: e */
    private Object f11633e = "SingleRedbagRankingActivity";

    /* renamed from: f */
    private Request f11634f;

    /* renamed from: g */
    private Gson f11635g;

    /* renamed from: h */
    private List<SingleRedbagRanking.DataBean.RankListBean> f11636h;

    /* renamed from: i */
    private ListView f11637i;

    /* renamed from: j */
    private int[] f11638j = {C2425R.C2426drawable.singleredbag_first, C2425R.C2426drawable.singleredbag_second, C2425R.C2426drawable.singleredbag_third};

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "单人猜红包排行榜", C2425R.layout.single_redbag_activity_ranking, true);
        if (this.f11635g == null) {
            this.f11635g = GsonUtil.m14241a();
        }
        m12782c();
        m12778a();
        m12780b();
    }

    /* renamed from: a */
    private void m12778a() {
        this.f11637i = (ListView) findViewById(C2425R.C2427id.f8357lv);
    }

    /* renamed from: b */
    private void m12780b() {
        this.f11634f = HttpUtil.m14342n(this, this.f11629a, this.f11633e);
    }

    /* renamed from: c */
    private void m12782c() {
        this.f11630b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11630b.setOnClickListener(this);
        this.f11631c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11632d = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11631c.setText("排行榜");
        this.f11632d.setVisibility(8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.revenue.SingleRedbagRankingActivity$a */
    public class C3122a extends BaseAdapter {
        private C3122a() {
        }

        public int getCount() {
            return (SingleRedbagRankingActivity.this.f11636h == null ? 0 : SingleRedbagRankingActivity.this.f11636h.size()) + 1;
        }

        public Object getItem(int i) {
            if (i == 0) {
                return "first item";
            }
            if (SingleRedbagRankingActivity.this.f11636h == null) {
                return null;
            }
            return (SingleRedbagRanking.DataBean.RankListBean) SingleRedbagRankingActivity.this.f11636h.get(i - 1);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.single_redbag_item, (ViewGroup) null);
            Object item = getItem(i);
            TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.tv_ranking);
            TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.tv_player);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.tv_hitbeans);
            ImageView imageView = (ImageView) inflate.findViewById(2131755361);
            if (i == 0) {
                textView.setBackgroundColor(SingleRedbagRankingActivity.this.getResources().getColor(C2425R.color.transparent));
                textView.getPaint().setFakeBoldText(true);
                textView2.getPaint().setFakeBoldText(true);
                textView3.getPaint().setFakeBoldText(true);
            } else {
                SingleRedbagRanking.DataBean.RankListBean rankListBean = (SingleRedbagRanking.DataBean.RankListBean) item;
                if (i <= 3) {
                    textView.setVisibility(8);
                    imageView.setVisibility(0);
                    imageView.setImageResource(SingleRedbagRankingActivity.this.f11638j[i - 1]);
                } else {
                    textView.setText(rankListBean.getRank() + "");
                    textView.setTextColor(SingleRedbagRankingActivity.this.getResources().getColor(2131624113));
                }
                textView2.setText(rankListBean.getNickName());
                textView3.setText(rankListBean.getTotalGainBeans() + "");
            }
            return inflate;
        }
    }
}
