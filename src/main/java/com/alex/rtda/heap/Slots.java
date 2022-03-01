package com.alex.rtda.heap;

import com.alex.rtda.Slot;

/**
 * @author alex101
 */
public class Slots {
    private Slot[] slots;
    public Slots(int size)
    {
        slots = new Slot[size];
        for (int i = 0; i < size; i++) {
            slots[i] = new Slot();
        }
    }

    public void setInt(int index, int val) {
        slots[index].setNum(val);
    }

    public int getInt(int index) {
        return slots[index].getNum();
    }

    public void setFloat(int index, float val) {
        slots[index].setNum(Float.floatToIntBits(val));
    }

    public float getFloat(int index) {
        return Float.intBitsToFloat(slots[index].getNum());
    }

    public void setLong(int index, long val) {
        //先存低32位
        slots[index].setNum( (int) (val));
        //再存高32位
        slots[index + 1].setNum((int) (val >> 32));
    }

    public long getLong(int index) {
        int low = slots[index].getNum();
        long high = slots[index + 1].getNum();
        return ((high & 0x000000ffffffffL) << 32) | (low & 0x00000000ffffffffL);
    }

    public void setDouble(int index, double val) {
        long bits = Double.doubleToLongBits(val);
        setLong(index, bits);
    }

    public double getDouble(int index) {
        long bits = getLong(index);
        return Double.longBitsToDouble(bits);
    }

    public void setRef(int index, Object ref) {
        slots[index].setRef(ref);
    }

    public Object getRef(int index) {
        return slots[index].getRef();
    }

    public void setSlot(int index, Slot slot) {
        slots[index] = slot;
    }


}
