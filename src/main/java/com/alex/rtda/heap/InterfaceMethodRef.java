package com.alex.rtda.heap;

import com.alex.classfile.constantInfo.ConstantInterfaceMethodrefInfo;

public class InterfaceMethodRef extends MemberRef{
    private Method method;

    public InterfaceMethodRef(RuntimeConstantPool runtimeConstantPool, ConstantInterfaceMethodrefInfo constantInterfaceMethodrefInfo) {
        this.runtimeConstantPool = runtimeConstantPool;
        copyMemberRefInfo(constantInterfaceMethodrefInfo);

    }
}
