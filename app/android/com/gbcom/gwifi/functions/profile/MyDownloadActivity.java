package com.gbcom.gwifi.functions.profile;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.functions.download.MyIntents;
import com.gbcom.gwifi.functions.product.DownedOtherActivity;
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
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.umeng.message.proguard.MessageStore;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import p041c.Request;

public class MyDownloadActivity extends GBActivity implements View.OnClickListener {
    public static final int DEAL_PROCESS = 1;
    public static final int PAGE = 1;
    public static final int PAGE_NEXT = 2;

    /* renamed from: a */
    private static final String f10847a = "MyDownloadActivity";

    /* renamed from: A */
    private TextView f10848A;

    /* renamed from: B */
    private List<DownloadFile> f10849B = new ArrayList();
    @SuppressLint({"HandlerLeak"})

    /* renamed from: C */
    private Handler f10850C = new Handler() {
        /* class com.gbcom.gwifi.functions.profile.MyDownloadActivity.HandlerC29511 */

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    MyDownloadActivity.this.m12252a((MyDownloadActivity) 1);
                    MyDownloadActivity.this.f10850C.removeMessages(1);
                    return;
                case 2:
                    MyDownloadActivity.this.m12252a((MyDownloadActivity) 2);
                    MyDownloadActivity.this.f10850C.removeMessages(2);
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: D */
    private OkRequestHandler<String> f10851D = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.profile.MyDownloadActivity.C29522 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (!MyDownloadActivity.this.isFinishing() && abVar == MyDownloadActivity.this.f10868r) {
                GBActivity.showMessageToast("请检查网络");
                MyDownloadActivity.this.dismissProgressDialog();
                MyDownloadActivity.this.finish();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            HashMap<String, Object> s;
            if (!MyDownloadActivity.this.isFinishing() && abVar == MyDownloadActivity.this.f10868r && (s = ResourceParse.m14468s(str.getBytes())) != null && ResultCode.m14475a((Integer) s.get(AbstractC5718b.JSON_ERRORCODE))) {
                Log.m14400c(MyDownloadActivity.f10847a, "下载次数增加成功");
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    private RelativeLayout f10852b;

    /* renamed from: c */
    private TextView f10853c;

    /* renamed from: d */
    private TextView f10854d;

    /* renamed from: e */
    private LinearLayout f10855e;

    /* renamed from: f */
    private DowningReceiver f10856f;

    /* renamed from: g */
    private LinearLayout f10857g;

    /* renamed from: h */
    private TextView f10858h;

    /* renamed from: i */
    private LinearLayout f10859i;

    /* renamed from: j */
    private TextView f10860j;

    /* renamed from: k */
    private LinearLayout f10861k;

    /* renamed from: l */
    private TextView f10862l;

    /* renamed from: m */
    private LinearLayout f10863m;

    /* renamed from: n */
    private TextView f10864n;

    /* renamed from: o */
    private boolean f10865o = false;

    /* renamed from: p */
    private boolean f10866p = false;

    /* renamed from: q */
    private HandlerC2953a f10867q;

    /* renamed from: r */
    private Request f10868r;

    /* renamed from: s */
    private LinearLayout f10869s;

    /* renamed from: t */
    private ImageView f10870t;

    /* renamed from: u */
    private TextView f10871u;

    /* renamed from: v */
    private TextView f10872v;

    /* renamed from: w */
    private ProgressBar f10873w;

    /* renamed from: x */
    private RelativeLayout f10874x;

    /* renamed from: y */
    private ProgressBar f10875y;

    /* renamed from: z */
    private TextView f10876z;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "我的下载界面", C2425R.layout.my_down_activity, true);
        m12251a();
    }

    /* renamed from: a */
    private void m12251a() {
        this.f10852b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10852b.setOnClickListener(this);
        this.f10854d = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10853c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10854d.setText("我的下载");
        this.f10853c.setOnClickListener(this);
        this.f10853c.setVisibility(8);
        this.f10869s = (LinearLayout) findViewById(C2425R.C2427id.downing_layout);
        this.f10870t = (ImageView) findViewById(C2425R.C2427id.downing_img);
        this.f10871u = (TextView) findViewById(C2425R.C2427id.downing_count);
        this.f10872v = (TextView) findViewById(C2425R.C2427id.downing_title);
        this.f10873w = (ProgressBar) findViewById(C2425R.C2427id.downing_detail_progress);
        this.f10874x = (RelativeLayout) findViewById(C2425R.C2427id.remaining_storage);
        this.f10875y = (ProgressBar) findViewById(C2425R.C2427id.game_detail_progress);
        this.f10876z = (TextView) findViewById(C2425R.C2427id.progress_tv);
        this.f10848A = (TextView) findViewById(C2425R.C2427id.downed_speed);
        this.f10855e = (LinearLayout) findViewById(C2425R.C2427id.downed_layout);
        this.f10857g = (LinearLayout) findViewById(C2425R.C2427id.myDown_video_layout);
        this.f10858h = (TextView) findViewById(C2425R.C2427id.mydown_video_num);
        this.f10859i = (LinearLayout) findViewById(C2425R.C2427id.myDown_game_layout);
        this.f10860j = (TextView) findViewById(C2425R.C2427id.mydown_game_num);
        this.f10861k = (LinearLayout) findViewById(C2425R.C2427id.myDown_book_layout);
        this.f10862l = (TextView) findViewById(C2425R.C2427id.mydown_book_num);
        this.f10863m = (LinearLayout) findViewById(C2425R.C2427id.myDown_app_layout);
        this.f10864n = (TextView) findViewById(C2425R.C2427id.mydown_app_num);
        this.f10869s.setOnClickListener(this);
        this.f10857g.setOnClickListener(this);
        this.f10859i.setOnClickListener(this);
        this.f10861k.setOnClickListener(this);
        this.f10863m.setOnClickListener(this);
        this.f10856f = new DowningReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.filter.download.receiver");
        registerReceiver(this.f10856f, intentFilter);
        this.f10867q = new HandlerC2953a();
        m12255b();
    }

    /* renamed from: b */
    private void m12255b() {
        this.f10876z.setText("手机已用空间为" + StorageUtils.m14490c(this) + ",可用空间为" + StorageUtils.m14487b(this));
        this.f10875y.setProgress(StorageUtils.m14493d(this));
        m12261d();
        if (this.f10849B.size() > 0) {
            this.f10869s.setVisibility(0);
            this.f10871u.setText("正在缓存(" + this.f10849B.size() + MessageStore.f23536t);
            this.f10872v.setText(this.f10849B.get(0).getName());
            if (this.f10849B.get(0).getFileTotalSize().longValue() != 0) {
                this.f10873w.setProgress((int) ((this.f10849B.get(0).getDownsize().longValue() * 100) / this.f10849B.get(0).getFileTotalSize().longValue()));
            }
            this.f10848A.setText(C3467s.m14501a(this.f10849B.get(0).getDownsize().longValue()) + " / " + C3467s.m14501a(this.f10849B.get(0).getFileTotalSize().longValue()));
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p009v4.app.FragmentActivity
    public void onStart() {
        super.onStart();
        m12258c();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        unregisterReceiver(this.f10856f);
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12258c() {
        this.f10853c.setVisibility(8);
        int a = DownloadFileDao.m12152b().mo25542a((Context) this, Constant.f13162Q);
        this.f10860j.setText(a != 0 ? a + "" : "");
        int a2 = DownloadFileDao.m12152b().mo25542a((Context) this, Constant.f13160O);
        this.f10864n.setText(a2 != 0 ? a2 + "" : "");
        int b = DownloadFileDao.m12152b().mo25549b((Context) this, Constant.f13158M);
        this.f10858h.setText(b != 0 ? b + "" : "");
    }

    public void onClick(View view) {
        boolean z = true;
        switch (view.getId()) {
            case C2425R.C2427id.downed_all_cancel /*{ENCODED_INT: 2131755226}*/:
                if (this.f10866p) {
                    z = false;
                }
                this.f10866p = z;
                if (this.f10866p) {
                }
                return;
            case C2425R.C2427id.downed_delete /*{ENCODED_INT: 2131755227}*/:
            case C2425R.C2427id.myDown_video_layout /*{ENCODED_INT: 2131755732}*/:
            default:
                return;
            case C2425R.C2427id.downing_layout /*{ENCODED_INT: 2131755727}*/:
                startActivity(new Intent(this, MyDownloadDetailActivity.class));
                return;
            case C2425R.C2427id.myDown_game_layout /*{ENCODED_INT: 2131755734}*/:
                Intent intent = new Intent(this, DownedOtherActivity.class);
                intent.putExtra("title", "游戏");
                intent.putExtra("buttonName", "安装");
                startActivity(intent);
                return;
            case C2425R.C2427id.myDown_book_layout /*{ENCODED_INT: 2131755736}*/:
                GBActivity.showMessageToast("正在努力开发中…");
                return;
            case C2425R.C2427id.myDown_app_layout /*{ENCODED_INT: 2131755738}*/:
                Intent intent2 = new Intent(this, DownedOtherActivity.class);
                intent2.putExtra("title", "应用");
                intent2.putExtra("buttonName", "安装");
                startActivity(intent2);
                return;
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.title_edit_tv /*{ENCODED_INT: 2131755759}*/:
                if (this.f10865o) {
                    z = false;
                }
                this.f10865o = z;
                if (this.f10865o) {
                    this.f10853c.setText("取消");
                    this.f10874x.setVisibility(8);
                    return;
                }
                this.f10853c.setText("编辑");
                this.f10866p = false;
                this.f10874x.setVisibility(0);
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12252a(int i) {
        this.f10855e.setVisibility(8);
        switch (i) {
            case 1:
                this.f10855e.setVisibility(0);
                return;
            case 2:
            default:
                return;
        }
    }

    public class DowningReceiver extends BroadcastReceiver {
        public DowningReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Log.m14400c(CommonNetImpl.TAG, "DowningReceiver广播");
            m12271a(intent);
        }

        /* renamed from: a */
        private void m12271a(Intent intent) {
            if (intent != null && intent.getAction().equals("com.filter.download.receiver")) {
                int intExtra = intent.getIntExtra("type", -1);
                Log.m14400c("tag11", intExtra + "");
                switch (intExtra) {
                    case 0:
                        MyDownloadActivity.this.f10867q.removeMessages(1);
                        MyDownloadActivity.this.f10867q.sendMessage(MyDownloadActivity.this.f10867q.obtainMessage(1, intent));
                        return;
                    case 1:
                        String stringExtra = intent.getStringExtra("url");
                        DownloadFile a = MyDownloadActivity.this.m12250a((MyDownloadActivity) stringExtra);
                        if (a != null) {
                            MyDownloadActivity.this.f10868r = HttpUtil.m14273a(a.getProductType(), a.getProductId(), MyDownloadActivity.this.f10851D, "");
                        }
                        if (!TextUtils.isEmpty(stringExtra)) {
                            MyDownloadActivity.this.m12257b((MyDownloadActivity) stringExtra);
                        }
                        if (MyDownloadActivity.this.f10849B.size() > 0) {
                            MyDownloadActivity.this.f10871u.setText("正在缓存(" + MyDownloadActivity.this.f10849B.size() + MessageStore.f23536t);
                            MyDownloadActivity.this.f10872v.setText(((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getName());
                            if (((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getFileTotalSize().longValue() != 0) {
                                MyDownloadActivity.this.f10873w.setProgress((int) ((((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getDownsize().longValue() * 100) / ((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getFileTotalSize().longValue()));
                            }
                            MyDownloadActivity.this.f10848A.setText(C3467s.m14501a(((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getDownsize().longValue()) + " / " + C3467s.m14501a(((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getFileTotalSize().longValue()));
                        } else {
                            MyDownloadActivity.this.f10869s.setVisibility(8);
                        }
                        MyDownloadActivity.this.m12258c();
                        return;
                    case 2:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    default:
                        return;
                    case 4:
                        MyDownloadActivity.this.m12261d();
                        if (MyDownloadActivity.this.f10849B.size() == 0) {
                            MyDownloadActivity.this.f10869s.setVisibility(8);
                            return;
                        }
                        MyDownloadActivity.this.f10871u.setText("正在缓存(" + MyDownloadActivity.this.f10849B.size() + MessageStore.f23536t);
                        MyDownloadActivity.this.f10872v.setText(((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getName());
                        if (((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getFileTotalSize().longValue() != 0) {
                            MyDownloadActivity.this.f10873w.setProgress((int) ((((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getDownsize().longValue() * 100) / ((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getFileTotalSize().longValue()));
                        }
                        MyDownloadActivity.this.f10848A.setText(C3467s.m14501a(((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getDownsize().longValue()) + " / " + C3467s.m14501a(((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getFileTotalSize().longValue()));
                        return;
                    case 6:
                        MyDownloadActivity.this.m12261d();
                        MyDownloadActivity.this.f10869s.setVisibility(0);
                        if (MyDownloadActivity.this.f10849B.size() > 0) {
                            MyDownloadActivity.this.f10871u.setText("正在缓存(" + MyDownloadActivity.this.f10849B.size() + MessageStore.f23536t);
                            MyDownloadActivity.this.f10872v.setText(((DownloadFile) MyDownloadActivity.this.f10849B.get(0)).getName());
                            return;
                        }
                        return;
                    case 9:
                        intent.getStringExtra("url");
                        if (MyDownloadActivity.this.f10849B.size() == 0) {
                            MyDownloadActivity.this.f10869s.setVisibility(8);
                            return;
                        }
                        return;
                    case 10:
                        if (MyDownloadActivity.this.f10849B.size() == 0) {
                            MyDownloadActivity.this.f10869s.setVisibility(8);
                            return;
                        }
                        return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    /* renamed from: com.gbcom.gwifi.functions.profile.MyDownloadActivity$a */
    public class HandlerC2953a extends Handler {
        private HandlerC2953a() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    String stringExtra = ((Intent) message.obj).getStringExtra("url");
                    for (int i = 0; i < MyDownloadActivity.this.f10849B.size(); i++) {
                        if (stringExtra.equals(((DownloadFile) MyDownloadActivity.this.f10849B.get(i)).getUrl())) {
                            MyDownloadActivity.this.f10872v.setText(((DownloadFile) MyDownloadActivity.this.f10849B.get(i)).getName());
                        }
                    }
                    MyDownloadActivity.this.f10873w.setProgress(Integer.parseInt(((Intent) message.obj).getStringExtra(MyIntents.f9255c)));
                    MyDownloadActivity.this.f10848A.setText(((Intent) message.obj).getStringExtra(MyIntents.f9254b));
                    Log.m14400c(MyDownloadActivity.f10847a, "+process>" + ((Intent) message.obj).getStringExtra(MyIntents.f9255c));
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m12261d() {
        try {
            Collection<? extends DownloadFile> query = DownloadFileDao.m12152b().mo24212d(this).where().mo33373ne("stateId", 0).query();
            if (query == null) {
                query = new ArrayList<>();
            }
            this.f10849B.clear();
            this.f10849B.addAll(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private DownloadFile m12250a(String str) {
        for (int i = 0; i < this.f10849B.size(); i++) {
            if (this.f10849B.get(i).getUrl().equals(str)) {
                return this.f10849B.get(i);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12257b(String str) {
        for (int i = 0; i < this.f10849B.size(); i++) {
            if (this.f10849B.get(i).getUrl().equals(str)) {
                this.f10849B.remove(i);
            }
        }
    }
}
