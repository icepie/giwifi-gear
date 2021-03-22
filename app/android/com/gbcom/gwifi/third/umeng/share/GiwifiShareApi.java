package com.gbcom.gwifi.third.umeng.share;

import android.content.Context;
import android.content.Intent;
import com.umeng.socialize.UMShareAPI;

public class GiwifiShareApi {
    private Context context;

    public GiwifiShareApi(Context context2) {
        this.context = context2;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        UMShareAPI.get(this.context).onActivityResult(i, i2, intent);
    }
}
