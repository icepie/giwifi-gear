package com.gbcom.gwifi.third.umeng.push;

import android.content.Context;
import com.gbcom.gwifi.util.Config;
import com.gbcom.gwifi.util.Log;
import com.umeng.commonsdk.UMConfigure;

public class GiwifiPushService {
    private static GiwifiPushAgent giwifiPushAgent;
    private static GiwifiPushService instance;
    private String TAG = "GiwifiPushService";

    public static GiwifiPushService getInstance(Context context) {
        if (instance == null) {
            instance = new GiwifiPushService(context);
        }
        return instance;
    }

    public GiwifiPushService(Context context) {
        String e = Config.m14094a().mo27802e();
        String f = Config.m14094a().mo27803f();
        Log.m14398b(this.TAG, "umengAppKey=" + e);
        Log.m14398b(this.TAG, "umengMessageSecret=" + f);
        UMConfigure.init(context, e, context.getApplicationInfo().packageName.contains("school") ? "GWiFiSchool" : "GWiFi", 1, f);
        giwifiPushAgent = new GiwifiPushAgent(context);
    }

    public GiwifiPushAgent getGiwifiPushAgent() {
        return giwifiPushAgent;
    }
}
