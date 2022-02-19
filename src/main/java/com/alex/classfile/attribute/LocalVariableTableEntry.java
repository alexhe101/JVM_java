package com.alex.classfile.attribute;

import com.alex.classfile.ClassReader;

public class LocalVariableTableEntry {
    int startPc;    //代表该局部变量的生命周期开始的字节码偏移量
    int length;     //代表该局部变量的作用范围所覆盖的长度
    int nameIndex;    //指向常量池中个CONSTANT_Utf8_info型常量的索引，代表局部变量名称
    int descriptorIndex;    //指向常量池中个CONSTANT_Utf8_info型常量的索引，变量描述符
    int index;      //该局部变量在栈帧局部变量包中slot的位置

    public LocalVariableTableEntry(ClassReader classReader) {
        startPc = classReader.readUint16();
        length = classReader.readUint16();
        nameIndex = classReader.readUint16();
        descriptorIndex = classReader.readUint16();
        index = classReader.readUint16();
    }

    public int getStartPc() {
        return startPc;
    }

    public int getLength() {
        return length;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public int getIndex() {
        return index;
    }
}
