package com.alex.classfile.constantInfo;

import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantInfo;
import com.alex.classfile.ConstantPool;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-27 16:36
 **/
public class ConstantClassInfo extends ConstantInfo {
    private ConstantPool constantPool;
    private int nameIndex;
    public ConstantClassInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader classReader) {
        nameIndex= classReader.readUint16();
    }

    @Override
    public String toString() {
        return constantPool.getUtf8(nameIndex);
    }

    public String getName() {
        return constantPool.getUtf8(nameIndex);
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
