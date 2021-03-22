package com.gbcom.gwifi.base.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.Toast;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.base.service.CheckNetworkService;
import com.gbcom.gwifi.functions.loading.LoginThirdActivity;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.loading.ShowActivity;
import com.gbcom.gwifi.functions.loading.StatusLoginActivity;
import com.gbcom.gwifi.functions.p244d.ShareHelper;
import com.gbcom.gwifi.p221a.p223b.HttpService;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.bugly.GiwifiBugly;
import com.gbcom.gwifi.third.kefu.easemob.GiwifiHXService;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiAnalyticsService;
import com.gbcom.gwifi.third.umeng.push.GiwifiPushService;
import com.gbcom.gwifi.third.umeng.share.GiwifiShareService;
import com.gbcom.gwifi.third.webview.p255x5.GiwifiWebViewService;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.WebViewUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.p278qq.p279e.comm.managers.GDTADManager;
import com.p278qq.p279e.comm.managers.setting.GlobalSetting;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.tencent.p369mm.opensdk.modelmsg.WXVideoFileObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

public class GBApplication extends Application {
    public static final String GIWIFI_FACE_DETECT_CALLBACK = "com.giwifi.facedetect.action";
    public static final String GIWIFI_FACE_NUM_CALLBACK = "com.giwifi.facenum.action";
    public static final String GIWIFI_FORM_UPLOAD_CALLBACK = "com.giwifi.form_upload_callback";
    public static final String GIWIFI_QRCODE_SCAN_CALLBACK = "com.giwifi.qrcodescan.action";
    public static final String GIWIFI_QRCODE_SCAN_FUNC_CALLBACK = "com.giwifi.qrcodescan.func.action";
    public static final String GIWIFI_TT_REWARDVIDEO_CALLBACK = "com.gbcom.tt.rewardvideo.action";
    public static final String GIWIFI_WM_REWARDVIDEO_CALLBACK = "com.gbcom.wm.rewardvideo.action";
    public static final String GIWIFI_WXPAY_CALLBACK_ACTION = "com.giwifi.wxPay.callback.action";
    public static final String GIWIFI_WXPAY_CALLBACK_ACTION_V2 = "com.giwifi.wxPay.callback.action.v2";
    public static final String GWIFI_CHANGE_BALANCE_ACTION = "com.giwifi.changebalance.action";
    public static final String GWIFI_LOGOUT = "gwifi_logout";
    public static final String GWIFI_SHARE_ACTION = "com.giwifi.share.action";
    public static final String GWIFI_SIGN_ACTION = "com.giwifi.sign.action";
    public static final boolean IS_DECODE_MODEL = true;

    /* renamed from: b */
    private static final String f8805b = "GBApplication";

    /* renamed from: d */
    private static GBApplication f8806d;

    /* renamed from: a */
    OkRequestHandler<String> f8807a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.base.app.GBApplication.C25296 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: c */
    private List<GBActivity> f8808c = Collections.synchronizedList(new LinkedList());

    /* renamed from: e */
    private ServiceManager f8809e;

    /* renamed from: f */
    private GiwifiPushService f8810f;

    /* renamed from: g */
    private BroadcastReceiver f8811g = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.base.app.GBApplication.C25242 */

