package com.alex.rtda.heap;

import com.alex.classfile.constantInfo.ConstantMethodrefInfo;

public class MethodRef extends MemberRef{
    private Method method;

    public MethodRef(RuntimeConstantPool runtimeConstantPool, ConstantMethodrefInfo constantMethodrefInfo) {
        this.runtimeConstantPool = runtimeConstantPool;
        copyMemberRefInfo(constantMethodrefInfo);
    }

    public Method resolvedMethod()
    {
        if(method==null)
        {
            resolveMethodRef();
        }
        return method;
    }

    private void resolveMethodRef() {
        Clazz d = runtimeConstantPool.getClazz();
        Clazz c = resolvedClass();
        if(c.isInterface())
        {
            throw  new IncompatibleClassChangeError();
        }
        method = lookupMethod(c,name,descriptor);
        if(method==null)
        {
            throw new NoSuchMethodError();
        }
        if(!method.isAccessibleTo(d))
        {
            throw new IllegalAccessError();
        }
    }

    private Method lookupMethod(Clazz c, String name, String descriptor) {
        Method tmp = MethodLookup.lookupMethodInClass(c,name,descriptor);
        if(tmp==null)
        {
            tmp = MethodLookup.lookupMethodInInterfaces(c.getInterfaces(),name,descriptor);
        }
        return tmp;
    }
}
