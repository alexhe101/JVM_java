package com.alex.instructions.comparisons.lcmp;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;

public class LCMP extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long v2 = operandStack.popLong();
        long v1 = operandStack.popLong();
        if(v1>v2){
            operandStack.pushInt(1);
        }else if(v1==v2){
            operandStack.pushInt(0);
        }else {
            operandStack.pushInt(-1);
        }
    }
}
