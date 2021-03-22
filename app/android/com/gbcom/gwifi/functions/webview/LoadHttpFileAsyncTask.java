package com.gbcom.gwifi.functions.webview;

import android.os.AsyncTask;
import android.text.TextUtils;
import java.net.HttpURLConnection;
import java.net.URL;
import java.p456io.File;
import java.p456io.FileOutputStream;
import java.p456io.InputStream;

/* renamed from: com.gbcom.gwifi.functions.webview.b */
public class LoadHttpFileAsyncTask extends AsyncTask<String, Integer, File> {

    /* renamed from: a */
    private AbstractC3372a f12761a;

    /* renamed from: b */
    private String f12762b;

    /* renamed from: c */
    private String f12763c;

    /* renamed from: com.gbcom.gwifi.functions.webview.b$a */
    /* compiled from: LoadHttpFileAsyncTask */
    public interface AbstractC3372a {
        /* renamed from: a */
        void mo27404a();

        /* renamed from: a */
        void mo27405a(File file);

        /* renamed from: a */
        void mo27406a(Integer num, Integer num2);
    }

    public LoadHttpFileAsyncTask(String str) {
        this.f12763c = str;
    }

    public LoadHttpFileAsyncTask(String str, String str2, AbstractC3372a aVar) {
        this.f12762b = str;
        this.f12763c = str2;
        this.f12761a = aVar;
    }

    /* renamed from: a */
    public void mo27415a(AbstractC3372a aVar) {
        this.f12761a = aVar;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        super.onCancelled();
        if (this.f12761a != null) {
            this.f12761a.mo27404a();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onCancelled(File file) {
        super.onCancelled(file);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File doInBackground(String... strArr) {
        int i = 0;
        String str = strArr[0];
        if (TextUtils.isEmpty(str) && this.f12761a != null) {
            this.f12761a.mo27404a();
        }
        File file = new File(this.f12762b);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(this.f12762b + File.separator + this.f12763c);
        if (file2.exists()) {
            return file2;
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            int contentLength = httpURLConnection.getContentLength();
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    i += read;
                    fileOutputStream.write(bArr, 0, read);
                    publishProgress(Integer.valueOf(i), Integer.valueOf(contentLength));
                }
                fileOutputStream.close();
                inputStream.close();
                httpURLConnection.disconnect();
            }
            return file2;
        } catch (Exception e) {
            e.printStackTrace();
            if (this.f12761a != null) {
                this.f12761a.mo27404a();
            }
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onProgressUpdate(Integer... numArr) {
        super.onProgressUpdate(numArr);
        if (this.f12761a != null) {
            this.f12761a.mo27406a(numArr[0], numArr[1]);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void onPostExecute(File file) {
        super.onPostExecute(file);
        if (this.f12761a == null) {
            return;
        }
        if (file == null || !file.exists()) {
            this.f12761a.mo27404a();
        } else {
            this.f12761a.mo27405a(file);
        }
    }
}
