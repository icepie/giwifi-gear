package com.gbcom.gwifi.functions.product.p248b;

import android.content.Context;
import android.database.Cursor;
import com.gbcom.gwifi.functions.product.domain.DownloadFile;
import com.gbcom.gwifi.p230b.p231a.BaseDao;
import com.j256.ormlite.dao.Dao;
import java.p456io.File;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.gbcom.gwifi.functions.product.b.b */
public class DownloadFileDao extends BaseDao<DownloadFile, Long> {

    /* renamed from: b */
    private static DownloadFileDao f10747b;

    /* renamed from: c */
    private final String f10748c = DownloadFileDao.class.getName();

    /* renamed from: b */
    public static synchronized DownloadFileDao m12152b() {
        DownloadFileDao bVar;
        synchronized (DownloadFileDao.class) {
            if (f10747b == null) {
                f10747b = new DownloadFileDao();
            }
            bVar = f10747b;
        }
        return bVar;
    }

    /* renamed from: a */
    public Dao.CreateOrUpdateStatus mo24215e(Context context, DownloadFile downloadFile) {
        try {
            return mo24200a(context).createOrUpdate(downloadFile);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.gbcom.gwifi.p230b.p231a.BaseDao
    /* renamed from: a */
    public Class<DownloadFile> mo24201a() {
        return DownloadFile.class;
    }

    /* renamed from: a */
    public int mo25542a(Context context, String str) {
        return mo25543a(context, str, (Long) -1L);
    }

    /* renamed from: a */
    public int mo25543a(Context context, String str, Long l) {
        int i = 0;
        Cursor a = m12152b().mo24199a(context, "select count(*) from DownloadFile where productType=? and stateId=0 and parentId =?", new String[]{str, "" + ((Object) l)});
        if (a.moveToNext()) {
            i = a.getInt(0);
        }
        if (!a.isClosed()) {
            a.close();
        }
        return i;
    }

    /* renamed from: b */
    public int mo25549b(Context context, String str) {
        int i = 0;
        Cursor a = m12152b().mo24199a(context, "select count(*) from DownloadFile where productType=? and stateId=0", new String[]{str});
        if (a.moveToNext()) {
            i = a.getInt(0);
        }
        if (!a.isClosed()) {
            a.close();
        }
        return i;
    }

    /* renamed from: b */
    public long mo25550b(Context context, String str, Long l) {
        HashMap hashMap = new HashMap();
        hashMap.put("productType", str);
        hashMap.put("parentId", l);
        hashMap.put("stateId", 0);
        long j = 0;
        List a = m12152b().mo24204a(context, (Map<String, Object>) hashMap);
        if (a != null && a.size() > 0) {
            for (int i = 0; i < a.size(); i++) {
                j += ((DownloadFile) a.get(i)).getFileTotalSize().longValue();
            }
        }
        return j;
    }

    /* renamed from: c */
    public long mo25551c(Context context, String str, Long l) {
        HashMap hashMap = new HashMap();
        hashMap.put("productType", str);
        hashMap.put("parentId", l);
        hashMap.put("stateId", 0);
        long j = 0;
        List a = m12152b().mo24204a(context, (Map<String, Object>) hashMap);
        if (a != null && a.size() > 0) {
            for (int i = 0; i < a.size(); i++) {
                j += ((DownloadFile) a.get(i)).getFinishTime().longValue() - ((DownloadFile) a.get(i)).getCreateTime().longValue();
            }
        }
        return j;
    }

    /* renamed from: d */
    public String mo25554d(Context context, String str, Long l) {
        HashMap hashMap = new HashMap();
        hashMap.put("productType", str);
        hashMap.put("parentId", l);
        hashMap.put("stateId", 0);
        List a = m12152b().mo24204a(context, (Map<String, Object>) hashMap);
        if (a == null || a.size() <= 0) {
            return "";
        }
        return ((DownloadFile) a.get(0)).getLocalFile();
    }

    /* renamed from: a */
    public void mo25546a(Context context, String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("productType", str);
        hashMap.put("productId", Integer.valueOf(i));
        hashMap.put("stateId", 0);
        List<DownloadFile> a = m12152b().mo24204a(context, (Map<String, Object>) hashMap);
        if (a != null && a.size() > 0) {
            m12152b().delete(context, (Collection) a);
            for (DownloadFile downloadFile : a) {
                File file = new File(downloadFile.getLocalFile());
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    /* renamed from: a */
    public void mo25548a(Context context, String str, String str2) {
        m12152b().mo24199a(context, "update DownloadFile set localFile = ? where url = ?", new String[]{str2, str});
    }

    /* renamed from: c */
    public String mo25552c(Context context, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("stateId", 0);
        List a = m12152b().mo24204a(context, (Map<String, Object>) hashMap);
        if (a == null || a.size() <= 0) {
            return null;
        }
        return ((DownloadFile) a.get(0)).getLocalFile();
    }

    /* renamed from: d */
    public int mo25553d(Context context, String str) {
        DownloadFile downloadFile = (DownloadFile) m12152b().mo24207b(context, "url", str);
        if (downloadFile != null) {
            return downloadFile.getStateId().intValue();
        }
        return 2;
    }

    /* renamed from: a */
    public void mo25547a(Context context, String str, Integer num) {
        m12152b().mo24199a(context, "update DownloadFile set stateId = ? where url = ?", new String[]{((Object) num) + "", str});
    }

    /* renamed from: e */
    public boolean mo25555e(Context context, String str) {
        Cursor a = m12152b().mo24199a(context, "delete from DownloadFile where url = ?", new String[]{str});
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

    /* renamed from: a */
    public void mo25545a(Context context, Long l, Long l2, String str) {
        m12152b().mo24199a(context, "update DownloadFile set downsize = ? ,fileTotalSize = ?,stateId = 1 where url = ?", new String[]{((Object) l) + "", ((Object) l2) + "", str});
    }
}
