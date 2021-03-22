package com.gbcom.gwifi.functions.revenue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.ResourceParse;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.LoadingView;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.util.ArrayList;
import java.util.HashMap;
import p041c.Request;

public class FindActivity extends GBActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    /* renamed from: a */
    private RelativeLayout f11463a;

    /* renamed from: b */
    private TextView f11464b;

    /* renamed from: c */
    private TextView f11465c;

    /* renamed from: d */
    private Request f11466d;

    /* renamed from: e */
    private GridView f11467e;

    /* renamed from: f */
    private ArrayList<HashMap<String, Object>> f11468f = new ArrayList<>();

    /* renamed from: g */
    private C3089a f11469g;

    /* renamed from: h */
    private LoadingView f11470h;

    /* renamed from: i */
    private int f11471i;

    /* renamed from: j */
    private OkRequestHandler<String> f11472j = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.revenue.FindActivity.C30871 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            FindActivity.this.startOfficeDrawable();
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (!FindActivity.this.isFinishing()) {
                FindActivity.this.errOfficeDrawable();
                GBActivity.showMessageToast("请检查网络");
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            HashMap<String, Object> w = ResourceParse.m14472w(str.getBytes());
            if (ResultCode.m14475a((Integer) w.get(AbstractC5718b.JSON_ERRORCODE))) {
                FindActivity.this.stopOfficeDrawable();
                FindActivity.this.dismissProgressDialog();
                HashMap hashMap = (HashMap) w.get("data");
                if (hashMap.containsKey("findList")) {
                    FindActivity.this.f11468f = (ArrayList) hashMap.get("findList");
                    if (FindActivity.this.f11468f.size() == 0) {
                        GBActivity.showMessageToast("当前活动尚未开放!请稍后重试!");
                        FindActivity.this.errOfficeDrawable();
                        return;
                    }
                    FindActivity.this.f11469g.notifyDataSetChanged();
                    return;
                }
                return;
            }
            FindActivity.this.errOfficeDrawable();
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: k */
    private LoadingView.AbstractC3488a f11473k = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.revenue.FindActivity.C30882 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            FindActivity.this.m12655a();
        }
    };

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "发现界面", C2425R.layout.find_activity_main, true);
        this.f11471i = DensityUtil.m14127a(this);
        m12657b();
        m12655a();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12655a() {
        this.f11466d = HttpUtil.m14336l(this, this.f11472j, "");
    }

    /* renamed from: b */
    private void m12657b() {
        this.f11463a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11463a.setOnClickListener(this);
        this.f11465c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11464b = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11465c.setText("领旺豆");
        this.f11464b.setText("");
        this.f11470h = (LoadingView) findViewById(C2425R.C2427id.loading_view);
        this.f11470h.mo28298a(this.f11473k);
        this.f11467e = (GridView) findViewById(2131755339);
        this.f11469g = new C3089a();
        this.f11467e.setAdapter((ListAdapter) this.f11469g);
        this.f11467e.setOnItemClickListener(this);
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

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        GBActivity.openFindBackWebView((String) this.f11468f.get(i).get("wapUrl"), "领旺豆", ((Integer) this.f11468f.get(i).get("second")).intValue(), ((Integer) this.f11468f.get(i).get("hitBeans")).intValue(), ((Integer) this.f11468f.get(i).get("id")).intValue(), true, 0);
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.revenue.FindActivity$a */
    public class C3089a extends BaseAdapter {
        private C3089a() {
        }

        public int getCount() {
            return FindActivity.this.f11468f.size();
        }

        public Object getItem(int i) {
            return FindActivity.this.f11468f.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            C3090b bVar;
            if (view != null) {
                bVar = (C3090b) view.getTag();
            } else {
                view = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.find_item_find, viewGroup, false);
                view.setLayoutParams(new AbsListView.LayoutParams((viewGroup.getWidth() / 2) - 20, viewGroup.getWidth() / 2));
                bVar = new C3090b();
                bVar.f11477a = (ImageView) view.findViewById(C2425R.C2427id.iv_item);
                bVar.f11478b = (TextView) view.findViewById(2131755156);
                bVar.f11479c = (TextView) view.findViewById(C2425R.C2427id.sub_title);
                view.setTag(bVar);
            }
            bVar.f11478b.setText("" + ((HashMap) FindActivity.this.f11468f.get(i)).get("title"));
            bVar.f11479c.setText("" + ((HashMap) FindActivity.this.f11468f.get(i)).get("subTitle"));
            ImageTools.m14149a(((HashMap) FindActivity.this.f11468f.get(i)).get("imgUrl") + "", bVar.f11477a, GBApplication.instance().options);
            return view;
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.revenue.FindActivity$b */
    private static class C3090b {

        /* renamed from: a */
        public ImageView f11477a;

        /* renamed from: b */
        public TextView f11478b;

        /* renamed from: c */
        public TextView f11479c;

        private C3090b() {
        }
    }

    public void startOfficeDrawable() {
        this.f11470h.mo28307i();
        this.f11470h.mo28313o();
        this.f11470h.mo28315q();
        if (this.f11470h.mo28310l()) {
            this.f11470h.mo28309k();
        }
        this.f11470h.mo28297a();
        this.f11470h.mo28302d();
        this.f11470h.mo28304f();
        this.f11470h.mo28300b();
    }

    public void errOfficeDrawable() {
        this.f11467e.setVisibility(8);
        this.f11470h.setVisibility(0);
        this.f11470h.mo28303e();
        this.f11470h.mo28301c();
        this.f11470h.mo28305g();
        this.f11470h.mo28306h();
        this.f11470h.mo28308j();
        this.f11470h.mo28312n();
        this.f11470h.mo28314p();
    }

    public void stopOfficeDrawable() {
        this.f11467e.setVisibility(0);
        this.f11470h.setVisibility(8);
        this.f11470h.mo28301c();
    }
}
