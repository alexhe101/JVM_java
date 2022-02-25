package com.alex.instructions.math.sh;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;

/**
 * @author alex101
 */
public class LUSHR extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        long v1 = operandStack.popLong();
        int s = v2&0x3f;
        operandStack.pushLong(v1>>>s);
    }
}
