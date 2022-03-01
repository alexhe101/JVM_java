package com.alex.rtda;

import com.alex.rtda.heap.Object;

import java.util.Arrays;

public class OperandStack {
    private int size;
    private Slot[] slots;
    public OperandStack(int maxStack) {
        slots = new Slot[maxStack];
    }
    public void pushInt(int val)
    {
        slots[size++] = new Slot(val);
    }
    public int popInt()
    {
        return slots[--size].getNum();
    }
    public void pushFloat(float val)
    {
        slots[size++] = new Slot(Float.floatToIntBits(val));
    }
    public float popFloat()
    {
        return Float.intBitsToFloat(slots[--size].getNum());
    }
    public void pushLong(long val)
    {
        //low
        slots[size++] = new Slot((int)val);
        //high
        slots[size++] = new Slot((int)(val>>32));
    }
    public long popLong()
    {
        size-=2;
        int low = (slots[size].getNum());
        int high = slots[size+1].getNum();
        return ((high & 0x000000ffffffffL) << 32) | (low & 0x00000000ffffffffL);
    }

    public void pushDouble(double val)
    {
        long bits = Double.doubleToLongBits(val);
        pushLong(bits);
    }

    public double popDouble()
    {
        long bits = popLong();
        return Double.longBitsToDouble(bits);
    }

    public void pushRef(Object ref)
    {
        slots[size++] = new Slot(ref);
    }
    public Object popRef()
    {
        --size;
        Object ref =slots[size].getRef();
//        slots[size].setRef(null);
        return ref;
    }

    public void pushSlot(Slot slot)
    {
        slots[size++] = slot;
    }

    public Slot popSlot()
    {
        return slots[--size];
    }

    @Override
    public String toString() {
        return "OperandStack{" +
                "size=" + size +
                ", slots=" + Arrays.toString(slots) +
                '}';
    }
}
