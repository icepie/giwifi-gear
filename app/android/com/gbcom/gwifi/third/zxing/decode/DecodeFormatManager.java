package com.gbcom.gwifi.third.zxing.decode;

import com.p136b.p145b.BarcodeFormat;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

public class DecodeFormatManager {
    private static final Set<BarcodeFormat> INDUSTRIAL_FORMATS = EnumSet.m39050of((Enum) BarcodeFormat.CODE_39, (Enum) BarcodeFormat.CODE_93, (Enum) BarcodeFormat.CODE_128, (Enum) BarcodeFormat.ITF, (Enum) BarcodeFormat.CODABAR);
    private static final Set<BarcodeFormat> ONE_D_FORMATS = EnumSet.copyOf(PRODUCT_FORMATS);
    private static final Set<BarcodeFormat> PRODUCT_FORMATS = EnumSet.m39051of((Enum) BarcodeFormat.UPC_A, (Enum[]) new BarcodeFormat[]{BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED});
    private static final Set<BarcodeFormat> QR_CODE_FORMATS = EnumSet.m39046of((Enum) BarcodeFormat.QR_CODE);

    static {
        ONE_D_FORMATS.addAll(INDUSTRIAL_FORMATS);
    }

    public static Collection<BarcodeFormat> getQrCodeFormats() {
        return QR_CODE_FORMATS;
    }

    public static Collection<BarcodeFormat> getBarCodeFormats() {
        return ONE_D_FORMATS;
    }
}
