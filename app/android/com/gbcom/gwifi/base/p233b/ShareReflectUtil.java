package com.gbcom.gwifi.base.p233b;

import android.content.Context;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/* renamed from: com.gbcom.gwifi.base.b.c */
public class ShareReflectUtil {
    /* renamed from: a */
    public static Field m10591a(Object obj, String str) throws NoSuchFieldException {
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException e) {
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found in " + ((Object) obj.getClass()));
    }

    /* renamed from: a */
    public static Field m10590a(Class<?> cls, String str) throws NoSuchFieldException {
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            try {
                Field declaredField = cls2.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException e) {
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found in " + ((Object) cls));
    }

    /* renamed from: a */
    public static Method m10593a(Object obj, String str, Class<?>... clsArr) throws NoSuchMethodException {
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException e) {
            }
        }
        throw new NoSuchMethodException("Method " + str + " with parameters " + ((Object) Arrays.asList(clsArr)) + " not found in " + ((Object) obj.getClass()));
    }

    /* renamed from: a */
    public static Method m10592a(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException {
        while (cls != null) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException e) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchMethodException("Method " + str + " with parameters " + ((Object) Arrays.asList(clsArr)) + " not found in " + ((Object) cls));
    }

    /* renamed from: a */
    public static Constructor<?> m10589a(Object obj, Class<?>... clsArr) throws NoSuchMethodException {
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Constructor<?> declaredConstructor = cls.getDeclaredConstructor(clsArr);
                if (!declaredConstructor.isAccessible()) {
                    declaredConstructor.setAccessible(true);
                }
                return declaredConstructor;
            } catch (NoSuchMethodException e) {
            }
        }
        throw new NoSuchMethodException("Constructor with parameters " + ((Object) Arrays.asList(clsArr)) + " not found in " + ((Object) obj.getClass()));
    }

    /* renamed from: a */
    public static void m10595a(Object obj, String str, Object[] objArr) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field a = m10591a(obj, str);
        Object[] objArr2 = (Object[]) a.get(obj);
        Object[] objArr3 = (Object[]) Array.newInstance(objArr2.getClass().getComponentType(), objArr2.length + objArr.length);
        System.arraycopy(objArr, 0, objArr3, 0, objArr.length);
        System.arraycopy(objArr2, 0, objArr3, objArr.length, objArr2.length);
        a.set(obj, objArr3);
    }

    /* renamed from: a */
    public static void m10594a(Object obj, String str, int i) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (i > 0) {
            Field a = m10591a(obj, str);
            Object[] objArr = (Object[]) a.get(obj);
            int length = objArr.length - i;
            if (length > 0) {
                Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), length);
                System.arraycopy(objArr, i, objArr2, 0, length);
                a.set(obj, objArr2);
            }
        }
    }

    /* renamed from: a */
    public static Object m10588a(Context context, Class<?> cls) {
        if (cls == null) {
            try {
                cls = Class.forName("android.app.ActivityThread");
            } catch (Throwable th) {
                return null;
            }
        }
        Method method = cls.getMethod("currentActivityThread", new Class[0]);
        method.setAccessible(true);
        Object invoke = method.invoke(null, new Object[0]);
        if (invoke != null || context == null) {
            return invoke;
        }
        Field field = context.getClass().getField("mLoadedApk");
        field.setAccessible(true);
        Object obj = field.get(context);
        Field declaredField = obj.getClass().getDeclaredField("mActivityThread");
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    /* renamed from: a */
    public static int m10587a(Class<?> cls, String str, int i) {
        try {
            return m10590a(cls, str).getInt(null);
        } catch (Throwable th) {
            return i;
        }
    }
}
