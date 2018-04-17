package com.whl.blog.web.common;


import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by whling on 2017/6/5.
 *
 */
public abstract class Assert {

    protected Assert() {
    }

    public static void notNull(Object object, String message) {
        if (null == object) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(String str, String message) {
        if (null == str || "".equals(str.trim())) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(String[] array, String message) {
        if (null == array || array.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean bool, String message) {
        if (!bool) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNumber(String num, String message) {
        if (!NumberUtils.isNumber(num)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isPositiveNumber(String num, String message) {
        if (!NumberUtils.isNumber(num) ||
                NumberUtils.toDouble(num, NumberUtils.DOUBLE_ZERO) <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

}