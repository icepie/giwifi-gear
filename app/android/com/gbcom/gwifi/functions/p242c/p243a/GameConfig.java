package com.gbcom.gwifi.functions.p242c.p243a;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.gbcom.gwifi.functions.c.a.a */
public class GameConfig {

    /* renamed from: a */
    private String f9174a;

    /* renamed from: b */
    private int f9175b;

    /* renamed from: c */
    private String f9176c;

    /* renamed from: d */
    private String f9177d;

    /* renamed from: e */
    private List<GameFilter> f9178e;

    /* renamed from: f */
    private List<GameIpNetMask> f9179f;

    /* renamed from: a */
    public String mo24518a() {
        return this.f9174a;
    }

    /* renamed from: a */
    public void mo24520a(String str) {
        this.f9174a = str;
    }

    /* renamed from: b */
    public int mo24523b() {
        return this.f9175b;
    }

    /* renamed from: a */
    public void mo24519a(int i) {
        this.f9175b = i;
    }

    /* renamed from: c */
    public String mo24526c() {
        return this.f9176c;
    }

    /* renamed from: b */
    public void mo24524b(String str) {
        this.f9176c = str;
    }

    /* renamed from: d */
    public String mo24528d() {
        return this.f9177d;
    }

    /* renamed from: c */
    public void mo24527c(String str) {
        this.f9177d = str;
    }

    /* renamed from: e */
    public List<GameFilter> mo24529e() {
        return this.f9178e;
    }

    /* renamed from: a */
    public void mo24521a(List<GameFilter> list) {
        this.f9178e = list;
    }

    /* renamed from: f */
    public List<GameIpNetMask> mo24530f() {
        if (this.f9179f == null) {
            this.f9179f = new ArrayList();
        }
        return this.f9179f;
    }

    /* renamed from: b */
    public void mo24525b(List<GameIpNetMask> list) {
        this.f9179f = list;
        if (this.f9179f == null) {
            this.f9179f = new ArrayList();
        }
    }

    /* renamed from: a */
    public boolean mo24522a(GameConfig aVar) {
        ArrayList arrayList;
        if (!(aVar != null && this.f9174a.equals(aVar.mo24518a()) && this.f9175b == aVar.f9175b && this.f9176c.equals(aVar.mo24526c()))) {
            return false;
        }
        List<GameFilter> e = aVar.mo24529e();
        if (this.f9178e.size() != e.size()) {
            return false;
        }
        for (int i = 0; i < this.f9178e.size(); i++) {
            if (!this.f9178e.get(i).mo24531a().equals(e.get(i).mo24531a()) || !this.f9178e.get(i).mo24533b().equals(e.get(i).mo24533b())) {
                return false;
            }
        }
        List<GameIpNetMask> f = aVar.mo24530f();
        if (f == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = f;
        }
        if (this.f9179f == null) {
            this.f9179f = new ArrayList();
        }
        if (this.f9179f.size() != arrayList.size()) {
            return false;
        }
        for (int i2 = 0; i2 < this.f9179f.size(); i2++) {
            if (!this.f9179f.get(i2).mo24535a().equals(arrayList.get(i2).mo24535a()) || !this.f9179f.get(i2).mo24537b().equals(arrayList.get(i2).mo24537b())) {
                return false;
            }
        }
        return true;
    }
}
