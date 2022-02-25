package com.alex.instructions.comparisons.ificmp;

import com.alex.instructions.base.BranchInstruction;
import com.alex.instructions.base.BranchLogic;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;

public class IF_ICMPNE extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int val2 = operandStack.popInt();
        int val1 = operandStack.popInt();
        if(val1!=val2)
        {
            BranchLogic.branch(frame,offset);
        }
    }
}
