package com.gbcom.edu.functionModule.main.circle.bean;

import android.icu.impl.PatternTokenizer;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class CircleUserBean implements Parcelable {
    public static final Parcelable.Creator<CircleUserBean> CREATOR = new Parcelable.Creator<CircleUserBean>() {
        /* class com.gbcom.edu.functionModule.main.circle.bean.CircleUserBean.C24221 */

        /* renamed from: a */
        public CircleUserBean createFromParcel(Parcel parcel) {
            return new CircleUserBean(parcel);
        }

        /* renamed from: a */
        public CircleUserBean[] newArray(int i) {
            return new CircleUserBean[i];
        }
    };

    /* renamed from: a */
    private int f8317a;

    /* renamed from: b */
    private int f8318b;

    /* renamed from: c */
    private int f8319c;

    /* renamed from: d */
    private String f8320d;

    /* renamed from: e */
    private int f8321e;

    /* renamed from: f */
    private String f8322f;

    /* renamed from: g */
    private String f8323g;

    /* renamed from: h */
    private int f8324h;

    /* renamed from: i */
    private String f8325i;

    /* renamed from: j */
    private String f8326j;

    /* renamed from: k */
    private String f8327k;

    /* renamed from: l */
    private String f8328l;

    /* renamed from: m */
    private String f8329m;

    /* renamed from: n */
    private int f8330n;

    /* renamed from: o */
    private int f8331o;

    /* renamed from: p */
    private String f8332p;

    /* renamed from: q */
    private String f8333q;

    /* renamed from: r */
    private String f8334r;

    /* renamed from: s */
    private List<UserAlbumBean> f8335s;

    /* renamed from: t */
    private int f8336t;

    public CircleUserBean() {
    }

    protected CircleUserBean(Parcel parcel) {
        this.f8317a = parcel.readInt();
        this.f8318b = parcel.readInt();
        this.f8319c = parcel.readInt();
        this.f8320d = parcel.readString();
        this.f8321e = parcel.readInt();
        this.f8322f = parcel.readString();
        this.f8323g = parcel.readString();
        this.f8324h = parcel.readInt();
        this.f8325i = parcel.readString();
        this.f8326j = parcel.readString();
        this.f8327k = parcel.readString();
        this.f8328l = parcel.readString();
        this.f8329m = parcel.readString();
        this.f8330n = parcel.readInt();
        this.f8331o = parcel.readInt();
        this.f8332p = parcel.readString();
        this.f8333q = parcel.readString();
        this.f8334r = parcel.readString();
        if (this.f8335s == null) {
            this.f8335s = new ArrayList();
        }
        parcel.readTypedList(this.f8335s, UserAlbumBean.CREATOR);
        this.f8336t = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f8317a);
        parcel.writeInt(this.f8318b);
        parcel.writeInt(this.f8319c);
        parcel.writeString(this.f8320d);
        parcel.writeInt(this.f8321e);
        parcel.writeString(this.f8322f);
        parcel.writeString(this.f8323g);
        parcel.writeInt(this.f8324h);
        parcel.writeString(this.f8325i);
        parcel.writeString(this.f8326j);
        parcel.writeString(this.f8327k);
        parcel.writeString(this.f8328l);
        parcel.writeString(this.f8329m);
        parcel.writeInt(this.f8330n);
        parcel.writeInt(this.f8331o);
        parcel.writeString(this.f8332p);
        parcel.writeString(this.f8333q);
        parcel.writeTypedList(this.f8335s);
        parcel.writeString(this.f8334r);
        parcel.writeInt(this.f8336t);
    }

    public int describeContents() {
        return 0;
    }

    /* renamed from: a */
    public int mo23971a() {
        return this.f8317a;
    }

    /* renamed from: a */
    public void mo23972a(int i) {
        this.f8317a = i;
    }

    /* renamed from: b */
    public int mo23975b() {
        return this.f8318b;
    }

    /* renamed from: b */
    public void mo23976b(int i) {
        this.f8318b = i;
    }

    /* renamed from: c */
    public int mo23978c() {
        return this.f8319c;
    }

    /* renamed from: c */
    public void mo23979c(int i) {
        this.f8319c = i;
    }

    /* renamed from: d */
    public String mo23981d() {
        return this.f8320d;
    }

    /* renamed from: a */
    public void mo23973a(String str) {
        this.f8320d = str;
    }

    /* renamed from: e */
    public String mo23985e() {
        return this.f8322f;
    }

    /* renamed from: b */
    public void mo23977b(String str) {
        this.f8322f = str;
    }

    /* renamed from: f */
    public String mo23988f() {
        return this.f8323g;
    }

    /* renamed from: c */
    public void mo23980c(String str) {
        this.f8323g = str;
    }

    /* renamed from: g */
    public int mo23991g() {
        return this.f8324h;
    }

    /* renamed from: d */
    public void mo23982d(int i) {
        this.f8324h = i;
    }

    /* renamed from: h */
    public String mo23994h() {
        return this.f8325i;
    }

    /* renamed from: d */
    public void mo23983d(String str) {
        this.f8325i = str;
    }

    /* renamed from: i */
    public String mo23997i() {
        return this.f8326j;
    }

    /* renamed from: e */
    public void mo23987e(String str) {
        this.f8326j = str;
    }

    /* renamed from: j */
    public String mo23999j() {
        return this.f8334r;
    }

    /* renamed from: f */
    public void mo23990f(String str) {
        this.f8334r = str;
    }

    /* renamed from: k */
    public int mo24001k() {
        return this.f8336t;
    }

    /* renamed from: e */
    public void mo23986e(int i) {
        this.f8336t = i;
    }

    /* renamed from: l */
    public String mo24003l() {
        return this.f8327k;
    }

    /* renamed from: g */
    public void mo23993g(String str) {
        this.f8327k = str;
    }

    /* renamed from: m */
    public String mo24004m() {
        return this.f8328l;
    }

    /* renamed from: h */
    public void mo23996h(String str) {
        this.f8328l = str;
    }

    /* renamed from: n */
    public String mo24005n() {
        return this.f8329m;
    }

    /* renamed from: i */
    public void mo23998i(String str) {
        this.f8329m = str;
    }

    /* renamed from: o */
    public int mo24006o() {
        return this.f8330n;
    }

    /* renamed from: f */
    public void mo23989f(int i) {
        this.f8330n = i;
    }

    /* renamed from: p */
    public int mo24007p() {
        return this.f8331o;
    }

    /* renamed from: g */
    public void mo23992g(int i) {
        this.f8331o = i;
    }

    /* renamed from: q */
    public String mo24008q() {
        return this.f8332p;
    }

    /* renamed from: j */
    public void mo24000j(String str) {
        this.f8332p = str;
    }

    /* renamed from: r */
    public String mo24009r() {
        return this.f8333q;
    }

    /* renamed from: k */
    public void mo24002k(String str) {
        this.f8333q = str;
    }

    /* renamed from: s */
    public int mo24010s() {
        return this.f8321e;
    }

    /* renamed from: h */
    public void mo23995h(int i) {
        this.f8321e = i;
    }

    /* renamed from: t */
    public List<UserAlbumBean> mo24011t() {
        return this.f8335s;
    }

    /* renamed from: a */
    public void mo23974a(List<UserAlbumBean> list) {
        this.f8335s = list;
    }

    @Override // java.lang.Object
    public String toString() {
        return "CircleUserBean{tableId=" + this.f8317a + ", userId=" + this.f8318b + ", userLevel=" + this.f8319c + ", backImg='" + this.f8320d + PatternTokenizer.SINGLE_QUOTE + ", uSex=" + this.f8321e + ", uTrueName='" + this.f8322f + PatternTokenizer.SINGLE_QUOTE + ", uUserName='" + this.f8323g + PatternTokenizer.SINGLE_QUOTE + ", uOrgId=" + this.f8324h + ", uOrgName='" + this.f8325i + PatternTokenizer.SINGLE_QUOTE + ", uHeadImg='" + this.f8326j + PatternTokenizer.SINGLE_QUOTE + ", uBirth='" + this.f8327k + PatternTokenizer.SINGLE_QUOTE + ", uHome='" + this.f8328l + PatternTokenizer.SINGLE_QUOTE + ", uCity='" + this.f8329m + PatternTokenizer.SINGLE_QUOTE + ", uEmotion=" + this.f8330n + ", uFind=" + this.f8331o + ", uSign='" + this.f8332p + PatternTokenizer.SINGLE_QUOTE + ", uTags='" + this.f8333q + PatternTokenizer.SINGLE_QUOTE + ", uCircleList='" + this.f8334r + PatternTokenizer.SINGLE_QUOTE + ", uAlbum=" + ((Object) this.f8335s) + ", isFriend=" + this.f8336t + '}';
    }
}
