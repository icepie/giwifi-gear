package com.gbcom.gwifi.functions.revenue;

import android.app.StatsManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.revenue.domain.DigBalanceResponse;
import com.gbcom.gwifi.functions.revenue.domain.DigConfigResponse;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.LoadingView;
import com.gbcom.gwifi.widget.MyGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

public class DigActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: A */
    private int f11421A = -1;

    /* renamed from: B */
    private LoadingView f11422B;

    /* renamed from: C */
    private Handler f11423C = new Handler() {
        /* class com.gbcom.gwifi.functions.revenue.DigActivity.HandlerC30791 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    };

    /* renamed from: D */
    private Runnable f11424D;

    /* renamed from: E */
    private RelativeLayout f11425E;

    /* renamed from: F */
    private List<Integer> f11426F = new ArrayList();

    /* renamed from: a */
    OkRequestHandler<String> f11427a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.revenue.DigActivity.C30813 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            DigActivity.this.errWebDrawable();
            C3470v.m14563a(DigActivity.this, "网络异常,请检查网络!");
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == DigActivity.this.f11433g) {
                try {
                    if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                        DigActivity.this.f11434h = ((DigConfigResponse) DigActivity.this.f11432f.mo21588a(str, DigConfigResponse.class)).getData().getAdCfgList();
                        for (int i = 0; i < DigActivity.this.f11434h.size(); i++) {
                            int adType = ((DigConfigResponse.DataBean.AdCfgListBean) DigActivity.this.f11434h.get(i)).getAdType();
                            if (adType == 3) {
                                DigActivity.this.f11445s++;
                            }
                            if (adType == 5) {
                                DigActivity.this.f11444r++;
                            }
                        }
                        if (DigActivity.this.f11444r > 0) {
                        }
                        if (DigActivity.this.f11444r == 0) {
                            DigActivity.this.m12634b();
                        }
                        DigActivity.this.f11435i = HttpUtil.m14333k(GBApplication.instance(), DigActivity.this.f11427a, DigActivity.this.f11431e);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (abVar == DigActivity.this.f11435i) {
                try {
                    if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                        DigActivity.this.f11436j = ((DigBalanceResponse) DigActivity.this.f11432f.mo21588a(str, DigBalanceResponse.class)).getData();
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            } else if (abVar == DigActivity.this.f11438l) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i2 = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
                    String string = jSONObject.getString("resultMsg");
                    if (i2 == 0) {
                        DigActivity.this.m12639d();
                    } else {
                        DigActivity.this.m12630a((DigActivity) string);
                    }
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    private RelativeLayout f11428b;

    /* renamed from: c */
    private TextView f11429c;

    /* renamed from: d */
    private TextView f11430d;

    /* renamed from: e */
    private Object f11431e = "DigActivity";

    /* renamed from: f */
    private Gson f11432f;

    /* renamed from: g */
    private Request f11433g;

    /* renamed from: h */
    private List<DigConfigResponse.DataBean.AdCfgListBean> f11434h;

    /* renamed from: i */
    private Request f11435i;

    /* renamed from: j */
    private DigBalanceResponse.DataBean f11436j;

    /* renamed from: k */
    private PopupWindow f11437k;

    /* renamed from: l */
    private Request f11438l;

    /* renamed from: m */
    private PopupWindow f11439m;

    /* renamed from: n */
    private PopupWindow f11440n;

    /* renamed from: o */
    private int f11441o;
    public DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(false).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();

    /* renamed from: p */
    private int f11442p;

    /* renamed from: q */
    private boolean f11443q;

    /* renamed from: r */
    private int f11444r;

    /* renamed from: s */
    private int f11445s;

    /* renamed from: t */
    private int f11446t;

    /* renamed from: u */
    private GridView f11447u;

    /* renamed from: v */
    private ImageView f11448v;

    /* renamed from: w */
    private boolean f11449w = true;

    /* renamed from: x */
    private boolean f11450x;

    /* renamed from: y */
    private int f11451y = -1;

    /* renamed from: z */
    private C3086a f11452z;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "挖矿", C2425R.layout.dig_activity_main, true);
        m12637c();
        m12625a();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12625a() {
        if (this.f11432f == null) {
            this.f11432f = GsonUtil.m14241a();
        }
        this.f11433g = HttpUtil.m14330j(this, this.f11427a, this.f11431e);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12634b() {
        this.f11426F.clear();
        this.f11452z = new C3086a();
        this.f11447u.setAdapter((ListAdapter) this.f11452z);
        stopOfficeDrawable();
        if (this.f11434h.size() == 0) {
            C3470v.m14563a(this, "当前活动尚未开放!请稍后重试!");
        }
    }

    /* renamed from: c */
    private void m12637c() {
        this.f11428b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11428b.setOnClickListener(this);
        this.f11429c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11430d = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11429c.setText("挖矿");
        this.f11430d.setVisibility(8);
        this.f11447u = (GridView) findViewById(2131755339);
        this.f11447u.setVerticalScrollBarEnabled(false);
        this.f11447u.setFastScrollEnabled(false);
        this.f11422B = (LoadingView) findViewById(C2425R.C2427id.loadingview);
        this.f11422B.mo28298a(new LoadingView.AbstractC3488a() {
            /* class com.gbcom.gwifi.functions.revenue.DigActivity.C30802 */

            @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
            /* renamed from: a */
            public void mo24655a(View view) {
                DigActivity.this.m12625a();
            }
        });
        startOfficeDrawable();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m12639d() {
        Random random = new Random();
        for (int i = 0; i < this.f11447u.getChildCount(); i++) {
            if (i != this.f11421A) {
                View childAt = this.f11447u.getChildAt(i);
                RelativeLayout relativeLayout = (RelativeLayout) childAt.findViewById(C2425R.C2427id.rl_content);
                relativeLayout.removeAllViews();
                relativeLayout.setEnabled(false);
                View findViewById = childAt.findViewById(C2425R.C2427id.rl_item);
                ((TextView) findViewById.findViewById(C2425R.C2427id.tv_balance)).setText((random.nextInt(this.f11436j.getMaxHitBeans() - 1) + 1) + "");
                findViewById.setVisibility(0);
            } else {
                View childAt2 = this.f11447u.getChildAt(i);
                ((RelativeLayout) childAt2.findViewById(2131755340)).setEnabled(false);
                View findViewById2 = childAt2.findViewById(C2425R.C2427id.ll_chose);
                TextView textView = (TextView) findViewById2.findViewById(C2425R.C2427id.tv_dig_balance);
                if (this.f11436j != null) {
                    textView.setText("+" + this.f11436j.getHitBeans());
                }
                findViewById2.setVisibility(0);
                ((ImageView) childAt2.findViewById(C2425R.C2427id.iv_again)).setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.revenue.DigActivity.View$OnClickListenerC30824 */

                    public void onClick(View view) {
                        DigActivity.this.m12625a();
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12630a(String str) {
        Random random = new Random();
        for (int i = 0; i < this.f11447u.getChildCount(); i++) {
            if (i != this.f11421A) {
                View childAt = this.f11447u.getChildAt(i);
                ((RelativeLayout) childAt.findViewById(C2425R.C2427id.rl_content)).removeAllViews();
                View findViewById = childAt.findViewById(C2425R.C2427id.rl_item);
                ((TextView) findViewById.findViewById(C2425R.C2427id.tv_balance)).setText((random.nextInt(this.f11436j.getMaxHitBeans() - 1) + 1) + "");
                findViewById.setVisibility(0);
            } else {
                View childAt2 = this.f11447u.getChildAt(i);
                RelativeLayout relativeLayout = (RelativeLayout) childAt2.findViewById(2131755340);
                relativeLayout.setEnabled(false);
                relativeLayout.setClickable(false);
                ((ImageView) childAt2.findViewById(C2425R.C2427id.iv_fail_again)).setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.revenue.DigActivity.View$OnClickListenerC30835 */

                    public void onClick(View view) {
                        DigActivity.this.m12625a();
                    }
                });
                childAt2.findViewById(C2425R.C2427id.ll_fail).setVisibility(0);
            }
        }
        C3470v.m14563a(this, str);
    }

    public void onPointerCaptureChanged(boolean z) {
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.revenue.DigActivity$a */
    public class C3086a extends BaseAdapter {
        private C3086a() {
        }

        public int getCount() {
            if (DigActivity.this.f11434h == null) {
                return 0;
            }
            return DigActivity.this.f11434h.size();
        }

        public Object getItem(int i) {
            return DigActivity.this.f11434h.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.dig_item_dig, (ViewGroup) null);
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C2425R.C2427id.rl_content);
            int adType = ((DigConfigResponse.DataBean.AdCfgListBean) DigActivity.this.f11434h.get(i)).getAdType();
            if ((viewGroup instanceof MyGridView) && ((MyGridView) viewGroup).f13695a) {
                return inflate;
            }
            if (!DigActivity.this.f11426F.contains(Integer.valueOf(i))) {
                DigActivity.this.f11426F.add(Integer.valueOf(i));
                DigActivity.this.m12626a(adType, i, relativeLayout);
            }
            return inflate;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12626a(int i, final int i2, RelativeLayout relativeLayout) {
        this.f11425E = relativeLayout;
        switch (i) {
            case 2:
                final ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                ImageTools.m14149a(this.f11434h.get(i2).getImgUrl(), imageView, this.options);
                imageView.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.revenue.DigActivity.View$OnClickListenerC30846 */

                    public void onClick(View view) {
                        imageView.setEnabled(false);
                        DigActivity.this.f11443q = true;
                        GBActivity.openBackWebView(((DigConfigResponse.DataBean.AdCfgListBean) DigActivity.this.f11434h.get(i2)).getWapUrl(), "");
                        DigActivity.this.f11421A = i2;
                        DigActivity.this.f11423C.postDelayed(new Runnable() {
                            /* class com.gbcom.gwifi.functions.revenue.DigActivity.View$OnClickListenerC30846.RunnableC30851 */

                            @Override // java.lang.Runnable
                            public void run() {
                                DigActivity.this.m12641e();
                            }
                        }, StatsManager.DEFAULT_TIMEOUT_MILLIS);
                    }
                });
                relativeLayout.removeAllViews();
                relativeLayout.addView(imageView);
                return;
            case 3:
                m12626a(2, i2, relativeLayout);
                return;
            case 4:
                m12626a(2, i2, relativeLayout);
                return;
            case 5:
                m12626a(2, i2, relativeLayout);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m12641e() {
        if (this.f11436j != null) {
            if (this.f11436j.getPermitGain() == 0) {
                Toast.makeText(this, "请先连接GiWiFi!", 0).show();
                return;
            }
            this.f11438l = HttpUtil.m14245a(GBApplication.instance(), 3, 3, CacheAccount.getInstance().getUserId() + "", this.f11436j.getHitBeans(), this.f11427a, this.f11431e);
        }
    }

    public void startOfficeDrawable() {
        this.f11422B.setVisibility(0);
        this.f11422B.mo28307i();
        this.f11422B.mo28313o();
        this.f11422B.mo28315q();
        if (this.f11422B.mo28310l()) {
            this.f11422B.mo28309k();
        }
        this.f11422B.mo28297a();
        this.f11422B.mo28302d();
        this.f11422B.mo28304f();
        this.f11422B.mo28300b();
    }

    public void errWebDrawable() {
        this.f11422B.setVisibility(0);
        this.f11422B.mo28303e();
        this.f11422B.mo28301c();
        this.f11422B.mo28305g();
        this.f11422B.mo28306h();
        this.f11422B.mo28308j();
        this.f11422B.mo28312n();
        this.f11422B.mo28314p();
    }

    public void stopOfficeDrawable() {
        this.f11422B.setVisibility(8);
        this.f11422B.mo28301c();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
        this.f11423C.removeCallbacksAndMessages(null);
        this.f11423C = null;
    }
}
