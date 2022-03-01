package com.alex.rtda.heap;

import com.alex.classfile.ConstantInfo;
import com.alex.classfile.ConstantPool;
import com.alex.classfile.constantInfo.*;

public class RuntimeConstantPool {
    private Clazz clazz;
    private RuntimeConstantInfo[] runtimeConstantInfos;

    public Clazz getClazz() {
        return clazz;
    }

    public RuntimeConstantPool(Clazz clazz, ConstantPool constantPool)
    {
        this.clazz = clazz;
        ConstantInfo[] constantInfos = constantPool.getConstantInfos();
        int cpCount = constantPool.getConstantPoolCount();
        runtimeConstantInfos = new RuntimeConstantInfo[cpCount];
        for (int i = 1; i < cpCount; i++) {
            ConstantInfo constantInfo = constantInfos[i];
            switch (constantInfo.type)
            {
                case ConstantInfo.CONSTANT_Integer:
                    runtimeConstantInfos[i] =  new RuntimeConstantInfo<Integer>(((ConstantIntegerInfo)constantInfo).getVal(),
                            ConstantInfo.CONSTANT_Integer);
                    break;
                case ConstantInfo.CONSTANT_Float:
                    runtimeConstantInfos[i] = new RuntimeConstantInfo<Float>(((ConstantFloatInfo)constantInfo).getVal(),
                            ConstantInfo.CONSTANT_Float);
                    break;
                case ConstantInfo.CONSTANT_Long:
                    runtimeConstantInfos[i] = new RuntimeConstantInfo<Long>(((ConstantLongInfo)constantInfo).getVal(),
                            ConstantInfo.CONSTANT_Long);
                    i++;
                    break;
                case ConstantInfo.CONSTANT_Double:
                    ConstantDoubleInfo doubleInfo = (ConstantDoubleInfo) constantInfo;
                    this.runtimeConstantInfos[i] = new RuntimeConstantInfo<Double>(doubleInfo.getVal(), ConstantInfo.CONSTANT_Double);
                    i++;
                    break;
                case ConstantInfo.CONSTANT_String:
                    ConstantStringInfo stringInfo = (ConstantStringInfo) constantInfo;
                    this.runtimeConstantInfos[i] = new RuntimeConstantInfo<String>(stringInfo.getString(),ConstantInfo.CONSTANT_String);
                    break;

                    //运行时常量池中的符号引用
                case ConstantInfo.CONSTANT_CLASS:
                    ConstantClassInfo classInfo = (ConstantClassInfo) constantInfo;
                    this.runtimeConstantInfos[i] = new RuntimeConstantInfo<ClassRef>(new ClassRef(this, classInfo), ConstantInfo.CONSTANT_CLASS);
                    break;
                case ConstantInfo.CONSTANT_Fieldref:
                     ConstantFieldrefInfo fieldRefInfo = (ConstantFieldrefInfo) constantInfo;
                    this.runtimeConstantInfos[i] = new RuntimeConstantInfo<FieldRef>(new FieldRef(this, fieldRefInfo), ConstantInfo.CONSTANT_Fieldref);
                    break;
                case ConstantInfo.CONSTANT_Methodref:
                    ConstantMethodrefInfo methodRefInfo = (ConstantMethodrefInfo) constantInfo;
                    this.runtimeConstantInfos[i] = new RuntimeConstantInfo<MethodRef>(new MethodRef(this, methodRefInfo), ConstantInfo.CONSTANT_Methodref);
                    break;
                case ConstantInfo.CONSTANT_InterfaceMethodref:
                    ConstantInterfaceMethodrefInfo interfaceMethodRefInfo = (ConstantInterfaceMethodrefInfo) constantInfo;
                    this.runtimeConstantInfos[i] = new RuntimeConstantInfo<InterfaceMethodRef>(new InterfaceMethodRef(this, interfaceMethodRefInfo), ConstantInfo.CONSTANT_InterfaceMethodref);
                    break;
                default:
                    break;
            }
        }
    }

    public RuntimeConstantInfo getConstant(int index)
    {
        if(runtimeConstantInfos[index]!=null)
        {
            return runtimeConstantInfos[index];
        }
        throw new RuntimeException("no constant at index "+index);
    }
}
