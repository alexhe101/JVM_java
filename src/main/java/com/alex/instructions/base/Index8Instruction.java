package com.alex.instructions.base;

import com.alex.rtda.Frame;

public abstract class Index8Instruction implements Instruction{
    protected int index;

    @Override
    public void fetchOperands(BytecodeReader bytecodeReader) {
        index = bytecodeReader.readUint8();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
