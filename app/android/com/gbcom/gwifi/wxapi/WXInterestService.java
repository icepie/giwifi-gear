package com.gbcom.gwifi.wxapi;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.gbcom.gwifi.base.app.GBWindowManager;
import com.gbcom.gwifi.util.C3467s;

public class WXInterestService extends Service {

    /* renamed from: a */
    private static final int f13866a = 1;

    /* renamed from: b */
    private String f13867b;

    /* renamed from: c */
    private Handler f13868c = new Handler() {
        /* class com.gbcom.gwifi.wxapi.WXInterestService.HandlerC34991 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    if (!C3467s.m14513e(WXInterestService.this.f13867b)) {
                        GBWindowManager.m10494a(WXInterestService.this.getApplicationContext(), WXInterestService.this.f13867b);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (C3467s.m14513e(this.f13867b)) {
            this.f13867b = intent.getStringExtra("wxNo");
            this.f13868c.obtainMessage().what = 1;
            this.f13868c.sendEmptyMessageDelayed(1, 1000);
        }
        return super.onStartCommand(intent, i, i2);
    }
}
