package com.alex.classfile.constantInfo;

import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantInfo;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-27 16:35
 **/
public class ConstantDoubleInfo extends ConstantInfo {
    private double val;

    public ConstantDoubleInfo(byte tag) {
        this.type = tag;
    }

    @Override
    public void readInfo(ClassReader classReader) {
        val = Double.longBitsToDouble(classReader.readUint64());
    }

    public double getVal() {
        return val;
    }


}
