package com.gbcom.gwifi.functions.product;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.download.MyIntents;
import com.gbcom.gwifi.functions.product.domain.App;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.p248b.DownloadFileDao;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.ResourceParse;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.StorageUtils;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.util.p257b.UIUtil;
import com.gbcom.gwifi.widget.ProgressWheel;
import com.gbcom.gwifi.widget.XListView;
import com.taobao.accs.common.Constants;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.umeng.message.common.C6366a;
import java.p456io.File;
import java.p456io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p041c.Request;

@SuppressLint({"ValidFragment"})
public class SearchAppFragment extends GBFragment implements XListView.AbstractC3496a {

    /* renamed from: a */
    public static final int f10584a = 5;

    /* renamed from: b */
    public static final int f10585b = 6;

    /* renamed from: c */
    public static final int f10586c = 7;

    /* renamed from: g */
    private static final String f10587g = "SearchAppFragment";

    /* renamed from: l */
    private static final int f10588l = 1;

    /* renamed from: m */
    private static final int f10589m = 5000;

    /* renamed from: A */
    private AppInstallReceiver f10590A;

    /* renamed from: B */
    private ResumeDownIconReceiver f10591B;

    /* renamed from: C */
    private ImageView f10592C;

    /* renamed from: D */
    private String f10593D = "";

