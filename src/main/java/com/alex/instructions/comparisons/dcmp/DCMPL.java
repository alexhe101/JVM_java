package com.alex.instructions.comparisons.dcmp;


import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.rtda.Frame;
import com.alex.rtda.OperandStack;
/**
 * Author: zhangxin
 * Time: 2017/5/5 0005.
 * Desc:
 */
public class DCMPL extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        DCMP._dcmp(frame, false);
    }
}
