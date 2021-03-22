package com.gbcom.gwifi.p221a.p227e.p229b;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.util.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.p456io.IOException;

/* renamed from: com.gbcom.gwifi.a.e.b.b */
public class MultiCastUdpService {

    /* renamed from: a */
    private static final String f8613a = "MultiCastUdpService";

    /* renamed from: e */
    private static MultiCastUdpService f8614e;

    /* renamed from: b */
    private MulticastSocket f8615b = null;

    /* renamed from: c */
    private DatagramSocket f8616c = null;

    /* renamed from: d */
    private InetAddress f8617d = null;

    /* renamed from: f */
    private Thread f8618f;

    /* renamed from: g */
    private boolean f8619g = false;

    /* renamed from: h */
    private final String f8620h = "224.1.1.100";

    /* renamed from: i */
    private final int f8621i = 60000;

    /* renamed from: j */
    private Context f8622j;

    /* renamed from: k */
    private Handler f8623k;

    /* renamed from: l */
    private final int f8624l = 1;

    /* renamed from: m */
    private boolean f8625m = false;

    /* renamed from: a */
    public static MultiCastUdpService m10371a(Context context) {
        if (f8614e == null) {
            f8614e = new MultiCastUdpService(context);
        }
        return f8614e;
    }

    /* renamed from: a */
    public void mo24178a() {
        if (!this.f8625m) {
            if (this.f8615b != null) {
                try {
                    this.f8615b.leaveGroup(this.f8617d);
                } catch (IOException e) {
                    Log.m14403e(f8613a, e.toString());
                }
            }
            try {
                this.f8615b.joinGroup(this.f8617d);
                this.f8625m = true;
            } catch (Exception e2) {
                this.f8625m = false;
                Log.m14403e(f8613a, e2.toString());
            }
        }
    }

    public MultiCastUdpService(Context context) {
        this.f8622j = context;
        try {
            this.f8616c = new DatagramSocket(60000);
            this.f8617d = InetAddress.getByName("224.1.1.100");
            this.f8615b = new MulticastSocket();
            mo24178a();
            this.f8618f = new C2455a();
            HandlerThread handlerThread = new HandlerThread("sendCast");
            handlerThread.start();
            this.f8623k = new Handler(handlerThread.getLooper()) {
                /* class com.gbcom.gwifi.p221a.p227e.p229b.MultiCastUdpService.HandlerC24541 */

                public void handleMessage(Message message) {
                    MultiCastUdpService.this.mo24178a();
                    if (MultiCastUdpService.this.f8625m) {
                        switch (message.what) {
                            case 1:
                                try {
                                    MultiCastUdpService.this.f8615b.send((DatagramPacket) message.obj);
                                    DatagramPacket datagramPacket = (DatagramPacket) message.obj;
                                    return;
                                } catch (SocketException e) {
                                    MultiCastUdpService.this.f8625m = false;
                                    Log.m14403e(MultiCastUdpService.f8613a, e.toString());
                                    return;
                                } catch (Exception e2) {
                                    Log.m14403e(MultiCastUdpService.f8613a, e2.toString());
                                    return;
                                }
                            default:
                                return;
                        }
                    }
                }
            };
        } catch (Exception e) {
            Log.m14403e(f8613a, e.toString());
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void mo24180b() {
        if (!this.f8619g) {
            this.f8619g = true;
            this.f8618f = new C2455a();
            this.f8618f.start();
        }
    }

    /* renamed from: c */
    public void mo24181c() {
        if (this.f8619g) {
            this.f8619g = false;
            if (this.f8618f != null) {
                this.f8618f.interrupt();
                this.f8618f = null;
            }
        }
    }

    /* renamed from: a */
    public void mo24179a(byte[] bArr) {
        Message obtainMessage = this.f8623k.obtainMessage(1);
        obtainMessage.obj = new DatagramPacket(bArr, bArr.length, this.f8617d, 60000);
        this.f8623k.sendMessage(obtainMessage);
    }

    /* renamed from: com.gbcom.gwifi.a.e.b.b$a */
    /* compiled from: MultiCastUdpService */
    class C2455a extends Thread {
        C2455a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!Thread.interrupted() && MultiCastUdpService.this.f8619g) {
                try {
                    DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
                    MultiCastUdpService.this.f8616c.receive(datagramPacket);
                    if (!datagramPacket.getAddress().getHostAddress().equals(WiFiUtil.m14022a((GBApplication) MultiCastUdpService.this.f8622j.getApplicationContext()).mo27616j())) {
                        System.arraycopy((Object) datagramPacket.getData(), 0, (Object) new byte[datagramPacket.getLength()], 0, datagramPacket.getLength());
                    }
                } catch (Exception e) {
                    MultiCastUdpService.this.f8619g = false;
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
}
