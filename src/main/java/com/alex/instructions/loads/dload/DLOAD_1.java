package com.alex.instructions.loads.dload;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.loads.Load;
import com.alex.rtda.Frame;

public class DLOAD_1 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Load.dload(frame,1);
    }
}
