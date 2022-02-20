package com.alex.rtda;

/**
 * @author alex101
 */
public class Frame {
    private Frame lower;
    private LocalVars localVars;
    private OperandStack operandStack;

    public Frame(int maxLocals,int maxStack) {
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
}
