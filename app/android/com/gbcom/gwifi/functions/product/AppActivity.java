package com.gbcom.gwifi.functions.product;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.download.MyIntents;
import com.gbcom.gwifi.functions.p244d.ShareHelper;
import com.gbcom.gwifi.functions.product.domain.App;
import com.gbcom.gwifi.functions.product.domain.AttentionFile;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.p248b.AttentionFileDao;
import com.gbcom.gwifi.functions.product.p248b.DownloadFileDao;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.third.umeng.share.GiwifiShareApi;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.FormatUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.ResourceParse;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.StorageUtils;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.NoScrollGridView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.umeng.message.common.C6366a;
import com.xiaomi.stat.MiStat;
import java.p456io.File;
import java.p456io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import p041c.Request;

public class AppActivity extends GBActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final int INSTALL_OPEN_ANIM = 6;
    public static final int P2C_ANIM = 7;

    /* renamed from: d */
    private static final String f9970d = "AppActivity";

    /* renamed from: e */
    private static final Object f9971e = f9970d;
    public static List<View> imageViews = new ArrayList();

    /* renamed from: A */
    private AttentionFile f9972A;

    /* renamed from: B */
    private DowningReceiver f9973B;

    /* renamed from: C */
    private boolean f9974C = false;

    /* renamed from: D */
    private String f9975D = "";

    /* renamed from: E */
    private String f9976E = "";

    /* renamed from: F */
    private long f9977F = 0;

    /* renamed from: G */
    private AppInstallReceiver f9978G;

    /* renamed from: H */
    private String f9979H = "";

    /* renamed from: I */
    private RelativeLayout f9980I;

    /* renamed from: J */
    private ImageView f9981J;

    /* renamed from: K */
    private TextView f9982K;

    /* renamed from: L */
    private TextView f9983L;

    /* renamed from: M */
    private LinearLayout f9984M;

    /* renamed from: N */
    private ImageView f9985N;

    /* renamed from: O */
    private TextView f9986O;

    /* renamed from: P */
    private LinearLayout f9987P;

    /* renamed from: Q */
    private WindowManager f9988Q;

    /* renamed from: R */
    private ImageView f9989R;

    /* renamed from: S */
    private ProgressBar f9990S;

    /* renamed from: T */
    private LinearLayout f9991T;

    /* renamed from: U */
    private LinearLayout f9992U;

    /* renamed from: V */
    private boolean f9993V = true;

    /* renamed from: W */
    private ImageView f9994W;

    /* renamed from: X */
    private TextView f9995X;

    /* renamed from: Y */
    private TextView f9996Y;

    /* renamed from: Z */
    private TextView f9997Z;

    /* renamed from: a */
    View.OnClickListener f9998a = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.product.AppActivity.View$OnClickListenerC27984 */

        public void onClick(View view) {
            AppActivity.this.f10008aj.dismiss();
            ShareHelper.m10859a(AppActivity.this).mo24541b(AppActivity.this.f9996Y.getText().toString(), AppActivity.this.f10026r.getText().toString(), AppActivity.this.f10005ag, AppActivity.this.f9975D, view.getId());
        }
    };

    /* renamed from: aa */
    private View f9999aa;

    /* renamed from: ab */
    private int f10000ab;

    /* renamed from: ac */
    private Request f10001ac;

    /* renamed from: ad */
    private Request f10002ad;

    /* renamed from: ae */
    private Request f10003ae;

    /* renamed from: af */
    private final int f10004af = 65;

    /* renamed from: ag */
    private String f10005ag = "";

    /* renamed from: ah */
    private boolean f10006ah = false;

    /* renamed from: ai */
    private ImageView f10007ai;

    /* renamed from: aj */
    private PopupWindow f10008aj;

    /* renamed from: ak */
    private BaseAdapter f10009ak = new BaseAdapter() {
        /* class com.gbcom.gwifi.functions.product.AppActivity.C28017 */

        public int getCount() {
            return AppActivity.this.f10032x.size();
        }

        public Object getItem(int i) {
            return AppActivity.this.f10032x.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            App app = (App) AppActivity.this.f10032x.get(i);
            View inflate = LayoutInflater.from(AppActivity.this).inflate(C2425R.layout.app_recommend_item, viewGroup, false);
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.app_recommend_icon);
            TextView textView = (TextView) inflate.findViewById(16908310);
            TextView textView2 = (TextView) inflate.findViewById(16908308);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.download_num);
            if (app.getImageUrl() != null && app.getImageUrl().length() > 5) {
                ImageTools.m14149a(app.getImageUrl(), imageView, GBApplication.instance().options);
            }
            textView.setText(app.getTitle());
            textView2.setText(app.getTags());
            textView3.setText("已下载" + C3467s.m14510c((long) app.getDownloadCount().intValue()));
            return inflate;
        }
    };

    /* renamed from: al */
    private OkRequestHandler<String> f10010al = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.AppActivity.C28028 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            AppActivity.this.showProgressDialog("正在加载…");
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (!AppActivity.this.isFinishing()) {
                GBActivity.showMessageToast("请检查网络");
                Log.m14403e(AppActivity.f9970d, "AppActivity:" + exc.getMessage());
                AppActivity.this.dismissProgressDialog();
                AppActivity.this.finish();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            HashMap<String, Object> s;
            int intValue;
            if (!AppActivity.this.isFinishing()) {
                AppActivity.this.dismissProgressDialog();
                if (abVar == AppActivity.this.f10003ae) {
                    HashMap<String, Object> f = ResourceParse.m14455f(str.getBytes());
                    if (f == null) {
                        AppActivity.this.finish();
                    } else if (!ResultCode.m14475a((Integer) f.get(AbstractC5718b.JSON_ERRORCODE))) {
                        Log.m14403e(AppActivity.f9970d, "AppActivity->onRequestFinish :resultCode " + f.get(AbstractC5718b.JSON_ERRORCODE));
                        AppActivity.this.finish();
                    } else {
                        HashMap hashMap = (HashMap) f.get("data");
                        HashMap hashMap2 = (HashMap) hashMap.get("product");
                        ArrayList arrayList = new ArrayList();
                        HashMap hashMap3 = (HashMap) hashMap.get("other");
                        ArrayList arrayList2 = (ArrayList) hashMap.get("preImages");
                        ArrayList arrayList3 = (ArrayList) hashMap.get("recommends");
                        Iterator it = ((ArrayList) hashMap.get("downloads")).iterator();
                        while (it.hasNext()) {
                            AppActivity.this.f9975D = (String) ((HashMap) it.next()).get("fileUrl");
                        }
                        String str2 = (String) hashMap2.get("imgUrl");
                        if (str2 != null) {
                            AppActivity.this.f10005ag = str2;
                            ImageTools.m14149a(str2, AppActivity.this.f10017i, GBApplication.instance().options);
                        } else {
                            AppActivity.this.f10017i.setVisibility(8);
                        }
                        AppActivity.this.f9996Y.setText((String) hashMap2.get("productName"));
                        AppActivity.this.f9976E = C3467s.m14507a((List<String[]>) ((ArrayList) hashMap2.get("tags")));
                        ArrayList<String[]> b = C3467s.m14509b(AppActivity.this.f9976E);
                        String str3 = "";
                        if (!b.isEmpty()) {
                            str3 = b.get(0)[1];
                        }
                        AppActivity.this.f10019k.setText(str3);
                        Double d = (Double) hashMap2.get("fileTotalSize");
                        AppActivity.this.f9977F = (long) (d.doubleValue() * 1024.0d);
                        AppActivity.this.f10020l.setText(C3467s.m14501a((long) (d.doubleValue() * 1024.0d)));
                        if (((Integer) hashMap2.get(MiStat.Param.PRICE)).intValue() == 0) {
                            AppActivity.this.f10018j.setText("免费");
                        } else {
                            AppActivity.this.f10018j.setText("内容收费");
                        }
                        AppActivity.this.f10026r.setText((String) hashMap2.get("remark"));
                        AppActivity.this.f10021m.setText(C3467s.m14510c((long) ((Integer) hashMap2.get("downloadCount")).intValue()));
                        AppActivity.this.f9979H = (String) hashMap2.get("appPackage");
                        if (arrayList2 != null && arrayList2.size() > 0) {
                            for (int i = 0; i < arrayList2.size(); i++) {
                                ImageView imageView = new ImageView(AppActivity.this);
                                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                imageView.setAdjustViewBounds(true);
                                imageView.setOnClickListener(AppActivity.this);
                                final ImageView imageView2 = new ImageView(AppActivity.this);
                                imageView2.setImageResource(C2425R.C2426drawable.loading_small);
                                AppActivity.imageViews.add(imageView2);
                                imageView2.setTag(0);
                                ImageTools.m14150a((String) arrayList2.get(i), imageView, GBApplication.instance().options, new ImageLoadingListener() {
                                    /* class com.gbcom.gwifi.functions.product.AppActivity.C28028.C28031 */

                                    @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                                    public void onLoadingStarted(String str, View view) {
                                    }

                                    @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                                    public void onLoadingFailed(String str, View view, FailReason failReason) {
                                    }

                                    @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                                        if (bitmap != null) {
                                            bitmap.getHeight();
                                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, (DensityUtil.m14130b(AppActivity.this) / 100) * 47);
                                            layoutParams.setMargins(0, 0, DensityUtil.m14128a(AppActivity.this, 10.0f), 0);
                                            view.setLayoutParams(layoutParams);
                                            imageView2.setImageBitmap(bitmap);
                                            imageView2.setTag(1);
                                            ImagesActivity.notifyImagesData();
                                            Log.m14400c(AppActivity.f9970d, "pageradapter Image complete ..");
                                        }
                                    }

                                    @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                                    public void onLoadingCancelled(String str, View view) {
                                    }
                                });
                                AppActivity.this.f10028t.addView(imageView);
                            }
                        }
                        Iterator it2 = arrayList3.iterator();
                        while (it2.hasNext()) {
                            HashMap hashMap4 = (HashMap) it2.next();
                            App app = new App();
                            app.setTitle((String) hashMap4.get("productName"));
                            app.setSid((Integer) hashMap4.get("productId"));
                            app.setImageUrl((String) hashMap4.get("imgUrl"));
                            app.setDownloadCount((Integer) hashMap4.get("downloadCount"));
                            app.setTags((String) hashMap4.get("tagName"));
                            arrayList.add(app);
                        }
                        AppActivity.this.f10032x = arrayList;
                        AppActivity appActivity = AppActivity.this;
                        if (((Integer) hashMap2.get("appPointsReward")) == null) {
                            intValue = 0;
                        } else {
                            intValue = ((Integer) hashMap2.get("appPointsReward")).intValue();
                        }
                        appActivity.f10000ab = intValue;
                        AppActivity.this.f9997Z.setText(FormatUtil.m14216a(Integer.valueOf(AppActivity.this.f10000ab)));
                        AppActivity.this.f9991T.setVisibility(0);
                        AppActivity.this.f9994W.setVisibility(0);
                        if (!StorageUtils.m14492c(AppActivity.this, AppActivity.this.f9979H)) {
                            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(AppActivity.this, "url", AppActivity.this.f9975D);
                            if (downloadFile != null) {
                                switch (downloadFile.getStateId().intValue()) {
                                    case 0:
                                        AppActivity.this.f9993V = false;
                                        AppActivity.this.f9991T.setVisibility(0);
                                        AppActivity.this.f9992U.setVisibility(8);
                                        AppActivity.this.f9982K.setText("安装");
                                        break;
                                    case 1:
                                        AppActivity.this.f9993V = false;
                                        if (downloadFile.getFileTotalSize().longValue() != 0) {
                                            AppActivity.this.f9983L.setText(((downloadFile.getDownsize().longValue() * 100) / downloadFile.getFileTotalSize().longValue()) + "%");
                                            AppActivity.this.f9990S.setProgress((int) ((downloadFile.getDownsize().longValue() * 100) / downloadFile.getFileTotalSize().longValue()));
                                        }
                                        AppActivity.this.f9990S.setProgressDrawable(AppActivity.this.getResources().getDrawable(C2425R.C2426drawable.gi_progressbar));
                                        AppActivity.this.f9991T.setVisibility(8);
                                        AppActivity.this.f9992U.setVisibility(0);
                                        break;
                                    case 2:
                                        AppActivity.this.f9991T.setVisibility(8);
                                        AppActivity.this.f9992U.setVisibility(0);
                                        AppActivity.this.f9981J.setImageResource(C2425R.C2426drawable.start_download);
                                        if (downloadFile.getFileTotalSize().longValue() != 0) {
                                            AppActivity.this.f9983L.setText(((downloadFile.getDownsize().longValue() * 100) / downloadFile.getFileTotalSize().longValue()) + "%");
                                        }
                                        AppActivity.this.f9990S.setProgressDrawable(AppActivity.this.getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
                                        AppActivity.this.f9983L.setVisibility(0);
                                        AppActivity.this.f9993V = true;
                                        break;
                                }
                            }
                        } else {
                            AppActivity.this.f9993V = false;
                            AppActivity.this.f9982K.setText("打开");
                        }
                        UmengCount.queryPostApp(AppActivity.this.getApplicationContext(), AppActivity.this.f9996Y.getText().toString(), AppActivity.this.f10006ah);
                        AppActivity.this.f9972A = (AttentionFile) AttentionFileDao.m12148b().mo24207b(AppActivity.this, "sid", (Integer) hashMap2.get("productId"));
                        if (AppActivity.this.f9972A != null) {
                            AppActivity.this.f9985N.setImageResource(C2425R.C2426drawable.attention_pressed);
                            AppActivity.this.f9986O.setText("已关注");
                        } else {
                            AppActivity.this.f10033y = new App();
                            AppActivity.this.f10033y.setTitle((String) hashMap2.get("productName"));
                            AppActivity.this.f10033y.setImageUrl((String) hashMap2.get("imgUrl"));
                            if (AppActivity.this.f10033y.getImageUrl() != null) {
                                ImageTools.m14154a(AppActivity.this.f10033y.getImageUrl(), AppActivity.this.f10011am);
                            }
                            AppActivity.this.f10033y.setDownloadCount((Integer) hashMap2.get("downloadCount"));
                            AppActivity.this.f10033y.setSid((Integer) hashMap2.get("productId"));
                            AppActivity.this.f10033y.setFileTotalSize(Long.valueOf((long) (((Double) hashMap2.get("fileTotalSize")).doubleValue() * 1024.0d)));
                            AppActivity.this.f10033y.setAppPointsReward(Integer.valueOf(AppActivity.this.f10000ab));
                            AppActivity.this.f9976E = C3467s.m14507a((List<String[]>) ((ArrayList) hashMap2.get("tags")));
                            AppActivity.this.f10033y.setTags(AppActivity.this.f9976E);
                        }
                        AppActivity.this.f9987P.setVisibility(0);
                    }
                } else if (abVar == AppActivity.this.f10001ac) {
                    HashMap<String, Object> r = ResourceParse.m14467r(str.getBytes());
                    if (r == null) {
                        return;
                    }
                    if (ResultCode.m14475a((Integer) r.get(AbstractC5718b.JSON_ERRORCODE))) {
                        HashMap hashMap5 = (HashMap) r.get("data");
                        if (5 == ((Integer) hashMap5.get("point_change_category")).intValue()) {
                            GBActivity.showMessageToast("已经增加" + ((Object) ((Integer) hashMap5.get("point_value"))) + Constant.f13309cr);
                            return;
                        }
                        return;
                    }
                    GBActivity.showMessageToast((String) r.get("resultMsg"));
                } else if (abVar == AppActivity.this.f10002ad && (s = ResourceParse.m14468s(str.getBytes())) != null && ResultCode.m14475a((Integer) s.get(AbstractC5718b.JSON_ERRORCODE))) {
                    Log.m14400c(AppActivity.f9970d, "下载次数增加成功");
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: am */
    private ImageLoadingListener f10011am = new ImageLoadingListener() {
        /* class com.gbcom.gwifi.functions.product.AppActivity.C28049 */

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingStarted(String str, View view) {
            AppActivity.this.f9984M.setEnabled(false);
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingFailed(String str, View view, FailReason failReason) {
            AppActivity.this.f9984M.setVisibility(4);
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            AppActivity.this.f10034z = bitmap;
            AppActivity.this.f9984M.setEnabled(true);
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: b */
    Handler f10012b = new Handler() {
        /* class com.gbcom.gwifi.functions.product.AppActivity.HandlerC27995 */

        public void handleMessage(Message message) {
            AppActivity.this.m11571h();
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: c */
    Handler f10013c = new Handler() {
        /* class com.gbcom.gwifi.functions.product.AppActivity.HandlerC28006 */

        public void handleMessage(Message message) {
            AppActivity.this.m11564d();
        }
    };

    /* renamed from: f */
    private RelativeLayout f10014f;

    /* renamed from: g */
    private TextView f10015g;

    /* renamed from: h */
    private TextView f10016h;

    /* renamed from: i */
    private ImageView f10017i;

    /* renamed from: j */
    private TextView f10018j;

    /* renamed from: k */
    private TextView f10019k;

    /* renamed from: l */
    private TextView f10020l;

    /* renamed from: m */
    private TextView f10021m;

    /* renamed from: n */
    private TextView f10022n;

    /* renamed from: o */
    private TextView f10023o;

    /* renamed from: p */
    private TextView f10024p;

    /* renamed from: q */
    private LinearLayout f10025q;

    /* renamed from: r */
    private TextView f10026r;

    /* renamed from: s */
    private LinearLayout f10027s;

    /* renamed from: t */
    private LinearLayout f10028t;

    /* renamed from: u */
    private LinearLayout f10029u;

    /* renamed from: v */
    private NoScrollGridView f10030v;

    /* renamed from: w */
    private int f10031w = -1;

    /* renamed from: x */
    private List<App> f10032x = new ArrayList();

    /* renamed from: y */
    private App f10033y;

    /* renamed from: z */
    private Bitmap f10034z;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "应用界面", C2425R.layout.new_app_activity, true);
        m11547a();
        m11557b();
        this.f9978G = new AppInstallReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(C6366a.f23181c);
        registerReceiver(this.f9978G, intentFilter);
        this.f9973B = new DowningReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.filter.download.receiver");
        registerReceiver(this.f9973B, intentFilter2);
    }

    /* renamed from: a */
    private void m11547a() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f9988Q = getWindow().getWindowManager();
        layoutParams.width = -1;
        layoutParams.height = DensityUtil.m14128a(this, 65.0f);
        layoutParams.gravity = 80;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.flags = 8;
        this.f9999aa = getLayoutInflater().inflate(C2425R.layout.app_activity_main, (ViewGroup) null);
        this.f9990S = (ProgressBar) this.f9999aa.findViewById(C2425R.C2427id.app_detail_progress);
        this.f9981J = (ImageView) this.f9999aa.findViewById(C2425R.C2427id.down_iv);
        this.f9981J.setOnClickListener(this);
        this.f9982K = (TextView) this.f9999aa.findViewById(C2425R.C2427id.down_tv);
        this.f9982K.setOnClickListener(this);
        this.f9983L = (TextView) this.f9999aa.findViewById(C2425R.C2427id.progress_tv);
        this.f9985N = (ImageView) this.f9999aa.findViewById(C2425R.C2427id.attention_iv);
        this.f9985N.setOnClickListener(this);
        this.f9989R = (ImageView) this.f9999aa.findViewById(C2425R.C2427id.cancel_download);
        this.f9989R.setOnClickListener(this);
        this.f9991T = (LinearLayout) this.f9999aa.findViewById(C2425R.C2427id.app_down_ly);
        this.f9992U = (LinearLayout) this.f9999aa.findViewById(C2425R.C2427id.app_progress_ly);
        this.f9994W = (ImageView) this.f9999aa.findViewById(C2425R.C2427id.line_view);
        this.f9988Q.addView(this.f9999aa, layoutParams);
    }

    /* renamed from: b */
    private void m11557b() {
        imageViews.clear();
        this.f10031w = getIntent().getIntExtra("productId", -1);
        this.f10006ah = getIntent().getBooleanExtra("isFromWifi", false);
        this.f9987P = (LinearLayout) findViewById(C2425R.C2427id.new_app_layout);
        this.f10014f = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10014f.setOnClickListener(this);
        this.f10016h = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10015g = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10016h.setText("应用详情");
        this.f10015g.setText("");
        this.f10007ai = (ImageView) findViewById(C2425R.C2427id.share_iv);
        this.f10007ai.setVisibility(0);
        this.f10007ai.setOnClickListener(this);
        this.f9980I = (RelativeLayout) findViewById(C2425R.C2427id.down_layout);
        this.f9980I.setOnClickListener(this);
        this.f9984M = (LinearLayout) findViewById(C2425R.C2427id.attention_layout);
        this.f9986O = (TextView) findViewById(C2425R.C2427id.attention_tv);
        this.f9984M.setOnClickListener(this);
        this.f10017i = (ImageView) findViewById(C2425R.C2427id.app_iv);
        this.f10018j = (TextView) findViewById(C2425R.C2427id.app_free);
        this.f10019k = (TextView) findViewById(C2425R.C2427id.app_content);
        this.f10020l = (TextView) findViewById(C2425R.C2427id.app_size);
        this.f10021m = (TextView) findViewById(C2425R.C2427id.app_down_num);
        this.f10022n = (TextView) findViewById(C2425R.C2427id.app_brief_main);
        this.f10023o = (TextView) findViewById(C2425R.C2427id.app_screen_main);
        this.f10024p = (TextView) findViewById(C2425R.C2427id.app_recommend_main);
        this.f10022n.setOnClickListener(this);
        this.f10023o.setOnClickListener(this);
        this.f10024p.setOnClickListener(this);
        this.f10026r = (TextView) findViewById(C2425R.C2427id.app_brief);
        this.f10028t = (LinearLayout) findViewById(C2425R.C2427id.scroll_layout);
        this.f10025q = (LinearLayout) findViewById(C2425R.C2427id.app_brief_include);
        this.f10027s = (LinearLayout) findViewById(C2425R.C2427id.app_screen_include);
        this.f10029u = (LinearLayout) findViewById(C2425R.C2427id.recommend_include);
        this.f10030v = (NoScrollGridView) findViewById(C2425R.C2427id.recommend_gv);
        this.f10030v.setAdapter((ListAdapter) this.f10009ak);
        this.f10030v.setOnItemClickListener(this);
        this.f9995X = (TextView) findViewById(C2425R.C2427id.app_attention_num);
        this.f9996Y = (TextView) findViewById(C2425R.C2427id.app_title);
        this.f9997Z = (TextView) findViewById(C2425R.C2427id.app_item_add_score);
        if (this.f10031w != -1) {
            this.f10003ae = HttpUtil.m14292b(this, Constant.f13160O, this.f10031w, this.f10010al, f9971e);
        }
        m11548a(1);
    }

    /* renamed from: a */
    private void m11548a(int i) {
        this.f10022n.setTextColor(getResources().getColor(C2425R.color.grey_3));
        this.f10023o.setTextColor(getResources().getColor(C2425R.color.grey_3));
        this.f10024p.setTextColor(getResources().getColor(C2425R.color.grey_3));
        this.f10025q.setVisibility(8);
        this.f10027s.setVisibility(8);
        this.f10029u.setVisibility(8);
        switch (i) {
            case 0:
                this.f10022n.setTextColor(getResources().getColor(C2425R.color.red));
                this.f10025q.setVisibility(0);
                return;
            case 1:
                this.f10023o.setTextColor(getResources().getColor(C2425R.color.red));
                this.f10027s.setVisibility(0);
                return;
            case 2:
                this.f10024p.setTextColor(getResources().getColor(C2425R.color.red));
                this.f10029u.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.down_tv /*{ENCODED_INT: 2131755211}*/:
                m11550a(view);
                return;
            case C2425R.C2427id.attention_iv /*{ENCODED_INT: 2131755212}*/:
                m11568f();
                return;
            case C2425R.C2427id.down_iv /*{ENCODED_INT: 2131755214}*/:
                if (this.f9993V) {
                    m11569g();
                    return;
                }
                this.f10012b.removeMessages(7);
                this.f10012b.sendMessageDelayed(this.f10012b.obtainMessage(7), 300);
                return;
            case C2425R.C2427id.cancel_download /*{ENCODED_INT: 2131755217}*/:
                m11569g();
                showNormalDialog("下载任务将被取消", "确认取消下载?", "确定", "取消", new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.product.AppActivity.C27951 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        AppActivity.this.m11576j();
                        dialog.dismiss();
                    }
                }, new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.product.AppActivity.C27962 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                    }
                });
                return;
            case C2425R.C2427id.down_layout /*{ENCODED_INT: 2131755356}*/:
                m11550a(view);
                return;
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.share_iv /*{ENCODED_INT: 2131755763}*/:
                m11561c();
                return;
            case C2425R.C2427id.attention_layout /*{ENCODED_INT: 2131755796}*/:
                m11568f();
                return;
            case C2425R.C2427id.app_brief_main /*{ENCODED_INT: 2131755798}*/:
                m11548a(0);
                return;
            case C2425R.C2427id.app_screen_main /*{ENCODED_INT: 2131755799}*/:
                m11548a(1);
                return;
            case C2425R.C2427id.app_recommend_main /*{ENCODED_INT: 2131755800}*/:
                m11548a(2);
                return;
            default:
                Intent intent = new Intent(this, ImagesActivity.class);
                intent.putExtra("activity", "appActivity");
                startActivity(intent);
                return;
        }
    }

    /* renamed from: c */
    private void m11561c() {
        if (this.f10008aj == null) {
            ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(C2425R.layout.share_menu, (ViewGroup) null, false);
            ((LinearLayout) viewGroup.findViewById(C2425R.C2427id.wechat_layout)).setOnClickListener(this.f9998a);
            ((LinearLayout) viewGroup.findViewById(C2425R.C2427id.wxcircle_layout)).setOnClickListener(this.f9998a);
            ((LinearLayout) viewGroup.findViewById(C2425R.C2427id.qzone_layout)).setOnClickListener(this.f9998a);
            ((LinearLayout) viewGroup.findViewById(C2425R.C2427id.sina_layout)).setOnClickListener(this.f9998a);
            ((LinearLayout) viewGroup.findViewById(C2425R.C2427id.qq_layout)).setOnClickListener(this.f9998a);
            this.f10008aj = new PopupWindow((View) viewGroup, -1, -1, true);
            this.f10008aj.setTouchable(true);
            viewGroup.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.product.AppActivity.View$OnClickListenerC27973 */

                public void onClick(View view) {
                    AppActivity.this.f10008aj.dismiss();
                }
            });
            this.f10008aj.setBackgroundDrawable(new BitmapDrawable());
            this.f10008aj.showAsDropDown(findViewById(C2425R.C2427id.share_iv), 0, 10);
            this.f10008aj.update();
        } else if (this.f10008aj.isShowing()) {
            this.f10008aj.dismiss();
            this.f10008aj = null;
        } else {
            this.f10008aj.showAsDropDown(findViewById(C2425R.C2427id.share_iv), 0, 10);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m11564d() {
        if (this.f9982K.getText().equals("打开")) {
            StorageUtils.m14488b(this, this.f9979H);
            return;
        }
        DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", this.f9975D);
        if (downloadFile == null) {
            this.f9981J.setImageResource(C2425R.C2426drawable.start_download);
            this.f9982K.setText("下载");
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (this.f9982K.getText().equals("安装")) {
            if (new File(downloadFile.getLocalFile()).exists()) {
                StorageUtils.m14482a(this, downloadFile.getLocalFile());
                return;
            }
            this.f9981J.setImageResource(C2425R.C2426drawable.start_download);
            this.f9982K.setText("下载");
            DownloadFileDao.m12152b().delete(this, downloadFile);
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (this.f9982K.getText().equals("打开")) {
            StorageUtils.m14488b(this, downloadFile.getPackageName());
        }
    }

    /* renamed from: a */
    private void m11550a(View view) {
        if (this.f9982K.getVisibility() != 0 || !this.f9982K.getText().toString().equals("下载")) {
            if (this.f9982K.getVisibility() != 0 || (!this.f9982K.getText().toString().equals("安装") && !this.f9982K.getText().toString().equals("打开"))) {
                if (this.f9983L.getVisibility() == 0) {
                }
                return;
            }
            this.f10013c.removeMessages(6);
            this.f10013c.sendMessageDelayed(this.f10013c.obtainMessage(6), 200);
        } else if (!this.f9975D.equals("")) {
            if (!StorageUtils.m14496e()) {
                GBActivity.showMessageToast("未发现SD卡");
            } else if (!StorageUtils.m14484a()) {
                GBActivity.showMessageToast("SD卡不能读写");
            } else {
                try {
                    StorageUtils.m14497f();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                m11566e();
                Intent intent = new Intent("com.action.download.download_service");
                intent.putExtra("type", 6);
                intent.putExtra("url", this.f9975D);
                startService(intent);
                this.f9991T.setVisibility(8);
                this.f9992U.setVisibility(0);
            }
        }
    }

    /* renamed from: e */
    private void m11566e() {
        if (((DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", this.f9975D)) != null) {
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 4);
            intent.putExtra("url", this.f9975D);
            startService(intent);
        }
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setUrl(this.f9975D);
        downloadFile.setImageUrl(this.f10005ag);
        downloadFile.setName(this.f9996Y.getText().toString());
        downloadFile.setTags(this.f9976E);
        downloadFile.setFileTotalSize(Long.valueOf(this.f9977F));
        downloadFile.setDownsize(0L);
        downloadFile.setCreateTime(Long.valueOf(System.currentTimeMillis()));
        downloadFile.setProductId(Integer.valueOf(this.f10031w));
        downloadFile.setProductType(Constant.f13160O);
        downloadFile.setStateId(2);
        downloadFile.setPackageName(this.f9979H);
        downloadFile.setAppPointsReward(Integer.valueOf(this.f10000ab));
        DownloadFileDao.m12152b().mo24215e((Context) this, downloadFile);
    }

    /* renamed from: f */
    private void m11568f() {
        if (this.f9986O.getText().toString().equals("已关注")) {
            showProgressDialog("正在取消关注请稍等…");
            AttentionFileDao.m12148b().delete(this, this.f9972A);
            dismissProgressDialog();
            this.f9985N.setImageResource(C2425R.C2426drawable.attention_count_large);
            this.f9986O.setText("关注");
            showMessageToast("正在取消关注...");
            return;
        }
        AttentionFile attentionFile = new AttentionFile();
        if (this.f10033y != null) {
            attentionFile.setTitle(this.f10033y.getTitle());
            attentionFile.setTags(this.f10033y.getTags());
            attentionFile.setSid(this.f10033y.getSid());
            attentionFile.setProductType(Constant.f13160O);
            attentionFile.setId(this.f10033y.getId());
            attentionFile.setAppPointsReward(this.f10033y.getAppPointsReward());
            attentionFile.setFileTotalSize(this.f10033y.getFileTotalSize());
            attentionFile.setDownloadCount(this.f10033y.getDownloadCount());
            attentionFile.setCacheIcon(ImageTools.m14160b(this.f10034z));
            this.f9972A = attentionFile;
        }
        AttentionFileDao.m12148b().mo24215e((Context) this, this.f9972A);
        this.f9986O.setText("已关注");
        this.f9985N.setImageResource(C2425R.C2426drawable.attention_pressed);
        showMessageToast("已经关注...");
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Intent intent = new Intent(this, AppActivity.class);
        intent.putExtra("productId", this.f10032x.get(i).getSid());
        startActivity(intent);
        finish();
    }

    public class AppInstallReceiver extends BroadcastReceiver {
        public AppInstallReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            StorageUtils.m14481a(GBApplication.instance());
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") && schemeSpecificPart.equals(AppActivity.this.f9979H)) {
                UmengCount.queryAppInstallCount(context, AppActivity.this.f9996Y.getText().toString());
                AppActivity.this.f10001ac = HttpUtil.m14262a(AppActivity.this, Constant.f13160O, Integer.valueOf(AppActivity.this.f10031w), AppActivity.this.f9996Y.getText().toString(), Integer.valueOf(AppActivity.this.f10000ab), AppActivity.this.f10010al, AppActivity.this.f9989R);
                AppActivity.this.f9982K.setText("打开");
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(context, "url", AppActivity.this.f9975D);
                if (downloadFile == null || downloadFile.getLocalFile() == null || downloadFile.getStateId().intValue() != 0 || !new File(downloadFile.getLocalFile()).exists()) {
                    AppActivity.this.f9981J.setImageResource(C2425R.C2426drawable.start_download);
                    AppActivity.this.f9981J.setVisibility(0);
                    AppActivity.this.f9982K.setText("下载");
                    AppActivity.this.f9982K.setVisibility(0);
                    return;
                }
                AppActivity.this.f9982K.setText("安装");
            }
        }
    }

    public class DowningReceiver extends BroadcastReceiver {
        public DowningReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            m11594a(intent);
        }

        /* renamed from: a */
        private void m11594a(Intent intent) {
            if (intent != null && intent.getAction().equals("com.filter.download.receiver")) {
                switch (intent.getIntExtra("type", -1)) {
                    case 0:
                        String stringExtra = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals(AppActivity.this.f9975D)) {
                            AppActivity.this.dealProcessTask(intent);
                            return;
                        }
                        return;
                    case 1:
                        String stringExtra2 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra2) && stringExtra2.equals(AppActivity.this.f9975D)) {
                            AppActivity.this.dealCompleteTask();
                            return;
                        }
                        return;
                    case 2:
                    case 4:
                    case 7:
                    case 8:
                    default:
                        return;
                    case 3:
                        String stringExtra3 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra3) && stringExtra3.equals(AppActivity.this.f9975D)) {
                            AppActivity.this.m11549a((AppActivity) intent);
                            return;
                        }
                        return;
                    case 5:
                        String stringExtra4 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra4) && stringExtra4.equals(AppActivity.this.f9975D)) {
                            AppActivity.this.dealContinueTask();
                            return;
                        }
                        return;
                    case 6:
                        String stringExtra5 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra5) && stringExtra5.equals(AppActivity.this.f9975D)) {
                            AppActivity.this.dealAddTask(stringExtra5);
                            return;
                        }
                        return;
                    case 9:
                        intent.getStringExtra("url");
                        return;
                    case 10:
                        String stringExtra6 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra6) && stringExtra6.equals(AppActivity.this.f9975D)) {
                            AppActivity.this.m11574i();
                            return;
                        }
                        return;
                }
            }
        }
    }

    /* renamed from: g */
    private void m11569g() {
        Intent intent = new Intent("com.action.download.download_service");
        intent.putExtra("type", 3);
        intent.putExtra("url", this.f9975D);
        startService(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: h */
    private void m11571h() {
        Intent intent = new Intent("com.action.download.download_service");
        intent.putExtra("type", 5);
        intent.putExtra("url", this.f9975D);
        startService(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11549a(Intent intent) {
        dealProcessTask(intent);
        this.f9981J.setImageResource(C2425R.C2426drawable.pause_download);
        this.f9993V = false;
        this.f9990S.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar));
        this.f9982K.setVisibility(0);
    }

    public void dealContinueTask() {
        this.f9981J.setImageResource(C2425R.C2426drawable.start_download);
        this.f9983L.setVisibility(0);
        this.f9993V = true;
        this.f9990S.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: i */
    private void m11574i() {
        this.f9983L.setText("0%");
        this.f9990S.setProgress(0);
        this.f9993V = false;
        this.f9981J.setImageResource(C2425R.C2426drawable.start_download);
        this.f9981J.setVisibility(0);
        this.f9982K.setText("下载");
        this.f9982K.setVisibility(0);
        this.f9990S.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
    }

    public void dealCompleteTask() {
        this.f10002ad = HttpUtil.m14273a(Constant.f13160O, Integer.valueOf(this.f10031w), this.f10010al, f9971e);
        this.f9991T.setVisibility(0);
        this.f9992U.setVisibility(8);
        this.f9993V = false;
        this.f9982K.setVisibility(0);
        this.f9982K.setText("安装");
        this.f9990S.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
        DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", this.f9975D);
        if (downloadFile != null && StorageUtils.m14492c(this, downloadFile.getPackageName())) {
            this.f9982K.setText("打开");
        }
    }

    public void dealAddTask(String str) {
        this.f9981J.setImageResource(C2425R.C2426drawable.start_download);
        this.f9983L.setVisibility(0);
        this.f9983L.setText("0%");
        this.f9993V = true;
        this.f9990S.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
    }

    public void dealProcessTask(Intent intent) {
        if (TextUtils.isEmpty(intent.getStringExtra(MyIntents.f9255c))) {
            this.f9983L.setText("0%");
            return;
        }
        this.f9990S.setProgress(Integer.parseInt(intent.getStringExtra(MyIntents.f9255c)));
        this.f9983L.setText(intent.getStringExtra(MyIntents.f9255c) + "%");
    }

    /* renamed from: b */
    private void m11558b(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (!this.f9975D.equals("")) {
            this.f9974C = !this.f9974C;
            if (this.f9974C) {
                intent.putExtra("type", 5);
                intent.putExtra("url", this.f9975D);
                startService(intent);
                return;
            }
            intent.putExtra("type", 3);
            intent.putExtra("url", this.f9975D);
            startService(intent);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        new GiwifiShareApi(this).onActivityResult(i, i2, intent);
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        unregisterReceiver(this.f9978G);
        unregisterReceiver(this.f9973B);
        this.f9988Q.removeViewImmediate(this.f9999aa);
        imageViews.clear();
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: j */
    private void m11576j() {
        List<DownloadFile> a = DownloadFileDao.m12152b().mo24203a((Context) this, "url", (Object) this.f9975D);
        DownloadFileDao.m12152b().delete((Context) this, (Collection) a);
        Intent intent = new Intent("com.action.download.download_service");
        for (int i = 0; i < a.size(); i++) {
            intent.putExtra("type", 4);
            intent.putExtra("url", a.get(i).getUrl());
            startService(intent);
        }
        m11553a(a);
        a.clear();
        this.f9992U.setVisibility(8);
        this.f9991T.setVisibility(0);
    }

    /* renamed from: a */
    private void m11553a(List<DownloadFile> list) {
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i).getUrl();
        }
        Intent intent = new Intent("com.filter.resumeIcon.receiver");
        intent.putExtra("deleteUrls", strArr);
        sendBroadcast(intent);
    }
}
