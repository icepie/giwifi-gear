package com.gbcom.gwifi.functions.product;

import android.os.Bundle;
import android.support.p009v4.view.PagerAdapter;
import android.support.p009v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.widget.JazzyViewPager;
import com.gbcom.gwifi.widget.OutlineContainer;
import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends GBActivity {

    /* renamed from: a */
    private static final String f10457a = "ImagesActivity";

    /* renamed from: b */
    private static JazzyViewPager f10458b;

    /* renamed from: g */
    private static boolean f10459g = false;

    /* renamed from: c */
    private LinearLayout f10460c;

    /* renamed from: d */
    private List<View> f10461d;

    /* renamed from: e */
    private Animation f10462e;

    /* renamed from: f */
    private String f10463f = "";

    /* renamed from: h */
    private ViewPager.OnPageChangeListener f10464h = new ViewPager.OnPageChangeListener() {
        /* class com.gbcom.gwifi.functions.product.ImagesActivity.C28811 */

        /* renamed from: b */
        private int f10467b = 0;

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            ImagesActivity.this.f10460c.getChildAt(this.f10467b).setBackgroundResource(C2425R.C2426drawable.gi_dot_normal);
            ImagesActivity.this.f10460c.getChildAt(i).setBackgroundResource(C2425R.C2426drawable.gi_dot_focused);
            this.f10467b = i;
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }
    };

    /* renamed from: i */
    private PagerAdapter f10465i = new PagerAdapter() {
        /* class com.gbcom.gwifi.functions.product.ImagesActivity.C28822 */

        @Override // android.support.p009v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view = (View) ImagesActivity.this.f10461d.get(i);
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            if (Integer.parseInt(((ImageView) view).getTag().toString()) == 0) {
                ((ImageView) view).setScaleType(ImageView.ScaleType.CENTER);
                viewGroup.addView(view, -2, -2);
                Log.m14400c(ImagesActivity.f10457a, "pagerAdapter if >.." + i);
            } else {
                ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_CENTER);
                ((ImageView) view).setAdjustViewBounds(true);
                viewGroup.addView(view, -1, -1);
                Log.m14400c(ImagesActivity.f10457a, "pagerAdapter else >.." + i);
            }
            ImagesActivity.f10458b.mo28280a(view, i);
            return view;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(ImagesActivity.f10458b.mo28286b(i));
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public int getCount() {
            return ImagesActivity.this.f10461d.size();
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view instanceof OutlineContainer ? ((OutlineContainer) view).getChildAt(0) == obj : view == obj;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }
    };

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "详情大图界面", C2425R.layout.common_image_activity, true);
        m11912b();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity
    public int customTitleType() {
        return 2;
    }

    /* renamed from: b */
    private void m11912b() {
        this.f10460c = (LinearLayout) findViewById(C2425R.C2427id.dot_ll);
        this.f10461d = new ArrayList();
        this.f10463f = getIntent().getStringExtra("activity");
        if (this.f10463f.equals("gameActivity")) {
            this.f10461d = GameActivity.imageViews;
        } else if (this.f10463f.equals("appActivity")) {
            this.f10461d = AppActivity.imageViews;
        } else {
            this.f10461d = MakeFriendActivity.imageViews;
        }
        this.f10462e = AnimationUtils.loadAnimation(this, C2425R.anim.right_center_translate);
        m11913c();
    }

    /* renamed from: c */
    private void m11913c() {
        for (int i = 0; i < this.f10461d.size(); i++) {
            this.f10460c.addView(getLayoutInflater().inflate(C2425R.layout.common_dot, (ViewGroup) this.f10460c, false));
        }
        m11910a(JazzyViewPager.EnumC3486b.Standard);
    }

    /* renamed from: a */
    private void m11910a(JazzyViewPager.EnumC3486b bVar) {
        if (f10458b == null) {
            f10458b = (JazzyViewPager) findViewById(C2425R.C2427id.j_pager);
            f10458b.startAnimation(this.f10462e);
            f10458b.mo28279a(bVar);
            f10458b.setAdapter(this.f10465i);
            f10458b.setOnPageChangeListener(this.f10464h);
            f10458b.setPageMargin(30);
        }
    }

    public static void notifyImagesData() {
        if (f10459g) {
            Log.m14400c(f10457a, "pagerAdapter notify");
            f10458b.getAdapter().notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        f10459g = true;
        notifyImagesData();
        super.onResume();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onPause() {
        f10459g = false;
        super.onPause();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        if (f10458b != null) {
            f10458b.removeAllViews();
            f10458b = null;
        }
        super.onDestroy();
    }
}
