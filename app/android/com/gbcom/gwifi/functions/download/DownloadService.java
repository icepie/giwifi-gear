package com.gbcom.gwifi.functions.download;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.download.DownloadManager;
import com.gbcom.gwifi.functions.download.IDownloadService;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.p248b.DownloadFileDao;
import com.gbcom.gwifi.util.Log;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadService extends Service {

    /* renamed from: b */
    private static final String f9192b = "DownloadService";

    /* renamed from: a */
    long f9193a = 0;

    /* renamed from: c */
    private DownloadManager f9194c;

    /* renamed from: d */
    private C2602c f9195d;

    /* renamed from: e */
    private HandlerC2600a f9196e;

    /* renamed from: f */
    private final int f9197f = 1;

    public IBinder onBind(Intent intent) {
        return new BinderC2601b();
    }

    public void onCreate() {
        super.onCreate();
        Log.m14400c(f9192b, "onCreate()");
        this.f9194c = DownloadManager.m10891a(this);
        this.f9195d = new C2602c();
        this.f9194c.mo24570a(this.f9195d);
        this.f9194c.mo24569a(new DownloadManager.AbstractC2607a() {
            /* class com.gbcom.gwifi.functions.download.DownloadService.C25991 */

            @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2607a
            /* renamed from: a */
            public List<IDownloadFile> mo24550a() {
                try {
                    return DownloadFileDao.m12152b().mo24212d(DownloadService.this).where().mo33373ne("stateId", 0).query();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        HandlerThread handlerThread = new HandlerThread("cache_data");
        handlerThread.start();
        this.f9196e = new HandlerC2600a(handlerThread.getLooper());
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        if (intent != null && intent.getAction().equals("com.action.download.download_service")) {
            Log.m14400c(f9192b, "onStart():download_service");
            switch (intent.getIntExtra("type", -1)) {
                case 2:
                    if (!this.f9194c.mo24580c()) {
                        this.f9194c.mo24568a();
                        return;
                    } else {
                        this.f9194c.mo24581d();
                        return;
                    }
                case 3:
                    String stringExtra = intent.getStringExtra("url");
                    if (!TextUtils.isEmpty(stringExtra)) {
                        this.f9194c.mo24579c(stringExtra);
                        return;
                    }
                    return;
                case 4:
                    String stringExtra2 = intent.getStringExtra("url");
                    if (!TextUtils.isEmpty(stringExtra2)) {
                        this.f9194c.mo24583d(stringExtra2);
                        return;
                    }
                    return;
                case 5:
                    String stringExtra3 = intent.getStringExtra("url");
                    if (!TextUtils.isEmpty(stringExtra3)) {
                        this.f9194c.mo24586e(stringExtra3);
                        return;
                    }
                    return;
                case 6:
                    String stringExtra4 = intent.getStringExtra("url");
                    if (!TextUtils.isEmpty(stringExtra4) && !this.f9194c.mo24577b(stringExtra4)) {
                        this.f9194c.mo24573a(stringExtra4);
                        return;
                    }
                    return;
                case 7:
                    this.f9194c.mo24575b();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.download.DownloadService$b */
    private class BinderC2601b extends IDownloadService.Stub {
        private BinderC2601b() {
        }

        @Override // com.gbcom.gwifi.functions.download.IDownloadService
        /* renamed from: a */
        public void mo24552a() throws RemoteException {
            DownloadService.this.f9194c.mo24568a();
        }

        @Override // com.gbcom.gwifi.functions.download.IDownloadService
        /* renamed from: a */
        public void mo24553a(String str) throws RemoteException {
            DownloadService.this.f9194c.mo24573a(str);
        }

        @Override // com.gbcom.gwifi.functions.download.IDownloadService
        /* renamed from: b */
        public void mo24554b(String str) throws RemoteException {
        }

        @Override // com.gbcom.gwifi.functions.download.IDownloadService
        /* renamed from: c */
        public void mo24555c(String str) throws RemoteException {
        }

        @Override // com.gbcom.gwifi.functions.download.IDownloadService
        /* renamed from: d */
        public void mo24556d(String str) throws RemoteException {
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.download.DownloadService$c */
    class C2602c implements DownloadManager.AbstractC2608b {
        C2602c() {
        }

        @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2608b
        /* renamed from: a */
        public void mo24561a(String str, String str2, String str3) {
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(DownloadService.this.getApplicationContext(), "url", str);
            if (downloadFile != null) {
                downloadFile.setTempFile(str2);
                downloadFile.setLocalFile(str3);
                DownloadFileDao.m12152b().mo24205a(DownloadService.this.getApplicationContext(), (Object) downloadFile);
            }
        }

        @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2608b
        /* renamed from: a */
        public void mo24559a(String str, long j, long j2, long j3, long j4) {
            DownloadService.this.f9196e.sendMessage(DownloadService.this.f9196e.obtainMessage(1, new Object[]{str, Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4)}));
        }

        @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2608b
        /* renamed from: a */
        public void mo24558a(String str, long j, long j2) {
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(DownloadService.this.getApplicationContext(), "url", str);
            if (downloadFile != null) {
                if (j > 0 && j2 > 0 && j <= j2) {
                    downloadFile.setDownsize(Long.valueOf(j));
                    downloadFile.setFileTotalSize(Long.valueOf(j2));
                }
                downloadFile.setStateId(1);
                DownloadFileDao.m12152b().mo24205a(DownloadService.this.getApplicationContext(), (Object) downloadFile);
            }
        }

        @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2608b
        /* renamed from: a */
        public void mo24557a(String str) {
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(DownloadService.this.getApplicationContext(), "url", str);
            if (downloadFile != null) {
                downloadFile.setStateId(2);
                DownloadFileDao.m12152b().mo24205a(DownloadService.this.getApplicationContext(), (Object) downloadFile);
            }
        }

        @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2608b
        /* renamed from: a */
        public void mo24560a(String str, String str2) {
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(DownloadService.this.getApplicationContext(), "url", str);
            if (downloadFile != null) {
                downloadFile.setDownsize(downloadFile.getFileTotalSize());
                downloadFile.setStateId(0);
                downloadFile.setFinishTime(Long.valueOf(System.currentTimeMillis()));
                downloadFile.setLocalFile(str2);
                DownloadFileDao.m12152b().mo24205a(DownloadService.this.getApplicationContext(), (Object) downloadFile);
            }
        }

        @Override // com.gbcom.gwifi.functions.download.DownloadManager.AbstractC2608b
        public void delete(String str) {
            DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(DownloadService.this.getApplicationContext(), "url", str);
            if (downloadFile != null) {
                DownloadFileDao.m12152b().delete(DownloadService.this.getApplicationContext(), downloadFile);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.download.DownloadService$a */
    public class HandlerC2600a extends Handler {

        /* renamed from: a */
        Map<String, Long> f9199a = new HashMap();

        public HandlerC2600a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    Object[] objArr = (Object[]) message.obj;
                    Long l = this.f9199a.get(objArr[0]);
                    if (l == null || System.currentTimeMillis() - l.longValue() > 3000) {
                        this.f9199a.put(objArr[0].toString(), Long.valueOf(System.currentTimeMillis()));
                        DownloadFile downloadFile = (DownloadFile) DownloadFileDao.m12152b().mo24207b(GBApplication.instance(), "url", objArr[0].toString());
                        if (downloadFile != null) {
                            downloadFile.setDownsize(Long.valueOf(Long.parseLong(objArr[1].toString())));
                            downloadFile.setFileTotalSize(Long.valueOf(Long.parseLong(objArr[2].toString())));
                            DownloadFileDao.m12152b().mo24205a((Context) GBApplication.instance(), (Object) downloadFile);
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onDestroy() {
        this.f9196e.getLooper().quit();
        super.onDestroy();
    }
}
