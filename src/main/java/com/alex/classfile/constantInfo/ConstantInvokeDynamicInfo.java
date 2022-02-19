package com.alex.classfile.constantInfo;

import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantInfo;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-27 16:37
 **/
public class ConstantInvokeDynamicInfo extends ConstantInfo {
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    @Override
    public void readInfo(ClassReader classReader) {
        bootstrapMethodAttrIndex = classReader.readUint16();
        nameAndTypeIndex = classReader.readUint16();
    }
}
