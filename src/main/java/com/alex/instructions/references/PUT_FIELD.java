package com.alex.instructions.references;

import com.alex.instructions.base.Index16Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.*;
import com.alex.rtda.heap.Object;

public class PUT_FIELD extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Method currentMethod  = frame.getMethod();
        Clazz currentClass = currentMethod.getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClass.getConstantPool();
        FieldRef fieldRef = (FieldRef) runtimeConstantPool.getConstant(getIndex()).getValue();
        Field field = fieldRef.resolvedField();
        if(field.isStatic())
        {
            throw new IncompatibleClassChangeError();
        }
        if(field.isFinal())
        {
            if(currentClass!=field.getClazz()||!currentMethod.getName().equals("<init>"))
            {
                throw new IllegalAccessError();
            }
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        OperandStack stack = frame.getOperandStack();
        Object instance;
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I': {
                int val = stack.popInt();
                instance = stack.popRef();
                if (instance == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                instance.getFields().setInt(slotId, val);
                break;
            }
            case 'F': {
                float val = stack.popFloat();
                instance = stack.popRef();
                if (instance == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                instance.getFields().setFloat(slotId, val);
                break;
            }
            case 'J': {
                long val = stack.popLong();
                instance = stack.popRef();
                if (instance == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                instance.getFields().setLong(slotId, val);
                break;
            }
            case 'D': {
                double val = stack.popDouble();
                instance = stack.popRef();
                if (instance == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                instance.getFields().setDouble(slotId, val);
                break;
            }
            case 'L':
            case '[': {
                Object val = stack.popRef();
                instance = stack.popRef();
                if (instance == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                instance.getFields().setRef(slotId, val);
                break;
            }
            default:
                break;
        }
    }
}
