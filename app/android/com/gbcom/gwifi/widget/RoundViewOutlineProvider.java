package com.gbcom.gwifi.widget;

import android.graphics.Outline;
import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;

@RequiresApi(api = 21)
/* renamed from: com.gbcom.gwifi.widget.a */
public class RoundViewOutlineProvider extends ViewOutlineProvider {

    /* renamed from: a */
    private float f13865a;

    public RoundViewOutlineProvider(float f) {
        this.f13865a = f;
    }

    public void getOutline(View view, Outline outline) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        outline.setRoundRect(new Rect(0, 0, rect.right - rect.left, rect.bottom - rect.top), this.f13865a);
    }
}
