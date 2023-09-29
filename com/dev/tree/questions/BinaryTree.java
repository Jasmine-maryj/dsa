package com.dev.tree.questions;

import java.util.*;

import javax.swing.tree.TreeNode;

import com.dev.stack.stack;

public class BinaryTree {

    public BinaryTree() {

    }

    private static class Node {
        private int value;
        Node left;
        Node right;
        Node next;

        Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

    }

    // public String serialize(TreeNode root) {
        
    // }

    // // Decodes your encoded data to tree.
    // public Node deserialize(String data) {
        
    // }

    public List<Integer> inorderTraversal(Node root) {
        List<Integer> result = new ArrayList<>();
        Stack<Node> s = new Stack<>();

        while(true){
            if(root != null){
                s.push(root);
                root = root.left;
            }else{
                if(s.empty()){
                    break;
                }
                root = s.peek();
                s.pop();
                result.add(root.value);
                root = root.right;
            }
        }
        return result;
    }

    public boolean isSubtree(Node T, Node S) {
        // add code here.
        if(S == null){
            return true;
        }
        if(T == null){
            return false;
        }

        if(areIdentical(T, S)){
            return true;
        }

        return isSubtree(T.left, S) || isSubtree(T.right, S);
    }

    public boolean areIdentical(Node t, Node s){
        if(t == null && s == null){
            return true;
        }
        if(t == null || s == null){
            return false;
        }

        return (t.value == s.value) && areIdentical(t.left,s.left) && areIdentical(t.right,s.right);
    }

    //inorder successor of bt
    public Node inorderSuccessor(Node root, Node n){
        if(n.right != null){
            return minimum(n.right);
        }

        Node p = n.parent;
        while(n.parent != null && n == p.right){
            n = p;
            p = p.parent;
        }

        return p;
    }

    private Node minimum(Node node) {
        while (node.left != null){
            node = node.left;
        }
        return node;
    }


