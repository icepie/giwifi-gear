package com.gbcom.gwifi.functions.loading;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.functions.wifi.p253a.AuthResultCode;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import java.util.HashMap;
import p041c.Request;

public class BundlePhoneActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: k */
    private static int f9415k = 180;

    /* renamed from: l */
    private static boolean f9416l = false;

    /* renamed from: a */
    private RelativeLayout f9417a;

    /* renamed from: b */
    private TextView f9418b;

    /* renamed from: c */
    private TextView f9419c;

    /* renamed from: d */
    private EditText f9420d;

    /* renamed from: e */
    private String f9421e;

    /* renamed from: f */
    private EditText f9422f;

    /* renamed from: g */
    private Button f9423g;

    /* renamed from: h */
    private Button f9424h;

    /* renamed from: i */
    private Request f9425i;

    /* renamed from: j */
    private Request f9426j;

    /* renamed from: m */
    private boolean f9427m = false;

    /* renamed from: n */
    private int f9428n;

    /* renamed from: o */
    private int f9429o;

    /* renamed from: p */
    private String f9430p;

    /* renamed from: q */
    private String f9431q;

    /* renamed from: r */
    private String f9432r;

    /* renamed from: s */
    private String f9433s;

    /* renamed from: t */
    private int f9434t;

    /* renamed from: u */
    private String f9435u;

    /* renamed from: v */
    private Dialog f9436v;

    /* renamed from: w */
    private Request f9437w;

    /* renamed from: x */
    private OkRequestHandler<String> f9438x = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.loading.BundlePhoneActivity.C26503 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            if (abVar == BundlePhoneActivity.this.f9426j) {
                BundlePhoneActivity.this.showProgressDialog(".....");
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            BundlePhoneActivity.this.f9427m = false;
            BundlePhoneActivity.this.dismissProgressDialog();
            if (abVar == BundlePhoneActivity.this.f9425i) {
                GBActivity.showMessageToast("获取验证码失败，请检查网络");
                boolean unused = BundlePhoneActivity.f9416l = true;
            } else if (abVar == BundlePhoneActivity.this.f9437w) {
                Intent intent = new Intent(BundlePhoneActivity.this, MainActivity.class);
                intent.putExtra(Constant.f13323i, BundlePhoneActivity.this.f9421e);
                intent.putExtra("staticPassword", BundlePhoneActivity.this.f9435u);
                BundlePhoneActivity.this.startActivity(intent);
                BundlePhoneActivity.this.finish();
            } else if (abVar == BundlePhoneActivity.this.f9426j) {
                GBActivity.showMessageToast("绑定手机号失败，请检查网络");
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            BundlePhoneActivity.this.f9427m = false;
            BundlePhoneActivity.this.dismissProgressDialog();
            if (abVar == BundlePhoneActivity.this.f9425i) {
                BundlePhoneActivity.this.m11091a((BundlePhoneActivity) str);
            } else if (abVar == BundlePhoneActivity.this.f9437w) {
                BundlePhoneActivity.this.m11099b((BundlePhoneActivity) str);
                GBGlobalConfig.m10510c().mo24414n();
            } else if (abVar == BundlePhoneActivity.this.f9426j) {
                CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
                if (!GBActivity.dealException(deSerializeJson)) {
                    if (BundlePhoneActivity.this.f9421e != null && !BundlePhoneActivity.this.f9421e.equals("")) {
                        CacheAccount.getInstance().setLoginPhone(BundlePhoneActivity.this.f9421e);
                        CacheAccount.getInstance().setLoginStaticPassword(BundlePhoneActivity.this.f9435u);
                    }
                    CacheAccount.getInstance().setLastLoginType(2);
                    CacheAccount.getInstance().setLastLoginPhone(BundlePhoneActivity.this.f9421e);
                    CacheAccount.getInstance().setCacheAccountBean(BundlePhoneActivity.this.f9434t, BundlePhoneActivity.this.f9428n, BundlePhoneActivity.this.f9431q, BundlePhoneActivity.this.f9432r, BundlePhoneActivity.this.f9433s, BundlePhoneActivity.this.f9430p);
                    Intent intent = new Intent(BundlePhoneActivity.this, MainActivity.class);
                    intent.putExtra(Constant.f13323i, BundlePhoneActivity.this.f9421e);
                    intent.putExtra("staticPassword", BundlePhoneActivity.this.f9435u);
                    BundlePhoneActivity.this.startActivity(intent);
                    BundlePhoneActivity.this.finish();
                } else if (deSerializeJson != null && AuthResultCode.m13887d(deSerializeJson.getResultCode().intValue())) {
                    BundlePhoneActivity.this.m11092a(deSerializeJson.getResultMsg(), "联系客服", new GBActivity.AbstractC2517a() {
                        /* class com.gbcom.gwifi.functions.loading.BundlePhoneActivity.C26503.C26511 */

                        @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                        public void onClick(Dialog dialog, View view) {
                            dialog.dismiss();
                            BundlePhoneActivity.this.contactClientPhone();
                        }
                    });
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    static /* synthetic */ int m11096b() {
        int i = f9415k;
        f9415k = i - 1;
        return i;
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "绑定手机号", C2425R.layout.login_activity_bundle_phone, true);
        m11103d();
        m11105e();
    }

    /* renamed from: d */
    private void m11103d() {
        this.f9428n = getIntent().getIntExtra("schoolId", 0);
        this.f9429o = getIntent().getIntExtra("statusId", 0);
        this.f9431q = getIntent().getStringExtra("schoolTv");
        this.f9432r = getIntent().getStringExtra("nameTv");
        this.f9433s = getIntent().getStringExtra("statusTv");
        this.f9430p = getIntent().getStringExtra("studNo");
        this.f9434t = getIntent().getIntExtra("orgType", 0);
        if (C3467s.m14513e(this.f9430p)) {
            this.f9430p = "";
        }
        this.f9435u = getIntent().getStringExtra("staticPassword");
    }

    /* renamed from: e */
    private void m11105e() {
        this.f9417a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f9417a.setOnClickListener(this);
        this.f9418b = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f9418b.setText("绑定手机号");
        this.f9419c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f9419c.setText("");
        this.f9420d = (EditText) findViewById(C2425R.C2427id.phone_et);
        this.f9422f = (EditText) findViewById(C2425R.C2427id.passcode_et);
        this.f9423g = (Button) findViewById(C2425R.C2427id.get_password_btn);
        this.f9423g.setOnClickListener(this);
        this.f9424h = (Button) findViewById(C2425R.C2427id.login_btn);
        this.f9424h.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.get_password_btn /*{ENCODED_INT: 2131755637}*/:
                this.f9421e = this.f9420d.getText().toString();
                if (TextUtils.isEmpty(this.f9421e)) {
                    GBActivity.showMessageToast("手机号不能为空");
                    return;
                } else if (!TextUtils.isDigitsOnly(this.f9421e) || this.f9421e.length() != 11) {
                    GBActivity.showMessageToast("手机号不合法");
                    return;
                } else {
                    m11109g();
                    this.f9425i = HttpUtil.m14258a(this, this.f9421e, 5, this.f9428n, this.f9429o, this.f9430p, this.f9432r, this.f9438x, "");
                    return;
                }
            case C2425R.C2427id.login_btn /*{ENCODED_INT: 2131755638}*/:
                m11107f();
                return;
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            default:
                return;
        }
    }

    /* renamed from: f */
    private void m11107f() {
        if (!this.f9427m) {
            this.f9421e = this.f9420d.getText().toString().trim();
            if (TextUtils.isEmpty(this.f9421e)) {
                GBActivity.showMessageToast("手机号不能为空");
            } else if (!TextUtils.isDigitsOnly(this.f9421e) || this.f9421e.length() != 11) {
                GBActivity.showMessageToast("手机号不合法");
            } else if (C3467s.m14513e(this.f9422f.getText().toString().trim())) {
                GBActivity.showMessageToast("验证码不能为空");
            } else {
                this.f9427m = true;
                this.f9426j = HttpUtil.m14248a(this, this.f9428n, this.f9429o, this.f9432r, this.f9430p, this.f9435u, this.f9421e, this.f9422f.getText().toString().trim(), this.f9438x, "");
            }
        } else {
            this.f9427m = false;
        }
    }

    /* renamed from: g */
    private void m11109g() {
        this.f9423g.setEnabled(false);
        new Thread(new Runnable() {
            /* class com.gbcom.gwifi.functions.loading.BundlePhoneActivity.RunnableC26471 */

            @Override // java.lang.Runnable
            public void run() {
                int unused = BundlePhoneActivity.f9415k = 180;
                boolean unused2 = BundlePhoneActivity.f9416l = false;
                while (!BundlePhoneActivity.f9416l) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        boolean unused3 = BundlePhoneActivity.f9416l = true;
                        e.printStackTrace();
                    }
                    BundlePhoneActivity.m11096b();
                    if (BundlePhoneActivity.f9415k == 0) {
                        boolean unused4 = BundlePhoneActivity.f9416l = true;
                    }
                    BundlePhoneActivity.this.runOnUiThread(new Runnable() {
                        /* class com.gbcom.gwifi.functions.loading.BundlePhoneActivity.RunnableC26471.RunnableC26481 */

                        @Override // java.lang.Runnable
                        public void run() {
                            if (BundlePhoneActivity.f9415k == 0 || BundlePhoneActivity.f9416l) {
                                BundlePhoneActivity.this.f9423g.setText("获取验证码");
                                BundlePhoneActivity.this.f9423g.setEnabled(true);
                                return;
                            }
                            BundlePhoneActivity.this.f9423g.setText(BundlePhoneActivity.f9415k + "秒后");
                        }
                    });
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11091a(String str) {
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (!dealException(deSerializeJson)) {
            GBActivity.showMessageToast("获取验证码成功");
            return;
        }
        f9416l = true;
        if (deSerializeJson != null && AuthResultCode.m13887d(deSerializeJson.getResultCode().intValue())) {
            m11092a(deSerializeJson.getResultMsg(), "联系客服", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.loading.BundlePhoneActivity.C26492 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    BundlePhoneActivity.this.contactClientPhone();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11099b(String str) {
        int i;
        int i2;
        int i3 = 0;
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (!GBActivity.dealException(deSerializeJson)) {
            HashMap hashMap = (HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class);
            int intValue = ((Integer) hashMap.get("uid")).intValue();
            String str2 = (String) hashMap.get("avatarUrl");
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            Integer valueOf = Integer.valueOf(hashMap.get("hotspot_group_id") != null ? Integer.parseInt(hashMap.get("hotspot_group_id").toString()) : 0);
            if (hashMap.get("college_id") != null) {
                i = ((Integer) hashMap.get("college_id")).intValue();
            } else {
                i = 0;
            }
            if (hashMap.get("identity_type") != null) {
                i2 = ((Integer) hashMap.get("identity_type")).intValue();
            } else {
                i2 = 0;
            }
            if (hashMap.get("department_id") != null) {
                i3 = ((Integer) hashMap.get("department_id")).intValue();
            }
            CacheAccount.getInstance().setCacheAccountBean(intValue, str2, valueOf.intValue(), i, i3, i2);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constant.f13323i, this.f9421e);
            intent.putExtra("staticPassword", this.f9435u);
            startActivity(intent);
            finish();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11092a(String str, String str2, final GBActivity.AbstractC2517a aVar) {
        this.f9436v = new Dialog(this, C2425R.C2430style.login_dialog);
        View inflate = LayoutInflater.from(getApplicationContext()).inflate(C2425R.layout.common_dialog, (ViewGroup) null);
        Button button = (Button) inflate.findViewById(C2425R.C2427id.add_bt);
        View inflate2 = getLayoutInflater().inflate(C2425R.layout.common_dialog_content, (ViewGroup) null);
        TextView textView = (TextView) inflate2.findViewById(2131755301);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView.setText(C3467s.m14514f(str));
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.view_layout)).addView(inflate2, -1, -2);
        ((TextView) inflate2.findViewById(C2425R.C2427id.dialog_title)).setText("");
        button.setText(str2);
        ((ImageView) inflate.findViewById(C2425R.C2427id.cancl_bt)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.BundlePhoneActivity.View$OnClickListenerC26524 */

            public void onClick(View view) {
                if (BundlePhoneActivity.this.f9436v != null) {
                    BundlePhoneActivity.this.f9436v.dismiss();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.BundlePhoneActivity.View$OnClickListenerC26535 */

            public void onClick(View view) {
                if (aVar != null) {
                    if (BundlePhoneActivity.this.f9436v != null) {
                        aVar.onClick(BundlePhoneActivity.this.f9436v, view);
                    }
                } else if (BundlePhoneActivity.this.f9436v != null) {
                    BundlePhoneActivity.this.f9436v.dismiss();
                }
            }
        });
        this.f9436v.setContentView(inflate, new ViewGroup.LayoutParams((int) getResources().getDimension(C2425R.dimen.dialog_width), -2));
        this.f9436v.setCancelable(!isReallyMust.booleanValue());
        this.f9436v.show();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        if (this.f9436v != null && this.f9436v.isShowing()) {
            this.f9436v.dismiss();
            this.f9436v = null;
        }
        super.onDestroy();
    }
}
