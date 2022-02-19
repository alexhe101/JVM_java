package com.alex.classfile.constantInfo;

import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantInfo;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-27 16:37
 **/
public class ConstantMethodHandleInfo extends ConstantInfo {
    private byte referenceKind;
    private int referenceIndex;

    @Override
    public void readInfo(ClassReader classReader) {
        referenceKind = classReader.readUint8();
        referenceIndex = classReader.readUint16();
    }

    public int getReferenceKind() {
        return (referenceKind + 256) % 256;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }
}
