package com.alex.utils;

import java.io.File;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-26 11:31
 **/
public class FileUtil {
    public static boolean exists(String path)
    {
        File file = new File(path);
        return file.exists();
    }
}
