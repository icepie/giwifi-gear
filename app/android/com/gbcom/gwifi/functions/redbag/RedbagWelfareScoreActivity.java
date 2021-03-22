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
import com.gbcom.gwifi.functions.redbag.domain.LuckyRedbagJoined;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagJoinedResponse;
import com.gbcom.gwifi.functions.redbag.p251a.CommonListAdapter;
import com.gbcom.gwifi.util.Constant;

public class RedbagWelfareScoreActivity extends GBActivity {

    /* renamed from: a */
    private RelativeLayout f11327a;

    /* renamed from: b */
    private TextView f11328b;

    /* renamed from: c */
    private TextView f11329c;

    /* renamed from: d */
    private ListView f11330d;

    /* renamed from: e */
    private LuckyRedbagJoinedResponse f11331e;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "福利红包个人战绩", C2425R.layout.redbag_activity_welfare_score, true);
        this.f11331e = (LuckyRedbagJoinedResponse) getIntent().getSerializableExtra("luckyRedbagJoinedResponse");
        m12567a();
    }

    /* renamed from: a */
    private void m12567a() {
        this.f11327a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11327a.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagWelfareScoreActivity.View$OnClickListenerC30571 */

            public void onClick(View view) {
                RedbagWelfareScoreActivity.this.finish();
            }
        });
        this.f11328b = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11329c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11328b.setText("福利红包个人战绩");
        this.f11329c.setVisibility(8);
        this.f11330d = (ListView) findViewById(C2425R.C2427id.f8357lv);
        this.f11330d.setAdapter((ListAdapter) new CommonListAdapter(this.f11331e.getResponse().getJoined(), C2425R.layout.redbag_score_item, new CommonListAdapter.AbstractC3060a<LuckyRedbagJoined>() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagWelfareScoreActivity.C30582 */

            /* renamed from: a */
            public void mo24662a(CommonListAdapter.C3061b bVar, final LuckyRedbagJoined luckyRedbagJoined, int i) {
                bVar.mo26112a(2131755200, "福利红包").mo26112a(C2425R.C2427id.tv_time, (CharSequence) luckyRedbagJoined.getCreateAt()).mo26112a(C2425R.C2427id.tv_balance, (CharSequence) (luckyRedbagJoined.getHitBeans() + Constant.f13309cr)).mo26111a(C2425R.C2427id.iv_go).setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagWelfareScoreActivity.C30582.View$OnClickListenerC30591 */

                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setAction("startLuckyActivity");
                        intent.putExtra("LuckyId", luckyRedbagJoined.getLuckyRedbagId());
                        intent.putExtra("isFromLuckyJoined", true);
                        RedbagWelfareScoreActivity.this.sendBroadcast(intent);
                    }
                });
            }
        }));
    }
}
