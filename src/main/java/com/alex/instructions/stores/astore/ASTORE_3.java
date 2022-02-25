package com.alex.instructions.stores.astore;

import com.alex.instructions.base.NoOperandsInstruction;
import com.alex.instructions.stores.Store;
import com.alex.rtda.Frame;

public class ASTORE_3 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Store.astore(frame,3);
    }
}
