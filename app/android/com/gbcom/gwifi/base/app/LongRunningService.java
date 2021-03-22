package com.gbcom.gwifi.base.app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import com.gbcom.gwifi.C2425R;

public class LongRunningService extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Notification notification = new Notification(C2425R.C2426drawable.ic_launcher, "您使用手机时间过长了！！！", System.currentTimeMillis());
        notification.flags = 1;
        startForeground(1, notification);
        Toast.makeText(this, "GiWiFi", 0).show();
        return super.onStartCommand(intent, i, i2);
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