        public void onReceive(Context context, Intent intent) {
            GBApplication.this.m10463a(intent.getStringExtra(Constant.f13323i));
        }
    };

    /* renamed from: h */
    private BroadcastReceiver f8812h = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.base.app.GBApplication.C25253 */

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals(GBApplication.GWIFI_SHARE_ACTION)) {
                String stringExtra = intent.getStringExtra("title");
                String stringExtra2 = intent.getStringExtra("intro");
                String stringExtra3 = intent.getStringExtra("icon_url");
                String stringExtra4 = intent.getStringExtra("wap_url");
                int intExtra = intent.getIntExtra(DispatchConstants.PLATFORM, 0);
                GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
                if (currentActivity != null) {
                    ShareHelper.m10859a(currentActivity).mo24540a(stringExtra2, stringExtra, stringExtra3, stringExtra4, intExtra);
                }
            }
        }
    };
    public DisplayImageOptions head_options = new DisplayImageOptions.Builder().showImageOnLoading(C2425R.C2426drawable.user).showImageForEmptyUri(C2425R.C2426drawable.user).showImageOnFail(C2425R.C2426drawable.user).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();

    /* renamed from: i */
    private BroadcastReceiver f8813i = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.base.app.GBApplication.C25264 */

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals(GBApplication.GWIFI_SIGN_ACTION)) {
                GBActivity.openBackWebView(HttpUtil.m14320f(), "签到", 0);
            }
        }
    };

    /* renamed from: j */
    private BroadcastReceiver f8814j = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.base.app.GBApplication.C25275 */

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals(GBApplication.GWIFI_CHANGE_BALANCE_ACTION)) {
                final int intExtra = intent.getIntExtra("balance", 0);
                HttpUtil.m14245a(GBApplication.instance(), 4, intent.getIntExtra("id", 0), CacheAccount.getInstance().getUserId() + "", intExtra, new OkRequestHandler<String>() {
                    /* class com.gbcom.gwifi.base.app.GBApplication.C25275.C25281 */

                    @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
                    public void onRequestStart(Request abVar) {
                    }

                    @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
                    public void onRequestFailed(Request abVar, Exception exc) {
                        GBActivity.showMessageToast("请检查网络");
                    }

                    /* renamed from: a */
                    public void onRequestFinish(Request abVar, String str) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE) == 0) {
                                GBActivity.showMessageToast("领取成功,获得" + intExtra + Constant.f13309cr);
                            } else {
                                GBActivity.showMessageToast(jSONObject.getString("resultMsg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
                    public void onRequestProgress(Request abVar, long j, long j2) {
                    }
                }, "");
                GBApplication.instance().sendBroadcast(new Intent(Constant.f13231bF));
            }
        }
    };
    public DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(C2425R.C2426drawable.loading_small).showImageForEmptyUri(C2425R.C2426drawable.loading_small).showImageOnFail(C2425R.C2426drawable.loading_small).cacheInMemory(false).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
    public DisplayImageOptions options2 = new DisplayImageOptions.Builder().showImageOnLoading(C2425R.C2426drawable.loading_big).showImageForEmptyUri(C2425R.C2426drawable.loading_big).showImageOnFail(C2425R.C2426drawable.loading_big).cacheInMemory(false).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
    public DisplayImageOptions options2Radius = new DisplayImageOptions.Builder().showImageOnLoading(C2425R.C2426drawable.loading_big).showImageForEmptyUri(C2425R.C2426drawable.loading_big).showImageOnFail(C2425R.C2426drawable.loading_big).cacheInMemory(false).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).displayer(new RoundedBitmapDisplayer(20)).build();
    public DisplayImageOptions options3 = new DisplayImageOptions.Builder().showImageOnLoading(C2425R.C2426drawable.loading_small).showImageForEmptyUri(C2425R.C2426drawable.loading_small).showImageOnFail(C2425R.C2426drawable.loading_small).cacheInMemory(false).cacheOnDisk(true).considerExifParams(true).build();
    public DisplayImageOptions options4 = new DisplayImageOptions.Builder().showImageOnLoading(C2425R.C2426drawable.loading_small).showImageForEmptyUri(C2425R.C2426drawable.loading_small).showImageOnFail(C2425R.C2426drawable.loading_small).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
    public DisplayImageOptions optionsRadius = new DisplayImageOptions.Builder().showImageOnLoading(C2425R.C2426drawable.loading_small).showImageForEmptyUri(C2425R.C2426drawable.loading_small).showImageOnFail(C2425R.C2426drawable.loading_small).cacheInMemory(false).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).displayer(new RoundedBitmapDisplayer(20)).build();
    public DisplayImageOptions tabOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();

    public GBApplication() {
        f8806d = this;
    }

    /* renamed from: a */
    static GBApplication m10461a() {
        return f8806d;
    }

    public static GBApplication instance() {
        if (f8806d != null) {
            return f8806d;
        }
        return null;
    }

    public LayoutInflater getLayoutInflater() {
        return (LayoutInflater) getSystemService("layout_inflater");
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    /* renamed from: b */
    private static void m10464b() {
        try {
            Class.forName("android.content.pm.PackageParser$Package").getDeclaredConstructor(String.class).setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mHiddenApiWarningShown");
            declaredField.setAccessible(true);
            declaredField.setBoolean(invoke, true);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onCreate() {
        Log.m14398b(f8805b, "GBApplication onCreate()");
        super.onCreate();
        String str = getApplicationInfo().packageName;
        Log.m14398b(f8805b, "applicationId=" + str);
        String g = SystemUtil.m14534g(this);
        Log.m14398b(f8805b, "processName=" + g);
        new Handler(Looper.myLooper()).post(new Runnable() {
            /* class com.gbcom.gwifi.base.app.GBApplication.RunnableC25231 */

            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        Looper.loop();
                    } catch (Exception e) {
                        Log.m14403e(GBApplication.f8805b, "GBApplication Exception:" + e.toString());
                    }
                }
            }
        });
        if (g != null && g.equals(str)) {
            GBGlobalConfig.m10510c().mo24389a();
        }
        this.f8810f = new GiwifiPushService(this);
        new GiwifiAnalyticsService(this);
        new GiwifiShareService(this);
        if (g != null && g.equals(str)) {
            GiwifiBugly.getInstance(this);
            Intent intent = new Intent();
            intent.setAction("com.action.download.download_service");
            intent.putExtra("type", 2);
            intent.setPackage(getPackageName());
            startService(intent);
            Intent intent2 = new Intent(this, CheckNetworkService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent2);
            } else {
                startService(intent2);
            }
            GiwifiHXService.getInstance(this);
            if (this.f8810f != null) {
                this.f8810f.getGiwifiPushAgent().registerNotificationReceiver();
            }
            registerReceiver(this.f8811g, new IntentFilter(GWIFI_LOGOUT));
            registerReceiver(this.f8812h, new IntentFilter(GWIFI_SHARE_ACTION));
            registerReceiver(this.f8814j, new IntentFilter(GWIFI_CHANGE_BALANCE_ACTION));
            registerReceiver(this.f8813i, new IntentFilter(GWIFI_SIGN_ACTION));
            initImageLoader(getApplicationContext());
            m10465c();
            GiwifiWebViewService.getInstance(getApplicationContext());
            m10464b();
            Resources resources = super.getResources();
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        if (!SystemUtil.m14533f()) {
            GDTADManager.getInstance().initWith(this, getResources().getString(C2425R.C2429string.GDTAppId));
            GlobalSetting.setChannel(1);
            GlobalSetting.setEnableMediationTool(true);
        }
        if (Build.VERSION.SDK_INT >= 28 && !getPackageName().equals(g)) {
            WebView.setDataDirectorySuffix(g);
        }
    }

    public GiwifiPushService getGiwifiPushService() {
        return this.f8810f;
    }

    /* renamed from: c */
    private void m10465c() {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m10463a(String str) {
        if (CacheAuth.getInstance().isPortal()) {
            HttpUtil.m14346p(instance(), this.f8807a, "");
        } else {
            HttpUtil.m14296b(this.f8807a, "");
        }
        CacheAccount.getInstance().clearCacheAccountPassword(str);
        CacheAuth.getInstance().resetPortalDisable();
        int lastLoginType = CacheAccount.getInstance().getLastLoginType();
        String lastLoginPhone = CacheAccount.getInstance().getLastLoginPhone();
        if (lastLoginType == 2) {
            Intent intent = new Intent(this, StatusLoginActivity.class);
            intent.putExtra(Constant.f13323i, lastLoginPhone);
            startActivity(intent);
        } else {
            Intent intent2 = new Intent(this, LoginThirdActivity.class);
            intent2.setFlags(268435456);
            intent2.putExtra(Constant.f13323i, lastLoginPhone);
            startActivity(intent2);
        }
        instance().exitOther();
    }

    public static void initImageLoader(Context context) {
        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(context).threadPriority(3).denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator()).diskCacheSize(WXVideoFileObject.FILE_SIZE_LIMIT).tasksProcessingOrder(QueueProcessingType.LIFO).build());
    }

    public void onLowMemory() {
        super.onLowMemory();
        showMessageToast("手机内存过低，请释放内存");
        exit();
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (configuration.fontScale != 1.0f) {
            getResources();
        }
        super.onConfigurationChanged(configuration);
    }

    public Resources getResources() {
        Resources resources = super.getResources();
        if (resources.getConfiguration().fontScale != 1.0f) {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }

    public SharedPreferences getSharedPreferences() {
        return getSharedPreferences(Constant.f13172a, 0);
    }

    public void addActivity(GBActivity gBActivity) {
        synchronized (this.f8808c) {
            this.f8808c.remove(gBActivity);
            this.f8808c.add(gBActivity);
        }
    }

    public void removeActivity(Activity activity) {
        synchronized (this.f8808c) {
            this.f8808c.remove(activity);
        }
    }

    /* renamed from: d */
    private void m10466d() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(268435456);
        intent.addCategory("android.intent.category.HOME");
        startActivity(intent);
    }

    public void exit() {
        CacheAuth.getInstance().setLastReleaseTime(0);
        synchronized (this.f8808c) {
            for (GBActivity gBActivity : this.f8808c) {
                gBActivity.finish();
            }
            this.f8808c.clear();
        }
        if (GBActivity.isRunModelChanged.booleanValue()) {
            ((ActivityManager) getSystemService("activity")).killBackgroundProcesses(getPackageName());
            m10466d();
        } else if (!GBActivity.isVersionCaching.booleanValue()) {
            ((ActivityManager) getSystemService("activity")).killBackgroundProcesses(getPackageName());
            m10466d();
        }
    }

    public List<GBActivity> getActivitiesList() {
        return this.f8808c;
    }

    public boolean isStarted() {
        boolean z = false;
        synchronized (this.f8808c) {
            if (this.f8808c != null && this.f8808c.size() > 0) {
                for (int i = 0; i < this.f8808c.size(); i++) {
                    Log.m14398b(f8805b, "activity " + i + ":" + this.f8808c.get(i).getActivityName());
                }
                z = true;
            }
        }
        return z;
    }

    public void showCurrentActivity(Context context) {
        Intent intent = new Intent(this, ShowActivity.class);
        intent.setFlags(268435456);
        startActivity(intent);
    }

    public boolean isExit() {
        return getCurrentActivity() == null;
    }

    public GBActivity getCurrentActivity() {
        GBActivity gBActivity;
        synchronized (this.f8808c) {
            if (this.f8808c == null || this.f8808c.size() <= 0) {
                gBActivity = null;
            } else {
                gBActivity = this.f8808c.get(this.f8808c.size() - 1);
            }
        }
        return gBActivity;
    }

    public MainActivity getCurrentMainActivity() {
        MainActivity mainActivity;
        synchronized (this.f8808c) {
            mainActivity = null;
            if (this.f8808c != null && this.f8808c.size() > 0) {
                int i = 0;
                while (true) {
                    if (i >= this.f8808c.size()) {
                        break;
                    } else if (this.f8808c.get(i) instanceof MainActivity) {
                        mainActivity = (MainActivity) this.f8808c.get(i);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        return mainActivity;
    }

    public HttpService getProtocolService(String str) {
        if (this.f8809e == null) {
            this.f8809e = new ServiceManager(this);
        }
        return this.f8809e.mo24387a(str);
    }

    public HttpService getProtocolServiceDeviceTest(String str) {
        if (this.f8809e == null) {
            this.f8809e = new ServiceManager(this);
        }
        return this.f8809e.mo24388b(str);
    }

    public void showMessageToast(String str) {
        Toast.makeText(this, str, 0).show();
    }

    public void onTerminate() {
        super.onTerminate();
        exit();
    }

    public void exitOther() {
        WebViewUtil.m14568a();
        synchronized (this.f8808c) {
            for (GBActivity gBActivity : this.f8808c) {
                gBActivity.finish();
            }
            this.f8808c.clear();
            if (ImageLoader.getInstance().isInited()) {
            }
            System.m38963gc();
        }
    }

    public void exit2Launch() {
        String str = getApplicationInfo().packageName;
        Log.m14398b(f8805b, "applicationId=" + str);
        synchronized (this.f8808c) {
            for (GBActivity gBActivity : this.f8808c) {
                gBActivity.finish();
            }
            this.f8808c.clear();
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage != null) {
                startActivity(launchIntentForPackage);
            }
        }
    }
}
