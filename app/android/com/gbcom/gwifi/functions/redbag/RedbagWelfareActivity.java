package com.gbcom.gwifi.functions.redbag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.redbag.domain.LuckyHitRecord;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagBettingNotify;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagDetailResponse;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagJoinedRequest;
import com.gbcom.gwifi.functions.redbag.p251a.CommonListAdapter;
import com.gbcom.gwifi.util.Constant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.apache.xpath.compiler.PsuedoNames;

public class RedbagWelfareActivity extends GBActivity {

    /* renamed from: a */
    private Executor f11309a = Executors.newFixedThreadPool(1);

    /* renamed from: b */
    private RelativeLayout f11310b;

    /* renamed from: c */
    private TextView f11311c;

    /* renamed from: d */
    private TextView f11312d;

    /* renamed from: e */
    private ListView f11313e;

    /* renamed from: f */
    private LuckyRedbagDetailResponse f11314f;

    /* renamed from: g */
    private LuckyRedbagDetailResponse.LuckyRedbagDetailResponseBody f11315g;

    /* renamed from: h */
    private int f11316h;

    /* renamed from: i */
    private int f11317i;

    /* renamed from: j */
    private boolean f11318j;

    /* renamed from: k */
    private BroadcastReceiver f11319k;

    /* renamed from: l */
    private BroadcastReceiver f11320l;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "福利红包个人战绩", C2425R.layout.redbag_activity_welfare, true);
        Intent intent = getIntent();
        this.f11314f = (LuckyRedbagDetailResponse) intent.getSerializableExtra("luckyRedbagDetailResponse");
        this.f11316h = intent.getIntExtra("mUid", -1);
        this.f11315g = this.f11314f.getResponse();
        this.f11320l = new BroadcastReceiver() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagWelfareActivity.C30511 */

            public void onReceive(Context context, Intent intent) {
                LuckyRedbagBettingNotify luckyRedbagBettingNotify = (LuckyRedbagBettingNotify) intent.getSerializableExtra("luckyRedbagBettingNotify");
                RedbagWelfareActivity.this.f11315g.setHasSale(RedbagWelfareActivity.this.f11315g.getHasSale() + 1);
                RedbagWelfareActivity.this.f11315g.setHasHitBeans(luckyRedbagBettingNotify.getNotify().getHasHitBeans());
                RedbagWelfareActivity.this.f11318j = true;
                ArrayList<LuckyHitRecord> hitRecord = RedbagWelfareActivity.this.f11315g.getHitRecord();
                LuckyHitRecord luckyHitRecord = new LuckyHitRecord();
                luckyHitRecord.setAccountId(luckyRedbagBettingNotify.getNotify().getAccountId());
                luckyHitRecord.setCreateAt(luckyRedbagBettingNotify.getNotify().getCreateAt());
                luckyHitRecord.setFaceUrl(luckyRedbagBettingNotify.getNotify().getFaceUrl());
                luckyHitRecord.setNickName(luckyRedbagBettingNotify.getNotify().getNickName());
                luckyHitRecord.setTenantName(luckyRedbagBettingNotify.getNotify().getTenantName());
                luckyHitRecord.setHitBeans(luckyRedbagBettingNotify.getNotify().getHitBeans());
                hitRecord.add(luckyHitRecord);
                RedbagWelfareActivity.this.m12556a();
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("lucky_notify");
        GBApplication.instance().registerReceiver(this.f11320l, intentFilter);
        this.f11319k = new BroadcastReceiver() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagWelfareActivity.C30522 */

            public void onReceive(Context context, Intent intent) {
                RedbagWelfareActivity.this.f11314f = (LuckyRedbagDetailResponse) intent.getSerializableExtra("luckyRedbagDetailResponse");
                RedbagWelfareActivity.this.f11315g = RedbagWelfareActivity.this.f11314f.getResponse();
                RedbagWelfareActivity.this.m12556a();
                GBApplication.instance().unregisterReceiver(RedbagWelfareActivity.this.f11319k);
                GBApplication.instance().unregisterReceiver(RedbagWelfareActivity.this.f11320l);
            }
        };
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("updateResult");
        GBApplication.instance().registerReceiver(this.f11319k, intentFilter2);
        m12558b();
        m12556a();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12556a() {
        this.f11313e = (ListView) findViewById(C2425R.C2427id.f8357lv);
        TextView textView = (TextView) findViewById(C2425R.C2427id.tv_balance);
        TextView textView2 = (TextView) findViewById(2131755200);
        if (this.f11314f.getResponse().getIsJoin() == 0) {
            textView2.setVisibility(8);
            textView.setText("已抢光");
        } else {
            textView.setText(this.f11314f.getResponse().getHitBeans() + "");
            textView2.setText(Constant.f13309cr);
        }
        TextView textView3 = (TextView) findViewById(C2425R.C2427id.tv_desc);
        int luckyCostTime = this.f11315g.getLuckyCostTime();
        this.f11315g.getLuckyCount();
        if (this.f11315g.getHasSale() < this.f11315g.getLuckyCount()) {
            textView3.setText("共" + this.f11315g.getHasSale() + PsuedoNames.PSEUDONAME_ROOT + this.f11315g.getLuckyCount() + "个红包,共" + this.f11315g.getHasHitBeans() + PsuedoNames.PSEUDONAME_ROOT + this.f11315g.getLuckyTotalBeans() + "个旺豆");
        } else {
            String a = m12555a(luckyCostTime);
            if (this.f11318j) {
                this.f11318j = false;
                textView3.setText("共" + this.f11315g.getHasSale() + PsuedoNames.PSEUDONAME_ROOT + this.f11315g.getLuckyCount() + "个红包,已被抢光");
            } else {
                textView3.setText("共" + this.f11315g.getHasSale() + PsuedoNames.PSEUDONAME_ROOT + this.f11315g.getLuckyCount() + "个红包," + a + "被抢光");
            }
        }
        this.f11313e.setAdapter((ListAdapter) new CommonListAdapter(this.f11315g.getHitRecord(), C2425R.layout.redbag_item_welfare, new CommonListAdapter.AbstractC3060a<LuckyHitRecord>() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagWelfareActivity.C30533 */

            /* renamed from: a */
            public void mo24662a(CommonListAdapter.C3061b bVar, LuckyHitRecord luckyHitRecord, int i) {
                bVar.mo26113a(C2425R.C2427id.iv_avater, luckyHitRecord.getFaceUrl()).mo26112a(C2425R.C2427id.tv_redbag_result_time, (CharSequence) luckyHitRecord.getCreateAt()).mo26112a(C2425R.C2427id.tv_redbag_result_name, (CharSequence) luckyHitRecord.getNickName()).mo26112a(C2425R.C2427id.tv_redbag_result_score, (CharSequence) (luckyHitRecord.getHitBeans() + Constant.f13309cr));
                if (luckyHitRecord.getBestLucky() == 1) {
                    bVar.mo26114a(C2425R.C2427id.tv_redbag_result_least, true);
                }
            }
        }));
    }

    /* renamed from: a */
    private String m12555a(int i) {
        if (i < 60) {
            return i + "秒";
        }
        if (i >= 3600) {
            return (i / 3600) + "小时";
        }
        if (i == 0) {
            return "已";
        }
        return (i / 60) + "分钟";
    }

    /* renamed from: b */
    private void m12558b() {
        this.f11310b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11310b.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagWelfareActivity.View$OnClickListenerC30544 */

            public void onClick(View view) {
                RedbagWelfareActivity.this.finish();
            }
        });
        this.f11311c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11312d = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11311c.setText("福利红包");
        this.f11312d.setVisibility(8);
        TextView textView = (TextView) findViewById(C2425R.C2427id.tv_myscore);
        textView.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagWelfareActivity.View$OnClickListenerC30555 */

            public void onClick(View view) {
                RedbagWelfareActivity.this.f11309a.execute(new Runnable() {
                    /* class com.gbcom.gwifi.functions.redbag.RedbagWelfareActivity.View$OnClickListenerC30555.RunnableC30561 */

                    @Override // java.lang.Runnable
                    public void run() {
                        LuckyRedbagJoinedRequest luckyRedbagJoinedRequest = new LuckyRedbagJoinedRequest();
                        luckyRedbagJoinedRequest.setAccountId(RedbagWelfareActivity.this.f11316h);
                        luckyRedbagJoinedRequest.setStart(0);
                        luckyRedbagJoinedRequest.setSize(20);
                        RedbagMainActivity.channel.writeAndFlush(luckyRedbagJoinedRequest);
                    }
                });
            }
        });
        SpannableString spannableString = new SpannableString("查看我的福利红包");
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 33);
        textView.setText(spannableString);
        List<GBActivity> activitiesList = GBApplication.instance().getActivitiesList();
        for (int i = 0; i < activitiesList.size(); i++) {
            if (activitiesList.get(i) instanceof RedbagWelfareActivity) {
                this.f11317i++;
            }
        }
        if (this.f11317i > 0) {
            textView.setVisibility(8);
        }
    }
}
