package com.gbcom.gwifi.functions.revenue;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.revenue.domain.ShakeAdResponse;
import com.gbcom.gwifi.functions.revenue.domain.ShakeResult;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.umeng.commonsdk.proguard.UMCommonContent;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

public class ShakeActivity extends GBActivity implements SensorEventListener, View.OnClickListener {

    /* renamed from: c */
    private static final int f11534c = 0;

    /* renamed from: A */
    private Request f11535A;

    /* renamed from: B */
    private PopupWindow f11536B;

    /* renamed from: C */
    private PopupWindow f11537C;

    /* renamed from: D */
    private int f11538D;

    /* renamed from: E */
    private ImageView f11539E;

    /* renamed from: F */
    private ShakeResult.DataBean f11540F;

    /* renamed from: G */
    private RelativeLayout f11541G;

    /* renamed from: H */
    private int f11542H;

    /* renamed from: I */
    private int f11543I;

    /* renamed from: a */
    DisplayImageOptions f11544a = new DisplayImageOptions.Builder().showImageOnLoading(C2425R.C2426drawable.loading_big).showImageForEmptyUri(C2425R.C2426drawable.loading_big).showImageOnFail(C2425R.C2426drawable.loading_big).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();

    /* renamed from: b */
    OkRequestHandler<String> f11545b = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.revenue.ShakeActivity.C31032 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            C3470v.m14563a(ShakeActivity.this, "网络异常,请检查网络!");
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            Log.d("qqqqqq", str);
            if (abVar == ShakeActivity.this.f11554l) {
                try {
                    if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                        ShakeActivity.this.f11560r = false;
                        ShakeActivity.this.f11556n = (ShakeAdResponse) ShakeActivity.this.f11555m.mo21588a(str, ShakeAdResponse.class);
                        ShakeActivity.this.f11538D = ShakeActivity.this.f11556n.getData().getAdType();
                        ShakeActivity.this.m12705a((ShakeActivity) ShakeActivity.this.f11538D);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (abVar == ShakeActivity.this.f11563u) {
                try {
                    if (new JSONObject(str).getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                        ShakeActivity.this.f11540F = ((ShakeResult) ShakeActivity.this.f11555m.mo21588a(str, ShakeResult.class)).getData();
                        ShakeActivity.this.m12707a((ShakeActivity) ShakeActivity.this.f11540F);
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            } else if (abVar == ShakeActivity.this.f11535A) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    final int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
                    final String string = jSONObject.getString("resultMsg");
                    ShakeActivity.this.f11561s.postDelayed(new Runnable() {
                        /* class com.gbcom.gwifi.functions.revenue.ShakeActivity.C31032.RunnableC31041 */

                        @Override // java.lang.Runnable
                        public void run() {
                            if (i == 0) {
                                ShakeActivity.this.m12716c();
                            } else {
                                ShakeActivity.this.m12708a((ShakeActivity) string);
                            }
                        }
                    }, 1000);
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: d */
    private RelativeLayout f11546d;

    /* renamed from: e */
    private TextView f11547e;

    /* renamed from: f */
    private TextView f11548f;

    /* renamed from: g */
    private SensorManager f11549g;

    /* renamed from: h */
    private Vibrator f11550h;

    /* renamed from: i */
    private boolean f11551i;

    /* renamed from: j */
    private String f11552j = "4634421";

    /* renamed from: k */
    private Object f11553k = "ShakeActivity";

    /* renamed from: l */
    private Request f11554l;

    /* renamed from: m */
    private Gson f11555m;

    /* renamed from: n */
    private ShakeAdResponse f11556n;

    /* renamed from: o */
    private String f11557o;

    /* renamed from: p */
    private String f11558p;

    /* renamed from: q */
    private ImageView f11559q;

    /* renamed from: r */
    private boolean f11560r;

    /* renamed from: s */
    private Handler f11561s = new Handler() {
        /* class com.gbcom.gwifi.functions.revenue.ShakeActivity.HandlerC31021 */

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (ShakeActivity.this.f11562t != null && ShakeActivity.this.f11562t.isRunning()) {
                        ShakeActivity.this.f11562t.stop();
                    }
                    if (ShakeActivity.this.f11538D == 6) {
                        if (!ShakeActivity.this.f11560r) {
                            ShakeActivity.this.f11560r = true;
                            ShakeActivity.this.m12712b();
                        }
                    } else if (ShakeActivity.this.f11564v == null) {
                        ShakeActivity.this.f11563u = HttpUtil.m14327i(GBApplication.instance(), ShakeActivity.this.f11545b, ShakeActivity.this.f11553k);
                    } else if (!ShakeActivity.this.f11564v.isShowing()) {
                        ShakeActivity.this.f11563u = HttpUtil.m14327i(GBApplication.instance(), ShakeActivity.this.f11545b, ShakeActivity.this.f11553k);
                    }
                    ShakeActivity.this.f11551i = false;
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: t */
    private AnimationDrawable f11562t;

    /* renamed from: u */
    private Request f11563u;

    /* renamed from: v */
    private PopupWindow f11564v;

    /* renamed from: w */
    private Bitmap f11565w;

    /* renamed from: x */
    private PopupWindow f11566x;

    /* renamed from: y */
    private int f11567y;

    /* renamed from: z */
    private int f11568z;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "摇一摇", C2425R.layout.shake_activity_main, true);
        this.f11549g = (SensorManager) getSystemService(UMCommonContent.f22170aa);
        this.f11550h = (Vibrator) getSystemService("vibrator");
        if (this.f11555m == null) {
            this.f11555m = GsonUtil.m14241a();
        }
        m12718d();
        m12704a();
        m12712b();
    }

    /* renamed from: a */
    private void m12704a() {
        ((ImageView) findViewById(C2425R.C2427id.iv_main_rule)).setOnClickListener(this);
        this.f11559q = (ImageView) findViewById(C2425R.C2427id.iv_shake);
        this.f11541G = (RelativeLayout) findViewById(C2425R.C2427id.rl_sample);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12712b() {
        this.f11554l = HttpUtil.m14324h(GBApplication.instance(), this.f11545b, this.f11553k);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12708a(String str) {
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.shake_receive_fail, (ViewGroup) null);
        ((TextView) inflate.findViewById(C2425R.C2427id.tv_reason)).setText(str);
        this.f11536B = new PopupWindow(inflate, -1, -1, true);
        this.f11536B.setBackgroundDrawable(new ColorDrawable());
        this.f11536B.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
        inflate.findViewById(C2425R.C2427id.iv_close).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.revenue.ShakeActivity.View$OnClickListenerC31053 */

            public void onClick(View view) {
                ShakeActivity.this.f11536B.dismiss();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12716c() {
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.shake_receive_success, (ViewGroup) null);
        ((TextView) inflate.findViewById(C2425R.C2427id.tv_balance)).setText("成功领取" + this.f11568z + Constant.f13309cr);
        this.f11537C = new PopupWindow(inflate, -1, -1, true);
        this.f11537C.setBackgroundDrawable(new ColorDrawable());
        this.f11537C.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
        inflate.findViewById(C2425R.C2427id.iv_close).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.revenue.ShakeActivity.View$OnClickListenerC31064 */

            public void onClick(View view) {
                ShakeActivity.this.f11537C.dismiss();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12707a(ShakeResult.DataBean dataBean) {
        if (dataBean == null) {
            Toast.makeText(this, "当前活动尚未开放!请稍后重试!", 0).show();
            return;
        }
        View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.shake_balance_result, (ViewGroup) null);
        inflate.findViewById(C2425R.C2427id.iv_close).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.revenue.ShakeActivity.View$OnClickListenerC31075 */

            public void onClick(View view) {
                if (ShakeActivity.this.f11564v != null && ShakeActivity.this.f11564v.isShowing()) {
                    ShakeActivity.this.f11564v.dismiss();
                    ShakeActivity.this.m12712b();
                }
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C2425R.C2427id.rl_content);
        this.f11567y = dataBean.getPermitGain();
        TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.tv_balance);
        TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.tv_desc);
        this.f11539E = (ImageView) inflate.findViewById(C2425R.C2427id.iv_ad);
        if (dataBean != null) {
            this.f11568z = dataBean.getHitBeans();
            textView.setText(this.f11568z + Constant.f13309cr);
        }
        textView2.setText(dataBean.getRemark());
        if (this.f11565w != null || this.f11538D == 5) {
            if (this.f11538D == 5) {
                if (relativeLayout.getVisibility() != 0) {
                    relativeLayout.setVisibility(0);
                }
                relativeLayout.removeAllViews();
            } else {
                this.f11539E.setImageBitmap(this.f11565w);
                this.f11539E.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.revenue.ShakeActivity.View$OnClickListenerC31086 */

                    public void onClick(View view) {
                        if (ShakeActivity.this.f11558p != null) {
                            GBActivity.openBackWebView(ShakeActivity.this.f11558p, "");
                        }
                        ShakeActivity.this.m12712b();
                        if (ShakeActivity.this.f11564v != null && ShakeActivity.this.f11564v.isShowing()) {
                            ShakeActivity.this.f11564v.dismiss();
                        }
                        if (ShakeActivity.this.f11567y == 0) {
                            Toast.makeText(ShakeActivity.this, "请先连接GiWiFi!", 0).show();
                            return;
                        }
                        int userId = CacheAccount.getInstance().getUserId();
                        ShakeActivity.this.f11535A = HttpUtil.m14245a(GBApplication.instance(), 2, 2, userId + "", ShakeActivity.this.f11568z, ShakeActivity.this.f11545b, ShakeActivity.this.f11553k);
                    }
                });
            }
        } else if (this.f11556n != null) {
            ImageTools.m14149a(this.f11556n.getData().getImgUrl(), this.f11539E, this.f11544a);
        }
        this.f11564v = new PopupWindow(inflate, -1, -1, true);
        this.f11564v.setOutsideTouchable(false);
        this.f11564v.setBackgroundDrawable(new ColorDrawable());
        if (!isFinishing() && !this.f11564v.isShowing()) {
            if (this.f11538D != 0) {
                this.f11564v.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
                return;
            }
            C3470v.m14563a(this, "当前活动尚未开放!请稍后重试!");
            m12712b();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12705a(int i) {
        switch (i) {
            case 2:
                this.f11557o = this.f11556n.getData().getImgUrl();
                this.f11558p = this.f11556n.getData().getWapUrl();
                new Thread(new Runnable() {
                    /* class com.gbcom.gwifi.functions.revenue.ShakeActivity.RunnableC31097 */

                    @Override // java.lang.Runnable
                    public void run() {
                        ShakeActivity.this.f11565w = ImageTools.m14166d(ShakeActivity.this.f11557o);
                    }
                }).start();
                return;
            case 3:
                m12705a(2);
                return;
            case 4:
                m12705a(2);
                return;
            case 5:
                m12705a(2);
                return;
            default:
                return;
        }
    }

    /* renamed from: d */
    private void m12718d() {
        this.f11546d = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11546d.setOnClickListener(this);
        this.f11547e = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11548f = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11547e.setText("摇一摇");
        this.f11548f.setVisibility(8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.iv_main_rule /*{ENCODED_INT: 2131755956}*/:
                m12721e();
                return;
            default:
                return;
        }
    }

    /* renamed from: e */
    private void m12721e() {
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.shake_popupwindow_rule, (ViewGroup) null);
        inflate.findViewById(C2425R.C2427id.iv_close).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.revenue.ShakeActivity.View$OnClickListenerC31108 */

            public void onClick(View view) {
                if (ShakeActivity.this.f11566x != null && ShakeActivity.this.f11566x.isShowing()) {
                    ShakeActivity.this.f11566x.dismiss();
                }
            }
        });
        this.f11566x = new PopupWindow(inflate, -1, -1, true);
        this.f11566x.setOutsideTouchable(false);
        this.f11566x.setBackgroundDrawable(new ColorDrawable());
        if (!this.f11551i) {
            this.f11566x.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
        if (this.f11549g != null) {
            this.f11549g.registerListener(this, this.f11549g.getDefaultSensor(1), 3);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onPause() {
        super.onPause();
        this.f11549g.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        float[] fArr = sensorEvent.values;
        if (type != 1) {
            return;
        }
        if ((Math.abs(fArr[0]) > 15.0f || Math.abs(fArr[1]) > 15.0f || Math.abs(fArr[2]) > 15.0f) && !this.f11551i && !m12725g()) {
            this.f11551i = true;
            this.f11550h.vibrate(500);
            runOnUiThread(new Runnable() {
                /* class com.gbcom.gwifi.functions.revenue.ShakeActivity.RunnableC31119 */

                @Override // java.lang.Runnable
                public void run() {
                    ShakeActivity.this.m12723f();
                }
            });
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: f */
    private void m12723f() {
        this.f11559q.setBackgroundResource(C2425R.C2426drawable.gi_shake_animation);
        this.f11562t = (AnimationDrawable) this.f11559q.getBackground();
        this.f11562t.start();
        this.f11561s.sendEmptyMessageDelayed(0, 500);
    }

    /* renamed from: g */
    private boolean m12725g() {
        if (this.f11564v != null && this.f11564v.isShowing()) {
            return true;
        }
        if (this.f11566x != null && this.f11566x.isShowing()) {
            return true;
        }
        if (this.f11537C != null && this.f11537C.isShowing()) {
            return true;
        }
        if (this.f11536B == null || !this.f11536B.isShowing()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
        this.f11561s.removeCallbacksAndMessages(null);
        this.f11561s = null;
    }

    public void onPointerCaptureChanged(boolean z) {
    }
}
