package com.gbcom.gwifi.p221a.p227e.p229b;

import android.os.SystemClock;
import com.gbcom.gwifi.p221a.p227e.UdpRequest;
import com.gbcom.gwifi.p221a.p227e.UdpRequestHandler;
import com.gbcom.gwifi.p221a.p227e.UdpResponse;
import com.gbcom.gwifi.p221a.p227e.UdpService;
import com.gbcom.gwifi.p221a.p227e.UdpTask;
import com.gbcom.gwifi.p221a.p227e.p228a.BaseUdpResponse;
import com.gbcom.gwifi.util.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.gbcom.gwifi.a.e.b.a */
public class DefaultUdpService implements UdpService {

    /* renamed from: c */
    private static final String f8604c = "DefaultUdpService";

    /* renamed from: e */
    private static DefaultUdpService f8605e = new DefaultUdpService();

    /* renamed from: a */
    protected final ConcurrentHashMap<UdpRequest, C2453a> f8606a = new ConcurrentHashMap<>();

    /* renamed from: b */
    protected Executor f8607b = new ThreadPoolExecutor(2, 6, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* renamed from: d */
    private final int f8608d = 5000;

    /* renamed from: a */
    public static DefaultUdpService m10361a() {
        return f8605e;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private DatagramSocket m10363c() {
        return mo24174b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public DatagramSocket mo24174b() {
        SocketException e;
        DatagramSocket datagramSocket;
        try {
            datagramSocket = new DatagramSocket();
            try {
                datagramSocket.setSoTimeout(5000);
            } catch (SocketException e2) {
                e = e2;
                e.printStackTrace();
                return datagramSocket;
            }
        } catch (SocketException e3) {
            datagramSocket = null;
            e = e3;
            e.printStackTrace();
            return datagramSocket;
        }
        return datagramSocket;
    }

    @Override // com.gbcom.gwifi.p221a.p227e.UdpService
    /* renamed from: a */
    public void mo24172a(UdpRequest bVar, UdpRequestHandler cVar) {
        C2453a b = mo24173b(bVar, cVar);
        if (this.f8606a.putIfAbsent(bVar, b) != null) {
            Log.m14403e(f8604c, "DefaultUdpService->exec:cannot exec duplicate request (same instance)");
        } else {
            b.mo24187a(this.f8607b, new Void[0]);
        }
    }

    @Override // com.gbcom.gwifi.p221a.p227e.UdpService
    /* renamed from: a */
    public UdpResponse mo24171a(UdpRequest bVar) {
        return (UdpResponse) mo24173b(bVar, null).mo24175b(new Object[0]);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C2453a mo24173b(UdpRequest bVar, UdpRequestHandler cVar) {
        return new C2453a(bVar, cVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: com.gbcom.gwifi.a.e.b.a$a */
    /* compiled from: DefaultUdpService */
    public class C2453a extends UdpTask {

        /* renamed from: a */
        protected long f8609a;

        /* renamed from: c */
        private UdpRequest f8611c;

        /* renamed from: d */
        private UdpRequestHandler f8612d;

        public C2453a(UdpRequest bVar, UdpRequestHandler cVar) {
            this.f8611c = bVar;
            this.f8612d = cVar;
        }

        /* access modifiers changed from: protected */
        @Override // com.gbcom.gwifi.p221a.p227e.UdpTask
        /* renamed from: b */
        public Object mo24175b(Object[] objArr) {
            BaseUdpResponse bVar;
            DatagramSocket c = DefaultUdpService.this.m10363c();
            try {
                c.send(new DatagramPacket(this.f8611c.mo24166a(), this.f8611c.mo24166a().length, InetAddress.getByName(this.f8611c.mo24168c()), this.f8611c.mo24167b()));
                byte[] bArr = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length);
                c.receive(datagramPacket);
                byte[] bArr2 = new byte[datagramPacket.getLength()];
                System.arraycopy((Object) datagramPacket.getData(), 0, (Object) bArr2, 0, bArr2.length);
                bVar = new BaseUdpResponse(bArr2, null);
                mo24190a(bVar);
            } catch (Exception e) {
                bVar = new BaseUdpResponse(null, e);
                mo24190a(bVar);
            } finally {
                c.close();
            }
            return bVar;
        }

        /* access modifiers changed from: protected */
        @Override // com.gbcom.gwifi.p221a.p227e.UdpTask
        /* renamed from: c */
        public void mo24176c(Object obj) {
            UdpResponse dVar = (UdpResponse) obj;
            if (!DefaultUdpService.this.f8606a.remove(this.f8611c, this)) {
                return;
            }
            if (dVar == null || dVar.mo24169a() != null) {
                this.f8612d.mo24185a(this.f8611c, dVar);
            } else {
                this.f8612d.mo24186b(this.f8611c, dVar);
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.gbcom.gwifi.p221a.p227e.UdpTask
        /* renamed from: e */
        public void mo24177e() {
            this.f8612d.mo24183a(this.f8611c);
            this.f8609a = SystemClock.elapsedRealtime();
        }
    }
}
