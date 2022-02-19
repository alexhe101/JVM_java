package com.alex.classfile.constantInfo;

import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantInfo;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-27 16:29
 **/
public class ConstantFloatInfo extends ConstantInfo {
    private float val;

    @Override
    public void readInfo(ClassReader classReader) {
        int t = classReader.readUint32();
        val = Float.intBitsToFloat(t);
    }

    public float getVal() {
        return val;
    }
}
