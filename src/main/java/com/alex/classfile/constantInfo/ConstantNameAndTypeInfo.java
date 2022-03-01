package com.alex.classfile.constantInfo;

import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantInfo;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-27 16:37
 **/
public class ConstantNameAndTypeInfo extends ConstantInfo {
    private int nameIndex;
    private int descriptorIndex;

    public ConstantNameAndTypeInfo(byte tag) {
        this.type = tag;
    }


    @Override
    public void readInfo(ClassReader classReader) {
        nameIndex = classReader.readUint16();
        descriptorIndex = classReader.readUint16();
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
