package com.alex.instructions.constants;

import com.alex.classfile.ConstantInfo;
import com.alex.instructions.base.Index16Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.RuntimeConstantInfo;
import com.alex.rtda.heap.RuntimeConstantPool;

public class LDC2_W extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getConstantPool();
        RuntimeConstantInfo runtimeConstantInfo = runtimeConstantPool.getConstant(getIndex());
        switch (runtimeConstantInfo.getType())
        {
            case ConstantInfo.CONSTANT_Long:
                stack.pushLong((long)runtimeConstantInfo.getValue());
                break;
            case ConstantInfo.CONSTANT_Double:
                stack.pushDouble((double) runtimeConstantInfo.getValue());
                break;
            default:
                throw new ClassFormatError();
        }
    }
}
