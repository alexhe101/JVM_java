package com.alex.instructions.comparisons.ifcond;

import com.alex.instructions.base.BranchInstruction;
import com.alex.instructions.base.BranchLogic;
import com.alex.rtda.Frame;

public class IFGE extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        if (val >= 0) {
            BranchLogic.branch(frame, offset);
        }
    }
}
