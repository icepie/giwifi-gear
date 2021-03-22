package com.gbcom.gwifi.functions.notify.p245a;

import android.content.Context;
import com.gbcom.gwifi.functions.notify.domain.Notify;
import com.gbcom.gwifi.p230b.p231a.BaseDao;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;

/* renamed from: com.gbcom.gwifi.functions.notify.a.a */
public class NotifyDao extends BaseDao<Notify, Long> {

    /* renamed from: b */
    private static NotifyDao f9932b;

    /* renamed from: c */
    private final String f9933c = NotifyDao.class.getName();

    /* renamed from: b */
    public static synchronized NotifyDao m11495b() {
        NotifyDao aVar;
        synchronized (NotifyDao.class) {
            if (f9932b == null) {
                f9932b = new NotifyDao();
            }
            aVar = f9932b;
        }
        return aVar;
    }

    /* renamed from: a */
    public Dao.CreateOrUpdateStatus mo24215e(Context context, Notify notify) {
        try {
            return mo24200a(context).createOrUpdate(notify);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.gbcom.gwifi.p230b.p231a.BaseDao
    /* renamed from: a */
    public Class<Notify> mo24201a() {
        return Notify.class;
    }
}
