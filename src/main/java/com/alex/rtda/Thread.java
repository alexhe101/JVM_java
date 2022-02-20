package com.alex.rtda;

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
    public Frame popFrame(Frame frame)
    {
        return stack.pop();
    }

    //todo:cur frame
    public Frame currentFrame()
    {
        return stack.top();
    }
}
