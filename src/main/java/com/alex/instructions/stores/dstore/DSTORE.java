package com.alex.instructions.stores.dstore;


import com.alex.instructions.base.Index8Instruction;
import com.alex.instructions.stores.Store;
import com.alex.rtda.Frame;

public class DSTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        Store.dstote(frame,index);
    }
}
