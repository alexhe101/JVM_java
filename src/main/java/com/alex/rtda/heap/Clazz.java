package com.alex.rtda.heap;

import com.alex.classfile.ClassFile;
import com.alex.classfile.ConstantPool;
import com.alex.classfile.MemberInfo;
import com.alex.rtda.Slot;

import javax.management.RuntimeErrorException;

//类信息 保存在方法区中
public class Clazz {
    private int accessFlags;
    private String name;
    private String superClassName;
    private String[] interfaceNames;
    private boolean initStarted;
    //运行时常量池
    private RuntimeConstantPool constantPool;
    //字段信息
    private Field[] fields;
    //方法信息
    private Method[] methods;

    private ClassLoader loader;
    private Clazz superClass;
    private Clazz[] interfaces;
    //实例变量
    private int instanceSlotCount;
    private int staticSlotCount;
    //todo:slots
    //静态变量
    private Slots staticVars;

    public Clazz(int accessFlags, String name, ClassLoader classLoader, boolean initStarted, Clazz superClass, Clazz[] interfaces) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.loader = classLoader;
        this.initStarted=  initStarted;
        this.superClass = superClass;
        this.interfaces = interfaces;

    }

    public RuntimeConstantPool getConstantPool() {
        return constantPool;
    }

    public boolean isInitStarted()
    {
        return initStarted;
    }

    public void startInit()
    {
        initStarted = true;
    }



    public Method[] getMethods() {
        return methods;
    }

    public void setConstantPool(RuntimeConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public Slots getStaticVars() {
        return staticVars;
    }

    public void setStaticVars(Slots staticVars) {
        this.staticVars = staticVars;
    }

    public int getStaticSlotCount() {
        return staticSlotCount;
    }

    public void setStaticSlotCount(int staticSlotCount) {
        this.staticSlotCount = staticSlotCount;
    }


    public Field[] getFields() {
        return fields;
    }

    public int getInstanceSlotCount() {
        return instanceSlotCount;
    }

    public void setInstanceSlotCount(int instanceSlotCount) {
        this.instanceSlotCount = instanceSlotCount;
    }

    public Clazz getSuperClass() {
        return superClass;
    }

    public Clazz[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Clazz[] interfaces) {
        this.interfaces = interfaces;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public ClassLoader getLoader() {
        return loader;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public void setSuperClass(Clazz superClass) {
        this.superClass = superClass;
    }

    public String getName() {
        return name;
    }

    public void setLoader(ClassLoader loader) {
        this.loader = loader;
    }

    public Clazz(ClassFile classFile)
    {
        accessFlags = classFile.getAccessFlags();
        name = classFile.getClassName();
        superClassName  = classFile.getSuperClassName();
        interfaceNames = classFile.getInterfaceNames();
        //创建运行时常量池
        constantPool = new RuntimeConstantPool(this,classFile.getConstantPool());
        fields = Field.newFields(this,classFile.getFields());
        methods = Method.newMethods(this,classFile.getMethods());

    }

    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSuper() {
        return 0 != (accessFlags & AccessFlags.ACC_SUPER);
    }

    public boolean isInterface() {
        return 0 != (accessFlags & AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return 0 != (accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isSynthetic() {
        return 0 != (accessFlags & AccessFlags.ACC_SYNTHETIC);
    }

    public boolean isAnnotation() {
        return 0 != (accessFlags & AccessFlags.ACC_ANNOTATION);
    }

    public boolean isEnum() {
        return 0 != (accessFlags & AccessFlags.ACC_ENUM);
    }

    public boolean isAccessibleTo(Clazz other) {
        return isPublic()||getPackageName().equals(other.getPackageName());
    }

    public String getPackageName() {
        int i = name.lastIndexOf("/");
        if (i > 0) {
            return name.substring(0, i);
        }
        return "";

    }

    public boolean isSubClassOf(Clazz c) {
        for(Clazz t=superClass;t!=null;t=t.getSuperClass())
        {
            if(t==c)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isSuperClassOf(Clazz sub) {
        return sub.isSubClassOf(this);
    }


    public Object newObject()
    {
        return new Object(this);
    }

    public Object newArray(int count)
    {
        if(!isArray())
        {
            throw new RuntimeException("not array class"+getName());
        }
        switch (getName()) {
            case "[Z":
                return new Object(this, new byte[count], null);
            case "[B":
                return new Object(this, new byte[count], null);
            case "[C":
                return new Object(this, new char[count], null);
            case "[S":
                return new Object(this, new short[count], null);
            case "[I":
                return new Object(this, new int[count], null);
            case "[J":
                return new Object(this, new long[count], null);
            case "[F":
                return new Object(this, new float[count], null);
            case "[D":
                return new Object(this, new double[count], null);
            default:
                return new Object(this, new Object[count], null);
        }
    }

    private boolean isArray() {
            return name.startsWith("[");
    }

    public boolean isJlObject() {
        return "java/lang/Object".equals(name);
    }

        //src是否由target拓展
        public boolean isAssignableFrom(Clazz source) {
            // source 是否由 target 扩展而来（子类）
            Clazz target = this;
            if (source == target) {
                return true;
            }

            if (!source.isArray()) {
                if (!source.isInterface()) {
                    if (!target.isInterface()) {
                        return source.isSubClassOf(target);
                    } else {
                        // target 是接口
                        return source.isImplements(target);
                    }
                } else {
                    // source 是接口
                    if (!target.isInterface()) {
                        return target.isJlObject();
                    } else {
                        // target 也是接口
                        return target.isSuperInterfaceOf(source);
                    }
                }
            } else {
                //source 是数组
                if (!target.isArray()) {
                    if (!target.isInterface()) {
                        return target.isJlObject();
                    } else {
                        // target 是接口
                        // t is interface;数组默认实现了Cloneable和Serializable接口
                        return target.isJlCloneable() || target.isJioSerializable();
                    }
                } else {
                    // target 也是数组
                    Clazz sc = source.getComponentClass();
                    Clazz tc = target.getComponentClass();
                    return sc == tc || tc.isAssignableFrom(source);
                }
            }
        }


    private boolean isJlCloneable() {
        return "java/lang/Cloneable".equals(name);
    }

    public boolean isJioSerializable() {
        return "java/io/Serializable".equals(name);
    }
    private boolean isSuperInterfaceOf(Clazz source) {
        return source.isSubInterfaceOf(this);
    }
    public boolean isImplements(Clazz iface) {
        for (Clazz c = this;c!=null;c=c.getSuperClass())
        {
            for(int i=0;i<c.getInterfaces().length;i++)
            {
                if(c.getInterfaces()[i]==iface||c.getInterfaces()[i].isSubInterfaceOf(iface))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSubInterfaceOf(Clazz iface) {
        for (Clazz superInterface : interfaces) {
            if (superInterface == iface || superInterface.isSubInterfaceOf(iface)) {
                return true;
            }
        }
        return false;
    }

    public Method getMainMethod()
    {
        return getStaticMethod("main","([Ljava/lang/String;)V");
    }

    private Method getStaticMethod(String name, String descriptor) {
        for (int i = 0; i < methods.length; i++) {
            if(methods[i].isStatic()&&methods[i].getName().equals(name)&&methods[i].getDescriptor().equals(descriptor))
            {
                return methods[i];
            }
        }
        return null;
    }

    public Method getClinitMethod() {
        return getStaticMethod("<clinit>","()V");
    }

    public Clazz arrClass() {
        String arrClassName = ClassNameHelper.getArrayClassName(name);
        return loader.loadClass(arrClassName);
    }

    public Clazz getComponentClass() {
        String componentClassName = ClassNameHelper.getComponentClassName(name);
        return loader.loadClass(componentClassName);
    }

    public Field getField(String name, String descriptor, boolean isStatic) {
        for(Clazz c = this;c!=null;c= c.superClass)
        {
            for (Field field:
                 fields) {
                if(field.isStatic()==isStatic&&field.getName().equals(name)
                &&field.getDescriptor().equals(descriptor))
                {
                    return field;
                }
            }
        }
        return null;
    }


    //todo:isaccessiable
//    public boolean isAccessibleTo(Clazz other) {
//        return isPublic() || getPackageName().equals(other.getPackageName());
//    }


}
