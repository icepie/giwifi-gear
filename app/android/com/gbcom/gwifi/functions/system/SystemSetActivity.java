package com.gbcom.gwifi.functions.system;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.edu.functionModule.main.manager.SDKUserManager;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.aboutus.AboutUsActivity;
import com.gbcom.gwifi.functions.loading.LoginThirdActivity;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.loading.StatusLoginActivity;
import com.gbcom.gwifi.functions.ping.PingTestActivity;
import com.gbcom.gwifi.functions.product.AppDownloadActivity;
import com.gbcom.gwifi.functions.test.DeviceTest2Activity;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.Config;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.WebViewUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import p041c.Request;

public class SystemSetActivity extends GBActivity implements View.OnClickListener {
    public static String ABOUT_US = "com.action.AboutUsActivity";

    /* renamed from: a */
    OkRequestHandler<String> f11672a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.system.SystemSetActivity.C31304 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (abVar == SystemSetActivity.this.f11683l) {
            }
            if (abVar == SystemSetActivity.this.f11684m) {
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == SystemSetActivity.this.f11683l) {
            }
            if (abVar == SystemSetActivity.this.f11684m) {
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    private RelativeLayout f11673b;

    /* renamed from: c */
    private RelativeLayout f11674c;

    /* renamed from: d */
    private RelativeLayout f11675d;

    /* renamed from: e */
    private RelativeLayout f11676e;

    /* renamed from: f */
    private RelativeLayout f11677f;

    /* renamed from: g */
    private CheckBox f11678g;

    /* renamed from: h */
    private RelativeLayout f11679h;

    /* renamed from: i */
    private TextView f11680i;

    /* renamed from: j */
    private TextView f11681j;

    /* renamed from: k */
    private RelativeLayout f11682k;

    /* renamed from: l */
    private Request f11683l;

    /* renamed from: m */
    private Request f11684m;

    /* renamed from: n */
    private Executor f11685n = Executors.newFixedThreadPool(1);

    /* renamed from: o */
    private String f11686o;

    /* renamed from: p */
    private String f11687p;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "系统设置界面", C2425R.layout.sys_activity_set, true);
        m12800a();
    }

    /* renamed from: a */
    private void m12800a() {
        this.f11679h = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11678g = (CheckBox) findViewById(C2425R.C2427id.sys_auto_connect_cb);
        this.f11678g.setChecked(Config.m14094a().mo27808k());
        this.f11678g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class com.gbcom.gwifi.functions.system.SystemSetActivity.C31271 */

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Config.m14094a().mo27797a(z);
            }
        });
        this.f11679h.setOnClickListener(this);
        this.f11681j = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11680i = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11681j.setText("系统设置");
        this.f11680i.setText((CharSequence) null);
        this.f11673b = (RelativeLayout) findViewById(C2425R.C2427id.sys_check_layout);
        this.f11674c = (RelativeLayout) findViewById(C2425R.C2427id.sys_about_layout);
        this.f11675d = (RelativeLayout) findViewById(C2425R.C2427id.sys_state_info_layout);
        this.f11673b.setOnClickListener(this);
        this.f11674c.setOnClickListener(this);
        this.f11675d.setOnClickListener(this);
        this.f11676e = (RelativeLayout) findViewById(C2425R.C2427id.sys_state_device);
        this.f11676e.setOnClickListener(this);
        this.f11677f = (RelativeLayout) findViewById(C2425R.C2427id.sys_ping_layout);
        this.f11677f.setOnClickListener(this);
        this.f11682k = (RelativeLayout) findViewById(C2425R.C2427id.exit_user);
        this.f11682k.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.exit_user /*{ENCODED_INT: 2131755707}*/:
                showNormalDialog("退出帐号", "是否退出当前帐号？", "退出", "取消", new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.system.SystemSetActivity.C31282 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                        SystemSetActivity.this.m12802b();
                        SystemSetActivity.this.m12807d();
                    }
                }, new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.system.SystemSetActivity.C31293 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                    }
                });
                return;
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.sys_check_layout /*{ENCODED_INT: 2131756029}*/:
                checkVersion();
                return;
            case C2425R.C2427id.sys_about_layout /*{ENCODED_INT: 2131756031}*/:
                startActivity(new Intent(this, AboutUsActivity.class));
                return;
            case C2425R.C2427id.sys_state_info_layout /*{ENCODED_INT: 2131756033}*/:
                startActivity(new Intent(this, SysStateActivity.class));
                return;
            case C2425R.C2427id.sys_state_device /*{ENCODED_INT: 2131756035}*/:
                Intent intent = new Intent(this, DeviceTest2Activity.class);
                intent.putExtra("type", "system_set");
                startActivity(intent);
                return;
            case C2425R.C2427id.sys_ping_layout /*{ENCODED_INT: 2131756037}*/:
                startActivity(new Intent(this, PingTestActivity.class));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12802b() {
        WebViewUtil.m14568a();
    }

    /* renamed from: c */
    private void m12805c() {
        MainActivity currentMainActivity = GBApplication.instance().getCurrentMainActivity();
        if (currentMainActivity != null) {
            new SDKUserManager(currentMainActivity).mo24029a();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m12807d() {
        if (CacheAuth.getInstance().isPortal()) {
            this.f11684m = HttpUtil.m14346p(GBApplication.instance(), this.f11672a, "");
        } else {
            this.f11683l = HttpUtil.m14296b(this.f11672a, "");
        }
        CacheAccount.getInstance().setUserId(0);
        CacheAccount.getInstance().setLastSignTime(0);
        CacheApp.getInstance().setWeChatGuide(false);
        CacheAuth.getInstance().setLastReleaseTime(0);
        List<GBActivity> activitiesList = GBApplication.instance().getActivitiesList();
        AppDownloadActivity appDownloadActivity = null;
        for (int i = 0; i < activitiesList.size(); i++) {
            if (activitiesList.get(i) instanceof AppDownloadActivity) {
                appDownloadActivity = (AppDownloadActivity) activitiesList.get(i);
            }
        }
        if (appDownloadActivity != null) {
            appDownloadActivity.getThirdFragment().mo25509d();
            appDownloadActivity.finish();
        }
        CacheWiFi.getInstance().setApLocation("");
        CacheAccount.getInstance().clearCacheAccountPassword(CacheAccount.getInstance().getLoginPhone());
        CacheAuth.getInstance().resetPortalDisable();
        if (Config.m14094a().mo27812o() && !CacheAuth.getInstance().isPortal()) {
            m12805c();
        }
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
