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
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.domain.Game;
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

public class BannerGameListActivity extends GBActivity implements View.OnClickListener {
    public static final int INSTALL_OPEN_ANIM = 6;
    public static final int P2C_ANIM = 7;

    /* renamed from: c */
    private static final String f10202c = "BannerGameListActivity";

    /* renamed from: d */
    private static final Object f10203d = f10202c;

    /* renamed from: A */
    private OkRequestHandler<String> f10204A = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.BannerGameListActivity.C28465 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            BannerGameListActivity.this.showProgressDialog("正在加载…");
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (!BannerGameListActivity.this.isFinishing()) {
                BannerGameListActivity.this.f10213k.mo28398a("更新于:" + BannerGameListActivity.this.f10216n.format(new Date(System.currentTimeMillis())));
                if (BannerGameListActivity.this.f10219q == abVar) {
                    GBActivity.showMessageToast("加载数据失败！");
                    BannerGameListActivity.this.dismissProgressDialog();
                    BannerGameListActivity.this.finish();
                }
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            int intValue;
            if (!BannerGameListActivity.this.isFinishing()) {
                BannerGameListActivity.this.dismissProgressDialog();
                BannerGameListActivity.this.f10213k.mo28398a("更新于:" + BannerGameListActivity.this.f10216n.format(new Date(System.currentTimeMillis())));
                if (abVar == BannerGameListActivity.this.f10219q) {
                    HashMap<String, Object> d = ResourceParse.m14452d(str.getBytes());
                    if (d == null) {
                        GBActivity.showMessageToast("抱歉,数据解析失败！");
                        BannerGameListActivity.this.finish();
                        return;
                    }
                    boolean a = ResultCode.m14475a((Integer) d.get(AbstractC5718b.JSON_ERRORCODE));
                    Log.m14400c(BannerGameListActivity.f10202c, ((Object) ((Integer) d.get(AbstractC5718b.JSON_ERRORCODE))) + "");
                    if (!a) {
                        GBActivity.showMessageToast("亲！返回码错误！");
                        BannerGameListActivity.this.finish();
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    Iterator it = ((ArrayList) ((HashMap) d.get("data")).get("products")).iterator();
                    while (it.hasNext()) {
                        HashMap hashMap = (HashMap) it.next();
                        Game game = new Game();
                        game.setTitle((String) hashMap.get("productName"));
                        game.setImageUrl((String) hashMap.get("imgUrl"));
                        game.setDownloadCount((Integer) hashMap.get("downloadCount"));
                        game.setSid((Integer) hashMap.get("productId"));
                        game.setFileTotalSize(Long.valueOf((long) (((Double) hashMap.get("fileTotalSize")).doubleValue() * 1024.0d)));
                        game.setSid((Integer) hashMap.get("productId"));
                        game.setSpeed((Integer) hashMap.get("isSpeed"));
                        if (((Integer) hashMap.get("appPointsReward")) == null) {
                            intValue = 0;
                        } else {
                            intValue = ((Integer) hashMap.get("appPointsReward")).intValue();
                        }
                        game.setAppPointsReward(Integer.valueOf(intValue));
                        game.setTags(C3467s.m14507a((List<String[]>) ((ArrayList) hashMap.get("tags"))));
                        game.setPackageName((String) hashMap.get("appPackage"));
                        Iterator it2 = ((ArrayList) hashMap.get("downloads")).iterator();
                        while (it2.hasNext()) {
                            game.setFileUrl((String) ((HashMap) it2.next()).get("fileUrl"));
                        }
                        arrayList.add(game);
                    }
                    BannerGameListActivity.this.f10218p.clear();
                    BannerGameListActivity.this.f10218p = arrayList;
                    if (BannerGameListActivity.this.f10218p.size() == 0) {
                        GBActivity.showMessageToast("已经加载完了");
                    } else {
                        BannerGameListActivity.this.f10228z.notifyDataSetChanged();
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
    Handler f10205a = new Handler() {
        /* class com.gbcom.gwifi.functions.product.BannerGameListActivity.HandlerC28432 */

        public void handleMessage(Message message) {
            BannerGameListActivity.this.m11723e((BannerGameListActivity) ((View) message.obj));
        }
    };
    @SuppressLint({"HandlerLeak"})

    /* renamed from: b */
    Handler f10206b = new Handler() {
        /* class com.gbcom.gwifi.functions.product.BannerGameListActivity.HandlerC28443 */

        public void handleMessage(Message message) {
            BannerGameListActivity.this.m11710a((BannerGameListActivity) ((View) message.obj));
        }
    };

    /* renamed from: e */
    private RelativeLayout f10207e;

    /* renamed from: f */
    private TextView f10208f;

    /* renamed from: g */
    private TextView f10209g;

    /* renamed from: h */
    private ImageView f10210h;

    /* renamed from: i */
    private ImageView f10211i;

    /* renamed from: j */
    private ImageView f10212j;

    /* renamed from: k */
    private PullToRefreshView f10213k;

    /* renamed from: l */
    private long f10214l = -1;

    /* renamed from: m */
    private final long f10215m = 3000;

    /* renamed from: n */
    private SimpleDateFormat f10216n;

    /* renamed from: o */
    private ListView f10217o;

    /* renamed from: p */
    private List<Game> f10218p;

    /* renamed from: q */
    private Request f10219q;

    /* renamed from: r */
    private boolean f10220r = false;

    /* renamed from: s */
    private int f10221s = -1;

    /* renamed from: t */
    private DowningReceiver f10222t;

    /* renamed from: u */
    private boolean f10223u = false;

    /* renamed from: v */
    private HashMap<String, View> f10224v = new HashMap<>();

    /* renamed from: w */
    private HashMap<String, View> f10225w = new HashMap<>();

    /* renamed from: x */
    private HandlerC2847a f10226x;

    /* renamed from: y */
    private AppInstallReceiver f10227y;

    /* renamed from: z */
    private BaseAdapter f10228z = new BaseAdapter() {
        /* class com.gbcom.gwifi.functions.product.BannerGameListActivity.C28454 */

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return BannerGameListActivity.this.f10218p.get(i);
        }

        public int getCount() {
            return BannerGameListActivity.this.f10218p.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Game game = (Game) BannerGameListActivity.this.f10218p.get(i);
            View inflate = BannerGameListActivity.this.getLayoutInflater().inflate(C2425R.layout.game_item, viewGroup, false);
            BannerGameListActivity.this.f10224v.put(game.getFileUrl(), inflate);
            BannerGameListActivity.this.f10225w.put(game.getPackageName(), inflate);
            ((TextView) inflate.findViewById(C2425R.C2427id.game_item_add_score)).setText(" + " + ((Object) game.getAppPointsReward()));
            ((TextView) inflate.findViewById(C2425R.C2427id.game_item_text)).setVisibility(0);
            ((TextView) inflate.findViewById(16908310)).setText(game.getTitle());
            ((TextView) inflate.findViewById(16908308)).setText(C3467s.m14506a(C3467s.m14509b(game.getTags()), "  "));
            ((TextView) inflate.findViewById(C2425R.C2427id.game_size)).setText(C3467s.m14501a(game.getFileTotalSize().longValue()));
            ((TextView) inflate.findViewById(C2425R.C2427id.download_num)).setText("已下载" + C3467s.m14510c((long) game.getDownloadCount().intValue()));
            if (game.getSpeed().intValue() == 1) {
                inflate.findViewById(C2425R.C2427id.speed_icon).setVisibility(0);
                inflate.findViewById(C2425R.C2427id.speed_icon2).setVisibility(0);
            }
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.game_download_iv);
            imageView.setTag(Integer.valueOf(i));
            imageView.setOnClickListener(BannerGameListActivity.this);
            Button button = (Button) inflate.findViewById(C2425R.C2427id.game_open_btn);
            button.setTag(Integer.valueOf(i));
            button.setOnClickListener(BannerGameListActivity.this);
            ProgressWheel progressWheel = (ProgressWheel) inflate.findViewById(C2425R.C2427id.game_pb);
            progressWheel.setTag(Integer.valueOf(i));
            progressWheel.setOnClickListener(BannerGameListActivity.this);
            ImageView imageView2 = (ImageView) inflate.findViewById(C2425R.C2427id.game_downing_pause);
            imageView2.setTag(Integer.valueOf(i));
            imageView2.setOnClickListener(BannerGameListActivity.this);
            ImageView imageView3 = (ImageView) inflate.findViewById(C2425R.C2427id.game_icon);
            if (game.getImageUrl() == null || game.getImageUrl().length() <= 5) {
                imageView3.setVisibility(8);
            } else {
                ImageTools.m14149a(game.getImageUrl(), imageView3, GBApplication.instance().options);
            }
            inflate.findViewById(C2425R.C2427id.game_item_rl).setTag(Integer.valueOf(i));
            inflate.findViewById(C2425R.C2427id.game_item_rl).setOnClickListener(BannerGameListActivity.this);
            if (!StorageUtils.m14492c(BannerGameListActivity.this, game.getPackageName())) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(BannerGameListActivity.this, "url", game.getFileUrl());
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
        super.onCreate(bundle, "顶部游戏专题界面", C2425R.layout.banner_game_list_activity, true);
        m11708a();
        update();
        this.f10227y = new AppInstallReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(C6366a.f23181c);
        registerReceiver(this.f10227y, intentFilter);
        this.f10226x = new HandlerC2847a();
        this.f10222t = new DowningReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.filter.download.receiver");
        registerReceiver(this.f10222t, intentFilter2);
    }

    /* renamed from: a */
    private void m11708a() {
        this.f10221s = getIntent().getIntExtra("catId", -1);
        this.f10207e = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10207e.setOnClickListener(this);
        this.f10209g = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10208f = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10209g.setText(getIntent().getStringExtra("title"));
        this.f10208f.setText((CharSequence) null);
        this.f10213k = (PullToRefreshView) findViewById(C2425R.C2427id.main_pull_refresh_view);
        this.f10216n = new SimpleDateFormat("MM-dd HH:mm");
        this.f10213k.mo28397a(new PullToRefreshView.AbstractC3494b() {
            /* class com.gbcom.gwifi.functions.product.BannerGameListActivity.C28421 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
            public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
                BannerGameListActivity.this.update();
            }
        });
        this.f10218p = new ArrayList();
        this.f10217o = (ListView) findViewById(C2425R.C2427id.banner_game_lv);
        this.f10217o.setAdapter((ListAdapter) this.f10228z);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.game_item_rl /*{ENCODED_INT: 2131755521}*/:
                int intValue = this.f10218p.get(((Integer) view.getTag()).intValue()).getSid().intValue();
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("productId", intValue);
                startActivity(intent);
                return;
            case C2425R.C2427id.game_download_iv /*{ENCODED_INT: 2131755523}*/:
                m11716b(view);
                return;
            case C2425R.C2427id.game_open_btn /*{ENCODED_INT: 2131755524}*/:
                UIUtil.m14202a(view);
                this.f10206b.removeMessages(6, view);
                this.f10206b.sendMessageDelayed(this.f10206b.obtainMessage(6, view), 200);
                return;
            case C2425R.C2427id.game_pb /*{ENCODED_INT: 2131755525}*/:
                m11721d(view);
                return;
            case C2425R.C2427id.game_downing_pause /*{ENCODED_INT: 2131755526}*/:
                view.startAnimation(AnimationUtils.loadAnimation(this, C2425R.anim.downing_pause_anim));
                this.f10205a.removeMessages(7, view);
                this.f10205a.sendMessageDelayed(this.f10205a.obtainMessage(7, view), 300);
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
    private void m11710a(View view) {
        String fileUrl = this.f10218p.get(((Integer) view.getTag()).intValue()).getFileUrl();
        View view2 = this.f10224v.get(fileUrl);
        Button button = (Button) view2.findViewById(C2425R.C2427id.game_open_btn);
        ImageView imageView = (ImageView) view2.findViewById(C2425R.C2427id.game_download_iv);
        ImageView imageView2 = (ImageView) view2.findViewById(C2425R.C2427id.game_downing_pause);
        if (button.getText().equals("打开")) {
            for (Map.Entry<String, View> entry : this.f10225w.entrySet()) {
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
            imageView2.setVisibility(8);
            imageView.setVisibility(0);
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
    private void m11716b(View view) {
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
            m11719c(view);
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 6);
            intent.putExtra("url", this.f10218p.get(((Integer) view.getTag()).intValue()).getFileUrl());
            startService(intent);
        }
    }

    /* renamed from: c */
    private void m11719c(View view) {
        if (((DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", this.f10218p.get(((Integer) view.getTag()).intValue()).getFileUrl())) != null) {
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 4);
            intent.putExtra("url", this.f10218p.get(((Integer) view.getTag()).intValue()).getFileUrl());
            startService(intent);
        }
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setUrl(this.f10218p.get(((Integer) view.getTag()).intValue()).getFileUrl());
        downloadFile.setName(this.f10218p.get(((Integer) view.getTag()).intValue()).getTitle());
        downloadFile.setTags(this.f10218p.get(((Integer) view.getTag()).intValue()).getTags());
        downloadFile.setDownsize(0L);
        downloadFile.setFileTotalSize(this.f10218p.get(((Integer) view.getTag()).intValue()).getFileTotalSize());
        downloadFile.setCreateTime(Long.valueOf(System.currentTimeMillis()));
        downloadFile.setProductId(this.f10218p.get(((Integer) view.getTag()).intValue()).getSid());
        downloadFile.setProductType(Constant.f13162Q);
        downloadFile.setStateId(2);
        downloadFile.setPackageName(this.f10218p.get(((Integer) view.getTag()).intValue()).getPackageName());
        DownloadFileDao.m12152b().mo24215e((Context) this, downloadFile);
    }

    public void update() {
        if (System.currentTimeMillis() - this.f10214l < 3000) {
            this.f10213k.mo28398a("更新于:" + this.f10216n.format(new Date(System.currentTimeMillis())));
            return;
        }
        this.f10214l = System.currentTimeMillis();
        if (this.f10221s != -1) {
            this.f10219q = HttpUtil.m14289b(this, this.f10221s, this.f10204A, f10203d);
        }
    }

    public class AppInstallReceiver extends BroadcastReceiver {
        public AppInstallReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            View view;
            View view2;
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            StorageUtils.m14481a(BannerGameListActivity.this);
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") && StorageUtils.m14492c(context, schemeSpecificPart) && (view2 = (View) BannerGameListActivity.this.f10225w.get(schemeSpecificPart)) != null) {
                ((Button) view2.findViewById(C2425R.C2427id.game_open_btn)).setText("打开");
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(BannerGameListActivity.this, Constants.KEY_PACKAGE_NAME, schemeSpecificPart);
                if (downloadFile == null || downloadFile.getLocalFile() == null || downloadFile.getStateId().intValue() != 0 || !new File(downloadFile.getLocalFile()).exists() || (view = (View) BannerGameListActivity.this.f10224v.get(downloadFile.getUrl())) == null) {
                    View view3 = (View) BannerGameListActivity.this.f10225w.get(schemeSpecificPart);
                    if (view3 != null) {
                        Button button = (Button) view3.findViewById(C2425R.C2427id.game_open_btn);
                        button.setText("安装");
                        button.setVisibility(8);
                        ((ImageView) view3.findViewById(C2425R.C2427id.game_download_iv)).setVisibility(0);
                        return;
                    }
                    return;
                }
                ((Button) view.findViewById(C2425R.C2427id.game_open_btn)).setText("安装");
            }
        }
    }

    public class DowningReceiver extends BroadcastReceiver {
        public DowningReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            m11729a(intent);
        }

        /* renamed from: a */
        private void m11729a(Intent intent) {
            if (intent != null && intent.getAction().equals("com.filter.download.receiver")) {
                switch (intent.getIntExtra("type", -1)) {
                    case 0:
                        BannerGameListActivity.this.f10226x.removeMessages(1);
                        BannerGameListActivity.this.f10226x.sendMessage(BannerGameListActivity.this.f10226x.obtainMessage(1, intent));
                        return;
                    case 1:
                        String stringExtra = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra)) {
                            BannerGameListActivity.this.dealCompleteTask(stringExtra);
                            Log.m14400c(BannerGameListActivity.f10202c, "COMPLETE url..>" + stringExtra);
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
                        BannerGameListActivity.this.m11709a((BannerGameListActivity) intent);
                        return;
                    case 5:
                        BannerGameListActivity.this.dealContinueTask(intent);
                        return;
                    case 6:
                        String stringExtra2 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra2)) {
                            BannerGameListActivity.this.dealAddTask(stringExtra2);
                            return;
                        }
                        return;
                    case 9:
                        intent.getStringExtra("url");
                        return;
                    case 10:
                        BannerGameListActivity.this.m11714a((BannerGameListActivity) intent.getStringExtra("url"));
                        return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11709a(Intent intent) {
        dealProcessTask(intent);
        View view = this.f10224v.get(intent.getStringExtra("url"));
        if (view != null) {
            ((ImageView) view.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(0);
            ((ProgressWheel) view.findViewById(C2425R.C2427id.game_pb)).setVisibility(8);
        }
    }

    public void dealContinueTask(Intent intent) {
        View view = this.f10224v.get(intent.getStringExtra("url"));
        if (view != null) {
            ((ImageView) view.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(8);
            ((ProgressWheel) view.findViewById(C2425R.C2427id.game_pb)).setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11714a(String str) {
        View view = this.f10224v.get(str);
        if (view != null) {
            ProgressWheel progressWheel = (ProgressWheel) view.findViewById(C2425R.C2427id.game_pb);
            progressWheel.mo28351a("0");
            progressWheel.mo28349a(0);
            progressWheel.setVisibility(8);
            ((ImageView) view.findViewById(C2425R.C2427id.game_download_iv)).setVisibility(0);
            ((ImageView) view.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(8);
        }
    }

    public void dealCompleteTask(String str) {
        View view = this.f10224v.get(str);
        if (view != null) {
            Button button = (Button) view.findViewById(C2425R.C2427id.game_open_btn);
            ((ImageView) view.findViewById(C2425R.C2427id.game_download_iv)).setVisibility(8);
            button.setVisibility(0);
            ((ProgressWheel) view.findViewById(C2425R.C2427id.game_pb)).setVisibility(8);
            ((ImageView) view.findViewById(C2425R.C2427id.game_downing_pause)).setVisibility(8);
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(this, "url", str);
            if (downloadFile != null && StorageUtils.m14492c(this, downloadFile.getPackageName())) {
                button.setText("打开");
            }
        }
    }

    /* renamed from: d */
    private void m11721d(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (this.f10224v.get(this.f10218p.get(((Integer) view.getTag()).intValue()).getFileUrl()) != null) {
            intent.putExtra("type", 3);
            intent.putExtra("url", this.f10218p.get(((Integer) view.getTag()).intValue()).getFileUrl());
            startService(intent);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m11723e(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        if (this.f10224v.get(this.f10218p.get(((Integer) view.getTag()).intValue()).getFileUrl()) != null) {
            intent.putExtra("type", 5);
            intent.putExtra("url", this.f10218p.get(((Integer) view.getTag()).intValue()).getFileUrl());
            startService(intent);
        }
    }

    public void dealAddTask(String str) {
        View view = this.f10224v.get(str);
        if (view != null) {
            ProgressWheel progressWheel = (ProgressWheel) view.findViewById(C2425R.C2427id.game_pb);
            ((ImageView) view.findViewById(C2425R.C2427id.game_download_iv)).setVisibility(8);
            ((Button) view.findViewById(C2425R.C2427id.game_open_btn)).setVisibility(8);
            progressWheel.setVisibility(0);
            progressWheel.mo28351a("0");
            progressWheel.mo28349a(0);
        }
    }

    public void dealProcessTask(Intent intent) {
        View view = this.f10224v.get(intent.getStringExtra("url"));
        if (view != null) {
            ProgressWheel progressWheel = (ProgressWheel) view.findViewById(C2425R.C2427id.game_pb);
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
    private void m11725f(View view) {
        Intent intent = new Intent("com.action.download.download_service");
        View view2 = this.f10224v.get(((Game) view.getTag()).getFileUrl());
        if (view2 != null) {
            ProgressWheel progressWheel = (ProgressWheel) view2.findViewById(C2425R.C2427id.game_pb);
            this.f10223u = !this.f10223u;
            if (this.f10223u) {
                intent.putExtra("type", 5);
                intent.putExtra("url", ((Game) view.getTag()).getFileUrl());
                startService(intent);
                return;
            }
            intent.putExtra("type", 3);
            intent.putExtra("url", ((Game) view.getTag()).getFileUrl());
            startService(intent);
            progressWheel.mo28351a("暂停");
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    /* renamed from: com.gbcom.gwifi.functions.product.BannerGameListActivity$a */
    public class HandlerC2847a extends Handler {
        private HandlerC2847a() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    BannerGameListActivity.this.dealProcessTask((Intent) message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        unregisterReceiver(this.f10222t);
        unregisterReceiver(this.f10227y);
        super.onDestroy();
    }
}
