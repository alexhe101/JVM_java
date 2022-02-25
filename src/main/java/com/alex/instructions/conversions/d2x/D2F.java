package com.alex.instructions.conversions.d2x;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;

public class D2F extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double val = operandStack.popDouble();
        float i = (float)val;
        operandStack.pushFloat(i);
    }
}
