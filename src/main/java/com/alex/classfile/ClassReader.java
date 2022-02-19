package com.alex.classfile;

import com.alex.utils.NumberUtils;

import java.util.Arrays;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-26 17:31
 **/
public class ClassReader {
    private byte[] data;
    private int index = 0;

    public ClassReader(byte[] data) {
        this.data = data;
    }

    public byte readUint8()
    {
        byte val = this.data[index++];
        return val;
    }

    public char readUint16()
    {
        byte[] tmp = new byte[2];
        tmp[0] = this.data[index++];
        tmp[1] = this.data[index++];
        char val= NumberUtils.bytesToChar(tmp);
        return val;
    }

    public int readUint32()
    {
        byte[] tmp = new byte[4];
        for(int i=0;i<4;i++)
        {
            tmp[i] = data[index++];
        }
        int val = NumberUtils.bytesToInt(tmp);
        return val&0x0FFFFFFFF;
    }

    public long readUint64()
    {
        byte[] tmp = new byte[8];
        for(int i=0;i<8;i++)
        {
            tmp[i] = data[index++];
        }
        long val = NumberUtils.bytesToLong(tmp);
        return val;
    }

    public char[] readUint16s()
    {
        int n = readUint16();//interface_count
        char s[] = new char[n];
        for(int i=0;i<n;i++)
        {
            s[i] = readUint16();
        }
        return s;
    }

    public byte[] readBytes(int n)
    {
        byte[] tmp = new byte[n];
        for(int i=0;i<n;i++)
        {
            tmp[i] = data[index++];
        }
        return tmp;
    }

}
