package com.gbcom.gwifi.functions.download;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.p241b.NoMemoryException;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.p248b.DownloadFileDao;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.StorageUtils;
import java.net.MalformedURLException;
import java.p456io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* renamed from: com.gbcom.gwifi.functions.download.a */
public class DownloadManager extends Thread {

    /* renamed from: a */
    public static final int f9210a = 0;

    /* renamed from: b */
    public static final int f9211b = 1;

    /* renamed from: c */
    public static final int f9212c = 2;

    /* renamed from: d */
    private static final int f9213d = 100;

    /* renamed from: e */
    private static final int f9214e = 3;

    /* renamed from: k */
    private static DownloadManager f9215k;

    /* renamed from: f */
    private Context f9216f;

    /* renamed from: g */
    private C2609c f9217g;

    /* renamed from: h */
    private List<DownloadTask> f9218h;

    /* renamed from: i */
    private List<DownloadTask> f9219i;

    /* renamed from: j */
    private Boolean f9220j = false;

    /* renamed from: l */
    private AbstractC2608b f9221l;

    /* renamed from: m */
    private AbstractC2607a f9222m;

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.download.a$a */
    /* compiled from: DownloadManager */
    public interface AbstractC2607a {
        /* renamed from: a */
        List<IDownloadFile> mo24550a();
    }

    /* renamed from: com.gbcom.gwifi.functions.download.a$b */
    /* compiled from: DownloadManager */
    public interface AbstractC2608b {
        /* renamed from: a */
        void mo24557a(String str);

        /* renamed from: a */
        void mo24558a(String str, long j, long j2);

        /* renamed from: a */
        void mo24559a(String str, long j, long j2, long j3, long j4);

        /* renamed from: a */
        void mo24560a(String str, String str2);

        /* renamed from: a */
        void mo24561a(String str, String str2, String str3);

        void delete(String str);
    }

    /* renamed from: a */
    public static DownloadManager m10891a(Context context) {
        if (f9215k == null) {
            f9215k = new DownloadManager(context);
        }
        return f9215k;
    }

    /* renamed from: a */
    public void mo24570a(AbstractC2608b bVar) {
        this.f9221l = bVar;
    }

    /* renamed from: a */
    public void mo24569a(AbstractC2607a aVar) {
        this.f9222m = aVar;
    }

    public DownloadManager(Context context) {
        this.f9216f = context;
        this.f9217g = new C2609c();
        this.f9218h = new ArrayList();
        this.f9219i = new ArrayList();
    }

    /* renamed from: a */
    public void mo24568a() {
        this.f9220j = true;
        start();
        mo24591i();
    }

    /* renamed from: b */
    public void mo24575b() {
        this.f9220j = false;
        mo24592j();
    }

    /* renamed from: c */
    public boolean mo24580c() {
        return this.f9220j.booleanValue();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        while (this.f9220j.booleanValue()) {
            DownloadTask a = this.f9217g.mo24597a();
            this.f9218h.add(a);
            a.execute(new Void[0]);
        }
    }

