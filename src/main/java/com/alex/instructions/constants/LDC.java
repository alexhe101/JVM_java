package com.alex.instructions.constants;

import com.alex.classfile.ConstantInfo;
import com.alex.instructions.base.Index8Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.RuntimeConstantInfo;
import com.alex.rtda.heap.RuntimeConstantPool;
public class LDC extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        _ldc(frame,getIndex());
    }

    public static void _ldc(Frame frame, int index) {
        OperandStack operandStack = frame.getOperandStack();
        RuntimeConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        RuntimeConstantInfo runtimeConstantInfo = constantPool.getConstant(index);
        switch (runtimeConstantInfo.getType())
        {
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
