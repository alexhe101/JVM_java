package com.alex.rtda.heap;

import com.alex.classfile.constantInfo.ConstantMemberRefInfo;

public class SymRef {
    //指向所在的常量池
    protected RuntimeConstantPool runtimeConstantPool;
    protected String className;
    //指向所在的类
    protected Clazz clazz;

    public Clazz resolvedClass()
    {
        if(clazz==null)
        {
            resolveClassRef();
        }
        return clazz;
    }

    private void resolveClassRef() {
        Clazz d = runtimeConstantPool.getClazz();
        Clazz c = d.getLoader().loadClass(className);
        if(!c.isAccessibleTo(d))
        {
            throw new IllegalAccessError("java.lang.IllegalAccessError");
        }
        clazz= c;
    }

}
