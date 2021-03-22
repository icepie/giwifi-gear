package com.gbcom.gwifi.third.umeng.analytics;

import android.content.Context;
import java.util.HashMap;

public class UmengCount {
    private static final String APP_BACK_SCREEN_AD = "app_back_screen_ad";
    private static final String APP_CUSTOMER_SERVICE_ACTION = "app_customer_service_action";
    private static final String APP_DOWNLOAD_ACTION = "app_download_action";
    private static final String APP_INSERT_SCREEN_AD = "app_insert_screen_ad";
    private static final String APP_INSTALL_ACTION = "app_install_action";
    private static final String APP_LAUNCHER_SCREEN_AD = "app_launcher_screen_ad";
    private static final String APP_LAUNCH_CUSTOM_ADS = "app_launch_custom_ads";
    private static final String APP_NEW_LOGIN_IN = "app_new_login_in";
    private static final String APP_NEW_SET_PASSWORD = "app_new_set_password";
    private static final String APP_NEW_UI_ACTION = "app_new_ui_action";
    private static final String ATTENTION_ACTION = "attention_action";
    private static final String CLOSE_WIFI_ACTION = "close_wifi_action";
    private static final String CONNECT_GWIFI_ACTION = "connect_gwifi_action";
    private static final String ENTERTAINMENT_BANNER_ACTION = "entertainment_banner_action";
    private static final String ENTERTAINMENT_FUNCTION_ACTION = "entertainment_function_action";
    private static final String EXIT_APP_ACTION = "exit_app_action";
    private static final String GAME_DOWNLOAD_ACTION = "game_download_action";
    private static final String GAME_INSTALL_ACTION = "game_install_action";
    private static final String LOGIN_ACTION = "login_action";
    private static final String MAKE_SCORE_ACTION = "make_score_aciton";
    private static final String MINIPROGRAM_LAUNCH_ACTION = "miniprogram_launch_action";
    private static final String MOVIE_ATTENTION_COUNT_ACTION = "movie_attention_count_action";
    private static final String MOVIE_VISIT_COUNT_ACTION = "movie_visit_count_action";
    private static final String NAVIGATION_FUNCTION = "navigation_function";
    private static final String NEAR_WIFI_ACTION = "near_wifi_action";
    private static final String NEW_NATIVE_FRAGMENT_FUNCTION_ACTION = "new_native_fragment_function_action";
    private static final String NEW_NATIVE_FRAGMENT_TOP_ACTION = "new_native_fragment_top_action";
    private static final String NOTIFY_CLICK_ACTION = "notify_click_action";
    private static final String NOTIFY_ITEM_CLICK_ACTION = "notify_item_click_action";
    private static final String OFFICE_AD_ACTION = "office_ad_action";
    private static final String OFFICE_NAVIGATION_ACTION = "office_navigation_action";
    private static final String OFFLINE_ACTION = "offline_action";
    private static final String ONE_KEY_LINE_ACTION = "one_key_line_action";
    private static final String OTHER_ACTION = "other_action";
    private static final String PLAY_TIME_ACTION = "play_time_action";
    private static final String POST_APP_ITEM_ACTION = "post_app_item_action";
    private static final String POST_GAME_ITEM_ACTION = "post_game_item_action";
    private static final String POST_MOVIE_ITEM_ACTION = "post_movie_item_action";
    private static final String POST_VARIETY_ITEM_ACTION = "post_variety_item_action";
    private static final String POST_VIDEO_ITEM_ACTION = "post_video_item_action";
    private static final String RECOMMEND_FRIEND_ACTION = "recommend_friend_action";
    private static final String REGISTER_ACTION = "register_action";
    private static final String SCORE_ACTION = "score_action";
    private static final String START_APP_ACTION = "start_app_action";
    private static final String WEB_VISIT_ACTION = "web_visit_action";
    private static final String WIFI_NAVIGATION_LIST_ACTION = "wifi_navigation_list_action";
    private static final String WIFI_POST_LIST_ACTION = "wifi_post_list_action";
    private static boolean isOpen = true;
    private static final String str = "time_test2";

