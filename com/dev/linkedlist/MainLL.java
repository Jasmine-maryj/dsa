package com.dev.linkedlist;

import com.dev.linkedlist.LL;

public class MainLL {
    public static void main(String[] args){
         LL linkedList = new LL();
         linkedList.insertFirst(30);
         linkedList.insertFirst(20);
         linkedList.insertFirst(20);
         linkedList.insertFirst(10);
         linkedList.insertFirst(10);

//         linkedList.insertLast(99);
//         linkedList.insertLastHeadOnly(100);
//         linkedList.insert(60, 4);
//         linkedList.insertRecursion(50, 2);


//         System.out.println(linkedList.deleteFirst());
//         System.out.println(linkedList.deleteLast());
//         System.out.println(linkedList.delete(3));

        linkedList.removeDuplicates();
         linkedList.display();

//        LinkedList ll = new LinkedList();
//        ll.insertFirst(10);
//        ll.insertFirst(20);
//        ll.insertFirst(30);
//        ll.insertFirst(40);
//
////        ll.insertLast(50);
////
////        ll.insert(100, 5);
////
////        ll.deleteFirst(40);
////
////        ll.deleteLast(100);
////
////        ll.delete(29, 1);




    }
}