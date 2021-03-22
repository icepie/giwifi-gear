package com.gbcom.gwifi.functions.product;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.download.MyIntents;
import com.gbcom.gwifi.functions.product.domain.App;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.p248b.DownloadFileDao;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.ResourceParse;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.StorageUtils;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.util.p257b.UIUtil;
import com.gbcom.gwifi.widget.ProgressWheel;
import com.gbcom.gwifi.widget.PullToRefreshView;
import com.taobao.accs.common.Constants;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.umeng.message.common.C6366a;
import java.p456io.File;
import java.p456io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p041c.Request;

public class BannerAppListActivity extends GBActivity implements View.OnClickListener {
    public static final int INSTALL_OPEN_ANIM = 6;
    public static final int P2C_ANIM = 7;

    /* renamed from: c */
    private static final String f10167c = "BannerAppListActivity";

    /* renamed from: d */
    private static final Object f10168d = f10167c;

    /* renamed from: A */
    private OkRequestHandler<String> f10169A = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.BannerAppListActivity.C28405 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            BannerAppListActivity.this.showProgressDialog("正在加载…");
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (!BannerAppListActivity.this.isFinishing()) {
                BannerAppListActivity.this.f10178k.mo28398a("更新于:" + BannerAppListActivity.this.f10181n.format(new Date(System.currentTimeMillis())));
                if (BannerAppListActivity.this.f10184q == abVar) {
                    Log.m14403e(BannerAppListActivity.f10167c, exc.getMessage());
                    GBActivity.showMessageToast("加载数据失败！");
                    BannerAppListActivity.this.dismissProgressDialog();
                    BannerAppListActivity.this.finish();
                }
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            int intValue;
            if (!BannerAppListActivity.this.isFinishing()) {
                BannerAppListActivity.this.dismissProgressDialog();
                BannerAppListActivity.this.f10178k.mo28398a("更新于:" + BannerAppListActivity.this.f10181n.format(new Date(System.currentTimeMillis())));
                if (abVar == BannerAppListActivity.this.f10184q) {
                    HashMap<String, Object> d = ResourceParse.m14452d(str.getBytes());
                    if (d == null) {
                        GBActivity.showMessageToast("抱歉,数据解析失败！");
                        BannerAppListActivity.this.finish();
                    } else if (!ResultCode.m14475a((Integer) d.get(AbstractC5718b.JSON_ERRORCODE))) {
                        Log.m14403e(BannerAppListActivity.f10167c, "BannerAppListActivity->onRequestFinish resultCode:" + d.get(AbstractC5718b.JSON_ERRORCODE));
                        GBActivity.showMessageToast("亲！返回码错误！");
                        BannerAppListActivity.this.finish();
                    } else {
                        ArrayList arrayList = new ArrayList();
                        Iterator it = ((ArrayList) ((HashMap) d.get("data")).get("products")).iterator();
                        while (it.hasNext()) {
                            HashMap hashMap = (HashMap) it.next();
                            App app = new App();
                            app.setTitle((String) hashMap.get("productName"));
                            app.setImageUrl((String) hashMap.get("imgUrl"));
                            app.setDownloadCount((Integer) hashMap.get("downloadCount"));
                            app.setSid((Integer) hashMap.get("productId"));
                            app.setFileTotalSize(Long.valueOf((long) (((Double) hashMap.get("fileTotalSize")).doubleValue() * 1024.0d)));
                            app.setSid((Integer) hashMap.get("productId"));
                            app.setSpeed((Integer) hashMap.get("isSpeed"));
                            if (((Integer) hashMap.get("appPointsReward")) == null) {
                                intValue = 0;
                            } else {
                                intValue = ((Integer) hashMap.get("appPointsReward")).intValue();
                            }
                            app.setAppPointsReward(Integer.valueOf(intValue));
                            app.setTags(C3467s.m14507a((List<String[]>) ((ArrayList) hashMap.get("tags"))));
                            app.setPackageName((String) hashMap.get("appPackage"));
                            Iterator it2 = ((ArrayList) hashMap.get("downloads")).iterator();
                            while (it2.hasNext()) {
                                app.setFileUrl((String) ((HashMap) it2.next()).get("fileUrl"));
                            }
                            arrayList.add(app);
                        }
                        BannerAppListActivity.this.f10183p.clear();
                        BannerAppListActivity.this.f10183p = arrayList;
                        if (BannerAppListActivity.this.f10183p.size() == 0) {
                            GBActivity.showMessageToast("已经加载完了");
                        } else {
                            BannerAppListActivity.this.f10193z.notifyDataSetChanged();
                        }
                    }
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: a */
    Handler f10170a = new Handler() {
        /* class com.gbcom.gwifi.functions.product.BannerAppListActivity.HandlerC28372 */

        public void handleMessage(Message message) {
            BannerAppListActivity.this.m11699e((BannerAppListActivity) ((View) message.obj));
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: b */
    Handler f10171b = new Handler() {
        /* class com.gbcom.gwifi.functions.product.BannerAppListActivity.HandlerC28383 */

        public void handleMessage(Message message) {
            BannerAppListActivity.this.m11686a((BannerAppListActivity) ((View) message.obj));
        }
    };

    /* renamed from: e */
    private RelativeLayout f10172e;

    /* renamed from: f */
    private TextView f10173f;

    /* renamed from: g */
    private TextView f10174g;

    /* renamed from: h */
    private ImageView f10175h;

    /* renamed from: i */
    private ImageView f10176i;

    /* renamed from: j */
    private ImageView f10177j;

    /* renamed from: k */
    private PullToRefreshView f10178k;

    /* renamed from: l */
    private long f10179l = -1;

    /* renamed from: m */
    private final long f10180m = 3000;

    /* renamed from: n */
    private SimpleDateFormat f10181n;

    /* renamed from: o */
    private ListView f10182o;

    /* renamed from: p */
    private List<App> f10183p = new ArrayList();

    /* renamed from: q */
    private Request f10184q;

    /* renamed from: r */
    private boolean f10185r = false;

    /* renamed from: s */
    private int f10186s = -1;

    /* renamed from: t */
    private BannerAppReceiver f10187t;

    /* renamed from: u */
    private boolean f10188u = false;

    /* renamed from: v */
    private HashMap<String, View> f10189v = new HashMap<>();

    /* renamed from: w */
    private HashMap<String, View> f10190w = new HashMap<>();

    /* renamed from: x */
    private HandlerC2841a f10191x;

    /* renamed from: y */
    private AppInstallReceiver f10192y;

    /* renamed from: z */
    private BaseAdapter f10193z = new BaseAdapter() {
        /* class com.gbcom.gwifi.functions.product.BannerAppListActivity.C28394 */

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return BannerAppListActivity.this.f10183p.get(i);
        }

        public int getCount() {
            return BannerAppListActivity.this.f10183p.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            App app = (App) BannerAppListActivity.this.f10183p.get(i);
            View inflate = BannerAppListActivity.this.getLayoutInflater().inflate(C2425R.layout.app_item, viewGroup, false);
            BannerAppListActivity.this.f10189v.put(app.getFileUrl(), inflate);
            BannerAppListActivity.this.f10190w.put(app.getPackageName(), inflate);
            ((TextView) inflate.findViewById(C2425R.C2427id.app_item_add_score)).setText(" + " + ((Object) app.getAppPointsReward()));
            ((TextView) inflate.findViewById(C2425R.C2427id.app_item_text)).setVisibility(0);
            ((TextView) inflate.findViewById(16908310)).setText(app.getTitle());
            ((TextView) inflate.findViewById(16908308)).setText(C3467s.m14506a(C3467s.m14509b(app.getTags()), "  "));
            ((TextView) inflate.findViewById(C2425R.C2427id.app_size)).setText(C3467s.m14501a(app.getFileTotalSize().longValue()));
            ((TextView) inflate.findViewById(C2425R.C2427id.download_num)).setText("已下载" + C3467s.m14510c((long) app.getDownloadCount().intValue()));
            if (app.getSpeed().intValue() == 1) {
                inflate.findViewById(C2425R.C2427id.speed_icon).setVisibility(0);
                inflate.findViewById(C2425R.C2427id.speed_icon2).setVisibility(0);
            }
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.app_download_iv);
            imageView.setTag(Integer.valueOf(i));
            imageView.setOnClickListener(BannerAppListActivity.this);
            Button button = (Button) inflate.findViewById(C2425R.C2427id.app_open_btn);
            button.setTag(Integer.valueOf(i));
            button.setOnClickListener(BannerAppListActivity.this);
            ProgressWheel progressWheel = (ProgressWheel) inflate.findViewById(C2425R.C2427id.app_pb);
            progressWheel.setTag(Integer.valueOf(i));
            progressWheel.setOnClickListener(BannerAppListActivity.this);
            ImageView imageView2 = (ImageView) inflate.findViewById(C2425R.C2427id.app_downing_pause);
            imageView2.setTag(Integer.valueOf(i));
            imageView2.setOnClickListener(BannerAppListActivity.this);
            ImageView imageView3 = (ImageView) inflate.findViewById(C2425R.C2427id.app_icon);
            if (app.getImageUrl() == null || app.getImageUrl().length() <= 5) {
                imageView3.setVisibility(8);
            } else {
                ImageTools.m14149a(app.getImageUrl(), imageView3, GBApplication.instance().options);
            }
            inflate.findViewById(C2425R.C2427id.app_item_rl).setTag(Integer.valueOf(i));
            inflate.findViewById(C2425R.C2427id.app_item_rl).setOnClickListener(BannerAppListActivity.this);
            if (!StorageUtils.m14492c(BannerAppListActivity.this, app.getPackageName())) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(BannerAppListActivity.this, "url", app.getFileUrl());
                if (downloadFile != null) {
                    switch (downloadFile.getStateId().intValue()) {
                        case 0:
                            imageView.setVisibility(8);
                            button.setVisibility(0);
                            progressWheel.setVisibility(8);
                            imageView2.setVisibility(8);
                            break;
                        case 1:
                            imageView.setVisibility(8);
                            button.setVisibility(8);
                            progressWheel.setVisibility(8);
                            imageView2.setVisibility(0);
                            break;
                        case 2:
                            imageView.setVisibility(8);
                            button.setVisibility(8);
                            progressWheel.setVisibility(0);
                            imageView2.setVisibility(8);
                            break;
                    }
                }
            } else {
                imageView.setVisibility(8);
                button.setVisibility(0);
                progressWheel.setVisibility(8);
                imageView2.setVisibility(8);
                button.setText("打开");
            }
            return inflate;
        }
    };

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "顶部应用专题界面", C2425R.layout.banner_app_list_activity, true);
        m11684a();
        update();
        this.f10192y = new AppInstallReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(C6366a.f23181c);
        registerReceiver(this.f10192y, intentFilter);
        this.f10191x = new HandlerC2841a();
        this.f10187t = new BannerAppReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.filter.download.receiver");
        registerReceiver(this.f10187t, intentFilter2);
    }

