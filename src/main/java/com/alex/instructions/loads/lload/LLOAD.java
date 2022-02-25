package com.alex.instructions.loads.lload;


import com.alex.instructions.base.Index8Instruction;
import com.alex.instructions.loads.Load;
import com.alex.rtda.Frame;

public class LLOAD extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        Load.lload(frame,index);
    }
}