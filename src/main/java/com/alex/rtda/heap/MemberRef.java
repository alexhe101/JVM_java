package com.alex.rtda.heap;

import com.alex.classfile.constantInfo.ConstantMemberRefInfo;

/**
 * @author alex101
 */
public class MemberRef extends SymRef{
    protected String name;
    protected String descriptor;

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void copyMemberRefInfo(ConstantMemberRefInfo constantMemberRefInfo)
    {
        className = constantMemberRefInfo.getClassName();
        name = constantMemberRefInfo.getNameAndDescriptor()[0];
        descriptor = constantMemberRefInfo.getNameAndDescriptor()[1];
    }




}
