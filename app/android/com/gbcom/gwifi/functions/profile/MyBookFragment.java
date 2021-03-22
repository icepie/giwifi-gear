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
import com.gbcom.gwifi.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.gbcom.gwifi.functions.profile.b */
public class MyBookFragment extends GBFragment {

    /* renamed from: c */
    private static final String f11023c = "MyBookFragment";

    /* renamed from: a */
    List<AttentionFile> f11024a = new ArrayList();

    /* renamed from: b */
    View.OnClickListener f11025b = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.profile.MyBookFragment.View$OnClickListenerC29851 */

        public void onClick(View view) {
            switch (view.getId()) {
                case C2425R.C2427id.book_all_cancel /*{ENCODED_INT: 2131755720}*/:
                    MyBookFragment.this.f11034l = !MyBookFragment.this.f11034l;
                    if (MyBookFragment.this.f11034l) {
                        MyBookFragment.this.f11029g.setText("取消");
                        return;
                    } else {
                        MyBookFragment.this.f11029g.setText("全选");
                        return;
                    }
                case C2425R.C2427id.book_delete /*{ENCODED_INT: 2131755721}*/:
                    if (MyBookFragment.this.f11024a.size() <= 0 || MyBookFragment.this.f11032j.size() <= 0) {
                        GBActivity.showMessageToast("亲！请先选中想删除的项哦");
                        return;
                    }
                    ((GBActivity) MyBookFragment.this.getActivity()).showProgressDialog("正在删除请稍等…");
                    MyBookFragment.this.f11024a.removeAll(MyBookFragment.this.f11032j);
                    AttentionFileDao.m12148b().delete((Context) GBApplication.instance(), (Collection) MyBookFragment.this.f11032j);
                    ((GBActivity) MyBookFragment.this.getActivity()).dismissProgressDialog();
                    GBActivity.showMessageToast("删除成功！");
                    return;
                case C2425R.C2427id.my_game_rl /*{ENCODED_INT: 2131755752}*/:
                    Log.m14400c(MyBookFragment.f11023c, "努力开发中!");
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: d */
    private TextView f11026d;

    /* renamed from: e */
    private ListView f11027e;

    /* renamed from: f */
    private RelativeLayout f11028f;

    /* renamed from: g */
    private Button f11029g;

    /* renamed from: h */
    private Button f11030h;

    /* renamed from: i */
    private C2986a f11031i;

    /* renamed from: j */
    private List<AttentionFile> f11032j = new ArrayList();

    /* renamed from: k */
    private boolean f11033k = false;

    /* renamed from: l */
    private boolean f11034l = false;

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C2425R.layout.my_book_fragment, viewGroup, false);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f11026d = (TextView) view.findViewById(C2425R.C2427id.book_attention_tv);
        this.f11027e = (ListView) view.findViewById(C2425R.C2427id.book_attention_lv);
        this.f11028f = (RelativeLayout) view.findViewById(C2425R.C2427id.bt_Layout);
        this.f11029g = (Button) view.findViewById(C2425R.C2427id.book_all_cancel);
        this.f11030h = (Button) view.findViewById(C2425R.C2427id.book_delete);
        m12376c();
        this.f11029g.setOnClickListener(this.f11025b);
        this.f11030h.setOnClickListener(this.f11025b);
    }

    /* renamed from: c */
    private void m12376c() {
        this.f11024a = AttentionFileDao.m12148b().mo24203a(GBApplication.instance(), "productType", Constant.f13162Q);
        this.f11026d.setText("正在开发中…");
    }

    /* renamed from: com.gbcom.gwifi.functions.profile.b$a */
    /* compiled from: MyBookFragment */
    private class C2986a extends BaseAdapter {
        private C2986a() {
        }

        public int getCount() {
            return MyBookFragment.this.f11024a.size();
        }

        public Object getItem(int i) {
            return MyBookFragment.this.f11024a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            final AttentionFile attentionFile = MyBookFragment.this.f11024a.get(i);
            View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.my_book_item, viewGroup, false);
            CheckBox checkBox = (CheckBox) inflate.findViewById(C2425R.C2427id.book_cb);
            TextView textView = (TextView) inflate.findViewById(16908310);
            TextView textView2 = (TextView) inflate.findViewById(16908308);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.book_size);
            TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.download_num);
            ((ImageView) inflate.findViewById(C2425R.C2427id.book_icon)).setImageBitmap(BitmapFactory.decodeByteArray(attentionFile.getCacheIcon(), 0, attentionFile.getCacheIcon().length));
            if (MyBookFragment.this.f11033k) {
                checkBox.setVisibility(0);
                if (MyBookFragment.this.f11034l) {
                    checkBox.setChecked(true);
                    MyBookFragment.this.f11032j.clear();
                    MyBookFragment.this.f11032j.addAll(MyBookFragment.this.f11024a);
                } else {
                    checkBox.setChecked(false);
                    MyBookFragment.this.f11032j.clear();
                }
            } else {
                checkBox.setVisibility(8);
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                /* class com.gbcom.gwifi.functions.profile.MyBookFragment.C2986a.C29871 */

                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        MyBookFragment.this.f11032j.add(attentionFile);
                    } else {
                        MyBookFragment.this.f11032j.remove(attentionFile);
                    }
                }
            });
            textView.setText(attentionFile.getTitle());
            textView2.setText(C3467s.m14506a(C3467s.m14509b(attentionFile.getTags()), "  "));
            textView3.setText(C3467s.m14501a(attentionFile.getFileTotalSize().longValue()));
            textView4.setText("已下载" + C3467s.m14510c((long) attentionFile.getDownloadCount().intValue()));
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C2425R.C2427id.my_book_rl);
            relativeLayout.setTag(Integer.valueOf(i));
            relativeLayout.setOnClickListener(MyBookFragment.this.f11025b);
            return inflate;
        }
    }

    /* renamed from: a */
    public void mo25995a() {
        this.f11033k = !this.f11033k;
        if (this.f11033k) {
            this.f11028f.setVisibility(0);
            return;
        }
        this.f11034l = false;
        this.f11029g.setText("全选");
        this.f11028f.setVisibility(8);
    }

    /* renamed from: b */
    public void mo25996b() {
        this.f11033k = false;
        this.f11028f.setVisibility(8);
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("我的小说界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        GiwifiMobclickAgent.onPageStart("我的小说界面");
    }
}
