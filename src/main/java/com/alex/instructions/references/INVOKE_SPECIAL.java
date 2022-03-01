package com.alex.instructions.references;

import com.alex.instructions.base.Index16Instruction;
import com.alex.rtda.Frame;

public class INVOKE_SPECIAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().popRef();
    }
}
