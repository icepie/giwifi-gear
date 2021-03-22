package com.gbcom.gwifi.functions.download;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDownloadService extends IInterface {
    /* renamed from: a */
    void mo24552a() throws RemoteException;

    /* renamed from: a */
    void mo24553a(String str) throws RemoteException;

    /* renamed from: b */
    void mo24554b(String str) throws RemoteException;

    /* renamed from: c */
    void mo24555c(String str) throws RemoteException;

    /* renamed from: d */
    void mo24556d(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IDownloadService {

        /* renamed from: a */
        private static final String f9203a = "com.gbcom.gwifi.functions.download.IDownloadService";

        /* renamed from: b */
        static final int f9204b = 1;

        /* renamed from: c */
        static final int f9205c = 2;

        /* renamed from: d */
        static final int f9206d = 3;

        /* renamed from: e */
        static final int f9207e = 4;

        /* renamed from: f */
        static final int f9208f = 5;

        public Stub() {
            attachInterface(this, f9203a);
        }

        /* renamed from: a */
        public static IDownloadService m10883a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(f9203a);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDownloadService)) {
                return new C2603a(iBinder);
            }
            return (IDownloadService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface(f9203a);
                    mo24552a();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(f9203a);
                    mo24553a(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(f9203a);
                    mo24554b(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(f9203a);
                    mo24555c(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(f9203a);
                    mo24556d(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString(f9203a);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* renamed from: com.gbcom.gwifi.functions.download.IDownloadService$Stub$a */
        private static class C2603a implements IDownloadService {

            /* renamed from: a */
            private IBinder f9209a;

            C2603a(IBinder iBinder) {
                this.f9209a = iBinder;
            }

            public IBinder asBinder() {
                return this.f9209a;
            }

            /* renamed from: b */
            public String mo24566b() {
                return Stub.f9203a;
            }

            @Override // com.gbcom.gwifi.functions.download.IDownloadService
            /* renamed from: a */
            public void mo24552a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.f9203a);
                    this.f9209a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.gbcom.gwifi.functions.download.IDownloadService
            /* renamed from: a */
            public void mo24553a(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.f9203a);
                    obtain.writeString(str);
                    this.f9209a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.gbcom.gwifi.functions.download.IDownloadService
            /* renamed from: b */
            public void mo24554b(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.f9203a);
                    obtain.writeString(str);
                    this.f9209a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.gbcom.gwifi.functions.download.IDownloadService
            /* renamed from: c */
            public void mo24555c(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.f9203a);
                    obtain.writeString(str);
                    this.f9209a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.gbcom.gwifi.functions.download.IDownloadService
            /* renamed from: d */
            public void mo24556d(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.f9203a);
                    obtain.writeString(str);
                    this.f9209a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
