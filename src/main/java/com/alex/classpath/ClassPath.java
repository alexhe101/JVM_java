package com.alex.classpath;

import com.alex.utils.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @program: jvm
 * @description: classPath
 * @author: alex101
 * @create: 2022-01-26 11:04
 **/
public class ClassPath {
    private Entry bootClassPath;//启动路径
    private Entry extClassPath;//扩展路径
    private Entry userClassPath;//用户路径

    public Entry getUserClassPath() {
        return userClassPath;
    }

    public ClassPath(String jreOption, String cpOption) {
        parseBootAndExtClassPath(jreOption);
        parseUserPath(cpOption);
    }

    private void parseUserPath(String cpOption)
    {
        if(cpOption==null||cpOption==""){
            cpOption = ".";
        }
        userClassPath = Entry.newEntry(cpOption);
    }

    private void parseBootAndExtClassPath(String jreOption)
    {
        String jreDir=  getJreDir(jreOption);
        //jre/lib/*
        String jreLibPath = jreDir+File.separator+"lib"+File.separator+"*";
        bootClassPath = new WildCardEntry(jreLibPath);

        //jre/lib/ext/*
        String jreExtPath = String.join(File.separator,new String[]{jreDir,"lib","ext","*"});
        extClassPath = new WildCardEntry(jreExtPath);
    }

    private String getJreDir(String jreOption)
    {
        if(jreOption!=null&&jreOption!=""){
            if(FileUtils.exists(jreOption))
            {
                return jreOption;
            }
        }
        if(FileUtils.exists("./jre")){
            return "./jre";
        }
        String jh = System.getenv("JAVA_HOME");
        if(jh!=""){
            return jh+File.separator+"jre";
        }
        throw new RuntimeException("can't find jreDir");
    }

    public byte[] readClass(String className)
    {
        className = className+".class";
        byte[] data;
        try {
            data = bootClassPath.readClass(className);
            if(data!=null) return data;
            data = extClassPath.readClass(className);
            if(data!=null) return data;
            data =userClassPath.readClass(className);
            if(data!=null) return data;


        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("can't find class");
    }

    @Override
    public String toString() {
        return userClassPath.toString();
    }
}
