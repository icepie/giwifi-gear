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
import android.widget.ListAdapter;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.download.MyIntents;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.domain.Game;
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
public class SearchGameFragment extends GBFragment implements XListView.AbstractC3496a {

    /* renamed from: a */
    public static final int f10627a = 5;

    /* renamed from: b */
    public static final int f10628b = 6;

    /* renamed from: c */
    public static final int f10629c = 7;

    /* renamed from: g */
    private static final String f10630g = "SearchGameFragment";
    @SuppressLint({"HandlerLeak"})

    /* renamed from: d */
    Handler f10631d = new Handler() {
        /* class com.gbcom.gwifi.functions.product.SearchGameFragment.HandlerC29103 */

        public void handleMessage(Message message) {
            SearchGameFragment.this.m12061b((SearchGameFragment) ((View) message.obj));
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: e */
    Handler f10632e = new Handler() {
        /* class com.gbcom.gwifi.functions.product.SearchGameFragment.HandlerC29114 */

        public void handleMessage(Message message) {
            SearchGameFragment.this.m12067c((SearchGameFragment) ((View) message.obj));
        }
    };

    /* renamed from: f */
    Handler f10633f = new Handler() {
        /* class com.gbcom.gwifi.functions.product.SearchGameFragment.HandlerC29125 */

        public void handleMessage(Message message) {
            View view = (View) message.obj;
            SearchGameFragment.this.m12077e((SearchGameFragment) view);
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 6);
            intent.putExtra("url", ((Game) view.getTag()).getFileUrl());
            Log.m14400c(SearchGameFragment.f10630g, "向服务传递URL>.." + ((Game) view.getTag()).getFileUrl());
            GBApplication.instance().startService(intent);
        }
    };

    /* renamed from: h */
    private final int f10634h = 1;

    /* renamed from: i */
    private XListView f10635i;

    /* renamed from: j */
    private HandlerC2913a f10636j;

    /* renamed from: k */
    private Request f10637k;

    /* renamed from: l */
    private int f10638l = 0;

    /* renamed from: m */
    private Boolean f10639m = false;

    /* renamed from: n */
    private List<Game> f10640n;

    /* renamed from: o */
    private C2914b f10641o;

    /* renamed from: p */
    private String f10642p = ",";

    /* renamed from: q */
    private DowningReceiver f10643q;

    /* renamed from: r */
    private HashMap<String, View> f10644r = new HashMap<>();

    /* renamed from: s */
    private HandlerC2915c f10645s;

    /* renamed from: t */
    private AppInstallReceiver f10646t;

    /* renamed from: u */
    private ResumeDownIconReceiver f10647u;

    /* renamed from: v */
    private ImageView f10648v;

    /* renamed from: w */
    private String f10649w = "";

    /* renamed from: x */
    private OkRequestHandler<String> f10650x = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.SearchGameFragment.C29081 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (SearchGameFragment.this.isAdded()) {
                SearchGameFragment.this.f10639m = false;
                if (SearchGameFragment.this.f10637k == abVar && SearchGameFragment.this.f10638l == 0) {
                    SearchGameFragment.this.f10635i.mo28427b();
                } else {
                    SearchGameFragment.this.f10635i.mo28431d();
                }
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            int intValue;
            if (SearchGameFragment.this.isAdded() && SearchGameFragment.this.f10637k == abVar) {
                SearchGameFragment.this.f10639m = false;
                HashMap<String, Object> c = ResourceParse.m14450c(str.getBytes());
                if (c == null) {
                    if (SearchGameFragment.this.f10638l == 0) {
                        SearchGameFragment.this.f10635i.mo28427b();
                    } else {
                        SearchGameFragment.this.f10635i.mo28431d();
                    }
                } else if (ResultCode.m14475a((Integer) c.get(AbstractC5718b.JSON_ERRORCODE))) {
                    SearchGameFragment.this.f10635i.mo28429c();
                    SearchGameFragment.m12081g(SearchGameFragment.this);
                    Iterator it = ((ArrayList) ((HashMap) c.get("data")).get("products")).iterator();
                    while (it.hasNext()) {
                        HashMap hashMap = (HashMap) it.next();
                        Integer num = (Integer) hashMap.get("productId");
                        if (!SearchGameFragment.this.f10642p.contains("," + ((Object) num) + ",")) {
                            SearchGameFragment.this.f10642p += ((Object) num) + ",";
                            Game game = new Game();
                            game.setTitle((String) hashMap.get("productName"));
                            game.setImageUrl((String) hashMap.get("imgUrl"));
                            if (((Integer) hashMap.get("appPointsReward")) == null) {
                                intValue = 0;
                            } else {
                                intValue = ((Integer) hashMap.get("appPointsReward")).intValue();
                            }
                            game.setAppPointsReward(Integer.valueOf(intValue));
                            game.setDownloadCount((Integer) hashMap.get("downloadCount"));
                            game.setSid((Integer) hashMap.get("productId"));
                            game.setFileTotalSize(Long.valueOf((long) (((Double) hashMap.get("fileTotalSize")).doubleValue() * 1024.0d)));
                            game.setSid((Integer) hashMap.get("productId"));
                            game.setSpeed((Integer) hashMap.get("isSpeed"));
                            game.setPackageName((String) hashMap.get("appPackage"));
                            game.setTags(C3467s.m14507a((List<String[]>) ((ArrayList) hashMap.get("tags"))));
                            Iterator it2 = ((ArrayList) hashMap.get("downloads")).iterator();
                            while (it2.hasNext()) {
                                game.setFileUrl((String) ((HashMap) it2.next()).get("fileUrl"));
                            }
                            SearchGameFragment.this.f10640n.add(game);
                        }
                    }
                    SearchGameFragment.this.f10641o.notifyDataSetChanged();
                } else if (SearchGameFragment.this.f10638l == 0) {
                    SearchGameFragment.this.f10635i.mo28427b();
                } else {
                    SearchGameFragment.this.f10635i.mo28431d();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: y */
    private View.OnClickListener f10651y = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.product.SearchGameFragment.View$OnClickListenerC29092 */

        public void onClick(View view) {
            switch (view.getId()) {
                case C2425R.C2427id.game_item_rl /*{ENCODED_INT: 2131755521}*/:
                    Intent intent = new Intent(GBApplication.instance(), GameActivity.class);
                    intent.putExtra("productId", (Integer) view.getTag());
                    SearchGameFragment.this.startActivity(intent);
                    return;
                case C2425R.C2427id.game_icon /*{ENCODED_INT: 2131755522}*/:
                default:
                    return;
                case C2425R.C2427id.game_download_iv /*{ENCODED_INT: 2131755523}*/:
                    SearchGameFragment.this.m12072d((SearchGameFragment) view);
                    return;
                case C2425R.C2427id.game_open_btn /*{ENCODED_INT: 2131755524}*/:
                    SearchGameFragment.this.f10632e.removeMessages(6, view);
                    SearchGameFragment.this.f10632e.sendMessageDelayed(SearchGameFragment.this.f10632e.obtainMessage(6, view), 200);
                    return;
                case C2425R.C2427id.game_pb /*{ENCODED_INT: 2131755525}*/:
                    SearchGameFragment.this.m12056a((SearchGameFragment) view);
                    return;
                case C2425R.C2427id.game_downing_pause /*{ENCODED_INT: 2131755526}*/:
                    SearchGameFragment.this.f10631d.removeMessages(7, view);
                    SearchGameFragment.this.f10631d.sendMessageDelayed(SearchGameFragment.this.f10631d.obtainMessage(7, view), 300);
                    return;
            }
        }
    };

    /* renamed from: g */
    static /* synthetic */ int m12081g(SearchGameFragment searchGameFragment) {
        int i = searchGameFragment.f10638l;
        searchGameFragment.f10638l = i + 1;
        return i;
    }

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C2425R.layout.search_list_layout, (ViewGroup) null);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.f10640n = new ArrayList();
        this.f10635i = (XListView) view.findViewById(C2425R.C2427id.post_listview);
        this.f10641o = new C2914b();
        this.f10635i.setAdapter((ListAdapter) this.f10641o);
        HandlerThread handlerThread = new HandlerThread("cache_news");
        handlerThread.start();
        this.f10636j = new HandlerC2913a(handlerThread.getLooper());
        this.f10645s = new HandlerC2915c();
        this.f10648v = (ImageView) getActivity().findViewById(C2425R.C2427id.down_anim_iv);
        this.f10646t = new AppInstallReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(C6366a.f23181c);
        GBApplication.instance().registerReceiver(this.f10646t, intentFilter);
        this.f10643q = new DowningReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.filter.download.receiver");
        GBApplication.instance().registerReceiver(this.f10643q, intentFilter2);
        this.f10647u = new ResumeDownIconReceiver();
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("com.filter.resumeIcon.receiver");
        GBApplication.instance().registerReceiver(this.f10647u, intentFilter3);
        this.f10635i.mo28426a(this);
        if (!this.f10649w.trim().equals("")) {
            this.f10635i.mo28424a();
        }
    }

    /* renamed from: a */
    public void mo25490a(String str) {
        this.f10649w = str;
        if (!str.trim().equals("") && this.f10635i != null) {
            this.f10635i.mo28424a();
        }
    }

    /* renamed from: b */
    public void mo25492b(String str) {
        this.f10649w = str;
    }

    /* renamed from: a */
    public void mo25488a() {
        if (this.f10640n != null) {
            this.f10640n.clear();
            this.f10642p = ",";
            this.f10641o.notifyDataSetChanged();
        }
    }

    @Override // com.gbcom.gwifi.widget.XListView.AbstractC3496a
    public void onRefresh() {
        this.f10638l = 0;
        mo25491b();
    }

    @Override // com.gbcom.gwifi.widget.XListView.AbstractC3496a
    public void onLoadMore() {
        mo25491b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.product.SearchGameFragment$b */
    public class C2914b extends BaseAdapter {
        C2914b() {
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Game game = (Game) SearchGameFragment.this.f10640n.get(i);
            View inflate = SearchGameFragment.this.getActivity().getLayoutInflater().inflate(C2425R.layout.game_item, viewGroup, false);
            inflate.setTag(game.getFileUrl());
            TextView textView = (TextView) inflate.findViewById(16908308);
            TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.game_size);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.download_num);
            ((TextView) inflate.findViewById(C2425R.C2427id.game_item_add_score)).setText("+ " + ((Object) game.getAppPointsReward()));
            ((TextView) inflate.findViewById(C2425R.C2427id.game_item_text)).setVisibility(0);
            ((TextView) inflate.findViewById(16908310)).setText(game.getTitle());
            ArrayList<String[]> b = C3467s.m14509b(game.getTags());
            String str = "";
            if (!b.isEmpty()) {
                str = b.get(0)[1];
            }
            textView.setText(str);
            textView2.setText(C3467s.m14501a(game.getFileTotalSize().longValue()));
            textView3.setText("已下载" + ((Object) game.getDownloadCount()) + "次");
            inflate.findViewById(C2425R.C2427id.game_item_rl).setOnClickListener(SearchGameFragment.this.f10651y);
            inflate.findViewById(C2425R.C2427id.game_item_rl).setTag(game.getSid());
            SearchGameFragment.this.f10644r.put(game.getPackageName(), inflate);
            Button button = (Button) inflate.findViewById(C2425R.C2427id.game_open_btn);
            button.setTag(game.getFileUrl());
            button.setOnClickListener(SearchGameFragment.this.f10651y);
            ProgressWheel progressWheel = (ProgressWheel) inflate.findViewById(C2425R.C2427id.game_pb);
            progressWheel.setTag(game);
            progressWheel.setOnClickListener(SearchGameFragment.this.f10651y);
            Button button2 = (Button) inflate.findViewById(C2425R.C2427id.game_downing_pause);
            button2.setTag(game);
            button2.setOnClickListener(SearchGameFragment.this.f10651y);
            if (game.getSpeed().intValue() == 1) {
                inflate.findViewById(C2425R.C2427id.speed_icon).setVisibility(0);
            }
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.game_icon);
            if (game.getImageUrl() == null || game.getImageUrl().length() <= 5) {
                imageView.setVisibility(8);
            } else {
                ImageTools.m14149a(game.getImageUrl(), imageView, GBApplication.instance().options);
            }
            if (StorageUtils.m14492c(GBApplication.instance(), game.getPackageName())) {
                button.setVisibility(0);
                progressWheel.setVisibility(8);
                button2.setVisibility(8);
                button.setText("打开");
            } else {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(GBApplication.instance(), "url", game.getFileUrl());
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
                if (game.getIsWap().intValue() == 1) {
                    button.setText("打开");
                    button.setVisibility(0);
                    progressWheel.setVisibility(8);
                    button2.setVisibility(8);
                }
            }
            return inflate;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return SearchGameFragment.this.f10640n.get(i);
        }

