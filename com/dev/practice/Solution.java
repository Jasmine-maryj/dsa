package com.dev.practice;

import java.util.*;

public class Solution {

    public static void main(String[] args){
//        nums = [1,3,-1,-3,5,3,6,7], k = 3
//        int[] num = {1,3,-1,-3,5,3,6,7};
//        int k = 3;
//        System.out.println(Arrays.toString(maxSlidingWindow(num, k)));

//        int[][] matrix = {
//                {0, 0, 0},
//                {0, 1, 0},
//                {1, 1, 1}
//        };
//        int[][] ans = updateMatrix(matrix);
//        System.out.println(Arrays.deepToString(ans));

//        int[][] roads = { {0, 1}, {0, 3}, {1, 2}, {1, 3} };
//        int[][] roads = { {0,1},{0,3},{1,2},{1,3},{2,3},{2,4} };
//        System.out.println(maximalNetworkRank(5, roads));

//        System.out.println(convertToTitle(701));

//        String str = "aaab";
//        System.out.println(reorganizeString(str));
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }

    public static String reorganizeString(String s) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));
        maxHeap.addAll(freqMap.keySet());

        if (freqMap.get(maxHeap.peek()) > (s.length() + 1) / 2) {
            return "";
        }

        StringBuilder res = new StringBuilder();
        char[] result = new char[s.length()];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            for (int j = 0; j < freqMap.get(c); j++) {
                if (i >= s.length()) i = 1;
                result[i] = c;
                i += 2;
            }
        }

        return new String(result);
    }

        public static String convertToTitle(int n) {
            StringBuilder sb = new StringBuilder();
            while (n > 0) {
                sb.insert(0, (char) ('A' + (n - 1) % 26));
                n = (n - 1) / 26;
            }
            return sb.toString();
        }


    public static int maximalNetworkRank(int n, int[][] roads) {
        int[] degrees = new int[n];
        for (int[] road : roads) {
            degrees[road[0]]++;
            degrees[road[1]]++;
        }

        Set<Integer> uniqueDegrees = new HashSet<>();
        for (int degree : degrees) {
            uniqueDegrees.add(degree);
        }

        List<Integer> sortedDegrees = new ArrayList<>(uniqueDegrees);
        Collections.sort(sortedDegrees, Collections.reverseOrder());

        int maxDeg = sortedDegrees.get(0);
        int secondMaxDeg = sortedDegrees.size() > 1 ? sortedDegrees.get(1) : 0;

        int maxCount = 0;
        for (int degree : degrees) {
            if (degree == maxDeg) maxCount++;
        }

        int secondMaxCount = 0;
        for (int degree : degrees) {
            if (degree == secondMaxDeg) secondMaxCount++;
        }

        if (maxCount > 1) {
            int directlyConnected = 0;
            for (int[] road : roads) {
                if (degrees[road[0]] == maxDeg && degrees[road[1]] == maxDeg)
                    directlyConnected++;
            }
            int possibleConnections = maxCount * (maxCount - 1) / 2;
            return possibleConnections == directlyConnected ? 2 * maxDeg - 1 : 2 * maxDeg;
        }

        int directConnectionsToSecond = 0;
        for (int[] road : roads) {
            if ((degrees[road[0]] == maxDeg && degrees[road[1]] == secondMaxDeg) ||
                    (degrees[road[0]] == secondMaxDeg && degrees[road[1]] == maxDeg))
                directConnectionsToSecond++;
        }

        return secondMaxCount == directConnectionsToSecond ? maxDeg + secondMaxDeg - 1 : maxDeg + secondMaxDeg;
    }

    public  static int maxNetworkCityRack(int n, int[][] roads){
        int[] degrees = new int[5];
        for(int[] road:roads){
            degrees[road[0]]++;
            degrees[road[1]]++;
        }

        Set<Integer> uniqueDegrees = new HashSet<>();
        for (int degree: degrees){
            uniqueDegrees.add(degree);
        }

        List<Integer> sortedDegree = new ArrayList<>(uniqueDegrees);
        Collections.sort(sortedDegree, Collections.reverseOrder());

        int maxDeg = sortedDegree.get(0);
        int secondMaxDeg = sortedDegree.size() > 1 ? sortedDegree.get(1) : 0;

        int maxCount = 0;
        for (int degree: degrees){
            if(degree == maxDeg) maxCount++;
        }

        int secondMaxCount = 0;
        for(int degree: degrees){
            if(degree == secondMaxCount) secondMaxCount++;
        }

        if(maxCount > 1){
            int directConnected = 0;
            for(int[] road : roads){
                if(degrees[road[0]] == maxDeg && degrees[road[1]] == maxDeg){
                    directConnected++;
                }
            }
            int possibleConnections = maxCount * (maxCount - 1) / 2;
            return possibleConnections == directConnected ? 2 * maxDeg - 1 : 2 * maxDeg;
        }

        int directConnectionToSecond = 0;
        for (int[] road : roads){
            if((degrees[road[0]] == maxDeg && degrees[road[1]] == secondMaxDeg) || (degrees[road[1]] == secondMaxDeg && degrees[road[0]] == maxDeg)){
                directConnectionToSecond++;
            }
        }

        return secondMaxCount == directConnectionToSecond ? maxDeg + secondMaxDeg - 1 : maxDeg + secondMaxDeg;

    }


    public static int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();

        for (int j = 0, i = 0; j < nums.length; j++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.pollLast();
            }
            deque.offerLast(nums[j]);

            if (j - i + 1 == k) {
                result.add(deque.peekFirst());
                if (deque.peekFirst() == nums[i]) {
                    deque.pollFirst();
                }
                i++;
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

        public static int[][] updateMatrix(int[][] mat) {
            Queue<int[]> q = new LinkedList<>();
            int rows = mat.length;
            int cols = mat[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (mat[i][j] == 0) {
                        q.offer(new int[]{i, j});
                    } else {
                        mat[i][j] = -1;
                    }
                }
            }

            int[] dirX = {1, -1, 0, 0};
            int[] dirY = {0, 0, 1, -1};

            int length = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                length++;
                while (size-- > 0) {
                    int[] front = q.poll();
                    int r = front[0];
                    int c = front[1];
                    for (int i = 0; i < 4; i++) {
                        int newRow = r + dirX[i];
                        int newCol = c + dirY[i];
                        if (newRow < 0 || newCol < 0 || newRow >= rows || newCol >= cols || mat[newRow][newCol] >= 0) {
                            continue;
                        }
                        mat[newRow][newCol] = length;
                        q.offer(new int[]{newRow, newCol});
                    }
                }
            }

            return mat;

    }
}
