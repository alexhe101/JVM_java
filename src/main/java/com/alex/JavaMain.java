package com.alex;

/**
 * @program: jvm
 * @description: main fuc
 * @author: alex101
 * @create: 2022-01-12 16:26
 **/
public class JavaMain {
    public static void main(String[] args) {
        CMD cmd = new CMD();
        cmd.processCMD(args);
        if(cmd.isVersionFlag()==true){
            System.out.println("version 0.0.1");
        }else if(cmd.isHelpFlag()==true||cmd.getCls()==null){
            cmd.printUsage(JavaMain.class.getName());
        }else{
            JVM jvm = new JVM();
            jvm.startJVM(cmd);
        }
    }
}
