package com.whl.blog.web.common.ocx;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by whling on 2017/6/16.
 *
 */
public class AESWithJCE {

    public AESWithJCE() {
    }

    public static String getCipher(String transferKey, String data) {
        String var2 = "E639CE75A6E443858FE1BFAD9475B54D";

        try {
            Base64 base64 = new Base64();
            if(data.contains(" ")) {
                data = data.replaceAll(" ", "+");
            }

            byte[] cypherArray = data.getBytes();
            Rijndael aes256 = new Rijndael();
            aes256.makeKey(var2.getBytes(), var2.length() * 8);
            byte[] tmp = aes256.decryptArrayNP(transferKey.getBytes(), 0);
            byte[] realKey = new byte[32];
            System.arraycopy(base64.encode(tmp), 0, realKey, 0, 32);
            aes256.makeKey(realKey, realKey.length * 8);
            byte[] plainArray = aes256.encryptArrayNP(cypherArray, 0);
            String plainText = (new Base64()).encodeToString(plainArray).trim();
            return plainText;
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    public static String getResult(String transferKey, String data) {
        String var2 = "D4A6AE7B18BB4c769E6E011AE8B23338";

        try {
            Base64 base64 = new Base64();
            if(data.contains(" ")) {
                data = data.replaceAll(" ", "+");
            }

            byte[] cypherArray = base64.decode(data);
            Rijndael aes256 = new Rijndael();
            aes256.makeKey(var2.getBytes(), var2.length() * 8);
            byte[] tmp = aes256.decryptArrayNP(transferKey.getBytes(), 0);
            byte[] realKey = new byte[32];
            System.arraycopy(base64.encode(tmp), 0, realKey, 0, 32);
            aes256.makeKey(realKey, realKey.length * 8);
            byte[] plainArray = aes256.decryptArrayNP(cypherArray, 0);
            String plainText = new String(plainArray);
            return plainText;
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    public static String getResultHex(String transferKey, String data) {
        String decryptData = getResult(transferKey, data);
        byte[] hextmp = Base64.decodeBase64(decryptData.toString());
        String hexdata = bytesToHexString(hextmp);
        return hexdata;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if(src != null && src.length > 0) {
            for(int i = 0; i < src.length; ++i) {
                int v = src[i] & 255;
                String hv = Integer.toHexString(v);
                if(hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        String key = "38003580610868258441717007075341";
        String data = "38003580610868258441717007075341";
        String miwen = getCipher(key, data);
        if(miwen.contains(" ")) {
            miwen = miwen.replaceAll(" ", "+");
        }

        String ming = getResult(key, miwen);
        System.out.println("密文：" + miwen + "明文:" + ming);
    }

}
