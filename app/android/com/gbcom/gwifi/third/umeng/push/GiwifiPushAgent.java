package com.gbcom.gwifi.third.umeng.push;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.p009v4.app.NotificationCompat;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.app.GBElementManager;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.loading.domain.NotifyWhenAppExit;
import com.gbcom.gwifi.functions.notify.NotifyListActivity;
import com.gbcom.gwifi.functions.notify.domain.Notify;
import com.gbcom.gwifi.functions.notify.p245a.NotifyDao;
import com.gbcom.gwifi.functions.notify.p246b.NotifyType;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.NotificationUtils;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import java.util.HashMap;
import p041c.Request;

public class GiwifiPushAgent {
    private static final int CUSTOM_NOTIFICATION_WHAT = 10;
    private static final String GIWIFI_NOTIFICATION_ACTION = "com.giwifi.noti.action";
    private static final int NOTIFICATION_ID = 100;
    private static final int OBJECT_BIG_ICON = 3;
    private static final int OBJECT_CONTEXT = 0;
    private static final int OBJECT_ICON = 2;
    private static final int OBJECT_UMSG = 1;
    private static final String REPORTTERMINALINFO = "reportterminalinfo";
    public static String TAG = "GiwifiPushAgent";
    public static GiwifiPushAgent instance;
    private static PushAgent mPushAgent;
    public static UMessage staticUMessage;
    private final String CLOSE = "关闭";
    private final String CONTACT = "联系我们";
    private final String DETAIL = "查看详情";
    private String UMessage;
    private Context context = null;
    private String deviceToken;
    private boolean isNotification = false;
    private String localAppUuid;
    private String localMac;
    private NotificationManager mNotificationManager = null;
    private Handler notificationHandler = new Handler() {
        /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.HandlerC34377 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 10:
                    GiwifiPushAgent.this.dealNotification(message);
                    return;
                default:
                    return;
            }
        }
    };
    public BroadcastReceiver notificationReceiver = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.C34355 */

        public void onReceive(Context context, Intent intent) {
            GiwifiPushAgent.this.mNotificationManager.cancel(100);
            GiwifiPushAgent.this.trackMsgDismissed();
        }
    };
    private OkRequestHandler<String> okReqHandler = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.C34366 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        public void onRequestFinish(Request abVar, String str) {
            if (GiwifiPushAgent.this.setDeviceTokenRequest == abVar) {
                if (ResultCode.m14476b(CommonMsg.deSerializeJson(str.getBytes()))) {
                    CacheApp.getInstance().setUmengPushMac(GiwifiPushAgent.this.localMac);
                    CacheApp.getInstance().setUmengPushRegisterId(GiwifiPushAgent.this.deviceToken);
                }
            } else if (abVar != GiwifiPushAgent.this.reportTerminalInfoRequest && abVar == GiwifiPushAgent.this.setAppSourceRequest) {
                CacheApp.getInstance().setUmengPushAppUuid(GiwifiPushAgent.this.localAppUuid);
                CacheApp.getInstance().setUmengPushRegisterId(GiwifiPushAgent.this.deviceToken);
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };
    private Request reportTerminalInfoRequest;
    private Dialog secondDialog;
    private Request setAppSourceRequest;
    private Request setDeviceTokenRequest;
    private int tempShowType = 0;

    public GiwifiPushAgent(Context context2) {
        this.context = context2;
        getPushAgent();
        register();
        setMessageHandler();
        setNotificationClickHandler();
    }

    public GiwifiPushAgent(Context context2, int i) {
        this.context = context2;
        getPushAgent();
    }

    public static GiwifiPushAgent getInstance(Context context2) {
        if (mPushAgent == null) {
            instance = null;
        }
        if (instance == null) {
            instance = new GiwifiPushAgent(context2);
        }
        return instance;
    }

    public void onDestroy() {
        if (this.secondDialog != null && this.secondDialog.isShowing()) {
            this.secondDialog.dismiss();
            this.secondDialog = null;
        }
    }

    public void onResume(Activity activity) {
        if (this.isNotification) {
            this.isNotification = false;
            Intent intent = new Intent(activity, NotifyListActivity.class);
            intent.putExtra("UMessage", this.UMessage);
            intent.addFlags(268435456);
            this.context.startActivity(intent);
        }
    }

    public void registerNotificationReceiver() {
        if (checkPushAgent()) {
            this.mNotificationManager = (NotificationManager) this.context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
            this.context.registerReceiver(this.notificationReceiver, new IntentFilter(GIWIFI_NOTIFICATION_ACTION));
        }
    }

    private boolean checkPushAgent() {
        if (mPushAgent == null) {
            return false;
        }
        return true;
    }

    public void getPushAgent() {
        mPushAgent = PushAgent.getInstance(this.context);
        if (this.context.getPackageName().contains("school") && mPushAgent != null) {
            mPushAgent.setResourcePackageName("com.gbcom.gwifi");
        }
    }

    public void setResourcePackageName(String str) {
        if (checkPushAgent()) {
            mPushAgent.setResourcePackageName(str);
        }
    }

    public void onAppStart() {
        if (checkPushAgent()) {
            mPushAgent.onAppStart();
        }
    }

    public void register() {
        if (checkPushAgent()) {
            mPushAgent.register(new IUmengRegisterCallback() {
                /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.C34231 */

                @Override // com.umeng.message.IUmengRegisterCallback
                public void onSuccess(String str) {
                    Log.m14400c(GiwifiPushAgent.TAG, "注册成功：deviceToken：-------->  " + str);
                }

                @Override // com.umeng.message.IUmengRegisterCallback
                public void onFailure(String str, String str2) {
                    Log.m14403e(GiwifiPushAgent.TAG, "注册失败：-------->  s:" + str + ",s1:" + str2);
                }
            });
        }
    }

    public void registerHandleToken() {
        if (checkPushAgent()) {
            mPushAgent.register(new IUmengRegisterCallback() {
                /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.C34312 */

                @Override // com.umeng.message.IUmengRegisterCallback
                public void onSuccess(String str) {
                    Log.m14400c(GiwifiPushAgent.TAG, "注册成功：deviceToken：-------->  " + str);
                    GiwifiPushAgent.this.updateStatus();
                }

                @Override // com.umeng.message.IUmengRegisterCallback
                public void onFailure(String str, String str2) {
                    Log.m14403e(GiwifiPushAgent.TAG, "注册失败：-------->  s:" + str + ",s1:" + str2);
                }
            });
        }
    }

    public void setMessageHandler() {
        if (checkPushAgent()) {
            mPushAgent.setMessageHandler(new UmengMessageHandler() {
                /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.C34323 */

                @Override // com.umeng.message.UmengMessageHandler
                public void dealWithCustomMessage(final Context context, final UMessage uMessage) {
                    new Handler(context.getMainLooper()).post(new Runnable() {
                        /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.C34323.RunnableC34331 */

                        @Override // java.lang.Runnable
                        public void run() {
                            GiwifiPushAgent.staticUMessage = uMessage;
                            UTrack.getInstance(context.getApplicationContext()).trackMsgClick(uMessage);
                            Log.m14398b(GiwifiPushAgent.TAG, Thread.currentThread().toString() + "-dealWithCustomMessage:" + uMessage.toString());
                            GiwifiPushAgent.this.processCustomMessage(context, new GiwifiMessage(uMessage.title, uMessage.text, uMessage.custom), true);
                        }
                    });
                }

                @Override // com.umeng.message.UmengMessageHandler
                public Notification getNotification(Context context, UMessage uMessage) {
                    GiwifiPushAgent.this.setNotificaitonOnForeground(true);
                    Log.m14398b(GiwifiPushAgent.TAG, Thread.currentThread().toString() + "-getNotification:" + uMessage.toString());
                    GiwifiPushAgent.this.processCustomMessage(context, new GiwifiMessage(uMessage.title, uMessage.text, uMessage.custom), false);
                    int i = uMessage.builder_id;
                    return super.getNotification(context, uMessage);
                }
            });
        }
    }

    public void setNotificationClickHandler() {
        if (checkPushAgent()) {
            mPushAgent.setNotificationClickHandler(new UmengNotificationClickHandler() {
                /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.C34344 */

                @Override // com.umeng.message.UHandler, com.umeng.message.UmengNotificationClickHandler
                public void handleMessage(Context context, UMessage uMessage) {
                    super.handleMessage(context, uMessage);
                }

                @Override // com.umeng.message.UmengNotificationClickHandler
                public void dealWithCustomAction(Context context, UMessage uMessage) {
                    if (uMessage != null) {
                        UTrack.getInstance(context.getApplicationContext()).trackMsgClick(uMessage);
                        Log.m14398b(GiwifiPushAgent.TAG, Thread.currentThread().toString() + "-dealWithCustomAction:" + uMessage.toString());
                        GiwifiPushAgent.this.processCustomNotification(new GiwifiMessage(uMessage.title, uMessage.text, uMessage.custom));
                    }
                }
            });
        }
    }

    public void setDisplayNotificationNumber(int i) {
        if (checkPushAgent()) {
            mPushAgent.setDisplayNotificationNumber(i);
        }
    }

    public void setNotificaitonOnForeground(boolean z) {
        if (checkPushAgent()) {
            mPushAgent.setNotificaitonOnForeground(z);
        }
    }

    public String getRegistrationId() {
        if (!checkPushAgent()) {
            return null;
        }
        return mPushAgent.getRegistrationId();
    }

    public void trackMsgDismissed() {
        UTrack.getInstance(this.context.getApplicationContext()).trackMsgDismissed(staticUMessage);
    }

    public void checkUnhandleMessage(Activity activity) {
        this.isNotification = activity.getIntent().getBooleanExtra(UMessage.DISPLAY_TYPE_NOTIFICATION, false);
        this.UMessage = activity.getIntent().getStringExtra("UMessage");
    }

    public void checkReportToken() {
        if (((!C3467s.m14513e(this.localAppUuid) && !CacheApp.getInstance().getUmengPushAppUuid().equals(this.localAppUuid)) || (!C3467s.m14513e(this.deviceToken) && !CacheApp.getInstance().getUmengPushRegisterId().equals(this.deviceToken))) && !C3467s.m14513e(CacheAccount.getInstance().getLoginPhone())) {
            this.setAppSourceRequest = HttpUtil.m14298b(this.deviceToken, this.okReqHandler, "");
        }
    }

    public void updateStatus() {
        if (checkPushAgent()) {
            this.deviceToken = getRegistrationId();
            Log.m14400c(TAG, "terminalInfo token:" + this.deviceToken);
            CacheApp.getInstance().setCacheUmengPushRegisterIdTemp(this.deviceToken);
            this.localAppUuid = CacheApp.getInstance().getAppUuid();
            checkReportToken();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void dealNotification(Message message) {
        int i;
        Intent intent;
        Object[] objArr = (Object[]) message.obj;
        Context context2 = (Context) objArr[0];
        GiwifiMessage giwifiMessage = (GiwifiMessage) objArr[1];
        Bitmap bitmap = (Bitmap) objArr[2];
        Bitmap bitmap2 = (Bitmap) objArr[3];
        RemoteViews remoteViews = new RemoteViews(context2.getApplicationContext().getPackageName(), (int) C2425R.layout.notification_view);
        remoteViews.setTextViewText(C2425R.C2427id.notification_title, giwifiMessage.title);
        remoteViews.setTextViewText(C2425R.C2427id.notification_text, giwifiMessage.text);
        if (bitmap != null) {
            remoteViews.setImageViewBitmap(C2425R.C2427id.notification_icon, bitmap);
        }
        remoteViews.setTextViewText(C2425R.C2427id.tv_custom_time, C3467s.m14500a());
        int i2 = NotificationUtils.m14438c(context2.getApplicationContext()) ? -1 : -16777216;
        if (i2 == -1) {
            i = -16777216;
        } else {
            i = -1;
        }
        remoteViews.setInt(C2425R.C2427id.notification_title, "setTextColor", i2);
        remoteViews.setInt(C2425R.C2427id.notification_text, "setTextColor", i2);
        remoteViews.setInt(C2425R.C2427id.tv_custom_time, "setTextColor", i2);
        remoteViews.setInt(C2425R.C2427id.notification_layout, "setBackgroundColor", i);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context2);
        builder.setContent(remoteViews).setTicker("您有新的消息").setDefaults(2).setPriority(0).setAutoCancel(true).setSmallIcon(C2425R.C2426drawable.icon);
        HashMap hashMap = (HashMap) JsonUtil.m14386a(giwifiMessage.custom, HashMap.class);
        if (GBApplication.instance() == null || !GBApplication.instance().isStarted()) {
            Intent intent2 = new Intent(GBApplication.instance(), MainActivity.class);
            intent2.setFlags(268435456);
            intent2.putExtra(UMessage.DISPLAY_TYPE_NOTIFICATION, true);
            intent2.putExtra("UMessage", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
            intent = intent2;
        } else {
            Intent intent3 = new Intent(GBApplication.instance(), NotifyListActivity.class);
            intent3.addFlags(268435456);
            intent3.putExtra("UMessage", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
            intent = intent3;
        }
        builder.setContentIntent(PendingIntent.getActivity(context2, 0, intent, 268435456));
        builder.setDeleteIntent(PendingIntent.getBroadcast(context2, 1, new Intent(GIWIFI_NOTIFICATION_ACTION), 268435456));
        Notification build = builder.build();
        if (Build.VERSION.SDK_INT >= 16) {
            if (bitmap2 != null) {
                remoteViews.setImageViewBitmap(C2425R.C2427id.notification_bigIcon, bitmap2);
                remoteViews.setViewVisibility(C2425R.C2427id.notification_bigIcon, 0);
            }
            build.bigContentView = remoteViews;
        } else {
            build.contentView = remoteViews;
        }
        this.mNotificationManager.notify(100, build);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void processCustomMessage(final Context context2, final GiwifiMessage giwifiMessage, boolean z) {
        HashMap hashMap;
        final String str = "";
        final String str2 = "";
        if (!(giwifiMessage == null || (hashMap = (HashMap) JsonUtil.m14386a(giwifiMessage.custom, HashMap.class)) == null)) {
            String str3 = (String) hashMap.get("title");
            String str4 = (String) hashMap.get("content");
            String obj = ((String) hashMap.get("wapUrl")) == null ? "" : hashMap.get("wapUrl").toString();
            String obj2 = ((String) hashMap.get(Constant.f13323i)) == null ? "" : hashMap.get(Constant.f13323i).toString();
            Integer valueOf = Integer.valueOf(((Integer) hashMap.get("type")) == null ? 0 : ((Integer) hashMap.get("type")).intValue());
            Long valueOf2 = Long.valueOf(hashMap.get("createTime") == null ? System.currentTimeMillis() : Long.parseLong(hashMap.get("createTime").toString()));
            Long valueOf3 = Long.valueOf(hashMap.get("productId") == null ? System.currentTimeMillis() : Long.parseLong(hashMap.get("productId").toString()));
            String obj3 = hashMap.get("msgId") == null ? "" + System.currentTimeMillis() : hashMap.get("msgId").toString();
            String obj4 = ((String) hashMap.get("productType")) == null ? "" : hashMap.get("productType").toString();
            String obj5 = ((String) hashMap.get("imageUrl")) == null ? "" : hashMap.get("imageUrl").toString();
            if (hashMap.containsKey("iconUrl")) {
                str = (String) hashMap.get("iconUrl");
            }
            if (hashMap.containsKey("bigIconUrl")) {
                str2 = (String) hashMap.get("bigIconUrl");
            }
            Integer valueOf4 = Integer.valueOf(((Integer) hashMap.get("showType")) == null ? 0 : Integer.parseInt(hashMap.get("showType").toString()));
            this.tempShowType = valueOf4.intValue();
            handleNotify(valueOf, valueOf4, str4, obj, str3, obj2, valueOf3, obj4, context2);
            NotifyType a = NotifyType.m11499a(valueOf.intValue());
            if (!a.equals(NotifyType.contentUpdate) && !a.equals(NotifyType.userOffline)) {
                Notify notify = new Notify();
                notify.setTitle(str3);
                notify.setContent(str4);
                notify.setWapUrl(obj);
                notify.setPhone(obj2);
                notify.setType(valueOf);
                notify.setMessageId(obj3);
                notify.setCreateTime(valueOf2);
                notify.setProductId(valueOf3);
                notify.setProductType(obj4);
                notify.setReceiveTime(Long.valueOf(System.currentTimeMillis()));
                notify.setRead(false);
                notify.setImageUrl(obj5);
                notify.setShowType(valueOf4);
                NotifyDao.m11495b().mo24215e((Context) GBApplication.instance(), notify);
                context2.sendBroadcast(new Intent(Constant.f13288cJ));
                context2.sendBroadcast(new Intent(Constant.f13287cI));
            }
            if (1 == this.tempShowType || a.equals(NotifyType.contentUpdate) || a.equals(NotifyType.userOffline)) {
                setNotificaitonOnForeground(false);
                return;
            }
        }
        if (z) {
            new Thread(new Runnable() {
                /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.RunnableC34388 */

                @Override // java.lang.Runnable
                public void run() {
                    Bitmap bitmap;
                    Bitmap bitmap2 = null;
                    if (!C3467s.m14513e(str)) {
                        bitmap = ImageTools.m14166d(str);
                    } else {
                        bitmap = null;
                    }
                    if (!C3467s.m14513e(str2)) {
                        bitmap2 = ImageTools.m14166d(str2);
                    }
                    GiwifiPushAgent.this.notificationHandler.removeMessages(10);
                    Message obtain = Message.obtain();
                    obtain.what = 10;
                    obtain.obj = new Object[]{context2, giwifiMessage, bitmap, bitmap2};
                    GiwifiPushAgent.this.notificationHandler.sendMessage(obtain);
                }
            }).start();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void processCustomNotification(GiwifiMessage giwifiMessage) {
        HashMap hashMap = (HashMap) JsonUtil.m14386a(giwifiMessage.custom, HashMap.class);
        if (GBApplication.instance() == null || !GBApplication.instance().isStarted()) {
            Intent intent = new Intent(GBApplication.instance(), MainActivity.class);
            intent.setFlags(268435456);
            intent.putExtra("UMessage", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
            intent.putExtra(UMessage.DISPLAY_TYPE_NOTIFICATION, true);
            GBApplication.instance().startActivity(intent);
            return;
        }
        Intent intent2 = new Intent(GBApplication.instance(), NotifyListActivity.class);
        intent2.putExtra("UMessage", JsonUtil.m14388a((HashMap<String, Object>) hashMap));
        intent2.addFlags(268435456);
        GBApplication.instance().startActivity(intent2);
    }

    public void handleDialog(String str, String str2, String str3, String str4, Long l, String str5, Integer num) {
        if (!str.equals("关闭")) {
            if (str.equals("查看详情") && num.intValue() == 0) {
                GBActivity.openBackWebView(str2, str3);
            } else if (str.equals("联系我们")) {
                GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
                if (currentActivity != null) {
                    currentActivity.callPhone(str4);
                }
            } else if (str.equals("查看详情") && num.intValue() == 1) {
                GBElementManager.m10471a();
                GBElementManager.m10474a(this.context, null, str5, l.intValue());
            }
        }
    }

    private void handleNotifyByType(Integer num, Integer num2, final String str, final String str2, final String str3, final String str4, final Long l, final String str5, Context context2, final String str6, final Integer num3) {
        if (num2.intValue() == 1) {
            final GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
            if (currentActivity == null) {
                String loginPhone = CacheAccount.getInstance().getLoginPhone();
                if (loginPhone != null && !loginPhone.equals("")) {
                    NotifyWhenAppExit notifyWhenAppExit = new NotifyWhenAppExit();
                    notifyWhenAppExit.setHasDialog(true);
                    notifyWhenAppExit.setType(num);
                    notifyWhenAppExit.setContent(str);
                    notifyWhenAppExit.setWapUrl(str2);
                    notifyWhenAppExit.setTitle(str3);
                    notifyWhenAppExit.setPhone(str4);
                    notifyWhenAppExit.setProductId(l);
                    notifyWhenAppExit.setProductType(str5);
                    notifyWhenAppExit.setReminder(str6);
                    notifyWhenAppExit.setSort(num3);
                    CacheApp.getInstance().setOfflineNotify(notifyWhenAppExit);
                    return;
                }
                return;
            }
            currentActivity.runOnUiThread(new Runnable() {
                /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.RunnableC34399 */

                @Override // java.lang.Runnable
                public void run() {
                    String loginPhone = CacheAccount.getInstance().getLoginPhone();
                    if (loginPhone != null && !loginPhone.equals("")) {
                        currentActivity.showSecondDialog("通知", str, str6, new GBActivity.AbstractC2517a() {
                            /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.RunnableC34399.C34401 */

                            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                            public void onClick(Dialog dialog, View view) {
                                dialog.dismiss();
                                GiwifiPushAgent.this.handleDialog(str6, str2, str3, str4, l, str5, num3);
                            }
                        }, new DialogInterface.OnCancelListener() {
                            /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.RunnableC34399.DialogInterface$OnCancelListenerC34412 */

                            public void onCancel(DialogInterface dialogInterface) {
                            }
                        });
                    }
                }
            });
        }
    }

    private void handleNotify(Integer num, Integer num2, String str, String str2, String str3, String str4, Long l, String str5, final Context context2) {
        switch (NotifyType.m11499a(num.intValue())) {
            case text:
                handleNotifyByType(num, num2, str, str2, str3, str4, l, str5, context2, "关闭", 0);
                return;
            case wap:
                handleNotifyByType(num, num2, str, str2, str3, str4, l, str5, context2, "查看详情", 0);
                return;
            case dialup:
                handleNotifyByType(num, num2, str, str2, str3, str4, l, str5, context2, "联系我们", 0);
                return;
            case versionUpdate:
            default:
                return;
            case productRecommend:
                handleNotifyByType(num, num2, str, str2, str3, str4, l, str5, context2, "查看详情", 1);
                return;
            case charge:
                context2.sendBroadcast(new Intent(Constant.f13233bH));
                handleNotifyByType(num, num2, str, str2, str3, str4, l, str5, context2, "查看详情", 0);
                return;
            case contentUpdate:
                context2.sendBroadcast(new Intent(Constant.f13240bO));
                return;
            case authOnline:
                context2.sendBroadcast(new Intent(Constant.f13241bP));
                handleNotifyByType(num, num2, str, str2, str3, str4, l, str5, context2, "关闭", 0);
                return;
            case pointChange:
                handleNotifyByType(num, num2, str, str2, str3, str4, l, str5, context2, "关闭", 0);
                return;
            case userOffline:
                if (GBApplication.instance().getCurrentActivity() != null) {
                    GBApplication.instance().getCurrentActivity().runOnUiThread(new Runnable() {
                        /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.RunnableC342410 */

                        @Override // java.lang.Runnable
                        public void run() {
                            String loginPhone = CacheAccount.getInstance().getLoginPhone();
                            if (loginPhone != null && !loginPhone.equals("")) {
                                Intent intent = new Intent(Constant.f13290cL);
                                intent.addCategory(context2.getPackageName());
                                context2.sendBroadcast(intent);
                            }
                        }
                    });
                    return;
                }
                String loginPhone = CacheAccount.getInstance().getLoginPhone();
                if (loginPhone != null && !loginPhone.equals("")) {
                    new Handler(context2.getMainLooper()).post(new Runnable() {
                        /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.RunnableC342511 */

                        @Override // java.lang.Runnable
                        public void run() {
                            Intent intent = new Intent(Constant.f13290cL);
                            intent.addCategory(context2.getPackageName());
                            context2.sendBroadcast(intent);
                        }
                    });
                    return;
                }
                return;
        }
    }

    private void showThirdDialog(String str, String str2, String str3, final GBActivity.AbstractC2517a aVar, DialogInterface.OnCancelListener onCancelListener) {
        if (this.secondDialog != null && this.secondDialog.isShowing()) {
            this.secondDialog.dismiss();
            this.secondDialog = null;
        }
        if (GBApplication.instance().getCurrentActivity() != null) {
            this.secondDialog = new Dialog(GBApplication.instance().getCurrentActivity(), C2425R.C2430style.login_dialog);
            View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.common_dialog, (ViewGroup) null);
            ((ImageView) inflate.findViewById(C2425R.C2427id.cancl_bt)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.View$OnClickListenerC342612 */

                public void onClick(View view) {
                    if (GiwifiPushAgent.this.secondDialog != null) {
                        GiwifiPushAgent.this.secondDialog.dismiss();
                    }
                }
            });
            Button button = (Button) inflate.findViewById(C2425R.C2427id.add_bt);
            View inflate2 = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.common_dialog_content, (ViewGroup) null);
            TextView textView = (TextView) inflate2.findViewById(2131755301);
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
            textView.setText(str2);
            ((TextView) inflate2.findViewById(C2425R.C2427id.dialog_title)).setText(str);
            ((LinearLayout) inflate.findViewById(C2425R.C2427id.view_layout)).addView(inflate2, -1, -2);
            button.setText(str3);
            button.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.View$OnClickListenerC342713 */

                public void onClick(View view) {
                    if (aVar != null) {
                        if (GiwifiPushAgent.this.secondDialog != null) {
                            aVar.onClick(GiwifiPushAgent.this.secondDialog, view);
                        }
                    } else if (GiwifiPushAgent.this.secondDialog != null) {
                        GiwifiPushAgent.this.secondDialog.dismiss();
                    }
                }
            });
            this.secondDialog.setContentView(inflate, new ViewGroup.LayoutParams((int) GBApplication.instance().getResources().getDimension(C2425R.dimen.dialog_width), -2));
            if (onCancelListener != null) {
                this.secondDialog.setOnCancelListener(onCancelListener);
            }
            this.secondDialog.show();
        }
    }

    private void handleNotifyWhenAppExist(boolean z, final NotifyWhenAppExit notifyWhenAppExit) {
        if (z) {
            showThirdDialog("通知", notifyWhenAppExit.getContent(), notifyWhenAppExit.getReminder(), new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.C342814 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    GiwifiPushAgent.this.handleDialog(notifyWhenAppExit.getReminder(), notifyWhenAppExit.getWapUrl(), notifyWhenAppExit.getTitle(), notifyWhenAppExit.getPhone(), notifyWhenAppExit.getProductId(), notifyWhenAppExit.getProductType(), notifyWhenAppExit.getSort());
                    dialog.dismiss();
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent.DialogInterface$OnCancelListenerC342915 */

                public void onCancel(DialogInterface dialogInterface) {
                }
            });
        }
    }

    public void handleNotifyWhenAppExist(NotifyWhenAppExit notifyWhenAppExit) {
        boolean isHasDialog = notifyWhenAppExit.isHasDialog();
        switch (NotifyType.m11499a(notifyWhenAppExit.getType().intValue())) {
            case text:
                handleNotifyWhenAppExist(isHasDialog, notifyWhenAppExit);
                return;
            case wap:
                handleNotifyWhenAppExist(isHasDialog, notifyWhenAppExit);
                return;
            case dialup:
                handleNotifyWhenAppExist(isHasDialog, notifyWhenAppExit);
                return;
            case versionUpdate:
            case contentUpdate:
            case userOffline:
            default:
                return;
            case productRecommend:
                handleNotifyWhenAppExist(isHasDialog, notifyWhenAppExit);
                return;
            case charge:
                handleNotifyWhenAppExist(isHasDialog, notifyWhenAppExit);
                return;
            case authOnline:
                handleNotifyWhenAppExist(isHasDialog, notifyWhenAppExit);
                return;
            case pointChange:
                handleNotifyWhenAppExist(isHasDialog, notifyWhenAppExit);
                return;
        }
    }
}
