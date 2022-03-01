package com.alex.rtda;

import com.alex.rtda.heap.Method;

/**
 * @author alex101
 */
public class Thread {
    private int pc;
    private Stack stack;

    public Thread() {
        stack = new Stack(1024);
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void pushFrame(Frame frame)
    {
        stack.push(frame);
    }

    //todo:frame
    public Frame popFrame()
    {
        return stack.pop();
    }

    //todo:cur frame
    public Frame currentFrame()
    {
        return stack.top();
    }

    public Frame newFrame(Thread thread,int maxLocals,int maxStack)
    {
        return new Frame(this,maxLocals,maxStack);
    }

    public Frame newFrame(Method method) {
        return new Frame(this,method);
    }
}
