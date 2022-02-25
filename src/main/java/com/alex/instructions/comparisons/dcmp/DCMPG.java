package com.alex.instructions.comparisons.dcmp;


import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
public class DCMPG extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        DCMP._dcmp(frame, true);
    }
}
