package com.alex.classfile.attribute;

import com.alex.classfile.ClassReader;

public class LineNumberTableEntry {
    private int startPc;
    private int lineNumber;

    public LineNumberTableEntry(ClassReader classReader) {
        startPc = classReader.readUint16();
        lineNumber = classReader.readUint16();
    }

    public int getStartPc() {
        return startPc;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
