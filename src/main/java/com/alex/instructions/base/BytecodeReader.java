package com.alex.instructions.base;

import com.alex.utils.NumberUtils;

/**
 * @author alex101
 */
public class BytecodeReader {
    private byte[] code;
    private int pc;

    public int getPc() {
        return pc;
    }

    public void reset(byte[] code, int pc)
    {
        this.code = code;
        this.pc = pc;
    }
    public short readInt16()
    {
        return (short)readUint16();
    }

    public int readUint8() {
        //0-255
        int res = code[pc++];
        res = (res + 256) % 256;
        return res;
    }

    public byte readInt8() {
        return code[pc++];
    }

    public int readUint16() {
        int a1 = readUint8();
        int a2 = readUint8();
        return (a1 << 8 | a2);
    }

    public int readInt32()
    {
        byte[] data = new byte[4];
        data[0] = readInt8();
        data[1] = readInt8();
        data[2] = readInt8();
        data[3] = readInt8();
        return NumberUtils.bytesToInt(data);
    }

    public int[] readInt32s(int n)
    {
        int []data = new int[n];
        for(int i=0;i<n;i++)
        {
            data[i] = readInt32();
        }
        return data;
    }

    public void skipPadding()
    {
        while (pc%4!=0)
        {
            readInt8();
        }
    }
}
