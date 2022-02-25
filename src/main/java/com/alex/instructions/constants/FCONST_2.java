package com.alex.instructions.constants;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;

public class FCONST_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(2.0f);
    }
}
