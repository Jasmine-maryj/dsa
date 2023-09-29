package com.dev.tree.questions;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SD {    

    private static class Node {
        private int value;
        Node left;
        Node right;
        
        public Node(int value) {
            this.value = value;
        }
    }

    public List<String> serialize(Node node){
        List<String> list = new ArrayList<>();
        helper(node, list);
        return list;
    }

    void helper(Node node, List<String> list){
        if(node == null){
            list.add(null);
            return;
        }
        list.add(String.valueOf(node.value));

        helper(node.left, list);
        helper(node.right, list);
    }

    Node deserialize(List<String> list){
        Collections.reverse(list);
        Node node = helper2(list);
        return node;
    }

    Node helper2(List<String> list){
        String value = list.remove(list.size() - 1);

        if(value.charAt(0) == 'n'){
            return null;
        }
        Node node = new Node(Integer.parseInt(value));

        node.left = helper2(list);
        node.right = helper2(list);

        return node;
    }
}

