package com.alex.instructions.extended.ifnull;

import com.alex.instructions.base.BranchInstruction;
import com.alex.instructions.base.BranchLogic;
import com.alex.rtda.Frame;
import com.alex.rtda.Object;

public class IFNULL extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        Object ref = frame.getOperandStack().popRef();
        if(ref==null)
        {
            BranchLogic.branch(frame,offset);
        }
    }
}
