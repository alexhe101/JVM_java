package com.alex.classfile;

/**
 * @program: jvm
 * @description:member_info和field_info结构一致
 * @author: alex101
 * @create: 2022-01-26 18:01
 **/
public class MemberInfo {
    private ConstantPool constantPool;
    private char accessFlags;
    private char nameIndex;
    private char descriptorIndex;
    private AttributeInfo[] attributes;

    public MemberInfo(ClassReader classReader,ConstantPool cp)
    {
        this.constantPool = cp;
        accessFlags = classReader.readUint16();
        nameIndex = classReader.readUint16();
        descriptorIndex = classReader.readUint16();
        attributes =  AttributeInfo.readAttributes(classReader,cp);
    }


    public String getName()
    {
        return constantPool.getUtf8(nameIndex);
    }

    public String getDescriptor()
    {
        return constantPool.getUtf8(descriptorIndex);
    }

//    public static MemberInfo[] readMembers(ClassReader classReader,ConstantPool cp)
//    {
//        int memberCount = classReader.readUint16();
//        MemberInfo[] members  =new MemberInfo[memberCount];
//        for(int i=0;i<memberCount;i++)
//        {
//            members[i] = new MemberInfo(classReader,cp);
//        }
//        return members;
//    }
}
