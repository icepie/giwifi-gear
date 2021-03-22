package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.p009v4.app.FragmentManager;
import android.support.p009v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.edu.SchoolNotifyActivity;
import com.gbcom.gwifi.functions.edu.domain.NotifyBean;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.cache.CacheEdu;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.util.p257b.ScreenUtil;
import com.gbcom.gwifi.util.p257b.StatusBarUtil;
import com.gbcom.gwifi.widget.JazzyViewPager;
import com.gbcom.gwifi.widget.OutlineContainer;
import com.gbcom.gwifi.widget.RoundViewOutlineProvider;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.util.ArrayList;
import java.util.List;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.template.builder.s */
public class NotifyBannerBuilder implements ViewBuilder {

    /* renamed from: a */
    OkRequestHandler f11865a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.builder.NotifyBannerBuilder.C31613 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (NotifyBannerBuilder.this.f11868d == abVar) {
                NotifyBannerBuilder.this.m12942a((NotifyBannerBuilder) str);
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    private TextView f11866b;

    /* renamed from: c */
    private TextView f11867c;

    /* renamed from: d */
    private Request f11868d;

    /* renamed from: e */
    private Gson f11869e;

    /* renamed from: f */
    private View f11870f;

    /* renamed from: g */
    private View f11871g;

    /* renamed from: h */
    private Context f11872h;

    /* renamed from: i */
    private List<String> f11873i = new ArrayList();

    /* renamed from: j */
    private PopupWindow f11874j;

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        this.f11873i.clear();
        if (jSONObject == null) {
            return null;
        }
        JSONArray jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS);
        if (SystemUtil.m14531e()) {
            this.f11870f = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.layout_notify_banner_builder_margin, (ViewGroup) null);
        } else {
            this.f11870f = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.layout_notify_banner_builder, (ViewGroup) null);
        }
        JazzyViewPager jazzyViewPager = (JazzyViewPager) this.f11870f.findViewById(C2425R.C2427id.jazzy_pager);
        if (SystemUtil.m14531e() && Build.VERSION.SDK_INT >= 21) {
            jazzyViewPager.setClipToOutline(true);
            jazzyViewPager.setOutlineProvider(new RoundViewOutlineProvider(20.0f));
        }
        jazzyViewPager.mo28279a(JazzyViewPager.EnumC3486b.Accordion);
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            this.f11873i.add(jSONArray.getJSONObject(i).getString("icon_url"));
        }
        if (arrayList.size() < 3 && arrayList.size() != 1) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                this.f11873i.add(jSONArray.getJSONObject(i2).getString("icon_url"));
            }
        }
        C3164a aVar = new C3164a();
        aVar.mo27054a(jazzyViewPager);
        aVar.mo27055a(this.f11873i);
        aVar.mo27053a(context);
        aVar.mo27056a(jSONArray);
        jazzyViewPager.setAdapter(aVar);
        jazzyViewPager.setPageMargin(30);
        jazzyViewPager.getLayoutParams().height = (DensityUtil.m14127a(GBApplication.instance()) * 43) / 100;
        jazzyViewPager.setCurrentItem(jSONArray.length() * 1000);
        if (jSONArray.length() > 1) {
            jazzyViewPager.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                /* class com.gbcom.gwifi.functions.template.builder.NotifyBannerBuilder.View$OnAttachStateChangeListenerC31591 */

                public void onViewAttachedToWindow(View view) {
                    ((JazzyViewPager) view).mo28287b();
                }

                public void onViewDetachedFromWindow(View view) {
                    ((JazzyViewPager) view).mo28294e();
                }
            });
        }
        if (this.f11869e == null) {
            this.f11869e = GsonUtil.m14241a();
        }
        this.f11872h = context;
        this.f11866b = (TextView) this.f11870f.findViewById(C2425R.C2427id.notify_latest);
        this.f11867c = (TextView) this.f11870f.findViewById(C2425R.C2427id.tv_notify);
        this.f11871g = this.f11870f.findViewById(C2425R.C2427id.ll_content);
        this.f11868d = HttpUtil.m14288b(0, this.f11865a, "");
        return this.f11870f;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12942a(String str) {
        try {
            if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                List<NotifyBean.DataBean.NotifyListBean> notify_list = ((NotifyBean) this.f11869e.mo21588a(str, NotifyBean.class)).getData().getNotify_list();
                if (notify_list.size() != 0) {
                    this.f11870f.setVisibility(0);
                    if (this.f11871g != null) {
                        this.f11871g.setOnClickListener(new View.OnClickListener() {
                            /* class com.gbcom.gwifi.functions.template.builder.NotifyBannerBuilder.View$OnClickListenerC31602 */

                            public void onClick(View view) {
                                NotifyBannerBuilder.this.f11872h.startActivity(new Intent(NotifyBannerBuilder.this.f11872h, SchoolNotifyActivity.class));
                            }
                        });
                    }
                    if (notify_list.size() > 1) {
                        this.f11866b.setText(notify_list.get(0).getTitle());
                        this.f11867c.setText(notify_list.get(1).getTitle());
                    }
                    if (notify_list.size() > 0 && notify_list.size() < 2) {
                        this.f11866b.setText(notify_list.get(0).getTitle());
                    }
                    int limit_num = notify_list.get(0).getLimit_num();
                    Long eduNotifyLastTime = CacheEdu.getInstance().getEduNotifyLastTime();
                    if (System.currentTimeMillis() - eduNotifyLastTime.longValue() >= 86400000) {
                        CacheEdu.getInstance().setEduNotifyNum(0);
                    }
                    String title = notify_list.get(0).getTitle();
                    if (!TextUtils.equals(title, CacheEdu.getInstance().getEduNotifyTitle())) {
                        CacheEdu.getInstance().setEduNotifyTitle(title);
                        CacheEdu.getInstance().setEduNotifyNum(0);
                        CacheEdu.getInstance().setEduNotifyLastTime(0);
                    }
                    int eduNotifyNum = CacheEdu.getInstance().getEduNotifyNum();
                    if (eduNotifyNum < limit_num && System.currentTimeMillis() - eduNotifyLastTime.longValue() >= 6000000) {
                        m12940a(notify_list.get(0), eduNotifyNum);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m12940a(final NotifyBean.DataBean.NotifyListBean notifyListBean, int i) {
        View inflate = LayoutInflater.from(this.f11872h).inflate(C2425R.layout.layout_schoolnotify, (ViewGroup) null);
        ((Button) inflate.findViewById(C2425R.C2427id.btn_all)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.builder.NotifyBannerBuilder.View$OnClickListenerC31624 */

            public void onClick(View view) {
                if (NotifyBannerBuilder.this.f11874j != null) {
                    NotifyBannerBuilder.this.f11874j.dismiss();
                }
                if (!TextUtils.isEmpty(notifyListBean.getWap_url())) {
                    GBActivity.openGiBackWebView(notifyListBean.getWap_url(), "");
                    CacheEdu.getInstance().setEduNotifyNum(notifyListBean.getLimit_num());
                }
            }
        });
        inflate.findViewById(C2425R.C2427id.iv_close).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.builder.NotifyBannerBuilder.View$OnClickListenerC31635 */

            public void onClick(View view) {
                if (NotifyBannerBuilder.this.f11874j != null) {
                    NotifyBannerBuilder.this.f11874j.dismiss();
                }
            }
        });
        int c = ScreenUtil.m14176c(this.f11872h) + StatusBarUtil.m14184a(this.f11872h);
        if (this.f11874j == null) {
            this.f11874j = new PopupWindow(inflate, -1, c, true);
        }
        this.f11874j.setClippingEnabled(false);
        this.f11874j.setBackgroundDrawable(new ColorDrawable());
        String digest = notifyListBean.getDigest();
        MainActivity currentMainActivity = GBApplication.instance().getCurrentMainActivity();
        if (currentMainActivity != null) {
            if (!TextUtils.isEmpty(digest)) {
                if (!currentMainActivity.isWindowShowing()) {
                    this.f11874j.showAtLocation(inflate, 51, 0, 0);
                    CacheEdu.getInstance().setEduNotifyNum(i + 1);
                    CacheEdu.getInstance().setEduNotifyLastTime(System.currentTimeMillis());
                }
            } else if (!TextUtils.isEmpty(notifyListBean.getTitle()) && !currentMainActivity.isWindowShowing()) {
                this.f11874j.showAtLocation(inflate, 51, 0, 0);
                CacheEdu.getInstance().setEduNotifyNum(i + 1);
                CacheEdu.getInstance().setEduNotifyLastTime(System.currentTimeMillis());
            }
            ((TextView) this.f11874j.getContentView().findViewById(2131755412)).setText(digest + "...");
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.template.builder.s$a */
    /* compiled from: NotifyBannerBuilder */
    class C3164a extends PagerAdapter {

        /* renamed from: b */
        private List<String> f11882b;

        /* renamed from: c */
        private JazzyViewPager f11883c;

        /* renamed from: d */
        private Context f11884d;

        /* renamed from: e */
        private JSONArray f11885e;

        /* renamed from: f */
        private View.OnClickListener f11886f = new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.builder.NotifyBannerBuilder.C3164a.View$OnClickListenerC31651 */

            public void onClick(View view) {
                try {
                    new CommonClickListener(C3164a.this.f11884d, C3164a.this.f11885e.getJSONObject(((Integer) view.getTag()).intValue())).onClick(view);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        C3164a() {
        }

        /* renamed from: a */
        public void mo27053a(Context context) {
            this.f11884d = context;
        }

        /* renamed from: a */
        public void mo27056a(JSONArray jSONArray) {
            this.f11885e = jSONArray;
        }

        /* renamed from: a */
        public void mo27055a(List<String> list) {
            this.f11882b = list;
        }

        /* renamed from: a */
        public void mo27054a(JazzyViewPager jazzyViewPager) {
            this.f11883c = jazzyViewPager;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            ImageView imageView = new ImageView(viewGroup.getContext());
            ImageTools.m14149a(this.f11882b.get(i % this.f11882b.size()), imageView, GBApplication.instance().options2);
            int a = DensityUtil.m14127a(GBApplication.instance());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(Integer.valueOf(i % this.f11885e.length()));
            imageView.setOnClickListener(this.f11886f);
            viewGroup.addView(imageView, -1, a);
            this.f11883c.mo28280a(imageView, i);
            return imageView;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(this.f11883c.mo28286b(i));
            this.f11883c.mo28291c(i);
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public int getCount() {
            if (this.f11885e.length() == 1) {
                return 1;
            }
            if (this.f11885e.length() == 0) {
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
