package com.alex.rtda;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;

public class Stack {
    private int maxSize;
    private int size;
    private Frame top;

    public Stack(int size) {
        maxSize = size;
    }

    public void push(Frame frame)
    {
        if(size<=maxSize)
        {
            size++;
            if(top!=null)
            {
                frame.setLower(top);
            }
            top = frame;
        }else {
            throw  new StackOverflowError();
        }

    }

    public Frame pop()
    {
        if(top==null)
        {
            throw new EmptyStackException();
        }
        Frame tmp = top;
        top = tmp.getLower();
        tmp.setLower(null);
        size--;
        return tmp;

    }

    public Frame top()
    {
        if(top==null)
        {
            throw new EmptyStackException();
        }
        return top;
    }
}
