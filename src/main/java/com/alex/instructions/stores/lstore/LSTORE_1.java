package com.alex.instructions.stores.lstore;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.stores.Store;
import com.alex.rtda.Frame;

public class LSTORE_1 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Store.lstore(frame,1);
    }
}
