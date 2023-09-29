package com.dev.linkedlist;

public class LL {

    private Node head;
    private Node tail;

    private int size;

    public LL(){
        this.size = 0;
    }

    public void removeDuplicates(){
        Node temp = head;
        while(temp.next != null && temp != null){
            if(temp.val == temp.next.val){
                temp.next = temp.next.next;
            }else{
                temp = temp.next;
            }
        }

    }

    public void insertRecursion(int value, int index){
        head = insertRec(value, index, head);
    }

    private Node insertRec(int value, int index, Node node){
        if(index == 0){
            Node newNode = new Node(value, node);
            size++;
            return newNode;
        }

        node.next = insertRec(value, index - 1, node.next); //gotcha
        return node;
    }

    public void insertFirst(int val){
        Node node = new Node(val);
        node.next = head;
        head = node;

        if(tail == null){
            tail = head;
        }
        size +=1;
    }

    public void display(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.print(" END ");
    }

    public void insertLast(int val){
        if(tail == null){
            insertFirst(val);
            return;
        }
        Node node = new Node(val);
        tail.next = node;
        tail = node;
        size += 1;
    }

    public void insertLastHeadOnly(int val){
        Node temp = head;
        Node node = new Node(val);
        while(temp.next != null){
          temp = temp.next;
        }
        temp.next = node;
        size += 1;
    }

    public void reverseRecursion(Node node){
        if(node == tail){
            head = tail;
            return;
        }

        reverseRecursion(node.next);

        tail.next = node;
        tail = node;
        tail.next = null;

    }

    //in place reversal
    public Node reverse(Node node){
        if(size < 2){
            return head;
        }
        Node prev = null;
        Node present = head;

        while(present != null){
            Node next = present.next;
            present.next  = prev;
            prev = present;
            present = next;
            if(next != null) {
                next = next.next;
            }
        }
//        head = prev;
        return prev;
    }

    public Node reverseBetween(Node head, int left, int right){
        if(left == right){
            return head;
        }

        Node prev = null;
        Node current = head;

        for(int i = 0; current != null && i < left - 1; i++){
            prev = current;
            current = current.next;
        }

        Node last = prev;
        Node newEnd = current;

        Node next = current.next;
        for(int i = 0; current != null && i< right - left + 1; i++){
            current.next = prev;
            prev = current;
            current = next;
            if(next != null){
                next = next.next;
            }
        }

        if(last != null){
            last.next = prev;
        }else {
            head = prev;
        }

        newEnd.next = current;

        return head;
    }

    public boolean palindromeLinkedList(Node head){
        Node mid = middleLL(head);
        Node headSecond = reverse(mid);
        Node reReverseHeadSecond = headSecond;

        while(head != null && headSecond != null){
            if(head.val != headSecond.val){
                break;
            }
            head = head.next;
            headSecond = headSecond.next;

        }
        if(head == null || headSecond == null){
            return true;
        }

        reverse(reReverseHeadSecond);

        return false;
    }

