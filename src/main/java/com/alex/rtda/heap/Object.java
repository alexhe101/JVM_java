package com.alex.rtda.heap;

public class Object {
    private Clazz clazz;
    private Slots fields;

    public Object(Clazz clazz) {
        this.clazz = clazz;
        fields = new Slots(clazz.getInstanceSlotCount());
    }

    public Slots getFields() {
        return fields;
    }

    public boolean isInstanceof(Clazz clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }

    public Clazz getClazz() {
        return clazz;
    }
}
