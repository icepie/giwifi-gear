package com.gbcom.gwifi.functions.product;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p009v4.content.FileProvider;
import android.support.p009v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.download.DownloadManager;
import com.gbcom.gwifi.functions.product.domain.DownloadComplete;
import com.gbcom.gwifi.functions.product.domain.RecommendAppListResponse;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.StorageUtils;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.umeng.message.common.C6366a;
import com.umeng.message.proguard.MessageStore;
import java.p456io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.product.a */
public class DownloadedListFragment extends GBFragment {

    /* renamed from: a */
    public static List<RecommendAppListResponse.DataBean.AppListBean> f10663a = new ArrayList();

    /* renamed from: b */
    public static C2928c f10664b;

    /* renamed from: c */
    public static RecommendAppListResponse.DataBean.AppListBean f10665c;

    /* renamed from: e */
    private static final int f10666e = 0;

    /* renamed from: f */
    private static ListView f10667f;

    /* renamed from: g */
    private static AppDownloadActivity f10668g;

    /* renamed from: i */
    private static TextView f10669i;

    /* renamed from: p */
    private static int f10670p;

    /* renamed from: q */
    private static String f10671q;

    /* renamed from: r */
    private static HashMap<String, List<RecommendAppListResponse.DataBean.AppListBean>> f10672r;

    /* renamed from: s */
    private static boolean f10673s;

    /* renamed from: t */
    private static int f10674t;

    /* renamed from: u */
    private static String f10675u;

    /* renamed from: v */
    private static List<String> f10676v;

    /* renamed from: d */
    BroadcastReceiver f10677d = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.product.DownloadedListFragment.C29212 */

