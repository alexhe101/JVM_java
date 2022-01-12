package com.alex.classpath;

import java.io.IOException;

/**
 * @program: jvm
 * @description: entry for path
 * @author: alex101
 * @create: 2022-01-12 18:39
 **/
public abstract class Entry {
    public static String pathListSeparator;
    public abstract byte[] readClass(String className) throws IOException;
    public Entry newEntry(String path)
    {
        if(path.contains(pathListSeparator))
        {
            return null;
        }
        if(path.endsWith("*"))
        {
            return null;
        }
        if(path.endsWith(".jar")||path.endsWith(".JAR")||
        path.endsWith(".zip")||path.endsWith((".ZIP")))
        {
            return null;
        }
        return new DirEntry(path);
    }

}
