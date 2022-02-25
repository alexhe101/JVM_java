package com.alex.instructions.stores.fstore;


import com.alex.instructions.base.Index8Instruction;
import com.alex.instructions.stores.Store;
import com.alex.rtda.Frame;

public class FSTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        Store.fstore(frame,index);
    }
}
