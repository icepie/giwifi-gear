package com.gbcom.gwifi.base.p232a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.gbcom.gwifi.functions.notify.domain.Notify;
import com.gbcom.gwifi.functions.product.domain.App;
import com.gbcom.gwifi.functions.product.domain.AttentionFile;
import com.gbcom.gwifi.functions.product.domain.Banner;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.functions.product.domain.Game;
import com.gbcom.gwifi.functions.product.domain.ImageNews;
import com.gbcom.gwifi.functions.product.domain.PlayHistory;
import com.gbcom.gwifi.functions.product.domain.ProductFile;
import com.gbcom.gwifi.functions.product.domain.TextNews;
import com.gbcom.gwifi.functions.product.domain.TvMobile;
import com.gbcom.gwifi.functions.product.domain.Video;
import com.gbcom.gwifi.functions.revenue.domain.NativeInfo;
import com.gbcom.gwifi.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/* renamed from: com.gbcom.gwifi.base.a.a */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    /* renamed from: a */
    private static final String f8650a = "DatabaseHelper";

    /* renamed from: b */
    private static final String f8651b = "gwifi.db";

    /* renamed from: c */
    private static final int f8652c = 12;

    /* renamed from: d */
    private static DatabaseHelper f8653d = null;

    /* renamed from: e */
    private Context f8654e;

    /* renamed from: a */
    public static DatabaseHelper m10426a(Context context) {
        if (f8653d == null) {
            synchronized (DatabaseHelper.class) {
                if (f8653d == null) {
                    f8653d = new DatabaseHelper(context);
                    f8653d.f8654e = context;
                }
            }
        }
        return f8653d;
    }

    public DatabaseHelper(Context context) {
        super(context, f8651b, null, 12);
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource) {
        try {
            Log.m14400c(f8650a, "DB onCreate");
            TableUtils.createTable(connectionSource, PlayHistory.class);
            TableUtils.createTable(connectionSource, Notify.class);
            TableUtils.createTable(connectionSource, TextNews.class);
            TableUtils.createTable(connectionSource, ImageNews.class);
            TableUtils.createTable(connectionSource, Banner.class);
            TableUtils.createTable(connectionSource, Video.class);
            TableUtils.createTable(connectionSource, Game.class);
            TableUtils.createTable(connectionSource, TvMobile.class);
            TableUtils.createTable(connectionSource, DownloadFile.class);
            TableUtils.createTable(connectionSource, AttentionFile.class);
            TableUtils.createTable(connectionSource, App.class);
            TableUtils.createTable(connectionSource, ProductFile.class);
            TableUtils.createTable(connectionSource, NativeInfo.class);
            mo24217a();
        } catch (SQLException e) {
            Log.m14403e(f8650a, "Can't create database:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public void mo24217a() {
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        for (int i3 = i + 1; i3 <= i2; i3++) {
            switch (i3) {
                case 2:
                    m10436h();
                    break;
                case 3:
                    m10428a(connectionSource, sQLiteDatabase);
                    break;
                case 4:
                    m10437i();
                    break;
                case 5:
                    m10438j();
                    break;
                case 6:
                    m10430b();
                    break;
                case 7:
                    m10431c();
                    break;
                case 8:
                    m10432d();
                    break;
                case 9:
                    m10427a(sQLiteDatabase);
                    break;
                case 10:
                    m10433e();
                    break;
                case 11:
                    m10434f();
                    break;
                case 12:
                    m10435g();
                    break;
            }
        }
    }

    /* renamed from: b */
    private void m10430b() {
        try {
            TableUtils.dropTable((ConnectionSource) this.connectionSource, TvMobile.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, TvMobile.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m10431c() {
        try {
            TableUtils.dropTable((ConnectionSource) this.connectionSource, TvMobile.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, TvMobile.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: d */
    private void m10432d() {
        try {
            TableUtils.dropTable((ConnectionSource) this.connectionSource, Notify.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, Notify.class);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, TvMobile.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, TvMobile.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m10427a(SQLiteDatabase sQLiteDatabase) {
        try {
            Log.m14400c(f8650a, "database update start");
            sQLiteDatabase.execSQL("ALTER TABLE 'notify' ADD 'imageUrl' VARCHAR(200) NOT NULL DEFAULT '';");
            Log.m14400c(f8650a, "database update result");
        } catch (android.database.SQLException e) {
            e.printStackTrace();
            Log.m14403e(f8650a, "database update error:" + e.getMessage());
        }
    }

    /* renamed from: e */
    private void m10433e() {
        try {
            TableUtils.dropTable((ConnectionSource) this.connectionSource, App.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, App.class);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, AttentionFile.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, AttentionFile.class);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, DownloadFile.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, DownloadFile.class);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, Game.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, Game.class);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, PlayHistory.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, PlayHistory.class);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, Notify.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, Notify.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: f */
    private void m10434f() {
        try {
            TableUtils.dropTable((ConnectionSource) this.connectionSource, App.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, App.class);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, AttentionFile.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, AttentionFile.class);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, DownloadFile.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, DownloadFile.class);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, Game.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, Game.class);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, PlayHistory.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, PlayHistory.class);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, Notify.class, true);
            TableUtils.createTableIfNotExists(this.connectionSource, Notify.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: g */
    private void m10435g() {
        try {
            TableUtils.createTableIfNotExists(this.connectionSource, NativeInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: h */
    private void m10436h() {
        try {
            TableUtils.createTableIfNotExists(this.connectionSource, Video.class);
            TableUtils.createTableIfNotExists(this.connectionSource, Game.class);
            TableUtils.createTableIfNotExists(this.connectionSource, TvMobile.class);
            TableUtils.createTableIfNotExists(this.connectionSource, DownloadFile.class);
            TableUtils.createTableIfNotExists(this.connectionSource, AttentionFile.class);
            TableUtils.createTableIfNotExists(this.connectionSource, App.class);
            TableUtils.createTableIfNotExists(this.connectionSource, ProductFile.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m10428a(ConnectionSource connectionSource, SQLiteDatabase sQLiteDatabase) {
        try {
            TableUtils.dropTable(connectionSource, App.class, true);
            TableUtils.createTable(connectionSource, App.class);
            TableUtils.dropTable(connectionSource, Game.class, true);
            TableUtils.createTable(connectionSource, Game.class);
            TableUtils.dropTable(connectionSource, DownloadFile.class, true);
            TableUtils.createTable(connectionSource, DownloadFile.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    private void m10437i() {
        try {
            TableUtils.dropTable((ConnectionSource) this.connectionSource, Video.class, true);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, Game.class, true);
            TableUtils.dropTable((ConnectionSource) this.connectionSource, App.class, true);
            TableUtils.createTable(this.connectionSource, Video.class);
            TableUtils.createTable(this.connectionSource, Game.class);
            TableUtils.createTable(this.connectionSource, App.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: j */
    private void m10438j() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0024 A[SYNTHETIC, Splitter:B:15:0x0024] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0032 A[SYNTHETIC, Splitter:B:23:0x0032] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] m10429a(android.content.Context r4, java.lang.String r5) {
        /*
            r3 = this;
            r1 = 0
            android.content.res.AssetManager r0 = r4.getAssets()
            java.io.InputStream r2 = r0.open(r5)     // Catch:{ IOException -> 0x001d, all -> 0x002e }
            int r0 = r2.available()     // Catch:{ IOException -> 0x003d }
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x003d }
            r2.read(r0)     // Catch:{ IOException -> 0x003d }
            if (r2 == 0) goto L_0x0017
            r2.close()     // Catch:{ IOException -> 0x0018 }
        L_0x0017:
            return r0
        L_0x0018:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0017
        L_0x001d:
            r0 = move-exception
            r2 = r1
        L_0x001f:
            r0.printStackTrace()     // Catch:{ all -> 0x003b }
            if (r2 == 0) goto L_0x0027
            r2.close()     // Catch:{ IOException -> 0x0029 }
        L_0x0027:
            r0 = r1
            goto L_0x0017
        L_0x0029:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0027
        L_0x002e:
            r0 = move-exception
            r2 = r1
        L_0x0030:
            if (r2 == 0) goto L_0x0035
            r2.close()     // Catch:{ IOException -> 0x0036 }
        L_0x0035:
            throw r0
        L_0x0036:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0035
        L_0x003b:
            r0 = move-exception
            goto L_0x0030
        L_0x003d:
            r0 = move-exception
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.base.p232a.DatabaseHelper.m10429a(android.content.Context, java.lang.String):byte[]");
    }
}
