package com.alex.rtda.heap;

import com.alex.classfile.constantInfo.ConstantMethodrefInfo;

public class MethodRef extends MemberRef{
    private Method method;

    public MethodRef(RuntimeConstantPool runtimeConstantPool, ConstantMethodrefInfo constantMethodrefInfo) {
        this.runtimeConstantPool = runtimeConstantPool;
        copyMemberRefInfo(constantMethodrefInfo);
    }
}
