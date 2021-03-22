package com.gbcom.gwifi.base.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.CheckUtil;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.umeng.message.entity.UMessage;
import java.util.Timer;
import java.util.TimerTask;
import libcore.icu.RelativeDateTimeFormatter;
import p041c.Request;

public class CheckNetworkService extends Service {

    /* renamed from: b */
    public static final String f8947b = "giwifi001";

    /* renamed from: c */
    private static final String f8948c = "CheckNetworkService";

    /* renamed from: e */
    private static Context f8949e = null;

    /* renamed from: h */
    private static Thread f8950h = null;

    /* renamed from: k */
    private static final int f8951k = 1001;

    /* renamed from: a */
    PendingIntent f8952a = null;

    /* renamed from: d */
    private int f8953d = 0;

    /* renamed from: f */
    private Notification f8954f;

    /* renamed from: g */
    private Context f8955g;

    /* renamed from: i */
    private MediaPlayer f8956i;

    /* renamed from: j */
    private boolean f8957j = true;

    /* renamed from: l */
    private WifiManager f8958l;

    /* renamed from: m */
    private NotificationManager f8959m;

    /* renamed from: n */
    private Timer f8960n = new Timer();

    /* renamed from: o */
    private TimerTask f8961o = new TimerTask() {
        /* class com.gbcom.gwifi.base.service.CheckNetworkService.C25691 */

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Log.m14398b(CheckNetworkService.f8948c, "CHECK_NET_WORK timer running");
            CheckNetworkService.this.sendBroadcast(new Intent(Constant.f13289cK));
        }
    };

    /* renamed from: p */
    private OkRequestHandler<String> f8962p = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.base.service.CheckNetworkService.C25702 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (abVar == CheckNetworkService.this.f8963q) {
                Log.m14398b(CheckNetworkService.f8948c, "wifiChange send notify to gateway failed");
                GBActivity.showMessageToast("Mac变化同步失败，请检查网络");
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == CheckNetworkService.this.f8963q) {
                Log.m14398b(CheckNetworkService.f8948c, "wifiChange send notify to gateway succeed");
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: q */
    private Request f8963q;

    /* renamed from: r */
    private BroadcastReceiver f8964r = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.base.service.CheckNetworkService.C25713 */

        public void onReceive(Context context, Intent intent) {
            if (GBGlobalConfig.m10510c().mo24400a(CheckNetworkService.this.getApplicationContext())) {
                Log.m14400c(CheckNetworkService.f8948c, "wifiChange2Receiver isAppOnForeground");
            } else if (intent == null || intent.getAction() == null) {
                Log.m14400c(CheckNetworkService.f8948c, "wifiChange2Receiver intent null");
            } else if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
                NetworkInfo.DetailedState detailedState = ((NetworkInfo) intent.getParcelableExtra("networkInfo")).getDetailedState();
                Log.m14400c(CheckNetworkService.f8948c, "detailedState:" + ((Object) detailedState));
                switch (C25724.f8968a[detailedState.ordinal()]) {
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    default:
                        return;
                    case 2:
                        if (CheckNetworkService.this.f8958l.getConnectionInfo() == null) {
                            Log.m14400c(CheckNetworkService.f8948c, "wifiChange2Receiver wifiInfo null");
                            return;
                        }
                        String trim = WiFiUtil.m14035e(GBApplication.instance().getApplicationContext()).trim();
                        if (CheckUtil.m14084b(trim) && !trim.equals("00:00:00:00:00:00") && !trim.equals(WifiInfo.DEFAULT_MAC_ADDRESS)) {
                            String localMac = CacheAuth.getInstance().getLocalMac();
                            if (localMac.equals("")) {
                                Log.m14398b(CheckNetworkService.f8948c, "wifiChange CONNECTED empty=>setLocalMac");
                                CacheAuth.getInstance().setLocalMac2(trim);
                                return;
                            } else if (!trim.toUpperCase().equals(localMac)) {
                                Log.m14398b(CheckNetworkService.f8948c, "wifiChange CONNECTED change=>setLocalMac");
                                GBGlobalConfig.f8851b = true;
                                CacheAuth.getInstance().setLocalMac2(trim);
                                if (!CacheAuth.getInstance().checkGameIpNetMask()) {
                                    Log.m14398b(CheckNetworkService.f8948c, "checkGameIpNetMask false");
                                    return;
                                } else if (CacheAuth.getInstance().isPortal()) {
                                    Log.m14398b(CheckNetworkService.f8948c, "checkGameIpNetMask true,syncAuthState to aaa");
                                    CheckNetworkService.this.f8963q = HttpUtil.m14328i(CheckNetworkService.this.f8962p, "");
                                    return;
                                } else {
                                    Log.m14398b(CheckNetworkService.f8948c, "checkGameIpNetMask true,syncAuthState to gw");
                                    CheckNetworkService.this.f8963q = HttpUtil.m14305c(CheckNetworkService.this.f8962p, "");
                                    return;
                                }
                            } else {
                                Log.m14398b(CheckNetworkService.f8948c, "mac not change");
                                return;
                            }
                        } else if (GBGlobalConfig.f8851b || !CheckUtil.m14084b(trim) || trim.equals("00:00:00:00:00:00") || trim.equals(WifiInfo.DEFAULT_MAC_ADDRESS)) {
                            Log.m14398b(CheckNetworkService.f8948c, "wifiChange CONNECTED clearLocalMac");
                            CacheAuth.getInstance().clearLocalMac();
                            return;
                        } else {
                            return;
                        }
                }
            }
        }
    };

    /* renamed from: com.gbcom.gwifi.base.service.CheckNetworkService$4 */
    static /* synthetic */ class C25724 {

        /* renamed from: a */
        static final /* synthetic */ int[] f8968a = new int[NetworkInfo.DetailedState.values().length];

        static {
            try {
                f8968a[NetworkInfo.DetailedState.AUTHENTICATING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8968a[NetworkInfo.DetailedState.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8968a[NetworkInfo.DetailedState.CONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f8968a[NetworkInfo.DetailedState.DISCONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f8968a[NetworkInfo.DetailedState.DISCONNECTING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f8968a[NetworkInfo.DetailedState.FAILED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f8968a[NetworkInfo.DetailedState.IDLE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f8968a[NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f8968a[NetworkInfo.DetailedState.SUSPENDED.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    /* renamed from: b */
    private void m10614b() {
        unregisterReceiver(this.f8964r);
    }

    public void onCreate() {
        Log.m14400c(f8948c, "onCreate");
        f8949e = GBApplication.instance();
        this.f8958l = (WifiManager) GBApplication.instance().getApplicationContext().getSystemService("wifi");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.NETWORK_IDS_CHANGED_ACTION);
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.f8964r, intentFilter);
        this.f8959m = (NotificationManager) GBApplication.instance().getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
        this.f8960n.schedule(this.f8961o, RelativeDateTimeFormatter.MINUTE_IN_MILLIS, RelativeDateTimeFormatter.MINUTE_IN_MILLIS);
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Log.m14400c(f8948c, "onStartCommand");
        this.f8955g = GBApplication.instance().getApplicationContext();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8959m.createNotificationChannel(new NotificationChannel(f8947b, this.f8955g.getString(2131296409), 4));
            startForeground(1, new Notification.Builder(getApplicationContext(), f8947b).build());
        } else {
            startForeground(100, new Notification.Builder(this.f8955g).setSmallIcon(C2425R.C2426drawable.icon).setWhen(System.currentTimeMillis()).setTicker(this.f8955g.getString(2131296409)).setContentTitle(this.f8955g.getString(2131296409)).setContentText("点击查看").setOngoing(true).setPriority(2).setContentIntent(PendingIntent.getActivity(this.f8955g, 0, new Intent(this.f8955g, MainActivity.class), 134217728)).setAutoCancel(false).build());
        }
        if (this.f8956i == null) {
            this.f8956i = MediaPlayer.create(this.f8955g, (int) C2425R.C2428raw.no_notice);
            this.f8956i.setLooping(true);
            this.f8956i.start();
        }
        return 1;
    }

    public IBinder onBind(Intent intent) {
        Log.m14400c(f8948c, "onBind");
        return null;
    }

    public void onDestroy() {
        Log.m14400c(f8948c, "onDestroy");
        m10614b();
        this.f8961o.cancel();
        this.f8960n.cancel();
        this.f8957j = false;
        stopForeground(true);
        if (this.f8956i == null) {
            this.f8956i.release();
        }
        stopSelf();
        super.onDestroy();
        Intent intent = new Intent(getApplicationContext(), CheckNetworkService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    /* renamed from: a */
    public static Context m10610a() {
        return f8949e;
    }

    public class GrayInnerService extends Service {
        public GrayInnerService() {
        }

        public IBinder onBind(Intent intent) {
            return null;
        }

        public int onStartCommand(Intent intent, int i, int i2) {
            startForeground(1001, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, i, i2);
        }
    }
}
