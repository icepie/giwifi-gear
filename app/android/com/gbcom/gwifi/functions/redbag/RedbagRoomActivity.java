package com.gbcom.gwifi.functions.redbag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.redbag.domain.HitRecord;
import com.gbcom.gwifi.functions.redbag.domain.NeedAdd;
import com.gbcom.gwifi.functions.redbag.msg.NotifyMsg;
import com.gbcom.gwifi.functions.redbag.msg.RedbagDetailRequest;
import com.gbcom.gwifi.functions.redbag.msg.RedbagDetailResponse;
import com.gbcom.gwifi.functions.redbag.msg.RedbagNotifyResult;
import com.gbcom.gwifi.functions.redbag.p251a.CommonListAdapter;
import com.gbcom.gwifi.p221a.p222a.ClientStarter;
import com.gbcom.gwifi.util.Constant;
import com.p136b.p137a.Gson;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import p419io.netty.channel.Channel;
import p419io.netty.channel.ChannelHandlerContext;

public class RedbagRoomActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: A */
    private RedbagDetailResponse f11266A;

    /* renamed from: B */
    private BroadcastReceiver f11267B;

    /* renamed from: C */
    private int f11268C;

    /* renamed from: a */
    ArrayList<HitRecord> f11269a = new ArrayList<>();

    /* renamed from: b */
    private RelativeLayout f11270b;

    /* renamed from: c */
    private TextView f11271c;

    /* renamed from: d */
    private TextView f11272d;

    /* renamed from: e */
    private String f11273e;

    /* renamed from: f */
    private RedbagDetailResponse f11274f;

    /* renamed from: g */
    private BroadcastReceiver f11275g;

    /* renamed from: h */
    private boolean f11276h;

    /* renamed from: i */
    private int f11277i;

    /* renamed from: j */
    private ChannelHandlerContext f11278j;

    /* renamed from: k */
    private Channel f11279k;

    /* renamed from: l */
    private Executor f11280l = Executors.newFixedThreadPool(2);

    /* renamed from: m */
    private int f11281m;

    /* renamed from: n */
    private String f11282n;

    /* renamed from: o */
    private Gson f11283o;

    /* renamed from: p */
    private ClientStarter f11284p;

    /* renamed from: q */
    private CommonListAdapter f11285q;

    /* renamed from: r */
    private TextView f11286r;

    /* renamed from: s */
    private ListView f11287s;

    /* renamed from: t */
    private BroadcastReceiver f11288t;

    /* renamed from: u */
    private boolean f11289u;

    /* renamed from: v */
    private boolean f11290v;

    /* renamed from: w */
    private boolean f11291w;

    /* renamed from: x */
    private int f11292x;

    /* renamed from: y */
    private ArrayList<HitRecord> f11293y = new ArrayList<>();

    /* renamed from: z */
    private boolean f11294z;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "抢红包", C2425R.layout.redbag_activity_room2, true);
        Intent intent = getIntent();
        this.f11273e = intent.getStringExtra("redName");
        this.f11277i = intent.getIntExtra("uid", -1);
        this.f11281m = intent.getIntExtra("resId", -1);
        this.f11282n = intent.getStringExtra("redRound");
        this.f11292x = intent.getIntExtra("totalBeans", -1);
        this.f11268C = intent.getIntExtra("totalCount", -1);
        m12537c();
        m12533a();
    }

    /* renamed from: a */
    private void m12533a() {
        this.f11287s = (ListView) findViewById(C2425R.C2427id.lv_redbag_result);
        this.f11287s.setVerticalScrollBarEnabled(false);
        this.f11288t = new BroadcastReceiver() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagRoomActivity.C30441 */

            public void onReceive(Context context, Intent intent) {
                RedbagNotifyResult redbagNotifyResult = (RedbagNotifyResult) intent.getSerializableExtra("notifyResult");
                redbagNotifyResult.getNotify().getHitRecord();
                int redId = redbagNotifyResult.getNotify().getRedId();
                String roundNo = redbagNotifyResult.getNotify().getRoundNo();
                if (redId == RedbagRoomActivity.this.f11281m && TextUtils.equals(roundNo, RedbagRoomActivity.this.f11282n)) {
                    RedbagRoomActivity.this.f11293y.clear();
                    RedbagRoomActivity.this.f11293y.addAll((ArrayList) redbagNotifyResult.getNotify().getHitRecord());
                    RedbagRoomActivity.this.m12535b();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("notify_result");
        GBApplication.instance().registerReceiver(this.f11288t, intentFilter);
        this.f11275g = new BroadcastReceiver() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagRoomActivity.C30452 */

            public void onReceive(Context context, Intent intent) {
                RedbagRoomActivity.this.f11266A = (RedbagDetailResponse) intent.getSerializableExtra("redbagDetailResponse");
                if (TextUtils.equals(RedbagRoomActivity.this.f11266A.getResponse().getRoundNo(), RedbagRoomActivity.this.f11282n)) {
                    RedbagRoomActivity.this.f11293y.clear();
                    RedbagRoomActivity.this.f11293y.addAll(RedbagRoomActivity.this.f11266A.getResponse().getHitRecord());
                    if (RedbagRoomActivity.this.f11293y.size() < RedbagRoomActivity.this.f11268C - 1) {
                        RedbagRoomActivity.this.f11267B = new BroadcastReceiver() {
                            /* class com.gbcom.gwifi.functions.redbag.RedbagRoomActivity.C30452.C30461 */

                            public void onReceive(Context context, Intent intent) {
                                if (RedbagRoomActivity.this.f11293y.size() >= RedbagRoomActivity.this.f11268C - 2) {
                                    GBApplication.instance().unregisterReceiver(RedbagRoomActivity.this.f11267B);
                                }
                                if (RedbagRoomActivity.this.f11293y.size() < RedbagRoomActivity.this.f11268C - 1) {
                                    NotifyMsg notifyMsg = (NotifyMsg) intent.getSerializableExtra("notifyMsg");
                                    HitRecord hitRecord = new HitRecord();
                                    hitRecord.setCreateAt(notifyMsg.getNotify().getCreateAt());
                                    hitRecord.setFaceUrl(notifyMsg.getNotify().getFaceUrl());
                                    hitRecord.setNickName(notifyMsg.getNotify().getNickName());
                                    if (notifyMsg.getNotify().getRedId() == RedbagRoomActivity.this.f11281m && TextUtils.equals(notifyMsg.getNotify().getRoundNo(), RedbagRoomActivity.this.f11282n)) {
                                        RedbagRoomActivity.this.f11293y.add(hitRecord);
                                        RedbagRoomActivity.this.m12535b();
                                    }
                                }
                            }
                        };
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction("notify_msg");
                        GBApplication.instance().registerReceiver(RedbagRoomActivity.this.f11267B, intentFilter);
                    }
                    GBApplication.instance().unregisterReceiver(RedbagRoomActivity.this.f11275g);
                    RedbagRoomActivity.this.m12535b();
                }
            }
        };
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("notify_update");
        GBApplication.instance().registerReceiver(this.f11275g, intentFilter2);
        this.f11280l.execute(new Runnable() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagRoomActivity.RunnableC30473 */

            @Override // java.lang.Runnable
            public void run() {
                RedbagDetailRequest redbagDetailRequest = new RedbagDetailRequest();
                redbagDetailRequest.setAccountId(RedbagRoomActivity.this.f11277i);
                redbagDetailRequest.setRedId(RedbagRoomActivity.this.f11281m);
                redbagDetailRequest.setRoundNo(RedbagRoomActivity.this.f11282n);
                RedbagMainActivity.channel.writeAndFlush(redbagDetailRequest);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12535b() {
        this.f11269a.clear();
        this.f11269a.addAll(this.f11293y);
        int size = this.f11268C - this.f11269a.size();
        for (int i = 0; i < size; i++) {
            this.f11269a.add(new NeedAdd());
        }
        this.f11285q = new CommonListAdapter(this.f11269a, C2425R.layout.redbag_listview_common_item, new CommonListAdapter.AbstractC3060a<HitRecord>() {
            /* class com.gbcom.gwifi.functions.redbag.RedbagRoomActivity.C30484 */

            /* renamed from: a */
            public void mo24662a(CommonListAdapter.C3061b bVar, HitRecord hitRecord, int i) {
                if (hitRecord.getHitBeans() != 0) {
                    if (hitRecord.getAccountId() == RedbagRoomActivity.this.f11277i) {
                        RedbagRoomActivity.this.f11286r.setText("+" + hitRecord.getHitBeans() + Constant.f13309cr);
                    }
                    bVar.mo26112a(C2425R.C2427id.tv_redbag_result_name, (CharSequence) hitRecord.getNickName()).mo26112a(C2425R.C2427id.tv_redbag_result_time, (CharSequence) hitRecord.getCreateAt()).mo26112a(C2425R.C2427id.tv_redbag_result_score, (CharSequence) ("+" + hitRecord.getHitBeans() + Constant.f13309cr)).mo26113a(C2425R.C2427id.iv_avater, hitRecord.getFaceUrl());
                    if (hitRecord.getHitBeans() < RedbagRoomActivity.this.f11292x) {
                        bVar.mo26114a(C2425R.C2427id.tv_redbag_result_least, true).mo26112a(C2425R.C2427id.tv_redbag_result_least, (CharSequence) ("最少，-" + RedbagRoomActivity.this.f11292x + Constant.f13309cr));
                    }
                } else if (hitRecord instanceof NeedAdd) {
                    bVar.mo26114a(C2425R.C2427id.ll_hasperson, false).mo26114a(C2425R.C2427id.ll_noperson, true);
                } else {
                    bVar.mo26112a(C2425R.C2427id.tv_redbag_result_name, (CharSequence) hitRecord.getNickName()).mo26112a(C2425R.C2427id.tv_redbag_result_time, (CharSequence) hitRecord.getCreateAt()).mo26113a(C2425R.C2427id.iv_avater, hitRecord.getFaceUrl()).mo26114a(C2425R.C2427id.tv_redbag_result_score, false);
                }
            }
        });
        if (!(size == 0 && this.f11269a.get(0).getHitBeans() == 0)) {
            this.f11287s.setAdapter((ListAdapter) this.f11285q);
        }
    }

    /* renamed from: c */
    private void m12537c() {
        this.f11270b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11270b.setOnClickListener(this);
        this.f11271c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11272d = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11271c.setText("抢红包");
        this.f11272d.setVisibility(8);
        ((TextView) findViewById(C2425R.C2427id.tv_redbagname)).setText(this.f11273e + "红包场");
        this.f11286r = (TextView) findViewById(C2425R.C2427id.tv_score_get);
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

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
        if (this.f11278j != null) {
            this.f11278j.close();
        }
        if (this.f11276h) {
            this.f11276h = false;
            GBApplication.instance().unregisterReceiver(this.f11275g);
        }
    }
}