    /* renamed from: E */
    private OkRequestHandler<String> f10594E = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.SearchAppFragment.C29001 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (SearchAppFragment.this.isAdded()) {
                SearchAppFragment.this.f10607r = false;
                if (SearchAppFragment.this.f10605p == abVar && SearchAppFragment.this.f10606q == 0) {
                    SearchAppFragment.this.f10603n.mo28427b();
                } else {
                    SearchAppFragment.this.f10603n.mo28431d();
                }
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            int intValue;
            if (SearchAppFragment.this.isAdded() && SearchAppFragment.this.f10605p == abVar) {
                SearchAppFragment.this.f10607r = false;
                HashMap<String, Object> c = ResourceParse.m14450c(str.getBytes());
                if (c == null) {
                    if (SearchAppFragment.this.f10606q == 0) {
                        SearchAppFragment.this.f10603n.mo28427b();
                    } else {
                        SearchAppFragment.this.f10603n.mo28431d();
                    }
                } else if (ResultCode.m14475a((Integer) c.get(AbstractC5718b.JSON_ERRORCODE))) {
                    SearchAppFragment.this.f10603n.mo28429c();
                    SearchAppFragment.m12042g(SearchAppFragment.this);
                    Iterator it = ((ArrayList) ((HashMap) c.get("data")).get("products")).iterator();
                    while (it.hasNext()) {
                        HashMap hashMap = (HashMap) it.next();
                        Integer num = (Integer) hashMap.get("productId");
                        if (!SearchAppFragment.this.f10611v.contains("," + ((Object) num) + ",")) {
                            SearchAppFragment.this.f10611v += ((Object) num) + ",";
                            App app = new App();
                            app.setTitle((String) hashMap.get("productName"));
                            app.setImageUrl((String) hashMap.get("imgUrl"));
                            if (((Integer) hashMap.get("appPointsReward")) == null) {
                                intValue = 0;
                            } else {
                                intValue = ((Integer) hashMap.get("appPointsReward")).intValue();
                            }
                            app.setAppPointsReward(Integer.valueOf(intValue));
                            app.setDownloadCount((Integer) hashMap.get("downloadCount"));
                            app.setSid((Integer) hashMap.get("productId"));
                            app.setFileTotalSize(Long.valueOf((long) (((Double) hashMap.get("fileTotalSize")).doubleValue() * 1024.0d)));
                            app.setSid((Integer) hashMap.get("productId"));
                            app.setSpeed((Integer) hashMap.get("isSpeed"));
                            app.setTags(C3467s.m14507a((List<String[]>) ((ArrayList) hashMap.get("tags"))));
                            app.setPackageName((String) hashMap.get("appPackage"));
                            Iterator it2 = ((ArrayList) hashMap.get("downloads")).iterator();
                            while (it2.hasNext()) {
                                app.setFileUrl((String) ((HashMap) it2.next()).get("fileUrl"));
                            }
                            SearchAppFragment.this.f10608s.add(app);
                        }
                    }
                    SearchAppFragment.this.f10609t.notifyDataSetChanged();
                } else if (SearchAppFragment.this.f10606q == 0) {
                    SearchAppFragment.this.f10603n.mo28427b();
                } else {
                    SearchAppFragment.this.f10603n.mo28431d();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: F */
    private View.OnClickListener f10595F = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.product.SearchAppFragment.View$OnClickListenerC29012 */

        public void onClick(View view) {
            switch (view.getId()) {
                case C2425R.C2427id.app_item_rl /*{ENCODED_INT: 2131755238}*/:
                    Intent intent = new Intent(GBApplication.instance(), AppActivity.class);
                    intent.putExtra("productId", (Integer) view.getTag());
                    SearchAppFragment.this.startActivity(intent);
                    return;
                case C2425R.C2427id.app_icon /*{ENCODED_INT: 2131755239}*/:
                case C2425R.C2427id.speed_icon2 /*{ENCODED_INT: 2131755240}*/:
                default:
                    return;
                case C2425R.C2427id.app_download_iv /*{ENCODED_INT: 2131755241}*/:
                    SearchAppFragment.this.m12033d((SearchAppFragment) view);
                    return;
                case C2425R.C2427id.app_open_btn /*{ENCODED_INT: 2131755242}*/:
                    SearchAppFragment.this.f10597e.removeMessages(6, view);
                    SearchAppFragment.this.f10597e.sendMessageDelayed(SearchAppFragment.this.f10597e.obtainMessage(6, view), 200);
                    return;
                case C2425R.C2427id.app_pb /*{ENCODED_INT: 2131755243}*/:
                    SearchAppFragment.this.m12017a((SearchAppFragment) view);
                    return;
                case C2425R.C2427id.app_downing_pause /*{ENCODED_INT: 2131755244}*/:
                    SearchAppFragment.this.f10596d.removeMessages(7, view);
                    SearchAppFragment.this.f10596d.sendMessageDelayed(SearchAppFragment.this.f10596d.obtainMessage(7, view), 300);
                    return;
            }
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: d */
    Handler f10596d = new Handler() {
        /* class com.gbcom.gwifi.functions.product.SearchAppFragment.HandlerC29023 */

        public void handleMessage(Message message) {
            SearchAppFragment.this.m12022b((SearchAppFragment) ((View) message.obj));
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: e */
    Handler f10597e = new Handler() {
        /* class com.gbcom.gwifi.functions.product.SearchAppFragment.HandlerC29034 */

        public void handleMessage(Message message) {
            SearchAppFragment.this.m12028c((SearchAppFragment) ((View) message.obj));
        }
    };

    /* renamed from: f */
    Handler f10598f = new Handler() {
        /* class com.gbcom.gwifi.functions.product.SearchAppFragment.HandlerC29045 */

        public void handleMessage(Message message) {
            View view = (View) message.obj;
            SearchAppFragment.this.m12038e((SearchAppFragment) view);
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 6);
            intent.putExtra("url", ((App) view.getTag()).getFileUrl());
            Log.m14400c(SearchAppFragment.f10587g, "向服务传递URL>.." + ((App) view.getTag()).getFileUrl());
            GBApplication.instance().startService(intent);
        }
    };

    /* renamed from: h */
    private boolean f10599h = false;

    /* renamed from: i */
    private boolean f10600i = false;

    /* renamed from: j */
    private final int f10601j = 0;

    /* renamed from: k */
    private final int f10602k = 1;

    /* renamed from: n */
    private XListView f10603n;

    /* renamed from: o */
    private HandlerC2905a f10604o;

    /* renamed from: p */
    private Request f10605p;

    /* renamed from: q */
    private int f10606q = 0;

    /* renamed from: r */
    private Boolean f10607r = false;

    /* renamed from: s */
    private List<App> f10608s;

    /* renamed from: t */
    private C2906b f10609t;

    /* renamed from: u */
    private LinearLayout f10610u;

    /* renamed from: v */
    private String f10611v = ",";

    /* renamed from: w */
    private DowningReceiver f10612w;

    /* renamed from: x */
    private boolean f10613x = false;

    /* renamed from: y */
    private HashMap<String, View> f10614y = new HashMap<>();

    /* renamed from: z */
    private HandlerC2907c f10615z;

    /* renamed from: g */
    static /* synthetic */ int m12042g(SearchAppFragment searchAppFragment) {
        int i = searchAppFragment.f10606q;
        searchAppFragment.f10606q = i + 1;
        return i;
    }

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C2425R.layout.search_list_layout, (ViewGroup) null);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.f10608s = new ArrayList();
        this.f10603n = (XListView) view.findViewById(C2425R.C2427id.post_listview);
        this.f10609t = new C2906b();
        this.f10603n.setAdapter((ListAdapter) this.f10609t);
        HandlerThread handlerThread = new HandlerThread("cache_news");
        handlerThread.start();
        this.f10604o = new HandlerC2905a(handlerThread.getLooper());
        this.f10615z = new HandlerC2907c();
        this.f10592C = (ImageView) getActivity().findViewById(C2425R.C2427id.down_anim_iv);
        this.f10590A = new AppInstallReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(C6366a.f23181c);
        GBApplication.instance().registerReceiver(this.f10590A, intentFilter);
        this.f10612w = new DowningReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.filter.download.receiver");
        GBApplication.instance().registerReceiver(this.f10612w, intentFilter2);
        this.f10591B = new ResumeDownIconReceiver();
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("com.filter.resumeIcon.receiver");
        GBApplication.instance().registerReceiver(this.f10591B, intentFilter3);
        this.f10603n.mo28426a(this);
        if (!this.f10593D.trim().equals("")) {
            this.f10603n.mo28424a();
        }
    }

    /* renamed from: a */
    public void mo25471a(String str) {
        this.f10593D = str;
        if (!str.trim().equals("") && this.f10603n != null) {
            this.f10603n.mo28424a();
        }
    }

    /* renamed from: b */
    public void mo25473b(String str) {
        this.f10593D = str;
    }

    /* renamed from: a */
    public void mo25469a() {
        if (this.f10608s != null) {
            this.f10611v = ",";
            this.f10608s.clear();
            this.f10609t.notifyDataSetChanged();
        }
    }

    @Override // com.gbcom.gwifi.widget.XListView.AbstractC3496a
    public void onRefresh() {
        this.f10606q = 0;
        mo25472b();
    }

    @Override // com.gbcom.gwifi.widget.XListView.AbstractC3496a
    public void onLoadMore() {
        mo25472b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.product.SearchAppFragment$b */
    public class C2906b extends BaseAdapter {
        C2906b() {
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            App app = (App) SearchAppFragment.this.f10608s.get(i);
            View inflate = SearchAppFragment.this.getActivity().getLayoutInflater().inflate(C2425R.layout.app_item, viewGroup, false);
            inflate.setTag(app.getFileUrl());
            TextView textView = (TextView) inflate.findViewById(16908308);
            TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.app_size);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.download_num);
            ((TextView) inflate.findViewById(C2425R.C2427id.app_item_add_score)).setText("+ " + ((Object) app.getAppPointsReward()));
            ((TextView) inflate.findViewById(C2425R.C2427id.app_item_text)).setVisibility(0);
            ((TextView) inflate.findViewById(16908310)).setText(app.getTitle());
            ArrayList<String[]> b = C3467s.m14509b(app.getTags());
            String str = "";
            if (!b.isEmpty()) {
                str = b.get(0)[1];
            }
            textView.setText(str);
            textView2.setText(C3467s.m14501a(app.getFileTotalSize().longValue()));
            textView3.setText("已下载" + ((Object) app.getDownloadCount()) + "次");
            inflate.findViewById(C2425R.C2427id.app_item_rl).setTag(app.getSid());
            inflate.findViewById(C2425R.C2427id.app_item_rl).setOnClickListener(SearchAppFragment.this.f10595F);
            SearchAppFragment.this.f10614y.put(app.getPackageName(), inflate);
            Button button = (Button) inflate.findViewById(C2425R.C2427id.app_open_btn);
            button.setTag(app.getFileUrl());
            button.setOnClickListener(SearchAppFragment.this.f10595F);
            ProgressWheel progressWheel = (ProgressWheel) inflate.findViewById(C2425R.C2427id.app_pb);
            progressWheel.setTag(app);
            progressWheel.setOnClickListener(SearchAppFragment.this.f10595F);
            Button button2 = (Button) inflate.findViewById(C2425R.C2427id.app_downing_pause);
            button2.setTag(app);
            button2.setOnClickListener(SearchAppFragment.this.f10595F);
            if (app.getSpeed().intValue() == 1) {
                inflate.findViewById(C2425R.C2427id.speed_icon).setVisibility(0);
            }
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.app_icon);
            if (app.getImageUrl() == null || app.getImageUrl().length() <= 5) {
                imageView.setVisibility(8);
            } else {
                ImageTools.m14149a(app.getImageUrl(), imageView, GBApplication.instance().options);
            }
            if (!StorageUtils.m14492c(GBApplication.instance(), app.getPackageName())) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(GBApplication.instance(), "url", app.getFileUrl());
                if (downloadFile != null) {
                    switch (downloadFile.getStateId().intValue()) {
                        case 0:
                            button.setVisibility(0);
                            progressWheel.setVisibility(8);
                            button2.setVisibility(8);
                            break;
                        case 1:
                            button.setVisibility(8);
                            progressWheel.setVisibility(8);
                            button2.setVisibility(0);
                            break;
                        case 2:
                            button.setVisibility(8);
                            progressWheel.setVisibility(0);
                            button2.setVisibility(8);
                            break;
                    }
                }
            } else {
                button.setVisibility(0);
                progressWheel.setVisibility(8);
                button2.setVisibility(8);
                button.setText("打开");
            }
            return inflate;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return SearchAppFragment.this.f10608s.get(i);
        }

        public int getCount() {
            return SearchAppFragment.this.f10608s.size();
        }
    }

