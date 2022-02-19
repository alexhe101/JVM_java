package com.alex.classfile;

/**
 * @program: jvm
 * @description:java class structure
 * @author: alex101
 * @create: 2022-01-26 17:58
 **/
public class ClassFile {
    private int magic;
    private char minorVersion;
    private char majorVersion;
    private ConstantPool constantPool;
    private char accessFlags;
    private char thisClass;
    private char superClass;
    private char[] interfaces;
    private MemberInfo[] fields;
    private MemberInfo[] methods;
    private AttributeInfo[] attributes;

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public ClassFile(byte[] classData)
    {
        ClassReader classReader = new ClassReader(classData);
        read(classReader);
    }

    private void read(ClassReader classReader)
    {
        //读取Java魔数
        readAndCheckMagic(classReader);
        //校验魔数(cafebabe)
        readAndCheckVersion(classReader);
        //读取常量池
        this.constantPool = readConstantPool(classReader);
        //读取类访问标识
        this.accessFlags = classReader.readUint16();
        //类名索引
        this.thisClass = classReader.readUint16();
        //父类名索引
        this.superClass = classReader.readUint16();
        //读取接口索引表
        this.interfaces = classReader.readUint16s();

        this.fields = readMembers(classReader);
        this.methods = readMembers(classReader);
        this.attributes = readAttributes(classReader);
    }

    private void readAndCheckMagic(ClassReader classReader)
    {
        magic = classReader.readUint32();
        int valid = 0xcafebabe;
        if(magic!=valid)
        {
            throw new RuntimeException("java.lang.ClassFormatError:magic!");
        }
    }

    private void readAndCheckVersion(ClassReader classReader)
    {
        minorVersion = classReader.readUint16();
        majorVersion = classReader.readUint16();
        if(majorVersion==45) return;
        if(majorVersion>=46&&majorVersion<=52)
        {
            if(minorVersion==0) return;
        }
        throw new RuntimeException("java.lang.UnsupportedClassVersionError!");
    }

    private ConstantPool readConstantPool(ClassReader classReader)
    {
        return new ConstantPool(classReader);
    }

    private MemberInfo[] readMembers(ClassReader classReader)
    {
        int fieldCount = classReader.readUint16();
        MemberInfo[] tmp = new MemberInfo[fieldCount];
        for (int i = 0; i < fieldCount; i++) {
            tmp[i] = new MemberInfo(classReader,constantPool);
        }
        return tmp;
    }


    private AttributeInfo[] readAttributes(ClassReader classReader)
    {
        return AttributeInfo.readAttributes(classReader,constantPool);
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public char getAccessFlags() {
        return accessFlags;
    }

    public MemberInfo[] getFields() {
        return fields;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public String getClassName() {
        return constantPool.getClassName(thisClass);
    }

    public String getSuperClassName() {
        if(superClass>0) {
            return constantPool.getClassName(superClass);
        } else {
            return "";
        }
    }

    public String[] getInterfaceNames()
    {
        String[] interfaceName = new String[interfaces.length];
        for (int i=0;i<interfaces.length;i++)
        {
            interfaceName[i] = constantPool.getClassName(interfaces[i]);
        }

        return interfaceName;
    }


}
