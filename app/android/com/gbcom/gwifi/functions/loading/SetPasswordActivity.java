package com.gbcom.gwifi.functions.loading;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.functions.wifi.MultiCheckNetWorkUtils;
import com.gbcom.gwifi.functions.wifi.entity.CheckResult;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.CheckUtil;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.taobao.agoo.p359a.p360a.C5719c;
import com.xiaomi.stat.MiStat;
import java.util.concurrent.atomic.AtomicBoolean;
import p041c.Request;

public class SetPasswordActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: k */
    private static int f9766k = 180;

    /* renamed from: l */
    private static boolean f9767l = false;

    /* renamed from: m */
    private static boolean f9768m = false;

    /* renamed from: a */
    private EditText f9769a;

    /* renamed from: b */
    private EditText f9770b;

    /* renamed from: c */
    private EditText f9771c;

    /* renamed from: d */
    private Button f9772d;

    /* renamed from: e */
    private RelativeLayout f9773e;

    /* renamed from: f */
    private Button f9774f;

    /* renamed from: g */
    private Button f9775g;

    /* renamed from: h */
    private Button f9776h;

    /* renamed from: i */
    private Request f9777i;

    /* renamed from: j */
    private Request f9778j;

    /* renamed from: n */
    private String f9779n;

    /* renamed from: o */
    private String f9780o;

    /* renamed from: p */
    private String f9781p;

    /* renamed from: q */
    private boolean f9782q = false;

    /* renamed from: r */
    private boolean f9783r = false;

    /* renamed from: s */
    private String f9784s = "";
    public Handler smsHandler = new Handler() {
        /* class com.gbcom.gwifi.functions.loading.SetPasswordActivity.HandlerC27542 */
    };

    /* renamed from: t */
    private AtomicBoolean f9785t = new AtomicBoolean(false);

    /* renamed from: u */
    private boolean f9786u = false;

    /* renamed from: v */
    private OkRequestHandler<String> f9787v = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.loading.SetPasswordActivity.C27564 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            SetPasswordActivity.this.dismissProgressDialog();
            if (abVar == SetPasswordActivity.this.f9777i) {
                GBActivity.showMessageToast("获取验证码失败，请检查网络");
                boolean unused = SetPasswordActivity.f9767l = true;
                boolean unused2 = SetPasswordActivity.f9768m = false;
            } else if (abVar == SetPasswordActivity.this.f9778j) {
                GBActivity.showMessageToast("重置密码失败，请检查网络");
                SetPasswordActivity.this.f9775g.setEnabled(true);
                SetPasswordActivity.this.f9775g.setText("确认");
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            SetPasswordActivity.this.dismissProgressDialog();
            CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
            if (abVar == SetPasswordActivity.this.f9777i) {
                if (!GBActivity.dealException(deSerializeJson)) {
                    boolean unused = SetPasswordActivity.f9768m = true;
                    GBActivity.showMessageToast("获取验证码成功");
                    return;
                }
                boolean unused2 = SetPasswordActivity.f9768m = false;
                boolean unused3 = SetPasswordActivity.f9767l = true;
            } else if (abVar != SetPasswordActivity.this.f9778j) {
            } else {
                if (!GBActivity.dealException(deSerializeJson)) {
                    GBActivity.showMessageToast("修改成功");
                    CacheAccount.getInstance().setLastLoginType(1);
                    if (!SetPasswordActivity.this.f9779n.equals(CacheAccount.getInstance().getLastLoginPhone())) {
                        CacheAccount.getInstance().resetCacheAccountBean();
                    }
                    Intent intent = new Intent(SetPasswordActivity.this, MainActivity.class);
                    intent.putExtra(Constant.f13323i, SetPasswordActivity.this.f9779n);
                    intent.putExtra("staticPassword", SetPasswordActivity.this.f9781p);
                    SetPasswordActivity.this.startActivity(intent);
                    SetPasswordActivity.this.finish();
                    return;
                }
                SetPasswordActivity.this.f9775g.setEnabled(true);
                SetPasswordActivity.this.f9775g.setText("确认");
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    static /* synthetic */ int m11349b() {
        int i = f9766k;
        f9766k = i - 1;
        return i;
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "新修改密码界面", C2425R.layout.login_activity_set_password, false);
        m11357e();
        MultiCheckNetWorkUtils.m13944b(GBApplication.instance(), new CallBack() {
            /* class com.gbcom.gwifi.functions.loading.SetPasswordActivity.C27531 */

            @Override // com.gbcom.gwifi.base.p234c.CallBack
            /* renamed from: a */
            public void mo24437a(Object obj) {
                CheckResult checkResult = (CheckResult) obj;
                if (checkResult != null) {
                    SetPasswordActivity.this.f9786u = checkResult.isGiwifi();
                }
            }
        });
    }

    /* renamed from: e */
    private void m11357e() {
        this.f9769a = (EditText) findViewById(C2425R.C2427id.phone_et);
        this.f9770b = (EditText) findViewById(C2425R.C2427id.password_et);
        this.f9771c = (EditText) findViewById(C2425R.C2427id.static_password_et);
        this.f9772d = (Button) findViewById(C2425R.C2427id.get_password_btn);
        this.f9775g = (Button) findViewById(C2425R.C2427id.login_btn);
        this.f9776h = (Button) findViewById(C2425R.C2427id.re_login_btn);
        this.f9773e = (RelativeLayout) findViewById(C2425R.C2427id.show_password);
        this.f9774f = (Button) findViewById(C2425R.C2427id.show_btn);
        this.f9771c.addTextChangedListener(new TextWatcher() {
            /* class com.gbcom.gwifi.functions.loading.SetPasswordActivity.C27553 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String obj = SetPasswordActivity.this.f9771c.getText().toString();
                String c = CheckUtil.m14085c(obj);
                if (!obj.equals(c) && c.length() < obj.length()) {
                    SetPasswordActivity.this.f9771c.setText(c);
                    SetPasswordActivity.this.f9771c.setSelection(c.length());
                    C3470v.m14563a(GBApplication.instance(), "字符非法");
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.f9772d.setOnClickListener(this);
        this.f9775g.setOnClickListener(this);
        this.f9776h.setOnClickListener(this);
        this.f9773e.setOnClickListener(this);
        this.f9774f.setOnClickListener(this);
        this.f9779n = getIntent().getStringExtra(Constant.f13323i);
        if (C3467s.m14513e(this.f9779n)) {
            this.f9779n = CacheAccount.getInstance().getLastLoginPhone();
        }
        this.f9783r = getIntent().getBooleanExtra("isChanged", false);
        this.f9784s = getIntent().getStringExtra("setPassword");
        this.f9769a.setText(this.f9779n);
        if (this.f9783r) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0053, code lost:
        m11359f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
        if (r6.f9786u == false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005a, code lost:
        r6.f9777i = com.gbcom.gwifi.util.HttpUtil.m14260a(r6, r6.f9779n, 2, r6.f9787v, "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0067, code lost:
        r6.f9777i = com.gbcom.gwifi.util.HttpUtil.m14260a(r6, r6.f9779n, 4, r6.f9787v, "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r7) {
        /*
        // Method dump skipped, instructions count: 426
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.functions.loading.SetPasswordActivity.onClick(android.view.View):void");
    }

    /* renamed from: f */
    private void m11359f() {
        this.f9772d.setEnabled(false);
        new Thread(new Runnable() {
            /* class com.gbcom.gwifi.functions.loading.SetPasswordActivity.RunnableC27575 */

            @Override // java.lang.Runnable
            public void run() {
                int unused = SetPasswordActivity.f9766k = 180;
                boolean unused2 = SetPasswordActivity.f9767l = false;
                while (!SetPasswordActivity.f9767l) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        boolean unused3 = SetPasswordActivity.f9767l = true;
                        e.printStackTrace();
                    }
                    SetPasswordActivity.m11349b();
                    if (SetPasswordActivity.f9766k == 0) {
                        boolean unused4 = SetPasswordActivity.f9767l = true;
                    }
                    SetPasswordActivity.this.runOnUiThread(new Runnable() {
                        /* class com.gbcom.gwifi.functions.loading.SetPasswordActivity.RunnableC27575.RunnableC27581 */

                        @Override // java.lang.Runnable
                        public void run() {
                            if (SetPasswordActivity.f9766k == 0 || SetPasswordActivity.f9767l) {
                                SetPasswordActivity.this.f9772d.setText("获取验证码");
                                SetPasswordActivity.this.f9772d.setEnabled(true);
                                synchronized (SetPasswordActivity.this.f9785t) {
                                    SetPasswordActivity.this.f9785t.set(false);
                                }
                            } else if (SetPasswordActivity.f9768m) {
                                SetPasswordActivity.this.f9772d.setText(SetPasswordActivity.f9766k + "秒后");
                            }
                        }
                    });
                }
            }
        }).start();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.f9784s.equals(MiStat.Event.LOGIN)) {
                Intent intent = new Intent(this, LoginThirdActivity.class);
                intent.putExtra(Constant.f13323i, this.f9779n);
                startActivity(intent);
            } else if (this.f9784s.equals(C5719c.JSON_CMD_REGISTER)) {
                Intent intent2 = new Intent(this, RegisterUserActivity.class);
                intent2.putExtra(Constant.f13323i, this.f9779n);
                startActivity(intent2);
            }
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
    }
}
