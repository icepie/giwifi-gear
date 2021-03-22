package com.gbcom.gwifi.functions.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.p009v4.app.ActivityCompat;
import android.support.p009v4.content.FileProvider;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.functions.js2app.X5Js2AppUtil;
import com.gbcom.gwifi.functions.webview.LoadHttpFileAsyncTask;
import com.gbcom.gwifi.functions.wifi.WiFiService;
import com.gbcom.gwifi.functions.wifi.entity.CheckResult;
import com.gbcom.gwifi.third.webview.p255x5.X5WebView;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.StorageUtils;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.p257b.StatusBarUtil;
import com.gbcom.gwifi.widget.LoadingView;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsReaderView;
import com.tencent.smtt.sdk.URLUtil;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.umeng.message.proguard.MessageStore;
import com.umeng.p377fb.common.C6299a;
import java.net.MalformedURLException;
import java.net.URL;
import java.p456io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.apache.xalan.templates.Constants;
import org.apache.xpath.compiler.PsuedoNames;

public class BackX5WebViewActivity extends GBActivity {
    public static final int MSG_FIND_TIMES = 3;
    public static final int MSG_INIT_UI = 1;
    public static final int MSG_INIT_UI_FILE = 4;
    public static final int MSG_OPEN_TEST_URL = 0;
    public static final int MSG_WILL_AUTH_MSG = 2;
    public static final int OPEN_FILE_CHOOSER_ACTIVITY = 2;
    public static final int OPEN_FILE_CHOOSER_ACTIVITY_UP = 3;

    /* renamed from: U */
    private static final int f12669U = 100;

    /* renamed from: V */
    private static final int f12670V = 120;

    /* renamed from: W */
    private static final int f12671W = 101;
    public static String cookie = null;

    /* renamed from: l */
    private static final String f12672l = "file:///android_asset/test.html";

    /* renamed from: m */
    private static final String f12673m = "BackX5WebViewActivity";

    /* renamed from: n */
    private static final int f12674n = 14;

    /* renamed from: A */
    private LinearLayout f12675A;

    /* renamed from: B */
    private boolean f12676B;

    /* renamed from: C */
    private int f12677C;

    /* renamed from: D */
    private int f12678D;

    /* renamed from: E */
    private int f12679E;

    /* renamed from: F */
    private TextView f12680F;

    /* renamed from: G */
    private boolean f12681G = false;

    /* renamed from: H */
    private boolean f12682H = false;

    /* renamed from: I */
    private ValueCallback<Uri> f12683I;

    /* renamed from: J */
    private ValueCallback<Uri[]> f12684J;

    /* renamed from: K */
    private View f12685K;

    /* renamed from: L */
    private FrameLayout f12686L;

    /* renamed from: M */
    private IX5WebChromeClient.CustomViewCallback f12687M;

    /* renamed from: N */
    private Button f12688N;

    /* renamed from: O */
    private Button f12689O;

    /* renamed from: P */
    private GwifiAuthObjJavascriptInterface f12690P;

