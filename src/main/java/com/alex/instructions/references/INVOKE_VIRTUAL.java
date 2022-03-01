package com.alex.instructions.references;

import com.alex.instructions.base.Index16Instruction;
import com.alex.instructions.base.MethodInovkeLogic;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.*;
import com.alex.rtda.heap.Object;

import javax.management.RuntimeErrorException;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Clazz currentClass = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClass.getConstantPool();
        //通过index,拿到方法符号引用,虚方法(用到了多态),这个方法引用指向的其实是父类的
        MethodRef methodRef = (MethodRef) runtimeConstantPool.getConstant(getIndex()).getValue();
        //将方法引用转换为方法
        //这一步拿到解析后的resolvedMethod主要是用来做下面权限的验证;
        //而真正的resolvedMethod是在下面拿到真正的调用者,再次解析到的methodToBeInvoked
        Method resolvedMethod = methodRef.resolvedMethod();

        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError(resolvedMethod.getName() + " in unstatic context");
        }

        //从操作数栈中获取调用该非静态方法的引用;参数的传递是从当前frame的操作数栈中根据参数个数,完整的拷贝到调用frame的本地变量表中;
        Object ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if (ref == null) {
            if(methodRef.getName().equals("println"))
            {

                println(frame.getOperandStack(), methodRef.getDescriptor());
                return;
            }else {
                throw new NullPointerException("called " + resolvedMethod.getName() + " on a null reference!");
            }
        }

        //验证protected的方法的调用权限
        if (resolvedMethod.isProtected() &&
                resolvedMethod.getClazz().isSuperClassOf(currentClass) &&
                !resolvedMethod.getClazz().getPackageName().equals(currentClass.getPackageName()) &&
                ref.getClazz() != currentClass &&
                !ref.getClazz().isSubClassOf(currentClass)) {

        }

        Method methodToBeInvoked = MethodLookup.lookupMethodInClass(ref.getClazz(),
                methodRef.getName(), methodRef.getDescriptor());

        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError(methodToBeInvoked.getName());
        }

        MethodInovkeLogic.InvokeMethod(frame,methodToBeInvoked);

    }

    private void println(OperandStack stack, String descriptor) {
        switch (descriptor)
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
                throw  new Error("println "+descriptor);

        }
        stack.popRef();
    }
}
