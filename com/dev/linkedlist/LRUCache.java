package com.dev.linkedlist;

import java.util.HashMap;

public class LRUCache {
    public class Node{
        private int value;
        private int key;
        Node prev;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    int cap;

    HashMap<Integer, Node> integerNodeHashMap = new HashMap<>();

    public LRUCache(int capacity){
        this.cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public void addNode(Node node){
        Node temp = head.next;

        node.next = temp;
        node.prev = head;

        head.next = node;
        tail.prev = node;
    }

    public void deleteNode(Node node){
        Node prev_node = node.prev;
        Node next_node = node.next;

        prev_node.next = next_node;
        next_node.prev = prev_node;
    }

    public int get(int key){
        if(integerNodeHashMap.containsKey(key)){
            Node resNode = integerNodeHashMap.get(key);
            int ans = resNode.value;

            integerNodeHashMap.remove(key);
            deleteNode(resNode);

            addNode(resNode);
            integerNodeHashMap.put(key, head.next);
            return ans;
        }
        return -1;
    }

    public void put(int key, int value){
        if(integerNodeHashMap.containsKey(key)){
            Node resNode = integerNodeHashMap.get(key);
            integerNodeHashMap.remove(key);
            deleteNode(resNode);
        }

        if(integerNodeHashMap.size() == cap){
            integerNodeHashMap.remove(key, tail.prev.key);
            deleteNode(tail.prev);
        }

        addNode(new Node(key, value));
        integerNodeHashMap.put(key, head.next);
    }

    public static void main(String[] args){
//        [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
        LRUCache lruCache = new LRUCache(2);
        System.out.println(lruCache.get(2));
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
