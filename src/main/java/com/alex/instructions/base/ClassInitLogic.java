package com.alex.instructions.base;

import com.alex.rtda.Frame;
import com.alex.rtda.Thread;
import com.alex.rtda.heap.Clazz;
import com.alex.rtda.heap.Method;

public class ClassInitLogic {
    public static void initClass(Thread thread, Clazz clazz)
    {
        clazz.startInit();
        scheduleClinit(thread,clazz);
        initSuperClass(thread,clazz);
    }

    private static void initSuperClass(Thread thread, Clazz clazz) {
        if(!clazz.isInterface())
        {
            Clazz superClazz = clazz.getSuperClass();
            if(superClazz!=null&&!superClazz.isInitStarted())
            {
                initClass(thread,superClazz);
            }
        }
    }

    private static void scheduleClinit(Thread thread, Clazz clazz) {
        Method clinit = clazz.getClinitMethod();
        if(clinit!=null)
        {
            Frame frame = thread.newFrame(clinit);
            thread.pushFrame(frame);
        }
    }
}
