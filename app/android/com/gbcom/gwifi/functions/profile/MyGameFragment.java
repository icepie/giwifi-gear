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
import com.gbcom.gwifi.functions.product.GameActivity;
import com.gbcom.gwifi.functions.product.domain.AttentionFile;
import com.gbcom.gwifi.functions.product.p248b.AttentionFileDao;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.gbcom.gwifi.functions.profile.c */
public class MyGameFragment extends GBFragment {

    /* renamed from: a */
    List<AttentionFile> f11039a = new ArrayList();

    /* renamed from: b */
    View.OnClickListener f11040b = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.profile.MyGameFragment.View$OnClickListenerC29881 */

        public void onClick(View view) {
            switch (view.getId()) {
                case C2425R.C2427id.game_all_cancel /*{ENCODED_INT: 2131755749}*/:
                    MyGameFragment.this.f11049k = !MyGameFragment.this.f11049k;
                    if (MyGameFragment.this.f11049k) {
                        MyGameFragment.this.f11044f.setText("取消");
                    } else {
                        MyGameFragment.this.f11044f.setText("全选");
                    }
                    MyGameFragment.this.f11046h.notifyDataSetChanged();
                    return;
                case C2425R.C2427id.game_delete /*{ENCODED_INT: 2131755750}*/:
                    if (MyGameFragment.this.f11039a.size() <= 0 || MyGameFragment.this.f11047i.size() <= 0) {
                        GBActivity.showMessageToast("亲！请先选中想删除的项哦");
                        return;
                    }
                    ((GBActivity) MyGameFragment.this.getActivity()).showProgressDialog("正在删除请稍等…");
                    MyGameFragment.this.f11039a.removeAll(MyGameFragment.this.f11047i);
                    MyGameFragment.this.f11041c.setText("视频共" + MyGameFragment.this.f11039a.size() + "个关注");
                    AttentionFileDao.m12148b().delete((Context) GBApplication.instance(), (Collection) MyGameFragment.this.f11047i);
                    MyGameFragment.this.f11046h.notifyDataSetChanged();
                    ((GBActivity) MyGameFragment.this.getActivity()).dismissProgressDialog();
                    GBActivity.showMessageToast("删除成功！");
                    return;
                case C2425R.C2427id.game_attention_lv /*{ENCODED_INT: 2131755751}*/:
                default:
                    return;
                case C2425R.C2427id.my_game_rl /*{ENCODED_INT: 2131755752}*/:
                    int intValue = MyGameFragment.this.f11039a.get(((Integer) view.getTag()).intValue()).getSid().intValue();
                    Intent intent = new Intent(GBApplication.instance(), GameActivity.class);
                    intent.putExtra("productId", intValue);
                    MyGameFragment.this.startActivity(intent);
                    MyGameFragment.this.getActivity().finish();
                    return;
            }
        }
    };

    /* renamed from: c */
    private TextView f11041c;

    /* renamed from: d */
    private ListView f11042d;

    /* renamed from: e */
    private RelativeLayout f11043e;

    /* renamed from: f */
    private Button f11044f;

    /* renamed from: g */
    private Button f11045g;

    /* renamed from: h */
    private C2989a f11046h;

    /* renamed from: i */
    private List<AttentionFile> f11047i = new ArrayList();

    /* renamed from: j */
    private boolean f11048j = false;

    /* renamed from: k */
    private boolean f11049k = false;

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C2425R.layout.my_game_fragment, viewGroup, false);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f11041c = (TextView) view.findViewById(C2425R.C2427id.game_attention_tv);
        this.f11042d = (ListView) view.findViewById(C2425R.C2427id.game_attention_lv);
        this.f11043e = (RelativeLayout) view.findViewById(C2425R.C2427id.bt_Layout);
        this.f11044f = (Button) view.findViewById(C2425R.C2427id.game_all_cancel);
        this.f11045g = (Button) view.findViewById(C2425R.C2427id.game_delete);
        this.f11046h = new C2989a();
        m12384c();
        this.f11042d.setAdapter((ListAdapter) this.f11046h);
        this.f11044f.setOnClickListener(this.f11040b);
        this.f11045g.setOnClickListener(this.f11040b);
    }

    /* renamed from: c */
    private void m12384c() {
        this.f11039a = AttentionFileDao.m12148b().mo24203a(GBApplication.instance(), "productType", Constant.f13162Q);
        this.f11041c.setText("游戏共" + this.f11039a.size() + "个关注");
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.profile.c$a */
    /* compiled from: MyGameFragment */
    public class C2989a extends BaseAdapter {
        private C2989a() {
        }

        public int getCount() {
            return MyGameFragment.this.f11039a.size();
        }

        public Object getItem(int i) {
            return MyGameFragment.this.f11039a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            final AttentionFile attentionFile = MyGameFragment.this.f11039a.get(i);
            View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.my_game_item, viewGroup, false);
            CheckBox checkBox = (CheckBox) inflate.findViewById(C2425R.C2427id.game_cb);
            TextView textView = (TextView) inflate.findViewById(16908310);
            TextView textView2 = (TextView) inflate.findViewById(16908308);
            TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.game_size);
            TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.download_num);
            ((ImageView) inflate.findViewById(C2425R.C2427id.game_icon)).setImageBitmap(BitmapFactory.decodeByteArray(attentionFile.getCacheIcon(), 0, attentionFile.getCacheIcon().length));
            if (MyGameFragment.this.f11048j) {
                checkBox.setVisibility(0);
                if (MyGameFragment.this.f11049k) {
                    checkBox.setChecked(true);
                    MyGameFragment.this.f11047i.clear();
                    MyGameFragment.this.f11047i.addAll(MyGameFragment.this.f11039a);
                } else {
                    checkBox.setChecked(false);
                    MyGameFragment.this.f11047i.clear();
                }
            } else {
                checkBox.setVisibility(8);
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                /* class com.gbcom.gwifi.functions.profile.MyGameFragment.C2989a.C29901 */

                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        MyGameFragment.this.f11047i.add(attentionFile);
                    } else {
                        MyGameFragment.this.f11047i.remove(attentionFile);
                    }
                }
            });
            textView.setText(attentionFile.getTitle());
            textView2.setText(C3467s.m14506a(C3467s.m14509b(attentionFile.getTags()), "  "));
            textView3.setText(C3467s.m14501a(attentionFile.getFileTotalSize().longValue()));
            textView4.setText("已下载" + C3467s.m14510c((long) attentionFile.getDownloadCount().intValue()));
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C2425R.C2427id.my_game_rl);
            relativeLayout.setTag(Integer.valueOf(i));
            relativeLayout.setOnClickListener(MyGameFragment.this.f11040b);
            return inflate;
        }
    }

    /* renamed from: a */
    public void mo26003a() {
        this.f11048j = !this.f11048j;
        if (this.f11048j) {
            this.f11043e.setVisibility(0);
        } else {
            this.f11049k = false;
            this.f11044f.setText("全选");
            this.f11043e.setVisibility(8);
        }
        this.f11046h.notifyDataSetChanged();
    }

    /* renamed from: b */
    public void mo26004b() {
        this.f11048j = false;
        this.f11043e.setVisibility(8);
        this.f11046h.notifyDataSetChanged();
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("我的游戏界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        GiwifiMobclickAgent.onPageStart("我的游戏界面");
    }
}
