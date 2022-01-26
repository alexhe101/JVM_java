package com.alex.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @program: jvm
 * @description: io fuc
 * @author: alex101
 * @create: 2022-01-12 18:57
 **/
public class IOUtils {
    public static byte[] readFromByteFile(File file) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        return readFromInputStream(inputStream);
    }

    public static byte[] readFromInputStream(InputStream inputStream) throws IOException
    {
        ByteArrayOutputStream outputStream  = new ByteArrayOutputStream(1024);
        byte[] temp = new byte[1024];
        int size = 0;
        while ((size=inputStream.read(temp))!=-1){
            outputStream.write(temp,0,size);
        }
        inputStream.close();
        byte[] ret = outputStream.toByteArray();
        outputStream.close();
        return ret;
    }

}
