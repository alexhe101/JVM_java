package com.alex.instructions.stores.istore;


import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.stores.Store;
import com.alex.rtda.Frame;


public class ISTORE_1 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Store.istore(frame,1);
    }
}
