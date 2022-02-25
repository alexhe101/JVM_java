package com.alex.instructions.comparisons.dcmp;


import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;

public class DCMP {
    static void _dcmp(Frame frame, boolean flag) {
        OperandStack stack = frame.getOperandStack();
        double val2 = stack.popDouble();
        double val1 = stack.popDouble();

        if (val1 > val2) {
            stack.pushInt(1);
        } else if (val1 == val2) {
            stack.pushInt(0);
        } else if (val1 > val2) {
            stack.pushInt(-1);
        } else if (flag) {
            stack.pushInt(1);
        } else {
            stack.pushInt(-1);
        }

    }
}
