package com.alex.instructions.loads;

import com.alex.rtda.Frame;
import com.alex.rtda.heap.Object;

public class Load {
    public static void iload(Frame frame,int index)
    {
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }

    public static void fload(Frame frame,int index)
    {
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }

    public static void dload(Frame frame,int index)
    {
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }

    public static void aload(Frame frame,int index)
    {
        Object ref = frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(ref);
    }

    public static void lload(Frame frame,int index)
    {
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }

    public static void checkNotNull(Object arrRef) {
        if(arrRef==null)
        {
            throw new NullPointerException();
        }
    }

    public static void checkIndex(int arrayLen, int index) {
        if(index<0||index>=arrayLen)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
