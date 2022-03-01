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
    public static void interpret(Method method,boolean logInst)
    {

        Thread thread = new Thread();
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);
        try {
            loop(thread,logInst);
        } catch (Exception e) {
            System.out.println(e);
//            System.out.println("localvals:"+frame.getLocalVars());
//            System.out.println(frame.getOperandStack());
            logFrame(thread);

        }
    }

    private static void logFrame(Thread thread) {
        while (!thread.isStackEmpty())
        {
            Frame frame = thread.popFrame();
            Method method = frame.getMethod();
            String className = method.getClazz().getName();
            System.out.printf(">> pc:%4d %s.%s%s \n",frame.getNextPC(),className,method.getName(),method.getDescriptor());
        }
    }

    private static void loop(Thread thread,boolean logInst) throws Exception
    {
        BytecodeReader bytecodeReader = new BytecodeReader();
        Frame frame;
        while (true)
        {
            frame = thread.currentFrame();
            int pc = frame.getNextPC();
            thread.setPc(pc);
            bytecodeReader.reset(frame.getMethod().getCode(), pc);
            int opcode = bytecodeReader.readUint8();
            Instruction instruction = InstructionFactory.newInstruction(opcode);
            instruction.fetchOperands(bytecodeReader);
            frame.setNextPC(bytecodeReader.getPc());
            if(logInst)
            {
                logInstrustion(frame,instruction);
            }
            instruction.execute(frame);
            if(thread.isStackEmpty())
            {
                break;
            }

        }
    }

    private static void logInstrustion(Frame frame, Instruction instruction) {
        Method method = frame.getMethod();
        String clsName = method.getClazz().getName();
        String methodName = method.getName();
        int pc = frame.getThread().getPc();
        System.out.printf("%s.%s() #pc%2d %s\n",clsName,methodName,pc,instruction.getClass().getSimpleName());
    }


}
