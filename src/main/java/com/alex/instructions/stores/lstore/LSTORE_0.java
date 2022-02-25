package com.alex.instructions.stores.lstore;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.stores.Store;
import com.alex.rtda.Frame;

public class LSTORE_0 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Store.lstore(frame,0);
    }
}
