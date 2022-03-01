package com.alex.classfile;

import com.alex.classfile.constantInfo.ConstantClassInfo;
import com.alex.classfile.constantInfo.ConstantNameAndTypeInfo;
import com.alex.classfile.constantInfo.ConstantUtf8Info;
import com.alex.rtda.heap.Clazz;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-26 18:00
 **/
public class ConstantPool {
    private ConstantInfo[] constantInfos;
    private int constantPoolCount;

    public ConstantInfo[] getConstantInfos() {
        return constantInfos;
    }

    public ConstantPool(ClassReader classReader) {
        //实际大小为n-1
        int cpCount = classReader.readUint16();
        constantPoolCount = cpCount;
        constantInfos = new ConstantInfo[cpCount];
        for(int i=1;i<cpCount;i++)
        {
            constantInfos[i] = ConstantInfo.readConstantInfo(classReader,this);
            switch (constantInfos[i].getClass().getSimpleName())
            {
                case "ConstantLongInfo":
                case "ConstantDoubleInfo":
                    i++;
                    break;
            }
        }
    }


    public ConstantInfo getConstantInfo(int index)
    {
        ConstantInfo tmp = constantInfos[index];
        if(tmp!=null)
        {
            return tmp;
        }
        throw new RuntimeException("Invalid constant pool index");
    }

    public String getClassName(int index)
    {
        ConstantClassInfo constantClassInfo = (ConstantClassInfo)constantInfos[index];
        return getUtf8(constantClassInfo.getNameIndex());
    }

    public String getUtf8(int index)
    {
        ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) constantInfos[index];
        return constantUtf8Info.getString();
    }

    public String[] getNameAndType(int index)
    {
        ConstantNameAndTypeInfo constantNameAndTypeInfo = (ConstantNameAndTypeInfo) getConstantInfo(index);
        String[] ret = new String[2];
        ret[0] = getUtf8(constantNameAndTypeInfo.getNameIndex());
        ret[1] = getUtf8(constantNameAndTypeInfo.getDescriptorIndex());
        return ret;
    }

    public int getConstantPoolCount() {
        return constantPoolCount;
    }
}
