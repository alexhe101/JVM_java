package com.alex.instructions.base;

import com.alex.rtda.Frame;

public interface Instruction {
    public void fetchOperands(BytecodeReader bytecodeReader);
    public void execute(Frame frame);
}
