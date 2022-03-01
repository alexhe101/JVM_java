package com.alex.instructions.stores.xastore;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.stores.Store;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.Object;
public class DASTORE extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        //所要赋的值
        double val = operandStack.popDouble();
        //数组索引
        int index = operandStack.popInt();
        //数组对象的引用
        Object arrRef = operandStack.popRef();

        Store.checkNotNull(arrRef);
        //得到数组对象
        double[] refs = arrRef.getDoubles();
        Store.checkIndex(arrRef.getArrayLen(), index);
        //将数组的 index 的元素进行赋值
        refs[index] = val;
    }
}
