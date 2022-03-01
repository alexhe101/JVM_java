package com.alex.rtda.heap;

import com.alex.classfile.constantInfo.ConstantFieldrefInfo;

public class FieldRef extends MemberRef{
    private Field field;

    public FieldRef(RuntimeConstantPool runtimeConstantPool, ConstantFieldrefInfo constantFieldrefInfo) {
        this.runtimeConstantPool = runtimeConstantPool;
        copyMemberRefInfo(constantFieldrefInfo);
    }

    public Field resolvedField()
    {
        if(field==null)
        {
            resolveFiledRef();
        }
        return field;
    }

    private void resolveFiledRef() {
        Clazz d = runtimeConstantPool.getClazz();
        Clazz c = resolvedClass();
        Field tmp = lookupField(c,name,descriptor);
        if(tmp==null)
        {
            throw new NoSuchFieldError();
        }
        if(!tmp.isAccessibleTo(d))
        {
            throw new IllegalAccessError();
        }
        field = tmp;
    }

    private Field lookupField(Clazz c, String name, String descriptor) {
        Field tmp;
        Field[] fields = c.getFields();
        for (Field field:
            fields ) {
            if(field.getDescriptor().equals(descriptor)&&field.getName().equals(name))
            {
                return field;
            }
        }
        Clazz[] interfaces = c.getInterfaces();
        for (Clazz inter:
             interfaces) {
            tmp = lookupField(inter,name,descriptor);
            if(tmp!=null) {
                return tmp;
            }
        }
        if(c.getSuperClass()!=null)
        {
            return lookupField(c.getSuperClass(),name,descriptor);
        }
        return null;
    }

}
