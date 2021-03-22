package com.gbcom.gwifi.functions.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.product.AppActivity;
import com.gbcom.gwifi.functions.product.domain.AttentionFile;
import com.gbcom.gwifi.functions.product.p248b.AttentionFileDao;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.gbcom.gwifi.functions.profile.a */
public class MyAppFragment extends GBFragment {

    /* renamed from: a */
    List<AttentionFile> f10997a = new ArrayList();

    /* renamed from: b */
    View.OnClickListener f10998b = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.profile.MyAppFragment.View$OnClickListenerC29791 */

        public void onClick(View view) {
            switch (view.getId()) {
                case C2425R.C2427id.app_all_cancel /*{ENCODED_INT: 2131755713}*/:
                    MyAppFragment.this.f11007k = !MyAppFragment.this.f11007k;
                    if (MyAppFragment.this.f11007k) {
                        MyAppFragment.this.f11002f.setText("取消");
                    } else {
                        MyAppFragment.this.f11002f.setText("全选");
                    }
                    MyAppFragment.this.f11004h.notifyDataSetChanged();
                    return;
                case C2425R.C2427id.app_delete /*{ENCODED_INT: 2131755714}*/:
                    if (MyAppFragment.this.f10997a.size() <= 0 || MyAppFragment.this.f11005i.size() <= 0) {
                        GBActivity.showMessageToast("亲！请先选中想删除的项哦");
                        return;
                    }
                    ((GBActivity) MyAppFragment.this.getActivity()).showProgressDialog("正在删除请稍等…");
                    MyAppFragment.this.f10997a.removeAll(MyAppFragment.this.f11005i);
                    MyAppFragment.this.f10999c.setText("应用共" + MyAppFragment.this.f10997a.size() + "个关注");
                    AttentionFileDao.m12148b().delete((Context) GBApplication.instance(), (Collection) MyAppFragment.this.f11005i);
                    MyAppFragment.this.f11004h.notifyDataSetChanged();
                    ((GBActivity) MyAppFragment.this.getActivity()).dismissProgressDialog();
                    GBActivity.showMessageToast("删除成功！");
                    return;
                case C2425R.C2427id.app_attention_lv /*{ENCODED_INT: 2131755715}*/:
                default:
                    return;
                case C2425R.C2427id.my_app_rl /*{ENCODED_INT: 2131755716}*/:
                    int intValue = MyAppFragment.this.f10997a.get(((Integer) view.getTag()).intValue()).getSid().intValue();
                    Intent intent = new Intent(GBApplication.instance(), AppActivity.class);
                    intent.putExtra("productId", intValue);
                    MyAppFragment.this.startActivity(intent);
                    MyAppFragment.this.getActivity().finish();
                    return;
            }
        }
    };

    /* renamed from: c */
    private TextView f10999c;

    /* renamed from: d */
    private ListView f11000d;

    /* renamed from: e */
    private RelativeLayout f11001e;

    /* renamed from: f */
    private Button f11002f;

    /* renamed from: g */
    private Button f11003g;

    /* renamed from: h */
    private C2980a f11004h;

    /* renamed from: i */
    private List<AttentionFile> f11005i = new ArrayList();

    /* renamed from: j */
    private boolean f11006j = false;

    /* renamed from: k */
    private boolean f11007k = false;

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C2425R.layout.my_app_fragment, viewGroup, false);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f10999c = (TextView) view.findViewById(C2425R.C2427id.app_attention_tv);
        this.f11000d = (ListView) view.findViewById(C2425R.C2427id.app_attention_lv);
        this.f11001e = (RelativeLayout) view.findViewById(C2425R.C2427id.bt_Layout);
        this.f11002f = (Button) view.findViewById(C2425R.C2427id.app_all_cancel);
        this.f11003g = (Button) view.findViewById(C2425R.C2427id.app_delete);
        m12357c();
        this.f11004h = new C2980a();
        this.f11000d.setAdapter((ListAdapter) this.f11004h);
        this.f11002f.setOnClickListener(this.f10998b);
        this.f11003g.setOnClickListener(this.f10998b);
    }

    /* renamed from: c */
    private void m12357c() {
        this.f10997a = AttentionFileDao.m12148b().mo24203a(GBApplication.instance(), "productType", Constant.f13160O);
        this.f10999c.setText("应用共" + this.f10997a.size() + "个关注");
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.profile.a$a */
    /* compiled from: MyAppFragment */
    public class C2980a extends BaseAdapter {
        private C2980a() {
        }

        public int getCount() {
            return MyAppFragment.this.f10997a.size();
        }

        public Object getItem(int i) {
            return MyAppFragment.this.f10997a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            final AttentionFile attentionFile = MyAppFragment.this.f10997a.get(i);
            View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.my_app_item, viewGroup, false);
            CheckBox checkBox = (CheckBox) inflate.findViewById(C2425R.C2427id.app_cb);
            TextView textView = (TextView) inflate.findViewById(16908310);
            TextView textView2 = (TextView) inflate.findViewById(16908308);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.app_size);
            TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.download_num);
            ((ImageView) inflate.findViewById(C2425R.C2427id.app_icon)).setImageBitmap(BitmapFactory.decodeByteArray(attentionFile.getCacheIcon(), 0, attentionFile.getCacheIcon().length));
            if (MyAppFragment.this.f11006j) {
                checkBox.setVisibility(0);
                if (MyAppFragment.this.f11007k) {
                    checkBox.setChecked(true);
                    MyAppFragment.this.f11005i.clear();
                    MyAppFragment.this.f11005i.addAll(MyAppFragment.this.f10997a);
                } else {
                    checkBox.setChecked(false);
                    MyAppFragment.this.f11005i.clear();
                }
            } else {
                checkBox.setVisibility(8);
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                /* class com.gbcom.gwifi.functions.profile.MyAppFragment.C2980a.C29811 */

                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        MyAppFragment.this.f11005i.add(attentionFile);
                    } else {
                        MyAppFragment.this.f11005i.remove(attentionFile);
                    }
                }
            });
            textView.setText(attentionFile.getTitle());
            textView2.setText(C3467s.m14506a(C3467s.m14509b(attentionFile.getTags()), "  "));
            textView3.setText(C3467s.m14501a(attentionFile.getFileTotalSize().longValue()));
            textView4.setText("已下载" + C3467s.m14510c((long) attentionFile.getDownloadCount().intValue()));
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C2425R.C2427id.my_app_rl);
            relativeLayout.setTag(Integer.valueOf(i));
            relativeLayout.setOnClickListener(MyAppFragment.this.f10998b);
            return inflate;
        }
    }

    /* renamed from: a */
    public void mo25979a() {
        this.f11006j = !this.f11006j;
        if (this.f11006j) {
            this.f11001e.setVisibility(0);
        } else {
            this.f11007k = false;
            this.f11002f.setText("全选");
            this.f11001e.setVisibility(8);
        }
        this.f11004h.notifyDataSetChanged();
    }

    /* renamed from: b */
    public void mo25980b() {
        this.f11006j = false;
        this.f11001e.setVisibility(8);
        this.f11004h.notifyDataSetChanged();
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("我的应用界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        GiwifiMobclickAgent.onPageStart("我的应用界面");
    }
}
