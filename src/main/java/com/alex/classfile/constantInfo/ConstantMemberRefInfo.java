package com.alex.classfile.constantInfo;

import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantInfo;
import com.alex.classfile.ConstantPool;

public class ConstantMemberRefInfo extends ConstantInfo {
    private ConstantPool constantPool;
    private int classIndex;
    private int nameAndTypeIndex;

    public ConstantMemberRefInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader classReader) {
        classIndex = classReader.readUint16();
        nameAndTypeIndex = classReader.readUint16();
    }

    public String getClassName()
    {
        return constantPool.getClassName(classIndex);
    }

    public String[] getNameAndDescriptor()
    {
        return constantPool.getNameAndType(nameAndTypeIndex);
    }
}
