package com.alex.instructions.comparisons.fcmp;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;

public class FCMPL extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        FCMP.fcmp(frame,false);
    }
}
