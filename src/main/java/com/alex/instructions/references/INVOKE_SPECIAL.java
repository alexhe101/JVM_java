package com.alex.instructions.references;

import com.alex.instructions.base.Index16Instruction;
import com.alex.instructions.base.MethodInovkeLogic;
import com.alex.rtda.Frame;
import com.alex.rtda.heap.*;
import com.alex.rtda.heap.Object;

public class INVOKE_SPECIAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Clazz currentClazz  = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClazz.getConstantPool();
        MethodRef methodRef = (MethodRef) runtimeConstantPool.getConstant(getIndex()).getValue();
        Clazz resolvedClass = methodRef.resolvedClass();
        Method resolvedMethod = methodRef.resolvedMethod();
        if(resolvedMethod.getName().equals("<init>")&&resolvedMethod.getClazz()!=resolvedClass)
        {
            throw new NoSuchMethodError();
        }
        if(resolvedMethod.isStatic())
        {
            throw new IncompatibleClassChangeError();
        }
        Object ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount()-1);
        if(ref==null)
        {
            throw new NullPointerException();
        }
        if(resolvedMethod.isProtected()&& resolvedMethod.getClazz().isSuperClassOf(currentClazz)
        &&!resolvedMethod.getClazz().getPackageName().equals(currentClazz.getPackageName())
        &&ref.getClazz()!=currentClazz&&!ref.getClazz().isSubClassOf(currentClazz))
        {
            throw new IllegalAccessError();
        }

        Method methodToBeInvoked = resolvedMethod;
        if(currentClazz.isSuper()&&resolvedClass.isSubClassOf(currentClazz)&&!resolvedMethod.getName().equals("<init>"))
        {
            methodToBeInvoked = MethodLookup.lookupMethodInClass(currentClazz.getSuperClass(),methodRef.getName(),methodRef.getDescriptor());
        }
        if(methodToBeInvoked==null||methodToBeInvoked.isAbstract())
        {
            throw new AbstractMethodError();
        }
        MethodInovkeLogic.InvokeMethod(frame,methodToBeInvoked);
    }
}
