package com.alex.rtda.heap;

import com.alex.classfile.MemberInfo;
import com.alex.classfile.attribute.ConstantValueAttribute;

public class Field extends ClassMember{
    private int slotId;
    private int constValueIndex;

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public void setConstValueIndex(int constValueIndex) {
        this.constValueIndex = constValueIndex;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getSlotId() {
        return slotId;
    }

    public static Field[] newFields(Clazz clazz, MemberInfo[] cfFields)
    {
        Field[] fields = new Field[cfFields.length];
        for(int i=0;i< cfFields.length;i++)
        {
            fields[i] = new Field();
            fields[i].setClazz(clazz);
            fields[i].copyMemberInfo(cfFields[i]);
            fields[i].copyAttributes(cfFields[i]);
        }
        return fields;
    }

    private void copyAttributes(MemberInfo cfField) {
        ConstantValueAttribute constantValueAttribute = cfField.getConstantValueAttribute();
        if(constantValueAttribute!=null)
        {
            constValueIndex = constantValueAttribute.getConstantValueIndex();
        }
    }


    public boolean isLongOrDouble() {
        return getDescriptor().equals("J")||getDescriptor().equals("D");
    }


}
