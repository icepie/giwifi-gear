package com.gbcom.gwifi.functions.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p009v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.download.DownloadManager;
import com.gbcom.gwifi.functions.download.DownloadTask;
import com.gbcom.gwifi.functions.product.domain.DownloadComplete;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.domain.MessageEvent;
import com.gbcom.gwifi.functions.product.domain.RecommendAppListResponse;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.umeng.message.proguard.MessageStore;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.product.b */
public class DownloadingListFragment extends GBFragment {

    /* renamed from: a */
    public static List<RecommendAppListResponse.DataBean.AppListBean> f10723a = new ArrayList();

    /* renamed from: b */
    private static final String f10724b = "DownloadingListFragment";

    /* renamed from: c */
    private static AppDownloadActivity f10725c;

    /* renamed from: d */
    private static SmartTabLayout f10726d;

    /* renamed from: e */
    private static TextView f10727e;

    /* renamed from: f */
    private static ViewPager f10728f;

    /* renamed from: g */
    private static LinearLayout f10729g;

    /* renamed from: h */
    private static OkRequestHandler<String> f10730h = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.DownloadingListFragment.C29321 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            Log.m14403e(DownloadingListFragment.f10724b, str);
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: i */
    private String f10731i;

    /* renamed from: j */
    private DownloadManager f10732j;

    /* renamed from: k */
    private HashMap<Integer, DownloadTask> f10733k = new HashMap<>();

    /* renamed from: l */
    private boolean f10734l;

    @Override // android.support.p009v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(C2425R.layout.fragment_task_downing, viewGroup, false);
        f10729g = (LinearLayout) inflate.findViewById(C2425R.C2427id.f8356ll);
        f10725c = (AppDownloadActivity) getActivity();
        f10726d = f10725c.getViewPagerTab();
        f10728f = f10725c.getViewPager();
        if (f10726d.mo34686b(1) != null) {
            f10727e = (TextView) f10726d.mo34686b(1);
            f10727e.setText("下载中");
        }
        return inflate;
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
    public void onMessageEvent(MessageEvent messageEvent) {
        mo25537a(messageEvent.getAppListBean());
    }

    /* renamed from: a */
    public void mo25537a(final RecommendAppListResponse.DataBean.AppListBean appListBean) {
        f10723a.add(appListBean);
        if (f10727e != null) {
            f10727e.setText("下载中(" + f10723a.size() + MessageStore.f23536t);
        }
        final View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.item_tasklist_downloading, (ViewGroup) f10729g, false);
        ImageTools.m14149a(appListBean.getIconUrl(), (ImageView) inflate.findViewById(2131755155), GBApplication.instance().options);
        ((TextView) inflate.findViewById(2131755200)).setText(appListBean.getAppName());
        ((TextView) inflate.findViewById(C2425R.C2427id.tv_desc)).setText(appListBean.getCategoryInfo().getCategoryName());
        ((TextView) inflate.findViewById(C2425R.C2427id.tv_count)).setText(new DecimalFormat("0.00").format((double) ((((float) Integer.parseInt(appListBean.getFileSize())) / 1024.0f) / 1024.0f)) + "MB  " + m12136a(appListBean.getAppDownCount()) + "次下载");
        ((TextView) inflate.findViewById(C2425R.C2427id.tv_balance)).setText("+" + appListBean.getPointsReward() + Constant.f13309cr);
        TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.tv_download);
        final TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.tv_prograss);
        final ProgressBar progressBar = (ProgressBar) inflate.findViewById(C2425R.C2427id.progressbar);
        if (appListBean.getPrograss() == 0) {
            final DownloadManager aVar = new DownloadManager(GBApplication.instance());
            aVar.mo24574a(appListBean.getApkUrl(), appListBean.getPkgName());
            final DownloadTask a = aVar.mo24567a(0);
            this.f10734l = true;
            progressBar.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.product.DownloadingListFragment.View$OnClickListenerC29332 */

                public void onClick(View view) {
                    if (textView2.getText().toString().trim().equals("开始")) {
                        aVar.mo24586e(appListBean.getApkUrl());
                        DownloadingListFragment.this.f10734l = true;
                        return;
                    }
                    DownloadingListFragment.this.f10734l = false;
                    aVar.mo24579c(appListBean.getApkUrl());
                    textView2.setText("开始");
                }
            });
            aVar.mo24570a(new DownloadManager.AbstractC2608b() {
                /* class com.gbcom.gwifi.functions.product.DownloadingListFragment.C29343 */

                @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2608b
                /* renamed from: a */
                public void mo24561a(String str, String str2, String str3) {
                }

                @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2608b
                /* renamed from: a */
                public void mo24559a(String str, long j, long j2, long j3, long j4) {
                    progressBar.setProgress((int) j3);
                    textView2.setText(j3 + "%");
                }

                @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2608b
                /* renamed from: a */
                public void mo24558a(String str, long j, long j2) {
                    appListBean.setPrograss((100 * j) / j2);
                    a.mo24611c();
                    appListBean.setAbsolutePath(a.mo24620j().getAbsolutePath());
                }

                @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2608b
                /* renamed from: a */
                public void mo24557a(String str) {
                }

                @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2608b
                /* renamed from: a */
                public void mo24560a(String str, String str2) {
                    try {
                        HttpUtil.m14256a(GBApplication.instance(), DownloadingListFragment.f10730h, "", appListBean.getPkgName(), appListBean.getReportDownloadUrl());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    appListBean.setState(Constant.f13281cC);
                    DownloadingListFragment.f10723a.remove(appListBean);
                    if (DownloadingListFragment.f10727e != null) {
                        if (DownloadingListFragment.f10723a.size() == 0) {
                            DownloadingListFragment.f10727e.setText("下载中");
                        } else {
                            DownloadingListFragment.f10727e.setText("下载中(" + DownloadingListFragment.f10723a.size() + MessageStore.f23536t);
                        }
                    }
                    DownloadingListFragment.f10729g.removeView(inflate);
                    EventBus.m39385a().mo61756c(new DownloadComplete(appListBean));
                    Intent intent = new Intent("need_install");
                    intent.putExtra("pkgName", appListBean.getPkgName());
                    GBApplication.instance().sendBroadcast(intent);
                    if (DownloadingListFragment.f10728f != null) {
                        DownloadingListFragment.f10728f.setCurrentItem(2);
                    }
                }

                @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2608b
                public void delete(String str) {
                }
            });
        } else {
            DownloadFile downloadFile = new DownloadFile();
            downloadFile.setTempFile(appListBean.getAbsolutePath());
            downloadFile.setLocalFile(appListBean.getAbsolutePath());
            downloadFile.setUrl(appListBean.getApkUrl());
            new DownloadManager(GBApplication.instance()).mo24572a(downloadFile);
        }
        f10729g.addView(inflate);
    }

    /* renamed from: a */
    public static String m12136a(long j) {
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
    public void onDestroy() {
        super.onDestroy();
    }
}
