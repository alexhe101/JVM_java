package com.alex;

/**
 * @program: jvm
 * @description: jvm
 * @author: alex101
 * @create: 2022-01-12 17:06
 **/
public class JVM {
    public void startJVM(CMD cmd)
    {
        System.out.printf("classpath:%s class:%s args:[%s]\n",cmd.getCpOption(),cmd.getCls(),String.join(" ",cmd.getArgs()));
    }
}
