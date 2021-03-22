package com.gbcom.gwifi.functions.loading;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.functions.wifi.MultiCheckNetWorkUtils;
import com.gbcom.gwifi.functions.wifi.p253a.AuthResultCode;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.CheckUtil;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.xiaomi.stat.MiStat;
import java.util.HashMap;
import p041c.HttpUrl;
import p041c.Request;

public class LoginThirdActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private static final String f9557a = "LoginThirdActivity";

    /* renamed from: b */
    private EditText f9558b;

    /* renamed from: c */
    private EditText f9559c;

    /* renamed from: d */
    private RelativeLayout f9560d;

    /* renamed from: e */
    private Button f9561e;

    /* renamed from: f */
    private Button f9562f;

    /* renamed from: g */
    private Button f9563g;

    /* renamed from: h */
    private Button f9564h;

    /* renamed from: i */
    private String f9565i;

    /* renamed from: j */
    private String f9566j;

    /* renamed from: k */
    private boolean f9567k = false;

    /* renamed from: l */
    private Request f9568l;

    /* renamed from: m */
    private TextView f9569m;

    /* renamed from: n */
    private int f9570n = 1;

    /* renamed from: o */
    private Dialog f9571o;

    /* renamed from: p */
    private TextView f9572p;

    /* renamed from: q */
    private LinearLayout f9573q;

    /* renamed from: r */
    private Button f9574r;

    /* renamed from: s */
    private Request f9575s;

    /* renamed from: t */
    private Request f9576t;

    /* renamed from: u */
    private OkRequestHandler<String> f9577u = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.loading.LoginThirdActivity.C26802 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            if (abVar == LoginThirdActivity.this.f9568l) {
                LoginThirdActivity.this.showProgressDialog(".....");
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            String str;
            if (!LoginThirdActivity.this.isFinishing()) {
                LoginThirdActivity.this.dismissProgressDialog();
                if (abVar == LoginThirdActivity.this.f9576t) {
                    Intent intent = new Intent(LoginThirdActivity.this, MainActivity.class);
                    intent.putExtra(Constant.f13323i, LoginThirdActivity.this.f9565i);
                    intent.putExtra("staticPassword", LoginThirdActivity.this.f9566j);
                    LoginThirdActivity.this.startActivity(intent);
                    LoginThirdActivity.this.finish();
                } else if (abVar == LoginThirdActivity.this.f9568l) {
                    String str2 = "";
                    HttpUrl a = LoginThirdActivity.this.f9568l.mo16085a();
                    if (a != null) {
                        str2 = a.mo16382i();
                    }
                    if (C3467s.m14513e(str2)) {
                        str = "登录失败，请检查网络";
                    } else {
                        str = "登录" + str2 + "失败，请检查网络";
                    }
                    GBActivity.showMessageToast(str);
                }
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (!LoginThirdActivity.this.isFinishing()) {
                LoginThirdActivity.this.dismissProgressDialog();
                if (abVar == LoginThirdActivity.this.f9568l) {
                    String str2 = "";
                    HttpUrl a = LoginThirdActivity.this.f9568l.mo16085a();
                    if (a != null) {
                        str2 = a.mo16382i();
                    }
                    Log.m14398b(LoginThirdActivity.f9557a, "loginRequest " + str2 + " succeed.");
                    LoginThirdActivity.this.m11201a((LoginThirdActivity) str);
                } else if (LoginThirdActivity.this.f9575s == abVar) {
                    LoginThirdActivity.this.m11206b((LoginThirdActivity) str);
                } else if (abVar == LoginThirdActivity.this.f9576t) {
                    LoginThirdActivity.this.m11210c((LoginThirdActivity) str);
                    GBGlobalConfig.m10510c().mo24414n();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "新登录界面", C2425R.layout.login_activity_third, false);
        m11199a();
    }

    /* renamed from: a */
    private void m11199a() {
        this.f9558b = (EditText) findViewById(C2425R.C2427id.phone_et);
        this.f9559c = (EditText) findViewById(C2425R.C2427id.static_password_et);
        this.f9559c.addTextChangedListener(new TextWatcher() {
            /* class com.gbcom.gwifi.functions.loading.LoginThirdActivity.C26761 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String obj = LoginThirdActivity.this.f9559c.getText().toString();
                String c = CheckUtil.m14085c(obj);
                if (!obj.equals(c) && c.length() < obj.length()) {
                    LoginThirdActivity.this.f9559c.setText(c);
                    LoginThirdActivity.this.f9559c.setSelection(c.length());
                    C3470v.m14563a(GBApplication.instance(), "字符非法");
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.f9562f = (Button) findViewById(C2425R.C2427id.login_btn);
        this.f9563g = (Button) findViewById(C2425R.C2427id.re_login_btn);
        this.f9560d = (RelativeLayout) findViewById(C2425R.C2427id.show_password);
        this.f9564h = (Button) findViewById(C2425R.C2427id.forget_password_btn);
        this.f9561e = (Button) findViewById(C2425R.C2427id.show_btn);
        this.f9569m = (TextView) findViewById(C2425R.C2427id.login_id);
        this.f9572p = (TextView) findViewById(C2425R.C2427id.type_tv);
        this.f9573q = (LinearLayout) findViewById(C2425R.C2427id.net_rl);
        this.f9574r = (Button) findViewById(C2425R.C2427id.net_btn);
        this.f9562f.setOnClickListener(this);
        this.f9563g.setOnClickListener(this);
        this.f9560d.setOnClickListener(this);
        this.f9561e.setOnClickListener(this);
        this.f9564h.setOnClickListener(this);
        this.f9569m.setOnClickListener(this);
        this.f9573q.setOnClickListener(this);
        this.f9574r.setOnClickListener(this);
        this.f9565i = getIntent().getStringExtra(Constant.f13323i);
        if (C3467s.m14513e(this.f9565i)) {
            this.f9565i = CacheAccount.getInstance().getLastLoginPhone();
        }
        this.f9558b.setText(this.f9565i);
        m11208c();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
        m11204b();
    }

    /* renamed from: b */
    private void m11204b() {
        this.f9575s = HttpUtil.m14319f(this.f9577u, "");
    }

    /* renamed from: c */
    private void m11208c() {
        int loginServiceType = CacheAccount.getInstance().getLoginServiceType();
        if (loginServiceType == 1) {
            this.f9570n = loginServiceType;
            if (SystemUtil.m14533f()) {
                this.f9572p.setText(Constant.f13198aZ);
            } else {
                this.f9572p.setText(Constant.f13252ba);
            }
        }
        if (loginServiceType == 2) {
            this.f9570n = loginServiceType;
            this.f9572p.setText(Constant.f13253bb);
        }
        if (loginServiceType == 3) {
            this.f9570n = loginServiceType;
            this.f9572p.setText(Constant.f13254bc);
        }
        if (loginServiceType == 4) {
            this.f9570n = loginServiceType;
            this.f9572p.setText(Constant.f13255bd);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.login_btn /*{ENCODED_INT: 2131755638}*/:
                m11212d();
                m11218g();
                return;
            case C2425R.C2427id.show_password /*{ENCODED_INT: 2131755644}*/:
            case C2425R.C2427id.show_btn /*{ENCODED_INT: 2131755645}*/:
                if (!this.f9567k) {
                    this.f9559c.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    this.f9567k = true;
                    this.f9561e.setBackgroundResource(C2425R.C2426drawable.show_password);
                    Editable text = this.f9559c.getText();
                    if (text instanceof Spannable) {
                        Selection.setSelection(text, text.length());
                        return;
                    }
                    return;
                }
                this.f9559c.setTransformationMethod(PasswordTransformationMethod.getInstance());
                this.f9561e.setBackgroundResource(C2425R.C2426drawable.noshow_password);
                this.f9567k = false;
                Editable text2 = this.f9559c.getText();
                if (text2 instanceof Spannable) {
                    Selection.setSelection(text2, text2.length());
                    return;
                }
                return;
            case C2425R.C2427id.re_login_btn /*{ENCODED_INT: 2131755647}*/:
                m11216f();
                return;
            case C2425R.C2427id.login_id /*{ENCODED_INT: 2131755648}*/:
                Intent intent = new Intent(this, StatusLoginActivity.class);
                intent.putExtra(Constant.f13323i, this.f9565i);
                startActivity(intent);
                finish();
                return;
            case C2425R.C2427id.forget_password_btn /*{ENCODED_INT: 2131755669}*/:
                Intent intent2 = new Intent(this, SetPasswordActivity.class);
                intent2.putExtra(Constant.f13323i, this.f9565i);
                intent2.putExtra("isChanged", true);
                intent2.putExtra("setPassword", MiStat.Event.LOGIN);
                startActivity(intent2);
                finish();
                return;
            case C2425R.C2427id.net_rl /*{ENCODED_INT: 2131755671}*/:
            case C2425R.C2427id.net_btn /*{ENCODED_INT: 2131755673}*/:
                m11214e();
                return;
            default:
                return;
        }
    }

    /* renamed from: d */
    private void m11212d() {
        String charSequence = this.f9572p.getText().toString();
        if (charSequence.equals(Constant.f13252ba)) {
            this.f9570n = 1;
        } else if (charSequence.equals(Constant.f13253bb)) {
            this.f9570n = 2;
        } else if (charSequence.equals(Constant.f13254bc)) {
            this.f9570n = 3;
        } else if (charSequence.equals(Constant.f13255bd)) {
            this.f9570n = 4;
        }
        if (SystemUtil.m14533f() && charSequence.equals(Constant.f13198aZ)) {
            this.f9570n = 1;
        }
    }

    /* renamed from: e */
    private void m11214e() {
        TextView textView;
        final Dialog dialog = new Dialog(this, C2425R.C2430style.login_dialog);
        View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.login_item_net_type, (ViewGroup) null);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.one)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.LoginThirdActivity.View$OnClickListenerC26835 */

            public void onClick(View view) {
                if (dialog != null) {
                    dialog.dismiss();
                    if (SystemUtil.m14533f()) {
                        LoginThirdActivity.this.f9572p.setText(Constant.f13198aZ);
                    } else {
                        LoginThirdActivity.this.f9572p.setText(Constant.f13252ba);
                    }
                }
            }
        });
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.two)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.LoginThirdActivity.View$OnClickListenerC26846 */

            public void onClick(View view) {
                if (dialog != null) {
                    dialog.dismiss();
                    LoginThirdActivity.this.f9572p.setText(Constant.f13253bb);
                }
            }
        });
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.three)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.LoginThirdActivity.View$OnClickListenerC26857 */

            public void onClick(View view) {
                if (dialog != null) {
                    dialog.dismiss();
                    LoginThirdActivity.this.f9572p.setText(Constant.f13254bc);
                }
            }
        });
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.four)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.LoginThirdActivity.View$OnClickListenerC26868 */

            public void onClick(View view) {
                if (dialog != null) {
                    dialog.dismiss();
                    LoginThirdActivity.this.f9572p.setText(Constant.f13255bd);
                }
            }
        });
        if (SystemUtil.m14533f() && (textView = (TextView) inflate.findViewById(C2425R.C2427id.one_text)) != null) {
            textView.setText(Constant.f13198aZ);
        }
        dialog.setContentView(inflate, new ViewGroup.LayoutParams((int) getResources().getDimension(C2425R.dimen.dialog_width), -2));
        dialog.show();
    }

    /* renamed from: f */
    private void m11216f() {
        startActivity(new Intent(this, RegisterUserActivity.class));
        finish();
    }

    /* renamed from: g */
    private void m11218g() {
        UmengCount.queryLoginIn(getApplicationContext());
        this.f9565i = this.f9558b.getText().toString();
        if (TextUtils.isEmpty(this.f9565i)) {
            GBActivity.showMessageToast("账号不能为空");
        } else if (this.f9565i.length() < 6 || this.f9565i.length() > 16) {
            GBActivity.showMessageToast("账号不合法");
        } else {
            this.f9566j = this.f9559c.getText().toString();
            if (TextUtils.isEmpty(this.f9566j)) {
                GBActivity.showMessageToast("密码不能为空");
            } else if (this.f9566j.length() < 6 || this.f9566j.length() > 16) {
                GBActivity.showMessageToast("密码必须为6-16位");
            } else if (C3467s.m14513e(this.f9572p.getText().toString())) {
                GBActivity.showMessageToast("请选择服务类型");
            } else if (CacheAuth.getInstance().isPortal()) {
                this.f9568l = HttpUtil.m14294b(this, this.f9565i, this.f9566j, this.f9570n, this.f9577u, "");
            } else {
                MultiCheckNetWorkUtils.m13939a(GBApplication.instance(), new CallBack() {
                    /* class com.gbcom.gwifi.functions.loading.LoginThirdActivity.C26879 */

                    @Override // com.gbcom.gwifi.base.p234c.CallBack
                    /* renamed from: a */
                    public void mo24437a(Object obj) {
                        if (CacheAuth.getInstance().isPortal()) {
                            LoginThirdActivity.this.f9568l = HttpUtil.m14294b(LoginThirdActivity.this, LoginThirdActivity.this.f9565i, LoginThirdActivity.this.f9566j, LoginThirdActivity.this.f9570n, LoginThirdActivity.this.f9577u, "");
                            return;
                        }
                        LoginThirdActivity.this.f9568l = HttpUtil.m14263a(LoginThirdActivity.this, LoginThirdActivity.this.f9565i, LoginThirdActivity.this.f9566j, LoginThirdActivity.this.f9570n, LoginThirdActivity.this.f9577u, "");
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11201a(String str) {
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (ResultCode.m14476b(deSerializeJson)) {
            CacheAccount.getInstance().setLastLoginType(1);
            CacheAccount.getInstance().setLoginPhone(this.f9565i);
            CacheAccount.getInstance().setLoginStaticPassword(this.f9566j);
            CacheAccount.getInstance().setLoginServiceType(this.f9570n);
            String lastLoginPhone = CacheAccount.getInstance().getLastLoginPhone();
            if (!C3467s.m14513e(this.f9565i) && !this.f9565i.equals(lastLoginPhone)) {
                CacheAccount.getInstance().setLastLoginPhone(this.f9565i);
                CacheAccount.getInstance().resetCacheAccountBean();
                CacheAccount.getInstance().setLoginServiceType(1);
            }
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constant.f13323i, this.f9565i);
            intent.putExtra("staticPassword", this.f9566j);
            startActivity(intent);
            finish();
        } else if (deSerializeJson == null) {
            showMessageToast("解析失败");
        } else {
            int intValue = deSerializeJson.getResultCode().intValue();
            if (AuthResultCode.m13885b(intValue)) {
                m11202a(deSerializeJson.getResultMsg(), "注册账号", new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.loading.LoginThirdActivity.C267710 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                        LoginThirdActivity.this.startActivity(new Intent(LoginThirdActivity.this, RegisterUserActivity.class).putExtra(Constant.f13323i, LoginThirdActivity.this.f9565i));
                        LoginThirdActivity.this.finish();
                    }
                });
            } else if (AuthResultCode.m13886c(intValue)) {
                m11202a(deSerializeJson.getResultMsg(), "设置密码", new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.loading.LoginThirdActivity.C267811 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                        Intent intent = new Intent(LoginThirdActivity.this, SetPasswordActivity.class);
                        intent.putExtra(Constant.f13323i, LoginThirdActivity.this.f9565i);
                        intent.putExtra("setPassword", MiStat.Event.LOGIN);
                        LoginThirdActivity.this.startActivity(intent);
                        LoginThirdActivity.this.finish();
                    }
                });
            } else if (AuthResultCode.m13887d(intValue)) {
                m11202a(deSerializeJson.getResultMsg(), "联系客服", new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.loading.LoginThirdActivity.C267912 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                        LoginThirdActivity.this.contactClientPhone();
                    }
                });
            } else if (AuthResultCode.m13888e(intValue)) {
                GBGlobalConfig.m10510c().mo24411k();
            } else if (AuthResultCode.m13889f(intValue)) {
                GBGlobalConfig.m10510c().mo24412l();
            } else {
                showMessageToast(deSerializeJson.getResultMsg());
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11206b(String str) {
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (ResultCode.m14476b(deSerializeJson)) {
            HashMap hashMap = (HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class);
            if (hashMap.get("hotspot_group_id") != null && hashMap.get("hotspot_group_type") != null) {
                int intValue = ((Integer) hashMap.get("hotspot_group_type")).intValue();
                CacheAuth.getInstance().setOrgType(intValue);
                if (intValue != 2 && intValue != 1) {
                    this.f9569m.setVisibility(4);
                } else if (CacheAccount.getInstance().getAppLoginSnState() == 0) {
                    this.f9569m.setVisibility(4);
                } else {
                    this.f9569m.setVisibility(0);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11210c(String str) {
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
            if (hashMap.get("department_id") != null) {
                i2 = ((Integer) hashMap.get("department_id")).intValue();
            } else {
                i2 = 0;
            }
            if (hashMap.get("identity_type") != null) {
                i3 = ((Integer) hashMap.get("identity_type")).intValue();
            }
            CacheAccount.getInstance().setCacheAccountBean(intValue, str2, valueOf.intValue(), i, i2, i3);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constant.f13323i, this.f9565i);
            intent.putExtra("staticPassword", this.f9566j);
            startActivity(intent);
            finish();
        }
    }

    /* renamed from: h */
    private void m11220h() {
        startActivity(new Intent(this, SetPasswordActivity.class));
        finish();
    }

    /* renamed from: a */
    private void m11202a(String str, String str2, final GBActivity.AbstractC2517a aVar) {
        this.f9571o = new Dialog(this, C2425R.C2430style.login_dialog);
        View inflate = LayoutInflater.from(getApplicationContext()).inflate(C2425R.layout.common_dialog, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.cancl_bt);
        Button button = (Button) inflate.findViewById(C2425R.C2427id.add_bt);
        View inflate2 = getLayoutInflater().inflate(C2425R.layout.common_dialog_content, (ViewGroup) null);
        TextView textView = (TextView) inflate2.findViewById(2131755301);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView.setText(C3467s.m14514f(str));
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.view_layout)).addView(inflate2, -1, -2);
        ((TextView) inflate2.findViewById(C2425R.C2427id.dialog_title)).setText("");
        button.setText(str2);
        if (TextUtils.isEmpty(str2)) {
            button.setVisibility(8);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.LoginThirdActivity.View$OnClickListenerC26813 */

            public void onClick(View view) {
                if (LoginThirdActivity.this.f9571o != null) {
                    LoginThirdActivity.this.f9571o.dismiss();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.LoginThirdActivity.View$OnClickListenerC26824 */

            public void onClick(View view) {
                if (aVar != null) {
                    if (LoginThirdActivity.this.f9571o != null) {
                        aVar.onClick(LoginThirdActivity.this.f9571o, view);
                    }
                } else if (LoginThirdActivity.this.f9571o != null) {
                    LoginThirdActivity.this.f9571o.dismiss();
                }
            }
        });
        this.f9571o.setContentView(inflate, new ViewGroup.LayoutParams((int) getResources().getDimension(C2425R.dimen.dialog_width), -2));
        this.f9571o.setCancelable(!isReallyMust.booleanValue());
        this.f9571o.show();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        if (this.f9571o != null && this.f9571o.isShowing()) {
            this.f9571o.dismiss();
            this.f9571o = null;
        }
        super.onDestroy();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        checkAgreementYes();
    }
}
