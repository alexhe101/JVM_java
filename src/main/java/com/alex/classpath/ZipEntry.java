package com.alex.classpath;

import java.io.File;
import java.io.IOException;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-12 19:02
 **/
public class ZipEntry extends Entry{

    private String absPath;
    @Override
    public byte[] readClass(String className) throws IOException {
        return new byte[0];
    }

    @Override
    public String toString() {
        return absPath;
    }

    public ZipEntry(String path) {
        File file = new File(path);
        if(file.exists()){
            this.absPath = file.getAbsolutePath();
        }

    }
}
