package com.gbcom.gwifi.functions.profile;

import android.content.Context;
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
import com.gbcom.gwifi.functions.product.domain.AttentionFile;
import com.gbcom.gwifi.functions.product.p248b.AttentionFileDao;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.gbcom.gwifi.functions.profile.d */
public class MyVideoFragment extends GBFragment {

    /* renamed from: a */
    List<AttentionFile> f11054a = new ArrayList();

    /* renamed from: b */
    View.OnClickListener f11055b = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.profile.MyVideoFragment.View$OnClickListenerC29911 */

        public void onClick(View view) {
            switch (view.getId()) {
                case C2425R.C2427id.video_all_cancel /*{ENCODED_INT: 2131755784}*/:
                    MyVideoFragment.this.f11064k = !MyVideoFragment.this.f11064k;
                    if (MyVideoFragment.this.f11064k) {
                        MyVideoFragment.this.f11059f.setText("取消");
                    } else {
                        MyVideoFragment.this.f11059f.setText("全选");
                    }
                    MyVideoFragment.this.f11061h.notifyDataSetChanged();
                    return;
                case C2425R.C2427id.video_delete /*{ENCODED_INT: 2131755785}*/:
                    if (MyVideoFragment.this.f11054a.size() <= 0 || MyVideoFragment.this.f11062i.size() <= 0) {
                        GBActivity.showMessageToast("亲！请先选中想删除的项哦");
                        return;
                    }
                    ((GBActivity) MyVideoFragment.this.getActivity()).showProgressDialog("正在删除请稍等…");
                    MyVideoFragment.this.f11054a.removeAll(MyVideoFragment.this.f11062i);
                    MyVideoFragment.this.f11056c.setText("视频共" + MyVideoFragment.this.f11054a.size() + "个关注");
                    AttentionFileDao.m12148b().delete((Context) GBApplication.instance(), (Collection) MyVideoFragment.this.f11062i);
                    MyVideoFragment.this.f11062i.clear();
                    MyVideoFragment.this.f11061h.notifyDataSetChanged();
                    ((GBActivity) MyVideoFragment.this.getActivity()).dismissProgressDialog();
                    GBActivity.showMessageToast("删除成功！");
                    return;
                case C2425R.C2427id.video_attention_lv /*{ENCODED_INT: 2131755786}*/:
                default:
                    return;
                case C2425R.C2427id.my_video_rl /*{ENCODED_INT: 2131755787}*/:
                    MyVideoFragment.this.f11054a.get(((Integer) view.getTag()).intValue()).getSid().intValue();
                    MyVideoFragment.this.getActivity().finish();
                    return;
            }
        }
    };

    /* renamed from: c */
    private TextView f11056c;

    /* renamed from: d */
    private ListView f11057d;

    /* renamed from: e */
    private RelativeLayout f11058e;

    /* renamed from: f */
    private Button f11059f;

    /* renamed from: g */
    private Button f11060g;

    /* renamed from: h */
    private C2992a f11061h;

    /* renamed from: i */
    private List<AttentionFile> f11062i = new ArrayList();

    /* renamed from: j */
    private boolean f11063j = false;

    /* renamed from: k */
    private boolean f11064k = false;

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C2425R.layout.my_video_fragment, viewGroup, false);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f11056c = (TextView) view.findViewById(C2425R.C2427id.video_attention_tv);
        this.f11057d = (ListView) view.findViewById(C2425R.C2427id.video_attention_lv);
        this.f11058e = (RelativeLayout) view.findViewById(C2425R.C2427id.bt_Layout);
        this.f11059f = (Button) view.findViewById(C2425R.C2427id.video_all_cancel);
        this.f11060g = (Button) view.findViewById(C2425R.C2427id.video_delete);
        m12394c();
        this.f11061h = new C2992a();
        this.f11057d.setAdapter((ListAdapter) this.f11061h);
        this.f11059f.setOnClickListener(this.f11055b);
        this.f11060g.setOnClickListener(this.f11055b);
    }

    /* renamed from: c */
    private void m12394c() {
        this.f11054a = AttentionFileDao.m12148b().mo24203a(GBApplication.instance(), "productType", Constant.f13158M);
        this.f11056c.setText("视频共" + this.f11054a.size() + "个关注");
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.profile.d$a */
    /* compiled from: MyVideoFragment */
    public class C2992a extends BaseAdapter {
        private C2992a() {
        }

        public int getCount() {
            return MyVideoFragment.this.f11054a.size();
        }

        public Object getItem(int i) {
            return MyVideoFragment.this.f11054a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            final AttentionFile attentionFile = MyVideoFragment.this.f11054a.get(i);
            View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.my_video_item, viewGroup, false);
            CheckBox checkBox = (CheckBox) inflate.findViewById(C2425R.C2427id.video_cb);
            TextView textView = (TextView) inflate.findViewById(16908310);
            TextView textView2 = (TextView) inflate.findViewById(16908308);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.video_size);
            TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.download_num);
            ((ImageView) inflate.findViewById(2131755267)).setImageBitmap(BitmapFactory.decodeByteArray(attentionFile.getCacheIcon(), 0, attentionFile.getCacheIcon().length));
            if (MyVideoFragment.this.f11063j) {
                checkBox.setVisibility(0);
                if (MyVideoFragment.this.f11064k) {
                    checkBox.setChecked(true);
                    MyVideoFragment.this.f11062i.clear();
                    MyVideoFragment.this.f11062i.addAll(MyVideoFragment.this.f11054a);
                } else {
                    checkBox.setChecked(false);
                    MyVideoFragment.this.f11062i.clear();
                }
            } else {
                checkBox.setVisibility(8);
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                /* class com.gbcom.gwifi.functions.profile.MyVideoFragment.C2992a.C29931 */

                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        MyVideoFragment.this.f11062i.add(attentionFile);
                    } else {
                        MyVideoFragment.this.f11062i.remove(attentionFile);
                    }
                }
            });
            textView.setText(attentionFile.getTitle());
            textView2.setText(C3467s.m14506a(C3467s.m14509b(attentionFile.getTags()), "  "));
            textView3.setText(C3467s.m14501a(attentionFile.getFileTotalSize().longValue()));
            textView4.setText("已下载" + C3467s.m14510c((long) attentionFile.getDownloadCount().intValue()));
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C2425R.C2427id.my_video_rl);
            relativeLayout.setTag(Integer.valueOf(i));
            relativeLayout.setOnClickListener(MyVideoFragment.this.f11055b);
            return inflate;
        }
    }

    /* renamed from: a */
    public void mo26011a() {
        this.f11063j = !this.f11063j;
        if (this.f11063j) {
            this.f11058e.setVisibility(0);
        } else {
            this.f11064k = false;
            this.f11059f.setText("全选");
            this.f11058e.setVisibility(8);
        }
        this.f11061h.notifyDataSetChanged();
    }

    /* renamed from: b */
    public void mo26012b() {
        this.f11063j = false;
        this.f11058e.setVisibility(8);
        this.f11061h.notifyDataSetChanged();
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("我的视频界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        GiwifiMobclickAgent.onPageStart("我的视频界面");
    }
}