    /* renamed from: a */
    public void mo24573a(String str) {
        if (!StorageUtils.m14496e()) {
            Toast.makeText(this.f9216f, "未发现SD卡", 1).show();
        } else if (!StorageUtils.m14484a()) {
            Toast.makeText(this.f9216f, "SD卡不能读写", 1).show();
        } else if (mo24590h() >= 100) {
            Toast.makeText(this.f9216f, "任务列表已满", 1).show();
        } else {
            try {
                m10900g(m10899g(str));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void mo24574a(String str, String str2) {
        if (!StorageUtils.m14496e()) {
            Toast.makeText(this.f9216f, "未发现SD卡", 1).show();
        } else if (!StorageUtils.m14484a()) {
            Toast.makeText(this.f9216f, "SD卡不能读写", 1).show();
        } else if (mo24590h() >= 100) {
            Toast.makeText(this.f9216f, "任务列表已满", 1).show();
        } else {
            try {
                m10900g(m10896b(str, str2));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void mo24572a(IDownloadFile dVar) {
        try {
            DownloadTask b = m10895b(dVar);
            this.f9219i.add(b);
            File j = b.mo24620j();
            long j2 = 0;
            if (j != null && j.exists()) {
                j2 = j.length();
            }
            if (this.f9221l != null) {
                this.f9221l.mo24558a(b.mo24604a(), j2, dVar.getFileTotalSize().longValue());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: g */
    private void m10900g(DownloadTask bVar) {
        if (this.f9221l != null) {
            this.f9221l.mo24561a(bVar.mo24604a(), bVar.mo24620j().getPath(), bVar.mo24619i().getPath());
        }
        m10898f(bVar.mo24604a());
        this.f9217g.mo24599a(bVar);
        if (!isAlive()) {
            mo24568a();
        }
    }

    /* renamed from: f */
    private void m10898f(String str) {
        m10893a(str, false);
    }

    /* renamed from: a */
    private void m10893a(String str, boolean z) {
        Intent intent = new Intent("com.filter.download.receiver");
        intent.putExtra("type", 6);
        intent.putExtra("url", str);
        intent.putExtra(MyIntents.f9259g, z);
        this.f9216f.sendBroadcast(intent);
    }

    /* renamed from: d */
    public void mo24581d() {
        for (int i = 0; i < this.f9218h.size(); i++) {
            DownloadTask bVar = this.f9218h.get(i);
            m10893a(bVar.mo24604a(), bVar.mo24610b());
        }
        for (int i2 = 0; i2 < this.f9217g.mo24600b(); i2++) {
            m10898f(this.f9217g.mo24598a(i2).mo24604a());
        }
        for (int i3 = 0; i3 < this.f9219i.size(); i3++) {
            m10898f(this.f9219i.get(i3).mo24604a());
        }
    }

    /* renamed from: b */
    public boolean mo24577b(String str) {
        for (int i = 0; i < this.f9218h.size(); i++) {
            if (this.f9218h.get(i).mo24604a().equals(str)) {
                return true;
            }
        }
        for (int i2 = 0; i2 < this.f9217g.mo24600b(); i2++) {
            this.f9217g.mo24598a(i2);
        }
        return false;
    }

    /* renamed from: a */
    public DownloadTask mo24567a(int i) {
        if (i >= this.f9218h.size()) {
            return this.f9217g.mo24598a(i - this.f9218h.size());
        }
        return this.f9218h.get(i);
    }

    /* renamed from: e */
    public int mo24584e() {
        return this.f9217g.mo24600b();
    }

    /* renamed from: f */
    public int mo24587f() {
        return this.f9218h.size();
    }

    /* renamed from: g */
    public int mo24589g() {
        return this.f9219i.size();
    }

    /* renamed from: h */
    public int mo24590h() {
        return mo24584e() + mo24587f() + mo24589g();
    }

    /* renamed from: i */
    public void mo24591i() {
        List<IDownloadFile> a;
        this.f9219i.clear();
        if (!(this.f9222m == null || (a = this.f9222m.mo24550a()) == null || a.size() == 0)) {
            for (IDownloadFile dVar : a) {
                if (dVar.getStateId().intValue() == 1 || dVar.getStateId().intValue() == 2) {
                    mo24572a(dVar);
                }
            }
        }
    }

    /* renamed from: c */
    public synchronized void mo24579c(String str) {
        synchronized (this) {
            for (int i = 0; i < this.f9217g.mo24600b(); i++) {
                DownloadTask a = this.f9217g.mo24598a(i);
                if (a != null && a.mo24604a().equals(str)) {
                    this.f9217g.mo24602b(a);
                    this.f9219i.add(a);
                    mo24571a(a);
                }
            }
            for (int i2 = 0; i2 < this.f9218h.size(); i2++) {
                DownloadTask bVar = this.f9218h.get(i2);
                if (bVar != null && bVar.mo24604a().equals(str)) {
                    mo24576b(bVar);
                }
            }
        }
    }

    /* renamed from: j */
    public synchronized void mo24592j() {
        synchronized (this) {
            for (int i = 0; i < this.f9217g.mo24600b(); i++) {
                DownloadTask a = this.f9217g.mo24598a(i);
                this.f9217g.mo24602b(a);
                this.f9219i.add(a);
            }
            for (int i2 = 0; i2 < this.f9218h.size(); i2++) {
                DownloadTask bVar = this.f9218h.get(i2);
                if (bVar != null) {
                    mo24576b(bVar);
                }
            }
        }
    }

    /* renamed from: d */
    public synchronized void mo24583d(String str) {
        int i = 0;
        synchronized (this) {
            Intent intent = new Intent("com.filter.download.receiver");
            intent.putExtra("type", 4);
            intent.putExtra("url", "");
            this.f9216f.sendBroadcast(intent);
            int i2 = 0;
            while (true) {
                if (i2 < this.f9218h.size()) {
                    DownloadTask bVar = this.f9218h.get(i2);
                    if (bVar == null || !bVar.mo24604a().equals(str)) {
                        i2++;
                    } else {
                        File j = bVar.mo24620j();
                        if (j != null && j.exists()) {
                            j.delete();
                        }
                        bVar.onCancelled();
                        mo24588f(bVar);
                        this.f9218h.remove(bVar);
                    }
                } else {
                    int i3 = 0;
                    while (true) {
                        if (i3 < this.f9217g.mo24600b()) {
                            DownloadTask a = this.f9217g.mo24598a(i3);
                            if (a != null && a.mo24604a().equals(str)) {
                                this.f9217g.mo24602b(a);
                                mo24588f(a);
                                break;
                            }
                            i3++;
                        } else {
                            while (true) {
                                if (i >= this.f9219i.size()) {
                                    break;
                                }
                                DownloadTask bVar2 = this.f9219i.get(i);
                                if (bVar2 == null || !bVar2.mo24604a().equals(str)) {
                                    i++;
                                } else {
                                    File j2 = bVar2.mo24620j();
                                    if (j2 != null && j2.exists()) {
                                        j2.delete();
                                    }
                                    bVar2.onCancelled();
                                    this.f9219i.remove(bVar2);
                                    mo24588f(bVar2);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: e */
    public synchronized void mo24586e(String str) {
        for (int i = 0; i < this.f9219i.size(); i++) {
            DownloadTask bVar = this.f9219i.get(i);
            if (bVar != null && bVar.mo24604a().equals(str)) {
                mo24582d(bVar);
            }
        }
    }

    /* renamed from: a */
    public synchronized void mo24571a(DownloadTask bVar) {
        if (bVar != null) {
            if (this.f9219i.contains(bVar)) {
                Intent intent = new Intent("com.filter.download.receiver");
                intent.putExtra("type", 3);
                intent.putExtra(MyIntents.f9260h, C3467s.m14501a(bVar.mo24616f()) + "/S");
                intent.putExtra(MyIntents.f9254b, C3467s.m14501a(bVar.mo24613d()) + " / " + C3467s.m14501a(bVar.mo24615e()));
                intent.putExtra(MyIntents.f9255c, bVar.mo24611c() + "");
                intent.putExtra("url", bVar.mo24604a());
                this.f9216f.sendBroadcast(intent);
            }
        }
    }

    /* renamed from: b */
    public synchronized void mo24576b(DownloadTask bVar) {
        if (bVar != null) {
            bVar.onCancelled();
            if (this.f9218h.contains(bVar)) {
                String a = bVar.mo24604a();
                try {
                    Intent intent = new Intent("com.filter.download.receiver");
                    intent.putExtra("type", 3);
                    intent.putExtra(MyIntents.f9260h, C3467s.m14501a(bVar.mo24616f()) + "/S");
                    intent.putExtra(MyIntents.f9254b, C3467s.m14501a(bVar.mo24613d()) + " / " + C3467s.m14501a(bVar.mo24615e()));
                    intent.putExtra(MyIntents.f9255c, bVar.mo24611c() + "");
                    intent.putExtra("url", bVar.mo24604a());
                    this.f9218h.remove(bVar);
                    File i = bVar.mo24619i();
                    File j = bVar.mo24620j();
                    if (this.f9221l != null) {
                        this.f9221l.mo24558a(bVar.mo24604a(), bVar.mo24613d(), bVar.mo24615e());
                    }
                    DownloadTask g = m10899g(a);
                    g.mo24606a(i);
                    g.mo24609b(j);
                    this.f9219i.add(g);
                    this.f9216f.sendBroadcast(intent);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: c */
    public synchronized void mo24578c(DownloadTask bVar) {
        if (bVar != null) {
            bVar.onCancelled();
            if (this.f9218h.contains(bVar)) {
                String a = bVar.mo24604a();
                try {
                    this.f9218h.remove(bVar);
                    File i = bVar.mo24619i();
                    File j = bVar.mo24620j();
                    if (this.f9221l != null) {
                        this.f9221l.mo24558a(bVar.mo24604a(), bVar.mo24613d(), bVar.mo24615e());
                    }
                    DownloadTask g = m10899g(a);
                    g.mo24606a(i);
                    g.mo24609b(j);
                    this.f9219i.add(g);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: d */
    public synchronized void mo24582d(DownloadTask bVar) {
        if (bVar != null) {
            if (this.f9219i.contains(bVar)) {
                this.f9219i.remove(bVar);
                this.f9217g.mo24599a(bVar);
                if (this.f9221l != null) {
                    this.f9221l.mo24557a(bVar.mo24604a());
                }
                Intent intent = new Intent("com.filter.download.receiver");
                intent.putExtra("type", 5);
                intent.putExtra("url", bVar.mo24604a());
                this.f9216f.sendBroadcast(intent);
            }
        }
    }

    /* renamed from: e */
    public synchronized void mo24585e(DownloadTask bVar) {
        if (this.f9218h.contains(bVar)) {
            this.f9218h.remove(bVar);
            if (this.f9221l != null) {
                this.f9221l.mo24560a(bVar.mo24604a(), bVar.mo24619i().getPath());
            }
            Intent intent = new Intent("com.filter.download.receiver");
            intent.putExtra("type", 1);
            intent.putExtra("url", bVar.mo24604a());
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(GBApplication.instance(), "url", bVar.mo24604a());
            if (downloadFile != null) {
                if (downloadFile.getProductType().equals(Constant.f13160O)) {
                    UmengCount.queryAppDownloadCount(GBApplication.instance(), downloadFile.getName());
                } else if (downloadFile.getProductType().equals(Constant.f13162Q)) {
                    UmengCount.queryGameDownloadCount(GBApplication.instance(), downloadFile.getName());
                }
            }
            this.f9216f.sendBroadcast(intent);
        }
    }

    /* renamed from: f */
    public synchronized void mo24588f(DownloadTask bVar) {
        if (bVar != null) {
            if (this.f9221l != null) {
                this.f9221l.delete(bVar.mo24604a());
            }
            Intent intent = new Intent("com.filter.download.receiver");
            intent.putExtra("type", 10);
            intent.putExtra("url", bVar.mo24604a());
            this.f9216f.sendBroadcast(intent);
        }
    }

    /* renamed from: g */
    private DownloadTask m10899g(String str) throws MalformedURLException {
        return new DownloadTask(this.f9216f, str, StorageUtils.f13479a, new DownloadTaskListener() {
            /* class com.gbcom.gwifi.functions.download.DownloadManager.C26041 */

            @Override // com.gbcom.gwifi.functions.download.DownloadTaskListener
            /* renamed from: a */
            public void mo24593a(DownloadTask bVar) {
                if (DownloadManager.this.f9221l != null) {
                    DownloadManager.this.f9221l.mo24559a(bVar.mo24604a(), bVar.mo24613d(), bVar.mo24615e(), bVar.mo24611c(), bVar.mo24616f());
                }
                Intent intent = new Intent("com.filter.download.receiver");
                intent.putExtra("type", 0);
                intent.putExtra(MyIntents.f9260h, C3467s.m14501a(bVar.mo24616f()) + "/S");
                intent.putExtra(MyIntents.f9254b, C3467s.m14501a(bVar.mo24613d()) + " / " + C3467s.m14501a(bVar.mo24615e()));
                if (Math.abs(bVar.mo24611c()) <= 100) {
                    intent.putExtra(MyIntents.f9255c, bVar.mo24611c() + "");
                    intent.putExtra("url", bVar.mo24604a());
                    DownloadManager.this.f9216f.sendBroadcast(intent);
                }
            }

            @Override // com.gbcom.gwifi.functions.download.DownloadTaskListener
            /* renamed from: b */
            public void mo24595b(DownloadTask bVar) {
            }

            @Override // com.gbcom.gwifi.functions.download.DownloadTaskListener
            /* renamed from: c */
            public void mo24596c(DownloadTask bVar) {
                DownloadManager.this.mo24585e(bVar);
            }

            @Override // com.gbcom.gwifi.functions.download.DownloadTaskListener
            /* renamed from: a */
            public void mo24594a(DownloadTask bVar, Throwable th) {
                if (th == null) {
                    return;
                }
                if (th instanceof NoMemoryException) {
                    DownloadManager.this.mo24576b(bVar);
                } else if (th.getMessage().equals("下载失败，请检查您的网络!") || th.getMessage().equals("对不起,请检查网络.")) {
                    DownloadManager.this.mo24576b(bVar);
                } else {
                    DownloadManager.this.mo24578c(bVar);
                    DownloadManager.this.m10901h(bVar);
                }
            }
        });
    }

    /* renamed from: b */
    private DownloadTask m10896b(String str, String str2) throws MalformedURLException {
        return new DownloadTask(this.f9216f, str, StorageUtils.f13479a, new DownloadTaskListener() {
            /* class com.gbcom.gwifi.functions.download.DownloadManager.C26052 */

            @Override // com.gbcom.gwifi.functions.download.DownloadTaskListener
            /* renamed from: a */
            public void mo24593a(DownloadTask bVar) {
                if (DownloadManager.this.f9221l != null) {
                    DownloadManager.this.f9221l.mo24559a(bVar.mo24604a(), bVar.mo24613d(), bVar.mo24615e(), bVar.mo24611c(), bVar.mo24616f());
                }
                Intent intent = new Intent("com.filter.download.receiver");
                intent.putExtra("type", 0);
                intent.putExtra(MyIntents.f9260h, C3467s.m14501a(bVar.mo24616f()) + "/S");
                intent.putExtra(MyIntents.f9254b, C3467s.m14501a(bVar.mo24613d()) + " / " + C3467s.m14501a(bVar.mo24615e()));
                if (Math.abs(bVar.mo24611c()) <= 100) {
                    intent.putExtra(MyIntents.f9255c, bVar.mo24611c() + "");
                    intent.putExtra("url", bVar.mo24604a());
                    DownloadManager.this.f9216f.sendBroadcast(intent);
                }
            }

            @Override // com.gbcom.gwifi.functions.download.DownloadTaskListener
            /* renamed from: b */
            public void mo24595b(DownloadTask bVar) {
            }

            @Override // com.gbcom.gwifi.functions.download.DownloadTaskListener
            /* renamed from: c */
            public void mo24596c(DownloadTask bVar) {
                DownloadManager.this.mo24585e(bVar);
            }

            @Override // com.gbcom.gwifi.functions.download.DownloadTaskListener
            /* renamed from: a */
            public void mo24594a(DownloadTask bVar, Throwable th) {
                if (th == null) {
                    return;
                }
                if (th instanceof NoMemoryException) {
                    DownloadManager.this.mo24576b(bVar);
                } else if (th.getMessage().equals("下载失败，请检查您的网络!") || th.getMessage().equals("对不起,请检查网络.")) {
                    DownloadManager.this.mo24576b(bVar);
                } else {
                    DownloadManager.this.mo24578c(bVar);
                    DownloadManager.this.m10901h(bVar);
                }
            }
        }, str2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: h */
    private void m10901h(DownloadTask bVar) {
        Intent intent = new Intent("com.action.download.download_service");
        intent.putExtra("type", 5);
        intent.putExtra("url", bVar.mo24604a());
        this.f9216f.startService(intent);
    }

    /* renamed from: b */
    private DownloadTask m10895b(IDownloadFile dVar) throws MalformedURLException {
        return new DownloadTask(this.f9216f, dVar, StorageUtils.f13479a, new DownloadTaskListener() {
            /* class com.gbcom.gwifi.functions.download.DownloadManager.C26063 */

            @Override // com.gbcom.gwifi.functions.download.DownloadTaskListener
            /* renamed from: a */
            public void mo24593a(DownloadTask bVar) {
                if (DownloadManager.this.f9221l != null) {
                    Toast.makeText(DownloadManager.this.f9216f, "task.getDownloadPercent():" + bVar.mo24611c(), 0).show();
                    DownloadManager.this.f9221l.mo24559a(bVar.mo24604a(), bVar.mo24613d(), bVar.mo24615e(), bVar.mo24611c(), bVar.mo24616f());
                }
                Intent intent = new Intent("com.filter.download.receiver");
                intent.putExtra("type", 0);
                intent.putExtra(MyIntents.f9260h, C3467s.m14501a(bVar.mo24616f()) + "/S");
                intent.putExtra(MyIntents.f9254b, C3467s.m14501a(bVar.mo24613d()) + " / " + C3467s.m14501a(bVar.mo24615e()));
                if (Math.abs(bVar.mo24611c()) <= 100) {
                    intent.putExtra(MyIntents.f9255c, bVar.mo24611c() + "");
                    intent.putExtra("url", bVar.mo24604a());
                    DownloadManager.this.f9216f.sendBroadcast(intent);
                }
            }

            @Override // com.gbcom.gwifi.functions.download.DownloadTaskListener
            /* renamed from: b */
            public void mo24595b(DownloadTask bVar) {
            }

            @Override // com.gbcom.gwifi.functions.download.DownloadTaskListener
            /* renamed from: c */
            public void mo24596c(DownloadTask bVar) {
                DownloadManager.this.mo24585e(bVar);
            }

            @Override // com.gbcom.gwifi.functions.download.DownloadTaskListener
            /* renamed from: a */
            public void mo24594a(DownloadTask bVar, Throwable th) {
                if (th != null) {
                    Toast.makeText(DownloadManager.this.f9216f, th.getMessage(), 1).show();
                    DownloadManager.this.mo24576b(bVar);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.download.a$c */
    /* compiled from: DownloadManager */
    public class C2609c {

        /* renamed from: b */
        private Queue<DownloadTask> f9227b = new LinkedList();

        public C2609c() {
        }

        /* renamed from: a */
        public void mo24599a(DownloadTask bVar) {
            synchronized (this.f9227b) {
                this.f9227b.offer(bVar);
            }
        }

        /* renamed from: a */
        public DownloadTask mo24597a() {
            DownloadTask poll;
            while (true) {
                if (DownloadManager.this.f9218h.size() < 3 && (poll = this.f9227b.poll()) != null) {
                    return poll;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /* renamed from: a */
        public DownloadTask mo24598a(int i) {
            DownloadTask bVar;
            if (i >= mo24600b()) {
                return null;
            }
            synchronized (this.f9227b) {
                bVar = (DownloadTask) ((LinkedList) this.f9227b).get(i);
            }
            return bVar;
        }

        /* renamed from: b */
        public int mo24600b() {
            int size;
            synchronized (this.f9227b) {
                size = this.f9227b.size();
            }
            return size;
        }

        /* renamed from: b */
        public boolean mo24601b(int i) {
            return this.f9227b.remove(mo24598a(i));
        }

        /* renamed from: b */
        public boolean mo24602b(DownloadTask bVar) {
            boolean remove;
            synchronized (this.f9227b) {
                remove = this.f9227b.remove(bVar);
            }
            return remove;
        }
    }
}
