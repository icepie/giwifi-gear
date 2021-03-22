package com.gbcom.gwifi.functions.profile;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.gbcom.gwifi.functions.download.MyIntents;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.p248b.DownloadFileDao;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.StorageUtils;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyDownloadDetailActivity extends GBActivity implements View.OnClickListener {
    public static final int DEAL_PAUSE = 2;
    public static final int DEAL_PROCESS = 1;

    /* renamed from: a */
    private RelativeLayout f10881a;

    /* renamed from: b */
    private TextView f10882b;

    /* renamed from: c */
    private TextView f10883c;

    /* renamed from: d */
    private LinearLayout f10884d;

    /* renamed from: e */
    private TextView f10885e;

    /* renamed from: f */
    private RelativeLayout f10886f;

    /* renamed from: g */
    private ListView f10887g;

    /* renamed from: h */
    private RelativeLayout f10888h;

    /* renamed from: i */
    private ProgressBar f10889i;

    /* renamed from: j */
    private TextView f10890j;

    /* renamed from: k */
    private Button f10891k;

    /* renamed from: l */
    private boolean f10892l = false;

    /* renamed from: m */
    private boolean f10893m = false;

    /* renamed from: n */
    private boolean f10894n = false;

    /* renamed from: o */
    private List<DownloadFile> f10895o = new ArrayList();

    /* renamed from: p */
    private List<DownloadFile> f10896p = new ArrayList();

    /* renamed from: q */
    private List<DownloadFile> f10897q = new ArrayList();

    /* renamed from: r */
    private C2955a f10898r;

    /* renamed from: s */
    private HandlerC2960c f10899s;

    /* renamed from: t */
    private HandlerC2959b f10900t;

    /* renamed from: u */
    private DowningReceiver f10901u;

    /* renamed from: v */
    private ImageView f10902v;

    /* renamed from: w */
    private ImageView f10903w;

    /* renamed from: x */
    private CheckBox f10904x;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "我的下载详情界面", C2425R.layout.my_down_detail_activity, true);
        m12274a();
    }

    /* renamed from: a */
    private void m12274a() {
        this.f10881a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10881a.setOnClickListener(this);
        this.f10883c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10882b = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10883c.setText("我的下载");
        this.f10882b.setOnClickListener(this);
        this.f10882b.setVisibility(0);
        this.f10884d = (LinearLayout) findViewById(C2425R.C2427id.cachemore_layout);
        this.f10902v = (ImageView) findViewById(C2425R.C2427id.my_down_pause);
        this.f10903w = (ImageView) findViewById(C2425R.C2427id.my_down_continue);
        this.f10904x = (CheckBox) findViewById(C2425R.C2427id.downed_cb);
        this.f10904x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class com.gbcom.gwifi.functions.profile.MyDownloadDetailActivity.C29541 */

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    MyDownloadDetailActivity.this.f10894n = true;
                    MyDownloadDetailActivity.this.f10898r.notifyDataSetChanged();
                    return;
                }
                MyDownloadDetailActivity.this.f10894n = false;
                MyDownloadDetailActivity.this.f10898r.notifyDataSetChanged();
            }
        });
        this.f10885e = (TextView) findViewById(C2425R.C2427id.pause_or_continue);
        this.f10886f = (RelativeLayout) findViewById(C2425R.C2427id.down_detail_bt_Layout);
        this.f10887g = (ListView) findViewById(C2425R.C2427id.child_downed_lv);
        this.f10888h = (RelativeLayout) findViewById(C2425R.C2427id.remaining_storage);
        this.f10889i = (ProgressBar) findViewById(C2425R.C2427id.game_detail_progress);
        this.f10890j = (TextView) findViewById(C2425R.C2427id.progress_tv);
        this.f10891k = (Button) findViewById(C2425R.C2427id.child_downed_delete);
        this.f10884d.setOnClickListener(this);
        this.f10886f.setOnClickListener(this);
        this.f10891k.setOnClickListener(this);
        this.f10898r = new C2955a();
        this.f10887g.setAdapter((ListAdapter) this.f10898r);
        this.f10901u = new DowningReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.filter.download.receiver");
        registerReceiver(this.f10901u, intentFilter);
        this.f10899s = new HandlerC2960c();
        this.f10900t = new HandlerC2959b();
        m12280b();
    }

    /* renamed from: b */
    private void m12280b() {
        this.f10890j.setText("手机已用空间为" + StorageUtils.m14490c(this) + ",可用空间为" + StorageUtils.m14487b(this));
        this.f10889i.setProgress(StorageUtils.m14493d(this));
        m12289e();
        m12291f();
        if (this.f10896p.size() <= 0) {
            this.f10893m = true;
            this.f10885e.setText("全部开启");
            m12287d();
        }
    }

    public void onClick(View view) {
        boolean z = true;
        int i = 0;
        switch (view.getId()) {
            case C2425R.C2427id.cachemore_layout /*{ENCODED_INT: 2131755740}*/:
                if (this.f10893m) {
                    z = false;
                }
                this.f10893m = z;
                if (this.f10893m) {
                    this.f10885e.setText("全部开始");
                    m12287d();
                    while (i < this.f10895o.size()) {
                        m12275a(this.f10895o.get(i));
                        i++;
                    }
                    return;
                }
                this.f10885e.setText("全部暂停");
                m12286c();
                while (i < this.f10895o.size()) {
                    m12281b(this.f10895o.get(i));
                    i++;
                }
                return;
            case C2425R.C2427id.down_detail_bt_Layout /*{ENCODED_INT: 2131755744}*/:
                this.f10894n = !this.f10894n;
                if (this.f10894n) {
                    this.f10904x.setChecked(true);
                } else {
                    this.f10904x.setChecked(false);
                }
                this.f10898r.notifyDataSetChanged();
                return;
            case C2425R.C2427id.child_downed_delete /*{ENCODED_INT: 2131755747}*/:
                if (this.f10897q.size() > 0) {
                    showProgressDialog("正在删除请稍等…");
                    this.f10895o.removeAll(this.f10897q);
                    DownloadFileDao.m12152b().delete((Context) this, (Collection) this.f10897q);
                    Intent intent = new Intent("com.action.download.download_service");
                    while (i < this.f10897q.size()) {
                        intent.putExtra("type", 4);
                        intent.putExtra("url", this.f10897q.get(i).getUrl());
                        startService(intent);
                        i++;
                    }
                    this.f10897q.clear();
                    this.f10898r.notifyDataSetChanged();
                    dismissProgressDialog();
                    GBActivity.showMessageToast("删除成功！");
                    return;
                }
                GBActivity.showMessageToast("请选中要删除的项");
                return;
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.title_edit_tv /*{ENCODED_INT: 2131755759}*/:
                if (this.f10892l) {
                    z = false;
                }
                this.f10892l = z;
                if (this.f10892l) {
                    this.f10882b.setText("取消");
                    this.f10888h.setVisibility(8);
                    this.f10891k.setVisibility(0);
                    this.f10884d.setVisibility(8);
                    this.f10886f.setVisibility(0);
                } else {
                    this.f10894n = false;
                    this.f10904x.setChecked(false);
                    this.f10882b.setText("编辑");
                    this.f10888h.setVisibility(0);
                    this.f10891k.setVisibility(8);
                    this.f10884d.setVisibility(0);
                    this.f10886f.setVisibility(8);
                }
                this.f10898r.notifyDataSetChanged();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12286c() {
        this.f10902v.setVisibility(0);
        this.f10903w.setVisibility(8);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m12287d() {
        this.f10902v.setVisibility(8);
        this.f10903w.setVisibility(0);
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.profile.MyDownloadDetailActivity$a */
    public class C2955a extends BaseAdapter {
        private C2955a() {
        }

        public int getCount() {
            return MyDownloadDetailActivity.this.f10895o.size();
        }

        public Object getItem(int i) {
            return MyDownloadDetailActivity.this.f10895o.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int i2;
            final DownloadFile downloadFile = (DownloadFile) MyDownloadDetailActivity.this.f10895o.get(i);
            View inflate = MyDownloadDetailActivity.this.getLayoutInflater().inflate(C2425R.layout.download_detail_adapter, (ViewGroup) null);
            inflate.setTag(downloadFile.getUrl());
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C2425R.C2427id.downed_item_layout);
            CheckBox checkBox = (CheckBox) inflate.findViewById(C2425R.C2427id.downed_cb);
            final ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.downed_other_item_img);
            TextView textView = (TextView) inflate.findViewById(16908310);
            TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.downed_speed);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.downing_rate);
            TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.downing_pause);
            ProgressBar progressBar = (ProgressBar) inflate.findViewById(C2425R.C2427id.downing_detail_progress);
            linearLayout.setTag(Integer.valueOf(i));
            linearLayout.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.profile.MyDownloadDetailActivity.C2955a.View$OnClickListenerC29561 */

                public void onClick(View view) {
                    boolean z = true;
                    DownloadFile downloadFile = (DownloadFile) MyDownloadDetailActivity.this.f10895o.get(((Integer) view.getTag()).intValue());
                    if (downloadFile.getStateId().intValue() == 1) {
                        z = false;
                    }
                    MyDownloadDetailActivity.this.f10893m = z;
                    if (z) {
                        MyDownloadDetailActivity.this.m12275a((MyDownloadDetailActivity) downloadFile);
                        MyDownloadDetailActivity.this.f10885e.setText("全部开始");
                        MyDownloadDetailActivity.this.m12287d();
                        return;
                    }
                    MyDownloadDetailActivity.this.m12281b((MyDownloadDetailActivity) downloadFile);
                    MyDownloadDetailActivity.this.f10885e.setText("全部暂停");
                    MyDownloadDetailActivity.this.m12286c();
                }
            });
            if (MyDownloadDetailActivity.this.f10892l) {
                checkBox.setVisibility(0);
                if (MyDownloadDetailActivity.this.f10894n) {
                    checkBox.setChecked(true);
                    MyDownloadDetailActivity.this.f10897q.clear();
                    MyDownloadDetailActivity.this.f10897q.addAll(MyDownloadDetailActivity.this.f10895o);
                } else {
                    checkBox.setChecked(false);
                    MyDownloadDetailActivity.this.f10897q.clear();
                }
            } else {
                checkBox.setVisibility(8);
            }
            if (downloadFile.getStateId().intValue() == 1) {
                textView4.setText("暂停");
                textView4.setVisibility(0);
            } else {
                textView4.setVisibility(8);
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                /* class com.gbcom.gwifi.functions.profile.MyDownloadDetailActivity.C2955a.C29572 */

                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        MyDownloadDetailActivity.this.f10897q.add(downloadFile);
                    } else {
                        MyDownloadDetailActivity.this.f10897q.remove(downloadFile);
                    }
                }
            });
            ImageTools.m14150a(downloadFile.getImageUrl(), imageView, GBApplication.instance().options4, new ImageLoadingListener() {
                /* class com.gbcom.gwifi.functions.profile.MyDownloadDetailActivity.C2955a.C29583 */

                @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                public void onLoadingStarted(String str, View view) {
                }

                @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                public void onLoadingFailed(String str, View view, FailReason failReason) {
                }

                @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }

                @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                }
            });
            textView.setText(downloadFile.getName());
            textView2.setText(C3467s.m14501a(downloadFile.getDownsize().longValue()) + " / " + C3467s.m14501a(downloadFile.getFileTotalSize().longValue()));
            textView3.setVisibility(8);
            if (downloadFile.getFileTotalSize().longValue() != 0) {
                i2 = (int) ((downloadFile.getDownsize().longValue() * 100) / downloadFile.getFileTotalSize().longValue());
            } else {
                i2 = 0;
            }
            progressBar.setProgress(i2);
            return inflate;
        }

        /* renamed from: a */
        public void mo25950a(View view, String str, String str2, String str3, String str4) {
            TextView textView = (TextView) view.findViewById(C2425R.C2427id.downing_rate);
            ((TextView) view.findViewById(C2425R.C2427id.downed_speed)).setText(str2);
            textView.setText(str4);
            textView.setVisibility(0);
            ((TextView) view.findViewById(C2425R.C2427id.downing_pause)).setVisibility(8);
            ((ProgressBar) view.findViewById(C2425R.C2427id.downing_detail_progress)).setProgress(Integer.parseInt(str3));
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.profile.MyDownloadDetailActivity$d */
    private class C2961d {
        private C2961d() {
        }
    }

    public class DowningReceiver extends BroadcastReceiver {
        public DowningReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Log.m14400c(CommonNetImpl.TAG, "DowningReceiver广播");
            m12299a(intent);
        }

        /* renamed from: a */
        private void m12299a(Intent intent) {
            if (intent != null && intent.getAction().equals("com.filter.download.receiver")) {
                int intExtra = intent.getIntExtra("type", -1);
                Log.m14400c("tag11", intExtra + "");
                String stringExtra = intent.getStringExtra("url");
                switch (intExtra) {
                    case 0:
                        MyDownloadDetailActivity.this.f10899s.sendMessage(MyDownloadDetailActivity.this.f10899s.obtainMessage(1, intent));
                        return;
                    case 1:
                        MyDownloadDetailActivity.this.m12283b((MyDownloadDetailActivity) stringExtra);
                        MyDownloadDetailActivity.this.f10898r.notifyDataSetChanged();
                        return;
                    case 2:
                    case 4:
                    case 7:
                    case 8:
                    case 9:
                    default:
                        return;
                    case 3:
                        MyDownloadDetailActivity.this.m12289e();
                        MyDownloadDetailActivity.this.f10900t.sendMessageDelayed(MyDownloadDetailActivity.this.f10900t.obtainMessage(2, MyDownloadDetailActivity.this.f10887g.findViewWithTag(stringExtra)), 300);
                        return;
                    case 5:
                        MyDownloadDetailActivity.this.m12289e();
                        MyDownloadDetailActivity.this.dealContinueView(MyDownloadDetailActivity.this.f10887g.findViewWithTag(stringExtra));
                        return;
                    case 6:
                        MyDownloadDetailActivity.this.m12289e();
                        MyDownloadDetailActivity.this.f10898r.notifyDataSetChanged();
                        return;
                    case 10:
                        MyDownloadDetailActivity.this.m12283b((MyDownloadDetailActivity) stringExtra);
                        MyDownloadDetailActivity.this.f10898r.notifyDataSetChanged();
                        return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m12289e() {
        try {
            Collection<? extends DownloadFile> query = DownloadFileDao.m12152b().mo24212d(this).where().mo33373ne("stateId", 0).query();
            if (query == null) {
                query = new ArrayList<>();
            }
            this.f10895o.clear();
            this.f10895o.addAll(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: f */
    private void m12291f() {
        try {
            Collection<? extends DownloadFile> query = DownloadFileDao.m12152b().mo24212d(this).where().mo33357eq("stateId", 2).query();
            if (query == null) {
                query = new ArrayList<>();
            }
            this.f10896p.clear();
            this.f10896p.addAll(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12275a(DownloadFile downloadFile) {
        Intent intent = new Intent("com.action.download.download_service");
        intent.putExtra("type", 3);
        intent.putExtra("url", downloadFile.getUrl());
        startService(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12281b(DownloadFile downloadFile) {
        Intent intent = new Intent("com.action.download.download_service");
        intent.putExtra("type", 5);
        intent.putExtra("url", downloadFile.getUrl());
        startService(intent);
    }

    /* renamed from: a */
    private DownloadFile m12272a(String str) {
        for (int i = 0; i < this.f10895o.size(); i++) {
            if (this.f10895o.get(i).getUrl().equals(str)) {
                return this.f10895o.get(i);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12283b(String str) {
        for (int i = 0; i < this.f10895o.size(); i++) {
            if (this.f10895o.get(i).getUrl().equals(str)) {
                this.f10895o.remove(i);
            }
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    /* renamed from: com.gbcom.gwifi.functions.profile.MyDownloadDetailActivity$c */
    public class HandlerC2960c extends Handler {
        private HandlerC2960c() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    String stringExtra = ((Intent) message.obj).getStringExtra("url");
                    View findViewWithTag = MyDownloadDetailActivity.this.f10887g.findViewWithTag(stringExtra);
                    if (findViewWithTag != null) {
                        MyDownloadDetailActivity.this.f10898r.mo25950a(findViewWithTag, stringExtra, ((Intent) message.obj).getStringExtra(MyIntents.f9254b), ((Intent) message.obj).getStringExtra(MyIntents.f9255c), ((Intent) message.obj).getStringExtra(MyIntents.f9260h));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    /* renamed from: com.gbcom.gwifi.functions.profile.MyDownloadDetailActivity$b */
    public class HandlerC2959b extends Handler {
        private HandlerC2959b() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 2:
                    MyDownloadDetailActivity.this.dealPauseView((View) message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    public void dealPauseView(View view) {
        if (view != null) {
            ((TextView) view.findViewById(C2425R.C2427id.downing_pause)).setVisibility(0);
            ((TextView) view.findViewById(C2425R.C2427id.downing_rate)).setVisibility(8);
        }
    }

    public void dealContinueView(View view) {
        if (view != null) {
            ((TextView) view.findViewById(C2425R.C2427id.downing_pause)).setVisibility(8);
            ((TextView) view.findViewById(C2425R.C2427id.downing_rate)).setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        unregisterReceiver(this.f10901u);
        super.onDestroy();
    }
}
