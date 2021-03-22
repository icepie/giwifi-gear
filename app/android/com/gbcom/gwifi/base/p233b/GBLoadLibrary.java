package com.gbcom.gwifi.base.p233b;

import android.content.Context;
import android.os.Build;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.p456io.File;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.gbcom.gwifi.base.b.b */
public class GBLoadLibrary {

    /* renamed from: a */
    private static final String f8939a = "GB.LoadLibrary";

    /* renamed from: a */
    public static synchronized void m10579a(ClassLoader classLoader, File file) throws Throwable {
        synchronized (GBLoadLibrary.class) {
            if (file != null) {
                if (file.exists()) {
                    if ((Build.VERSION.SDK_INT == 25 && m10575a() != 0) || Build.VERSION.SDK_INT > 25) {
                        try {
                            C2568c.m10586b(classLoader, file);
                        } catch (Throwable th) {
                            C2567b.m10584b(classLoader, file);
                        }
                    } else if (Build.VERSION.SDK_INT >= 23) {
                        try {
                            C2567b.m10584b(classLoader, file);
                        } catch (Throwable th2) {
                            C2566a.m10582b(classLoader, file);
                        }
                    } else if (Build.VERSION.SDK_INT >= 14) {
                        C2566a.m10582b(classLoader, file);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static int m10575a() {
        try {
            return Build.VERSION.PREVIEW_SDK_INT;
        } catch (Throwable th) {
            return 1;
        }
    }

    /* renamed from: com.gbcom.gwifi.base.b.b$a */
    /* compiled from: GBLoadLibrary */
    private static final class C2566a {
        private C2566a() {
        }

        /* access modifiers changed from: private */
        /* renamed from: b */
        public static void m10582b(ClassLoader classLoader, File file) throws Throwable {
            ShareReflectUtil.m10595a(ShareReflectUtil.m10591a(classLoader, "pathList").get(classLoader), "nativeLibraryDirectories", new File[]{file});
        }
    }

    /* renamed from: com.gbcom.gwifi.base.b.b$b */
    /* compiled from: GBLoadLibrary */
    private static final class C2567b {
        private C2567b() {
        }

        /* access modifiers changed from: private */
        /* renamed from: b */
        public static void m10584b(ClassLoader classLoader, File file) throws Throwable {
            Object obj = ShareReflectUtil.m10591a(classLoader, "pathList").get(classLoader);
            List list = (List) ShareReflectUtil.m10591a(obj, "nativeLibraryDirectories").get(obj);
            list.add(0, file);
            Method a = ShareReflectUtil.m10593a(obj, "makePathElements", (Class<?>[]) new Class[]{List.class, File.class, List.class});
            ArrayList arrayList = new ArrayList();
            list.addAll((List) ShareReflectUtil.m10591a(obj, "systemNativeLibraryDirectories").get(obj));
            Field a2 = ShareReflectUtil.m10591a(obj, "nativeLibraryPathElements");
            a2.setAccessible(true);
            a2.set(obj, (Object[]) a.invoke(obj, list, null, arrayList));
        }
    }

    /* renamed from: com.gbcom.gwifi.base.b.b$c */
    /* compiled from: GBLoadLibrary */
    private static final class C2568c {
        private C2568c() {
        }

        /* access modifiers changed from: private */
        /* renamed from: b */
        public static void m10586b(ClassLoader classLoader, File file) throws Throwable {
            Object obj = ShareReflectUtil.m10591a(classLoader, "pathList").get(classLoader);
            List list = (List) ShareReflectUtil.m10591a(obj, "nativeLibraryDirectories").get(obj);
            list.add(0, file);
            Method a = ShareReflectUtil.m10593a(obj, "makePathElements", (Class<?>[]) new Class[]{List.class});
            list.addAll((List) ShareReflectUtil.m10591a(obj, "systemNativeLibraryDirectories").get(obj));
            Field a2 = ShareReflectUtil.m10591a(obj, "nativeLibraryPathElements");
            a2.setAccessible(true);
            a2.set(obj, (Object[]) a.invoke(obj, list));
        }
    }

    /* renamed from: a */
    public static void m10577a(Context context) {
        if (Build.VERSION.SDK_INT >= 14) {
            File dir = context.getDir("libs", 0);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (Build.VERSION.SDK_INT >= 23) {
                m10580b(dir, context);
            } else {
                m10578a(dir, context);
            }
        }
    }

    /* renamed from: a */
    private static void m10578a(File file, Context context) {
        Object a = m10576a((PathClassLoader) context.getClassLoader());
        if (a != null) {
            try {
                Field declaredField = a.getClass().getDeclaredField("nativeLibraryDirectories");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(a);
                if (obj instanceof List) {
                    ((List) obj).add(file);
                } else if (obj instanceof File[]) {
                    File[] fileArr = new File[(((File[]) obj).length + 1)];
                    System.arraycopy(obj, 0, fileArr, 0, ((File[]) obj).length);
                    fileArr[((File[]) obj).length] = file;
                    declaredField.set(a, fileArr);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v8, resolved type: java.io.File[] */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    private static void m10580b(File file, Context context) {
        Object a = m10576a((PathClassLoader) context.getClassLoader());
        if (a != null) {
            try {
                Field declaredField = a.getClass().getDeclaredField("nativeLibraryPathElements");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(a);
                Constructor<?> constructor = declaredField.getType().getComponentType().getConstructor(File.class, Boolean.TYPE, File.class, DexFile.class);
                constructor.setAccessible(true);
                Object newInstance = constructor.newInstance(file, true, null, null);
                if (obj instanceof List) {
                    ((List) obj).add(newInstance);
                } else if (obj instanceof Object[]) {
                    File[] fileArr = new File[(((Object[]) obj).length + 1)];
                    System.arraycopy(obj, 0, fileArr, 0, ((Object[]) obj).length);
                    fileArr[((Object[]) obj).length] = newInstance;
                    declaredField.set(a, fileArr);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            } catch (InstantiationException e4) {
                e4.printStackTrace();
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private static Object m10576a(Object obj) {
        try {
            Field declaredField = Class.forName("dalvik.system.BaseDexClassLoader").getDeclaredField("pathList");
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
        return null;
    }
}
