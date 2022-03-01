package com.alex.rtda.heap;

import java.util.HashMap;
import java.util.Map;

public class ClassNameHelper {
    public static HashMap<String, String> primitiveTypes;

    static {
        primitiveTypes = new HashMap<String, String>();
        primitiveTypes.put("void", "V");
        primitiveTypes.put("boolean", "Z");
        primitiveTypes.put("byte", "B");
        primitiveTypes.put("short", "S");
        primitiveTypes.put("int", "I");
        primitiveTypes.put("long", "J");
        primitiveTypes.put("char", "C");
        primitiveTypes.put("float", "F");
        primitiveTypes.put("double", "D");
    }

    public static String getArrayClassName(String className)
    {
        return "["+toDescriptor(className);
    }

    private static String toDescriptor(String className) {
        if(className.charAt(0)=='[')
        {
            return className;
        }
        if(primitiveTypes.containsKey(className))
        {
            return primitiveTypes.get(className);
        }
        return "L"+className+";";
    }

    public static String getComponentClassName(String name) {
        if(name.charAt(0)=='[')
        {
            String componentTypeDescriptor = name.substring(1);
            return toClassName(componentTypeDescriptor);
        }
        throw new RuntimeException("not array "+name);
    }

    private static String toClassName(String componentTypeDescriptor) {
        if(componentTypeDescriptor.charAt(0)=='[')
        {
            return componentTypeDescriptor;
        }
        if(componentTypeDescriptor.charAt(0)=='L')
        {
            return componentTypeDescriptor.substring(1, componentTypeDescriptor.length() - 1);
        }
        for (Map.Entry<String, String> entry : primitiveTypes.entrySet()) {
            if (entry.getValue().equals(componentTypeDescriptor)) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("invalid descriptor "+componentTypeDescriptor);
    }
}
