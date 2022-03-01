package com.alex.rtda.heap;

public class Object {
    private Clazz clazz;
    private java.lang.Object data;

    public Object(Clazz clazz) {
        this.clazz = clazz;
        data = new Slots(clazz.getInstanceSlotCount());
    }

    public Object(Clazz clazz, java.lang.Object data, java.lang.Object extra)
    {
        this.clazz = clazz;
        this.data = data;
    }

    public Slots getFields() {
        return (Slots) data;
    }

    public boolean isInstanceof(Clazz clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }

    public Clazz getClazz() {
        return clazz;
    }

    //为数组添加一些特有的方法：
    public byte[] getBytes() {
        return (byte[]) data;
    }

    public char[] getChars() {
        return (char[]) data;
    }

    public short[] getShorts() {
        return (short[]) data;
    }

    public int[] getInts() {
        return (int[]) data;
    }

    public long[] getLongs() {
        return (long[]) data;
    }

    public float[] getFloats() {
        return (float[]) data;
    }

    public double[] getDoubles() {
        return (double[]) data;
    }

    public Object[] getRefs() {
        return (Object[]) data;
    }

    public int getArrayLen() {
        switch (data.getClass().getSimpleName()) {
            case "byte[]":
                return getBytes().length;
            case "short[]":
                return getShorts().length;
            case "char[]":
                return getChars().length;
            case "int[]":
                return getInts().length;
            case "long[]":
                return getLongs().length;
            case "float[]":
                return getFloats().length;
            case "double[]":
                return getDoubles().length;
            case "Object[]":
                return getRefs().length;
            default:
                throw new RuntimeException("called length on a none array object!");
        }
    }
}
