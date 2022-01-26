package com.alex.classpath;

import com.alex.utils.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-12 19:02
 **/
public class ZipJarEntry extends Entry{

    private String absPath;
    private String zipName;
    @Override
    public byte[] readClass(String className) throws IOException {
        File file = new File(absPath);
        ZipFile zipFile = new ZipFile(file);
        ZipEntry zipEntry;
        if(zipFile.getName().endsWith(".jar")){
            zipEntry = zipFile.getEntry(className);
        }else{
            zipEntry = zipFile.getEntry(zipName+"/"+className);
        }
        if(zipEntry==null){
            return null;
        }
        byte[] tmp = IOUtils.readFromInputStream(zipFile.getInputStream(zipEntry));
        zipFile.close();
        return tmp;
    }

    @Override
    public String toString() {
        return absPath;
    }

    public ZipJarEntry(String path) {
        File file = new File(path);
        if(file.exists()){
            this.absPath = file.getAbsolutePath();
            this.zipName  = file.getName();
            this.zipName = zipName.substring(0,zipName.length()-4);
        }

    }
}
