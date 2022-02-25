package com.alex.instructions.stack.dup;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.Slot;

public class DUP extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack= frame.getOperandStack();
        Slot slot = operandStack.popSlot();
        operandStack.pushSlot(slot);
        operandStack.pushSlot(slot);
    }
}
