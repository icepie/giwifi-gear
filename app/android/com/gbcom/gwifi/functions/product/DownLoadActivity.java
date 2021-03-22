package com.gbcom.gwifi.functions.product;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.domain.ProductFile;
import com.gbcom.gwifi.functions.product.p248b.DownloadFileDao;
import com.gbcom.gwifi.functions.product.p248b.ProductFileDao;
import com.gbcom.gwifi.functions.product.view.CacheButton;
import com.gbcom.gwifi.functions.profile.MyDownloadActivity;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.StorageUtils;
import java.p456io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownLoadActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: b */
    private static final String f10259b = "DownLoadActivity";

    /* renamed from: a */
    CacheButton.AbstractC2946b f10260a = new CacheButton.AbstractC2946b() {
        /* class com.gbcom.gwifi.functions.product.DownLoadActivity.C28522 */

        @Override // com.gbcom.gwifi.functions.product.view.CacheButton.AbstractC2946b
        /* renamed from: a */
        public void mo25382a(View view) {
            CacheButton cacheButton = (CacheButton) view;
            if (cacheButton.mo25921d()) {
                DownLoadActivity.this.f10275q.add((Integer) view.getTag());
                cacheButton.mo25919b();
            } else if (cacheButton.mo25922e()) {
                DownLoadActivity.this.f10275q.remove((Integer) view.getTag());
                cacheButton.mo25916a();
            }
        }
    };

    /* renamed from: c */
    private RelativeLayout f10261c;

    /* renamed from: d */
    private TextView f10262d;

    /* renamed from: e */
    private TextView f10263e;

    /* renamed from: f */
    private Button f10264f;

    /* renamed from: g */
    private GridView f10265g;

    /* renamed from: h */
    private String[] f10266h;

    /* renamed from: i */
    private String[] f10267i;

    /* renamed from: j */
    private List<String> f10268j = new ArrayList();

    /* renamed from: k */
    private List<String> f10269k = new ArrayList();

    /* renamed from: l */
    private String f10270l = "";

    /* renamed from: m */
    private int f10271m = -1;

    /* renamed from: n */
    private int f10272n = 0;

    /* renamed from: o */
    private String f10273o = "";

    /* renamed from: p */
    private long f10274p = 0;

    /* renamed from: q */
    private List<Integer> f10275q = new ArrayList();

    /* renamed from: r */
    private DowningReceiver f10276r;

    /* renamed from: s */
    private List<DownloadFile> f10277s;

    /* renamed from: t */
    private boolean f10278t = false;

    /* renamed from: u */
    private String f10279u = "";

    /* renamed from: v */
    private Integer f10280v;

    /* renamed from: w */
    private BaseAdapter f10281w = new BaseAdapter() {
        /* class com.gbcom.gwifi.functions.product.DownLoadActivity.C28511 */

        public int getCount() {
            return DownLoadActivity.this.f10269k.size();
        }

        public Object getItem(int i) {
            return DownLoadActivity.this.f10269k.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            String str = (String) DownLoadActivity.this.f10269k.get(i);
            View inflate = LayoutInflater.from(DownLoadActivity.this).inflate(C2425R.layout.download_item, viewGroup, false);
            inflate.setTag(Integer.valueOf(i));
            CacheButton cacheButton = (CacheButton) inflate.findViewById(C2425R.C2427id.down_layout);
            if (Integer.parseInt(str) < 10) {
                cacheButton.mo25918a("0" + str);
            } else {
                cacheButton.mo25918a(str);
            }
            cacheButton.mo25917a(DownLoadActivity.this.f10260a);
            cacheButton.setTag(Integer.valueOf(i));
            if (DownLoadActivity.this.f10277s.size() > 0) {
                for (DownloadFile downloadFile : DownLoadActivity.this.f10277s) {
                    if (((String) DownLoadActivity.this.f10268j.get(i)).equals(downloadFile.getUrl()) && downloadFile.getStateId().intValue() == 0) {
                        cacheButton.mo25920c();
                        cacheButton.setEnabled(false);
                    }
                    if (((String) DownLoadActivity.this.f10268j.get(i)).equals(downloadFile.getUrl()) && downloadFile.getStateId().intValue() == 2) {
                        cacheButton.mo25919b();
                        cacheButton.setEnabled(false);
                    }
                }
            }
            return inflate;
        }
    };

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "下载缓存界面", C2425R.layout.download_activity, true);
        m11740a();
        this.f10276r = new DowningReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.filter.download.receiver");
        registerReceiver(this.f10276r, intentFilter);
    }

    /* renamed from: a */
    private void m11740a() {
        this.f10266h = getIntent().getStringArrayExtra("urls");
        Collections.addAll(this.f10268j, this.f10266h);
        this.f10267i = getIntent().getStringArrayExtra("nos");
        Collections.addAll(this.f10269k, this.f10267i);
        this.f10270l = getIntent().getStringExtra("title");
        this.f10279u = getIntent().getStringExtra("imageUrl");
        this.f10280v = Integer.valueOf(getIntent().getIntExtra("totalEpisode", 0));
        this.f10271m = getIntent().getIntExtra("productId", -1);
        this.f10272n = getIntent().getIntExtra("type", 0);
        this.f10273o = getIntent().getStringExtra("tags");
        this.f10274p = getIntent().getLongExtra("totalSize", 0);
        Log.m14400c(f10259b, "urls.size:" + this.f10268j.size());
        this.f10261c = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10261c.setOnClickListener(this);
        this.f10263e = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10262d = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10263e.setText(this.f10270l);
        this.f10262d.setText("缓存列表");
        this.f10262d.setOnClickListener(this);
        this.f10277s = new ArrayList();
        this.f10264f = (Button) findViewById(C2425R.C2427id.start_catch);
        this.f10265g = (GridView) findViewById(C2425R.C2427id.download_gv);
        if (this.f10272n != 0) {
            this.f10264f.setVisibility(0);
        }
        this.f10264f.setOnClickListener(this);
        this.f10265g.setAdapter((ListAdapter) this.f10281w);
        m11744b();
    }

    /* renamed from: b */
    private void m11744b() {
        this.f10277s = DownloadFileDao.m12152b().mo24203a((Context) this, "productId", (Object) Integer.valueOf(this.f10271m));
        if (this.f10277s == null) {
            this.f10277s = new ArrayList();
        }
        this.f10281w.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.start_catch /*{ENCODED_INT: 2131755350}*/:
                if (this.f10275q.size() > 0) {
                    m11746c();
                    return;
                } else if (!this.f10278t) {
                    GBActivity.showMessageToast("请先选择要下载的项哦");
                    return;
                } else {
                    GBActivity.showMessageToast("正在努力下载中！");
                    return;
                }
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.title_edit_tv /*{ENCODED_INT: 2131755759}*/:
                startActivity(new Intent(GBApplication.instance(), MyDownloadActivity.class));
                return;
            default:
                return;
        }
    }

    /* renamed from: c */
    private void m11746c() {
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
            GBActivity.showMessageToast("已添加到缓存列表");
            m11748d();
            m11744b();
        }
    }

    /* renamed from: d */
    private void m11748d() {
        DownloadFile downloadFile;
        HashMap hashMap = new HashMap();
        hashMap.put("productId", Integer.valueOf(this.f10271m));
        hashMap.put("parentId", -1L);
        List a = DownloadFileDao.m12152b().mo24204a((Context) this, (Map<String, Object>) hashMap);
        if (a.size() > 0) {
            downloadFile = (DownloadFile) a.get(0);
        } else {
            DownloadFile downloadFile2 = new DownloadFile();
            downloadFile2.setName(this.f10270l);
            downloadFile2.setImageUrl(this.f10279u);
            downloadFile2.setTotalEpisode(this.f10280v);
            downloadFile2.setTags(this.f10273o);
            downloadFile2.setCreateTime(Long.valueOf(System.currentTimeMillis()));
            downloadFile2.setProductId(Integer.valueOf(this.f10271m));
            downloadFile2.setProductType(Constant.f13158M);
            if (this.f10272n == 2) {
                downloadFile2.setIsTV(true);
            } else {
                downloadFile2.setIsTV(false);
            }
            DownloadFileDao.m12152b().mo24215e((Context) this, downloadFile2);
            if (this.f10272n == 2) {
                for (int i = 0; i < this.f10269k.size(); i++) {
                    ProductFile productFile = new ProductFile();
                    productFile.setTitle(this.f10269k.get(i));
                    productFile.setUrl(this.f10268j.get(i));
                    productFile.setProductId(Integer.valueOf(this.f10271m));
                    productFile.setTags(this.f10273o);
                    productFile.setFileTotalSize(Long.valueOf(this.f10274p / ((long) this.f10275q.size())));
                    ProductFileDao.m12169b().mo24215e((Context) this, productFile);
                }
            }
            downloadFile = downloadFile2;
        }
        for (int i2 = 0; i2 < this.f10275q.size(); i2++) {
            DownloadFile downloadFile3 = new DownloadFile();
            if (this.f10272n == 2) {
                downloadFile3.setIsTV(true);
                downloadFile3.setName(this.f10270l + "第" + this.f10269k.get(this.f10275q.get(i2).intValue()) + "集");
            } else {
                downloadFile3.setIsTV(false);
                downloadFile3.setName(this.f10270l);
            }
            downloadFile3.setCurrentNo(this.f10269k.get(this.f10275q.get(i2).intValue()));
            downloadFile3.setUrl(this.f10268j.get(this.f10275q.get(i2).intValue()));
            downloadFile3.setTags(this.f10273o);
            downloadFile3.setImageUrl(this.f10279u);
            downloadFile3.setTotalEpisode(this.f10280v);
            downloadFile3.setDownsize(0L);
            downloadFile3.setFileTotalSize(Long.valueOf(this.f10274p / ((long) this.f10275q.size())));
            downloadFile3.setCreateTime(Long.valueOf(System.currentTimeMillis()));
            downloadFile3.setProductId(Integer.valueOf(this.f10271m));
            downloadFile3.setProductType(Constant.f13158M);
            downloadFile3.setParentId(downloadFile.getId());
            downloadFile3.setStateId(2);
            DownloadFileDao.m12152b().mo24215e((Context) this, downloadFile3);
            Intent intent = new Intent("com.action.download.download_service");
            intent.putExtra("type", 6);
            intent.putExtra("url", this.f10268j.get(this.f10275q.get(i2).intValue()));
            Log.m14400c("TAG", "DownloadActivity>.." + this.f10268j.get(this.f10275q.get(i2).intValue()));
            startService(intent);
        }
        this.f10275q.clear();
        this.f10278t = true;
    }

    public class DowningReceiver extends BroadcastReceiver {
        public DowningReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Log.m14400c(DownLoadActivity.f10259b, "DowningReceiver广播");
            m11750a(intent);
        }

        /* renamed from: a */
        private void m11750a(Intent intent) {
            if (intent != null && intent.getAction().equals("com.filter.download.receiver")) {
                switch (intent.getIntExtra("type", -1)) {
                    case 1:
                        String stringExtra = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra)) {
                            DownLoadActivity.this.dealCompleteTask(stringExtra);
                            Log.m14400c(DownLoadActivity.f10259b, "COMPLETE url..>" + stringExtra);
                            return;
                        }
                        return;
                    case 6:
                        String stringExtra2 = intent.getStringExtra("url");
                        if (!TextUtils.isEmpty(stringExtra2)) {
                            DownLoadActivity.this.dealAddTask(stringExtra2);
                            return;
                        }
                        return;
                    case 9:
                        intent.getStringExtra("url");
                        return;
                    case 10:
                        DownLoadActivity.this.m11742a((DownLoadActivity) intent.getStringExtra("url"));
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void dealAddTask(String str) {
        View findViewWithTag;
        for (int i = 0; i < this.f10268j.size(); i++) {
            if (str.equals(this.f10268j.get(i)) && (findViewWithTag = this.f10265g.findViewWithTag(Integer.valueOf(i))) != null) {
                ((CacheButton) findViewWithTag.findViewById(C2425R.C2427id.down_layout)).setClickable(false);
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11742a(String str) {
        View findViewWithTag;
        for (int i = 0; i < this.f10268j.size(); i++) {
            if (str.equals(this.f10268j.get(i)) && (findViewWithTag = this.f10265g.findViewWithTag(Integer.valueOf(i))) != null) {
                CacheButton cacheButton = (CacheButton) findViewWithTag.findViewById(C2425R.C2427id.down_layout);
                if (cacheButton.mo25922e()) {
                    cacheButton.mo25916a();
                    return;
                }
                return;
            }
        }
    }

    public void dealCompleteTask(String str) {
        View findViewWithTag;
        for (int i = 0; i < this.f10268j.size(); i++) {
            if (str.equals(this.f10268j.get(i)) && (findViewWithTag = this.f10265g.findViewWithTag(Integer.valueOf(i))) != null) {
                ((CacheButton) findViewWithTag.findViewById(C2425R.C2427id.down_layout)).mo25920c();
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        unregisterReceiver(this.f10276r);
        super.onDestroy();
    }
}
