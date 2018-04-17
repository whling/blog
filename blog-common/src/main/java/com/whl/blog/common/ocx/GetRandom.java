package com.whl.blog.common.ocx;

import java.util.Random;

/**
 * Created by whling on 2017/6/16.
 *
 */
public class GetRandom {

    public static final String allChar = "xlzxhxjnyj5u0evam0cmc8zkpxvg28ok";

    public static String generateString(int length)
    {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        System.out.println(generateString(32));
    }

}
