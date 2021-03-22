package com.gbcom.gwifi.base.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.StatsManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.p009v4.app.ActivityCompat;
import android.support.p009v4.app.FragmentActivity;
import android.support.p009v4.app.NotificationCompat;
import android.support.p009v4.content.ContextCompat;
import android.support.p009v4.content.FileProvider;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.functions.loading.DialogActivity;
import com.gbcom.gwifi.functions.loading.LaunchActivity;
import com.gbcom.gwifi.functions.loading.LoginErrorActivity;
import com.gbcom.gwifi.functions.product.AppDownloadActivity;
import com.gbcom.gwifi.functions.webview.BackX5WebViewActivity;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.functions.wifi.entity.AuthInfo;
import com.gbcom.gwifi.p221a.p223b.HttpResponse;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.C3470v;
import com.gbcom.gwifi.util.CheckUtil;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Config;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.NetworkUtils;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.URLUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.util.p257b.StatusBarUtil;
import com.tencent.smtt.sdk.TbsMediaPlayer;
import com.tencent.smtt.sdk.TbsReaderView;
import com.tencent.smtt.sdk.WebView;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.bean.HandlerRequestCode;
import java.lang.reflect.Field;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.p456io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.xpath.compiler.PsuedoNames;
import p041c.Request;

public class GBActivity extends FragmentActivity {
    public static final String BUSINESS_CONSULTATION = "业务咨询";
    public static final String COMPLAINTS_HOTLINE = "投诉电话";
    private static final int DOWNLOAD_VERSION_CANCEL = 3;
    private static final int DOWNLOAD_VERSION_FAILED = 1;
    private static final int DOWNLOAD_VERSION_FINISH = 2;
    private static final int DOWNLOAD_VERSION_PROGRESS = 0;
    private static final int NOTIFICATION_DOWNLOAD = 4;
    public static final String ONLINE_SERVICE = "在线客服";
    private static final String TAG = "GBActivity";
    public static final int TITLE_TYPE_ARROW = 3;
    public static final int TITLE_TYPE_BUTTON_SEARCH = 5;
    public static final int TITLE_TYPE_DOUBLE_LINE = 6;
    public static final int TITLE_TYPE_DOUBLE_LINE_PROGRESS = 7;
    public static final int TITLE_TYPE_EDIT_SEARCH = 4;
    public static final int TITLE_TYPE_NONE = 2;
    public static final int TITLE_TYPE_WIDE = 1;
    private static final int TOAST = 8;
    private static String apkFilePath;
    private static boolean cancelUpdate = true;
    private static Request checkAppCustomRequest;
    private static Request checkVersionHiddenRequest;
    private static Request checkVersionRequest;
    private static Dialog commonDialog;
    private static int curVersionCode;
    private static Dialog dialogAd;
    public static boolean isCheckVersion = false;
    public static Boolean isReallyMust = false;
    private static boolean isResetTime = false;
    public static Boolean isRunModelChanged = false;
    public static Boolean isVersionCaching = false;
    private static NotificationCompat.Builder mBuilder;
    private static String mSavePath;
    public static Toast mToast = null;
    private static NotificationManager mnNotificationManager;
    private static PackageInfo packageInfo = null;
    private static ProgressDialog proDialog;
    private static ProgressBar progressBar;
    private static TextView progressLeftText;
    private static TextView progressRightText;
    private static Dialog secondDialog;
    private static Dialog thirdDialog;
    private static Handler uiHandler = new Handler() {
        /* class com.gbcom.gwifi.base.activity.GBActivity.HandlerC24873 */

        public void handleMessage(Message message) {
            GBActivity currentActivity;
            switch (message.what) {
                case 0:
                    long[] jArr = (long[]) message.obj;
                    GBActivity.setCommonProgress(jArr[0], jArr[1]);
                    GBActivity.uiHandler.sendMessage(GBActivity.uiHandler.obtainMessage(4, Integer.valueOf((int) ((jArr[0] * 100) / jArr[1]))));
                    return;
                case 1:
                    boolean unused = GBActivity.cancelUpdate = true;
                    GBActivity.dismissNormalProgress();
                    GBActivity.mnNotificationManager.cancel(0);
                    GBActivity.showMessageToast("很抱歉，getUserAgentInfo");
                    if (GBActivity.isReallyMust.booleanValue() && (currentActivity = GBApplication.instance().getCurrentActivity()) != null) {
                        currentActivity.exitApp();
                        return;
                    }
                    return;
                case 2:
                    GBActivity.dismissNormalProgress();
                    GBActivity.mBuilder.setContentText("下载已完成").setContentInfo("");
                    GBActivity.mBuilder.setProgress(0, 0, false).setOngoing(false);
                    Object[] objArr = (Object[]) message.obj;
                    if (((Boolean) objArr[1]).booleanValue()) {
                        HashMap<String, Object> hashMap = (HashMap) objArr[2];
                        hashMap.put(TbsReaderView.KEY_FILE_PATH, objArr[0].toString());
                        CacheApp.getInstance().setDownloadVersionBackground(hashMap);
                        return;
                    }
                    boolean unused2 = GBActivity.cancelUpdate = true;
                    GBActivity.mnNotificationManager.notify(0, GBActivity.mBuilder.build());
                    GBActivity.installApk(objArr[0].toString());
                    return;
                case 3:
                    GBActivity.showMessageToast("已取消");
                    GBActivity.mnNotificationManager.cancel(0);
                    return;
                case 4:
                    int intValue = ((Integer) message.obj).intValue();
                    GBActivity.mBuilder.setProgress(100, intValue, false).setContentInfo(intValue + "%");
                    GBActivity.mnNotificationManager.notify(0, GBActivity.mBuilder.build());
                    return;
                case 5:
                case 6:
                case 7:
                default:
                    return;
                case 8:
                    if (GBActivity.mToast == null) {
                        GBActivity.mToast = Toast.makeText(GBApplication.instance(), (String) message.obj, 0);
                    } else {
                        GBActivity.mToast.setText((String) message.obj);
                        GBActivity.mToast.setDuration(0);
                    }
                    GBActivity.mToast.show();
                    return;
            }
        }
    };
    private static HashMap<String, Object> versionData;
    private static Dialog versionDialog;
    private static Dialog webDialog;
    private String ActivityName = "";
    private boolean finishing = false;
    private boolean hasTitle = false;
    private boolean isActive = true;
    private AtomicBoolean isDestroy = new AtomicBoolean(false);
    protected boolean isShowPermissionsDialog = false;
    protected View leftTitleButton;
    protected long mExitTime;
    protected Dialog managedDialog;
    protected int managedDialogId = 0;
    private OkRequestHandler<byte[]> okReqHandler = new OkRequestHandler<byte[]>() {
        /* class com.gbcom.gwifi.base.activity.GBActivity.C25095 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            GBActivity.this.showProgressDialog("正在检查版本...");
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            GBActivity.this.dismissProgressDialog();
            if (abVar == GBActivity.checkVersionRequest) {
                GBActivity.showMessageToast("升级失败，请检查网络");
            } else if (abVar == GBActivity.checkVersionHiddenRequest) {
                GBActivity.isCheckVersion = false;
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, byte[] bArr) {
            boolean booleanValue;
            String str;
            GBActivity.this.dismissProgressDialog();
            CommonMsg deSerializeJson = CommonMsg.deSerializeJson(bArr);
            if (deSerializeJson == null) {
                if (abVar != GBActivity.checkVersionHiddenRequest) {
                }
                return;
            }
            boolean z = false;
            if (deSerializeJson == null) {
                z = true;
            } else if (ResultCode.m14476b(deSerializeJson)) {
                z = false;
            }
            if (!z) {
                CacheApp.getInstance().setLastAutoCheckVersionTime(System.currentTimeMillis());
                if (abVar == GBActivity.checkVersionRequest) {
                    GBActivity.this.dismissProgressDialog();
                    HashMap unused = GBActivity.versionData = (HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class);
                    if (GBActivity.versionData == null) {
                        if (!Config.m14094a().mo27812o() || !Config.m14094a().mo27811n()) {
                            GBActivity.showMessageToast("当前已经是最新版本");
                        }
                        GBActivity.this.dismissProgressDialog();
                        return;
                    }
                    GBActivity.isReallyMust = (Boolean) GBActivity.versionData.get("isMust");
                    if (GBActivity.isReallyMust == null) {
                        booleanValue = false;
                    } else {
                        booleanValue = GBActivity.isReallyMust.booleanValue();
                    }
                    GBActivity.isReallyMust = Boolean.valueOf(booleanValue);
                    final Integer num = (Integer) GBActivity.versionData.get("versionCode");
                    String obj = GBActivity.versionData.get("versionName").toString();
                    final long parseLong = Long.parseLong(GBActivity.versionData.get("versionFileSize").toString());
                    final HashMap hashMap = new HashMap();
                    hashMap.put("isReallyMust", GBActivity.isReallyMust);
                    hashMap.put("versionCode", num);
                    hashMap.put("versionName", obj);
                    hashMap.put("versionContent", (String) GBActivity.versionData.get("versionContent"));
                    if (GBActivity.this.checkNeedDownloadVersion(num.intValue())) {
                        String str2 = (String) GBActivity.versionData.get("versionContent");
                        GBActivity gBActivity = GBActivity.this;
                        String str3 = "最新版本：" + GBActivity.versionData.get("versionName").toString();
                        if (GBActivity.isReallyMust.booleanValue()) {
                            str = "退出应用";
                        } else {
                            str = "稍后更新";
                        }
                        gBActivity.showVersionDialog(str3, str2, "立即更新", str, new AbstractC2517a() {
                            /* class com.gbcom.gwifi.base.activity.GBActivity.C25095.C25101 */

                            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                            public void onClick(Dialog dialog, View view) {
                                dialog.dismiss();
                                HashMap downloadVersionBackground = CacheApp.getInstance().getDownloadVersionBackground();
                                if (downloadVersionBackground != null && downloadVersionBackground.containsKey("versionCode") && num.equals(downloadVersionBackground.get("versionCode")) && GBActivity.installApkBack(downloadVersionBackground.get(TbsReaderView.KEY_FILE_PATH).toString())) {
                                    return;
                                }
                                if (CacheAuth.getInstance().getGwSn().equalsIgnoreCase(AuthInfo.DEFAULT_SN) || GBActivity.versionData.get("localDownloadUrl") == null || GBActivity.versionData.get("localDownloadUrl").toString().length() <= 0) {
                                    GBActivity.this.downloadVersion(GBActivity.versionData.get("md5").toString(), GBActivity.versionData.get("downloadUrl").toString(), parseLong);
                                } else {
                                    GBActivity.this.downloadVersion(GBActivity.versionData.get("md5").toString(), GBActivity.versionData.get("localDownloadUrl").toString(), parseLong);
                                }
                            }
                        }, new AbstractC2517a() {
                            /* class com.gbcom.gwifi.base.activity.GBActivity.C25095.C25112 */

                            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                            public void onClick(Dialog dialog, View view) {
                                dialog.dismiss();
                                if (GBActivity.isReallyMust.booleanValue()) {
                                    GBActivity.this.exitApp();
                                }
                                GBActivity.this.downloadNewVersion(hashMap, parseLong, num, true);
                            }
                        }, GBActivity.isReallyMust.booleanValue(), new DialogInterface.OnCancelListener() {
                            /* class com.gbcom.gwifi.base.activity.GBActivity.C25095.DialogInterface$OnCancelListenerC25123 */

                            public void onCancel(DialogInterface dialogInterface) {
                                GBActivity.this.downloadNewVersion(hashMap, parseLong, num, true);
                            }
                        });
                    } else if (!Config.m14094a().mo27812o() || !Config.m14094a().mo27811n()) {
                        GBActivity.showMessageToast("当前已经是最新版本");
                    }
                    GBActivity.this.dismissProgressDialog();
                } else if (abVar == GBActivity.checkVersionHiddenRequest) {
                    GBActivity.isCheckVersion = false;
                    HashMap unused2 = GBActivity.versionData = (HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class);
                    if (GBActivity.versionData != null) {
                        Integer num2 = (Integer) GBActivity.versionData.get("versionCode");
                        GBActivity.isReallyMust = (Boolean) GBActivity.versionData.get("isMust");
                        GBActivity.isReallyMust = Boolean.valueOf(GBActivity.isReallyMust == null ? false : GBActivity.isReallyMust.booleanValue());
                        String obj2 = GBActivity.versionData.get("versionName").toString();
                        long parseLong2 = Long.parseLong(GBActivity.versionData.get("versionFileSize").toString());
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("isReallyMust", GBActivity.isReallyMust);
                        hashMap2.put("versionCode", num2);
                        hashMap2.put("versionName", obj2);
                        hashMap2.put("versionContent", (String) GBActivity.versionData.get("versionContent"));
                        if (GBActivity.this.checkNeedDownloadVersion(num2.intValue())) {
                            String str4 = (String) GBActivity.versionData.get("versionContent");
                            GBActivity.this.downloadNewVersion(hashMap2, parseLong2, num2, false);
                        }
                    }
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };
    private OkRequestHandler<String> okReqStrHandler = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.base.activity.GBActivity.C24984 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (abVar == GBActivity.checkAppCustomRequest) {
                GBActivity.this.switchIcon(CacheAccount.getInstance().getHotspotGroupIdShow().intValue(), 0, false);
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (abVar == GBActivity.checkAppCustomRequest) {
                GBActivity.this.processCheckAppCustomResponse(str);
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };
    protected ImageButton rightLeftTitleButton;
    protected View rightTitleButton;
    protected String sequenceAction;
    protected TextView subtitleText;
    protected Button titleButton;
    protected TextView titleText;

    /* renamed from: com.gbcom.gwifi.base.activity.GBActivity$a */
    public interface AbstractC2517a {
        void onClick(Dialog dialog, View view);
    }

    /* renamed from: com.gbcom.gwifi.base.activity.GBActivity$b */
    public interface AbstractC2518b {
        /* renamed from: a */
        void mo24345a(Dialog dialog, View view);
    }

    /* renamed from: com.gbcom.gwifi.base.activity.GBActivity$c */
    public interface AbstractC2519c {
        /* renamed from: a */
        void mo24346a(Dialog dialog, View view);
    }

    /* renamed from: com.gbcom.gwifi.base.activity.GBActivity$d */
    public interface AbstractC2520d {
        /* renamed from: a */
        void mo24347a(Dialog dialog, View view);
    }

    /* renamed from: com.gbcom.gwifi.base.activity.GBActivity$e */
    public interface AbstractC2521e {
        /* renamed from: a */
        void mo24348a(Dialog dialog, View view);
    }

    /* renamed from: com.gbcom.gwifi.base.activity.GBActivity$f */
    public enum EnumC2522f {
        NO_BAR,
        BAR
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.m14400c(TAG, this.ActivityName + "  onCreate");
        initCustomTitle();
        initBackBtn();
        this.isDestroy.set(false);
        GiwifiPushAgent.getInstance(GBApplication.instance()).onAppStart();
    }

    public void onCreate(Bundle bundle, String str, int i, boolean z) {
        if (z) {
            setHasTitle(z);
        }
        setActivityName(str);
        super.onCreate(bundle);
        Log.m14400c(TAG, this.ActivityName + "  onCreate");
        initCustomTitle();
        initBackBtn();
        this.isDestroy.set(false);
        GiwifiPushAgent.getInstance(GBApplication.instance()).onAppStart();
        if (!z) {
            StatusBarUtil.m14194c(this);
        } else if (customTitleType() != 2) {
            StatusBarUtil.m14195c(this, 2131624113);
        }
        setContentView(i);
        if (z && customTitleType() != 2) {
            getWindow().setFeatureInt(7, C2425R.layout.my_title_bar);
        }
    }

    public void onRestart() {
        super.onRestart();
    }

    public void startActivity(String str) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public void initBackBtn() {
        if (this.leftTitleButton != null) {
            this.leftTitleButton.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC24651 */

                public void onClick(View view) {
                    GBActivity.this.finish();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void setHasTitle(boolean z) {
        this.hasTitle = z;
    }

    /* access modifiers changed from: protected */
    public int customTitleType() {
        if (this.hasTitle) {
            return 1;
        }
        return 2;
    }

    /* access modifiers changed from: protected */
    public void initCustomTitle() {
        if (getParent() == null) {
            int customTitleType = customTitleType();
            if (customTitleType == 1) {
                requestWindowFeature(7);
            } else if (customTitleType == 2) {
                requestWindowFeature(1);
            } else {
                if (customTitleType == 3 || customTitleType == 4 || customTitleType == 5 || customTitleType == 6 || customTitleType == 7) {
                }
            }
        }
    }

    public void showNormalProgress(String str, String str2, String str3, AbstractC2517a aVar, AbstractC2517a aVar2) {
        showNormalProgress(str, str2, str3, aVar, aVar2, false);
    }

    public void showNormalProgress(String str, String str2, String str3, AbstractC2517a aVar, AbstractC2517a aVar2, boolean z) {
        View inflate = getLayoutInflater().inflate(C2425R.layout.normal_progress_bar, (ViewGroup) null);
        progressBar = (ProgressBar) inflate.findViewById(2131755320);
        progressLeftText = (TextView) inflate.findViewById(C2425R.C2427id.left_text);
        progressRightText = (TextView) inflate.findViewById(C2425R.C2427id.right_text);
        showNormalDialog(str, inflate, str2, str3, aVar, aVar2, z, (DialogInterface.OnCancelListener) null);
    }

    public void showVersionProgress(String str, String str2, String str3, AbstractC2517a aVar, AbstractC2517a aVar2) {
        showVersionProgress(str, str2, str3, aVar, aVar2, false);
    }

    public void showVersionProgress(String str, String str2, String str3, AbstractC2517a aVar, AbstractC2517a aVar2, boolean z) {
        View inflate = getLayoutInflater().inflate(C2425R.layout.version_upgrade_progress_bar, (ViewGroup) null);
        progressBar = (ProgressBar) inflate.findViewById(2131755320);
        progressLeftText = (TextView) inflate.findViewById(C2425R.C2427id.left_text);
        progressRightText = (TextView) inflate.findViewById(C2425R.C2427id.right_text);
        showVersionDialog(str, inflate, str2, str3, aVar, aVar2, z, (DialogInterface.OnCancelListener) null);
    }

    public static void dismissNormalProgress() {
        if (progressBar != null) {
            progressBar = null;
            dismissNormalDialog();
        }
    }

    public static void setCommonProgress(int i, int i2) {
        if (progressBar != null) {
            progressBar.setProgress((i * 100) / i2);
            progressRightText.setText(C3467s.m14501a((long) i) + PsuedoNames.PSEUDONAME_ROOT + C3467s.m14501a((long) i2));
            progressLeftText.setText(((i * 100) / i2) + "%");
        }
    }

    public static void setCommonProgress(long j, long j2) {
        if (progressBar != null) {
            progressBar.setProgress((int) ((j * 100) / j2));
            progressRightText.setText(C3467s.m14501a(j) + PsuedoNames.PSEUDONAME_ROOT + C3467s.m14501a(j2));
            progressLeftText.setText(((j * 100) / j2) + "%");
        }
    }

    public static void dismissNormalDialog() {
        if (commonDialog != null && commonDialog.isShowing()) {
            commonDialog.dismiss();
        }
        commonDialog = null;
        if (secondDialog != null && secondDialog.isShowing()) {
            secondDialog.dismiss();
        }
        secondDialog = null;
        if (proDialog != null && proDialog.isShowing()) {
            proDialog.dismiss();
        }
        proDialog = null;
        if (thirdDialog != null && thirdDialog.isShowing()) {
            thirdDialog.dismiss();
        }
        thirdDialog = null;
        if (webDialog != null && webDialog.isShowing()) {
            webDialog.dismiss();
        }
        webDialog = null;
        if (versionDialog != null && versionDialog.isShowing()) {
            versionDialog.dismiss();
        }
        versionDialog = null;
    }

    public Dialog showNormalDialog(String str, String str2, String str3, String str4, AbstractC2517a aVar, AbstractC2517a aVar2) {
        return showNormalDialog(str, str2, str3, str4, aVar, aVar2, false, (DialogInterface.OnCancelListener) null);
    }

    public Dialog showNormalDialog(String str, String str2, String str3, String str4, AbstractC2517a aVar, AbstractC2517a aVar2, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        View inflate = getLayoutInflater().inflate(C2425R.layout.common_dialog_content, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(2131755301);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView.setText(str2);
        ((TextView) inflate.findViewById(C2425R.C2427id.dialog_title)).setText(str);
        return showNormalDialog(str, inflate, str3, str4, aVar, aVar2, z, onCancelListener);
    }

    public Dialog showNormalDialog(String str, View view, String str2, String str3, AbstractC2517a aVar, AbstractC2517a aVar2) {
        return showNormalDialog(str, view, str2, str3, aVar, aVar2, false, (DialogInterface.OnCancelListener) null);
    }

    public Dialog showNormalDialog(String str, View view, String str2, String str3, final AbstractC2517a aVar, final AbstractC2517a aVar2, boolean z, final DialogInterface.OnCancelListener onCancelListener) {
        if (commonDialog != null && commonDialog.isShowing()) {
            commonDialog.dismiss();
            commonDialog = null;
        }
        if (isFinishing()) {
            return null;
        }
        commonDialog = new Dialog(this, C2425R.C2430style.login_dialog);
        View inflate = LayoutInflater.from(getApplicationContext()).inflate(C2425R.layout.common_dialog, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.cancl_bt);
        Button button = (Button) inflate.findViewById(C2425R.C2427id.add_bt);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.view_layout)).addView(view, -1, -2);
        if (z) {
        }
        button.setText(str2);
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC246812 */

            public void onClick(View view) {
                if (aVar2 != null) {
                    if (GBActivity.commonDialog != null) {
                        aVar2.onClick(GBActivity.commonDialog, view);
                    }
                } else if (GBActivity.commonDialog != null) {
                    GBActivity.commonDialog.dismiss();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC248023 */

            public void onClick(View view) {
                if (aVar != null) {
                    if (GBActivity.commonDialog != null) {
                        aVar.onClick(GBActivity.commonDialog, view);
                    }
                } else if (GBActivity.commonDialog != null) {
                    GBActivity.commonDialog.dismiss();
                }
            }
        });
        commonDialog.setContentView(inflate, new ViewGroup.LayoutParams((int) getResources().getDimension(C2425R.dimen.dialog_width), -2));
        commonDialog.setCancelable(!z);
        commonDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            /* class com.gbcom.gwifi.base.activity.GBActivity.DialogInterface$OnCancelListenerC249234 */

            public void onCancel(DialogInterface dialogInterface) {
                if (onCancelListener != null) {
                    onCancelListener.onCancel(dialogInterface);
                }
            }
        });
        commonDialog.show();
        return commonDialog;
    }

    public Dialog showVersionDialog(String str, String str2, String str3, String str4, AbstractC2517a aVar, AbstractC2517a aVar2) {
        return showVersionDialog(str, str2, str3, str4, aVar, aVar2, false, (DialogInterface.OnCancelListener) null);
    }

    public Dialog showVersionDialog(String str, String str2, String str3, String str4, AbstractC2517a aVar, AbstractC2517a aVar2, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        View inflate = getLayoutInflater().inflate(C2425R.layout.version_dialog_content, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(2131755301);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView.setText(str2);
        ((TextView) inflate.findViewById(C2425R.C2427id.dialog_title)).setText("");
        return showVersionDialog(str, inflate, str3, str4, aVar, aVar2, z, onCancelListener);
    }

    public Dialog showVersionDialog(String str, View view, String str2, String str3, AbstractC2517a aVar, AbstractC2517a aVar2) {
        return showVersionDialog(str, view, str2, str3, aVar, aVar2, false, (DialogInterface.OnCancelListener) null);
    }

    public synchronized Dialog showVersionDialog(String str, View view, String str2, String str3, final AbstractC2517a aVar, final AbstractC2517a aVar2, boolean z, final DialogInterface.OnCancelListener onCancelListener) {
        Dialog dialog = null;
        synchronized (this) {
            Log.m14398b(TAG, "showVersionDialog " + str);
            if (versionDialog != null && versionDialog.isShowing()) {
                Log.m14398b(TAG, "showVersionDialog isShowing");
                versionDialog.dismiss();
                versionDialog = null;
            }
            if (versionDialog != null) {
                Log.m14398b(TAG, "showVersionDialog is not Showing");
            }
            if (!isFinishing()) {
                versionDialog = new Dialog(this, C2425R.C2430style.login_dialog);
                View inflate = LayoutInflater.from(getApplicationContext()).inflate(C2425R.layout.version_dialog, (ViewGroup) null);
                ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.cancl_bt);
                Button button = (Button) inflate.findViewById(C2425R.C2427id.add_bt);
                ((LinearLayout) inflate.findViewById(C2425R.C2427id.view_layout)).addView(view, -1, -2);
                if (z) {
                }
                if (z) {
                    imageView.setVisibility(8);
                } else {
                    imageView.setVisibility(0);
                }
                button.setText(str2);
                imageView.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC250445 */

                    public void onClick(View view) {
                        if (aVar2 != null) {
                            if (GBActivity.versionDialog != null) {
                                aVar2.onClick(GBActivity.versionDialog, view);
                            }
                        } else if (GBActivity.versionDialog != null) {
                            GBActivity.versionDialog.dismiss();
                        }
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC250546 */

                    public void onClick(View view) {
                        if (aVar != null) {
                            if (GBActivity.versionDialog != null) {
                                aVar.onClick(GBActivity.versionDialog, view);
                            }
                        } else if (GBActivity.versionDialog != null) {
                            GBActivity.versionDialog.dismiss();
                        }
                    }
                });
                int dimension = (int) getResources().getDimension(C2425R.dimen.dialog_width);
                Log.m14398b(TAG, "showVersionDialog width=" + dimension);
                versionDialog.setContentView(inflate, new ViewGroup.LayoutParams(dimension, -2));
                Log.m14398b(TAG, "showVersionDialog setContentView");
                versionDialog.setCancelable(!z);
                versionDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    /* class com.gbcom.gwifi.base.activity.GBActivity.DialogInterface$OnCancelListenerC250647 */

                    public void onCancel(DialogInterface dialogInterface) {
                        if (onCancelListener != null) {
                            onCancelListener.onCancel(dialogInterface);
                        }
                    }
                });
                versionDialog.show();
                Log.m14398b(TAG, "showVersionDialog show");
                dialog = versionDialog;
            }
        }
        return dialog;
    }

    public AlertDialog showCheckDialog(String str, View view, DialogInterface.OnClickListener onClickListener) {
        AlertDialog create = new AlertDialog.Builder(this).setTitle(str).setView(view).setIcon(C2425R.C2426drawable.ic_dialog_alert_holo_light).setPositiveButton("确定", onClickListener).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            /* class com.gbcom.gwifi.base.activity.GBActivity.DialogInterface$OnClickListenerC250748 */

            public void onClick(DialogInterface dialogInterface, int i) {
                GBActivity.this.distoryDialog(dialogInterface);
                dialogInterface.dismiss();
            }
        }).create();
        create.show();
        return create;
    }

    public AlertDialog.Builder dialogBuilder(String str, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(str).setPositiveButton("确定", onClickListener).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            /* class com.gbcom.gwifi.base.activity.GBActivity.DialogInterface$OnClickListenerC250849 */

            public void onClick(DialogInterface dialogInterface, int i) {
                GBActivity.this.distoryDialog(dialogInterface);
                dialogInterface.dismiss();
            }
        });
        builder.create();
        builder.show();
        return builder;
    }

    public void keepDialog(DialogInterface dialogInterface) {
        try {
            Field declaredField = dialogInterface.getClass().getSuperclass().getDeclaredField("mShowing");
            declaredField.setAccessible(true);
            declaredField.set(dialogInterface, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void distoryDialog(DialogInterface dialogInterface) {
        try {
            Field declaredField = dialogInterface.getClass().getSuperclass().getDeclaredField("mShowing");
            declaredField.setAccessible(true);
            declaredField.set(dialogInterface, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessageDialog(String str) {
        new AlertDialog.Builder(this).setMessage(str).show();
    }

    public void showMessageDialog(String str, String str2) {
        new AlertDialog.Builder(this).setTitle(str).setMessage(str2).show();
    }

    public static void showMessageToast(String str) {
        uiHandler.sendMessage(uiHandler.obtainMessage(8, str));
    }

    public static void showMessageLongToast(String str) {
        Toast.makeText(GBApplication.instance(), str, 1).show();
    }

    public void showMessageToastAndFinish(String str) {
        Toast.makeText(this, str, 0).show();
        finish();
    }

    public SharedPreferences getSharedPreferences() {
        return getSharedPreferences(Constant.f13172a, 0);
    }

    public SharedPreferences.Editor getSPEditor() {
        return getSharedPreferences().edit();
    }

    public String getValueBySP(String str, String str2) {
        return getSharedPreferences().getString(str, str2);
    }

    public boolean getValueBySP(String str, boolean z) {
        return getSharedPreferences().getBoolean(str, z);
    }

    public float getValueBySP(String str, float f) {
        return getSharedPreferences().getFloat(str, f);
    }

    public int getValueBySP(String str, int i) {
        return getSharedPreferences().getInt(str, i);
    }

    public long getValueBySP(String str, long j) {
        return getSharedPreferences().getLong(str, j);
    }

    public void showProgressDialog(String str) {
        Log.m14398b(TAG, "showProgressDialog " + str);
        showProgressDialog(str, str, EnumC2522f.NO_BAR);
    }

    public void showProgressDialog(String str, String str2) {
        showProgressDialog(str, str2, EnumC2522f.NO_BAR);
    }

    public void showProgressDialog(String str, String str2, EnumC2522f fVar) {
        if (proDialog != null && proDialog.isShowing()) {
            dismissProgressDialog();
        }
        if (!isFinishing()) {
            proDialog = new ProgressDialog(this);
            proDialog.setTitle(str);
            proDialog.setMessage(str2);
            proDialog.setCancelable(true);
            proDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.DialogInterface$OnCancelListenerC24762 */

                public void onCancel(DialogInterface dialogInterface) {
                    if (GBActivity.proDialog != null) {
                        GBActivity.proDialog.hide();
                    }
                }
            });
            if (fVar == EnumC2522f.NO_BAR) {
                proDialog.show();
                proDialog.getWindow().setContentView(C2425R.layout.progress);
                proDialog.getWindow().getAttributes().dimAmount = 0.0f;
                proDialog.getWindow().setGravity(1);
                return;
            }
            proDialog.setProgressStyle(1);
            proDialog.setProgress(100);
            proDialog.setIndeterminate(false);
            proDialog.setCancelable(false);
            proDialog.show();
        }
    }

    public void setDialogProgress(int i) {
        if (proDialog != null && proDialog.isShowing()) {
            proDialog.setProgress(i);
        }
    }

    public void dismissProgressDialog() {
        Log.m14398b(TAG, "dismissProgressDialog ");
        if (proDialog != null) {
            proDialog.cancel();
            proDialog.dismiss();
            proDialog = null;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        Log.m14400c(TAG, this.ActivityName + "  onDestroy");
        this.isDestroy.set(true);
        Log.m14400c(getClass().getName(), "dismiss dialog");
        if (!(this instanceof LaunchActivity)) {
            dismissNormalDialog();
        }
        super.onDestroy();
        GBApplication.instance().removeActivity(this);
    }

    public boolean checkDestroyed() {
        if (this.isDestroy == null) {
            return false;
        }
        return this.isDestroy.get();
    }

    public boolean showException(HttpResponse dVar) {
        if (dVar.mo24085a() != null) {
            if (dVar.mo24085a() instanceof SocketTimeoutException) {
                showMessageToast("访问超时");
                return true;
            } else if (dVar.mo24085a() instanceof UnknownHostException) {
                showMessageToast("云端域名找不到");
                return true;
            } else if (dVar.mo24085a() instanceof SocketException) {
                showMessageToast("连接失败");
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p009v4.app.FragmentActivity
    public void onStop() {
        super.onStop();
        if (!GBGlobalConfig.m10510c().mo24400a(getApplicationContext())) {
            CacheApp.getInstance().setOnBackgroundTime();
            this.isActive = false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
        if (isResetTime) {
            CacheApp.getInstance().setOnBackgroundTime();
            isResetTime = false;
        }
        if (!this.isActive) {
            this.isActive = true;
            long onBackgroundTime = CacheApp.getInstance().getOnBackgroundTime();
            long backgroundStayTime = CacheApp.getInstance().getBackgroundStayTime();
            if (backgroundStayTime != 0 && System.currentTimeMillis() > onBackgroundTime + backgroundStayTime) {
                if (!SystemUtil.m14521a()) {
                    GBApplication.instance().exit2Launch();
                    return;
                }
                return;
            }
        }
        Log.m14398b(TAG, "GB.onResume()........." + toString());
        GiwifiMobclickAgent.onPageStart(getActivityName());
        GiwifiMobclickAgent.onResume(this);
        GBApplication.instance().addActivity(this);
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p009v4.app.FragmentActivity
    public void onPause() {
        super.onPause();
        Log.m14400c(TAG, this.ActivityName + "  onPause");
        GiwifiMobclickAgent.onPageEnd(getActivityName());
        GiwifiMobclickAgent.onPause(this);
    }

    public static boolean dealException(CommonMsg commonMsg) {
        if (commonMsg == null) {
            return true;
        }
        if (ResultCode.m14476b(commonMsg)) {
            return false;
        }
        showMessageToast(ResultCode.m14474a(commonMsg));
        return true;
    }

    /* access modifiers changed from: protected */
    public void exitApp() {
        List<GBActivity> activitiesList = GBApplication.instance().getActivitiesList();
        AppDownloadActivity appDownloadActivity = null;
        for (int i = 0; i < activitiesList.size(); i++) {
            if (activitiesList.get(i) instanceof AppDownloadActivity) {
                appDownloadActivity = (AppDownloadActivity) activitiesList.get(i);
            }
        }
        if (appDownloadActivity != null) {
            appDownloadActivity.getThirdFragment().mo25509d();
            appDownloadActivity.finish();
        }
        CacheWiFi.getInstance().setApLocation("");
        GiwifiMobclickAgent.onKillProcess(this);
        CacheApp.getInstance().setLastAutoCheckVersionTime(-1);
        ((GBApplication) getApplicationContext()).onTerminate();
    }

    /* access modifiers changed from: protected */
    public void checkAndExitApp() {
        if (System.currentTimeMillis() - this.mExitTime > StatsManager.DEFAULT_TIMEOUT_MILLIS) {
            Toast.makeText(this, "再按一次退出程序", 0).show();
            this.mExitTime = System.currentTimeMillis();
            return;
        }
        exitApp();
    }

    /* access modifiers changed from: protected */
    public void checkAndExitNoToastApp() {
        if (System.currentTimeMillis() - this.mExitTime > StatsManager.DEFAULT_TIMEOUT_MILLIS) {
            this.mExitTime = System.currentTimeMillis();
        } else {
            exitApp();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x009b A[Catch:{ JSONException -> 0x01c9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b0 A[Catch:{ JSONException -> 0x01c9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c4 A[Catch:{ JSONException -> 0x01c9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x019c A[Catch:{ JSONException -> 0x01c9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processCheckAppCustomResponse(java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 483
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.base.activity.GBActivity.processCheckAppCustomResponse(java.lang.String):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean checkNeedDownloadVersion(int i) {
        if (i <= curVersionCode) {
            return false;
        }
        if (!SystemUtil.m14533f() || i / 10000 == curVersionCode / 10000) {
            return true;
        }
        Log.m14398b(TAG, "custom assist versionCode=" + i + ",curVersionCode=" + curVersionCode + ",no need update");
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void downloadNewVersion(HashMap<String, Object> hashMap, long j, Integer num, boolean z) {
        String str;
        HashMap downloadVersionBackground = CacheApp.getInstance().getDownloadVersionBackground();
        if (downloadVersionBackground != null && downloadVersionBackground.containsKey("versionCode") && hashMap.get("versionCode").equals(downloadVersionBackground.get("versionCode"))) {
            downloadVersionBackground.put("isReallyMust", hashMap.get("isReallyMust"));
            CacheApp.getInstance().setDownloadVersionBackground(downloadVersionBackground);
        }
        if (downloadVersionBackground == null || !downloadVersionBackground.containsKey(TbsReaderView.KEY_FILE_PATH) || (str = (String) downloadVersionBackground.get(TbsReaderView.KEY_FILE_PATH)) == null || str.equals("") || !new File(str).exists() || !downloadVersionBackground.get("versionCode").equals(num)) {
            if (downloadVersionBackground != null) {
                CacheApp.getInstance().removeDownloadVersionBackground();
            }
            if (!NetworkUtils.m14430b(WiFiUtil.m14022a(GBApplication.instance()).mo27615i())) {
                return;
            }
            if (CacheAuth.getInstance().getGwSn().equalsIgnoreCase(AuthInfo.DEFAULT_SN) || versionData.get("localDownloadUrl") == null || versionData.get("localDownloadUrl").toString().length() <= 0) {
                downloadVersionBackGround(versionData.get("md5").toString(), versionData.get("downloadUrl").toString(), hashMap, j);
            } else {
                downloadVersionBackGround(versionData.get("md5").toString(), versionData.get("localDownloadUrl").toString(), hashMap, j);
            }
        } else if (!z) {
            checkVersionDialog(downloadVersionBackground);
        }
    }

    private void checkVersionDialog(final HashMap<String, Object> hashMap) {
        String str;
        String str2 = "最新版本：" + hashMap.get("versionName").toString();
        String obj = hashMap.get("versionContent").toString();
        if (((Boolean) hashMap.get("isReallyMust")).booleanValue()) {
            str = "退出应用";
        } else {
            str = "稍后更新";
        }
        showVersionDialog(str2, obj, "立即更新", str, new AbstractC2517a() {
            /* class com.gbcom.gwifi.base.activity.GBActivity.C25136 */

            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
            public void onClick(Dialog dialog, View view) {
                dialog.dismiss();
                if (GBActivity.installApkBack(hashMap.get(TbsReaderView.KEY_FILE_PATH).toString())) {
                }
            }
        }, new AbstractC2517a() {
            /* class com.gbcom.gwifi.base.activity.GBActivity.C25147 */

            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
            public void onClick(Dialog dialog, View view) {
                dialog.dismiss();
                if (((Boolean) hashMap.get("isReallyMust")).booleanValue()) {
                    GBActivity.this.exitApp();
                }
            }
        }, ((Boolean) hashMap.get("isReallyMust")).booleanValue(), new DialogInterface.OnCancelListener() {
            /* class com.gbcom.gwifi.base.activity.GBActivity.DialogInterface$OnCancelListenerC25158 */

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
    }

    private String getStoragePath() {
        String str = GBApplication.instance().getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator;
        Log.m14398b(TAG, "getStoragePath: " + str);
        return str;
    }

    private void doDownloadVersionBackGround(String str, String str2, final HashMap<String, Object> hashMap, final long j) {
        final String str3;
        isVersionCaching = true;
        if (str2.equals("")) {
            str3 = HttpUtil.m14279a(str);
        } else {
            str3 = str2;
        }
        mSavePath = getStoragePath() + "wifi2";
        File file = new File(mSavePath, versionData.get("versionName").toString() + ".apk");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        final String path = file.getPath();
        notificationInit();
        new Thread(new Runnable() {
            /* class com.gbcom.gwifi.base.activity.GBActivity.RunnableC25169 */

            @Override // java.lang.Runnable
            public void run() {
                if (!CacheAuth.getInstance().isPortal()) {
                    GBActivity.downloadUpdateFile(str3, path, hashMap, j);
                } else if (!str3.startsWith("http://down.gwifi.com.cn/")) {
                    GBActivity.downloadUpdateFile(str3, path, hashMap, j);
                } else {
                    String a = NetworkUtils.m14425a("down.gwifi.com.cn");
                    String k = WiFiUtil.m14022a(GBApplication.instance()).mo27617k();
                    String portalHost = CacheAuth.getInstance().getPortalHost();
                    if (TextUtils.isEmpty(portalHost) || a == portalHost || a == k) {
                        GBActivity.downloadUpdateFile(str3, path, hashMap, j);
                    } else {
                        GBActivity.downloadUpdateFile(str3.replace("down.gwifi.com.cn", portalHost), path, hashMap, j);
                    }
                }
            }
        }).start();
    }

    private void downloadVersionBackGround(String str, String str2, HashMap<String, Object> hashMap, long j) {
        doDownloadVersionBackGround(str, str2, hashMap, j);
    }

    private void doDownloadVersion(String str, final String str2, final long j) {
        String str3;
        String str4;
        cancelUpdate = false;
        if (str2.equals("")) {
            str2 = HttpUtil.m14279a(str);
        }
        mSavePath = getStoragePath() + "wifi";
        File file = new File(mSavePath, versionData.get("versionName").toString() + ".apk");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (isReallyMust.booleanValue()) {
            str3 = "正在下载";
        } else {
            str3 = "后台下载";
        }
        if (isReallyMust.booleanValue()) {
            str4 = "退出应用";
        } else {
            str4 = "取消下载";
        }
        showVersionProgress("升级版本", str3, str4, new AbstractC2517a() {
            /* class com.gbcom.gwifi.base.activity.GBActivity.C246610 */

            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
            public void onClick(Dialog dialog, View view) {
                if (!GBActivity.isReallyMust.booleanValue()) {
                    GBActivity.dismissNormalProgress();
                }
            }
        }, new AbstractC2517a() {
            /* class com.gbcom.gwifi.base.activity.GBActivity.C246711 */

            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
            public void onClick(Dialog dialog, View view) {
                GBActivity.dismissNormalProgress();
                boolean unused = GBActivity.cancelUpdate = true;
                if (GBActivity.isReallyMust.booleanValue()) {
                    GBActivity.mnNotificationManager.cancel(0);
                    GBActivity.this.exitApp();
                }
            }
        }, isReallyMust.booleanValue());
        final String path = file.getPath();
        notificationInit();
        new Thread(new Runnable() {
            /* class com.gbcom.gwifi.base.activity.GBActivity.RunnableC246913 */

            @Override // java.lang.Runnable
            public void run() {
                if (!CacheAuth.getInstance().isPortal()) {
                    GBActivity.downloadUpdateFile(str2, path, j);
                } else if (!str2.startsWith("http://down.gwifi.com.cn/")) {
                    GBActivity.downloadUpdateFile(str2, path, j);
                } else {
                    String a = NetworkUtils.m14425a("down.gwifi.com.cn");
                    String k = WiFiUtil.m14022a(GBApplication.instance()).mo27617k();
                    String portalHost = CacheAuth.getInstance().getPortalHost();
                    if (TextUtils.isEmpty(portalHost) || a == portalHost || a == k) {
                        GBActivity.downloadUpdateFile(str2, path, j);
                    } else {
                        GBActivity.downloadUpdateFile(str2.replace("down.gwifi.com.cn", portalHost), path, j);
                    }
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void downloadVersion(String str, String str2, long j) {
        doDownloadVersion(str, str2, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004e A[SYNTHETIC, Splitter:B:15:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0053 A[Catch:{ IOException -> 0x00c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00d0 A[SYNTHETIC, Splitter:B:48:0x00d0] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d5 A[Catch:{ IOException -> 0x00d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void downloadUpdateFile(java.lang.String r17, java.lang.String r18, java.util.HashMap<java.lang.String, java.lang.Object> r19, long r20) {
        /*
        // Method dump skipped, instructions count: 271
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.base.activity.GBActivity.downloadUpdateFile(java.lang.String, java.lang.String, java.util.HashMap, long):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x005a A[SYNTHETIC, Splitter:B:15:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005f A[Catch:{ IOException -> 0x00ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00fb A[SYNTHETIC, Splitter:B:53:0x00fb] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0100 A[Catch:{ IOException -> 0x0104 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void downloadUpdateFile(java.lang.String r18, java.lang.String r19, long r20) {
        /*
        // Method dump skipped, instructions count: 315
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.base.activity.GBActivity.downloadUpdateFile(java.lang.String, java.lang.String, long):void");
    }

    public void checkVersion() {
        if (!cancelUpdate) {
            showVersionProgress("升级版本", "后台下载", "取消下载", new AbstractC2517a() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.C247014 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    GBActivity.dismissNormalProgress();
                }
            }, new AbstractC2517a() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.C247115 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    GBActivity.dismissNormalProgress();
                    boolean unused = GBActivity.cancelUpdate = true;
                }
            });
            return;
        }
        try {
            packageInfo = GBApplication.instance().getPackageManager().getPackageInfo(GBApplication.instance().getPackageName(), 0);
            curVersionCode = packageInfo.versionCode;
            checkVersionRequest = HttpUtil.m14251a(GBApplication.instance(), this.okReqHandler, "");
        } catch (PackageManager.NameNotFoundException e) {
            Log.m14403e(TAG, "checkVersion():" + e.toString());
            e.printStackTrace();
        }
    }

    public void checkVersionHidden() {
        if (!cancelUpdate) {
            showVersionProgress("升级版本", "后台下载", "取消下载", new AbstractC2517a() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.C247216 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    GBActivity.dismissNormalProgress();
                }
            }, new AbstractC2517a() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.C247317 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    GBActivity.dismissNormalProgress();
                    boolean unused = GBActivity.cancelUpdate = true;
                }
            });
            return;
        }
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            curVersionCode = packageInfo.versionCode;
            checkVersionHiddenRequest = HttpUtil.m14251a(GBApplication.instance(), this.okReqHandler, "");
        } catch (PackageManager.NameNotFoundException e) {
            Log.m14403e(TAG, "checkVersionHidden():" + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Intent getInstallIntent(File file) {
        Uri fromFile;
        Intent intent = new Intent("android.intent.action.VIEW");
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                fromFile = FileProvider.getUriForFile(GBApplication.instance().getApplicationContext(), GBApplication.instance().getPackageName() + ".provider", file);
                intent.setAction("android.intent.action.INSTALL_PACKAGE");
                intent.addFlags(1);
            } else {
                fromFile = Uri.fromFile(file);
                intent.setAction("android.intent.action.VIEW");
                intent.setFlags(268435456);
            }
            intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ActivityNotFoundException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return intent;
    }

    private static void doInstallApk(String str) {
        File file = new File(str);
        if (!file.exists()) {
            Log.m14398b(TAG, "installApk not exist " + str);
            return;
        }
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity == null) {
            Log.m14403e(TAG, "currentActivity is null ");
        } else {
            currentActivity.startActivity(getInstallIntent(file));
        }
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 26)
    public static void startInstallPermissionSettingActivity(GBActivity gBActivity) {
        gBActivity.startActivityForResult(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + gBActivity.getPackageName())), HandlerRequestCode.WX_REQUEST_CODE);
    }

    /* access modifiers changed from: private */
    public static void installApk(String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
            if (currentActivity != null) {
                if (!currentActivity.getPackageManager().canRequestPackageInstalls()) {
                    apkFilePath = str;
                    GBGlobalConfig.m10510c().mo24392a(currentActivity, "安装应用需要打开未知来源权限，请去设置中开启权限", new DialogInterface.OnClickListener(currentActivity) {
                        /* class com.gbcom.gwifi.base.activity.GBActivity.DialogInterface$OnClickListenerC247418 */

                        /* renamed from: a */
                        final /* synthetic */ GBActivity f8681a;

                        {
                            this.f8681a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i) {
                                case -1:
                                    if (Build.VERSION.SDK_INT >= 26) {
                                        GBActivity.startInstallPermissionSettingActivity(this.f8681a);
                                        return;
                                    }
                                    return;
                                default:
                                    return;
                            }
                        }
                    });
                    return;
                }
            } else {
                return;
            }
        }
        doInstallApk(str);
    }

    public static boolean doInstallApkBack(String str) {
        File file = new File(str);
        if (!file.exists()) {
            Log.m14398b(TAG, "installApkBack not exist " + str);
            return false;
        }
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity == null) {
            Log.m14398b(TAG, "currentActivity is null ");
            return false;
        }
        currentActivity.startActivity(getInstallIntent(file));
        return true;
    }

    public static boolean installApkBack(String str) {
        return doInstallApkBack(str);
    }

    public static void openGiBackWebView(String str, String str2) {
        Log.m14398b(TAG, "X5WebView openGiBackWebView");
        Intent intent = new Intent(GBApplication.instance(), BackX5WebViewActivity.class);
        intent.putExtra("url", URLUtil.m14541a(str));
        intent.putExtra("title", str2);
        intent.putExtra("hidTitle", true);
        intent.addFlags(131072);
        if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().getCurrentActivity().startActivity(intent);
        }
    }

    public static void openOfficeBackWebView(String str, String str2) {
        Log.m14398b(TAG, "X5WebView openOfficeBackWebView");
        isResetTime = true;
        Intent intent = new Intent(GBApplication.instance(), BackX5WebViewActivity.class);
        intent.putExtra("url", URLUtil.m14541a(str));
        intent.putExtra("title", str2);
        intent.addFlags(131072);
        if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().getCurrentActivity().startActivity(intent);
        }
    }

    public static void openBackWebView(String str, String str2) {
        Log.m14398b(TAG, "X5WebView openBackWebView1");
        isResetTime = true;
        Intent intent = new Intent(GBApplication.instance(), BackX5WebViewActivity.class);
        intent.putExtra("url", URLUtil.m14541a(str));
        intent.putExtra("title", str2);
        intent.addFlags(131072);
        if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().getCurrentActivity().startActivity(intent);
        }
    }

    public static void openBackWebView(String str, String str2, int i, int i2) {
        Log.m14398b(TAG, "X5WebView openBackWebView2");
        isResetTime = true;
        Intent intent = new Intent(GBApplication.instance(), BackX5WebViewActivity.class);
        intent.putExtra("url", URLUtil.m14541a(str));
        intent.putExtra("title", str2);
        intent.putExtra("hidden_title_bar", i);
        intent.putExtra("is_local", i2);
        intent.addFlags(131072);
        if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().getCurrentActivity().startActivity(intent);
        }
    }

    public static void openBackWebView(String str, String str2, int i) {
        Log.m14398b(TAG, "X5WebView openBackWebView3");
        isResetTime = true;
        Intent intent = new Intent(GBApplication.instance(), BackX5WebViewActivity.class);
        intent.putExtra("url", URLUtil.m14541a(str));
        intent.putExtra("title", str2);
        intent.putExtra("hidden_title_bar", i);
        intent.addFlags(131072);
        if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().getCurrentActivity().startActivity(intent);
        }
    }

    public static void openFindBackWebView(String str, String str2, int i, int i2, int i3, boolean z, int i4) {
        Log.m14398b(TAG, "X5WebView openFindBackWebView");
        isResetTime = true;
        Intent intent = new Intent(GBApplication.instance(), BackX5WebViewActivity.class);
        intent.putExtra("url", URLUtil.m14541a(str));
        intent.putExtra("title", str2);
        intent.putExtra("hidden_title_bar", i4);
        intent.putExtra("second", i);
        intent.putExtra("hitBeans", i2);
        intent.putExtra("id", i3);
        intent.putExtra("isFind", z);
        intent.addFlags(131072);
        if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().getCurrentActivity().startActivity(intent);
        }
    }

    public static void openSignBackWebView(String str, String str2, boolean z) {
        Log.m14398b(TAG, "X5WebView openSignBackWebView");
        isResetTime = true;
        Intent intent = new Intent(GBApplication.instance(), BackX5WebViewActivity.class);
        intent.putExtra("url", URLUtil.m14541a(str));
        intent.putExtra("title", str2);
        intent.putExtra("sign", z);
        intent.addFlags(131072);
        if (GBApplication.instance().getCurrentActivity() != null) {
            GBApplication.instance().getCurrentActivity().startActivity(intent);
        }
    }

    public static String getSDPath() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Environment.getExternalStorageDirectory().getPath();
        }
        return "";
    }

    public void contactClientPhone() {
        try {
            UmengCount.queryAppCustomerService(this, BUSINESS_CONSULTATION);
            callPhone(Constant.f13225b);
        } catch (Exception e) {
            e.printStackTrace();
            GBApplication.instance().showMessageToast("你好像没有安装 拨号 应用！");
        }
    }

    public void contactSalesMan(View view) {
        try {
            UmengCount.queryAppCustomerService(this, BUSINESS_CONSULTATION);
            callPhone(CacheAuth.getInstance().getContactPhone());
        } catch (Exception e) {
            e.printStackTrace();
            GBApplication.instance().showMessageToast("你好像没有安装 拨号 应用！");
        }
    }

    public void contactSuggest(View view) {
        try {
            UmengCount.queryAppCustomerService(this, COMPLAINTS_HOTLINE);
            callPhone(CacheAuth.getInstance().getSuggestPhone());
        } catch (Exception e) {
            GBApplication.instance().showMessageToast("你好像没有安装 拨号 应用！");
            e.printStackTrace();
        }
    }

    public void contactKefu(View view) {
        try {
            UmengCount.queryAppCustomerService(this, COMPLAINTS_HOTLINE);
            callPhone(Constant.f13225b);
        } catch (Exception e) {
            GBApplication.instance().showMessageToast("你好像没有安装 拨号 应用！");
            e.printStackTrace();
        }
    }

    private void notificationInit() {
        String d = SystemUtil.m14528d();
        mnNotificationManager = (NotificationManager) getApplicationContext().getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
        mBuilder = new NotificationCompat.Builder(getApplicationContext());
        mBuilder.setContentTitle(d + versionData.get("versionName").toString());
        mBuilder.setContentText("下载进度");
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), C2425R.C2426drawable.download_image2));
        mBuilder.setSmallIcon(C2425R.C2426drawable.icon).setTicker("").setOngoing(true);
        mBuilder.setContentInfo("0%");
    }

    public String getActivityName() {
        return this.ActivityName;
    }

    public void setActivityName(String str) {
        this.ActivityName = str;
    }

    public void gotoError(String str, String str2) {
        if (SystemUtil.m14531e()) {
            GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
            if (currentActivity != null) {
                Intent intent = new Intent(GBApplication.instance(), LoginErrorActivity.class);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                }
                intent.putExtra(Constant.f13323i, str);
                intent.putExtra("errinfo", str2);
                currentActivity.startActivity(intent);
                return;
            }
            return;
        }
        C3470v.m14563a(GBApplication.instance(), str2);
    }

    public void gotoError(String str, CommonMsg commonMsg) {
        gotoError(str, ResultCode.m14474a(commonMsg));
    }

    public void showSecondDialog(String str, String str2, String str3, final AbstractC2517a aVar, final DialogInterface.OnCancelListener onCancelListener) {
        Log.m14398b(TAG, "showSecondDialog " + str + "," + str2);
        if (!GBGlobalConfig.m10510c().mo24400a(getApplicationContext())) {
            showMessageToast(str2);
            return;
        }
        if (secondDialog != null && secondDialog.isShowing()) {
            secondDialog.dismiss();
            secondDialog = null;
        }
        if (!isFinishing()) {
            secondDialog = new Dialog(this, C2425R.C2430style.login_dialog);
            View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.common_dialog, (ViewGroup) null);
            ((ImageView) inflate.findViewById(C2425R.C2427id.cancl_bt)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC247519 */

                public void onClick(View view) {
                    if (onCancelListener != null) {
                        if (GBActivity.secondDialog != null) {
                            onCancelListener.onCancel(GBActivity.secondDialog);
                        }
                        if (GBActivity.secondDialog != null) {
                            GBActivity.secondDialog.dismiss();
                        }
                    } else if (GBActivity.secondDialog != null) {
                        GBActivity.secondDialog.dismiss();
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
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC247720 */

                public void onClick(View view) {
                    if (aVar != null) {
                        if (GBActivity.secondDialog != null) {
                            aVar.onClick(GBActivity.secondDialog, view);
                        }
                        if (GBActivity.secondDialog != null) {
                            GBActivity.secondDialog.dismiss();
                        }
                    } else if (GBActivity.secondDialog != null) {
                        GBActivity.secondDialog.dismiss();
                    }
                }
            });
            secondDialog.setContentView(inflate, new ViewGroup.LayoutParams((int) GBApplication.instance().getResources().getDimension(C2425R.dimen.dialog_width), -2));
            if (onCancelListener != null) {
                secondDialog.setOnCancelListener(onCancelListener);
            }
            secondDialog.show();
        }
    }

    public void showThirdDialog(String str, String str2, String str3, String str4, final AbstractC2518b bVar, final AbstractC2520d dVar, final AbstractC2519c cVar) {
        if (thirdDialog != null && thirdDialog.isShowing()) {
            thirdDialog.dismiss();
            thirdDialog = null;
        }
        if (!isFinishing()) {
            thirdDialog = new Dialog(this, C2425R.C2430style.login_dialog);
            View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.wifi_weixin_dialog_third, (ViewGroup) null);
            ((ImageView) inflate.findViewById(C2425R.C2427id.cancl_bt)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC247821 */

                public void onClick(View view) {
                    if (GBActivity.thirdDialog != null) {
                        GBActivity.thirdDialog.dismiss();
                    }
                }
            });
            ((ImageView) inflate.findViewById(C2425R.C2427id.add_bt1)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC247922 */

                public void onClick(View view) {
                    if (bVar != null) {
                        if (GBActivity.thirdDialog != null) {
                            bVar.mo24345a(GBActivity.thirdDialog, view);
                        }
                    } else if (GBActivity.thirdDialog != null) {
                        GBActivity.thirdDialog.dismiss();
                    }
                }
            });
            ((ImageView) inflate.findViewById(C2425R.C2427id.add_bt2)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC248124 */

                public void onClick(View view) {
                    if (dVar != null) {
                        if (GBActivity.thirdDialog != null) {
                            dVar.mo24347a(GBActivity.thirdDialog, view);
                        }
                    } else if (GBActivity.thirdDialog != null) {
                        GBActivity.thirdDialog.dismiss();
                    }
                }
            });
            ((ImageView) inflate.findViewById(C2425R.C2427id.add_bt3)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC248225 */

                public void onClick(View view) {
                    if (cVar != null) {
                        if (GBActivity.thirdDialog != null) {
                            cVar.mo24346a(GBActivity.thirdDialog, view);
                        }
                    } else if (GBActivity.thirdDialog != null) {
                        GBActivity.thirdDialog.dismiss();
                    }
                }
            });
            int a = DensityUtil.m14127a(GBApplication.instance());
            thirdDialog.setContentView(inflate, new ViewGroup.LayoutParams((int) (((double) a) * 0.76d), (int) (((double) a) * 0.76d * 1.1765d)));
            thirdDialog.show();
        }
    }

    public void showDialogAd(Context context, String str, int i, final AbstractC2517a aVar, DialogInterface.OnCancelListener onCancelListener) {
        if (dialogAd != null && dialogAd.isShowing()) {
            dialogAd.dismiss();
            dialogAd = null;
        }
        if (!isFinishing()) {
            dialogAd = new Dialog(this, C2425R.C2430style.login_dialog);
            View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.tp_dialog_ad, (ViewGroup) null);
            ((RelativeLayout) inflate.findViewById(C2425R.C2427id.iv_bg)).getLayoutParams().height = i;
            ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.dialog_iv);
            ImageTools.m14149a(str, imageView, GBApplication.instance().options);
            ((ImageView) inflate.findViewById(C2425R.C2427id.dialog_btn)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC248326 */

                public void onClick(View view) {
                    if (GBActivity.dialogAd != null) {
                        GBActivity.dialogAd.dismiss();
                    }
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC248427 */

                public void onClick(View view) {
                    if (aVar != null) {
                        if (GBActivity.dialogAd != null) {
                            aVar.onClick(GBActivity.dialogAd, view);
                        }
                    } else if (GBActivity.dialogAd != null) {
                        GBActivity.dialogAd.dismiss();
                    }
                }
            });
            dialogAd.setContentView(inflate);
            if (onCancelListener != null) {
                dialogAd.setOnCancelListener(onCancelListener);
            }
            dialogAd.show();
        }
    }

    public static void scaleImage(final Activity activity, final View view, int i) {
        Point point = new Point();
        activity.getWindow().getWindowManager().getDefaultDisplay().getSize(point);
        Bitmap decodeResource = BitmapFactory.decodeResource(activity.getResources(), i);
        if (decodeResource != null) {
            final Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeResource, point.x, Math.round((((float) (decodeResource.getHeight() * point.x)) * 1.0f) / ((float) decodeResource.getWidth())), false);
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.ViewTreeObserver$OnPreDrawListenerC248528 */

                public boolean onPreDraw() {
                    if (!createScaledBitmap.isRecycled()) {
                        int height = (createScaledBitmap.getHeight() - view.getMeasuredHeight()) / 2;
                        Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap, 0, height, createScaledBitmap.getWidth(), createScaledBitmap.getHeight() - (height * 2));
                        if (!createBitmap.equals(createScaledBitmap)) {
                            createScaledBitmap.recycle();
                            System.m38963gc();
                        }
                        view.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), createBitmap));
                    }
                    return true;
                }
            });
        }
    }

    public void showWebDialog(final AbstractC2521e eVar) {
        if (webDialog != null && webDialog.isShowing()) {
            webDialog.dismiss();
            webDialog = null;
        }
        if (!isFinishing()) {
            webDialog = new Dialog(this, C2425R.C2430style.login_dialog);
            webDialog.setCanceledOnTouchOutside(false);
            webDialog.setCancelable(false);
            View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.wifi_weixin_dialog, (ViewGroup) null);
            final ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.button_skip);
            final ImageView imageView2 = (ImageView) inflate.findViewById(C2425R.C2427id.weixin_wifi_step_1);
            final ImageView imageView3 = (ImageView) inflate.findViewById(C2425R.C2427id.weixin_wifi_step_2);
            final ImageView imageView4 = (ImageView) inflate.findViewById(C2425R.C2427id.weixin_wifi_step_3);
            final ImageView imageView5 = (ImageView) inflate.findViewById(C2425R.C2427id.weixin_wifi_step_4);
            final ImageView imageView6 = (ImageView) inflate.findViewById(C2425R.C2427id.weixin_wifi_step_5);
            final ImageView imageView7 = (ImageView) inflate.findViewById(C2425R.C2427id.weixin_wifi_step_6);
            final ImageView imageView8 = (ImageView) inflate.findViewById(C2425R.C2427id.weixin_wifi_step_7);
            final ImageView imageView9 = (ImageView) inflate.findViewById(C2425R.C2427id.button_next_step_1);
            final ImageView imageView10 = (ImageView) inflate.findViewById(C2425R.C2427id.button_next_step_2);
            final ImageView imageView11 = (ImageView) inflate.findViewById(C2425R.C2427id.button_next_step_3);
            final ImageView imageView12 = (ImageView) inflate.findViewById(C2425R.C2427id.button_next_step_4);
            final ImageView imageView13 = (ImageView) inflate.findViewById(C2425R.C2427id.button_next_step_5);
            final ImageView imageView14 = (ImageView) inflate.findViewById(C2425R.C2427id.button_next_step_6);
            final ImageView imageView15 = (ImageView) inflate.findViewById(C2425R.C2427id.button_see_again);
            final ImageView imageView16 = (ImageView) inflate.findViewById(C2425R.C2427id.button_close);
            imageView.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC248629 */

                public void onClick(View view) {
                    if (eVar != null) {
                        if (GBActivity.webDialog != null) {
                            eVar.mo24348a(GBActivity.webDialog, view);
                        }
                    } else if (GBActivity.webDialog != null) {
                        GBActivity.webDialog.dismiss();
                    }
                }
            });
            imageView9.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC248830 */

                public void onClick(View view) {
                    imageView2.setVisibility(8);
                    imageView3.setVisibility(0);
                    imageView.setVisibility(8);
                    imageView9.setVisibility(8);
                    imageView10.setVisibility(0);
                }
            });
            imageView10.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC248931 */

                public void onClick(View view) {
                    imageView3.setVisibility(8);
                    imageView4.setVisibility(0);
                    imageView10.setVisibility(8);
                    imageView11.setVisibility(0);
                }
            });
            imageView11.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC249032 */

                public void onClick(View view) {
                    imageView4.setVisibility(8);
                    imageView5.setVisibility(0);
                    imageView11.setVisibility(8);
                    imageView12.setVisibility(0);
                }
            });
            imageView12.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC249133 */

                public void onClick(View view) {
                    imageView5.setVisibility(8);
                    imageView6.setVisibility(0);
                    imageView12.setVisibility(8);
                    imageView13.setVisibility(0);
                }
            });
            imageView13.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC249335 */

                public void onClick(View view) {
                    imageView6.setVisibility(8);
                    imageView7.setVisibility(0);
                    imageView13.setVisibility(8);
                    imageView14.setVisibility(0);
                }
            });
            imageView14.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC249436 */

                public void onClick(View view) {
                    imageView7.setVisibility(8);
                    imageView8.setVisibility(0);
                    imageView14.setVisibility(8);
                    imageView15.setVisibility(0);
                    imageView16.setVisibility(0);
                }
            });
            imageView15.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC249537 */

                public void onClick(View view) {
                    imageView8.setVisibility(8);
                    imageView2.setVisibility(0);
                    imageView15.setVisibility(8);
                    imageView16.setVisibility(8);
                    imageView9.setVisibility(0);
                    imageView.setVisibility(0);
                }
            });
            imageView16.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC249638 */

                public void onClick(View view) {
                    if (eVar != null) {
                        if (GBActivity.webDialog != null) {
                            eVar.mo24348a(GBActivity.webDialog, view);
                        }
                    } else if (GBActivity.webDialog != null) {
                        GBActivity.webDialog.dismiss();
                    }
                }
            });
            int a = DensityUtil.m14127a(this);
            int b = DensityUtil.m14130b(this);
            ViewGroup.LayoutParams layoutParams = imageView2.getLayoutParams();
            int i = layoutParams.width;
            int i2 = layoutParams.height;
            Log.m14398b(TAG, "checkImg:windowWidth=" + a + ",windowHeight=" + b);
            Log.m14398b(TAG, "checkImg:imgWidth=" + i + ",imgHeight=" + i2);
            int i3 = (int) (((float) a) / (((float) i) / ((float) i2)));
            layoutParams.width = a - 0;
            layoutParams.height = b - 0;
            imageView2.setLayoutParams(layoutParams);
            imageView3.setLayoutParams(layoutParams);
            imageView4.setLayoutParams(layoutParams);
            imageView5.setLayoutParams(layoutParams);
            imageView6.setLayoutParams(layoutParams);
            imageView7.setLayoutParams(layoutParams);
            imageView8.setLayoutParams(layoutParams);
            webDialog.setContentView(inflate, new ViewGroup.LayoutParams(a - 0, b - 0));
            webDialog.show();
        }
    }

    public void showWebDialogOld(final AbstractC2521e eVar) {
        if (webDialog != null && webDialog.isShowing()) {
            webDialog.dismiss();
            webDialog = null;
        }
        if (!isFinishing()) {
            webDialog = new Dialog(this, C2425R.C2430style.login_dialog);
            webDialog.setCanceledOnTouchOutside(false);
            webDialog.setCancelable(false);
            View inflate = LayoutInflater.from(GBApplication.instance()).inflate(C2425R.layout.web_dialog, (ViewGroup) null);
            final ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.image_first_steps);
            final ImageView imageView2 = (ImageView) inflate.findViewById(C2425R.C2427id.image_second_steps);
            final ImageView imageView3 = (ImageView) inflate.findViewById(C2425R.C2427id.image_thirdh_steps);
            final ImageView imageView4 = (ImageView) inflate.findViewById(C2425R.C2427id.image_fourth_steps);
            final ImageView imageView5 = (ImageView) inflate.findViewById(C2425R.C2427id.button_next_step_one);
            final ImageView imageView6 = (ImageView) inflate.findViewById(C2425R.C2427id.button_next_step_two);
            final ImageView imageView7 = (ImageView) inflate.findViewById(C2425R.C2427id.button_next_step_three);
            final ImageView imageView8 = (ImageView) inflate.findViewById(C2425R.C2427id.button_start);
            ((ImageView) inflate.findViewById(C2425R.C2427id.button_skip)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC249739 */

                public void onClick(View view) {
                    if (eVar != null) {
                        if (GBActivity.webDialog != null) {
                            eVar.mo24348a(GBActivity.webDialog, view);
                        }
                    } else if (GBActivity.webDialog != null) {
                        GBActivity.webDialog.dismiss();
                    }
                }
            });
            imageView5.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC249940 */

                public void onClick(View view) {
                    imageView.setVisibility(8);
                    imageView2.setVisibility(0);
                    imageView5.setVisibility(8);
                    imageView6.setVisibility(0);
                }
            });
            imageView6.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC250041 */

                public void onClick(View view) {
                    imageView2.setVisibility(8);
                    imageView3.setVisibility(0);
                    imageView6.setVisibility(8);
                    imageView7.setVisibility(0);
                }
            });
            imageView7.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC250142 */

                public void onClick(View view) {
                    imageView3.setVisibility(8);
                    imageView4.setVisibility(0);
                    imageView7.setVisibility(8);
                    imageView8.setVisibility(0);
                }
            });
            imageView8.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.View$OnClickListenerC250243 */

                public void onClick(View view) {
                    if (eVar != null) {
                        if (GBActivity.webDialog != null) {
                            eVar.mo24348a(GBActivity.webDialog, view);
                        }
                    } else if (GBActivity.webDialog != null) {
                        GBActivity.webDialog.dismiss();
                    }
                }
            });
            webDialog.setContentView(inflate, new ViewGroup.LayoutParams(DensityUtil.m14127a(this), DensityUtil.m14130b(this)));
            webDialog.show();
        }
    }

    public int[] getAppCustomIconProjectIdArray() {
        if (CacheAuth.getInstance().getStationCloud().equals("test.gwifi.com.cn")) {
            return new int[]{338, 112, 499, 524, 573, 584, 609, 683, 773, 794, 804, 805, 807, 814, 817, 825, 826, 837, 838, 863, 876, 881, 882, 885, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_SUBTITLE_TIMED_OUT, 906, 910, 911, 912, 927};
        }
        return new int[]{112, 499, 524, 573, 584, 609, 683, 773, 794, 804, 805, 807, 814, 817, 825, 826, 837, 838, 863, 876, 881, 882, 885, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_SUBTITLE_TIMED_OUT, 906, 910, 911, 912, 927};
    }

    public int[] getAppCustomIcomAllProjectIdArray() {
        return new int[]{338, 112, 499, 524, 573, 584, 609, 683, 773, 794, 804, 805, 807, 814, 817, 825, 826, 837, 838, 863, 876, 881, 882, 885, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_SUBTITLE_TIMED_OUT, 906, 910, 911, 912, 927};
    }

    public void switchIcon(int i, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        ComponentName componentName;
        ComponentName componentName2;
        int i6;
        int i7;
        ComponentName componentName3;
        int i8;
        ComponentName componentName4;
        int i9;
        try {
            if (!SystemUtil.m14531e()) {
                return;
            }
            if (z) {
                checkAppCustomRequest = HttpUtil.m14306c(this.okReqStrHandler, TAG, this);
                return;
            }
            int[] appCustomIconProjectIdArray = getAppCustomIconProjectIdArray();
            if (i == 0 || CheckUtil.m14082a(i, appCustomIconProjectIdArray)) {
                i3 = i;
            } else {
                i3 = 0;
            }
            if (i2 == 0 || CheckUtil.m14082a(i2, appCustomIconProjectIdArray)) {
                i4 = i2;
            } else {
                i4 = 0;
            }
            if (i3 != i4) {
                if (i3 > 0 && getResources().getIdentifier("icon_" + i3, "drawable", getPackageName()) <= 0) {
                    i3 = 0;
                }
                if (i4 <= 0 || getResources().getIdentifier("icon_" + i4, "drawable", getPackageName()) > 0) {
                    i5 = i4;
                } else {
                    i5 = 0;
                }
                if (i3 != i5) {
                    if (i5 <= 0 || i3 > 0) {
                    }
                    int intValue = CacheAccount.getInstance().getHotspotGroupIdShow().intValue();
                    String str = "com.gbcom.gwifi" + ".functions.loading.LaunchActivity";
                    PackageManager packageManager = getPackageManager();
                    if (intValue == 0) {
                        componentName = null;
                        componentName2 = new ComponentName(getBaseContext(), str);
                        i6 = 0;
                        i7 = 2;
                    } else {
                        componentName = new ComponentName(getBaseContext(), "com.gbcom.gwifi" + ".icon_tag_" + intValue);
                        componentName2 = null;
                        i6 = 2;
                        i7 = 0;
                    }
                    if (i5 == 0) {
                        componentName3 = new ComponentName(getBaseContext(), str);
                        i8 = 1;
                        componentName4 = null;
                        i9 = 0;
                    } else {
                        componentName3 = componentName2;
                        i8 = i7;
                        componentName4 = new ComponentName(getBaseContext(), "com.gbcom.gwifi" + ".icon_tag_" + i5);
                        i9 = 1;
                    }
                    if (componentName != null) {
                        if (packageManager.getComponentEnabledSetting(componentName) != i6) {
                            packageManager.setComponentEnabledSetting(componentName, i6, 1);
                        } else {
                            int[] appCustomIcomAllProjectIdArray = getAppCustomIcomAllProjectIdArray();
                            int i10 = 0;
                            while (true) {
                                if (i10 >= appCustomIcomAllProjectIdArray.length) {
                                    break;
                                }
                                if (!(intValue == appCustomIcomAllProjectIdArray[i10] || i5 == appCustomIcomAllProjectIdArray[i10])) {
                                    ComponentName componentName5 = new ComponentName(getBaseContext(), "com.gbcom.gwifi" + ".icon_tag_" + appCustomIcomAllProjectIdArray[i10]);
                                    if (packageManager.getComponentEnabledSetting(componentName5) != i6) {
                                        packageManager.setComponentEnabledSetting(componentName5, i6, 1);
                                        break;
                                    }
                                }
                                i10++;
                            }
                            if (i10 == appCustomIcomAllProjectIdArray.length && i5 > 0) {
                                ComponentName componentName6 = new ComponentName(getBaseContext(), str);
                                if (packageManager.getComponentEnabledSetting(componentName6) != i6) {
                                    packageManager.setComponentEnabledSetting(componentName6, i6, 1);
                                }
                            }
                        }
                    } else if (componentName3 != null) {
                        if (packageManager.getComponentEnabledSetting(componentName3) != i8) {
                            packageManager.setComponentEnabledSetting(componentName3, i8, 1);
                        } else {
                            int[] appCustomIcomAllProjectIdArray2 = getAppCustomIcomAllProjectIdArray();
                            int i11 = 0;
                            while (true) {
                                if (i11 >= appCustomIcomAllProjectIdArray2.length) {
                                    break;
                                }
                                if (!(intValue == appCustomIcomAllProjectIdArray2[i11] || i5 == appCustomIcomAllProjectIdArray2[i11])) {
                                    ComponentName componentName7 = new ComponentName(getBaseContext(), "com.gbcom.gwifi" + ".icon_tag_" + appCustomIcomAllProjectIdArray2[i11]);
                                    if (packageManager.getComponentEnabledSetting(componentName7) != i6) {
                                        packageManager.setComponentEnabledSetting(componentName7, i6, 1);
                                        break;
                                    }
                                }
                                i11++;
                            }
                        }
                    }
                    if (componentName4 != null) {
                        if (packageManager.getComponentEnabledSetting(componentName4) != i9) {
                            packageManager.setComponentEnabledSetting(componentName4, i9, 1);
                        }
                    } else if (!(componentName3 == null || packageManager.getComponentEnabledSetting(componentName3) == i8)) {
                        packageManager.setComponentEnabledSetting(componentName3, i8, 1);
                    }
                    CacheAccount.getInstance().setHotspotGroupIdShow(i5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkPermissions(Context context, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return true;
        }
        boolean z = true;
        for (String str : strArr) {
            z &= ContextCompat.checkSelfPermission(context, str) == 0;
        }
        return z;
    }

    @Override // android.support.p009v4.app.ActivityCompat.OnRequestPermissionsResultCallback, android.support.p009v4.app.FragmentActivity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        boolean z = true;
        for (int i2 : iArr) {
            z &= i2 == 0;
        }
        if (z) {
            this.isShowPermissionsDialog = false;
        } else {
            this.isShowPermissionsDialog = true;
        }
        PermissionsManager.m4670a().mo21378a(strArr, iArr);
    }

    public boolean checkReadPermission(String str, int i) {
        if (ContextCompat.checkSelfPermission(this, str) == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{str}, i);
        return false;
    }

    public void callPhone(final String str) {
        String[] strArr = {"android.permission.CALL_PHONE"};
        String str2 = "在设置-应用-" + SystemUtil.m14528d() + "-权限中开启允许拨号权限，以正常使用功能";
        if (checkPermissions(this, strArr)) {
            Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(WebView.SCHEME_TEL + str));
            intent.setFlags(268435456);
            startActivity(intent);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CALL_PHONE")) {
            GBGlobalConfig.m10510c().mo24391a((Activity) this, str2);
        } else {
            PermissionsManager.m4670a().mo21377a(this, strArr, new PermissionsResultAction() {
                /* class com.gbcom.gwifi.base.activity.GBActivity.C250344 */

                @Override // com.anthonycr.grant.PermissionsResultAction
                public void onGranted() {
                    Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(WebView.SCHEME_TEL + str));
                    intent.setFlags(268435456);
                    GBActivity.this.startActivity(intent);
                }

                @Override // com.anthonycr.grant.PermissionsResultAction
                public void onDenied(String str) {
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p009v4.app.FragmentActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 10086) {
            doInstallApk(apkFilePath);
        }
    }

    /* access modifiers changed from: protected */
    public void checkAgreementYes() {
        boolean z;
        String str;
        String str2 = null;
        boolean z2 = false;
        if (!SystemUtil.m14533f()) {
            if (CacheApp.getInstance().getAgreementYes()) {
                long agreementTime = CacheApp.getInstance().getAgreementTime();
                long agrtServiceUpdateTime = CacheApp.getInstance().getAgrtServiceUpdateTime();
                long agrtPrivacyUpdateTime = CacheApp.getInstance().getAgrtPrivacyUpdateTime();
                if (agreementTime < agrtServiceUpdateTime) {
                    z = true;
                } else {
                    z = false;
                }
                if (agreementTime < agrtPrivacyUpdateTime) {
                    z2 = true;
                }
            } else {
                z2 = true;
                z = true;
            }
            if (z || z2) {
                if (!z || z2) {
                    str = null;
                } else {
                    str = "服务协议";
                    str2 = "\"用户服务协议\"";
                }
                if (!z && z2) {
                    str = "隐私政策";
                    str2 = "\"用户隐私保护政策\"";
                }
                if (z && z2) {
                    str = "服务协议与隐私政策";
                    str2 = "\"用户服务协议\"、\"用户隐私保护政策\"";
                }
                Intent intent = new Intent(GBApplication.instance(), DialogActivity.class);
                intent.putExtra("title", str);
                intent.putExtra("content", "请您务必仔细阅读以下" + str2 + "等内容，并审慎点击同意；如您点击同意，则表示您充分理解并同意协议条款内容。");
                intent.putExtra("positiveText", "不同意并退出");
                intent.putExtra("negativeText", "同意");
                intent.putExtra("showService", z);
                intent.putExtra("showPrivacy", z2);
                intent.putExtra("type", 5);
                intent.setFlags(268435456);
                GBApplication.instance().startActivity(intent);
            }
        }
    }

    @Override // android.support.p009v4.app.FragmentActivity
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
}
