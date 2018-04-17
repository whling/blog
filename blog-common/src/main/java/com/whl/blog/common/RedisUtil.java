package com.whl.blog.common;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.Map;

/**
 * Created by whling on 2017/6/10.
 *
 */
public class RedisUtil {


    private static final Logger log     = LoggerFactory.getLogger(RedisUtil.class);

    private static String REDIS_KEY     = "JPP_M_";

    private static final JedisPool pool;

    static {
        JedisPoolConfig writePoolConfig = getPoolConfig();
        pool = new JedisPool( writePoolConfig, MerchantContext.REDIS_HOST, MerchantContext.REDIS_PORT);
        set("TEST", "TRUE");
        del("TEST");
    }

    private static JedisPoolConfig getPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(MerchantContext.REDIS_MAX_IDLE);
        poolConfig.setMaxTotal(MerchantContext.REDIS_MAX_TOTAL);
        poolConfig.setTestOnBorrow(MerchantContext.REDIS_TEST_ON_BORROW);
        poolConfig.setMaxWaitMillis(MerchantContext.REDIS_MAX_WAIT_MILLIS);
        poolConfig.setMinEvictableIdleTimeMillis(MerchantContext.REDIS_MIN_EVICTABLE_IDLE_TIME_MILLS);
        log.info("redis pool config info is {}", new String[]{JSON.toJSONString(poolConfig)});
        return poolConfig;
    }

    public static JedisPool getPool() {
        return pool;
    }

    public static int getActiveNumber() {
        return pool.getNumActive();
    }

    public static int getIdleNumber() {
        return pool.getNumIdle();
    }

    public static int getWaitNumber() {
        return pool.getNumWaiters();
    }

    public static void close(Jedis jedis) {
        close(pool, jedis);
    }

    private static void close(JedisPool pool, Jedis jedis) {
        if (jedis != null && pool != null) {
            pool.returnResource(jedis);
        }
    }


    public static String makeKey(String key) {
        if (key.startsWith(REDIS_KEY)) {
            return key;
        }
        return REDIS_KEY + key;
    }

    public static void set(String key, String value) {
        Assert.notEmpty(key, "key无意义");
        Assert.notNull(value, "value不能为null");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(makeKey(key), value);
        } finally {
            close(jedis);
        }
    }

    public static void setWithExpire(String key, String value, int expire) {
        Assert.notEmpty(key, "key无意义");
        Assert.notNull(value, "value不能为null");
        Assert.isTrue(expire > 0, "expire不能小于或等于0");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.setex(makeKey(key), expire, value);
        } finally {
            close(jedis);
        }
    }

    public static String get(String key) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.get(makeKey(key));
        } finally {
            close(jedis);
        }
    }

    /**
     * 带过期时间的保存
     * @param expireAt
     */
    public  static void setExpireAt(String key, long expireAt) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.expireAt(makeKey(key), expireAt);
        }  finally {
            close(jedis);
        }
    }

    /**
     * 带过期时间的保存
     * @param expire 过期时间的时间,注意精确到秒！！！
     */
    public  static void setExpire(String key, int expire) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.expire(makeKey(key), expire);
        }  finally {
            close(jedis);
        }
    }

    public static boolean isExists(String key) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.exists(makeKey(key));
        }  finally {
            close(jedis);
        }
    }

    public static Long getExpireWithSecond(String key) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.ttl(makeKey(key));
        } finally {
            close(jedis);
        }
    }


    /**
     * 由Redis保证的原子加操作
     */
    public static long plus(String key, long value) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.incrBy(makeKey(key), value);
        } finally {
            close(jedis);
        }

    }

    public static long lpush(String key, String value) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lpush(key, value);
        } finally {
            close(jedis);
        }
    }

    public static String ltrim(String key, long start, long end) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.ltrim(key, start, end);
        } finally {
            close(jedis);
        }
    }



    public static  void addIntoHashMap(String key, Map<String, String> valueMap) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.hmset(makeKey(key), valueMap);
        } finally {
            close(jedis);
        }
    }

    public static  void addIntoHashMap(String key, String field, String value) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.hset(makeKey(key), field, value);
        } finally {
            close(jedis);
        }
    }

    public static  String getFromHashMap(String key, String field) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hget(makeKey(key), field);
        } finally {
            close(jedis);
        }
    }

    public static boolean existInMap(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hexists(makeKey(key), field);
        } finally {
            close(jedis);
        }
    }

    public static Map<String, String> getHashMap(String key) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hgetAll(makeKey(key));
        } finally {
            close(jedis);
        }
    }

    public static long del(String key) {
        Assert.notEmpty(key, "key无意义");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.del(makeKey(key));
        } finally {
            close(jedis);
        }
    }

    public static void addIntoSortedSet(String key, long weight, String member) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.zadd(makeKey(key), weight, member);
        } finally {
            close(jedis);
        }
    }

    public static long getSortedSetSize(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zcard(makeKey(key));
        } finally {
            close(jedis);
        }
    }

    public static boolean deleteFromSortedSet(String key, String member) {
        Assert.notEmpty(key, "key无意义");
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zrem(makeKey(key), member) == 1;
        } finally {
            close(jedis);
        }
    }

    public static void pipelinedWithoutReturn(PipelinedProxy pipelinedProxy) {
        Assert.notNull(pipelinedProxy, "无效参数");
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Pipeline pipeline = jedis.pipelined();
            pipelinedProxy.proxy(pipeline);
            pipeline.sync();
        } finally {
            close(jedis);
        }
    }

    public static List<Object> pipelinedWithReturn(PipelinedProxy pipelinedProxy) {
        Assert.notNull(pipelinedProxy, "无效参数");
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Pipeline pipeline = jedis.pipelined();
            pipelinedProxy.proxy(pipeline);
            return pipeline.syncAndReturnAll();
        } finally {
            close(jedis);
        }
    }

    public interface PipelinedProxy {
        public void proxy(Pipeline pipeline);
    }


}
