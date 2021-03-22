package com.gbcom.gwifi.functions.product;

import android.os.Bundle;
import android.support.p009v4.app.Fragment;
import android.support.p009v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.SystemUtil;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.p276a.p277a.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.p276a.p277a.FragmentPagerItems;
import java.util.ArrayList;
import java.util.List;

public class AppDownloadActivity extends GBActivity {

    /* renamed from: h */
    private static DownloadingListFragment f10048h;

    /* renamed from: i */
    private static TaskListFragment f10049i;

    /* renamed from: j */
    private static DownloadedListFragment f10050j;

    /* renamed from: a */
    List<Fragment> f10051a = new ArrayList();

    /* renamed from: b */
    private RelativeLayout f10052b;

    /* renamed from: c */
    private TextView f10053c;

    /* renamed from: d */
    private TextView f10054d;

    /* renamed from: e */
    private SmartTabLayout f10055e;

    /* renamed from: f */
    private ViewPager f10056f;

    /* renamed from: g */
    private FragmentPagerItemAdapter f10057g;

    /* renamed from: k */
    private List<String> f10058k;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "游戏列表", C2425R.layout.app_activity_download, true);
        m11597b();
        m11596a();
    }

    /* renamed from: a */
    private void m11596a() {
        this.f10057g = new FragmentPagerItemAdapter(getSupportFragmentManager(), FragmentPagerItems.m17161a(this).mo34740a("下载列表", TaskListFragment.class).mo34740a("下载中", DownloadingListFragment.class).mo34740a("已完成", DownloadedListFragment.class).mo34742a());
        this.f10056f = (ViewPager) findViewById(C2425R.C2427id.viewpager);
        this.f10056f.setOffscreenPageLimit(2);
        this.f10056f.setAdapter(this.f10057g);
        this.f10055e = (SmartTabLayout) findViewById(C2425R.C2427id.viewpagertab);
        this.f10055e.mo34687b(0);
        this.f10055e.mo34678a(this.f10056f);
        ((TextView) this.f10055e.mo34686b(0)).setTextColor(getResources().getColor(C2425R.color.applist_select));
        this.f10055e.mo34677a(new ViewPager.OnPageChangeListener() {
            /* class com.gbcom.gwifi.functions.product.AppDownloadActivity.C28051 */

            @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                for (int i2 = 0; i2 < 3; i2++) {
                    if (i2 == i) {
                        ((TextView) AppDownloadActivity.this.f10055e.mo34686b(i2)).setTextColor(AppDownloadActivity.this.getResources().getColor(C2425R.color.applist_select));
                    } else {
                        ((TextView) AppDownloadActivity.this.f10055e.mo34686b(i2)).setTextColor(AppDownloadActivity.this.getResources().getColor(C2425R.color.applist_normal));
                    }
                }
            }

            @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        this.f10058k = SystemUtil.m14538k(this);
    }

    /* renamed from: b */
    private void m11597b() {
        this.f10052b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10052b.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.product.AppDownloadActivity.View$OnClickListenerC28062 */

            public void onClick(View view) {
                Constant.f13285cG = true;
                AppDownloadActivity.this.moveTaskToBack(true);
            }
        });
        this.f10053c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10054d = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10053c.setText("游戏列表");
        this.f10054d.setVisibility(8);
    }

    public void setTabText(String str, int i) {
        ((TextView) this.f10055e.mo34686b(i)).setText(str);
    }

    public SmartTabLayout getViewPagerTab() {
        if (this.f10055e != null) {
            return this.f10055e;
        }
        return null;
    }

    public ViewPager getViewPager() {
        if (this.f10056f != null) {
            return this.f10056f;
        }
        return null;
    }

    public DownloadingListFragment getSecondFragment() {
        f10048h = (DownloadingListFragment) this.f10057g.getItem(1);
        return f10048h;
    }

    public TaskListFragment getFirstFragment() {
        f10049i = (TaskListFragment) this.f10057g.getItem(0);
        return f10049i;
    }

    public DownloadedListFragment getThirdFragment() {
        f10050j = (DownloadedListFragment) this.f10057g.getItem(2);
        return f10050j;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        Constant.f13285cG = false;
        super.onResume();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.support.p009v4.app.FragmentActivity
    public void onBackPressed() {
        Constant.f13285cG = true;
        moveTaskToBack(true);
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity
    public void onRestart() {
        getThirdFragment().mo25510e();
        getThirdFragment().mo25508c();
        super.onRestart();
    }

    public List<String> getAppPkgName() {
        return this.f10058k;
    }
}