    public boolean hasCycle(Node head){
        Node fast = head;
        Node slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    public int lengthCycle(Node head){
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                Node temp = slow;
                int length = 0;
                do{
                    temp = temp.next;
                    length++;
                }while (temp != slow);
                return length;
            }
        }
        return 0;
    }

    public Node cycleStartNode(Node head){
        int length = 0;
        Node fast = head;
        Node slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                length = lengthCycle(slow);
                break;
            }
        }

        if(length == 0){
            return null;
        }

        Node first = head;
        Node second = head;
        while(length > 0){
            second = second.next;
            length--;
        }

        while(first != second){
            first = first.next;
            second = second.next;
        }
        return second;
    }

    public boolean isHappy(int n){
        int slow = n;
        int fast = n;

        do{
            slow = findSquare(slow);
            fast = findSquare(findSquare(fast));
        }while (fast != slow);

        return slow == 1;
    }

    private int findSquare(int number){
        int ans = 0;
        while(number > 0){
            int rem = number % 10;
            ans += rem * rem;
            number = number / 10;
        }
        return ans;
    }

    public Node middleLL(Node head){
        Node slow = head;
        Node fast = head;

        if(head == null){
            return null;
        }

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public Node sortList(Node head){
        if (head == null || head.next == null) {
            return head;
        }

        Node middle = middleLL(head);
        Node left = sortList(head);
        Node right = sortList(middle);

        return merge(left, right);
    }


    public Node merge(Node list1, Node list2){
        Node dummy = new Node(0);
        Node current = dummy;

        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                list1.next = merge(list1.next, list2);
                list1 = list1.next;
            }else{
                list2.next = merge(list1, list2.next);
                list2 = list2.next;
            }
            current = current.next;
        }
        current.next = list1 != null ? list1 : list2;

        return dummy.next;
    }

    public void sortWithBubbleSort(Node head){
        sortWithBubbleSort(size - 1, 0);
    }

    private void sortWithBubbleSort(int row, int col) {
        if(row == 0){
            return;
        }
        if(col < row){
            Node first = getIndex(col);
            Node second = getIndex(col + 1);

            if(first.val > second.val){
                //swap
                if(first == head){
                    head = second;
                    first.next = second.next;
                    second.next = first;
                } else if (second == tail) {
                    Node prev = getIndex(col - 1);
                    prev.next = second;
                    tail = first;
                    first.next = null;
                    second.next = tail;
                }else{
                    Node prev = getIndex(col - 1);
                    prev.next = second;
                    first.next = second.next;
                    second.next = first;
                }
            }
            sortWithBubbleSort(row, col + 1);
        }else{
            sortWithBubbleSort(row - 1, 0);
        }
    }

    public LL merge(LL first, LL second){
        Node firstHead = first.head;
        Node secondHead = second.head;

        LL ans = new LL();
        while(firstHead != null && secondHead != null){
            if(firstHead.val < secondHead.val){
                ans.insertLast(firstHead.val);
                firstHead = firstHead.next;
            }else{
                ans.insertLast(secondHead.val);
                secondHead = secondHead.next;
            }
        }
        while(firstHead != null){
            ans.insertLast(firstHead.val);
            firstHead = firstHead.next;
        }
        while (secondHead != null){
            ans.insertLast(secondHead.val);
            secondHead = secondHead.next;
        }
        return ans;
    }

    public void reorderList(Node head){
        if(head == null || head.next == null){
            return;
        }
        Node mid = middleLL(head);

        Node headSecond = reverse(mid);
        Node headFirst = head;

        //reorder
        while(headFirst != null && headSecond != null){
            Node temp = headFirst.next;
            headFirst.next = headSecond;
            headFirst = temp;

            temp = headSecond.next;
            headSecond.next = headFirst;
            headSecond = temp;
        }

        if(headFirst != null){
            headFirst.next = null;
        }


    }

    public void insert(int val, int index){
        if(head == null){
            insertFirst(val);
            return;
        }
        if(index == size){
            insertLast(val);
            return;
        }

        Node temp = head;

        for(int i = 1; i < index; i++){
            temp = temp.next;
        }

        Node node = new Node(val, temp.next);
        temp.next = node;
    
        size += 1;
    }

    public int deleteFirst(){
        int val = head.val;
        head = head.next;

        if(head == null){
            tail = null;
        }
        size -= 1;
        return val;
    }

    public int deleteLast(){
        if(size <= 1){
            return deleteFirst();
        }

        Node secondLast = getIndex(size - 2);
        int val = tail.val;
        tail = secondLast;
        tail.next = null;
        return val;
    }

    public int delete(int index){
        if(index == 0){
            return deleteFirst();
        }
        if(index == size-1){
            return deleteLast();
        }
        Node node = getIndex(index - 1);
        int val = node.next.val;
        node.next = node.next.next;

        return val;
    }

    public Node findNode(int value){
        Node node = head;
        while(node != null){
            if(node.val == value){
                return node;
            }
            node = node.next;
        }

        return null;
    }

    public Node getIndex(int index){
        Node node = head;
        for(int i = 0; i < index; i++){
            node = node.next;
        }
        return node;
    }


    private class Node{
        private int val;
        private Node next;
        
        public Node(int val){
            this.val = val;
        }

        public Node(int val, Node next){
            this.val = val;
            this.next = next;
        }
    }
}
