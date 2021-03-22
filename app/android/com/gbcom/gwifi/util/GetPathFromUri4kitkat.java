package com.gbcom.gwifi.util;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.apache.xpath.compiler.PsuedoNames;

/* renamed from: com.gbcom.gwifi.util.f */
public class GetPathFromUri4kitkat {
    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static String m14236a(Context context, Uri uri) {
        Uri uri2 = null;
        if (!(Build.VERSION.SDK_INT >= 19) || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                return m14237a(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
            return null;
        } else if (m14238a(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                return ((Object) Environment.getExternalStorageDirectory()) + PsuedoNames.PSEUDONAME_ROOT + split[1];
            }
            return null;
        } else if (m14239b(uri)) {
            return m14237a(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else if (!m14240c(uri)) {
            return null;
        } else {
            String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
            String str = split2[0];
            if (SocializeProtocolConstants.IMAGE.equals(str)) {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(str)) {
                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(str)) {
                uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return m14237a(context, uri2, "_id=?", new String[]{split2[1]});
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0039  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m14237a(android.content.Context r7, android.net.Uri r8, java.lang.String r9, java.lang.String[] r10) {
        /*
            r6 = 0
            java.lang.String r0 = "_data"
            r0 = 1
            java.lang.String[] r2 = new java.lang.String[r0]
            r0 = 0
            java.lang.String r1 = "_data"
            r2[r0] = r1
            android.content.ContentResolver r0 = r7.getContentResolver()     // Catch:{ all -> 0x0036 }
            r5 = 0
            r1 = r8
            r3 = r9
            r4 = r10
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x002f
            boolean r0 = r1.moveToFirst()     // Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x002f
            java.lang.String r0 = "_data"
            int r0 = r1.getColumnIndexOrThrow(r0)     // Catch:{ all -> 0x003d }
            java.lang.String r0 = r1.getString(r0)     // Catch:{ all -> 0x003d }
            if (r1 == 0) goto L_0x002e
            r1.close()
        L_0x002e:
            return r0
        L_0x002f:
            if (r1 == 0) goto L_0x0034
            r1.close()
        L_0x0034:
            r0 = r6
            goto L_0x002e
        L_0x0036:
            r0 = move-exception
        L_0x0037:
            if (r6 == 0) goto L_0x003c
            r6.close()
        L_0x003c:
            throw r0
        L_0x003d:
            r0 = move-exception
            r6 = r1
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.util.GetPathFromUri4kitkat.m14237a(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    /* renamed from: a */
    public static boolean m14238a(Uri uri) {
        return MediaStore.EXTERNAL_STORAGE_PROVIDER_AUTHORITY.equals(uri.getAuthority());
    }

    /* renamed from: b */
    public static boolean m14239b(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /* renamed from: c */
    public static boolean m14240c(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
