package com.alex.rtda.heap;

import com.alex.classfile.MemberInfo;

/**
 * @author alex101
 */
public class ClassMember {
    private int accessFlags;
    private String name;
    private String descriptor;
    //引用 指向所属类
    private Clazz clazz;

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public void copyMemberInfo(MemberInfo memberInfo)
    {
        accessFlags = memberInfo.getAccessFlags();
        name = memberInfo.getName();
        descriptor = memberInfo.getDescriptor();

    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public Clazz getClazz() {
        return clazz;
    }
    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isPrivate() {
        return 0 != (accessFlags & AccessFlags.ACC_PRIVATE);
    }

    public boolean isProtected() {
        return 0 != (accessFlags & AccessFlags.ACC_PROTECTED);
    }

    public boolean isStatic() {
        return 0 != (accessFlags & AccessFlags.ACC_STATIC);
    }

    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSynthetic() {
        return 0 != (accessFlags & AccessFlags.ACC_SYNTHETIC);
    }

    public boolean isAbstract(){
        return (accessFlags&AccessFlags.ACC_ABSTRACT)!=0;
    }

    public boolean isAccessibleTo(Clazz d) {
        if(isPublic())
        {
            return true;
        }
        Clazz c = clazz;
        if(isProtected())
        {
            return d==c||d.isSubClassOf(c)||c.getPackageName().equals(d.getPackageName());

        }
        if(!isPrivate())
        {
            return c.getPackageName().equals(d.getPackageName());
        }
        return c==d;
    }
}
