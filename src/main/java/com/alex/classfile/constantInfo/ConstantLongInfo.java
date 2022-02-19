package com.alex.classfile.constantInfo;

import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantInfo;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-27 16:30
 **/
public class ConstantLongInfo extends ConstantInfo {
    private long val;

    @Override
    public void readInfo(ClassReader classReader) {
        val = classReader.readUint64();
    }

    public long getVal() {
        return val;
    }
}
