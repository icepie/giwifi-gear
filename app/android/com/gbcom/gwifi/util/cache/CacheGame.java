package com.gbcom.gwifi.util.cache;

import com.gbcom.gwifi.functions.p242c.p243a.GameConfig;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.GsonUtil;

public class CacheGame extends CacheBase {
    private static final String CACHE_GAME_CONFIG = "CACHE_GAME_CONFIG";
    private static CacheGame instance = null;

    public static CacheGame getInstance() {
        if (instance == null) {
            instance = new CacheGame();
        }
        return instance;
    }

    public void setGameConfig(GameConfig aVar) {
        if (aVar == null) {
            setStringValue(CACHE_GAME_CONFIG, "");
            return;
        }
        String b = GsonUtil.m14241a().mo21597b(aVar);
        if (!C3467s.m14513e(b)) {
            setStringValue(CACHE_GAME_CONFIG, b);
        }
    }

    public GameConfig getGameConfig() {
        String stringValue = getStringValue(CACHE_GAME_CONFIG);
        if (!stringValue.equals("")) {
            return (GameConfig) GsonUtil.m14241a().mo21588a(stringValue, GameConfig.class);
        }
        return null;
    }
}
