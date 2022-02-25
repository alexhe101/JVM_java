package com.alex.instructions.constants;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;

public class ICONST_3 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(3);
    }
}
