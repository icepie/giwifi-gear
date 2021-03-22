package com.gbcom.gwifi.util.p257b;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/* renamed from: com.gbcom.gwifi.util.b.f */
public class UIUtil {
    /* renamed from: a */
    public static void m14202a(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillAfter(false);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setFillAfter(false);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(300);
        animationSet.setFillAfter(false);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            /* class com.gbcom.gwifi.util.p257b.UIUtil.animationAnimation$AnimationListenerC34531 */

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
            }
        });
        view.startAnimation(animationSet);
    }

    /* renamed from: b */
    public static void m14204b(View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(300);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setStartOffset(100);
        rotateAnimation.setInterpolator(new OvershootInterpolator(2.0f));
        view.startAnimation(rotateAnimation);
    }

    /* renamed from: a */
    public static void m14203a(final View view, Float f, Float f2, Float f3, Float f4) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        Float valueOf = Float.valueOf(f.floatValue() - ((float) iArr[0]));
        Float valueOf2 = Float.valueOf(f3.floatValue() - ((float) iArr[0]));
        Float valueOf3 = Float.valueOf(f2.floatValue() - ((float) iArr[1]));
        Float valueOf4 = Float.valueOf(f4.floatValue() - ((float) iArr[1]));
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 720.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(1500);
        rotateAnimation.setFillAfter(false);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        TranslateAnimation translateAnimation = new TranslateAnimation(valueOf.floatValue(), valueOf2.floatValue(), valueOf3.floatValue(), valueOf4.floatValue());
        translateAnimation.setDuration(1500);
        translateAnimation.setFillAfter(false);
        translateAnimation.setInterpolator(new AnticipateInterpolator());
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(1500);
        animationSet.setFillAfter(false);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            /* class com.gbcom.gwifi.util.p257b.UIUtil.animationAnimation$AnimationListenerC34542 */

            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.setVisibility(8);
            }
        });
        view.startAnimation(animationSet);
    }

    /* renamed from: c */
    public static void m14205c(View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(400);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        view.startAnimation(rotateAnimation);
    }

    /* renamed from: d */
    public static void m14206d(View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(180.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(400);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        view.startAnimation(rotateAnimation);
    }
}
