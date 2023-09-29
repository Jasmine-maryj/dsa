package com.dev.stack;

import java.util.ArrayList;

public class stack<T> {

    ArrayList<T> arrayList;

    private int top = -1;

    private int size;

    public stack(int size){
        this.size = size;
        this.arrayList = new ArrayList<T>(size);
    }

    public void push(T item){
        if(top + 1 == size){
            System.out.println("Stack Overflow");
        }else{
            top = top + 1;
            if(arrayList.size() > top){
                arrayList.set(top, item);
            }else {
                arrayList.add(item);
            }
        }
    }

    public void pop(){
        if(top == -1){
            System.out.println("Stack Underflow");
        }else {
            top--;
        }

    }

    public boolean empty(){
        if(top == -1){
            return true;
        }
        return false;
    }

    public int search(T item){
        for(int i = 0; i < top; i++){
            if(arrayList.get(i) == item){
                return i;
            }
        }
        return -1;
    }

    public T peak(){
        if(top == -1){
            System.out.println("Stack underflow");
            return null;
        }

        return arrayList.get(top);
    }

    public String display(){
        String ans = "";
        for(int i = 0; i < top; i++){
            ans += String.valueOf(arrayList.get(i)) + " -> ";
        }
        ans += String.valueOf(arrayList.get(top));

        return ans;
    }

    public static void main(String[] args){
        stack<Integer> numberStack = new stack<>(5);

        numberStack.push(1);
        numberStack.push(2);
        numberStack.push(3);
        numberStack.push(4);
        numberStack.push(5);

        numberStack.pop();
        numberStack.pop();
        int topEle = numberStack.peak();
        System.out.println("Stack top element is " + topEle);

        int found = numberStack.search(5);
        System.out.println("Number found at index: "+found);

        String stack = numberStack.display();
        System.out.println(stack);

        boolean isEmpty = numberStack.empty();
        System.out.println(isEmpty);
    }


}
