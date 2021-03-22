package com.gbcom.gwifi.functions.product.p248b;

import android.content.Context;
import android.database.Cursor;
import com.gbcom.gwifi.functions.product.domain.ProductFile;
import com.gbcom.gwifi.p230b.p231a.BaseDao;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.gbcom.gwifi.functions.product.b.c */
public class ProductFileDao extends BaseDao<ProductFile, Long> {

    /* renamed from: b */
    private static ProductFileDao f10749b;

    /* renamed from: c */
    private final String f10750c = ProductFileDao.class.getName();

    /* renamed from: b */
    public static synchronized ProductFileDao m12169b() {
        ProductFileDao cVar;
        synchronized (ProductFileDao.class) {
            if (f10749b == null) {
                f10749b = new ProductFileDao();
            }
            cVar = f10749b;
        }
        return cVar;
    }

    /* renamed from: a */
    public Dao.CreateOrUpdateStatus mo24215e(Context context, ProductFile productFile) {
        try {
            return mo24200a(context).createOrUpdate(productFile);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.gbcom.gwifi.p230b.p231a.BaseDao
    /* renamed from: a */
    public Class<ProductFile> mo24201a() {
        return ProductFile.class;
    }

    /* renamed from: a */
    public String[] mo25557a(Context context, int i) {
        Cursor a = m12169b().mo24199a(context, "select url from ProductFile where productId=? ", new String[]{i + ""});
        ArrayList arrayList = new ArrayList();
        while (a.moveToNext()) {
            arrayList.add(a.getString(0));
        }
        if (!a.isClosed()) {
            a.close();
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* renamed from: b */
    public String[] mo25558b(Context context, int i) {
        Cursor a = m12169b().mo24199a(context, "select title from ProductFile where productId=? ", new String[]{i + ""});
        ArrayList arrayList = new ArrayList();
        while (a.moveToNext()) {
            arrayList.add(a.getString(0));
        }
        if (!a.isClosed()) {
            a.close();
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* renamed from: c */
    public String mo25559c(Context context, int i) {
        return ((ProductFile) m12169b().mo24207b(context, "productId", Integer.valueOf(i))).getTags();
    }

    /* renamed from: d */
    public long mo25560d(Context context, int i) {
        long j = 0;
        List a = m12169b().mo24203a(context, "productId", Integer.valueOf(i));
        if (a != null && a.size() > 0) {
            for (int i2 = 0; i2 < a.size(); i2++) {
                j += ((ProductFile) a.get(i2)).getFileTotalSize().longValue();
            }
        }
        return j;
    }

    /* renamed from: e */
    public boolean mo25561e(Context context, int i) {
        Cursor a = m12169b().mo24199a(context, "delete from ProductFile where productId=?", new String[]{i + ""});
        if (!a.moveToNext()) {
            if (a != null) {
                a.close();
            }
            return false;
        } else if (a == null) {
            return true;
        } else {
            a.close();
            return true;
        }
    }
}
