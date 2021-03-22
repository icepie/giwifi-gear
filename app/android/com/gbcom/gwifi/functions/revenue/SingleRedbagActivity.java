package com.gbcom.gwifi.functions.revenue;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.redbag.view.TabContainer;
import com.gbcom.gwifi.functions.revenue.domain.SingleRedbagConfig;
import com.gbcom.gwifi.functions.revenue.domain.SingleRedbagResult;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.widget.LoadingView;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

public class SingleRedbagActivity extends GBActivity implements View.OnClickListener, TabContainer.AbstractC3078a {

    /* renamed from: A */
    private Request f11581A;

    /* renamed from: B */
    private boolean f11582B;

    /* renamed from: C */
    private int f11583C;

    /* renamed from: D */
    private LoadingView f11584D;

    /* renamed from: E */
    private boolean f11585E;

    /* renamed from: F */
    private int f11586F;

    /* renamed from: G */
    private boolean f11587G;

    /* renamed from: a */
    List<Integer> f11588a = new ArrayList();

    /* renamed from: b */
    OkRequestHandler<String> f11589b = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.revenue.SingleRedbagActivity.C31143 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            Toast.makeText(SingleRedbagActivity.this, "请求网络数据失败,请重试!", 0).show();
            SingleRedbagActivity.this.errWebDrawable();
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            Log.d("qqq", "response:" + str);
            if (abVar == SingleRedbagActivity.this.f11594g) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                        SingleRedbagActivity.this.f11596i = ((SingleRedbagConfig) SingleRedbagActivity.this.f11595h.mo21588a(str, SingleRedbagConfig.class)).getData();
                        SingleRedbagActivity.this.m12762f();
                        return;
                    }
                    SingleRedbagActivity.this.errWebDrawable();
                    Toast.makeText(SingleRedbagActivity.this, jSONObject.getString("resultMsg"), 0).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (abVar == SingleRedbagActivity.this.f11604q) {
                try {
                    JSONObject jSONObject2 = new JSONObject(str);
                    if (jSONObject2.getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                        SingleRedbagActivity.this.f11605r = ((SingleRedbagResult) SingleRedbagActivity.this.f11595h.mo21588a(str, SingleRedbagResult.class)).getData();
                        List<Integer> candidateList = SingleRedbagActivity.this.f11605r.getCandidateList();
                        SingleRedbagActivity.this.f11588a.clear();
                        for (int i = 0; i < candidateList.size(); i++) {
                            if (SingleRedbagActivity.this.f11605r.getHitBeans() != candidateList.get(i).intValue()) {
                                SingleRedbagActivity.this.f11588a.add(candidateList.get(i));
                            }
                        }
                        for (int i2 = 0; i2 < (candidateList.size() - SingleRedbagActivity.this.f11588a.size()) - 1; i2++) {
                            SingleRedbagActivity.this.f11588a.add(Integer.valueOf(SingleRedbagActivity.this.f11605r.getHitBeans()));
                        }
                        Collections.shuffle(SingleRedbagActivity.this.f11588a);
                        if (SingleRedbagActivity.this.f11582B) {
                            return;
                        }
                        if (SingleRedbagActivity.this.f11609v || SingleRedbagActivity.this.f11585E) {
                            SingleRedbagActivity.this.f11602o.setAdapter((ListAdapter) new C3119a());
                            SingleRedbagActivity.this.stopOfficeDrawable();
                            SingleRedbagActivity.this.f11609v = false;
                            SingleRedbagActivity.this.f11585E = false;
                            return;
                        }
                        return;
                    }
                    Toast.makeText(SingleRedbagActivity.this, jSONObject2.getString("resultMsg"), 0).show();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            } else if (abVar == SingleRedbagActivity.this.f11581A) {
                try {
                    JSONObject jSONObject3 = new JSONObject(str);
                    int i3 = jSONObject3.getInt(AbstractC5718b.JSON_ERRORCODE);
                    String string = jSONObject3.getString("resultMsg");
                    if (i3 == 0) {
                        SingleRedbagActivity.this.f11587G = true;
                        SingleRedbagActivity.this.f11594g = HttpUtil.m14339m(GBApplication.instance(), SingleRedbagActivity.this.f11589b, SingleRedbagActivity.this.f11593f);
                        return;
                    }
                    Toast.makeText(SingleRedbagActivity.this, string, 0).show();
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: c */
    private RelativeLayout f11590c;

    /* renamed from: d */
    private TextView f11591d;

    /* renamed from: e */
    private TextView f11592e;

    /* renamed from: f */
    private Object f11593f = "SingleRedbagActivity";

    /* renamed from: g */
    private Request f11594g;

    /* renamed from: h */
    private Gson f11595h;

    /* renamed from: i */
    private SingleRedbagConfig.DataBean f11596i;

    /* renamed from: j */
    private TextView f11597j;

    /* renamed from: k */
    private int f11598k;

    /* renamed from: l */
    private int f11599l;

    /* renamed from: m */
    private RelativeLayout f11600m;

    /* renamed from: n */
    private TabContainer f11601n;

    /* renamed from: o */
    private GridView f11602o;

    /* renamed from: p */
    private List<SingleRedbagConfig.DataBean.RedListBean> f11603p;

    /* renamed from: q */
    private Request f11604q;

    /* renamed from: r */
    private SingleRedbagResult.DataBean f11605r;

    /* renamed from: s */
    private LinearLayout f11606s;

    /* renamed from: t */
    private TextView f11607t;

    /* renamed from: u */
    private TextView f11608u;

    /* renamed from: v */
    private boolean f11609v = true;

    /* renamed from: w */
    private Handler f11610w = new Handler() {
        /* class com.gbcom.gwifi.functions.revenue.SingleRedbagActivity.HandlerC31121 */

        public void handleMessage(Message message) {
            int i = message.what;
        }
    };

    /* renamed from: x */
    private int f11611x;

    /* renamed from: y */
    private boolean f11612y;

    /* renamed from: z */
    private int f11613z;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "单人拆红包", C2425R.layout.single_redbag_activity_main, true);
        m12755c();
        m12748a();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f11598k = displayMetrics.heightPixels;
        this.f11599l = displayMetrics.widthPixels;
        m12751b();
    }

    /* renamed from: a */
    private void m12748a() {
        this.f11597j = (TextView) findViewById(C2425R.C2427id.tv_balance);
        findViewById(C2425R.C2427id.rl_balance).setOnClickListener(this);
        findViewById(C2425R.C2427id.iv_ranking).setOnClickListener(this);
        this.f11600m = (RelativeLayout) findViewById(C2425R.C2427id.rl_middle);
        this.f11601n = (TabContainer) findViewById(C2425R.C2427id.tabcontainer);
        this.f11601n.mo26759a(this);
        this.f11602o = (GridView) findViewById(2131755339);
        this.f11606s = (LinearLayout) findViewById(C2425R.C2427id.ll_content);
        this.f11608u = (TextView) findViewById(C2425R.C2427id.tv_start);
        this.f11608u.setOnClickListener(this);
        this.f11607t = (TextView) findViewById(C2425R.C2427id.tv_desc);
        this.f11584D = (LoadingView) findViewById(C2425R.C2427id.loadingview);
        this.f11584D.mo28298a(new LoadingView.AbstractC3488a() {
            /* class com.gbcom.gwifi.functions.revenue.SingleRedbagActivity.C31132 */

            @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
            /* renamed from: a */
            public void mo24655a(View view) {
                SingleRedbagActivity.this.f11585E = true;
                if (SingleRedbagActivity.this.f11603p != null) {
                    SingleRedbagActivity.this.f11603p.clear();
                }
                SingleRedbagActivity.this.m12751b();
                SingleRedbagActivity.this.startOfficeDrawable();
            }
        });
        startOfficeDrawable();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12751b() {
        if (this.f11595h == null) {
            this.f11595h = GsonUtil.m14241a();
        }
        this.f11594g = HttpUtil.m14339m(GBApplication.instance(), this.f11589b, this.f11593f);
    }

    /* renamed from: c */
    private void m12755c() {
        this.f11590c = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11590c.setOnClickListener(this);
        this.f11591d = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11592e = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11591d.setText("单人猜红包");
        this.f11592e.setVisibility(8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.rl_balance /*{ENCODED_INT: 2131755964}*/:
                if (this.f11596i != null) {
                    String beansChargeLink = this.f11596i.getBeansChargeLink();
                    if (!TextUtils.isEmpty(beansChargeLink)) {
                        GBActivity.openBackWebView(beansChargeLink, "在线购买旺豆");
                        this.f11587G = true;
                        return;
                    }
                    return;
                }
                return;
            case C2425R.C2427id.iv_ranking /*{ENCODED_INT: 2131755965}*/:
                startActivity(new Intent(GBApplication.instance(), SingleRedbagRankingActivity.class));
                return;
            case C2425R.C2427id.tv_start /*{ENCODED_INT: 2131755967}*/:
                if (TextUtils.isEmpty(this.f11597j.getText().toString().trim())) {
                    return;
                }
                if (Integer.parseInt(this.f11597j.getText().toString().trim()) < this.f11586F) {
                    C3470v.m14563a(this, "旺豆不足,正在前往赚旺豆页面");
                    startActivity(new Intent(this, ScoreActivity.class));
                    this.f11587G = true;
                    return;
                }
                m12758d();
                this.f11602o.setEnabled(true);
                if (this.f11612y) {
                    this.f11612y = false;
                    this.f11613z = 0;
                    this.f11604q = HttpUtil.m14310d(this, this.f11611x, this.f11589b, this.f11593f);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: d */
    private void m12758d() {
        this.f11602o.setAdapter((ListAdapter) new C3120b());
        this.f11607t.setText("请随意点击下方红包");
        this.f11608u.setText("请拆红包");
        this.f11582B = true;
        this.f11608u.setBackgroundResource(C2425R.C2426drawable.singleredbag_btn_press);
        this.f11608u.setTextColor(getResources().getColor(2131624113));
        this.f11608u.setEnabled(false);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f11606s, "rotation", 0.0f, 180.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f11606s, "scaleX", 1.0f, 0.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f11606s, "scaleY", 1.0f, 0.0f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.f11606s, "rotation", 180.0f, 360.0f);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.f11606s, "scaleX", 0.0f, 1.0f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.f11606s, "scaleY", 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3).before(ofFloat4).before(ofFloat5).before(ofFloat6);
        animatorSet.setDuration(1000L);
        animatorSet.start();
        this.f11602o.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* class com.gbcom.gwifi.functions.revenue.SingleRedbagActivity.C31154 */

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long j) {
                SingleRedbagActivity.this.f11602o.setEnabled(false);
                SingleRedbagActivity.this.f11581A = HttpUtil.m14245a(GBApplication.instance(), 5, SingleRedbagActivity.this.f11611x, CacheAccount.getInstance().getUserId() + "", SingleRedbagActivity.this.f11605r.getHitBeans(), SingleRedbagActivity.this.f11589b, SingleRedbagActivity.this.f11593f);
                final ImageView imageView = (ImageView) view.findViewById(C2425R.C2427id.f8354iv);
                final TextView textView = (TextView) view.findViewById(C2425R.C2427id.tv_balance);
                String str = Build.BRAND;
                String str2 = Build.MODEL;
                int i2 = Build.VERSION.SDK_INT;
                if (TextUtils.equals(str, "HUAWEI") || i2 >= 24) {
                    imageView.setImageResource(C2425R.C2426drawable.singleredbag_getpoints);
                    if (SingleRedbagActivity.this.f11605r != null) {
                        textView.setText(SingleRedbagActivity.this.f11605r.getHitBeans() + "");
                    }
                    for (int i3 = 0; i3 < adapterView.getChildCount(); i3++) {
                        if (i != i3) {
                            View childAt = adapterView.getChildAt(i3);
                            ((ImageView) childAt.findViewById(C2425R.C2427id.f8354iv)).setImageResource(C2425R.C2426drawable.singleredbag_open);
                            ((TextView) childAt.findViewById(C2425R.C2427id.tv_balance)).setText(((Object) SingleRedbagActivity.this.f11588a.get(SingleRedbagActivity.this.f11613z)) + "");
                            if (SingleRedbagActivity.this.f11613z >= SingleRedbagActivity.this.f11605r.getCandidateList().size() - 2) {
                                SingleRedbagActivity.this.f11613z = 0;
                            } else {
                                SingleRedbagActivity.this.f11613z++;
                            }
                            SingleRedbagActivity.this.m12759e();
                        }
                    }
                    return;
                }
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, "rotationY", 0.0f, 180.0f);
                ofFloat.setDuration(1000L);
                ofFloat.start();
                ofFloat.addListener(new Animator.AnimatorListener() {
                    /* class com.gbcom.gwifi.functions.revenue.SingleRedbagActivity.C31154.C31161 */

                    public void onAnimationStart(Animator animator) {
                    }

                    public void onAnimationEnd(Animator animator) {
                        imageView.setImageResource(C2425R.C2426drawable.singleredbag_getpoints);
                        if (SingleRedbagActivity.this.f11605r != null) {
                            textView.setText(SingleRedbagActivity.this.f11605r.getHitBeans() + "");
                        }
                        for (int i = 0; i < adapterView.getChildCount(); i++) {
                            if (i != i) {
                                View childAt = adapterView.getChildAt(i);
                                final ImageView imageView = (ImageView) childAt.findViewById(C2425R.C2427id.f8354iv);
                                final TextView textView = (TextView) childAt.findViewById(C2425R.C2427id.tv_balance);
                                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, "rotationY", 0.0f, 180.0f);
                                ofFloat.setDuration(1000L);
                                ofFloat.start();
                                ofFloat.addListener(new Animator.AnimatorListener() {
                                    /* class com.gbcom.gwifi.functions.revenue.SingleRedbagActivity.C31154.C31161.C31171 */

                                    public void onAnimationStart(Animator animator) {
                                    }

                                    public void onAnimationEnd(Animator animator) {
                                        imageView.setImageResource(C2425R.C2426drawable.singleredbag_open);
                                        textView.setText(((Object) SingleRedbagActivity.this.f11588a.get(SingleRedbagActivity.this.f11613z)) + "");
                                        if (SingleRedbagActivity.this.f11613z >= SingleRedbagActivity.this.f11605r.getCandidateList().size() - 2) {
                                            SingleRedbagActivity.this.f11613z = 0;
                                        } else {
                                            SingleRedbagActivity.this.f11613z++;
                                        }
                                        SingleRedbagActivity.this.m12759e();
                                    }

                                    public void onAnimationCancel(Animator animator) {
                                    }

                                    public void onAnimationRepeat(Animator animator) {
                                    }
                                });
                            }
                        }
                    }

                    public void onAnimationCancel(Animator animator) {
                    }

                    public void onAnimationRepeat(Animator animator) {
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m12759e() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("恭喜您,获得" + this.f11605r.getHitBeans() + Constant.f13309cr + ",已存入个人账户");
        spannableStringBuilder.setSpan(new ForegroundColorSpan(-65536), 6, (this.f11605r.getHitBeans() + Constant.f13309cr).length() + 6, 34);
        this.f11607t.setText(spannableStringBuilder);
        this.f11608u.setEnabled(true);
        this.f11608u.setText("再来一局");
        this.f11582B = false;
        this.f11608u.setTextColor(getResources().getColor(C2425R.color.red));
        this.f11608u.setBackgroundResource(C2425R.C2426drawable.singleredbag_btn);
        this.f11612y = true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: f */
    private void m12762f() {
        if (this.f11587G) {
            this.f11597j.setText(this.f11596i.getBeans() + "");
            this.f11587G = false;
            return;
        }
        this.f11597j.setText(this.f11596i.getBeans() + "");
        this.f11607t.setText("点击开始游戏,随意抽取以下金额红包");
        this.f11608u.setText("开始游戏");
        this.f11603p = this.f11596i.getRedList();
        this.f11601n.removeAllViews();
        for (int i = 0; i < this.f11603p.size(); i++) {
            this.f11601n.mo26758a(m12743a(i, this.f11603p));
        }
    }

    /* renamed from: a */
    private View m12743a(int i, List<SingleRedbagConfig.DataBean.RedListBean> list) {
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.single_redbag_item_list, (ViewGroup) null);
        ((TextView) inflate.findViewById(C2425R.C2427id.tv_deductBeans)).setText("扣除" + list.get(i).getTotalBeans() + Constant.f13309cr);
        ((TextView) inflate.findViewById(2131755200)).setText(list.get(i).getRedName() + Constant.f13309cr + "区");
        ((TextView) inflate.findViewById(C2425R.C2427id.tv_balance_get)).setText("抢" + list.get(i).getMinGainBeans() + "-" + list.get(i).getMaxGainBeans() + Constant.f13309cr);
        inflate.setOnTouchListener(new View.OnTouchListener() {
            /* class com.gbcom.gwifi.functions.revenue.SingleRedbagActivity.View$OnTouchListenerC31185 */

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        if (SingleRedbagActivity.this.f11582B && SingleRedbagActivity.this.f11583C != ((Integer) view.getTag()).intValue()) {
                            C3470v.m14563a(SingleRedbagActivity.this, "请先拆红包");
                            return true;
                        }
                }
                return false;
            }
        });
        return inflate;
    }

    @Override // com.gbcom.gwifi.functions.redbag.view.TabContainer.AbstractC3078a
    public void onTabSelected(int i, View view) {
        this.f11608u.setText("开始游戏");
        if (this.f11612y) {
            this.f11607t.setText("点击开始游戏,随意抽取以下金额红包");
        }
        this.f11583C = i;
        ((ImageView) view.findViewById(C2425R.C2427id.iv_bg)).setImageResource(C2425R.C2426drawable.icon_wangdou_press);
        ((TextView) view.findViewById(2131755200)).setTextColor(getResources().getColor(C2425R.color.singleredbag_biue));
        this.f11611x = this.f11603p.get(i).getRedId();
        this.f11586F = this.f11603p.get(i).getTotalBeans();
        this.f11609v = true;
        this.f11612y = false;
        this.f11604q = HttpUtil.m14310d(this, this.f11611x, this.f11589b, this.f11593f);
    }

    @Override // com.gbcom.gwifi.functions.redbag.view.TabContainer.AbstractC3078a
    public void onTabUnselected(int i, View view) {
        ((ImageView) view.findViewById(C2425R.C2427id.iv_bg)).setImageResource(C2425R.C2426drawable.icon_wangdou_default);
        ((TextView) view.findViewById(2131755200)).setTextColor(getResources().getColor(C2425R.color.singleredbag_grey));
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.revenue.SingleRedbagActivity$a */
    public class C3119a extends BaseAdapter {
        private C3119a() {
        }

        public int getCount() {
            if (SingleRedbagActivity.this.f11605r == null) {
                return 0;
            }
            return SingleRedbagActivity.this.f11605r.getCandidateList().size();
        }

        public Object getItem(int i) {
            return SingleRedbagActivity.this.f11605r.getCandidateList().get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.single_redbag_item_main, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.tv_balance);
            if (SingleRedbagActivity.this.f11605r != null) {
                textView.setText(((Object) SingleRedbagActivity.this.f11605r.getCandidateList().get(i)) + "");
            }
            return inflate;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.revenue.SingleRedbagActivity$b */
    public class C3120b extends BaseAdapter {
        private C3120b() {
        }

        public int getCount() {
            if (SingleRedbagActivity.this.f11605r == null) {
                return 0;
            }
            return SingleRedbagActivity.this.f11605r.getCandidateList().size();
        }

        public Object getItem(int i) {
            return SingleRedbagActivity.this.f11605r.getCandidateList().get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.single_redbag_item_close, (ViewGroup) null);
        }
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity
    public void onRestart() {
        super.onRestart();
        if (this.f11587G) {
            this.f11594g = HttpUtil.m14339m(GBApplication.instance(), this.f11589b, this.f11593f);
        }
    }

    public void startOfficeDrawable() {
        this.f11584D.setVisibility(0);
        this.f11584D.mo28307i();
        this.f11584D.mo28313o();
        this.f11584D.mo28315q();
        if (this.f11584D.mo28310l()) {
            this.f11584D.mo28309k();
        }
        this.f11584D.mo28297a();
        this.f11584D.mo28302d();
        this.f11584D.mo28304f();
        this.f11584D.mo28300b();
    }

    public void errWebDrawable() {
        this.f11584D.setVisibility(0);
        this.f11584D.mo28303e();
        this.f11584D.mo28301c();
        this.f11584D.mo28305g();
        this.f11584D.mo28306h();
        this.f11584D.mo28308j();
        this.f11584D.mo28312n();
        this.f11584D.mo28314p();
    }

    public void stopOfficeDrawable() {
        this.f11584D.setVisibility(8);
        this.f11584D.mo28301c();
    }
}
