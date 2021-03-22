package com.gbcom.gwifi.handler;

import java.lang.Thread;
import java.p456io.PrintWriter;
import java.p456io.StringWriter;

/* renamed from: com.gbcom.gwifi.handler.b */
public class JavaCrashHandler implements Thread.UncaughtExceptionHandler {
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        m14080a(th);
        mo27642a();
    }

    /* renamed from: a */
    public void mo27642a() {
    }

    /* renamed from: a */
    public static String m14080a(Throwable th) {
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            String obj = stringWriter.toString();
            printWriter.close();
            return obj;
        } catch (Exception e) {
            return "";
        }
    }
}
