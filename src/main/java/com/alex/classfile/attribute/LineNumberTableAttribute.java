package com.alex.classfile.attribute;

import com.alex.classfile.AttributeInfo;
import com.alex.classfile.ClassReader;

public class LineNumberTableAttribute extends AttributeInfo {
    private LineNumberTableEntry[] lineNumberTable;

    @Override
    public void readInfo(ClassReader classReader) {
        int len = classReader.readUint16();
        lineNumberTable = new LineNumberTableEntry[len];
        for (int i = 0; i < len; i++) {
            lineNumberTable[i] = new LineNumberTableEntry(classReader);
        }
    }
}
