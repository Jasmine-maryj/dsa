package com.dev.queue;

public class CircularQueue {
    public int[] data;
    private static final int DEFAULT_SIZE = 10;
    public int end = -1;
    public int front = -1;
    private int size = 0;

    public CircularQueue(){
        this(DEFAULT_SIZE);
    }
    public CircularQueue(int size){
        this.data = new int[size];
    }

    public boolean isFull(){
        return size == data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean insert(int item){
        if(isFull()){
            return false;
        }
        data[end] = item;
        end++;
        end = end % data.length;
        size++;
        return true;
    }

    public int delete(int item){
        if(isEmpty()){
            return -1;
        }
        int deletedItem = data[front];
        front = front % data.length;
        size--;
        return deletedItem;
    }

    public int front(){
        if(isEmpty()){
            return -1;
        }
        return data[front];
    }

    public int rear(){
        if(isEmpty()){
            return -1;
        }
        return data[end];
    }

    public void display(){
        if(isEmpty()){
            System.out.println("Empty");
            return;
        }
        int i = 0;
        do{
            System.out.println(data[i]);
            i++;
             i %= data.length;
        }while (i != end);
    }
}