    /* renamed from: Q */
    private LoadingView.AbstractC3488a f12691Q = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C33491 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            BackX5WebViewActivity.this.startWebDrawable();
            BackX5WebViewActivity.this.f12721u = "";
            BackX5WebViewActivity.this.f12706c.reload();
        }
    };

    /* renamed from: R */
    private BroadcastReceiver f12692R = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C335111 */

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constant.f13235bJ)) {
                BackX5WebViewActivity.this.m13744a((BackX5WebViewActivity) BackX5WebViewActivity.this.f12719s);
            }
        }
    };

    /* renamed from: S */
    private BroadcastReceiver f12693S = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C335212 */

        public void onReceive(Context context, Intent intent) {
            BackX5WebViewActivity.this.finish();
        }
    };

    /* renamed from: T */
    private BroadcastReceiver f12694T = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C335313 */

        public void onReceive(Context context, Intent intent) {
            CheckResult checkResult;
            if (intent != null && intent.getAction().equals(Constant.f13237bL) && (checkResult = (CheckResult) intent.getExtras().getParcelable("check")) != null) {
                if (!checkResult.isGiwifi()) {
                    BackX5WebViewActivity.this.f12726z.mo28299a("请连接" + BackX5WebViewActivity.this.getResources().getString(C2425R.C2429string.wifi_name) + "网络浏览");
                    BackX5WebViewActivity.this.errWebDrawable();
                } else if (checkResult.getGwReqState().intValue() != 2) {
                    BackX5WebViewActivity.this.f12726z.mo28299a("请认证后浏览");
                    BackX5WebViewActivity.this.errWebDrawable();
                } else if (BackX5WebViewActivity.this.f12726z != null && BackX5WebViewActivity.this.f12726z.getVisibility() == 0) {
                    Log.m14398b(BackX5WebViewActivity.f12673m, "NET_CHECK_RESULT_BROADCAST_ACTION");
                    BackX5WebViewActivity.this.stopWebDrawable();
                    if (BackX5WebViewActivity.this.f12706c != null) {
                        BackX5WebViewActivity.this.startWebDrawable();
                        BackX5WebViewActivity.this.f12721u = "";
                        BackX5WebViewActivity.this.f12706c.reload();
                    }
                }
            }
        }
    };

    /* renamed from: X */
    private boolean f12695X = false;

    /* renamed from: Y */
    private boolean f12696Y = false;

    /* renamed from: Z */
    private Uri f12697Z;

    /* renamed from: a */
    boolean[] f12698a = {true, true, true, true, false, false, true};

    /* renamed from: aa */
    private View.OnClickListener f12699aa = new View.OnClickListener() {
        /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.View$OnClickListenerC33636 */

        public void onClick(View view) {
            switch (view.getId()) {
                case C2425R.C2427id.web_linear /*{ENCODED_INT: 2131756202}*/:
                    ((ClipboardManager) BackX5WebViewActivity.this.getSystemService("clipboard")).setText(BackX5WebViewActivity.this.f12719s);
                    Toast.makeText(BackX5WebViewActivity.this.getApplicationContext(), "已复制到剪切板", 0).show();
                    if (BackX5WebViewActivity.this.f12714k != null && BackX5WebViewActivity.this.f12714k.isShowing()) {
                        BackX5WebViewActivity.this.f12714k.dismiss();
                        BackX5WebViewActivity.this.f12714k = null;
                        return;
                    }
                    return;
                case C2425R.C2427id.webView1 /*{ENCODED_INT: 2131756203}*/:
                case C2425R.C2427id.web_view_back /*{ENCODED_INT: 2131756205}*/:
                default:
                    return;
                case C2425R.C2427id.web_view_linear /*{ENCODED_INT: 2131756204}*/:
                    if (BackX5WebViewActivity.this.f12706c.canGoBack()) {
                        BackX5WebViewActivity.this.f12706c.goBack();
                        return;
                    } else {
                        BackX5WebViewActivity.this.finish();
                        return;
                    }
                case C2425R.C2427id.web_view_close /*{ENCODED_INT: 2131756206}*/:
                    BackX5WebViewActivity.this.finish();
                    return;
            }
        }
    };

    /* renamed from: ab */
    private String f12700ab;

    /* renamed from: ac */
    private String f12701ac;

    /* renamed from: ad */
    private final int f12702ad = 0;

    /* renamed from: ae */
    private int f12703ae = 0;

    /* renamed from: af */
    private Handler f12704af = new Handler() {
        /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.HandlerC335010 */

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (BackX5WebViewActivity.this.f12715o) {
                        if (BackX5WebViewActivity.this.f12706c != null) {
                            BackX5WebViewActivity.this.f12706c.loadUrl(BackX5WebViewActivity.f12672l);
                        }
                        BackX5WebViewActivity.m13792w(BackX5WebViewActivity.this);
                        break;
                    } else {
                        return;
                    }
                case 1:
                    BackX5WebViewActivity.this.m13758d();
                    break;
                case 2:
                    if (BackX5WebViewActivity.this.f12706c != null) {
                        BackX5WebViewActivity.this.f12706c.setVisibility(4);
                        break;
                    }
                    break;
                case 3:
                    if (BackX5WebViewActivity.this.f12678D != 0) {
                        BackX5WebViewActivity.m13726C(BackX5WebViewActivity.this);
                        BackX5WebViewActivity.this.f12680F.setText("浏览领旺豆,还剩" + BackX5WebViewActivity.this.f12678D + "秒,即可领取" + Constant.f13309cr + "...");
                        removeMessages(3);
                        sendEmptyMessageDelayed(3, 1000);
                        break;
                    } else {
                        BackX5WebViewActivity.this.f12675A.setVisibility(8);
                        removeMessages(3);
                        Intent intent = new Intent(GBApplication.GWIFI_CHANGE_BALANCE_ACTION);
                        intent.putExtra("balance", BackX5WebViewActivity.this.f12677C);
                        intent.putExtra("id", BackX5WebViewActivity.this.f12679E);
                        GBApplication.instance().sendBroadcast(intent);
                        break;
                    }
                case 4:
                    BackX5WebViewActivity.this.m13762e();
                    break;
            }
            super.handleMessage(message);
        }
    };

    /* renamed from: b */
    TbsReaderView f12705b;

    /* renamed from: c */
    private X5WebView f12706c;

    /* renamed from: d */
    private ViewGroup f12707d;

    /* renamed from: e */
    private boolean f12708e = false;

    /* renamed from: f */
    private TextView f12709f;

    /* renamed from: g */
    private ImageView f12710g;

    /* renamed from: h */
    private LinearLayout f12711h;

    /* renamed from: i */
    private ImageView f12712i;

    /* renamed from: j */
    private ProgressBar f12713j;

    /* renamed from: k */
    private PopupWindow f12714k;

    /* renamed from: o */
    private boolean f12715o = false;

    /* renamed from: p */
    private final int f12716p = 120;

    /* renamed from: q */
    private final int f12717q = 255;

    /* renamed from: r */
    private ValueCallback<Uri> f12718r;

    /* renamed from: s */
    private String f12719s = "";

    /* renamed from: t */
    private String f12720t = "";

    /* renamed from: u */
    private String f12721u = "";

    /* renamed from: v */
    private URL f12722v;

    /* renamed from: w */
    private String f12723w = "";

    /* renamed from: x */
    private int f12724x = 0;

    /* renamed from: y */
    private int f12725y = 0;

    /* renamed from: z */
    private LoadingView f12726z;

    /* renamed from: C */
    static /* synthetic */ int m13726C(BackX5WebViewActivity backX5WebViewActivity) {
        int i = backX5WebViewActivity.f12678D;
        backX5WebViewActivity.f12678D = i - 1;
        return i;
    }

    /* renamed from: w */
    static /* synthetic */ int m13792w(BackX5WebViewActivity backX5WebViewActivity) {
        int i = backX5WebViewActivity.f12703ae;
        backX5WebViewActivity.f12703ae = i + 1;
        return i;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13744a(String str) {
        if (this.f12708e) {
            this.f12708e = false;
            openBackWebView(str, this.f12723w);
        }
    }

    /* renamed from: a */
    private void m13732a() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.f12719s = intent.getStringExtra("url");
                this.f12720t = this.f12719s;
                Log.m14398b(f12673m, "url..." + this.f12719s);
                this.f12722v = new URL(this.f12719s);
                this.f12723w = intent.getStringExtra("title");
                this.f12724x = intent.getIntExtra("hidden_title_bar", 0);
                this.f12725y = intent.getIntExtra("is_local", 0);
                if (intent.getBooleanExtra("sign", false)) {
                    GBApplication.instance().sendBroadcast(new Intent(GBApplication.GWIFI_SIGN_ACTION));
                }
                setActivityName(this.f12723w + "网页界面");
                if (this.f12724x == 0) {
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception | NullPointerException e2) {
            }
        }
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        setActivityName("Web界面");
        m13732a();
        super.onCreate(bundle);
        StatusBarUtil.m14195c(this, 2131624113);
        setContentView(C2425R.layout.web_view_x5_activity);
        this.f12726z = (LoadingView) findViewById(C2425R.C2427id.loading_view);
        this.f12726z.mo28298a(this.f12691Q);
        startWebDrawable();
        m13769h();
        Log.m14398b(f12673m, "BackX5WebViewActivity:OnCreate url:" + this.f12719s);
        m13758d();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        Log.m14398b(f12673m, "onResume()");
        super.onResume();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13742a(WebView webView) {
    }

    /* renamed from: b */
    private void m13749b() {
        this.f12713j = (ProgressBar) findViewById(C2425R.C2427id.WebViewProgress);
        this.f12713j.setMax(100);
        if (this.f12724x != 0) {
            this.f12713j.setVisibility(8);
        }
    }

    public void startWebDrawable() {
        Log.m14398b(f12673m, "startWebDrawable");
        this.f12726z.mo28307i();
        this.f12726z.mo28313o();
        this.f12726z.mo28315q();
        if (this.f12726z.mo28310l()) {
            this.f12726z.mo28309k();
        }
        this.f12726z.setVisibility(0);
        this.f12726z.mo28297a();
        this.f12726z.mo28302d();
        this.f12726z.mo28304f();
        this.f12726z.mo28300b();
        Log.m14398b(f12673m, "startWebDrawable end");
    }

    public void errWebDrawable() {
        Log.m14398b(f12673m, "errWebDrawable");
        this.f12726z.setVisibility(0);
        this.f12726z.mo28303e();
        this.f12726z.mo28301c();
        this.f12726z.mo28305g();
        this.f12726z.mo28306h();
        this.f12726z.mo28308j();
        this.f12726z.mo28312n();
        this.f12726z.mo28314p();
        Log.m14398b(f12673m, "errWebDrawable end");
    }

    public void stopWebDrawable() {
        Log.m14398b(f12673m, "stopWebDrawable");
        this.f12726z.setVisibility(8);
        this.f12726z.mo28301c();
        Log.m14398b(f12673m, "stopWebDrawable end");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.webview.BackX5WebViewActivity$a */
    public static class C3367a extends FrameLayout {
        public C3367a(Context context) {
            super(context);
            setBackgroundColor(context.getResources().getColor(17170444));
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            return true;
        }
    }

    /* renamed from: a */
    private void m13745a(boolean z) {
        int i;
        if (z) {
            i = 0;
        } else {
            i = 1024;
        }
        getWindow().setFlags(i, 1024);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13735a(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        Log.m14400c(f12673m, "show customview");
        setRequestedOrientation(0);
        getWindow().getDecorView();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.f12686L = new C3367a(this);
        this.f12686L.addView(view, layoutParams);
        ((FrameLayout) getWindow().getDecorView()).addView(this.f12686L, layoutParams);
        this.f12685K = view;
        m13745a(false);
        this.f12687M = customViewCallback;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m13754c() {
        if (this.f12685K != null) {
            Log.m14400c(f12673m, "hide customview");
            setRequestedOrientation(1);
            m13745a(true);
            ((FrameLayout) getWindow().getDecorView()).removeView(this.f12686L);
            this.f12686L = null;
            this.f12685K = null;
            this.f12687M.onCustomViewHidden();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m13758d() {
        Log.m14398b(f12673m, "init()");
        this.f12707d = (ViewGroup) findViewById(C2425R.C2427id.webView1);
        this.f12706c = (X5WebView) findViewById(2131755371);
        registerForContextMenu(this.f12706c);
        m13749b();
        this.f12706c.setWebViewClient(new WebViewClient() {
            /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C335414 */

            @Override // com.tencent.smtt.sdk.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Log.m14400c(BackX5WebViewActivity.f12673m, "url:" + str);
                if (str.endsWith(".pdf") || str.endsWith(".pptx") || str.endsWith(".docx") || str.endsWith(".xlsx")) {
                    BackX5WebViewActivity.this.m13759d((BackX5WebViewActivity) str);
                    return true;
                } else if (X5Js2AppUtil.processURL(webView, str)) {
                    return true;
                } else {
                    BackX5WebViewActivity.this.hideOptionBtnCustom2();
                    BackX5WebViewActivity.this.hideOptionBtnCustom1();
                    Uri parse = Uri.parse(str);
                    Log.m14400c(BackX5WebViewActivity.f12673m, "uri:" + ((Object) parse));
                    if ("alipays".equals(parse.getScheme()) || "weixin".equals(parse.getScheme())) {
                        try {
                            BackX5WebViewActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                            return true;
                        } catch (ActivityNotFoundException e) {
                            return true;
                        }
                    } else if (!str.startsWith("taobao://") || !SystemUtil.m14522a(GBApplication.instance(), AgooConstants.TAOBAO_PACKAGE)) {
                        String decode = Uri.decode(str);
                        if (decode.startsWith(WebView.SCHEME_TEL)) {
                            BackX5WebViewActivity.this.callPhone(decode.substring(4));
                            return true;
                        } else if (decode.startsWith("sms:")) {
                            BackX5WebViewActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(decode)));
                            return true;
                        } else if (decode.equals("gbcom://com.gbcom.gwifi") || decode.equals("gbcom://com.gbcom.gwifi.school")) {
                            BackX5WebViewActivity.this.finish();
                            return true;
                        } else if (decode.startsWith("http:") || decode.startsWith("https:") || decode.startsWith("ftp")) {
                            return false;
                        } else {
                            try {
                                Intent intent = new Intent("android.intent.action.VIEW", parse);
                                List<ResolveInfo> queryIntentActivities = GBApplication.instance().getPackageManager().queryIntentActivities(intent, 65536);
                                if (queryIntentActivities == null || queryIntentActivities.size() == 0) {
                                    Log.m14403e(BackX5WebViewActivity.f12673m, "queryIntentActivity empty");
                                    return true;
                                }
                                Log.m14398b(BackX5WebViewActivity.f12673m, "queryIntentActivity not empty");
                                BackX5WebViewActivity.this.startActivity(intent);
                                return true;
                            } catch (ActivityNotFoundException e2) {
                                return true;
                            }
                        }
                    } else {
                        try {
                            Intent intent2 = new Intent();
                            intent2.setAction("android.intent.action.VIEW");
                            intent2.setData(parse);
                            BackX5WebViewActivity.this.startActivity(intent2);
                            return true;
                        } catch (Exception e3) {
                            return true;
                        }
                    }
                }
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                BackX5WebViewActivity.cookie = CookieManager.getInstance().getCookie(str);
                if (webView != null) {
                    String title = webView.getTitle();
                    Log.m14398b(BackX5WebViewActivity.f12673m, "viewTitle:" + title);
                    if (!(C3467s.m14513e(title) || title.indexOf("http://") == 0 || title.indexOf("https://") == 0 || title.indexOf("gwifi.com.cn/") == 0 || BackX5WebViewActivity.this.f12724x != 0)) {
                        BackX5WebViewActivity.this.f12709f.setText(title);
                    }
                    if (Integer.parseInt(Build.VERSION.SDK) >= 16) {
                        BackX5WebViewActivity.this.m13742a((BackX5WebViewActivity) webView);
                    }
                    BackX5WebViewActivity.this.f12713j.setVisibility(8);
                    if (BackX5WebViewActivity.this.f12681G || str.equals(BackX5WebViewActivity.this.f12721u)) {
                        BackX5WebViewActivity.this.errWebDrawable();
                        BackX5WebViewActivity.this.f12681G = false;
                        BackX5WebViewActivity.this.f12682H = true;
                    }
                    if (BackX5WebViewActivity.this.f12676B && BackX5WebViewActivity.this.f12677C > 0 && !BackX5WebViewActivity.this.f12682H) {
                        BackX5WebViewActivity.this.f12676B = false;
                        BackX5WebViewActivity.this.f12682H = false;
                        BackX5WebViewActivity.this.m13770i();
                    }
                }
            }

            /* renamed from: a */
            public void mo27390a(WebView webView, String str) {
            }
        });
        this.f12706c.setWebChromeClient(new WebChromeClient() {
            /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C335515 */

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
                BackX5WebViewActivity.this.m13741a((BackX5WebViewActivity) valueCallback, (ValueCallback) str);
            }

            /* renamed from: a */
            public void mo27391a(ValueCallback<Uri> valueCallback) {
                BackX5WebViewActivity.this.m13741a((BackX5WebViewActivity) valueCallback, (ValueCallback) "");
            }

            /* renamed from: a */
            public void mo27392a(ValueCallback<Uri> valueCallback, String str) {
                BackX5WebViewActivity.this.m13741a((BackX5WebViewActivity) valueCallback, (ValueCallback) str);
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                int i;
                BackX5WebViewActivity.this.f12684J = valueCallback;
                if (Build.VERSION.SDK_INT >= 21) {
                    i = fileChooserParams.getMode();
                } else {
                    i = 0;
                }
                Log.m14398b(BackX5WebViewActivity.f12673m, "chooserMode:" + i);
                String[] strArr = new String[0];
                if (Build.VERSION.SDK_INT >= 21) {
                    strArr = fileChooserParams.getAcceptTypes();
                }
                if (strArr != null && strArr.length > 0) {
                    Log.m14398b(BackX5WebViewActivity.f12673m, "acceptTypes:" + strArr[0]);
                    if (strArr[0].equals("image/*")) {
                        BackX5WebViewActivity.this.f12696Y = true;
                    }
                    if (strArr[0].equals("video/*")) {
                        BackX5WebViewActivity.this.f12695X = true;
                    }
                }
                if (BackX5WebViewActivity.this.f12695X) {
                    BackX5WebViewActivity.this.m13778m();
                } else if (BackX5WebViewActivity.this.f12696Y) {
                    BackX5WebViewActivity.this.m13733a((BackX5WebViewActivity) i);
                } else {
                    Intent intent = new Intent("android.intent.action.GET_CONTENT");
                    intent.addCategory("android.intent.category.OPENABLE");
                    intent.setType("*/*");
                    BackX5WebViewActivity.this.startActivityForResult(Intent.createChooser(intent, "文件选择"), 3);
                }
                return true;
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
                return super.onJsConfirm(webView, str, str2, jsResult);
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
                BackX5WebViewActivity.this.m13735a((BackX5WebViewActivity) view, (View) customViewCallback);
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onHideCustomView() {
                BackX5WebViewActivity.this.m13754c();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                Log.m14398b(BackX5WebViewActivity.f12673m, "mWebView.getProgress()=" + BackX5WebViewActivity.this.f12706c.getProgress());
                Log.m14398b(BackX5WebViewActivity.f12673m, "newProgress=" + i);
                if (i >= 70) {
                    BackX5WebViewActivity.this.stopWebDrawable();
                }
                if (i >= 100) {
                    BackX5WebViewActivity.this.f12713j.setVisibility(8);
                    return;
                }
                if (BackX5WebViewActivity.this.f12713j.getVisibility() == 8 && BackX5WebViewActivity.this.f12724x == 0) {
                    BackX5WebViewActivity.this.f12713j.setVisibility(0);
                }
                try {
                    BackX5WebViewActivity.this.f12713j.setProgress(BackX5WebViewActivity.this.f12706c.getProgress());
                } catch (Exception e) {
                }
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissionsCallback geolocationPermissionsCallback) {
                geolocationPermissionsCallback.invoke(str, true, false);
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                return super.onJsAlert(null, str, str2, jsResult);
            }
        });
        this.f12706c.setDownloadListener(new C3368b());
        WebSettings settings = this.f12706c.getSettings();
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportMultipleWindows(false);
        settings.setAppCachePath(getDir("appcache", 0).getPath());
        settings.setDatabasePath(getDir("databases", 0).getPath());
        settings.setGeolocationDatabasePath(getFilesDir().getPath());
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setCacheMode(-1);
        }
        settings.setSaveFormData(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        this.f12690P = new GwifiAuthObjJavascriptInterface(this);
        this.f12706c.addJavascriptInterface(this.f12690P, "auth_obj");
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f12722v == null) {
            this.f12706c.loadUrl(f12672l);
        } else {
            this.f12706c.loadUrl(this.f12722v.toString());
        }
        try {
            if (Integer.parseInt(Build.VERSION.SDK) >= 11) {
                getWindow().setFlags(16777216, 16777216);
            }
        } catch (Exception e) {
        }
        Log.m14398b("time-cost", "cost time: " + (System.currentTimeMillis() - currentTimeMillis));
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m13762e() {
        m13759d(this.f12719s);
    }

    public void userLogin() {
        this.f12708e = true;
        if (this.f12704af != null) {
            this.f12704af.sendMessage(this.f12704af.obtainMessage(2));
        }
        GBGlobalConfig.m10510c().mo24401a(new CallBack() {
            /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C335616 */

            @Override // com.gbcom.gwifi.base.p234c.CallBack
            /* renamed from: a */
            public void mo24437a(Object obj) {
                if (obj != null) {
                    if (obj instanceof Boolean) {
                        if (((Boolean) obj).booleanValue()) {
                            BackX5WebViewActivity.this.m13744a((BackX5WebViewActivity) BackX5WebViewActivity.this.f12720t);
                        } else if (BackX5WebViewActivity.this.f12706c != null) {
                            BackX5WebViewActivity.this.f12681G = true;
                            BackX5WebViewActivity.this.f12706c.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
                            BackX5WebViewActivity.this.errWebDrawable();
                        }
                    }
                    if ((obj instanceof Integer) && ((Integer) obj).intValue() == 200 && BackX5WebViewActivity.this.f12706c != null) {
                        BackX5WebViewActivity.this.f12681G = true;
                        BackX5WebViewActivity.this.f12706c.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
                        BackX5WebViewActivity.this.errWebDrawable();
                    }
                }
            }
        });
    }

    /* renamed from: com.gbcom.gwifi.functions.webview.BackX5WebViewActivity$b */
    public class C3368b implements DownloadListener {

        /* renamed from: a */
        Context f12751a;

        /* renamed from: b */
        long f12752b;

        /* renamed from: c */
        DownloadManager f12753c;

        /* renamed from: e */
        private BroadcastReceiver f12755e = new BroadcastReceiver() {
            /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C3368b.C33691 */

            public void onReceive(Context context, Intent intent) {
                Log.m14398b("MyWebViewDownLoadListener:", "ACTION_DOWNLOAD_COMPLETE");
                C3368b.this.f12751a = context;
                C3368b.this.m13804a();
            }
        };

        public C3368b() {
        }

        @Override // com.tencent.smtt.sdk.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            Log.m14400c(BackX5WebViewActivity.f12673m, "MyWebViewDownLoadListener:" + str);
            if (str.endsWith(".pdf") || str.endsWith(".pptx") || str.endsWith(".docx") || str.endsWith(".xlsx")) {
                BackX5WebViewActivity.this.m13759d((BackX5WebViewActivity) str);
            } else {
                mo27408a(str, str3, str4);
            }
        }

        /* renamed from: a */
        private void m13806a(String str) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setData(Uri.parse(str));
            BackX5WebViewActivity.this.startActivity(intent);
        }

        /* renamed from: b */
        private void m13808b(String str, String str2, String str3) {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(1);
            request.setAllowedOverMetered(false);
            request.setVisibleInDownloadsUi(false);
            request.setAllowedOverRoaming(true);
            request.setAllowedNetworkTypes(2);
            String guessFileName = URLUtil.guessFileName(str, str2, str3);
            Log.m14398b("fileName:{}", guessFileName);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, guessFileName);
            Log.m14398b("downloadId:{}", "" + ((DownloadManager) BackX5WebViewActivity.this.getSystemService("download")).enqueue(request));
        }

        /* renamed from: com.gbcom.gwifi.functions.webview.BackX5WebViewActivity$b$a */
        private class C3370a extends BroadcastReceiver {
            private C3370a() {
            }

            public void onReceive(Context context, Intent intent) {
                Log.m14398b("onReceive. intent:{}", intent != null ? intent.toUri(0) : "");
                if (intent != null && "android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
                    long longExtra = intent.getLongExtra("extra_download_id", -1);
                    Log.m14398b("downloadId:{}", "" + longExtra);
                    DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
                    String mimeTypeForDownloadedFile = downloadManager.getMimeTypeForDownloadedFile(longExtra);
                    Log.m14398b("getMimeTypeForDownloadedFile:{}", mimeTypeForDownloadedFile);
                    if (TextUtils.isEmpty(mimeTypeForDownloadedFile)) {
                        mimeTypeForDownloadedFile = "*/*";
                    }
                    Uri uriForDownloadedFile = downloadManager.getUriForDownloadedFile(longExtra);
                    Log.m14398b("UriForDownloadedFile:{}", uriForDownloadedFile.toString());
                    if (uriForDownloadedFile != null) {
                        Intent intent2 = new Intent("android.intent.action.VIEW");
                        intent2.setDataAndType(uriForDownloadedFile, mimeTypeForDownloadedFile);
                        context.startActivity(intent2);
                    }
                }
            }
        }

        /* renamed from: a */
        public void mo27408a(String str, String str2, String str3) {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(1);
            request.setAllowedOverMetered(false);
            request.setVisibleInDownloadsUi(false);
            request.setAllowedOverRoaming(true);
            request.setAllowedNetworkTypes(2);
            request.setDestinationInExternalPublicDir(BackX5WebViewActivity.getStoragePath(), URLUtil.guessFileName(str, str2, str3));
            Log.m14398b("MyWebViewDownLoadListener:", "registerReceiver");
            BackX5WebViewActivity.this.registerReceiver(this.f12755e, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            Log.m14398b("MyWebViewDownLoadListener:", "beginDownload");
            this.f12753c = (DownloadManager) BackX5WebViewActivity.this.getSystemService("download");
            this.f12752b = this.f12753c.enqueue(request);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        /* renamed from: a */
        private void m13804a() {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(this.f12752b);
            Cursor query2 = this.f12753c.query(query);
            if (query2.moveToFirst()) {
                int i = query2.getInt(query2.getColumnIndex("status"));
                Log.m14398b("MyWebViewDownLoadListener:", "status=" + i);
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    default:
                        return;
                    case 8:
                        Log.m14398b("MyWebViewDownLoadListener:", "STATUS_SUCCESSFUL");
                        String mimeTypeForDownloadedFile = this.f12753c.getMimeTypeForDownloadedFile(this.f12752b);
                        Log.m14398b("MyWebViewDownLoadListener:", mimeTypeForDownloadedFile);
                        if (TextUtils.isEmpty(mimeTypeForDownloadedFile)) {
                            mimeTypeForDownloadedFile = "*/*";
                        }
                        if (mimeTypeForDownloadedFile.equals("application/vnd.android.package-archive")) {
                            m13807b();
                            return;
                        }
                        return;
                    case 16:
                        Toast.makeText(this.f12751a, "下载失败", 0).show();
                        return;
                }
            }
        }

        /* renamed from: b */
        private void m13807b() {
            Uri fromFile;
            Intent intent = new Intent();
            File c = m13809c();
            if (Build.VERSION.SDK_INT >= 24) {
                Log.m14398b("MyWebViewDownLoadListener:", "ACTION_INSTALL_PACKAGE");
                Log.m14398b("MyWebViewDownLoadListener:", c.getAbsolutePath());
                fromFile = Uri.parse("file://" + c.toString());
                intent.setAction("android.intent.action.INSTALL_PACKAGE");
                intent.addFlags(1);
                Log.m14398b("MyWebViewDownLoadListener:", fromFile.toString());
            } else {
                Log.m14398b("MyWebViewDownLoadListener:", "ACTION_VIEW");
                fromFile = Uri.fromFile(c);
                intent.setAction("android.intent.action.VIEW");
                intent.setFlags(268435456);
            }
            intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
            Log.m14398b("MyWebViewDownLoadListener:", "start Install");
            BackX5WebViewActivity.this.startActivity(intent);
            Log.m14398b("MyWebViewDownLoadListener:", "start Install end");
        }

        /* renamed from: c */
        private File m13809c() {
            File file = null;
            if (this.f12752b != -1) {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(this.f12752b);
                query.setFilterByStatus(8);
                Cursor query2 = this.f12753c.query(query);
                if (query2 != null) {
                    if (query2.moveToFirst()) {
                        String string = query2.getString(query2.getColumnIndex("local_uri"));
                        if (!string.isEmpty()) {
                            file = new File(Uri.parse(string).getPath());
                        }
                    }
                    query2.close();
                }
            }
            return file;
        }
    }

    /* renamed from: f */
    private void m13765f() {
        Intent intent = getIntent();
        this.f12676B = intent.getBooleanExtra("isFind", false);
        if (this.f12676B) {
            this.f12677C = intent.getIntExtra("hitBeans", 0);
            this.f12679E = intent.getIntExtra("id", 0);
            int intExtra = intent.getIntExtra("second", 60);
            if (intExtra == 0) {
                this.f12678D = 60;
            } else {
                this.f12678D = intExtra;
            }
        }
    }

    /* renamed from: g */
    private void m13766g() {
        boolean booleanExtra = getIntent().getBooleanExtra("hidTitle", false);
        if (this.f12724x == 0) {
            this.f12709f = (TextView) findViewById(C2425R.C2427id.web_view_title);
            this.f12709f.setText(this.f12723w);
            this.f12709f.setVisibility(0);
            this.f12711h = (LinearLayout) findViewById(C2425R.C2427id.web_view_linear);
            this.f12711h.setOnClickListener(this.f12699aa);
            this.f12711h.setVisibility(0);
            this.f12710g = (ImageView) findViewById(C2425R.C2427id.web_view_back);
            this.f12710g.setImageResource(C2425R.C2426drawable.gi_back);
            this.f12710g.setVisibility(0);
            this.f12712i = (ImageView) findViewById(C2425R.C2427id.web_view_close);
            this.f12712i.setOnClickListener(this.f12699aa);
            this.f12712i.setVisibility(0);
            TextView textView = (TextView) findViewById(C2425R.C2427id.tv_divider);
            if (booleanExtra) {
                textView.setVisibility(0);
                this.f12709f.setVisibility(8);
                this.f12712i.setVisibility(8);
                return;
            }
            this.f12712i.setVisibility(0);
        }
    }

    /* renamed from: h */
    private void m13769h() {
        setProgressBarVisibility(true);
        m13766g();
        registerReceiver(this.f12692R, new IntentFilter(Constant.f13235bJ));
        registerReceiver(this.f12693S, new IntentFilter(GBApplication.GWIFI_LOGOUT));
        if (this.f12725y == 1) {
            registerReceiver(this.f12694T, new IntentFilter(Constant.f13237bL));
        }
        WiFiService.m13974a().mo27583b(this);
        this.f12675A = (LinearLayout) findViewById(C2425R.C2427id.find_ll);
        this.f12680F = (TextView) findViewById(2131755835);
        m13765f();
        this.f12688N = (Button) findViewById(C2425R.C2427id.btnCustom1);
        this.f12688N.setVisibility(8);
        this.f12689O = (Button) findViewById(C2425R.C2427id.btnCustom2);
        this.f12689O.setVisibility(8);
        hideOptionBtnCustom2();
        hideOptionBtnCustom1();
    }

    public void showOptionBtn(Button button, int i, String str, final String str2, final String str3, final String str4) {
        button.setVisibility(0);
        button.setText(str);
        button.setEnabled(true);
        if (i == 1) {
            button.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.View$OnClickListenerC335717 */

                public void onClick(View view) {
                    if (BackX5WebViewActivity.this.f12706c != null) {
                        BackX5WebViewActivity.this.hideOptionBtnCustom2();
                        BackX5WebViewActivity.this.hideOptionBtnCustom1();
                        BackX5WebViewActivity.this.f12706c.loadUrl(str2);
                    }
                }
            });
        } else if (i == 2) {
            button.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.View$OnClickListenerC335818 */

                public void onClick(View view) {
                    if (BackX5WebViewActivity.this.f12706c != null) {
                        BackX5WebViewActivity.this.f12706c.loadUrl("javascript:" + str3 + MessageStore.f23535s + str4 + MessageStore.f23536t);
                    }
                }
            });
        }
    }

    public void hideOptionBtn(Button button) {
        button.setVisibility(8);
        button.setText("");
        button.setEnabled(false);
    }

    public void showOptionBtnCustom1(int i, String str, String str2, String str3, String str4) {
        showOptionBtn(this.f12688N, i, str, str2, str3, str4);
    }

    public void showOptionBtnCustom2(int i, String str, String str2, String str3, String str4) {
        showOptionBtn(this.f12689O, i, str, str2, str3, str4);
    }

    public void hideOptionBtnCustom1() {
        hideOptionBtn(this.f12688N);
    }

    public void hideOptionBtnCustom2() {
        hideOptionBtn(this.f12689O);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: i */
    private void m13770i() {
        this.f12675A.setVisibility(0);
        this.f12680F.setText("浏览领旺豆,还剩" + this.f12678D + "秒,即可领取旺豆...");
        this.f12704af.sendEmptyMessageDelayed(3, 1000);
    }

    public static String getStoragePath() {
        String path = GBApplication.instance().getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath();
        Log.m14398b(f12673m, "BackX5WebViewActivity getStoragePath: " + path);
        return path;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13733a(int i) {
        String storagePath = getStoragePath();
        Log.m14398b(f12673m, "storagePath=" + storagePath);
        String str = storagePath + File.separator + "camerademo";
        Log.m14398b(f12673m, "storagePath=" + str);
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str2 = str + File.separator + SystemClock.currentThreadTimeMillis() + C6299a.f22754m;
        Log.m14398b(f12673m, "storagePath=" + str2);
        File file2 = new File(str2);
        if (Build.VERSION.SDK_INT >= 24) {
            this.f12697Z = FileProvider.getUriForFile(this, getPackageName() + ".provider", file2);
        } else {
            this.f12697Z = Uri.fromFile(file2);
        }
        if (i > 0) {
            String[] strArr = {"android.permission.CAMERA"};
            final String str3 = "在设置-应用-" + SystemUtil.m14528d() + "-权限中开启相机权限，以正常使用功能";
            if (checkPermissions(this, strArr)) {
                m13773j();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
                GBGlobalConfig.m10510c().mo24391a((Activity) this, str3);
            } else {
                PermissionsManager.m4670a().mo21377a(this, strArr, new PermissionsResultAction() {
                    /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C33592 */

                    @Override // com.anthonycr.grant.PermissionsResultAction
                    public void onGranted() {
                        BackX5WebViewActivity.this.m13773j();
                    }

                    @Override // com.anthonycr.grant.PermissionsResultAction
                    public void onDenied(String str) {
                        GBGlobalConfig.m10510c().mo24391a((Activity) BackX5WebViewActivity.this, str3);
                    }
                });
            }
        } else {
            String[] strArr2 = {"android.permission.CAMERA"};
            final String str4 = "在设置-应用-" + SystemUtil.m14528d() + "-权限中开启相机权限，以正常使用功能";
            if (checkPermissions(this, strArr2)) {
                m13774k();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
                GBGlobalConfig.m10510c().mo24391a((Activity) this, str4);
            } else {
                PermissionsManager.m4670a().mo21377a(this, strArr2, new PermissionsResultAction() {
                    /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C33603 */

                    @Override // com.anthonycr.grant.PermissionsResultAction
                    public void onGranted() {
                        BackX5WebViewActivity.this.m13774k();
                    }

                    @Override // com.anthonycr.grant.PermissionsResultAction
                    public void onDenied(String str) {
                        GBGlobalConfig.m10510c().mo24391a((Activity) BackX5WebViewActivity.this, str4);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: j */
    private void m13773j() {
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        for (ResolveInfo resolveInfo : getPackageManager().queryIntentActivities(intent, 0)) {
            String str = resolveInfo.activityInfo.packageName;
            Intent intent2 = new Intent(intent);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
            intent2.setPackage(str);
            if (Build.VERSION.SDK_INT > 23) {
                intent2.setFlags(1);
            }
            intent2.putExtra("output", this.f12697Z);
            arrayList.add(intent2);
        }
        Intent intent3 = new Intent("android.intent.action.GET_CONTENT");
        intent3.addCategory("android.intent.category.OPENABLE");
        intent3.setType("*/*");
        Intent createChooser = Intent.createChooser(intent3, "文件选择");
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        startActivityForResult(createChooser, 101);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: k */
    private void m13774k() {
        if (!StorageUtils.m14495d()) {
            Toast.makeText(this, "SDCard not exist", 0).show();
            return;
        }
        try {
            File file = new File(getStoragePath());
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (Build.VERSION.SDK_INT > 23) {
                intent.setFlags(1);
            }
            intent.putExtra("output", this.f12697Z);
            startActivityForResult(intent, 101);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: l */
    private void m13776l() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
        startActivityForResult(intent, 120);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: m */
    private void m13778m() {
        String[] strArr = {"android.permission.CAMERA"};
        final String str = "在设置-应用-" + SystemUtil.m14528d() + "-权限中开启相机权限，以正常使用功能";
        if (checkPermissions(this, strArr)) {
            m13776l();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
            GBGlobalConfig.m10510c().mo24391a((Activity) this, str);
        } else {
            PermissionsManager.m4670a().mo21377a(this, strArr, new PermissionsResultAction() {
                /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C33614 */

                @Override // com.anthonycr.grant.PermissionsResultAction
                public void onGranted() {
                    BackX5WebViewActivity.this.m13776l();
                }

                @Override // com.anthonycr.grant.PermissionsResultAction
                public void onDenied(String str) {
                    GBGlobalConfig.m10510c().mo24391a((Activity) BackX5WebViewActivity.this, str);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13741a(ValueCallback<Uri> valueCallback, String str) {
        this.f12683I = valueCallback;
        if (this.f12695X) {
            m13778m();
        } else if (this.f12696Y) {
            m13733a(0);
        } else {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            startActivityForResult(Intent.createChooser(intent, "文件选择"), 2);
        }
    }

    /* renamed from: n */
    private void m13780n() {
        View inflate = getLayoutInflater().inflate(C2425R.layout.web_spin_item, (ViewGroup) null);
        ((LinearLayout) inflate.findViewById(C2425R.C2427id.web_linear)).setOnClickListener(this.f12699aa);
        this.f12714k = new PopupWindow(inflate, -2, -2, true);
        inflate.setOnTouchListener(new View.OnTouchListener() {
            /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.View$OnTouchListenerC33625 */

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (BackX5WebViewActivity.this.f12714k == null || !BackX5WebViewActivity.this.f12714k.isShowing()) {
                    return false;
                }
                BackX5WebViewActivity.this.f12714k.dismiss();
                BackX5WebViewActivity.this.f12714k = null;
                return false;
            }
        });
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.f12685K != null) {
            m13754c();
            return true;
        } else if (this.f12706c == null || !this.f12706c.canGoBack()) {
            finish();
            return true;
        } else {
            this.f12706c.goBack();
            if (Integer.parseInt(Build.VERSION.SDK) < 16) {
                return true;
            }
            m13742a(this.f12706c);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri[] uriArr;
        Log.m14398b(f12673m, "onActivityResult, requestCode:" + i + ",resultCode:" + i2);
        if (i == 2) {
            if (this.f12683I != null) {
                this.f12683I.onReceiveValue((intent == null || i2 != -1) ? null : intent.getData());
                this.f12683I = null;
            }
        } else if (i != 3) {
            if (i == 100) {
                if (this.f12683I != null || this.f12684J != null) {
                    Uri data = (intent == null || i2 != -1) ? null : intent.getData();
                    if (this.f12684J != null) {
                        m13734a(i, i2, intent);
                    } else if (this.f12683I != null) {
                        this.f12683I.onReceiveValue(data);
                        this.f12683I = null;
                    }
                } else {
                    return;
                }
            }
            if (i == 101) {
                if (this.f12683I != null || this.f12684J != null) {
                    Uri data2 = (intent == null || i2 != -1) ? null : intent.getData();
                    if (this.f12684J != null) {
                        m13734a(i, i2, intent);
                    } else if (this.f12683I != null) {
                        this.f12683I.onReceiveValue(data2);
                        this.f12683I = null;
                    }
                } else {
                    return;
                }
            } else if (i == 120) {
                if (this.f12683I != null || this.f12684J != null) {
                    Uri data3 = (intent == null || i2 != -1) ? null : intent.getData();
                    if (this.f12684J != null) {
                        if (i2 == -1) {
                            this.f12684J.onReceiveValue(new Uri[]{data3});
                            this.f12684J = null;
                        } else {
                            this.f12684J.onReceiveValue(new Uri[0]);
                            this.f12684J = null;
                        }
                    } else if (this.f12683I != null) {
                        if (i2 == -1) {
                            this.f12683I.onReceiveValue(data3);
                            this.f12683I = null;
                        } else {
                            this.f12683I.onReceiveValue(Uri.EMPTY);
                            this.f12683I = null;
                        }
                    }
                } else {
                    return;
                }
            }
            if (i != 0) {
                return;
            }
            if (intent != null) {
                try {
                    JSONObject parseObject = JSON.parseObject(intent.getStringExtra("result"));
                    parseObject.put((Object) "username", (Object) CacheAccount.getInstance().getLoginPhone());
                    parseObject.put((Object) "orgId", (Object) CacheAuth.getInstance().getOrgId());
                    if (((Integer) parseObject.get("qrCodeType")).intValue() == 1) {
                        callJSFunction(this.f12706c, "onSuccess".toString(), CommonMsg.commonMsgMap(0, "", parseObject));
                    } else {
                        callJSFunction(this.f12706c, "onError".toString(), CommonMsg.commonMsgMap(2, "不是迎新二维码!", parseObject));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callJSFunction(this.f12706c, "onError".toString(), CommonMsg.commonMsgMap(2, "不是迎新二维码!", Constant.f13284cF));
                }
            } else if (cameraIsCanUse()) {
                callJSFunction(this.f12706c, "onError".toString(), CommonMsg.commonMsgMap(3, "intent返回null", Constant.f13284cF));
            } else {
                callJSFunction(this.f12706c, "onError".toString(), CommonMsg.commonMsgMap(1, "没有开启摄像头权限!", Constant.f13284cF));
            }
        } else if (this.f12684J != null) {
            if (i2 == -1) {
                if (intent == null) {
                    uriArr = null;
                } else {
                    String dataString = intent.getDataString();
                    if (dataString != null) {
                        uriArr = new Uri[]{Uri.parse(dataString)};
                    }
                }
                this.f12684J.onReceiveValue(uriArr);
                this.f12684J = null;
            }
            uriArr = null;
            this.f12684J.onReceiveValue(uriArr);
            this.f12684J = null;
        }
    }

    @TargetApi(21)
    /* renamed from: a */
    private void m13734a(int i, int i2, Intent intent) {
        Uri[] uriArr;
        if ((i == 100 || i == 101) && this.f12684J != null) {
            if (i2 != -1) {
                uriArr = null;
            } else if (intent == null) {
                uriArr = new Uri[]{this.f12697Z};
            } else {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    Uri[] uriArr2 = new Uri[clipData.getItemCount()];
                    for (int i3 = 0; i3 < clipData.getItemCount(); i3++) {
                        uriArr2[i3] = clipData.getItemAt(i3).getUri();
                    }
                    uriArr = uriArr2;
                } else {
                    uriArr = null;
                }
                if (dataString != null) {
                    uriArr = new Uri[]{Uri.parse(dataString)};
                }
            }
            this.f12684J.onReceiveValue(uriArr);
            this.f12684J = null;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p009v4.app.FragmentActivity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && this.f12706c != null) {
            if (this.f12706c.getVisibility() != 0) {
                this.f12706c.setVisibility(0);
            }
            this.f12706c.loadUrl(intent.getStringExtra("url"));
            this.f12706c.postDelayed(new Runnable() {
                /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.RunnableC33647 */

                @Override // java.lang.Runnable
                public void run() {
                    BackX5WebViewActivity.this.f12706c.clearHistory();
                }
            }, 1000);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p009v4.app.FragmentActivity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.f12706c.saveState(bundle);
        Log.m14400c(f12673m, "onSaveInstanceState");
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.f12706c.restoreState(bundle);
        Log.m14400c(f12673m, "onRestoreInstanceState");
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        unregisterReceiver(this.f12692R);
        unregisterReceiver(this.f12693S);
        if (this.f12725y == 1) {
            unregisterReceiver(this.f12694T);
        }
        WiFiService.m13974a().mo27584c(this);
        if (this.f12704af != null) {
            this.f12704af.removeCallbacksAndMessages(null);
        }
        if (this.f12706c != null) {
            this.f12706c.setWebViewClient(null);
            this.f12706c.setWebChromeClient(null);
            this.f12706c.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            this.f12706c.clearHistory();
            this.f12706c.destroy();
        }
        m13782o();
        super.onDestroy();
        Log.m14400c(f12673m, "onDestroy");
    }

    public void finish() {
        ((ViewGroup) getWindow().getDecorView()).removeAllViews();
        if (this.f12706c != null) {
        }
        super.finish();
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        if (this.f12706c != null) {
            super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
            WebView.HitTestResult hitTestResult = ((WebView) view).getHitTestResult();
            if (hitTestResult != null) {
                int type = hitTestResult.getType();
                if (type == 5 || type == 8) {
                    contextMenu.add("保存图片到图库");
                    hitTestResult.getExtra();
                }
            }
        }
    }

    public boolean cameraIsCanUse() {
        boolean z;
        Camera camera;
        Camera camera2 = null;
        try {
            camera2 = Camera.open();
            camera2.setParameters(camera2.getParameters());
            z = true;
            camera = camera2;
        } catch (Exception e) {
            z = false;
            camera = camera2;
        }
        if (camera != null) {
            try {
                camera.release();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    public void callJSFunction(WebView webView, String str, Object obj) {
        String str2 = null;
        if (obj != null) {
            str2 = JsonUtil.m14387a(obj);
        }
        if (str2 != null) {
            webView.loadUrl("javascript:" + str + MessageStore.f23535s + str2 + MessageStore.f23536t);
        } else {
            webView.loadUrl("javascript:" + str + "()");
        }
    }

    /* renamed from: o */
    private void m13782o() {
        if (this.f12705b != null) {
            this.f12705b.onStop();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13743a(File file) {
        String path = file.getPath();
        Log.m14398b(f12673m, "displayFile path:" + path);
        String b = m13748b(path);
        Log.m14398b(f12673m, "displayFile format:" + b);
        if (this.f12705b == null) {
            this.f12705b = new TbsReaderView(this, new TbsReaderView.ReaderCallback() {
                /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C33658 */

                @Override // com.tencent.smtt.sdk.TbsReaderView.ReaderCallback
                public void onCallBackAction(Integer num, Object obj, Object obj2) {
                }
            });
            this.f12707d.addView(this.f12705b, new FrameLayout.LayoutParams(-1, -1));
        }
        if (this.f12705b.preOpen(b, false)) {
            Log.m14398b(f12673m, "displayFile mTbsReaderView.preOpen succeed");
            Bundle bundle = new Bundle();
            bundle.putString(TbsReaderView.KEY_FILE_PATH, path);
            bundle.putString(TbsReaderView.KEY_TEMP_PATH, getStoragePath());
            this.f12705b.openFile(bundle);
            stopWebDrawable();
            return;
        }
        Log.m14398b(f12673m, "displayFile mTbsReaderView.preOpen failed");
        if (QbSdk.isSuportOpenFile(b, 1)) {
            Log.m14398b(f12673m, "displayFile QbSdk.isSuportOpenFile succeed");
            HashMap hashMap = new HashMap();
            hashMap.put("style", "1");
            hashMap.put(AgooConstants.MESSAGE_LOCAL, "false");
            TbsReaderAssist.m13825a(this, path, hashMap, null);
            return;
        }
        Log.m14398b(f12673m, "Unsupport format");
    }

    /* renamed from: b */
    private String m13748b(String str) {
        return str.substring(str.lastIndexOf(Constants.ATTRVAL_THIS) + 1);
    }

    /* renamed from: c */
    private String m13753c(String str) {
        boolean isEmpty;
        long currentTimeMillis;
        CharSequence charSequence = null;
        try {
            charSequence = str.substring(str.lastIndexOf(PsuedoNames.PSEUDONAME_ROOT) + 1);
            if (isEmpty) {
                return String.valueOf(currentTimeMillis);
            }
            return charSequence;
        } finally {
            if (TextUtils.isEmpty(charSequence)) {
                String.valueOf(System.currentTimeMillis());
            }
        }
    }

    /* renamed from: p */
    private File m13784p() {
        return new File(GBApplication.instance().getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath(), this.f12701ac);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m13759d(String str) {
        this.f12707d.setVisibility(0);
        this.f12706c.setVisibility(8);
        this.f12700ab = getStoragePath();
        this.f12701ac = m13753c(str);
        Log.m14398b(f12673m, "loadHttpFile 1");
        File p = m13784p();
        if (p.exists()) {
            m13743a(p);
            return;
        }
        Log.m14398b(f12673m, "loadHttpFile 2");
        new LoadHttpFileAsyncTask(this.f12700ab, this.f12701ac, new LoadHttpFileAsyncTask.AbstractC3372a() {
            /* class com.gbcom.gwifi.functions.webview.BackX5WebViewActivity.C33669 */

            @Override // com.gbcom.gwifi.functions.webview.LoadHttpFileAsyncTask.AbstractC3372a
            /* renamed from: a */
            public void mo27405a(File file) {
                Log.m14398b(BackX5WebViewActivity.f12673m, "loadHttpFile onCompleteListener");
                BackX5WebViewActivity.this.m13743a((BackX5WebViewActivity) file);
            }

            @Override // com.gbcom.gwifi.functions.webview.LoadHttpFileAsyncTask.AbstractC3372a
            /* renamed from: a */
            public void mo27404a() {
                Log.m14403e(BackX5WebViewActivity.f12673m, "loadHttpFile onFailureListener");
                Log.m14403e(BackX5WebViewActivity.f12673m, "onFailureListener");
                BackX5WebViewActivity.this.stopWebDrawable();
            }

            @Override // com.gbcom.gwifi.functions.webview.LoadHttpFileAsyncTask.AbstractC3372a
            /* renamed from: a */
            public void mo27406a(Integer num, Integer num2) {
                Log.m14398b(BackX5WebViewActivity.f12673m, "loadHttpFile onProgressListener");
            }
        }).execute(str);
    }
}
