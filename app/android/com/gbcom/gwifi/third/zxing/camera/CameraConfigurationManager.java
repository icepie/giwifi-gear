package com.gbcom.gwifi.third.zxing.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import p419io.netty.handler.codec.http.HttpConstants;

public final class CameraConfigurationManager {
    private static final double MAX_ASPECT_DISTORTION = 0.15d;
    private static final int MIN_PREVIEW_PIXELS = 153600;
    private static final String TAG = "CameraConfiguration";
    private Point cameraResolution;
    private final Context context;
    private Point screenResolution;

    public CameraConfigurationManager(Context context2) {
        this.context = context2;
    }

    public void initFromCameraParameters(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        Display defaultDisplay = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
        new Point();
        this.screenResolution = getDisplaySize(defaultDisplay);
        Log.i(TAG, "Screen resolution: " + ((Object) this.screenResolution));
        Point point = new Point();
        point.x = this.screenResolution.x;
        point.y = this.screenResolution.y;
        if (this.screenResolution.x < this.screenResolution.y) {
            point.x = this.screenResolution.y;
            point.y = this.screenResolution.x;
        }
        this.cameraResolution = findBestPreviewSizeValue(parameters, point);
        Log.i(TAG, "Camera resolution x: " + this.cameraResolution.x);
        Log.i(TAG, "Camera resolution y: " + this.cameraResolution.y);
    }

    @SuppressLint({"NewApi"})
    private Point getDisplaySize(Display display) {
        Point point = new Point();
        try {
            display.getSize(point);
        } catch (NoSuchMethodError e) {
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        return point;
    }

    public void setDesiredCameraParameters(Camera camera, boolean z) {
        Camera.Parameters parameters = camera.getParameters();
        if (parameters == null) {
            Log.w(TAG, "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i(TAG, "Initial camera parameters: " + parameters.flatten());
        if (z) {
            Log.w(TAG, "In camera config safe mode -- most settings will not be honored");
        }
        parameters.setPreviewSize(this.cameraResolution.x, this.cameraResolution.y);
        camera.setParameters(parameters);
        Camera.Size previewSize = camera.getParameters().getPreviewSize();
        if (!(previewSize == null || (this.cameraResolution.x == previewSize.width && this.cameraResolution.y == previewSize.height))) {
            Log.w(TAG, "Camera said it supported preview size " + this.cameraResolution.x + 'x' + this.cameraResolution.y + ", but after setting it, preview size is " + previewSize.width + 'x' + previewSize.height);
            this.cameraResolution.x = previewSize.width;
            this.cameraResolution.y = previewSize.height;
        }
        camera.setDisplayOrientation(90);
    }

    public Point getCameraResolution() {
        return this.cameraResolution;
    }

    public Point getScreenResolution() {
        return this.screenResolution;
    }

    private Point findBestPreviewSizeValue(Camera.Parameters parameters, Point point) {
        int i;
        int i2;
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null) {
            Log.w(TAG, "Device returned no supported preview sizes; using default");
            Camera.Size previewSize = parameters.getPreviewSize();
            return new Point(previewSize.width, previewSize.height);
        }
        ArrayList<Camera.Size> arrayList = new ArrayList(supportedPreviewSizes);
        Collections.sort(arrayList, new Comparator<Camera.Size>() {
            /* class com.gbcom.gwifi.third.zxing.camera.CameraConfigurationManager.C34481 */

            public int compare(Camera.Size size, Camera.Size size2) {
                int i = size.height * size.width;
                int i2 = size2.height * size2.width;
                if (i2 < i) {
                    return -1;
                }
                if (i2 > i) {
                    return 1;
                }
                return 0;
            }
        });
        if (Log.isLoggable(TAG, 4)) {
            StringBuilder sb = new StringBuilder();
            for (Camera.Size size : arrayList) {
                sb.append(size.width).append('x').append(size.height).append(HttpConstants.SP_CHAR);
            }
            Log.i(TAG, "Supported preview sizes: " + ((Object) sb));
        }
        double d = ((double) point.x) / ((double) point.y);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Camera.Size size2 = (Camera.Size) it.next();
            int i3 = size2.width;
            int i4 = size2.height;
            if (i3 * i4 < MIN_PREVIEW_PIXELS) {
                it.remove();
            } else {
                boolean z = i3 < i4;
                if (z) {
                    i = i4;
                } else {
                    i = i3;
                }
                if (z) {
                    i2 = i3;
                } else {
                    i2 = i4;
                }
                if (Math.abs((((double) i) / ((double) i2)) - d) > MAX_ASPECT_DISTORTION) {
                    it.remove();
                } else if (i == point.x && i2 == point.y) {
                    Point point2 = new Point(i3, i4);
                    Log.i(TAG, "Found preview size exactly matching screen size: " + ((Object) point2));
                    return point2;
                }
            }
        }
        if (!arrayList.isEmpty()) {
            Camera.Size size3 = (Camera.Size) arrayList.get(0);
            Point point3 = new Point(size3.width, size3.height);
            Log.i(TAG, "Using largest suitable preview size: " + ((Object) point3));
            return point3;
        }
        Camera.Size previewSize2 = parameters.getPreviewSize();
        Point point4 = new Point(previewSize2.width, previewSize2.height);
        Log.i(TAG, "No suitable preview sizes, using default: " + ((Object) point4));
        return point4;
    }
}
