package com.gbcom.gwifi.functions.profile;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.loading.LoginThirdActivity;
import com.gbcom.gwifi.functions.loading.StatusLoginActivity;
import com.gbcom.gwifi.functions.notify.NotifyListActivity;
import com.gbcom.gwifi.functions.p244d.ShareHelper;
import com.gbcom.gwifi.functions.system.SystemSetActivity;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.WebViewUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAuth;
import p041c.Request;

public class UserActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    OkRequestHandler<String> f10962a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.profile.UserActivity.C29763 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (abVar == UserActivity.this.f10981t) {
            }
            if (abVar == UserActivity.this.f10982u) {
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == UserActivity.this.f10981t) {
            }
            if (abVar == UserActivity.this.f10982u) {
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    private RelativeLayout f10963b;

    /* renamed from: c */
    private RelativeLayout f10964c;

    /* renamed from: d */
    private RelativeLayout f10965d;

    /* renamed from: e */
    private RelativeLayout f10966e;

    /* renamed from: f */
    private RelativeLayout f10967f;

    /* renamed from: g */
    private RelativeLayout f10968g;

    /* renamed from: h */
    private RelativeLayout f10969h;

    /* renamed from: i */
    private RelativeLayout f10970i;

    /* renamed from: j */
    private RelativeLayout f10971j;

    /* renamed from: k */
    private RelativeLayout f10972k;

    /* renamed from: l */
    private TextView f10973l;

    /* renamed from: m */
    private RelativeLayout f10974m;

    /* renamed from: n */
    private RelativeLayout f10975n;

    /* renamed from: o */
    private TextView f10976o;

    /* renamed from: p */
    private TextView f10977p;

    /* renamed from: q */
    private ImageView f10978q;

    /* renamed from: r */
    private UpdateUserInfoReceiver f10979r;

    /* renamed from: s */
    private final String f10980s = Constant.f13309cr;

    /* renamed from: t */
    private Request f10981t;

    /* renamed from: u */
    private Request f10982u;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "用户界面", C2425R.layout.my_user_fragment_new_again, true);
        m12336a();
        m12338b();
    }

    /* renamed from: a */
    private void m12336a() {
        this.f10975n = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10975n.setOnClickListener(this);
        this.f10977p = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10976o = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10978q = (ImageView) findViewById(C2425R.C2427id.download_iv);
        this.f10977p.setText("我的");
        this.f10976o.setVisibility(8);
        this.f10978q.setVisibility(8);
        this.f10978q.setOnClickListener(this);
        this.f10963b = (RelativeLayout) findViewById(C2425R.C2427id.store);
        this.f10965d = (RelativeLayout) findViewById(C2425R.C2427id.my_notify);
        this.f10964c = (RelativeLayout) findViewById(C2425R.C2427id.my_info);
        this.f10973l = (TextView) findViewById(C2425R.C2427id.user_scores);
        this.f10966e = (RelativeLayout) findViewById(C2425R.C2427id.user_download);
        this.f10972k = (RelativeLayout) findViewById(C2425R.C2427id.play_history_layout);
        this.f10967f = (RelativeLayout) findViewById(C2425R.C2427id.user_attention);
        this.f10968g = (RelativeLayout) findViewById(C2425R.C2427id.recommend_friend);
        this.f10971j = (RelativeLayout) findViewById(C2425R.C2427id.earn_scores);
        this.f10969h = (RelativeLayout) findViewById(C2425R.C2427id.system_set);
        this.f10974m = (RelativeLayout) findViewById(C2425R.C2427id.exit_user);
        this.f10970i = (RelativeLayout) findViewById(C2425R.C2427id.questionnaire);
        this.f10971j = (RelativeLayout) findViewById(C2425R.C2427id.earn_scores);
        this.f10963b.setOnClickListener(this);
        this.f10965d.setOnClickListener(this);
        this.f10971j.setOnClickListener(this);
        this.f10964c.setOnClickListener(this);
        this.f10966e.setOnClickListener(this);
        this.f10972k.setOnClickListener(this);
        this.f10967f.setOnClickListener(this);
        this.f10968g.setOnClickListener(this);
        this.f10969h.setOnClickListener(this);
        this.f10974m.setOnClickListener(this);
        this.f10970i.setOnClickListener(this);
    }

    /* renamed from: b */
    private void m12338b() {
        this.f10979r = new UpdateUserInfoReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.f13231bF);
        GBApplication.instance().registerReceiver(this.f10979r, intentFilter);
        m12346f();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.exit_user /*{ENCODED_INT: 2131755707}*/:
                showNormalDialog("退出帐号", "是否退出当前帐号？", "退出", "取消", new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.profile.UserActivity.C29741 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                        UserActivity.this.m12340c();
                        UserActivity.this.m12347g();
                    }
                }, new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.profile.UserActivity.C29752 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                    }
                });
                return;
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.my_info /*{ENCODED_INT: 2131755764}*/:
                if (CacheAccount.getInstance().getLoginPhone().equals("")) {
                    GBActivity.showMessageToast("对不起，请先注册会员");
                    return;
                } else {
                    startActivity(new Intent(GBApplication.instance(), MyProfileActivity.class));
                    return;
                }
            case C2425R.C2427id.my_notify /*{ENCODED_INT: 2131755765}*/:
                UmengCount.queryNotifyClick(GBApplication.instance());
                startActivity(new Intent(GBApplication.instance(), NotifyListActivity.class));
                return;
            case C2425R.C2427id.earn_scores /*{ENCODED_INT: 2131755767}*/:
                UmengCount.queryMakeScore(getApplicationContext());
                m12345e();
                return;
            case C2425R.C2427id.store /*{ENCODED_INT: 2131755768}*/:
                UmengCount.queryScore(getApplicationContext());
                m12343d();
                return;
            case C2425R.C2427id.questionnaire /*{ENCODED_INT: 2131755771}*/:
                GBActivity.openBackWebView("http://" + CacheAuth.getInstance().getStationCloud() + "/shop/wenjuan", "问卷调查");
                return;
            case C2425R.C2427id.play_history_layout /*{ENCODED_INT: 2131755774}*/:
            default:
                return;
            case C2425R.C2427id.user_download /*{ENCODED_INT: 2131755776}*/:
                startActivity(new Intent(GBApplication.instance(), MyDownloadActivity.class));
                return;
            case C2425R.C2427id.user_attention /*{ENCODED_INT: 2131755777}*/:
                UmengCount.queryAttention(getApplicationContext());
                startActivity(new Intent(GBApplication.instance(), MyAttentionActivity.class));
                return;
            case C2425R.C2427id.recommend_friend /*{ENCODED_INT: 2131755778}*/:
                UmengCount.queryRecommendFriend(getApplicationContext());
                ShareHelper.m10859a(this).mo24539a();
                return;
            case C2425R.C2427id.system_set /*{ENCODED_INT: 2131755781}*/:
                startActivity(new Intent(GBApplication.instance(), SystemSetActivity.class));
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12340c() {
        WebViewUtil.m14568a();
    }

    /* renamed from: d */
    private void m12343d() {
        String j = HttpUtil.m14332j();
        if (!C3467s.m14513e(j)) {
            GBActivity.openBackWebView(j, "兑换时长");
        } else {
            GBActivity.showMessageToast("你不去娱乐栏看下我怎么放心加载...");
        }
    }

    /* renamed from: e */
    private void m12345e() {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: f */
    private void m12346f() {
        String totalScore = CacheAccount.getInstance().getTotalScore();
        if (totalScore != null && totalScore.length() > 0) {
            this.f10973l.setText(totalScore + Constant.f13309cr);
        }
    }

    public class UpdateUserInfoReceiver extends BroadcastReceiver {
        public UpdateUserInfoReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            m12349a(intent);
        }

        /* renamed from: a */
        private void m12349a(Intent intent) {
            if (intent != null && intent.getAction().equals(Constant.f13231bF)) {
                UserActivity.this.m12346f();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        GBApplication.instance().unregisterReceiver(this.f10979r);
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: g */
    private void m12347g() {
        if (CacheAuth.getInstance().isPortal()) {
            this.f10982u = HttpUtil.m14346p(GBApplication.instance(), this.f10962a, "");
        } else {
            this.f10981t = HttpUtil.m14296b(this.f10962a, "");
        }
        CacheAccount.getInstance().clearCacheAccountPassword(CacheAccount.getInstance().getLoginPhone());
        CacheAuth.getInstance().resetPortalDisable();
        int lastLoginType = CacheAccount.getInstance().getLastLoginType();
        String lastLoginPhone = CacheAccount.getInstance().getLastLoginPhone();
        if (lastLoginType == 2) {
            Intent intent = new Intent(this, StatusLoginActivity.class);
            intent.putExtra(Constant.f13323i, lastLoginPhone);
            startActivity(intent);
        } else {
            Intent intent2 = new Intent(this, LoginThirdActivity.class);
            intent2.putExtra(Constant.f13323i, lastLoginPhone);
            startActivity(intent2);
        }
        GBApplication.instance().exitOther();
    }
}
