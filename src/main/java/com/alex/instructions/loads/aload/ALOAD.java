package com.alex.instructions.loads.aload;

import com.alex.instructions.base.Index8Instruction;
import com.alex.instructions.loads.Load;
import com.alex.rtda.Frame;

public class ALOAD extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        Load.aload(frame,index);
    }
}