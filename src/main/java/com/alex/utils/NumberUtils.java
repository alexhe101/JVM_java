package com.alex.utils;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-26 17:41
 **/
public class NumberUtils {
    public static char bytesToChar(byte[] b)
    {
        char c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
        return c;
    }

    public static int bytesToInt(byte[] b)
    {
        int c = (int)(((b[0]&0xff)<<24)|((b[1]&0xff)<<16)|((b[2]&0xff)<<8)|(b[3]&0xff));
        return c;
    }

    public static long bytesToLong(byte[] b)
    {
        long c = (long)(
                (b[0]&0xff)<<56|
                (b[1]&0xff)<<48|
                (b[2]&0xff)<<40|
                (b[3]&0xff)<<32|
                (b[4]&0xff)<<24|
                (b[5]&0xff)<<16|
                (b[6]&0xff)<<8|
                (b[7]&0xff)
                );
        return c;
    }
}
