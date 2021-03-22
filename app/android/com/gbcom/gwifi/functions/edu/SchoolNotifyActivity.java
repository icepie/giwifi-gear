package com.gbcom.gwifi.functions.edu;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.functions.edu.domain.AllNotify;
import com.gbcom.gwifi.functions.redbag.p251a.CommonListAdapter;
import com.gbcom.gwifi.functions.template.p252a.CommonNotifyClickListener;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

public class SchoolNotifyActivity extends GBActivity {

    /* renamed from: a */
    OkRequestHandler f9348a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.edu.SchoolNotifyActivity.C26334 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == SchoolNotifyActivity.this.f9353f) {
                try {
                    if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                        SchoolNotifyActivity.this.f9355h = (AllNotify) SchoolNotifyActivity.this.f9354g.mo21588a(str, AllNotify.class);
                        SchoolNotifyActivity.this.m11027c();
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
    private RelativeLayout f9349b;

    /* renamed from: c */
    private TextView f9350c;

    /* renamed from: d */
    private TextView f9351d;

    /* renamed from: e */
    private ListView f9352e;

    /* renamed from: f */
    private Request f9353f;

    /* renamed from: g */
    private Gson f9354g;

    /* renamed from: h */
    private AllNotify f9355h;

    /* renamed from: i */
    private DisplayImageOptions f9356i;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "学校通知", C2425R.layout.school_notify_activity, true);
        m11023a();
        if (this.f9354g == null) {
            this.f9354g = GsonUtil.m14241a();
        }
        m11025b();
    }

    /* renamed from: a */
    private void m11023a() {
        this.f9349b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f9350c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f9351d = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f9350c.setText("通知");
        this.f9351d.setVisibility(8);
        this.f9349b.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.edu.SchoolNotifyActivity.View$OnClickListenerC26301 */

            public void onClick(View view) {
                SchoolNotifyActivity.this.finish();
            }
        });
        this.f9356i = new DisplayImageOptions.Builder().showImageForEmptyUri(C2425R.C2426drawable.notify_avater).showImageOnFail(C2425R.C2426drawable.notify_avater).cacheInMemory(false).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
    }

    /* renamed from: b */
    private void m11025b() {
        this.f9353f = HttpUtil.m14288b(1, this.f9348a, "");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11027c() {
        this.f9352e = (ListView) findViewById(C2425R.C2427id.f8357lv);
        if (this.f9355h != null) {
            this.f9352e.setAdapter((ListAdapter) new CommonListAdapter(this.f9355h.getData().getNotify_list(), C2425R.layout.school_notify_item_lv, new CommonListAdapter.AbstractC3060a<AllNotify.DataBean.NotifyListBean>() {
                /* class com.gbcom.gwifi.functions.edu.SchoolNotifyActivity.C26312 */

                /* renamed from: a */
                public void mo24662a(CommonListAdapter.C3061b bVar, AllNotify.DataBean.NotifyListBean notifyListBean, int i) {
                    bVar.mo26111a(2131755381);
                    bVar.mo26112a(2131755200, (CharSequence) notifyListBean.getPublish_department()).mo26112a(C2425R.C2427id.tv_time, (CharSequence) notifyListBean.getCreate_time());
                    ImageTools.m14149a(notifyListBean.getPublish_department_avatar(), (ImageView) bVar.mo26111a(C2425R.C2427id.iv_avater), SchoolNotifyActivity.this.f9356i);
                    ((TextView) bVar.mo26111a(2131755412)).setText(notifyListBean.getTitle());
                    ImageView imageView = (ImageView) bVar.mo26111a(C2425R.C2427id.iv_latest);
                    if (i == 0) {
                        imageView.setVisibility(0);
                    }
                }
            }));
            this.f9352e.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                /* class com.gbcom.gwifi.functions.edu.SchoolNotifyActivity.C26323 */

                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    try {
                        new CommonNotifyClickListener(SchoolNotifyActivity.this, new JSONObject(SchoolNotifyActivity.this.f9354g.mo21597b(SchoolNotifyActivity.this.f9355h.getData().getNotify_list().get(i)))).onClick(view);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
