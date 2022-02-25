package com.alex.instructions.control;

import com.alex.instructions.base.BranchInstruction;
import com.alex.instructions.base.BranchLogic;
import com.alex.rtda.Frame;

public class GOTO extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        BranchLogic.branch(frame,offset);
    }
}