    /* renamed from: a */
    private void m11684a() {
        this.f10186s = getIntent().getIntExtra("catId", -1);
        this.f10172e = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10172e.setOnClickListener(this);
        this.f10174g = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10173f = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10174g.setText(getIntent().getStringExtra("title"));
        this.f10173f.setText((CharSequence) null);
        this.f10178k = (PullToRefreshView) findViewById(C2425R.C2427id.main_pull_refresh_view);
        this.f10181n = new SimpleDateFormat("MM-dd HH:mm");
        this.f10178k.mo28397a(new PullToRefreshView.AbstractC3494b() {
            /* class com.gbcom.gwifi.functions.product.BannerAppListActivity.C28361 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
            public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
                BannerAppListActivity.this.update();
            }
        });
        this.f10183p = new ArrayList();
        this.f10182o = (ListView) findViewById(C2425R.C2427id.app_banner_lv);
        this.f10182o.setAdapter((ListAdapter) this.f10193z);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.app_item_rl /*{ENCODED_INT: 2131755238}*/:
                int intValue = this.f10183p.get(((Integer) view.getTag()).intValue()).getSid().intValue();
                Intent intent = new Intent(this, AppActivity.class);
                intent.putExtra("productId", intValue);
                startActivity(intent);
                return;
            case C2425R.C2427id.app_download_iv /*{ENCODED_INT: 2131755241}*/:
                m11692b(view);
                return;
            case C2425R.C2427id.app_open_btn /*{ENCODED_INT: 2131755242}*/:
                UIUtil.m14202a(view);
                this.f10171b.removeMessages(6, view);
                this.f10171b.sendMessageDelayed(this.f10171b.obtainMessage(6, view), 200);
                return;
            case C2425R.C2427id.app_pb /*{ENCODED_INT: 2131755243}*/:
                m11697d(view);
                return;
            case C2425R.C2427id.app_downing_pause /*{ENCODED_INT: 2131755244}*/:
                view.startAnimation(AnimationUtils.loadAnimation(this, C2425R.anim.downing_pause_anim));
                this.f10170a.removeMessages(7, view);
                this.f10170a.sendMessageDelayed(this.f10170a.obtainMessage(7, view), 300);
                return;
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11686a(View view) {
        String fileUrl = this.f10183p.get(((Integer) view.getTag()).intValue()).getFileUrl();
        View view2 = this.f10189v.get(fileUrl);
        Button button = (Button) view2.findViewById(C2425R.C2427id.app_open_btn);
        ImageView imageView = (ImageView) view2.findViewById(C2425R.C2427id.app_download_iv);
        ImageView imageView2 = (ImageView) view2.findViewById(C2425R.C2427id.app_downing_pause);
        if (button.getText().equals("打开")) {
            for (Map.Entry<String, View> entry : this.f10190w.entrySet()) {
                if (entry.getValue() == view2) {
                    StorageUtils.m14488b(this, entry.getKey());
                    return;
                }
            }
            return;
        }
        DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", fileUrl);
        if (downloadFile == null) {
            button.setText("安装");
            button.setVisibility(8);
            imageView.setVisibility(0);
            imageView2.setVisibility(8);
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (button.getText().equals("安装")) {
            if (new File(downloadFile.getLocalFile()).exists()) {
                StorageUtils.m14482a(this, downloadFile.getLocalFile());
                return;
            }
            button.setVisibility(8);
            imageView2.setVisibility(8);
            imageView.setVisibility(0);
            DownloadFileDao.m12152b().delete(this, downloadFile);
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (button.getText().equals("打开")) {
            StorageUtils.m14488b(this, downloadFile.getPackageName());
        }
    }

    /* renamed from: b */
    private void m11692b(View view) {
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
            m11695c(view);
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 6);
            intent.putExtra("url", this.f10183p.get(((Integer) view.getTag()).intValue()).getFileUrl());
            startService(intent);
        }
    }

    /* renamed from: c */
    private void m11695c(View view) {
        if (((DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", this.f10183p.get(((Integer) view.getTag()).intValue()).getFileUrl())) != null) {
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 4);
            intent.putExtra("url", this.f10183p.get(((Integer) view.getTag()).intValue()).getFileUrl());
            startService(intent);
        }
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setUrl(this.f10183p.get(((Integer) view.getTag()).intValue()).getFileUrl());
        downloadFile.setName(this.f10183p.get(((Integer) view.getTag()).intValue()).getTitle());
        downloadFile.setTags(this.f10183p.get(((Integer) view.getTag()).intValue()).getTags());
        downloadFile.setDownsize(0L);
        downloadFile.setFileTotalSize(this.f10183p.get(((Integer) view.getTag()).intValue()).getFileTotalSize());
        downloadFile.setCreateTime(Long.valueOf(System.currentTimeMillis()));
        downloadFile.setProductId(this.f10183p.get(((Integer) view.getTag()).intValue()).getSid());
        downloadFile.setProductType(Constant.f13160O);
        downloadFile.setStateId(2);
        downloadFile.setPackageName(this.f10183p.get(((Integer) view.getTag()).intValue()).getPackageName());
        DownloadFileDao.m12152b().mo24215e((Context) this, downloadFile);
    }

    public void update() {
        if (System.currentTimeMillis() - this.f10179l < 3000) {
            this.f10178k.mo28398a("更新于:" + this.f10181n.format(new Date(System.currentTimeMillis())));
            return;
        }
        this.f10179l = System.currentTimeMillis();
        if (this.f10186s != -1) {
            this.f10184q = HttpUtil.m14289b(this, this.f10186s, this.f10169A, f10168d);
        }
    }

    public class AppInstallReceiver extends BroadcastReceiver {
        public AppInstallReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            View view;
            View view2;
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            StorageUtils.m14481a(BannerAppListActivity.this);
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") && StorageUtils.m14492c(context, schemeSpecificPart) && (view2 = (View) BannerAppListActivity.this.f10190w.get(schemeSpecificPart)) != null) {
                ((Button) view2.findViewById(C2425R.C2427id.app_open_btn)).setText("打开");
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(BannerAppListActivity.this, Constants.KEY_PACKAGE_NAME, schemeSpecificPart);
                if (downloadFile == null || downloadFile.getLocalFile() == null || downloadFile.getStateId().intValue() != 0 || !new File(downloadFile.getLocalFile()).exists() || (view = (View) BannerAppListActivity.this.f10189v.get(downloadFile.getUrl())) == null) {
                    View view3 = (View) BannerAppListActivity.this.f10190w.get(schemeSpecificPart);
                    if (view3 != null) {
                        Button button = (Button) view3.findViewById(C2425R.C2427id.app_open_btn);
                        button.setText("安装");
                        button.setVisibility(8);
                        ((ImageView) view3.findViewById(C2425R.C2427id.app_download_iv)).setVisibility(0);
                        return;
                    }
                    return;
                }
                ((Button) view.findViewById(C2425R.C2427id.app_open_btn)).setText("安装");
            }
        }
    }

    public class BannerAppReceiver extends BroadcastReceiver {
        public BannerAppReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            m11705a(intent);
        }

        /* renamed from: a */
        private void m11705a(Intent intent) {
            if (intent != null && intent.getAction().equals("com.filter.download.receiver")) {
                switch (intent.getIntExtra("type", -1)) {
                    case 0:
                        BannerAppListActivity.this.f10191x.removeMessages(1);
                        BannerAppListActivity.this.f10191x.sendMessage(BannerAppListActivity.this.f10191x.obtainMessage(1, intent));
                        return;
                    case 1:
                        String stringExtra = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra)) {
                            BannerAppListActivity.this.dealCompleteTask(stringExtra);
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
                        BannerAppListActivity.this.m11685a((BannerAppListActivity) intent);
                        return;
                    case 5:
                        BannerAppListActivity.this.dealContinueTask(intent);
                        return;
                    case 6:
                        String stringExtra2 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra2)) {
                            BannerAppListActivity.this.dealAddTask(stringExtra2);
                            return;
                        }
                        return;
                    case 9:
                        intent.getStringExtra("url");
                        return;
                    case 10:
                        BannerAppListActivity.this.m11690a((BannerAppListActivity) intent.getStringExtra("url"));
                        return;
                }
            }
        }
    }

    /* renamed from: d */
    private void m11697d(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (this.f10189v.get(this.f10183p.get(((Integer) view.getTag()).intValue()).getFileUrl()) != null) {
            intent.putExtra("type", 3);
            intent.putExtra("url", this.f10183p.get(((Integer) view.getTag()).intValue()).getFileUrl());
            startService(intent);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m11699e(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (this.f10189v.get(this.f10183p.get(((Integer) view.getTag()).intValue()).getFileUrl()) != null) {
            intent.putExtra("type", 5);
            intent.putExtra("url", this.f10183p.get(((Integer) view.getTag()).intValue()).getFileUrl());
            startService(intent);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11685a(Intent intent) {
        dealProcessTask(intent);
        View view = this.f10189v.get(intent.getStringExtra("url"));
        if (view != null) {
            ((ImageView) view.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(0);
            ((ProgressWheel) view.findViewById(C2425R.C2427id.app_pb)).setVisibility(8);
        }
    }

    public void dealContinueTask(Intent intent) {
        View view = this.f10189v.get(intent.getStringExtra("url"));
        if (view != null) {
            ((ImageView) view.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(8);
            ((ProgressWheel) view.findViewById(C2425R.C2427id.app_pb)).setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11690a(String str) {
        View view = this.f10189v.get(str);
        if (view != null) {
            ProgressWheel progressWheel = (ProgressWheel) view.findViewById(C2425R.C2427id.app_pb);
            progressWheel.mo28351a("0");
            progressWheel.mo28349a(0);
            progressWheel.setVisibility(8);
            ((ImageView) view.findViewById(C2425R.C2427id.app_download_iv)).setVisibility(0);
            ((ImageView) view.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(8);
        }
    }

    public void dealCompleteTask(String str) {
        View view = this.f10189v.get(str);
        if (view != null) {
            Button button = (Button) view.findViewById(C2425R.C2427id.app_open_btn);
            ((ImageView) view.findViewById(C2425R.C2427id.app_download_iv)).setVisibility(8);
            button.setVisibility(0);
            ((ProgressWheel) view.findViewById(C2425R.C2427id.app_pb)).setVisibility(8);
            ((ImageView) view.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(8);
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", str);
            if (downloadFile != null && StorageUtils.m14492c(this, downloadFile.getPackageName())) {
                button.setText("打开");
            }
        }
    }

    public void dealAddTask(String str) {
        View view = this.f10189v.get(str);
        if (view != null) {
            ProgressWheel progressWheel = (ProgressWheel) view.findViewById(C2425R.C2427id.app_pb);
            ((ImageView) view.findViewById(C2425R.C2427id.app_download_iv)).setVisibility(8);
            ((Button) view.findViewById(C2425R.C2427id.app_open_btn)).setVisibility(8);
            progressWheel.setVisibility(0);
            progressWheel.mo28351a("0");
            progressWheel.mo28349a(0);
        }
    }

    public void dealProcessTask(Intent intent) {
        View view = this.f10189v.get(intent.getStringExtra("url"));
        if (view != null) {
            ProgressWheel progressWheel = (ProgressWheel) view.findViewById(C2425R.C2427id.app_pb);
            if (TextUtils.isEmpty(intent.getStringExtra(MyIntents.f9255c))) {
                progressWheel.mo28351a("0");
                progressWheel.mo28349a(0);
                return;
            }
            progressWheel.mo28351a(intent.getStringExtra(MyIntents.f9255c) + "%");
            progressWheel.mo28349a((Integer.parseInt(intent.getStringExtra(MyIntents.f9255c)) * 360) / 100);
        }
    }

    /* renamed from: f */
    private void m11701f(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        View view2 = this.f10189v.get(((App) view.getTag()).getFileUrl());
        if (view2 != null) {
            ProgressWheel progressWheel = (ProgressWheel) view2.findViewById(C2425R.C2427id.app_pb);
            this.f10188u = !this.f10188u;
            if (this.f10188u) {
                intent.putExtra("type", 5);
                intent.putExtra("url", ((App) view.getTag()).getFileUrl());
                startService(intent);
                return;
            }
            intent.putExtra("type", 3);
            intent.putExtra("url", ((App) view.getTag()).getFileUrl());
            startService(intent);
            progressWheel.mo28351a("暂停");
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    /* renamed from: com.gbcom.gwifi.functions.product.BannerAppListActivity$a */
    public class HandlerC2841a extends Handler {
        private HandlerC2841a() {
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    BannerAppListActivity.this.dealProcessTask((Intent) message.obj);
                    break;
            }
            super.handleMessage(message);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        unregisterReceiver(this.f10187t);
        super.onDestroy();
    }
}
