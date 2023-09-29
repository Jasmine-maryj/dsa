package com.dev.queue;

public class CustomQueue {
    private int[] data;

    private static final int DEFAULT_SIZE = 10;
    private int end = -1;

    public CustomQueue(){
        this(DEFAULT_SIZE);
    }

    public CustomQueue(int size){
        this.data = new int[size];
    }

    public boolean isFull(){
        return end == data.length;
    }

    public boolean isEmpty(){
        return end == 0;
    }

    public boolean insert(int item){
        if(isFull()){
            return false;
        }
        data[end] = item;
        end++;
        return true;
    }

    public int delete(int item){
        if(isEmpty()){
            return -1;
        }
        int deletedItem = data[0];
        for(int i = 1; i < end; i++){
            data[i - 1] = data[i];
        }
        end--;
        return deletedItem;
    }

    public int front(){
        if(isEmpty()){
            return -1;
        }
        return data[0];
    }

    public void display(){
        for (int i = 0; i < end; i++) {
            System.out.println(data[i] + " -> ");
        }
    }
}
