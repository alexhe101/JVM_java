package com.alex.instructions.base;

import com.alex.rtda.Frame;
import com.alex.rtda.Slot;
import com.alex.rtda.Thread;
import com.alex.rtda.heap.Method;

public class MethodInovkeLogic {
    public static void InvokeMethod(Frame invokerFrame, Method method)
    {
        Thread thread = invokerFrame.getThread();
        Frame newFrame = thread.newFrame(method);
        thread.pushFrame(newFrame);
        int argSlotCount = method.getArgSlotCount();
        if(argSlotCount>0)
        {
            for (int i = argSlotCount-1; i >=0 ; i--) {
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i,slot);

            }
        }
    }
}
