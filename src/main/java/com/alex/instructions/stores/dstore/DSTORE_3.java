package com.alex.instructions.stores.dstore;


import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.stores.Store;
import com.alex.rtda.Frame;

public class DSTORE_3 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Store.dstote(frame,3);
    }
}
