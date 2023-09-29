package com.dev.linkedlist;

public class LinkedList {

    private Node head;
    private Node tail;
    private int size;

    public void insertFirst(int value){
        Node node = new Node(value);
        node.next = head;
        head = node;
        if(tail == null){
            tail = head;
        }
        size +=1;
    }

    public void insertLastHeadOnly(int value){
        Node temp = head;
        while(temp != null){
            temp = temp.next;
        }
        Node node = new Node(value);
        temp.next = node;
        size += 1;
    }

    public void insertLast(int value){
        if(tail == null){
            insertFirst(value);
            return;
        }
        Node node = new Node(value);
        tail.next = node;
        node = tail;
        size += 1;
    }

    public void insert(int value, int index){
        if(index == 0){
            insertFirst(value);
            return;
        }
        if(index == size){
            insertLast(value);
            return;
        }

        Node temp = getIndex(index - 1);
        Node node = new Node(value, temp.next);
        temp.next = node;
        size += 1;
    }

    public int deleteFirst(int value){
        int val = head.value;
        head = head.next;

        if(head == null){
            tail = null;
        }
        size --;
        return val;
    }

    public int deleteLast(int value){
        if(size <= 1){
            return deleteFirst(value);
        }
        Node node = getIndex(size - 2);
        int val = tail.value;
        tail = node;
        tail.next = null;
        size--;
        return val;
    }

    public int delete(int value, int index){
        if(index == 0){
            return deleteFirst(value);
        }
        if(index == size-1){
            return deleteLast(value);
        }

        Node node = getIndex(index - 1);
        int val = node.next.value;
        node.next = node.next.next;
        return val;
    }

    public Node findNode(int value){
        Node node = head;
        while(node != null){
            if(node.value == value){
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public void display(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.value+" -> ");
            temp = temp.next;
        }
        System.out.print("END");
    }

    public Node getIndex(int index){
        Node temp = head;
        for(int i = 1; i < index; i++){
            temp = temp.next;
        }
        return temp;
    }




    public LinkedList(){
        int size = 0;
    }

    private class Node{
        private int value;
        private Node next;

        public Node(int value){
            this.value = value;
        }

        public Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
    }
}
