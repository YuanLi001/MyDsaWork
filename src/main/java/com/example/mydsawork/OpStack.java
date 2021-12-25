package com.example.mydsawork;

public class OpStack {
    String[] stack=new String[20];
    int top=0;
    int size=20;

    public OpStack() {
    }

    public OpStack(String[] stack, int top, int size) {
        this.stack = stack;
        this.top = top;
        this.size = size;
    }

}
