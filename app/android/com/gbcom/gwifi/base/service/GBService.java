package com.gbcom.gwifi.base.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.gbcom.gwifi.util.Log;

public class GBService extends Service {

    /* renamed from: a */
    private static final String f8970a = "GBService";

    public void onCreate() {
        super.onCreate();
        ((AlarmManager) getSystemService("alarm")).setInexactRepeating(1, System.currentTimeMillis(), 10000, PendingIntent.getService(this, 0, new Intent(getApplicationContext(), GBService.class), 268435456));
        Log.m14400c(f8970a, "GBService start.....");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Log.m14400c(f8970a, "GBService run.....");
        return 1;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        Log.m14400c(f8970a, "destroy service");
    }
}
