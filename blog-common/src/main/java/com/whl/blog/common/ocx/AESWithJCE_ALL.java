package com.whl.blog.common.ocx;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by whling on 2017/6/16.
 *
 */
public class AESWithJCE_ALL {

    public static String getResult(String transferKey, String data, String saltKey)
    {
        try
        {
            Base64 base64 = new Base64();
            if (data.contains(" ")) {
                data = data.replaceAll(" ", "+");
            }
            byte[] cypherArray = base64.decode(data.getBytes());

            Rijndael aes256 = new Rijndael();

            aes256.makeKey(saltKey.getBytes(), saltKey.length() * 8);

            byte[] tmp = aes256.decryptArrayNP(transferKey.getBytes(), 0);

            byte[] realKey = new byte[32];
            System.arraycopy(base64.encode(tmp), 0, realKey, 0, 32);
            aes256.makeKey(realKey, realKey.length * 8);

            byte[] plainArray = aes256.decryptArrayNP(cypherArray, 0);
            return new String(plainArray).trim();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
        String key = "gx8j0xxacjnjh5ehxkppxnyzlxvaxxma";
        String data = "ElubkhLyjLjrp23vVcXRpw==";
        if (data.contains(" ")) {
            data = data.replaceAll(" ", "+");
        }
    }

}
