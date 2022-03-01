package com.alex.instructions.loads.xload;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.loads.Load;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
import com.alex.rtda.heap.Object;
public class BALOAD extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        //数组元素的索引值
        int index = operandStack.popInt();
        //数组对象的引用
        Object arrRef = operandStack.popRef();

        Load.checkNotNull(arrRef);
        //得到数组对象
        byte[] refs = arrRef.getBytes();
        Load.checkIndex(arrRef.getArrayLen(), index);
        //将数组的 index 的值压栈
        operandStack.pushInt(refs[index]);
    }
}
