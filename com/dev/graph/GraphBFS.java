package com.dev.graph;

import java.util.ArrayList;
import java.util.Queue;

import com.dev.stack.stack;

import java.util.LinkedList;

public class GraphBFS {

    // Edge class
    static class Edge {
        int src;
        int dest;
        int weight;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static void main(String[] args) {
        int v = 7;

        ArrayList<Edge>[] graph = new ArrayList[v];

        boolean[] visited = new boolean[v];

        createGraph(graph);

        // for (int i = 0; i < v; i++) {
        // if (visited[i] == false) {
        // bfs(graph, v, visited, i);
        // }
        // }

        // for(int i = 0; i < v; i++){
        //     if(visited[i] == false){
        //         dfs(graph, i, visited);
        //     }
        // }
        // dfs(graph, 0, visited);

        int src = 0;
        int dest = 5;
        allPathsFromSrcToDest(graph, new boolean[v], "0", src, dest);

        System.out.println();
    }

    public static boolean cycleDetectionDFS(ArrayList<Edge>[] graph, boolean[] visited, boolean[] recStack, int cur){
        visited[cur] = true;
        recStack[cur] = true;

        for(int i = 0; i < graph[cur].size(); i++){
            Edge e = graph[cur].get(i);
            if(recStack[e.dest]){
                return true;
            }
            if(!visited[cur]){
                if(cycleDetectionDFS(graph, visited, recStack, e.dest)){
                    return true;
                }
            }
        }
        recStack[cur] = false;

        return false;
    }

    public static void allPathsFromSrcToDest(ArrayList<Edge>[] graph, boolean[] visited, String path, int curr, int target){
        if(curr == target){
            System.out.println(path);
            return;
        }

        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(visited[e.dest] == false){
                visited[curr] = true;
                allPathsFromSrcToDest(graph, visited, path+e.dest, e.dest, target);
                visited[curr] = false;
            }
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));

        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));

        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));

        graph[6].add(new Edge(6, 5));
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] visited) {
        System.out.print(curr + " ");
        visited[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!visited[e.dest]) {
                dfs(graph, e.dest, visited);
            }
        }
    }

    public static void bfs(ArrayList<Edge>[] graph, int v, boolean[] visited, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.remove();
            if (!visited[cur]) {
                System.out.print(cur + " ");
                visited[cur] = true;

                for (int i = 0; i < graph[cur].size(); i++) {
                    Edge e = graph[cur].get(i);
                    q.add(e.dest);
                }
            }
        }

    }
}
