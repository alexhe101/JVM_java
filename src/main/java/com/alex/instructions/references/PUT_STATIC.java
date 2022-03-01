package com.alex.instructions.references;

import com.alex.instructions.base.Index16Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.*;

import java.lang.annotation.IncompleteAnnotationException;

public class PUT_STATIC extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Method currentMethod  = frame.getMethod();
        Clazz currentClass = currentMethod.getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClass.getConstantPool();
        FieldRef fieldRef = (FieldRef) runtimeConstantPool.getConstant(getIndex()).getValue();
        Field field = fieldRef.resolvedField();
        Clazz clazz = field.getClazz();
        if(!field.isStatic())
        {
            throw new IncompatibleClassChangeError();
        }
        if(field.isFinal())
        {
            if(currentClass!=clazz||!currentMethod.getName().equals("<clinit>"))
            {
                throw new IllegalAccessError();
            }
        }
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        Slots slots = clazz.getStaticVars();
        OperandStack stack = frame.getOperandStack();
        switch (descriptor.charAt(0))
        {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                slots.setInt(slotId,stack.popInt());
                break;
            case 'F':
                slots.setFloat(slotId,stack.popFloat());
                break;
            case 'J':
                slots.setLong(slotId, stack.popLong());
                break;
            case 'D':
                slots.setDouble(slotId, stack.popDouble());
                break;
            case 'L':
            case '[':
                slots.setRef(slotId,stack.popRef());
                break;
        }
    }
}
