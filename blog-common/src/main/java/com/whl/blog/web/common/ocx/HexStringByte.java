package com.whl.blog.web.common.ocx;

/**
 * Created by whling on 2017/6/16.
 *
 */
public class HexStringByte {

    public static String stringToHex(String bin)
    {
        char[] digital = "0123456789ABCDEF".toCharArray();
        StringBuffer sb = new StringBuffer("");
        byte[] bs = bin.getBytes();
        for (int i = 0; i < bs.length; i++)
        {
            int bit = (bs[i] & 0xF0) >> 4;
            sb.append(digital[bit]);
            bit = bs[i] & 0xF;
            sb.append(digital[bit]);
        }
        return sb.toString();
    }

    public static String hexToString(String hex)
    {
        String digital = "0123456789ABCDEF";
        char[] hex2char = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++)
        {
            int temp = digital.indexOf(hex2char[(2 * i)]) * 16;
            temp += digital.indexOf(hex2char[(2 * i + 1)]);
            bytes[i] = ((byte)(temp & 0xFF));
        }
        return new String(bytes);
    }

    public static String byteToHex(byte[] b)
    {
        String hs = "";
        String tmp;
        for (int n = 0; n < b.length; n++)
        {
            tmp = Integer.toHexString(b[n] & 0xFF);
            if (tmp.length() == 1) {
                hs = hs + "0" + tmp;
            } else {
                hs = hs + tmp;
            }
        }
        return hs.toUpperCase();
    }

    public static String byteToHex2(byte[] b)
    {
        String hs = "";
        String tmp;
        for (int n = 0; n < b.length; n++)
        {
            tmp = Integer.toHexString(b[n] & 0xFF);
            if (tmp.length() == 1) {
                hs = hs + "0x0" + tmp;
            } else {
                hs = hs + "0x" + tmp;
            }
        }
        return hs;
    }

    public static byte[] hexToByte(byte[] b)
    {
        if (b.length % 2 != 0) {
            throw new IllegalArgumentException("the length of input bytes is not even number");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2)
        {
            String item = new String(b, n, 2);

            b2[(n / 2)] = ((byte)Integer.parseInt(item, 16));
        }
        b = (byte[])null;
        return b2;
    }

}
