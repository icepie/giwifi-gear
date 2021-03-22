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
import android.widget.TextView;
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

/* renamed from: com.gbcom.gwifi.functions.template.builder.m */
public class ImageRotationWithTitleBuilder implements ViewBuilder {

    /* renamed from: a */
    private Context f11830a;

    /* renamed from: b */
    private int f11831b;

    /* renamed from: c */
    private int f11832c;

    /* renamed from: d */
    private JSONArray f11833d;

    /* renamed from: e */
    private List<SchoolNotifyBanner> f11834e = new ArrayList();

    /* renamed from: f */
    private Handler f11835f = new Handler() {
        /* class com.gbcom.gwifi.functions.template.builder.ImageRotationWithTitleBuilder.HandlerC31531 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    ImageRotationWithTitleBuilder.this.f11836g.setCurrentItem(ImageRotationWithTitleBuilder.this.f11836g.getCurrentItem() + 1);
                    ImageRotationWithTitleBuilder.this.f11835f.sendEmptyMessageDelayed(0, 3000);
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: g */
    private JazzyViewPager f11836g;

    /* renamed from: h */
    private LinearLayout f11837h;

    /* renamed from: i */
    private ArrayList<String> f11838i;

    /* renamed from: j */
    private TextView f11839j;

    /* renamed from: k */
    private ArrayList<String> f11840k;

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        View view = null;
        this.f11830a = context;
        this.f11834e.clear();
        this.f11831b = context.getResources().getDisplayMetrics().heightPixels;
        this.f11832c = context.getResources().getDisplayMetrics().widthPixels;
        if (jSONObject != null) {
            this.f11833d = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS);
            if (!(this.f11833d == null || this.f11833d.length() == 0)) {
                for (int i = 0; i < this.f11833d.length(); i++) {
                    JSONObject jSONObject2 = this.f11833d.getJSONObject(i);
                    int i2 = jSONObject2.getInt("source_type");
                    SchoolNotifyBanner schoolNotifyBanner = new SchoolNotifyBanner();
                    String string = jSONObject2.getString("icon_url");
                    String string2 = jSONObject2.getString("wap_url");
                    schoolNotifyBanner.setImgUrl(string);
                    schoolNotifyBanner.setSource_type(i2);
                    schoolNotifyBanner.setWapUrl(string2);
                    this.f11834e.add(schoolNotifyBanner);
                }
                if (SystemUtil.m14531e()) {
                    view = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_image_rotation_with_title_margin, (ViewGroup) null);
                } else {
                    view = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_image_rotation_with_title, (ViewGroup) null);
                }
                this.f11837h = (LinearLayout) view.findViewById(C2425R.C2427id.dot_ll);
                this.f11838i = new ArrayList<>(this.f11833d.length());
                this.f11839j = (TextView) view.findViewById(2131755156);
                this.f11840k = new ArrayList<>(this.f11833d.length());
                new ArrayList(this.f11833d.length());
                for (int i3 = 0; i3 < this.f11833d.length(); i3++) {
                    this.f11838i.add(this.f11833d.getJSONObject(i3).getString("icon_url"));
                    this.f11840k.add(this.f11833d.getJSONObject(i3).getString("title"));
                    GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.common_dot, (ViewGroup) this.f11837h, true);
                }
                if (this.f11840k.size() > 0) {
                    this.f11839j.setText(this.f11840k.get(0));
                }
                this.f11836g = (JazzyViewPager) view.findViewById(C2425R.C2427id.jazzy_pager);
                if (SystemUtil.m14531e() && Build.VERSION.SDK_INT >= 21) {
                    this.f11836g.setClipToOutline(true);
                    this.f11836g.setOutlineProvider(new RoundViewOutlineProvider(20.0f));
                }
                mo27036a();
            }
        }
        return view;
    }

    /* renamed from: a */
    public void mo27036a() {
        this.f11836g.mo28279a(JazzyViewPager.EnumC3486b.Accordion);
        C3154a aVar = new C3154a();
        aVar.mo27039a(this.f11836g);
        aVar.mo27038a(this.f11830a);
        aVar.mo27040a(this.f11833d);
        this.f11836g.setAdapter(aVar);
        this.f11836g.setCurrentItem(1000);
        C3156b bVar = new C3156b();
        bVar.mo27042a(this.f11837h);
        bVar.mo27044a(this.f11840k);
        bVar.mo27043a(this.f11839j);
        this.f11836g.setOnPageChangeListener(bVar);
        this.f11836g.setPageMargin(30);
        this.f11836g.getLayoutParams().height = (DensityUtil.m14127a(GBApplication.instance()) * 43) / 100;
        if (this.f11835f != null) {
            this.f11835f.removeCallbacksAndMessages(null);
        }
        if (this.f11834e.size() > 1) {
            this.f11835f.sendEmptyMessageDelayed(0, 3000);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.template.builder.m$a */
    /* compiled from: ImageRotationWithTitleBuilder */
    public class C3154a extends PagerAdapter {

        /* renamed from: b */
        private JazzyViewPager f11843b;

        /* renamed from: c */
        private Context f11844c;

        /* renamed from: d */
        private JSONArray f11845d;

        /* renamed from: e */
        private View.OnClickListener f11846e = new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.builder.ImageRotationWithTitleBuilder.C3154a.View$OnClickListenerC31551 */

            public void onClick(View view) {
                try {
                    new CommonClickListener(C3154a.this.f11844c, C3154a.this.f11845d.getJSONObject(((Integer) view.getTag()).intValue())).onClick(view);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        C3154a() {
        }

        /* renamed from: a */
        public void mo27038a(Context context) {
            this.f11844c = context;
        }

        /* renamed from: a */
        public void mo27040a(JSONArray jSONArray) {
            this.f11845d = jSONArray;
        }

        /* renamed from: a */
        public void mo27039a(JazzyViewPager jazzyViewPager) {
            this.f11843b = jazzyViewPager;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            List list = ImageRotationWithTitleBuilder.this.f11834e;
            ImageView imageView = new ImageView(GBApplication.instance());
            int a = DensityUtil.m14127a(GBApplication.instance());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(Integer.valueOf(i % ImageRotationWithTitleBuilder.this.f11833d.length()));
            imageView.setOnClickListener(this.f11846e);
            ImageTools.m14149a(((SchoolNotifyBanner) list.get(i % ImageRotationWithTitleBuilder.this.f11834e.size())).getImgUrl(), imageView, GBApplication.instance().options);
            viewGroup.addView(imageView, -1, a);
            this.f11843b.mo28280a(imageView, i);
            return imageView;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            View view = (View) obj;
            viewGroup.removeView((View) obj);
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public int getCount() {
            if (ImageRotationWithTitleBuilder.this.f11834e.size() == 1) {
                return 1;
            }
            if (ImageRotationWithTitleBuilder.this.f11834e.size() == 0) {
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
    /* renamed from: com.gbcom.gwifi.functions.template.builder.m$b */
    /* compiled from: ImageRotationWithTitleBuilder */
    public class C3156b implements ViewPager.OnPageChangeListener {

        /* renamed from: b */
        private int f11849b = 0;

        /* renamed from: c */
        private LinearLayout f11850c;

        /* renamed from: d */
        private TextView f11851d;

        /* renamed from: e */
        private List<String> f11852e;

        C3156b() {
        }

        /* renamed from: a */
        public void mo27042a(LinearLayout linearLayout) {
            this.f11850c = linearLayout;
        }

        /* renamed from: a */
        public void mo27043a(TextView textView) {
            this.f11851d = textView;
        }

        /* renamed from: a */
        public void mo27044a(List<String> list) {
            this.f11852e = list;
        }

        @Override // android.support.p009v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            if (this.f11852e.size() != 1) {
                int size = i % this.f11852e.size();
                if (this.f11850c.getChildAt(this.f11849b) != null && this.f11850c.getChildAt(size) != null) {
                    this.f11850c.getChildAt(this.f11849b).setBackgroundResource(C2425R.C2426drawable.gi_dot_normal);
                    this.f11850c.getChildAt(size).setBackgroundResource(C2425R.C2426drawable.gi_dot_focused);
                    this.f11851d.setText(this.f11852e.get(size));
                    this.f11849b = size;
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
