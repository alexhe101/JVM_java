package com.alex.classpath;

import com.alex.utils.IOUtils;

import java.io.File;
import java.io.IOException;

/**
 * @program: jvm
 * @description: simple entry
 * @author: alex101
 * @create: 2022-01-12 18:50
 **/
public class DirEntry extends Entry{
    private String absDir;

    public DirEntry(String path) {
        File dict = new File(path);
        if(dict.exists())
        {
            this.absDir = dict.getAbsolutePath();
        }
    }


    @Override
    public byte[] readClass(String className) throws IOException {
        File file = new File(absDir,className);
        return IOUtils.readFromByteFile(file);
    }

    @Override
    public String toString() {
        return absDir;
    }
}
