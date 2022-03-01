package com.alex.instructions.control.ret;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;
import com.alex.rtda.Thread;

public class LRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Thread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        long retVal = currentFrame.getOperandStack().popLong();
        invokerFrame.getOperandStack().pushLong(retVal);
    }
}
