package com.alex.instructions.constants;

import com.alex.instructions.base.BytecodeReader;
import com.alex.instructions.base.Instruction;
import com.alex.rtda.Frame;

public class SIPUSH implements Instruction {
    private short val;

    @Override
    public void fetchOperands(BytecodeReader bytecodeReader) {
        val = bytecodeReader.readInt16();
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt((val + 65536) % 65536);
    }
}
