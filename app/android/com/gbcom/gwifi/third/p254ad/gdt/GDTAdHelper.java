package com.gbcom.gwifi.third.p254ad.gdt;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.gbcom.gwifi.C2425R;
import com.p278qq.p279e.ads.interstitial.AbstractInterstitialADListener;
import com.p278qq.p279e.ads.interstitial.InterstitialAD;
import com.p278qq.p279e.ads.nativ.ADSize;
import com.p278qq.p279e.ads.nativ.NativeExpressAD;
import com.p278qq.p279e.ads.nativ.NativeExpressADView;
import com.p278qq.p279e.ads.splash.SplashAD;
import com.p278qq.p279e.ads.splash.SplashADListener;
import com.p278qq.p279e.comm.util.AdError;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.gbcom.gwifi.third.ad.gdt.GDTAdHelper */
public class GDTAdHelper {
    private String TAG = "GDTAdHelper";
    private int adCount = 0;
    private List<NativeExpressADView> adList = new ArrayList();
    private InterstitialAD interGDTAd;
    private NativeExpressAD.NativeExpressADListener listener = new NativeExpressAD.NativeExpressADListener() {
        /* class com.gbcom.gwifi.third.p254ad.gdt.GDTAdHelper.C34111 */

        @Override // com.p278qq.p279e.ads.AbstractAD.BasicADListener
        public void onNoAD(AdError adError) {
            Log.i(GDTAdHelper.this.TAG, "onNoAD");
        }

        @Override // com.p278qq.p279e.ads.nativ.NativeExpressAD.NativeExpressADListener
        public void onADLoaded(List<NativeExpressADView> list) {
            Log.i(GDTAdHelper.this.TAG, "onADLoaded");
            if (GDTAdHelper.this.adList != null) {
                for (int i = 0; i < GDTAdHelper.this.adList.size(); i++) {
                    ((NativeExpressADView) GDTAdHelper.this.adList.get(i)).destroy();
                }
            }
            GDTAdHelper.this.adList.clear();
            GDTAdHelper.this.adList = list;
            if (GDTAdHelper.this.viewNativeADListener != null) {
                GDTAdHelper.this.viewNativeADListener.onADLoaded();
            }
        }

        @Override // com.p278qq.p279e.ads.nativ.NativeExpressAD.NativeExpressADListener
        public void onRenderFail(NativeExpressADView nativeExpressADView) {
            Log.i(GDTAdHelper.this.TAG, "onRenderFail");
        }

        @Override // com.p278qq.p279e.ads.nativ.NativeExpressAD.NativeExpressADListener
        public void onRenderSuccess(NativeExpressADView nativeExpressADView) {
            Log.i(GDTAdHelper.this.TAG, "onRenderSuccess");
        }

        @Override // com.p278qq.p279e.ads.nativ.NativeExpressAD.NativeExpressADListener
        public void onADExposure(NativeExpressADView nativeExpressADView) {
            Log.i(GDTAdHelper.this.TAG, "onADExposure");
        }

        @Override // com.p278qq.p279e.ads.nativ.NativeExpressAD.NativeExpressADListener
        public void onADClicked(NativeExpressADView nativeExpressADView) {
            Log.i(GDTAdHelper.this.TAG, "onADClicked");
            int i = 0;
            Object tag = nativeExpressADView.getTag();
            if (tag != null && (tag instanceof Integer)) {
                i = ((Integer) nativeExpressADView.getTag()).intValue();
            }
            if (GDTAdHelper.this.viewNativeADListener != null) {
                GDTAdHelper.this.viewNativeADListener.onADClicked(i);
            }
        }

        @Override // com.p278qq.p279e.ads.nativ.NativeExpressAD.NativeExpressADListener
        public void onADClosed(NativeExpressADView nativeExpressADView) {
            Log.i(GDTAdHelper.this.TAG, "onADClosed");
        }

        @Override // com.p278qq.p279e.ads.nativ.NativeExpressAD.NativeExpressADListener
        public void onADLeftApplication(NativeExpressADView nativeExpressADView) {
            Log.i(GDTAdHelper.this.TAG, "onADLeftApplication");
        }

        @Override // com.p278qq.p279e.ads.nativ.NativeExpressAD.NativeExpressADListener
        public void onADOpenOverlay(NativeExpressADView nativeExpressADView) {
            Log.i(GDTAdHelper.this.TAG, "onADOpenOverlay");
        }

        @Override // com.p278qq.p279e.ads.nativ.NativeExpressAD.NativeExpressADListener
        public void onADCloseOverlay(NativeExpressADView nativeExpressADView) {
            Log.i(GDTAdHelper.this.TAG, "onADCloseOverlay");
        }
    };
    private NativeExpressAD nativeExpressAD;
    private ViewNativeADListener viewNativeADListener;
    private ViewSplashADListener viewSplashADListener;

