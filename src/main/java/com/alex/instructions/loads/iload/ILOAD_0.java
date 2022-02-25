package com.alex.instructions.loads.iload;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.loads.Load;
import com.alex.rtda.Frame;

public class ILOAD_0 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Load.iload(frame,0);
    }
}
