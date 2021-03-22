package com.gbcom.gwifi.functions.redbag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.functions.redbag.domain.RedbagJoined;
import com.gbcom.gwifi.functions.redbag.msg.RedbagJoinedResponse;
import com.gbcom.gwifi.functions.redbag.p251a.CommonListAdapter;
import com.gbcom.gwifi.util.Constant;

public class RedbagScoreActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private RelativeLayout f11300a;

    /* renamed from: b */
    private TextView f11301b;

    /* renamed from: c */
    private TextView f11302c;

    /* renamed from: d */
    private ListView f11303d;

    /* renamed from: e */
    private RedbagJoinedResponse f11304e;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "", C2425R.layout.redbag_activity_score, true);
        this.f11304e = (RedbagJoinedResponse) getIntent().getSerializableExtra("redbagJoinedResponse");
        m12549a();
    }

    /* renamed from: a */
    private void m12549a() {
        this.f11300a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11300a.setOnClickListener(this);
        this.f11301b = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11302c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11301b.setText("抢红包个人战绩");
        this.f11302c.setVisibility(8);
        this.f11303d = (ListView) findViewById(C2425R.C2427id.f8357lv);
        this.f11303d.setAdapter((ListAdapter) new CommonListAdapter(this.f11304e.getResponse().getJoined(), C2425R.layout.redbag_score_item, new CommonListAdapter.AbstractC3060a<RedbagJoined>() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagScoreActivity.C30491 */

            /* renamed from: a */
            public void mo24662a(CommonListAdapter.C3061b bVar, final RedbagJoined redbagJoined, final int i) {
                bVar.mo26112a(2131755200, (CharSequence) (redbagJoined.getRedName() + "红包场")).mo26112a(C2425R.C2427id.tv_time, (CharSequence) redbagJoined.getCreateAt()).mo26112a(C2425R.C2427id.tv_balance, (CharSequence) (redbagJoined.getHitBeans() + Constant.f13309cr)).mo26111a(C2425R.C2427id.iv_go).setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagScoreActivity.C30491.View$OnClickListenerC30501 */

                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setAction("startactivity");
                        intent.putExtra("redId", RedbagScoreActivity.this.f11304e.getResponse().getJoined().get(i).getRedId());
                        intent.putExtra("roundNo", RedbagScoreActivity.this.f11304e.getResponse().getJoined().get(i).getRoundNo());
                        intent.putExtra("redName", redbagJoined.getRedName());
                        intent.putExtra("totalBeans", redbagJoined.getTotalBeans() + (redbagJoined.getTotalBeans() / 5));
                        RedbagScoreActivity.this.sendBroadcast(intent);
                    }
                });
            }
        }));
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
