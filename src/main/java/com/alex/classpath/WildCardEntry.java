package com.alex.classpath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: jvm
 * @description: end with "*"
 * @author: alex101
 * @create: 2022-01-26 10:48
 **/
public class WildCardEntry extends Entry{
    private CompositeEntry compositeEntry;
    private String path;

    public WildCardEntry(String path) {
        this.path = path;
        String baseDir = path.substring(0,path.length()-1);
        File dir = new File(baseDir);
        File[] files = dir.listFiles();
        List<String> strings = new ArrayList<String>();
        for(File file:files)
        {
            if(file.isFile()&&file.getName().endsWith(".jar")||file.getName().endsWith(".JAR"))
            {
                strings.add(file.getAbsolutePath());
            }
        }
        int size = strings.size();
        String []tmp = (String[]) strings.toArray(new String[size]);
        compositeEntry = new CompositeEntry(String.join(Entry.pathListSeparator,tmp));
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        return compositeEntry.readClass(className);
    }

    @Override
    public String toString() {
        return compositeEntry.toString();
    }
}