    /* renamed from: b */
    public void mo25472b() {
        if (!this.f10607r.booleanValue()) {
            this.f10607r = true;
            this.f10605p = HttpUtil.m14271a(this.f10593D, this.f10606q + 1, 10, Constant.f13160O, this.f10594E, "");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12017a(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (this.f10603n.findViewWithTag(((App) view.getTag()).getFileUrl()) != null) {
            intent.putExtra("type", 3);
            intent.putExtra("url", ((App) view.getTag()).getFileUrl());
            GBApplication.instance().startService(intent);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12022b(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (this.f10603n.findViewWithTag(((App) view.getTag()).getFileUrl()) != null) {
            intent.putExtra("type", 5);
            intent.putExtra("url", ((App) view.getTag()).getFileUrl());
            GBApplication.instance().startService(intent);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12028c(View view) {
        String str = (String) view.getTag();
        View findViewWithTag = this.f10603n.findViewWithTag(str);
        Button button = (Button) findViewWithTag.findViewById(C2425R.C2427id.app_open_btn);
        Button button2 = (Button) findViewWithTag.findViewById(C2425R.C2427id.app_downing_pause);
        if (button.getText().equals("打开")) {
            for (Map.Entry<String, View> entry : this.f10614y.entrySet()) {
                if (entry.getValue() == findViewWithTag) {
                    StorageUtils.m14488b(GBApplication.instance(), entry.getKey());
                    return;
                }
            }
            return;
        }
        DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(GBApplication.instance(), "url", str);
        if (downloadFile == null) {
            button.setText("安装");
            button.setVisibility(8);
            button2.setVisibility(8);
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (button.getText().equals("安装")) {
            if (new File(downloadFile.getLocalFile()).exists()) {
                StorageUtils.m14482a(GBApplication.instance(), downloadFile.getLocalFile());
                return;
            }
            button.setVisibility(8);
            button2.setVisibility(8);
            DownloadFileDao.m12152b().delete(GBApplication.instance(), downloadFile);
            GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
        } else if (button.getText().equals("打开")) {
            StorageUtils.m14488b(GBApplication.instance(), StorageUtils.m14494d(GBApplication.instance(), downloadFile.getLocalFile()));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m12033d(View view) {
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
            int a = DensityUtil.m14127a(GBApplication.instance());
            int b = DensityUtil.m14130b(GBApplication.instance());
            this.f10592C.setVisibility(0);
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            UIUtil.m14203a(this.f10592C, Float.valueOf((float) iArr[0]), Float.valueOf((float) iArr[1]), Float.valueOf((((float) a) - ((float) (a / 8))) - ((float) (DensityUtil.m14128a(GBApplication.instance(), 25.0f) / 2))), Float.valueOf(((float) b) - ((float) DensityUtil.m14128a(GBApplication.instance(), 40.0f))));
            this.f10598f.removeMessages(5, view);
            this.f10598f.sendMessageDelayed(this.f10598f.obtainMessage(5, view), 1500);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m12038e(View view) {
        if (((DownloadFile) DownloadFileDao.m12152b().mo24207b(GBApplication.instance(), "url", ((App) view.getTag()).getFileUrl())) != null) {
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 4);
            intent.putExtra("url", ((App) view.getTag()).getFileUrl());
            GBApplication.instance().startService(intent);
        }
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setUrl(((App) view.getTag()).getFileUrl());
        downloadFile.setName(((App) view.getTag()).getTitle());
        downloadFile.setTags(((App) view.getTag()).getTags());
        downloadFile.setDownsize(0L);
        downloadFile.setFileTotalSize(((App) view.getTag()).getFileTotalSize());
        downloadFile.setCreateTime(Long.valueOf(System.currentTimeMillis()));
        downloadFile.setProductId(((App) view.getTag()).getSid());
        downloadFile.setProductType(Constant.f13160O);
        downloadFile.setStateId(2);
        downloadFile.setPackageName(((App) view.getTag()).getPackageName());
        DownloadFileDao.m12152b().mo24215e((Context) GBApplication.instance(), downloadFile);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.product.SearchAppFragment$a */
    public class HandlerC2905a extends Handler {
        public HandlerC2905a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    SearchAppFragment.this.m12021b((SearchAppFragment) ((Intent) message.obj));
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12021b(Intent intent) {
        int parseInt;
        View findViewWithTag = this.f10603n.findViewWithTag(intent.getStringExtra("url"));
        if (findViewWithTag != null) {
            ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb);
            if (TextUtils.isEmpty(intent.getStringExtra(MyIntents.f9255c))) {
                parseInt = 0;
            } else {
                parseInt = Integer.parseInt(intent.getStringExtra(MyIntents.f9255c));
            }
            if (progressWheel != null) {
                Message obtainMessage = this.f10615z.obtainMessage(1);
                obtainMessage.obj = new Object[]{progressWheel, Integer.valueOf(parseInt)};
                this.f10615z.sendMessage(obtainMessage);
            }
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    /* renamed from: com.gbcom.gwifi.functions.product.SearchAppFragment$c */
    public class HandlerC2907c extends Handler {
        private HandlerC2907c() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    Object[] objArr = (Object[]) message.obj;
                    ((ProgressWheel) objArr[0]).mo28351a(objArr[1] + "%");
                    ((ProgressWheel) objArr[0]).mo28349a((Integer.parseInt(objArr[1].toString()) * 360) / 100);
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        GiwifiMobclickAgent.onPageStart("搜索应用界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("搜索应用界面");
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        GBApplication.instance().unregisterReceiver(this.f10612w);
        GBApplication.instance().unregisterReceiver(this.f10590A);
        GBApplication.instance().unregisterReceiver(this.f10591B);
        this.f10604o.getLooper().quit();
        super.onDestroy();
    }

    public class AppInstallReceiver extends BroadcastReceiver {
        public AppInstallReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            View findViewWithTag;
            View view;
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            StorageUtils.m14481a(GBApplication.instance());
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") && StorageUtils.m14492c(context, schemeSpecificPart) && (view = (View) SearchAppFragment.this.f10614y.get(schemeSpecificPart)) != null) {
                ((Button) view.findViewById(C2425R.C2427id.app_open_btn)).setText("打开");
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(GBApplication.instance(), Constants.KEY_PACKAGE_NAME, schemeSpecificPart);
                if (downloadFile == null || downloadFile.getLocalFile() == null || downloadFile.getStateId().intValue() != 0 || !new File(downloadFile.getLocalFile()).exists() || (findViewWithTag = SearchAppFragment.this.f10603n.findViewWithTag(downloadFile.getUrl())) == null) {
                    View view2 = (View) SearchAppFragment.this.f10614y.get(schemeSpecificPart);
                    if (view2 != null) {
                        Button button = (Button) view2.findViewById(C2425R.C2427id.app_open_btn);
                        button.setText("安装");
                        button.setVisibility(8);
                        return;
                    }
                    return;
                }
                ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_open_btn)).setText("安装");
            }
        }
    }

    public class ResumeDownIconReceiver extends BroadcastReceiver {
        public ResumeDownIconReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String[] stringArrayExtra;
            if (intent != null && intent.getAction().equals("com.filter.resumeIcon.receiver")) {
                for (String str : intent.getStringArrayExtra("deleteUrls")) {
                    View findViewWithTag = SearchAppFragment.this.f10603n.findViewWithTag(str);
                    for (Map.Entry entry : SearchAppFragment.this.f10614y.entrySet()) {
                        if (entry.getValue() == findViewWithTag && StorageUtils.m14492c(GBApplication.instance(), (String) entry.getKey())) {
                            ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_open_btn)).setText("打开");
                            return;
                        }
                    }
                    if (findViewWithTag != null) {
                        Button button = (Button) findViewWithTag.findViewById(C2425R.C2427id.app_open_btn);
                        button.setText("安装");
                        button.setVisibility(8);
                        ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(8);
                        ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb);
                        progressWheel.mo28351a("0");
                        progressWheel.mo28349a(0);
                        progressWheel.setVisibility(8);
                    }
                }
            }
        }
    }

    public class DowningReceiver extends BroadcastReceiver {
        public DowningReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            m12052a(intent);
        }

        /* renamed from: a */
        private void m12052a(Intent intent) {
            if (intent != null && intent.getAction().equals("com.filter.download.receiver")) {
                switch (intent.getIntExtra("type", -1)) {
                    case 0:
                        SearchAppFragment.this.f10604o.removeMessages(1);
                        SearchAppFragment.this.f10604o.sendMessage(SearchAppFragment.this.f10604o.obtainMessage(1, intent));
                        return;
                    case 1:
                        String stringExtra = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra)) {
                            SearchAppFragment.this.m12040e((SearchAppFragment) stringExtra);
                            Log.m14400c(SearchAppFragment.f10587g, "COMPLETE url..>" + stringExtra);
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
                        SearchAppFragment.this.m12027c((SearchAppFragment) intent);
                        return;
                    case 5:
                        SearchAppFragment.this.mo25470a(intent);
                        return;
                    case 6:
                        String stringExtra2 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra2)) {
                            SearchAppFragment.this.m12036d((SearchAppFragment) stringExtra2);
                            return;
                        }
                        return;
                    case 9:
                        intent.getStringExtra("url");
                        return;
                    case 10:
                        SearchAppFragment.this.m12031c((SearchAppFragment) intent.getStringExtra("url"));
                        return;
                }
            }
        }
    }

    /* renamed from: a */
    public void mo25470a(Intent intent) {
        View findViewWithTag = this.f10603n.findViewWithTag(intent.getStringExtra("url"));
        if (findViewWithTag != null) {
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(8);
            ((ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb)).setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12027c(Intent intent) {
        m12021b(intent);
        View findViewWithTag = this.f10603n.findViewWithTag(intent.getStringExtra("url"));
        if (findViewWithTag != null) {
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(0);
            ((ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb)).setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12031c(String str) {
        View findViewWithTag = this.f10603n.findViewWithTag(str);
        if (findViewWithTag != null) {
            ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb);
            progressWheel.mo28351a("0");
            progressWheel.mo28349a(0);
            progressWheel.setVisibility(8);
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m12036d(String str) {
        View findViewWithTag = this.f10603n.findViewWithTag(str);
        if (findViewWithTag != null) {
            ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb);
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_open_btn)).setVisibility(8);
            progressWheel.setVisibility(0);
            progressWheel.mo28351a("0");
            progressWheel.mo28349a(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m12040e(String str) {
        Log.m14398b(f10587g, "dealCompleteTask");
        View findViewWithTag = this.f10603n.findViewWithTag(str);
        if (findViewWithTag != null) {
            Button button = (Button) findViewWithTag.findViewById(C2425R.C2427id.app_open_btn);
            button.setVisibility(0);
            ((ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.app_pb)).setVisibility(8);
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.app_downing_pause)).setVisibility(8);
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(GBApplication.instance(), "url", str);
            if (downloadFile != null && StorageUtils.m14492c(GBApplication.instance(), downloadFile.getPackageName())) {
                button.setText("打开");
            }
        }
    }
}
