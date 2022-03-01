package com.alex.classfile.constantInfo;

import com.alex.classfile.ConstantInfo;
import com.alex.classfile.ConstantPool;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-27 16:37
 **/
public class ConstantInterfaceMethodrefInfo extends ConstantMemberRefInfo {
    public ConstantInterfaceMethodrefInfo(ConstantPool constantPool,int tag) {
        super(constantPool);
        this.type = tag;
    }
}
