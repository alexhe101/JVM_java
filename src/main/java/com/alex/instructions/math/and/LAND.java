package com.alex.instructions.math.and;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;

public class LAND extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long v1 = operandStack.popLong();
        long v2 = operandStack.popLong();
        operandStack.pushLong(v1&v2);
    }
}
