package com.gbcom.gwifi.functions.edu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.builder.GridBuilder;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.cache.CacheEdu;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.DragGridLayout;
import java.util.ArrayList;
import java.util.List;

public class DragFunctionActivity extends GBActivity {

    /* renamed from: a */
    private RelativeLayout f9272a;

    /* renamed from: b */
    private TextView f9273b;

    /* renamed from: c */
    private TextView f9274c;

    /* renamed from: d */
    private List<View> f9275d = new ArrayList();

    /* renamed from: e */
    private List<View> f9276e = new ArrayList();

    /* renamed from: f */
    private List<GridBuilder.C3146a> f9277f = new ArrayList();

    /* renamed from: g */
    private List<GridBuilder.C3146a> f9278g = new ArrayList();

    /* renamed from: h */
    private DragGridLayout f9279h;

    /* renamed from: i */
    private List<GridBuilder.C3146a> f9280i;

    /* renamed from: j */
    private List<Integer> f9281j = new ArrayList();

    /* renamed from: k */
    private DragGridLayout f9282k;

    /* renamed from: l */
    private List<GridBuilder.C3146a> f9283l = new ArrayList();

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "EDU模块", C2425R.layout.edumod_activity_drag_function, true);
        m10977a();
        this.f9280i = CacheEdu.getInstance().getEduList();
        m10979b();
    }

    /* renamed from: a */
    private void m10977a() {
        this.f9272a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f9273b = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f9274c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f9273b.setText("全部功能");
        this.f9274c.setText("管理");
    }

    /* renamed from: b */
    private void m10979b() {
        this.f9282k = (DragGridLayout) findViewById(C2425R.C2427id.bottomDragGridLayout);
        this.f9279h = (DragGridLayout) findViewById(C2425R.C2427id.topDragGridLayout);
        this.f9274c.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.edu.DragFunctionActivity.View$OnClickListenerC26121 */

            public void onClick(View view) {
                if (DragFunctionActivity.this.f9274c.getText().toString().trim().equals("管理")) {
                    DragFunctionActivity.this.f9274c.setText("完成");
                    for (int i = 0; i < DragFunctionActivity.this.f9279h.getChildCount(); i++) {
                        ImageView imageView = (ImageView) DragFunctionActivity.this.f9279h.getChildAt(i).findViewById(C2425R.C2427id.iv_close);
                        imageView.setVisibility(0);
                        imageView.setImageResource(C2425R.C2426drawable.reduction);
                    }
                    for (int i2 = 0; i2 < DragFunctionActivity.this.f9282k.getChildCount(); i2++) {
                        View childAt = DragFunctionActivity.this.f9282k.getChildAt(i2);
                        ImageView imageView2 = (ImageView) childAt.findViewById(C2425R.C2427id.iv_close);
                        imageView2.setVisibility(0);
                        if (((GridBuilder.C3146a) childAt.getTag()).mo27015a()) {
                            imageView2.setEnabled(false);
                            imageView2.setImageResource(C2425R.C2426drawable.gou);
                        } else {
                            imageView2.setImageResource(C2425R.C2426drawable.add);
                        }
                    }
                    return;
                }
                DragFunctionActivity.this.f9274c.setText("管理");
                DragFunctionActivity.this.f9283l.clear();
                if (DragFunctionActivity.this.f9279h.getChildCount() == 0) {
                    C3470v.m14563a(DragFunctionActivity.this, "首页最少添加1个应用");
                } else if (DragFunctionActivity.this.f9279h.getChildCount() > 7) {
                    C3470v.m14563a(DragFunctionActivity.this, "首页最多添加7个应用");
                } else {
                    for (int i3 = 0; i3 < DragFunctionActivity.this.f9279h.getChildCount(); i3++) {
                        View childAt2 = DragFunctionActivity.this.f9279h.getChildAt(i3);
                        ((ImageView) childAt2.findViewById(C2425R.C2427id.iv_close)).setVisibility(4);
                        GridBuilder.C3146a aVar = (GridBuilder.C3146a) childAt2.getTag();
                        aVar.mo27014a(true);
                        DragFunctionActivity.this.f9283l.add(aVar);
                    }
                    for (int i4 = 0; i4 < DragFunctionActivity.this.f9282k.getChildCount(); i4++) {
                        View childAt3 = DragFunctionActivity.this.f9282k.getChildAt(i4);
                        ((ImageView) childAt3.findViewById(C2425R.C2427id.iv_close)).setVisibility(4);
                        GridBuilder.C3146a aVar2 = (GridBuilder.C3146a) childAt3.getTag();
                        if (!DragFunctionActivity.this.f9283l.contains(aVar2)) {
                            aVar2.mo27014a(false);
                            DragFunctionActivity.this.f9283l.add(aVar2);
                        }
                    }
                    CacheEdu.getInstance().setEduList(DragFunctionActivity.this.f9283l);
                    Intent intent = new Intent();
                    intent.setAction("updataGridBuilder");
                    DragFunctionActivity.this.sendBroadcast(intent);
                }
            }
        });
        this.f9275d.clear();
        this.f9277f.clear();
        for (int i = 0; i < this.f9280i.size() - 1; i++) {
            GridBuilder.C3146a aVar = this.f9280i.get(i);
            if (aVar.mo27015a()) {
                this.f9277f.add(aVar);
                final View inflate = LayoutInflater.from(this).inflate(C2425R.layout.edumod_item_drag, (ViewGroup) null);
                inflate.setTag(aVar);
                ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.iv_close);
                imageView.setTag(aVar);
                imageView.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.edu.DragFunctionActivity.View$OnClickListenerC26132 */

                    public void onClick(View view) {
                        DragFunctionActivity.this.f9279h.removeView(inflate);
                        DragFunctionActivity.this.f9282k.addView(DragFunctionActivity.this.m10975a((GridBuilder.C3146a) view.getTag(), true));
                    }
                });
                ((TextView) inflate.findViewById(C2425R.C2427id.tv_item)).setText(aVar.mo27018c());
                ImageTools.m14149a(aVar.mo27020d(), (ImageView) inflate.findViewById(2131755361), GBApplication.instance().options);
                this.f9275d.add(inflate);
            }
        }
        this.f9279h.mo28260a(true);
        this.f9279h.mo28259a(this.f9275d);
        this.f9279h.mo28258a(new DragGridLayout.AbstractC3480b() {
            /* class com.gbcom.gwifi.functions.edu.DragFunctionActivity.C26143 */

            @Override // com.gbcom.gwifi.widget.DragGridLayout.AbstractC3480b
            /* renamed from: a */
            public void mo24635a(View view) {
                for (int i = 0; i < DragFunctionActivity.this.f9279h.getChildCount(); i++) {
                    ImageView imageView = (ImageView) DragFunctionActivity.this.f9279h.getChildAt(i).findViewById(C2425R.C2427id.iv_close);
                    imageView.setVisibility(0);
                    imageView.setImageResource(C2425R.C2426drawable.reduction);
                }
                for (int i2 = 0; i2 < DragFunctionActivity.this.f9282k.getChildCount(); i2++) {
                    View childAt = DragFunctionActivity.this.f9282k.getChildAt(i2);
                    ImageView imageView2 = (ImageView) childAt.findViewById(C2425R.C2427id.iv_close);
                    imageView2.setVisibility(0);
                    if (((GridBuilder.C3146a) childAt.getTag()).mo27015a()) {
                        imageView2.setEnabled(false);
                        imageView2.setImageResource(C2425R.C2426drawable.gou);
                    } else {
                        imageView2.setImageResource(C2425R.C2426drawable.add);
                    }
                }
                DragFunctionActivity.this.f9274c.setText("完成");
            }
        });
        m10981c();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private View m10975a(GridBuilder.C3146a aVar, final boolean z) {
        final View inflate = LayoutInflater.from(this).inflate(C2425R.layout.edumod_item_drag, (ViewGroup) null);
        inflate.setTag(aVar);
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.iv_close);
        imageView.setTag(aVar);
        imageView.setVisibility(0);
        if (z) {
            imageView.setImageResource(C2425R.C2426drawable.add);
        } else {
            imageView.setImageResource(C2425R.C2426drawable.reduction);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.edu.DragFunctionActivity.View$OnClickListenerC26154 */

            public void onClick(View view) {
                GridBuilder.C3146a aVar = (GridBuilder.C3146a) view.getTag();
                if (!z) {
                    DragFunctionActivity.this.f9279h.removeView(inflate);
                    DragFunctionActivity.this.f9282k.addView(DragFunctionActivity.this.m10975a(aVar, true));
                    return;
                }
                DragFunctionActivity.this.f9282k.removeView(inflate);
                DragFunctionActivity.this.f9279h.addView(DragFunctionActivity.this.m10975a(aVar, false));
            }
        });
        ((TextView) inflate.findViewById(C2425R.C2427id.tv_item)).setText(aVar.mo27018c());
        ImageTools.m14149a(aVar.mo27020d(), (ImageView) inflate.findViewById(2131755361), GBApplication.instance().options);
        if (z) {
            this.f9282k.mo28256a(inflate);
        } else {
            this.f9279h.mo28256a(inflate);
        }
        return inflate;
    }

    /* renamed from: c */
    private void m10981c() {
        this.f9276e.clear();
        for (int i = 0; i < this.f9280i.size(); i++) {
            GridBuilder.C3146a aVar = this.f9280i.get(i);
            if (!aVar.mo27015a()) {
                this.f9278g.add(aVar);
                final View inflate = LayoutInflater.from(this).inflate(C2425R.layout.edumod_item_drag, (ViewGroup) null);
                inflate.setTag(aVar);
                ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.iv_close);
                imageView.setTag(aVar);
                imageView.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.edu.DragFunctionActivity.View$OnClickListenerC26165 */

                    public void onClick(View view) {
                        DragFunctionActivity.this.f9282k.removeView(inflate);
                        DragFunctionActivity.this.f9279h.addView(DragFunctionActivity.this.m10975a((GridBuilder.C3146a) view.getTag(), false));
                        ((ImageView) view).setImageResource(C2425R.C2426drawable.reduction);
                    }
                });
                ((TextView) inflate.findViewById(C2425R.C2427id.tv_item)).setText(aVar.mo27018c());
                ImageTools.m14149a(aVar.mo27020d(), (ImageView) inflate.findViewById(2131755361), GBApplication.instance().options);
                this.f9276e.add(inflate);
            }
        }
        this.f9282k.mo28260a(false);
        this.f9282k.mo28259a(this.f9276e);
        this.f9282k.mo28258a(new DragGridLayout.AbstractC3480b() {
            /* class com.gbcom.gwifi.functions.edu.DragFunctionActivity.C26176 */

            @Override // com.gbcom.gwifi.widget.DragGridLayout.AbstractC3480b
            /* renamed from: a */
            public void mo24635a(View view) {
                for (int i = 0; i < DragFunctionActivity.this.f9279h.getChildCount(); i++) {
                    ImageView imageView = (ImageView) DragFunctionActivity.this.f9279h.getChildAt(i).findViewById(C2425R.C2427id.iv_close);
                    imageView.setVisibility(0);
                    imageView.setImageResource(C2425R.C2426drawable.reduction);
                }
                for (int i2 = 0; i2 < DragFunctionActivity.this.f9282k.getChildCount(); i2++) {
                    View childAt = DragFunctionActivity.this.f9282k.getChildAt(i2);
                    ImageView imageView2 = (ImageView) childAt.findViewById(C2425R.C2427id.iv_close);
                    imageView2.setVisibility(0);
                    if (((GridBuilder.C3146a) childAt.getTag()).mo27015a()) {
                        imageView2.setEnabled(false);
                        imageView2.setImageResource(C2425R.C2426drawable.gou);
                    } else {
                        imageView2.setImageResource(C2425R.C2426drawable.add);
                    }
                }
                DragFunctionActivity.this.f9274c.setText("完成");
            }
        });
    }
}
