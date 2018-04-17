package com.whl.blog.web.cache;

import com.whl.blog.web.common.RedisUtil;

import java.util.Map;

/**
 * Created by whling on 2018/4/17.
 */
public class BlogCache {

    private static final int CACHE_EXPIRE           = 60*60;

    private static final String BLOG_KEY            = "BLOG_";

    public static final String BLOG_ID              = BLOG_KEY.concat("ID");

    public static final String BLOG_STS             = BLOG_KEY.concat("STS");

    public static final String BLOG_CNM             = BLOG_KEY.concat("CNM");

    public static final String BLOG_PROV            = BLOG_KEY.concat("PROV");

    public static final String BLOG_CITY            = BLOG_KEY.concat("CITY");

    public static final String BLOG_ABBR            = BLOG_KEY.concat("ABBR");

    public static final String BLOG_AC_NO           = BLOG_KEY.concat("AC_NO");

    public static final String BLOG_USR_NO          = BLOG_KEY.concat("USR_NO");

    public static final String BLOG_CLR_AC_TYP      = BLOG_KEY.concat("CLR_AC_TYP");

    public static final String BLOG_OPR_NO          = BLOG_KEY.concat("OPR_NO");

    public static final String BLOG_OPR_ID          = BLOG_KEY.concat("OPR_ID");

    public static final String BLOG_OPR_STS         = BLOG_KEY.concat("OPR_STS");

    public static final String BLOG_OPR_NM          = BLOG_KEY.concat("OPR_NM");

    public static final String BLOG_OPR_TOKEN       = BLOG_KEY.concat("OPR_TOKEN");

    public static final String BLOG_OPR_LOG_ID      = BLOG_KEY.concat("OPR_LOG_ID");

    public static final String BLOG_OPR_HEAD_FLG    = BLOG_KEY.concat("OPR_HEAD_FLG");

    public static final String BLOG_LOGIN_TIME      = BLOG_KEY.concat("LOGIN_TIME");

    public static final String BLOG_REQUEST_ID      = BLOG_KEY.concat("REQUEST_ID");

    public static final String BLOG_REQUEST_IP      = BLOG_KEY.concat("REQUEST_IP");

    public static final String BLOG_REQUEST_URL     = BLOG_KEY.concat("REQUEST_URL");

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
        return BLOG_KEY.concat(oprLogId);
    }
}
