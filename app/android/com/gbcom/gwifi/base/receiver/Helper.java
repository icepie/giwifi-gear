package com.gbcom.gwifi.base.receiver;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import java.util.List;

/* renamed from: com.gbcom.gwifi.base.receiver.a */
public class Helper {
    /* renamed from: a */
    public static boolean m10609a(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (runningServices == null || runningServices.isEmpty()) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            if (runningServices.get(i).service.getClassName().equals(str) && TextUtils.equals(runningServices.get(i).service.getPackageName(), context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
