package com.alex.instructions.control.ret;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;
import com.alex.rtda.Thread;
import com.alex.rtda.heap.Object;

public class ARETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Thread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        Object retVal = currentFrame.getOperandStack().popRef();
        invokerFrame.getOperandStack().pushRef(retVal);
    }
}
