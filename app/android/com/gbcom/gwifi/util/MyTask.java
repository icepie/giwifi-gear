package com.gbcom.gwifi.util;

import android.os.Handler;
import android.os.Message;
import com.gbcom.gwifi.base.app.GBApplication;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.gbcom.gwifi.util.l */
public abstract class MyTask {

    /* renamed from: a */
    private static final int f13449a = 2;

    /* renamed from: b */
    private static final int f13450b = 3;

    /* renamed from: c */
    private static final int f13451c = 1;

    /* renamed from: d */
    private static final HandlerC3461b f13452d = new HandlerC3461b();

    /* renamed from: e */
    private final FutureTask f13453e = new FutureTask(this.f13456h) {
        /* class com.gbcom.gwifi.util.MyTask.C34592 */

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.FutureTask
        public void done() {
            try {
                MyTask.this.m14410e(get());
            } catch (Exception e) {
                Log.m14394a(CommonNetImpl.CANCEL);
            }
        }
    };

    /* renamed from: f */
    private volatile EnumC3462c f13454f = EnumC3462c.PENDING;

    /* renamed from: g */
    private final AtomicBoolean f13455g = new AtomicBoolean();

    /* renamed from: h */
    private final AbstractCallableC3463d f13456h = new AbstractCallableC3463d() {
        /* class com.gbcom.gwifi.util.MyTask.C34581 */

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            MyTask.this.f13455g.set(true);
            return MyTask.this.m14409d(MyTask.this.mo24061a(this.f13465b));
        }
    };

    /* renamed from: com.gbcom.gwifi.util.l$c */
    /* compiled from: MyTask */
    public enum EnumC3462c {
        FINISHED,
        PENDING,
        RUNNING
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo24061a(Object[] objArr);

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m14408c(Object obj) {
        if (!mo28161e()) {
            mo24064a(obj);
        } else {
            mo28157b(obj);
        }
        this.f13454f = EnumC3462c.FINISHED;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private Object m14409d(Object obj) {
        f13452d.obtainMessage(1, new C3460a(this, new Object[]{obj})).sendToTarget();
        return obj;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m14410e(Object obj) {
        if (!this.f13455g.get()) {
            m14409d(obj);
        }
    }

    /* renamed from: a */
    public final boolean mo28156a(boolean z) {
        return this.f13453e.cancel(z);
    }

    /* renamed from: a */
    public final MyTask mo28154a(Executor executor, Object[] objArr) {
        if (this.f13454f == EnumC3462c.PENDING) {
            this.f13454f = EnumC3462c.RUNNING;
            f13452d.obtainMessage(3, new C3460a(this, null)).sendToTarget();
            this.f13456h.f13465b = objArr;
            executor.execute(this.f13453e);
            return this;
        } else if (this.f13454f == EnumC3462c.RUNNING) {
            throw new IllegalStateException("Cannot execute task: the task is already running.");
        } else if (this.f13454f != EnumC3462c.FINISHED) {
            return null;
        } else {
            throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
        }
    }

    /* renamed from: c */
    public final Object mo28158c() throws InterruptedException, ExecutionException {
        return this.f13453e.get();
    }

    /* renamed from: a */
    public final Object mo28155a(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.f13453e.get(j, timeUnit);
    }

    /* renamed from: d */
    public final EnumC3462c mo28160d() {
        return this.f13454f;
    }

    /* renamed from: e */
    public final boolean mo28161e() {
        return this.f13453e.isCancelled();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo24062a() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo28157b(Object obj) {
        mo24062a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo24064a(Object obj) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo24065b() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo24066b(Object[] objArr) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final void mo28159c(Object[] objArr) {
        if (!mo28161e()) {
            f13452d.obtainMessage(2, new C3460a(this, objArr)).sendToTarget();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.util.l$a */
    /* compiled from: MyTask */
    public static class C3460a {

        /* renamed from: a */
        final Object[] f13459a;

        /* renamed from: b */
        final MyTask f13460b;

        C3460a(MyTask lVar, Object[] objArr) {
            this.f13460b = lVar;
            this.f13459a = objArr;
        }
    }

    /* renamed from: com.gbcom.gwifi.util.l$d */
    /* compiled from: MyTask */
    private static abstract class AbstractCallableC3463d implements Callable {

        /* renamed from: b */
        Object[] f13465b;

        private AbstractCallableC3463d() {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.util.l$b */
    /* compiled from: MyTask */
    public static class HandlerC3461b extends Handler {
        public void handleMessage(Message message) {
            C3460a aVar = (C3460a) message.obj;
            switch (message.what) {
                case 1:
                    aVar.f13460b.m14408c((MyTask) aVar.f13459a[0]);
                    return;
                case 2:
                    aVar.f13460b.mo24066b(aVar.f13459a);
                    return;
                case 3:
                    aVar.f13460b.mo24065b();
                    return;
                default:
                    return;
            }
        }

        public HandlerC3461b() {
            super(GBApplication.instance().getMainLooper());
        }
    }
}