        public int getCount() {
            return SearchGameFragment.this.f10640n.size();
        }
    }

    /* renamed from: b */
    public void mo25491b() {
        if (!this.f10639m.booleanValue()) {
            this.f10639m = true;
            this.f10637k = HttpUtil.m14271a(this.f10649w, this.f10638l + 1, 10, Constant.f13162Q, this.f10650x, "");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12056a(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (this.f10635i.findViewWithTag(((Game) view.getTag()).getFileUrl()) != null) {
            intent.putExtra("type", 3);
            intent.putExtra("url", ((Game) view.getTag()).getFileUrl());
            GBApplication.instance().startService(intent);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12061b(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (this.f10635i.findViewWithTag(((Game) view.getTag()).getFileUrl()) != null) {
            intent.putExtra("type", 5);
            intent.putExtra("url", ((Game) view.getTag()).getFileUrl());
            GBApplication.instance().startService(intent);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12067c(View view) {
        String str = (String) view.getTag();
        View findViewWithTag = this.f10635i.findViewWithTag(str);
        Button button = (Button) findViewWithTag.findViewById(C2425R.C2427id.game_open_btn);
        Button button2 = (Button) findViewWithTag.findViewById(C2425R.C2427id.game_downing_pause);
        if (button.getText().equals("打开")) {
            for (Map.Entry<String, View> entry : this.f10644r.entrySet()) {
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
    private void m12072d(View view) {
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
            this.f10648v.setVisibility(0);
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            UIUtil.m14203a(this.f10648v, Float.valueOf((float) iArr[0]), Float.valueOf((float) iArr[1]), Float.valueOf((((float) a) - ((float) (a / 8))) - ((float) (DensityUtil.m14128a(GBApplication.instance(), 25.0f) / 2))), Float.valueOf(((float) b) - ((float) DensityUtil.m14128a(GBApplication.instance(), 40.0f))));
            this.f10633f.removeMessages(5, view);
            this.f10633f.sendMessageDelayed(this.f10633f.obtainMessage(5, view), 1500);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m12077e(View view) {
        if (((DownloadFile) DownloadFileDao.m12152b().mo24207b(GBApplication.instance(), "url", ((Game) view.getTag()).getFileUrl())) != null) {
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 4);
            intent.putExtra("url", ((Game) view.getTag()).getFileUrl());
            GBApplication.instance().startService(intent);
        }
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setUrl(((Game) view.getTag()).getFileUrl());
        downloadFile.setName(((Game) view.getTag()).getTitle());
        downloadFile.setTags(((Game) view.getTag()).getTags());
        downloadFile.setDownsize(0L);
        downloadFile.setFileTotalSize(((Game) view.getTag()).getFileTotalSize());
        downloadFile.setCreateTime(Long.valueOf(System.currentTimeMillis()));
        downloadFile.setProductId(((Game) view.getTag()).getSid());
        downloadFile.setProductType(Constant.f13162Q);
        downloadFile.setStateId(2);
        downloadFile.setPackageName(((Game) view.getTag()).getPackageName());
        DownloadFileDao.m12152b().mo24215e((Context) GBApplication.instance(), downloadFile);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.product.SearchGameFragment$a */
    public class HandlerC2913a extends Handler {
        public HandlerC2913a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    SearchGameFragment.this.m12060b((SearchGameFragment) ((Intent) message.obj));
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12060b(Intent intent) {
        int parseInt;
        View findViewWithTag = this.f10635i.findViewWithTag(intent.getStringExtra("url"));
        if (findViewWithTag != null) {
            ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb);
            if (TextUtils.isEmpty(intent.getStringExtra(MyIntents.f9255c))) {
                parseInt = 0;
            } else {
                parseInt = Integer.parseInt(intent.getStringExtra(MyIntents.f9255c));
            }
            if (progressWheel != null) {
                Message obtainMessage = this.f10645s.obtainMessage(1);
                obtainMessage.obj = new Object[]{progressWheel, Integer.valueOf(parseInt)};
                this.f10645s.sendMessage(obtainMessage);
            }
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    /* renamed from: com.gbcom.gwifi.functions.product.SearchGameFragment$c */
    public class HandlerC2915c extends Handler {
        private HandlerC2915c() {
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
        GiwifiMobclickAgent.onPageStart("搜索游戏界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("搜索游戏界面");
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        GBApplication.instance().unregisterReceiver(this.f10643q);
        GBApplication.instance().unregisterReceiver(this.f10646t);
        GBApplication.instance().unregisterReceiver(this.f10647u);
        this.f10636j.getLooper().quit();
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
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") && StorageUtils.m14492c(context, schemeSpecificPart) && (view = (View) SearchGameFragment.this.f10644r.get(schemeSpecificPart)) != null) {
                ((Button) view.findViewById(C2425R.C2427id.game_open_btn)).setText("打开");
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(GBApplication.instance(), Constants.KEY_PACKAGE_NAME, schemeSpecificPart);
                if (downloadFile == null || downloadFile.getLocalFile() == null || downloadFile.getStateId().intValue() != 0 || !new File(downloadFile.getLocalFile()).exists() || (findViewWithTag = SearchGameFragment.this.f10635i.findViewWithTag(downloadFile.getUrl())) == null) {
                    View view2 = (View) SearchGameFragment.this.f10644r.get(schemeSpecificPart);
                    if (view2 != null) {
                        Button button = (Button) view2.findViewById(C2425R.C2427id.game_open_btn);
                        button.setText("安装");
                        button.setVisibility(8);
                        return;
                    }
                    return;
                }
                ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_open_btn)).setText("安装");
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
                    View findViewWithTag = SearchGameFragment.this.f10635i.findViewWithTag(str);
                    for (Map.Entry entry : SearchGameFragment.this.f10644r.entrySet()) {
                        if (entry.getValue() == findViewWithTag && StorageUtils.m14492c(GBApplication.instance(), (String) entry.getKey())) {
                            ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_open_btn)).setText("打开");
                            return;
                        }
                    }
                    if (findViewWithTag != null) {
                        Button button = (Button) findViewWithTag.findViewById(C2425R.C2427id.game_open_btn);
                        button.setText("安装");
                        button.setVisibility(8);
                        ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(8);
                        ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb);
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
            m12091a(intent);
        }

        /* renamed from: a */
        private void m12091a(Intent intent) {
            if (intent != null && intent.getAction().equals("com.filter.download.receiver")) {
                switch (intent.getIntExtra("type", -1)) {
                    case 0:
                        SearchGameFragment.this.f10636j.removeMessages(1);
                        SearchGameFragment.this.f10636j.sendMessage(SearchGameFragment.this.f10636j.obtainMessage(1, intent));
                        return;
                    case 1:
                        String stringExtra = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra)) {
                            SearchGameFragment.this.m12079e((SearchGameFragment) stringExtra);
                            Log.m14400c(SearchGameFragment.f10630g, "COMPLETE url..>" + stringExtra);
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
                        SearchGameFragment.this.m12066c((SearchGameFragment) intent);
                        return;
                    case 5:
                        SearchGameFragment.this.mo25489a(intent);
                        return;
                    case 6:
                        String stringExtra2 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra2)) {
                            SearchGameFragment.this.m12075d((SearchGameFragment) stringExtra2);
                            return;
                        }
                        return;
                    case 9:
                        intent.getStringExtra("url");
                        return;
                    case 10:
                        SearchGameFragment.this.m12070c((SearchGameFragment) intent.getStringExtra("url"));
                        return;
                }
            }
        }
    }

    /* renamed from: a */
    public void mo25489a(Intent intent) {
        View findViewWithTag = this.f10635i.findViewWithTag(intent.getStringExtra("url"));
        if (findViewWithTag != null) {
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(8);
            ((ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb)).setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12066c(Intent intent) {
        m12060b(intent);
        View findViewWithTag = this.f10635i.findViewWithTag(intent.getStringExtra("url"));
        if (findViewWithTag != null) {
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(0);
            ((ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb)).setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12070c(String str) {
        View findViewWithTag = this.f10635i.findViewWithTag(str);
        if (findViewWithTag != null) {
            ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb);
            progressWheel.mo28351a("0");
            progressWheel.mo28349a(0);
            progressWheel.setVisibility(8);
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m12075d(String str) {
        View findViewWithTag = this.f10635i.findViewWithTag(str);
        if (findViewWithTag != null) {
            ProgressWheel progressWheel = (ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb);
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_open_btn)).setVisibility(8);
            progressWheel.setVisibility(0);
            progressWheel.mo28351a("0");
            progressWheel.mo28349a(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m12079e(String str) {
        Log.m14398b(f10630g, "dealCompleteTask");
        View findViewWithTag = this.f10635i.findViewWithTag(str);
        if (findViewWithTag != null) {
            Button button = (Button) findViewWithTag.findViewById(C2425R.C2427id.game_open_btn);
            button.setVisibility(0);
            ((ProgressWheel) findViewWithTag.findViewById(C2425R.C2427id.game_pb)).setVisibility(8);
            ((Button) findViewWithTag.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(8);
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(GBApplication.instance(), "url", str);
            if (downloadFile != null && StorageUtils.m14492c(GBApplication.instance(), downloadFile.getPackageName())) {
                button.setText("打开");
            }
        }
    }
}
