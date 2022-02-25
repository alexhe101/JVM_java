package com.alex.instructions.math.rem;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;

public class IREM extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        if(v2==0)
        {
            throw new ArithmeticException("divided by zero");
        }
        operandStack.pushInt(v1%v2);
    }
}
