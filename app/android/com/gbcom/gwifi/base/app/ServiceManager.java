package com.gbcom.gwifi.base.app;

import android.content.Context;
import com.gbcom.gwifi.functions.test.DeviceTestHttpService;
import com.gbcom.gwifi.p221a.p223b.DefaultHttpService;
import com.gbcom.gwifi.p221a.p223b.HttpService;
import com.gbcom.gwifi.p221a.p223b.HttpsService;
import com.gbcom.gwifi.util.Log;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.gbcom.gwifi.base.app.c */
public class ServiceManager {

    /* renamed from: a */
    private static final String f8847a = "ServiceManager";

    /* renamed from: b */
    private final Context f8848b;

    /* renamed from: c */
    private HttpService f8849c;

    public ServiceManager(Context context) {
        this.f8848b = context;
    }

    /* renamed from: a */
    public HttpService mo24387a(String str) {
        try {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 6, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
            if ("http".equals(str)) {
                if (this.f8849c == null) {
                    this.f8849c = new DefaultHttpService(this.f8848b, threadPoolExecutor);
                }
            } else if ("https".equals(str)) {
                this.f8849c = new HttpsService(this.f8848b, threadPoolExecutor);
            } else {
                Log.m14403e(f8847a, "unknown service \"" + str + "\"");
            }
            return this.f8849c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public HttpService mo24388b(String str) {
        try {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 6, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
            if ("http".equals(str)) {
                if (this.f8849c == null) {
                    this.f8849c = new DeviceTestHttpService(this.f8848b, threadPoolExecutor);
                }
            } else if ("https".equals(str)) {
                this.f8849c = new HttpsService(this.f8848b, threadPoolExecutor);
            } else {
                Log.m14403e(f8847a, "unknown service \"" + str + "\"");
            }
            return this.f8849c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
