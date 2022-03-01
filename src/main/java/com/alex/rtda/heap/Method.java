package com.alex.rtda.heap;

import com.alex.classfile.MemberInfo;
import com.alex.classfile.attribute.CodeAttribute;

public class Method extends ClassMember{
    private int maxStack;
    private int maxLocals;
    private byte[] code;
    private int argSlotCount;

    public int getArgSlotCount() {
        return argSlotCount;
    }

    public void setArgSlotCount(int argSlotCount) {
        this.argSlotCount = argSlotCount;
    }

    public byte[] getCode() {
        return code;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public static Method[] newMethods(Clazz clazz, MemberInfo[] cfMethods)
    {
        Method[] methods = new Method[cfMethods.length];
        for(int i=0;i< cfMethods.length;i++)
        {
            methods[i] = new Method();
            methods[i].setClazz(clazz);
            methods[i].copyMemberInfo(cfMethods[i]);
            methods[i].copyAttributes(cfMethods[i]);
            methods[i].calcArgSlotCount();
        }
        return methods;
    }

    private void calcArgSlotCount() {
        MethodDescriptor methodDescriptor = new MethodDescriptor(getDescriptor());
        for (String paramType:
             methodDescriptor.getParameterTypes()) {
            argSlotCount++;
            if(paramType.equals("J")||paramType.equals("D"))
            {
                argSlotCount++;
            }
        }
        if(!isStatic())
        {
            argSlotCount++;
        }
    }

    public void copyAttributes(MemberInfo memberInfo)
    {
        CodeAttribute codeAttribute = memberInfo.getCodeAttribute();
        if(codeAttribute!=null)
        {
            maxLocals = codeAttribute.getMaxLocals();
            maxStack = codeAttribute.getMaxStack();
            code = codeAttribute.getCode();
        }
    }
}
