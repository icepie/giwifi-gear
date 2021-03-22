package com.gbcom.gwifi.p235c.p236a;

import com.hyphenate.util.ImageUtils;

/* renamed from: com.gbcom.gwifi.c.a.h */
public class LastWindowInfo {

    /* renamed from: a */
    private FloatViewParams f9133a;

    /* renamed from: a */
    public static LastWindowInfo m10759a() {
        return C2594a.f9134a;
    }

    private LastWindowInfo() {
        this.f9133a = null;
    }

    /* renamed from: com.gbcom.gwifi.c.a.h$a */
    /* compiled from: LastWindowInfo */
    private static class C2594a {

        /* renamed from: a */
        private static final LastWindowInfo f9134a = new LastWindowInfo();

        private C2594a() {
        }
    }

    /* renamed from: b */
    public synchronized void mo24496b() {
        m10760f();
    }

    /* renamed from: f */
    private void m10760f() {
        mo24495a(null);
    }

    /* renamed from: c */
    public FloatViewParams mo24497c() {
        return this.f9133a;
    }

    /* renamed from: a */
    public void mo24495a(FloatViewParams dVar) {
        this.f9133a = dVar;
    }

    /* renamed from: d */
    public int mo24498d() {
        return ImageUtils.SCALE_IMAGE_WIDTH;
    }

    /* renamed from: e */
    public int mo24499e() {
        return ImageUtils.SCALE_IMAGE_HEIGHT;
    }
}
