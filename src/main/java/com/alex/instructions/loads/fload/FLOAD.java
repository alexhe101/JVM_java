package com.alex.instructions.loads.fload;

import com.alex.instructions.base.Index8Instruction;
import com.alex.instructions.loads.Load;
import com.alex.rtda.Frame;

public class FLOAD extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        Load.fload(frame,index);
    }
}
