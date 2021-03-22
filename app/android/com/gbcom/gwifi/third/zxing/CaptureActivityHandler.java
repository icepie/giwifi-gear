package com.gbcom.gwifi.third.zxing;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.third.zxing.camera.CameraManager;
import com.gbcom.gwifi.third.zxing.decode.DecodeThread;

public class CaptureActivityHandler extends Handler {
    private final CaptureActivity activity;
    private final CameraManager cameraManager;
    private final DecodeThread decodeThread;
    private State state = State.SUCCESS;

    /* access modifiers changed from: private */
    public enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public CaptureActivityHandler(CaptureActivity captureActivity, CameraManager cameraManager2, int i) {
        this.activity = captureActivity;
        this.decodeThread = new DecodeThread(captureActivity, i);
        this.decodeThread.start();
        this.cameraManager = cameraManager2;
        cameraManager2.startPreview();
        restartPreviewAndDecode();
    }

    public void handleMessage(Message message) {
        if (message.what == 2131755025) {
            restartPreviewAndDecode();
        } else if (message.what == 2131755018) {
            this.state = State.SUCCESS;
            this.activity.handleDecode((String) message.obj, message.getData());
        } else if (message.what == 2131755017) {
            this.state = State.PREVIEW;
            this.cameraManager.requestPreviewFrame(this.decodeThread.getHandler(), C2425R.C2427id.decode);
        } else if (message.what == 2131755026) {
            this.activity.setResult(-1, (Intent) message.obj);
            this.activity.finish();
        }
    }

    public void quitSynchronously() {
        this.state = State.DONE;
        this.cameraManager.stopPreview();
        Message.obtain(this.decodeThread.getHandler(), (int) C2425R.C2427id.quit).sendToTarget();
        try {
            this.decodeThread.join(500);
        } catch (InterruptedException e) {
        }
        removeMessages(C2425R.C2427id.decode_succeeded);
        removeMessages(C2425R.C2427id.decode_failed);
    }

    public void restartPreviewAndDecode() {
        if (this.state == State.SUCCESS) {
            this.state = State.PREVIEW;
            this.cameraManager.requestPreviewFrame(this.decodeThread.getHandler(), C2425R.C2427id.decode);
        }
    }
}
