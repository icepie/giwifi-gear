package com.gbcom.gwifi.functions.product;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p009v4.view.ViewPager;
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
import com.gbcom.gwifi.functions.product.domain.MessageEvent;
import com.gbcom.gwifi.functions.product.domain.RecommendAppListResponse;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.PullToRefreshView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.p136b.p137a.Gson;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.product.c */
public class TaskListFragment extends GBFragment implements PullToRefreshView.AbstractC3493a, PullToRefreshView.AbstractC3494b {

    /* renamed from: A */
    private static List<String> f10751A;

    /* renamed from: B */
    private static int f10752B;

    /* renamed from: f */
    private static ListView f10753f;

    /* renamed from: g */
    private static AppDownloadActivity f10754g;

    /* renamed from: i */
    private static TextView f10755i;

    /* renamed from: j */
    private static List<RecommendAppListResponse.DataBean.AppListBean> f10756j = new ArrayList();

    /* renamed from: k */
    private static List<RecommendAppListResponse.DataBean.AppListBean> f10757k = new ArrayList();

    /* renamed from: l */
    private static C2940a f10758l;

    /* renamed from: m */
    private static ViewPager f10759m;

    /* renamed from: t */
    private static String f10760t;

    /* renamed from: u */
    private static int f10761u;

    /* renamed from: v */
    private static String f10762v;

    /* renamed from: w */
    private static boolean f10763w;

    /* renamed from: x */
    private static int f10764x;

    /* renamed from: y */
    private static int f10765y;

    /* renamed from: C */
    private List<RecommendAppListResponse.DataBean.AppListBean> f10766C = new ArrayList();

    /* renamed from: D */
    private List<String> f10767D = new ArrayList();

    /* renamed from: a */
    private String f10768a = getClass().getSimpleName();

    /* renamed from: b */
    private Request f10769b;

    /* renamed from: c */
    private Gson f10770c;

    /* renamed from: d */
    private boolean f10771d = true;

