package com.gbcom.gwifi.functions.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.gbcom.gwifi.base.service.CheckNetworkService;

public class BootBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent intent2 = new Intent(context, CheckNetworkService.class);
            intent2.setFlags(268435456);
            context.startService(intent2);
        }
    }
}
