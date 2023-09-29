package com.dev.tree.questions;

import java.util.List;
import java.util.ListIterator;

import javax.swing.tree.TreeNode;

import java.util.ArrayList;


public class PathSum {

    private static class Node {
        private int value;
        Node left;
        Node right;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public List<List<Integer>> binaryTreePaths(Node root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        helperPaths(root, sum, path, paths);
        return paths;
    }

    void helperPaths(Node node, int sum, List<Integer> path, List<List<Integer>> paths){
        if(node == null){
            return;
        }
        path.add(node.value);

        if(node.value == sum && node.left == null && node.right == null){
            paths.add(new ArrayList<>(path));
        }else{
            helperPaths(node.left, sum - node.value, path, paths);
            helperPaths(node.right, sum - node.value, path, paths);
        }

        path.remove(path.size() - 1);

    }

    public int countPaths(Node node, int sum){
        if(node == null){
            return 0;
        }
        List<Integer> paths = new ArrayList<>();

        return helperCountPaths(node, sum, paths);
    }

    public int helperCountPaths(Node node, int sum, List<Integer> paths){
        if(node == null){
            return 0;
        }

        paths.add(node.value);
        int count = 0;
        int s = 0;
        ListIterator<Integer> iterator = paths.listIterator(paths.size());
        while(iterator.hasPrevious()){
            s += iterator.previous();
            
            if(s == sum){
                count++;
            }
        }

        count += helperCountPaths(node.left, sum, paths) + helperCountPaths(node.right, sum, paths);

        //backtrack
        paths.remove(paths.size() - 1);
        return count;
    }

    public boolean isPathExists(Node node, int[] arr){
        if(node == null){
            return arr.length == 0;
        }

        return helper(node, arr, 0);
    }

    public boolean helper(Node node, int[] arr, int index){
        if(node == null){
            return false;
        }

        if(index >= arr.length || node.value != arr[index]){
            return false;
        }

        if(node.left == null && node.right == null && index == arr.length - 1){
            return true;
        }

        return helper(node.left, arr, index + 1) || helper(node.right, arr, index + 1);
    }

    //max Path Sum
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(Node root) {
        helper(root);
        return ans;
    }

    int helper(Node node){
        if(node == null){
            return 0;
        }
        int left = helper(node.left);
        int right = helper(node.right);

        left = Math.max(left, 0);
        right = Math.max(0, right);

        int max = left + right + node.value;

        ans = Math.max(ans, max);

        return Math.max(left, right) + node.value;
    }

    public int sumNumbers(Node root) {
        return helper(root, 0);
    }

    int helper(Node node, int sum){
        if(node == null){
            return 0;
        }

        sum  = sum * 10 + node.value;

        if(node.left == null && node.right == null){
            return sum;
        }
        return helper(node.left, sum) + helper(node.right, sum);
    }

    public boolean hasPathSum(Node root, int targetSum) {
        if(root == null){
            return false;
        }

        //leaf node
        if(root.value == targetSum && root.left == null && root.right == null){
            return true;
        }

        return hasPathSum(root.left, targetSum - root.value) || hasPathSum(root.right, targetSum - root.value);

    }
    
}
