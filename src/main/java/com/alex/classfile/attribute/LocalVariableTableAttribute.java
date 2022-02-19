package com.alex.classfile.attribute;

import com.alex.classfile.AttributeInfo;
import com.alex.classfile.ClassReader;

public class LocalVariableTableAttribute extends AttributeInfo {
    private LocalVariableTableEntry[] localVariableTable;

    @Override
    public void readInfo(ClassReader classReader) {
        int len  =classReader.readUint16();
        localVariableTable = new LocalVariableTableEntry[len];
        for (int i = 0; i < len; i++) {
            localVariableTable[i] = new LocalVariableTableEntry(classReader);
        }
    }
}
