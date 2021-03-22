package com.gbcom.gwifi.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.p009v4.app.Fragment;
import android.support.p009v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TabHost;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.FormatUtil;
import com.gbcom.gwifi.util.Log;
import java.util.HashMap;

public class FragmentTabActivity extends GBActivity {

    /* renamed from: c */
    private static final String f8655c = FragmentTabActivity.class.getSimpleName();

    /* renamed from: a */
    protected TabHost f8656a;

    /* renamed from: b */
    protected C2462a f8657b;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        Log.m14398b(f8655c, "init:11 " + FormatUtil.m14213a());
        super.onCreate(bundle);
        Log.m14398b(f8655c, "init:12 " + FormatUtil.m14213a());
        mo24220a();
        Log.m14398b(f8655c, "init:13 " + FormatUtil.m14213a());
        this.f8656a = (TabHost) findViewById(16908306);
        Log.m14398b(f8655c, "init:14 " + FormatUtil.m14213a());
        Log.m14398b(f8655c, "init:15 " + FormatUtil.m14213a());
        this.f8656a.setup();
        Log.m14398b(f8655c, "init:16 " + FormatUtil.m14213a());
        this.f8657b = new C2462a(this, this.f8656a, C2425R.C2427id.realtabcontent);
        Log.m14398b(f8655c, "init:17 " + FormatUtil.m14213a());
        mo24221a(0);
        Log.m14398b(f8655c, "init:18 " + FormatUtil.m14213a());
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo24220a() {
        setContentView(C2425R.layout.fragment_tabs);
    }

    public void addTab(String str, int i, Class<?> cls, Bundle bundle) {
        if (str != null) {
            this.f8657b.mo24234a(this.f8656a.newTabSpec(str).setIndicator(new LabelIndicatorStrategy(this, str, i).mo24349a(this.f8656a)), cls, bundle);
        }
    }

