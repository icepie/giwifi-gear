package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.support.p009v4.app.FragmentManager;
import android.support.p009v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.JazzyViewPager;
import com.gbcom.gwifi.widget.OutlineContainer;
import java.util.ArrayList;
import java.util.List;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.aj */
public class WifiConnectBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() == 0) {
            return null;
        }
        View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_image_rotation_no_title, (ViewGroup) null);
        List<View> arrayList = new ArrayList<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            ImageView imageView = new ImageView(GBApplication.instance());
            ImageTools.m14149a(jSONArray.getJSONObject(i).getString("icon_url"), imageView, GBApplication.instance().options2);
            arrayList.add(imageView);
        }
        while (arrayList.size() != 1 && arrayList.size() < 4) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                ImageView imageView2 = new ImageView(GBApplication.instance());
                ImageTools.m14149a(jSONArray.getJSONObject(i2).getString("icon_url"), imageView2, GBApplication.instance().options2);
                arrayList.add(imageView2);
            }
        }
        JazzyViewPager jazzyViewPager = (JazzyViewPager) inflate.findViewById(C2425R.C2427id.jazzy_pager);
        jazzyViewPager.mo28279a(JazzyViewPager.EnumC3486b.Accordion);
        C3132a aVar = new C3132a();
        aVar.mo26988a(jazzyViewPager);
        aVar.mo26989a(arrayList);
        aVar.mo26987a(context);
        aVar.mo26990a(jSONArray);
        jazzyViewPager.setAdapter(aVar);
        jazzyViewPager.setPageMargin(30);
        jazzyViewPager.getLayoutParams().height = (DensityUtil.m14127a(GBApplication.instance()) * 1) / 2;
        jazzyViewPager.setCurrentItem(jSONArray.length() * 1000);
        if (jSONArray.length() > 1) {
            jazzyViewPager.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                /* class com.gbcom.gwifi.functions.template.builder.WifiConnectBuilder.View$OnAttachStateChangeListenerC31311 */

                public void onViewAttachedToWindow(View view) {
                    ((JazzyViewPager) view).mo28287b();
                }

                public void onViewDetachedFromWindow(View view) {
                    ((JazzyViewPager) view).mo28294e();
                }
            });
        }
        return inflate;
    }

    /* renamed from: com.gbcom.gwifi.functions.template.builder.aj$a */
    /* compiled from: WifiConnectBuilder */
    class C3132a extends PagerAdapter {

        /* renamed from: b */
        private List<View> f11748b;

        /* renamed from: c */
        private JazzyViewPager f11749c;

        /* renamed from: d */
        private Context f11750d;

        /* renamed from: e */
        private JSONArray f11751e;

        /* renamed from: f */
        private View.OnClickListener f11752f = new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.builder.WifiConnectBuilder.C3132a.View$OnClickListenerC31331 */

            public void onClick(View view) {
                try {
                    new CommonClickListener(C3132a.this.f11750d, C3132a.this.f11751e.getJSONObject(((Integer) view.getTag()).intValue())).onClick(view);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        C3132a() {
        }

        /* renamed from: a */
        public void mo26987a(Context context) {
            this.f11750d = context;
        }

        /* renamed from: a */
        public void mo26990a(JSONArray jSONArray) {
            this.f11751e = jSONArray;
        }

        /* renamed from: a */
        public void mo26989a(List<View> list) {
            this.f11748b = list;
        }

        /* renamed from: a */
        public void mo26988a(JazzyViewPager jazzyViewPager) {
            this.f11749c = jazzyViewPager;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view;
            if (this.f11751e.length() == 0) {
                view = new ImageView(GBApplication.instance());
            } else {
                view = this.f11748b.get(i % this.f11748b.size());
            }
            if (view.getParent() == null) {
                int a = DensityUtil.m14127a(GBApplication.instance());
                ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
                view.setTag(Integer.valueOf(i % this.f11751e.length()));
                view.setOnClickListener(this.f11752f);
                viewGroup.addView(view, -1, a);
            }
            this.f11749c.mo28280a(view, i);
            return view;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(this.f11749c.mo28286b(i));
            this.f11749c.mo28291c(i);
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public int getCount() {
            if (this.f11751e.length() == 1) {
                return 1;
            }
            if (this.f11751e.length() == 0) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view instanceof OutlineContainer ? ((OutlineContainer) view).getChildAt(0) == obj : view == obj;
        }
    }
}
