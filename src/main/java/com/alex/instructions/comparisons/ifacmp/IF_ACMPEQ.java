package com.alex.instructions.comparisons.ifacmp;

import com.alex.instructions.base.BranchInstruction;
import com.alex.instructions.base.BranchLogic;
import com.alex.rtda.Frame;
import com.alex.rtda.Object;

public class IF_ACMPEQ extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        Object ref2 = frame.getOperandStack().popRef();
        Object ref1 = frame.getOperandStack().popRef();
        if(ref1==ref2)
        {
            BranchLogic.branch(frame,offset);
        }
    }
}
