package com.alex.instructions.loads.iload;

import com.alex.instructions.base.Index8Instruction;
import com.alex.instructions.loads.Load;
import com.alex.rtda.Frame;

public class ILOAD extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        Load.iload(frame,index);
    }
}
