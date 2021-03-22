package com.gbcom.gwifi.functions.loading;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.p009v4.content.ContextCompat;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.codec.EncryptUtil;
import com.gbcom.gwifi.functions.loading.domain.OrgsInfo;
import com.gbcom.gwifi.functions.wifi.MultiCheckNetWorkUtils;
import com.gbcom.gwifi.functions.wifi.p253a.AuthResultCode;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.location.baidu.GiwifiLocationListener;
import com.gbcom.gwifi.third.location.baidu.GiwifiLocationService;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.CheckUtil;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.ResourceParse;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.umeng.message.common.C6366a;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.xpath.XPath;
import org.json.JSONObject;
import p041c.Request;

public class StatusLoginActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: b */
    private static final String f9794b = "StatusLoginActivity";

    /* renamed from: A */
    private int f9795A = 2;

    /* renamed from: B */
    private boolean f9796B = false;

    /* renamed from: C */
    private RelativeLayout f9797C;

    /* renamed from: D */
    private TextView f9798D;

    /* renamed from: E */
    private EditText f9799E;

    /* renamed from: F */
    private Request f9800F;

    /* renamed from: G */
    private String f9801G;

    /* renamed from: H */
    private Request f9802H;

    /* renamed from: I */
    private Gson f9803I;

    /* renamed from: J */
    private boolean f9804J;

    /* renamed from: K */
    private boolean f9805K;

    /* renamed from: L */
    private double f9806L;

    /* renamed from: M */
    private double f9807M;

    /* renamed from: N */
    private boolean f9808N;

    /* renamed from: O */
    private boolean f9809O;

    /* renamed from: P */
    private GiwifiLocationService f9810P;

    /* renamed from: a */
    final OkRequestHandler<String> f9811a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.C27765 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            try {
                StatusLoginActivity.this.showProgressDialog("正在加载...");
            } catch (Exception e) {
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (!StatusLoginActivity.this.isFinishing()) {
                StatusLoginActivity.this.f9826q = false;
                StatusLoginActivity.this.dismissProgressDialog();
                if (abVar == StatusLoginActivity.this.f9800F) {
                    Intent intent = new Intent(StatusLoginActivity.this, MainActivity.class);
                    intent.putExtra(Constant.f13323i, StatusLoginActivity.this.f9801G);
                    intent.putExtra("staticPassword", StatusLoginActivity.this.f9821l.getText().toString());
                    StatusLoginActivity.this.startActivity(intent);
                    StatusLoginActivity.this.finish();
                } else if (abVar == StatusLoginActivity.this.f9827r) {
                    GBActivity.showMessageToast("学工号登录失败，请检查网络");
                } else if (abVar == StatusLoginActivity.this.f9802H) {
                    GBActivity.showMessageToast("定位学校失败，请检查网络");
                }
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (!StatusLoginActivity.this.isFinishing()) {
                StatusLoginActivity.this.f9826q = false;
                StatusLoginActivity.this.dismissProgressDialog();
                if (StatusLoginActivity.this.f9827r == abVar) {
                    StatusLoginActivity.this.m11374a((StatusLoginActivity) str);
                } else if (abVar == StatusLoginActivity.this.f9800F) {
                    StatusLoginActivity.this.m11382b((StatusLoginActivity) str);
                    GBGlobalConfig.m10510c().mo24414n();
                } else if (abVar == StatusLoginActivity.this.f9802H) {
                    StatusLoginActivity.this.m11388c((StatusLoginActivity) str);
                } else if (abVar == StatusLoginActivity.this.f9828s) {
                    StatusLoginActivity.this.m11395d((StatusLoginActivity) str);
                } else if (abVar == StatusLoginActivity.this.f9829t) {
                    StatusLoginActivity.this.m11399e((StatusLoginActivity) str);
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: c */
    private RelativeLayout f9812c;

    /* renamed from: d */
    private TextView f9813d;

    /* renamed from: e */
    private int f9814e = 0;

    /* renamed from: f */
    private RelativeLayout f9815f;

    /* renamed from: g */
    private TextView f9816g;

    /* renamed from: h */
    private RelativeLayout f9817h;

    /* renamed from: i */
    private TextView f9818i;

    /* renamed from: j */
    private int f9819j = 0;

    /* renamed from: k */
    private EditText f9820k;

    /* renamed from: l */
    private EditText f9821l;

    /* renamed from: m */
    private Button f9822m;

    /* renamed from: n */
    private TextView f9823n;

    /* renamed from: o */
    private PopupWindow f9824o;

    /* renamed from: p */
    private ArrayList<String> f9825p = new ArrayList<>();

    /* renamed from: q */
    private boolean f9826q = false;

    /* renamed from: r */
    private Request f9827r;

    /* renamed from: s */
    private Request f9828s;

    /* renamed from: t */
    private Request f9829t;

    /* renamed from: u */
    private String f9830u;

    /* renamed from: v */
    private CheckBox f9831v;

    /* renamed from: w */
    private TextView f9832w;

    /* renamed from: x */
    private Button f9833x;

    /* renamed from: y */
    private RelativeLayout f9834y;

    /* renamed from: z */
    private Button f9835z;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "身份选择界面", C2425R.layout.login_activity_status, false);
        m11393d();
        m11386c();
        m11368a();
    }

    /* renamed from: a */
    private void m11368a() {
        this.f9810P = new GiwifiLocationService(getApplicationContext(), new GiwifiLocationListener() {
            /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.C27591 */

            @Override // com.gbcom.gwifi.third.location.baidu.GiwifiLocationListener
            public void onReceiveLocation(double d, double d2) {
                StatusLoginActivity.this.f9806L = d;
                StatusLoginActivity.this.f9807M = d2;
                if (StatusLoginActivity.this.f9806L == Double.MIN_VALUE || StatusLoginActivity.this.f9807M == Double.MIN_VALUE || StatusLoginActivity.this.f9806L == XPath.MATCH_SCORE_QNAME || StatusLoginActivity.this.f9807M == XPath.MATCH_SCORE_QNAME) {
                    if (!StatusLoginActivity.this.f9809O) {
                        StatusLoginActivity.this.m11416m();
                    }
                    StatusLoginActivity.this.f9809O = true;
                    return;
                }
                if (!StatusLoginActivity.this.f9808N) {
                    StatusLoginActivity.this.m11369a((StatusLoginActivity) StatusLoginActivity.this.f9806L, StatusLoginActivity.this.f9807M);
                }
                StatusLoginActivity.this.f9808N = true;
                StatusLoginActivity.this.f9810P.stop();
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
        if (!this.f9798D.getText().toString().trim().equals(CacheAccount.getInstance().getHotspotGroupName())) {
        }
        m11380b();
    }

    /* renamed from: b */
    private void m11380b() {
        if (CacheAuth.getInstance().isPortal()) {
            this.f9829t = HttpUtil.m14331j(this.f9811a, "");
        } else {
            MultiCheckNetWorkUtils.m13939a(GBApplication.instance(), new CallBack() {
                /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.C276212 */

                @Override // com.gbcom.gwifi.base.p234c.CallBack
                /* renamed from: a */
                public void mo24437a(Object obj) {
                    if (CacheAuth.getInstance().isPortal()) {
                        StatusLoginActivity.this.f9829t = HttpUtil.m14331j(StatusLoginActivity.this.f9811a, "");
                        return;
                    }
                    StatusLoginActivity.this.f9828s = HttpUtil.m14319f(StatusLoginActivity.this.f9811a, "");
                }
            });
        }
    }

    /* renamed from: c */
    private void m11386c() {
        this.f9830u = getIntent().getStringExtra(Constant.f13323i);
        String hotspotGroupName = CacheAccount.getInstance().getHotspotGroupName();
        if (!hotspotGroupName.isEmpty()) {
            this.f9798D.setText(hotspotGroupName);
            this.f9798D.setTextColor(getResources().getColor(C2425R.color.black));
        }
        String identityTypeName = CacheAccount.getInstance().getIdentityTypeName();
        if (!identityTypeName.isEmpty()) {
            this.f9818i.setText(identityTypeName);
            this.f9818i.setTextColor(getResources().getColor(C2425R.color.black));
        }
        String studentNumber = CacheAccount.getInstance().getStudentNumber();
        if (!studentNumber.isEmpty()) {
            this.f9820k.setText(studentNumber);
        }
        String identityName = CacheAccount.getInstance().getIdentityName();
        if (!identityName.isEmpty()) {
            this.f9799E.setText(identityName);
        }
        int intValue = CacheAccount.getInstance().getHotspotGroupId().intValue();
        if (intValue != 0) {
            this.f9814e = intValue;
        }
        int hotspotGroupType = CacheAccount.getInstance().getHotspotGroupType();
        if (hotspotGroupType != 0) {
            this.f9795A = hotspotGroupType;
        }
    }

    /* renamed from: d */
    private void m11393d() {
        this.f9812c = (RelativeLayout) findViewById(C2425R.C2427id.school_rl);
        this.f9813d = (TextView) findViewById(C2425R.C2427id.school_tv);
        this.f9815f = (RelativeLayout) findViewById(C2425R.C2427id.name_rl);
        this.f9816g = (TextView) findViewById(C2425R.C2427id.name_tv);
        this.f9817h = (RelativeLayout) findViewById(C2425R.C2427id.status_rl);
        this.f9818i = (TextView) findViewById(C2425R.C2427id.status_tv);
        this.f9820k = (EditText) findViewById(C2425R.C2427id.stud_no);
        this.f9821l = (EditText) findViewById(C2425R.C2427id.stud_pw);
        this.f9821l.addTextChangedListener(new TextWatcher() {
            /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.C276616 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String obj = StatusLoginActivity.this.f9821l.getText().toString();
                String c = CheckUtil.m14085c(obj);
                if (!obj.equals(c) && c.length() < obj.length()) {
                    StatusLoginActivity.this.f9821l.setText(c);
                    StatusLoginActivity.this.f9821l.setSelection(c.length());
                    C3470v.m14563a(GBApplication.instance(), "字符非法");
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.f9822m = (Button) findViewById(C2425R.C2427id.login_btn);
        this.f9823n = (TextView) findViewById(C2425R.C2427id.phone_login);
        this.f9833x = (Button) findViewById(C2425R.C2427id.forget_password_btn);
        this.f9834y = (RelativeLayout) findViewById(C2425R.C2427id.show_password);
        this.f9835z = (Button) findViewById(C2425R.C2427id.show_btn);
        this.f9797C = (RelativeLayout) findViewById(C2425R.C2427id.school_rl_login);
        this.f9798D = (TextView) findViewById(C2425R.C2427id.school_tv_login);
        this.f9799E = (EditText) findViewById(C2425R.C2427id.name_et);
        this.f9812c.setOnClickListener(this);
        this.f9815f.setOnClickListener(this);
        this.f9817h.setOnClickListener(this);
        this.f9822m.setOnClickListener(this);
        this.f9823n.setOnClickListener(this);
        this.f9833x.setOnClickListener(this);
        this.f9834y.setOnClickListener(this);
        this.f9835z.setOnClickListener(this);
        this.f9797C.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.View$OnClickListenerC276717 */

            public void onClick(View view) {
                if (StatusLoginActivity.this.findViewById(C2425R.C2427id.iv_more).getVisibility() != 0) {
                    return;
                }
                if (!CacheAuth.getInstance().isPortal() || TextUtils.isEmpty(CacheAuth.getInstance().getPortalHost())) {
                    StatusLoginActivity.this.f9805K = true;
                    if (!SystemUtil.m14536i(StatusLoginActivity.this)) {
                        StatusLoginActivity.this.m11414l();
                    } else if (ContextCompat.checkSelfPermission(StatusLoginActivity.this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                        StatusLoginActivity.this.m11416m();
                    } else if (StatusLoginActivity.this.f9806L == Double.MIN_VALUE || StatusLoginActivity.this.f9807M == Double.MIN_VALUE || StatusLoginActivity.this.f9806L == XPath.MATCH_SCORE_QNAME || StatusLoginActivity.this.f9807M == XPath.MATCH_SCORE_QNAME) {
                        StatusLoginActivity.this.f9810P.start();
                    } else {
                        StatusLoginActivity.this.m11369a((StatusLoginActivity) StatusLoginActivity.this.f9806L, StatusLoginActivity.this.f9807M);
                    }
                } else {
                    StatusLoginActivity.this.m11369a((StatusLoginActivity) XPath.MATCH_SCORE_QNAME, (double) XPath.MATCH_SCORE_QNAME);
                }
            }
        });
        this.f9832w = (TextView) findViewById(C2425R.C2427id.user_protocol);
        this.f9832w.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.View$OnClickListenerC276818 */

            public void onClick(View view) {
                String g = HttpUtil.m14323g();
                if (!C3467s.m14513e(g)) {
                    GBActivity.openBackWebView(g, "用户服务协议");
                } else {
                    GBActivity.showMessageToast("链接无效...");
                }
            }
        });
        this.f9831v = (CheckBox) findViewById(16908289);
        this.f9831v.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.C276919 */

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    StatusLoginActivity.this.f9822m.setEnabled(true);
                } else {
                    StatusLoginActivity.this.f9822m.setEnabled(false);
                }
            }
        });
        if (SystemUtil.m14533f()) {
            ((RelativeLayout) findViewById(C2425R.C2427id.rl_user_protocol)).setVisibility(8);
            this.f9795A = Integer.parseInt(getResources().getString(C2425R.C2429string.project_type));
            String string = getResources().getString(C2425R.C2429string.project_name);
            if (!TextUtils.equals(this.f9798D.getText().toString().trim(), string)) {
                this.f9818i.setText("选择用户身份");
                this.f9820k.setText("");
                this.f9799E.setText("");
                this.f9821l.setText("");
            }
            this.f9798D.setText(string);
            this.f9814e = Integer.parseInt(getResources().getString(C2425R.C2429string.project_id));
            this.f9819j = 0;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.login_btn /*{ENCODED_INT: 2131755638}*/:
                m11397e();
                m11408i();
                return;
            case C2425R.C2427id.show_password /*{ENCODED_INT: 2131755644}*/:
            case C2425R.C2427id.show_btn /*{ENCODED_INT: 2131755645}*/:
                if (!this.f9796B) {
                    this.f9821l.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    this.f9796B = true;
                    this.f9835z.setBackgroundResource(C2425R.C2426drawable.show_password);
                    Editable text = this.f9821l.getText();
                    if (text instanceof Spannable) {
                        Selection.setSelection(text, text.length());
                        return;
                    }
                    return;
                }
                this.f9821l.setTransformationMethod(PasswordTransformationMethod.getInstance());
                this.f9835z.setBackgroundResource(C2425R.C2426drawable.noshow_password);
                this.f9796B = false;
                Editable text2 = this.f9821l.getText();
                if (text2 instanceof Spannable) {
                    Selection.setSelection(text2, text2.length());
                    return;
                }
                return;
            case C2425R.C2427id.status_rl /*{ENCODED_INT: 2131755659}*/:
                m11401f();
                return;
            case C2425R.C2427id.forget_password_btn /*{ENCODED_INT: 2131755669}*/:
                Intent intent = new Intent(this, SetPasswordActivity.class);
                intent.putExtra(Constant.f13323i, this.f9830u);
                intent.putExtra("setPassword", "statusLogin");
                startActivity(intent);
                return;
            case C2425R.C2427id.phone_login /*{ENCODED_INT: 2131755670}*/:
                Intent intent2 = new Intent(this, LoginThirdActivity.class);
                intent2.setFlags(268435456);
                intent2.putExtra(Constant.f13323i, this.f9830u);
                startActivity(intent2);
                finish();
                return;
            default:
                return;
        }
    }

    /* renamed from: e */
    private void m11397e() {
        String charSequence = this.f9818i.getText().toString();
        if (charSequence.equals("学生")) {
            this.f9819j = 1;
        } else if (charSequence.equals("教师")) {
            this.f9819j = 2;
        } else if (charSequence.equals("校职工")) {
            this.f9819j = 3;
        } else if (charSequence.equals("员工")) {
            this.f9819j = 4;
        }
    }

    /* renamed from: f */
    private void m11401f() {
        if (this.f9795A != 0) {
            if (this.f9795A == 1) {
                m11405h();
            } else if (this.f9795A == 2) {
                m11404g();
            }
        }
    }

    /* renamed from: g */
    private void m11404g() {
        final Dialog dialog = new Dialog(this, C2425R.C2430style.login_dialog);
        View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.login_status_two, (ViewGroup) null);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.one)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.View$OnClickListenerC277120 */

            public void onClick(View view) {
                if (dialog != null) {
                    dialog.dismiss();
                    StatusLoginActivity.this.f9818i.setText("学生");
                    StatusLoginActivity.this.f9818i.setTextColor(StatusLoginActivity.this.getResources().getColor(C2425R.color.black));
                    StatusLoginActivity.this.f9820k.setText("");
                    StatusLoginActivity.this.f9799E.setText("");
                    StatusLoginActivity.this.f9821l.setText("");
                }
            }
        });
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.two)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.View$OnClickListenerC277221 */

            public void onClick(View view) {
                if (dialog != null) {
                    dialog.dismiss();
                    StatusLoginActivity.this.f9818i.setText("教师");
                    StatusLoginActivity.this.f9818i.setTextColor(StatusLoginActivity.this.getResources().getColor(C2425R.color.black));
                    StatusLoginActivity.this.f9820k.setText("");
                    StatusLoginActivity.this.f9799E.setText("");
                    StatusLoginActivity.this.f9821l.setText("");
                }
            }
        });
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.three)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.View$OnClickListenerC277322 */

            public void onClick(View view) {
                if (dialog != null) {
                    dialog.dismiss();
                    StatusLoginActivity.this.f9818i.setText("校职工");
                    StatusLoginActivity.this.f9818i.setTextColor(StatusLoginActivity.this.getResources().getColor(C2425R.color.black));
                    StatusLoginActivity.this.f9820k.setText("");
                    StatusLoginActivity.this.f9799E.setText("");
                    StatusLoginActivity.this.f9821l.setText("");
                }
            }
        });
        dialog.setContentView(inflate, new ViewGroup.LayoutParams((int) getResources().getDimension(C2425R.dimen.dialog_width), -2));
        dialog.show();
    }

    /* renamed from: a */
    private void m11373a(final OrgsInfo orgsInfo) {
        View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.login_status_list, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(C2425R.C2427id.f8357lv);
        listView.setAdapter((ListAdapter) new C2781a(orgsInfo.getData()));
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.C27702 */

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                OrgsInfo.DataBean dataBean = orgsInfo.getData().get(i);
                StatusLoginActivity.this.f9795A = dataBean.getOrgType();
                StatusLoginActivity.this.f9798D.setText(dataBean.getName());
                StatusLoginActivity.this.f9814e = dataBean.getId();
                StatusLoginActivity.this.f9819j = dataBean.getOrgType();
            }
        });
        popupWindow.showAtLocation(getWindow().getDecorView(), 17, 0, 0);
    }

    /* renamed from: h */
    private void m11405h() {
        final Dialog dialog = new Dialog(this, C2425R.C2430style.login_dialog);
        View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.login_status_one, (ViewGroup) null);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.one)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.View$OnClickListenerC27743 */

            public void onClick(View view) {
                if (dialog != null) {
                    dialog.dismiss();
                    StatusLoginActivity.this.f9818i.setText("员工");
                    StatusLoginActivity.this.f9818i.setTextColor(StatusLoginActivity.this.getResources().getColor(C2425R.color.black));
                    StatusLoginActivity.this.f9820k.setText("");
                    StatusLoginActivity.this.f9799E.setText("");
                    StatusLoginActivity.this.f9821l.setText("");
                }
            }
        });
        dialog.setContentView(inflate, new ViewGroup.LayoutParams((int) getResources().getDimension(C2425R.dimen.dialog_width), -2));
        dialog.show();
    }

    /* renamed from: i */
    private void m11408i() {
        if (this.f9798D.getText().toString().trim().startsWith("请连接")) {
        }
        if (this.f9798D.getText().toString().trim().startsWith("当前环境不支持")) {
        }
        if (this.f9818i.getText().toString().trim().endsWith("身份")) {
        }
        if (C3467s.m14513e(this.f9820k.getText().toString().trim())) {
            showMessageToast("请输入学工号");
        } else if (C3467s.m14513e(this.f9799E.getText().toString().trim())) {
            showMessageToast("请输入姓名");
        } else {
            Pattern.compile("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$").matcher(this.f9799E.getText().toString().trim()).matches();
            if (C3467s.m14513e(this.f9821l.getText().toString().trim())) {
                showMessageToast("请输入密码");
            } else if (this.f9821l.getText().toString().trim().length() < 6 || this.f9821l.getText().toString().trim().length() > 16) {
                GBActivity.showMessageToast("密码必须为6-16位");
            } else {
                try {
                    m11410j();
                } catch (Exception e) {
                }
            }
        }
    }

    /* renamed from: j */
    private void m11410j() {
        if (this.f9826q) {
            this.f9826q = false;
        } else if (this.f9814e != 0 && this.f9819j != 0) {
            this.f9826q = true;
            if (!CacheAuth.getInstance().isPortal()) {
                MultiCheckNetWorkUtils.m13944b(GBApplication.instance(), new CallBack() {
                    /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.C27754 */

                    @Override // com.gbcom.gwifi.base.p234c.CallBack
                    /* renamed from: a */
                    public void mo24437a(Object obj) {
                        if (!CacheAuth.getInstance().isPortal()) {
                            StatusLoginActivity.this.f9827r = HttpUtil.m14247a(StatusLoginActivity.this, StatusLoginActivity.this.f9814e, StatusLoginActivity.this.f9819j, StatusLoginActivity.this.f9820k.getText().toString(), StatusLoginActivity.this.f9799E.getText().toString(), StatusLoginActivity.this.f9821l.getText().toString(), StatusLoginActivity.this.f9811a, "");
                        } else if (!TextUtils.isEmpty(CacheAuth.getInstance().getPortalHost())) {
                            StatusLoginActivity.this.f9827r = HttpUtil.m14247a(StatusLoginActivity.this, StatusLoginActivity.this.f9814e, StatusLoginActivity.this.f9819j, StatusLoginActivity.this.f9820k.getText().toString(), StatusLoginActivity.this.f9799E.getText().toString(), StatusLoginActivity.this.f9821l.getText().toString(), StatusLoginActivity.this.f9811a, "");
                        } else {
                            Log.m14403e(StatusLoginActivity.f9794b, "invalid data: portal but no host");
                        }
                    }
                });
            } else if (!TextUtils.isEmpty(CacheAuth.getInstance().getPortalHost())) {
                this.f9827r = HttpUtil.m14247a(this, this.f9814e, this.f9819j, this.f9820k.getText().toString(), this.f9799E.getText().toString(), this.f9821l.getText().toString(), this.f9811a, "");
            } else {
                Log.m14403e(f9794b, "invalid data: portal but no host");
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11374a(String str) {
        HashMap<String, Object> d = ResourceParse.m14451d(str);
        if (d == null) {
            showMessageToast("解析失败");
            return;
        }
        int intValue = ((Integer) d.get(AbstractC5718b.JSON_ERRORCODE)).intValue();
        String str2 = (String) d.get("resultMsg");
        if (ResultCode.m14475a(Integer.valueOf(intValue))) {
            this.f9801G = (String) d.get(Constant.f13323i);
            if (this.f9801G != null && !this.f9801G.equals("")) {
                CacheAccount.getInstance().setLoginPhone(this.f9801G);
                CacheAccount.getInstance().setLoginStaticPassword(this.f9821l.getText().toString());
            }
            CacheAccount.getInstance().setLastLoginType(2);
            CacheAccount.getInstance().setLastLoginPhone(this.f9801G);
            CacheAccount.getInstance().setCacheAccountBean(this.f9795A, this.f9814e, this.f9798D.getText().toString(), this.f9799E.getText().toString(), this.f9818i.getText().toString(), this.f9820k.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constant.f13323i, this.f9801G);
            intent.putExtra("staticPassword", this.f9821l.getText().toString());
            startActivity(intent);
            finish();
        } else if (AuthResultCode.m13891h(intValue)) {
            Intent intent2 = new Intent(this, BundlePhoneActivity.class);
            intent2.putExtra("staticPassword", this.f9821l.getText().toString());
            intent2.putExtra("schoolId", this.f9814e);
            intent2.putExtra("statusId", this.f9819j);
            intent2.putExtra("schoolTv", this.f9798D.getText().toString());
            intent2.putExtra("statusTv", this.f9818i.getText().toString());
            intent2.putExtra("studNo", this.f9820k.getText().toString());
            intent2.putExtra("nameTv", this.f9799E.getText().toString());
            intent2.putExtra("orgType", this.f9795A);
            startActivity(intent2);
        } else {
            showMessageToast(str2);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11382b(String str) {
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
            intent.putExtra(Constant.f13323i, this.f9801G);
            intent.putExtra("staticPassword", this.f9821l.getText().toString());
            startActivity(intent);
            finish();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11388c(String str) {
        if (this.f9803I == null) {
            this.f9803I = GsonUtil.m14241a();
        }
        OrgsInfo orgsInfo = (OrgsInfo) this.f9803I.mo21588a(str, OrgsInfo.class);
        if (orgsInfo.getResponse() == 100 && orgsInfo.getData().size() != 0) {
            if (!this.f9805K && this.f9814e == 0) {
                OrgsInfo.DataBean dataBean = orgsInfo.getData().get(0);
                this.f9795A = dataBean.getOrgType();
                this.f9798D.setText(dataBean.getName());
                this.f9814e = dataBean.getId();
                this.f9819j = dataBean.getOrgType();
            }
            if (this.f9805K) {
                this.f9805K = false;
                Intent intent = new Intent(this, SchoolSelectActivity.class);
                intent.putExtra("orgsInfo", orgsInfo);
                List<GBActivity> activitiesList = GBApplication.instance().getActivitiesList();
                for (int i = 0; i < activitiesList.size(); i++) {
                    if (activitiesList.get(i) instanceof SchoolSelectActivity) {
                        return;
                    }
                }
                startActivityForResult(intent, 0);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m11395d(String str) {
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (deSerializeJson != null && ResultCode.m14476b(deSerializeJson)) {
            HashMap hashMap = (HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class);
            if (hashMap.get("hotspot_group_id") != null && hashMap.get("hotspot_group_type") != null) {
                int intValue = ((Integer) hashMap.get("hotspot_group_type")).intValue();
                CacheAuth.getInstance().setOrgType(intValue);
                m11370a(((Integer) hashMap.get("hotspot_group_id")).intValue(), (String) hashMap.get("hotspot_group_name"), intValue);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m11399e(String str) {
        try {
            JSONObject jSONObject = new JSONObject(EncryptUtil.decrypt(URLDecoder.decode(new JSONObject(str).getString("data"), "UTF-8")));
            if (jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                if (jSONObject2.has("hotspotGroupId") && jSONObject2.has("hotspotGroupType")) {
                    int i = jSONObject2.getInt("hotspotGroupId");
                    int i2 = jSONObject2.getInt("hotspotGroupType");
                    String str2 = "";
                    if (jSONObject2.has("hotspotGroupName")) {
                        str2 = jSONObject2.getString("hotspotGroupName");
                    }
                    if (C3467s.m14513e(str2)) {
                        if (i2 == 1) {
                            str2 = "企业";
                        } else {
                            str2 = "学校";
                        }
                    }
                    CacheAuth.getInstance().setOrgType(i2);
                    m11370a(i, str2, i2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 0 && intent != null) {
            OrgsInfo.DataBean dataBean = (OrgsInfo.DataBean) intent.getSerializableExtra("orgsInfoBean");
            this.f9795A = dataBean.getOrgType();
            String name = dataBean.getName();
            if (!TextUtils.equals(this.f9798D.getText().toString().trim(), name)) {
                this.f9818i.setText("选择用户身份");
                this.f9820k.setText("");
                this.f9799E.setText("");
                this.f9821l.setText("");
            }
            this.f9798D.setText(name);
            this.f9814e = dataBean.getId();
            this.f9819j = dataBean.getOrgType();
        }
    }

    /* renamed from: a */
    private void m11370a(int i, String str, int i2) {
        if (i2 != 2 && i2 != 1) {
            this.f9798D.setTextColor(getResources().getColor(C2425R.color.black));
            this.f9818i.setText("请选择用户身份");
            this.f9818i.setTextColor(getResources().getColor(C2425R.color.black));
            this.f9820k.setText("");
            this.f9799E.setText("");
            this.f9821l.setText("");
            this.f9814e = i;
            this.f9795A = i2;
        } else if (!this.f9798D.getText().toString().equals(str) || this.f9795A != i2 || this.f9814e != i) {
            findViewById(C2425R.C2427id.iv_more).setVisibility(4);
            this.f9814e = i;
            this.f9795A = i2;
            this.f9820k.setText("");
            this.f9799E.setText("");
            this.f9821l.setText("");
            if (i2 == 2) {
                this.f9798D.setText(str);
                this.f9818i.setText("请选择用户身份");
                this.f9798D.setTextColor(getResources().getColor(C2425R.color.black));
                this.f9818i.setTextColor(getResources().getColor(C2425R.color.black));
            } else if (i2 == 1) {
                this.f9798D.setText(str);
                this.f9818i.setText("员工");
                this.f9798D.setTextColor(getResources().getColor(C2425R.color.black));
                this.f9818i.setTextColor(getResources().getColor(C2425R.color.black));
            }
        }
    }

    /* renamed from: k */
    private void m11412k() {
        if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().getCurrentActivity().showSecondDialog("", "连接" + getResources().getString(C2425R.C2429string.wifi_name) + "网络可自动识别您的学校,或者手动选择!", "连接" + getResources().getString(C2425R.C2429string.wifi_name), new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.C27776 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    StatusLoginActivity.this.m11422p();
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.DialogInterface$OnCancelListenerC27787 */

                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: l */
    private void m11414l() {
        if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().getCurrentActivity().showSecondDialog("", "开启位置服务,获取精准定位", "开启定位", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.C27798 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    StatusLoginActivity.this.m11418n();
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.DialogInterface$OnCancelListenerC27809 */

                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: m */
    private void m11416m() {
        if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().getCurrentActivity().showSecondDialog("", "此功能需要打开定位权限!", "打开权限设置", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.C276010 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    Intent intent = new Intent();
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts(C6366a.f23181c, StatusLoginActivity.this.getPackageName(), null));
                    StatusLoginActivity.this.startActivity(intent);
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.DialogInterface$OnCancelListenerC276111 */

                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: n */
    private void m11418n() {
        startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 0);
    }

    /* renamed from: o */
    private void m11420o() {
        if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().getCurrentActivity().showSecondDialog("", "当前环境不支持学工号登陆！", "确认", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.C276313 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.DialogInterface$OnCancelListenerC276414 */

                public void onCancel(DialogInterface dialogInterface) {
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: p */
    private void m11422p() {
        startActivity(new Intent("android.settings.WIFI_SETTINGS"));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11369a(final double d, final double d2) {
        if (!CacheAuth.getInstance().isPortal()) {
            MultiCheckNetWorkUtils.m13939a(GBApplication.instance(), new CallBack() {
                /* class com.gbcom.gwifi.functions.loading.StatusLoginActivity.C276515 */

                @Override // com.gbcom.gwifi.base.p234c.CallBack
                /* renamed from: a */
                public void mo24437a(Object obj) {
                    if (CacheAuth.getInstance().isPortal()) {
                        StatusLoginActivity.this.f9802H = HttpUtil.m14242a((float) d2, (float) d, (Integer) 0, StatusLoginActivity.this.f9811a, (Object) "");
                        return;
                    }
                    StatusLoginActivity.this.f9802H = HttpUtil.m14242a((float) d2, (float) d, (Integer) 0, StatusLoginActivity.this.f9811a, (Object) "");
                }
            });
        } else if (!TextUtils.isEmpty(CacheAuth.getInstance().getPortalHost())) {
            this.f9802H = HttpUtil.m14242a((float) d2, (float) d, (Integer) 0, this.f9811a, (Object) "");
        } else {
            Log.m14403e(f9794b, "invalid data: portal but no host");
        }
        Log.m14398b(f9794b, "经纬度信息：" + d2 + "  " + d);
    }

    /* renamed from: com.gbcom.gwifi.functions.loading.StatusLoginActivity$a */
    private static class C2781a extends BaseAdapter {

        /* renamed from: a */
        private List<OrgsInfo.DataBean> f9866a;

        public C2781a(List<OrgsInfo.DataBean> list) {
            this.f9866a = list;
        }

        public int getCount() {
            if (this.f9866a == null) {
                return 0;
            }
            return this.f9866a.size();
        }

        public Object getItem(int i) {
            return this.f9866a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.login_item_status_list, viewGroup, false);
            ((TextView) inflate.findViewById(C2425R.C2427id.f8363tv)).setText(this.f9866a.get(i).getName());
            return inflate;
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        checkAgreementYes();
    }
}
