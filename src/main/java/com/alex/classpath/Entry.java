package com.alex.classpath;

import java.io.IOException;

/**
 * @program: jvm
 * @description: entry for path
 * @author: alex101
 * @create: 2022-01-12 18:39
 **/
public abstract class Entry {
    public static String pathListSeparator=System.getProperty("os.name").contains("Windows") ? ";" : ":";


    /**
     * read binary from a class file
     * @param className
     * @return bytes of class
     * @throws IOException
     */
    public abstract byte[] readClass(String className) throws IOException;
    public static Entry newEntry(String path)
    {
        if(path.contains(pathListSeparator))
        {
            return new CompositeEntry(path);
        }
        if(path.endsWith("*"))
        {
            return new WildCardEntry(path);
        }
        if(path.endsWith(".jar")||path.endsWith(".JAR")||
        path.endsWith(".zip")||path.endsWith((".ZIP")))
        {
            return new ZipJarEntry(path);
        }
        return new DirEntry(path);
    }

}