    public Node buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0){
            return null;
        }

        int root = preorder[0];
        int index = 0;

        for(int i = 0; i < inorder.length; i++){
            if(inorder[i] == root){
                index = i;
            }
        }

        Node node = new Node(root);

        node.left = buildTree(Arrays.copyOfRange(preorder, 1, index + 1), Arrays.copyOfRange(inorder, 0, index));

        node.right = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length), Arrays.copyOfRange(inorder, index + 1, inorder.length));

        return node;
    }

    public int kthSmallest(Node root, int k) {
        return kthSmallestHelper(root, k).value;
    }

    static int count = 0;
    public static Node kthSmallestHelper(Node root, int k) {
        if(root == null){
            return null;
        }

        Node left = kthSmallestHelper(root.left, k);
        if(left != null){
            return left;
        }

        count++;

        if(count == k){
            return root;
        }

        return kthSmallestHelper(root.right, k);
        
    }

    public static Node lowestCommonAncestor(Node root, Node p, Node q) {
        if(root == null){
            return null;
        }

        if(root == p || root == q){
            return root;
        }

        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null){
            return root;
        }
        return left == null ? right : left;
    }

    public static boolean isValidBST(Node root) {
        return helper(root, null, null);
    }


    public static boolean helper(Node node, Integer low, Integer high){
        if(node == null){
            return true;
        }

        if(low != null && node.value <= low){
            return false;
        }
        if(high != null && node.value >= high){
            return false;
        }

        boolean leftTree = helper(node.left, low, node.value);
        boolean rightTree = helper(node.right, node.value, high);

        return leftTree && rightTree;
    }

    public static void flatten(Node root) {
        if(root == null){
            return;
        }

        Node cn = root;
        while(cn != null){
            if(cn.left != null){
                Node temp = cn.left;
                while(temp.right != null){
                    temp = temp.right;
                }
                temp.right = cn.right;
                cn.right = cn.left;
                cn.left = null;
            }
            cn = cn.right;
        }

    }

    public static int maxDepth(Node root) {
        if(root == null){
            return 0;
        }        

        int leftDep = maxDepth(root.left);
        int rightDep = maxDepth(root.right);

        int dep = Math.max(leftDep, rightDep) + 1;

        return dep;
    }

    public static Node invertTree(Node root) {
        if(root == null){
            return null;
        }

        Node left = invertTree(root.left);
        Node right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    static int diameter = 0;
    public static int diameterOfBinaryTree(Node root) {
        height(root);

        return diameter - 1;
    }

    public static int height(Node node){
        if(node == null){
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        int d = leftHeight + rightHeight + 1;
        diameter = Math.max(diameter, d);

        return Math.max(leftHeight, rightHeight) + 1;

    }

    public static boolean isSymmetric(Node root) {
        if(root == null){
            return false;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root.left);
        q.offer(root.right);

        while(!q.isEmpty()){
            Node left = q.poll();
            Node right = q.poll();

            if(left == null && right == null){
                continue;
            }
            if(left == null || right == null){
                return false;
            }

            if(left.value != right.value){
                return false;
            }
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
        }

        return true;
    }

    public static boolean isCousin(Node root, int x, int y){
        Node xx = findNode(root, x);
        Node yy = findNode(root, y);

        return (level(root, xx, 0) == level(root, yy, 0) && (!isSibling(root, xx, yy)));
    }

    public static Node findNode(Node node, int x){
        if(node == null){
            return null;
        }
        if(node.value == x){
            return node;
        }
        Node n = findNode(node.left, x);
        if(n != null){
            return n;
        }
        return findNode(node.right, x);
    }

    public static boolean isSibling(Node node, Node x, Node y){
        if(node == null){
            return false;
        }
        return (
        (node.left == x && node.right == y) || 
        (node.left == y && node.right == x) || 
        isSibling(node.left, x, y) || 
        isSibling(node.right, x, y)
        );
    }

    public static int level(Node node, Node x, int lev){
        if(node == null){
            return 0;
        }

        if(node == x){
            return lev;
        }
        int l = level(node.left, x, lev + 1);
        if(l != 0){
            return l;
        }
        return level(node.right, x, lev+1);
    }


    public static List<Integer> rightSideView(Node root){
        List<Integer> list = new ArrayList<>();

        if(root == null){
            return list;
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int level = q.size();
            for(int i = 0; i < level; i++){
                Node cn = q.poll();
                if(i == level - 1){
                    list.add(cn.value);
                }
                if(cn.left != null){
                    q.offer(cn.left);
                }
                if(cn.right != null){
                    q.offer(cn.right);
                }
            }
        }
        return list;
    }

    public static Node connect(Node root) {
        if(root == null){
            return null;
        }

        Node leftMost = root;

        while(leftMost.left != null){
            Node cn = leftMost;

            while(cn != null){
                if (cn.left != null) {
                    cn.left.next = cn.right;
                }
                
                if (cn.right != null && cn.next != null) {
                    cn.right.next = cn.next.left;
                }
                cn = cn.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }

    public static List<List<Integer>> levelOrderBottom(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int level = q.size();
            List<Integer> l = new ArrayList<>(level);

            for (int i = 0; i < level; i++) {
                Node cn = q.poll();
                l.add(cn.value);
                if (cn.left != null) {
                    q.offer(cn.left);
                }
                if (cn.right != null) {
                    q.offer(cn.right);
                }
            }
            result.add(0, l);
        }
        return result;
    }

    public static Node findSuccessorNode(Node root, int key) {
        if (root == null) {
            return null;
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node cn = q.poll();
            if (cn.left != null) {
                q.offer(cn.left);
            }
            if (cn.right != null) {
                q.offer(cn.right);
            }
            if (cn.value == key) {
                break;
            }
        }
        return q.peek();
    }

    public static List<List<Integer>> zigzagTraversal(Node root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<Node> dq = new LinkedList<>();
        dq.offer(root);

        boolean reverse = false;

        while (!dq.isEmpty()) {
            int level = dq.size();
            List<Integer> l = new ArrayList<>(level);

            for (int i = 0; i < level; i++) {
                if (!reverse) {
                    Node cn = dq.pollFirst();
                    l.add(cn.value);
                    if (cn.left != null) {
                        dq.addLast(cn.left);
                    }
                    if (cn.right != null) {
                        dq.addLast(cn.right);
                    }
                } else {
                    Node cn = dq.pollLast();
                    l.add(cn.value);
                    if (cn.right != null) {
                        dq.addFirst(cn.right);
                    }
                    if (cn.left != null) {
                        dq.addFirst(cn.left);
                    }
                }
            }
            reverse = !reverse;
            result.add(l);
        }
        return result;
    }

    public static List<Integer> sumLevel(Node root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<Node> q = new LinkedList<>();

        q.offer(root);

        while (!q.isEmpty()) {
            int avg = 0;
            int level = q.size();

            for (int i = 0; i < level; i++) {
                Node cn = q.poll();
                avg += cn.value;
                if (cn.left != null) {
                    q.offer(cn.left);
                }
                if (cn.right != null) {
                    q.offer(cn.right);
                }
            }
            result.add(avg);
        }
        return result;
    }

    public static List<Double> averageLevel(Node root) {
        List<Double> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<Node> nodeQueue = new LinkedList<>();

        nodeQueue.offer(root);

        while (!nodeQueue.isEmpty()) {

            int level = nodeQueue.size();
            double avg = 0;

            for (int i = 0; i < level; i++) {
                Node currentNode = nodeQueue.poll();
                avg += currentNode.value;
                if (currentNode.left != null) {
                    nodeQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    nodeQueue.offer(currentNode.right);
                }
            }
            avg = avg / level;
            result.add(avg);
        }

        return result;
    }

    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<Node> nodeQueue = new LinkedList<>();

        nodeQueue.offer(root);

        while (!nodeQueue.isEmpty()) {
            int level = nodeQueue.size();

            List<Integer> currentLevel = new ArrayList<>(level);
            for (int i = 0; i < level; i++) {
                Node currentNode = nodeQueue.poll();
                currentLevel.add(currentNode.value);
                if (currentNode.left != null) {
                    nodeQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    nodeQueue.offer(currentNode.right);
                }
            }
            result.add(currentLevel);
        }

        return result;
    }
}
