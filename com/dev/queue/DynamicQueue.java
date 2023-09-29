package com.dev.queue;

public class DynamicQueue extends CircularQueue{
    public DynamicQueue(){
        super();
    }
    public DynamicQueue(int size){
        super(size);
    }

    @Override
    public boolean insert(int item) {
        if (isFull()){
            int[] tempArr = new int[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                tempArr[i] = data[(front + i) % data.length];
            }
            front = 0;
            end = data.length;;
            data = tempArr;
        }
        return super.insert(item);
    }
}
