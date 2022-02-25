package com.alex.instructions.loads.aload;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.loads.Load;
import com.alex.rtda.Frame;

public class ALOAD_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Load.aload(frame,2);
    }
}