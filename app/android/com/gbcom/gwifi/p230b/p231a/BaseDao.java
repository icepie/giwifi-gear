package com.gbcom.gwifi.p230b.p231a;

import android.content.Context;
import android.database.Cursor;
import com.gbcom.gwifi.base.p232a.DatabaseHelper;
import com.gbcom.gwifi.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* renamed from: com.gbcom.gwifi.b.a.a */
public abstract class BaseDao<T, PK> {

    /* renamed from: b */
    private static final String f8648b = "BaseDao";

    /* renamed from: a */
    public Class<T> f8649a;

    /* renamed from: a */
    public abstract Class<T> mo24201a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Dao<T, PK> mo24200a(Context context) throws SQLException {
        return DatabaseHelper.m10426a(context).getDao(mo24201a());
    }

    /* renamed from: a */
    public Cursor mo24199a(Context context, String str, String[] strArr) {
        return DatabaseHelper.m10426a(context).getWritableDatabase().rawQuery(str, strArr);
    }

    /* renamed from: a */
    public void mo24205a(Context context, T t) {
        try {
            mo24200a(context).update((Object) t);
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
        }
    }

    /* renamed from: b */
    public T mo24206b(Context context, PK pk) {
        try {
            return mo24200a(context).queryForId(pk);
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            return null;
        }
    }

    /* renamed from: b */
    public List<T> mo24208b(Context context) {
        try {
            return mo24200a(context).queryForAll();
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            return null;
        }
    }

    /* renamed from: c */
    public long mo24209c(Context context) {
        try {
            return mo24200a(context).queryRawValue("select count(*) from " + mo24201a().getSimpleName(), new String[0]);
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            return 0;
        }
    }

    /* renamed from: a */
    public List<T> mo24203a(Context context, String str, Object obj) {
        try {
            return mo24200a(context).queryForEq(str, obj);
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            return null;
        }
    }

    /* renamed from: b */
    public T mo24207b(Context context, String str, Object obj) {
        try {
            List<T> queryForEq = mo24200a(context).queryForEq(str, obj);
            if (queryForEq != null && queryForEq.size() > 0) {
                return queryForEq.get(0);
            }
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
        }
        return null;
    }

    /* renamed from: c */
    public T mo24210c(Context context, T t) {
        try {
            mo24200a(context).refresh(t);
            return t;
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            return null;
        }
    }

    public int delete(Context context, T t) {
        try {
            return mo24200a(context).delete((Object) t);
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            return -1;
        }
    }

    /* renamed from: a */
    public List<T> mo24204a(Context context, Map<String, Object> map) {
        try {
            return mo24200a(context).queryForFieldValues(map);
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            return null;
        }
    }

    /* renamed from: d */
    public int mo24211d(Context context, T t) {
        try {
            return mo24200a(context).create(t);
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            return -1;
        }
    }

    /* renamed from: a */
    public <TS> TS mo24202a(Context context, Callable<TS> callable) {
        try {
            return (TS) TransactionManager.callInTransaction(mo24200a(context).getConnectionSource(), callable);
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            return null;
        }
    }

    public int delete(Context context, Collection<T> collection) {
        int i = 0;
        if (collection == null) {
            return 0;
        }
        try {
            for (T t : collection) {
                i += mo24200a(context).delete((Object) t);
            }
            return i;
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            return -1;
        }
    }

    /* renamed from: e */
    public Dao.CreateOrUpdateStatus mo24215e(Context context, T t) {
        try {
            return mo24200a(context).createOrUpdate(t);
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: f */
    public int mo24216f(Context context, T t) {
        try {
            return mo24200a(context).create(t);
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: d */
    public QueryBuilder<T, PK> mo24212d(Context context) {
        try {
            return mo24200a(context).queryBuilder();
        } catch (SQLException e) {
            Log.m14403e(f8648b, getClass().getName() + ":" + e.toString());
            return null;
        }
    }
}
