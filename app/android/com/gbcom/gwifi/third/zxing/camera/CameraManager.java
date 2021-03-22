package com.gbcom.gwifi.third.zxing.camera;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.gbcom.gwifi.third.zxing.camera.open.OpenCameraInterface;
import java.p456io.IOException;

public class CameraManager {
    private static final String TAG = CameraManager.class.getSimpleName();
    private AutoFocusManager autoFocusManager;
    private Camera camera;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private boolean initialized;
    private final PreviewCallback previewCallback;
    private boolean previewing;
    private int requestedCameraId = -1;

    public CameraManager(Context context2) {
        this.context = context2;
        this.configManager = new CameraConfigurationManager(context2);
        this.previewCallback = new PreviewCallback(this.configManager);
    }

    public synchronized void openDriver(SurfaceHolder surfaceHolder) throws IOException {
        Camera camera2 = this.camera;
        if (camera2 == null) {
            if (this.requestedCameraId >= 0) {
                camera2 = OpenCameraInterface.open(this.requestedCameraId);
            } else {
                camera2 = OpenCameraInterface.open();
            }
            if (camera2 == null) {
                throw new IOException();
            }
            this.camera = camera2;
        }
        camera2.setPreviewDisplay(surfaceHolder);
        if (!this.initialized) {
            this.initialized = true;
            this.configManager.initFromCameraParameters(camera2);
        }
        Camera.Parameters parameters = camera2.getParameters();
        String flatten = parameters == null ? null : parameters.flatten();
        try {
            this.configManager.setDesiredCameraParameters(camera2, false);
        } catch (RuntimeException e) {
            Log.w(TAG, "Camera rejected parameters. Setting only minimal safe-mode parameters");
            Log.i(TAG, "Resetting to saved camera params: " + flatten);
            if (flatten != null) {
                Camera.Parameters parameters2 = camera2.getParameters();
                parameters2.unflatten(flatten);
                try {
                    camera2.setParameters(parameters2);
                    this.configManager.setDesiredCameraParameters(camera2, true);
                } catch (RuntimeException e2) {
                    Log.w(TAG, "Camera rejected even safe-mode parameters! No configuration");
                }
            }
        }
    }

    public synchronized boolean isOpen() {
        return this.camera != null;
    }

    public synchronized void closeDriver() {
        if (this.camera != null) {
            this.camera.release();
            this.camera = null;
        }
    }

    public synchronized void startPreview() {
        Camera camera2 = this.camera;
        if (camera2 != null && !this.previewing) {
            camera2.startPreview();
            this.previewing = true;
            this.autoFocusManager = new AutoFocusManager(this.context, this.camera);
        }
    }

    public synchronized void stopPreview() {
        if (this.autoFocusManager != null) {
            this.autoFocusManager.stop();
            this.autoFocusManager = null;
        }
        if (this.camera != null && this.previewing) {
            this.camera.stopPreview();
            this.previewCallback.setHandler(null, 0);
            this.previewing = false;
        }
    }

    public synchronized void requestPreviewFrame(Handler handler, int i) {
        Camera camera2 = this.camera;
        if (camera2 != null && this.previewing) {
            this.previewCallback.setHandler(handler, i);
            camera2.setOneShotPreviewCallback(this.previewCallback);
        }
    }

    public synchronized void setManualCameraId(int i) {
        this.requestedCameraId = i;
    }

    public Point getCameraResolution() {
        return this.configManager.getCameraResolution();
    }

    public Camera.Size getPreviewSize() {
        if (this.camera != null) {
            return this.camera.getParameters().getPreviewSize();
        }
        return null;
    }
}
