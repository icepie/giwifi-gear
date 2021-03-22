package com.gbcom.gwifi.functions.wifi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.support.annotation.RequiresApi;

@RequiresApi(api = 21)
/* renamed from: com.gbcom.gwifi.functions.wifi.a */
public class ConnectionStateMonitor extends ConnectivityManager.NetworkCallback {

    /* renamed from: a */
    final NetworkRequest f12848a = new NetworkRequest.Builder().addTransportType(0).addTransportType(1).build();

    /* renamed from: a */
    public void mo27457a(Context context) {
        ((ConnectivityManager) context.getSystemService("connectivity")).registerNetworkCallback(this.f12848a, this);
    }

    public void onAvailable(Network network) {
    }
}
