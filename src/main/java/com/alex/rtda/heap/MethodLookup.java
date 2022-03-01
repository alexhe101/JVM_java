package com.alex.rtda.heap;

public class MethodLookup {
    public static Method lookupMethodInClass(Clazz clazz,String name,String descriptor)
    {
        for (Clazz c = clazz;c!=null;c = c.getSuperClass())
        {
            for (Method method:
                c.getMethods() ) {
                if(method.getName().equals(name)&&method.getDescriptor().equals(descriptor))
                {
                    return method;
                }
            }
        }
        return null;
    }

    public static Method lookupMethodInInterfaces(Clazz[] ifaces,String name,String descriptor)
    {
        for (Clazz ifcae:
                ifaces) {
            for (Method method:
                    ifcae.getMethods()) {
                if(method.getName().equals(name)&&method.getDescriptor().equals(descriptor))
                {
                    return method;
                }
            }
            Method tmp = lookupMethodInInterfaces(ifcae.getInterfaces(),name,descriptor);
            if(tmp!=null)
            {
                return tmp;
            }
        }
        return null;
    }
}
