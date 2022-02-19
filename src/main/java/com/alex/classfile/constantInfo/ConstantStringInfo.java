package com.alex.classfile.constantInfo;

import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantInfo;
import com.alex.classfile.ConstantPool;

/** 
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-27 16:36
 **/
public class ConstantStringInfo extends ConstantInfo {
    private ConstantPool constantPool;
    private int stringIndex;
    public ConstantStringInfo(ConstantPool cp) {
        this.constantPool = cp;
    }

    @Override
    public void readInfo(ClassReader classReader) {
        stringIndex = classReader.readUint16();
    }


    public String getString()
    {
        return constantPool.getUtf8(stringIndex);
    }
}
