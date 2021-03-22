package com.gbcom.gwifi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Pattern;
import p419io.netty.handler.codec.http.HttpConstants;

/* renamed from: com.gbcom.gwifi.util.e */
public class FormatUtil {
    /* renamed from: a */
    public static byte[] m14218a(int i) {
        return new byte[]{(byte) ((i >>> 24) & 255), (byte) ((i >>> 16) & 255), (byte) ((i >>> 8) & 255), (byte) ((i >>> 0) & 255)};
    }

    /* renamed from: a */
    public static byte[] m14219a(short s) {
        return new byte[]{(byte) ((s >>> 8) & 255), (byte) ((s >>> 0) & 255)};
    }

    /* renamed from: a */
    public static int m14211a(byte[] bArr) {
        if (bArr.length != 4) {
            return 0;
        }
        return ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
    }

    /* renamed from: a */
    public static int m14212a(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, 4);
        int i3 = 0;
        for (int i4 = 0; i4 < min; i4++) {
            i3 = (i3 << 8) | (bArr[i + i4] & 255);
        }
        return i3;
    }

    /* renamed from: a */
    public static String m14217a(String str) {
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((("0123456789ABCDEF".indexOf(charArray[i * 2]) * 16) + "0123456789ABCDEF".indexOf(charArray[(i * 2) + 1])) & 255);
        }
        return new String(bArr).trim();
    }

    /* renamed from: b */
    public static String m14222b(byte[] bArr) {
        String str = "";
        if (bArr != null) {
            int length = bArr.length;
            int i = 0;
            while (i < length) {
                Byte valueOf = Byte.valueOf(bArr[i]);
                i++;
                str = str + String.format("%02X", Integer.valueOf(valueOf.intValue() & 255));
            }
        }
        return str;
    }

    /* renamed from: b */
    public static byte[] m14223b(String str) {
        byte[] bArr = {0, 0, 0, 0, 0, 0};
        String[] split = str.split("-");
        for (int i = 0; i < split.length; i++) {
            bArr[i] = (byte) (Integer.parseInt(split[i], 16) & 255);
        }
        return bArr;
    }

    /* renamed from: a */
    public static String m14213a() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /* renamed from: b */
    public static String m14220b() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /* renamed from: c */
    public static long m14225c() {
        return System.currentTimeMillis() / 1000;
    }

    /* renamed from: a */
    public static String m14215a(long j, long j2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        if (j <= 0) {
            return simpleDateFormat.format((Object) 0);
        }
        if (j > 0 && j < j2) {
            return simpleDateFormat.format(Long.valueOf(j));
        }
        if (j >= j2) {
            return simpleDateFormat.format(Long.valueOf(j2));
        }
        return "";
    }

    /* renamed from: a */
    public static String m14214a(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(j));
    }

    /* renamed from: b */
    public static String m14221b(long j) {
        return new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(j));
    }

    /* renamed from: c */
    public static String m14227c(long j) {
        return new SimpleDateFormat("HH:mm:ss").format(Long.valueOf(j));
    }

    /* renamed from: d */
    public static String m14231d(long j) {
        return new SimpleDateFormat("HH:mm").format(Long.valueOf(j));
    }

    /* renamed from: e */
    public static String m14233e(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String[] split = simpleDateFormat.format(Long.valueOf(j)).split(":");
        if (split == null || split.length <= 0) {
            return "";
        }
        String str = split[0];
        String str2 = split[1];
        String str3 = split[2];
        if (Integer.parseInt(str) != 0) {
            return str + "小时" + str2 + "分" + str3 + "秒";
        }
        if (Integer.parseInt(str2) != 0) {
            return str2 + "分" + str3 + "秒";
        }
        if (Integer.parseInt(str3) == 0) {
            return "";
        }
        return str3 + "秒";
    }

    /* renamed from: d */
    public static String m14230d() {
        return new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    }

    /* renamed from: e */
    public static String m14232e() {
        return String.valueOf(Calendar.getInstance().get(1));
    }

    /* renamed from: a */
    public static int m14210a(int i, int i2) {
        return (new Random().nextInt(i2) % ((i2 - i) + 1)) + i;
    }

    /* renamed from: f */
    public static int m14235f() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(m14210a(1, 9));
        for (int i = 0; i < 6; i++) {
            stringBuffer.append(m14210a(0, 9));
        }
        return Integer.parseInt(stringBuffer.toString());
    }

    /* renamed from: b */
    public static int[] m14224b(int i) {
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = i2;
        }
        Random random = new Random();
        int i3 = i - 1;
        for (int i4 = 0; i4 < i; i4++) {
            int nextInt = random.nextInt(i3 + 1);
            iArr2[i4] = iArr[nextInt];
            iArr[nextInt] = iArr[i3];
            i3--;
        }
        return iArr2;
    }

    /* renamed from: c */
    public static String m14228c(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 12288) {
                charArray[i] = HttpConstants.SP_CHAR;
            } else if (charArray[i] > 65280 && charArray[i] < 65375) {
                charArray[i] = (char) (charArray[i] - 65248);
            }
        }
        return new String(charArray);
    }

    /* renamed from: c */
    public static String m14226c(int i) {
        switch (i) {
            case 0:
                return Constant.f13330p;
            case 1:
                return Constant.f13331q;
            case 2:
                return Constant.f13332r;
            default:
                return "error";
        }
    }

    /* renamed from: d */
    public static int m14229d(String str) {
        if (str.equals(Constant.f13331q)) {
            return 1;
        }
        if (str.equals(Constant.f13332r)) {
            return 2;
        }
        return 0;
    }

    /* renamed from: a */
    public static String m14216a(Integer num) {
        return " + " + ((Object) num) + Constant.f13309cr;
    }

    /* renamed from: e */
    public static boolean m14234e(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }
}
