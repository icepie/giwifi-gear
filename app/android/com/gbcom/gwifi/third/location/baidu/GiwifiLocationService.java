package com.gbcom.gwifi.third.location.baidu;

import android.content.Context;
import com.alipay.p082e.p083a.p084a.p090e.p091a.C1438a;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class GiwifiLocationService {
    private LocationClient mLocationClient;

    public GiwifiLocationService(Context context, final GiwifiLocationListener giwifiLocationListener) {
        this.mLocationClient = new LocationClient(context);
        this.mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            /* class com.gbcom.gwifi.third.location.baidu.GiwifiLocationService.C34191 */

            @Override // com.baidu.location.BDAbstractLocationListener
            public void onReceiveLocation(BDLocation bDLocation) {
                giwifiLocationListener.onReceiveLocation(bDLocation.getLatitude(), bDLocation.getLongitude());
            }
        });
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        locationClientOption.setCoorType(BDLocation.BDLOCATION_GCJ02_TO_BD09LL);
        locationClientOption.setScanSpan(100);
        locationClientOption.setOpenGps(true);
        locationClientOption.setIsNeedAddress(true);
        locationClientOption.setIgnoreKillProcess(true);
        locationClientOption.SetIgnoreCacheException(false);
        locationClientOption.setWifiCacheTimeOut(C1438a.f3099a);
        locationClientOption.setEnableSimulateGps(false);
        this.mLocationClient.setLocOption(locationClientOption);
    }

    public void start() {
        if (this.mLocationClient != null) {
            this.mLocationClient.start();
        }
    }

    public void stop() {
        if (this.mLocationClient != null) {
            this.mLocationClient.stop();
        }
    }
}
