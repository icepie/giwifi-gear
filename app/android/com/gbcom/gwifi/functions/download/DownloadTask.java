package com.gbcom.gwifi.functions.download;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.AsyncTask;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.functions.p241b.FileAlreadyExistException;
import com.gbcom.gwifi.functions.p241b.NoMemoryException;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.NetworkUtils;
import com.gbcom.gwifi.util.StorageUtils;
import com.umeng.commonsdk.proguard.UMSysLocationCache;
import java.net.MalformedURLException;
import java.p456io.BufferedInputStream;
import java.p456io.File;
import java.p456io.FileNotFoundException;
import java.p456io.IOException;
import java.p456io.InputStream;
import java.p456io.RandomAccessFile;
import java.p456io.Serializable;
import java.util.UUID;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectTimeoutException;

/* renamed from: com.gbcom.gwifi.functions.download.b */
public class DownloadTask extends AsyncTask<Void, Integer, Long> implements Serializable {

    /* renamed from: a */
    public static final int f9228a = 30000;

    /* renamed from: b */
    private static final int f9229b = 8192;

    /* renamed from: c */
    private static final String f9230c = "DownloadTask";

    /* renamed from: d */
    private static final boolean f9231d = true;

    /* renamed from: e */
    private static final String f9232e = ".download";

    /* renamed from: f */
    private File f9233f;

    /* renamed from: g */
    private File f9234g;

    /* renamed from: h */
    private String f9235h;

    /* renamed from: i */
    private RandomAccessFile f9236i;

    /* renamed from: j */
    private DownloadTaskListener f9237j;

    /* renamed from: k */
    private Context f9238k;

    /* renamed from: l */
    private long f9239l;

    /* renamed from: m */
    private long f9240m;

    /* renamed from: n */
    private long f9241n;

    /* renamed from: o */
    private long f9242o;

    /* renamed from: p */
    private long f9243p;

    /* renamed from: q */
    private long f9244q;

    /* renamed from: r */
    private long f9245r;

    /* renamed from: s */
    private Throwable f9246s;

    /* renamed from: t */
    private boolean f9247t;

    /* renamed from: u */
    private long f9248u;

    /* renamed from: v */
    private HttpResponse f9249v;

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.download.b$a */
    /* compiled from: DownloadTask */
    public final class C2610a extends RandomAccessFile implements Serializable {

        /* renamed from: b */
        private int f9251b = 0;

        /* renamed from: c */
        private long f9252c = 0;

        public C2610a(File file, String str) throws FileNotFoundException {
            super(file, str);
        }

        @Override // java.p456io.RandomAccessFile, java.p456io.DataOutput
        public void write(byte[] bArr, int i, int i2) throws IOException {
            super.write(bArr, i, i2);
            this.f9251b += i2;
            if (System.currentTimeMillis() - this.f9252c > 500) {
                this.f9252c = System.currentTimeMillis();
                DownloadTask.this.publishProgress(new Integer[]{Integer.valueOf(this.f9251b)});
            }
        }
    }

    public DownloadTask(Context context, String str, String str2) throws MalformedURLException {
        this(context, str, str2, (DownloadTaskListener) null);
    }

    public DownloadTask(Context context, String str, String str2, DownloadTaskListener cVar) throws MalformedURLException {
        this.f9239l = 0;
        this.f9241n = 0;
        this.f9246s = null;
        this.f9247t = false;
        this.f9248u = 0;
        this.f9235h = str;
        UUID randomUUID = UUID.randomUUID();
        this.f9237j = cVar;
        this.f9233f = new File(str2, randomUUID.toString());
        this.f9234g = new File(str2, this.f9233f.getName() + f9232e);
        this.f9238k = context;
    }

    public DownloadTask(Context context, String str, String str2, DownloadTaskListener cVar, String str3) throws MalformedURLException {
        this.f9239l = 0;
        this.f9241n = 0;
        this.f9246s = null;
        this.f9247t = false;
        this.f9248u = 0;
        this.f9235h = str;
        UUID.randomUUID();
        this.f9237j = cVar;
        this.f9233f = new File(str2, str3 + ".apk");
        this.f9234g = new File(str2, this.f9233f.getName() + f9232e);
        this.f9238k = context;
    }

    public DownloadTask(Context context, IDownloadFile dVar, String str, DownloadTaskListener cVar) throws MalformedURLException {
        this.f9239l = 0;
        this.f9241n = 0;
        this.f9246s = null;
        this.f9247t = false;
        this.f9248u = 0;
        this.f9235h = dVar.getUrl();
        if (dVar.getLocalFile() != null) {
            this.f9233f = new File(dVar.getLocalFile());
        } else {
            GBActivity.showMessageToast("本地下载文件路径为空!");
        }
        this.f9237j = cVar;
        if (dVar.getTempFile() != null) {
            this.f9234g = new File(dVar.getTempFile());
        } else {
            GBActivity.showMessageToast("本地临时文件路径为空!");
        }
        this.f9238k = context;
    }

    /* renamed from: a */
    public String mo24604a() {
        return this.f9235h;
    }

    /* renamed from: b */
    public boolean mo24610b() {
        return this.f9247t;
    }

    /* renamed from: c */
    public long mo24611c() {
        return this.f9242o;
    }

    /* renamed from: d */
    public long mo24613d() {
        return this.f9239l + this.f9240m;
    }

    /* renamed from: e */
    public long mo24615e() {
        return this.f9241n;
    }

    /* renamed from: f */
    public long mo24616f() {
        return this.f9243p;
    }

    /* renamed from: g */
    public long mo24617g() {
        return this.f9245r;
    }

    /* renamed from: h */
    public DownloadTaskListener mo24618h() {
        return this.f9237j;
    }

