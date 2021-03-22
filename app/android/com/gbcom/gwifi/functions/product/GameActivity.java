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
import com.gbcom.gwifi.functions.product.domain.AttentionFile;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.domain.Game;
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
import com.umeng.socialize.net.dplus.CommonNetImpl;
import com.xiaomi.stat.MiStat;
import java.p456io.File;
import java.p456io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import p041c.Request;

public class GameActivity extends GBActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final int INSTALL_OPEN_ANIM = 6;
    public static final int P2C_ANIM = 7;

    /* renamed from: d */
    private static final String f10313d = "GBActivity";

    /* renamed from: e */
    private static final Object f10314e = "GameActivity";
    public static List<View> imageViews = new ArrayList();

    /* renamed from: A */
    private AttentionFile f10315A;

    /* renamed from: B */
    private DowningReceiver f10316B;

    /* renamed from: C */
    private boolean f10317C = false;

    /* renamed from: D */
    private int f10318D = 0;

    /* renamed from: E */
    private String f10319E = "";

    /* renamed from: F */
    private String f10320F = "";

    /* renamed from: G */
    private String f10321G = "";

    /* renamed from: H */
    private long f10322H = 0;

    /* renamed from: I */
    private AppInstallReceiver f10323I;

    /* renamed from: J */
    private String f10324J = "";

    /* renamed from: K */
    private RelativeLayout f10325K;

    /* renamed from: L */
    private ImageView f10326L;

    /* renamed from: M */
    private TextView f10327M;

    /* renamed from: N */
    private TextView f10328N;

    /* renamed from: O */
    private LinearLayout f10329O;

    /* renamed from: P */
    private ImageView f10330P;

    /* renamed from: Q */
    private TextView f10331Q;

    /* renamed from: R */
    private LinearLayout f10332R;

    /* renamed from: S */
    private Boolean f10333S = false;

    /* renamed from: T */
    private WindowManager f10334T;

    /* renamed from: U */
    private ImageView f10335U;

    /* renamed from: V */
    private ProgressBar f10336V;

    /* renamed from: W */
    private LinearLayout f10337W;

    /* renamed from: X */
    private LinearLayout f10338X;

    /* renamed from: Y */
    private boolean f10339Y = true;

    /* renamed from: Z */
    private ImageView f10340Z;

    /* renamed from: a */
    View.OnClickListener f10341a = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.product.GameActivity.View$OnClickListenerC28604 */

        public void onClick(View view) {
            GameActivity.this.f10354am.dismiss();
            ShareHelper.m10859a(GameActivity.this).mo24541b(GameActivity.this.f10343ab.getText().toString(), GameActivity.this.f10372r.getText().toString(), GameActivity.this.f10351aj, C3467s.m14513e(GameActivity.this.f10319E) ? GameActivity.this.f10320F : GameActivity.this.f10319E, view.getId());
        }
    };

    /* renamed from: aa */
    private TextView f10342aa;

    /* renamed from: ab */
    private TextView f10343ab;

    /* renamed from: ac */
    private TextView f10344ac;

    /* renamed from: ad */
    private View f10345ad;

    /* renamed from: ae */
    private int f10346ae;

    /* renamed from: af */
    private Request f10347af;

    /* renamed from: ag */
    private Request f10348ag;

    /* renamed from: ah */
    private Request f10349ah;

    /* renamed from: ai */
    private final int f10350ai = 65;

    /* renamed from: aj */
    private String f10351aj = "";

    /* renamed from: ak */
    private boolean f10352ak = false;

    /* renamed from: al */
    private ImageView f10353al;

    /* renamed from: am */
    private PopupWindow f10354am;

    /* renamed from: an */
    private BaseAdapter f10355an = new BaseAdapter() {
        /* class com.gbcom.gwifi.functions.product.GameActivity.C28637 */

        public int getCount() {
            return GameActivity.this.f10378x.size();
        }

        public Object getItem(int i) {
            return GameActivity.this.f10378x.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Game game = (Game) GameActivity.this.f10378x.get(i);
            View inflate = LayoutInflater.from(GameActivity.this).inflate(C2425R.layout.app_recommend_item, viewGroup, false);
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.app_recommend_icon);
            TextView textView = (TextView) inflate.findViewById(16908310);
            TextView textView2 = (TextView) inflate.findViewById(16908308);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.download_num);
            if (game.getImageUrl() != null && game.getImageUrl().length() > 5) {
                ImageTools.m14149a(game.getImageUrl(), imageView, GBApplication.instance().options);
            }
            textView.setText(game.getTitle());
            textView2.setText(game.getTags());
            textView3.setText("已下载" + C3467s.m14510c((long) game.getDownloadCount().intValue()));
            return inflate;
        }
    };

    /* renamed from: ao */
    private OkRequestHandler<String> f10356ao = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.GameActivity.C28648 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            GameActivity.this.showProgressDialog("正在加载…");
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (!GameActivity.this.isFinishing()) {
                GBActivity.showMessageToast("请检查网络");
                GameActivity.this.dismissProgressDialog();
                GameActivity.this.finish();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            HashMap<String, Object> s;
            int intValue;
            if (!GameActivity.this.isFinishing()) {
                GameActivity.this.dismissProgressDialog();
                if (abVar == GameActivity.this.f10347af) {
                    HashMap<String, Object> f = ResourceParse.m14455f(str.getBytes());
                    if (f == null) {
                        GBActivity.showMessageToast("抱歉,数据解析失败！");
                        GameActivity.this.finish();
                    } else if (!ResultCode.m14475a((Integer) f.get(AbstractC5718b.JSON_ERRORCODE))) {
                        GBActivity.showMessageToast("亲！返回码错误！");
                        GameActivity.this.finish();
                    } else {
                        HashMap hashMap = (HashMap) f.get("data");
                        HashMap hashMap2 = (HashMap) hashMap.get("product");
                        ArrayList arrayList = new ArrayList();
                        HashMap hashMap3 = (HashMap) hashMap.get("other");
                        ArrayList arrayList2 = (ArrayList) hashMap.get("preImages");
                        ArrayList arrayList3 = (ArrayList) hashMap.get("recommends");
                        Iterator it = ((ArrayList) hashMap.get("downloads")).iterator();
                        while (it.hasNext()) {
                            HashMap hashMap4 = (HashMap) it.next();
                            GameActivity.this.f10320F = (String) hashMap4.get("fileUrl");
                            GameActivity.this.f10318D = ((Integer) hashMap4.get("isWap")).intValue();
                            if (hashMap4.containsKey("wapUrl")) {
                                GameActivity.this.f10319E = (String) hashMap4.get("wapUrl");
                            }
                        }
                        String str2 = (String) hashMap2.get("imgUrl");
                        if (str2 != null) {
                            GameActivity.this.f10351aj = str2;
                            ImageTools.m14149a(str2, GameActivity.this.f10363i, GBApplication.instance().options);
                        } else {
                            GameActivity.this.f10363i.setVisibility(8);
                        }
                        GameActivity.this.f10343ab.setText((String) hashMap2.get("productName"));
                        GameActivity.this.f10321G = C3467s.m14507a((List<String[]>) ((ArrayList) hashMap2.get("tags")));
                        ArrayList<String[]> b = C3467s.m14509b(GameActivity.this.f10321G);
                        String str3 = "";
                        if (!b.isEmpty()) {
                            str3 = b.get(0)[1];
                        }
                        GameActivity.this.f10365k.setText(str3);
                        Double d = (Double) hashMap2.get("fileTotalSize");
                        GameActivity.this.f10322H = (long) (d.doubleValue() * 1024.0d);
                        GameActivity.this.f10366l.setText(C3467s.m14501a((long) (d.doubleValue() * 1024.0d)));
                        if (((Integer) hashMap2.get(MiStat.Param.PRICE)).intValue() == 0) {
                            GameActivity.this.f10364j.setText("免费");
                        } else {
                            GameActivity.this.f10364j.setText("内容收费");
                        }
                        GameActivity.this.f10372r.setText((String) hashMap2.get("remark"));
                        GameActivity.this.f10367m.setText(C3467s.m14510c((long) ((Integer) hashMap2.get("downloadCount")).intValue()));
                        GameActivity.this.f10324J = (String) hashMap2.get("appPackage");
                        Log.m14400c(CommonNetImpl.TAG, GameActivity.this.f10324J);
                        if (arrayList2 != null && arrayList2.size() > 0) {
                            for (int i = 0; i < arrayList2.size(); i++) {
                                ImageView imageView = new ImageView(GameActivity.this);
                                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                imageView.setAdjustViewBounds(true);
                                imageView.setOnClickListener(GameActivity.this);
                                final ImageView imageView2 = new ImageView(GameActivity.this);
                                imageView2.setImageResource(C2425R.C2426drawable.loading_small);
                                GameActivity.imageViews.add(imageView2);
                                imageView2.setTag(0);
                                ImageTools.m14150a((String) arrayList2.get(i), imageView, GBApplication.instance().options, new ImageLoadingListener() {
                                    /* class com.gbcom.gwifi.functions.product.GameActivity.C28648.C28651 */

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
                                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, (DensityUtil.m14130b(GameActivity.this) / 100) * 47);
                                            layoutParams.setMargins(0, 0, DensityUtil.m14128a(GameActivity.this, 10.0f), 0);
                                            view.setLayoutParams(layoutParams);
                                            imageView2.setImageBitmap(bitmap);
                                            imageView2.setTag(1);
                                            ImagesActivity.notifyImagesData();
                                        }
                                    }

                                    @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                                    public void onLoadingCancelled(String str, View view) {
                                    }
                                });
                                GameActivity.this.f10374t.addView(imageView);
                            }
                        }
                        Iterator it2 = arrayList3.iterator();
                        while (it2.hasNext()) {
                            HashMap hashMap5 = (HashMap) it2.next();
                            Game game = new Game();
                            game.setTitle((String) hashMap5.get("productName"));
                            game.setSid((Integer) hashMap5.get("productId"));
                            game.setImageUrl((String) hashMap5.get("imgUrl"));
                            game.setDownloadCount((Integer) hashMap5.get("downloadCount"));
                            game.setTags((String) hashMap5.get("tagName"));
                            arrayList.add(game);
                        }
                        GameActivity.this.f10378x = arrayList;
                        GameActivity gameActivity = GameActivity.this;
                        if (((Integer) hashMap2.get("appPointsReward")) == null) {
                            intValue = 0;
                        } else {
                            intValue = ((Integer) hashMap2.get("appPointsReward")).intValue();
                        }
                        gameActivity.f10346ae = intValue;
                        GameActivity.this.f10344ac.setText(FormatUtil.m14216a(Integer.valueOf(GameActivity.this.f10346ae)));
                        GameActivity.this.f10337W.setVisibility(0);
                        GameActivity.this.f10340Z.setVisibility(0);
                        if (!StorageUtils.m14492c(GameActivity.this, GameActivity.this.f10324J)) {
                            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(GameActivity.this, "url", GameActivity.this.f10320F);
                            if (downloadFile != null) {
                                switch (downloadFile.getStateId().intValue()) {
                                    case 0:
                                        GameActivity.this.f10339Y = false;
                                        GameActivity.this.f10337W.setVisibility(0);
                                        GameActivity.this.f10338X.setVisibility(8);
                                        GameActivity.this.f10327M.setText("安装");
                                        break;
                                    case 1:
                                        GameActivity.this.f10339Y = false;
                                        if (downloadFile.getFileTotalSize().longValue() != 0) {
                                            GameActivity.this.f10328N.setText(((downloadFile.getDownsize().longValue() * 100) / downloadFile.getFileTotalSize().longValue()) + "%");
                                            GameActivity.this.f10336V.setProgress((int) ((downloadFile.getDownsize().longValue() * 100) / downloadFile.getFileTotalSize().longValue()));
                                        }
                                        GameActivity.this.f10336V.setProgressDrawable(GameActivity.this.getResources().getDrawable(C2425R.C2426drawable.gi_progressbar));
                                        GameActivity.this.f10337W.setVisibility(8);
                                        GameActivity.this.f10338X.setVisibility(0);
                                        break;
                                    case 2:
                                        GameActivity.this.f10337W.setVisibility(8);
                                        GameActivity.this.f10338X.setVisibility(0);
                                        GameActivity.this.f10326L.setImageResource(C2425R.C2426drawable.start_download);
                                        if (downloadFile.getFileTotalSize().longValue() != 0) {
                                            GameActivity.this.f10328N.setText(((downloadFile.getDownsize().longValue() * 100) / downloadFile.getFileTotalSize().longValue()) + "%");
                                        }
                                        GameActivity.this.f10336V.setProgressDrawable(GameActivity.this.getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
                                        GameActivity.this.f10328N.setVisibility(0);
                                        GameActivity.this.f10339Y = true;
                                        break;
                                }
                            }
                        } else {
                            GameActivity.this.f10339Y = false;
                            GameActivity.this.f10327M.setText("打开");
                        }
                        UmengCount.queryPostGame(GameActivity.this.getApplicationContext(), GameActivity.this.f10343ab.getText().toString(), GameActivity.this.f10352ak);
                        GameActivity.this.f10315A = (AttentionFile) AttentionFileDao.m12148b().mo24207b(GameActivity.this, "sid", (Integer) hashMap2.get("productId"));
                        if (GameActivity.this.f10315A != null) {
                            GameActivity.this.f10330P.setImageResource(C2425R.C2426drawable.attention_pressed);
                            GameActivity.this.f10331Q.setText("已关注");
                        } else {
                            GameActivity.this.f10379y = new Game();
                            GameActivity.this.f10379y.setTitle((String) hashMap2.get("productName"));
                            GameActivity.this.f10379y.setImageUrl((String) hashMap2.get("imgUrl"));
                            if (GameActivity.this.f10379y.getImageUrl() != null) {
                                ImageTools.m14154a(GameActivity.this.f10379y.getImageUrl(), GameActivity.this.f10357ap);
                            }
                            GameActivity.this.f10379y.setDownloadCount((Integer) hashMap2.get("downloadCount"));
                            GameActivity.this.f10379y.setSid((Integer) hashMap2.get("productId"));
                            GameActivity.this.f10379y.setFileTotalSize(Long.valueOf((long) (((Double) hashMap2.get("fileTotalSize")).doubleValue() * 1024.0d)));
                            GameActivity.this.f10379y.setAppPointsReward(Integer.valueOf(GameActivity.this.f10346ae));
                            GameActivity.this.f10379y.setTags(C3467s.m14507a((List<String[]>) ((ArrayList) hashMap2.get("tags"))));
                        }
                        GameActivity.this.f10332R.setVisibility(0);
                        if (GameActivity.this.f10318D == 1) {
                            GameActivity.this.f10327M.setText("打开");
                            return;
                        }
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction("com.filter.download.receiver");
                        GameActivity.this.registerReceiver(GameActivity.this.f10316B, intentFilter);
                        GameActivity.this.f10333S = true;
                    }
                } else if (abVar == GameActivity.this.f10348ag) {
                    HashMap<String, Object> r = ResourceParse.m14467r(str.getBytes());
                    if (r == null) {
                        return;
                    }
                    if (ResultCode.m14475a((Integer) r.get(AbstractC5718b.JSON_ERRORCODE))) {
                        HashMap hashMap6 = (HashMap) r.get("data");
                        if (5 == ((Integer) hashMap6.get("point_change_category")).intValue()) {
                            GBActivity.showMessageToast("已经增加" + ((Object) ((Integer) hashMap6.get("point_value"))) + Constant.f13309cr);
                            return;
                        }
                        return;
                    }
                    GBActivity.showMessageToast((String) r.get("resultMsg"));
                } else if (abVar == GameActivity.this.f10349ah && (s = ResourceParse.m14468s(str.getBytes())) != null && ResultCode.m14475a((Integer) s.get(AbstractC5718b.JSON_ERRORCODE))) {
                    Log.m14400c(GameActivity.f10313d, "下载次数增加成功");
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: ap */
    private ImageLoadingListener f10357ap = new ImageLoadingListener() {
        /* class com.gbcom.gwifi.functions.product.GameActivity.C28669 */

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingStarted(String str, View view) {
            GameActivity.this.f10329O.setEnabled(false);
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingFailed(String str, View view, FailReason failReason) {
            GameActivity.this.f10329O.setVisibility(4);
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            GameActivity.this.f10380z = bitmap;
            GameActivity.this.f10329O.setEnabled(true);
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: b */
    Handler f10358b = new Handler() {
        /* class com.gbcom.gwifi.functions.product.GameActivity.HandlerC28615 */

        public void handleMessage(Message message) {
            GameActivity.this.m11828i();
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: c */
    Handler f10359c = new Handler() {
        /* class com.gbcom.gwifi.functions.product.GameActivity.HandlerC28626 */

        public void handleMessage(Message message) {
            GameActivity.this.m11821e();
        }
    };

    /* renamed from: f */
    private RelativeLayout f10360f;

    /* renamed from: g */
    private TextView f10361g;

    /* renamed from: h */
    private TextView f10362h;

    /* renamed from: i */
    private ImageView f10363i;

    /* renamed from: j */
    private TextView f10364j;

    /* renamed from: k */
    private TextView f10365k;

    /* renamed from: l */
    private TextView f10366l;

    /* renamed from: m */
    private TextView f10367m;

    /* renamed from: n */
    private TextView f10368n;

    /* renamed from: o */
    private TextView f10369o;

    /* renamed from: p */
    private TextView f10370p;

    /* renamed from: q */
    private LinearLayout f10371q;

    /* renamed from: r */
    private TextView f10372r;

    /* renamed from: s */
    private LinearLayout f10373s;

    /* renamed from: t */
    private LinearLayout f10374t;

    /* renamed from: u */
    private LinearLayout f10375u;

    /* renamed from: v */
    private NoScrollGridView f10376v;

    /* renamed from: w */
    private int f10377w = -1;

    /* renamed from: x */
    private List<Game> f10378x = new ArrayList();

    /* renamed from: y */
    private Game f10379y;

    /* renamed from: z */
    private Bitmap f10380z;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "游戏界面", C2425R.layout.new_game_activity, true);
        m11811b();
        m11815c();
        this.f10323I = new AppInstallReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(C6366a.f23181c);
        registerReceiver(this.f10323I, intentFilter);
        this.f10316B = new DowningReceiver();
    }

    /* renamed from: b */
    private void m11811b() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f10334T = getWindow().getWindowManager();
        layoutParams.width = -1;
        layoutParams.height = DensityUtil.m14128a(this, 65.0f);
        layoutParams.gravity = 80;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.flags = 8;
        this.f10345ad = getLayoutInflater().inflate(C2425R.layout.game_window, (ViewGroup) null);
        this.f10336V = (ProgressBar) this.f10345ad.findViewById(C2425R.C2427id.game_detail_progress);
        this.f10326L = (ImageView) this.f10345ad.findViewById(C2425R.C2427id.down_iv);
        this.f10326L.setOnClickListener(this);
        this.f10327M = (TextView) this.f10345ad.findViewById(C2425R.C2427id.down_tv);
        this.f10327M.setOnClickListener(this);
        this.f10328N = (TextView) this.f10345ad.findViewById(C2425R.C2427id.progress_tv);
        this.f10330P = (ImageView) this.f10345ad.findViewById(C2425R.C2427id.attention_iv);
        this.f10330P.setOnClickListener(this);
        this.f10335U = (ImageView) this.f10345ad.findViewById(C2425R.C2427id.cancel_download);
        this.f10335U.setOnClickListener(this);
        this.f10337W = (LinearLayout) this.f10345ad.findViewById(C2425R.C2427id.game_down_ly);
        this.f10338X = (LinearLayout) this.f10345ad.findViewById(C2425R.C2427id.game_progress_ly);
        this.f10340Z = (ImageView) this.f10345ad.findViewById(C2425R.C2427id.line_view);
        this.f10334T.addView(this.f10345ad, layoutParams);
    }

    /* renamed from: c */
    private void m11815c() {
        imageViews.clear();
        this.f10377w = getIntent().getIntExtra("productId", -1);
        this.f10352ak = getIntent().getBooleanExtra("isFromWifi", false);
        this.f10332R = (LinearLayout) findViewById(C2425R.C2427id.new_game_layout);
        this.f10360f = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10360f.setOnClickListener(this);
        this.f10362h = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10361g = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10362h.setText("游戏详情");
        this.f10361g.setText("");
        this.f10353al = (ImageView) findViewById(C2425R.C2427id.share_iv);
        this.f10353al.setVisibility(0);
        this.f10353al.setOnClickListener(this);
        this.f10325K = (RelativeLayout) findViewById(C2425R.C2427id.down_layout);
        this.f10325K.setOnClickListener(this);
        this.f10329O = (LinearLayout) findViewById(C2425R.C2427id.attention_layout);
        this.f10331Q = (TextView) findViewById(C2425R.C2427id.attention_tv);
        this.f10329O.setOnClickListener(this);
        this.f10363i = (ImageView) findViewById(C2425R.C2427id.game_iv);
        this.f10364j = (TextView) findViewById(C2425R.C2427id.game_free);
        this.f10365k = (TextView) findViewById(C2425R.C2427id.game_content);
        this.f10366l = (TextView) findViewById(C2425R.C2427id.game_size);
        this.f10367m = (TextView) findViewById(C2425R.C2427id.game_down_num);
        this.f10368n = (TextView) findViewById(C2425R.C2427id.game_brief_main);
        this.f10369o = (TextView) findViewById(C2425R.C2427id.game_screen_main);
        this.f10370p = (TextView) findViewById(C2425R.C2427id.game_recommend_main);
        this.f10368n.setOnClickListener(this);
        this.f10369o.setOnClickListener(this);
        this.f10370p.setOnClickListener(this);
        this.f10372r = (TextView) findViewById(C2425R.C2427id.app_brief);
        this.f10374t = (LinearLayout) findViewById(C2425R.C2427id.scroll_layout);
        this.f10371q = (LinearLayout) findViewById(C2425R.C2427id.app_brief_include);
        this.f10373s = (LinearLayout) findViewById(C2425R.C2427id.app_screen_include);
        this.f10375u = (LinearLayout) findViewById(C2425R.C2427id.recommend_include);
        this.f10376v = (NoScrollGridView) findViewById(C2425R.C2427id.recommend_gv);
        this.f10376v.setAdapter((ListAdapter) this.f10355an);
        this.f10376v.setOnItemClickListener(this);
        this.f10342aa = (TextView) findViewById(C2425R.C2427id.game_attention_num);
        this.f10343ab = (TextView) findViewById(C2425R.C2427id.game_title);
        this.f10344ac = (TextView) findViewById(C2425R.C2427id.game_item_add_score);
        if (this.f10377w != -1) {
            this.f10347af = HttpUtil.m14292b(this, Constant.f13162Q, this.f10377w, this.f10356ao, f10314e);
        }
        m11801a(1);
    }

    /* renamed from: a */
    private void m11801a(int i) {
        this.f10368n.setTextColor(getResources().getColor(C2425R.color.grey_3));
        this.f10369o.setTextColor(getResources().getColor(C2425R.color.grey_3));
        this.f10370p.setTextColor(getResources().getColor(C2425R.color.grey_3));
        this.f10371q.setVisibility(8);
        this.f10373s.setVisibility(8);
        this.f10375u.setVisibility(8);
        switch (i) {
            case 0:
                this.f10368n.setTextColor(getResources().getColor(C2425R.color.red));
                this.f10371q.setVisibility(0);
                return;
            case 1:
                this.f10369o.setTextColor(getResources().getColor(C2425R.color.red));
                this.f10373s.setVisibility(0);
                return;
            case 2:
                this.f10370p.setTextColor(getResources().getColor(C2425R.color.red));
                this.f10375u.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.down_tv /*{ENCODED_INT: 2131755211}*/:
                m11803a(view);
                return;
            case C2425R.C2427id.attention_iv /*{ENCODED_INT: 2131755212}*/:
                m11825g();
                return;
            case C2425R.C2427id.down_iv /*{ENCODED_INT: 2131755214}*/:
                if (this.f10339Y) {
                    m11826h();
                    return;
                }
                this.f10358b.removeMessages(7);
                this.f10358b.sendMessageDelayed(this.f10358b.obtainMessage(7), 300);
                return;
            case C2425R.C2427id.cancel_download /*{ENCODED_INT: 2131755217}*/:
                m11826h();
                showNormalDialog("下载任务将被取消", "确认取消下载?", "确定", "取消", new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.product.GameActivity.C28571 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        GameActivity.this.m11833k();
                        dialog.dismiss();
                    }
                }, new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.product.GameActivity.C28582 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                    }
                });
                return;
            case C2425R.C2427id.down_layout /*{ENCODED_INT: 2131755356}*/:
                m11803a(view);
                return;
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.share_iv /*{ENCODED_INT: 2131755763}*/:
                m11818d();
                return;
            case C2425R.C2427id.attention_layout /*{ENCODED_INT: 2131755796}*/:
                m11825g();
                return;
            case C2425R.C2427id.game_brief_main /*{ENCODED_INT: 2131755808}*/:
                m11801a(0);
                return;
            case C2425R.C2427id.game_screen_main /*{ENCODED_INT: 2131755809}*/:
                m11801a(1);
                return;
            case C2425R.C2427id.game_recommend_main /*{ENCODED_INT: 2131755810}*/:
                m11801a(2);
                return;
            default:
                Intent intent = new Intent(this, ImagesActivity.class);
                intent.putExtra("activity", "gameActivity");
                startActivity(intent);
                return;
        }
    }

    /* renamed from: d */
    private void m11818d() {
        if (this.f10354am == null) {
            ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(C2425R.layout.share_menu, (ViewGroup) null, false);
            ((LinearLayout) viewGroup.findViewById(C2425R.C2427id.wechat_layout)).setOnClickListener(this.f10341a);
            ((LinearLayout) viewGroup.findViewById(C2425R.C2427id.wxcircle_layout)).setOnClickListener(this.f10341a);
            ((LinearLayout) viewGroup.findViewById(C2425R.C2427id.qzone_layout)).setOnClickListener(this.f10341a);
            ((LinearLayout) viewGroup.findViewById(C2425R.C2427id.sina_layout)).setOnClickListener(this.f10341a);
            ((LinearLayout) viewGroup.findViewById(C2425R.C2427id.qq_layout)).setOnClickListener(this.f10341a);
            this.f10354am = new PopupWindow((View) viewGroup, -1, -1, true);
            this.f10354am.setTouchable(true);
            viewGroup.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.product.GameActivity.View$OnClickListenerC28593 */

                public void onClick(View view) {
                    GameActivity.this.f10354am.dismiss();
                }
            });
            this.f10354am.setBackgroundDrawable(new BitmapDrawable());
            this.f10354am.showAsDropDown(findViewById(C2425R.C2427id.share_iv), 0, 10);
            this.f10354am.update();
        } else if (this.f10354am.isShowing()) {
            this.f10354am.dismiss();
            this.f10354am = null;
        } else {
            this.f10354am.showAsDropDown(findViewById(C2425R.C2427id.share_iv), 0, 10);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m11821e() {
        if (this.f10327M.getText().equals("打开")) {
            StorageUtils.m14488b(this, this.f10324J);
            return;
        }
        DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", this.f10320F);
        if (downloadFile == null) {
            this.f10326L.setImageResource(C2425R.C2426drawable.start_download);
            this.f10327M.setText("下载");
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (this.f10327M.getText().equals("安装")) {
            if (new File(downloadFile.getLocalFile()).exists()) {
                StorageUtils.m14482a(this, downloadFile.getLocalFile());
                return;
            }
            this.f10326L.setImageResource(C2425R.C2426drawable.start_download);
            this.f10327M.setText("下载");
            DownloadFileDao.m12152b().delete(this, downloadFile);
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (this.f10327M.getText().equals("打开")) {
            StorageUtils.m14488b(this, downloadFile.getPackageName());
        }
    }

    /* renamed from: a */
    private void m11803a(View view) {
        if (this.f10318D == 1) {
            GBActivity.openBackWebView(this.f10319E, this.f10343ab.getText().toString());
        } else if (this.f10327M.getVisibility() != 0 || !this.f10327M.getText().toString().equals("下载")) {
            if (this.f10327M.getVisibility() != 0 || (!this.f10327M.getText().toString().equals("安装") && !this.f10327M.getText().toString().equals("打开"))) {
                if (this.f10328N.getVisibility() == 0) {
                }
                return;
            }
            this.f10359c.removeMessages(6);
            this.f10359c.sendMessageDelayed(this.f10359c.obtainMessage(6), 200);
        } else if (this.f10320F.equals("")) {
        } else {
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
                m11823f();
                Intent intent = new Intent("com.action.download.download_service");
                intent.putExtra("type", 6);
                intent.putExtra("url", this.f10320F);
                startService(intent);
                this.f10337W.setVisibility(8);
                this.f10338X.setVisibility(0);
            }
        }
    }

    /* renamed from: f */
    private void m11823f() {
        if (((DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", this.f10320F)) != null) {
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 4);
            intent.putExtra("url", this.f10320F);
            startService(intent);
        }
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setUrl(this.f10320F);
        downloadFile.setImageUrl(this.f10351aj);
        downloadFile.setName(this.f10343ab.getText().toString());
        downloadFile.setTags(this.f10321G);
        downloadFile.setFileTotalSize(Long.valueOf(this.f10322H));
        downloadFile.setDownsize(0L);
        downloadFile.setCreateTime(Long.valueOf(System.currentTimeMillis()));
        downloadFile.setProductId(Integer.valueOf(this.f10377w));
        downloadFile.setProductType(Constant.f13162Q);
        downloadFile.setStateId(2);
        downloadFile.setPackageName(this.f10324J);
        downloadFile.setAppPointsReward(Integer.valueOf(this.f10346ae));
        DownloadFileDao.m12152b().mo24215e((Context) this, downloadFile);
    }

    /* renamed from: g */
    private void m11825g() {
        if (this.f10331Q.getText().toString().equals("已关注")) {
            Log.m14400c(f10313d, "取消关注");
            showProgressDialog("正在取消关注请稍等…");
            AttentionFileDao.m12148b().delete(this, this.f10315A);
            dismissProgressDialog();
            this.f10330P.setImageResource(C2425R.C2426drawable.attention_count_large);
            this.f10331Q.setText("关注");
            showMessageToast("正在取消关注...");
            return;
        }
        AttentionFile attentionFile = new AttentionFile();
        if (this.f10379y != null) {
            attentionFile.setTitle(this.f10379y.getTitle());
            attentionFile.setTags(this.f10379y.getTags());
            attentionFile.setSid(this.f10379y.getSid());
            attentionFile.setProductType(Constant.f13162Q);
            attentionFile.setId(this.f10379y.getId());
            attentionFile.setFileTotalSize(this.f10379y.getFileTotalSize());
            attentionFile.setDownloadCount(this.f10379y.getDownloadCount());
            attentionFile.setAppPointsReward(this.f10379y.getAppPointsReward());
            attentionFile.setCacheIcon(ImageTools.m14160b(this.f10380z));
            this.f10315A = attentionFile;
        }
        AttentionFileDao.m12148b().mo24215e((Context) this, this.f10315A);
        this.f10331Q.setText("已关注");
        this.f10330P.setImageResource(C2425R.C2426drawable.attention_pressed);
        showMessageToast("已经关注...");
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("productId", this.f10378x.get(i).getSid());
        startActivity(intent);
        finish();
    }

    public class AppInstallReceiver extends BroadcastReceiver {
        public AppInstallReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            StorageUtils.m14481a(GBApplication.instance());
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") && schemeSpecificPart.equals(GameActivity.this.f10324J)) {
                UmengCount.queryGameInstallCount(context, GameActivity.this.f10343ab.getText().toString());
                GameActivity.this.f10348ag = HttpUtil.m14262a(GameActivity.this, Constant.f13162Q, Integer.valueOf(GameActivity.this.f10377w), GameActivity.this.f10343ab.getText().toString(), Integer.valueOf(GameActivity.this.f10346ae), GameActivity.this.f10356ao, GameActivity.f10314e);
                GameActivity.this.f10327M.setText("打开");
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(context, "url", GameActivity.this.f10320F);
                if (downloadFile == null || downloadFile.getLocalFile() == null || downloadFile.getStateId().intValue() != 0 || !new File(downloadFile.getLocalFile()).exists()) {
                    GameActivity.this.f10326L.setImageResource(C2425R.C2426drawable.start_download);
                    GameActivity.this.f10326L.setVisibility(0);
                    GameActivity.this.f10327M.setText("下载");
                    GameActivity.this.f10327M.setVisibility(0);
                    return;
                }
                GameActivity.this.f10327M.setText("安装");
            }
        }
    }

    public class DowningReceiver extends BroadcastReceiver {
        public DowningReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            m11850a(intent);
        }

        /* renamed from: a */
        private void m11850a(Intent intent) {
            if (intent != null && intent.getAction().equals("com.filter.download.receiver")) {
                switch (intent.getIntExtra("type", -1)) {
                    case 0:
                        String stringExtra = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals(GameActivity.this.f10320F)) {
                            GameActivity.this.dealProcessTask(intent);
                            return;
                        }
                        return;
                    case 1:
                        String stringExtra2 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra2) && stringExtra2.equals(GameActivity.this.f10320F)) {
                            GameActivity.this.dealCompleteTask();
                            Log.m14400c(GameActivity.f10313d, "COMPLETE url..>" + stringExtra2);
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
                        if (!TextUtils.isEmpty(stringExtra3) && stringExtra3.equals(GameActivity.this.f10320F)) {
                            GameActivity.this.m11802a((GameActivity) intent);
                            return;
                        }
                        return;
                    case 5:
                        String stringExtra4 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra4) && stringExtra4.equals(GameActivity.this.f10320F)) {
                            GameActivity.this.dealContinueTask();
                            return;
                        }
                        return;
                    case 6:
                        String stringExtra5 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra5) && stringExtra5.equals(GameActivity.this.f10320F)) {
                            GameActivity.this.dealAddTask(stringExtra5);
                            return;
                        }
                        return;
                    case 9:
                        intent.getStringExtra("url");
                        return;
                    case 10:
                        String stringExtra6 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra6) && stringExtra6.equals(GameActivity.this.f10320F)) {
                            GameActivity.this.m11831j();
                            return;
                        }
                        return;
                }
            }
        }
    }

    /* renamed from: h */
    private void m11826h() {
        Intent intent = new Intent("com.action.download.download_service");
        intent.putExtra("type", 3);
        intent.putExtra("url", this.f10320F);
        startService(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: i */
    private void m11828i() {
        Intent intent = new Intent("com.action.download.download_service");
        intent.putExtra("type", 5);
        intent.putExtra("url", this.f10320F);
        startService(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11802a(Intent intent) {
        dealProcessTask(intent);
        this.f10326L.setImageResource(C2425R.C2426drawable.pause_download);
        this.f10339Y = false;
        this.f10336V.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar));
        this.f10327M.setVisibility(0);
    }

    public void dealContinueTask() {
        this.f10326L.setImageResource(C2425R.C2426drawable.start_download);
        this.f10328N.setVisibility(0);
        this.f10336V.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
        this.f10339Y = true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: j */
    private void m11831j() {
        this.f10328N.setText("0%");
        this.f10336V.setProgress(0);
        this.f10339Y = false;
        this.f10326L.setImageResource(C2425R.C2426drawable.start_download);
        this.f10326L.setVisibility(0);
        this.f10327M.setText("下载");
        this.f10327M.setVisibility(0);
        this.f10336V.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
    }

    public void dealCompleteTask() {
        this.f10349ah = HttpUtil.m14273a(Constant.f13162Q, Integer.valueOf(this.f10377w), this.f10356ao, f10314e);
        this.f10337W.setVisibility(0);
        this.f10338X.setVisibility(8);
        this.f10339Y = false;
        this.f10327M.setVisibility(0);
        this.f10327M.setText("安装");
        this.f10336V.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
        DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", this.f10320F);
        if (downloadFile != null && StorageUtils.m14492c(this, downloadFile.getPackageName())) {
            this.f10327M.setText("打开");
        }
    }

    public void dealAddTask(String str) {
        this.f10326L.setImageResource(C2425R.C2426drawable.start_download);
        this.f10328N.setVisibility(0);
        this.f10328N.setText("0%");
        this.f10339Y = true;
        this.f10336V.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
    }

    public void dealProcessTask(Intent intent) {
        if (TextUtils.isEmpty(intent.getStringExtra(MyIntents.f9255c))) {
            this.f10328N.setText("0%");
            return;
        }
        this.f10336V.setProgress(Integer.parseInt(intent.getStringExtra(MyIntents.f9255c)));
        this.f10328N.setText(intent.getStringExtra(MyIntents.f9255c) + "%");
    }

    /* renamed from: b */
    private void m11812b(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (!this.f10320F.equals("")) {
            this.f10317C = !this.f10317C;
            if (this.f10317C) {
                intent.putExtra("type", 5);
                intent.putExtra("url", this.f10320F);
                startService(intent);
                return;
            }
            intent.putExtra("type", 3);
            intent.putExtra("url", this.f10320F);
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
        if (this.f10333S.booleanValue()) {
            unregisterReceiver(this.f10316B);
        }
        unregisterReceiver(this.f10323I);
        this.f10334T.removeViewImmediate(this.f10345ad);
        imageViews.clear();
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: k */
    private void m11833k() {
        List<DownloadFile> a = DownloadFileDao.m12152b().mo24203a((Context) this, "url", (Object) this.f10320F);
        DownloadFileDao.m12152b().delete((Context) this, (Collection) a);
        Intent intent = new Intent("com.action.download.download_service");
        for (int i = 0; i < a.size(); i++) {
            intent.putExtra("type", 4);
            intent.putExtra("url", a.get(i).getUrl());
            startService(intent);
        }
        m11806a(a);
        a.clear();
        this.f10338X.setVisibility(8);
        this.f10337W.setVisibility(0);
    }

    /* renamed from: a */
    private void m11806a(List<DownloadFile> list) {
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i).getUrl();
        }
        Intent intent = new Intent("com.filter.resumeIcon.receiver");
        intent.putExtra("deleteUrls", strArr);
        sendBroadcast(intent);
    }
}
