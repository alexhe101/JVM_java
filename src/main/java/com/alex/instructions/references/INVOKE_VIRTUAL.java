package com.alex.instructions.references;

import com.alex.instructions.base.Index16Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.MethodRef;
import com.alex.rtda.heap.RuntimeConstantPool;

import javax.management.RuntimeErrorException;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool constantPool  = frame.getMethod().getClazz().getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(getIndex()).getValue();
        if(methodRef.getName().equals("println"))
        {
            OperandStack stack = frame.getOperandStack();
            switch (methodRef.getDescriptor())
            {
                case "(Z)V":
                    System.out.println(stack.popInt()!=0);
                    break;
                case "(C)V":
                    System.out.println(stack.popInt());
                    break;
                case ("(B)V"):
                    System.out.println(stack.popInt());
                    break;
                case "(S)V":
                    System.out.println(stack.popInt());
                    break;
                case "(I)V":
                    System.out.println(stack.popInt());
                    break;
                case "(J)V":
                    System.out.println(stack.popLong());
                    break;
                case "(F)V":
                    System.out.println(stack.popFloat());
                    break;
                case "(D)V":
                    System.out.println(stack.popDouble());
                    break;
                default:
                    throw  new Error("println "+methodRef.getDescriptor());

            }
            stack.popRef();
        }
    }
}
