package com.alex.classfile.attribute;

import com.alex.classfile.AttributeInfo;
import com.alex.classfile.ClassReader;

public class ConstantValueAttribute extends AttributeInfo {
    private int constantValueIndex;

    @Override
    public void readInfo(ClassReader classReader) {
        constantValueIndex = classReader.readUint16();
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }
}
