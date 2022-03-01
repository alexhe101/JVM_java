package com.alex.rtda.heap;

import com.alex.classfile.constantInfo.ConstantClassInfo;

public class ClassRef extends SymRef{
    public ClassRef(RuntimeConstantPool runtimeConstantPool, ConstantClassInfo constantClassInfo) {
        this.runtimeConstantPool = runtimeConstantPool;
        className = constantClassInfo.getName();
    }
}
