package com.gbcom.gwifi.third.umeng.analytics;

import android.content.Context;

public class GiwifiAnalyticsService {
    private static GiwifiAnalyticsService instance;

    public static GiwifiAnalyticsService getInstance(Context context) {
        if (instance == null) {
            instance = new GiwifiAnalyticsService(context);
        }
        return instance;
    }

    public GiwifiAnalyticsService(Context context) {
        GiwifiMobclickAgent.setPageCollectionMode(4);
        GiwifiMobclickAgent.setCatchUncaughtExceptions(false);
    }
}
