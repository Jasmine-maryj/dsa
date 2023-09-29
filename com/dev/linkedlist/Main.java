package com.dev.linkedlist;

import com.dev.linkedlist.DoublyLinkedList;

public class Main {
    public static void main(String[] args){
    DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    doublyLinkedList.insertFirst(10);
    doublyLinkedList.insertFirst(20);
    doublyLinkedList.insertFirst(30);
    doublyLinkedList.insertFirst(40);
    doublyLinkedList.insertLast(20);
    doublyLinkedList.display();
    }
}
