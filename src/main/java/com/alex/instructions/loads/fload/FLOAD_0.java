package com.alex.instructions.loads.fload;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.loads.Load;
import com.alex.rtda.Frame;

public class FLOAD_0 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Load.fload(frame,0);
    }
}
