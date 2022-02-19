package com.alex.classfile;

import com.alex.classfile.attribute.*;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-26 18:02
 **/
public abstract class AttributeInfo {


    public abstract void readInfo(ClassReader classReader);
    public static AttributeInfo[] readAttributes(ClassReader classReader, ConstantPool cp) {
        int attributesCount = classReader.readUint16();
        AttributeInfo[]attributes = new AttributeInfo[attributesCount];
        for (int i=0;i<attributesCount;i++)
        {
            attributes[i] = AttributeInfo.readAttributeInfo(classReader,cp);
        }
        return attributes;
    }

    public static AttributeInfo readAttributeInfo(ClassReader classReader,ConstantPool constantPool)
    {
        int attrNameIndex = classReader.readUint16();
        String attrName = constantPool.getUtf8(attrNameIndex);
        int attrLen = classReader.readUint32();
        AttributeInfo attributeInfo = newAttributeInfo(attrName,attrLen,constantPool);
        attributeInfo.readInfo(classReader);
        return attributeInfo;
    }
    public static AttributeInfo newAttributeInfo(String attrName,int attrLen,ConstantPool constantPool)
    {
        switch (attrName){
            case "Code":return new CodeAttribute(constantPool);
            case "ConstantValue":return new ConstantValueAttribute();
            case "Deprecated":
                return new DeprecatedAttribute();
            case "Exceptions":return new ExceptionsAttribute();
            case "LineNumberTable":return new LineNumberTableAttribute();
            case "LocalVariableTable":return new LocalVariableTableAttribute();
            case "SourceFile":return new SourceFileAttribute(constantPool);
            case "Synthetic":
                return new SyntheticAttribute();
            default:return new UnparsedAttribute(attrName,attrLen);
        }
    }

}
