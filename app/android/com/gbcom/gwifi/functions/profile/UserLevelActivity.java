package com.gbcom.gwifi.functions.profile;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.util.cache.CacheAccount;
import java.util.ArrayList;
import java.util.HashMap;

public class UserLevelActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private RelativeLayout f10987a;

    /* renamed from: b */
    private TextView f10988b;

    /* renamed from: c */
    private TextView f10989c;

    /* renamed from: d */
    private ImageView f10990d;

    /* renamed from: e */
    private ImageView f10991e;

    /* renamed from: f */
    private TextView f10992f;

    /* renamed from: g */
    private ListView f10993g;

    /* renamed from: h */
    private C2978a f10994h;

    /* renamed from: i */
    private ArrayList f10995i = new ArrayList();

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "剩余时长界面", C2425R.layout.my_activity_userlevel, true);
        m12351a();
        m12352b();
    }

    /* renamed from: a */
    private void m12351a() {
        this.f10987a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10987a.setOnClickListener(this);
        this.f10989c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10988b = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10990d = (ImageView) findViewById(C2425R.C2427id.download_iv);
        this.f10989c.setText("剩余时长");
        this.f10988b.setVisibility(8);
        this.f10990d.setVisibility(8);
        this.f10991e = (ImageView) findViewById(C2425R.C2427id.level_iv);
        this.f10992f = (TextView) findViewById(C2425R.C2427id.level_tv);
        this.f10993g = (ListView) findViewById(C2425R.C2427id.user_level_gv);
        this.f10994h = new C2978a();
        this.f10993g.setAdapter((ListAdapter) this.f10994h);
    }

    /* renamed from: b */
    private void m12352b() {
        String userLevelName = CacheAccount.getInstance().getUserLevelName();
        if (userLevelName != null) {
            if (userLevelName.contains("vip")) {
                this.f10991e.setImageResource(C2425R.C2426drawable.user_level_big_vip);
            } else {
                this.f10991e.setImageResource(C2425R.C2426drawable.user_level_big_normal);
            }
            this.f10992f.setText(userLevelName);
        }
        HashMap<String, Object> subdata = CacheAccount.getInstance().getSubdata();
        if (subdata != null && subdata.size() > 0) {
            for (String str : subdata.keySet()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                arrayList.add(subdata.get(str));
                this.f10995i.add(arrayList);
            }
        }
        this.f10994h.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.profile.UserLevelActivity$a */
    public class C2978a extends BaseAdapter {
        private C2978a() {
        }

        public int getCount() {
            return UserLevelActivity.this.f10995i.size();
        }

        public Object getItem(int i) {
            return UserLevelActivity.this.f10995i.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ArrayList arrayList = (ArrayList) UserLevelActivity.this.f10995i.get(i);
            View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.my_user_level_item, viewGroup, false);
            ((TextView) inflate.findViewById(2131755041)).setText(arrayList.get(0).toString());
            ((TextView) inflate.findViewById(2131755042)).setText(arrayList.get(1).toString());
            return inflate;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            default:
                return;
        }
    }
}
