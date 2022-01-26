package com.alex;

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

        System.out.printf("classpath:%s class:%s args:[%s]\n",classPath.getUserClassPath(),cmd.getCls(),String.join(" ",cmd.getArgs()));

        String className = cmd.getCls().replaceAll("\\.","/");
        byte[] data = classPath.readClass(className);
        System.out.println("class data"+ Arrays.toString(data));
    }
}
