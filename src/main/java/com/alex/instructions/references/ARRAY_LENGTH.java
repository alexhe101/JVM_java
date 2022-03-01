package com.alex.instructions.references;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.Object;

public class ARRAY_LENGTH extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Object arrRef = stack.popRef();
        if(arrRef==null)
        {
            throw new NullPointerException();
        }
        int arrlen = arrRef.getArrayLen();
        stack.pushInt(arrlen);
    }
}
