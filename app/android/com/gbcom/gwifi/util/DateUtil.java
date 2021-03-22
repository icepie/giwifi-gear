package com.gbcom.gwifi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* renamed from: com.gbcom.gwifi.util.d */
public class DateUtil {
    /* renamed from: a */
    public static Date m14208a(String str) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(str);
    }

    /* renamed from: a */
    public static int m14207a(Date date) throws Exception {
        Calendar instance = Calendar.getInstance();
        if (instance.before(date)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int i = instance.get(1);
        int i2 = instance.get(2);
        int i3 = instance.get(5);
        instance.setTime(date);
        int i4 = instance.get(1);
        int i5 = instance.get(2);
        int i6 = instance.get(5);
        int i7 = i - i4;
        if (i2 > i5) {
            return i7;
        }
        if (i2 != i5) {
            return i7 - 1;
        }
        if (i3 < i6) {
            return i7 - 1;
        }
        return i7;
    }

    /* renamed from: b */
    public static int m14209b(String str) {
        Exception e;
        int i;
        try {
            i = m14207a(m14208a(str));
            try {
                System.out.println("age:" + i);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return i;
            }
        } catch (Exception e3) {
            i = 20;
            e = e3;
            e.printStackTrace();
            return i;
        }
        return i;
    }
}
