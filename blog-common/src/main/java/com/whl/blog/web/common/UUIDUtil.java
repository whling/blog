package com.whl.blog.web.common;

import java.util.UUID;

/**
 * Created by whling on 2017/6/27.
 *
 */
public class UUIDUtil {

    public static String genUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
