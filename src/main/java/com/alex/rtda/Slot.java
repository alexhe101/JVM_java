package com.alex.rtda;

public class Slot {
    private int num;
    private Object ref;

    public Slot() {
    }

    public Slot(int num) {
        this.num = num;
    }

    public Slot(Object ref) {
        this.ref = ref;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Object getRef() {
        return ref;
    }

    public void setRef(Object ref) {
        this.ref = ref;
    }
}
