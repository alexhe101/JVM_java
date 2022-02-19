package com.alex.classfile.attribute;

import com.alex.classfile.ClassReader;

public class ExceptionTableEntry {
    private int startPc;
    private int endPc;
    private int handlerPc;
    private int catchType;

    public ExceptionTableEntry(ClassReader classReader) {
        startPc = classReader.readUint16();
        endPc = classReader.readUint16();
        handlerPc = classReader.readUint16();
        catchType = classReader.readUint16();
    }

    public int getStartPc() {
        return startPc;
    }

    public int getEndPc() {
        return endPc;
    }

    public int getHandlerPc() {
        return handlerPc;
    }

    public int getCatchType() {
        return catchType;
    }
}
