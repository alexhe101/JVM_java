package com.alex.instructions.references;

import com.alex.instructions.base.Index16Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.ClassRef;
import com.alex.rtda.heap.Clazz;
import com.alex.rtda.heap.Object;
import com.alex.rtda.heap.RuntimeConstantPool;

public class CHECK_CAST extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Object ref = stack.popRef();
        stack.pushRef(ref);
        if(ref==null)
        {
            return;
        }
        RuntimeConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(getIndex()).getValue();
        Clazz clazz = classRef.resolvedClass();
        if(!ref.isInstanceof(clazz))
        {
            throw new ClassCastException();
        }

    }
}
