package com.gbcom.gwifi.functions.loading;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.functions.loading.domain.University;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.ResourceParse;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.widget.LoadingView;
import com.gbcom.gwifi.widget.Sidebar;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import p041c.Request;

public class ChoiceUniversityActivity extends GBActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    /* renamed from: a */
    protected InputMethodManager f9447a;

    /* renamed from: b */
    protected ImageButton f9448b;

    /* renamed from: c */
    private RelativeLayout f9449c;

    /* renamed from: d */
    private TextView f9450d;

    /* renamed from: e */
    private TextView f9451e;

    /* renamed from: f */
    private EditText f9452f;

    /* renamed from: g */
    private ListView f9453g;

    /* renamed from: h */
    private Sidebar f9454h;

    /* renamed from: i */
    private ContactAdapter f9455i;

    /* renamed from: j */
    private List<University> f9456j;

    /* renamed from: k */
    private boolean f9457k = false;

    /* renamed from: l */
    private Request f9458l;

    /* renamed from: m */
    private boolean f9459m = false;

    /* renamed from: n */
    private int f9460n = 0;

    /* renamed from: o */
    private String f9461o = "选择学校";

    /* renamed from: p */
    private LoadingView f9462p;

    /* renamed from: q */
    private OkRequestHandler<String> f9463q = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.loading.ChoiceUniversityActivity.C26585 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            if (ChoiceUniversityActivity.this.f9458l == abVar) {
                ChoiceUniversityActivity.this.startAppDrawable();
                if (ChoiceUniversityActivity.this.f9462p.getVisibility() == 8) {
                    ChoiceUniversityActivity.this.showProgressDialog("正在加载...");
                    return;
                }
                return;
            }
            ChoiceUniversityActivity.this.showProgressDialog("正在加载...");
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (!ChoiceUniversityActivity.this.isFinishing()) {
                ChoiceUniversityActivity.this.f9459m = false;
                ChoiceUniversityActivity.this.dismissProgressDialog();
                ChoiceUniversityActivity.this.errAppDrawable();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (!ChoiceUniversityActivity.this.isFinishing()) {
                ChoiceUniversityActivity.this.f9459m = false;
                ChoiceUniversityActivity.this.dismissProgressDialog();
                HashMap<String, Object> c = ResourceParse.m14449c(str);
                if (c == null) {
                    ChoiceUniversityActivity.this.errAppDrawable();
                } else if (ResultCode.m14475a((Integer) c.get(AbstractC5718b.JSON_ERRORCODE))) {
                    ChoiceUniversityActivity.this.m11119a((ChoiceUniversityActivity) ((ArrayList) c.get("univerLists")));
                    ChoiceUniversityActivity.this.stopAppDrawable();
                } else {
                    ChoiceUniversityActivity.this.errAppDrawable();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: r */
    private LoadingView.AbstractC3488a f9464r = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.loading.ChoiceUniversityActivity.C26596 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            ChoiceUniversityActivity.this.m11125c();
        }
    };

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "选择学校", C2425R.layout.login_activity_choice_university, true);
        this.f9447a = (InputMethodManager) getSystemService("input_method");
        m11126d();
        m11122b();
        m11125c();
    }

    /* renamed from: b */
    private void m11122b() {
        this.f9460n = getIntent().getIntExtra("orgType", 2);
        if (this.f9460n == 1) {
            this.f9461o = "选择工厂";
            this.f9450d.setText(this.f9461o);
            this.f9452f.setHint("请输入工厂名称或首字母查询");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11125c() {
        if (!this.f9459m) {
            this.f9459m = true;
            this.f9458l = HttpUtil.m14249a(this, this.f9460n, this.f9463q, "");
        }
    }

    /* renamed from: d */
    private void m11126d() {
        this.f9449c = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f9449c.setOnClickListener(this);
        this.f9450d = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f9450d.setText(this.f9461o);
        this.f9451e = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f9451e.setText("");
        this.f9452f = (EditText) findViewById(C2425R.C2427id.query);
        this.f9448b = (ImageButton) findViewById(C2425R.C2427id.clear_ib);
        this.f9453g = (ListView) findViewById(2131755541);
        this.f9454h = (Sidebar) findViewById(C2425R.C2427id.sidebar);
        this.f9456j = new ArrayList();
        this.f9455i = new ContactAdapter(this, C2425R.layout.login_university_item, this.f9456j);
        this.f9453g.setAdapter((ListAdapter) this.f9455i);
        this.f9453g.setOnItemClickListener(this);
        this.f9454h.mo28421a(this.f9453g);
        this.f9448b.setOnClickListener(this);
        this.f9462p = (LoadingView) findViewById(C2425R.C2427id.loading_view);
        this.f9462p.mo28298a(this.f9464r);
        this.f9452f.addTextChangedListener(new TextWatcher() {
            /* class com.gbcom.gwifi.functions.loading.ChoiceUniversityActivity.C26541 */

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                ChoiceUniversityActivity.this.f9455i.getFilter().filter(charSequence);
                if (charSequence.length() > 0) {
                    ChoiceUniversityActivity.this.f9457k = true;
                    ChoiceUniversityActivity.this.f9448b.setVisibility(0);
                    return;
                }
                ChoiceUniversityActivity.this.f9457k = false;
                ChoiceUniversityActivity.this.f9448b.setVisibility(4);
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.f9453g.setOnTouchListener(new View.OnTouchListener() {
            /* class com.gbcom.gwifi.functions.loading.ChoiceUniversityActivity.View$OnTouchListenerC26552 */

            public boolean onTouch(View view, MotionEvent motionEvent) {
                ChoiceUniversityActivity.this.mo24895a();
                return false;
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                m11130g();
                return;
            case C2425R.C2427id.clear_ib /*{ENCODED_INT: 2131755953}*/:
                m11128e();
                return;
            default:
                return;
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Intent intent = new Intent(this, StatusLoginActivity.class);
        intent.putExtra("universityName", this.f9456j.get(i).getUniverName());
        intent.putExtra("schoolId", this.f9456j.get(i).getId());
        setResult(1, intent);
        finish();
    }

    /* renamed from: e */
    private void m11128e() {
        this.f9457k = false;
        this.f9452f.getText().clear();
        this.f9448b.setVisibility(4);
        mo24895a();
        this.f9451e.setEnabled(false);
        this.f9451e.setTextColor(getResources().getColor(C2425R.color.grey_9));
    }

    /* renamed from: f */
    private void m11129f() {
        if (this.f9452f.getText().toString().trim().endsWith("大学") || this.f9452f.getText().toString().trim().endsWith("学院")) {
            Intent intent = new Intent(this, StatusLoginActivity.class);
            intent.putExtra("universityName", this.f9452f.getText().toString().trim());
            setResult(1, intent);
            finish();
        }
    }

    /* renamed from: g */
    private void m11130g() {
        if (!this.f9457k) {
            showNormalDialog(this.f9461o, "是否放弃" + this.f9461o + "？", "放弃", "继续编辑", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.loading.ChoiceUniversityActivity.C26563 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    ChoiceUniversityActivity.this.finish();
                }
            }, new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.loading.ChoiceUniversityActivity.C26574 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                }
            });
        } else {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo24895a() {
        if (getWindow().getAttributes().softInputMode != 2 && getCurrentFocus() != null) {
            this.f9447a.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.loading.ChoiceUniversityActivity$a */
    public class C2660a implements Comparator<University> {
        C2660a() {
        }

        /* renamed from: a */
        public int compare(University university, University university2) {
            int i = university.getHeader().toCharArray()[0] - university2.getHeader().toCharArray()[0];
            if (i > 0) {
                return 1;
            }
            if (i != 0) {
                return -1;
            }
            return 0;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11119a(ArrayList<HashMap> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            this.f9456j.clear();
            Iterator<HashMap> it = arrayList.iterator();
            while (it.hasNext()) {
                HashMap next = it.next();
                University university = new University();
                university.setId(((Integer) next.get("id")).intValue());
                university.setUniverName((String) next.get("name"));
                university.setHeader((String) next.get("firstLetter"));
                this.f9456j.add(university);
            }
            Collections.sort(this.f9456j, new C2660a());
            this.f9455i.notifyDataSetChanged();
        }
    }

    public void startAppDrawable() {
        this.f9462p.mo28307i();
        this.f9462p.mo28313o();
        this.f9462p.mo28315q();
        if (this.f9462p.mo28310l()) {
            this.f9462p.mo28309k();
        }
        this.f9462p.mo28297a();
        this.f9462p.mo28302d();
        this.f9462p.mo28304f();
        this.f9462p.mo28300b();
    }

    public void errAppDrawable() {
        this.f9453g.setVisibility(8);
        this.f9462p.setVisibility(0);
        this.f9462p.mo28303e();
        this.f9462p.mo28301c();
        this.f9462p.mo28305g();
        this.f9462p.mo28306h();
        this.f9462p.mo28308j();
        this.f9462p.mo28312n();
        this.f9462p.mo28314p();
    }

    public void stopAppDrawable() {
        this.f9453g.setVisibility(0);
        this.f9462p.setVisibility(8);
        this.f9462p.mo28301c();
    }
}
