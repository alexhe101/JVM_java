package com.alex.instructions.loads.lload;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.loads.Load;
import com.alex.rtda.Frame;

public class LLOAD_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Load.lload(frame,2);
    }
}
