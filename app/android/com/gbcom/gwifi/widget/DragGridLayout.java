package com.gbcom.gwifi.widget;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import java.util.ArrayList;
import java.util.List;

public class DragGridLayout extends GridLayout {

    /* renamed from: a */
    private int f13599a;

    /* renamed from: b */
    private int f13600b;

    /* renamed from: c */
    private boolean f13601c;

    /* renamed from: d */
    private AbstractC3479a f13602d;

    /* renamed from: e */
    private AbstractC3480b f13603e;

    /* renamed from: f */
    private View.OnDragListener f13604f;

    /* renamed from: g */
    private Rect[] f13605g;

    /* renamed from: h */
    private View f13606h;

    /* renamed from: i */
    private View.OnLongClickListener f13607i;

    /* renamed from: com.gbcom.gwifi.widget.DragGridLayout$a */
    public interface AbstractC3479a {
        /* renamed from: a */
        void mo24638a(View view);
    }

    /* renamed from: com.gbcom.gwifi.widget.DragGridLayout$b */
    public interface AbstractC3480b {
        /* renamed from: a */
        void mo24635a(View view);
    }

    public DragGridLayout(Context context) {
        this(context, null);
    }

    public DragGridLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DragGridLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f13604f = new View.OnDragListener() {
            /* class com.gbcom.gwifi.widget.DragGridLayout.View$OnDragListenerC34772 */

            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {
                    case 1:
                        DragGridLayout.this.m14646c();
                        break;
                    case 2:
                        int a = DragGridLayout.this.m14639a((DragGridLayout) dragEvent);
                        if (!(a <= -1 || DragGridLayout.this.f13606h == null || DragGridLayout.this.f13606h == DragGridLayout.this.getChildAt(a))) {
                            DragGridLayout.this.removeView(DragGridLayout.this.f13606h);
                            DragGridLayout.this.addView(DragGridLayout.this.f13606h, a);
                            break;
                        }
                    case 4:
                        if (DragGridLayout.this.f13606h != null) {
                            DragGridLayout.this.f13606h.setEnabled(true);
                            break;
                        }
                        break;
                }
                return true;
            }
        };
        this.f13607i = new View.OnLongClickListener() {
            /* class com.gbcom.gwifi.widget.DragGridLayout.View$OnLongClickListenerC34783 */

            public boolean onLongClick(View view) {
                view.startDrag(null, new View.DragShadowBuilder(view), null, 0);
                view.setEnabled(false);
                DragGridLayout.this.f13606h = view;
                if (DragGridLayout.this.f13603e != null) {
                    DragGridLayout.this.f13603e.mo24635a(view);
                }
                return false;
            }
        };
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C2425R.styleable.f8376J);
        this.f13599a = obtainStyledAttributes.getInteger(0, 2);
        this.f13600b = obtainStyledAttributes.getInteger(1, 4);
        m14643b();
    }

    /* renamed from: b */
    private void m14643b() {
        setColumnCount(this.f13600b);
        setLayoutTransition(new LayoutTransition());
    }

    /* renamed from: a */
    public void mo28259a(List<View> list) {
        for (View view : list) {
            mo28256a(view);
            addView(view);
        }
    }

    /* renamed from: a */
    public void mo28256a(View view) {
        int i = this.f13599a;
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = ((getResources().getDisplayMetrics().widthPixels - (DensityUtil.m14128a(getContext(), 10.0f) * 2)) - (DensityUtil.m14128a(getContext(), (float) i) * 2)) / this.f13600b;
        layoutParams.height = (int) (((double) (((getResources().getDisplayMetrics().widthPixels - (DensityUtil.m14128a(getContext(), 10.0f) * 2)) - DensityUtil.m14128a(getContext(), (float) i)) / this.f13600b)) * 0.8d);
        if (i == 0 && Build.VERSION.SDK_INT >= 21) {
            layoutParams.columnSpec = GridLayout.spec(Integer.MIN_VALUE, 1, 1.0f);
            layoutParams.rowSpec = GridLayout.spec(Integer.MIN_VALUE, 1, 1.0f);
        }
        layoutParams.setMargins(i, i, i, i);
        view.setLayoutParams(layoutParams);
        if (this.f13601c) {
            view.setOnLongClickListener(this.f13607i);
        } else {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                /* class com.gbcom.gwifi.widget.DragGridLayout.View$OnLongClickListenerC34761 */

                public boolean onLongClick(View view) {
                    if (DragGridLayout.this.f13603e == null) {
                        return false;
                    }
                    DragGridLayout.this.f13603e.mo24635a(view);
                    return false;
                }
            });
        }
    }

    /* renamed from: a */
    public void mo28260a(boolean z) {
        this.f13601c = z;
        if (this.f13601c) {
            setOnDragListener(this.f13604f);
        } else {
            setOnDragListener(null);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m14646c() {
        this.f13605g = new Rect[getChildCount()];
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            this.f13605g[i] = new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private int m14639a(DragEvent dragEvent) {
        for (int i = 0; i < this.f13605g.length; i++) {
            if (this.f13605g[i].contains((int) dragEvent.getX(), (int) dragEvent.getY())) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public List<String> mo28255a() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            arrayList.add(((TextView) getChildAt(i)).getText().toString());
        }
        return arrayList;
    }

    /* renamed from: a */
    public void mo28257a(AbstractC3479a aVar) {
        this.f13602d = aVar;
    }

    /* renamed from: a */
    public void mo28258a(AbstractC3480b bVar) {
        this.f13603e = bVar;
    }
}
