package com.whl.blog.common;

/**
 * Created by pasta on 2017/6/5.
 *
 */
public class BlogContext {

    public static String ENV;

    /**
     * Cookie info
     * */
    public static String SIT_PATH;
    public static String SIT_DOMAIN;
    public static int    SIT_MAXAGE;

    /**
     * Redis Info
     * */
    public static String    REDIS_HOST;
    public static int       REDIS_PORT;
    public static int       REDIS_MAX_IDLE;
    public static int       REDIS_MAX_TOTAL;
    public static boolean   REDIS_TEST_ON_BORROW;
    public static int       REDIS_MAX_WAIT_MILLIS;
    public static int       REDIS_MIN_EVICTABLE_IDLE_TIME_MILLS;
    public static String    FILEUPLOADPATH;
    
    /**
     * ocxpge
     * */
    public static String    OCX_PGE_CERT;


    public static void initMerchantContext(PPSUtil ppsUtil) {

        ENV                                 = ppsUtil.getEValue("env").getValue();

        /**
         * Cookie info
         * */
        SIT_PATH                            = ppsUtil.getEValue("sit.path").getValue();
        SIT_DOMAIN                          = ppsUtil.getEValue("sit.domain").getValue();
        SIT_MAXAGE                          = ppsUtil.getEValue("sit.maxAge").getIntValue();

        /**
         * Redis Info
         * */
        REDIS_HOST                          = ppsUtil.getEValue("cache.host").getValue();
        REDIS_PORT                          = ppsUtil.getEValue("cache.port").getIntValue();
        REDIS_MAX_IDLE                      = ppsUtil.getEValue("cache.maxidle").getIntValue();
        REDIS_MAX_TOTAL                     = ppsUtil.getEValue("cache.maxtotal").getIntValue();
        REDIS_MAX_WAIT_MILLIS               = ppsUtil.getEValue("cache.maxwaitmillis").getIntValue();
        REDIS_TEST_ON_BORROW                = ppsUtil.getEValue("cache.testonborrow").getBooleanValue();
        REDIS_MIN_EVICTABLE_IDLE_TIME_MILLS = ppsUtil.getEValue("cache.softMinEvictableIdleTime").getIntValue();

        /**
         * ocxpge
         * */
        OCX_PGE_CERT                        = ppsUtil.getEValue("ocx.pge.cert").getValue();
        FILEUPLOADPATH						= ppsUtil.getEValue("upload.file.path").getValue();
    }

}
