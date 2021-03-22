package com.gbcom.gwifi.p221a.p222a;

import p419io.netty.bootstrap.Bootstrap;
import p419io.netty.channel.Channel;

/* renamed from: com.gbcom.gwifi.a.a.b */
public class ClientStarter {

    /* renamed from: a */
    private static final String f8467a = "testservice.gwifi.com.cn";

    /* renamed from: b */
    private static final String f8468b = "mobileapi.gwifi.com.cn";

    /* renamed from: c */
    private static int f8469c = 9999;

    /* renamed from: d */
    private static String f8470d;

    /* renamed from: g */
    private static Bootstrap f8471g;

    /* renamed from: h */
    private static Channel f8472h;

    /* renamed from: e */
    private MsgDispatcherListener f8473e;

    /* renamed from: f */
    private AbstractC2435a f8474f;

    /* renamed from: com.gbcom.gwifi.a.a.b$a */
    /* compiled from: ClientStarter */
    public interface AbstractC2435a {
        void setOnError(String str);
    }

    public ClientStarter(MsgDispatcherListener cVar) {
        this.f8473e = cVar;
    }

    /* JADX WARN: Type inference failed for: r0v15, types: [io.netty.channel.ChannelFuture] */
    /* JADX WARN: Type inference failed for: r0v16, types: [io.netty.channel.ChannelFuture] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo24044a() {
        /*
        // Method dump skipped, instructions count: 170
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.p221a.p222a.ClientStarter.mo24044a():void");
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [io.netty.channel.ChannelFuture] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m10165b() throws java.lang.InterruptedException {
        /*
            io.netty.bootstrap.Bootstrap r0 = com.gbcom.gwifi.p221a.p222a.ClientStarter.f8471g
            java.net.InetSocketAddress r1 = new java.net.InetSocketAddress
            java.lang.String r2 = com.gbcom.gwifi.p221a.p222a.ClientStarter.f8470d
            int r3 = com.gbcom.gwifi.p221a.p222a.ClientStarter.f8469c
            r1.<init>(r2, r3)
            io.netty.channel.ChannelFuture r0 = r0.connect(r1)
            io.netty.channel.ChannelFuture r0 = r0.sync()
            io.netty.channel.Channel r0 = r0.channel()
            com.gbcom.gwifi.p221a.p222a.ClientStarter.f8472h = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.p221a.p222a.ClientStarter.m10165b():void");
    }

    /* renamed from: a */
    public void mo24045a(AbstractC2435a aVar) {
        this.f8474f = aVar;
    }
}
