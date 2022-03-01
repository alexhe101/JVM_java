package com.alex.rtda;

import com.alex.rtda.heap.Method;

/**
 * @author alex101
 */
public class Frame {
    private Frame lower;
    private LocalVars localVars;
    private OperandStack operandStack;
    private Thread thread;
    private int nextPC;
    private Method method;


    public Method getMethod() {
        return method;
    }

    public int getNextPC() {
        return nextPC;
    }

    public Frame(Thread thread, int maxLocals, int maxStack) {
        this.thread = thread;
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }

    public Frame(Thread thread, Method method) {
        this.thread = thread;
        this.method = method;
        localVars = new LocalVars(method.getMaxLocals());
        operandStack = new OperandStack(method.getMaxStack());
    }

    public Frame(int maxLocals, int maxStack) {
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }



    public Frame getLower() {
        return lower;
    }

    public void setLower(Frame lower) {
        this.lower = lower;
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public Thread getThread() {
        return thread;
    }

    public void setNextPC(int nextPc) {
        this.nextPC = nextPc;
    }

    public void revertNextPc() {
        nextPC = thread.getPc();
    }
}
