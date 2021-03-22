package com.gbcom.gwifi.functions.product;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.p248b.DownloadFileDao;
import com.gbcom.gwifi.p221a.RequestHandler;
import com.gbcom.gwifi.p221a.p223b.HttpRequest;
import com.gbcom.gwifi.p221a.p223b.HttpResponse;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.ResourceParse;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.StorageUtils;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.umeng.message.common.C6366a;
import java.p456io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownedOtherActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private RelativeLayout f10285a;

    /* renamed from: b */
    private TextView f10286b;

    /* renamed from: c */
    private TextView f10287c;

    /* renamed from: d */
    private ListView f10288d;

    /* renamed from: e */
    private LinearLayout f10289e;

    /* renamed from: f */
    private Button f10290f;

    /* renamed from: g */
    private Button f10291g;

    /* renamed from: h */
    private boolean f10292h = false;

    /* renamed from: i */
    private boolean f10293i = false;

    /* renamed from: j */
    private C2854a f10294j;

    /* renamed from: k */
    private List<DownloadFile> f10295k = new ArrayList();

    /* renamed from: l */
    private List<DownloadFile> f10296l = new ArrayList();

    /* renamed from: m */
    private String f10297m = "";

    /* renamed from: n */
    private String f10298n = "";

    /* renamed from: o */
    private AppInstallReceiver f10299o;

    /* renamed from: p */
    private HttpRequest f10300p;

    /* renamed from: q */
    private RelativeLayout f10301q;

    /* renamed from: r */
    private ProgressBar f10302r;

    /* renamed from: s */
    private TextView f10303s;

    /* renamed from: t */
    private RequestHandler<HttpRequest, HttpResponse> f10304t = new RequestHandler<HttpRequest, HttpResponse>() {
        /* class com.gbcom.gwifi.functions.product.DownedOtherActivity.C28531 */

        /* renamed from: a */
        public void mo24084b(HttpRequest cVar, HttpResponse dVar) {
            if (!DownedOtherActivity.this.isFinishing() && cVar == DownedOtherActivity.this.f10300p) {
                GBActivity.showMessageToast("请检查网络");
                DownedOtherActivity.this.dismissProgressDialog();
                DownedOtherActivity.this.finish();
            }
        }

        /* renamed from: b */
        public void mo24083a(HttpRequest cVar, HttpResponse dVar) {
            HashMap<String, Object> r;
            if (DownedOtherActivity.this.isFinishing() || cVar != DownedOtherActivity.this.f10300p || (r = ResourceParse.m14467r((byte[]) dVar.mo24086b())) == null) {
                return;
            }
            if (ResultCode.m14475a((Integer) r.get(AbstractC5718b.JSON_ERRORCODE))) {
                HashMap hashMap = (HashMap) r.get("data");
                if (5 == ((Integer) hashMap.get("point_change_category")).intValue()) {
                    GBActivity.showMessageToast("已经增加" + ((Object) ((Integer) hashMap.get("point_value"))) + Constant.f13309cr);
                    return;
                }
                return;
            }
            GBActivity.showMessageToast((String) r.get("resultMsg"));
        }

        /* renamed from: a */
        public void mo24082a(HttpRequest cVar, long j, long j2) {
        }

        /* renamed from: a */
        public void mo24081a(HttpRequest cVar) {
        }
    };

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "下载游戏or应用界面", C2425R.layout.app_downed_activity, true);
        m11753a();
        this.f10299o = new AppInstallReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(C6366a.f23181c);
        registerReceiver(this.f10299o, intentFilter);
    }

    /* renamed from: a */
    private void m11753a() {
        this.f10298n = getIntent().getStringExtra("buttonName");
        this.f10297m = getIntent().getStringExtra("title");
        this.f10285a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10285a.setOnClickListener(this);
        this.f10287c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10286b = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10287c.setText(this.f10297m);
        this.f10286b.setOnClickListener(this);
        this.f10288d = (ListView) findViewById(C2425R.C2427id.downed_lv);
        m11757c();
        this.f10294j = new C2854a();
        this.f10288d.setAdapter((ListAdapter) this.f10294j);
        this.f10289e = (LinearLayout) findViewById(C2425R.C2427id.downed_activity_layout);
        this.f10290f = (Button) findViewById(C2425R.C2427id.downed_all_cancel);
        this.f10291g = (Button) findViewById(C2425R.C2427id.downed_delete);
        this.f10290f.setOnClickListener(this);
        this.f10291g.setOnClickListener(this);
        this.f10301q = (RelativeLayout) findViewById(C2425R.C2427id.remaining_storage);
        this.f10302r = (ProgressBar) findViewById(C2425R.C2427id.game_detail_progress);
        this.f10303s = (TextView) findViewById(C2425R.C2427id.progress_tv);
        m11755b();
    }

    /* renamed from: b */
    private void m11755b() {
        this.f10303s.setText("手机已用空间为" + StorageUtils.m14490c(this) + ",可用空间为" + StorageUtils.m14487b(this));
        this.f10302r.setProgress(StorageUtils.m14493d(this));
    }

    /* renamed from: c */
    private List<DownloadFile> m11757c() {
        HashMap hashMap = new HashMap();
        if (this.f10297m.equals("游戏")) {
            hashMap.put("productType", Constant.f13162Q);
        } else if (this.f10297m.equals("应用")) {
            hashMap.put("productType", Constant.f13160O);
        }
        hashMap.put("parentId", -1L);
        hashMap.put("stateId", 0);
        List a = DownloadFileDao.m12152b().mo24204a((Context) this, (Map<String, Object>) hashMap);
        if (a != null && a.size() > 0) {
            this.f10295k.addAll(a);
        }
        return this.f10295k;
    }

    public void onClick(View view) {
        boolean z = true;
        switch (view.getId()) {
            case C2425R.C2427id.downed_all_cancel /*{ENCODED_INT: 2131755226}*/:
                if (this.f10293i) {
                    z = false;
                }
                this.f10293i = z;
                if (this.f10293i) {
                    this.f10290f.setText("取消");
                } else {
                    this.f10290f.setText("全选");
                }
                this.f10294j.notifyDataSetChanged();
                return;
            case C2425R.C2427id.downed_delete /*{ENCODED_INT: 2131755227}*/:
                if (this.f10296l == null || this.f10296l.size() <= 0) {
                    GBActivity.showMessageToast("亲！请先选中想删除的项哦");
                    return;
                }
                showProgressDialog("正在删除请稍等…");
                this.f10295k.removeAll(this.f10296l);
                DownloadFileDao.m12152b().delete((Context) this, (Collection) this.f10296l);
                for (DownloadFile downloadFile : this.f10296l) {
                    File file = new File(downloadFile.getLocalFile());
                    if (file.exists()) {
                        file.delete();
                    }
                }
                m11754a(this.f10296l);
                this.f10296l.clear();
                this.f10294j.notifyDataSetChanged();
                dismissProgressDialog();
                GBActivity.showMessageToast("删除成功！");
                return;
            case C2425R.C2427id.downed_item_layout /*{ENCODED_INT: 2131755230}*/:
            default:
                return;
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.title_edit_tv /*{ENCODED_INT: 2131755759}*/:
                if (this.f10292h) {
                    z = false;
                }
                this.f10292h = z;
                if (this.f10292h) {
                    this.f10286b.setText("取消");
                    this.f10289e.setVisibility(0);
                    this.f10301q.setVisibility(8);
                    this.f10291g.setEnabled(false);
                } else {
                    this.f10286b.setText("编辑");
                    this.f10293i = false;
                    this.f10290f.setText("全选");
                    this.f10289e.setVisibility(8);
                    this.f10301q.setVisibility(0);
                }
                this.f10294j.notifyDataSetChanged();
                return;
        }
    }

    /* renamed from: a */
    private void m11754a(List<DownloadFile> list) {
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i).getUrl();
        }
        Intent intent = new Intent("com.filter.resumeIcon.receiver");
        intent.putExtra("deleteUrls", strArr);
        sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.product.DownedOtherActivity$a */
    public class C2854a extends BaseAdapter {
        private C2854a() {
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            final DownloadFile downloadFile = (DownloadFile) DownedOtherActivity.this.f10295k.get(i);
            View inflate = LayoutInflater.from(DownedOtherActivity.this).inflate(C2425R.layout.app_downed_other_item, (ViewGroup) null);
            inflate.setTag(downloadFile.getUrl());
            CheckBox checkBox = (CheckBox) inflate.findViewById(C2425R.C2427id.downed_cb);
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.downed_other_item_img);
            TextView textView = (TextView) inflate.findViewById(16908310);
            TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.downed_other_tag);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.downed_other_size);
            TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.downed_other_time);
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C2425R.C2427id.downed_item_layout);
            final Button button = (Button) inflate.findViewById(C2425R.C2427id.downed_other_bt);
            imageView.setVisibility(0);
            ImageTools.m14149a(downloadFile.getImageUrl(), imageView, GBApplication.instance().options4);
            if (DownedOtherActivity.this.f10292h) {
                checkBox.setVisibility(0);
                if (DownedOtherActivity.this.f10293i) {
                    DownedOtherActivity.this.f10291g.setEnabled(true);
                    checkBox.setChecked(true);
                    DownedOtherActivity.this.f10296l.clear();
                    DownedOtherActivity.this.f10296l.addAll(DownedOtherActivity.this.f10295k);
                } else {
                    DownedOtherActivity.this.f10291g.setEnabled(false);
                    checkBox.setChecked(false);
                    DownedOtherActivity.this.f10296l.clear();
                }
            } else {
                checkBox.setVisibility(8);
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                /* class com.gbcom.gwifi.functions.product.DownedOtherActivity.C2854a.C28551 */

                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        DownedOtherActivity.this.f10291g.setEnabled(true);
                        DownedOtherActivity.this.f10296l.add(downloadFile);
                        return;
                    }
                    DownedOtherActivity.this.f10291g.setEnabled(false);
                    DownedOtherActivity.this.f10296l.remove(downloadFile);
                }
            });
            relativeLayout.setTag(Integer.valueOf(i));
            relativeLayout.setOnClickListener(DownedOtherActivity.this);
            button.setText(DownedOtherActivity.this.f10298n);
            if (DownedOtherActivity.this.f10298n.equals("安装") && StorageUtils.m14492c(DownedOtherActivity.this, downloadFile.getPackageName())) {
                button.setText("打开");
                button.setTextColor(DownedOtherActivity.this.getResources().getColor(C2425R.color.green_3));
            }
            button.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.product.DownedOtherActivity.C2854a.View$OnClickListenerC28562 */

                public void onClick(View view) {
                    if (button.getText().equals("安装")) {
                        if (new File(downloadFile.getLocalFile()).exists()) {
                            StorageUtils.m14482a(DownedOtherActivity.this, downloadFile.getLocalFile());
                            return;
                        }
                        DownloadFileDao.m12152b().delete(DownedOtherActivity.this, downloadFile);
                        DownedOtherActivity.this.f10295k.remove(downloadFile);
                        DownedOtherActivity.this.f10294j.notifyDataSetChanged();
                        GBActivity.showMessageToast("文件不存在，请重新下载哦^&^");
                    } else if (button.getText().equals("打开")) {
                        StorageUtils.m14488b(DownedOtherActivity.this, downloadFile.getPackageName());
                    }
                }
            });
            textView.setText(downloadFile.getName());
            ArrayList<String[]> b = C3467s.m14509b(downloadFile.getTags());
            String str = "";
            if (!b.isEmpty()) {
                str = b.get(0)[1];
            }
            textView2.setText(str);
            textView3.setText(C3467s.m14501a(downloadFile.getFileTotalSize().longValue()));
            textView4.setText(C3467s.m14502a(downloadFile.getFinishTime().longValue(), downloadFile.getCreateTime().longValue()));
            return inflate;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return DownedOtherActivity.this.f10295k.get(i);
        }

        public int getCount() {
            return DownedOtherActivity.this.f10295k.size();
        }
    }

    public class AppInstallReceiver extends BroadcastReceiver {
        public AppInstallReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            View findViewWithTag;
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            StorageUtils.m14481a(GBApplication.instance());
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                for (DownloadFile downloadFile : DownedOtherActivity.this.f10295k) {
                    if (downloadFile.getLocalFile() != null && schemeSpecificPart.equals(downloadFile.getPackageName())) {
                        if (downloadFile.getProductType().equals(Constant.f13160O)) {
                            UmengCount.queryAppInstallCount(GBApplication.instance(), downloadFile.getName());
                        } else if (downloadFile.getProductType().equals(Constant.f13162Q)) {
                            UmengCount.queryGameInstallCount(GBApplication.instance(), downloadFile.getName());
                        }
                        DownedOtherActivity.this.f10300p = HttpUtil.m14276a(DownedOtherActivity.this, downloadFile.getProductType(), downloadFile.getProductId(), downloadFile.getName(), downloadFile.getAppPointsReward(), DownedOtherActivity.this.f10304t);
                        View findViewWithTag2 = DownedOtherActivity.this.f10288d.findViewWithTag(downloadFile.getUrl());
                        if (findViewWithTag2 != null) {
                            Button button = (Button) findViewWithTag2.findViewById(C2425R.C2427id.downed_other_bt);
                            button.setText("打开");
                            button.setTextColor(DownedOtherActivity.this.getResources().getColor(C2425R.color.green_3));
                        }
                    }
                }
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                for (DownloadFile downloadFile2 : DownedOtherActivity.this.f10295k) {
                    if (!(downloadFile2.getLocalFile() == null || !schemeSpecificPart.equals(downloadFile2.getPackageName()) || (findViewWithTag = DownedOtherActivity.this.f10288d.findViewWithTag(downloadFile2.getUrl())) == null)) {
                        Button button2 = (Button) findViewWithTag.findViewById(C2425R.C2427id.downed_other_bt);
                        button2.setText("安装");
                        button2.setTextColor(DownedOtherActivity.this.getResources().getColor(C2425R.color.blue_4));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        unregisterReceiver(this.f10299o);
        super.onDestroy();
    }
}
