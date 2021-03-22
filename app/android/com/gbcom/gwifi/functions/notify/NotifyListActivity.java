package com.gbcom.gwifi.functions.notify;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.app.GBElementManager;
import com.gbcom.gwifi.base.p232a.DatabaseHelper;
import com.gbcom.gwifi.functions.notify.domain.Notify;
import com.gbcom.gwifi.functions.notify.p245a.NotifyDao;
import com.gbcom.gwifi.functions.notify.p246b.NotifyType;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.FormatUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class NotifyListActivity extends GBActivity implements View.OnClickListener, View.OnLongClickListener {

    /* renamed from: a */
    private static final String f9913a = "NotifyListActivity";

    /* renamed from: b */
    private ListView f9914b;

    /* renamed from: c */
    private RelativeLayout f9915c;

    /* renamed from: d */
    private TextView f9916d;

    /* renamed from: e */
    private TextView f9917e;

    /* renamed from: f */
    private C2791a f9918f;

    /* renamed from: g */
    private LinkedHashMap<String, List<Notify>> f9919g = new LinkedHashMap<>();

    /* renamed from: h */
    private ArrayList<String> f9920h = new ArrayList<>();

    /* renamed from: i */
    private List<Notify> f9921i = new ArrayList();

    /* renamed from: j */
    private String f9922j;

    /* renamed from: k */
    private BroadcastReceiver f9923k = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.notify.NotifyListActivity.C27883 */

        public void onReceive(Context context, Intent intent) {
            NotifyListActivity.this.f9920h.clear();
            NotifyListActivity.this.f9919g.clear();
            NotifyListActivity.this.m11486a();
            NotifyListActivity.this.f9918f.notifyDataSetChanged();
        }
    };

    /* renamed from: l */
    private ImageLoadingListener f9924l = new ImageLoadingListener() {
        /* class com.gbcom.gwifi.functions.notify.NotifyListActivity.C27894 */

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingFailed(String str, View view, FailReason failReason) {
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingStarted(String str, View view) {
        }
    };

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "通知界面", C2425R.layout.notify_list_activity, true);
        this.f9922j = getIntent().getStringExtra("UMessage");
        if (!C3467s.m14513e(this.f9922j)) {
            m11490a(this.f9922j);
        }
        this.f9915c = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f9915c.setOnClickListener(this);
        this.f9916d = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f9916d.setVisibility(4);
        this.f9917e = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f9917e.setText("我的消息");
        m11486a();
        this.f9914b = (ListView) findViewById(C2425R.C2427id.notifyLV);
        this.f9918f = new C2791a();
        this.f9914b.setAdapter((ListAdapter) this.f9918f);
        registerReceiver(this.f9923k, new IntentFilter(Constant.f13288cJ));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11486a() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Notify notify : NotifyDao.m11495b().mo24212d(this).orderBy("createTime", false).query()) {
                Long createTime = notify.getCreateTime();
                notify.getReceiveTime();
                String format = simpleDateFormat.format(new Date(createTime.longValue()));
                if (!this.f9920h.contains(format)) {
                    this.f9920h.add(format);
                }
                List<Notify> list = this.f9919g.get(format);
                if (list == null) {
                    list = new ArrayList<>();
                    this.f9919g.put(format, list);
                }
                list.add(notify);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.notify_list_item_ly /*{ENCODED_INT: 2131755852}*/:
            case C2425R.C2427id.notify_list_link /*{ENCODED_INT: 2131755856}*/:
                Notify notify = (Notify) view.getTag();
                UmengCount.queryNotifyItem(GBApplication.instance(), notify.getTitle());
                switch (NotifyType.m11499a(notify.getType().intValue())) {
                    case text:
                    case contentUpdate:
                    case authOnline:
                    case userOffline:
                    default:
                        return;
                    case wap:
                        GBActivity.openBackWebView(notify.getWapUrl(), notify.getTitle());
                        return;
                    case dialup:
                        callPhone(notify.getPhone());
                        return;
                    case versionUpdate:
                        checkVersion();
                        return;
                    case productRecommend:
                        Long productId = notify.getProductId();
                        String productType = notify.getProductType();
                        notify.getCreateTime().longValue();
                        GBElementManager.m10471a();
                        GBElementManager.m10474a(this, null, productType, productId.intValue());
                        return;
                    case charge:
                        if (notify.getWapUrl() != null && !notify.getWapUrl().equals("")) {
                            GBActivity.openBackWebView(notify.getWapUrl(), notify.getTitle());
                            return;
                        }
                        return;
                    case pointChange:
                        if (notify.getWapUrl() != null && !notify.getWapUrl().equals("")) {
                            GBActivity.openBackWebView(notify.getWapUrl(), notify.getTitle());
                            return;
                        }
                        return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.notify.NotifyListActivity$a */
    public class C2791a extends BaseAdapter {
        private C2791a() {
        }

        public int getCount() {
            return NotifyListActivity.this.f9919g.size();
        }

        public Object getItem(int i) {
            return NotifyListActivity.this.f9919g.get(Integer.valueOf(i));
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            String str = (String) NotifyListActivity.this.f9920h.get(i);
            NotifyListActivity.this.f9921i = (List) NotifyListActivity.this.f9919g.get(str);
            View inflate = NotifyListActivity.this.getLayoutInflater().inflate(C2425R.layout.notify_time_item, viewGroup, false);
            NotifyListActivity.this.m11488a((NotifyListActivity) ((LinearLayout) inflate.findViewById(C2425R.C2427id.notify_time_list)));
            ((TextView) inflate.findViewById(C2425R.C2427id.notify_time)).setText(str);
            return inflate;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11488a(LinearLayout linearLayout) {
        for (int i = 0; i < this.f9921i.size(); i++) {
            Notify notify = this.f9921i.get(i);
            Long createTime = notify.getCreateTime();
            View inflate = getLayoutInflater().inflate(C2425R.layout.notify_list_item, (ViewGroup) null);
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(C2425R.C2427id.notify_list_link);
            LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(C2425R.C2427id.notify_list_item_ly);
            ((TextView) inflate.findViewById(C2425R.C2427id.receive_time)).setText(FormatUtil.m14214a(createTime.longValue()));
            linearLayout3.setOnLongClickListener(this);
            linearLayout3.setOnClickListener(this);
            linearLayout2.setOnClickListener(this);
            linearLayout2.setTag(notify);
            linearLayout3.setTag(notify);
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.notify_list_img);
            TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.notify_list_text);
            ((TextView) inflate.findViewById(C2425R.C2427id.notify_info)).setText(notify.getContent());
            m11487a(notify.getType().intValue(), imageView, textView, (ImageView) inflate.findViewById(C2425R.C2427id.notify_img), notify.getImageUrl(), notify.getWapUrl());
            linearLayout.addView(inflate);
        }
    }

    /* renamed from: a */
    private void m11487a(int i, ImageView imageView, TextView textView, ImageView imageView2, String str, String str2) {
        switch (NotifyType.m11499a(i)) {
            case text:
                Log.m14400c(f9913a, "type....." + i);
                if (str != null && !str.equals("")) {
                    m11491a(str, imageView2);
                    imageView2.setVisibility(0);
                }
                imageView.setVisibility(8);
                textView.setVisibility(8);
                return;
            case wap:
                if (str != null && !str.equals("")) {
                    m11491a(str, imageView2);
                    imageView2.setVisibility(0);
                }
                imageView.setImageResource(C2425R.C2426drawable.web_link);
                textView.setText("查看详情");
                imageView.setVisibility(0);
                textView.setVisibility(0);
                return;
            case dialup:
                if (str != null && !str.equals("")) {
                    m11491a(str, imageView2);
                    imageView2.setVisibility(0);
                }
                imageView.setImageResource(C2425R.C2426drawable.telephone);
                textView.setText("联系我们");
                imageView.setVisibility(0);
                textView.setVisibility(0);
                return;
            case versionUpdate:
                if (str != null && !str.equals("")) {
                    m11491a(str, imageView2);
                    imageView2.setVisibility(0);
                }
                imageView.setVisibility(8);
                textView.setText("版本更新");
                textView.setVisibility(0);
                return;
            case productRecommend:
                if (str != null && !str.equals("")) {
                    m11491a(str, imageView2);
                    imageView2.setVisibility(0);
                }
                imageView.setVisibility(8);
                textView.setText("产品推荐");
                textView.setVisibility(0);
                return;
            case charge:
                if (str != null && !str.equals("")) {
                    m11491a(str, imageView2);
                    imageView2.setVisibility(0);
                }
                if (str2 != null && !str2.equals("")) {
                    imageView.setImageResource(C2425R.C2426drawable.web_link);
                    textView.setText("查看详情");
                    imageView.setVisibility(0);
                    textView.setVisibility(0);
                    return;
                }
                return;
            case contentUpdate:
                if (str != null && !str.equals("")) {
                    m11491a(str, imageView2);
                    imageView2.setVisibility(0);
                    return;
                }
                return;
            case authOnline:
                if (str != null && !str.equals("")) {
                    m11491a(str, imageView2);
                    imageView2.setVisibility(0);
                }
                imageView.setVisibility(8);
                textView.setVisibility(8);
                return;
            case pointChange:
                if (str != null && !str.equals("")) {
                    m11491a(str, imageView2);
                    imageView2.setVisibility(0);
                }
                imageView.setVisibility(8);
                textView.setVisibility(8);
                return;
            case userOffline:
                if (str != null && !str.equals("")) {
                    m11491a(str, imageView2);
                    imageView2.setVisibility(0);
                }
                imageView.setVisibility(8);
                textView.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public boolean onLongClick(final View view) {
        switch (view.getId()) {
            case C2425R.C2427id.notify_list_item_ly /*{ENCODED_INT: 2131755852}*/:
                showNormalDialog("通知", "是否删除？", "删除", "取消", new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.notify.NotifyListActivity.C27861 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        NotifyDao.m11495b().delete(GBApplication.instance(), (Notify) view.getTag());
                        NotifyListActivity.this.f9920h.clear();
                        NotifyListActivity.this.f9919g.clear();
                        NotifyListActivity.this.m11486a();
                        NotifyListActivity.this.f9918f.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }, new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.notify.NotifyListActivity.C27872 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                    }
                });
                return false;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onStop() {
        try {
            DatabaseHelper.m10426a(this).getDao(Notify.class).updateRaw("UPDATE Notify  SET read='1' WHERE read='0'", new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sendBroadcast(new Intent(Constant.f13286cH));
        super.onStop();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        unregisterReceiver(this.f9923k);
        super.onDestroy();
    }

    /* renamed from: a */
    private void m11491a(String str, ImageView imageView) {
        ImageTools.m14151a(str, imageView, GBApplication.instance().options, this.f9924l, ImageLoader.DEALIMAGE.FILLWIDTH);
    }

    /* renamed from: a */
    private void m11490a(String str) {
        HashMap hashMap;
        String obj;
        String obj2;
        int intValue;
        String obj3;
        if (str != null && (hashMap = (HashMap) JsonUtil.m14386a(str, HashMap.class)) != null) {
            String str2 = (String) hashMap.get("title");
            String str3 = (String) hashMap.get("content");
            if (((String) hashMap.get("wapUrl")) == null) {
                obj = "";
            } else {
                obj = hashMap.get("wapUrl").toString();
            }
            if (((String) hashMap.get(Constant.f13323i)) == null) {
                obj2 = "";
            } else {
                obj2 = hashMap.get(Constant.f13323i).toString();
            }
            if (((Integer) hashMap.get("type")) == null) {
                intValue = 0;
            } else {
                intValue = ((Integer) hashMap.get("type")).intValue();
            }
            Integer valueOf = Integer.valueOf(intValue);
            Long.valueOf(hashMap.get("createTime") == null ? System.currentTimeMillis() : Long.parseLong(hashMap.get("createTime").toString()));
            Long valueOf2 = Long.valueOf(hashMap.get("productId") == null ? System.currentTimeMillis() : Long.parseLong(hashMap.get("productId").toString()));
            if (hashMap.get("msgId") == null) {
                String str4 = "" + System.currentTimeMillis();
            } else {
                hashMap.get("msgId").toString();
            }
            if (((String) hashMap.get("productType")) == null) {
                obj3 = "";
            } else {
                obj3 = hashMap.get("productType").toString();
            }
            if (((String) hashMap.get("imageUrl")) != null) {
                hashMap.get("imageUrl").toString();
            }
            if (hashMap.containsKey("iconUrl")) {
                String str5 = (String) hashMap.get("iconUrl");
            }
            if (hashMap.containsKey("bigIconUrl")) {
                String str6 = (String) hashMap.get("bigIconUrl");
            }
            switch (NotifyType.m11499a(valueOf.intValue())) {
                case text:
                case contentUpdate:
                case authOnline:
                case userOffline:
                default:
                    return;
                case wap:
                    GBActivity.openBackWebView(obj, str2);
                    return;
                case dialup:
                    callPhone(obj2);
                    return;
                case versionUpdate:
                    checkVersion();
                    return;
                case productRecommend:
                    GBElementManager.m10471a();
                    GBElementManager.m10474a(this, null, obj3, valueOf2.intValue());
                    return;
                case charge:
                    if (obj != null && !obj.equals("")) {
                        GBActivity.openBackWebView(obj, str2);
                        return;
                    }
                    return;
                case pointChange:
                    if (obj != null && !obj.equals("")) {
                        GBActivity.openBackWebView(obj, str2);
                        return;
                    }
                    return;
            }
        }
    }
}
