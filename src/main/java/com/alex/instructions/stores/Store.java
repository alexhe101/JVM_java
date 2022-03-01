package com.alex.instructions.stores;

import com.alex.rtda.Frame;
import com.alex.rtda.heap.Object;

public class Store {
    public static void lstore(Frame frame,int index)
    {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index,val);
    }

    public static void astore(Frame frame, int index) {
        Object ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(index, ref);
    }

    public static void istore(Frame frame, int index) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }

    public static void fstore(Frame frame, int index) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }

    public static void dstote(Frame frame, int index) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }
}
