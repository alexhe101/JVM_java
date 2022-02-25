package com.alex.instructions.extended;

import com.alex.instructions.base.BytecodeReader;
import com.alex.instructions.base.Instruction;
import com.alex.instructions.loads.aload.ALOAD;
import com.alex.instructions.loads.dload.DLOAD;
import com.alex.instructions.loads.fload.FLOAD;
import com.alex.instructions.loads.iload.ILOAD;
import com.alex.instructions.loads.lload.LLOAD;
import com.alex.instructions.math.iinc.IINC;
import com.alex.instructions.stores.astore.ASTORE;
import com.alex.instructions.stores.dstore.DSTORE;
import com.alex.instructions.stores.fstore.FSTORE;
import com.alex.instructions.stores.istore.ISTORE;
import com.alex.instructions.stores.lstore.LSTORE;
import com.alex.rtda.Frame;

public class WIDE implements Instruction {
    private Instruction modifiedInstruction;


    @Override
    public void fetchOperands(BytecodeReader bytecodeReader) {
        int opcode = bytecodeReader.readUint8();
        switch (opcode)
        {
            case 0x15:
                ILOAD inst = new ILOAD();
                inst.setIndex(bytecodeReader.readUint16());
                modifiedInstruction = inst;
                break;
            case 0x16:
                LLOAD lload = new LLOAD();
                lload.setIndex(bytecodeReader.readUint16());
                modifiedInstruction = lload;
                break;
            case 0x17:
                FLOAD fload = new FLOAD();
                fload.setIndex(bytecodeReader.readUint16());
                modifiedInstruction = fload;
                break;
            case 0x18:
                DLOAD dload = new DLOAD();
                dload.setIndex(bytecodeReader.readUint16());
                modifiedInstruction = dload;
                break;
            case 0x19:
                ALOAD aload = new ALOAD();
                aload.setIndex(bytecodeReader.readUint16());
                modifiedInstruction = aload;
                break;
            case 0x36:
                ISTORE istore = new ISTORE();
                istore.setIndex(bytecodeReader.readUint16());
                modifiedInstruction = istore;
                break;
            case 0x37:
                LSTORE lstore = new LSTORE();
                lstore.setIndex(bytecodeReader.readUint16());
                modifiedInstruction = lstore;
                break;
            case 0x38:
                FSTORE fstore=new FSTORE();
                fstore.setIndex(bytecodeReader.readUint16());
                modifiedInstruction = fstore;
                break;
            case 0x39:
                DSTORE dstore = new DSTORE();
                dstore.setIndex(bytecodeReader.readUint16());
                modifiedInstruction = dstore;
                break;
            case 0x3a:
                ASTORE astore = new ASTORE();
                astore.setIndex(bytecodeReader.readUint16());
                modifiedInstruction= astore;
                break;
            case 0x84:
                IINC innc = new IINC();
                innc.setIndex(bytecodeReader.readUint16());
                innc.setOffset(bytecodeReader.readInt16());
                modifiedInstruction = innc;
                break;
            case 0xa9:
                throw new RuntimeException("Unsupported opcode: 0xa9!");

        }
    }

    @Override
    public void execute(Frame frame) {
        modifiedInstruction.execute(frame);
    }
}
