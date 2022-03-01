package com.alex.rtda;

import com.alex.rtda.heap.Object;

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

    @Override
    public String toString() {
        return "Slot{" +
                "num=" + num +
                ", ref=" + ref +
                '}';
    }
}
