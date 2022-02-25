package com.alex.instructions.comparisons.ificmp;

import com.alex.instructions.base.BranchInstruction;
import com.alex.instructions.base.BranchLogic;
import com.alex.rtda.Frame;

public class IF_ICMPEQ extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        int[] res = IfIcmp._icmpPop(frame);
        int val1 = res[0];
        int val2 = res[1];
        if (val1 == val2) {
            BranchLogic.branch(frame, offset);
        }
    }
}
