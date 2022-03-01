package com.alex.instructions.base;

import com.alex.rtda.Frame;

public abstract class Index16Instruction implements Instruction{
    private int index;

    @Override
    public void fetchOperands(BytecodeReader bytecodeReader) {
        index = bytecodeReader.readUint16();
    }

    public int getIndex() {
        return index;
    }
}
