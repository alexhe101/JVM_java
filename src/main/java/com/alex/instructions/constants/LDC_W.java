package com.alex.instructions.constants;

import com.alex.instructions.base.Index16Instruction;
import com.alex.rtda.Frame;

public class LDC_W extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        LDC._ldc(frame,getIndex());
    }
}
