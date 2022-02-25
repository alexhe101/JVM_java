package com.alex.instructions.loads.dload;

import com.alex.instructions.base.Index8Instruction;
import com.alex.instructions.loads.Load;
import com.alex.rtda.Frame;

public class DLOAD extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        Load.dload(frame,index);
    }
}