package com.gbcom.gwifi.functions.loading;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.gbcom.gwifi.functions.wifi.entity.CheckResult;
import com.gbcom.gwifi.functions.wifi.p253a.AuthResultCode;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.CheckUtil;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.taobao.agoo.p359a.p360a.C5719c;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import p041c.Request;

public class RegisterUserActivity extends GBActivity implements View.OnClickListener {
    public static final int FOWARD_TYPE_CONTACT_KEFU = 3;
    public static final int FOWARD_TYPE_LOGIN = 1;
    public static final int FOWARD_TYPE_SET_PASSWORD = 2;

    /* renamed from: o */
    private static int f9718o = 180;

    /* renamed from: p */
    private static boolean f9719p = false;

    /* renamed from: q */
    private static boolean f9720q = false;

    /* renamed from: A */
    private OkRequestHandler<String> f9721A = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.C274113 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            if (abVar != RegisterUserActivity.this.f9734m && abVar == RegisterUserActivity.this.f9735n) {
                RegisterUserActivity.this.showProgressDialog(".....");
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            RegisterUserActivity.this.dismissProgressDialog();
            if (abVar == RegisterUserActivity.this.f9734m) {
                GBActivity.showMessageToast("获取验证码失败，请检查网络");
                boolean unused = RegisterUserActivity.f9719p = true;
                boolean unused2 = RegisterUserActivity.f9720q = false;
            } else if (abVar == RegisterUserActivity.this.f9735n) {
                GBActivity.showMessageToast("注册失败，请检查网络");
                RegisterUserActivity.this.f9728g.setEnabled(true);
                RegisterUserActivity.this.f9728g.setText("注册");
            } else if (abVar == RegisterUserActivity.this.f9744z) {
                GBActivity.showMessageToast("获取用户信息失败，请检查网络");
                RegisterUserActivity.this.startActivity(new Intent(RegisterUserActivity.this, MainActivity.class));
                RegisterUserActivity.this.finish();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            RegisterUserActivity.this.dismissProgressDialog();
            CommonMsg.deSerializeJson(str.getBytes());
            if (abVar == RegisterUserActivity.this.f9744z) {
                RegisterUserActivity.this.m11314a((RegisterUserActivity) str);
            } else if (abVar == RegisterUserActivity.this.f9734m) {
                RegisterUserActivity.this.m11323b((RegisterUserActivity) str);
            } else if (abVar == RegisterUserActivity.this.f9735n) {
                RegisterUserActivity.this.m11329c((RegisterUserActivity) str);
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: a */
    private EditText f9722a;

    /* renamed from: b */
    private EditText f9723b;

    /* renamed from: c */
    private Button f9724c;

    /* renamed from: d */
    private EditText f9725d;

    /* renamed from: e */
    private RelativeLayout f9726e;

    /* renamed from: f */
    private Button f9727f;

    /* renamed from: g */
    private Button f9728g;

    /* renamed from: h */
    private Button f9729h;

    /* renamed from: i */
    private String f9730i;

    /* renamed from: j */
    private String f9731j;

    /* renamed from: k */
    private String f9732k;

    /* renamed from: l */
    private boolean f9733l = false;

    /* renamed from: m */
    private Request f9734m;

    /* renamed from: n */
    private Request f9735n;

    /* renamed from: r */
    private CheckBox f9736r;

    /* renamed from: s */
    private TextView f9737s;
    public Handler smsHandler = new Handler() {
        /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.HandlerC27476 */
    };

    /* renamed from: t */
    private final int f9738t = 2;

    /* renamed from: u */
    private TextView f9739u;

    /* renamed from: v */
    private Dialog f9740v;

    /* renamed from: w */
    private AtomicBoolean f9741w = new AtomicBoolean(false);

    /* renamed from: x */
    private boolean f9742x = false;

    /* renamed from: y */
    private boolean f9743y = false;

    /* renamed from: z */
    private Request f9744z;

    /* renamed from: b */
    static /* synthetic */ int m11320b() {
        int i = f9718o;
        f9718o = i - 1;
        return i;
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "新注册界面", C2425R.layout.login_activity_register, false);
        m11333e();
        MultiCheckNetWorkUtils.m13944b(GBApplication.instance(), new CallBack() {
            /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.C27371 */

            @Override // com.gbcom.gwifi.base.p234c.CallBack
            /* renamed from: a */
            public void mo24437a(Object obj) {
                CheckResult checkResult = (CheckResult) obj;
                if (checkResult != null) {
                    RegisterUserActivity.this.f9743y = checkResult.isGiwifi();
                }
            }
        });
    }

    /* renamed from: e */
    private void m11333e() {
        this.f9722a = (EditText) findViewById(C2425R.C2427id.phone_et);
        this.f9723b = (EditText) findViewById(C2425R.C2427id.password_et);
        this.f9725d = (EditText) findViewById(C2425R.C2427id.static_password_et);
        this.f9725d.addTextChangedListener(new TextWatcher() {
            /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.C27487 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String obj = RegisterUserActivity.this.f9725d.getText().toString();
                String c = CheckUtil.m14085c(obj);
                if (!obj.equals(c) && c.length() < obj.length()) {
                    RegisterUserActivity.this.f9725d.setText(c);
                    RegisterUserActivity.this.f9725d.setSelection(c.length());
                    C3470v.m14563a(GBApplication.instance(), "字符非法");
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.f9724c = (Button) findViewById(C2425R.C2427id.get_password_btn);
        this.f9726e = (RelativeLayout) findViewById(C2425R.C2427id.show_password);
        this.f9728g = (Button) findViewById(C2425R.C2427id.login_btn);
        this.f9729h = (Button) findViewById(C2425R.C2427id.re_login_btn);
        this.f9727f = (Button) findViewById(C2425R.C2427id.show_btn);
        this.f9724c.setOnClickListener(this);
        this.f9726e.setOnClickListener(this);
        this.f9727f.setOnClickListener(this);
        this.f9728g.setOnClickListener(this);
        this.f9729h.setOnClickListener(this);
        this.f9730i = getIntent().getStringExtra(Constant.f13323i);
        this.f9722a.setText(this.f9730i);
        this.f9737s = (TextView) findViewById(C2425R.C2427id.user_protocol);
        this.f9737s.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.View$OnClickListenerC27498 */

            public void onClick(View view) {
                String g = HttpUtil.m14323g();
                if (!C3467s.m14513e(g)) {
                    GBActivity.openBackWebView(g, "用户服务协议");
                } else {
                    GBActivity.showMessageToast("链接无效...");
                }
            }
        });
        this.f9736r = (CheckBox) findViewById(16908289);
        this.f9736r.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.C27509 */

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    RegisterUserActivity.this.f9728g.setEnabled(true);
                } else {
                    RegisterUserActivity.this.f9728g.setEnabled(false);
                }
            }
        });
        this.f9739u = (TextView) findViewById(C2425R.C2427id.login_id);
        this.f9739u.setOnClickListener(this);
        if (SystemUtil.m14533f()) {
            this.f9736r.setVisibility(8);
            this.f9737s.setVisibility(8);
            TextView textView = (TextView) findViewById(16908308);
            if (textView != null) {
                textView.setVisibility(8);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0053, code lost:
        m11335f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
        if (r11.f9743y == false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005a, code lost:
        r11.f9734m = com.gbcom.gwifi.util.HttpUtil.m14260a(r11, r11.f9730i, 1, r11.f9721A, "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0067, code lost:
        r11.f9734m = com.gbcom.gwifi.util.HttpUtil.m14260a(r11, r11.f9730i, 3, r11.f9721A, "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r12) {
        /*
        // Method dump skipped, instructions count: 484
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.functions.loading.RegisterUserActivity.onClick(android.view.View):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11314a(String str) {
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
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11323b(String str) {
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (deSerializeJson == null) {
            showMessageToast("解析失败");
        } else if (GBActivity.dealException(deSerializeJson)) {
        } else {
            if (ResultCode.m14476b(deSerializeJson)) {
                f9720q = true;
                GBActivity.showMessageToast("获取验证码成功");
                return;
            }
            f9720q = false;
            f9719p = true;
            int intValue = deSerializeJson.getResultCode().intValue();
            if (AuthResultCode.m13890g(intValue)) {
                m11315a(deSerializeJson.getResultMsg(), "登录", 1);
            } else if (AuthResultCode.m13886c(intValue)) {
                m11315a(deSerializeJson.getResultMsg(), "设置密码", 2);
            } else if (AuthResultCode.m13887d(intValue)) {
                m11315a(deSerializeJson.getResultMsg(), "联系客服", 3);
            } else {
                showMessageToast(deSerializeJson.getResultMsg());
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11329c(String str) {
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (deSerializeJson == null) {
            showMessageToast("解析失败");
        } else if (GBActivity.dealException(deSerializeJson)) {
        } else {
            if (ResultCode.m14476b(deSerializeJson)) {
                HashMap hashMap = (HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class);
                Integer num = (Integer) hashMap.get("userType");
                Integer num2 = (Integer) hashMap.get("limitTime");
                Integer num3 = (Integer) hashMap.get("need_complete_data");
                final String str2 = (String) hashMap.get("complete_data_url");
                CacheAccount.getInstance().updateInfo(this.f9730i, (String) hashMap.get("userToken"));
                if (this.f9730i != null && !this.f9730i.equals("")) {
                    CacheAccount.getInstance().setLoginPhone(this.f9730i);
                    CacheAccount.getInstance().setLoginStaticPassword(this.f9732k);
                }
                if (num3 == null || num3.intValue() != 1) {
                    GBActivity.showMessageToast("注册成功");
                    CacheAccount.getInstance().setLastLoginType(1);
                    if (!this.f9730i.equals(CacheAccount.getInstance().getLastLoginPhone())) {
                        CacheAccount.getInstance().resetCacheAccountBean();
                    }
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    return;
                }
                GBApplication.instance().getCurrentActivity().showNormalDialog("提示", "请完善您的个人信息", "立即完善", "关闭", (GBActivity.AbstractC2517a) new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.C273810 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        RegisterUserActivity.this.f9742x = true;
                        dialog.dismiss();
                        GBApplication.instance().getCurrentActivity();
                        GBActivity.openBackWebView(str2, "完善个人信息", 1);
                    }
                }, (GBActivity.AbstractC2517a) new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.C273911 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                        CacheAccount.getInstance().setLastLoginType(1);
                        if (!RegisterUserActivity.this.f9730i.equals(CacheAccount.getInstance().getLastLoginPhone())) {
                            CacheAccount.getInstance().resetCacheAccountBean();
                        }
                        RegisterUserActivity.this.startActivity(new Intent(RegisterUserActivity.this, MainActivity.class));
                        RegisterUserActivity.this.finish();
                    }
                }, false, (DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                    /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.DialogInterface$OnCancelListenerC274012 */

                    public void onCancel(DialogInterface dialogInterface) {
                        CacheAccount.getInstance().setLastLoginType(1);
                        if (!RegisterUserActivity.this.f9730i.equals(CacheAccount.getInstance().getLastLoginPhone())) {
                            CacheAccount.getInstance().resetCacheAccountBean();
                        }
                        RegisterUserActivity.this.startActivity(new Intent(RegisterUserActivity.this, MainActivity.class));
                        RegisterUserActivity.this.finish();
                    }
                });
                return;
            }
            int intValue = deSerializeJson.getResultCode().intValue();
            if (AuthResultCode.m13890g(intValue)) {
                m11315a(deSerializeJson.getResultMsg(), "登录", 1);
            } else if (AuthResultCode.m13886c(intValue)) {
                m11315a(deSerializeJson.getResultMsg(), "设置密码", 2);
            } else if (AuthResultCode.m13887d(intValue)) {
                m11315a(deSerializeJson.getResultMsg(), "联系客服", 3);
            } else if (AuthResultCode.m13888e(intValue)) {
                GBGlobalConfig.m10510c().mo24411k();
            } else if (AuthResultCode.m13889f(intValue)) {
                GBGlobalConfig.m10510c().mo24412l();
            } else {
                showMessageToast(deSerializeJson.getResultMsg());
            }
            this.f9728g.setEnabled(true);
            this.f9728g.setText("注 册");
        }
    }

