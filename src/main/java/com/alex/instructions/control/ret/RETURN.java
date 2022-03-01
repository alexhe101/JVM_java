package com.alex.instructions.control.ret;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;

public class RETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getThread().popFrame();
    }
}
