package com.gbcom.gwifi.functions.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.p009v4.app.ActivityCompat;
import android.support.p009v4.content.FileProvider;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.gbcom.edu.functionModule.main.circle.bean.CircleUserBean;
import com.gbcom.edu.functionModule.main.manager.ISDKUser;
import com.gbcom.edu.functionModule.main.manager.SDKUserManager;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.functions.loading.LoginThirdActivity;
import com.gbcom.gwifi.functions.loading.StatusLoginActivity;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.FormatUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.StorageUtils;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.WebViewUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.util.p257b.ImageTools;
import java.p456io.ByteArrayOutputStream;
import java.p456io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import p041c.Request;

public class MyProfileActivity extends GBActivity implements TextWatcher, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    public static final int CLIP_PIC = 3;
    public static final String TMP_PATH = "clip_temp.jpg";

    /* renamed from: a */
    private static final String f10916a = "MyProfileActivity";

    /* renamed from: A */
    private RelativeLayout f10917A;

    /* renamed from: B */
    private Request f10918B;

    /* renamed from: C */
    private Request f10919C;

    /* renamed from: D */
    private SDKUserManager f10920D;

    /* renamed from: E */
    private OkRequestHandler<String> f10921E = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.profile.MyProfileActivity.C29673 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (!MyProfileActivity.this.isFinishing()) {
                MyProfileActivity.this.dismissProgressDialog();
                if (abVar == MyProfileActivity.this.f10938r) {
                }
                if (abVar == MyProfileActivity.this.f10941u) {
                    MyProfileActivity.this.f10940t = !MyProfileActivity.this.f10940t;
                    MyProfileActivity.this.f10923c.setEnabled(true);
                    MyProfileActivity.this.f10923c.setTextColor(MyProfileActivity.this.getResources().getColor(C2425R.color.black));
                }
                if (abVar == MyProfileActivity.this.f10918B) {
                }
                if (abVar == MyProfileActivity.this.f10919C) {
                }
                GBActivity.showMessageToast("请检查网络");
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            HashMap hashMap;
            MyProfileActivity.this.dismissProgressDialog();
            CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
            if (abVar == MyProfileActivity.this.f10938r && !GBActivity.dealException(deSerializeJson)) {
                String data = deSerializeJson.getData();
                if (!TextUtils.isEmpty(data) && (hashMap = (HashMap) JsonUtil.m14386a(data, HashMap.class)) != null) {
                    GBActivity.showMessageToast("头像更新成功");
                    String str2 = hashMap.get("url") == null ? "" : (String) hashMap.get("url");
                    CacheAccount.getInstance().setAvatarUrl(str2);
                    if (!MyProfileActivity.this.isFinishing()) {
                        ImageTools.m14149a(str2, MyProfileActivity.this.f10927g, GBApplication.instance().head_options);
                        MyProfileActivity.this.sendBroadcast(new Intent(Constant.f13231bF));
                        MyProfileActivity.this.m12328l();
                    } else {
                        return;
                    }
                }
            }
            if (MyProfileActivity.this.f10941u == abVar && !GBActivity.dealException(deSerializeJson)) {
                if (!MyProfileActivity.this.isFinishing()) {
                    MyProfileActivity.this.m12320h();
                    MyProfileActivity.this.m12328l();
                    MyProfileActivity.this.finish();
                } else {
                    return;
                }
            }
            if (abVar == MyProfileActivity.this.f10918B) {
            }
            if (abVar == MyProfileActivity.this.f10919C) {
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
            Log.m14400c(MyProfileActivity.f10916a, "current:" + j + ",total:" + j2);
        }
    };

    /* renamed from: b */
    private RelativeLayout f10922b;

    /* renamed from: c */
    private TextView f10923c;

    /* renamed from: d */
    private TextView f10924d;

    /* renamed from: e */
    private TextView f10925e;

    /* renamed from: f */
    private PopupWindow f10926f;

    /* renamed from: g */
    private ImageView f10927g;

    /* renamed from: h */
    private RelativeLayout f10928h;

    /* renamed from: i */
    private EditText f10929i;

    /* renamed from: j */
    private TextView f10930j;

    /* renamed from: k */
    private EditText f10931k;

    /* renamed from: l */
    private TextView f10932l;

    /* renamed from: m */
    private RelativeLayout f10933m;

    /* renamed from: n */
    private EditText f10934n;

    /* renamed from: o */
    private RelativeLayout f10935o;

    /* renamed from: p */
    private RelativeLayout f10936p;

    /* renamed from: q */
    private boolean f10937q = false;

    /* renamed from: r */
    private Request f10938r;

    /* renamed from: s */
    private String f10939s;

    /* renamed from: t */
    private boolean f10940t = false;

    /* renamed from: u */
    private Request f10941u;

    /* renamed from: v */
    private RadioGroup f10942v;

    /* renamed from: w */
    private RadioButton f10943w;

    /* renamed from: x */
    private RadioButton f10944x;

    /* renamed from: y */
    private int f10945y = 1;

    /* renamed from: z */
    private boolean f10946z = false;

    public static String getStoragePath() {
        String str = GBApplication.instance().getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath() + File.separator + TMP_PATH;
        Log.m14398b(f10916a, "getStoragePath: " + str);
        return str;
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "我的资料界面", C2425R.layout.my_activity_profile, true);
        m12307b();
        m12310c();
    }

    /* renamed from: b */
    private void m12307b() {
        this.f10922b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10936p = (RelativeLayout) findViewById(C2425R.C2427id.modify_password_rl);
        this.f10922b.setOnClickListener(this);
        this.f10923c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10923c.setText("完成");
        this.f10923c.setEnabled(false);
        this.f10923c.setTextColor(getResources().getColor(C2425R.color.black));
        this.f10927g = (ImageView) findViewById(C2425R.C2427id.my_head_iv);
        this.f10929i = (EditText) findViewById(C2425R.C2427id.name_et);
        this.f10930j = (TextView) findViewById(C2425R.C2427id.phone_tv);
        this.f10931k = (EditText) findViewById(C2425R.C2427id.real_name_et);
        this.f10932l = (TextView) findViewById(C2425R.C2427id.gender_tv);
        this.f10934n = (EditText) findViewById(C2425R.C2427id.mail_et);
        this.f10935o = (RelativeLayout) findViewById(C2425R.C2427id.status_rl);
        this.f10928h = (RelativeLayout) findViewById(C2425R.C2427id.my_head_layout);
        this.f10933m = (RelativeLayout) findViewById(C2425R.C2427id.gender_layout);
        this.f10942v = (RadioGroup) findViewById(C2425R.C2427id.radio_group);
        this.f10943w = (RadioButton) findViewById(C2425R.C2427id.radio_button1);
        this.f10944x = (RadioButton) findViewById(C2425R.C2427id.radio_button2);
        this.f10923c.setOnClickListener(this);
        this.f10933m.setOnClickListener(this);
        this.f10928h.setOnClickListener(this);
        this.f10936p.setOnClickListener(this);
        this.f10935o.setOnClickListener(this);
        this.f10942v.setOnCheckedChangeListener(this);
        this.f10917A = (RelativeLayout) findViewById(C2425R.C2427id.exit_user);
        this.f10917A.setOnClickListener(this);
        this.f10920D = new SDKUserManager(this);
    }

    /* renamed from: c */
    private void m12310c() {
        ImageTools.m14149a(CacheAccount.getInstance().getAvatarUrl(), this.f10927g, GBApplication.instance().head_options);
        if (CacheAccount.getInstance().getLoginPhone() != null) {
            this.f10930j.setText(CacheAccount.getInstance().getLoginPhone());
        }
        this.f10929i.setText(CacheAccount.getInstance().getNickName());
        Selection.setSelection(this.f10929i.getEditableText(), this.f10929i.length());
        this.f10931k.setText(CacheAccount.getInstance().getIdentityName());
        String genderName = CacheAccount.getInstance().getGenderName();
        this.f10932l.setText(genderName);
        int d = FormatUtil.m14229d(genderName);
        if (d == 0 || d == 1) {
            this.f10945y = 1;
            this.f10943w.setChecked(true);
        } else if (d == 2) {
            this.f10945y = d;
            this.f10944x.setChecked(true);
        }
        this.f10934n.setText(CacheAccount.getInstance().getEmail());
        this.f10929i.addTextChangedListener(this);
        this.f10931k.addTextChangedListener(this);
        this.f10932l.addTextChangedListener(this);
        this.f10934n.addTextChangedListener(this);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m12312d() {
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
            if (Build.VERSION.SDK_INT <= 23) {
                intent.putExtra("output", Uri.fromFile(file));
            } else {
                intent.setFlags(1);
                intent.putExtra("output", FileProvider.getUriForFile(GBApplication.instance().getApplicationContext(), GBApplication.instance().getPackageName() + ".provider", file));
            }
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        boolean z = true;
        switch (view.getId()) {
            case C2425R.C2427id.photo_tv /*{ENCODED_INT: 2131755307}*/:
                Log.m14400c(f10916a, "dian ji le photo");
                this.f10926f.dismiss();
                String[] strArr = {"android.permission.CAMERA"};
                final String str = "在设置-应用-" + SystemUtil.m14528d() + "-权限中开启相机权限，以正常使用功能";
                if (checkPermissions(this, strArr)) {
                    m12312d();
                    return;
                } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
                    GBGlobalConfig.m10510c().mo24391a((Activity) this, str);
                    return;
                } else {
                    PermissionsManager.m4670a().mo21377a(this, strArr, new PermissionsResultAction() {
                        /* class com.gbcom.gwifi.functions.profile.MyProfileActivity.C29695 */

                        @Override // com.anthonycr.grant.PermissionsResultAction
                        public void onGranted() {
                            MyProfileActivity.this.m12312d();
                        }

                        @Override // com.anthonycr.grant.PermissionsResultAction
                        public void onDenied(String str) {
                            GBGlobalConfig.m10510c().mo24391a((Activity) MyProfileActivity.this, str);
                        }
                    });
                    return;
                }
            case C2425R.C2427id.local_tv /*{ENCODED_INT: 2131755309}*/:
                this.f10926f.dismiss();
                String[] strArr2 = {"android.permission.READ_EXTERNAL_STORAGE"};
                if (!checkPermissions(this, strArr2)) {
                    PermissionsManager.m4670a().mo21377a(this, strArr2, new PermissionsResultAction() {
                        /* class com.gbcom.gwifi.functions.profile.MyProfileActivity.C29621 */

                        @Override // com.anthonycr.grant.PermissionsResultAction
                        public void onGranted() {
                            MyProfileActivity.this.mo25960a();
                        }

                        @Override // com.anthonycr.grant.PermissionsResultAction
                        public void onDenied(String str) {
                        }
                    });
                    return;
                } else {
                    mo25960a();
                    return;
                }
            case C2425R.C2427id.status_rl /*{ENCODED_INT: 2131755659}*/:
                openBackWebView(HttpUtil.m14317e(), "身份信息", 0);
                return;
            case C2425R.C2427id.my_head_layout /*{ENCODED_INT: 2131755689}*/:
                m12321i();
                return;
            case C2425R.C2427id.gender_layout /*{ENCODED_INT: 2131755697}*/:
                m12324j();
                return;
            case C2425R.C2427id.modify_password_rl /*{ENCODED_INT: 2131755705}*/:
                startActivity(new Intent(this, ModifyPasswordActivity.class));
                return;
            case C2425R.C2427id.exit_user /*{ENCODED_INT: 2131755707}*/:
                showNormalDialog("退出帐号", "是否退出当前帐号？", "退出", "取消", new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.profile.MyProfileActivity.C29706 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                        WebViewUtil.m14568a();
                        MyProfileActivity.this.m12314e();
                    }
                }, new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.profile.MyProfileActivity.C29717 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                    }
                });
                return;
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                m12318g();
                return;
            case C2425R.C2427id.title_edit_tv /*{ENCODED_INT: 2131755759}*/:
                if (this.f10940t) {
                    if (this.f10940t) {
                        z = false;
                    }
                    this.f10940t = z;
                    this.f10923c.setEnabled(false);
                    this.f10923c.setTextColor(getResources().getColor(C2425R.color.black));
                    m12316f();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m12314e() {
        if (CacheAuth.getInstance().isPortal()) {
            this.f10919C = HttpUtil.m14346p(GBApplication.instance(), this.f10921E, "");
        } else {
            this.f10918B = HttpUtil.m14296b(this.f10921E, "");
        }
        CacheAccount.getInstance().setUserId(0);
        CacheAccount.getInstance().setLastSignTime(0);
        CacheApp.getInstance().setWeChatGuide(false);
        CacheAuth.getInstance().setLastReleaseTime(0);
        CacheWiFi.getInstance().setApLocation("");
        CacheAccount.getInstance().clearCacheAccountPassword(CacheAccount.getInstance().getLoginPhone());
        CacheAuth.getInstance().resetPortalDisable();
        int lastLoginType = CacheAccount.getInstance().getLastLoginType();
        String lastLoginPhone = CacheAccount.getInstance().getLastLoginPhone();
        if (lastLoginType == 2) {
            Intent intent = new Intent(this, StatusLoginActivity.class);
            intent.putExtra(Constant.f13323i, lastLoginPhone);
            startActivity(intent);
        } else {
            Intent intent2 = new Intent(this, LoginThirdActivity.class);
            intent2.putExtra(Constant.f13323i, lastLoginPhone);
            startActivity(intent2);
        }
        GBApplication.instance().exitOther();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo25960a() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
        } else {
            intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(intent, 0);
    }

    /* renamed from: f */
    private void m12316f() {
        if (this.f10945y == 1) {
        }
        if (this.f10945y == 2) {
        }
        if (!CacheAccount.getInstance().getNickName().equals(this.f10929i.getText().toString())) {
            this.f10941u = HttpUtil.m14264a(this, this.f10929i.getText().toString(), CacheAccount.getInstance().getIdentityName(), CacheAccount.getInstance().getGender(), "", this.f10921E, "");
        } else {
            finish();
        }
    }

    /* renamed from: a */
    private void m12302a(Bitmap bitmap) {
        m12305a(Bitmap2Bytes(bitmap));
    }

    public byte[] Bitmap2Bytes(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    private void m12305a(byte[] bArr) {
        new Thread(new Runnable() {
            /* class com.gbcom.gwifi.functions.profile.MyProfileActivity.RunnableC29728 */

            @Override // java.lang.Runnable
            public void run() {
            }
        }).start();
    }

    /* renamed from: g */
    private void m12318g() {
        if (this.f10940t) {
            showNormalDialog("编辑资料", "是否放弃对资料的修改？", "放弃", "继续编辑", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.profile.MyProfileActivity.C29739 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    MyProfileActivity.this.finish();
                }
            }, new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.profile.MyProfileActivity.C296310 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                }
            });
        } else {
            finish();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        m12318g();
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: h */
    private void m12320h() {
        if (this.f10929i.getText().toString().trim() != null) {
            CacheAccount.getInstance().setNickName(this.f10929i.getText().toString());
        }
        if (this.f10934n.getText().toString().trim() != null) {
            CacheAccount.getInstance().setEmail(this.f10934n.getText().toString());
        }
        if (this.f10931k.getText().toString().trim() != null) {
            CacheAccount.getInstance().setIdentityName(this.f10931k.getText().toString());
        }
        if (this.f10945y == 1) {
            CacheAccount.getInstance().setGenderName(Constant.f13331q);
        }
        if (this.f10945y == 2) {
            CacheAccount.getInstance().setGenderName(Constant.f13332r);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri data;
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 0:
                    if (intent != null && (data = intent.getData()) != null) {
                        Log.m14398b(f10916a, "Profile Gallery:" + data.getPath());
                        m12303a(data);
                        return;
                    }
                    return;
                case 1:
                    m12303a(Uri.fromFile(new File(getStoragePath())));
                    return;
                case 3:
                    if (intent != null) {
                        this.f10939s = intent.getStringExtra(ClipImageActivity.RESULT_PATH);
                        this.f10938r = HttpUtil.m14257a(this, new File(this.f10939s), this.f10921E, "");
                        return;
                    }
                    return;
                case 100:
                    if (checkPermissions(this, new String[]{"android.permission.CAMERA"})) {
                        m12312d();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private void m12301a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap bitmap = (Bitmap) extras.getParcelable("data");
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, new ByteArrayOutputStream());
            this.f10927g.setImageBitmap(bitmap);
        }
    }

    /* renamed from: i */
    private void m12321i() {
        if (this.f10926f == null) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(C2425R.layout.common_pop_head_menu, (ViewGroup) null, true);
            this.f10926f = new PopupWindow((View) viewGroup, -1, -1, true);
            this.f10926f.setTouchable(true);
            ((TextView) viewGroup.findViewById(C2425R.C2427id.local_tv)).setOnClickListener(this);
            ((TextView) viewGroup.findViewById(C2425R.C2427id.photo_tv)).setOnClickListener(this);
            viewGroup.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.profile.MyProfileActivity.View$OnClickListenerC296411 */

                public void onClick(View view) {
                    MyProfileActivity.this.f10926f.dismiss();
                }
            });
            this.f10926f.setBackgroundDrawable(new BitmapDrawable());
            this.f10926f.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
            this.f10926f.setSoftInputMode(16);
            this.f10926f.update();
        } else if (this.f10926f.isShowing()) {
            this.f10926f.dismiss();
            this.f10926f = null;
        } else {
            this.f10926f.showAsDropDown(findViewById(C2425R.C2427id.my_title_layout));
        }
    }

    /* renamed from: j */
    private void m12324j() {
        Log.m14400c(f10916a, "dialog");
        View inflate = LayoutInflater.from(this).inflate(C2425R.layout.my_profile_dialog, (ViewGroup) null);
        final AlertDialog create = new AlertDialog.Builder(this).create();
        create.setView(inflate);
        this.f10924d = (TextView) inflate.findViewById(C2425R.C2427id.man_tv);
        this.f10925e = (TextView) inflate.findViewById(C2425R.C2427id.lady_tv);
        this.f10924d.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.profile.MyProfileActivity.View$OnClickListenerC296512 */

            public void onClick(View view) {
                MyProfileActivity.this.f10932l.setText(MyProfileActivity.this.f10924d.getText().toString());
                create.dismiss();
            }
        });
        this.f10925e.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.profile.MyProfileActivity.View$OnClickListenerC29662 */

            public void onClick(View view) {
                MyProfileActivity.this.f10932l.setText(MyProfileActivity.this.f10925e.getText().toString());
                create.dismiss();
            }
        });
        create.show();
    }

    @SuppressLint({"SimpleDateFormat"})
    /* renamed from: k */
    private String m12326k() {
        return new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss").format(new Date(System.currentTimeMillis()));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: l */
    private void m12328l() {
        this.f10920D.mo24031a(CacheAccount.getInstance().getLoginPhone(), CacheAccount.getInstance().getNickName(), this.f10945y, CacheAccount.getInstance().getAvatarUrl(), new ISDKUser() {
            /* class com.gbcom.gwifi.functions.profile.MyProfileActivity.C29684 */

            @Override // com.gbcom.edu.functionModule.main.manager.ISDKUser
            /* renamed from: a */
            public void mo24034a(int i, CircleUserBean circleUserBean) {
                Log.m14398b(MyProfileActivity.f10916a, "i=" + i);
            }

            @Override // com.gbcom.edu.functionModule.main.manager.ISDKUser
            /* renamed from: a */
            public void mo24033a(int i) {
                Log.m14398b(MyProfileActivity.f10916a, "i=" + i);
            }
        });
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.f10940t = true;
        this.f10923c.setEnabled(true);
        this.f10923c.setTextColor(getResources().getColor(C2425R.color.black));
    }

    public void afterTextChanged(Editable editable) {
    }

    /* renamed from: a */
    private void m12303a(Uri uri) {
        ClipImageActivity.startActivity(this, uri, 3);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (this.f10946z) {
            this.f10940t = true;
            this.f10923c.setEnabled(true);
            this.f10923c.setTextColor(getResources().getColor(C2425R.color.black));
            if (i == this.f10943w.getId()) {
                this.f10945y = 1;
            } else if (i == this.f10944x.getId()) {
                this.f10945y = 2;
            }
        }
        this.f10946z = true;
    }
}
