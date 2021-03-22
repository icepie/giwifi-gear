package com.gbcom.gwifi.third.zxing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.p009v4.app.ActivityCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.third.zxing.camera.CameraManager;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.FormatUtil;
import com.gbcom.gwifi.util.SystemUtil;
import com.xiaomi.stat.C6765d;
import java.p456io.IOException;

public class CaptureActivity extends GBActivity implements SurfaceHolder.Callback, View.OnClickListener {
    private static final int ACTION_REQUEST_PERMISSIONS = 1;
    public static final int MSG_REQUEST_PERMISSIONS = 0;
    private BeepManager beepManager;
    private CameraManager cameraManager;
    private String from;
    private CaptureActivityHandler handler;
    private InactivityTimer inactivityTimer;
    private boolean isHasSurface = false;
    private Rect mCropRect = null;
    private Handler mTestHandler = new Handler() {
        /* class com.gbcom.gwifi.third.zxing.CaptureActivity.HandlerC34441 */

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    CaptureActivity.this.requestPermissions();
                    break;
            }
            super.handleMessage(message);
        }
    };
    private RelativeLayout scanContainer;
    private RelativeLayout scanCropView;
    private ImageView scanLine;
    private SurfaceView scanPreview = null;
    private RelativeLayout title_back_layout;
    private TextView title_edit_tv;
    private TextView title_main_tv;

    public Handler getHandler() {
        return this.handler;
    }

    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "二维码扫描界面", C2425R.layout.sys_activity_capture, true);
        initView();
        Intent intent = getIntent();
        if (intent != null) {
            this.from = intent.getStringExtra("from");
            this.mTestHandler.sendEmptyMessageDelayed(0, 10);
        }
    }

    private void initView() {
        this.title_back_layout = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.title_back_layout.setOnClickListener(this);
        this.title_edit_tv = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.title_edit_tv.setVisibility(8);
        this.title_main_tv = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.title_main_tv.setText("二维码扫描");
        this.scanPreview = (SurfaceView) findViewById(C2425R.C2427id.capture_preview);
        this.scanContainer = (RelativeLayout) findViewById(C2425R.C2427id.capture_container);
        this.scanCropView = (RelativeLayout) findViewById(C2425R.C2427id.capture_crop_view);
        this.scanLine = (ImageView) findViewById(C2425R.C2427id.capture_scan_line);
        this.inactivityTimer = new InactivityTimer(this);
        this.beepManager = new BeepManager(this);
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 2, 0.0f, 2, 0.9f);
        translateAnimation.setDuration(4500);
        translateAnimation.setRepeatCount(-1);
        translateAnimation.setRepeatMode(1);
        this.scanLine.startAnimation(translateAnimation);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void requestPermissions() {
        String[] strArr = {"android.permission.CAMERA"};
        final String str = "在设置-应用-" + SystemUtil.m14528d() + "-权限中开启相机权限，以正常使用功能";
        if (checkPermissions(this, strArr)) {
            return;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
            GBGlobalConfig.m10510c().mo24391a((Activity) this, str);
        } else {
            PermissionsManager.m4670a().mo21377a(this, strArr, new PermissionsResultAction() {
                /* class com.gbcom.gwifi.third.zxing.CaptureActivity.C34452 */

                @Override // com.anthonycr.grant.PermissionsResultAction
                public void onGranted() {
                    CaptureActivity.this.finish();
                    CaptureActivity.this.startActivity(CaptureActivity.this.getIntent());
                }

                @Override // com.anthonycr.grant.PermissionsResultAction
                public void onDenied(String str) {
                    GBGlobalConfig.m10510c().mo24391a((Activity) CaptureActivity.this, str);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
        this.cameraManager = new CameraManager(getApplicationContext());
        this.handler = null;
        if (this.isHasSurface) {
            initCamera(this.scanPreview.getHolder());
        } else {
            this.scanPreview.getHolder().addCallback(this);
        }
        this.inactivityTimer.onResume();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onPause() {
        if (this.handler != null) {
            this.handler.quitSynchronously();
            this.handler = null;
        }
        this.inactivityTimer.onPause();
        this.beepManager.close();
        this.cameraManager.closeDriver();
        if (!this.isHasSurface) {
            this.scanPreview.getHolder().removeCallback(this);
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        this.inactivityTimer.shutdown();
        super.onDestroy();
    }

    public void handleDecode(String str, Bundle bundle) {
        Intent intent;
        if (FormatUtil.m14234e(str)) {
            this.handler.restartPreviewAndDecode();
            return;
        }
        this.inactivityTimer.onActivity();
        this.beepManager.playBeepSoundAndVibrate();
        Intent intent2 = new Intent();
        bundle.putInt("width", this.mCropRect.width());
        bundle.putInt("height", this.mCropRect.height());
        bundle.putString("result", str);
        intent2.putExtras(bundle);
        setResult(-1, intent2);
        if (C3467s.m14513e(this.from) || this.from.equals("webview")) {
            intent = new Intent(GBApplication.GIWIFI_QRCODE_SCAN_CALLBACK);
        } else {
            intent = new Intent(GBApplication.GIWIFI_QRCODE_SCAN_FUNC_CALLBACK);
        }
        intent.putExtra("text", str);
        sendBroadcast(intent);
        finish();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided.");
        } else if (this.cameraManager.isOpen()) {
            Log.d(C6765d.f24844O, "initCamera() while already open -- late surface callback?");
        } else {
            try {
                this.cameraManager.openDriver(surfaceHolder);
                if (this.handler == null) {
                    this.handler = new CaptureActivityHandler(this, this.cameraManager, 768);
                }
                initCrop();
            } catch (IOException e) {
                e.printStackTrace();
                displayFrameworkBugMessageAndExit();
            } catch (RuntimeException e2) {
                Log.w(C6765d.f24844O, "Unexpected error initializing camera", e2);
                displayFrameworkBugMessageAndExit();
            }
        }
    }

    private void displayFrameworkBugMessageAndExit() {
    }

    public void restartPreviewAfterDelay(long j) {
        if (this.handler != null) {
            this.handler.sendEmptyMessageDelayed(C2425R.C2427id.restart_preview, j);
        }
    }

    public Rect getCropRect() {
        return this.mCropRect;
    }

    private void initCrop() {
        int i = this.cameraManager.getCameraResolution().y;
        int i2 = this.cameraManager.getCameraResolution().x;
        int[] iArr = new int[2];
        this.scanCropView.getLocationInWindow(iArr);
        int i3 = iArr[0];
        int statusBarHeight = iArr[1] - getStatusBarHeight();
        int width = this.scanCropView.getWidth();
        int height = this.scanCropView.getHeight();
        int width2 = this.scanContainer.getWidth();
        int height2 = this.scanContainer.getHeight();
        int i4 = (i3 * i) / width2;
        int i5 = (statusBarHeight * i2) / height2;
        this.mCropRect = new Rect(i4, i5, ((i * width) / width2) + i4, ((i2 * height) / height2) + i5);
    }

    private int getStatusBarHeight() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            Log.e(C6765d.f24844O, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!this.isHasSurface) {
            this.isHasSurface = true;
            initCamera(surfaceHolder);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.isHasSurface = false;
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            default:
                return;
        }
    }
}
