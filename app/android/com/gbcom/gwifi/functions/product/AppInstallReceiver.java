package com.gbcom.gwifi.functions.product;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AppInstallReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        context.getPackageManager();
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            Toast.makeText(context, "安装成功" + intent.getData().getSchemeSpecificPart(), 1).show();
        }
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            Toast.makeText(context, "卸载成功" + intent.getData().getSchemeSpecificPart(), 1).show();
        }
        if (intent.getAction().equals("android.intent.action.PACKAGE_REPLACED")) {
            Toast.makeText(context, "替换成功" + intent.getData().getSchemeSpecificPart(), 1).show();
        }
    }
}
