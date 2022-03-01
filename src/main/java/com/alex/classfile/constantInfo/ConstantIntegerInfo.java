package com.alex.classfile.constantInfo;

import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantInfo;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-27 16:19
 **/
public class ConstantIntegerInfo extends ConstantInfo {
    private int val;

    public ConstantIntegerInfo(byte tag) {
        this.type = tag;
    }

    @Override
    public void readInfo(ClassReader classReader) {
        this.val = classReader.readUint32();
    }

    public int getVal() {
        return val;
    }
}
