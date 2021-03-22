package com.gbcom.gwifi.functions.redbag;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.functions.redbag.domain.HitRecord;
import com.gbcom.gwifi.functions.redbag.msg.RedbagDetailResponse;
import com.gbcom.gwifi.functions.redbag.p251a.CommonListAdapter;
import com.gbcom.gwifi.util.Constant;
import java.util.ArrayList;

public class RedbagResultActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private RelativeLayout f11256a;

    /* renamed from: b */
    private TextView f11257b;

    /* renamed from: c */
    private TextView f11258c;

    /* renamed from: d */
    private String f11259d;

    /* renamed from: e */
    private RedbagDetailResponse f11260e;

    /* renamed from: f */
    private int f11261f;

    /* renamed from: g */
    private ArrayList<HitRecord> f11262g;

    /* renamed from: h */
    private int f11263h;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "抢红包", C2425R.layout.redbag_activity_result, true);
        m12525c();
        Intent intent = getIntent();
        this.f11259d = intent.getStringExtra("redName");
        ((TextView) findViewById(C2425R.C2427id.tv_redbagname)).setText(this.f11259d + "红包场");
        this.f11261f = intent.getIntExtra("totalBeans", -1);
        String stringExtra = intent.getStringExtra("source");
        if (TextUtils.equals(stringExtra, "lastRound")) {
            this.f11263h = intent.getIntExtra("mUid", -1);
            this.f11262g = (ArrayList) intent.getSerializableExtra("lastRound");
            m12524b();
        }
        if (TextUtils.equals(stringExtra, "fromPersonal")) {
            this.f11260e = (RedbagDetailResponse) intent.getSerializableExtra("redbagDetailResponse");
            m12523a();
        }
    }

    /* renamed from: a */
    private void m12523a() {
        this.f11260e.getResponse().getHitBeans();
        int isJoin = this.f11260e.getResponse().getIsJoin();
        TextView textView = (TextView) findViewById(C2425R.C2427id.tv_score_get);
        if (isJoin == 1) {
            textView.setText("+" + this.f11260e.getResponse().getHitBeans() + Constant.f13309cr);
        } else {
            textView.setText("您未参与本次红包");
        }
        ListView listView = (ListView) findViewById(C2425R.C2427id.lv_redbag_result);
        listView.setVerticalScrollBarEnabled(false);
        listView.setAdapter((ListAdapter) new CommonListAdapter(this.f11260e.getResponse().getHitRecord(), C2425R.layout.redbag_listview_common_item, new CommonListAdapter.AbstractC3060a<HitRecord>() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagResultActivity.C30421 */

            /* renamed from: a */
            public void mo24662a(CommonListAdapter.C3061b bVar, HitRecord hitRecord, int i) {
                bVar.mo26112a(C2425R.C2427id.tv_redbag_result_name, (CharSequence) hitRecord.getNickName()).mo26112a(C2425R.C2427id.tv_redbag_result_time, (CharSequence) hitRecord.getCreateAt()).mo26112a(C2425R.C2427id.tv_redbag_result_score, (CharSequence) ("+" + hitRecord.getHitBeans() + Constant.f13309cr)).mo26113a(C2425R.C2427id.iv_avater, hitRecord.getFaceUrl());
                if (hitRecord.getHitBeans() < RedbagResultActivity.this.f11261f) {
                    bVar.mo26114a(C2425R.C2427id.tv_redbag_result_least, true).mo26112a(C2425R.C2427id.tv_redbag_result_least, (CharSequence) ("最少，-" + RedbagResultActivity.this.f11261f + Constant.f13309cr));
                }
            }
        }));
    }

    /* renamed from: b */
    private void m12524b() {
        TextView textView = (TextView) findViewById(C2425R.C2427id.tv_score_get);
        for (int i = 0; i < this.f11262g.size(); i++) {
            if (this.f11262g.get(i).getAccountId() == this.f11263h) {
                textView.setText("+" + this.f11262g.get(i).getHitBeans() + Constant.f13309cr);
            }
        }
        ListView listView = (ListView) findViewById(C2425R.C2427id.lv_redbag_result);
        listView.setVerticalScrollBarEnabled(false);
        listView.setAdapter((ListAdapter) new CommonListAdapter(this.f11262g, C2425R.layout.redbag_listview_common_item, new CommonListAdapter.AbstractC3060a<HitRecord>() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagResultActivity.C30432 */

            /* renamed from: a */
            public void mo24662a(CommonListAdapter.C3061b bVar, HitRecord hitRecord, int i) {
                bVar.mo26112a(C2425R.C2427id.tv_redbag_result_name, (CharSequence) hitRecord.getNickName()).mo26112a(C2425R.C2427id.tv_redbag_result_time, (CharSequence) hitRecord.getCreateAt()).mo26112a(C2425R.C2427id.tv_redbag_result_score, (CharSequence) ("+" + hitRecord.getHitBeans() + Constant.f13309cr)).mo26113a(C2425R.C2427id.iv_avater, hitRecord.getFaceUrl());
                if (hitRecord.getHitBeans() < RedbagResultActivity.this.f11261f) {
                    bVar.mo26114a(C2425R.C2427id.tv_redbag_result_least, true).mo26112a(C2425R.C2427id.tv_redbag_result_least, (CharSequence) ("最少，-" + RedbagResultActivity.this.f11261f + Constant.f13309cr));
                }
            }
        }));
    }

    /* renamed from: c */
    private void m12525c() {
        this.f11256a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11256a.setOnClickListener(this);
        this.f11257b = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11258c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11257b.setText("抢红包");
        this.f11258c.setVisibility(8);
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
}
