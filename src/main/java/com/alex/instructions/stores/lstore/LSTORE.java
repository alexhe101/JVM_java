package com.alex.instructions.stores.lstore;

import com.alex.instructions.base.Index8Instruction;
import com.alex.instructions.stores.Store;
import com.alex.rtda.Frame;

public class LSTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        Store.lstore(frame,index);
    }
}
