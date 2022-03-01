package com.alex.rtda.heap;

import com.alex.classfile.constantInfo.ConstantInterfaceMethodrefInfo;

/**
 * @author alex101
 */
public class InterfaceMethodRef extends MemberRef{
    private Method method;

    public InterfaceMethodRef(RuntimeConstantPool runtimeConstantPool, ConstantInterfaceMethodrefInfo constantInterfaceMethodrefInfo) {
        this.runtimeConstantPool = runtimeConstantPool;
        copyMemberRefInfo(constantInterfaceMethodrefInfo);
    }

    public Method resolvedInterfaceMethod()
    {
        if(method==null)
        {
            resolveInterfaceMethodRef();
        }
        return method;
    }

    private void resolveInterfaceMethodRef() {
        Clazz d = runtimeConstantPool.getClazz();
        Clazz c = resolvedClass();
        if(!c.isInterface())
        {
            throw  new IncompatibleClassChangeError();
        }
        method = lookupInterfaceMethod(c,name,descriptor);
        if(method==null)
        {
            throw new NoSuchMethodError();
        }
        if(!method.isAccessibleTo(d))
        {
            throw new IllegalAccessError();
        }
    }

    private Method lookupInterfaceMethod(Clazz iface, String name, String descriptor) {
        for (Method method:
             iface.getMethods()) {
            if(method.getDescriptor().equals(descriptor)&&method.getName().equals(name))
            {
                return method;
            }
        }
        return MethodLookup.lookupMethodInInterfaces(iface.getInterfaces(),name,descriptor);
    }
}
