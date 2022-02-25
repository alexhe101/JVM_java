package com.alex.instructions.base;

import com.alex.rtda.Frame;

public abstract class BranchInstruction implements Instruction{
    protected int offset;

    @Override
    public void fetchOperands(BytecodeReader bytecodeReader) {
        offset = (int)bytecodeReader.readInt16();
    }

}