    /* renamed from: a */
    private void m11315a(String str, String str2, final int i) {
        m11316a(str, str2, new GBActivity.AbstractC2517a() {
            /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.C27422 */

            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
            public void onClick(Dialog dialog, View view) {
                dialog.dismiss();
                if (i == 1) {
                    RegisterUserActivity.this.startActivity(new Intent(RegisterUserActivity.this, LoginThirdActivity.class).putExtra(Constant.f13323i, RegisterUserActivity.this.f9730i));
                    RegisterUserActivity.this.finish();
                } else if (i == 2) {
                    Intent intent = new Intent(RegisterUserActivity.this, SetPasswordActivity.class);
                    intent.putExtra(Constant.f13323i, RegisterUserActivity.this.f9730i);
                    intent.putExtra("setPassword", C5719c.JSON_CMD_REGISTER);
                    RegisterUserActivity.this.startActivity(intent);
                    RegisterUserActivity.this.finish();
                } else if (i == 3) {
                    RegisterUserActivity.this.contactClientPhone();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
        if (this.f9742x) {
            CacheAccount.getInstance().setLastLoginType(1);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    /* renamed from: f */
    private void m11335f() {
        this.f9724c.setEnabled(false);
        new Thread(new Runnable() {
            /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.RunnableC27433 */

            @Override // java.lang.Runnable
            public void run() {
                int unused = RegisterUserActivity.f9718o = 180;
                boolean unused2 = RegisterUserActivity.f9719p = false;
                while (!RegisterUserActivity.f9719p) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        boolean unused3 = RegisterUserActivity.f9719p = true;
                        e.printStackTrace();
                    }
                    RegisterUserActivity.m11320b();
                    if (RegisterUserActivity.f9718o == 0) {
                        boolean unused4 = RegisterUserActivity.f9719p = true;
                    }
                    RegisterUserActivity.this.runOnUiThread(new Runnable() {
                        /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.RunnableC27433.RunnableC27441 */

                        @Override // java.lang.Runnable
                        public void run() {
                            if (RegisterUserActivity.f9718o == 0 || RegisterUserActivity.f9719p) {
                                RegisterUserActivity.this.f9724c.setText("获取验证码");
                                RegisterUserActivity.this.f9724c.setEnabled(true);
                                synchronized (RegisterUserActivity.this.f9741w) {
                                    RegisterUserActivity.this.f9741w.set(false);
                                }
                            } else if (RegisterUserActivity.f9720q) {
                                RegisterUserActivity.this.f9724c.setText(RegisterUserActivity.f9718o + "秒后");
                            }
                        }
                    });
                }
            }
        }).start();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            startActivity(new Intent(this, LoginThirdActivity.class).putExtra(Constant.f13323i, this.f9730i));
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* renamed from: a */
    private void m11316a(String str, String str2, final GBActivity.AbstractC2517a aVar) {
        this.f9740v = new Dialog(this, C2425R.C2430style.login_dialog);
        View inflate = LayoutInflater.from(getApplicationContext()).inflate(C2425R.layout.common_dialog, (ViewGroup) null);
        Button button = (Button) inflate.findViewById(C2425R.C2427id.add_bt);
        View inflate2 = getLayoutInflater().inflate(C2425R.layout.common_dialog_content, (ViewGroup) null);
        TextView textView = (TextView) inflate2.findViewById(2131755301);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView.setText(str);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.view_layout)).addView(inflate2, -1, -2);
        ((TextView) inflate2.findViewById(C2425R.C2427id.dialog_title)).setText("");
        button.setText(str2);
        ((ImageView) inflate.findViewById(C2425R.C2427id.cancl_bt)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.View$OnClickListenerC27454 */

            public void onClick(View view) {
                if (RegisterUserActivity.this.f9740v != null) {
                    RegisterUserActivity.this.f9740v.dismiss();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.RegisterUserActivity.View$OnClickListenerC27465 */

            public void onClick(View view) {
                if (aVar != null) {
                    if (RegisterUserActivity.this.f9740v != null) {
                        aVar.onClick(RegisterUserActivity.this.f9740v, view);
                    }
                } else if (RegisterUserActivity.this.f9740v != null) {
                    RegisterUserActivity.this.f9740v.dismiss();
                }
            }
        });
        this.f9740v.setContentView(inflate, new ViewGroup.LayoutParams((int) getResources().getDimension(C2425R.dimen.dialog_width), -2));
        this.f9740v.setCancelable(!isReallyMust.booleanValue());
        this.f9740v.show();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        if (this.f9740v != null && this.f9740v.isShowing()) {
            this.f9740v.dismiss();
            this.f9740v = null;
        }
        super.onDestroy();
    }
}
