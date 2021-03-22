package com.gbcom.gwifi.functions.profile;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.CheckUtil;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.cache.CacheAccount;
import java.p456io.UnsupportedEncodingException;
import p041c.Request;

public class ModifyPasswordActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private static final String f10819a = "ModifyPasswordActivity";

    /* renamed from: b */
    private Request f10820b;

    /* renamed from: c */
    private Button f10821c;

    /* renamed from: d */
    private String f10822d;

    /* renamed from: e */
    private String f10823e;

    /* renamed from: f */
    private EditText f10824f;

    /* renamed from: g */
    private EditText f10825g;

    /* renamed from: h */
    private EditText f10826h;

    /* renamed from: i */
    private RelativeLayout f10827i;

    /* renamed from: j */
    private TextView f10828j;

    /* renamed from: k */
    private OkRequestHandler<String> f10829k = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.profile.ModifyPasswordActivity.C29493 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            ModifyPasswordActivity.this.f10821c.setText("正在修改...");
            ModifyPasswordActivity.this.f10821c.setEnabled(false);
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (!ModifyPasswordActivity.this.isFinishing() && abVar == ModifyPasswordActivity.this.f10820b) {
                GBActivity.showMessageToast("修改密码失败，请检查网络");
                ModifyPasswordActivity.this.f10821c.setEnabled(true);
                ModifyPasswordActivity.this.f10821c.setText("确 定");
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (!ModifyPasswordActivity.this.isFinishing() && abVar == ModifyPasswordActivity.this.f10820b) {
                CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
                if (deSerializeJson == null) {
                    GBActivity.showMessageToast("数据解析失败!");
                    try {
                        Log.m14403e(ModifyPasswordActivity.f10819a, "ModifyPasswordActivity bad json:" + new String(str.getBytes(), "utf-8").trim());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    ModifyPasswordActivity.this.finish();
                } else if (!GBActivity.dealException(deSerializeJson)) {
                    GBActivity.showMessageToast("修改成功");
                    CacheAccount.getInstance().setLoginStaticPassword(ModifyPasswordActivity.this.f10823e);
                    ModifyPasswordActivity.this.finish();
                } else {
                    ModifyPasswordActivity.this.f10821c.setEnabled(true);
                    ModifyPasswordActivity.this.f10821c.setText("确 定");
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "修改密码界面", C2425R.layout.sys_activity_modify_password, true);
        mo25931a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo25931a() {
        this.f10827i = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10827i.setOnClickListener(this);
        findViewById(C2425R.C2427id.title_edit_tv).setVisibility(4);
        this.f10828j = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10828j.setText("修改密码");
        this.f10824f = (EditText) findViewById(C2425R.C2427id.static_password_et);
        this.f10825g = (EditText) findViewById(C2425R.C2427id.new_static_password_et);
        this.f10826h = (EditText) findViewById(C2425R.C2427id.renew_static_password_et);
        this.f10826h.addTextChangedListener(new TextWatcher() {
            /* class com.gbcom.gwifi.functions.profile.ModifyPasswordActivity.C29471 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String obj = ModifyPasswordActivity.this.f10826h.getText().toString();
                String c = CheckUtil.m14085c(obj);
                if (!obj.equals(c) && c.length() < obj.length()) {
                    ModifyPasswordActivity.this.f10826h.setText(c);
                    ModifyPasswordActivity.this.f10826h.setSelection(c.length());
                    C3470v.m14563a(GBApplication.instance(), "字符非法");
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.f10821c = (Button) findViewById(C2425R.C2427id.modify_btn);
        this.f10821c.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.profile.ModifyPasswordActivity.View$OnClickListenerC29482 */

            public void onClick(View view) {
                ModifyPasswordActivity.this.f10822d = ModifyPasswordActivity.this.f10824f.getText().toString();
                if (TextUtils.isEmpty(ModifyPasswordActivity.this.f10822d)) {
                    GBActivity.showMessageToast("密码不能为空");
                    return;
                }
                ModifyPasswordActivity.this.f10823e = ModifyPasswordActivity.this.f10825g.getText().toString();
                if (TextUtils.isEmpty(ModifyPasswordActivity.this.f10823e)) {
                    GBActivity.showMessageToast("新密码不能为空");
                } else if (ModifyPasswordActivity.this.f10823e.length() < 6 || ModifyPasswordActivity.this.f10823e.length() > 16) {
                    GBActivity.showMessageToast("新密码必须为6-16位");
                } else {
                    String obj = ModifyPasswordActivity.this.f10826h.getText().toString();
                    if (TextUtils.isEmpty(obj) || !obj.equals(ModifyPasswordActivity.this.f10823e)) {
                        GBActivity.showMessageToast("两次密码输入不一致");
                        return;
                    }
                    String loginPhone = CacheAccount.getInstance().getLoginPhone();
                    ModifyPasswordActivity.this.f10820b = HttpUtil.m14295b(ModifyPasswordActivity.this, loginPhone, ModifyPasswordActivity.this.f10822d, ModifyPasswordActivity.this.f10823e, ModifyPasswordActivity.this.f10829k, "");
                }
            }
        });
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
}
