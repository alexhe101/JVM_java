package com.alex.instructions.control;

import com.alex.instructions.base.BranchLogic;
import com.alex.instructions.base.BytecodeReader;
import com.alex.instructions.base.Instruction;
import com.alex.rtda.Frame;

public class TABLE_SWITCH implements Instruction {
    private int defaultOffset;
    private int low;
    private int high;
    private int[] jumpOffsets;

    @Override
    public void fetchOperands(BytecodeReader bytecodeReader) {
        bytecodeReader.skipPadding();
        defaultOffset = bytecodeReader.readInt32();
        low = bytecodeReader.readInt32();
        high = bytecodeReader.readInt32();
        int jumpOffsetsCount = high-low+1;
        jumpOffsets = bytecodeReader.readInt32s(jumpOffsetsCount);
    }

    @Override
    public void execute(Frame frame) {
        int index = frame.getOperandStack().popInt();
        int offset;
        if(index>=low&&index<=high)
        {
            offset =jumpOffsets[index-low];
        }else {
            offset = defaultOffset;
        }
        BranchLogic.branch(frame,offset);
    }
}
