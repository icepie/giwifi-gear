package com.gbcom.gwifi.third.umeng.analytics;

import android.content.Context;
import com.umeng.analytics.MobclickAgent;
import java.util.Map;

public class GiwifiMobclickAgent {
    public static final int AUTO = 1;
    public static final int LEGACY_AUTO = 3;
    public static final int LEGACY_MANUAL = 4;
    public static final int MANUAL = 2;
    public static final int SCENARIO_TYPE_ANALYTICS_OEM = 3;
    public static final int SCENARIO_TYPE_GAME = 2;
    public static final int SCENARIO_TYPE_GAME_OEM = 4;
    public static final int SCENARIO_TYPE_NORMAL = 1;

    public static void onPageStart(String str) {
        MobclickAgent.onPageStart(str);
    }

    public static void onPageEnd(String str) {
        MobclickAgent.onPageEnd(str);
    }

    public static void onPause(Context context) {
        MobclickAgent.onPause(context);
    }

    public static void onResume(Context context) {
        MobclickAgent.onResume(context);
    }

    public static void reportError(Context context, String str) {
        MobclickAgent.reportError(context, str);
    }

    public static void onEvent(Context context, String str) {
        MobclickAgent.onEvent(context, str);
    }

    public static void onEvent(Context context, String str, Map<String, String> map) {
        MobclickAgent.onEvent(context, str, map);
    }

    public static void onEventValue(Context context, String str, Map<String, String> map, int i) {
        MobclickAgent.onEventValue(context, str, map, i);
    }

    public static void onKillProcess(Context context) {
        MobclickAgent.onKillProcess(context);
    }

    public static void setPageCollectionMode(int i) {
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.LEGACY_MANUAL);
    }

    public static void setScenarioType(Context context, int i) {
        MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }

    public static void setCatchUncaughtExceptions(boolean z) {
        MobclickAgent.setCatchUncaughtExceptions(z);
    }
}
