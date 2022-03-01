package com.alex.instructions.references;

import com.alex.instructions.base.BytecodeReader;
import com.alex.instructions.base.Index16Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.ClassRef;
import com.alex.rtda.heap.Clazz;
import com.alex.rtda.heap.Object;
import com.alex.rtda.heap.RuntimeConstantPool;
import javafx.scene.effect.Blend;

import java.util.Arrays;

public class MULTI_ANEW_ARRAY extends Index16Instruction {
    private int dimensions;
    @Override
    public void fetchOperands(BytecodeReader reader) {
        super.fetchOperands(reader);
        dimensions = reader.readUint8();
    }
    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool runtimeConstantPool=frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) runtimeConstantPool.getConstant(index).getValue();
        Clazz arrClass = classRef.resolvedClass();
        OperandStack stack = frame.getOperandStack();
        int[] counts = popAndCheckCounts(stack,dimensions);
        Object arr = newMultiDimensionalArray(counts,arrClass);
        stack.pushRef(arr);
    }

    private Object newMultiDimensionalArray(int[] counts, Clazz arrClass) {
        int count = counts[0];
        Object arr = arrClass.newArray(count);
        if(counts.length>1)
        {
            Object[] refs = arr.getRefs();
            for (int i=0;i<refs.length;i++)
            {
                refs[i] = newMultiDimensionalArray(Arrays.copyOfRange(counts,1,counts.length),arrClass.getComponentClass());
            }
        }
        return arr;
    }

    private int[] popAndCheckCounts(OperandStack stack, int dimensions) {

        int[] counts = new int[dimensions];
        for (int i = dimensions-1; i >=0 ; i--) {
            counts[i] = stack.popInt();
            if(counts[i]<0){
                throw new NegativeArraySizeException();
            }
        }
        return counts;
    }

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }
}
