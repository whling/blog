package com.whl.blog.web.cache;

import com.whl.blog.common.RedisUtil;

import java.util.Map;

/**
 * Created by whling on 2018/4/17.
 */
public class BlogCache {

    private static final int CACHE_EXPIRE          = 60*60;

    private static final String MER_KEY            = "MER_";

    public static final String MER_ID              = MER_KEY.concat("ID");

    public static final String MER_STS             = MER_KEY.concat("STS");

    public static final String MER_CNM             = MER_KEY.concat("CNM");

    public static final String MER_PROV            = MER_KEY.concat("PROV");

    public static final String MER_CITY            = MER_KEY.concat("CITY");

    public static final String MER_ABBR            = MER_KEY.concat("ABBR");

    public static final String MER_AC_NO           = MER_KEY.concat("AC_NO");

    public static final String MER_USR_NO          = MER_KEY.concat("USR_NO");

    public static final String MER_CLR_AC_TYP      = MER_KEY.concat("CLR_AC_TYP");

    public static final String MER_OPR_NO          = MER_KEY.concat("OPR_NO");

    public static final String MER_OPR_ID          = MER_KEY.concat("OPR_ID");

    public static final String MER_OPR_STS         = MER_KEY.concat("OPR_STS");

    public static final String MER_OPR_NM          = MER_KEY.concat("OPR_NM");

    public static final String MER_OPR_TOKEN       = MER_KEY.concat("OPR_TOKEN");

    public static final String MER_OPR_LOG_ID      = MER_KEY.concat("OPR_LOG_ID");

    public static final String MER_OPR_HEAD_FLG    = MER_KEY.concat("OPR_HEAD_FLG");

    public static final String MER_LOGIN_TIME      = MER_KEY.concat("LOGIN_TIME");

    public static final String MER_REQUEST_ID      = MER_KEY.concat("REQUEST_ID");

    public static final String MER_REQUEST_IP      = MER_KEY.concat("REQUEST_IP");

    public static final String MER_REQUEST_URL     = MER_KEY.concat("REQUEST_URL");

    public static void addCache(String sessionToken, Map<String, String> cacheMap) {
        RedisUtil.addIntoHashMap(makeKey(sessionToken), cacheMap);
        RedisUtil.setExpire(makeKey(sessionToken), CACHE_EXPIRE);
    }

    public static void delCache(String oprLogId) {
        RedisUtil.del(makeKey(oprLogId));
    }

    public static boolean isTimeout(String oprLogId) {
        return !RedisUtil.isExists(makeKey(oprLogId));
    }

    public static String getValue(String oprLogId, String field) {
        return RedisUtil.getFromHashMap(makeKey(oprLogId), field);
    }

    public static Map<String, String> getCacheMap(String oprLogId) {
        return RedisUtil.getHashMap(makeKey(oprLogId));
    }

    private static String makeKey(String oprLogId) {
        return MER_KEY.concat(oprLogId);
    }
}
