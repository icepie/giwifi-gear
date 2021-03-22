package com.gbcom.gwifi.functions.redbag.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class TabContainer extends LinearLayout {

    /* renamed from: a */
    private int f11417a;

    /* renamed from: b */
    private int f11418b;

    /* renamed from: c */
    private AbstractC3078a f11419c;

    /* renamed from: com.gbcom.gwifi.functions.redbag.view.TabContainer$a */
    public interface AbstractC3078a {
        void onTabSelected(int i, View view);

        void onTabUnselected(int i, View view);
    }

    public TabContainer(Context context) {
        this(context, null);
    }

    public TabContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TabContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11417a = 0;
        this.f11418b = 0;
        m12616a();
    }

    /* renamed from: a */
    private void m12616a() {
        setOrientation(0);
    }

    /* renamed from: a */
    public void mo26758a(View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
        layoutParams.weight = 1.0f;
        view.setTag(Integer.valueOf(this.f11417a));
        view.setLayoutParams(layoutParams);
        addView(view);
        if (this.f11417a == 0 && this.f11419c != null) {
            this.f11419c.onTabSelected(0, view);
        }
        view.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.view.TabContainer.View$OnClickListenerC30771 */

            public void onClick(View view) {
                if (!(TabContainer.this.f11419c == null || TabContainer.this.f11418b == ((Integer) view.getTag()).intValue())) {
                    TabContainer.this.f11419c.onTabUnselected(TabContainer.this.f11418b, TabContainer.this.getChildAt(TabContainer.this.f11418b));
                    TabContainer.this.f11419c.onTabSelected(((Integer) view.getTag()).intValue(), view);
                }
                TabContainer.this.f11418b = ((Integer) view.getTag()).intValue();
            }
        });
        this.f11417a++;
    }

    /* renamed from: a */
    public void mo26759a(AbstractC3078a aVar) {
        this.f11419c = aVar;
    }

    public void removeAllViews() {
        super.removeAllViews();
        this.f11417a = 0;
        this.f11418b = 0;
    }

    /* renamed from: a */
    public void mo26757a(int i) {
        if (this.f11419c != null) {
            this.f11419c.onTabUnselected(this.f11418b, getChildAt(this.f11418b));
            this.f11419c.onTabSelected(i, getChildAt(i));
        }
        this.f11418b = i;
    }
}
