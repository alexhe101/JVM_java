package com.alex.instructions.references;

import com.alex.instructions.base.Index16Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.*;
import com.alex.rtda.heap.Object;

public class GET_FIELD extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getConstantPool();
        FieldRef fieldRef = (FieldRef) runtimeConstantPool.getConstant(getIndex()).getValue();
        Field field = fieldRef.resolvedField();
        if(field.isStatic())
        {
            throw new IncompatibleClassChangeError();
        }
        OperandStack stack = frame.getOperandStack();
        Object ref = stack.popRef();
        if(ref==null)
        {
            throw new NullPointerException("call "+field.getName()+" on a null object");
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        Slots slots = ref.getFields();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                stack.pushInt(slots.getInt(slotId));
                break;
            case 'F':
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case 'J':
                stack.pushLong(slots.getLong(slotId));
                break;
            case 'D':
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case 'L':
            case '[':
                stack.pushRef(slots.getRef(slotId));
                break;
            default:
                break;
        }
    }


}
