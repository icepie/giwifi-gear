package com.gbcom.gwifi.functions.product;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.download.MyIntents;
import com.gbcom.gwifi.functions.product.domain.AttentionFile;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.domain.Game;
import com.gbcom.gwifi.functions.product.p248b.AttentionFileDao;
import com.gbcom.gwifi.functions.product.p248b.DownloadFileDao;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
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

public class MakeFriendActivity extends GBActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final int INSTALL_OPEN_ANIM = 6;
    public static final int P2C_ANIM = 7;

    /* renamed from: c */
    private static final String f10469c = "MakeFriendActivity";
    public static List<View> imageViews = new ArrayList();

    /* renamed from: A */
    private DowningReceiver f10470A;

    /* renamed from: B */
    private boolean f10471B = false;

    /* renamed from: C */
    private String f10472C = "";

    /* renamed from: D */
    private String f10473D = "";

    /* renamed from: E */
    private long f10474E = 0;

    /* renamed from: F */
    private AppInstallReceiver f10475F;

    /* renamed from: G */
    private String f10476G = "";

    /* renamed from: H */
    private RelativeLayout f10477H;

    /* renamed from: I */
    private ImageView f10478I;

    /* renamed from: J */
    private TextView f10479J;

    /* renamed from: K */
    private TextView f10480K;

    /* renamed from: L */
    private LinearLayout f10481L;

    /* renamed from: M */
    private ImageView f10482M;

    /* renamed from: N */
    private TextView f10483N;

    /* renamed from: O */
    private LinearLayout f10484O;

    /* renamed from: P */
    private WindowManager f10485P;

    /* renamed from: Q */
    private ImageView f10486Q;

    /* renamed from: R */
    private ProgressBar f10487R;

    /* renamed from: S */
    private LinearLayout f10488S;

    /* renamed from: T */
    private LinearLayout f10489T;

    /* renamed from: U */
    private boolean f10490U = true;

    /* renamed from: V */
    private ImageView f10491V;

    /* renamed from: W */
    private TextView f10492W;

    /* renamed from: X */
    private TextView f10493X;

    /* renamed from: Y */
    private TextView f10494Y;

    /* renamed from: Z */
    private View f10495Z;
    @SuppressLint({"HandlerLeak"})

    /* renamed from: a */
    Handler f10496a = new Handler() {
        /* class com.gbcom.gwifi.functions.product.MakeFriendActivity.HandlerC28853 */

        public void handleMessage(Message message) {
            MakeFriendActivity.this.m11957g();
        }
    };

    /* renamed from: aa */
    private Request f10497aa;

    /* renamed from: ab */
    private int f10498ab;

    /* renamed from: ac */
    private Request f10499ac;

    /* renamed from: ad */
    private final int f10500ad = 65;

    /* renamed from: ae */
    private String f10501ae = "";

    /* renamed from: af */
    private BaseAdapter f10502af = new BaseAdapter() {
        /* class com.gbcom.gwifi.functions.product.MakeFriendActivity.C28875 */

        public int getCount() {
            return MakeFriendActivity.this.f10525w.size();
        }

        public Object getItem(int i) {
            return MakeFriendActivity.this.f10525w.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Game game = (Game) MakeFriendActivity.this.f10525w.get(i);
            View inflate = LayoutInflater.from(MakeFriendActivity.this).inflate(C2425R.layout.app_recommend_item, viewGroup, false);
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

    /* renamed from: ag */
    private OkRequestHandler<String> f10503ag = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.MakeFriendActivity.C28886 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            MakeFriendActivity.this.showProgressDialog("正在加载…");
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            GBActivity.showMessageToast("请检查网络");
            MakeFriendActivity.this.dismissProgressDialog();
            MakeFriendActivity.this.finish();
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            HashMap<String, Object> s;
            int intValue;
            MakeFriendActivity.this.dismissProgressDialog();
            if (abVar == MakeFriendActivity.this.f10523u) {
                HashMap<String, Object> f = ResourceParse.m14455f(str.getBytes());
                if (f == null) {
                    GBActivity.showMessageToast("抱歉,数据解析失败！");
                    MakeFriendActivity.this.finish();
                } else if (!ResultCode.m14475a((Integer) f.get(AbstractC5718b.JSON_ERRORCODE))) {
                    GBActivity.showMessageToast("亲！返回码错误！");
                    MakeFriendActivity.this.finish();
                } else {
                    HashMap hashMap = (HashMap) f.get("data");
                    HashMap hashMap2 = (HashMap) hashMap.get("product");
                    MakeFriendActivity.this.f10524v = ((Integer) hashMap2.get("productId")).intValue();
                    ArrayList arrayList = new ArrayList();
                    HashMap hashMap3 = (HashMap) hashMap.get("other");
                    ArrayList arrayList2 = (ArrayList) hashMap.get("preImages");
                    ArrayList arrayList3 = (ArrayList) hashMap.get("recommends");
                    Iterator it = ((ArrayList) hashMap.get("downloads")).iterator();
                    while (it.hasNext()) {
                        MakeFriendActivity.this.f10472C = (String) ((HashMap) it.next()).get("fileUrl");
                    }
                    String str2 = (String) hashMap2.get("imgUrl");
                    if (str2 != null) {
                        MakeFriendActivity.this.f10501ae = str2;
                        ImageTools.m14149a(str2, MakeFriendActivity.this.f10509g, GBApplication.instance().options);
                    } else {
                        MakeFriendActivity.this.f10509g.setVisibility(8);
                    }
                    MakeFriendActivity.this.f10493X.setText((String) hashMap2.get("productName"));
                    MakeFriendActivity.this.f10473D = C3467s.m14507a((List<String[]>) ((ArrayList) hashMap2.get("tags")));
                    ArrayList<String[]> b = C3467s.m14509b(MakeFriendActivity.this.f10473D);
                    String str3 = "";
                    if (!b.isEmpty()) {
                        str3 = b.get(0)[1];
                    }
                    MakeFriendActivity.this.f10511i.setText(str3);
                    Double d = (Double) hashMap2.get("fileTotalSize");
                    MakeFriendActivity.this.f10474E = (long) (d.doubleValue() * 1024.0d);
                    MakeFriendActivity.this.f10512j.setText(C3467s.m14501a((long) (d.doubleValue() * 1024.0d)));
                    if (((Integer) hashMap2.get(MiStat.Param.PRICE)).intValue() == 0) {
                        MakeFriendActivity.this.f10510h.setText("免费");
                    } else {
                        MakeFriendActivity.this.f10510h.setText("内容收费");
                    }
                    MakeFriendActivity.this.f10518p.setText((String) hashMap2.get("remark"));
                    MakeFriendActivity.this.f10513k.setText(C3467s.m14510c((long) ((Integer) hashMap2.get("downloadCount")).intValue()));
                    MakeFriendActivity.this.f10476G = (String) hashMap2.get("appPackage");
                    Log.m14400c(CommonNetImpl.TAG, MakeFriendActivity.this.f10476G);
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        for (int i = 0; i < arrayList2.size(); i++) {
                            ImageView imageView = new ImageView(MakeFriendActivity.this);
                            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            imageView.setAdjustViewBounds(true);
                            imageView.setOnClickListener(MakeFriendActivity.this);
                            final ImageView imageView2 = new ImageView(MakeFriendActivity.this);
                            imageView2.setImageResource(C2425R.C2426drawable.loading_small);
                            MakeFriendActivity.imageViews.add(imageView2);
                            imageView2.setTag(0);
                            ImageTools.m14150a((String) arrayList2.get(i), imageView, GBApplication.instance().options, new ImageLoadingListener() {
                                /* class com.gbcom.gwifi.functions.product.MakeFriendActivity.C28886.C28891 */

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
                                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, (DensityUtil.m14130b(MakeFriendActivity.this) / 100) * 47);
                                        layoutParams.setMargins(0, 0, DensityUtil.m14128a(MakeFriendActivity.this, 10.0f), 0);
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
                            MakeFriendActivity.this.f10520r.addView(imageView);
                        }
                    }
                    Iterator it2 = arrayList3.iterator();
                    while (it2.hasNext()) {
                        HashMap hashMap4 = (HashMap) it2.next();
                        Game game = new Game();
                        game.setTitle((String) hashMap4.get("productName"));
                        game.setSid((Integer) hashMap4.get("productId"));
                        game.setImageUrl((String) hashMap4.get("imgUrl"));
                        game.setDownloadCount((Integer) hashMap4.get("downloadCount"));
                        game.setTags((String) hashMap4.get("tagName"));
                        arrayList.add(game);
                    }
                    MakeFriendActivity.this.f10525w = arrayList;
                    MakeFriendActivity makeFriendActivity = MakeFriendActivity.this;
                    if (((Integer) hashMap2.get("appPointsReward")) == null) {
                        intValue = 0;
                    } else {
                        intValue = ((Integer) hashMap2.get("appPointsReward")).intValue();
                    }
                    makeFriendActivity.f10498ab = intValue;
                    MakeFriendActivity.this.f10494Y.setText(FormatUtil.m14216a(Integer.valueOf(MakeFriendActivity.this.f10498ab)));
                    if (StorageUtils.m14492c(MakeFriendActivity.this, MakeFriendActivity.this.f10476G)) {
                        MakeFriendActivity.this.f10490U = false;
                        MakeFriendActivity.this.f10479J.setText("打开");
                        StorageUtils.m14488b(GBApplication.instance(), MakeFriendActivity.this.f10476G);
                        MakeFriendActivity.this.finish();
                        return;
                    }
                    DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(MakeFriendActivity.this, "url", MakeFriendActivity.this.f10472C);
                    if (downloadFile != null) {
                        switch (downloadFile.getStateId().intValue()) {
                            case 0:
                                MakeFriendActivity.this.f10490U = false;
                                MakeFriendActivity.this.f10488S.setVisibility(0);
                                MakeFriendActivity.this.f10489T.setVisibility(8);
                                MakeFriendActivity.this.f10479J.setText("安装");
                                break;
                            case 1:
                                MakeFriendActivity.this.f10490U = false;
                                if (downloadFile.getFileTotalSize().longValue() != 0) {
                                    MakeFriendActivity.this.f10480K.setText(((downloadFile.getDownsize().longValue() * 100) / downloadFile.getFileTotalSize().longValue()) + "%");
                                    MakeFriendActivity.this.f10487R.setProgress((int) ((downloadFile.getDownsize().longValue() * 100) / downloadFile.getFileTotalSize().longValue()));
                                }
                                MakeFriendActivity.this.f10487R.setProgressDrawable(MakeFriendActivity.this.getResources().getDrawable(C2425R.C2426drawable.gi_progressbar));
                                MakeFriendActivity.this.f10488S.setVisibility(8);
                                MakeFriendActivity.this.f10489T.setVisibility(0);
                                break;
                            case 2:
                                MakeFriendActivity.this.f10488S.setVisibility(8);
                                MakeFriendActivity.this.f10489T.setVisibility(0);
                                MakeFriendActivity.this.f10478I.setImageResource(C2425R.C2426drawable.start_download);
                                if (downloadFile.getFileTotalSize().longValue() != 0) {
                                    MakeFriendActivity.this.f10480K.setText(((downloadFile.getDownsize().longValue() * 100) / downloadFile.getFileTotalSize().longValue()) + "%");
                                }
                                MakeFriendActivity.this.f10487R.setProgressDrawable(MakeFriendActivity.this.getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
                                MakeFriendActivity.this.f10480K.setVisibility(0);
                                MakeFriendActivity.this.f10490U = true;
                                break;
                        }
                    }
                    MakeFriendActivity.this.f10488S.setVisibility(0);
                    MakeFriendActivity.this.f10491V.setVisibility(0);
                    MakeFriendActivity.this.f10528z = (AttentionFile) AttentionFileDao.m12148b().mo24207b(MakeFriendActivity.this, "sid", (Integer) hashMap2.get("productId"));
                    if (MakeFriendActivity.this.f10528z != null) {
                        MakeFriendActivity.this.f10482M.setImageResource(C2425R.C2426drawable.attention_pressed);
                        MakeFriendActivity.this.f10483N.setText("已关注");
                    } else {
                        MakeFriendActivity.this.f10526x = new Game();
                        MakeFriendActivity.this.f10526x.setTitle((String) hashMap2.get("productName"));
                        MakeFriendActivity.this.f10526x.setImageUrl((String) hashMap2.get("imgUrl"));
                        if (MakeFriendActivity.this.f10526x.getImageUrl() != null) {
                            ImageTools.m14154a(MakeFriendActivity.this.f10526x.getImageUrl(), MakeFriendActivity.this.f10504ah);
                        }
                        MakeFriendActivity.this.f10526x.setDownloadCount((Integer) hashMap2.get("downloadCount"));
                        MakeFriendActivity.this.f10526x.setSid((Integer) hashMap2.get("productId"));
                        MakeFriendActivity.this.f10526x.setFileTotalSize(Long.valueOf((long) (((Double) hashMap2.get("fileTotalSize")).doubleValue() * 1024.0d)));
                        MakeFriendActivity.this.f10526x.setAppPointsReward(Integer.valueOf(MakeFriendActivity.this.f10498ab));
                        MakeFriendActivity.this.f10526x.setTags(C3467s.m14507a((List<String[]>) ((ArrayList) hashMap2.get("tags"))));
                    }
                    MakeFriendActivity.this.f10484O.setVisibility(0);
                }
            } else if (abVar == MakeFriendActivity.this.f10497aa) {
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
            } else if (abVar == MakeFriendActivity.this.f10499ac && (s = ResourceParse.m14468s(str.getBytes())) != null && ResultCode.m14475a((Integer) s.get(AbstractC5718b.JSON_ERRORCODE))) {
                Log.m14400c(MakeFriendActivity.f10469c, "下载次数增加成功");
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: ah */
    private ImageLoadingListener f10504ah = new ImageLoadingListener() {
        /* class com.gbcom.gwifi.functions.product.MakeFriendActivity.C28907 */

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingStarted(String str, View view) {
            MakeFriendActivity.this.f10481L.setEnabled(false);
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingFailed(String str, View view, FailReason failReason) {
            MakeFriendActivity.this.f10481L.setVisibility(4);
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            MakeFriendActivity.this.f10527y = bitmap;
            MakeFriendActivity.this.f10481L.setEnabled(true);
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: b */
    Handler f10505b = new Handler() {
        /* class com.gbcom.gwifi.functions.product.MakeFriendActivity.HandlerC28864 */

        public void handleMessage(Message message) {
            MakeFriendActivity.this.m11947c();
        }
    };

    /* renamed from: d */
    private RelativeLayout f10506d;

    /* renamed from: e */
    private TextView f10507e;

    /* renamed from: f */
    private TextView f10508f;

    /* renamed from: g */
    private ImageView f10509g;

    /* renamed from: h */
    private TextView f10510h;

    /* renamed from: i */
    private TextView f10511i;

    /* renamed from: j */
    private TextView f10512j;

    /* renamed from: k */
    private TextView f10513k;

    /* renamed from: l */
    private TextView f10514l;

    /* renamed from: m */
    private TextView f10515m;

    /* renamed from: n */
    private TextView f10516n;

    /* renamed from: o */
    private LinearLayout f10517o;

    /* renamed from: p */
    private TextView f10518p;

    /* renamed from: q */
    private LinearLayout f10519q;

    /* renamed from: r */
    private LinearLayout f10520r;

    /* renamed from: s */
    private LinearLayout f10521s;

    /* renamed from: t */
    private GridView f10522t;

    /* renamed from: u */
    private Request f10523u;

    /* renamed from: v */
    private int f10524v = -1;

    /* renamed from: w */
    private List<Game> f10525w = new ArrayList();

    /* renamed from: x */
    private Game f10526x;

    /* renamed from: y */
    private Bitmap f10527y;

    /* renamed from: z */
    private AttentionFile f10528z;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "交友界面", C2425R.layout.new_game_activity, true);
        m11933a();
        m11943b();
        this.f10475F = new AppInstallReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(C6366a.f23181c);
        registerReceiver(this.f10475F, intentFilter);
        this.f10470A = new DowningReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.filter.download.receiver");
        registerReceiver(this.f10470A, intentFilter2);
    }

    /* renamed from: a */
    private void m11933a() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f10485P = getWindow().getWindowManager();
        layoutParams.width = -1;
        layoutParams.height = DensityUtil.m14128a(this, 65.0f);
        layoutParams.gravity = 80;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.flags = 8;
        this.f10495Z = getLayoutInflater().inflate(C2425R.layout.game_window, (ViewGroup) null);
        this.f10487R = (ProgressBar) this.f10495Z.findViewById(C2425R.C2427id.game_detail_progress);
        this.f10478I = (ImageView) this.f10495Z.findViewById(C2425R.C2427id.down_iv);
        this.f10478I.setOnClickListener(this);
        this.f10479J = (TextView) this.f10495Z.findViewById(C2425R.C2427id.down_tv);
        this.f10479J.setOnClickListener(this);
        this.f10480K = (TextView) this.f10495Z.findViewById(C2425R.C2427id.progress_tv);
        this.f10482M = (ImageView) this.f10495Z.findViewById(C2425R.C2427id.attention_iv);
        this.f10482M.setOnClickListener(this);
        this.f10486Q = (ImageView) this.f10495Z.findViewById(C2425R.C2427id.cancel_download);
        this.f10486Q.setOnClickListener(this);
        this.f10488S = (LinearLayout) this.f10495Z.findViewById(C2425R.C2427id.game_down_ly);
        this.f10489T = (LinearLayout) this.f10495Z.findViewById(C2425R.C2427id.game_progress_ly);
        this.f10491V = (ImageView) this.f10495Z.findViewById(C2425R.C2427id.line_view);
        this.f10485P.addView(this.f10495Z, layoutParams);
    }

    /* renamed from: b */
    private void m11943b() {
        imageViews.clear();
        this.f10484O = (LinearLayout) findViewById(C2425R.C2427id.new_game_layout);
        this.f10506d = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10506d.setOnClickListener(this);
        this.f10508f = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10507e = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10508f.setText("应用详情");
        this.f10507e.setText("");
        this.f10477H = (RelativeLayout) findViewById(C2425R.C2427id.down_layout);
        this.f10477H.setOnClickListener(this);
        this.f10481L = (LinearLayout) findViewById(C2425R.C2427id.attention_layout);
        this.f10483N = (TextView) findViewById(C2425R.C2427id.attention_tv);
        this.f10481L.setOnClickListener(this);
        this.f10509g = (ImageView) findViewById(C2425R.C2427id.game_iv);
        this.f10510h = (TextView) findViewById(C2425R.C2427id.game_free);
        this.f10511i = (TextView) findViewById(C2425R.C2427id.game_content);
        this.f10512j = (TextView) findViewById(C2425R.C2427id.game_size);
        this.f10513k = (TextView) findViewById(C2425R.C2427id.game_down_num);
        this.f10514l = (TextView) findViewById(C2425R.C2427id.game_brief_main);
        this.f10515m = (TextView) findViewById(C2425R.C2427id.game_screen_main);
        this.f10516n = (TextView) findViewById(C2425R.C2427id.game_recommend_main);
        this.f10514l.setOnClickListener(this);
        this.f10515m.setOnClickListener(this);
        this.f10516n.setOnClickListener(this);
        this.f10518p = (TextView) findViewById(C2425R.C2427id.app_brief);
        this.f10520r = (LinearLayout) findViewById(C2425R.C2427id.scroll_layout);
        this.f10522t = (GridView) findViewById(C2425R.C2427id.recommend_gv);
        this.f10517o = (LinearLayout) findViewById(C2425R.C2427id.app_brief_include);
        this.f10519q = (LinearLayout) findViewById(C2425R.C2427id.app_screen_include);
        this.f10521s = (LinearLayout) findViewById(C2425R.C2427id.recommend_include);
        this.f10522t = (GridView) findViewById(C2425R.C2427id.recommend_gv);
        this.f10522t.setAdapter((ListAdapter) this.f10502af);
        this.f10522t.setOnItemClickListener(this);
        this.f10492W = (TextView) findViewById(C2425R.C2427id.game_attention_num);
        this.f10493X = (TextView) findViewById(C2425R.C2427id.game_title);
        this.f10494Y = (TextView) findViewById(C2425R.C2427id.game_item_add_score);
        this.f10523u = HttpUtil.m14311d(this, this.f10503ag, "");
        m11934a(1);
    }

    /* renamed from: a */
    private void m11934a(int i) {
        this.f10514l.setTextColor(getResources().getColor(C2425R.color.grey_3));
        this.f10515m.setTextColor(getResources().getColor(C2425R.color.grey_3));
        this.f10516n.setTextColor(getResources().getColor(C2425R.color.grey_3));
        this.f10517o.setVisibility(8);
        this.f10519q.setVisibility(8);
        this.f10521s.setVisibility(8);
        switch (i) {
            case 0:
                this.f10514l.setTextColor(getResources().getColor(C2425R.color.red));
                this.f10517o.setVisibility(0);
                return;
            case 1:
                this.f10515m.setTextColor(getResources().getColor(C2425R.color.red));
                this.f10519q.setVisibility(0);
                return;
            case 2:
                this.f10516n.setTextColor(getResources().getColor(C2425R.color.red));
                this.f10521s.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.down_tv /*{ENCODED_INT: 2131755211}*/:
                m11936a(view);
                return;
            case C2425R.C2427id.attention_iv /*{ENCODED_INT: 2131755212}*/:
                m11953e();
                return;
            case C2425R.C2427id.down_iv /*{ENCODED_INT: 2131755214}*/:
                if (this.f10490U) {
                    m11955f();
                    return;
                }
                this.f10496a.removeMessages(7);
                this.f10496a.sendMessageDelayed(this.f10496a.obtainMessage(7), 300);
                return;
            case C2425R.C2427id.cancel_download /*{ENCODED_INT: 2131755217}*/:
                m11955f();
                showNormalDialog("下载任务将被取消", "确认取消下载?", "确定", "取消", new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.product.MakeFriendActivity.C28831 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        MakeFriendActivity.this.m11961i();
                        dialog.dismiss();
                    }
                }, new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.product.MakeFriendActivity.C28842 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                    }
                });
                return;
            case C2425R.C2427id.down_layout /*{ENCODED_INT: 2131755356}*/:
                m11936a(view);
                return;
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.attention_layout /*{ENCODED_INT: 2131755796}*/:
                m11953e();
                return;
            case C2425R.C2427id.game_brief_main /*{ENCODED_INT: 2131755808}*/:
                m11934a(0);
                return;
            case C2425R.C2427id.game_screen_main /*{ENCODED_INT: 2131755809}*/:
                m11934a(1);
                return;
            case C2425R.C2427id.game_recommend_main /*{ENCODED_INT: 2131755810}*/:
                m11934a(2);
                return;
            default:
                Intent intent = new Intent(this, ImagesActivity.class);
                intent.putExtra("activity", "makeFriendActivity");
                startActivity(intent);
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11947c() {
        if (this.f10479J.getText().equals("打开")) {
            StorageUtils.m14488b(this, this.f10476G);
            return;
        }
        DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", this.f10472C);
        if (downloadFile == null) {
            this.f10478I.setImageResource(C2425R.C2426drawable.start_download);
            this.f10479J.setText("下载");
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (this.f10479J.getText().equals("安装")) {
            if (new File(downloadFile.getLocalFile()).exists()) {
                StorageUtils.m14482a(this, downloadFile.getLocalFile());
                return;
            }
            this.f10478I.setImageResource(C2425R.C2426drawable.start_download);
            this.f10479J.setText("下载");
            DownloadFileDao.m12152b().delete(this, downloadFile);
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (this.f10479J.getText().equals("打开")) {
            StorageUtils.m14488b(this, downloadFile.getPackageName());
        }
    }

    /* renamed from: a */
    private void m11936a(View view) {
        if (this.f10479J.getVisibility() != 0 || !this.f10479J.getText().toString().equals("下载")) {
            if (this.f10479J.getVisibility() != 0 || (!this.f10479J.getText().toString().equals("安装") && !this.f10479J.getText().toString().equals("打开"))) {
                if (this.f10480K.getVisibility() == 0) {
                }
                return;
            }
            this.f10505b.removeMessages(6);
            this.f10505b.sendMessageDelayed(this.f10505b.obtainMessage(6), 200);
        } else if (!this.f10472C.equals("")) {
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
                m11951d();
                Intent intent = new Intent("com.action.download.download_service");
                intent.putExtra("type", 6);
                intent.putExtra("url", this.f10472C);
                startService(intent);
                this.f10488S.setVisibility(8);
                this.f10489T.setVisibility(0);
            }
        }
    }

    /* renamed from: d */
    private void m11951d() {
        if (((DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", this.f10472C)) != null) {
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 4);
            intent.putExtra("url", this.f10472C);
            startService(intent);
        }
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setUrl(this.f10472C);
        downloadFile.setImageUrl(this.f10501ae);
        downloadFile.setName(this.f10493X.getText().toString());
        downloadFile.setTags(this.f10473D);
        downloadFile.setFileTotalSize(Long.valueOf(this.f10474E));
        downloadFile.setDownsize(0L);
        downloadFile.setCreateTime(Long.valueOf(System.currentTimeMillis()));
        downloadFile.setProductId(Integer.valueOf(this.f10524v));
        downloadFile.setProductType(Constant.f13162Q);
        downloadFile.setStateId(2);
        downloadFile.setPackageName(this.f10476G);
        DownloadFileDao.m12152b().mo24215e((Context) this, downloadFile);
    }

    /* renamed from: e */
    private void m11953e() {
        if (this.f10483N.getText().toString().equals("已关注")) {
            Log.m14400c(f10469c, "取消关注");
            showProgressDialog("正在取消关注请稍等…");
            AttentionFileDao.m12148b().delete(this, this.f10528z);
            dismissProgressDialog();
            this.f10482M.setImageResource(C2425R.C2426drawable.attention_count_large);
            this.f10483N.setText("关注");
            showMessageToast("正在取消关注...");
            return;
        }
        AttentionFile attentionFile = new AttentionFile();
        if (this.f10526x != null) {
            attentionFile.setTitle(this.f10526x.getTitle());
            attentionFile.setTags(this.f10526x.getTags());
            attentionFile.setSid(this.f10526x.getSid());
            attentionFile.setProductType(Constant.f13160O);
            attentionFile.setId(this.f10526x.getId());
            attentionFile.setFileTotalSize(this.f10526x.getFileTotalSize());
            attentionFile.setDownloadCount(this.f10526x.getDownloadCount());
            attentionFile.setCacheIcon(ImageTools.m14160b(this.f10527y));
            this.f10528z = attentionFile;
        }
        AttentionFileDao.m12148b().mo24215e((Context) this, this.f10528z);
        this.f10483N.setText("已关注");
        this.f10482M.setImageResource(C2425R.C2426drawable.attention_pressed);
        showMessageToast("已经关注...");
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("productId", this.f10525w.get(i).getSid());
        startActivity(intent);
        finish();
    }

    public class AppInstallReceiver extends BroadcastReceiver {
        public AppInstallReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            StorageUtils.m14481a(GBApplication.instance());
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") && schemeSpecificPart.equals(MakeFriendActivity.this.f10476G)) {
                MakeFriendActivity.this.f10479J.setText("打开");
                MakeFriendActivity.this.f10497aa = HttpUtil.m14262a(MakeFriendActivity.this, Constant.f13160O, Integer.valueOf(MakeFriendActivity.this.f10524v), MakeFriendActivity.this.f10493X.getText().toString(), Integer.valueOf(MakeFriendActivity.this.f10498ab), MakeFriendActivity.this.f10503ag, "");
                StorageUtils.m14488b(GBApplication.instance(), MakeFriendActivity.this.f10476G);
                MakeFriendActivity.this.finish();
            } else if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(context, "url", MakeFriendActivity.this.f10472C);
                if (downloadFile == null || downloadFile.getLocalFile() == null || downloadFile.getStateId().intValue() != 0 || !new File(downloadFile.getLocalFile()).exists()) {
                    MakeFriendActivity.this.f10478I.setImageResource(C2425R.C2426drawable.start_download);
                    MakeFriendActivity.this.f10478I.setVisibility(0);
                    MakeFriendActivity.this.f10479J.setText("下载");
                    MakeFriendActivity.this.f10479J.setVisibility(0);
                    return;
                }
                MakeFriendActivity.this.f10479J.setText("安装");
            }
        }
    }

    public class DowningReceiver extends BroadcastReceiver {
        public DowningReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            m11980a(intent);
        }

        /* renamed from: a */
        private void m11980a(Intent intent) {
            if (intent != null && intent.getAction().equals("com.filter.download.receiver")) {
                switch (intent.getIntExtra("type", -1)) {
                    case 0:
                        String stringExtra = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals(MakeFriendActivity.this.f10472C)) {
                            MakeFriendActivity.this.dealProcessTask(intent);
                            return;
                        }
                        return;
                    case 1:
                        String stringExtra2 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra2) && stringExtra2.equals(MakeFriendActivity.this.f10472C)) {
                            MakeFriendActivity.this.dealCompleteTask();
                            Log.m14400c(MakeFriendActivity.f10469c, "COMPLETE url..>" + stringExtra2);
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
                        if (!TextUtils.isEmpty(stringExtra3) && stringExtra3.equals(MakeFriendActivity.this.f10472C)) {
                            MakeFriendActivity.this.m11935a((MakeFriendActivity) intent);
                            return;
                        }
                        return;
                    case 5:
                        String stringExtra4 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra4) && stringExtra4.equals(MakeFriendActivity.this.f10472C)) {
                            MakeFriendActivity.this.dealContinueTask();
                            return;
                        }
                        return;
                    case 6:
                        String stringExtra5 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra5) && stringExtra5.equals(MakeFriendActivity.this.f10472C)) {
                            MakeFriendActivity.this.dealAddTask(stringExtra5);
                            return;
                        }
                        return;
                    case 9:
                        intent.getStringExtra("url");
                        return;
                    case 10:
                        String stringExtra6 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra6) && stringExtra6.equals(MakeFriendActivity.this.f10472C)) {
                            MakeFriendActivity.this.m11959h();
                            return;
                        }
                        return;
                }
            }
        }
    }

    /* renamed from: f */
    private void m11955f() {
        Intent intent = new Intent("com.action.download.download_service");
        intent.putExtra("type", 3);
        intent.putExtra("url", this.f10472C);
        startService(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: g */
    private void m11957g() {
        Intent intent = new Intent("com.action.download.download_service");
        intent.putExtra("type", 5);
        intent.putExtra("url", this.f10472C);
        startService(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11935a(Intent intent) {
        dealProcessTask(intent);
        this.f10478I.setImageResource(C2425R.C2426drawable.pause_download);
        this.f10490U = false;
        this.f10487R.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar));
        this.f10479J.setVisibility(0);
    }

    public void dealContinueTask() {
        this.f10478I.setImageResource(C2425R.C2426drawable.start_download);
        this.f10480K.setVisibility(0);
        this.f10487R.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
        this.f10490U = true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: h */
    private void m11959h() {
        this.f10480K.setText("0%");
        this.f10487R.setProgress(0);
        this.f10490U = false;
        this.f10478I.setImageResource(C2425R.C2426drawable.start_download);
        this.f10478I.setVisibility(0);
        this.f10479J.setText("下载");
        this.f10479J.setVisibility(0);
        this.f10487R.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
    }

    public void dealCompleteTask() {
        this.f10499ac = HttpUtil.m14273a(Constant.f13160O, Integer.valueOf(this.f10524v), this.f10503ag, "");
        this.f10488S.setVisibility(0);
        this.f10489T.setVisibility(8);
        this.f10490U = false;
        this.f10479J.setVisibility(0);
        this.f10479J.setText("安装");
        this.f10487R.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
        DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", this.f10472C);
        if (downloadFile != null && StorageUtils.m14492c(this, downloadFile.getPackageName())) {
            this.f10479J.setText("打开");
        }
    }

    public void dealAddTask(String str) {
        this.f10478I.setImageResource(C2425R.C2426drawable.start_download);
        this.f10480K.setVisibility(0);
        this.f10480K.setText("0%");
        this.f10490U = true;
        this.f10487R.setProgressDrawable(getResources().getDrawable(C2425R.C2426drawable.gi_progressbar_green));
    }

    public void dealProcessTask(Intent intent) {
        if (TextUtils.isEmpty(intent.getStringExtra(MyIntents.f9255c))) {
            this.f10480K.setText("0%");
            return;
        }
        this.f10487R.setProgress(Integer.parseInt(intent.getStringExtra(MyIntents.f9255c)));
        this.f10480K.setText(intent.getStringExtra(MyIntents.f9255c) + "%");
    }

    /* renamed from: b */
    private void m11944b(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (!this.f10472C.equals("")) {
            this.f10471B = !this.f10471B;
            if (this.f10471B) {
                intent.putExtra("type", 5);
                intent.putExtra("url", this.f10472C);
                startService(intent);
                return;
            }
            intent.putExtra("type", 3);
            intent.putExtra("url", this.f10472C);
            startService(intent);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        unregisterReceiver(this.f10470A);
        unregisterReceiver(this.f10475F);
        this.f10485P.removeViewImmediate(this.f10495Z);
        imageViews.clear();
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: i */
    private void m11961i() {
        List<DownloadFile> a = DownloadFileDao.m12152b().mo24203a((Context) this, "url", (Object) this.f10472C);
        DownloadFileDao.m12152b().delete((Context) this, (Collection) a);
        Intent intent = new Intent("com.action.download.download_service");
        for (int i = 0; i < a.size(); i++) {
            intent.putExtra("type", 4);
            intent.putExtra("url", a.get(i).getUrl());
            startService(intent);
        }
        m11939a(a);
        a.clear();
        this.f10489T.setVisibility(8);
        this.f10488S.setVisibility(0);
    }

    /* renamed from: a */
    private void m11939a(List<DownloadFile> list) {
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i).getUrl();
        }
        Intent intent = new Intent("com.filter.resumeIcon.receiver");
        intent.putExtra("deleteUrls", strArr);
        sendBroadcast(intent);
    }
}