    public static void controlSwitch(boolean z) {
        isOpen = z;
    }

    public static void queryOneKeyLine(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, ONE_KEY_LINE_ACTION);
        }
    }

    public static void queryConnectGwifi(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, CONNECT_GWIFI_ACTION);
        }
    }

    public static void queryNearWifi(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, NEAR_WIFI_ACTION);
        }
    }

    public static void queryOffLine(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, OFFLINE_ACTION);
        }
    }

    public static void queryCloseWifi(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, CLOSE_WIFI_ACTION);
        }
    }

    public static void queryExitApp(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, EXIT_APP_ACTION);
        }
    }

    public static void queryStartApp(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, START_APP_ACTION);
        }
    }

    public static void queryNavigation(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, NAVIGATION_FUNCTION, hashMap);
        }
    }

    public static void queryLauncherScreenAd(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, APP_LAUNCHER_SCREEN_AD, hashMap);
        }
    }

    public static void queryInsertScreenAd(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, APP_INSERT_SCREEN_AD, hashMap);
        }
    }

    public static void queryBackScreenAd(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, APP_BACK_SCREEN_AD, hashMap);
        }
    }

    public static void queryOther(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, OTHER_ACTION, hashMap);
        }
    }

    public static void queryLogin(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, LOGIN_ACTION);
        }
    }

    public static void queryOfficeAds(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, OFFICE_AD_ACTION);
        }
    }

    public static void queryOfficeNavigation(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, OFFICE_NAVIGATION_ACTION, hashMap);
        }
    }

    public static void queryRegister(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, REGISTER_ACTION);
        }
    }

    public static void queryMakeScore(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, MAKE_SCORE_ACTION);
        }
    }

    public static void queryScore(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, SCORE_ACTION);
        }
    }

    public static void queryRecommendFriend(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, RECOMMEND_FRIEND_ACTION);
        }
    }

    public static void queryNotifyClick(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, NOTIFY_CLICK_ACTION);
        }
    }

    public static void queryAttention(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, ATTENTION_ACTION);
        }
    }

    public static void queryEntertainmentBanner(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, ENTERTAINMENT_BANNER_ACTION, hashMap);
        }
    }

    public static void queryEntertainmentFunction(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, ENTERTAINMENT_FUNCTION_ACTION, hashMap);
        }
    }

    public static void queryPostvideo(Context context, String str2, boolean z) {
        if (isOpen && str2 != null) {
            if (!z) {
                HashMap hashMap = new HashMap();
                hashMap.put("type", str2);
                GiwifiMobclickAgent.onEvent(context, POST_VIDEO_ITEM_ACTION, hashMap);
                return;
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, WIFI_POST_LIST_ACTION, hashMap2);
        }
    }

    public static void queryPostMovie(Context context, String str2, boolean z) {
        if (isOpen && str2 != null) {
            if (!z) {
                HashMap hashMap = new HashMap();
                hashMap.put("type", str2);
                GiwifiMobclickAgent.onEvent(context, POST_MOVIE_ITEM_ACTION, hashMap);
                return;
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, WIFI_POST_LIST_ACTION, hashMap2);
        }
    }

    public static void queryPostVariety(Context context, String str2, boolean z) {
        if (isOpen && str2 != null) {
            if (!z) {
                HashMap hashMap = new HashMap();
                hashMap.put("type", str2);
                GiwifiMobclickAgent.onEvent(context, POST_VARIETY_ITEM_ACTION, hashMap);
                return;
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, WIFI_POST_LIST_ACTION, hashMap2);
        }
    }

    public static void queryPostApp(Context context, String str2, boolean z) {
        if (isOpen && str2 != null) {
            if (!z) {
                HashMap hashMap = new HashMap();
                hashMap.put("type", str2);
                GiwifiMobclickAgent.onEvent(context, POST_APP_ITEM_ACTION, hashMap);
                return;
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, WIFI_POST_LIST_ACTION, hashMap2);
        }
    }

    public static void queryPostGame(Context context, String str2, boolean z) {
        if (isOpen && str2 != null) {
            if (!z) {
                HashMap hashMap = new HashMap();
                hashMap.put("type", str2);
                GiwifiMobclickAgent.onEvent(context, POST_GAME_ITEM_ACTION, hashMap);
                return;
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, WIFI_POST_LIST_ACTION, hashMap2);
        }
    }

    public static void queryCustomAdCount(Context context, String str2, String str3) {
        if (isOpen && str2 != null && str3 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(str2, str3);
            GiwifiMobclickAgent.onEvent(context, APP_LAUNCH_CUSTOM_ADS, hashMap);
        }
    }

    public static void queryMovieVisitCount(Context context, String str2, String str3) {
        if (isOpen && str2 != null && str3 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(str2, str3);
            GiwifiMobclickAgent.onEvent(context, MOVIE_ATTENTION_COUNT_ACTION, hashMap);
        }
    }

    public static void queryMovieAttentionCount(Context context, String str2, String str3) {
        if (isOpen && str2 != null && str3 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(str2, str3);
            GiwifiMobclickAgent.onEvent(context, MOVIE_ATTENTION_COUNT_ACTION, hashMap);
        }
    }

    public static void queryPlayTime(Context context, String str2, String str3, int i) {
        if (isOpen && i > 0 && str2 != null && str3 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(str2, str3);
            GiwifiMobclickAgent.onEventValue(context, PLAY_TIME_ACTION, hashMap, i / 1000);
        }
    }

    public static void queryWebVisit(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, WEB_VISIT_ACTION, hashMap);
        }
    }

    public static void addTabClick(Context context, String str2, String str3) {
        if (isOpen && str3 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str3);
            GiwifiMobclickAgent.onEvent(context, str2, hashMap);
        }
    }

    public static void queryNext(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, APP_NEW_UI_ACTION);
        }
    }

    public static void queryLoginIn(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, APP_NEW_LOGIN_IN);
        }
    }

    public static void queryModifyPassword(Context context) {
        if (isOpen) {
            GiwifiMobclickAgent.onEvent(context, APP_NEW_SET_PASSWORD);
        }
    }

    public static void queryNotifyItem(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, NOTIFY_ITEM_CLICK_ACTION, hashMap);
        }
    }

    public static void queryWifiNavigationList(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, WIFI_NAVIGATION_LIST_ACTION, hashMap);
        }
    }

    public static void queryAppCustomerService(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str2);
            GiwifiMobclickAgent.onEvent(context, APP_CUSTOMER_SERVICE_ACTION, hashMap);
        }
    }

    public static void queryGameDownloadCount(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("游戏下载完成", str2);
            GiwifiMobclickAgent.onEvent(context, GAME_DOWNLOAD_ACTION, hashMap);
        }
    }

    public static void queryGameInstallCount(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("游戏安装完成", str2);
            GiwifiMobclickAgent.onEvent(context, GAME_INSTALL_ACTION, hashMap);
        }
    }

    public static void queryAppDownloadCount(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("应用下载完成", str2);
            GiwifiMobclickAgent.onEvent(context, APP_DOWNLOAD_ACTION, hashMap);
        }
    }

    public static void queryAppInstallCount(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("应用安装完成", str2);
            GiwifiMobclickAgent.onEvent(context, APP_INSTALL_ACTION, hashMap);
        }
    }

    public static void queryNativePost(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("新本地顶部推荐", str2);
            GiwifiMobclickAgent.onEvent(context, NEW_NATIVE_FRAGMENT_TOP_ACTION, hashMap);
        }
    }

    public static void queryNativeFunction(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("新本地功能推荐", str2);
            GiwifiMobclickAgent.onEvent(context, NEW_NATIVE_FRAGMENT_FUNCTION_ACTION, hashMap);
        }
    }

    public static void queryMiniProgramLaunchAction(Context context, String str2) {
        if (isOpen && str2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("userName", str2);
            GiwifiMobclickAgent.onEvent(context, MINIPROGRAM_LAUNCH_ACTION, hashMap);
        }
    }
}
