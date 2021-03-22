package com.gbcom.gwifi.handler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* renamed from: com.gbcom.gwifi.handler.a */
public class ANRCacheHelper {

    /* renamed from: a */
    private static C3410a f13096a = null;

    /* renamed from: b */
    private static final String f13097b = "android.intent.action.ANR";

    /* renamed from: a */
    public static void m14078a(Context context) {
        f13096a = new C3410a();
        context.registerReceiver(f13096a, new IntentFilter(f13097b));
    }

    /* renamed from: b */
    public static void m14079b(Context context) {
        if (f13096a != null) {
            context.unregisterReceiver(f13096a);
        }
    }

    /* renamed from: com.gbcom.gwifi.handler.a$a */
    /* compiled from: ANRCacheHelper */
    private static class C3410a extends BroadcastReceiver {
        private C3410a() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ANRCacheHelper.f13097b)) {
            }
        }
    }
}
