package com.alex.instructions.stores.astore;


import com.alex.instructions.base.Index8Instruction;
import com.alex.instructions.stores.Store;
import com.alex.rtda.Frame;

public class ASTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        Store.astore(frame,index);
    }
}
