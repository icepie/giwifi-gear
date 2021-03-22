package com.gbcom.gwifi.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.text.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.functions.loading.ContactAdapter;
import com.gbcom.gwifi.util.p257b.DensityUtil;

public class Sidebar extends View {

    /* renamed from: a */
    private Paint f13799a;

    /* renamed from: b */
    private TextView f13800b;

    /* renamed from: c */
    private float f13801c;

    /* renamed from: d */
    private ListView f13802d;

    /* renamed from: e */
    private Context f13803e;

    /* renamed from: f */
    private String[] f13804f;

    /* renamed from: a */
    public void mo28421a(ListView listView) {
        this.f13802d = listView;
    }

    public Sidebar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f13803e = context;
        m14832a();
    }

    /* renamed from: a */
    private void m14832a() {
        this.f13804f = new String[]{"搜", "A", "B", "C", "D", DateFormat.ABBR_WEEKDAY, "F", "G", DateFormat.HOUR24, "I", "J", "K", "L", DateFormat.NUM_MONTH, "N", "O", "P", "Q", DateFormat.JP_ERA_2019_NARROW, "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        this.f13799a = new Paint(1);
        this.f13799a.setColor(Color.parseColor("#0080FF"));
        this.f13799a.setTextAlign(Paint.Align.CENTER);
        this.f13799a.setTextSize((float) DensityUtil.m14131b(this.f13803e, 10.0f));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = (float) (getWidth() / 2);
        this.f13801c = (float) (getHeight() / this.f13804f.length);
        int length = this.f13804f.length;
        while (true) {
            length--;
            if (length > -1) {
                canvas.drawText(this.f13804f[length], width, this.f13801c * ((float) (length + 1)), this.f13799a);
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    private int m14831a(float f) {
        int i = (int) (f / this.f13801c);
        if (i < 0) {
            i = 0;
        }
        if (i > this.f13804f.length - 1) {
            return this.f13804f.length - 1;
        }
        return i;
    }

    /* renamed from: a */
    private void m14833a(MotionEvent motionEvent) {
        if (this.f13802d != null) {
            String str = this.f13804f[m14831a(motionEvent.getY())];
            this.f13800b.setText(str);
            ContactAdapter aVar = (ContactAdapter) this.f13802d.getAdapter();
            String[] strArr = (String[]) aVar.getSections();
            try {
                int length = strArr.length;
                while (true) {
                    length--;
                    if (length <= -1) {
                        return;
                    }
                    if (strArr[length].equals(str)) {
                        this.f13802d.setSelection(aVar.getPositionForSection(length));
                        return;
                    }
                }
            } catch (Exception e) {
                Log.e("setHeaderTextAndscroll", e.getMessage());
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.f13800b == null) {
                    this.f13800b = (TextView) ((View) getParent()).findViewById(C2425R.C2427id.floating_header);
                }
                m14833a(motionEvent);
                this.f13800b.setVisibility(0);
                setBackgroundResource(C2425R.C2426drawable.gi_bg_sidebar);
                return true;
            case 1:
                this.f13800b.setVisibility(4);
                setBackgroundColor(0);
                return true;
            case 2:
                m14833a(motionEvent);
                return true;
            case 3:
                this.f13800b.setVisibility(4);
                setBackgroundColor(0);
                return true;
            default:
                return super.onTouchEvent(motionEvent);
        }
    }
}