    /* renamed from: i */
    public File mo24619i() {
        return this.f9233f;
    }

    /* renamed from: a */
    public void mo24606a(File file) {
        this.f9233f = file;
    }

    /* renamed from: j */
    public File mo24620j() {
        return this.f9234g;
    }

    /* renamed from: b */
    public void mo24609b(File file) {
        this.f9234g = file;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        this.f9244q = System.currentTimeMillis();
        if (this.f9237j != null) {
            this.f9237j.mo24595b(this);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Long doInBackground(Void... voidArr) {
        long j = -1;
        try {
            j = m10953k();
        } catch (NetworkErrorException e) {
            this.f9246s = new Exception("下载失败，请检查您的网络!");
        } catch (IOException e2) {
            Log.m14403e(f9230c, "e::+ " + ((Object) e2));
            this.f9246s = new Exception("下载失败，请检查您的网络或资源!");
        } catch (Exception e3) {
            this.f9246s = e3;
        }
        return Long.valueOf(j);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onProgressUpdate(Integer... numArr) {
        if (numArr.length > 1) {
            this.f9241n = (long) numArr[1].intValue();
            if (this.f9241n == -1 && this.f9237j != null) {
                this.f9237j.mo24594a(this, this.f9246s);
                return;
            }
            return;
        }
        this.f9245r = System.currentTimeMillis() - this.f9244q;
        this.f9244q = System.currentTimeMillis();
        this.f9239l = (long) numArr[0].intValue();
        this.f9242o = ((this.f9239l + this.f9240m) * 100) / this.f9241n;
        Log.m14403e(f9230c, "downloadPercent:" + this.f9242o);
        if (Math.abs(this.f9242o) > 100) {
            this.f9247t = true;
        }
        this.f9243p = ((this.f9239l - this.f9248u) * 1000) / this.f9245r;
        this.f9248u = this.f9239l;
        Log.m14403e(f9230c, "downloadPercent:=" + this.f9242o + " //networkSpeed:=" + this.f9243p);
        if (this.f9237j != null) {
            this.f9237j.mo24593a(this);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Long l) {
        if (l.longValue() == -1 || this.f9247t || this.f9246s != null) {
            if (this.f9246s != null) {
                Log.m14395a(f9230c, "Download failed." + this.f9246s.getMessage());
            }
            if (this.f9237j != null) {
                this.f9237j.mo24594a(this, this.f9246s);
                return;
            }
            return;
        }
        this.f9234g.renameTo(this.f9233f);
        if (this.f9237j != null) {
            this.f9237j.mo24596c(this);
        }
    }

    public void onCancelled() {
        super.onCancelled();
        this.f9247t = true;
    }

    /* renamed from: k */
    private long m10953k() throws NetworkErrorException, IOException, FileAlreadyExistException, NoMemoryException {
        Log.m14395a(f9230c, "totalSize: " + this.f9241n);
        if (!NetworkUtils.m14426a(this.f9238k)) {
            throw new NetworkErrorException("对不起,请检查网络.");
        }
        String str = this.f9235h;
        try {
            mo24605a(this.f9235h);
        } catch (Exception e) {
        }
        this.f9241n = this.f9249v.getEntity().getContentLength();
        if (this.f9234g.exists()) {
            this.f9240m = this.f9234g.length();
            Log.m14395a(f9230c, "File is not complete, download now.");
            Log.m14395a(f9230c, "File length:" + this.f9234g.length() + " totalSize:" + this.f9241n);
        }
        long b = StorageUtils.m14485b();
        Log.m14400c(null, "storage:" + b + " totalSize:" + this.f9241n);
        if (this.f9241n - this.f9234g.length() > b) {
            throw new NoMemoryException("对不起,SD卡空间不足");
        }
        this.f9236i = new C2610a(this.f9234g, "rw");
        publishProgress(0, Integer.valueOf((int) this.f9241n));
        int copy = copy(this.f9249v.getEntity().getContent(), this.f9236i);
        if (this.f9240m + ((long) copy) == this.f9241n || this.f9241n == -1 || this.f9247t) {
            Log.m14395a(f9230c, "Download completed successfully.");
            return (long) copy;
        }
        throw new IOException("文件下载出错，请删除重新下载");
    }

    /* renamed from: a */
    public String mo24605a(String str) throws Exception {
        Header lastHeader = this.f9249v.getLastHeader("location");
        return lastHeader != null ? lastHeader.getValue() : str;
    }

    public int copy(InputStream inputStream, RandomAccessFile randomAccessFile) throws IOException, NetworkErrorException {
        if (inputStream == null || randomAccessFile == null) {
            return -1;
        }
        byte[] bArr = new byte[8192];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        Log.m14395a(f9230c, "length" + randomAccessFile.length());
        try {
            randomAccessFile.seek(randomAccessFile.length());
            int i = 0;
            long j = -1;
            while (!this.f9247t) {
                int read = bufferedInputStream.read(bArr, 0, 8192);
                if (read == -1) {
                    break;
                }
                randomAccessFile.write(bArr, 0, read);
                int i2 = i + read;
                if (!NetworkUtils.m14426a(this.f9238k)) {
                    throw new NetworkErrorException("对不起,请检查网络.");
                } else if (this.f9243p != 0) {
                    j = -1;
                    i = i2;
                } else if (j <= 0) {
                    j = System.currentTimeMillis();
                    i = i2;
                } else if (System.currentTimeMillis() - j > UMSysLocationCache.f22134d) {
                    throw new ConnectTimeoutException("对不起,请检查网络.");
                } else {
                    i = i2;
                }
            }
            return i;
        } finally {
            randomAccessFile.close();
            bufferedInputStream.close();
            inputStream.close();
        }
    }
}
