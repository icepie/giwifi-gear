package com.gbcom.gwifi.functions.loading;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.p009v4.view.GravityCompat;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import p041c.Request;

public class DialogActivity extends Activity implements View.OnClickListener {

    /* renamed from: b */
    private static final String f9472b = "DialogActivity";

    /* renamed from: a */
    OkRequestHandler<String> f9473a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.loading.DialogActivity.C26611 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (DialogActivity.this.f9484m == abVar) {
                DialogActivity.this.finish();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (DialogActivity.this.f9484m == abVar) {
                if (!GBActivity.dealException(CommonMsg.deSerializeJson(str.getBytes()))) {
                }
                DialogActivity.this.finish();
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: c */
    private ImageView f9474c;

    /* renamed from: d */
    private Button f9475d;

    /* renamed from: e */
    private TextView f9476e;

    /* renamed from: f */
    private TextView f9477f;

    /* renamed from: g */
    private LinearLayout f9478g;

    /* renamed from: h */
    private String f9479h;

    /* renamed from: i */
    private String f9480i;

    /* renamed from: j */
    private String f9481j;

    /* renamed from: k */
    private String f9482k;

    /* renamed from: l */
    private int f9483l = 0;

    /* renamed from: m */
    private Request f9484m;

    /* renamed from: n */
    private TextView f9485n;

    /* renamed from: o */
    private TextView f9486o;

    /* renamed from: p */
    private TextView f9487p;

    /* renamed from: q */
    private Button f9488q;

    /* renamed from: r */
    private Button f9489r;

    public void onCreate(Bundle bundle) {
        Log.m14400c(f9472b, "DialogActivity  onCreate");
        super.onCreate(bundle);
        setContentView(C2425R.layout.common_dialog_activity);
        m11136a();
    }

    /* renamed from: a */
    private void m11136a() {
        this.f9474c = (ImageView) findViewById(C2425R.C2427id.cancl_bt);
        this.f9475d = (Button) findViewById(C2425R.C2427id.add_bt);
        this.f9478g = (LinearLayout) findViewById(C2425R.C2427id.view_layout);
        View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.common_dialog_content, (ViewGroup) null);
        this.f9477f = (TextView) inflate.findViewById(2131755301);
        this.f9477f.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.f9476e = (TextView) inflate.findViewById(C2425R.C2427id.dialog_title);
        this.f9478g.addView(inflate, -1, -2);
        this.f9474c.setOnClickListener(this);
        this.f9475d.setOnClickListener(this);
        this.f9479h = getIntent().getStringExtra("title");
        this.f9482k = getIntent().getStringExtra("content");
        this.f9480i = getIntent().getStringExtra("positiveText");
        this.f9481j = getIntent().getStringExtra("negativeText");
        this.f9483l = getIntent().getIntExtra("type", 0);
        if (this.f9483l == 3) {
            this.f9485n = (TextView) findViewById(16908309);
            this.f9485n.setOnClickListener(this);
            this.f9485n.setVisibility(0);
        }
        if (this.f9483l == 5) {
            boolean booleanExtra = getIntent().getBooleanExtra("showService", false);
            boolean booleanExtra2 = getIntent().getBooleanExtra("showPrivacy", false);
            this.f9486o = (TextView) findViewById(C2425R.C2427id.service_agree);
            if (booleanExtra) {
                this.f9486o.setOnClickListener(this);
                this.f9486o.setVisibility(0);
            } else {
                this.f9486o.setVisibility(8);
            }
            this.f9487p = (TextView) findViewById(C2425R.C2427id.privacy_agree);
            if (booleanExtra2) {
                this.f9487p.setOnClickListener(this);
                this.f9487p.setVisibility(0);
            } else {
                this.f9487p.setVisibility(8);
            }
            this.f9474c.setVisibility(8);
            this.f9475d.setVisibility(8);
            this.f9488q = (Button) findViewById(C2425R.C2427id.no_btn);
            this.f9488q.setVisibility(0);
            this.f9488q.setOnClickListener(this);
            this.f9488q.setText(this.f9480i);
            this.f9489r = (Button) findViewById(C2425R.C2427id.yes_btn);
            this.f9489r.setVisibility(0);
            this.f9489r.setOnClickListener(this);
            this.f9489r.setText(this.f9481j);
            this.f9476e.setTypeface(Typeface.defaultFromStyle(1));
            this.f9476e.setTextSize(2, 18.0f);
            this.f9477f.setGravity(GravityCompat.START);
        }
        this.f9476e.setText(this.f9479h);
        this.f9477f.setText(this.f9482k);
        this.f9475d.setText(this.f9481j);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 16908309:
                String g = HttpUtil.m14323g();
                if (!C3467s.m14513e(g)) {
                    GBActivity.openBackWebView(g, "");
                    return;
                } else {
                    GBActivity.showMessageToast("链接无效...");
                    return;
                }
            case C2425R.C2427id.cancl_bt /*{ENCODED_INT: 2131755291}*/:
                if (this.f9483l == 4) {
                    CacheApp.getInstance().setAppDptipShow(false);
                    finish();
                    return;
                } else if (this.f9483l == 5) {
                    CacheWiFi.getInstance().setApLocation("");
                    GiwifiMobclickAgent.onKillProcess(this);
                    CacheApp.getInstance().setLastAutoCheckVersionTime(-1);
                    ((GBApplication) getApplicationContext()).onTerminate();
                    finish();
                    return;
                } else {
                    finish();
                    return;
                }
            case C2425R.C2427id.add_bt /*{ENCODED_INT: 2131755294}*/:
                if (this.f9483l == 1) {
                    GBGlobalConfig.m10510c().mo24407g();
                    finish();
                    return;
                } else if (this.f9483l == 2) {
                    GBGlobalConfig.m10510c().mo24413m();
                    finish();
                    return;
                } else if (this.f9483l == 3) {
                    this.f9484m = HttpUtil.m14303c(GBApplication.instance(), this.f9473a, "");
                    finish();
                    return;
                } else if (this.f9483l == 4) {
                    CacheApp.getInstance().setAppDptipShow(false);
                    finish();
                    return;
                } else if (this.f9483l == 5) {
                    CacheApp.getInstance().setAgreementYes(true);
                    CacheApp.getInstance().setAgreementTime(System.currentTimeMillis() / 1000);
                    finish();
                    return;
                } else {
                    finish();
                    return;
                }
            case C2425R.C2427id.service_agree /*{ENCODED_INT: 2131755296}*/:
                String g2 = HttpUtil.m14323g();
                if (!C3467s.m14513e(g2)) {
                    GBActivity.openBackWebView(g2, "");
                    return;
                } else {
                    GBActivity.showMessageToast("链接无效...");
                    return;
                }
            case C2425R.C2427id.privacy_agree /*{ENCODED_INT: 2131755297}*/:
                String h = HttpUtil.m14326h();
                if (!C3467s.m14513e(h)) {
                    GBActivity.openBackWebView(h, "");
                    return;
                } else {
                    GBActivity.showMessageToast("链接无效...");
                    return;
                }
            case C2425R.C2427id.no_btn /*{ENCODED_INT: 2131755298}*/:
                if (this.f9483l == 5) {
                    CacheWiFi.getInstance().setApLocation("");
                    GiwifiMobclickAgent.onKillProcess(this);
                    CacheApp.getInstance().setLastAutoCheckVersionTime(-1);
                    ((GBApplication) getApplicationContext()).onTerminate();
                    finish();
                    return;
                }
                finish();
                return;
            case C2425R.C2427id.yes_btn /*{ENCODED_INT: 2131755299}*/:
                if (this.f9483l == 5) {
                    CacheApp.getInstance().setAgreementYes(true);
                    CacheApp.getInstance().setAgreementTime(System.currentTimeMillis() / 1000);
                    finish();
                    return;
                }
                finish();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.m14400c(f9472b, "DialogActivity  onResume");
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.m14400c(f9472b, "DialogActivity  onPause");
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Log.m14400c(f9472b, "DialogActivity  onDestroy");
        super.onDestroy();
    }
}
