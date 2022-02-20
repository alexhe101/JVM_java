package com.alex.rtda;

public class LocalVars {
    private Slot[] slots;
    public LocalVars(int maxLocals) {
        slots = new Slot[maxLocals];
    }
    public void setInt(int index,int val)
    {
        slots[index] = new Slot();
        slots[index].setNum(val);
    }
    public int getInt(int index)
    {
        return slots[index].getNum();
    }

    public void setFloat(int index,float val)
    {
        slots[index] = new Slot();
        slots[index].setNum(Float.floatToIntBits(val));
    }

    public float getFloat(int index)
    {
        return Float.intBitsToFloat(slots[index].getNum());
    }

    public void setLong(int index,long val)
    {
        slots[index] = new Slot();
        slots[index+1] = new Slot();
        slots[index].setNum((int)val);
        slots[index+1].setNum((int)(val>>32));
    }

    public Long getLong(int index)
    {
        int low = slots[index].getNum();
        int high = slots[index+1].getNum();
        return ((high & 0x000000ffffffffL) << 32) | (low & 0x00000000ffffffffL);
    }

    public void setDouble(int index,double val)
    {
        long bits = Double.doubleToLongBits(val);
        setLong(index,bits);
    }

    public double getDouble(int index)
    {
        long bits = getLong(index);
        return Double.longBitsToDouble(bits);
    }

    public void setRef(int index,Object ref)
    {
        slots[index] = new Slot();
        slots[index].setRef(ref);
    }

    public Object getRef(int index)
    {
        return slots[index].getRef();
    }

}
