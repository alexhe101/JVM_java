package com.alex.instructions.stores.fstore;


import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.stores.Store;
import com.alex.rtda.Frame;

public class FSTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Store.fstore(frame,2);
    }
}
