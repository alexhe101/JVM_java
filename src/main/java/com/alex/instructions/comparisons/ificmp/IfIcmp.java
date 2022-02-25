package com.alex.instructions.comparisons.ificmp;

import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;

public class IfIcmp {
    public static int[] _icmpPop(Frame frame)
    {
        OperandStack stack = frame.getOperandStack();
        int[] res = new int[2];
        res[1] = stack.popInt();
        res[0] = stack.popInt();
        return res;
    }
}
