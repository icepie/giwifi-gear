package com.gbcom.gwifi.functions.product.p248b;

import android.content.Context;
import com.gbcom.gwifi.functions.product.domain.AttentionFile;
import com.gbcom.gwifi.p230b.p231a.BaseDao;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;

/* renamed from: com.gbcom.gwifi.functions.product.b.a */
public class AttentionFileDao extends BaseDao<AttentionFile, Long> {

    /* renamed from: b */
    private static AttentionFileDao f10745b;

    /* renamed from: c */
    private final String f10746c = AttentionFileDao.class.getName();

    /* renamed from: b */
    public static synchronized AttentionFileDao m12148b() {
        AttentionFileDao aVar;
        synchronized (AttentionFileDao.class) {
            if (f10745b == null) {
                f10745b = new AttentionFileDao();
            }
            aVar = f10745b;
        }
        return aVar;
    }

    /* renamed from: a */
    public Dao.CreateOrUpdateStatus mo24215e(Context context, AttentionFile attentionFile) {
        try {
            return mo24200a(context).createOrUpdate(attentionFile);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.gbcom.gwifi.p230b.p231a.BaseDao
    /* renamed from: a */
    public Class<AttentionFile> mo24201a() {
        return AttentionFile.class;
    }
}
