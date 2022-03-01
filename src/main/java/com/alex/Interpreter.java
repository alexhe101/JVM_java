package com.alex;

import com.alex.classfile.MemberInfo;
import com.alex.classfile.attribute.CodeAttribute;
import com.alex.instructions.InstructionFactory;
import com.alex.instructions.base.BytecodeReader;
import com.alex.instructions.base.Instruction;
import com.alex.rtda.Frame;
import com.alex.rtda.Thread;
import com.alex.rtda.heap.Method;

public class Interpreter {
    public static void interpret(Method method)
    {
//        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
//        int maxLocals= codeAttribute.getMaxLocals();
//        int maxStack = codeAttribute.getMaxStack();
//        byte[] bytecode=  codeAttribute.getCode();
        Thread thread = new Thread();
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);
        try {
            loop(thread,method.getCode());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("localvals:"+frame.getLocalVars());
            System.out.println(frame.getOperandStack());

        }
    }

    private static void loop(Thread thread,byte[] bytecode) throws Exception
    {
        Frame frame = thread.popFrame();
        BytecodeReader bytecodeReader = new BytecodeReader();
        while (true)
        {
            int pc = frame.getNextPC();
            thread.setPc(pc);
            bytecodeReader.reset(bytecode,pc);
            int opcode = bytecodeReader.readUint8();
            Instruction instruction = InstructionFactory.newInstruction(opcode);
            instruction.fetchOperands(bytecodeReader);
            frame.setNextPC(bytecodeReader.getPc());
            System.out.printf("pc:%d  inst:%s\n",pc,instruction.getClass().getSimpleName());
            instruction.execute(frame);
        }
    }


}
