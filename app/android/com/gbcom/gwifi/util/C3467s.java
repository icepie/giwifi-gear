package com.gbcom.gwifi.util;

import android.icu.text.DateFormat;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import p419io.netty.handler.codec.http.HttpConstants;

/* renamed from: com.gbcom.gwifi.util.s */
/* compiled from: StringUtils */
public class C3467s {
    /* renamed from: a */
    public static String m14503a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length() - 1;
        if (length >= 0) {
            return (str.charAt(0) == '\"' && str.charAt(length) == '\"') ? str : "\"" + str + "\"";
        }
        return str;
    }

    /* renamed from: a */
    public static String m14507a(List<String[]> list) {
        String str = "";
        for (String[] strArr : list) {
            str = str + strArr[0] + "_" + strArr[1] + "|";
        }
        return str.length() > 0 ? str.substring(0, str.length() - 1) : str;
    }

    /* renamed from: a */
    public static String m14504a(String str, String str2) {
        return "" + str + "_" + str2 + "|";
    }

    /* renamed from: b */
    public static ArrayList<String[]> m14509b(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList<>();
        }
        String[] split = str.split("\\|");
        ArrayList<String[]> arrayList = new ArrayList<>();
        for (String str2 : split) {
            arrayList.add(str2.split("_"));
        }
        return arrayList;
    }

    /* renamed from: c */
    public static String[] m14511c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split("\\,");
    }

    /* renamed from: a */
    public static String m14506a(ArrayList<String[]> arrayList, String str) {
        String str2 = "";
        Iterator<String[]> it = arrayList.iterator();
        while (it.hasNext()) {
            str2 = str2 + it.next()[1] + str;
        }
        return str2.length() > 0 ? str2.substring(0, str2.length() - str.length()) : str2;
    }

    /* renamed from: d */
    public static ArrayList<String> m14512d(String str) {
        String[] split = str.split("\\$\\|\\$");
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str2 : split) {
            arrayList.add(str2);
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String m14505a(ArrayList<String> arrayList) {
        String str = "";
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            str = str + it.next() + "$|$";
        }
        return str.length() > 0 ? str.substring(0, str.length() - "$|$".length()) : str;
    }

    /* renamed from: a */
    public static String m14501a(long j) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        if (j > WifiManager.WIFI_FEATURE_LOW_LATENCY) {
            return decimalFormat.format(((double) j) / 1.073741824E9d) + "G";
        }
        if (j > WifiManager.WIFI_FEATURE_MKEEP_ALIVE) {
            return decimalFormat.format(((double) j) / 1048576.0d) + DateFormat.NUM_MONTH;
        }
        if (j > 1024) {
            return decimalFormat.format(((double) j) / 1024.0d) + "KB";
        }
        return ((int) j) + "B";
    }

    /* renamed from: b */
    public static String m14508b(long j) {
        DecimalFormat decimalFormat = new DecimalFormat(".0");
        if (j > WifiManager.WIFI_FEATURE_LOW_LATENCY) {
            return decimalFormat.format(((double) j) / 1.073741824E9d) + "G";
        }
        if (j > WifiManager.WIFI_FEATURE_MKEEP_ALIVE) {
            return decimalFormat.format(((double) j) / 1048576.0d) + DateFormat.NUM_MONTH;
        }
        if (j > 1024) {
            return decimalFormat.format(((double) j) / 1024.0d) + "KB";
        }
        return ((int) j) + "B";
    }

    /* renamed from: c */
    public static String m14510c(long j) {
        if (j > 10000) {
            return new DecimalFormat(".00").format(((double) j) / 10000.0d) + "万";
        }
        return j + "";
    }

    /* renamed from: a */
    public static String m14502a(long j, long j2) {
        long j3 = (j - j2) / 1000;
        long j4 = j3 % 60;
        long j5 = (j3 / 60) % 60;
        long j6 = ((j3 / 60) / 60) % 24;
        StringBuilder sb = new StringBuilder();
        if ((j3 / 60) / 60 > 0) {
            if (j6 < 10) {
                sb.append("0" + j6 + "小时");
            } else {
                sb.append(j6 + "小时");
            }
        }
        if (j5 < 10) {
            sb.append("0" + j5 + "分");
        } else {
            sb.append(j5 + "分");
        }
        if (j4 < 10) {
            sb.append("0" + j4 + "秒");
        } else {
            sb.append(j4 + "秒");
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static String m14500a() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(11);
        return i + ":" + instance.get(12);
    }

    /* renamed from: e */
    public static boolean m14513e(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public static String m14514f(String str) {
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

    /* renamed from: g */
    public static Date m14515g(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str, new ParsePosition(0));
    }

    /* renamed from: h */
    public static boolean m14516h(String str) {
        try {
            new SimpleDateFormat("yyyy-MM-DD").parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /* renamed from: i */
    public static String m14517i(String str) {
        return Pattern.compile("[『』]").matcher(str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!").replaceAll("：", ":")).replaceAll("").trim();
    }

    /* renamed from: j */
    public static boolean m14518j(String str) {
        if (!Pattern.compile("[0-9]*").matcher(str).matches()) {
            return false;
        }
        return true;
    }
}
