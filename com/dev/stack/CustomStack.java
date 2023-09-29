package com.dev.stack;

public class CustomStack {
    protected int[] data;
    private static final int DEFAULT_SIZE = 10;

    private int top = -1;

    public CustomStack(){
        this(DEFAULT_SIZE);
    }

    public CustomStack(int size){
        this.data = new int[size];
    }

    public boolean push(int item){
        if(isFull()){
            System.out.println("Stack Overflow");
            return false;
        }
        top++;
        data[top] = item;
        return true;
    }

    public int pop(){
        if(isEmpty()){
            System.out.println("Stack Underflow");
            return -1;
        }
        int item = data[top];
        top--;
        return item;
    }

    public int peek(){
        if(isFull()){
            System.out.println("Stack Overflow");
            return -1;
        }
        return data[top];
    }

    public boolean isFull() {
        return top == data.length - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }



}
