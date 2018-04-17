package com.whl.blog.web.common;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by whling on 2017/6/15.
 *
 */
public class Base64Util {

    public static byte[] encode(byte[] bytes) {
        return Base64.encodeBase64(bytes);
    }

    public static byte[] decode(String base64Str) {
        return Base64.decodeBase64(base64Str);
    }

}
