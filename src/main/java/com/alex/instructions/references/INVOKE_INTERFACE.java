package com.alex.instructions.references;

import com.alex.instructions.base.BytecodeReader;
import com.alex.instructions.base.Index16Instruction;
import com.alex.instructions.base.Instruction;
import com.alex.instructions.base.MethodInovkeLogic;
import com.alex.rtda.Frame;
import com.alex.rtda.heap.*;
import com.alex.rtda.heap.Object;

public class INVOKE_INTERFACE extends Index16Instruction {
    private int count;
    private int zero;

    @Override
    public void fetchOperands(BytecodeReader bytecodeReader) {
        index = bytecodeReader.readUint16();
        bytecodeReader.readUint8();
        bytecodeReader.readUint8();
    }

    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getConstantPool();
        InterfaceMethodRef methodRef = (InterfaceMethodRef) runtimeConstantPool.getConstant(index).getValue();
        Method resolvedMethod = methodRef.resolvedInterfaceMethod();
        if (resolvedMethod.isStatic()||resolvedMethod.isPrivate()) {
            throw new IncompatibleClassChangeError(resolvedMethod.getName() + " in unstatic context");
        }
        Object ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount()-1);
        if (ref == null) {
            throw new NullPointerException("called " + resolvedMethod.getName() + " on a null reference!");
        }
        if(!ref.getClazz().isImplements(methodRef.resolvedClass()))
        {
            throw new IncompatibleClassChangeError(resolvedMethod.getName() + " in unstatic context");
        }
        Method methodToBeInvoked = MethodLookup.lookupMethodInClass(ref.getClazz(),methodRef.getName(),methodRef.getDescriptor());
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError(methodToBeInvoked.getName());
        }
        if(!methodToBeInvoked.isPublic())
        {
            throw new IllegalAccessError();
        }
        MethodInovkeLogic.InvokeMethod(frame,methodToBeInvoked);
    }
}
