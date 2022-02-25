package com.alex.instructions.stores.istore;


import com.alex.instructions.base.Index8Instruction;
import com.alex.instructions.stores.Store;
import com.alex.rtda.Frame;


public class ISTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        Store.istore(frame,index);
    }
}
