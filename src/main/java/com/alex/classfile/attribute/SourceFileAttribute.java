package com.alex.classfile.attribute;

import com.alex.classfile.AttributeInfo;
import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantPool;

public class SourceFileAttribute extends AttributeInfo {
    private ConstantPool constantPool;
    private int sourceFileIndex;

    public SourceFileAttribute(ConstantPool constantPool) {
        super();
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader classReader) {
        sourceFileIndex = classReader.readUint16();
    }

    public String getFileName()
    {
        return constantPool.getUtf8(sourceFileIndex);
    }
}
