package com.alex.instructions.references;

import com.alex.instructions.base.BytecodeReader;
import com.alex.instructions.base.Index8Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.ClassLoader;
import com.alex.rtda.heap.Clazz;
import com.alex.rtda.heap.Object;

public class NEW_ARRAY extends Index8Instruction {
    private final int AT_BOOLEAN = 4;
    private final int AT_CHAR = 5;
    private final int AT_FLOAT = 6;
    private final int AT_DOUBLE = 7;
    private final int AT_BYTE = 8;
    private final int AT_SHORT = 9;
    private final int AT_INT = 10;
    private final int AT_LONG = 11;



    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if(count<0)
        {
            throw new NegativeArraySizeException();
        }
        ClassLoader classLoader = frame.getMethod().getClazz().getLoader();
        Clazz arrClass= getPrimitiveArrayClass(classLoader,index);
        Object arr = arrClass.newArray(count);
        stack.pushRef(arr);
    }

    private Clazz getPrimitiveArrayClass(ClassLoader loader, int index) {
        switch (index)
        {
            case AT_BOOLEAN:
                return loader.loadClass("[Z");
            case AT_BYTE:
                return loader.loadClass("[B");
            case AT_CHAR:
                return loader.loadClass("[C");
            case AT_SHORT:
                return loader.loadClass("[S");
            case AT_INT:
                return loader.loadClass("[I");
            case AT_LONG:
                return loader.loadClass("[J");
            case AT_FLOAT:
                return loader.loadClass("[F");
            case AT_DOUBLE:
                return loader.loadClass("[D");
            default:
                throw new RuntimeException("Invalid atype!");

        }
    }
}
