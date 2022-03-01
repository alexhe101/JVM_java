package com.alex.rtda.heap;

import com.alex.Interpreter;
import com.alex.classfile.ClassFile;
import com.alex.classpath.ClassPath;

import java.io.File;
import java.util.HashMap;

public class ClassLoader {
    private boolean verboseFlag;
    private ClassPath classPath;
    //方法区的具体实现 将class存在map中
    private HashMap<String,Clazz> classMap;

    public ClassLoader(ClassPath classPath,boolean verboseFlag) {
        this.verboseFlag = verboseFlag;
        this.classPath = classPath;
        classMap = new HashMap<>();
    }

    public Clazz loadClass(String name)
    {
        if(classMap.containsKey(name))
        {
            return classMap.get(name);
        }
        if(name.charAt(0)=='[')
        {
            return loadArrayClass(name);
        }
        return loadNonArrayClass(name);

    }

    private Clazz loadArrayClass(String name) {
        Clazz clazz = new Clazz(AccessFlags.ACC_PUBLIC,name,this,true,loadClass("java/lang/Object"),
                new Clazz[]{loadClass("java/lang/Cloneable"),loadClass("java/io/Serializable")});
        classMap.put(name,clazz);
        return clazz;
    }

    //jvm类加载机制
    private Clazz loadNonArrayClass(String name)
    {
        byte[] data = readClass(name);
        Clazz clazz = defineClass(data);
        link(clazz);
        if(verboseFlag)
        {
            System.out.printf("[Loaded %s]\n",name);
        }
        return clazz;
    }

    private void link(Clazz clazz) {
        verify(clazz);
        prepare(clazz);
    }

    private void prepare(Clazz clazz)
    {
        calcInstanceFieldSlotIds(clazz);
        calcStaticFieldSlotIds(clazz);
        allocAndInitStaticVars(clazz);
        //todo:6.4
    }

    private void allocAndInitStaticVars(Clazz clazz) {
        clazz.setStaticVars(new Slots(clazz.getStaticSlotCount()));
        Field[] fields = clazz.getFields();
        for (int i = 0; i < fields.length; i++) {
            if(fields[i].isStatic()&&fields[i].isFinal())
            {
                initStaticFinalVar(clazz,fields[i]);
            }
        }
    }

    private void initStaticFinalVar(Clazz clazz, Field field) {
        Slots vars = clazz.getStaticVars();
        RuntimeConstantPool constantPool = clazz.getConstantPool();
        int cpIndex = field.getConstValueIndex();
        int slotId = field.getSlotId();
        if(cpIndex>0)
        {
            switch (field.getDescriptor())
            {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    int val = (int) constantPool.getConstant(cpIndex).getValue();
                    vars.setInt(slotId,val);
                    break;
                case "J":
                    long lv = (long)constantPool.getConstant(cpIndex).getValue();
                    vars.setLong(slotId,lv);
                    break;
                case "F":
                    float fv = (float) constantPool.getConstant(cpIndex).getValue();
                    vars.setFloat(slotId,fv);
                    break;
                case "D":
                    double dv = (double) constantPool.getConstant(cpIndex).getValue();
                    vars.setDouble(slotId,dv);
                    break;
                    //todo:panic
                case "Ljava/lang/String;":
                    String str = (String) constantPool.getConstant(cpIndex).getValue();
                    Object jstr = StringPool.jString(clazz.getLoader(),str);
                    vars.setRef(slotId,jstr);
            }
        }
    }

    private void calcStaticFieldSlotIds(Clazz clazz) {
        int slotId = 0;
        for(int i=0;i<clazz.getFields().length;i++)
        {
            Field field = clazz.getFields()[i];
            if(field.isStatic())
            {
                field.setSlotId(slotId);
                slotId++;
                if(field.isLongOrDouble())
                {
                    slotId++;
                }
            }
        }
        clazz.setStaticSlotCount(slotId);
    }

    private void calcInstanceFieldSlotIds(Clazz clazz) {
        int slotId = 0;
        if(clazz.getSuperClass()!=null)
        {
            slotId = clazz.getSuperClass().getInstanceSlotCount();
        }
        for(int i=0;i<clazz.getFields().length;i++)
        {
            Field field = clazz.getFields()[i];
            if(!field.isStatic())
            {
                field.setSlotId(slotId);
                slotId++;
                if(field.isLongOrDouble())
                {
                    slotId++;
                }
            }
        }
        clazz.setInstanceSlotCount(slotId);
    }

    private void verify(Clazz clazz)
    {
        //empty function
    }

    private byte[] readClass(String name)
    {
        byte[] data = classPath.readClass(name);
        if (data != null) {
            return data;
        } else {
            throw new ClassCastException("class name: " + name);
        }
    }
    
    private Clazz defineClass(byte[] data)
    {
        Clazz clazz = parseClass(data);// data->classfile->class
        clazz.setLoader(this);
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        classMap.put(clazz.getName(),clazz);
        return clazz;
    }

    private void resolveInterfaces(Clazz clazz) {
        int interfaceCount = clazz.getInterfaceNames().length;
        if(interfaceCount>0)
        {
            clazz.setInterfaces(new Clazz[interfaceCount]);
            for (int i = 0; i < interfaceCount; i++) {
                clazz.getInterfaces()[i] = clazz.getLoader().loadClass(clazz.getInterfaceNames()[i]);
            }
        }
    }

    private void resolveSuperClass(Clazz clazz) {
        if(!clazz.getName().equals("java/lang/Object"))
        {
            clazz.setSuperClass(clazz.getLoader().loadClass(clazz.getSuperClassName()));
        }
    }

    private Clazz parseClass(byte[] data) {
        ClassFile classFile = new ClassFile(data);
        return new Clazz(classFile);
    }

}
