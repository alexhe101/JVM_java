package com.alex.classfile.attribute;

import com.alex.classfile.AttributeInfo;
import com.alex.classfile.ClassReader;

public class UnparsedAttribute extends AttributeInfo {
    private byte[]info;
    private int length;
    private String name;

    @Override
    public void readInfo(ClassReader classReader) {
        info = classReader.readBytes(length);
    }

    public UnparsedAttribute(String attrName,int attrLen) {
        name = attrName;
        length = attrLen;
    }
}