    /* renamed from: com.gbcom.gwifi.third.ad.gdt.GDTAdHelper$ViewNativeADListener */
    public interface ViewNativeADListener {
        void onADClicked(int i);

        void onADLoaded();
    }

    /* renamed from: com.gbcom.gwifi.third.ad.gdt.GDTAdHelper$ViewSplashADListener */
    public interface ViewSplashADListener {
        void onADClicked();

        void onADDismissed();

        void onADTick(long j);

        void onNoAD();
    }

    public void registerViewNativeADListener(ViewNativeADListener viewNativeADListener2) {
        this.viewNativeADListener = viewNativeADListener2;
    }

    public void loadGDTNativeExpressAD(Context context, ViewGroup viewGroup, int i, int i2, int i3) {
        ADSize aDSize = new ADSize(i, i2);
        if (this.nativeExpressAD == null) {
            this.nativeExpressAD = new NativeExpressAD(viewGroup.getContext(), aDSize, context.getResources().getString(C2425R.C2429string.GDTAppId), context.getResources().getString(C2425R.C2429string.GDTNativeExpressPosId), this.listener);
        }
        this.adCount = i3;
        try {
            this.nativeExpressAD.loadAD(i3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object showNativeExpressAD(ViewGroup viewGroup, NativeExpressADView nativeExpressADView) {
        if (!(viewGroup == null || nativeExpressADView == null)) {
            viewGroup.addView(nativeExpressADView);
            nativeExpressADView.render();
            nativeExpressADView.setOnTouchListener(new View.OnTouchListener() {
                /* class com.gbcom.gwifi.third.p254ad.gdt.GDTAdHelper.View$OnTouchListenerC34122 */

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
        }
        return nativeExpressADView;
    }

    private void showNativeExpressAD(RelativeLayout relativeLayout, NativeExpressADView nativeExpressADView) {
        if (relativeLayout != null && nativeExpressADView != null) {
            relativeLayout.addView(nativeExpressADView);
            nativeExpressADView.render();
        }
    }

    public Object showGDTNativeExpressAD(ViewGroup viewGroup, int i) {
        if (this.adList == null || this.adList.size() == 0) {
            return null;
        }
        NativeExpressADView nativeExpressADView = this.adList.get(i % this.adList.size());
        if (nativeExpressADView != null) {
            return showNativeExpressAD(viewGroup, nativeExpressADView);
        }
        return null;
    }

    public Object showGDTNativeExpressAD(ViewGroup viewGroup, int i, int i2, int i3) {
        int size = i % this.adList.size();
        if (this.adList == null || this.adList.size() == 0) {
            return null;
        }
        NativeExpressADView nativeExpressADView = this.adList.get(size);
        if (nativeExpressADView == null) {
            return null;
        }
        nativeExpressADView.setAdSize(new ADSize(i2, i3));
        return showNativeExpressAD(viewGroup, nativeExpressADView);
    }

    public void loadGDTNativeAD(Context context, int i, int i2, int i3) {
        ADSize aDSize = new ADSize(i, i2);
        if (this.nativeExpressAD == null) {
            this.nativeExpressAD = new NativeExpressAD(context, aDSize, context.getResources().getString(C2425R.C2429string.GDTAppId), context.getResources().getString(C2425R.C2429string.GDTNativePosId), this.listener);
        }
        this.adCount = i3;
        try {
            this.nativeExpressAD.loadAD(i3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showGDTNativeAD(RelativeLayout relativeLayout, int i) {
        NativeExpressADView nativeExpressADView;
        if (this.adList != null && this.adList.size() != 0 && (nativeExpressADView = this.adList.get(i % this.adList.size())) != null) {
            nativeExpressADView.setTag(Integer.valueOf(i));
            showNativeExpressAD(relativeLayout, nativeExpressADView);
        }
    }

    public void destroyGDTNativeAD() {
        if (this.adList != null) {
            for (int i = 0; i < this.adList.size(); i++) {
                NativeExpressADView nativeExpressADView = this.adList.get(i);
                if (nativeExpressADView != null) {
                    nativeExpressADView.destroy();
                }
            }
        }
    }

    public void registerViewSplashADListener(ViewSplashADListener viewSplashADListener2) {
        this.viewSplashADListener = viewSplashADListener2;
    }

    public void loadGDTSplashAD(Activity activity, ViewGroup viewGroup, View view) {
        new SplashAD(activity, view, activity.getResources().getString(C2425R.C2429string.GDTSplashPosId), new SplashADListener() {
            /* class com.gbcom.gwifi.third.p254ad.gdt.GDTAdHelper.C34133 */

            @Override // com.p278qq.p279e.ads.splash.SplashADListener
            public void onADDismissed() {
                Log.d(GDTAdHelper.this.TAG, "onADDismissed()");
                if (GDTAdHelper.this.viewSplashADListener != null) {
                    GDTAdHelper.this.viewSplashADListener.onADDismissed();
                }
            }

            @Override // com.p278qq.p279e.ads.splash.SplashADListener
            public void onNoAD(AdError adError) {
                Log.d(GDTAdHelper.this.TAG, "onNoAD()");
                Log.d(GDTAdHelper.this.TAG, String.format("onNoAD, eCode=%d, errorMsg=%s", Integer.valueOf(adError.getErrorCode()), adError.getErrorMsg()));
                if (GDTAdHelper.this.viewSplashADListener != null) {
                    GDTAdHelper.this.viewSplashADListener.onNoAD();
                }
            }

            @Override // com.p278qq.p279e.ads.splash.SplashADListener
            public void onADPresent() {
                Log.d(GDTAdHelper.this.TAG, "onADPresent()");
            }

            @Override // com.p278qq.p279e.ads.splash.SplashADListener
            public void onADClicked() {
                Log.d(GDTAdHelper.this.TAG, "onADClicked()");
                if (GDTAdHelper.this.viewSplashADListener != null) {
                    GDTAdHelper.this.viewSplashADListener.onADClicked();
                }
            }

            @Override // com.p278qq.p279e.ads.splash.SplashADListener
            public void onADTick(long j) {
                Log.d(GDTAdHelper.this.TAG, "onADTick()");
                if (GDTAdHelper.this.viewSplashADListener != null) {
                    GDTAdHelper.this.viewSplashADListener.onADTick(j);
                }
            }

            @Override // com.p278qq.p279e.ads.splash.SplashADListener
            public void onADExposure() {
                Log.d(GDTAdHelper.this.TAG, "onADExposure()");
            }

            @Override // com.p278qq.p279e.ads.splash.SplashADListener
            public void onADLoaded(long j) {
                Log.d(GDTAdHelper.this.TAG, "onADLoaded()");
            }
        }, 0).fetchAndShowIn(viewGroup);
    }

    public void loadGDTInterstitialAD(final Activity activity) {
        String string = activity.getResources().getString(C2425R.C2429string.GDTAppId);
        String string2 = activity.getResources().getString(C2425R.C2429string.GDTInterstitialPosId);
        if (this.interGDTAd == null) {
            this.interGDTAd = new InterstitialAD(activity, string, string2);
        }
        this.interGDTAd.setADListener(new AbstractInterstitialADListener() {
            /* class com.gbcom.gwifi.third.p254ad.gdt.GDTAdHelper.C34144 */

            @Override // com.p278qq.p279e.ads.interstitial.InterstitialADListener
            public void onADReceive() {
                GDTAdHelper.this.interGDTAd.show();
            }

            @Override // com.p278qq.p279e.ads.interstitial.InterstitialADListener
            public void onNoAD(AdError adError) {
                Toast.makeText(activity, adError.getErrorMsg().toString(), 0).show();
            }
        });
        try {
            this.interGDTAd.loadAD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