    /* renamed from: e */
    private OkRequestHandler<String> f10772e = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.product.TaskListFragment.C29351 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == TaskListFragment.this.f10769b) {
                try {
                    if (((Integer) new JSONObject(str).get(AbstractC5718b.JSON_ERRORCODE)).intValue() == 0) {
                        if (TaskListFragment.this.f10770c == null) {
                            TaskListFragment.this.f10770c = GsonUtil.m14241a();
                        }
                        TaskListFragment.this.f10778r = CacheApp.getInstance().getAppDownloadData();
                        TaskListFragment.this.f10779s = CacheAccount.getInstance().getLoginPhone();
                        new ArrayList();
                        if (TaskListFragment.this.f10778r.containsKey(TaskListFragment.this.f10779s)) {
                            List list = (List) TaskListFragment.this.f10778r.get(TaskListFragment.this.f10779s);
                        }
                        List unused = TaskListFragment.f10756j = ((RecommendAppListResponse) TaskListFragment.this.f10770c.mo21588a(str, RecommendAppListResponse.class)).getData().getAppList();
                        TaskListFragment.f10757k.clear();
                        for (int i = 0; i < TaskListFragment.f10756j.size(); i++) {
                            if (((RecommendAppListResponse.DataBean.AppListBean) TaskListFragment.f10756j.get(i)).getHasFinished() == 1) {
                                ((RecommendAppListResponse.DataBean.AppListBean) TaskListFragment.f10756j.get(i)).setState(Constant.f13283cE);
                                TaskListFragment.f10757k.add(TaskListFragment.f10756j.get(i));
                            }
                        }
                        TaskListFragment.f10756j.removeAll(TaskListFragment.f10757k);
                        DownloadedListFragment.f10663a.addAll(TaskListFragment.f10757k);
                        DownloadedListFragment.m12103b();
                        if (TaskListFragment.this.f10766C.size() > 0) {
                            Iterator it = TaskListFragment.f10756j.iterator();
                            while (it.hasNext()) {
                                RecommendAppListResponse.DataBean.AppListBean appListBean = (RecommendAppListResponse.DataBean.AppListBean) it.next();
                                for (int i2 = 0; i2 < TaskListFragment.this.f10766C.size(); i2++) {
                                    if (appListBean.getPkgName().equals(((RecommendAppListResponse.DataBean.AppListBean) TaskListFragment.this.f10766C.get(i2)).getPkgName())) {
                                        it.remove();
                                    }
                                }
                            }
                        }
                        if (TaskListFragment.this.f10767D.size() > 0) {
                            Iterator it2 = TaskListFragment.f10756j.iterator();
                            while (it2.hasNext()) {
                                RecommendAppListResponse.DataBean.AppListBean appListBean2 = (RecommendAppListResponse.DataBean.AppListBean) it2.next();
                                for (int i3 = 0; i3 < TaskListFragment.this.f10767D.size(); i3++) {
                                    if (appListBean2.getPkgName().equals((String) TaskListFragment.this.f10767D.get(i3))) {
                                        it2.remove();
                                    }
                                }
                            }
                        }
                        if (TaskListFragment.f10756j.size() != 0) {
                            TaskListFragment.this.m12190b((TaskListFragment) TaskListFragment.f10756j);
                            if (TaskListFragment.f10755i != null) {
                                TaskListFragment.f10755i.setText("下载列表");
                            }
                            TaskListFragment.this.f10774n.mo28398a("更新于:" + TaskListFragment.this.f10777q.format(new Date(System.currentTimeMillis())));
                            TaskListFragment.this.f10774n.mo28400b();
                            return;
                        }
                        TaskListFragment.this.f10774n.mo28398a("更新于:" + TaskListFragment.this.f10777q.format(new Date(System.currentTimeMillis())));
                        TaskListFragment.this.f10774n.mo28400b();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (TaskListFragment.this.f10776p == abVar) {
                try {
                    if (((Integer) new JSONObject(str).get(AbstractC5718b.JSON_ERRORCODE)).intValue() == 0) {
                        if (TaskListFragment.this.f10770c == null) {
                            TaskListFragment.this.f10770c = GsonUtil.m14241a();
                        }
                        TaskListFragment.f10756j.addAll(((RecommendAppListResponse) TaskListFragment.this.f10770c.mo21588a(str, RecommendAppListResponse.class)).getData().getAppList());
                        List<RecommendAppListResponse.DataBean.AppListBean> f = TaskListFragment.f10754g.getThirdFragment().mo25511f();
                        if (f.size() > 0) {
                            Iterator it3 = TaskListFragment.f10756j.iterator();
                            while (it3.hasNext()) {
                                RecommendAppListResponse.DataBean.AppListBean appListBean3 = (RecommendAppListResponse.DataBean.AppListBean) it3.next();
                                for (int i4 = 0; i4 < f.size(); i4++) {
                                    if (appListBean3.getPkgName().equals(f.get(i4).getPkgName())) {
                                        it3.remove();
                                    }
                                }
                            }
                        }
                        TaskListFragment.f10758l.notifyDataSetChanged();
                        TaskListFragment.f10753f.setSelection(TaskListFragment.f10753f.getCount() - 1);
                        TaskListFragment.this.f10774n.mo28403c();
                        if (TaskListFragment.f10755i != null) {
                            TaskListFragment.f10755i.setText("下载列表");
                        }
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            } else if (abVar == TaskListFragment.this.f10780z) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i5 = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
                    Intent intent = new Intent("get_prize_complete");
                    intent.putExtra("pkgName", ((RecommendAppListResponse.DataBean.AppListBean) TaskListFragment.f10756j.get(TaskListFragment.f10761u)).getPkgName());
                    GBApplication.instance().sendBroadcast(intent);
                    if (i5 == 0) {
                        ((RecommendAppListResponse.DataBean.AppListBean) TaskListFragment.f10756j.get(TaskListFragment.f10761u)).setState(Constant.f13283cE);
                        ((TextView) TaskListFragment.f10753f.getChildAt(TaskListFragment.f10761u).findViewById(C2425R.C2427id.tv_download)).setText("已完成");
                        GBApplication.instance().getCurrentActivity().showSecondDialog("提示", "恭喜您," + TaskListFragment.f10765y + "旺豆领取成功!", "确定", new GBActivity.AbstractC2517a() {
                            /* class com.gbcom.gwifi.functions.product.TaskListFragment.C29351.C29361 */

                            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                            public void onClick(Dialog dialog, View view) {
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnCancelListener() {
                            /* class com.gbcom.gwifi.functions.product.TaskListFragment.C29351.DialogInterface$OnCancelListenerC29372 */

                            public void onCancel(DialogInterface dialogInterface) {
                                dialogInterface.dismiss();
                            }
                        });
                        return;
                    }
                    String string = jSONObject.getString("resultMsg");
                    ((RecommendAppListResponse.DataBean.AppListBean) TaskListFragment.f10756j.get(TaskListFragment.f10761u)).setState(Constant.f13283cE);
                    ((TextView) TaskListFragment.f10753f.getChildAt(TaskListFragment.f10761u).findViewById(C2425R.C2427id.tv_download)).setText("已完成");
                    GBApplication.instance().getCurrentActivity().showSecondDialog("提示", string, "确定", new GBActivity.AbstractC2517a() {
                        /* class com.gbcom.gwifi.functions.product.TaskListFragment.C29351.C29383 */

                        @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                        public void onClick(Dialog dialog, View view) {
                            dialog.dismiss();
                        }
                    }, new DialogInterface.OnCancelListener() {
                        /* class com.gbcom.gwifi.functions.product.TaskListFragment.C29351.DialogInterface$OnCancelListenerC29394 */

                        public void onCancel(DialogInterface dialogInterface) {
                            dialogInterface.dismiss();
                        }
                    });
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: h */
    private SmartTabLayout f10773h;

    /* renamed from: n */
    private PullToRefreshView f10774n;

    /* renamed from: o */
    private int f10775o = 1;

    /* renamed from: p */
    private Request f10776p;

    /* renamed from: q */
    private SimpleDateFormat f10777q;

    /* renamed from: r */
    private HashMap<String, List<RecommendAppListResponse.DataBean.AppListBean>> f10778r;

    /* renamed from: s */
    private String f10779s;

    /* renamed from: z */
    private Request f10780z;

    @Override // android.support.p009v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        List<RecommendAppListResponse.DataBean.AppListBean> list;
        View inflate = layoutInflater.inflate(C2425R.layout.fragment_tasklist_pullto, viewGroup, false);
        f10753f = (ListView) inflate.findViewById(C2425R.C2427id.f8357lv);
        f10754g = (AppDownloadActivity) getActivity();
        f10751A = f10754g.getAppPkgName();
        this.f10773h = f10754g.getViewPagerTab();
        f10759m = f10754g.getViewPager();
        if (this.f10773h.mo34686b(0) != null) {
            f10755i = (TextView) this.f10773h.mo34686b(0);
        }
        HashMap<String, List<RecommendAppListResponse.DataBean.AppListBean>> appDownloadData = CacheApp.getInstance().getAppDownloadData();
        if (!(appDownloadData == null || (list = appDownloadData.get(CacheAccount.getInstance().getLoginPhone())) == null || list.size() <= 0)) {
            this.f10766C = list;
        }
        this.f10777q = new SimpleDateFormat("MM-dd HH:mm");
        this.f10769b = HttpUtil.m14253a(viewGroup.getContext(), this.f10772e, this.f10768a, this.f10775o, 20);
        this.f10774n = (PullToRefreshView) inflate.findViewById(C2425R.C2427id.PullToRefreshView);
        this.f10774n.mo28396a((PullToRefreshView.AbstractC3493a) this);
        this.f10774n.mo28397a((PullToRefreshView.AbstractC3494b) this);
        return inflate;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m12190b(List<RecommendAppListResponse.DataBean.AppListBean> list) {
        f10758l = new C2940a(list);
        f10753f.setAdapter((ListAdapter) f10758l);
    }

    /* renamed from: a */
    public void mo25562a(RecommendAppListResponse.DataBean.AppListBean appListBean) {
        appListBean.setState(Constant.f13280cB);
        int i = -1;
        for (int i2 = 0; i2 < f10756j.size(); i2++) {
            if (f10756j.get(i2).getPkgName().equals(appListBean.getPkgName())) {
                i = i2;
            }
        }
        f10756j.remove(i);
        f10758l.notifyDataSetChanged();
        if (f10755i != null) {
            f10755i.setText("下载列表");
        }
        if (f10759m != null) {
            f10759m.setCurrentItem(1);
        }
        f10754g.getSecondFragment().mo25537a(appListBean);
    }

    @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3493a
    public void onFooterRefresh(PullToRefreshView pullToRefreshView) {
        this.f10775o++;
        this.f10776p = HttpUtil.m14253a(getContext(), this.f10772e, this.f10768a, this.f10775o, 10);
    }

    @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
    public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
        this.f10775o = 1;
        this.f10769b = HttpUtil.m14253a(getContext(), this.f10772e, this.f10768a, this.f10775o, 10);
    }

    /* renamed from: com.gbcom.gwifi.functions.product.c$a */
    /* compiled from: TaskListFragment */
    public class C2940a extends BaseAdapter {

        /* renamed from: b */
        private List<RecommendAppListResponse.DataBean.AppListBean> f10787b = new ArrayList();

        public C2940a(List<RecommendAppListResponse.DataBean.AppListBean> list) {
            this.f10787b = list;
        }

        public int getCount() {
            if (this.f10787b == null) {
                return 0;
            }
            return this.f10787b.size();
        }

        public Object getItem(int i) {
            return this.f10787b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(final int i, View view, final ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.item_tasklist, viewGroup, false);
            final RecommendAppListResponse.DataBean.AppListBean appListBean = this.f10787b.get(i);
            final int productId = appListBean.getProductId();
            ImageView imageView = (ImageView) inflate.findViewById(2131755155);
            inflate.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.product.TaskListFragment.C2940a.View$OnClickListenerC29411 */

                public void onClick(View view) {
                    HttpUtil.m14256a(TaskListFragment.this.getContext(), TaskListFragment.this.f10772e, "", appListBean.getPkgName(), appListBean.getReportExposureUrl());
                    Intent intent = new Intent();
                    intent.putExtra("productId", productId);
                    intent.putExtra("state", appListBean.getState());
                    intent.putExtra("appListBean", appListBean);
                    intent.putExtra("position", i);
                    intent.putExtra("hasFinished", appListBean.getHasFinished());
                    intent.putExtra("currentItem", 0);
                    intent.setClass(viewGroup.getContext(), AppDownloadDetailActivity.class);
                    TaskListFragment.this.startActivity(intent);
                }
            });
            ImageTools.m14149a(appListBean.getIconUrl(), imageView, GBApplication.instance().options);
            ((TextView) inflate.findViewById(2131755200)).setText(appListBean.getAppName());
            ((TextView) inflate.findViewById(C2425R.C2427id.tv_desc)).setText(appListBean.getCategoryInfo().getCategoryName());
            ((TextView) inflate.findViewById(C2425R.C2427id.tv_count)).setText(new DecimalFormat("0.00").format((double) ((((float) Integer.parseInt(appListBean.getFileSize())) / 1024.0f) / 1024.0f)) + "MB  " + TaskListFragment.m12188b(appListBean.getAppDownCount()) + "次下载");
            ImageView imageView2 = (ImageView) inflate.findViewById(C2425R.C2427id.iv_balance);
            final TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.tv_download);
            inflate.findViewById(C2425R.C2427id.ll_balance);
            ((TextView) inflate.findViewById(C2425R.C2427id.tv_balance)).setText("+" + appListBean.getPointsReward() + Constant.f13309cr);
            textView.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.product.TaskListFragment.C2940a.View$OnClickListenerC29422 */

                public void onClick(View view) {
                    if (textView.getText().toString().trim().equals("下载")) {
                        TaskListFragment.this.f10767D.add(appListBean.getPkgName());
                        TaskListFragment.f10756j.remove(i);
                        appListBean.setState(Constant.f13280cB);
                        TaskListFragment.f10758l.notifyDataSetChanged();
                        if (TaskListFragment.f10755i != null) {
                            TaskListFragment.f10755i.setText("下载列表");
                        }
                        if (TaskListFragment.f10759m != null) {
                            TaskListFragment.f10759m.setCurrentItem(1);
                        }
                        int unused = TaskListFragment.f10752B = i;
                        EventBus.m39385a().mo61756c(new MessageEvent(appListBean));
                    }
                }
            });
            return inflate;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static String m12188b(long j) {
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
}
