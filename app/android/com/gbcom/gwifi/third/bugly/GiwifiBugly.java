package com.gbcom.gwifi.third.bugly;

import android.content.Context;
import com.gbcom.gwifi.C2425R;
import com.tencent.bugly.BUGLY;

public class GiwifiBugly {
    private static GiwifiBugly instance;

    public GiwifiBugly(Context context) {
        BUGLY.init(context, context.getResources().getString(C2425R.C2429string.buglyAppId), false);
    }

    public static GiwifiBugly getInstance(Context context) {
        if (instance == null) {
            instance = new GiwifiBugly(context);
        }
        return instance;
    }
}
