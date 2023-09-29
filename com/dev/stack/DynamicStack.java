package com.dev.stack;

public class DynamicStack extends CustomStack{

    public DynamicStack(){
        super();
    }
    public DynamicStack(int size){
        super(size);
    }

    @Override
    public boolean push(int item) {
        if(this.isFull()){
            int[] tempArr = new int[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                tempArr[i] = data[i];
            }
            data = tempArr;
        }

        return super.push(item);
    }
}
