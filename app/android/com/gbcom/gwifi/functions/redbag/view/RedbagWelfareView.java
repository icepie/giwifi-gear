package com.gbcom.gwifi.functions.redbag.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.redbag.RedbagMainActivity;
import com.gbcom.gwifi.functions.redbag.domain.LuckyRedbagSimple;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagBettingRequest;
import com.gbcom.gwifi.functions.redbag.msg.LuckyRedbagDetailRequest;
import com.gbcom.gwifi.util.cache.CacheAccount;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RedbagWelfareView extends LinearLayout {

    /* renamed from: a */
    private int f11388a;

    /* renamed from: b */
    private int f11389b;

    /* renamed from: c */
    private int f11390c;

    /* renamed from: d */
    private int f11391d;

    /* renamed from: e */
    private ImageView f11392e;

    /* renamed from: f */
    private LuckyRedbagSimple f11393f;

    /* renamed from: g */
    private Executor f11394g;

    /* renamed from: h */
    private ObjectAnimator f11395h;

    /* renamed from: i */
    private View f11396i;

    /* renamed from: j */
    private int f11397j;

    /* renamed from: k */
    private int f11398k;

    /* renamed from: l */
    private RedbagMainActivity f11399l;

    /* renamed from: m */
    private int f11400m;

    /* renamed from: n */
    private int f11401n;

    /* renamed from: o */
    private int f11402o;

    /* renamed from: p */
    private RelativeLayout f11403p;

    /* renamed from: q */
    private RelativeLayout f11404q;

    /* renamed from: r */
    private RelativeLayout f11405r;

    /* renamed from: s */
    private boolean f11406s;

    public RedbagWelfareView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RedbagWelfareView(Context context) {
        this(context, null);
    }

    public RedbagWelfareView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11394g = Executors.newFixedThreadPool(1);
        this.f11406s = true;
    }

    /* renamed from: a */
    private void m12608a() {
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(-15.0f, 15.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setRepeatMode(2);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(Integer.MAX_VALUE);
        rotateAnimation.setFillAfter(true);
        animationSet.addAnimation(rotateAnimation);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setRepeatMode(2);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setRepeatCount(Integer.MAX_VALUE);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        setAnimation(animationSet);
        animationSet.start();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        int i2;
        int i3 = 10;
        switch (motionEvent.getAction()) {
            case 0:
                this.f11388a = (int) motionEvent.getRawX();
                this.f11389b = (int) motionEvent.getRawY();
                this.f11390c = (int) motionEvent.getRawX();
                this.f11391d = (int) motionEvent.getRawY();
                break;
            case 1:
                int rawX = ((int) motionEvent.getRawX()) - this.f11390c;
                int rawY = ((int) motionEvent.getRawY()) - this.f11391d;
                if (Math.abs(rawX) < 5 || Math.abs(rawY) < 5) {
                    this.f11393f.getLuckyRedbagId();
                    int isJoin = this.f11393f.getIsJoin();
                    int status = this.f11393f.getStatus();
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams.leftMargin = getLeft();
                    layoutParams.topMargin = getTop();
                    layoutParams.setMargins(getLeft(), getTop(), 0, 0);
                    setLayoutParams(layoutParams);
                    try {
                        if (((Boolean) getTag()).booleanValue()) {
                            this.f11394g.execute(new Runnable() {
                                /* class com.gbcom.gwifi.functions.redbag.view.RedbagWelfareView.RunnableC30691 */

                                @Override // java.lang.Runnable
                                public void run() {
                                    LuckyRedbagDetailRequest luckyRedbagDetailRequest = new LuckyRedbagDetailRequest();
                                    luckyRedbagDetailRequest.setLuckyRedbagId(RedbagWelfareView.this.f11393f.getLuckyRedbagId());
                                    luckyRedbagDetailRequest.setAccountId(CacheAccount.getInstance().getUserId());
                                    RedbagMainActivity.channel.writeAndFlush(luckyRedbagDetailRequest);
                                }
                            });
                            break;
                        }
                    } catch (Exception e) {
                    }
                    if (status == 0) {
                        if (isJoin != 1) {
                            m12610b();
                            break;
                        } else {
                            this.f11394g.execute(new Runnable() {
                                /* class com.gbcom.gwifi.functions.redbag.view.RedbagWelfareView.RunnableC30702 */

                                @Override // java.lang.Runnable
                                public void run() {
                                    LuckyRedbagDetailRequest luckyRedbagDetailRequest = new LuckyRedbagDetailRequest();
                                    luckyRedbagDetailRequest.setLuckyRedbagId(RedbagWelfareView.this.f11393f.getLuckyRedbagId());
                                    luckyRedbagDetailRequest.setAccountId(CacheAccount.getInstance().getUserId());
                                    RedbagMainActivity.channel.writeAndFlush(luckyRedbagDetailRequest);
                                }
                            });
                            break;
                        }
                    } else {
                        this.f11394g.execute(new Runnable() {
                            /* class com.gbcom.gwifi.functions.redbag.view.RedbagWelfareView.RunnableC30713 */

                            @Override // java.lang.Runnable
                            public void run() {
                                LuckyRedbagDetailRequest luckyRedbagDetailRequest = new LuckyRedbagDetailRequest();
                                luckyRedbagDetailRequest.setLuckyRedbagId(RedbagWelfareView.this.f11393f.getLuckyRedbagId());
                                luckyRedbagDetailRequest.setAccountId(CacheAccount.getInstance().getUserId());
                                RedbagMainActivity.channel.writeAndFlush(luckyRedbagDetailRequest);
                            }
                        });
                        break;
                    }
                }
            case 2:
                int rawX2 = (int) motionEvent.getRawX();
                int rawY2 = (int) motionEvent.getRawY();
                int i4 = rawX2 - this.f11388a;
                int i5 = rawY2 - this.f11389b;
                int left = getLeft();
                int top = getTop();
                int right = getRight();
                int bottom = getBottom();
                if (left < 10) {
                    right = getMeasuredWidth() + 10;
                } else {
                    i3 = left;
                }
                if (right > this.f11397j - 10) {
                    right = this.f11397j - 10;
                    i3 = (this.f11397j - 10) - getMeasuredWidth();
                }
                if (top < this.f11401n) {
                    i2 = this.f11401n;
                    i = getMeasuredHeight() + i2;
                } else {
                    i = bottom;
                    i2 = top;
                }
                if (i > this.f11400m + this.f11401n) {
                    i = this.f11400m + this.f11401n;
                    i2 = i - getMeasuredHeight();
                }
                layout(i3 + i4, i2 + i5, right + i4, i + i5);
                this.f11406s = false;
                Log.d("kkkkkk", "welfare.getHeight() ------------->" + this.f11398k);
                Log.d("kkkkkk", "welfare.getBottom() ------------->" + getBottom());
                postInvalidate();
                this.f11388a = rawX2;
                this.f11389b = rawY2;
                break;
        }
        return true;
    }

    /* renamed from: a */
    public void mo26750a(LuckyRedbagSimple luckyRedbagSimple) {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        this.f11397j = windowManager.getDefaultDisplay().getWidth();
        this.f11398k = windowManager.getDefaultDisplay().getHeight();
        List<GBActivity> activitiesList = GBApplication.instance().getActivitiesList();
        this.f11399l = null;
        for (int i = 0; i < activitiesList.size(); i++) {
            GBActivity gBActivity = activitiesList.get(i);
            if (gBActivity instanceof RedbagMainActivity) {
                this.f11399l = (RedbagMainActivity) gBActivity;
            }
        }
        if (this.f11399l != null) {
            this.f11400m = ((ListView) this.f11399l.findViewById(C2425R.C2427id.lv_main)).getMeasuredHeight();
            this.f11401n = ((LinearLayout) this.f11399l.findViewById(C2425R.C2427id.ll_head)).getMeasuredHeight();
            this.f11402o = ((RelativeLayout) this.f11399l.findViewById(C2425R.C2427id.my_title_layout)).getMeasuredHeight();
        }
        this.f11393f = luckyRedbagSimple;
        int status = this.f11393f.getStatus();
        int isJoin = luckyRedbagSimple.getIsJoin();
        this.f11396i = LayoutInflater.from(getContext()).inflate(C2425R.layout.redbag_item_welfareview_gain, (ViewGroup) null);
        this.f11403p = (RelativeLayout) this.f11396i.findViewById(C2425R.C2427id.rl_out);
        this.f11404q = (RelativeLayout) this.f11396i.findViewById(C2425R.C2427id.rl_already_gain);
        this.f11405r = (RelativeLayout) this.f11396i.findViewById(C2425R.C2427id.rl_welfare);
        this.f11396i.findViewById(2131755542).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.view.RedbagWelfareView.View$OnClickListenerC30724 */

            public void onClick(View view) {
                RedbagWelfareView.this.f11396i.setVisibility(8);
            }
        });
        this.f11396i.findViewById(C2425R.C2427id.imageView_out).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.view.RedbagWelfareView.View$OnClickListenerC30735 */

            public void onClick(View view) {
                RedbagWelfareView.this.f11396i.setVisibility(8);
            }
        });
        if (status == 0) {
            if (isJoin == 1) {
                this.f11403p.setVisibility(8);
                this.f11405r.setVisibility(8);
                this.f11404q.setVisibility(0);
                if (this.f11395h != null && this.f11395h.isRunning()) {
                    this.f11395h.cancel();
                }
            } else {
                this.f11403p.setVisibility(8);
                this.f11404q.setVisibility(8);
                this.f11405r.setVisibility(0);
                m12608a();
            }
        } else if (status == 1) {
            this.f11405r.setVisibility(8);
            this.f11404q.setVisibility(8);
            this.f11403p.setVisibility(0);
            if (this.f11395h != null && this.f11395h.isRunning()) {
                this.f11395h.cancel();
            }
        }
        removeAllViews();
        addView(this.f11396i);
    }

    /* renamed from: b */
    private void m12610b() {
        RedbagMainActivity redbagMainActivity;
        RedbagMainActivity redbagMainActivity2 = null;
        View inflate = LayoutInflater.from(getContext()).inflate(C2425R.layout.redbag_popup_welfare_betting, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.iv_gain);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        List<GBActivity> activitiesList = GBApplication.instance().getActivitiesList();
        int i = 0;
        while (i < activitiesList.size()) {
            GBActivity gBActivity = activitiesList.get(i);
            if (gBActivity instanceof RedbagMainActivity) {
                redbagMainActivity = (RedbagMainActivity) gBActivity;
            } else {
                redbagMainActivity = redbagMainActivity2;
            }
            i++;
            redbagMainActivity2 = redbagMainActivity;
        }
        popupWindow.showAsDropDown(redbagMainActivity2.findViewById(C2425R.C2427id.my_title_layout));
        inflate.findViewById(C2425R.C2427id.iv_close).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.view.RedbagWelfareView.View$OnClickListenerC30746 */

            public void onClick(View view) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.redbag.view.RedbagWelfareView.View$OnClickListenerC30757 */

            public void onClick(View view) {
                try {
                    if (((Boolean) view.getTag()).booleanValue()) {
                        LuckyRedbagDetailRequest luckyRedbagDetailRequest = new LuckyRedbagDetailRequest();
                        luckyRedbagDetailRequest.setLuckyRedbagId(RedbagWelfareView.this.f11393f.getLuckyRedbagId());
                        luckyRedbagDetailRequest.setAccountId(CacheAccount.getInstance().getUserId());
                        RedbagMainActivity.channel.writeAndFlush(luckyRedbagDetailRequest);
                    }
                } catch (Exception e) {
                    RedbagWelfareView.this.f11394g.execute(new Runnable() {
                        /* class com.gbcom.gwifi.functions.redbag.view.RedbagWelfareView.View$OnClickListenerC30757.RunnableC30761 */

                        @Override // java.lang.Runnable
                        public void run() {
                            LuckyRedbagBettingRequest luckyRedbagBettingRequest = new LuckyRedbagBettingRequest();
                            luckyRedbagBettingRequest.setLuckyRedbagId(RedbagWelfareView.this.f11393f.getLuckyRedbagId());
                            luckyRedbagBettingRequest.setAccountId(CacheAccount.getInstance().getUserId());
                            RedbagMainActivity.channel.writeAndFlush(luckyRedbagBettingRequest);
                        }
                    });
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                    }
                }
            }
        });
    }

    /* renamed from: b */
    public void mo26751b(LuckyRedbagSimple luckyRedbagSimple) {
        this.f11393f = luckyRedbagSimple;
        int status = this.f11393f.getStatus();
        int isJoin = luckyRedbagSimple.getIsJoin();
        if (status == 0) {
            if (isJoin == 1) {
                this.f11403p.setVisibility(8);
                this.f11405r.setVisibility(8);
                this.f11404q.setVisibility(0);
                clearAnimation();
                return;
            }
            this.f11403p.setVisibility(8);
            this.f11404q.setVisibility(8);
            this.f11405r.setVisibility(0);
            m12608a();
        } else if (status == 1) {
            setRotationY(0.0f);
            this.f11405r.setVisibility(8);
            this.f11404q.setVisibility(8);
            this.f11403p.setVisibility(0);
            clearAnimation();
        }
    }
}
