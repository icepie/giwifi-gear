package com.gbcom.gwifi.p221a.p227e;

import android.os.Handler;
import android.os.Message;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.util.Log;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.gbcom.gwifi.a.e.g */
public abstract class UdpTask {

    /* renamed from: a */
    private static final int f8631a = 2;

    /* renamed from: b */
    private static final int f8632b = 1;

    /* renamed from: c */
    private static final int f8633c = 3;

    /* renamed from: d */
    private static final HandlerC2459b f8634d = new HandlerC2459b();

    /* renamed from: e */
    private final FutureTask f8635e = new FutureTask(this.f8638h) {
        /* class com.gbcom.gwifi.p221a.p227e.UdpTask.C24572 */

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.FutureTask
        public void done() {
            try {
                UdpTask.this.m10395e(get());
            } catch (Exception e) {
                Log.m14394a(CommonNetImpl.CANCEL);
            }
        }
    };

    /* renamed from: f */
    private volatile EnumC2460c f8636f = EnumC2460c.PENDING;

    /* renamed from: g */
    private final AtomicBoolean f8637g = new AtomicBoolean();

    /* renamed from: h */
    private final AbstractCallableC2461d f8638h = new AbstractCallableC2461d() {
        /* class com.gbcom.gwifi.p221a.p227e.UdpTask.C24561 */

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            UdpTask.this.f8637g.set(true);
            UdpTask.this.mo24175b(this.f8647b);
            return null;
        }
    };

    /* renamed from: com.gbcom.gwifi.a.e.g$c */
    /* compiled from: UdpTask */
    public enum EnumC2460c {
        FINISHED,
        PENDING,
        RUNNING
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract Object mo24175b(Object[] objArr);

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m10394d(Object obj) {
        if (!mo24196c()) {
            mo24176c(obj);
        } else {
            mo24194b(obj);
        }
        this.f8636f = EnumC2460c.FINISHED;
    }

    /* renamed from: a */
    public Object mo24190a(Object obj) {
        f8634d.obtainMessage(1, new C2458a(this, new Object[]{obj})).sendToTarget();
        return obj;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m10395e(Object obj) {
        if (!this.f8637g.get()) {
        }
    }

    /* renamed from: a */
    public final boolean mo24192a(boolean z) {
        return this.f8635e.cancel(z);
    }

    /* renamed from: a */
    public final UdpTask mo24187a(Executor executor, Object[] objArr) {
        if (this.f8636f == EnumC2460c.PENDING) {
            this.f8636f = EnumC2460c.RUNNING;
            f8634d.obtainMessage(3, new C2458a(this, null)).sendToTarget();
            this.f8638h.f8647b = objArr;
            executor.execute(this.f8635e);
            return this;
        } else if (this.f8636f == EnumC2460c.RUNNING) {
            throw new IllegalStateException("Cannot execute task: the task is already running.");
        } else if (this.f8636f != EnumC2460c.FINISHED) {
            return null;
        } else {
            throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
        }
    }

    /* renamed from: a */
    public final Object mo24188a() throws InterruptedException, ExecutionException {
        return this.f8635e.get();
    }

    /* renamed from: a */
    public final Object mo24189a(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.f8635e.get(j, timeUnit);
    }

    /* renamed from: b */
    public final EnumC2460c mo24193b() {
        return this.f8636f;
    }

    /* renamed from: c */
    public final boolean mo24196c() {
        return this.f8635e.isCancelled();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo24197d() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo24194b(Object obj) {
        mo24197d();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo24176c(Object obj) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo24177e() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo24191a(Object[] objArr) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final void mo24195c(Object[] objArr) {
        if (!mo24196c()) {
            f8634d.obtainMessage(2, new C2458a(this, objArr)).sendToTarget();
        }
    }

    /* renamed from: com.gbcom.gwifi.a.e.g$a */
    /* compiled from: UdpTask */
    private static class C2458a {

        /* renamed from: a */
        final Object[] f8641a;

        /* renamed from: b */
        final UdpTask f8642b;

        C2458a(UdpTask gVar, Object[] objArr) {
            this.f8642b = gVar;
            this.f8641a = objArr;
        }
    }

    /* renamed from: com.gbcom.gwifi.a.e.g$d */
    /* compiled from: UdpTask */
    private static abstract class AbstractCallableC2461d implements Callable {

        /* renamed from: b */
        Object[] f8647b;

        private AbstractCallableC2461d() {
        }
    }

    /* renamed from: com.gbcom.gwifi.a.e.g$b */
    /* compiled from: UdpTask */
    private static class HandlerC2459b extends Handler {
        public void handleMessage(Message message) {
            C2458a aVar = (C2458a) message.obj;
            switch (message.what) {
                case 1:
                    aVar.f8642b.m10394d(aVar.f8641a[0]);
                    return;
                case 2:
                    aVar.f8642b.mo24191a(aVar.f8641a);
                    return;
                case 3:
                    aVar.f8642b.mo24177e();
                    return;
                default:
                    return;
            }
        }

        public HandlerC2459b() {
            super(GBApplication.instance().getMainLooper());
        }
    }
}
