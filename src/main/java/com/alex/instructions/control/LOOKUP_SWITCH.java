package com.alex.instructions.control;

import com.alex.instructions.base.BranchLogic;
import com.alex.instructions.base.BytecodeReader;
import com.alex.instructions.base.Instruction;
import com.alex.rtda.Frame;

public class LOOKUP_SWITCH implements Instruction {
    private int defaultOffset;
    private int nparis;
    private int[] matchOffsets;

    @Override
    public void fetchOperands(BytecodeReader bytecodeReader) {
        bytecodeReader.skipPadding();
        defaultOffset = bytecodeReader.readInt32();
        nparis = bytecodeReader.readInt32();
        matchOffsets = bytecodeReader.readInt32s(nparis*2);

    }

    @Override
    public void execute(Frame frame) {
        int key = frame.getOperandStack().popInt();
        for (int i = 0; i < nparis*2; i+=2) {
            if(matchOffsets[i]==key)
            {
                int offset = matchOffsets[i+1];
                BranchLogic.branch(frame,offset);
                return;
            }
        }
        BranchLogic.branch(frame,defaultOffset);
    }
}
