package com.alex.instructions.constants;

import com.alex.classfile.ConstantInfo;
import com.alex.instructions.base.Index8Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.*;
import com.alex.rtda.heap.Object;

public class LDC extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        _ldc(frame,getIndex());
    }

    public static void _ldc(Frame frame, int index) {
        OperandStack operandStack = frame.getOperandStack();
        Clazz clazz = frame.getMethod().getClazz();

        RuntimeConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        RuntimeConstantInfo runtimeConstantInfo = constantPool.getConstant(index);
        switch (runtimeConstantInfo.getType())
        {
            case ConstantInfo.CONSTANT_String:
                Object internedStr = StringPool.jString(clazz.getLoader(),(String)runtimeConstantInfo.getValue());
                operandStack.pushRef(internedStr);
                break;
            case ConstantInfo.CONSTANT_Integer:
                operandStack.pushInt((int)runtimeConstantInfo.getValue());
                break;
            case ConstantInfo.CONSTANT_Float:
                operandStack.pushFloat((float) runtimeConstantInfo.getValue());
                break;
            default:
                break;
        }
    }
}
