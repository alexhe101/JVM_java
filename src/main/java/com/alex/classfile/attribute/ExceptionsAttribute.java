package com.alex.classfile.attribute;

import com.alex.classfile.AttributeInfo;
import com.alex.classfile.ClassReader;

public class ExceptionsAttribute extends AttributeInfo {
    private char[] exceptionIndexTable;

    @Override
    public void readInfo(ClassReader classReader) {
        exceptionIndexTable = classReader.readUint16s();
    }

    public char[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }
}
