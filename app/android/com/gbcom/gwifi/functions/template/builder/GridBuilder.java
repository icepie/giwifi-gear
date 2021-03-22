package com.gbcom.gwifi.functions.template.builder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.p009v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.edu.DragFunctionActivity;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.cache.CacheEdu;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.p456io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.template.builder.i */
public class GridBuilder implements ViewBuilder {

    /* renamed from: a */
    OkRequestHandler f11790a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.builder.GridBuilder.C31453 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == GridBuilder.this.f11795f) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                        boolean firstEdu = CacheEdu.getInstance().getFirstEdu();
                        JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONArray("edu_list");
                        if (firstEdu) {
                            GridBuilder.this.f11791b.clear();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                if (jSONArray.length() < 7) {
                                    C3146a aVar = new C3146a();
                                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                                    aVar.mo27017b(jSONObject2.getString("title"));
                                    aVar.mo27019c(jSONObject2.getString("img_url"));
                                    aVar.mo27013a(jSONObject2.getString("wap_url"));
                                    aVar.mo27014a(true);
                                    aVar.mo27012a(jSONObject2.getInt("id"));
                                    GridBuilder.this.f11791b.add(aVar);
                                } else if (i <= 6) {
                                    C3146a aVar2 = new C3146a();
                                    JSONObject jSONObject3 = (JSONObject) jSONArray.get(i);
                                    aVar2.mo27017b(jSONObject3.getString("title"));
                                    aVar2.mo27019c(jSONObject3.getString("img_url"));
                                    aVar2.mo27013a(jSONObject3.getString("wap_url"));
                                    aVar2.mo27014a(true);
                                    aVar2.mo27012a(jSONObject3.getInt("id"));
                                    GridBuilder.this.f11791b.add(aVar2);
                                } else {
                                    C3146a aVar3 = new C3146a();
                                    JSONObject jSONObject4 = (JSONObject) jSONArray.get(i);
                                    aVar3.mo27017b(jSONObject4.getString("title"));
                                    aVar3.mo27019c(jSONObject4.getString("img_url"));
                                    aVar3.mo27013a(jSONObject4.getString("wap_url"));
                                    aVar3.mo27014a(false);
                                    aVar3.mo27012a(jSONObject4.getInt("id"));
                                    GridBuilder.this.f11791b.add(aVar3);
                                }
                            }
                            CacheEdu.getInstance().setEduList(GridBuilder.this.f11791b);
                            CacheEdu.getInstance().setFirstEdu(false);
                            C3146a aVar4 = new C3146a();
                            aVar4.mo27014a(true);
                            GridBuilder.this.f11792c.clear();
                            GridBuilder.this.f11792c.addAll(GridBuilder.this.f11791b);
                            GridBuilder.this.f11792c.add(aVar4);
                            if (GridBuilder.this.f11794e != null) {
                                GridBuilder.this.f11794e.setAdapter((ListAdapter) new C3147b(GridBuilder.this.f11792c));
                                return;
                            }
                            return;
                        }
                        List<C3146a> eduList = CacheEdu.getInstance().getEduList();
                        GridBuilder.this.f11793d.clear();
                        for (int i2 = 0; i2 < eduList.size(); i2++) {
                            GridBuilder.this.f11793d.add(Integer.valueOf(eduList.get(i2).mo27021e()));
                        }
                        for (int i3 = 0; i3 < eduList.size(); i3++) {
                            int e = eduList.get(i3).mo27021e();
                            C3146a aVar5 = eduList.get(i3);
                            int i4 = 0;
                            while (true) {
                                if (i4 >= jSONArray.length()) {
                                    break;
                                }
                                JSONObject jSONObject5 = (JSONObject) jSONArray.get(i4);
                                if (e == jSONObject5.getInt("id")) {
                                    aVar5.mo27017b(jSONObject5.getString("title"));
                                    aVar5.mo27019c(jSONObject5.getString("img_url"));
                                    aVar5.mo27013a(jSONObject5.getString("wap_url"));
                                    aVar5.mo27012a(jSONObject5.getInt("id"));
                                    break;
                                }
                                aVar5.mo27017b("");
                                i4++;
                            }
                        }
                        for (int i5 = 0; i5 < jSONArray.length(); i5++) {
                            JSONObject jSONObject6 = (JSONObject) jSONArray.get(i5);
                            if (!GridBuilder.this.f11793d.contains(Integer.valueOf(jSONObject6.getInt("id")))) {
                                C3146a aVar6 = new C3146a();
                                aVar6.mo27017b(jSONObject6.getString("title"));
                                aVar6.mo27019c(jSONObject6.getString("img_url"));
                                aVar6.mo27013a(jSONObject6.getString("wap_url"));
                                aVar6.mo27012a(jSONObject6.getInt("id"));
                                aVar6.mo27014a(false);
                                eduList.add(aVar6);
                            }
                        }
                        GridBuilder.this.f11792c.clear();
                        GridBuilder.this.f11791b.clear();
                        for (int i6 = 0; i6 < eduList.size(); i6++) {
                            if (!TextUtils.equals(eduList.get(i6).mo27018c(), "")) {
                                GridBuilder.this.f11791b.add(eduList.get(i6));
                                GridBuilder.this.f11792c.add(eduList.get(i6));
                            }
                        }
                        C3146a aVar7 = new C3146a();
                        aVar7.mo27014a(true);
                        GridBuilder.this.f11792c.add(aVar7);
                        if (GridBuilder.this.f11794e != null) {
                            GridBuilder.this.f11794e.setAdapter((ListAdapter) new C3147b(GridBuilder.this.f11792c));
                        }
                        CacheEdu.getInstance().setEduList(GridBuilder.this.f11791b);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    private List<C3146a> f11791b = new ArrayList();

    /* renamed from: c */
    private List<C3146a> f11792c = new ArrayList();

    /* renamed from: d */
    private List<Integer> f11793d = new ArrayList();

    /* renamed from: e */
    private GridView f11794e;

    /* renamed from: f */
    private Request f11795f;

    /* renamed from: g */
    private BroadcastReceiver f11796g;

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(final Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        View inflate = LayoutInflater.from(context).inflate(C2425R.layout.layout_gridbuilder, viewGroup, false);
        this.f11796g = new BroadcastReceiver() {
            /* class com.gbcom.gwifi.functions.template.builder.GridBuilder.C31431 */

            public void onReceive(Context context, Intent intent) {
                GridBuilder.this.mo27008b();
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("updataGridBuilder");
        GBApplication.instance().registerReceiver(this.f11796g, intentFilter);
        this.f11794e = (GridView) inflate.findViewById(2131755339);
        this.f11794e.setFocusable(false);
        mo27007a();
        this.f11794e.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* class com.gbcom.gwifi.functions.template.builder.GridBuilder.C31442 */

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == adapterView.getCount() - 1) {
                    context.startActivity(new Intent(context, DragFunctionActivity.class));
                } else if (GridBuilder.this.f11792c != null && GridBuilder.this.f11792c.size() != 0) {
                    GBActivity.openBackWebView(((C3146a) GridBuilder.this.f11792c.get(i)).mo27016b(), ((C3146a) GridBuilder.this.f11792c.get(i)).mo27018c());
                }
            }
        });
        return inflate;
    }

    /* renamed from: a */
    public void mo27007a() {
        this.f11795f = HttpUtil.m14244a(1, this.f11790a, "");
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.template.builder.i$b */
    /* compiled from: GridBuilder */
    public class C3147b extends BaseAdapter {

        /* renamed from: b */
        private final List<C3146a> f11807b = new ArrayList();

        public C3147b(List<C3146a> list) {
            this.f11807b.clear();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).mo27015a()) {
                    this.f11807b.add(list.get(i));
                }
            }
        }

        public int getCount() {
            if (this.f11807b == null || this.f11807b.size() == 1) {
                return 0;
            }
            int i = 0;
            for (int i2 = 0; i2 < this.f11807b.size(); i2++) {
                if (this.f11807b.get(i2).mo27015a()) {
                    i++;
                }
            }
            return i;
        }

        public Object getItem(int i) {
            return this.f11807b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.edumod_item_drag, viewGroup, false);
            ImageView imageView = (ImageView) inflate.findViewById(2131755361);
            TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.tv_item);
            if (i != this.f11807b.size() - 1) {
                ImageTools.m14149a(this.f11807b.get(i).mo27020d(), imageView, GBApplication.instance().options);
                textView.setText(this.f11807b.get(i).mo27018c());
            } else {
                imageView.setImageResource(C2425R.C2426drawable.gi_more);
                textView.setText("更多");
                textView.setTextColor(viewGroup.getContext().getResources().getColor(C2425R.color.red));
            }
            return inflate;
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.template.builder.i$a */
    /* compiled from: GridBuilder */
    public static class C3146a implements Serializable {

        /* renamed from: a */
        private String f11801a;

        /* renamed from: b */
        private String f11802b;

        /* renamed from: c */
        private int f11803c;

        /* renamed from: d */
        private boolean f11804d;

        /* renamed from: e */
        private String f11805e;

        /* renamed from: a */
        public boolean mo27015a() {
            return this.f11804d;
        }

        /* renamed from: a */
        public void mo27014a(boolean z) {
            this.f11804d = z;
        }

        /* renamed from: b */
        public String mo27016b() {
            return this.f11805e;
        }

        /* renamed from: a */
        public void mo27013a(String str) {
            this.f11805e = str;
        }

        /* renamed from: c */
        public String mo27018c() {
            return this.f11802b;
        }

        /* renamed from: b */
        public void mo27017b(String str) {
            this.f11802b = str;
        }

        /* renamed from: d */
        public String mo27020d() {
            return this.f11801a;
        }

        /* renamed from: c */
        public void mo27019c(String str) {
            this.f11801a = str;
        }

        /* renamed from: e */
        public int mo27021e() {
            return this.f11803c;
        }

        /* renamed from: a */
        public void mo27012a(int i) {
            this.f11803c = i;
        }
    }

    /* renamed from: b */
    public void mo27008b() {
        List<C3146a> eduList = CacheEdu.getInstance().getEduList();
        C3146a aVar = new C3146a();
        aVar.mo27014a(true);
        eduList.add(aVar);
        this.f11792c.clear();
        this.f11792c.addAll(eduList);
        if (this.f11794e != null) {
            this.f11794e.setAdapter((ListAdapter) new C3147b(this.f11792c));
        }
    }
}
