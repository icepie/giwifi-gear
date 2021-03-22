package com.gbcom.gwifi.functions.profile.p250a;

import android.content.Context;
import android.os.Bundle;
import android.support.p009v4.app.Fragment;
import android.support.p009v4.app.FragmentActivity;
import android.support.p009v4.app.FragmentPagerAdapter;
import android.support.p009v4.view.ViewPager;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.gbcom.gwifi.functions.profile.a.a */
public class TabsAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    /* renamed from: a */
    C2984c f11012a;

    /* renamed from: b */
    private final Context f11013b;

    /* renamed from: c */
    private final TabHost f11014c;

    /* renamed from: d */
    private final ViewPager f11015d;

    /* renamed from: e */
    private final ArrayList<C2984c> f11016e = new ArrayList<>();

    /* renamed from: f */
    private Map<String, Fragment> f11017f = new HashMap();

    /* renamed from: g */
    private AbstractC2983b f11018g;

    /* renamed from: com.gbcom.gwifi.functions.profile.a.a$b */
    /* compiled from: TabsAdapter */
    public interface AbstractC2983b {
        /* renamed from: a */
        void mo25939a(String str);
    }

    /* renamed from: com.gbcom.gwifi.functions.profile.a.a$c */
    /* compiled from: TabsAdapter */
    static final class C2984c {

        /* renamed from: a */
        private final String f11020a;

        /* renamed from: b */
        private final Fragment f11021b;

        /* renamed from: c */
        private final Bundle f11022c;

        C2984c(String str, Fragment fragment, Bundle bundle) {
            this.f11020a = str;
            this.f11021b = fragment;
            this.f11022c = bundle;
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.profile.a.a$a */
    /* compiled from: TabsAdapter */
    static class C2982a implements TabHost.TabContentFactory {

        /* renamed from: a */
        private final Context f11019a;

        public C2982a(Context context) {
            this.f11019a = context;
        }

        public View createTabContent(String str) {
            View view = new View(this.f11019a);
            view.setMinimumWidth(0);
            view.setMinimumHeight(0);
            return view;
        }
    }

    public TabsAdapter(FragmentActivity fragmentActivity, TabHost tabHost, ViewPager viewPager) {
        super(fragmentActivity.getSupportFragmentManager());
        this.f11013b = fragmentActivity;
        this.f11014c = tabHost;
        this.f11015d = viewPager;
        this.f11014c.setOnTabChangedListener(this);
        this.f11015d.setAdapter(this);
        this.f11015d.setOnPageChangeListener(this);
    }

    /* renamed from: a */
    public void mo25989a(TabHost.TabSpec tabSpec, Fragment fragment, Bundle bundle) {
        tabSpec.setContent(new C2982a(this.f11013b));
        String tag = tabSpec.getTag();
        this.f11016e.add(new C2984c(tag, fragment, bundle));
        this.f11017f.put(tag, fragment);
        this.f11014c.addTab(tabSpec);
        notifyDataSetChanged();
    }

    @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        TabWidget tabWidget = this.f11014c.getTabWidget();
        int descendantFocusability = tabWidget.getDescendantFocusability();
        tabWidget.setDescendantFocusability(393216);
        this.f11014c.setCurrentTab(i);
        tabWidget.setDescendantFocusability(descendantFocusability);
        this.f11012a = this.f11016e.get(i);
        if (this.f11018g != null) {
            this.f11018g.mo25939a(this.f11014c.getCurrentTabTag());
        }
    }

    /* renamed from: a */
    public AbstractC2983b mo25988a() {
        return this.f11018g;
    }

    /* renamed from: a */
    public void mo25990a(AbstractC2983b bVar) {
        this.f11018g = bVar;
    }

    public void onTabChanged(String str) {
        int currentTab = this.f11014c.getCurrentTab();
        this.f11012a = this.f11016e.get(currentTab);
        this.f11015d.setCurrentItem(currentTab);
    }

    @Override // android.support.p009v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.f11016e.get(i).f11021b;
    }

    @Override // android.support.p009v4.view.PagerAdapter
    public int getCount() {
        return this.f11016e.size();
    }

    /* renamed from: b */
    public Fragment mo25991b() {
        return this.f11012a.f11021b;
    }

    /* renamed from: c */
    public String mo25992c() {
        return this.f11012a.f11020a;
    }

    /* renamed from: a */
    public Fragment mo25987a(String str) {
        return this.f11017f.get(str);
    }
}
