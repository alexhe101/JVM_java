package com.alex.utils;

import java.io.*;

/**
 * @program: jvm
 * @description: io fuc
 * @author: alex101
 * @create: 2022-01-12 18:57
 **/
public class IOUtils {
    public static byte[] readFromByteFile(File file) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        ByteArrayOutputStream outputStream  = new ByteArrayOutputStream(1024);
        byte[] temp = new byte[1024];
        int size = 0;
        while ((size=inputStream.read(temp))!=-1){
            outputStream.write(temp,0,size);
        }
        inputStream.close();
        byte[] ret = outputStream.toByteArray();
        return ret;
    }
}
