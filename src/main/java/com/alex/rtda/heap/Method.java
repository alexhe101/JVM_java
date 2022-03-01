package com.alex.rtda.heap;

import com.alex.classfile.MemberInfo;
import com.alex.classfile.attribute.CodeAttribute;

public class Method extends ClassMember{
    private int maxStack;
    private int maxLocals;
    private byte[] code;

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
        }
        return methods;
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
