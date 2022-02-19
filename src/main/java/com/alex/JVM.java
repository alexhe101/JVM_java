package com.alex;

import com.alex.classfile.AttributeInfo;
import com.alex.classfile.ClassFile;
import com.alex.classfile.MemberInfo;
import com.alex.classpath.ClassPath;

import java.util.Arrays;

/**
 * @program: jvm
 * @description: jvm
 * @author: alex101
 * @create: 2022-01-12 17:06
 **/
public class JVM {
    public void startJVM(CMD cmd)
    {
        ClassPath classPath = new ClassPath(cmd.getXjreOption(),cmd.getCpOption());

//        System.out.printf("classpath:%s class:%s args:[%s]\n",classPath.getUserClassPath(),cmd.getCls(),String.join(" ",cmd.getArgs()));

        String className = cmd.getCls().replaceAll("\\.","/");
        ClassFile classFile = loadClass(className,classPath);
        printClassInfo(classFile);
//        byte[] data = classPath.readClass(className);
//        System.out.println("class data"+ Arrays.toString(data));
    }
    private void printClassInfo(ClassFile classFile)
    {
        System.out.println("version:"+classFile.getMajorVersion()+"."+classFile.getMinorVersion());
        System.out.println("constantCount:"+classFile.getConstantPool().getConstantPoolCount());
//        System.out.println("classFile.getMajorVersion: " + classFile.getMajorVersion());
//        System.out.println("classFile.getMinorVersion: " + classFile.getMinorVersion());
        System.out.println("classFile.getAccessFlags:0x"+Integer.toHexString(classFile.getAccessFlags()));
        System.out.println("classFile.getClassName: " + classFile.getClassName());
        System.out.println("classFile.getSuperClassName: " + classFile.getSuperClassName());
        System.out.println("interface names:");
        classFile.getInterfaceNames();
        for (String name : classFile.getInterfaceNames()) {
            System.out.println(name);
        }
        System.out.println("---------------------");
        System.out.println("field count: " + classFile.getFields().length);
        for (MemberInfo name : classFile.getFields()) {
            System.out.println(name.getName());
        }
        System.out.println("---------------------");
        System.out.println("method count: " + classFile.getMethods().length);
        for (MemberInfo name : classFile.getMethods()) {
            System.out.println(name.getName() + ":" + name.getDescriptor());
        }
        System.out.println("---------------------");
        System.out.println("constantPool count: "+classFile.getConstantPool().getConstantPoolCount());
        System.out.println("---------------------");
        System.out.println("attribute count:"+classFile.getAttributes().length);
        for (AttributeInfo attribute:classFile.getAttributes()){
            System.out.println(attribute.getClass());
        }
    }

    private ClassFile loadClass(String className, ClassPath classPath) {
        byte[] data = classPath.readClass(className);
        ClassFile classFile = new ClassFile(data);
        return classFile;
    }
}
