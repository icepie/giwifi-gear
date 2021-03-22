package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.p009v4.app.FragmentManager;
import android.support.p009v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.functions.edu.SchoolNotifyActivity;
import com.gbcom.gwifi.functions.edu.domain.NotifyBean;
import com.gbcom.gwifi.functions.edu.domain.SchoolNotifyBanner;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.template.p252a.CommonNotifyClickListener;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.cache.CacheEdu;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.JazzyViewPager;
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

/* renamed from: com.gbcom.gwifi.functions.template.builder.w */
public class SchoolNotifyBuilder implements ViewBuilder {

    /* renamed from: a */
    OkRequestHandler f11910a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.builder.SchoolNotifyBuilder.C31723 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (SchoolNotifyBuilder.this.f11912c == abVar) {
                SchoolNotifyBuilder.this.m12967a((SchoolNotifyBuilder) str);
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    private Gson f11911b;

    /* renamed from: c */
    private Request f11912c;

    /* renamed from: d */
    private TextView f11913d;

    /* renamed from: e */
    private TextView f11914e;

    /* renamed from: f */
    private LinearLayout f11915f;

    /* renamed from: g */
    private Context f11916g;

    /* renamed from: h */
    private PopupWindow f11917h;

    /* renamed from: i */
    private List<SchoolNotifyBanner> f11918i = new ArrayList();

    /* renamed from: j */
    private JazzyViewPager f11919j;

    /* renamed from: k */
    private int f11920k;

    /* renamed from: l */
    private int f11921l;

    /* renamed from: m */
    private View f11922m;

    /* renamed from: n */
    private int f11923n;

    /* renamed from: o */
    private int f11924o;

    /* renamed from: p */
    private JSONArray f11925p;

    /* renamed from: q */
    private int f11926q;

    /* renamed from: r */
    private int f11927r;

    /* renamed from: s */
    private Handler f11928s = new Handler() {
        /* class com.gbcom.gwifi.functions.template.builder.SchoolNotifyBuilder.HandlerC31701 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    SchoolNotifyBuilder.this.f11919j.setCurrentItem(SchoolNotifyBuilder.this.f11919j.getCurrentItem() + 1);
                    SchoolNotifyBuilder.this.f11928s.sendEmptyMessageDelayed(0, 3000);
                    return;
                default:
                    return;
            }
        }
    };

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        this.f11915f = null;
        if (SystemUtil.m14531e()) {
            this.f11915f = (LinearLayout) LayoutInflater.from(context).inflate(C2425R.layout.layout_schoolnotifybuilder_margin, viewGroup, false);
        } else {
            this.f11915f = (LinearLayout) LayoutInflater.from(context).inflate(C2425R.layout.layout_schoolnotifybuilder, viewGroup, false);
        }
        this.f11919j = (JazzyViewPager) this.f11915f.findViewById(C2425R.C2427id.viewpager);
        if (SystemUtil.m14531e() && Build.VERSION.SDK_INT >= 21) {
            this.f11919j.setClipToOutline(true);
            this.f11919j.setOutlineProvider(new RoundViewOutlineProvider(20.0f));
        }
        this.f11922m = this.f11915f.findViewById(C2425R.C2427id.ll_content);
        if (this.f11911b == null) {
            this.f11911b = GsonUtil.m14241a();
        }
        this.f11913d = (TextView) this.f11915f.findViewById(C2425R.C2427id.notify_latest);
        this.f11914e = (TextView) this.f11915f.findViewById(C2425R.C2427id.tv_notify);
        this.f11912c = HttpUtil.m14288b(0, this.f11910a, "");
        this.f11916g = context;
        this.f11923n = context.getResources().getDisplayMetrics().heightPixels;
        this.f11924o = context.getResources().getDisplayMetrics().widthPixels;
        this.f11918i.clear();
        this.f11925p = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS);
        if (this.f11925p == null || this.f11925p.length() == 0) {
            return this.f11915f;
        }
        for (int i = 0; i < this.f11925p.length(); i++) {
            JSONObject jSONObject2 = this.f11925p.getJSONObject(i);
            int i2 = jSONObject2.getInt("source_type");
            SchoolNotifyBanner schoolNotifyBanner = new SchoolNotifyBanner();
            String string = jSONObject2.getString("icon_url");
            String string2 = jSONObject2.getString("wap_url");
            schoolNotifyBanner.setImgUrl(string);
            schoolNotifyBanner.setSource_type(i2);
            schoolNotifyBanner.setWapUrl(string2);
            this.f11918i.add(schoolNotifyBanner);
        }
        m12969b();
        return this.f11915f;
    }

    /* renamed from: b */
    private void m12969b() {
        if (SystemUtil.m14531e()) {
            this.f11919j.getLayoutParams().height = (DensityUtil.m14127a(GBApplication.instance()) * 43) / 100;
        } else {
            DensityUtil.m14127a(GBApplication.instance());
            this.f11919j.getLayoutParams().height = (int) (((double) this.f11924o) * 0.43d);
            this.f11919j.getLayoutParams().width = this.f11924o;
        }
        if (this.f11928s != null) {
            this.f11928s.removeCallbacksAndMessages(null);
        }
        if (this.f11918i.size() > 1) {
            this.f11928s.sendEmptyMessageDelayed(0, 3000);
        }
        this.f11919j.setAdapter(new C3175a());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12967a(String str) {
        try {
            if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                List<NotifyBean.DataBean.NotifyListBean> notify_list = ((NotifyBean) this.f11911b.mo21588a(str, NotifyBean.class)).getData().getNotify_list();
                if (notify_list.size() != 0) {
                    this.f11915f.setVisibility(0);
                    if (this.f11922m != null) {
                        this.f11922m.setOnClickListener(new View.OnClickListener() {
                            /* class com.gbcom.gwifi.functions.template.builder.SchoolNotifyBuilder.View$OnClickListenerC31712 */

                            public void onClick(View view) {
                                SchoolNotifyBuilder.this.f11916g.startActivity(new Intent(SchoolNotifyBuilder.this.f11916g, SchoolNotifyActivity.class));
                            }
                        });
                    }
                    if (notify_list.size() > 1) {
                        this.f11913d.setText(notify_list.get(0).getTitle());
                        this.f11914e.setText(notify_list.get(1).getTitle());
                    }
                    if (notify_list.size() > 0 && notify_list.size() < 2) {
                        this.f11913d.setText(notify_list.get(0).getTitle());
                    }
                    GBGlobalConfig.m10510c().mo24399a(notify_list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m12965a(final NotifyBean.DataBean.NotifyListBean notifyListBean, int i) {
        View inflate = LayoutInflater.from(this.f11916g).inflate(C2425R.layout.layout_schoolnotify, (ViewGroup) null);
        ((Button) inflate.findViewById(C2425R.C2427id.btn_all)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.builder.SchoolNotifyBuilder.View$OnClickListenerC31734 */

            public void onClick(View view) {
                if (SchoolNotifyBuilder.this.f11917h != null) {
                    SchoolNotifyBuilder.this.f11917h.dismiss();
                }
                if (!TextUtils.isEmpty(notifyListBean.getWap_url())) {
                    GBActivity.openGiBackWebView(notifyListBean.getWap_url(), "");
                    CacheEdu.getInstance().setEduNotifyNum(notifyListBean.getLimit_num());
                }
            }
        });
        inflate.findViewById(C2425R.C2427id.iv_close).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.builder.SchoolNotifyBuilder.View$OnClickListenerC31745 */

            public void onClick(View view) {
                if (SchoolNotifyBuilder.this.f11917h != null) {
                    SchoolNotifyBuilder.this.f11917h.dismiss();
                }
            }
        });
        if (this.f11917h == null) {
            this.f11917h = new PopupWindow(inflate, -1, -1, true);
        }
        this.f11917h.setBackgroundDrawable(new ColorDrawable());
        String digest = notifyListBean.getDigest();
        MainActivity currentMainActivity = GBApplication.instance().getCurrentMainActivity();
        if (currentMainActivity != null) {
            if (!TextUtils.isEmpty(digest)) {
                if (!currentMainActivity.isWindowShowing()) {
                    this.f11917h.showAsDropDown(currentMainActivity.findViewById(C2425R.C2427id.include));
                    CacheEdu.getInstance().setEduNotifyNum(i + 1);
                    CacheEdu.getInstance().setEduNotifyLastTime(System.currentTimeMillis());
                }
            } else if (!TextUtils.isEmpty(notifyListBean.getTitle()) && !currentMainActivity.isWindowShowing()) {
                this.f11917h.showAsDropDown(currentMainActivity.findViewById(C2425R.C2427id.include));
                CacheEdu.getInstance().setEduNotifyNum(i + 1);
                CacheEdu.getInstance().setEduNotifyLastTime(System.currentTimeMillis());
            }
            ((TextView) this.f11917h.getContentView().findViewById(2131755412)).setText(digest + "...");
        }
    }

    /* renamed from: a */
    public void mo27065a() {
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.template.builder.w$a */
    /* compiled from: SchoolNotifyBuilder */
    public class C3175a extends PagerAdapter {
        private C3175a() {
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public int getCount() {
            if (SchoolNotifyBuilder.this.f11918i.size() == 0) {
                return 0;
            }
            if (SchoolNotifyBuilder.this.f11918i.size() != 1) {
                return 100000000;
            }
            return 1;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, final int i) {
            SchoolNotifyBanner schoolNotifyBanner = (SchoolNotifyBanner) SchoolNotifyBuilder.this.f11918i.get(i % SchoolNotifyBuilder.this.f11918i.size());
            schoolNotifyBanner.getSource_type();
            ImageView imageView = new ImageView(SchoolNotifyBuilder.this.f11916g);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(Integer.valueOf(i));
            imageView.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.template.builder.SchoolNotifyBuilder.C3175a.View$OnClickListenerC31761 */

                public void onClick(View view) {
                    try {
                        new CommonNotifyClickListener(SchoolNotifyBuilder.this.f11916g, SchoolNotifyBuilder.this.f11925p.getJSONObject(i % SchoolNotifyBuilder.this.f11925p.length())).mo26965c();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            SchoolNotifyBuilder.this.f11919j.setTag(Integer.valueOf(i));
            ImageTools.m14149a(schoolNotifyBanner.getImgUrl(), imageView, GBApplication.instance().options);
            viewGroup.addView(imageView);
            return imageView;
        }

        @Override // android.support.p009v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }
}
