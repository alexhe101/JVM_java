package com.alex.instructions.conversions.i2x;


import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
public class I2S extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int val1 = stack.popInt();
        short val2 = (short) val1;
        stack.pushInt(val2);
    }
}
