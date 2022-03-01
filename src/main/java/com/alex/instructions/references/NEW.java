package com.alex.instructions.references;

import com.alex.instructions.base.Index16Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.heap.ClassRef;
import com.alex.rtda.heap.Clazz;
import com.alex.rtda.heap.Object;
import com.alex.rtda.heap.RuntimeConstantPool;

public class NEW extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef clazzRef = (ClassRef) runtimeConstantPool.getConstant(getIndex()).getValue();
        Clazz clazz = clazzRef.resolvedClass();
        if(clazz.isInterface()||clazz.isAbstract())
        {
            throw  new InstantiationError();
        }
        Object ref = clazz.newObject();
        frame.getOperandStack().pushRef(ref);
    }
}
