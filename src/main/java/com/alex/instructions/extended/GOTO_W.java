package com.alex.instructions.extended;

import com.alex.instructions.base.BranchLogic;
import com.alex.instructions.base.BytecodeReader;
import com.alex.instructions.base.Instruction;
import com.alex.rtda.Frame;

public class GOTO_W implements Instruction {
    private int offset;

    @Override
    public void fetchOperands(BytecodeReader bytecodeReader) {
        offset = bytecodeReader.readInt32();
    }

    @Override
    public void execute(Frame frame) {
        BranchLogic.branch(frame,offset);
    }
}
