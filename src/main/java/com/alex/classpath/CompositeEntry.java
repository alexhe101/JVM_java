package com.alex.classpath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: jvm
 * @description: composition pattern
 * @author: alex101
 * @create: 2022-01-26 10:25
 **/
public class CompositeEntry extends Entry{
    private String pathList;
    List<Entry> compositeEntry;

    public CompositeEntry(String pathList) {
        this.pathList = pathList;
        compositeEntry = new ArrayList<Entry>();
        String[] paths = pathList.split(Entry.pathListSeparator);
        for(String path:paths)
        {
            Entry entry = Entry.newEntry(path);
            compositeEntry.add(entry);
        }
    }

    @Override
    public byte[] readClass(String className)  {
        byte[] data;
        for(Entry entry:compositeEntry)
        {
            try {
                data = entry.readClass(className);
                if (data != null)
                {
                    return data;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String[] strings = new String[compositeEntry.size()];
        for(int i=0;i<compositeEntry.size();i++)
        {
            strings[i] = compositeEntry.get(i).toString();
        }
        return String.join(Entry.pathListSeparator,strings);
    }
}