    public void addTab(String str, View view, Class<?> cls, Bundle bundle) {
        if (str != null) {
            this.f8657b.mo24234a(this.f8656a.newTabSpec(str).setIndicator(view), cls, bundle);
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p009v4.app.FragmentActivity
    public void onSaveInstanceState(Bundle bundle) {
    }

    public void setCurrentTab(String str) {
        this.f8656a.setCurrentTabByTag(str);
    }

    public void setCurrentTabId(int i) {
        this.f8656a.setCurrentTab(i);
    }

    public void onTabChanged(String str) {
    }

    public TabHost getTabHost() {
        if (this.f8657b == null) {
            return null;
        }
        return this.f8657b.mo24233a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo24221a(int i) {
        if (i > 0) {
            this.f8656a.getTabWidget().setBackgroundResource(i);
        }
    }

    public Fragment getLastFragment() {
        if (this.f8657b != null) {
            return this.f8657b.mo24235b();
        }
        return null;
    }

    public String getmLastTabTag() {
        return this.f8657b.mo24236c();
    }

    public Fragment getFragment(String str) {
        return this.f8657b.mo24232a(str);
    }

    /* renamed from: com.gbcom.gwifi.base.activity.FragmentTabActivity$a */
    public static class C2462a implements TabHost.OnTabChangeListener {

        /* renamed from: a */
        C2464b f8658a;

        /* renamed from: b */
        private final FragmentTabActivity f8659b;

        /* renamed from: c */
        private final int f8660c;

        /* renamed from: d */
        private final TabHost f8661d;

        /* renamed from: e */
        private final HashMap<String, C2464b> f8662e = new HashMap<>();

        public C2462a(FragmentTabActivity fragmentTabActivity, TabHost tabHost, int i) {
            this.f8659b = fragmentTabActivity;
            this.f8661d = tabHost;
            this.f8660c = i;
            this.f8661d.setOnTabChangedListener(this);
        }

        /* renamed from: a */
        public TabHost mo24233a() {
            return this.f8661d;
        }

        /* renamed from: a */
        public Fragment mo24232a(String str) {
            C2464b bVar = this.f8662e.get(str);
            if (bVar == null) {
                return null;
            }
            return bVar.f8666c;
        }

        /* renamed from: b */
        public Fragment mo24235b() {
            C2464b bVar;
            if (this.f8662e == null || (bVar = this.f8662e.get(this.f8658a.f8667d)) == null) {
                return null;
            }
            return bVar.f8666c;
        }

        /* renamed from: a */
        public void mo24234a(TabHost.TabSpec tabSpec, Class<?> cls, Bundle bundle) {
            tabSpec.setContent(new C2463a(this.f8659b));
            String tag = tabSpec.getTag();
            C2464b bVar = new C2464b(tag, cls, bundle);
            bVar.f8666c = this.f8659b.getSupportFragmentManager().findFragmentByTag(tag);
            if (bVar.f8666c != null && !bVar.f8666c.isHidden()) {
                FragmentTransaction beginTransaction = this.f8659b.getSupportFragmentManager().beginTransaction();
                beginTransaction.hide(bVar.f8666c);
                beginTransaction.commitAllowingStateLoss();
            }
            this.f8662e.put(tag, bVar);
            this.f8661d.addTab(tabSpec);
        }

        /* renamed from: c */
        public String mo24236c() {
            return this.f8658a.f8667d;
        }

        public void onTabChanged(String str) {
            C2464b bVar = this.f8662e.get(str);
            if (this.f8658a != bVar) {
                FragmentTransaction beginTransaction = this.f8659b.getSupportFragmentManager().beginTransaction();
                if (!(this.f8658a == null || this.f8658a.f8666c == null)) {
                    beginTransaction.hide(this.f8658a.f8666c);
                }
                if (bVar == null) {
                    Log.m14400c(FragmentTabActivity.f8655c, "onTabChanged with tabId:" + str + ", newTab is null");
                } else if (bVar.f8666c != null) {
                    beginTransaction.show(bVar.f8666c);
                    Log.m14400c(FragmentTabActivity.f8655c, "onTabChanged with tabId:" + str + ", show fragment success");
                } else {
                    bVar.f8666c = Fragment.instantiate(this.f8659b, bVar.f8665b.getName(), bVar.f8664a);
                    beginTransaction.add(this.f8660c, bVar.f8666c, bVar.f8667d);
                    Log.m14400c(FragmentTabActivity.f8655c, "onTabChanged with tabId:" + str + ", newTab.fragment is null, newTab clss is " + bVar.f8665b.getName() + ", newTab.tag is " + bVar.f8667d);
                }
                this.f8658a = bVar;
                beginTransaction.commitAllowingStateLoss();
                this.f8659b.getSupportFragmentManager().executePendingTransactions();
            }
            this.f8659b.onTabChanged(str);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.gbcom.gwifi.base.activity.FragmentTabActivity$a$a */
        public static class C2463a implements TabHost.TabContentFactory {

            /* renamed from: a */
            private final Context f8663a;

            public C2463a(Context context) {
                this.f8663a = context;
            }

            public View createTabContent(String str) {
                View view = new View(this.f8663a);
                view.setMinimumWidth(0);
                view.setMinimumHeight(0);
                return view;
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.gbcom.gwifi.base.activity.FragmentTabActivity$a$b */
        public static final class C2464b {

            /* renamed from: a */
            private final Bundle f8664a;

            /* renamed from: b */
            private final Class<?> f8665b;

            /* renamed from: c */
            private Fragment f8666c;

            /* renamed from: d */
            private final String f8667d;

            C2464b(String str, Class<?> cls, Bundle bundle) {
                this.f8667d = str;
                this.f8665b = cls;
                this.f8664a = bundle;
            }
        }

        /* renamed from: d */
        public void mo24237d() {
            this.f8658a = null;
            this.f8662e.clear();
            this.f8661d.clearAllTabs();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        if (this.f8657b != null) {
            this.f8657b.mo24237d();
            this.f8657b = null;
        }
        this.f8656a.clearAllTabs();
        super.onDestroy();
    }
}
