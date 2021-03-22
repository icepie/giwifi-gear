package com.gbcom.gwifi.functions.loading;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.functions.loading.domain.OrgsInfo;
import com.gbcom.gwifi.util.p257b.StatusBarUtil;
import java.util.List;

public class SchoolSelectActivity extends GBActivity {

    /* renamed from: a */
    private OrgsInfo f9762a;

    /* renamed from: b */
    private ListView f9763b;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StatusBarUtil.m14194c(this);
        setContentView(C2425R.layout.login_activity_school_select);
        this.f9762a = (OrgsInfo) getIntent().getSerializableExtra("orgsInfo");
        m11342a();
    }

    /* renamed from: a */
    private void m11342a() {
        this.f9763b = (ListView) findViewById(C2425R.C2427id.f8357lv);
        if (this.f9762a != null) {
            m11343b();
        }
    }

    /* renamed from: b */
    private void m11343b() {
        this.f9763b.setAdapter((ListAdapter) new C2752a(this.f9762a.getData()));
        this.f9763b.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* class com.gbcom.gwifi.functions.loading.SchoolSelectActivity.C27511 */

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = new Intent();
                intent.putExtra("orgsInfoBean", SchoolSelectActivity.this.f9762a.getData().get(i));
                SchoolSelectActivity.this.setResult(0, intent);
                SchoolSelectActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.loading.SchoolSelectActivity$a */
    public static class C2752a extends BaseAdapter {

        /* renamed from: a */
        private List<OrgsInfo.DataBean> f9765a;

        public C2752a(List<OrgsInfo.DataBean> list) {
            this.f9765a = list;
        }

        public int getCount() {
            if (this.f9765a == null) {
                return 0;
            }
            return this.f9765a.size();
        }

        public Object getItem(int i) {
            return this.f9765a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.login_item_status_list, viewGroup, false);
            ((TextView) inflate.findViewById(C2425R.C2427id.f8363tv)).setText(this.f9765a.get(i).getName());
            return inflate;
        }
    }
}
