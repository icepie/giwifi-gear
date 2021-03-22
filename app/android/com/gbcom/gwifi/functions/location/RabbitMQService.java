package com.gbcom.gwifi.functions.location;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.p012v7.app.AppCompatActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;

public class RabbitMQService extends AppCompatActivity {

    /* renamed from: a */
    private static final String f9883a = "RabbitMQService";

    /* renamed from: b */
    private RabbitMQParam f9884b;

    /* renamed from: c */
    private SymmetricCryptoUtil f9885c;

    /* renamed from: d */
    private WifiManager f9886d;

    /* renamed from: e */
    private LocationInfo f9887e;

    /* renamed from: f */
    private RabbitMQService f9888f;

    public RabbitMQService getInstance() {
        if (this.f9888f == null) {
            this.f9888f = new RabbitMQService();
        }
        return this.f9888f;
    }

    public RabbitMQService() {
        try {
            this.f9885c = new SymmetricCryptoUtil(null, PublicPrivateKey.m11460a());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f9886d = (WifiManager) GBApplication.instance().getApplicationContext().getSystemService("wifi");
    }

    public void publishPositionToAMPQ() {
        new Thread(new Runnable() {
            /* class com.gbcom.gwifi.functions.location.RabbitMQService.RunnableC27851 */

            @Override // java.lang.Runnable
            public void run() {
                if (RabbitMQService.this.f9887e == null) {
                    RabbitMQService.this.f9887e = new LocationInfo();
                    RabbitMQService.this.f9887e.mo25194c(CacheApp.getInstance().getAppUuid());
                }
                RabbitMQService.this.f9887e.mo25192b(CacheAccount.getInstance().getLoginPhone());
                RabbitMQService.this.f9887e.mo25189a(CacheAccount.getInstance().getStudentNumber());
                RabbitMQService.this.f9887e.mo25188a(System.currentTimeMillis() / 1000);
                RabbitMQService.this.f9887e.mo25196d(CacheAuth.getInstance().getLocalMac());
                String t = WiFiUtil.m14021a().mo27626t();
                boolean isWifiEnabled = RabbitMQService.this.f9886d.isWifiEnabled();
                if (!isWifiEnabled && t.equals("")) {
                    return;
                }
                if (!isWifiEnabled && !t.equals("")) {
                    RabbitMQService.this.f9887e.mo25198e(t);
                    RabbitMQService.this.f9887e.mo25191b(0);
                    RabbitMQService.this.f9887e.mo25187a(0);
                    RabbitMQService.this.f9887e.mo25200f("");
                } else if ((!isWifiEnabled || !t.equals("")) && isWifiEnabled && !t.equals("")) {
                    RabbitMQService.this.f9887e.mo25198e(t);
                    WifiInfo connectionInfo = RabbitMQService.this.f9886d.getConnectionInfo();
                    if (connectionInfo != null) {
                        RabbitMQService.this.f9887e.mo25200f(connectionInfo.getBSSID());
                    }
                }
            }
        }).start();
    }

    public void setParam(RabbitMQParam cVar) {
        this.f9884b = cVar;
        m11438a();
    }

    /* renamed from: a */
    private void m11438a() {
    }

    /* renamed from: a */
    private void m11439a(byte[] bArr) {
        if (this.f9884b != null) {
            this.f9884b.mo25217g();
            this.f9884b.mo25219h();
            this.f9884b.mo25221i();
            this.f9884b.mo25215f();
        }
    }

    /* renamed from: b */
    private void m11441b() {
        if (this.f9884b != null) {
            this.f9884b.mo25215f();
        }
    }
}
