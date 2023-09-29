package com.dev.linkedlist;

public class DoublyLinkedList {

    private Node head;
    private int size;

    public DoublyLinkedList(){
        this.size = 0;
    }

    public void insertFirst(int value){
        Node node = new Node(value);
        node.next = head;
        node.prev = null;
        if(head != null) {
            head.prev = node;
        }
        head = node;
        size++;
    }

    public void insertLast(int value){
        if(head == null){
            insertFirst(value);
            return;
        }
        Node node = new Node(value);
        Node lastNode = getIndex(size);
        lastNode.next = node;
        node.next = null;
        node.prev = lastNode;
        size++;

    }


    public Node getIndex(int index){
        Node temp = head;
//        while(temp != null){
//            temp = temp.next;
//        }
        for(int i = 1; i < index; i++ ){
            temp = temp.next;
        }
        return temp;
    }

    public void display(){
        Node temp = head;
        Node node = null;
        while(temp != null){
            System.out.print(temp.value+" -> ");
            node = temp;
            temp = temp.next;
        }
        System.out.print("END");

        System.out.println();

        while(node != null){
            System.out.print(node.value+" -> ");
            node = node.prev;
        }
        System.out.print("START");
    }


    private class Node{
        private  int value;
        private Node next;
        private Node prev;

        public Node(int value){
            this.value = value;
        }

        public Node(int value, Node next){
            this.value = value;
            this.next = next;
        }

        public Node(int value, Node next, Node prev){
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
