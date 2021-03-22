package com.gbcom.gwifi.third.zxing.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import anet.channel.entity.ConnType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.RejectedExecutionException;

public class AutoFocusManager implements Camera.AutoFocusCallback {
    private static final long AUTO_FOCUS_INTERVAL_MS = 2000;
    private static final Collection<String> FOCUS_MODES_CALLING_AF = new ArrayList(2);
    private static final String TAG = AutoFocusManager.class.getSimpleName();
    private final Camera camera;
    private boolean focusing;
    private AsyncTask<?, ?, ?> outstandingTask;
    private boolean stopped;
    private final boolean useAutoFocus;

    static {
        FOCUS_MODES_CALLING_AF.add(ConnType.PK_AUTO);
        FOCUS_MODES_CALLING_AF.add("macro");
    }

    public AutoFocusManager(Context context, Camera camera2) {
        this.camera = camera2;
        String focusMode = camera2.getParameters().getFocusMode();
        this.useAutoFocus = FOCUS_MODES_CALLING_AF.contains(focusMode);
        Log.i(TAG, "Current focus mode '" + focusMode + "'; use auto focus? " + this.useAutoFocus);
        start();
    }

    public synchronized void onAutoFocus(boolean z, Camera camera2) {
        this.focusing = false;
        autoFocusAgainLater();
    }

    @SuppressLint({"NewApi"})
    private synchronized void autoFocusAgainLater() {
        if (!this.stopped && this.outstandingTask == null) {
            AutoFocusTask autoFocusTask = new AutoFocusTask();
            try {
                if (Build.VERSION.SDK_INT >= 11) {
                    autoFocusTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                } else {
                    autoFocusTask.execute(new Object[0]);
                }
                this.outstandingTask = autoFocusTask;
            } catch (RejectedExecutionException e) {
                Log.w(TAG, "Could not request auto focus", e);
            }
        }
    }

    public synchronized void start() {
        if (this.useAutoFocus) {
            this.outstandingTask = null;
            if (!this.stopped && !this.focusing) {
                try {
                    this.camera.autoFocus(this);
                    this.focusing = true;
                } catch (RuntimeException e) {
                    Log.w(TAG, "Unexpected exception while focusing", e);
                    autoFocusAgainLater();
                }
            }
        }
    }

    private synchronized void cancelOutstandingTask() {
        if (this.outstandingTask != null) {
            if (this.outstandingTask.getStatus() != AsyncTask.Status.FINISHED) {
                this.outstandingTask.cancel(true);
            }
            this.outstandingTask = null;
        }
    }

    public synchronized void stop() {
        this.stopped = true;
        if (this.useAutoFocus) {
            cancelOutstandingTask();
            try {
                this.camera.cancelAutoFocus();
            } catch (RuntimeException e) {
                Log.w(TAG, "Unexpected exception while cancelling focusing", e);
            }
        }
    }

    /* access modifiers changed from: private */
    public final class AutoFocusTask extends AsyncTask<Object, Object, Object> {
        private AutoFocusTask() {
        }

        /* access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            AutoFocusManager.this.start();
            return null;
        }
    }
}
