package com.gbcom.gwifi.third.zxing.decode;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.third.zxing.CaptureActivity;
import com.p136b.p145b.BinaryBitmap;
import com.p136b.p145b.DecodeHintType;
import com.p136b.p145b.MultiFormatReader;
import com.p136b.p145b.PlanarYUVLuminanceSource;
import com.p136b.p145b.ReaderException;
import com.p136b.p145b.Result;
import com.p136b.p145b.p154c.HybridBinarizer;
import java.p456io.ByteArrayOutputStream;
import java.util.Map;

public class DecodeHandler extends Handler {
    private final CaptureActivity activity;
    private final MultiFormatReader multiFormatReader = new MultiFormatReader();
    private boolean running = true;

    public DecodeHandler(CaptureActivity captureActivity, Map<DecodeHintType, Object> map) {
        this.multiFormatReader.mo22286a(map);
        this.activity = captureActivity;
    }

    private static void bundleThumbnail(PlanarYUVLuminanceSource nVar, Bundle bundle) {
        int[] i = nVar.mo22288i();
        int j = nVar.mo22289j();
        Bitmap createBitmap = Bitmap.createBitmap(i, 0, j, j, nVar.mo22290k(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray(DecodeThread.BARCODE_BITMAP, byteArrayOutputStream.toByteArray());
    }

    public void handleMessage(Message message) {
        if (this.running) {
            if (message.what == 2131755016) {
                decode((byte[]) message.obj, message.arg1, message.arg2);
            } else if (message.what == 2131755024) {
                this.running = false;
                Looper.myLooper().quit();
            }
        }
    }

    private void decode(byte[] bArr, int i, int i2) {
        Camera.Size previewSize = this.activity.getCameraManager().getPreviewSize();
        if (previewSize != null) {
            byte[] bArr2 = new byte[bArr.length];
            for (int i3 = 0; i3 < previewSize.height; i3++) {
                for (int i4 = 0; i4 < previewSize.width; i4++) {
                    bArr2[(((previewSize.height * i4) + previewSize.height) - i3) - 1] = bArr[(previewSize.width * i3) + i4];
                }
            }
            int i5 = previewSize.width;
            previewSize.width = previewSize.height;
            previewSize.height = i5;
            Result rVar = null;
            PlanarYUVLuminanceSource buildLuminanceSource = buildLuminanceSource(bArr2, previewSize.width, previewSize.height);
            if (buildLuminanceSource != null) {
                try {
                    rVar = this.multiFormatReader.mo22287b(new BinaryBitmap(new HybridBinarizer(buildLuminanceSource)));
                } catch (ReaderException e) {
                } finally {
                    this.multiFormatReader.mo21714a();
                }
            }
            Handler handler = this.activity.getHandler();
            if (rVar != null) {
                String a = rVar.mo22291a();
                if (handler != null) {
                    Message obtain = Message.obtain(handler, C2425R.C2427id.decode_succeeded, a);
                    Bundle bundle = new Bundle();
                    bundleThumbnail(buildLuminanceSource, bundle);
                    obtain.setData(bundle);
                    obtain.sendToTarget();
                }
            } else if (handler != null) {
                Message.obtain(handler, (int) C2425R.C2427id.decode_failed).sendToTarget();
            }
        }
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i, int i2) {
        Rect cropRect = this.activity.getCropRect();
        if (cropRect == null) {
            return null;
        }
        return new PlanarYUVLuminanceSource(bArr, i, i2, cropRect.left, cropRect.top, cropRect.width(), cropRect.height(), false);
    }
}
