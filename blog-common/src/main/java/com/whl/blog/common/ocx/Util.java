package com.whl.blog.common.ocx;

/**
 * Created by whling on 2017/6/16.
 *
 */
public class Util {

    public static byte[] addByteArrays(byte[] first, byte[] second)
    {
        byte[] result = new byte[first.length + second.length];

        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);

        return result;
    }

}
