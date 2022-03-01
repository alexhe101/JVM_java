package com.alex.classfile.constantInfo;

import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantInfo;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-27 16:37
 **/
public class ConstantMethodTypeInfo extends ConstantInfo {
    private int descriptorIndex;

    public ConstantMethodTypeInfo(byte tag) {
        this.type = tag;
    }

    @Override
    public void readInfo(ClassReader classReader) {
        descriptorIndex = classReader.readUint16();
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
