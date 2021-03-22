package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.p009v4.app.FragmentManager;
import android.support.p009v4.view.PagerAdapter;
import android.support.p009v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.edu.domain.SchoolNotifyBanner;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.JazzyViewPager;
import com.gbcom.gwifi.widget.OutlineContainer;
import com.gbcom.gwifi.widget.RoundViewOutlineProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.l */
public class ImageRotationNoTitleBuilder implements ViewBuilder {

    /* renamed from: a */
    private Context f11809a;

    /* renamed from: b */
    private int f11810b;

    /* renamed from: c */
    private int f11811c;

    /* renamed from: d */
    private JSONArray f11812d;

    /* renamed from: e */
    private List<SchoolNotifyBanner> f11813e = new ArrayList();

    /* renamed from: f */
    private Handler f11814f = new Handler() {
        /* class com.gbcom.gwifi.functions.template.builder.ImageRotationNoTitleBuilder.HandlerC31491 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    ImageRotationNoTitleBuilder.this.f11815g.setCurrentItem(ImageRotationNoTitleBuilder.this.f11815g.getCurrentItem() + 1);
                    ImageRotationNoTitleBuilder.this.f11814f.sendEmptyMessageDelayed(0, 3000);
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: g */
    private JazzyViewPager f11815g;

    /* renamed from: h */
    private LinearLayout f11816h;

    /* renamed from: i */
    private ArrayList<String> f11817i;

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        View view = null;
        this.f11809a = context;
        this.f11813e.clear();
        this.f11810b = context.getResources().getDisplayMetrics().heightPixels;
        this.f11811c = context.getResources().getDisplayMetrics().widthPixels;
        if (jSONObject != null) {
            this.f11812d = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS);
            if (!(this.f11812d == null || this.f11812d.length() == 0)) {
                for (int i = 0; i < this.f11812d.length(); i++) {
                    JSONObject jSONObject2 = this.f11812d.getJSONObject(i);
                    int i2 = jSONObject2.getInt("source_type");
                    SchoolNotifyBanner schoolNotifyBanner = new SchoolNotifyBanner();
                    String string = jSONObject2.getString("icon_url");
                    String string2 = jSONObject2.getString("wap_url");
                    schoolNotifyBanner.setImgUrl(string);
                    schoolNotifyBanner.setSource_type(i2);
                    schoolNotifyBanner.setWapUrl(string2);
                    this.f11813e.add(schoolNotifyBanner);
                }
                if (SystemUtil.m14531e()) {
                    view = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_image_rotation_no_title_margin, (ViewGroup) null);
                } else {
                    view = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_image_rotation_no_title, (ViewGroup) null);
                }
                this.f11816h = (LinearLayout) view.findViewById(C2425R.C2427id.dot_ll);
                this.f11817i = new ArrayList<>(this.f11812d.length());
                new ArrayList(this.f11812d.length());
                for (int i3 = 0; i3 < this.f11812d.length(); i3++) {
                    this.f11817i.add(this.f11812d.getJSONObject(i3).getString("icon_url"));
                    GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.common_dot, (ViewGroup) this.f11816h, true);
                }
                this.f11815g = (JazzyViewPager) view.findViewById(C2425R.C2427id.jazzy_pager);
                if (SystemUtil.m14531e() && Build.VERSION.SDK_INT >= 21) {
                    this.f11815g.setClipToOutline(true);
                    this.f11815g.setOutlineProvider(new RoundViewOutlineProvider(20.0f));
                }
                mo27027a();
            }
        }
        return view;
    }

    /* renamed from: a */
    public void mo27027a() {
        this.f11815g.mo28279a(JazzyViewPager.EnumC3486b.Accordion);
        C3150a aVar = new C3150a();
        aVar.mo27030a(this.f11815g);
        aVar.mo27029a(this.f11809a);
        aVar.mo27032a(this.f11812d);
        this.f11815g.setAdapter(aVar);
        C3152b bVar = new C3152b();
        bVar.mo27034a(this.f11816h);
        bVar.mo27035a(this.f11817i);
        this.f11815g.setOnPageChangeListener(bVar);
        this.f11815g.setCurrentItem(1000);
        this.f11815g.setPageMargin(30);
        this.f11815g.getLayoutParams().height = (DensityUtil.m14127a(GBApplication.instance()) * 43) / 100;
        if (this.f11814f != null) {
            this.f11814f.removeCallbacksAndMessages(null);
        }
        if (this.f11813e.size() > 1) {
            this.f11814f.sendEmptyMessageDelayed(0, 3000);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.template.builder.l$a */
    /* compiled from: ImageRotationNoTitleBuilder */
    public class C3150a extends PagerAdapter {

        /* renamed from: b */
        private List<View> f11820b;

        /* renamed from: c */
        private JazzyViewPager f11821c;

        /* renamed from: d */
        private Context f11822d;

        /* renamed from: e */
        private JSONArray f11823e;

        /* renamed from: f */
        private View.OnClickListener f11824f = new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.builder.ImageRotationNoTitleBuilder.C3150a.View$OnClickListenerC31511 */

            public void onClick(View view) {
                try {
                    new CommonClickListener(C3150a.this.f11822d, C3150a.this.f11823e.getJSONObject(((Integer) view.getTag()).intValue())).onClick(view);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        C3150a() {
        }

        /* renamed from: a */
        public void mo27029a(Context context) {
            this.f11822d = context;
        }

        /* renamed from: a */
        public void mo27032a(JSONArray jSONArray) {
            this.f11823e = jSONArray;
        }

        /* renamed from: a */
        public void mo27031a(List<View> list) {
            this.f11820b = list;
        }

        /* renamed from: a */
        public void mo27030a(JazzyViewPager jazzyViewPager) {
            this.f11821c = jazzyViewPager;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            List list = ImageRotationNoTitleBuilder.this.f11813e;
            ImageView imageView = new ImageView(GBApplication.instance());
            int a = DensityUtil.m14127a(GBApplication.instance());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(Integer.valueOf(i % ImageRotationNoTitleBuilder.this.f11812d.length()));
            imageView.setOnClickListener(this.f11824f);
            ImageTools.m14149a(((SchoolNotifyBanner) list.get(i % ImageRotationNoTitleBuilder.this.f11813e.size())).getImgUrl(), imageView, GBApplication.instance().options);
            viewGroup.addView(imageView, -1, a);
            this.f11821c.mo28280a(imageView, i);
            return imageView;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            View view = (View) obj;
            viewGroup.removeView((View) obj);
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public int getCount() {
            if (ImageRotationNoTitleBuilder.this.f11813e.size() == 1) {
                return 1;
            }
            if (ImageRotationNoTitleBuilder.this.f11813e.size() == 0) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view instanceof OutlineContainer ? ((OutlineContainer) view).getChildAt(0) == obj : view == obj;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.template.builder.l$b */
    /* compiled from: ImageRotationNoTitleBuilder */
    public class C3152b implements ViewPager.OnPageChangeListener {

        /* renamed from: b */
        private int f11827b = 0;

        /* renamed from: c */
        private LinearLayout f11828c;

        /* renamed from: d */
        private List<String> f11829d;

        C3152b() {
        }

        /* renamed from: a */
        public void mo27034a(LinearLayout linearLayout) {
            this.f11828c = linearLayout;
        }

        /* renamed from: a */
        public void mo27035a(List<String> list) {
            this.f11829d = list;
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            if (this.f11829d.size() != 1) {
                int size = i % this.f11829d.size();
                if (this.f11828c.getChildAt(this.f11827b) != null && this.f11828c.getChildAt(size) != null) {
                    this.f11828c.getChildAt(this.f11827b).setBackgroundResource(C2425R.C2426drawable.gi_dot_normal);
                    this.f11828c.getChildAt(size).setBackgroundResource(C2425R.C2426drawable.gi_dot_focused);
                    this.f11827b = size;
                }
            }
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }
    }
}
