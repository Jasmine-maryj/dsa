package com.dev.queue;

public class DeQue {

    private static final String QUEUE_UNDERFLOW = "Queue is Empty";
    private static final String QUEUE_OVERFLOW  = "Queue is Full";
    private static final int DEFAULT_SIZE = 10;
    private int front;
    private int rear;
    private int size;

    private int[] data;

    public DeQue(){
        this(DEFAULT_SIZE);
    }

    public DeQue(int size){
        this.data = new int[size];
    }

    public boolean isFull(){
        return ((front == 0 && rear == size - 1) || front == rear + 1);
    }

    public boolean isEmpty(){
        return (front == -1);
    }

    public void insertFront(int key){
        if(isFull()){
            System.out.println(QUEUE_OVERFLOW);
        }
        if(front == -1){
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = size - 1;
        }else {
            front = front - 1;
        }
        data[front] = key;
    }

    public void insertRear(int key){
        if(isFull()){
            System.out.println(QUEUE_OVERFLOW);
        }
        if(front == -1){
            front = 0;
            rear = 0;
        } else if (rear == size - 1) {
            rear = 0;
        }else {
            rear = rear + 1;
        }
        data[rear] = key;
    }

    public void deleteFront(int key){
        if(isEmpty()){
            System.out.println(QUEUE_UNDERFLOW);
        }
        if(front == rear){
            front = -1;
            rear = -1;
        } else if (front == size - 1) {
            front = 0;
        }else{
            front = front + 1;
        }
    }

    public void deleteRear(int key){
        if(isEmpty()){
            System.out.println(QUEUE_UNDERFLOW);
        }
        if(front == rear){
            front = -1;
            rear = -1;
        } else if (rear == 0) {
            rear = size - 1;
        }else {
            rear = rear - 1;
        }
    }

    public int getFront(){
        if(isEmpty()){
            System.out.println("Queue is Empty");
            return -1;
        }
        return data[front];
    }

    public int getRear(){
        if(isEmpty() || rear < 0){
            System.out.println("Queue is Empty");
            return -1;
        }
        return data[rear];
    }
}
