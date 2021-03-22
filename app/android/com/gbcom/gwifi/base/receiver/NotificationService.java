package com.gbcom.gwifi.base.receiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NotificationService extends Service {
    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
