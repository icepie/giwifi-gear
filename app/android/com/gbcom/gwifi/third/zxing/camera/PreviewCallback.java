package com.gbcom.gwifi.third.zxing.camera;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

public class PreviewCallback implements Camera.PreviewCallback {
    private static final String TAG = PreviewCallback.class.getSimpleName();
    private final CameraConfigurationManager configManager;
    private Handler previewHandler;
    private int previewMessage;

    public PreviewCallback(CameraConfigurationManager cameraConfigurationManager) {
        this.configManager = cameraConfigurationManager;
    }

    public void setHandler(Handler handler, int i) {
        this.previewHandler = handler;
        this.previewMessage = i;
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        Point cameraResolution = this.configManager.getCameraResolution();
        Handler handler = this.previewHandler;
        if (cameraResolution == null || handler == null) {
            Log.d(TAG, "Got preview callback, but no handler or resolution available");
            return;
        }
        handler.obtainMessage(this.previewMessage, cameraResolution.x, cameraResolution.y, bArr).sendToTarget();
        this.previewHandler = null;
    }
}
