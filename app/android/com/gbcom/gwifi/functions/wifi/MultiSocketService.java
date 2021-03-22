package com.gbcom.gwifi.functions.wifi;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.p456io.IOException;

public class MultiSocketService extends Service {

    /* renamed from: a */
    protected static final String f12771a = "MultiSocketService";

    /* renamed from: c */
    private static String f12772c = "228.0.0.8";

    /* renamed from: d */
    private static int f12773d = 8888;

    /* renamed from: b */
    private Handler f12774b;

    /* renamed from: e */
    private Runnable f12775e = new Runnable() {
        /* class com.gbcom.gwifi.functions.wifi.MultiSocketService.RunnableC33751 */

        @Override // java.lang.Runnable
        public void run() {
            Log.v(MultiSocketService.f12771a, "run...");
            try {
                MultiSocketService.this.mo27427a();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Log.v(f12771a, "onCreate");
        HandlerThread handlerThread = new HandlerThread("MultiSocketB");
        handlerThread.start();
        this.f12774b = new Handler(handlerThread.getLooper());
        this.f12774b.post(this.f12775e);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo27427a() throws IOException {
        Log.v(f12771a, "receiveMultiBroadcast...");
        MulticastSocket multicastSocket = new MulticastSocket(f12773d);
        multicastSocket.joinGroup(InetAddress.getByName(f12772c));
        Log.v(f12771a, "receiver packet");
        byte[] bArr = new byte[512];
        DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length);
        multicastSocket.receive(datagramPacket);
        Log.v(f12771a, "get data = " + new String(datagramPacket.getData()).trim());
    }
}
