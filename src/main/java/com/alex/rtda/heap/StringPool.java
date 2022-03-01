package com.alex.rtda.heap;

import java.util.HashMap;

public class StringPool {
    private static HashMap<String,Object> internedStrings=new HashMap<>();
    private static HashMap<Object,String> realInternedStrings= new HashMap<>();
    public static Object jString(ClassLoader loader,String str)
    {
        if(internedStrings.containsKey(str))
        {
            return internedStrings.get(str);
        }
        char[] chars = str.toCharArray();
        Object jChars = new Object(loader.loadClass("[C"),chars,null);
        Object jStr = loader.loadClass("java/lang/String").newObject();
        jStr.setRefVal("value","[C",jChars);
        internedStrings.put(str,jStr);
        return jStr;
    }

    public static String realString(Object jstr) {
        if (realInternedStrings.containsKey(jstr)) {
            return realInternedStrings.get(jstr);
        }

        Object ref = jstr.getRefVar("value", "[C");
        char[] chars = ref.getChars();
        String realStr = new String(chars);
        realInternedStrings.put(jstr, realStr);
        return realStr;

    }
}
