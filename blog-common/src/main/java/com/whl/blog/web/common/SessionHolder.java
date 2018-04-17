package com.whl.blog.web.common;

import java.util.Map;

/**
 * Created by whling on 2017/6/18.
 *
 */
public final class SessionHolder {

    private static final ThreadLocal<Map<String, String>> holder = new ThreadLocal<>();

    public static Map<String, String> getMap() {
        return holder.get();
    }

    public static String getValue(String key) {
        return holder.get().get(key);
    }

    public static void setMap(Map<String, String> merMap) {
        holder.set(merMap);
    }

    public static void clear() {
        holder.remove();
    }

}
