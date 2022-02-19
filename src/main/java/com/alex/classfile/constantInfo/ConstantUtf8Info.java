package com.alex.classfile.constantInfo;

import com.alex.classfile.ClassReader;
import com.alex.classfile.ConstantInfo;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @program: jvm
 * @description:
 * @author: alex101
 * @create: 2022-01-27 16:35
 **/
public class ConstantUtf8Info extends ConstantInfo {
    private String string;

    @Override
    public void readInfo(ClassReader classReader) {
        int length = classReader.readUint16();
        if(length==0)
        {
            string="";
            return;
        }
        byte []bytes = classReader.readBytes(length);

//        InputStream is = new ByteArrayInputStream(bytes);
//        DataInputStream dataInputStream = new DataInputStream(is);
        try {
            string = new String(bytes,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getString() {
        return string;
    }
}
