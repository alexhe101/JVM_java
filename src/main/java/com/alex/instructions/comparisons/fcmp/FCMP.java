package com.alex.instructions.comparisons.fcmp;

import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;

public class FCMP {
    public static void fcmp(Frame frame,boolean gFlag)
    {
        OperandStack operandStack = frame.getOperandStack();
        float v2 = operandStack.popFloat();
        float v1  = operandStack.popFloat();
        if(v1>v2){
            operandStack.pushInt(1);
        }else if(v1==v2){
            operandStack.pushInt(0);
        }else if(v1<v2){
            operandStack.pushInt(-1);
        }else if(gFlag){
            operandStack.pushInt(1);
        }else {
            operandStack.pushInt(-1);
        }
    }
}
