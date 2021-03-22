package com.gbcom.edu.functionModule.main.circle.bean;

import android.icu.impl.PatternTokenizer;
import android.os.Parcel;
import android.os.Parcelable;

public class UserAlbumBean implements Parcelable {
    public static final Parcelable.Creator<UserAlbumBean> CREATOR = new Parcelable.Creator<UserAlbumBean>() {
        /* class com.gbcom.edu.functionModule.main.circle.bean.UserAlbumBean.C24231 */

        /* renamed from: a */
        public UserAlbumBean createFromParcel(Parcel parcel) {
            return new UserAlbumBean(parcel);
        }

        /* renamed from: a */
        public UserAlbumBean[] newArray(int i) {
            return new UserAlbumBean[i];
        }
    };

    /* renamed from: a */
    private int f8337a;

    /* renamed from: b */
    private String f8338b;

    /* renamed from: c */
    private int f8339c;

    public UserAlbumBean() {
    }

    protected UserAlbumBean(Parcel parcel) {
        this.f8337a = parcel.readInt();
        this.f8338b = parcel.readString();
        this.f8339c = parcel.readInt();
    }

    /* renamed from: a */
    public int mo24017a() {
        return this.f8337a;
    }

    /* renamed from: a */
    public void mo24018a(int i) {
        this.f8337a = i;
    }

    /* renamed from: b */
    public String mo24020b() {
        return this.f8338b;
    }

    /* renamed from: a */
    public void mo24019a(String str) {
        this.f8338b = str;
    }

    /* renamed from: c */
    public int mo24022c() {
        return this.f8339c;
    }

    /* renamed from: b */
    public void mo24021b(int i) {
        this.f8339c = i;
    }

    @Override // java.lang.Object
    public String toString() {
        return "UserAlbumBean{userId=" + this.f8337a + ", albumAddress='" + this.f8338b + PatternTokenizer.SINGLE_QUOTE + ", albumSort=" + this.f8339c + '}';
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f8337a);
        parcel.writeString(this.f8338b);
        parcel.writeInt(this.f8339c);
    }
}
