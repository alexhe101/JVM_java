package com.alex.instructions.references;

import com.alex.instructions.base.Index16Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.ClassRef;
import com.alex.rtda.heap.Clazz;
import com.alex.rtda.heap.Object;
import com.alex.rtda.heap.RuntimeConstantPool;

public class ANEW_ARRAY extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) runtimeConstantPool.getConstant(index).getValue();
        Clazz componentClass = classRef.resolvedClass();
        OperandStack stack = frame.getOperandStack();
        int count= stack.popInt();
        if(count<0)
        {
            throw new NegativeArraySizeException();
        }
        Clazz arrClass = componentClass.arrClass();
        Object arr = arrClass.newArray(count);
        stack.pushRef(arr);
    }
}
