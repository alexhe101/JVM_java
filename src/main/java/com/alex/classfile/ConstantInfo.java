package com.alex.classfile;

import com.alex.classfile.constantInfo.*;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-26 22:20
 **/
public abstract class ConstantInfo {
    public int type;
    public static final int CONSTANT_CLASS=7;
    public static final int CONSTANT_Fieldref=9;
    public static final int CONSTANT_Methodref=10;
    public static final int CONSTANT_InterfaceMethodref=11;
    public static final int CONSTANT_String=8;
    public static final int CONSTANT_Integer=3;
    public static final int CONSTANT_Float=4;
    public static final int CONSTANT_Long=5;
    public static final int CONSTANT_Double=6;
    public static final int CONSTANT_NameAndType=12;
    public static final int CONSTANT_Utf8=1;
    public static final int CONSTANT_MethodHandle=15;
    public static final int CONSTANT_MethodType=16;
    public static final int CONSTANT_InvokeDynamic=18;
    public abstract void  readInfo(ClassReader classReader);
    public static ConstantInfo readConstantInfo(ClassReader classReader,ConstantPool constantPool)
    {
        byte tag = classReader.readUint8();
        ConstantInfo constantInfo = newConstantInfo(tag,constantPool);
        constantInfo.readInfo(classReader);
        return constantInfo;

    }

    private static ConstantInfo newConstantInfo(byte tag,ConstantPool constantPool)
    {
        switch (tag)
        {
            case CONSTANT_Integer:
                return new ConstantIntegerInfo(tag);
            case CONSTANT_Float:
                return new ConstantFloatInfo(tag);
            case CONSTANT_Long:
                return new ConstantLongInfo(tag);
            case CONSTANT_Double:
                return new ConstantDoubleInfo(tag);
            case CONSTANT_Utf8:
                return new ConstantUtf8Info(tag);
            case CONSTANT_String:
                return new ConstantStringInfo(constantPool,tag);
            case CONSTANT_CLASS:
                return new ConstantClassInfo(constantPool,tag);
            case CONSTANT_Fieldref:
                return new ConstantFieldrefInfo(constantPool,tag);
            case CONSTANT_Methodref:
                return new ConstantMethodrefInfo(constantPool,tag);
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodrefInfo(constantPool,tag);
            case CONSTANT_NameAndType:
                return new ConstantNameAndTypeInfo(tag);
            case CONSTANT_MethodType:
                return new ConstantMethodTypeInfo(tag);
            case CONSTANT_MethodHandle:
                return new ConstantMethodHandleInfo(tag);
            case CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamicInfo(tag);
            default:
                throw new RuntimeException("java.lang.ClassFormatError:constant pool tag!");
        }
    }

}
