package com.gbcom.gwifi.functions.product;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.p012v7.widget.LinearLayoutManager;
import android.support.p012v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.product.domain.AppDetailResponse;
import com.gbcom.gwifi.functions.product.domain.RecommendAppListResponse;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.StorageUtils;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.JazzyViewPager;
import com.gbcom.gwifi.widget.LoadingView;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

public class AppDownloadDetailActivity extends GBActivity {

    /* renamed from: a */
    private RelativeLayout f10061a;

    /* renamed from: b */
    private TextView f10062b;

    /* renamed from: c */
    private TextView f10063c;

    /* renamed from: d */
    private Gson f10064d;

    /* renamed from: e */
    private OkRequestHandler<String> f10065e = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.C28071 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (abVar == AppDownloadDetailActivity.this.f10082v) {
                AppDownloadDetailActivity.this.stopOfficeDrawable();
                AppDownloadDetailActivity.this.errWebDrawable();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == AppDownloadDetailActivity.this.f10082v) {
                try {
                    if (((Integer) new JSONObject(str).get(AbstractC5718b.JSON_ERRORCODE)).intValue() == 0) {
                        if (AppDownloadDetailActivity.this.f10064d == null) {
                            AppDownloadDetailActivity.this.f10064d = GsonUtil.m14241a();
                        }
                        AppDownloadDetailActivity.this.f10066f = (AppDetailResponse) AppDownloadDetailActivity.this.f10064d.mo21588a(str, AppDetailResponse.class);
                        AppDownloadDetailActivity.this.m11604a();
                        return;
                    }
                    AppDownloadDetailActivity.this.errWebDrawable();
                } catch (JSONException e) {
                    e.printStackTrace();
                    AppDownloadDetailActivity.this.errWebDrawable();
                }
            } else if (abVar == AppDownloadDetailActivity.this.f10079s) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
                    Intent intent = new Intent("get_prize_complete");
                    intent.putExtra("pkgName", AppDownloadDetailActivity.this.f10073m.getPkgName());
                    GBApplication.instance().sendBroadcast(intent);
                    if (i == 0) {
                        if (StorageUtils.m14492c(AppDownloadDetailActivity.this, AppDownloadDetailActivity.this.f10073m.getPkgName())) {
                            AppDownloadDetailActivity.this.f10080t.setText("打开");
                        } else {
                            AppDownloadDetailActivity.this.f10080t.setText("已完成");
                        }
                        GBApplication.instance().getCurrentActivity().showSecondDialog("提示", "恭喜您," + AppDownloadDetailActivity.this.f10073m.getPointsReward() + "旺豆领取成功!", "确定", new GBActivity.AbstractC2517a() {
                            /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.C28071.C28081 */

                            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                            public void onClick(Dialog dialog, View view) {
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnCancelListener() {
                            /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.C28071.DialogInterface$OnCancelListenerC28092 */

                            public void onCancel(DialogInterface dialogInterface) {
                                dialogInterface.dismiss();
                            }
                        });
                        return;
                    }
                    if (StorageUtils.m14492c(AppDownloadDetailActivity.this, AppDownloadDetailActivity.this.f10073m.getPkgName())) {
                        AppDownloadDetailActivity.this.f10080t.setText("打开");
                    } else {
                        AppDownloadDetailActivity.this.f10080t.setText("已完成");
                    }
                    GBApplication.instance().getCurrentActivity().showSecondDialog("提示", jSONObject.getString("resultMsg"), "确定", new GBActivity.AbstractC2517a() {
                        /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.C28071.C28103 */

                        @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                        public void onClick(Dialog dialog, View view) {
                            dialog.dismiss();
                        }
                    }, new DialogInterface.OnCancelListener() {
                        /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.C28071.DialogInterface$OnCancelListenerC28114 */

                        public void onCancel(DialogInterface dialogInterface) {
                            dialogInterface.dismiss();
                        }
                    });
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: f */
    private AppDetailResponse f10066f;

    /* renamed from: g */
    private int f10067g;

    /* renamed from: h */
    private JazzyViewPager f10068h;

    /* renamed from: i */
    private int f10069i;

    /* renamed from: j */
    private int f10070j;

    /* renamed from: k */
    private boolean f10071k;

    /* renamed from: l */
    private ScrollView f10072l;

    /* renamed from: m */
    private RecommendAppListResponse.DataBean.AppListBean f10073m;

    /* renamed from: n */
    private int f10074n;

    /* renamed from: o */
    private LoadingView f10075o;

    /* renamed from: p */
    private int f10076p;

    /* renamed from: q */
    private int f10077q;

    /* renamed from: r */
    private boolean f10078r;

    /* renamed from: s */
    private Request f10079s;

    /* renamed from: t */
    private TextView f10080t;

    /* renamed from: u */
    private int f10081u;

    /* renamed from: v */
    private Request f10082v;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11604a() {
        ((TextView) findViewById(C2425R.C2427id.balance_number)).setText("安装并体验APP后奖励" + this.f10073m.getPointsReward() + Constant.f13309cr);
        ImageTools.m14149a(this.f10066f.getData().getIconUrl(), (ImageView) findViewById(2131755155), GBApplication.instance().options);
        ((TextView) findViewById(2131755200)).setText(this.f10066f.getData().getAppName());
        ((TextView) findViewById(C2425R.C2427id.tv_desc)).setText(this.f10066f.getData().getCategoryInfo().getCategoryName());
        ((TextView) findViewById(C2425R.C2427id.tv_count)).setText(new DecimalFormat("0.00").format((double) ((((float) Integer.parseInt(this.f10066f.getData().getFileSize())) / 1024.0f) / 1024.0f)) + "MB  " + m11603a(this.f10066f.getData().getAppDownCount()) + "次下载");
        this.f10080t = (TextView) findViewById(C2425R.C2427id.tv_download);
        if (Constant.f13279cA == this.f10067g) {
            this.f10080t.setText("下载");
        } else if (Constant.f13280cB == this.f10067g) {
            this.f10080t.setText("下载中");
        } else if (Constant.f13281cC == this.f10067g) {
            this.f10080t.setText("安装");
        } else if (Constant.f13282cD == this.f10067g) {
            this.f10080t.setText("打开领奖励");
        } else if (Constant.f13283cE == this.f10067g) {
            this.f10080t.setText("已领取");
            this.f10080t.setEnabled(false);
            this.f10080t.setBackgroundResource(C2425R.C2426drawable.gi_app_bg_step_finish);
            this.f10080t.setTextColor(getResources().getColor(2131624113));
        }
        this.f10080t.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.View$OnClickListenerC28122 */

            public void onClick(View view) {
                AppDownloadActivity appDownloadActivity;
                AppDownloadActivity appDownloadActivity2 = null;
                List<GBActivity> activitiesList = GBApplication.instance().getActivitiesList();
                int i = 0;
                while (i < activitiesList.size()) {
                    GBActivity gBActivity = activitiesList.get(i);
                    if (gBActivity instanceof AppDownloadActivity) {
                        appDownloadActivity = (AppDownloadActivity) gBActivity;
                    } else {
                        appDownloadActivity = appDownloadActivity2;
                    }
                    i++;
                    appDownloadActivity2 = appDownloadActivity;
                }
                if (TextUtils.equals(AppDownloadDetailActivity.this.f10080t.getText().toString().trim(), "下载")) {
                    appDownloadActivity2.getFirstFragment().mo25562a(AppDownloadDetailActivity.this.f10073m);
                    AppDownloadDetailActivity.this.startActivity(new Intent(GBApplication.instance(), AppDownloadActivity.class));
                    AppDownloadDetailActivity.this.finish();
                } else if (TextUtils.equals(AppDownloadDetailActivity.this.f10080t.getText().toString().trim(), "安装")) {
                    if (AppDownloadDetailActivity.this.f10077q == -1) {
                        Intent intent = new Intent("install_apk");
                        intent.putExtra("appListBean", AppDownloadDetailActivity.this.f10073m);
                        GBApplication.instance().sendBroadcast(intent);
                        AppDownloadDetailActivity.this.finish();
                    } else if (AppDownloadDetailActivity.this.f10077q == 0) {
                        Intent intent2 = new Intent("install_apk_firstitem");
                        intent2.putExtra("appListBean", AppDownloadDetailActivity.this.f10073m);
                        GBApplication.instance().sendBroadcast(intent2);
                        AppDownloadDetailActivity.this.finish();
                    }
                } else if (TextUtils.equals(AppDownloadDetailActivity.this.f10080t.getText().toString().trim(), "打开领奖励")) {
                    AppDownloadDetailActivity.this.m11606a((AppDownloadDetailActivity) AppDownloadDetailActivity.this.f10073m.getPkgName(), (String) true);
                } else if (TextUtils.equals(AppDownloadDetailActivity.this.f10080t.getText().toString().trim(), "打开")) {
                    AppDownloadDetailActivity.this.m11606a((AppDownloadDetailActivity) AppDownloadDetailActivity.this.f10073m.getPkgName(), (String) false);
                }
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(2131755207);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new C2820a(this.f10066f.getData().getScreenshots()));
        final TextView textView = (TextView) findViewById(C2425R.C2427id.f8363tv);
        textView.setText(this.f10066f.getData().getDescription());
        textView.setMaxLines(5);
        this.f10072l = (ScrollView) findViewById(C2425R.C2427id.scrollview);
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.ViewTreeObserver$OnGlobalLayoutListenerC28133 */

            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                AppDownloadDetailActivity.this.f10069i = textView.getHeight();
                textView.setMaxLines(1000);
                textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.ViewTreeObserver$OnGlobalLayoutListenerC28133.ViewTreeObserver$OnGlobalLayoutListenerC28141 */

                    public void onGlobalLayout() {
                        textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        AppDownloadDetailActivity.this.f10070j = textView.getHeight();
                        textView.setHeight(AppDownloadDetailActivity.this.f10069i);
                    }
                });
            }
        });
        ((TextView) findViewById(C2425R.C2427id.tv_icon)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.View$OnClickListenerC28154 */

            public void onClick(View view) {
                boolean z = true;
                ValueAnimator valueAnimator = new ValueAnimator();
                if (AppDownloadDetailActivity.this.f10071k) {
                    valueAnimator.setIntValues(AppDownloadDetailActivity.this.f10070j, AppDownloadDetailActivity.this.f10069i);
                } else {
                    valueAnimator.setIntValues(AppDownloadDetailActivity.this.f10069i, AppDownloadDetailActivity.this.f10070j);
                }
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.View$OnClickListenerC28154.C28161 */

                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        textView.setHeight(((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.View$OnClickListenerC28154.C28172 */

                    public void onAnimationStart(Animator animator) {
                    }

                    public void onAnimationEnd(Animator animator) {
                    }

                    public void onAnimationCancel(Animator animator) {
                    }

                    public void onAnimationRepeat(Animator animator) {
                    }
                });
                valueAnimator.setDuration(500L);
                valueAnimator.start();
                AppDownloadDetailActivity appDownloadDetailActivity = AppDownloadDetailActivity.this;
                if (AppDownloadDetailActivity.this.f10071k) {
                    z = false;
                }
                appDownloadDetailActivity.f10071k = z;
            }
        });
        stopOfficeDrawable();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.product.AppDownloadDetailActivity$a */
    public class C2820a extends RecyclerView.Adapter<C2821b> {

        /* renamed from: b */
        private List<AppDetailResponse.DataBean.ScreenshotsBean> f10099b = new ArrayList();

        public C2820a(List<AppDetailResponse.DataBean.ScreenshotsBean> list) {
            this.f10099b = list;
        }

        /* renamed from: a */
        public C2821b onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new C2821b(LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.app_down_detail_item, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(C2821b bVar, int i) {
            ImageTools.m14149a(this.f10099b.get(i).getOriginalUrl(), bVar.f10100a, GBApplication.instance().options);
        }

        @Override // android.support.p012v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.f10099b.size();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11606a(String str, boolean z) {
        startActivity(getPackageManager().getLaunchIntentForPackage(str));
        this.f10078r = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.product.AppDownloadDetailActivity$b */
    public class C2821b extends RecyclerView.ViewHolder {

        /* renamed from: a */
        ImageView f10100a;

        public C2821b(View view) {
            super(view);
            this.f10100a = (ImageView) view.findViewById(C2425R.C2427id.imageview);
        }
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "游戏列表", C2425R.layout.app_activity_download_detail, true);
        Intent intent = getIntent();
        this.f10067g = intent.getIntExtra("state", 1);
        this.f10074n = intent.getIntExtra("position", -1);
        this.f10076p = intent.getIntExtra("productId", -1);
        this.f10073m = (RecommendAppListResponse.DataBean.AppListBean) intent.getSerializableExtra("appListBean");
        this.f10077q = intent.getIntExtra("currentItem", -1);
        this.f10081u = intent.getIntExtra("hasFinished", -1);
        this.f10082v = HttpUtil.m14252a(this, this.f10065e, "", this.f10076p);
        m11610b();
    }

    /* renamed from: b */
    private void m11610b() {
        this.f10061a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10061a.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.View$OnClickListenerC28185 */

            public void onClick(View view) {
                AppDownloadDetailActivity.this.startActivity(new Intent(GBApplication.instance(), AppDownloadActivity.class));
                AppDownloadDetailActivity.this.finish();
            }
        });
        this.f10062b = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10063c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10062b.setText("应用详情");
        this.f10063c.setVisibility(8);
        this.f10075o = (LoadingView) findViewById(C2425R.C2427id.loadingview);
        this.f10075o.mo28298a(new LoadingView.AbstractC3488a() {
            /* class com.gbcom.gwifi.functions.product.AppDownloadDetailActivity.C28196 */

            @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
            /* renamed from: a */
            public void mo24655a(View view) {
                AppDownloadDetailActivity.this.startOfficeDrawable();
                AppDownloadDetailActivity.this.f10082v = HttpUtil.m14252a(AppDownloadDetailActivity.this, AppDownloadDetailActivity.this.f10065e, "", AppDownloadDetailActivity.this.f10076p);
            }
        });
        startOfficeDrawable();
    }

    /* renamed from: a */
    private static String m11603a(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (j > 100000000) {
            return decimalFormat.format((double) (((float) j) / 1.0E8f)) + "亿";
        }
        if (j < 10000) {
            return j + "";
        }
        if (j >= 10000) {
            return decimalFormat.format((double) (((float) j) / 10000.0f)) + "万";
        }
        return "";
    }

    public void startOfficeDrawable() {
        this.f10075o.setVisibility(0);
        this.f10075o.mo28307i();
        this.f10075o.mo28313o();
        this.f10075o.mo28315q();
        if (this.f10075o.mo28310l()) {
            this.f10075o.mo28309k();
        }
        this.f10075o.mo28297a();
        this.f10075o.mo28302d();
        this.f10075o.mo28304f();
        this.f10075o.mo28300b();
    }

    public void stopOfficeDrawable() {
        this.f10075o.setVisibility(8);
        this.f10075o.mo28301c();
    }

    public void errWebDrawable() {
        this.f10075o.setVisibility(0);
        this.f10075o.mo28303e();
        this.f10075o.mo28301c();
        this.f10075o.mo28305g();
        this.f10075o.mo28306h();
        this.f10075o.mo28308j();
        this.f10075o.mo28312n();
        this.f10075o.mo28314p();
    }

    @Override // android.support.p009v4.app.FragmentActivity
    public void onBackPressed() {
        startActivity(new Intent(GBApplication.instance(), AppDownloadActivity.class));
        super.onBackPressed();
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity
    public void onRestart() {
        if (this.f10078r) {
            this.f10078r = false;
            this.f10079s = HttpUtil.m14254a(GBApplication.instance(), this.f10065e, "", this.f10073m.getProductId(), this.f10073m.getPkgName(), this.f10073m.getPointsReward());
        }
        super.onRestart();
    }
}