        public void onReceive(Context context, Intent intent) {
            RecommendAppListResponse.DataBean.AppListBean appListBean = (RecommendAppListResponse.DataBean.AppListBean) intent.getSerializableExtra("appListBean");
            int i = -1;
            for (int i2 = 0; i2 < DownloadedListFragment.f10663a.size(); i2++) {
                if (DownloadedListFragment.f10663a.get(i2).getPkgName().equals(appListBean.getPkgName())) {
                    i = i2;
                }
            }
            if (i != -1) {
                DownloadedListFragment.this.mo25507a(i, DownloadedListFragment.f10663a.get(i).getFilePath(), DownloadedListFragment.f10663a.get(i).getPkgName(), DownloadedListFragment.f10663a.get(i));
            }
        }
    };

    /* renamed from: h */
    private SmartTabLayout f10678h;

    /* renamed from: j */
    private DownloadManager f10679j;

    /* renamed from: k */
    private C2926a f10680k;

    /* renamed from: l */
    private String f10681l;

    /* renamed from: m */
    private int f10682m;

    /* renamed from: n */
    private OkRequestHandler<String> f10683n = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.DownloadedListFragment.C29161 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == DownloadedListFragment.this.f10684o) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
                    Intent intent = new Intent("get_prize_complete");
                    intent.putExtra("pkgName", DownloadedListFragment.f10663a.get(DownloadedListFragment.this.f10682m).getPkgName());
                    GBApplication.instance().sendBroadcast(intent);
                    if (i == 0) {
                        DownloadedListFragment.f10663a.get(DownloadedListFragment.this.f10682m).setState(Constant.f13283cE);
                        ((TextView) DownloadedListFragment.f10667f.getChildAt(DownloadedListFragment.this.f10682m).findViewById(C2425R.C2427id.tv_download)).setText("已完成");
                        if (DownloadedListFragment.f10670p != 0) {
                            GBApplication.instance().getCurrentActivity().showSecondDialog("提示", "恭喜您," + DownloadedListFragment.f10670p + "旺豆领取成功!", "查看旺豆", new GBActivity.AbstractC2517a() {
                                /* class com.gbcom.gwifi.functions.product.DownloadedListFragment.C29161.C29171 */

                                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                                public void onClick(Dialog dialog, View view) {
                                    String k = HttpUtil.m14335k();
                                    if (!C3467s.m14513e(k)) {
                                        GBActivity.openOfficeBackWebView(k, "");
                                    } else {
                                        GBActivity.showMessageToast("链接无效...");
                                    }
                                    dialog.dismiss();
                                }
                            }, new DialogInterface.OnCancelListener() {
                                /* class com.gbcom.gwifi.functions.product.DownloadedListFragment.C29161.DialogInterface$OnCancelListenerC29182 */

                                public void onCancel(DialogInterface dialogInterface) {
                                    dialogInterface.dismiss();
                                }
                            });
                            return;
                        }
                        return;
                    }
                    String string = jSONObject.getString("resultMsg");
                    DownloadedListFragment.f10663a.get(DownloadedListFragment.this.f10682m).setState(Constant.f13283cE);
                    ((TextView) DownloadedListFragment.f10667f.getChildAt(DownloadedListFragment.this.f10682m).findViewById(C2425R.C2427id.tv_download)).setText("已完成");
                    GBApplication.instance().getCurrentActivity().showSecondDialog("提示", string, "确定", new GBActivity.AbstractC2517a() {
                        /* class com.gbcom.gwifi.functions.product.DownloadedListFragment.C29161.C29193 */

                        @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                        public void onClick(Dialog dialog, View view) {
                            dialog.dismiss();
                        }
                    }, new DialogInterface.OnCancelListener() {
                        /* class com.gbcom.gwifi.functions.product.DownloadedListFragment.C29161.DialogInterface$OnCancelListenerC29204 */

                        public void onCancel(DialogInterface dialogInterface) {
                            dialogInterface.dismiss();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: o */
    private Request f10684o;

    /* renamed from: w */
    private C2927b f10685w;

    /* renamed from: x */
    private ViewPager f10686x;

    @Override // android.support.p009v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(C2425R.layout.fragment_tasklist, viewGroup, false);
        f10671q = CacheAccount.getInstance().getLoginPhone();
        f10672r = CacheApp.getInstance().getAppDownloadData();
        f10663a.clear();
        if (f10672r.containsKey(f10671q)) {
            f10663a.addAll(f10672r.get(f10671q));
        }
        f10667f = (ListView) inflate.findViewById(C2425R.C2427id.f8357lv);
        f10668g = (AppDownloadActivity) getActivity();
        f10676v = f10668g.getAppPkgName();
        this.f10686x = f10668g.getViewPager();
        this.f10678h = f10668g.getViewPagerTab();
        if (this.f10678h.mo34686b(2) != null) {
            f10669i = (TextView) this.f10678h.mo34686b(2);
        }
        f10664b = new C2928c(f10663a);
        f10667f.setAdapter((ListAdapter) f10664b);
        this.f10680k = new C2926a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(C6366a.f23181c);
        GBApplication.instance().registerReceiver(this.f10680k, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("install_apk");
        GBApplication.instance().registerReceiver(this.f10677d, intentFilter2);
        this.f10685w = new C2927b();
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("get_prize_complete");
        GBApplication.instance().registerReceiver(this.f10685w, intentFilter3);
        if (f10669i != null) {
            if (f10663a.size() == 0) {
                f10669i.setText("已完成");
            } else {
                f10669i.setText("已完成(" + f10663a.size() + MessageStore.f23536t);
            }
        }
        return inflate;
    }

    /* renamed from: a */
    public static void m12098a() {
        if (f10669i == null) {
            return;
        }
        if (f10663a.size() == 0) {
            f10669i.setText("已完成");
        } else {
            f10669i.setText("已完成(" + f10663a.size() + MessageStore.f23536t);
        }
    }

    /* renamed from: b */
    public static void m12103b() {
        for (int i = 0; i < f10663a.size() - 1; i++) {
            for (int size = f10663a.size() - 1; size > i; size--) {
                if (f10663a.get(size).getPkgName().equals(f10663a.get(i).getPkgName())) {
                    f10663a.remove(size);
                }
            }
        }
        if (f10664b != null) {
            f10664b.notifyDataSetChanged();
            m12098a();
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.product.a$b */
    /* compiled from: DownloadedListFragment */
    class C2927b extends BroadcastReceiver {
        C2927b() {
        }

        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("pkgName");
            int i = 0;
            while (true) {
                if (i >= DownloadedListFragment.f10663a.size()) {
                    i = 0;
                    break;
                } else if (DownloadedListFragment.f10663a.get(i).getPkgName().equals(stringExtra)) {
                    break;
                } else {
                    i++;
                }
            }
            DownloadedListFragment.f10663a.get(i).setState(Constant.f13283cE);
            DownloadedListFragment.f10664b.notifyDataSetChanged();
        }
    }

    /* renamed from: b */
    private List<String> m12102b(String str) {
        ArrayList arrayList = new ArrayList();
        File file = new File(str);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (!file2.isDirectory()) {
                    String name = file2.getName();
                    if (name.endsWith(".apk")) {
                        arrayList.add(name.substring(0, name.length() - 4));
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
    }

    /* renamed from: com.gbcom.gwifi.functions.product.a$a */
    /* compiled from: DownloadedListFragment */
    class C2926a extends BroadcastReceiver {
        C2926a() {
        }

        public void onReceive(Context context, Intent intent) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            StorageUtils.m14481a(GBApplication.instance());
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") && StorageUtils.m14492c(context, schemeSpecificPart)) {
                int currentItem = DownloadedListFragment.this.f10686x.getCurrentItem();
                int i = 0;
                while (true) {
                    if (i >= DownloadedListFragment.f10663a.size()) {
                        i = 0;
                        break;
                    } else if (DownloadedListFragment.f10663a.get(i).getPkgName().equals(schemeSpecificPart)) {
                        break;
                    } else {
                        i++;
                    }
                }
                DownloadedListFragment.f10663a.get(i).setState(Constant.f13282cD);
                DownloadedListFragment.f10664b.notifyDataSetChanged();
                if (currentItem == 2) {
                    HttpUtil.m14256a(GBApplication.instance(), DownloadedListFragment.this.f10683n, "", schemeSpecificPart, DownloadedListFragment.f10665c.getReportInstallUrl());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m12105c(String str) {
        int i;
        int i2;
        int i3 = 0;
        if (StorageUtils.m14492c(getContext(), str)) {
            f10673s = true;
            startActivity(getActivity().getPackageManager().getLaunchIntentForPackage(str));
            return;
        }
        f10673s = false;
        if (new File("/sdcard/Download/" + str + ".apk").exists()) {
            while (true) {
                i2 = i3;
                if (i2 >= f10663a.size()) {
                    i2 = -1;
                    break;
                } else if (f10663a.get(i2).getPkgName().equals(str)) {
                    break;
                } else {
                    i3 = i2 + 1;
                }
            }
            f10663a.get(i2).setState(Constant.f13281cC);
            f10664b.notifyDataSetChanged();
            GBApplication.instance().getCurrentActivity().showSecondDialog("提示", "应用已卸载，请重新安装", "确定", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.product.DownloadedListFragment.C29223 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.functions.product.DownloadedListFragment.DialogInterface$OnCancelListenerC29234 */

                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                }
            });
            return;
        }
        while (true) {
            if (i3 >= f10663a.size()) {
                i = -1;
                break;
            } else if (f10663a.get(i3).getPkgName().equals(str)) {
                i = i3;
                break;
            } else {
                i3++;
            }
        }
        GBApplication.instance().getCurrentActivity().showSecondDialog("提示", "应用包已删除,请重新下载", "确定", new GBActivity.AbstractC2517a() {
            /* class com.gbcom.gwifi.functions.product.DownloadedListFragment.C29245 */

            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
            public void onClick(Dialog dialog, View view) {
                dialog.dismiss();
            }
        }, new DialogInterface.OnCancelListener() {
            /* class com.gbcom.gwifi.functions.product.DownloadedListFragment.DialogInterface$OnCancelListenerC29256 */

            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        });
        f10663a.remove(i);
        f10664b.notifyDataSetChanged();
    }

    /* renamed from: com.gbcom.gwifi.functions.product.a$c */
    /* compiled from: DownloadedListFragment */
    public class C2928c extends BaseAdapter {

        /* renamed from: b */
        private List<RecommendAppListResponse.DataBean.AppListBean> f10700b = new ArrayList();

        public C2928c(List<RecommendAppListResponse.DataBean.AppListBean> list) {
            this.f10700b = list;
        }

        public int getCount() {
            if (this.f10700b == null) {
                return 0;
            }
            return this.f10700b.size();
        }

        public Object getItem(int i) {
            return this.f10700b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(final int i, View view, final ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.item_tasklist_downloaded, viewGroup, false);
            final RecommendAppListResponse.DataBean.AppListBean appListBean = this.f10700b.get(i);
            inflate.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.product.DownloadedListFragment.C2928c.View$OnClickListenerC29291 */

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("position", i);
                    intent.putExtra("productId", appListBean.getProductId());
                    intent.putExtra("state", appListBean.getState());
                    intent.putExtra("appListBean", appListBean);
                    intent.setClass(viewGroup.getContext(), AppDownloadDetailActivity.class);
                    DownloadedListFragment.this.startActivity(intent);
                }
            });
            ImageTools.m14149a(appListBean.getIconUrl(), (ImageView) inflate.findViewById(2131755155), GBApplication.instance().options);
            ((TextView) inflate.findViewById(2131755200)).setText(appListBean.getAppName());
            ((TextView) inflate.findViewById(C2425R.C2427id.tv_desc)).setText(appListBean.getCategoryInfo().getCategoryName());
            ((TextView) inflate.findViewById(C2425R.C2427id.tv_count)).setText(new DecimalFormat("0.00").format((double) ((((float) Integer.parseInt(appListBean.getFileSize())) / 1024.0f) / 1024.0f)) + "MB  " + DownloadedListFragment.this.m12095a((DownloadedListFragment) appListBean.getAppDownCount()) + "次下载");
            TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.tv_balance);
            textView.setText("+" + appListBean.getPointsReward() + Constant.f13309cr);
            final TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.tv_download);
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.iv_balance);
            int state = appListBean.getState();
            if (Constant.f13279cA == state) {
                textView2.setText("下载");
            } else if (Constant.f13280cB == state) {
                textView2.setText("下载中");
            } else if (Constant.f13281cC == state) {
                textView2.setText("安装");
            } else if (Constant.f13282cD == state) {
                textView2.setText("打开领奖励");
            } else if (Constant.f13283cE == state) {
                textView2.setText("已领取");
                textView2.setEnabled(false);
                textView2.setBackgroundResource(C2425R.C2426drawable.gi_app_bg_step_finish);
                textView2.setTextColor(DownloadedListFragment.this.getResources().getColor(2131624113));
                textView.setTextColor(DownloadedListFragment.this.getResources().getColor(C2425R.color.grey_c));
                imageView.setImageResource(C2425R.C2426drawable.appdownload_balance_grey);
            }
            textView2.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.product.DownloadedListFragment.C2928c.View$OnClickListenerC29302 */

                public void onClick(View view) {
                    String trim = textView2.getText().toString().trim();
                    if (TextUtils.equals(trim, "安装")) {
                        DownloadedListFragment.this.mo25507a(i, appListBean.getFilePath(), appListBean.getPkgName(), appListBean);
                    } else if (TextUtils.equals(trim, "领取奖励")) {
                        DownloadedListFragment.this.f10684o = HttpUtil.m14254a(GBApplication.instance(), DownloadedListFragment.this.f10683n, "", appListBean.getProductId(), appListBean.getPkgName(), appListBean.getPointsReward());
                    } else if (TextUtils.equals(trim, "打开领奖励")) {
                        DownloadedListFragment.this.m12105c((DownloadedListFragment) appListBean.getPkgName());
                        int unused = DownloadedListFragment.f10670p = appListBean.getPointsReward();
                        int unused2 = DownloadedListFragment.f10674t = appListBean.getProductId();
                        String unused3 = DownloadedListFragment.f10675u = appListBean.getAppName();
                    } else if (textView2.getText().toString().trim().equals("打开")) {
                        DownloadedListFragment.this.m12105c((DownloadedListFragment) appListBean.getPkgName());
                    }
                }
            });
            return inflate;
        }
    }

    /* renamed from: a */
    public void mo25507a(int i, String str, String str2, RecommendAppListResponse.DataBean.AppListBean appListBean) {
        File file = new File(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setFlags(1);
            intent.setDataAndType(FileProvider.getUriForFile(getContext(), GBApplication.instance().getApplicationInfo().packageName + ".FileProvider", file), "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        startActivityForResult(intent, 0);
        f10665c = appListBean;
        this.f10681l = str2;
        this.f10682m = i;
    }

    @Override // android.support.p009v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 0) {
        }
    }

    /* renamed from: c */
    public void mo25508c() {
        f10676v = f10668g.getAppPkgName();
        f10664b.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private String m12095a(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (j > 100000000) {
            return decimalFormat.format((double) (((float) j) / 1.0E8f)) + "亿";
        }
        if (j < 10000) {
            return j + "";
        }
        if (j >= 10000) {
            return decimalFormat.format((double) (((float) j) / 10000.0f)) + "万";
        }
        return "";
    }

    @Override // android.support.p009v4.app.Fragment
    public void onStart() {
        super.onStart();
        EventBus.m39385a().register(this);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onStop() {
        super.onStop();
        EventBus.m39385a().mo61755b(this);
    }

    @Subscribe(mo61793a = ThreadMode.MAIN)
    public void onDownloadComplete(DownloadComplete downloadComplete) {
        RecommendAppListResponse.DataBean.AppListBean appListBean = downloadComplete.getAppListBean();
        Iterator<RecommendAppListResponse.DataBean.AppListBean> it = f10663a.iterator();
        while (it.hasNext()) {
            if (it.next().getPkgName().equals(appListBean.getPkgName())) {
                it.remove();
            }
        }
        appListBean.setState(Constant.f13281cC);
        f10663a.add(0, appListBean);
        appListBean.setFilePath(new File(StorageUtils.f13479a, appListBean.getPkgName() + ".apk").getAbsolutePath());
        f10664b.notifyDataSetChanged();
        if (f10669i != null) {
            f10669i.setText("已完成(" + f10663a.size() + MessageStore.f23536t);
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        GBApplication.instance().unregisterReceiver(this.f10680k);
        mo25509d();
        super.onDestroy();
    }

    /* renamed from: d */
    public void mo25509d() {
        f10672r.put(f10671q, f10663a);
        CacheApp.getInstance().setAppDownloadData(f10672r);
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
    }

    /* renamed from: e */
    public void mo25510e() {
        if (f10673s) {
            this.f10684o = HttpUtil.m14254a(GBApplication.instance(), this.f10683n, "", f10674t, f10675u, f10670p);
            f10673s = false;
        }
    }

    /* renamed from: f */
    public List<RecommendAppListResponse.DataBean.AppListBean> mo25511f() {
        return f10663a;
    }
}
