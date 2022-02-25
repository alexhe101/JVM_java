package com.alex.instructions.base;

import com.alex.rtda.Frame;

public class Index16Instruction implements Instruction{
    private int index;

    @Override
    public void fetchOperands(BytecodeReader bytecodeReader) {
        index = bytecodeReader.readUint16();
    }

    @Override
    public void execute(Frame frame) {

    }
}
